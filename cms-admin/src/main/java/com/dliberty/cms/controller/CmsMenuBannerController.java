package com.dliberty.cms.controller;

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
import com.dliberty.cms.dao.entity.CmsMenuBanner;
import com.dliberty.cms.service.CmsMenuBannerService;
import com.dliberty.cms.util.RowsResultModelBuilder;
import com.dliberty.cms.vo.CmsMenuBannerParam;
import com.dliberty.cms.vo.CmsMenuBannerQueryParam;
import com.dliberty.cms.vo.JsonBean;
import com.dliberty.cms.vo.PageInfo;
import com.dliberty.cms.vo.RowsResultModel;

@RestController
@RequestMapping("/admin/banner")
public class CmsMenuBannerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CmsMenuBannerController.class);
	
	@Autowired
	private CmsMenuBannerService cmsMenuBannerService;
	
	@GetMapping("/list")
	public RowsResultModel<CmsMenuBanner> list(CmsMenuBannerQueryParam param,PageInfo pageInfo) {
		param.setPageInfo(pageInfo);
		IPage<CmsMenuBanner> listPage = cmsMenuBannerService.listPage(param);
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
		CmsMenuBanner banner = cmsMenuBannerService.getById(id);
		JsonBean json = new JsonBean();
		json.put("banner", banner);
		return json;
	}
	
	@PostMapping("/create")
	public JsonBean create(@Validated @RequestBody CmsMenuBannerParam param) {
		logger.info("创建banner param={}",JSONObject.toJSONString(param));
		CmsMenuBanner banner = cmsMenuBannerService.create(param);
		JsonBean json = new JsonBean();
		json.put("banner", banner);
		return json;
	}
	
	@PostMapping("/update/{id}")
	public JsonBean update(@PathVariable("id")Integer id,@Validated @RequestBody CmsMenuBannerParam param) {
		logger.info("修改id={}的菜谱banner param={}",id,JSONObject.toJSONString(param));
		CmsMenuBanner banner = cmsMenuBannerService.update(id, param);
		JsonBean json = new JsonBean();
		json.put("banner", banner);
		return json;
	}
}
