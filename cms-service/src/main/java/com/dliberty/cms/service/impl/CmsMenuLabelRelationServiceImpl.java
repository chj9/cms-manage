package com.dliberty.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dliberty.cms.constants.Constants;
import com.dliberty.cms.dao.entity.CmsMenuLabelRelation;
import com.dliberty.cms.dao.mapper.CmsMenuLabelRelationMapper;
import com.dliberty.cms.service.CmsMenuLabelRelationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LG
 * @since 2019-09-05
 */
@Service
public class CmsMenuLabelRelationServiceImpl extends ServiceImpl<CmsMenuLabelRelationMapper, CmsMenuLabelRelation> implements CmsMenuLabelRelationService {

	@Override
	public void deleteByMenuId(Long menuId) {
		QueryWrapper<CmsMenuLabelRelation> wrapper = new QueryWrapper<>();
		wrapper.eq("menu_id", menuId).eq("is_deleted", Constants.COMMON_FLAG_NO);
		List<CmsMenuLabelRelation> list = baseMapper.selectList(wrapper);
		if (list.size() > 0) {
			list.forEach(item -> {
				item.setIsDeleted(Constants.COMMON_FLAG_YES);
				item.setUpdateTime(new Date());
			});
			updateBatchById(list);
		}
	}

	@Override
	public void save(Long menuId, List<Integer> labels) {
		if (menuId == null || labels == null || labels.size() == 0) {
			return;
		}
		List<CmsMenuLabelRelation> list = new ArrayList<CmsMenuLabelRelation>();
		labels.forEach(id -> {
			CmsMenuLabelRelation r = new CmsMenuLabelRelation();
			r.setIsDeleted(Constants.COMMON_FLAG_NO);
			r.setMenuId(menuId);
			r.setLabelId(id);
			r.setCreateTime(new Date());
			r.setUpdateTime(new Date());
			list.add(r);
		});
		saveBatch(list);
	}

}
