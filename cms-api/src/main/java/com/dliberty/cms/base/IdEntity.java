package com.dliberty.cms.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.dliberty.cms.common.exception.SystemException;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.Instant;

/**
 * 主键实体
 *
 * @author chenxiaobo
 */
public class IdEntity extends BasicIdEntity<Long> {

    private static final long serialVersionUID = -1430010950223063423L;

    /**
     * id 字段名称
     */
    public static final String ID_FIELD_NAME = "id";

    /**
     * 创建时间字段名称
     */
    public static final String CREATED_AT_FIELD_NAME = "createdAt";

    /**
     * 更新时间字段名称
     */
    public static final String UPDATED_AT_FIELD_NAME = "updatedAt";

    /**
     * 主键 id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 创建时间
     */
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT)
    private Instant createTime;

    /**
     * 最后更新时间
     */
    @JsonIgnore
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Instant updateTime;


    public IdEntity() {
    }


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Instant createTime) {
        this.createTime = createTime;
    }

    public Instant getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Instant updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 创建一个新的对象，并把 idEntity 的 id 复制到新对象中
     *
     * @param <T> 主键实体子类
     * @return 新的主键实体
     */
    public <T extends IdEntity> T ofNew() {
        T result;
        try {
            //noinspection unchecked
            result = (T) this.getClass().getConstructor().newInstance();
            result.setCreateTime(null);
            result.setId(getId());
            return result;
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }
}
