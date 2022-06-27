package com.dliberty.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dliberty.cms.constants.Constants;
import com.dliberty.cms.dao.entity.CmsMenuStep;
import com.dliberty.cms.dao.mapper.CmsMenuStepMapper;
import com.dliberty.cms.service.CmsMenuService;
import com.dliberty.cms.service.CmsMenuStepService;
import com.dliberty.cms.vo.CmsMenuStepItemVo;
import com.dliberty.cms.vo.CmsMenuStepParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LG
 * @since 2019-06-13
 */
@Service
public class CmsMenuStepServiceImpl extends ServiceImpl<CmsMenuStepMapper, CmsMenuStep> implements CmsMenuStepService {

    private final CmsMenuService cmsMenuService;

    @Autowired
    public CmsMenuStepServiceImpl(CmsMenuService cmsMenuService) {
        this.cmsMenuService = cmsMenuService;
    }

    @Override
    public void saveStep(List<CmsMenuStep> stepList, Long menuId) {
        if (stepList == null || stepList.size() == 0 || menuId == null) {
            return;
        }
        stepList.forEach(item -> {
            item.setMenuId(menuId);
        });
        saveBatch(stepList);
    }

    @Override
    public List<CmsMenuStep> selectByMenuId(Long menuId) {
        LambdaQueryWrapper<CmsMenuStep> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CmsMenuStep::getMenuId, menuId);
        queryWrapper.eq(CmsMenuStep::getIsDeleted, Constants.COMMON_FLAG_NO);
        queryWrapper.orderByAsc(CmsMenuStep::getStepIndex);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public void delete(Long menuId) {
        List<CmsMenuStep> stepList = selectByMenuId(menuId);
        stepList.forEach(item -> {
            item.setIsDeleted(Constants.COMMON_FLAG_YES);
        });
        updateBatchById(stepList);
    }

    @Override
    @Transactional
    public void update(Long menuId, CmsMenuStepParam param) {
        if (menuId == null) {
            return;
        }
        if (param.getStepList() == null || param.getStepList().size() == 0) {
            return;
        }
        //先删除之前的步骤
        delete(menuId);
        //新增
        List<CmsMenuStep> stepList = new ArrayList<CmsMenuStep>();
        int i = 1;
        for (CmsMenuStepItemVo vo : param.getStepList()) {
            CmsMenuStep step = new CmsMenuStep();
            step.setMenuId(menuId);
            step.setStepIndex(i);
            step.setStepDesc(vo.getStepDesc());
            step.setStepImg(vo.getStepImg());
            step.setIsDeleted(Constants.COMMON_FLAG_NO);
            stepList.add(step);
            i++;
        }
        saveBatch(stepList);

        //更新搜索引擎
        cmsMenuService.updateEs(cmsMenuService.getById(menuId));
    }

}
