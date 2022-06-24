package com.dliberty.cms.es;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dliberty.cms.vo.CmsMenuVo;

public interface CmsMenuEsService {

	long count();

	CmsMenuVo save(CmsMenuVo vo);
	
	void saveAll(List<CmsMenuVo> list);

    void delete(CmsMenuVo vo);
    
    void deleteAll();
    
    void deleteById(Integer id);

    Iterable<CmsMenuVo> getAll();

    CmsMenuVo getById(Integer id);
    
    List<CmsMenuVo> getByIds(List<Integer> ids);
    
    boolean existsById(Integer id);

    /**
     * 搜索查询 不分词
     * @param pageNo
     * @param pageSize
     * @param kw
     * @return
     */
    Page<CmsMenuVo> pageQuery(Integer pageNo, Integer pageSize, String kw);
    
    /**
     * 根据类别id 过滤
     * @param pageNo
     * @param pageSize
     * @param categoryId
     * @return
     */
    Page<CmsMenuVo> pageQueryByCateId(Integer pageNo, Integer pageSize, Integer categoryId);
    
    /**
     * 根据labelid 过滤
     * @param pageNo
     * @param pageSize
     * @param labelId
     * @return
     */
    Page<CmsMenuVo> pageQueryByLabelId(Integer pageNo, Integer pageSize, Integer labelId);
}
