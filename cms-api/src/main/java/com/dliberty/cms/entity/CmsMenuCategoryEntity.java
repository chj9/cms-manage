package com.dliberty.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dliberty.cms.base.IdEntity;
import com.dliberty.cms.common.constants.Constants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author LG
 * @since 2019-06-13
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@TableName(value = "cms_menu_category", autoResultMap = true)
public class CmsMenuCategoryEntity extends IdEntity {


    private String categoryName;

    private String categoryCode;

    private Long parentId;

    private Integer isDeleted = Constants.COMMON_FLAG_NO;

    private Integer hasNext;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }


    public Integer getHasNext() {
        return hasNext;
    }

    public void setHasNext(Integer hasNext) {
        this.hasNext = hasNext;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }


}
