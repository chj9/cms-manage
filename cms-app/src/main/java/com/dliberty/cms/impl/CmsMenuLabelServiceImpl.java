package com.dliberty.cms.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dliberty.cms.constants.Constants;
import com.dliberty.cms.dao.entity.CmsMenuLabel;
import com.dliberty.cms.dao.mapper.CmsMenuLabelMapper;
import com.dliberty.cms.service.CmsMenuLabelService;
import com.dliberty.cms.vo.CmsMenuLabelParam;
import com.dliberty.cms.vo.CmsMenuLabelQueryParam;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LG
 * @since 2019-06-13
 */
@Service
public class CmsMenuLabelServiceImpl extends ServiceImpl<CmsMenuLabelMapper, CmsMenuLabel> implements CmsMenuLabelService {

	@Override
	public List<CmsMenuLabel> selectByMenuId(Integer menuId) {
		return baseMapper.selectByMenuId(menuId);
	}
	
	@Override
	public List<CmsMenuLabel> listAll() {
		QueryWrapper<CmsMenuLabel> wrapper = new QueryWrapper<>();
		wrapper.eq("is_deleted", Constants.COMMON_FLAG_NO);
		return baseMapper.selectList(wrapper);
	}

	@Override
	public IPage<CmsMenuLabel> listPage(CmsMenuLabelQueryParam param) {
		QueryWrapper<CmsMenuLabel> wrapper = new QueryWrapper<>();
		if (StringUtils.isNotEmpty(param.getKeyword())) {
			wrapper.like("label_name", param.getKeyword());
		}
		wrapper.eq("is_deleted", Constants.COMMON_FLAG_NO);
		
		return baseMapper.selectPage(param.getPageInfo(), wrapper);
	}

	@Override
	public CmsMenuLabel create(CmsMenuLabelParam param) {
		CmsMenuLabel label = new CmsMenuLabel();
		BeanUtils.copyProperties(param, label);
		label.setUpdateTime(new Date());
		label.setCreateTime(new Date());
		label.setIsDeleted(Constants.COMMON_FLAG_NO);
		save(label);
		return label;
	}

	@Override
	public CmsMenuLabel update(Integer id, CmsMenuLabelParam param) {
		CmsMenuLabel label = getById(id);
		if (label != null) {
			BeanUtils.copyProperties(param, label);
			label.setUpdateTime(new Date());
			updateById(label);
		}
		return label;
	}

	@Override
	public void delete(Integer id) {
		CmsMenuLabel label = getById(id);
		if (label != null) {
			label.setUpdateTime(new Date());
			label.setIsDeleted(Constants.COMMON_FLAG_YES);
			updateById(label);
			
		}
		
	}


}
