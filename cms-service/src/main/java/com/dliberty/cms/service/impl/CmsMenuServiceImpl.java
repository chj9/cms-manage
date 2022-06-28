package com.dliberty.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dliberty.cms.common.constants.Constants;
import com.dliberty.cms.common.exception.CommonException;
import com.dliberty.cms.common.page.PageParam;
import com.dliberty.cms.common.util.data.IntUtils;
import com.dliberty.cms.dao.mapper.CmsMenuCollectionMapper;
import com.dliberty.cms.dao.mapper.CmsMenuMapper;
import com.dliberty.cms.dao.mapper.CmsMenuStepMapper;
import com.dliberty.cms.entity.CmsMenuEntity;
import com.dliberty.cms.entity.CmsMenuLabelEntity;
import com.dliberty.cms.entity.CmsMenuMaterialEntity;
import com.dliberty.cms.entity.CmsMenuStepEntity;
import com.dliberty.cms.service.*;
import com.dliberty.cms.vo.CmsMenuAddOrModifyParam;
import com.dliberty.cms.vo.CmsMenuParam;
import com.dliberty.cms.vo.CmsMenuQueryParam;
import com.dliberty.cms.vo.CmsMenuVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LG
 * @since 2019-06-13
 */
@Service
public class CmsMenuServiceImpl extends ServiceImpl<CmsMenuMapper, CmsMenuEntity> implements CmsMenuService {

    private final CmsMenuStepMapper cmsMenuStepMapper;
    private final CmsMenuMaterialService cmsMenuMaterialService;
    private final CmsMenuEsService cmsMenuEsService;
    private final CmsMenuCollectionMapper cmsMenuCollectionMapper;
    private final FileService fileService;
    private final CmsMenuLabelService cmsMenuLabelService;
    private final CmsMenuLabelRelationService cmsMenuLabelRelationService;

    private final ExecutorService executor = Executors.newCachedThreadPool();

    @Autowired
    public CmsMenuServiceImpl(CmsMenuMaterialService cmsMenuMaterialService,
                              CmsMenuEsService cmsMenuEsService,
                              FileService fileService,
                              CmsMenuLabelService cmsMenuLabelService,
                              CmsMenuLabelRelationService cmsMenuLabelRelationService,
                              CmsMenuStepMapper cmsMenuStepMapper,
                              CmsMenuCollectionMapper cmsMenuCollectionMapper) {

        this.cmsMenuMaterialService = cmsMenuMaterialService;
        this.cmsMenuEsService = cmsMenuEsService;
        this.fileService = fileService;
        this.cmsMenuLabelService = cmsMenuLabelService;
        this.cmsMenuLabelRelationService = cmsMenuLabelRelationService;
        this.cmsMenuStepMapper = cmsMenuStepMapper;
        this.cmsMenuCollectionMapper = cmsMenuCollectionMapper;

    }


    @Override
    public PageDTO<CmsMenuVo> listPageEs(CmsMenuQueryParam queryParam, PageParam pageParam) {
        return cmsMenuEsService.pageQuery(pageParam, queryParam);
    }

    @Override
    public CmsMenuVo getPageEs(String id) {
        return cmsMenuEsService.getById(id);
    }

    /**
     * 分页查询
     *
     * @param param
     * @return
     */
    @Override
    public IPage<CmsMenuEntity> listPage(CmsMenuQueryParam param,PageParam pageParam) {
        LambdaQueryWrapper<CmsMenuEntity> wrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotEmpty(param.getKeyword())) {
            wrapper.like(CmsMenuEntity::getMenuName, param.getKeyword());
        }
        wrapper.eq(CmsMenuEntity::getIsDeleted, Constants.COMMON_FLAG_NO);
        return baseMapper.selectPage(new PageDTO<>(pageParam.getCurrent(), pageParam.getSize()), wrapper);
    }


    @Override
    @Transactional
    public CmsMenuEntity createMenu(CmsMenuParam param) {
        List<CmsMenuEntity> selectByRefId = selectByRefId(param.getRefId());
        if (selectByRefId == null || selectByRefId.size() > 0) {
            return null;
        }
        CmsMenuEntity menu = new CmsMenuEntity();
        BeanUtils.copyProperties(param, menu);
        menu.setBrowseNum(0);
        menu.setCollectNum(0);
        save(menu);

        //保存步骤
        List<CmsMenuStepEntity> cmsMenuStepList = param.getStepList();
        if (!CollectionUtils.isEmpty(cmsMenuStepList)) {
            cmsMenuStepList.forEach(item -> {
                item.setMenuId(menu.getId());
                cmsMenuStepMapper.insert(item);
            });
        }
        //保存用料
        cmsMenuMaterialService.saveMaterial(param.getMaterialList(), menu.getId());
        return menu;
    }

    @Override
    public List<CmsMenuEntity> selectByRefId(String refId) {
        QueryWrapper<CmsMenuEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ref_id", refId);
        queryWrapper.eq("is_deleted", Constants.COMMON_FLAG_NO);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public void browse(Long menuId) {

        executor.execute(new Runnable() {
            @Override
            public void run() {
                if (menuId == null) {
                    return;
                }
                CmsMenuEntity menu = getById(menuId);
                menu.setBrowseNum(IntUtils.defaultInt(menu.getBrowseNum(), 0) + 1);
                updateById(menu);

                //更新搜索引擎
                updateEs(menu);
            }
        });
    }

    @Override
    public void collection(Long menuId, Integer num) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                if (menuId == null || num == null) {
                    return;
                }
                CmsMenuEntity menu = getById(menuId);
                if (menu == null) {
                    return;
                }
                menu.setCollectNum(IntUtils.defaultInt(menu.getCollectNum(), 0) + IntUtils.defaultInt(num, 0));
                updateById(menu);

                //更新搜索引擎
                updateEs(menu);
            }
        });
    }

    @Override
    public List<CmsMenuVo> selectCollectionByUserId(Long userId) {
        if (userId == null) {
            return new ArrayList<>();
        }
        List<String> ids = cmsMenuCollectionMapper.selectByUserId(userId).stream().map(String::valueOf).collect(Collectors.toList());
        final List<CmsMenuVo> menuList = new ArrayList<>();
        Optional.of(ids).ifPresent(
                (list) -> {
                    menuList.addAll(cmsMenuEsService.getByIds(list));
                }
        );
        return menuList;
    }

    @Override
    public void updateEs(CmsMenuEntity menu) {
        if (menu == null) {
            return;
        }

        executor.execute(new Runnable() {
            @Override
            public void run() {
                CmsMenuVo vo = new CmsMenuVo();
                BeanUtils.copyProperties(menu, vo);

                LambdaQueryWrapper<CmsMenuStepEntity> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(CmsMenuStepEntity::getMenuId, menu.getId());
                queryWrapper.eq(CmsMenuStepEntity::getIsDeleted, Constants.COMMON_FLAG_NO);
                queryWrapper.orderByAsc(CmsMenuStepEntity::getStepIndex);
                List<CmsMenuStepEntity> stepList = cmsMenuStepMapper.selectList(queryWrapper);
                vo.setStepList(stepList);

                List<CmsMenuMaterialEntity> materialList = cmsMenuMaterialService.selectByMenuId(menu.getId());
                vo.setMaterialList(materialList);
                List<CmsMenuLabelEntity> labelList = cmsMenuLabelService.selectByMenuId(menu.getId());
                vo.setLabelList(labelList);
                cmsMenuEsService.save(vo);
            }
        });


    }

    @Override
    public void delete(Integer id) {
        CmsMenuEntity menu = getById(id);
        if (menu == null) {
            throw new CommonException("删除失败，删除数据不存在");
        }

        //deleteImg(menu);

        deleteEs(id);
        //删除主数据
        menu.setIsDeleted(Constants.COMMON_FLAG_YES);
        updateById(menu);
        //删除步骤
        //删除材料
        //删除标签关联关系
    }

    /**
     * 删除图片
     *
     * @param menu
     */
    @SuppressWarnings("unused")
    private void deleteImg(CmsMenuEntity menu) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                //删除主图
                if (StringUtils.isNotEmpty(menu.getMenuImg())) {
                    fileService.delFile(menu.getMenuImg());
                }
                LambdaQueryWrapper<CmsMenuStepEntity> queryWrapper = new LambdaQueryWrapper<>();
                queryWrapper.eq(CmsMenuStepEntity::getMenuId, menu.getId());
                queryWrapper.eq(CmsMenuStepEntity::getIsDeleted, Constants.COMMON_FLAG_NO);
                queryWrapper.orderByAsc(CmsMenuStepEntity::getStepIndex);
                List<CmsMenuStepEntity> stepList = cmsMenuStepMapper.selectList(queryWrapper);
                if (stepList != null && stepList.size() > 0) {
                    stepList.forEach(step -> {
                        Optional.ofNullable(step.getStepImg()).ifPresent(fileService::delFile);
                    });
                }
            }
        });

    }

    /**
     * 删除es
     */
    private void deleteEs(Integer id) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                cmsMenuEsService.deleteById(id + "");
            }
        });

    }

    @Override
    @Transactional
    public CmsMenuEntity update(Integer id, CmsMenuAddOrModifyParam param) {
        CmsMenuEntity menu = getById(id);
        if (menu != null) {
            BeanUtils.copyProperties(param, menu);
            updateById(menu);
            //保存标签
            saveLabels(menu.getId(), menu.getLabels());
        }

        updateEs(menu);
        return menu;
    }

    private void saveLabels(Long menuId, List<Integer> labels) {
        cmsMenuLabelRelationService.deleteByMenuId(menuId);
        cmsMenuLabelRelationService.save(menuId, labels);
    }


}
