package com.chj9.cms.api.base;

import java.time.Instant;

/**
 * 逻辑删除实体
 *
 * @author chenxiaobo
 */
public class LogicDeleteEntity extends IdEntity {

    private static final long serialVersionUID = 2906056237154364425L;

    /**
     * 更新时间字段名称
     */
    public static final String DELETED_AT_FIELD_NAME = "deletedAt";

    /**
     * 逻辑删除时间
     */
    private Instant deletedAt;

    public LogicDeleteEntity() {
    }

    /**
     * 获取删除时间
     *
     * @return 删除时间
     */
    public Instant getDeletedAt() {
        return deletedAt;
    }

    /**
     * 设置删除时间
     *
     * @param deletedAt 删除时间
     */
    public void setDeletedAt(Instant deletedAt) {
        this.deletedAt = deletedAt;
    }

}
