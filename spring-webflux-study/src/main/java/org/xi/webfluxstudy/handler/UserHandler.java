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
public class UserHandler {

    public Mono<ServerResponse> get(ServerRequest request) {
        Optional<String> id = request.queryParam("id");
        User user = new User(Integer.valueOf(id.get()), "rarexixi", "rarexixi@outlook.com");
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(BodyInserters.fromObject(user));
    }
}
