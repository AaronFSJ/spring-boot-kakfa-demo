package com.marty.controller;


import com.marty.entity.User;
import com.marty.util.BeanUtil;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;


@RestController
@RequestMapping("/kafka")
public class CollectController {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private KafkaTemplate kafkaTemplate;



    /**
     * spring boot方式启动的
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String send(HttpServletRequest request, HttpServletResponse response){
        try {
            /**
             * spring-kafka,可以实现producer的消息均发，但是如果producer中没有定义分区策略，也就是说程序采用默认的kafka.producer.DefaultPartitioner
             * 其核心思想就是对每个消息的key的hash值对partition数取模得到，所以如果key没有传入，则key为null,代码每次的hash值都一样，因此只发到了一个分区上，
             * 所以如果想要分送到不同的分区上，可以自己实现Partitioner，也可以指定每条消息发送的key,key值要根据实际情况发生变化，虽然不是均发，
             * 但是至少能保证不是每个消息都发送到同一个分区上
             */
            kafkaTemplate.send("replicated-topic","key5","2323");
            logger.info("myTopic kafka的消息={}");

            logger.info("发送myTopic kafka成功.");
            return "发送myTopic kafka成功";
        } catch (Exception e) {
            logger.error("发送myTopic kafka失败", e);
            return "发送myTopic kafka失败";
        }
    }



}