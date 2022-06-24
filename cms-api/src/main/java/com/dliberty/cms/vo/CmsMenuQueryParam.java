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
	
	private Integer categoryId;
	
	private Integer labelId;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getLabelId() {
		return labelId;
	}

	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}
	
	
	
	
}
