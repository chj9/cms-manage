package com.chj9.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chj9.cms.common.constants.Constants;
import com.chj9.cms.dao.mapper.CmsMenuMaterialMapper;
import com.chj9.cms.api.entity.CmsMenuMaterialEntity;
import com.chj9.cms.service.CmsMenuMaterialService;
import com.chj9.cms.api.vo.CmsMenuMaterialParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LG
 * @since 2019-06-13
 */
@Service
public class CmsMenuMaterialServiceImpl extends ServiceImpl<CmsMenuMaterialMapper, CmsMenuMaterialEntity> implements CmsMenuMaterialService {

	@Override
	public void saveMaterial(List<CmsMenuMaterialEntity> materialList, Long menuId) {
		if (materialList == null || materialList.size() == 0 || menuId == null) {
			return;
		}
		materialList.forEach(item -> {
			item.setMenuId(menuId);
		});
		saveBatch(materialList);
	}

	@Override
	public List<CmsMenuMaterialEntity> selectByMenuId(Long menuId) {
		QueryWrapper<CmsMenuMaterialEntity> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("menu_id", menuId);
		queryWrapper.eq("is_deleted", Constants.COMMON_FLAG_NO);
		List<CmsMenuMaterialEntity> materialList = baseMapper.selectList(queryWrapper);
		return materialList;
	}
	
	@Override
	public void deleteByMenuId(Long menuId) {
		List<CmsMenuMaterialEntity> materialList = selectByMenuId(menuId);
		materialList.forEach(item -> {
			item.setIsDeleted(Constants.COMMON_FLAG_YES);
		});
		updateBatchById(materialList);
	}

	@Override
	@Transactional
	public void update(Long menuId, CmsMenuMaterialParam param) {
		if (menuId == null) {
			return;
		}
		if (param.getMaterialList() == null || param.getMaterialList().size() == 0) {
			return;
		}
		//删除之前
		deleteByMenuId(menuId);
		List<CmsMenuMaterialEntity> materialList = new ArrayList<CmsMenuMaterialEntity>();
		param.getMaterialList().forEach(item -> {
			CmsMenuMaterialEntity material = new CmsMenuMaterialEntity();
			material.setMenuId(menuId);
			material.setMaterialName(item.getMaterialName());
			material.setMaterialDesc(item.getMaterialDesc());
			materialList.add(material);
		});
		saveBatch(materialList);
	}

}
