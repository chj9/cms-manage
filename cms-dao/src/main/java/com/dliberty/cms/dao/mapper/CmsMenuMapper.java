package com.dliberty.cms.dao.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dliberty.cms.dto.CmsMenuQueryDto;
import com.dliberty.cms.entity.CmsMenuEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
public interface CmsMenuMapper extends BaseMapper<CmsMenuEntity> {

	/**
	 * 查询用户收藏
	 * @param userId
	 * @return
	 */
	List<CmsMenuEntity> selectCollectionByUserId(@Param("userId") Long userId);
	
	/**
	 * 分页查询数据
	 * @param page
	 * @return
	 */
	IPage<CmsMenuEntity> listPage(Page<CmsMenuEntity> page,@Param("dto") CmsMenuQueryDto dto);
}
