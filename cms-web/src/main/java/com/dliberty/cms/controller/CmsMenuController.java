package com.dliberty.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.dliberty.cms.common.page.PageParam;
import com.dliberty.cms.common.page.PageResult;
import com.dliberty.cms.common.util.RowsResultModelBuilder;
import com.dliberty.cms.common.vo.JsonBean;
import com.dliberty.cms.common.vo.PageInfo;
import com.dliberty.cms.common.vo.RowsResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dliberty.cms.service.CmsMenuService;
import com.dliberty.cms.vo.CmsMenuQueryParam;
import com.dliberty.cms.vo.CmsMenuVo;

@RestController
@RequestMapping("/menu")
public class CmsMenuController extends BaseController {

    @Autowired
    private CmsMenuService cmsMenuService;

    @GetMapping("list")
    public PageResult<CmsMenuVo> list(CmsMenuQueryParam queryParam, PageParam pageParam) {
        PageDTO<CmsMenuVo> listPage = cmsMenuService.listPageEs(queryParam, pageParam);
        PageResult<CmsMenuVo> pageResult =
                new PageResult<>(pageParam, listPage.getRecords(), listPage.getTotal(), listPage.getPages());
        return pageResult;
    }

    @GetMapping(value = "/view/{id}")
    public JsonBean view(@PathVariable("id") String id) {
        CmsMenuVo menuVo = cmsMenuService.getPageEs(id);
        cmsMenuService.browse(Long.valueOf(id));
        JsonBean json = new JsonBean();
        json.put("menuVo", menuVo);
        return json;
    }


}
