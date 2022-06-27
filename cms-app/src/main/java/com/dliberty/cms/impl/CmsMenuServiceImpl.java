package com.dliberty.cms.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dliberty.cms.constants.Constants;
import com.dliberty.cms.dao.entity.CmsMenu;
import com.dliberty.cms.dao.entity.CmsMenuLabel;
import com.dliberty.cms.dao.entity.CmsMenuMaterial;
import com.dliberty.cms.dao.entity.CmsMenuStep;
import com.dliberty.cms.dao.mapper.CmsMenuMapper;
import com.dliberty.cms.es.CmsMenuEsService;
import com.dliberty.cms.exception.CommonException;
import com.dliberty.cms.lang.data.IntUtils;
import com.dliberty.cms.service.CmsMenuCollectionService;
import com.dliberty.cms.service.CmsMenuLabelRelationService;
import com.dliberty.cms.service.CmsMenuLabelService;
import com.dliberty.cms.service.CmsMenuMaterialService;
import com.dliberty.cms.service.CmsMenuService;
import com.dliberty.cms.service.CmsMenuStepService;
import com.dliberty.cms.service.FileService;
import com.dliberty.cms.vo.CmsMenuAddOrModifyParam;
import com.dliberty.cms.vo.CmsMenuParam;
import com.dliberty.cms.vo.CmsMenuQueryParam;
import com.dliberty.cms.vo.CmsMenuVo;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LG
 * @since 2019-06-13
 */
@Service
public class CmsMenuServiceImpl extends ServiceImpl<CmsMenuMapper, CmsMenu> implements CmsMenuService {

	@Autowired
	private CmsMenuStepService cmsMenuStepService;
	@Autowired
	private CmsMenuMaterialService cmsMenuMaterialService;
	@Autowired
	private CmsMenuEsService cmsMenuEsService;
	@Autowired
	private CmsMenuCollectionService cmsMenuCollectionService;
	@Autowired
	private FileService fileService;
	@Autowired
	private CmsMenuLabelService cmsMenuLabelService;
	@Autowired
	private CmsMenuLabelRelationService cmsMenuLabelRelationService;

	private final ExecutorService executor = Executors.newCachedThreadPool();
	
	@Override
	public PageDTO<CmsMenuVo> listPageEs(CmsMenuQueryParam param) {
		return cmsMenuEsService.pageQuery(param.getPageInfo().getPageNum(), param.getPageInfo().getPageSize(), param);
	}
	
	@Override
	public CmsMenuVo getPageEs(String id) {
		return cmsMenuEsService.getById(id);
	}
	
	/**
	 * 分页查询
	 * @param param
	 * @return
	 */
	@Override
	public IPage<CmsMenu> listPage(CmsMenuQueryParam param) {
		LambdaQueryWrapper<CmsMenu> wrapper = new LambdaQueryWrapper<>();
		if (StringUtils.isNotEmpty(param.getKeyword())) {
			wrapper.like(CmsMenu::getMenuName, param.getKeyword());
		}
		wrapper.eq(CmsMenu::getIsDeleted,Constants.COMMON_FLAG_NO);
		return baseMapper.selectPage(param.getPageInfo(), wrapper);
	}
	
	
	
	@Override
	@Transactional
	public CmsMenu createMenu(CmsMenuParam param) {
		List<CmsMenu> selectByRefId = selectByRefId(param.getRefId());
		if (selectByRefId == null || selectByRefId.size() > 0) {
			return null;
		}
		CmsMenu menu = new CmsMenu();
		BeanUtils.copyProperties(param, menu);
		menu.setCreateTime(new Date());
		menu.setUpdateTime(new Date());
		menu.setIsDeleted(Constants.COMMON_FLAG_NO);
		menu.setBrowseNum(0);
		menu.setCollectNum(0);
		save(menu);
		
		//保存步骤
		cmsMenuStepService.saveStep(param.getStepList(), menu.getId());
		//保存用料
		cmsMenuMaterialService.saveMaterial(param.getMaterialList(), menu.getId());
		return menu;
	}

	@Override
	public List<CmsMenu> selectByRefId(String refId) {
		QueryWrapper<CmsMenu> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("ref_id", refId);
		queryWrapper.eq("is_deleted", Constants.COMMON_FLAG_NO);
		return baseMapper.selectList(queryWrapper);
	}

	@Override
	public void browse(Integer menuId) {
		
		executor.execute(new Runnable() {
			@Override
			public void run() {
				if (menuId == null) {
					return;
				}
				CmsMenu menu = getById(menuId);
				menu.setBrowseNum(IntUtils.defaultInt(menu.getBrowseNum(), 0) + 1);
				menu.setUpdateTime(new Date());
				updateById(menu);
				
				//更新搜索引擎
				updateEs(menu);
			}
		});
	}

	@Override
	public void collection(Integer menuId, Integer num) {
		executor.execute(new Runnable() {
			@Override
			public void run() {
				if (menuId == null || num == null) {
					return;
				}
				CmsMenu menu = getById(menuId);
				if (menu == null) {
					return;
				}
				menu.setCollectNum(IntUtils.defaultInt(menu.getCollectNum(), 0) + IntUtils.defaultInt(num, 0));
				menu.setUpdateTime(new Date());
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
		List<String> ids = cmsMenuCollectionService.selectByUserId(userId).stream().map(item ->{
			return String.valueOf(item);
		}).collect(Collectors.toList());
		final List<CmsMenuVo> menuList = new ArrayList<>();
		Optional.ofNullable(ids).ifPresent(
			(list) -> {
				menuList.addAll(cmsMenuEsService.getByIds(list));
			}
		);
		return menuList;
	}

	@Override
	public void updateEs(CmsMenu menu) {
		if (menu == null) {
			return;
		}
		
		executor.execute(new Runnable() {
			@Override
			public void run() {
				CmsMenuVo vo = new CmsMenuVo();
				BeanUtils.copyProperties(menu, vo);
				List<CmsMenuStep> stepList = cmsMenuStepService.selectByMenuId(menu.getId());
				vo.setStepList(stepList);
				List<CmsMenuMaterial> materialList = cmsMenuMaterialService.selectByMenuId(menu.getId());
				vo.setMaterialList(materialList);
				List<CmsMenuLabel> labelList = cmsMenuLabelService.selectByMenuId(menu.getId());
				vo.setLabels(labelList);
				
				cmsMenuEsService.save(vo);
			}
		});
		
		
	}

	@Override
	public void delete(Integer id) {
		CmsMenu menu = getById(id);
		if (menu == null) {
			throw new CommonException("删除失败，删除数据不存在");
		}
		
		//deleteImg(menu);
		
		deleteEs(id);
		//删除主数据
		menu.setIsDeleted(Constants.COMMON_FLAG_YES);
		menu.setUpdateTime(new Date());
		updateById(menu);
		//删除步骤
		//删除材料
		//删除标签关联关系
	}
	
	/**
	 * 删除图片
	 * @param menu
	 */
	@SuppressWarnings("unused")
	private void deleteImg(CmsMenu menu) {
		executor.execute(new Runnable() {
			@Override
			public void run() {
				//删除主图
				if (StringUtils.isNotEmpty(menu.getMenuImg())) {
					fileService.delFile(menu.getMenuImg());
				}
				List<CmsMenuStep> stepList = cmsMenuStepService.selectByMenuId(menu.getId());
				if (stepList != null && stepList.size() > 0) {
					stepList.stream().forEach(step -> {
						Optional.ofNullable(step.getStepImg()).ifPresent(img -> {
							fileService.delFile(img);
						});
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
				cmsMenuEsService.deleteById(id+"");
			}
		});
		
	}

	@Override
	@Transactional
	public CmsMenu update(Integer id, CmsMenuAddOrModifyParam param) {
		CmsMenu menu = getById(id);
		if (menu != null) {
			BeanUtils.copyProperties(param, menu);
			menu.setUpdateTime(new Date());
			updateById(menu);
			
			//保存标签
			saveLabels(menu.getId(), menu.getLabels());
		}
		
		updateEs(menu);
		return menu;
	}
	
	private void saveLabels(Integer menuId,List<Integer> labels) {
		cmsMenuLabelRelationService.deleteByMenuId(menuId);
		cmsMenuLabelRelationService.save(menuId, labels);
	}
	

}
