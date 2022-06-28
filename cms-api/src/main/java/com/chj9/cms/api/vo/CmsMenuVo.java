package com.chj9.cms.api.vo;

import com.chj9.cms.api.entity.CmsMenuLabelEntity;
import com.chj9.cms.api.entity.CmsMenuMaterialEntity;
import com.chj9.cms.api.entity.CmsMenuStepEntity;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;


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


    private Instant createTime;

    private Instant updateTime;


    private List<CmsMenuStepEntity> stepList;

    private List<CmsMenuMaterialEntity> materialList;

    private List<CmsMenuLabelEntity> labelList;

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

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    public List<CmsMenuLabelEntity> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<CmsMenuLabelEntity> labelList) {
        this.labelList = labelList;
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

}
