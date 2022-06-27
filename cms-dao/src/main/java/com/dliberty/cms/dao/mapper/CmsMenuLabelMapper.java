package com.dliberty.cms.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dliberty.cms.dao.base.BaseMapper;
import com.dliberty.cms.dao.entity.CmsMenuLabel;
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
public interface CmsMenuLabelMapper extends BaseMapper<CmsMenuLabel> {

	/**
	 * 根据menuId 查询所属标签列表
	 * @param menuId
	 * @return
	 */
	List<CmsMenuLabel> selectByMenuId(@Param("menuId")Integer menuId);
}
