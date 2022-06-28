package com.chj9.cms.menu.controller;

import java.util.List;

import com.chj9.cms.common.vo.JsonBean;
import com.chj9.cms.api.entity.CmsMenuCategoryEntity;
import com.chj9.cms.service.CmsMenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
