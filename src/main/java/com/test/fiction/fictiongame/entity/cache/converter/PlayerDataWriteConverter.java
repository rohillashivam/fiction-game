package com.test.fiction.fictiongame.entity.cache.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.test.fiction.fictiongame.entity.cache.PlayerData;

@WritingConverter
public class PlayerDataWriteConverter implements Converter<PlayerData, byte[]> {

	private final Jackson2JsonRedisSerializer<PlayerData> serializer;

	public PlayerDataWriteConverter() {
		serializer = new Jackson2JsonRedisSerializer<PlayerData>(PlayerData.class);
		serializer.setObjectMapper(new ObjectMapper());
	}

	@Override
	public byte[] convert(PlayerData value) {
		return serializer.serialize(value);
	}

}
