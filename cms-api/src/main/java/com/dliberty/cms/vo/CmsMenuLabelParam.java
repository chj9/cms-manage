package com.dliberty.cms.vo;

import com.dliberty.cms.common.vo.BaseVo;

import javax.validation.constraints.NotEmpty;

/**
 * 查询参数
 * @author LG
 *
 */
public class CmsMenuLabelParam extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "标签名称不能为空")
    private String labelName;

    private String labelImg;

    private String labelUrl;

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
    
    

	
	
}
