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
@TableName(value="cms_menu_step", autoResultMap = true)
public class CmsMenuStepEntity extends IdEntity {

    private Long menuId;

    private Integer stepIndex;

    private String stepDesc;

    private String stepImg;

    private Integer isDeleted = Constants.COMMON_FLAG_NO;

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    public Integer getStepIndex() {
        return stepIndex;
    }

    public void setStepIndex(Integer stepIndex) {
        this.stepIndex = stepIndex;
    }
    public String getStepDesc() {
        return stepDesc;
    }

    public void setStepDesc(String stepDesc) {
        this.stepDesc = stepDesc;
    }
    public String getStepImg() {
        return stepImg;
    }

    public void setStepImg(String stepImg) {
        this.stepImg = stepImg;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "CmsMenuStep{" +
        ", menuId=" + menuId +
        ", stepIndex=" + stepIndex +
        ", stepDesc=" + stepDesc +
        ", stepImg=" + stepImg +
        ", isDeleted=" + isDeleted +
        "}";
    }
}
