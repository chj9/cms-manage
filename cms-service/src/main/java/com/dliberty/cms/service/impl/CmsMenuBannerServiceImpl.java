package com.dliberty.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dliberty.cms.constants.Constants;
import com.dliberty.cms.dao.entity.CmsMenuBanner;
import com.dliberty.cms.dao.mapper.CmsMenuBannerMapper;
import com.dliberty.cms.service.CmsMenuBannerService;
import com.dliberty.cms.vo.CmsMenuBannerParam;
import com.dliberty.cms.vo.CmsMenuBannerQueryParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LG
 * @since 2019-09-03
 */
@Service
public class CmsMenuBannerServiceImpl extends ServiceImpl<CmsMenuBannerMapper, CmsMenuBanner> implements CmsMenuBannerService {
	
	@Override
	public List<CmsMenuBanner> listAll(Integer bannerType) {
		QueryWrapper<CmsMenuBanner> wrapper = new QueryWrapper<>();
		wrapper.eq("is_deleted", Constants.COMMON_FLAG_NO);
		wrapper.eq("banner_type", bannerType);
		return baseMapper.selectList(wrapper);
	}

	@Override
	public IPage<CmsMenuBanner> listPage(CmsMenuBannerQueryParam param) {
		QueryWrapper<CmsMenuBanner> wrapper = new QueryWrapper<>();
		if (StringUtils.isNotEmpty(param.getKeyword())) {
			wrapper.like("banner_name", param.getKeyword());
		}
		if (param.getBannerType() != null) {
			wrapper.eq("banner_type", param.getBannerType());
		}
		wrapper.eq("is_deleted", Constants.COMMON_FLAG_NO);
		
		return baseMapper.selectPage(param.getPageInfo(), wrapper);
	}

	@Override
	public CmsMenuBanner create(CmsMenuBannerParam param) {
		CmsMenuBanner banner = new CmsMenuBanner();
		BeanUtils.copyProperties(param, banner);
		banner.setUpdateTime(new Date());
		banner.setCreateTime(new Date());
		banner.setIsDeleted(Constants.COMMON_FLAG_NO);
		save(banner);
		return banner;
	}

	@Override
	public CmsMenuBanner update(Integer id, CmsMenuBannerParam param) {
		CmsMenuBanner banner = getById(id);
		if (banner != null) {
			BeanUtils.copyProperties(param, banner);
			banner.setUpdateTime(new Date());
			updateById(banner);
		}
		return banner;
	}

	@Override
	public void delete(Integer id) {
		CmsMenuBanner banner = getById(id);
		if (banner != null) {
			banner.setUpdateTime(new Date());
			banner.setIsDeleted(Constants.COMMON_FLAG_YES);
			updateById(banner);
			
		}
	}

}
