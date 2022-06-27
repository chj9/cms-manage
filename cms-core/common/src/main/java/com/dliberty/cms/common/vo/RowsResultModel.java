package com.dliberty.cms.common.vo;

import java.util.List;

public class RowsResultModel<T> {

	private List<T> list;
    private long total;
    private PageInfo pageInfo;
    private String code = "0";

    public RowsResultModel(List<T> list, long total) {
        this.list = list;
        this.total = total;
    }

    public RowsResultModel(List<T> list, PageInfo pageInfo) {
        this.list = list;
        this.pageInfo = pageInfo;
        this.total = this.pageInfo.getTotalRecords();
    }


    public List<T> getRows() {
        return list;
    }

    public long getTotal() {
        return total;
    }

    public RowsResultModel<T> setList(List<T> list, long total) {
        this.list = list;
        this.total = total;
        return this;
    }

    public RowsResultModel<T> setList(List<T> list, PageInfo pageInfo) {
        setList(list, pageInfo.getTotalRecords());
        this.pageInfo = pageInfo;
        return this;
    }


    public PageInfo getPageInfo() {
        if (this.pageInfo == null) {
            this.pageInfo = new PageInfo();
        }
        return pageInfo;
    }

    public RowsResultModel<T> setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
        if (this.total == 0) {
            this.total = pageInfo.getTotalRecords();
        }
        return this;
    }

    public RowsResultModel() {
        super();
        if (this.pageInfo == null) {
            this.pageInfo = new PageInfo();
        }
    }

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
    
    
}
