package com.chj9.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chj9.cms.common.constants.Constants;
import com.chj9.cms.common.page.PageParam;
import com.chj9.cms.dao.mapper.CmsMenuBannerMapper;
import com.chj9.cms.api.entity.CmsMenuBannerEntity;
import com.chj9.cms.service.CmsMenuBannerService;
import com.chj9.cms.api.vo.CmsMenuBannerParam;
import com.chj9.cms.api.vo.CmsMenuBannerQueryParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LG
 * @since 2019-09-03
 */
@Service
public class CmsMenuBannerServiceImpl extends ServiceImpl<CmsMenuBannerMapper, CmsMenuBannerEntity> implements CmsMenuBannerService {

    @Override
    public List<CmsMenuBannerEntity> listAll(Integer bannerType) {
        QueryWrapper<CmsMenuBannerEntity> wrapper = new QueryWrapper<>();
        wrapper.eq("is_deleted", Constants.COMMON_FLAG_NO);
        wrapper.eq("banner_type", bannerType);
        return baseMapper.selectList(wrapper);
    }

    @Override
    public PageDTO<CmsMenuBannerEntity> listPage(PageParam pageParam, CmsMenuBannerQueryParam param) {
        QueryWrapper<CmsMenuBannerEntity> wrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(param.getKeyword())) {
            wrapper.like("banner_name", param.getKeyword());
        }
        if (param.getBannerType() != null) {
            wrapper.eq("banner_type", param.getBannerType());
        }
        wrapper.eq("is_deleted", Constants.COMMON_FLAG_NO);

        return baseMapper.selectPage(new PageDTO<>(pageParam.getCurrent(), pageParam.getSize()), wrapper);
    }

    @Override
    public CmsMenuBannerEntity create(CmsMenuBannerParam param) {
        CmsMenuBannerEntity banner = new CmsMenuBannerEntity();
        BeanUtils.copyProperties(param, banner);
        save(banner);
        return banner;
    }

    @Override
    public CmsMenuBannerEntity update(Integer id, CmsMenuBannerParam param) {
        CmsMenuBannerEntity banner = getById(id);
        if (banner != null) {
            BeanUtils.copyProperties(param, banner);
            updateById(banner);
        }
        return banner;
    }

    @Override
    public void delete(Integer id) {
        CmsMenuBannerEntity banner = getById(id);
        if (banner != null) {
            banner.setIsDeleted(Constants.COMMON_FLAG_YES);
            updateById(banner);

        }
    }

}
