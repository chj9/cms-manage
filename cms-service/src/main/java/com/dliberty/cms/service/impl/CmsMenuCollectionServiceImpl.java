package com.dliberty.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dliberty.cms.common.constants.Constants;
import com.dliberty.cms.common.exception.CommonException;
import com.dliberty.cms.dao.mapper.CmsMenuCollectionMapper;
import com.dliberty.cms.entity.CmsMenuCollectionEntity;
import com.dliberty.cms.service.CmsMenuCollectionService;
import com.dliberty.cms.service.CmsMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author LG
 * @since 2019-06-14
 */
@Service
public class CmsMenuCollectionServiceImpl extends ServiceImpl<CmsMenuCollectionMapper, CmsMenuCollectionEntity> implements CmsMenuCollectionService {

    private final CmsMenuService cmsMenuService;

    @Autowired
    public CmsMenuCollectionServiceImpl(CmsMenuService cmsMenuService) {
        this.cmsMenuService = cmsMenuService;
    }

    @Override
    public CmsMenuCollectionEntity collection(Long userId, Long menuId) {
        List<CmsMenuCollectionEntity> collList = selectByMenuId(userId, menuId);
        if (collList != null && collList.size() > 0) {
            throw new CommonException("已经收藏，不能重复收藏");
        }
        CmsMenuCollectionEntity coll = new CmsMenuCollectionEntity();
        coll.setMenuId(menuId);
        coll.setUserId(userId);
        coll.setCreateTime(new Date());
        coll.setUpdateTime(new Date());
        coll.setIsDeleted(Constants.COMMON_FLAG_NO);
        save(coll);
        cmsMenuService.collection(menuId, 1);
        return coll;
    }

    @Override
    public void removeCollection(Long userId, Long menuId) {
        List<CmsMenuCollectionEntity> collList = selectByMenuId(userId, menuId);
        if (collList != null && collList.size() > 0) {
            collList.forEach(item -> {
                item.setUpdateTime(new Date());
                item.setIsDeleted(Constants.COMMON_FLAG_YES);
            });
            updateBatchById(collList);
            cmsMenuService.collection(menuId, -1);
        }
    }

    @Override
    public List<CmsMenuCollectionEntity> selectByMenuId(Long userId, Long menuId) {
        LambdaQueryWrapper<CmsMenuCollectionEntity> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CmsMenuCollectionEntity::getMenuId, menuId);
        queryWrapper.eq(CmsMenuCollectionEntity::getUserId, userId);
        queryWrapper.eq(CmsMenuCollectionEntity::getIsDeleted, Constants.COMMON_FLAG_NO);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Integer> selectByUserId(Long userId) {
        return baseMapper.selectByUserId(userId);
    }

}
