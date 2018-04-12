package org.xi.webfluxstudy.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.xi.webfluxstudy.handler.HelloHandler;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class RouterFunctionConfig {

    @Autowired
    HelloHandler helloHandler;

    @Bean
    public RouterFunction<ServerResponse> restaurantRouter() {
        RouterFunction<ServerResponse> router =
                RouterFunctions.route(GET("/hell").and(accept(APPLICATION_JSON)), helloHandler::getPerson);

        return router;
    }
}
