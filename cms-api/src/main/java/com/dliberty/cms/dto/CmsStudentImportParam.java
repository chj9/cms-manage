package com.dliberty.cms.dto;

import javax.validation.constraints.NotNull;

import com.dliberty.cms.vo.BaseVo;

public class CmsStudentImportParam extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	@NotNull(message = "文件路径为空,请重新上传")
	private String url;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	
    
    

}
