package com.dliberty.cms.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dliberty.cms.base.IdEntity;
import com.dliberty.cms.common.constants.Constants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author LG
 * @since 2019-06-13
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@TableName(value = "cms_menu", autoResultMap = true)
public class CmsMenuEntity extends IdEntity {

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

    private Integer isDeleted = Constants.COMMON_FLAG_NO;

    /**
     * 所属标签，暂时不会查出来
     */
    @TableField(exist = false)
    private List<Integer> labels = new ArrayList<Integer>();

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

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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
