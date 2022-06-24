package com.dliberty.cms.impl;


import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dliberty.cms.dao.entity.DocFile;
import com.dliberty.cms.dao.mapper.DocFileMapper;
import com.dliberty.cms.lang.data.StringUtils;
import com.dliberty.cms.service.DocFileService;

@Service
@Transactional
public class DocFileServiceImpl implements DocFileService {

	@Autowired
	private DocFileMapper docFileMapper;
	
	@Override
	public DocFile save(DocFile file) {
		file.setCreateTime(new Date());
		file.setUpdateTime(new Date());
		file.setIsDeleted("0");
		docFileMapper.insert(file);
		return file;
	}

	@Override
	public DocFile selectByFileKey(String fileKey) {
		if (StringUtils.isEmpty(fileKey)) {
			return null;
		}
		return docFileMapper.selectByFileKey(fileKey);
	}

	@Override
	public DocFile update(DocFile file) {
		file.setUpdateTime(new Date());
		docFileMapper.updateByPrimaryKey(file);
		return file;
	}

	@Override
	public DocFile selectById(Integer id) {
		return docFileMapper.selectByPrimaryKey(id);
	}

}
