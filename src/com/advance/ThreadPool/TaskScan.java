/**  
* TODO(用一句话描述该文件做什么)
* @company Finedo.cn
* @author zhangkuo
* @date 2019年1月2日 下午4:17:49
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
public class TaskScan {
	public static void main(String[] args) {
		ScheduledExecutorService  executor = Executors.newScheduledThreadPool(3);
	        executor.schedule(new ThreadTest(1),2, TimeUnit.SECONDS);
	}
}
