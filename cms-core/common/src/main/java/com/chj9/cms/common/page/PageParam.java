package com.chj9.cms.common.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

public class PageParam implements Serializable {

    private static final long serialVersionUID = -6341308040600454505L;

    public static final int MIN_PAGE = 1;
    public static final int MIN_OFFSET = 0;
    public static final int MIN_PAGE_SIZE = 1;
    public static final int MAX_PAGE_SIZE = 100;

    private static final int DEFAULT_PAGE = MIN_PAGE;
    private static final int DEFAULT_PAGE_SIZE = 10;

    private static final int DEFAULT_OFFSET = 0;
    private static final int DEFAULT_LIMIT = DEFAULT_PAGE_SIZE;

    /**
     * 当前页
     */
    @Min(MIN_PAGE)
    private int current = DEFAULT_PAGE;

    /**
     * 每页记录容量
     */
    @Min(MIN_PAGE_SIZE)
    private int size = DEFAULT_PAGE_SIZE;

    /**
     * 页数偏移量
     */
    @Min(MIN_OFFSET)
    private int offset = DEFAULT_OFFSET;

    /**
     * 数量限制
     */
    @Min(MIN_PAGE_SIZE)
    @Max(MAX_PAGE_SIZE)
    private int limit = DEFAULT_LIMIT;

    public PageParam() {}

    public PageParam(int current, int size) {
        this.current = current;
        this.size = size;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = Math.max(current, MIN_PAGE);
        this.offset = this.size * (this.current - 1);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = Math.max(size, MIN_PAGE_SIZE);
        this.size = Math.min(this.size, MAX_PAGE_SIZE);

        this.limit = this.size;
        this.offset = this.size * (this.current - 1);
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = Math.max(offset, MIN_OFFSET);
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = Math.max(limit, MIN_PAGE_SIZE);
        this.limit = Math.min(this.limit, MAX_PAGE_SIZE);
    }

    public <T> PageDTO<T> toPage() {
        return new PageDTO<>(this.getCurrent(), this.getSize());
    }

}
