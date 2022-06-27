package com.dliberty.cms.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.dliberty.cms.dao.base.BaseMapper;
import com.dliberty.cms.dao.entity.CmsMenuCollection;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author LG
 * @since 2019-06-14
 */
@Mapper
@Repository
public interface CmsMenuCollectionMapper extends BaseMapper<CmsMenuCollection> {

	List<Integer> selectByUserId(@Param("userId")Long userId);
}
