package com.chj9.cms.service;

import com.chj9.cms.api.dto.UmsPermissionNode;
import com.chj9.cms.api.entity.UmsPermissionEntity;

import java.util.List;

/**
 * 后台用户权限管理Service
 * Created by macro on 2018/9/29.
 */
public interface UmsPermissionService {
    /**
     * 添加权限
     */
    int create(UmsPermissionEntity permission);

    /**
     * 修改权限
     */
    int update(Long id,UmsPermissionEntity permission);

    /**
     * 批量删除权限
     */
    int delete(List<Long> ids);

    /**
     * 以层级结构返回所有权限
     */
    List<UmsPermissionNode> treeList();

    /**
     * 获取所有权限
     */
    List<UmsPermissionEntity> list();
}
