package com.chj9.cms.dao.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chj9.cms.api.entity.CmsMenuLabelEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LG
 * @since 2019-06-13
 */
@Mapper
@Repository
public interface CmsMenuLabelMapper extends BaseMapper<CmsMenuLabelEntity> {

	/**
	 * 根据menuId 查询所属标签列表
	 * @param menuId
	 * @return
	 */
	List<CmsMenuLabelEntity> selectByMenuId(@Param("menuId")Long menuId);
}
