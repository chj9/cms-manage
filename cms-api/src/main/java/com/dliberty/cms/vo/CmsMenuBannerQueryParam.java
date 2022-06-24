package com.dliberty.cms.vo;

/**
 * 查询参数
 * @author LG
 *
 */
public class CmsMenuBannerQueryParam extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String keyword;
	
	private Integer bannerType;
	

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getBannerType() {
		return bannerType;
	}

	public void setBannerType(Integer bannerType) {
		this.bannerType = bannerType;
	}

	
	
}
