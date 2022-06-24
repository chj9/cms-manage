package com.dliberty.cms.service;


import com.dliberty.cms.dao.entity.DocFile;

/**
 * 文件
 * @author LG
 *
 */
public interface DocFileService {

	/**
	 * 保存文件
	 * @param file
	 * @return
	 */
	public DocFile save(DocFile file);
	
	/**
	 * 根据fileKey查询文件
	 * @param fileKey
	 * @return
	 */
	public DocFile selectByFileKey(String fileKey);
	
	/**
	 * 修改
	 * @param file
	 * @return
	 */
	public DocFile update(DocFile file);
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public DocFile selectById(Integer id);
}
