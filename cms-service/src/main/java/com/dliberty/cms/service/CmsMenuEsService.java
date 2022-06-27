package com.dliberty.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.dliberty.cms.common.page.PageParam;
import com.dliberty.cms.vo.CmsMenuQueryParam;
import com.dliberty.cms.vo.CmsMenuVo;

import java.io.IOException;
import java.util.List;

public interface CmsMenuEsService {

	long count() throws IOException;

    void save(CmsMenuVo vo);
	
	void saveAll(List<CmsMenuVo> list);

    void delete(CmsMenuQueryParam vo);
    
    void deleteAll();
    
    void deleteById(String id);


    Iterable<CmsMenuVo> getAll();

    CmsMenuVo getById(String id);

    List<CmsMenuVo> getByIds(List<String> ids);

    boolean existsById(String id);


    /**
     * 搜索查询 不分词
     * @param pageNo
     * @param pageSize
     * @param kw
     * @return
     */
    PageDTO<CmsMenuVo> pageQuery(PageParam pageParam, CmsMenuQueryParam param);
}
