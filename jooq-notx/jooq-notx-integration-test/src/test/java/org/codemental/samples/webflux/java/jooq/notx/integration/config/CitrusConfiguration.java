package org.codemental.samples.webflux.java.jooq.notx.integration.config;

import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import com.consol.citrus.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;


@PropertySource(value = "citrus.properties")
@Import({DatabaseConfiguration.class})
public class CitrusConfiguration {

    @Value("${jooq.notx.service.url}")
    private String serviceUrl;

    @Bean
    public HttpClient httpClient() {
        return CitrusEndpoints.http()
                .client()
                .requestUrl("http://" + serviceUrl)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .build();
    }
}
