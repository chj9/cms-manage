package com.dliberty.cms.service.impl;

import com.dliberty.cms.dao.mapper.UmsPermissionMapper;
import com.dliberty.cms.dto.UmsPermissionNode;
import com.dliberty.cms.entity.UmsPermissionEntity;
import com.dliberty.cms.entity.UmsPermissionExampleEntity;
import com.dliberty.cms.service.UmsPermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 后台用户权限管理Service实现类
 * Created by macro on 2018/9/29.
 */
@Service
public class UmsPermissionServiceImpl implements UmsPermissionService {
    @Autowired
    private UmsPermissionMapper permissionMapper;

    @Override
    public int create(UmsPermissionEntity permission) {
        permission.setStatus(1);
        permission.setSort(0);
        return permissionMapper.insert(permission);
    }

    @Override
    public int update(Long id, UmsPermissionEntity permission) {
        permission.setId(id);
        return permissionMapper.updateByPrimaryKey(permission);
    }

    @Override
    public int delete(List<Long> ids) {
        UmsPermissionExampleEntity example = new UmsPermissionExampleEntity();
        example.createCriteria().andIdIn(ids);
        return permissionMapper.deleteByExample(example);
    }

    @Override
    public List<UmsPermissionNode> treeList() {
        List<UmsPermissionEntity> permissionList = permissionMapper.selectByExample(new UmsPermissionExampleEntity());
        List<UmsPermissionNode> result = permissionList.stream()
                .filter(permission -> permission.getPid().equals(0L))
                .map(permission -> covert(permission,permissionList)).collect(Collectors.toList());
        return result;
    }

    @Override
    public List<UmsPermissionEntity> list() {
        return permissionMapper.selectByExample(new UmsPermissionExampleEntity());
    }

    /**
     * 将权限转换为带有子级的权限对象
     * 当找不到子级权限的时候map操作不会再递归调用covert
     */
    private UmsPermissionNode covert(UmsPermissionEntity permission,List<UmsPermissionEntity> permissionList){
        UmsPermissionNode node = new UmsPermissionNode();
        BeanUtils.copyProperties(permission,node);
        List<UmsPermissionNode> children = permissionList.stream()
                .filter(subPermission -> subPermission.getPid().equals(permission.getId()))
                .map(subPermission -> covert(subPermission,permissionList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
