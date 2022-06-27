package com.dliberty.cms.vo;

import java.util.List;

import com.dliberty.cms.common.vo.BaseVo;
import com.dliberty.cms.entity.CmsMenuLabelEntity;
import com.dliberty.cms.entity.CmsMenuMaterialEntity;
import com.dliberty.cms.entity.CmsMenuStepEntity;

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
	
	private Integer categoryId;
    
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
	
	

}
