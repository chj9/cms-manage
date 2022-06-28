package com.chj9.cms.dao.mapper;

import java.util.List;

import com.chj9.cms.api.entity.UmsRoleEntity;
import com.chj9.cms.api.entity.UmsRoleExampleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UmsRoleMapper {
    int countByExample(UmsRoleExampleEntity example);

    int deleteByExample(UmsRoleExampleEntity example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsRoleEntity record);

    int insertSelective(UmsRoleEntity record);

    List<UmsRoleEntity> selectByExample(UmsRoleExampleEntity example);

    UmsRoleEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsRoleEntity record, @Param("example") UmsRoleExampleEntity example);

    int updateByExample(@Param("record") UmsRoleEntity record, @Param("example") UmsRoleExampleEntity example);

    int updateByPrimaryKeySelective(UmsRoleEntity record);

    int updateByPrimaryKey(UmsRoleEntity record);
    
    /**
     * 根据用户查询用户的角色code
     * @param adminId
     * @return
     */
    List<String> selectCode(Long adminId);
}