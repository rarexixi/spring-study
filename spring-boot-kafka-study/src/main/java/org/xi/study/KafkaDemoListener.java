package org.xi.study;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaDemoListener {

    //声明consumerID为demo，监听topicName为topic.quick.demo的Topic
    @KafkaListener(id = "callback", topics = "callback")
    public void listen(String data) {
        System.out.println("receive : "+ data);
    }
}
