package com.chj9.cms.api.vo;

import com.chj9.cms.common.vo.BaseVo;

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
