package com.dliberty.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dliberty.cms.dao.entity.CmsMenuStep;
import com.dliberty.cms.service.CmsMenuStepService;
import com.dliberty.cms.vo.JsonBean;

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
	public JsonBean view(@PathVariable("menuId") Integer menuId) {
		JsonBean json = new JsonBean();
		List<CmsMenuStep> stepList = cmsMenuStepService.selectByMenuId(menuId);
		json.put("stepList", stepList);
		return json;
	}
	
	
}
