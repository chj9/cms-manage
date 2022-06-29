package com.chj9.cms.controller;


import com.chj9.cms.common.vo.JsonBean;
import com.chj9.cms.api.entity.CmsMenuStepEntity;
import com.chj9.cms.service.CmsMenuStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu/step")
public class CmsMenuStepController extends BaseController {

	@Autowired
	private CmsMenuStepService cmsMenuStepService;
	
	
	/**
	 * 查询步骤
	 * @param menuId
	 * @return
	 */
	@GetMapping(value = "/view/{menuId}")
	public JsonBean view(@PathVariable("menuId") Long menuId) {
		JsonBean json = new JsonBean();
		List<CmsMenuStepEntity> stepList = cmsMenuStepService.selectByMenuId(menuId);
		json.put("stepList", stepList);
		return json;
	}
	
	
}
