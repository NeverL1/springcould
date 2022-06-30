package com.ljc.eurekasupport.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @program: bank_seckill
 * @description: kafka消费者
 * @author: LJC
 * @create: 2022-04-11 20:55
 **/
@Component
@Controller
@Slf4j
public class KafkaProducer {

    private final static String TOPIC_NAME = "topic1"; //topic的名称

    @Resource
    private KafkaTemplate<String,String> kafkaTemplate;

    @RequestMapping("/send")
    public void sendMessage(String msg){
        ListenableFuture<SendResult<String, String>> seckill = kafkaTemplate.send(TOPIC_NAME, "partition1", msg);
        seckill.addCallback(
                success -> log.info("消息发送成功：{}",success.toString()),throwable -> log.info("消息发送失败,{}" + throwable.getMessage())
        );
    }

}
