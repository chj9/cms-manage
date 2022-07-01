package com.chj9.cms.web.advice;


import com.chj9.cms.common.page.PageParam;
import com.chj9.cms.common.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author
 */
@RestControllerAdvice
public class CmsControllerAdvice {

    @ModelAttribute
    public PageParam pageParam(HttpServletRequest request) {
        String page = request.getParameter("current");
        String pageSize = request.getParameter("size");
        String offset = request.getParameter("offset");
        String limit = request.getParameter("limit");

        PageParam pageParam = new PageParam();
        if (StringUtil.allEmpty(page, pageSize, offset, limit)) {
            return pageParam;
        }

        if (StringUtils.isNumeric(page) || StringUtils.isNumeric(pageSize)) {
            if (StringUtils.isNumeric(page)) {
                pageParam.setCurrent(Integer.parseInt(page));
            }
            if (StringUtils.isNumeric(pageSize)) {
                pageParam.setSize(Integer.parseInt(pageSize));
            }
        } else if (StringUtils.isNumeric(offset) || StringUtils.isNumeric(limit)) {
            if (StringUtils.isNumeric(offset)) {
                pageParam.setOffset(Integer.parseInt(offset));
            }
            if (StringUtils.isNumeric(limit)) {
                pageParam.setLimit(Integer.parseInt(limit));
            }
        }
        return pageParam;
    }

}
