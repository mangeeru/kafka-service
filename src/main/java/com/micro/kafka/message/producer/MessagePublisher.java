package com.micro.kafka.message.producer;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.micro.kafka.api.dto.UserDto;
import com.micro.kafka.constant.KafkaProperties;

@Service
public class MessagePublisher {

	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;

	public void pushtoTopic(String message) {
		CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(KafkaProperties.KAFKA_TOPIC, message);
		future.whenComplete((result, ex) -> {
			if (ex == null) {
				System.out.println(
						"Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			} else {
				System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
			}
		});
	}

	public void pushUserToTopic(UserDto userDto) {
		try {
			CompletableFuture<SendResult<String, Object>> future = kafkaTemplate.send(KafkaProperties.KAFKA_TOPIC,
					userDto);
			future.whenComplete((result, ex) -> {
				if (ex == null) {
					System.out.println("Sent message=[" + userDto.toString() + "] with offset=["
							+ result.getRecordMetadata().offset() + "]");
				} else {
					System.out
							.println("Unable to send message=[" + userDto.toString() + "] due to : " + ex.getMessage());
				}
			});

		} catch (Exception ex) {
			System.out.println("ERROR : " + ex.getMessage());
		}
	}

}
