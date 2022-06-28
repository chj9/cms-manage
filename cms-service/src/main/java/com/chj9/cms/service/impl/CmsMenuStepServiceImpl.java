package com.chj9.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chj9.cms.common.constants.Constants;
import com.chj9.cms.dao.mapper.CmsMenuStepMapper;
import com.chj9.cms.api.entity.CmsMenuStepEntity;
import com.chj9.cms.service.CmsMenuService;
import com.chj9.cms.service.CmsMenuStepService;
import com.chj9.cms.api.vo.CmsMenuStepItemVo;
import com.chj9.cms.api.vo.CmsMenuStepParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LG
 * @since 2019-06-13
 */
@Service
public class CmsMenuStepServiceImpl extends ServiceImpl<CmsMenuStepMapper, CmsMenuStepEntity> implements CmsMenuStepService {

    private final CmsMenuService cmsMenuService;

    @Autowired
    public CmsMenuStepServiceImpl(CmsMenuService cmsMenuService) {
        this.cmsMenuService = cmsMenuService;
    }

    @Override
    public void saveStep(List<CmsMenuStepEntity> stepList, Long menuId) {
        if (stepList == null || stepList.size() == 0 || menuId == null) {
            return;
        }
        stepList.forEach(item -> {
            item.setMenuId(menuId);
        });
        saveBatch(stepList);
    }

    @Override
    public List<CmsMenuStepEntity> selectByMenuId(Long menuId) {
        LambdaQueryWrapper<CmsMenuStepEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CmsMenuStepEntity::getMenuId, menuId);
        queryWrapper.eq(CmsMenuStepEntity::getIsDeleted, Constants.COMMON_FLAG_NO);
        queryWrapper.orderByAsc(CmsMenuStepEntity::getStepIndex);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public void delete(Long menuId) {
        List<CmsMenuStepEntity> stepList = selectByMenuId(menuId);
        stepList.forEach(item -> {
            item.setIsDeleted(Constants.COMMON_FLAG_YES);
        });
        updateBatchById(stepList);
    }

    @Override
    @Transactional
    public void update(Long menuId, CmsMenuStepParam param) {
        if (menuId == null) {
            return;
        }
        if (param.getStepList() == null || param.getStepList().size() == 0) {
            return;
        }
        //先删除之前的步骤
        delete(menuId);
        //新增
        List<CmsMenuStepEntity> stepList = new ArrayList<CmsMenuStepEntity>();
        int i = 1;
        for (CmsMenuStepItemVo vo : param.getStepList()) {
            CmsMenuStepEntity step = new CmsMenuStepEntity();
            step.setMenuId(menuId);
            step.setStepIndex(i);
            step.setStepDesc(vo.getStepDesc());
            step.setStepImg(vo.getStepImg());
            step.setIsDeleted(Constants.COMMON_FLAG_NO);
            stepList.add(step);
            i++;
        }
        saveBatch(stepList);

        //更新搜索引擎
        cmsMenuService.updateEs(cmsMenuService.getById(menuId));
    }

}
