package com.chj9.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chj9.cms.common.constants.Constants;
import com.chj9.cms.common.page.PageParam;
import com.chj9.cms.dao.mapper.CmsMenuLabelMapper;
import com.chj9.cms.api.entity.CmsMenuLabelEntity;
import com.chj9.cms.service.CmsMenuLabelService;
import com.chj9.cms.api.vo.CmsMenuLabelParam;
import com.chj9.cms.api.vo.CmsMenuLabelQueryParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
public class CmsMenuLabelServiceImpl extends ServiceImpl<CmsMenuLabelMapper, CmsMenuLabelEntity> implements CmsMenuLabelService {

	@Override
	public List<CmsMenuLabelEntity> selectByMenuId(Long menuId) {
		return baseMapper.selectByMenuId(menuId);
	}
	
	@Override
	public List<CmsMenuLabelEntity> listAll() {
		QueryWrapper<CmsMenuLabelEntity> wrapper = new QueryWrapper<>();
		wrapper.eq("is_deleted", Constants.COMMON_FLAG_NO);
		return baseMapper.selectList(wrapper);
	}

	@Override
	public PageDTO<CmsMenuLabelEntity> listPage(PageParam pageParam, CmsMenuLabelQueryParam param) {
		LambdaQueryWrapper<CmsMenuLabelEntity> wrapper = new LambdaQueryWrapper<>();
		if (StringUtils.isNotEmpty(param.getKeyword())) {
			wrapper.like(CmsMenuLabelEntity::getLabelName, param.getKeyword());
		}
		wrapper.eq(CmsMenuLabelEntity::getIsDeleted, Constants.COMMON_FLAG_NO);
		
		return baseMapper.selectPage(new PageDTO<>(pageParam.getCurrent(), pageParam.getSize()), wrapper);
	}

	@Override
	public CmsMenuLabelEntity create(CmsMenuLabelParam param) {
		CmsMenuLabelEntity label = new CmsMenuLabelEntity();
		BeanUtils.copyProperties(param, label);
		save(label);
		return label;
	}

	@Override
	public CmsMenuLabelEntity update(Integer id, CmsMenuLabelParam param) {
		CmsMenuLabelEntity label = getById(id);
		if (label != null) {
			BeanUtils.copyProperties(param, label);
			updateById(label);
		}
		return label;
	}

	@Override
	public void delete(Integer id) {
		CmsMenuLabelEntity label = getById(id);
		if (label != null) {
			label.setIsDeleted(Constants.COMMON_FLAG_YES);
			updateById(label);
			
		}
		
	}


}
