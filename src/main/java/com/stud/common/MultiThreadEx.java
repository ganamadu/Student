package com.stud.common;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MultiThreadEx {

	private MultiThreadEx() {
	}

	class CustomThreadPool extends ThreadPoolExecutor {

		public CustomThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
				BlockingQueue<Runnable> workQueue) {
			super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
		}
	}

	private ExecutorService executorService = new CustomThreadPool(600, 1200, 7, TimeUnit.DAYS,
			new ArrayBlockingQueue<Runnable>(10));

	public Future<Object> getFutureReference(Callable<Object> c) {
		return executorService.submit(c);
	}

	public static MultiThreadEx instance = new MultiThreadEx();

	public static MultiThreadEx getInstance() {
		return instance;
	}

}
