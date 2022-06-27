package com.dliberty.cms.elasticsearch.service;

import co.elastic.clients.elasticsearch.ElasticsearchAsyncClient;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.CountResponse;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.SearchTemplateRequest;
import co.elastic.clients.elasticsearch.core.SearchTemplateResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.DeleteIndexResponse;
import co.elastic.clients.elasticsearch.indices.RolloverResponse;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.dliberty.cms.common.logging.SouthernQuietLogger;
import com.dliberty.cms.common.logging.SouthernQuietLoggerFactory;
import com.dliberty.cms.common.util.Assert;
import com.dliberty.cms.elasticsearch.vo.IndexDataToSave;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * ES 封装服务类
 * 例子在
 * com.qibingdaojia.turnright.merchant.elasticsearch.service.EsServiceExample
 *
 * @param <T> 索引实体类
 * @author chenhongjie
 * @date 2022/4/13
 */
@SuppressWarnings({"SpringJavaAutowiredFieldsWarningInspection"})
@Service
public class EsService<T> {

    private static final SouthernQuietLogger log = SouthernQuietLoggerFactory.getLogger(EsService.class);

    @Autowired
    private ElasticsearchClient elasticsearchClient;
    @Autowired
    private ElasticsearchAsyncClient elasticsearchAsyncClient;
    @Autowired
    private ObjectMapper mapper;

    public EsService() {
    }

    public EsService(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    private JsonNode toJsonNode(Object source) {
        JsonNode node;
        if (source instanceof JsonNode) {
            node = (JsonNode) source;
        } else {
            node = mapper.valueToTree(source);
        }
        return node;
    }

    /**
     * 单条数据保存，自定义ID
     *
     * @param indexNameOrAlias 索引名称或者别名
     * @param id               数据ID
     * @param source           数据体
     * @return IndexResponse
     */
    public IndexResponse save(String indexNameOrAlias, String id, Object source) throws Exception {
        Assert.notEmpty(indexNameOrAlias, "indexNameOrAlias不能为空");
        Assert.notEmpty(source, "source不能为空");

        JsonNode node = toJsonNode(source);
        return elasticsearchClient.index(indexRequest ->
                indexRequest.index(indexNameOrAlias).id(id).document(node)
        );
    }

    /**
     * 单条数据保存，随机ID
     *
     * @param indexNameOrAlias 索引名称或者别名
     * @param source           数据体
     * @return IndexResponse
     */
    public IndexResponse save(String indexNameOrAlias, Object source) throws IOException {
        Assert.notEmpty(indexNameOrAlias, "indexNameOrAlias不能为空");
        Assert.notEmpty(source, "source不能为空");

        JsonNode node = toJsonNode(source);
        return elasticsearchClient.index(indexRequest ->
                indexRequest.index(indexNameOrAlias).document(node)
        );
    }

    /**
     * 异步单条数据保存，指定ID
     *
     * @param indexNameOrAlias 索引名称或者别名
     * @param id               数据ID
     * @param source           数据体
     */
    public void saveAsync(String indexNameOrAlias, String id, Object source) throws IOException {
        Assert.notEmpty(indexNameOrAlias, "indexNameOrAlias不能为空");
        Assert.notEmpty(source, "source不能为空");

        JsonNode node = toJsonNode(source);
        elasticsearchAsyncClient.index(indexRequest ->
                indexRequest.index(indexNameOrAlias).id(id).document(node)
        ).whenComplete((response, exception) -> {
            if (exception != null) {
                log.message("Failed to index")
                        .exception(exception)
                        .context("index", indexNameOrAlias)
                        .context("source", node)
                        .info();
            }
        });
        ;
    }

    /**
     * 异步单条数据保存，随机ID
     *
     * @param indexNameOrAlias 索引名称或者别名
     * @param source           数据体
     */
    public void saveAsync(String indexNameOrAlias, Object source) throws IOException {
        Assert.notEmpty(indexNameOrAlias, "indexNameOrAlias不能为空");
        Assert.notEmpty(source, "source不能为空");

        JsonNode node = toJsonNode(source);
        elasticsearchAsyncClient.index(indexRequest ->
                indexRequest.index(indexNameOrAlias).document(node)
        ).whenComplete((response, exception) -> {
            if (exception != null) {
                log.message("Failed to index")
                        .exception(exception)
                        .context("index", indexNameOrAlias)
                        .context("source", node)
                        .info();
            }
        });
        ;
    }

    /**
     * 乐观锁版本控制,锁由ES的数据version提供
     *
     * @param indexNameOrAlias 索引名称或者别名
     * @param id               数据ID
     * @param version          数据version
     * @param source           数据体
     * @return IndexResponse
     */
    public IndexResponse save(String indexNameOrAlias, String id, long version, Object source) throws IOException {
        Assert.notEmpty(indexNameOrAlias, "indexNameOrAlias不能为空");
        Assert.notEmpty(source, "source不能为空");
        JsonNode node = toJsonNode(source);
        return elasticsearchClient.index(indexRequest ->
                indexRequest.index(indexNameOrAlias).id(id).version(version).document(node)
        );
    }


    /**
     * 批量保存数据
     *
     * @param indexNameOrAlias 索引名称或者别名
     * @param dataList         数据列表
     * @return BulkResponse
     */
    public BulkResponse saveBatch(String indexNameOrAlias, List<IndexDataToSave> dataList) throws IOException {
        Assert.notEmpty(indexNameOrAlias, "indexNameOrAlias不能为空");
        List<BulkOperation> bulkOperationList = saveBatch(dataList);
        return elasticsearchClient.bulk(bulkRequest ->
                bulkRequest.index(indexNameOrAlias).operations(bulkOperationList)
        );
    }

    /**
     * 异步批量保存数据
     *
     * @param indexNameOrAlias 索引名称或者别名
     * @param dataList         数据列表
     */
    public void saveAsyncBatch(String indexNameOrAlias, List<IndexDataToSave> dataList) throws IOException {
        Assert.notEmpty(indexNameOrAlias, "indexNameOrAlias不能为空");
        List<BulkOperation> bulkOperationList = saveBatch(dataList);
        elasticsearchAsyncClient.bulk(bulkRequest ->
                bulkRequest.index(indexNameOrAlias).operations(bulkOperationList)
        ).whenComplete((response, exception) -> {
            if (exception != null) {
                log.message("Failed Batch to index")
                        .exception(exception)
                        .context("index", indexNameOrAlias)
                        .info();
            }
        });
        ;
    }

    private List<BulkOperation> saveBatch(List<IndexDataToSave> dataList) {
        Assert.notEmpty(dataList, "dataList不能为空");
        List<BulkOperation> bulkOperationList = new ArrayList<>();
        dataList.stream()
                .filter(data -> data.getId() != null && data.getPayload() != null)
                .map(data -> new BulkOperation.Builder().create(e ->
                        e.id(data.getId()).document(toJsonNode(data.getPayload()))
                ).build())
                .forEach(bulkOperationList::add);
        return bulkOperationList;
    }

    /**
     * 查询索引是否存在
     *
     * @param indexNameOrAlias 索引名称或者别名
     * @return 存在 true; 不存在 false
     */
    public boolean isIndexExisted(String indexNameOrAlias) throws IOException {
        Assert.notEmpty(indexNameOrAlias, "indexNameOrAlias不能为空");
        BooleanResponse booleanResponse = elasticsearchClient.indices()
                .exists(existsRequest ->
                        existsRequest.index(indexNameOrAlias)
                );
        return booleanResponse.value();
    }

    /**
     * rollover滚动索引,数据开始往新的索引写，旧的索引可查
     *
     * @param rolloverAlias  rollover别名名称
     * @param deleteOldIndex 是否删除旧索引
     * @return 成功 true; 失败 false
     */
    public boolean rolloverNewIndex(String rolloverAlias, boolean deleteOldIndex) throws IOException {
        RolloverResponse response = elasticsearchClient.indices().rollover(
                rolloverRequest -> rolloverRequest.alias(rolloverAlias)
        );
        if (deleteOldIndex) {
            String oldIndex = response.oldIndex();
            deleteIndexIfExisted(oldIndex);
        }
        return response.rolledOver();
    }

    /**
     * 删除索引
     *
     * @param indexName 索引名称
     * @return 成功 true; 失败 false
     */
    public boolean deleteIndexIfExisted(String indexName) throws IOException {
        Assert.notEmpty(indexName, "indexName不能为空");
        boolean deleteIndexResponse = false;
        if (isIndexExisted(indexName)) {
            deleteIndexResponse = deleteIndex(indexName);
        } else {
            log.message("索引名不存在，删除失败")
                    .context("索引名", indexName)
                    .warn();
        }
        return deleteIndexResponse;
    }

    private boolean deleteIndex(String indexName) throws IOException {
        DeleteIndexResponse deleteIndexResponse = elasticsearchClient.indices()
                .delete(deleteIndexRequest ->
                        deleteIndexRequest.index(indexName)
                );
        return deleteIndexResponse.acknowledged();
    }

    /**
     * 求索引的数据数量
     *
     * @param indexNameOrAlias 索引名称或者别名
     * @param query            查询类
     * @return 数据量
     */
    public long countDocument(String indexNameOrAlias, Query query) throws IOException {
        Assert.notEmpty(indexNameOrAlias, "indexNameOrAlias不能为空");
        CountResponse count = elasticsearchClient.count(
                countRequest -> countRequest.index(indexNameOrAlias).query(query)
        );
        return count.count();
    }

    /**
     * 查询返回响应体，一般使用聚合查询的话使用这个方法
     * 参考方法 esDataTModelList 转换成对象
     *
     * @param searchRequest 请求查询体
     * @return 查询返回响应体
     */
    public SearchResponse<JsonNode> search(SearchRequest searchRequest) throws IOException {
        return elasticsearchClient.search(searchRequest, JsonNode.class);
    }

    /**
     * 根据ID查询
     *
     * @param id        文档id
     * @param dataModel 数据体模型
     * @return 查询返回响应体
     */
    public T searchById(String indexName, String id, Class<T> dataModel) throws IOException {
        SearchRequest searchRequest = SearchRequest.of(r -> r
                .index(indexName)
                .query(
                        myTerm -> myTerm.term(
                                termQuery -> termQuery.field("_id").value(value -> value.stringValue(id))
                        )
                ));
        SearchResponse<JsonNode> response = elasticsearchClient.search(searchRequest, JsonNode.class);
        return esDataTModel(response.hits().hits(), dataModel);
    }

    /**
     * in查询
     *
     * @param indexName 索引名
     * @param field     in查询的字段
     * @param valueList in查询的内容
     * @param dataModel 数据体模型
     * @return 查询返回响应体
     */
    public List<T> searchByIn(String indexName, String field, List<String> valueList, Class<T> dataModel) throws IOException {
        List<FieldValue> v = valueList.stream().filter(Objects::nonNull).map(FieldValue::of).collect(Collectors.toList());
        Query query = Query.of(myTerm -> myTerm.terms(
                termQuery -> termQuery.field(field).terms(value -> value.value(v))));
        long count = countDocument(indexName, query);
        SearchRequest searchRequest = SearchRequest.of(r -> r
                .index(indexName)
                .size((int) count)
                .query(query)
        );
        SearchResponse<JsonNode> response = elasticsearchClient.search(searchRequest, JsonNode.class);
        return esDataTModelList(response.hits().hits(), dataModel);
    }

    /**
     * 查询返回数据列表，一般不使用聚合查询的话使用这个方法，更简单
     * 数据量 使用size超过 10000 条数据可能会报错，由index.max_result_window控制
     *
     * @param searchRequest 请求查询体
     * @param dataModel     数据体模型
     * @return 数据列表
     */
    public List<T> searchDocList(SearchRequest searchRequest, Class<T> dataModel) throws IOException {
        SearchResponse<JsonNode> searchResponse = elasticsearchClient.search(searchRequest, JsonNode.class);
        return esDataTModelList(searchResponse.hits().hits(), dataModel);
    }

    /**
     * 根据查询模板查询
     *
     * @param searchTemplateRequest 请求查询体
     * @param dataModel             数据体模型
     * @return 查询返回响应体
     */
    public List<T> searchBySearchTemplate(SearchTemplateRequest searchTemplateRequest, Class<T> dataModel) throws IOException {
        SearchTemplateResponse<JsonNode> response = elasticsearchClient.searchTemplate(searchTemplateRequest, JsonNode.class);
        return esDataTModelList(response.hits().hits(), dataModel);
    }

    public SearchTemplateResponse<JsonNode> searchBySearchTemplate(SearchTemplateRequest searchTemplateRequest) throws IOException {
        return elasticsearchClient.searchTemplate(searchTemplateRequest, JsonNode.class);
    }

    public List<T> esDataTModelList(List<Hit<JsonNode>> hits, Class<T> dataModel) throws JsonProcessingException {
        if (CollectionUtils.isEmpty(hits)) {
            return new ArrayList<>();
        }
        List<T> list = new ArrayList<>(hits.size());
        for (Hit<JsonNode> nodeHit : hits) {
            list.add(mapper.treeToValue(nodeHit.source(), dataModel));
        }
        return list;
    }

    private T esDataTModel(List<Hit<JsonNode>> hits, Class<T> dataModel) throws JsonProcessingException {
        List<T> list = esDataTModelList(hits, dataModel);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            return list.get(0);
        }
    }

    /**
     * 删除es索引中的文档
     *
     * @param indexNameOrAlias 索引名称或者别名
     * @param documentId       数据ID
     */
    public void deleteDocumentById(String indexNameOrAlias, String documentId) throws IOException {
        Assert.notEmpty(indexNameOrAlias, "indexNameOrAlias不能为空");
        Assert.notEmpty(documentId, "documentId不能为空");
        elasticsearchClient.delete(request ->
                request.index(indexNameOrAlias).id(documentId)
        );
    }

    /**
     * 根据搜索条件删除es指定索引中的数据
     *
     * @param indexNameOrAlias 索引名称或者别名
     * @param query            查询条件
     * @param refresh          是否立刻生效,false的话会等待 index.refresh_interval 的刷新间隔
     */
    public void deleteDocumentByQuery(String indexNameOrAlias, Query query, boolean refresh) throws IOException {
        Assert.notEmpty(indexNameOrAlias, "indexNameOrAlias不能为空");
        elasticsearchClient.deleteByQuery(deleteByQueryRequest ->
                deleteByQueryRequest.index(indexNameOrAlias).query(query).refresh(refresh)
        );
    }

    /**
     * 根据搜索条件更新es指定索引中的数据
     *
     * @param indexNameOrAlias 索引名称或者别名
     * @param query            查询条件
     * @param refresh          是否立刻生效,false的话会等待 index.refresh_interval 的刷新间隔
     */
    public void updateDocumentByQuery(String indexNameOrAlias, Query query, boolean refresh) throws IOException {
        Assert.notEmpty(indexNameOrAlias, "indexNameOrAlias不能为空");
        elasticsearchClient.updateByQuery(updateByQueryRequest ->
                updateByQueryRequest.index(indexNameOrAlias).query(query).refresh(refresh)
        );
    }
}
