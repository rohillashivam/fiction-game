package com.test.fiction.fictiongame.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.convert.MappingRedisConverter;
import org.springframework.data.redis.core.convert.RedisCustomConversions;
import org.springframework.data.redis.core.convert.ReferenceResolver;
import org.springframework.data.redis.core.mapping.RedisMappingContext;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import com.test.fiction.fictiongame.entity.cache.PlayerData;
import com.test.fiction.fictiongame.entity.cache.converter.PlayerDataReadConverter;
import com.test.fiction.fictiongame.entity.cache.converter.PlayerDataWriteConverter;

@Configuration
@ComponentScan("com.junglee.fiction.fictiongame.cache*")
@EnableRedisRepositories(basePackages = "com.junglee.fiction.fictiongame.repositories.cache")
@PropertySource({ "classpath:application.properties", "classpath:persistence.properties" })
public class RedisConfig {

	/*
	 * @Value("${spring.redis.port}") private Long portNum;
	 * 
	 * @Value("${spring.redis.host}") private String host;
	 * 
	 * @Bean(destroyMethod = "shutdown") ClientResources clientResources() { return
	 * DefaultClientResources.create(); }
	 * 
	 * @Bean(destroyMethod = "shutdown") RedisClusterClient
	 * redisClusterClient(ClientResources clientResources) {
	 * 
	 * RedisURI redisURI = RedisURI.Builder.redis(host, portNum.intValue()).build();
	 * //RedisURI redisURI = RedisURI.create(host, portNum.intValue());
	 * 
	 * return RedisClusterClient.create(clientResources, redisURI); }
	 * 
	 * @Bean(destroyMethod = "close") StatefulRedisClusterConnection<String, String>
	 * clusterConnection(RedisClusterClient clusterClient) { return
	 * clusterClient.connect(); }
	 */

	@Bean
	public RedisConnectionFactory redisConnectionFactory() {
		LettuceConnectionFactory connectionFactory = new LettuceConnectionFactory();
		return connectionFactory;
	}

	@Bean
	public RedisTemplate<String, PlayerData> redisTemplate() {
		RedisTemplate<String, PlayerData> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(redisConnectionFactory());
		return redisTemplate;
	}
	
	/*@Bean
    public RedisCustomConversions redisCustomConversions(OffsetDateTimeToBytesConverter offsetToBytes,
                                                         BytesToOffsetDateTimeConverter bytesToOffset) {
        return new RedisCustomConversions(Arrays.asList(offsetToBytes, bytesToOffset));
    }*/
	
	
	
	@Bean
    public RedisCustomConversions redisCustomConversions() {
        return new RedisCustomConversions(Arrays.asList(new PlayerDataReadConverter(), new PlayerDataWriteConverter()));
    }

}
