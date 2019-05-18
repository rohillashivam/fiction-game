package com.test.fiction.fictiongame.entity.cache.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.test.fiction.fictiongame.entity.cache.PlayerData;

@ReadingConverter
public class PlayerDataReadConverter implements Converter<byte[], PlayerData> {

	private final Jackson2JsonRedisSerializer<PlayerData> serializer;

	public PlayerDataReadConverter() {
		serializer = new Jackson2JsonRedisSerializer<PlayerData>(PlayerData.class);
		serializer.setObjectMapper(new ObjectMapper());
	}

	@Override
	public PlayerData convert(byte[] value) {
		// TODO Auto-generated method stub
		return serializer.deserialize(value);
	}

}