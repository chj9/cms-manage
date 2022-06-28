package com.dliberty.cms.controller;

import java.util.List;

import com.dliberty.cms.common.vo.JsonBean;
import com.dliberty.cms.entity.CmsMenuCategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dliberty.cms.service.CmsMenuCategoryService;

@RestController
@RequestMapping("/menu/cate")
public class CmsMenuCategoryController extends BaseController {

	@Autowired
	private CmsMenuCategoryService cmsMenuCategoryService;
	
	
	
	@GetMapping(value = "/view/{parentId}")
	public JsonBean view(@PathVariable("parentId") Long parentId) {
		List<CmsMenuCategoryEntity> cates = cmsMenuCategoryService.selectByParentId(parentId);
		JsonBean json = new JsonBean();
		json.put("cates", cates);
		return json;
	}
	
	
}
