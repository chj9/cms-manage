package com.dliberty.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dliberty.cms.service.FileService;

@RestController
@RequestMapping("/file")
public class FileController {

	//private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	FileService fileService;
	



}
