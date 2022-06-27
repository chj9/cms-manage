package com.dliberty.cms.common.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 分页结果
 *
 * @author
 */
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = 4759135376933706178L;
    /**
     * 记录总数
     */
    private long totals;
    /**
     * 总页数
     */
    private long pages;
    /**
     * 当前页码
     */
    private long current;
    /**
     * 每页容量
     */
    private long size;
    /**
     * 数据列表
     */
    private List<T> records = Collections.emptyList();

    public PageResult() {
    }

    public PageResult(PageParam pageParam) {
        this.current = pageParam.getCurrent();
        this.size = pageParam.getSize();
        this.totals = 0L;
        this.pages = 0L;
        this.records = Collections.emptyList();
    }

    public PageResult(PageParam pageParam, List<T> records, long totals, long pages) {
        this.current = pageParam.getCurrent();
        this.size = pageParam.getSize();
        this.totals = totals;
        this.pages = pages;
        this.records = records;
    }

    public PageResult(long current, long size, long totals, long pages, List<T> records) {
        this.current = current;
        this.size = size;
        this.totals = totals;
        this.pages = pages;
        this.records = records;
    }

    public long getTotals() {
        return totals;
    }

    public void setTotals(long totals) {
        this.totals = totals;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    /**
     * 类型转换函数式接口
     *
     * @param <R> 返回类型
     * @param <P> 待转换类型
     */
    @FunctionalInterface
    public interface PageElementsConverter<R, P> {
        /**
         * 转换方法
         *
         * @param p 待转换数据
         * @return 转换结果
         */
        R convert(P p);
    }

    public static <R, P> List<R> convertListElements(List<P> list, PageElementsConverter<R, P> converter) {
        List<R> rList = new ArrayList<>();
        list.forEach(item -> {
            R result = converter.convert(item);
            rList.add(result);
        });
        return rList;
    }

    /**
     * 转换分页结果中列表元素的类型
     *
     * @param pageParam 分页查询请求参数
     * @param dto       mybatis分页查询结果
     * @param converter 转换函数式接口方法
     * @param <T>       转换后的类型
     * @param <P>       入参类型
     * @return 转换后的分页结果
     */
    public static <T, P> PageResult<T> convertPage(PageParam pageParam,
                                                   Page<P> dto,
                                                   PageElementsConverter<T, P> converter) {
        List<T> tList = convertListElements(dto.getRecords(), converter);
        return new PageResult<>(pageParam, tList, dto.getTotal(), dto.getPages());
    }


    /**
     * 转换分页结果中列表元素的类型
     *
     * @param page      mybatis分页查询结果
     * @param converter 转换函数式接口方法
     * @param <T>       转换后的类型
     * @param <P>       入参类型
     * @return 转换后的分页结果
     */
    public static <T, P> PageResult<T> convertPage(Page<P> page, PageElementsConverter<T, P> converter) {
        List<T> tList = convertListElements(page.getRecords(), converter);
        return new PageResult<>(page.getCurrent(), page.getSize(), page.getTotal(), page.getPages(), tList);
    }

    public static <T> PageResult<T> valueOf(Paging<T> paging) {
        if (paging != null) {
            return new PageResult<>(
                paging.getCurrentPageNum(),
                paging.getCurrentPageSize(),
                paging.getTotalElements(),
                paging.getTotalPages(),
                paging.getElementList());
        }
        return new PageResult<>(new PageParam());
    }
}
