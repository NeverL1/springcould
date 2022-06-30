package com.ljc.eurekasupport.kafka;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


/**
 * @program: bank_seckill
 * @description: kafka消费者
 * @author: LJC
 * @create: 2022-04-11 20:57
 **/
@Component
@Slf4j
public class KafkaConsumer {



    //kafka的监听器，topic为"topic1"，消费者组为"springcould"
    @KafkaListener(topics = "topic1", groupId = "springcould")
    public void ConsumerMessage(ConsumerRecord<String, String> record) {

    }
}
