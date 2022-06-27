package com.dliberty.cms.dao.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dliberty.cms.entity.CmsMenuCollectionEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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
public interface CmsMenuCollectionMapper extends BaseMapper<CmsMenuCollectionEntity> {

	List<Integer> selectByUserId(@Param("userId")Long userId);
}
