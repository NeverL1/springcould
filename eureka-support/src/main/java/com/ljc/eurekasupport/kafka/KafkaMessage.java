package com.ljc.eurekasupport.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: bank_seckill
 * @description: 秒杀消息
 * @author: LJC
 * @create: 2022-04-12 15:13
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class KafkaMessage {

    private Long id;

    private String userName;

}
