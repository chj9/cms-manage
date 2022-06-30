package com.chj9.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.chj9.cms.api.entity.CmsMenuLabelEntity;
import com.chj9.cms.common.page.PageParam;
import com.chj9.cms.common.util.RowsResultModelBuilder;
import com.chj9.cms.common.vo.JsonBean;
import com.chj9.cms.common.vo.RowsResultModel;
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

import java.util.List;

@RestController
@RequestMapping("/admin/label")
public class CmsMenuLabelController {

    private static final Logger logger = LoggerFactory.getLogger(CmsMenuLabelController.class);

    @Autowired
    private CmsMenuLabelService cmsMenuLabelService;

    @GetMapping("/listAll")
    public JsonBean listAll() {
        List<CmsMenuLabelEntity> list = cmsMenuLabelService.listAll();
        JsonBean json = new JsonBean();
        json.put("list", list);
        return json;
    }

    @GetMapping("/list/{menuId}")
    public JsonBean listByMenu(@PathVariable("menuId") Long menuId) {
        List<CmsMenuLabelEntity> list = cmsMenuLabelService.selectByMenuId(menuId);
        JsonBean json = new JsonBean();
        json.put("list", list);
        return json;
    }

    @GetMapping("/list")
    public RowsResultModel<CmsMenuLabelEntity> list(CmsMenuLabelQueryParam param, PageParam pageParam) {
        PageDTO<CmsMenuLabelEntity> listPage = cmsMenuLabelService.listPage(pageParam, param);
        return RowsResultModelBuilder.of(listPage);
    }

    @GetMapping("/delete/{id}")
    public JsonBean delete(@PathVariable("id") Long id) {
        logger.info("删除id={}的label", id);
        cmsMenuLabelService.delete(id);
        return new JsonBean();
    }

    @GetMapping("/select/{id}")
    public JsonBean select(@PathVariable("id") Long id) {
        logger.info("查找id={}的label", id);
        CmsMenuLabelEntity label = cmsMenuLabelService.getById(id);
        JsonBean json = new JsonBean();
        json.put("label", label);
        return json;
    }

    @PostMapping("/create")
    public JsonBean create(@Validated @RequestBody CmsMenuLabelParam param) {
        logger.info("创建label param={}", JSONObject.toJSONString(param));
        CmsMenuLabelEntity label = cmsMenuLabelService.create(param);
        JsonBean json = new JsonBean();
        json.put("label", label);
        return json;
    }

    @PostMapping("/update/{id}")
    public JsonBean update(@PathVariable("id") Long id, @Validated @RequestBody CmsMenuLabelParam param) {
        logger.info("修改id={}的菜谱label param={}", id, JSONObject.toJSONString(param));
        CmsMenuLabelEntity label = cmsMenuLabelService.update(id, param);
        JsonBean json = new JsonBean();
        json.put("label", label);
        return json;
    }
}
