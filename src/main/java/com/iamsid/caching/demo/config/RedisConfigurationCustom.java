package com.iamsid.caching.demo.config;

import com.iamsid.caching.demo.model.CustomCacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;


@Configuration
@EnableCaching
public class RedisConfigurationCustom {

    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.json()))
                //Configure in Property file as per use case, hardcoded just for demo
                .entryTtl(Duration.ofSeconds(600));
    }

    @Bean
    public RedisCacheWriter redisCacheWriter() {

        return RedisCacheWriter.lockingRedisCacheWriter(redisConnectionFactory());

    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {

        return new LettuceConnectionFactory();
    }

    @Bean
    public RedisCacheManager redisCacheManager() {
        return new CustomCacheManager(redisCacheWriter(), redisCacheConfiguration());
    }
}
