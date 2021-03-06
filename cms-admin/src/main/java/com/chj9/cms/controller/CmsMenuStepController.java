package com.chj9.cms.controller;

import java.util.List;

import com.chj9.cms.api.entity.CmsMenuStepEntity;
import com.chj9.cms.common.vo.JsonBean;
import com.chj9.cms.service.CmsMenuStepService;
import com.chj9.cms.api.vo.CmsMenuStepParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("/admin/step")
public class CmsMenuStepController {
	
	private static final Logger logger = LoggerFactory.getLogger(CmsMenuStepController.class);
	
	@Autowired
    CmsMenuStepService cmsMenuStepService;

	@GetMapping("/select/{menuId}")
	public JsonBean getList(@PathVariable("menuId")Long menuId) {
		List<CmsMenuStepEntity> stepList = cmsMenuStepService.selectByMenuId(menuId);
		JsonBean json = new JsonBean();
		json.put("stepList", stepList);
		return json;
	}
	
	@PostMapping("/update/{id}")
	public JsonBean update(@PathVariable("id")Long id,@Validated @RequestBody CmsMenuStepParam param) {
		logger.info("修改id={}的菜谱 步骤param={}",id,JSONObject.toJSONString(param));
		cmsMenuStepService.update(id, param);
		return new JsonBean();
	}
	
	
	
}
