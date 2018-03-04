package org.codemental.samples.webflux.java.http.adapter.main;

import org.codemental.samples.webflux.java.http.adapter.errorhandling.BackendConnectionExceptionTranslator;
import org.springframework.context.annotation.Bean;

public class ErrorHandlingConfiguration {

    @Bean
    public BackendConnectionExceptionTranslator backendConnectionExceptionTranslator() {
        return new BackendConnectionExceptionTranslator();
    }
}
