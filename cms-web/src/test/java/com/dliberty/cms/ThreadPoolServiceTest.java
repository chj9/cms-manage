package com.dliberty.cms;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dliberty.cms.service.ThreadPoolService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringBootStartApplication.class)
public class ThreadPoolServiceTest {
	 
	@Autowired
	private ThreadPoolService threadPoolService;
	
	@Test
	public void thread1Test() {
		for (int i = 0; i < 10; i++) {
			threadPoolService.thread1(i);
		}
	}
	
	@Test
	public void thread2Test() {
		List<Future<Integer>> list = new ArrayList<>();
		long time1 = System.currentTimeMillis();
		for (int i = 0; i < 10; i++) {
			Future<Integer> thread2 = threadPoolService.thread2(i);
			list.add(thread2);
		}
		long time2 = System.currentTimeMillis();
		System.out.println("时间1="+(time2-time1));
		for (int i = 0; i < list.size(); i++) {
			try {
				System.out.println(list.get(i).get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		long time3 = System.currentTimeMillis();
		System.out.println("时间2="+(time3-time2));
	}

}
