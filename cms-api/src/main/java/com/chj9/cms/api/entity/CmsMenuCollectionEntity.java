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
 * @since 2019-06-14
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@TableName(value = "cms_menu_collection", autoResultMap = true)
public class CmsMenuCollectionEntity extends IdEntity {


    private Long userId;

    private Long menuId;

    private Integer isDeleted = Constants.COMMON_FLAG_NO;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "CmsMenuCollection{" +
                ", userId=" + userId +
                ", menuId=" + menuId +
                ", isDeleted=" + isDeleted +
                "}";
    }
}
