package org.xi.study.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class HomeController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @PostMapping(value = "/callback/{event}")
    public String callback(@PathVariable("event") String event, @RequestBody String data) throws Exception {

        JSONObject json = new JSONObject();
        json.put("business", "huanxin");
        json.put("event", event);
        json.put("data", data);

        ListenableFuture<SendResult> listenableFuture = kafkaTemplate.send("callback", json.toJSONString());
        listenableFuture.addCallback(result -> System.out.println("环信数据接收成功"), result -> System.out.println("环信数据接收失败"));
        return "";
    }
}
