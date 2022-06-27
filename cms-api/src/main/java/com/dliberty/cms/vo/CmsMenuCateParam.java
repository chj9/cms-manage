package com.dliberty.cms.vo;

import com.dliberty.cms.common.vo.BaseVo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CmsMenuCateParam extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty(message="分类名称不能为空")
	private String categoryName;

    private String categoryCode;

    @NotNull(message="父级不能为空")
    private Integer parentId;
    
    private Integer sort;

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
    
    
	

}
