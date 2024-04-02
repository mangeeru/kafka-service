package com.micro.kafka.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.micro.kafka.api.dto.UserDto;
import com.micro.kafka.message.producer.MessagePublisher;

@RestController
public class AppController {
	
	@Autowired
	private MessagePublisher publisher;
	
	@GetMapping("/publish/{message}")
	public ResponseEntity<?> publishString(@PathVariable String message) {
		try {
            for (int i = 0; i <= 1000; i++) {
                publisher.pushtoTopic(message+" : "+i);
            }
            return ResponseEntity.ok("message published successfully ..");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
	}
	
	@PostMapping("/publish/user")
	public ResponseEntity<?> publishUser(@RequestBody UserDto userDto){
		try {
			publisher.pushUserToTopic(userDto);
			return ResponseEntity.ok("user published successfully ..");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
		}
	}

}
