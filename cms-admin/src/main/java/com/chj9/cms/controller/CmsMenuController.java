package com.chj9.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.chj9.cms.api.entity.CmsMenuEntity;
import com.chj9.cms.common.page.PageParam;
import com.chj9.cms.common.util.RowsResultModelBuilder;
import com.chj9.cms.common.vo.JsonBean;
import com.chj9.cms.common.vo.RowsResultModel;
import com.chj9.cms.service.CmsMenuService;
import com.chj9.cms.api.vo.CmsMenuAddOrModifyParam;
import com.chj9.cms.api.vo.CmsMenuQueryParam;
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
@RequestMapping("/admin/menu")
public class CmsMenuController {
	
	private static final Logger logger = LoggerFactory.getLogger(CmsMenuController.class);
	
	@Autowired
    CmsMenuService cmsMenuService;

	@GetMapping("/list")
	public RowsResultModel<CmsMenuEntity> getList(CmsMenuQueryParam param, PageParam pageParam) {
		PageDTO<CmsMenuEntity> listPage = cmsMenuService.listPage(param,pageParam);
		return RowsResultModelBuilder.of(listPage);
	}
	
	@GetMapping("/{id}")
	public JsonBean get(@PathVariable("id")Integer id) {
		CmsMenuEntity menu = cmsMenuService.getById(id);
		JsonBean json = new JsonBean();
		json.put("menu", menu);
		return json;
	}
	
	@GetMapping("/delete/{id}")
	public JsonBean delete(@PathVariable("id")Integer id) {
		logger.info("删除id={}的菜谱",id);
		cmsMenuService.delete(id);
		return new JsonBean();
	}
	
	@PostMapping("/update/{id}")
	public JsonBean update(@PathVariable("id")Integer id,@Validated @RequestBody CmsMenuAddOrModifyParam param) {
		logger.info("修改id={}的菜谱 param={}",id,JSONObject.toJSONString(param));
		CmsMenuEntity menu = cmsMenuService.update(id, param);
		JsonBean json = new JsonBean();
		json.put("menu", menu);
		return json;
	}
	
}
