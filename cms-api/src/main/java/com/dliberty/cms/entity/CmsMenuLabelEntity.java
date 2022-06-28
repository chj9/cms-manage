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
@TableName(value = "cms_menu_label", autoResultMap = true)
public class CmsMenuLabelEntity extends IdEntity {


    private String labelName;

    private String labelImg;

    private String labelUrl;


    private Integer isDeleted = Constants.COMMON_FLAG_NO;

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getLabelImg() {
        return labelImg;
    }

    public void setLabelImg(String labelImg) {
        this.labelImg = labelImg;
    }

    public String getLabelUrl() {
        return labelUrl;
    }

    public void setLabelUrl(String labelUrl) {
        this.labelUrl = labelUrl;
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }


}
