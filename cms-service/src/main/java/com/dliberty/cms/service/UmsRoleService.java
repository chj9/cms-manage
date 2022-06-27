package com.dliberty.cms.service;

import com.dliberty.cms.entity.UmsPermissionEntity;
import com.dliberty.cms.entity.UmsRoleEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台角色管理Service
 * Created by macro on 2018/9/30.
 */
public interface UmsRoleService {
    /**
     * 添加角色
     */
    int create(UmsRoleEntity role);

    /**
     * 修改角色信息
     */
    int update(Long id, UmsRoleEntity role);

    /**
     * 批量删除角色
     */
    int delete(List<Long> ids);

    /**
     * 获取指定角色权限
     */
    List<UmsPermissionEntity> getPermissionList(Long roleId);
    
    /**
     * 获取指定角色权限
     */
    List<UmsPermissionEntity> getPermissionListByAdminId(Long adminId);

    /**
     * 修改指定角色的权限
     */
    @Transactional
    int updatePermission(Long roleId, List<Long> permissionIds);

    /**
     * 获取角色列表
     */
    List<UmsRoleEntity> list();
    
    /**
     * 根据用户查询用户的角色code
     * @param adminId
     * @return
     */
    List<String> selectCode(Long adminId);
}
