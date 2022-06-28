package com.dliberty.cms.service.impl;


import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.dliberty.cms.entity.DocFileEntity;
import com.dliberty.cms.service.DocFileService;
import com.dliberty.cms.service.FileService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Base64Utils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

/**
 * 文件服务
 * @author LG
 *不支持事物
 */
@Service
@Transactional(propagation=Propagation.NOT_SUPPORTED)
public class FileServiceImpl implements FileService {

	private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);
	
	@Autowired
	DocFileService docFileService;
	
	@Value("${oss.endpoint}")
	private String endpoint;
	@Value("${oss.accessKeyId}")
	private String accessKeyId;
	@Value("${oss.accessKeySecret}")
	private String accessKeySecret;
	@Value("${oss.bucketName}")
	private String bucketName;
	
	@Override
	public String putFile(String local) {
		File file = new File(local);
		if (file.exists()) {
			return putFile(file);
		}
		return null;
	}

	@Override
	public String putFile(File file) {
		if (!file.exists()) {
			return null;
		}
		try {
			InputStream input = new FileInputStream(file);
			return putFile(input,file.getName());
		} catch(Exception e) {
			logger.info("文件上传发生异常{}",e.getMessage());
			e.getStackTrace();
		}
		return null;
	}
	
	@Override
	public String putFile(MultipartFile file) {
		try {
			return putFile(file.getInputStream(),file.getOriginalFilename());
		} catch(Exception e) {
			logger.info("文件上传发生异常{}",e.getMessage());
			e.getStackTrace();
		}
		return null;
	}

	@Override
	public String putFile(InputStream local, String fileName) {
		try {
			String key = saveDocFile(fileName);
			// 创建OSSClient实例。
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			// 使用访问OSS。
			ossClient.putObject(bucketName, key, local);
			// 关闭ossClient。
			ossClient.shutdown();
			return key;
		} catch(Exception e) {
			logger.info("文件上传发生异常{}",e.getMessage());
			e.getStackTrace();
		}
		return null;
	}
	
	@Override
	public String putFile(String url, String fileName) {
		try {
			String key = saveDocFile(fileName);
			// 创建OSSClient实例。
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			// 使用访问OSS。
			// 上传网络流。
			InputStream inputStream = new URL(url).openStream();
			ossClient.putObject(bucketName, key, inputStream);
			// 关闭ossClient。
			ossClient.shutdown();
			return key;
		} catch(Exception e) {
			logger.info("文件上传发生异常{},url={}",e.getMessage(),url);
			e.getStackTrace();
		}
		return null;
	}
	
	public String saveDocFile(String fileName) {
		DocFileEntity file = new DocFileEntity();
		file.setFileName(fileName);
		String key = "doc_file" + System.currentTimeMillis();
		String encodeToString = Base64Utils.encodeToString(key.getBytes());
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		//String path = "dliberty/image/" + DateFormatUtils.format(new Date(), "yyyy-MM-dd") + "/"  + module1 + "/" + module2 + "/"+ encodeToString + "." + suffix;
		file.setFileKey(encodeToString+ "." + suffix);
		//file.setFilePath(path);
		file.setFileType(suffix);
		docFileService.save(file);
		return file.getFileKey() ;
	}

	@Override
	public void delFile(String fileKey) {
		try {
			DocFileEntity docFile = docFileService.selectByFileKey(fileKey);
			if (docFile == null) {
				return;
			}
			String objectName = docFile.getFileKey();
			// 创建OSSClient实例。
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			// 删除Object。
			ossClient.deleteObject(bucketName, objectName);
			// 关闭Client。
			ossClient.shutdown();
			docFileService.update(docFile);
		} catch(Exception e) {
			logger.warn("删除oss文件发生异常{}",e.getMessage());
			e.getStackTrace();
		}
		
	}

	@Override
	public DocFileEntity getFile(String fileKey) {
		if (StringUtils.isEmpty(fileKey)) {
			return null;
		}
		try {
			DocFileEntity docFile = docFileService.selectByFileKey(fileKey);
			if (docFile == null) {
				return null;
			}
			String objectName = docFile.getFilePath();
			// 创建OSSClient实例。
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			//ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
			OSSObject ossObject = ossClient.getObject(bucketName, objectName);
			// 读取文件内容。
			InputStream inputStream = ossObject.getObjectContent();
			docFile.setInputStream(inputStream);
			// 关闭Client。
			ossClient.shutdown();
			return docFile;
		} catch(Exception e) {
			logger.warn("oss文件下载发生异常{}",e.getMessage());
			e.getStackTrace();
		}
		
		return null;
	}


}
