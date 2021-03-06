package com.chj9.cms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chj9.cms.common.constants.Constants;
import com.chj9.cms.common.page.PageParam;
import com.chj9.cms.dao.mapper.CmsMenuCategoryMapper;
import com.chj9.cms.api.entity.CmsMenuCategoryEntity;
import com.chj9.cms.service.CmsMenuCategoryService;
import com.chj9.cms.api.vo.CmsMenuCateParam;
import com.chj9.cms.api.vo.CmsMenuCategoryQueryParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
public class CmsMenuCategoryServiceImpl extends ServiceImpl<CmsMenuCategoryMapper, CmsMenuCategoryEntity> implements CmsMenuCategoryService {

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
    public CmsMenuCategoryEntity selectByNameAndParentId(String cateName, Long parentId) {
        QueryWrapper<CmsMenuCategoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("category_name", cateName);
        queryWrapper.eq("parent_id", parentId);
        queryWrapper.eq("is_deleted", Constants.COMMON_FLAG_NO);

        List<CmsMenuCategoryEntity> selectList = baseMapper.selectList(queryWrapper);
        if (selectList.size() > 0) {
            return selectList.get(0);
        }
        return null;
    }


    @Override
    public CmsMenuCategoryEntity save(String cateName, Long parentId) {
        CmsMenuCategoryEntity category = selectByNameAndParentId(cateName, parentId);
        if (category != null) {
            return category;
        }
        category = new CmsMenuCategoryEntity();
        category.setCategoryName(cateName);
        category.setParentId(parentId);
        save(category);
        return category;
    }


    @Override
    public List<CmsMenuCategoryEntity> selectByParentId(Long parentId) {

//		List<CmsMenuCategory> cateList = (List<CmsMenuCategory>) redisClient.get("cmsMenuCategory."+parentId);
//		if (cateList != null && cateList.size() > 0) {
//			return cateList;
//		}
        QueryWrapper<CmsMenuCategoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        queryWrapper.eq("is_deleted", Constants.COMMON_FLAG_NO);
        queryWrapper.orderByAsc("sort");
        List<CmsMenuCategoryEntity> cateList = baseMapper.selectList(queryWrapper);
        //redisClient.set("cmsMenuCategory."+parentId, cateList, 60 * 60 * 24 * 10);
        return cateList;
    }

    @Override
    public List<CmsMenuCategoryEntity> selectLastCategory(String cateName) {
        QueryWrapper<CmsMenuCategoryEntity> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotEmpty(cateName)) {
            queryWrapper.eq("category_name", cateName);
        }

        queryWrapper.eq("is_deleted", Constants.COMMON_FLAG_NO);
        queryWrapper.eq("has_next", Constants.COMMON_FLAG_YES);
        List<CmsMenuCategoryEntity> cateList = baseMapper.selectList(queryWrapper);
        return cateList;
    }

    @Override
    public PageDTO<CmsMenuCategoryEntity> listPage(Long parentId, PageParam pageParam, CmsMenuCategoryQueryParam param) {
        QueryWrapper<CmsMenuCategoryEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id", parentId);
        queryWrapper.eq("is_deleted", Constants.COMMON_FLAG_NO);
        queryWrapper.orderByAsc("sort");
        PageDTO<CmsMenuCategoryEntity> page = baseMapper.selectPage(new PageDTO<>(pageParam.getCurrent(), pageParam.getSize()), queryWrapper);
        return page;
    }

    @Override
    public void delete(Integer id) {
        CmsMenuCategoryEntity cate = getById(id);
        if (cate != null) {
            cate.setIsDeleted(Constants.COMMON_FLAG_YES);
            updateById(cate);

            List<CmsMenuCategoryEntity> parentList = selectByParentId(cate.getParentId());
            if (parentList.size() > 0) {
                CmsMenuCategoryEntity parent = getById(cate.getParentId());
                if (parent != null) {
                    parent.setHasNext(Constants.COMMON_FLAG_YES);
                    baseMapper.updateById(parent);
                }
            }
        }

        //redisClient.del("cmsMenuCategory."+cate.getParentId());
    }

    @Override
    public CmsMenuCategoryEntity create(CmsMenuCateParam param) {
        CmsMenuCategoryEntity cate = new CmsMenuCategoryEntity();
        BeanUtils.copyProperties(param, cate);
        cate.setHasNext(Constants.COMMON_FLAG_NO);
        baseMapper.insert(cate);

        CmsMenuCategoryEntity parent = getById(param.getParentId());
        if (parent != null) {
            parent.setHasNext(Constants.COMMON_FLAG_YES);
            baseMapper.updateById(parent);
        }
        //redisClient.del("cmsMenuCategory."+cate.getParentId());

        return cate;
    }

    @Override
    public CmsMenuCategoryEntity update(Integer id, CmsMenuCateParam param) {
        CmsMenuCategoryEntity cate = getById(id);
        BeanUtils.copyProperties(param, cate);
        baseMapper.updateById(cate);
        //redisClient.del("cmsMenuCategory."+cate.getParentId());
        return cate;
    }


}
