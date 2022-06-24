package com.dliberty.cms.vo;

import java.util.List;

import javax.validation.constraints.NotEmpty;

public class CmsMenuAddOrModifyParam extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "菜谱名称不能为空")
	private String menuName;

	@NotEmpty(message = "菜谱描述不能为空")
	private String menuDesc;

	@NotEmpty(message = "菜谱主图不能为空")
	private String menuImg;

	private String menuAuth;

	private Integer browseNum;

	private Integer collectNum;

	private String menuTip;
	
	private Integer categoryId;
    
    private String categoryName;

	private String refId;
	
	private List<Integer> labels;
	

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public String getMenuImg() {
		return menuImg;
	}

	public void setMenuImg(String menuImg) {
		this.menuImg = menuImg;
	}

	public String getMenuAuth() {
		return menuAuth;
	}

	public void setMenuAuth(String menuAuth) {
		this.menuAuth = menuAuth;
	}

	public Integer getBrowseNum() {
		return browseNum;
	}

	public void setBrowseNum(Integer browseNum) {
		this.browseNum = browseNum;
	}

	public Integer getCollectNum() {
		return collectNum;
	}

	public void setCollectNum(Integer collectNum) {
		this.collectNum = collectNum;
	}

	public String getMenuTip() {
		return menuTip;
	}

	public void setMenuTip(String menuTip) {
		this.menuTip = menuTip;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public List<Integer> getLabels() {
		return labels;
	}

	public void setLabels(List<Integer> labels) {
		this.labels = labels;
	}

	

}
