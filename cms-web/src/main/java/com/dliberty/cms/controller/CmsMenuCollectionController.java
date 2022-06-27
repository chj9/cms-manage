package com.dliberty.cms.controller;

import java.util.List;

import com.dliberty.cms.common.vo.JsonBean;
import com.dliberty.cms.entity.CmsMenuCollectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dliberty.cms.service.CmsMenuCollectionService;
import com.dliberty.cms.service.CmsMenuService;
import com.dliberty.cms.vo.CmsMenuVo;

/**
 * 收藏
 *
 * @author LG
 */
@RestController
@RequestMapping("/menu/collection")
public class CmsMenuCollectionController extends BaseController {

    private final CmsMenuCollectionService cmsMenuCollectionService;
    private final CmsMenuService cmsMenuService;

    @Autowired
    public CmsMenuCollectionController(CmsMenuCollectionService cmsMenuCollectionService, CmsMenuService cmsMenuService) {
        this.cmsMenuCollectionService = cmsMenuCollectionService;
        this.cmsMenuService = cmsMenuService;
    }

    @GetMapping(value = "/add/{menuId}")
    public JsonBean collection(@PathVariable("menuId") Long menuId) {
        Long userId = getUserId();
        CmsMenuCollectionEntity collection = cmsMenuCollectionService.collection(userId, menuId);
        JsonBean json = new JsonBean();
        json.put("collection", collection);
        return json;
    }

    @GetMapping(value = "/remove/{menuId}")
    public JsonBean removeCollection(@PathVariable("menuId") Long menuId) {
        Long userId = getUserId();
        cmsMenuCollectionService.removeCollection(userId, menuId);
        return new JsonBean();
    }

    @GetMapping(value = "/view")
    public JsonBean view() {
        Long userId = getUserId();
        List<CmsMenuVo> menuList = cmsMenuService.selectCollectionByUserId(userId);
        JsonBean json = new JsonBean();
        json.put("menuList", menuList);
        return json;
    }

    @GetMapping(value = "/{menuId}")
    public JsonBean get(@PathVariable("menuId") Long menuId) {
        Long userId = getUserId();
        List<CmsMenuCollectionEntity> collections = cmsMenuCollectionService.selectByMenuId(userId, menuId);
        JsonBean json = new JsonBean();
        json.put("collections", collections);
        return json;
    }


}
