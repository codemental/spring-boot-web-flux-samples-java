package org.codemental.samples.webflux.java.jooq.notx.errorhandling;

import org.springframework.context.annotation.Bean;

public class ErrorHandlingConfiguration {

    /**
     * The order of these ExceptionTranslators here matters !!
     */
    @Bean
    public BadRequestExceptionTranslator httpExceptionTranslator() {
        return new BadRequestExceptionTranslator();
    }

    @Bean
    public PersistenceExceptionTranslator persistenceExceptionTranslator() {
        return new PersistenceExceptionTranslator();
    }

    @Bean
    public InternalServerErrorExceptionTranslator serviceExceptionTranslator() {
        return new InternalServerErrorExceptionTranslator();
    }
}
