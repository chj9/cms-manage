package com.chj9.cms.menu.controller;

import com.chj9.cms.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/file")
public class FileController {

	//private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    FileService fileService;
	



}
