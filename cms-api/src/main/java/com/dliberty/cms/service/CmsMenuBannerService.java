package com.dliberty.cms.service;

import java.util.List;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dliberty.cms.dao.entity.CmsMenuBanner;
import com.dliberty.cms.vo.CmsMenuBannerParam;
import com.dliberty.cms.vo.CmsMenuBannerQueryParam;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author LG
 * @since 2019-09-03
 */
public interface CmsMenuBannerService extends IService<CmsMenuBanner> {
	
	/**
	 * 查询所有
	 * @return
	 */
	List<CmsMenuBanner> listAll(Integer bannerType);
	/**
	 * 查询所有分页
	 * @return
	 */
	IPage<CmsMenuBanner> listPage(CmsMenuBannerQueryParam param);
	
	/**
	 * 创建
	 * @param param
	 * @return
	 */
	CmsMenuBanner create(CmsMenuBannerParam param);
	
	/**
	 * 修改
	 * @param id
	 * @param param
	 * @return
	 */
	CmsMenuBanner update(Integer id,CmsMenuBannerParam param);
	
	/**
	 * 删除
	 * @param id
	 */
	void delete(Integer id);

}
