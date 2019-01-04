/**  
 * TODO(用一句话描述该文件做什么)
 * @company Finedo.cn
 * @author zhangkuo
 * @date 2019年1月2日 下午4:18:30
 * @version V1.0  
 */
package com.advance.ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * @author dell
 * 
 */
public class ThreadTest implements Runnable {

	private Integer index;
	ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

	public ThreadTest(Integer index) {
		this.index = index;
	}

	@Override
	public void run() {

		executor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				for (int i = 0;i<3;i++)
				{
					printMsg();
				}
			}

		}, 0, 1, TimeUnit.SECONDS);
	}

	public void printMsg() {
		Thread t = Thread.currentThread();
		String name = t.getName();
		System.out.println(name);
	}
}
