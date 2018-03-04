package org.codemental.samples.webflux.java.http.adapter.main;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import org.codemental.samples.webflux.java.http.adapter.controller.InboundApiController;

import java.util.concurrent.TimeUnit;

public class HttpConfiguration {

    @Value("${external.api.url}")
    private String externalApiUrl;

    @Value("${external.channel.connection.timeout}")
    private Integer externalChannelConnectionTimeout;

    @Value("${external.channel.request.timeout}")
    private Integer externalChannelRequestTimeout;

    @Bean
    public InboundApiController inboundApiController() {
        return new InboundApiController();
    }

    @Bean
    public WebClient externalApiClient() {
        return WebClient.builder()
                .baseUrl(externalApiUrl)
                .clientConnector(createConnector())
                .build();
    }

    private ReactorClientHttpConnector createConnector() {
        return new ReactorClientHttpConnector(
                options -> options.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, externalChannelConnectionTimeout)
                        .compression(true)
                        .afterNettyContextInit(ctx -> {
                            ctx.addHandlerLast(new ReadTimeoutHandler(externalChannelRequestTimeout, TimeUnit.MILLISECONDS));
                        }));
    }
}
