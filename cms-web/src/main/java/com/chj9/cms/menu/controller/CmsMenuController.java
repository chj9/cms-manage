package com.chj9.cms.menu.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.chj9.cms.common.page.PageParam;
import com.chj9.cms.common.util.RowsResultModelBuilder;
import com.chj9.cms.common.vo.JsonBean;
import com.chj9.cms.common.vo.RowsResultModel;
import com.chj9.cms.service.CmsMenuService;
import com.chj9.cms.api.vo.CmsMenuQueryParam;
import com.chj9.cms.api.vo.CmsMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/menu")
public class CmsMenuController extends BaseController {

    @Autowired
    private CmsMenuService cmsMenuService;

    @GetMapping("list")
    public RowsResultModel<CmsMenuVo> list(CmsMenuQueryParam queryParam, PageParam pageParam) {
        PageDTO<CmsMenuVo> listPage = cmsMenuService.listPageEs(queryParam, pageParam);
//        PageResult<CmsMenuVo> pageResult =
//                new PageResult<>(pageParam, listPage.getRecords(), listPage.getTotal(), listPage.getPages());
        return RowsResultModelBuilder.of(listPage);
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
