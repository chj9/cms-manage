package com.dliberty.cms.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@TableName(value = "ums_role", autoResultMap = true)
public class UmsRoleEntity implements Serializable {
    private Long id;

    /**
     * 名称
     *
     * @mbggenerated
     */
    private String name;

    /**
     * 描述
     *
     * @mbggenerated
     */
    private String description;

    /**
     * 角色code
     */
    private String code;

    /**
     * 后台用户数量
     *
     * @mbggenerated
     */
    private Integer adminCount;

    /**
     * 创建时间
     *
     * @mbggenerated
     */
    private Instant createTime;

    /**
     * 启用状态：0->禁用；1->启用
     *
     * @mbggenerated
     */
    private Integer status;

    private Integer sort;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAdminCount() {
        return adminCount;
    }

    public void setAdminCount(Integer adminCount) {
        this.adminCount = adminCount;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", description=").append(description);
        sb.append(", adminCount=").append(adminCount);
        sb.append(", createTime=").append(createTime);
        sb.append(", status=").append(status);
        sb.append(", sort=").append(sort);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}