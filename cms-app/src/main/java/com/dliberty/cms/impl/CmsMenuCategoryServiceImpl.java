package com.dliberty.cms.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dliberty.cms.constants.Constants;
import com.dliberty.cms.dao.entity.CmsMenuCategory;
import com.dliberty.cms.dao.mapper.CmsMenuCategoryMapper;
import com.dliberty.cms.service.CmsMenuCategoryService;
import com.dliberty.cms.vo.CmsMenuCateParam;
import com.dliberty.cms.vo.CmsMenuCategoryQueryParam;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author LG
 * @since 2019-06-13
 */
@Service
public class CmsMenuCategoryServiceImpl extends ServiceImpl<CmsMenuCategoryMapper, CmsMenuCategory> implements CmsMenuCategoryService {
	
//	static Map<Integer,List<CmsMenuCategory>> map = new HashMap<>();
//	
//	@PostConstruct
//	public void init(){
//		QueryWrapper<CmsMenuCategory> queryWrapper = new QueryWrapper<>();
//		queryWrapper.eq("is_deleted", Constants.COMMON_FLAG_NO);
//		queryWrapper.orderByAsc("sort");
//		List<CmsMenuCategory> cateList = baseMapper.selectList(queryWrapper);
//		
//		map = cateList.stream().collect(
//		   Collectors.groupingBy(CmsMenuCategory::getParentId)
//		);
//		
//		
//	}
	
	//@Autowired
	//private RedisClient redisClient;

	@Override
	public CmsMenuCategory selectByNameAndParentId(String cateName, Integer parentId) {
		QueryWrapper<CmsMenuCategory> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("category_name", cateName);
		queryWrapper.eq("parent_id", parentId);
		queryWrapper.eq("is_deleted", Constants.COMMON_FLAG_NO);
		
		List<CmsMenuCategory> selectList = baseMapper.selectList(queryWrapper);
		if (selectList.size() > 0) {
			return selectList.get(0);
		}
		return null;
	}

	
	@Override
	public CmsMenuCategory save(String cateName, Integer parentId) {
		CmsMenuCategory category = selectByNameAndParentId(cateName, parentId);
		if (category != null) {
			return category;
		}
		category = new CmsMenuCategory();
		category.setCategoryName(cateName);
		category.setParentId(parentId);
		category.setCreateTime(new Date());
		category.setUpdateTime(new Date());
		category.setIsDeleted(Constants.COMMON_FLAG_NO);
		save(category);
		return category;
	}


	@Override
	public List<CmsMenuCategory> selectByParentId(Integer parentId) {
		
//		List<CmsMenuCategory> cateList = (List<CmsMenuCategory>) redisClient.get("cmsMenuCategory."+parentId);
//		if (cateList != null && cateList.size() > 0) {
//			return cateList;
//		}
		QueryWrapper<CmsMenuCategory> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("parent_id", parentId);
		queryWrapper.eq("is_deleted", Constants.COMMON_FLAG_NO);
		queryWrapper.orderByAsc("sort");
		List<CmsMenuCategory> cateList = baseMapper.selectList(queryWrapper);
		//redisClient.set("cmsMenuCategory."+parentId, cateList, 60 * 60 * 24 * 10);
		return cateList;
	}

	@Override
	public List<CmsMenuCategory> selectLastCategory(String cateName) {
		QueryWrapper<CmsMenuCategory> queryWrapper = new QueryWrapper<>();
		if (StringUtils.isNotEmpty(cateName)) {
			queryWrapper.eq("category_name", cateName);
		}
		
		queryWrapper.eq("is_deleted", Constants.COMMON_FLAG_NO);
		queryWrapper.eq("has_next", Constants.COMMON_FLAG_YES);
		List<CmsMenuCategory> cateList = baseMapper.selectList(queryWrapper);
		return cateList;
	}

	@Override
	public IPage<CmsMenuCategory> listPage(Integer parentId, CmsMenuCategoryQueryParam param) {
		QueryWrapper<CmsMenuCategory> queryWrapper = new QueryWrapper<>();
		queryWrapper.eq("parent_id", parentId);
		queryWrapper.eq("is_deleted", Constants.COMMON_FLAG_NO);
		queryWrapper.orderByAsc("sort");
		IPage<CmsMenuCategory> page = baseMapper.selectPage(param.getPageInfo(), queryWrapper);
		return page;
	}

	@Override
	public void delete(Integer id) {
		CmsMenuCategory cate = getById(id);
		if (cate != null) {
			cate.setIsDeleted(Constants.COMMON_FLAG_YES);
			cate.setUpdateTime(new Date());
			updateById(cate);
			
			List<CmsMenuCategory> parentList = selectByParentId(cate.getParentId());
			if (parentList.size() > 0) {
				CmsMenuCategory parent = getById(cate.getParentId());
				if (parent != null) {
					parent.setHasNext(Constants.COMMON_FLAG_YES);
					parent.setUpdateTime(new Date());
					baseMapper.updateById(parent);
				}
			}
		}
		
		//redisClient.del("cmsMenuCategory."+cate.getParentId());
	}

	@Override
	public CmsMenuCategory create(CmsMenuCateParam param) {
		CmsMenuCategory cate = new CmsMenuCategory();
		BeanUtils.copyProperties(param, cate);
		cate.setCreateTime(new Date());
		cate.setUpdateTime(new Date());
		cate.setIsDeleted(Constants.COMMON_FLAG_NO);
		cate.setHasNext(Constants.COMMON_FLAG_NO);
		baseMapper.insert(cate);
		
		CmsMenuCategory parent = getById(param.getParentId());
		if (parent != null) {
			parent.setHasNext(Constants.COMMON_FLAG_YES);
			parent.setUpdateTime(new Date());
			baseMapper.updateById(parent);
		}
		//redisClient.del("cmsMenuCategory."+cate.getParentId());
		
		return cate;
	}

	@Override
	public CmsMenuCategory update(Integer id, CmsMenuCateParam param) {
		CmsMenuCategory cate = getById(id);
		BeanUtils.copyProperties(param, cate);
		cate.setUpdateTime(new Date());
		baseMapper.updateById(cate);
		//redisClient.del("cmsMenuCategory."+cate.getParentId());
		return cate;
	}

	
}
