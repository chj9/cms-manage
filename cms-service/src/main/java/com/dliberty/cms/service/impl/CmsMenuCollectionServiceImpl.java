package com.dliberty.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dliberty.cms.constants.Constants;
import com.dliberty.cms.dao.entity.CmsMenuCollection;
import com.dliberty.cms.dao.mapper.CmsMenuCollectionMapper;
import com.dliberty.cms.exception.CommonException;
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
public class CmsMenuCollectionServiceImpl extends ServiceImpl<CmsMenuCollectionMapper, CmsMenuCollection> implements CmsMenuCollectionService {

    private final CmsMenuService cmsMenuService;

    @Autowired
    public CmsMenuCollectionServiceImpl(CmsMenuService cmsMenuService) {
        this.cmsMenuService = cmsMenuService;
    }

    @Override
    public CmsMenuCollection collection(Long userId, Integer menuId) {
        List<CmsMenuCollection> collList = selectByMenuId(userId, menuId);
        if (collList != null && collList.size() > 0) {
            throw new CommonException("已经收藏，不能重复收藏");
        }
        CmsMenuCollection coll = new CmsMenuCollection();
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
    public void removeCollection(Long userId, Integer menuId) {
        List<CmsMenuCollection> collList = selectByMenuId(userId, menuId);
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
    public List<CmsMenuCollection> selectByMenuId(Long userId, Integer menuId) {
        LambdaQueryWrapper<CmsMenuCollection> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CmsMenuCollection::getMenuId, menuId);
        queryWrapper.eq(CmsMenuCollection::getUserId, userId);
        queryWrapper.eq(CmsMenuCollection::getIsDeleted, Constants.COMMON_FLAG_NO);
        return baseMapper.selectList(queryWrapper);
    }

    @Override
    public List<Integer> selectByUserId(Long userId) {
        return baseMapper.selectByUserId(userId);
    }

}
