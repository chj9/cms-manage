package com.chj9.cms.api.dto;

import java.io.Serializable;

/**
 * 查询dto
 * @author LG
 *
 */
public class CmsMenuQueryDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
private String keyword;
	
	private Integer categoryId;

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

}
