package com.chj9.cms.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chj9.cms.api.entity.CmsMenuMaterialEntity;
import org.apache.ibatis.annotations.Mapper;
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
public interface CmsMenuMaterialMapper extends BaseMapper<CmsMenuMaterialEntity> {

}
