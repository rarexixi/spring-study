package org.xi.ehcache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.xi.ehcache.sys.service.UserService;

@SpringBootApplication
@EnableCaching
public class RedisApplication implements CommandLineRunner {
    public static void main(String[] args) {
        SpringApplication.run(RedisApplication.class, args);
    }

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(userService.detail(1));
        System.out.println(userService.detail(1));
        userService.delete(1);
        System.out.println(userService.detail(1));
        System.out.println(userService.detail(2));
        System.out.println(userService.detail(3));
        userService.cleanCache();
        System.out.println(userService.add(1));
        System.out.println(userService.add(1));
        userService.cleanCache();
//        String key = "xi";￿
//        double min = 1, max = 10;
//        long offset = 0, limit = 10;
//        Set<ZSetOperations.TypedTuple<String>> result = stringRedisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max, offset, limit);
//        result.forEach(item -> System.out.println(item.getValue()));
    }
}
