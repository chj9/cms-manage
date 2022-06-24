package com.dliberty.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dliberty.cms.dao.entity.CmsMenuBanner;
import com.dliberty.cms.service.CmsMenuBannerService;
import com.dliberty.cms.vo.JsonBean;

@RestController
@RequestMapping("/banner")
public class CmsMenuBannerController {
	
	@Autowired
	private CmsMenuBannerService cmsMenuBannerService;

	@GetMapping("/list/{bannerType}")
	public JsonBean listAll(@PathVariable("bannerType") Integer bannerType) {
		JsonBean json = new JsonBean();
		List<CmsMenuBanner> listAll = cmsMenuBannerService.listAll(bannerType);
		json.put("list", listAll);
		return json;
	}
}
