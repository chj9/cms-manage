package com.dliberty.cms.vo;

import java.io.Serializable;

/**
 * 作者：闫广坤
 * 创建时间：2016年5月18日
 * 功能描述：
 * 版本:2.0.0
 */
public class BaseVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private PageInfo pageInfo;
    private String paramCache;
    private String requestIp;
    private String requestURL;
    private Long userId;
    private String userName;


    public PageInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    public String getParamCache() {
        return paramCache;
    }

    public void setParamCache(String paramCache) {
        this.paramCache = paramCache;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }

    public String getRequestURL() {
        return requestURL;
    }

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    
    
}

