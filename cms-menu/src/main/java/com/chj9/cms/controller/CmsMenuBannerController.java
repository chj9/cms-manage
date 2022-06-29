package com.chj9.cms.controller;

import java.util.List;

import com.chj9.cms.common.vo.JsonBean;
import com.chj9.cms.api.entity.CmsMenuBannerEntity;
import com.chj9.cms.service.CmsMenuBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banner")
public class CmsMenuBannerController {
	
	@Autowired
	private CmsMenuBannerService cmsMenuBannerService;

	@GetMapping("/list/{bannerType}")
	public JsonBean listAll(@PathVariable("bannerType") Integer bannerType) {
		JsonBean json = new JsonBean();
		List<CmsMenuBannerEntity> listAll = cmsMenuBannerService.listAll(bannerType);
		json.put("list", listAll);
		return json;
	}
}
