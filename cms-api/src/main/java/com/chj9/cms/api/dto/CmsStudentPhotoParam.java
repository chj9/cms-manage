package com.chj9.cms.api.dto;


import javax.validation.constraints.NotBlank;

import com.chj9.cms.common.vo.BaseVo;

public class CmsStudentPhotoParam extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    
    @NotBlank(message = "请上传身份证正面照片")
    private String idCardFullFaceImg;
    
    @NotBlank(message = "请上传学籍头像")
    private String stuStatusImg;

	public String getIdCardFullFaceImg() {
		return idCardFullFaceImg;
	}

	public void setIdCardFullFaceImg(String idCardFullFaceImg) {
		this.idCardFullFaceImg = idCardFullFaceImg;
	}

	public String getStuStatusImg() {
		return stuStatusImg;
	}

	public void setStuStatusImg(String stuStatusImg) {
		this.stuStatusImg = stuStatusImg;
	}

    
	
    
	
    
    

}
