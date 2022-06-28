package com.chj9.cms.service;

import java.util.concurrent.Future;

/**
 * 线程池测试
 * @author LG
 *
 */
public interface ThreadPoolService {

	/**
	 * 无返回值
	 * @param a
	 */
	public void thread1(int a);
	
	/**
	 * 有返回值
	 * @param a
	 * @return
	 */
	public Future<Integer> thread2(int a);
}
