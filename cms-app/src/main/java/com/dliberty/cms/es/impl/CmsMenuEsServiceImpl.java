package com.dliberty.cms.es.impl;

import static org.elasticsearch.index.query.QueryBuilders.matchAllQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.IteratorUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;

import com.dliberty.cms.es.CmsMenuEsService;
import com.dliberty.cms.es.CmsMenuRepository;
import com.dliberty.cms.vo.CmsMenuVo;

@Service
public class CmsMenuEsServiceImpl implements CmsMenuEsService {

	private static final Logger logger = LoggerFactory.getLogger(CmsMenuEsServiceImpl.class);

	@Autowired
	private CmsMenuRepository cmsMenuRepository;

	@Override
	public long count() {
		return cmsMenuRepository.count();
	}

	@Override
	public CmsMenuVo save(CmsMenuVo vo) {
		return cmsMenuRepository.save(vo);
	}

	@Override
	public void saveAll(List<CmsMenuVo> list) {
		cmsMenuRepository.saveAll(list);
	}

	@Override
	public void delete(CmsMenuVo vo) {
		cmsMenuRepository.delete(vo);
	}

	@Override
	public void deleteAll() {
		cmsMenuRepository.deleteAll();
	}

	@Override
	public void deleteById(Integer id) {
		cmsMenuRepository.deleteById(id);
	}

	@Override
	public Iterable<CmsMenuVo> getAll() {
		return cmsMenuRepository.findAll();
	}

	@Override
	public CmsMenuVo getById(Integer id) {
		Optional<CmsMenuVo> optional = cmsMenuRepository.findById(id);
		return optional.get();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CmsMenuVo> getByIds(List<Integer> ids) {
		logger.info("ids={}", StringUtils.join(ids, ","));
		if (ids.size() == 0) {
			return new ArrayList<CmsMenuVo>();
		}
		Iterable<CmsMenuVo> menus = cmsMenuRepository.findAllById(ids);
		return IteratorUtils.toList(menus.iterator());
	}

	@Override
	public boolean existsById(Integer id) {
		return cmsMenuRepository.existsById(id);
	}

	@Override
	public Page<CmsMenuVo> pageQuery(Integer pageNo, Integer pageSize, String kw) {

		logger.info("pageNo={},pageSize={},kw={}", pageNo, pageSize, kw);

		PageRequest page = PageRequest.of(pageNo, pageSize);

		if (StringUtils.isEmpty(kw)) {
			SearchQuery query = new NativeSearchQueryBuilder().withQuery(matchAllQuery()).withPageable(page)
					.withSort(SortBuilders.fieldSort("browseNum").order(SortOrder.DESC)).build();
			return cmsMenuRepository.search(query);
		} else {
			// 多字段查找
			MultiMatchQueryBuilder builder = new MultiMatchQueryBuilder(kw).field("menuName", 10).field("menuDesc", 5)
					.field("categoryName", 5).field("stepList.stepDesc", 9).field("materialList.materialName", 10)
					.field("labelList.labelName",9)
					.fuzziness(Fuzziness.AUTO);

			SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(builder).withPageable(page)
					// .withSort(SortBuilders.fieldSort("browseNum").order(SortOrder.DESC))
					.build();
			return cmsMenuRepository.search(searchQuery);
		}

	}

	@Override
	public Page<CmsMenuVo> pageQueryByCateId(Integer pageNo, Integer pageSize, Integer categoryId) {

		logger.info("pageNo={},pageSize={},categoryId={}", pageNo, pageSize, categoryId);

		// 单字段过滤
		TermQueryBuilder tqb = QueryBuilders.termQuery("categoryId", categoryId);

		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(tqb)
				.withPageable(PageRequest.of(pageNo, pageSize))
				.withSort(SortBuilders.fieldSort("browseNum").order(SortOrder.DESC)).build();
		return cmsMenuRepository.search(searchQuery);

	}

	@Override
	public Page<CmsMenuVo> pageQueryByLabelId(Integer pageNo, Integer pageSize, Integer labelId) {
		logger.info("pageNo={},pageSize={},labelId={}", pageNo, pageSize, labelId);

		// 单字段过滤
		TermQueryBuilder tqb = QueryBuilders.termQuery("labels.id", labelId);

		SearchQuery searchQuery = new NativeSearchQueryBuilder().withQuery(tqb)
				.withPageable(PageRequest.of(pageNo, pageSize))
				.withSort(SortBuilders.fieldSort("browseNum").order(SortOrder.DESC)).build();
		return cmsMenuRepository.search(searchQuery);
	}

}
