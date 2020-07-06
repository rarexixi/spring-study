package org.xi.ehcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.xi.ehcache.sys.service.UserService;

@Component
public class TestCommand implements CommandLineRunner {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(userService.detail(1));
        System.out.println(userService.detail(1));
        System.out.println(userService.detail(1));
//        String key = "xi";￿
//        double min = 1, max = 10;
//        long offset = 0, limit = 10;
//        Set<ZSetOperations.TypedTuple<String>> result = stringRedisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max, offset, limit);
//        result.forEach(item -> System.out.println(item.getValue()));
    }
}
