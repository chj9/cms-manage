package com.chj9.cms.dao.mapper;

import java.util.List;

import com.chj9.cms.api.entity.UmsRolePermissionRelationEntity;
import com.chj9.cms.api.entity.UmsRolePermissionRelationExampleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UmsRolePermissionRelationMapper {
    int countByExample(UmsRolePermissionRelationExampleEntity example);

    int deleteByExample(UmsRolePermissionRelationExampleEntity example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsRolePermissionRelationEntity record);

    int insertSelective(UmsRolePermissionRelationEntity record);

    List<UmsRolePermissionRelationEntity> selectByExample(UmsRolePermissionRelationExampleEntity example);

    UmsRolePermissionRelationEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsRolePermissionRelationEntity record, @Param("example") UmsRolePermissionRelationExampleEntity example);

    int updateByExample(@Param("record") UmsRolePermissionRelationEntity record, @Param("example") UmsRolePermissionRelationExampleEntity example);

    int updateByPrimaryKeySelective(UmsRolePermissionRelationEntity record);

    int updateByPrimaryKey(UmsRolePermissionRelationEntity record);
    /**
     * 批量插入角色和权限关系
     */
    int insertList(@Param("list")List<UmsRolePermissionRelationEntity> list);

    
}