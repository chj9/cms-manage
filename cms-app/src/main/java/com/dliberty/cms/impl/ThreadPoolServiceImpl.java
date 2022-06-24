package com.dliberty.cms.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.dliberty.cms.service.ThreadPoolService;

@Service
public class ThreadPoolServiceImpl implements ThreadPoolService {

	@Autowired
	ThreadPoolTaskExecutor asyncServiceExecutor;
	
	@Override
	@Async("asyncServiceExecutor")
	public void thread1(int a) {
		asyncServiceExecutor.execute(new Runnable() {
			@Override
			public void run() {
				System.out.println(a);
			}
		});
	}

	@Override
	public Future<Integer> thread2(int a) {
		return asyncServiceExecutor.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				return a;
			}
		});
		
		
	}

}
