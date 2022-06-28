package com.chj9.cms.api.vo;

import com.chj9.cms.common.vo.BaseVo;

public class CmsMenuMaterialItemVo extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String materialName;

    private String materialDesc;

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
    
    
	
	

}
