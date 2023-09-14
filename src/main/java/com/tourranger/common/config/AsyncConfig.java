package com.tourranger.common.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.Value;

@Configuration
public class AsyncConfig {
	private int CORE_POOL_SIZE = Integer.parseInt(System.getenv("CORE_POOL_SIZE"));
	private int MAX_POOL_SIZE = Integer.parseInt(System.getenv("MAX_POOL_SIZE"));
	private int QUEUE_CAPACITY = Integer.parseInt(System.getenv("QUEUE_CAPACITY"));
	private String THREAD_NAME_PREFIX = System.getenv("THREAD_NAME_PREFIX");

	@Bean(name = "taskExecutor")
	public Executor taskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(CORE_POOL_SIZE); // 스레드 풀의 핵심(core) 스레드 수
		executor.setMaxPoolSize(MAX_POOL_SIZE); // 스레드 풀의 최대 스레드 수
		executor.setQueueCapacity(QUEUE_CAPACITY); // 대기 큐의 최대 용량
		executor.setThreadNamePrefix(THREAD_NAME_PREFIX); // 스레드 이름 접두사
		executor.initialize(); // 스레드 풀을 초기화
		return executor;
	}
}