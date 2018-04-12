package org.xi.webfluxstudy.handler;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.xi.webfluxstudy.model.User;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Service
public class HelloHandler {

    public Mono<ServerResponse> getPerson(ServerRequest request) {
        Optional<String> id = request.queryParam("id");
        User user = new User();
        user.setId(Integer.valueOf(id.get()));
        user.setUsername("rarexixi");
        user.setEmail("rarexixi@outlook.com");
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(BodyInserters.fromObject(user));
    }
}
