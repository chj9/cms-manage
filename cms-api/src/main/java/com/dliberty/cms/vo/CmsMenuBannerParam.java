package com.dliberty.cms.vo;

import com.dliberty.cms.common.vo.BaseVo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CmsMenuBannerParam extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * banner名称
     */
	@NotEmpty(message="banner名称不能为空")
    private String bannerName;

    /**
     * banner跳转链接
     */
	@NotEmpty(message="banner跳转链接不能为空")
    private String bannerUrl;

    /**
     * 跳转链接类型
     */
	@NotNull(message="banner跳转链接类型不能为空")
    private Integer redirectType;

    /**
     * banner图片
     */
	@NotEmpty(message="banner图片不能为空")
    private String bannerImg;

    /**
     * 排序
     */
	@NotNull(message="banner顺序不能为空")
    private Integer sort;
	
	@NotNull(message="banner类型不能为空")
	private Integer bannerType;

	public String getBannerName() {
		return bannerName;
	}

	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}

	public String getBannerUrl() {
		return bannerUrl;
	}

	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	public Integer getRedirectType() {
		return redirectType;
	}

	public void setRedirectType(Integer redirectType) {
		this.redirectType = redirectType;
	}

	public String getBannerImg() {
		return bannerImg;
	}

	public void setBannerImg(String bannerImg) {
		this.bannerImg = bannerImg;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getBannerType() {
		return bannerType;
	}

	public void setBannerType(Integer bannerType) {
		this.bannerType = bannerType;
	}
	
	
	
	

}
