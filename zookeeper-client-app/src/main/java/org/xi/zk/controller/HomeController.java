package org.xi.zk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.xi.zk.balanse.LoadBalanse;

import java.util.Map;

@RestController
public class HomeController {

    private RestTemplate restTemplate = new RestTemplate();

    private LoadBalanse loadBalanse = new LoadBalanse();

    @GetMapping("/hello")
    public Object hello(Integer id) {
        String host = loadBalanse.choseServiceHost();
        Map res = restTemplate.getForObject("http://" + host + "/hello/" + id, Map.class);
        res.put("host", host);
        return res;
    }
}