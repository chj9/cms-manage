package com.dliberty.cms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.dliberty.cms.base.IdEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 后台用户表
 * </p>
 *
 * @author GuoJingtao
 * @since 2019-03-20
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@TableName(value = "users", autoResultMap = true)
public class UsersEntity extends IdEntity {

    private String username;

    private String password;

    private String salt;

    private String trueName;

    /**
     * 邮箱
     */
    private String email;


    /**
     * 帐号启用状态：0->禁用；1->启用
     */
    private Integer status;


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


    public String getTrueName() {
        return trueName;
    }


    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public Integer getStatus() {
        return status;
    }


    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getSalt() {
        return salt;
    }


    public void setSalt(String salt) {
        this.salt = salt;
    }

}
