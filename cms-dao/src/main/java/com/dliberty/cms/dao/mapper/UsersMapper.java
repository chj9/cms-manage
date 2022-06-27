package com.dliberty.cms.dao.mapper;

import com.dliberty.cms.dao.entity.Users;
import com.dliberty.cms.dao.base.BaseMapper;
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
public interface UsersMapper extends BaseMapper<Users> {

}
