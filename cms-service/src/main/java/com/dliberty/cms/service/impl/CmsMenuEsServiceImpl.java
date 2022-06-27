package com.dliberty.cms.service.impl;


import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.query_dsl.MultiMatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.TermQuery;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.elasticsearch.core.search.TotalHitsRelation;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.dliberty.cms.elasticsearch.model.vo.IndexDataToSave;
import com.dliberty.cms.elasticsearch.service.EsService;
import com.dliberty.cms.logging.SouthernQuietLogger;
import com.dliberty.cms.logging.SouthernQuietLoggerFactory;
import com.dliberty.cms.service.CmsMenuEsService;
import com.dliberty.cms.vo.CmsMenuQueryParam;
import com.dliberty.cms.vo.CmsMenuVo;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CmsMenuEsServiceImpl implements CmsMenuEsService {

    private static final SouthernQuietLogger LOGGER = SouthernQuietLoggerFactory.getLogger(CmsMenuEsServiceImpl.class);

    private final EsService<CmsMenuVo> esService;

    private final String WRITE_ALIAS_NAME = "menu";

    @Autowired
    public CmsMenuEsServiceImpl(EsService<CmsMenuVo> esService) {
        this.esService = esService;
    }

    @Override
    public long count() {
        try {
            return esService.countDocument(WRITE_ALIAS_NAME, handleQuery(null));
        } catch (Exception e) {
            LOGGER.message("查询失败")
                    .exception(e)
                    .error();
        }
        return 0L;
    }

    @Override
    public void save(CmsMenuVo vo) {
        try {
            esService.save(WRITE_ALIAS_NAME, vo);
        } catch (Exception e) {
            LOGGER.message("查询失败")
                    .exception(e)
                    .error();
        }
    }

    @Override
    public void saveAll(List<CmsMenuVo> list) {
        try {
            List<IndexDataToSave> saveList = list.stream().map(item -> {
                return new IndexDataToSave(item.getId(), item);
            }).collect(Collectors.toList());
            esService.saveBatch(WRITE_ALIAS_NAME, saveList);
        } catch (Exception e) {
            LOGGER.message("查询失败")
                    .exception(e)
                    .error();
        }
    }


    @Override
    public void delete(CmsMenuQueryParam param) {
        try {
            esService.deleteDocumentByQuery(WRITE_ALIAS_NAME, handleQuery(param), true);
        } catch (Exception e) {
            LOGGER.message("查询失败")
                    .exception(e)
                    .error();
        }
    }

    @Override
    public void deleteAll() {
        try {
            esService.deleteDocumentByQuery(WRITE_ALIAS_NAME, handleQuery(null), true);
        } catch (Exception e) {
            LOGGER.message("查询失败")
                    .exception(e)
                    .error();
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            esService.deleteDocumentById(WRITE_ALIAS_NAME, id);
        } catch (Exception e) {
            LOGGER.message("查询失败")
                    .exception(e)
                    .error();
        }
    }

    @Override
    public Iterable<CmsMenuVo> getAll() {
        return null;
    }

    @Override
    public CmsMenuVo getById(String id) {
        return null;
    }

    @Override
    public List<CmsMenuVo> getByIds(List<String> ids) {
        return null;
    }

    @Override
    public boolean existsById(String id) {
        return false;
    }

    @Override
    public PageDTO<CmsMenuVo> pageQuery(Integer pageNo, Integer pageSize, CmsMenuQueryParam param) {
        LOGGER.message("Query")
                .context("pageNo", pageNo)
                .context("pageSize", pageSize)
                .context("CmsMenuVo", param)
                .info();
        try {
            PageRequest page = PageRequest.of(pageNo, pageSize);
            Query finalSearchTextQuery = handleQuery(param);
            SearchRequest searchRequest = SearchRequest.of(r -> r
                    .index(WRITE_ALIAS_NAME)
                    .query(finalSearchTextQuery)
                    .from(page.getPageNumber())
                    .size(page.getPageSize())
                    .sort(handleSort())
            );
            SearchResponse<JsonNode> response = esService.search(searchRequest);
            TotalHits totalHits = response.hits().total();
            long totalNum;
            if (totalHits != null && totalHits.relation() == TotalHitsRelation.Eq) {
                totalNum = totalHits.value();
            } else {
                totalNum = esService.countDocument(WRITE_ALIAS_NAME, finalSearchTextQuery);
            }
            List<CmsMenuVo> esOrderServiceList = esService.esDataTModelList(response.hits().hits(), CmsMenuVo.class);
            PageDTO<CmsMenuVo> pageDTO = new PageDTO<>();
            pageDTO.setCurrent(pageNo);
            pageDTO.setSize(pageSize);
            pageDTO.setTotal(totalNum);
            pageDTO.setRecords(esOrderServiceList);
            return pageDTO;
        } catch (Exception e) {
            LOGGER.message("查询失败")
                    .exception(e)
                    .error();
        }
        return null;
    }


    /**
     * 处理排序，默认按创建时间倒序
     */
    private List<SortOptions> handleSort() {
        List<SortOptions> sortOptionsList = new ArrayList<>();
        SortOptions sortOptions = SortOptions.of(s -> s.field(f -> f.field("createTime").order(SortOrder.Desc)));
        sortOptionsList.add(sortOptions);
        return sortOptionsList;
    }

    /**
     * 查询处理器
     */
    private Query handleQuery(CmsMenuQueryParam param) {
        if (param == null) {
            return Query.of(query -> query.matchAll(
                    all -> all.boost(1.0F)
            ));
        }
        List<Query> queryList = new ArrayList<>();
        if (StringUtils.isEmpty(param.getKeyword())) {
            Query query = MultiMatchQuery.of(m -> m
                    .fields(List.of("menuName", "menuDesc", "categoryName", "stepList.stepDesc", "materialList.materialName", "labelList.labelName"))
                    .query(String.valueOf(FieldValue.of(param.getKeyword())))
            )._toQuery();
            queryList.add(query);
        }
        if (StringUtils.isEmpty(param.getCategoryId())) {
            Query query = TermQuery.of(m -> m
                    .field("categoryId").value(value -> value.stringValue(param.getCategoryId()))
            )._toQuery();
            queryList.add(query);
        }
        if (StringUtils.isEmpty(param.getLabelId())) {
            Query query = TermQuery.of(m -> m
                    .field("labels.id").value(value -> value.stringValue(param.getLabelId()))
            )._toQuery();
            queryList.add(query);
        }
        if (CollectionUtils.isEmpty(queryList)) {
            return Query.of(query -> query.matchAll(
                    all -> all.boost(1.0F)
            ));
        }
        // 在同一个 boolQuery 中 must 会将 should 覆盖
        return Query.of(query -> query.bool(
                boolQuery -> boolQuery
                        .must(queryList)
        ));
    }
}
