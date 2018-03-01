package org.codemental.samples.webflux.java.http.adapter.main;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import org.codemental.samples.webflux.java.http.adapter.controller.InboundApiController;

public class HttpConfiguration {

    @Value("${external.api.url}")
    private String externalApiUrl;

    @Bean
    public InboundApiController inboundApiController() {
        return new InboundApiController();
    }

    @Bean
    public WebClient externalApiClient() {
        return WebClient.create(externalApiUrl);
    }
}
