package com.sparta.msa_exam.order.global.config;

import java.time.Duration;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisCacheConfig {

	@Bean
	// CacheManager로 진행해도 정상 동작
	public RedisCacheManager cacheManager(
		RedisConnectionFactory redisConnectionFactory
	) {
		// 설정 구성을 먼저 진행한다.
		// Redis를 이용해서 Spring Cache를 사용할 때
		// Redis 관련 설정을 모아두는 클래스
		RedisCacheConfiguration configuration = RedisCacheConfiguration
			.defaultCacheConfig()
			// null을 캐싱 할것인지
			.disableCachingNullValues()
			// 기본 캐시 유지 시간 (Time To Live)
			.entryTtl(Duration.ofSeconds(60))
			// 캐시를 구분하는 접두사 설정
			.computePrefixWith(CacheKeyPrefix.simple())
			// 캐시에 저장할 값을 어떻게 직렬화 / 역직렬화 할것인지
			.serializeKeysWith(
				RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())
			)
			.serializeValuesWith(
				RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())
			);

		return RedisCacheManager
			.builder(redisConnectionFactory)
			.cacheDefaults(configuration)
			.build();
	}
}