package com.dliberty.cms.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dliberty.cms.service.CmsMenuService;
import com.dliberty.cms.util.RowsResultModelBuilder;
import com.dliberty.cms.vo.CmsMenuQueryParam;
import com.dliberty.cms.vo.CmsMenuVo;
import com.dliberty.cms.vo.JsonBean;
import com.dliberty.cms.vo.PageInfo;
import com.dliberty.cms.vo.RowsResultModel;

@RestController
@RequestMapping("/menu")
public class CmsMenuController extends BaseController {

    @Autowired
    private CmsMenuService cmsMenuService;

    @GetMapping("list")
    public RowsResultModel<CmsMenuVo> list(CmsMenuQueryParam param, PageInfo pageInfo) {
        param.setPageInfo(pageInfo);
        PageDTO<CmsMenuVo> listPage = cmsMenuService.listPageEs(param);
        return RowsResultModelBuilder.of(listPage);
    }

    @GetMapping(value = "/view/{id}")
    public JsonBean view(@PathVariable("id") String id) {
        CmsMenuVo menuVo = cmsMenuService.getPageEs(id);
        cmsMenuService.browse(Integer.valueOf(id));
        JsonBean json = new JsonBean();
        json.put("menuVo", menuVo);
        return json;
    }


}
