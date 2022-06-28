package com.chj9.cms.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chj9.cms.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dliberty.cms.exception.CommonException;
import com.dliberty.cms.vo.JsonBean;

@RestController
@RequestMapping("/admin/file")
public class FileController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    FileService fileService;
	
	@PostMapping("/upload")
	public JsonBean upload(HttpServletRequest request,HttpServletResponse response) {
		
		logger.info("上传文件");
		MultipartHttpServletRequest req = (MultipartHttpServletRequest) request;
		MultipartFile multipartFile = req.getFile("file");
		if (multipartFile == null) {
			throw new CommonException("请选择需要上传的图片");
		}
		JsonBean bean = new JsonBean();
		
		String fileId = fileService.putFile(multipartFile);
		bean.put("fileId", fileId);
		return bean;
	}


}
