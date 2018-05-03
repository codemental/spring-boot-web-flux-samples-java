package org.codemental.samples.webflux.java.jooq.adapter.integration.config;

import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.http.server.HttpServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;


@PropertySource(value = "citrus.properties")
@Import({})// you can import more configuration classes here
public class CitrusConfiguration {

    @Value("${http.adapter.service.url}")
    private String serviceUrl;
    @Value("${external.api.mock.port}")
    private int externalApiMockPort;

    @Bean
    public HttpClient inboundClient() {
        return CitrusEndpoints.http()
                .client()
                .requestUrl("http://" + serviceUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .build();
    }

    @Bean
    public HttpServer externalApiMock() {
        return CitrusEndpoints.http()
                .server()
                .port(externalApiMockPort)
                .autoStart(true)
                .timeout(10000)
                .build();
    }
}
