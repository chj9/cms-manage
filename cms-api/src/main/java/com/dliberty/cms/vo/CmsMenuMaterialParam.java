package com.dliberty.cms.vo;

import java.util.List;

public class CmsMenuMaterialParam extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<CmsMenuMaterialItemVo> materialList;

	public List<CmsMenuMaterialItemVo> getMaterialList() {
		return materialList;
	}

	public void setMaterialList(List<CmsMenuMaterialItemVo> materialList) {
		this.materialList = materialList;
	}


}
