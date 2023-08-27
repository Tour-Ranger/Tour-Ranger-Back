package com.tourranger.common.redis;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RedisUtils {
	private final RedisTemplate<String, Object> redisTemplate;
	private final ModelMapper modelMapper;

	// key값으로 value를 저장한다
	// 만약 만료 시간을 지정하는 경우엔 time 과 단위를 함께 세팅한다
	public void put(String key, Object value, Long expirationTime) {
		if (expirationTime != null) {
			redisTemplate.setKeySerializer(new StringRedisSerializer());
			redisTemplate.setValueSerializer(new StringRedisSerializer());
			redisTemplate.opsForValue().set(key, value, expirationTime, TimeUnit.SECONDS);
		} else {
			redisTemplate.opsForValue().set(key, value);
		}
	}

	public void put(String key, Object field, Object value, Long expirationTime) {
		String token = field.toString();
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new StringRedisSerializer());
		redisTemplate.opsForHash().put(key, token, value);

		if (expirationTime != null) {
			Duration timeout = Duration.ofSeconds(expirationTime);
			redisTemplate.setHashKeySerializer(new StringRedisSerializer());
			redisTemplate.expire(token, timeout);
		}
	}

	// key값에 대한 데이터를 삭제한다
	public void delete(String key) {
		redisTemplate.delete(key);
	}

	public void delete(String key, Object hashKey) {
		String token = hashKey.toString();
		redisTemplate.opsForHash().delete(key, token);
	}

	public <T> T get(String key, Class<T> tClass) {
		// key 값으로 value 를 가져온다
		Object object = redisTemplate.opsForValue().get(key);

		if (object != null) {
			if (object instanceof List<?>) {
				return modelMapper.map(object, tClass);
			} else {
				return tClass.cast(object);
			}
		}
		return null;
	}

	public <T> T get(String key, Object hashKey, Class<T> tClass) {
		// key 값으로 value 를 가져온다clear
		String token = hashKey.toString();
		Object object = redisTemplate.opsForHash().get(key, token);

		if (object != null) {
			if (object instanceof List<?>) {
				return modelMapper.map(object, tClass);
			} else {
				return tClass.cast(object);
			}
		}
		return null;
	}

	public boolean isExists(String key) {
		return redisTemplate.hasKey(key);
	}

	public void setExpireTime(String key, Long expirationTime) {
		redisTemplate.expire(key, expirationTime, TimeUnit.SECONDS);
	}

	public Long getExpireTime(String key) {
		return redisTemplate.getExpire(key, TimeUnit.SECONDS);
	}
}
