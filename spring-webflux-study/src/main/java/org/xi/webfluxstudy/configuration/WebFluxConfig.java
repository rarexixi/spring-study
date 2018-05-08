package org.xi.webfluxstudy.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFlux
@ComponentScan(basePackages = {"org.xi.webfluxstudy.controller", "org.xi.webfluxstudy.handler"})
public class WebFluxConfig {
}
