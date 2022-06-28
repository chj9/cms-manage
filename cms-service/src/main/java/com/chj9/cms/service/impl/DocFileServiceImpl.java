package com.chj9.cms.service.impl;


import com.chj9.cms.dao.mapper.DocFileMapper;
import com.chj9.cms.api.entity.DocFileEntity;
import com.chj9.cms.service.DocFileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DocFileServiceImpl implements DocFileService {

	@Autowired
	private DocFileMapper docFileMapper;
	
	@Override
	public DocFileEntity save(DocFileEntity file) {
		docFileMapper.insert(file);
		return file;
	}

	@Override
	public DocFileEntity selectByFileKey(String fileKey) {
		if (StringUtils.isEmpty(fileKey)) {
			return null;
		}
		return docFileMapper.selectByFileKey(fileKey);
	}

	@Override
	public DocFileEntity update(DocFileEntity file) {
		docFileMapper.updateByPrimaryKey(file);
		return file;
	}

	@Override
	public DocFileEntity selectById(Integer id) {
		return docFileMapper.selectByPrimaryKey(id);
	}

}
