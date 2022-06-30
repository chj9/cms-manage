package com.chj9.cms.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chj9.cms.api.entity.CmsMenuBannerEntity;
import com.chj9.cms.common.page.PageParam;
import com.chj9.cms.common.util.RowsResultModelBuilder;
import com.chj9.cms.common.vo.JsonBean;
import com.chj9.cms.common.vo.RowsResultModel;
import com.chj9.cms.service.CmsMenuBannerService;
import com.chj9.cms.api.vo.CmsMenuBannerParam;
import com.chj9.cms.api.vo.CmsMenuBannerQueryParam;
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
@RequestMapping("/admin/banner")
public class CmsMenuBannerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CmsMenuBannerController.class);
	
	@Autowired
	private CmsMenuBannerService cmsMenuBannerService;
	
	@GetMapping("/list")
	public RowsResultModel<CmsMenuBannerEntity> list(CmsMenuBannerQueryParam param, PageParam pageParam) {
		IPage<CmsMenuBannerEntity> listPage = cmsMenuBannerService.listPage(pageParam,param);
		return RowsResultModelBuilder.of(listPage);
	}
	
	@GetMapping("/delete/{id}")
	public JsonBean delete(@PathVariable("id")Integer id) {
		logger.info("删除id={}的banner",id);
		cmsMenuBannerService.delete(id);
		return new JsonBean();
	}
	
	@GetMapping("/select/{id}")
	public JsonBean select(@PathVariable("id")Integer id) {
		logger.info("查找id={}的banner",id);
		CmsMenuBannerEntity banner = cmsMenuBannerService.getById(id);
		JsonBean json = new JsonBean();
		json.put("banner", banner);
		return json;
	}
	
	@PostMapping("/create")
	public JsonBean create(@Validated @RequestBody CmsMenuBannerParam param) {
		logger.info("创建banner param={}",JSONObject.toJSONString(param));
		CmsMenuBannerEntity banner = cmsMenuBannerService.create(param);
		JsonBean json = new JsonBean();
		json.put("banner", banner);
		return json;
	}
	
	@PostMapping("/update/{id}")
	public JsonBean update(@PathVariable("id")Integer id,@Validated @RequestBody CmsMenuBannerParam param) {
		logger.info("修改id={}的菜谱banner param={}",id,JSONObject.toJSONString(param));
		CmsMenuBannerEntity banner = cmsMenuBannerService.update(id, param);
		JsonBean json = new JsonBean();
		json.put("banner", banner);
		return json;
	}
}
