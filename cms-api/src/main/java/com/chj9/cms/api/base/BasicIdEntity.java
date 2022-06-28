package com.chj9.cms.api.base;

import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;

public class BasicIdEntity<T> extends Model implements Serializable {

    private static final long serialVersionUID = -2579649132362949467L;

    private T id;

    public BasicIdEntity() {
    }

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }
}
