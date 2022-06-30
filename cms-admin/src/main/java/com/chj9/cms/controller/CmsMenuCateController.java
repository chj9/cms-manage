package com.chj9.cms.controller;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.chj9.cms.api.entity.CmsMenuCategoryEntity;
import com.chj9.cms.common.page.PageParam;
import com.chj9.cms.common.util.RowsResultModelBuilder;
import com.chj9.cms.common.vo.JsonBean;
import com.chj9.cms.common.vo.RowsResultModel;
import com.chj9.cms.service.CmsMenuCategoryService;
import com.chj9.cms.api.vo.CmsMenuCateParam;
import com.chj9.cms.api.vo.CmsMenuCategoryQueryParam;
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
@RequestMapping("/admin/category")
public class CmsMenuCateController {
	
	private static final Logger logger = LoggerFactory.getLogger(CmsMenuCateController.class);
	
	@Autowired
	private CmsMenuCategoryService cmsMenuCategoryService;

	@GetMapping("/list/{parentId}")
	public JsonBean list(@PathVariable("parentId")Long parentId) {
		if (parentId == null) parentId = -1L;
		List<CmsMenuCategoryEntity> cateList = cmsMenuCategoryService.selectByParentId(parentId);
		JsonBean json = new JsonBean();
		json.put("cateList", cateList);
		return json;
	}
	
	@GetMapping("/productCategory/list/{parentId}")
	public RowsResultModel<CmsMenuCategoryEntity> productCategory(@PathVariable("parentId")Long parentId, CmsMenuCategoryQueryParam param, PageParam pageParam) {
		if (parentId == null) parentId = -1L;
		PageDTO<CmsMenuCategoryEntity> listPage = cmsMenuCategoryService.listPage(parentId,pageParam, param);
		return RowsResultModelBuilder.of(listPage);
	}
	
	@GetMapping("/delete/{id}")
	public JsonBean delete(@PathVariable("id")Integer id) {
		logger.info("删除id={}的菜谱分类",id);
		cmsMenuCategoryService.delete(id);
		return new JsonBean();
	}
	
	@GetMapping("/select/{id}")
	public JsonBean select(@PathVariable("id")Long id) {
		logger.info("查找id={}的菜谱分类",id);
		CmsMenuCategoryEntity category = cmsMenuCategoryService.getById(id);
		JsonBean json = new JsonBean();
		json.put("category", category);
		return json;
	}
	
	@PostMapping("/create")
	public JsonBean create(@Validated @RequestBody CmsMenuCateParam param) {
		logger.info("创建菜谱分类param={}",JSONObject.toJSONString(param));
		CmsMenuCategoryEntity category = cmsMenuCategoryService.create(param);
		JsonBean json = new JsonBean();
		json.put("category", category);
		return json;
	}
	
	@PostMapping("/update/{id}")
	public JsonBean update(@PathVariable("id")Integer id,@Validated @RequestBody CmsMenuCateParam param) {
		logger.info("修改id={}的菜谱分类param={}",id,JSONObject.toJSONString(param));
		CmsMenuCategoryEntity category = cmsMenuCategoryService.update(id, param);
		JsonBean json = new JsonBean();
		json.put("category", category);
		return json;
	}
}
