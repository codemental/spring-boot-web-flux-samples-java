package org.codemental.samples.webflux.java.http.adapter.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableAutoConfiguration
@EnableWebFlux
@Profile({"default"})
@Import({ErrorHandlingConfiguration.class, HttpConfiguration.class})
public class HttpAdapterApplication {

    public static void main(String[] args) {
        SpringApplication.run(HttpAdapterApplication.class, args);
    }
}
