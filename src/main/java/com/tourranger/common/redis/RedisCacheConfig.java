package com.tourranger.common.redis;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.BasicPolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
@EnableCaching
public class RedisCacheConfig {
	@Bean
	public RedisCacheManager redisCacheManager(RedisConnectionFactory cacheConnectionFactory) {
		GenericJackson2JsonRedisSerializer redisSerializer = getGenericJackson2JsonRedisSerializer();

		RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
			.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
			.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
			.entryTtl(Duration.ofMinutes(10)); // 캐시 지속 시간 설정

		return RedisCacheManager
			.RedisCacheManagerBuilder
			.fromConnectionFactory(cacheConnectionFactory)
			.cacheDefaults(config)
			.build();
	}

	private GenericJackson2JsonRedisSerializer getGenericJackson2JsonRedisSerializer() {
		PolymorphicTypeValidator typeValidator = BasicPolymorphicTypeValidator
			.builder()
			.allowIfSubType(Object.class)
			.build();

		// ObjectMapper 를 사용해서 LocalTimeDate를 직렬화 하기 위해 매핑
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.activateDefaultTyping(typeValidator, ObjectMapper.DefaultTyping.NON_FINAL);

		GenericJackson2JsonRedisSerializer redisSerializer = new GenericJackson2JsonRedisSerializer(objectMapper);

		return redisSerializer;
	}
}
