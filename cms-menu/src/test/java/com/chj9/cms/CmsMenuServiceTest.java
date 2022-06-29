package com.chj9.cms;

import java.util.ArrayList;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.chj9.cms.api.entity.CmsMenuEntity;
import com.chj9.cms.api.entity.CmsMenuLabelEntity;
import com.chj9.cms.api.entity.CmsMenuMaterialEntity;
import com.chj9.cms.api.entity.CmsMenuStepEntity;
import com.chj9.cms.service.CmsMenuEsService;
import com.chj9.cms.service.CmsMenuLabelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.chj9.cms.service.CmsMenuMaterialService;
import com.chj9.cms.service.CmsMenuService;
import com.chj9.cms.service.CmsMenuStepService;
import com.chj9.cms.api.vo.CmsMenuVo;
import org.springframework.util.CollectionUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MenuApplication.class)
public class CmsMenuServiceTest {

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

    @Test
    public void saveTest() {
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
            System.out.println("最后ID" + lastId);
            List<CmsMenuVo> cmsMenuVoList = new ArrayList<>();
            cmsMenuEntityList.forEach(menu -> {
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
    }

}
