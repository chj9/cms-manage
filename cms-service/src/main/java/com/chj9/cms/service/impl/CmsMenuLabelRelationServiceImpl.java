package com.chj9.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chj9.cms.common.constants.Constants;
import com.chj9.cms.dao.mapper.CmsMenuLabelRelationMapper;
import com.chj9.cms.api.entity.CmsMenuLabelRelationEntity;
import com.chj9.cms.service.CmsMenuLabelRelationService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class CmsMenuLabelRelationServiceImpl extends ServiceImpl<CmsMenuLabelRelationMapper, CmsMenuLabelRelationEntity> implements CmsMenuLabelRelationService {

	@Override
	public void deleteByMenuId(Long menuId) {
		QueryWrapper<CmsMenuLabelRelationEntity> wrapper = new QueryWrapper<>();
		wrapper.eq("menu_id", menuId).eq("is_deleted", Constants.COMMON_FLAG_NO);
		List<CmsMenuLabelRelationEntity> list = baseMapper.selectList(wrapper);
		if (list.size() > 0) {
			updateBatchById(list);
		}
	}

	@Override
	public void save(Long menuId, List<Integer> labels) {
		if (menuId == null || labels == null || labels.size() == 0) {
			return;
		}
		List<CmsMenuLabelRelationEntity> list = new ArrayList<CmsMenuLabelRelationEntity>();
		labels.forEach(id -> {
			CmsMenuLabelRelationEntity r = new CmsMenuLabelRelationEntity();
			r.setMenuId(menuId);
			r.setLabelId(id);
			list.add(r);
		});
		saveBatch(list);
	}

}
