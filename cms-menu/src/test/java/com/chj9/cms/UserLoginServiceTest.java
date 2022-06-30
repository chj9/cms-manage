package com.chj9.cms;

import com.chj9.cms.service.impl.UserLoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MenuApplication.class)
public class UserLoginServiceTest {

	@Autowired
	private UserLoginService myUserService;
	
	@Test
	public void loginTest() {
		//myUserService.login("o8FtK5GJ8ldqgSrv8wMPKli8mv5A", "o8FtK5GJ8ldqgSrv8wMPKli8mv5A");
	}
	
	//@Test
	//public void registerTest() {
	//	myUserService.register("o8FtK5GJ8ldqgSrv8wMPKli8mv5A", "o8FtK5GJ8ldqgSrv8wMPKli8mv5A");
	//}
}
