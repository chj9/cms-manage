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
 * @since 2019-06-13
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@TableName(value = "cms_menu_material", autoResultMap = true)
public class CmsMenuMaterialEntity extends IdEntity {


    private Long menuId;

    private String materialName;

    private String materialDesc;

    private String materialUrl;

    private Integer isDeleted = Constants.COMMON_FLAG_NO;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialDesc() {
        return materialDesc;
    }

    public void setMaterialDesc(String materialDesc) {
        this.materialDesc = materialDesc;
    }

    public String getMaterialUrl() {
        return materialUrl;
    }

    public void setMaterialUrl(String materialUrl) {
        this.materialUrl = materialUrl;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "CmsMenuMaterial{" +
                ", menuId=" + menuId +
                ", materialName=" + materialName +
                ", materialDesc=" + materialDesc +
                ", materialUrl=" + materialUrl +
                ", isDeleted=" + isDeleted +
                "}";
    }
}
