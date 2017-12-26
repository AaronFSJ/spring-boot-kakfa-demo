package com.marty.controller;


import com.marty.entity.User;
import com.marty.util.BeanUtil;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
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
            /*因为spring boot方式启动时用的是默认方式 ，即key和value都是用StringSerializer*/
            kafkaTemplate.send("myTopic", "2323");
            logger.info("myTopic kafka的消息={}");

            logger.info("发送myTopic kafka成功.");
            return "发送myTopic kafka成功";
        } catch (Exception e) {
            logger.error("发送myTopic kafka失败", e);
            return "发送myTopic kafka失败";
        }
    }



}