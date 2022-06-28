package com.chj9.cms.api.vo;

import com.chj9.cms.common.vo.BaseVo;

import java.util.List;

public class CmsMenuStepParam extends BaseVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<CmsMenuStepItemVo> stepList;

	public List<CmsMenuStepItemVo> getStepList() {
		return stepList;
	}

	public void setStepList(List<CmsMenuStepItemVo> stepList) {
		this.stepList = stepList;
	}
	
	
	

}
