package com.chj9.cms.api.dto;

import javax.validation.constraints.NotBlank;


/**
 * 用户登录参数
 * Created by macro on 2018/4/26.
 */
public class UmsAdminLoginParam {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
