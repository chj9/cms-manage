package com.chj9.cms.web.controller;

import java.util.List;

import com.chj9.cms.service.CmsMenuLabelService;
import com.chj9.cms.api.vo.CmsMenuLabelParam;
import com.chj9.cms.api.vo.CmsMenuLabelQueryParam;
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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dliberty.cms.dao.entity.CmsMenuLabel;
import com.dliberty.cms.util.RowsResultModelBuilder;
import com.dliberty.cms.vo.JsonBean;
import com.dliberty.cms.vo.PageInfo;
import com.dliberty.cms.vo.RowsResultModel;

@RestController
@RequestMapping("/admin/label")
public class CmsMenuLabelController {
	
	private static final Logger logger = LoggerFactory.getLogger(CmsMenuLabelController.class);
	
	@Autowired
	private CmsMenuLabelService cmsMenuLabelService;
	
	@GetMapping("/listAll")
	public JsonBean listAll() {
		List<CmsMenuLabel> list = cmsMenuLabelService.listAll();
		JsonBean json = new JsonBean();
		json.put("list", list);
		return json; 
	}
	
	@GetMapping("/list/{menuId}")
	public JsonBean listByMenu(@PathVariable("menuId")Integer menuId) {
		List<CmsMenuLabel> list = cmsMenuLabelService.selectByMenuId(menuId);
		JsonBean json = new JsonBean();
		json.put("list", list);
		return json; 
	}
	
	@GetMapping("/list")
	public RowsResultModel<CmsMenuLabel> list(CmsMenuLabelQueryParam param, PageInfo pageInfo) {
		param.setPageInfo(pageInfo);
		IPage<CmsMenuLabel> listPage = cmsMenuLabelService.listPage(param);
		return RowsResultModelBuilder.of(listPage);
	}
	
	@GetMapping("/delete/{id}")
	public JsonBean delete(@PathVariable("id")Integer id) {
		logger.info("删除id={}的label",id);
		cmsMenuLabelService.delete(id);
		return new JsonBean();
	}
	
	@GetMapping("/select/{id}")
	public JsonBean select(@PathVariable("id")Integer id) {
		logger.info("查找id={}的label",id);
		CmsMenuLabel label = cmsMenuLabelService.getById(id);
		JsonBean json = new JsonBean();
		json.put("label", label);
		return json;
	}
	
	@PostMapping("/create")
	public JsonBean create(@Validated @RequestBody CmsMenuLabelParam param) {
		logger.info("创建label param={}",JSONObject.toJSONString(param));
		CmsMenuLabel label = cmsMenuLabelService.create(param);
		JsonBean json = new JsonBean();
		json.put("label", label);
		return json;
	}
	
	@PostMapping("/update/{id}")
	public JsonBean update(@PathVariable("id")Integer id,@Validated @RequestBody CmsMenuLabelParam param) {
		logger.info("修改id={}的菜谱label param={}",id,JSONObject.toJSONString(param));
		CmsMenuLabel label = cmsMenuLabelService.update(id, param);
		JsonBean json = new JsonBean();
		json.put("label", label);
		return json;
	}
}
