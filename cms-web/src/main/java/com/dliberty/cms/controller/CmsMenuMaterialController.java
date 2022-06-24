package com.dliberty.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dliberty.cms.dao.entity.CmsMenuMaterial;
import com.dliberty.cms.service.CmsMenuMaterialService;
import com.dliberty.cms.vo.JsonBean;

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
	public JsonBean view(@PathVariable("menuId") Integer menuId) {
		JsonBean json = new JsonBean();
		List<CmsMenuMaterial> materialList = cmsMenuMaterialService.selectByMenuId(menuId);
		json.put("materialList", materialList);
		return json;
	}
	
	
}
