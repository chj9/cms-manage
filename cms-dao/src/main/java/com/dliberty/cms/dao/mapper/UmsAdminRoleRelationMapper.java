package com.dliberty.cms.dao.mapper;


import com.dliberty.cms.entity.UmsAdminRoleRelationEntity;
import com.dliberty.cms.entity.UmsAdminRoleRelationExampleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UmsAdminRoleRelationMapper {
    int countByExample(UmsAdminRoleRelationExampleEntity example);

    int deleteByExample(UmsAdminRoleRelationExampleEntity example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsAdminRoleRelationEntity record);

    int insertSelective(UmsAdminRoleRelationEntity record);

    List<UmsAdminRoleRelationEntity> selectByExample(UmsAdminRoleRelationExampleEntity example);

    UmsAdminRoleRelationEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsAdminRoleRelationEntity record, @Param("example") UmsAdminRoleRelationExampleEntity example);

    int updateByExample(@Param("record") UmsAdminRoleRelationEntity record, @Param("example") UmsAdminRoleRelationExampleEntity example);

    int updateByPrimaryKeySelective(UmsAdminRoleRelationEntity record);

    int updateByPrimaryKey(UmsAdminRoleRelationEntity record);
}