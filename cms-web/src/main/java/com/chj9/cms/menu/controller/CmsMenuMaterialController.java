package com.chj9.cms.menu.controller;

import java.util.List;

import com.chj9.cms.common.vo.JsonBean;
import com.chj9.cms.api.entity.CmsMenuMaterialEntity;
import com.chj9.cms.service.CmsMenuMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 材料
 * @author LG
 *
 */
@RestController
@RequestMapping("/menu/material")
public class CmsMenuMaterialController extends BaseController {

	@Autowired
	private CmsMenuMaterialService cmsMenuMaterialService;
	
	
	/**
	 * 查询材料
	 * @param menuId
	 * @return
	 */
	@GetMapping(value = "/view/{menuId}")
	public JsonBean view(@PathVariable("menuId") Long menuId) {
		JsonBean json = new JsonBean();
		List<CmsMenuMaterialEntity> materialList = cmsMenuMaterialService.selectByMenuId(menuId);
		json.put("materialList", materialList);
		return json;
	}
	
	
}
