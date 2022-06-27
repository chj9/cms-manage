package com.dliberty.cms.service;


import com.dliberty.cms.entity.DocFileEntity;

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
	public DocFileEntity save(DocFileEntity file);
	
	/**
	 * 根据fileKey查询文件
	 * @param fileKey
	 * @return
	 */
	public DocFileEntity selectByFileKey(String fileKey);
	
	/**
	 * 修改
	 * @param file
	 * @return
	 */
	public DocFileEntity update(DocFileEntity file);
	
	/**
	 * 根据id查找
	 * @param id
	 * @return
	 */
	public DocFileEntity selectById(Integer id);
}
