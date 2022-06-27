package com.dliberty.cms.dao.mapper;

import java.util.List;

import com.dliberty.cms.entity.UmsPermissionEntity;
import com.dliberty.cms.entity.UmsPermissionExampleEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UmsPermissionMapper {
    int countByExample(UmsPermissionExampleEntity example);

    int deleteByExample(UmsPermissionExampleEntity example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsPermissionEntity record);

    int insertSelective(UmsPermissionEntity record);

    List<UmsPermissionEntity> selectByExample(UmsPermissionExampleEntity example);

    UmsPermissionEntity selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsPermissionEntity record, @Param("example") UmsPermissionExampleEntity example);

    int updateByExample(@Param("record") UmsPermissionEntity record, @Param("example") UmsPermissionExampleEntity example);

    int updateByPrimaryKeySelective(UmsPermissionEntity record);

    int updateByPrimaryKey(UmsPermissionEntity record);
    
    /**
     * 根据角色获取权限
     */
    List<UmsPermissionEntity> getPermissionList(@Param("roleId") Long roleId);
    
    /**
     * 获取用户所有权限(包括+-权限)
     */
    List<UmsPermissionEntity> getPermissionListByAdminId(@Param("adminId") Long adminId);
}