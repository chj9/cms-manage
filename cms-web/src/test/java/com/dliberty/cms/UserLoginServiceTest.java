package com.dliberty.cms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dliberty.cms.service.UserLoginService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootStartApplication.class)
public class UserLoginServiceTest {

	@Autowired
	private UserLoginService userLoginService;
	
	@Test
	public void loginTest() {
		userLoginService.login("o8FtK5GJ8ldqgSrv8wMPKli8mv5A", "o8FtK5GJ8ldqgSrv8wMPKli8mv5A");
	}
	
	@Test
	public void registerTest() {
		userLoginService.register("o8FtK5GJ8ldqgSrv8wMPKli8mv5A", "o8FtK5GJ8ldqgSrv8wMPKli8mv5A");
	}
}
