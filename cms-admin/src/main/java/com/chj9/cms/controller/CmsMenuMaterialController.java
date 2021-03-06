package com.chj9.cms.controller;

import com.chj9.cms.api.entity.CmsMenuMaterialEntity;
import com.chj9.cms.common.vo.JsonBean;
import com.chj9.cms.service.CmsMenuMaterialService;
import com.chj9.cms.api.vo.CmsMenuMaterialParam;
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

import java.util.List;

@RestController
@RequestMapping("/admin/material")
public class CmsMenuMaterialController {
	
	private static final Logger logger = LoggerFactory.getLogger(CmsMenuMaterialController.class);
	
	@Autowired
    CmsMenuMaterialService cmsMenuMaterialService;

	@GetMapping("/select/{menuId}")
	public JsonBean getList(@PathVariable("menuId")Long menuId) {
		List<CmsMenuMaterialEntity> materialList = cmsMenuMaterialService.selectByMenuId(menuId);
		JsonBean json = new JsonBean();
		json.put("materialList", materialList);
		return json;
	}
	
	@PostMapping("/update/{id}")
	public JsonBean update(@PathVariable("id")Long id,@Validated @RequestBody CmsMenuMaterialParam param) {
		logger.info("修改id={}的菜谱用料param={}",id,JSONObject.toJSONString(param));
		cmsMenuMaterialService.update(id, param);
		return new JsonBean();
	}
	
	
	
}
