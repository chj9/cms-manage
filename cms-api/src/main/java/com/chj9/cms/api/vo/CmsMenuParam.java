package com.chj9.cms.api.vo;

import java.util.List;

import com.chj9.cms.common.vo.BaseVo;
import com.chj9.cms.api.entity.CmsMenuLabelEntity;
import com.chj9.cms.api.entity.CmsMenuMaterialEntity;
import com.chj9.cms.api.entity.CmsMenuStepEntity;

public class CmsMenuParam extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String menuName;

	private String menuDesc;

	private String menuImg;

	private String menuAuth;

	private Integer browseNum;

	private Integer collectNum;

	private String menuTip;
	
	private Long categoryId;
    
    private String categoryName;

	private String refId;
	
	private List<CmsMenuStepEntity> stepList;
	
	private List<CmsMenuMaterialEntity> materialList;
	
	private List<CmsMenuLabelEntity> labelList;

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

	public List<CmsMenuStepEntity> getStepList() {
		return stepList;
	}

	public void setStepList(List<CmsMenuStepEntity> stepList) {
		this.stepList = stepList;
	}

	public List<CmsMenuMaterialEntity> getMaterialList() {
		return materialList;
	}

	public void setMaterialList(List<CmsMenuMaterialEntity> materialList) {
		this.materialList = materialList;
	}

	public List<CmsMenuLabelEntity> getLabelList() {
		return labelList;
	}

	public void setLabelList(List<CmsMenuLabelEntity> labelList) {
		this.labelList = labelList;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	

}
