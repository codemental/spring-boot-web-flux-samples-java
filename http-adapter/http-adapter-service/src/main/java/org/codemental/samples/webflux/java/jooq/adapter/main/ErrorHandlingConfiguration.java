package org.codemental.samples.webflux.java.jooq.adapter.main;

import org.codemental.samples.webflux.java.jooq.adapter.errorhandling.BackendConnectionExceptionTranslator;
import org.springframework.context.annotation.Bean;

public class ErrorHandlingConfiguration {

    @Bean
    public BackendConnectionExceptionTranslator backendConnectionExceptionTranslator() {
        return new BackendConnectionExceptionTranslator();
    }
}
