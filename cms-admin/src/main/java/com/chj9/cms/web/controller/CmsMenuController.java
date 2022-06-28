package com.chj9.cms.web.controller;

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
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dliberty.cms.dao.entity.CmsMenu;
import com.dliberty.cms.util.RowsResultModelBuilder;
import com.dliberty.cms.vo.JsonBean;
import com.dliberty.cms.vo.PageInfo;
import com.dliberty.cms.vo.RowsResultModel;

@RestController
@RequestMapping("/admin/menu")
public class CmsMenuController {
	
	private static final Logger logger = LoggerFactory.getLogger(CmsMenuController.class);
	
	@Autowired
    CmsMenuService cmsMenuService;

	@GetMapping("/list")
	public RowsResultModel<CmsMenu> getList(CmsMenuQueryParam param, PageInfo pageInfo) {
		param.setPageInfo(pageInfo);
		IPage<CmsMenu> listPage = cmsMenuService.listPage(param);
		return RowsResultModelBuilder.of(listPage);
	}
	
	@GetMapping("/{id}")
	public JsonBean get(@PathVariable("id")Integer id) {
		CmsMenu menu = cmsMenuService.getById(id);
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
		CmsMenu menu = cmsMenuService.update(id, param);
		JsonBean json = new JsonBean();
		json.put("menu", menu);
		return json;
	}
	
}
