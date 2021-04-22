package org.xi.security.authorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

@SpringBootApplication
@EnableOAuth2Sso
public class AuthorizationClient01Application {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationClient01Application.class, args);
    }

}
