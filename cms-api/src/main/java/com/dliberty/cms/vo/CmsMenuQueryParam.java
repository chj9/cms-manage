package com.dliberty.cms.vo;

/**
 * 查询参数
 * @author LG
 *
 */
public class CmsMenuQueryParam extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String keyword;
	
	private String categoryId;
	
	private String labelId;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}
}
