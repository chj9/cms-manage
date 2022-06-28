package com.chj9.cms.api.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.chj9.cms.api.base.IdEntity;
import com.chj9.cms.common.constants.Constants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * <p>
 *
 * </p>
 *
 * @author LG
 * @since 2019-09-05
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@TableName(value = "cms_menu_label_relation", autoResultMap = true)
public class CmsMenuLabelRelationEntity extends IdEntity {

    private static final long serialVersionUID = 1L;


    private Long menuId;

    private Integer labelId;

    private Integer isDeleted = Constants.COMMON_FLAG_NO;


    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Integer getLabelId() {
        return labelId;
    }

    public void setLabelId(Integer labelId) {
        this.labelId = labelId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "CmsMenuLabelRelation{" +
                ", menuId=" + menuId +
                ", labelId=" + labelId +
                ", isDeleted=" + isDeleted +
                "}";
    }
}
