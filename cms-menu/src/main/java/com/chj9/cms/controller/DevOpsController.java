package com.chj9.cms.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chj9.cms.service.*;
import com.chj9.cms.common.logging.SouthernQuietLogger;
import com.chj9.cms.common.logging.SouthernQuietLoggerFactory;
import com.chj9.cms.api.entity.CmsMenuEntity;
import com.chj9.cms.api.entity.CmsMenuLabelEntity;
import com.chj9.cms.api.entity.CmsMenuMaterialEntity;
import com.chj9.cms.api.entity.CmsMenuStepEntity;
import com.chj9.cms.service.impl.CmsMenuEsServiceImpl;
import com.chj9.cms.api.vo.CmsMenuVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 后台用户管理
 * Created by macro on 2018/4/26.
 */
@RestController
@RequestMapping("/devops")
public class DevOpsController extends BaseController {

    private static final SouthernQuietLogger LOGGER = SouthernQuietLoggerFactory.getLogger(CmsMenuEsServiceImpl.class);

    @Autowired
    private CmsMenuEsService cmsMenuEsService;
    @Autowired
    private CmsMenuService cmsMenuService;
    @Autowired
    private CmsMenuStepService cmsMenuStepService;
    @Autowired
    private CmsMenuMaterialService cmsMenuMaterialService;
    @Autowired
    private CmsMenuLabelService cmsMenuLabelService;

    @GetMapping(value = "/syncMenuTEs")
    public String syncMenuTEs() {
        long lastId = 0;
        int size = 1000;
        while (true) {
            LambdaQueryWrapper<CmsMenuEntity> wrapper =
                    new LambdaQueryWrapper<CmsMenuEntity>()
                            .gt(CmsMenuEntity::getId, lastId)
                            .orderByAsc(CmsMenuEntity::getId)
                            .last("LIMIT " + size);
            List<CmsMenuEntity> cmsMenuEntityList = cmsMenuService.list(wrapper);
            if (CollectionUtils.isEmpty(cmsMenuEntityList)) {
                break;
            }
            lastId = cmsMenuEntityList.get(cmsMenuEntityList.size() - 1).getId();
            LOGGER.message("数据同步").context("lastId",lastId).info();
            List<CmsMenuVo> cmsMenuVoList = new ArrayList<>();
            cmsMenuEntityList.parallelStream().forEach(menu -> {
                CmsMenuVo vo = new CmsMenuVo();
                BeanUtils.copyProperties(menu, vo);
                vo.setId(Long.toString(menu.getId()));
                vo.setCreateTime(menu.getCreateTime());
                vo.setUpdateTime(menu.getUpdateTime());
                List<CmsMenuStepEntity> stepList = cmsMenuStepService.selectByMenuId(menu.getId());
                vo.setStepList(stepList);
                List<CmsMenuMaterialEntity> materialList = cmsMenuMaterialService.selectByMenuId(menu.getId());
                vo.setMaterialList(materialList);
                List<CmsMenuLabelEntity> labelList = cmsMenuLabelService.selectByMenuId(menu.getId());
                vo.setLabelList(labelList);
                cmsMenuVoList.add(vo);
            });
            cmsMenuEsService.saveAll(cmsMenuVoList);
        }
        LOGGER.message("数据完成").info();
        return "1";
    }
}
