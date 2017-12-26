package com.marty;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class KafkaDemoApplication implements CommandLineRunner{

	public static Logger logger = LoggerFactory.getLogger(KafkaDemoApplication.class);


	@Autowired
	private KafkaTemplate<String, String> template;

	private final CountDownLatch latch = new CountDownLatch(3);

	/*
	 * @Author: Aaron
	 * @Description:
	 * @Date: 2017/12/26 14:46
	 * @Param:
	 */
	@Override
	public void run(String... args) throws Exception {
		this.template.send("myTopic", "foo1");
		latch.await(1, TimeUnit.SECONDS);
		logger.info("All received");
	}



	public static void main(String[] args) {
		SpringApplication.run(KafkaDemoApplication.class, args);
	}

}
