package com.micro.kafka.message.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.micro.kafka.api.dto.UserDto;
import com.micro.kafka.constant.KafkaProperties;

@Service
public class MessageConsumer {
	
	@KafkaListener(topics = KafkaProperties.KAFKA_TOPIC, groupId = KafkaProperties.KAFKA_GROUP_ID)
	public void readData(UserDto userDto) {
		
		System.out.println("Data from Kafka consumer:"+userDto.toString());
		
	}

}
