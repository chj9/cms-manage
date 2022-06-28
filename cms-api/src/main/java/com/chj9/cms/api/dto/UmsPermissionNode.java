package com.chj9.cms.api.dto;

import java.util.List;

import com.chj9.cms.api.entity.UmsPermissionEntity;

/**
 * Created by macro on 2018/9/30.
 */
public class UmsPermissionNode extends UmsPermissionEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<UmsPermissionNode> children;

	public List<UmsPermissionNode> getChildren() {
		return children;
	}

	public void setChildren(List<UmsPermissionNode> children) {
		this.children = children;
	}
    
    
}
