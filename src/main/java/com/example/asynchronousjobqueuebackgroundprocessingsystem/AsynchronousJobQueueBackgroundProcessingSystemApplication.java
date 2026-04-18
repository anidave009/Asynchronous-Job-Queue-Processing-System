package com.example.asynchronousjobqueuebackgroundprocessingsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class AsynchronousJobQueueBackgroundProcessingSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsynchronousJobQueueBackgroundProcessingSystemApplication.class, args);
	}

}
