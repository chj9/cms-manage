package com.dliberty.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dliberty.cms.constants.Constants;
import com.dliberty.cms.dao.entity.CmsMenuMaterial;
import com.dliberty.cms.dao.mapper.CmsMenuMaterialMapper;
import com.dliberty.cms.service.CmsMenuMaterialService;
import com.dliberty.cms.vo.CmsMenuMaterialParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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
public class CmsMenuMaterialServiceImpl extends ServiceImpl<CmsMenuMaterialMapper, CmsMenuMaterial> implements CmsMenuMaterialService {

	@Override
	public void saveMaterial(List<CmsMenuMaterial> materialList, Long menuId) {
		if (materialList == null || materialList.size() == 0 || menuId == null) {
			return;
		}
		materialList.forEach(item -> {
			item.setMenuId(menuId);
			item.setCreateTime(new Date());
			item.setUpdateTime(new Date());
			item.setIsDeleted(Constants.COMMON_FLAG_NO);
		});
		saveBatch(materialList);
	}

	@Override
	public List<CmsMenuMaterial> selectByMenuId(Long menuId) {
		QueryWrapper<CmsMenuMaterial> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("menu_id", menuId);
		queryWrapper.eq("is_deleted", Constants.COMMON_FLAG_NO);
		List<CmsMenuMaterial> materialList = baseMapper.selectList(queryWrapper);
		return materialList;
	}
	
	@Override
	public void deleteByMenuId(Long menuId) {
		List<CmsMenuMaterial> materialList = selectByMenuId(menuId);
		materialList.stream().forEach(item -> {
			item.setIsDeleted(Constants.COMMON_FLAG_YES);
			item.setUpdateTime(new Date());
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
		List<CmsMenuMaterial> materialList = new ArrayList<CmsMenuMaterial>();
		param.getMaterialList().forEach(item -> {
			CmsMenuMaterial material = new CmsMenuMaterial();
			material.setIsDeleted(Constants.COMMON_FLAG_NO);
			material.setMenuId(menuId);
			material.setMaterialName(item.getMaterialName());
			material.setMaterialDesc(item.getMaterialDesc());
			material.setCreateTime(new Date());
			material.setUpdateTime(new Date());
			materialList.add(material);
		});
		saveBatch(materialList);
	}

}
