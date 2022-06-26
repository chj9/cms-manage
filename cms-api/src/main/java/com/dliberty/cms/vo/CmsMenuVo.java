package com.dliberty.cms.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;

import com.dliberty.cms.dao.entity.CmsMenuLabel;
import com.dliberty.cms.dao.entity.CmsMenuMaterial;
import com.dliberty.cms.dao.entity.CmsMenuStep;

public class CmsMenuVo implements Serializable {

	private static final long serialVersionUID = 2996056237154364425L;

	private String id;

    private String menuName;

    private String menuDesc;

    private String menuImg;

    private String menuAuth;

    private Integer browseNum;

    private Integer collectNum;

    private String menuTip;
    
    private Integer categoryId;
    
    private String categoryName;


    private Date createTime;

    private Date updateTime;
    
    
    private List<CmsMenuStep> stepList;
    
    private List<CmsMenuMaterial> materialList;
    
    private List<CmsMenuLabel> labels;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<CmsMenuLabel> getLabels() {
		return labels;
	}

	public void setLabels(List<CmsMenuLabel> labels) {
		this.labels = labels;
	}

	public List<CmsMenuStep> getStepList() {
		return stepList;
	}

	public void setStepList(List<CmsMenuStep> stepList) {
		this.stepList = stepList;
	}

	public List<CmsMenuMaterial> getMaterialList() {
		return materialList;
	}

	public void setMaterialList(List<CmsMenuMaterial> materialList) {
		this.materialList = materialList;
	}

}
