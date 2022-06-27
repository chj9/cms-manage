package com.dliberty.cms.dao.mapper;

import java.util.List;

import com.dliberty.cms.entity.UmsAdminPermissionRelationEntity;
import com.dliberty.cms.entity.UmsAdminPermissionRelationExampleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UmsAdminPermissionRelationMapper {
    int countByExample(UmsAdminPermissionRelationExampleEntity example);

    int deleteByExample(UmsAdminPermissionRelationExampleEntity example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsAdminPermissionRelationExampleEntity record);

    int insertSelective(UmsAdminPermissionRelationEntity record);

    List<UmsAdminPermissionRelationEntity> selectByExample(UmsAdminPermissionRelationExampleEntity example);

    UmsAdminPermissionRelationEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsAdminPermissionRelationEntity record, @Param("example") UmsAdminPermissionRelationExampleEntity example);

    int updateByExample(@Param("record") UmsAdminPermissionRelationEntity record, @Param("example") UmsAdminPermissionRelationExampleEntity example);

    int updateByPrimaryKeySelective(UmsAdminPermissionRelationEntity record);

    int updateByPrimaryKey(UmsAdminPermissionRelationEntity record);
}