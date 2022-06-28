package com.chj9.cms.service;


import com.chj9.cms.api.entity.DocFileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

/**
 * 文件服务接口
 * @author LG
 *
 */
public interface FileService {

	/**
	 * 文件上传 
	 * @param local 本地位置
	 * @param module1
	 * @param module2
	 * @return
	 */
	public String putFile(String local);
	
	/**
	 * 
	 * @param file 文件
	 * @param module1
	 * @param module2
	 * @return
	 */
	public String putFile(File file);
	
	/**
	 * 
	 * @param file 文件
	 * @param module1
	 * @param module2
	 * @return
	 */
	public String putFile(MultipartFile file);
	
	/**
	 * 
	 * @param local 流
	 * @param module1
	 * @param module2
	 * @return
	 */
	public String putFile(InputStream local,String fileName);
	
	/**
	 * 上传网络资源
	 * @param url
	 * @param module1
	 * @param module2
	 * @param fileName
	 * @return
	 */
	public String putFile(String url,String fileName);
	
	/**
	 * 删除文件
	 * @param fileKey
	 */
	public void delFile(String fileKey);
	
	/**
	 * 读取文件
	 * @param fileKey
	 * @return
	 */
	public DocFileEntity getFile(String fileKey);
	
	
}
