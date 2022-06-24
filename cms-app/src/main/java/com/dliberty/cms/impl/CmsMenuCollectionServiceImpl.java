package com.dliberty.cms.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dliberty.cms.constants.Constants;
import com.dliberty.cms.dao.entity.CmsMenuCollection;
import com.dliberty.cms.dao.mapper.CmsMenuCollectionMapper;
import com.dliberty.cms.exception.CommonException;
import com.dliberty.cms.service.CmsMenuCollectionService;
import com.dliberty.cms.service.CmsMenuService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LG
 * @since 2019-06-14
 */
@Service
public class CmsMenuCollectionServiceImpl extends ServiceImpl<CmsMenuCollectionMapper, CmsMenuCollection> implements CmsMenuCollectionService {
	
	@Autowired
	private CmsMenuService cmsMenuService;

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
		cmsMenuService.collection(menuId,1);
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
			cmsMenuService.collection(menuId,-1);
		}
	}

	@Override
	public List<CmsMenuCollection> selectByMenuId(Long userId, Integer menuId) {
		QueryWrapper<CmsMenuCollection> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("menu_id", menuId);
		queryWrapper.eq("user_id", userId);
		queryWrapper.eq("is_deleted", Constants.COMMON_FLAG_NO);
		List<CmsMenuCollection> collList = baseMapper.selectList(queryWrapper);
		return collList;
	}

	@Override
	public List<Integer> selectByUserId(Long userId) {
		return baseMapper.selectByUserId(userId);
	}

}
