package com.chj9.cms.common.page;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Paging<T> {
    private long totalElements;
    private long totalPages;

    private long currentPageNum;
    private long currentPageSize;

    private List<T> elementList = Collections.emptyList();

    public Paging() {
    }

    public static long calTotalPages(long totalElements, long currentPageSize) {
        long totalPages = totalElements / currentPageSize;
        if (totalElements % currentPageSize > 0) {
            totalPages += 1;
        }
        return totalPages;
    }

    public Paging(List<T> totalElementList, long currentPageNum, long currentPageSize, long totalElements) {
        this.currentPageNum = currentPageNum;
        this.currentPageSize = currentPageSize;
        this.totalElements = totalElements;

        if (0 == currentPageSize) return;

        this.totalPages = calTotalPages(totalElements, currentPageSize);

        this.elementList = totalElementList.stream()
                .skip(Math.max(currentPageNum - 1, 0) * currentPageSize)
                .limit(currentPageSize)
                .collect(Collectors.toList());
    }

    public Paging(List<T> totalElementList, long currentPageNum, long currentPageSize) {
        this.currentPageNum = currentPageNum;
        this.currentPageSize = currentPageSize;
        this.totalElements = totalElementList.size();

        if (0 == currentPageSize) return;

        this.totalPages = calTotalPages(totalElements, currentPageSize);

        this.elementList = totalElementList.stream()
                .skip((currentPageNum - 1) * currentPageSize)
                .limit(currentPageSize)
                .collect(Collectors.toList());
    }


    /**
     * 静态方法返回当前页对象
     *
     * @param elementList     当前页数据
     * @param currentPageNum  当前页
     * @param currentPageSize 每页条数
     * @param totalElements   数据总数
     * @param <T>             对象
     * @return Paging
     */
    public static <T> Paging<T> currentPaging(List<T> elementList, long currentPageNum, long currentPageSize, long totalElements) {
        Paging<T> paging = new Paging<>();
        // 当前页
        paging.setCurrentPageNum(currentPageNum);
        // 每页条数
        paging.setCurrentPageSize(currentPageSize);
        // 数据总数
        paging.setTotalElements(totalElements);
        // 总页数
        paging.setTotalPages(Paging.calTotalPages(totalElements, currentPageSize));
        // 当前页数据
        paging.setElementList(elementList);
        return paging;
    }


    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

    public long getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(long currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public long getCurrentPageSize() {
        return currentPageSize;
    }

    public void setCurrentPageSize(long currentPageSize) {
        this.currentPageSize = currentPageSize;
    }

    public List<T> getElementList() {
        return elementList;
    }

    public void setElementList(List<T> elementList) {
        this.elementList = elementList;
    }
}

