package org.xi.security.authorization;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class JwtApplicationTest {

    final static Key key = Keys.hmacShaKeyFor(Decoders.BASE64.decode("sdfdhuaiojdewqbfhioncd0wqkpoiru92384321421gyidhsbkfnsjafdshbidsoid9024u120"));

    @Test
    public void testCreateAndParseToken() {
        String token = Jwts.builder()
                .setId("8888")
                .setSubject("Rose")
                .setIssuedAt(new Date())
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        System.out.println(token);

        System.out.println("+=========================+");
        String[] splits = token.split("\\.");
        System.out.println(decodeToString(splits[0]));
        System.out.println(decodeToString(splits[1]));

        System.out.println("+=========================+");

        Claims xxxx = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        System.out.println(xxxx.getId());
        System.out.println(xxxx.getSubject());
        System.out.println(xxxx.getIssuedAt());
    }


    @Test
    public void testCreateAndParseTokenExpire() {
        long now = System.currentTimeMillis();

        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("8888")
                .setSubject("Rose")
                .setIssuedAt(new Date())
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(new Date(now + 1000)); // 设置1s后过期
        String token = jwtBuilder.compact();
        System.out.println(token);

        System.out.println("+=========================+");
        String[] splits = token.split("\\.");
        System.out.println(decodeToString(splits[0]));
        System.out.println(decodeToString(splits[1]));

        {
            System.out.println("+=========================+");

            Claims xxxx = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            System.out.println(xxxx.getId());
            System.out.println(xxxx.getSubject());
            System.out.println(xxxx.getIssuedAt());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("签发时间：" + simpleDateFormat.format(xxxx.getIssuedAt()));
            System.out.println("过期时间：" + simpleDateFormat.format(xxxx.getExpiration()));
            System.out.println("当前时间：" + simpleDateFormat.format(new Date()));
        }

        try {
            Thread.sleep(1000);
            System.out.println("+=========================+");
            Claims xxxx = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            System.out.println(xxxx.getId());
            System.out.println(xxxx.getSubject());
            System.out.println(xxxx.getIssuedAt());

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("签发时间：" + simpleDateFormat.format(xxxx.getIssuedAt()));
            System.out.println("过期时间：" + simpleDateFormat.format(xxxx.getExpiration()));
            System.out.println("当前时间：" + simpleDateFormat.format(new Date()));
        } catch (ExpiredJwtException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testCreateAndParseTokenClaims() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", "xishihao");
        claims.put("address", "linfen");
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("8888")
                .setSubject("Rose")
                .setIssuedAt(new Date())
                .signWith(key, SignatureAlgorithm.HS256)
                .claim("roles", "admin") // 两种方式
                .addClaims(claims);
        String token = jwtBuilder.compact();
        System.out.println(token);

        System.out.println("+=========================+");
        String[] splits = token.split("\\.");
        System.out.println(decodeToString(splits[0]));
        System.out.println(decodeToString(splits[1]));

        System.out.println("+=========================+");
        Claims xxxx = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        System.out.println(xxxx.getId());
        System.out.println(xxxx.getSubject());
        System.out.println(xxxx.getIssuedAt());
        xxxx.forEach((key, value) -> System.out.println(key + ":" + value));
    }

    private String decodeToString(String tokenPart) {
        return new String(Decoders.BASE64.decode(tokenPart), StandardCharsets.UTF_8);
    }
}
