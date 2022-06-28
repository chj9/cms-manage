package com.chj9.cms.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chj9.cms.api.entity.UsersEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 后台用户表 Mapper 接口
 * </p>
 *
 * @author GuoJingtao
 * @since 2019-03-20
 */
@Mapper
@Repository
public interface UsersMapper extends BaseMapper<UsersEntity> {

}
