package com.marty.listener;

import com.marty.entity.User;
import com.marty.util.BeanUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

/**
 * @Author: Aaron
 * @Descprition:
 * @Date: Create in 2017/12/20 09:44
 * @Modyfied By:
 */
@Configuration
public class Listener {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());


    @KafkaListener(topics = "myTopic")
    public void listen(ConsumerRecord<?, ?> record){
        logger.info("==============================");
        logger.info(record.toString());
        logger.info("myTopic kafka的topic：{}",record.topic());
        logger.info("myTopic kafka的key：{}",record.key());
        logger.info("myTopic kafka的value：{}",record.value());
    }
}
