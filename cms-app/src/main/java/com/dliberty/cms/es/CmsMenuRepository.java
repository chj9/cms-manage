package com.dliberty.cms.es;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.dliberty.cms.vo.CmsMenuVo;

@Repository
public interface CmsMenuRepository extends ElasticsearchRepository<CmsMenuVo, Integer> {

}
