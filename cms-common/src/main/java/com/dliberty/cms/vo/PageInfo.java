package com.dliberty.cms.vo;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class PageInfo.
 */
public class PageInfo implements Serializable {

	private static final long serialVersionUID = 1L;
    /**
     * The logger.
     */
    static Logger logger = LoggerFactory.getLogger(PageInfo.class);

    /**
     * The total records.
     */
    private int totalRecords;

    /**
     * The total pages.
     */
    private int totalPages;

    /**
     * The page size.
     */
    private int pageSize = 5;

    /**
     * The current page no.
     */
    private int pageNum;

    /**
     * The previous page no.
     */
    private int previousPageNo;

    /**
     * The next page no.
     */
    private int nextPageNo;

    /**
     * The is first page.
     */
    private boolean isFirstPage;

    /**
     * The is last page.
     */
    private boolean isLastPage;

    /**
     * The has previous pag.
     */
    private boolean hasPreviousPage;

    /**
     * The has next page.
     */
    private boolean hasNextPage;



    public PageInfo() {
        this.pageNum = 1;
    }

    public PageInfo(int totalRecords, int pageNum) {

        this(totalRecords, pageNum, 10);
    }

    /**
     * Instantiates a new page info.
     *
     * @param totalRecords the total records
     */
    public PageInfo(int totalRecords, int pageNum, int pageSize) {
        logger.debug("Start to initialize the page info.");

        // 设置每页记录数
        setPageSize(pageSize);
        // 设置当前页
        this.pageNum = pageNum;

        infoTotalRecords(totalRecords);
    }

    public void infoTotalRecords(int totalRecords) {
        // 设置总记录数
        if (totalRecords >= 0) {
            this.totalRecords = totalRecords;
        } else {
            totalRecords = 0;
        }

        // 设置总页面数
        if (totalRecords % pageSize == 0) {
            totalPages = totalRecords / pageSize;
        } else {
            totalPages = (totalRecords / pageSize) + 1;
        }

        // 设置当前页
        if (pageNum > totalPages) {
            this.pageNum = totalPages;
        }

        // 每次设置当前页时更新上一页、下一页、是否首页、是否末页、是否有上一页、是否有下一页标志
        isFirstPage = (pageNum == 1) ? true : false;
        isLastPage = (pageNum == totalPages) ? true : false;
        hasPreviousPage = (pageNum == 1) ? false : true;
        hasNextPage = (pageNum == totalPages) ? false : true;
        previousPageNo = (hasPreviousPage) ? (pageNum - 1) : pageNum;
        nextPageNo = (hasNextPage) ? (pageNum + 1) : pageNum;
    }

    /**
     * Gets the current page no.
     *
     * @return the current page no
     */
    public int getPageNum() {
        return pageNum;
    }

    /**
     * Sets the current page no.
     *
     * @param pageNo the new current page no
     */
    public void setPageNum(int pageNum) {
        if (pageNum < 0) {
            this.pageNum = 1;
        } else {
            this.pageNum = pageNum;
        }
    }

    /**
     * Gets the current page size.
     *
     * @return the current page size
     */
    public int getCurrentPageSize() {
        if (totalRecords == 0) {
            return 0;
        } else if (pageNum < totalPages) {
            // 非末页
            return pageSize;
        } else {
            // 末页
            return (totalRecords - (pageNum - 1) * pageSize);
        }
    }

    /**
     * Gets the current page start record.
     *
     * @return the current page start record
     */
    public int getCurrentPageStartRecord() {
        if (pageNum < 1) pageNum = 1;
        return (pageNum - 1) * pageSize;
    }

    /**
     * Gets the current page end record.
     *
     * @return the current page end record
     */
    public int getCurrentPageEndRecord() {
        return (pageNum - 1) * pageSize + getCurrentPageSize();
    }

    /**
     * Checks if is has next page.
     *
     * @return true, if is has next page
     */
    public boolean isHasNextPage() {
        return hasNextPage;
    }

    /**
     * Checks if is has previous pag.
     *
     * @return true, if is has previous pag
     */
    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    /**
     * Checks if is first page.
     *
     * @return true, if is first page
     */
    public boolean isFirstPage() {
        return isFirstPage;
    }

    /**
     * Checks if is last page.
     *
     * @return true, if is last page
     */
    public boolean isLastPage() {
        return isLastPage;
    }

    /**
     * Gets the page size.
     *
     * @return the page size
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * Sets the page size.
     *
     * @param pageSize the new page size
     */
    public void setPageSize(int pageSize) {
        this.pageSize = (pageSize <= 0) ? 10 : pageSize;
    }

    /**
     * Gets the previous page no.
     *
     * @return the previous page no
     */
    public int getPreviousPageNo() {
        return previousPageNo;
    }

    /**
     * Gets the next page no.
     *
     * @return the next page no
     */
    public int getNextPageNo() {
        return nextPageNo;
    }

    /**
     * Gets the total pages.
     *
     * @return the total pages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * Gets the total records.
     *
     * @return the total records
     */
    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
        infoTotalRecords(this.totalRecords);
    }

}