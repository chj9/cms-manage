package com.dliberty.cms.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dliberty.cms.constants.Constants;
import com.dliberty.cms.dao.entity.CmsMenuStep;
import com.dliberty.cms.dao.mapper.CmsMenuStepMapper;
import com.dliberty.cms.service.CmsMenuService;
import com.dliberty.cms.service.CmsMenuStepService;
import com.dliberty.cms.vo.CmsMenuStepItemVo;
import com.dliberty.cms.vo.CmsMenuStepParam;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LG
 * @since 2019-06-13
 */
@Service
public class CmsMenuStepServiceImpl extends ServiceImpl<CmsMenuStepMapper, CmsMenuStep> implements CmsMenuStepService {
	
	@Autowired
	private CmsMenuService cmsMenuService;

	@Override
	public void saveStep(List<CmsMenuStep> stepList, Integer menuId) {
		if (stepList == null || stepList.size() == 0 || menuId == null) {
			return;
		}
		stepList.forEach(item -> {
			item.setMenuId(menuId);
			item.setCreateTime(new Date());
			item.setUpdateTime(new Date());
			item.setIsDeleted(Constants.COMMON_FLAG_NO);
		});
		saveBatch(stepList);
	}

	@Override
	public List<CmsMenuStep> selectByMenuId(Integer menuId) {
		QueryWrapper<CmsMenuStep> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("menu_id", menuId);
		queryWrapper.eq("is_deleted", Constants.COMMON_FLAG_NO);
		queryWrapper.orderByAsc("step_index");
		List<CmsMenuStep> stepList = baseMapper.selectList(queryWrapper);
		return stepList;
	}
	
	@Override
	public void delete(Integer menuId) {
		List<CmsMenuStep> stepList = selectByMenuId(menuId);
		stepList.stream().forEach(item -> {
			item.setIsDeleted(Constants.COMMON_FLAG_YES);
			item.setUpdateTime(new Date());
		});
		updateBatchById(stepList);
	}

	@Override
	@Transactional
	public void update(Integer menuId, CmsMenuStepParam param) {
		if (menuId == null) {
			return;
		}
		if (param.getStepList() == null || param.getStepList().size() == 0) {
			return;
		}
		//先删除之前的步骤
		delete(menuId);
		//新增
		List<CmsMenuStep> stepList = new ArrayList<CmsMenuStep>();
		int i = 1;
		for (CmsMenuStepItemVo vo : param.getStepList()) {
			CmsMenuStep step = new CmsMenuStep();
			step.setCreateTime(new Date());
			step.setUpdateTime(new Date());
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
