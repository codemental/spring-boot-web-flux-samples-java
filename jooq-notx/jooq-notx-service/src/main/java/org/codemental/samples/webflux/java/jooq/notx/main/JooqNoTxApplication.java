package org.codemental.samples.webflux.java.jooq.notx.main;

import org.codemental.samples.webflux.java.jooq.notx.errorhandling.ErrorHandlingConfiguration;
import org.codemental.samples.webflux.java.jooq.notx.persistence.PersistenceConfiguration;
import org.codemental.samples.webflux.java.jooq.notx.user.UserConfiguration;
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
@Import({ErrorHandlingConfiguration.class, PersistenceConfiguration.class, UserConfiguration.class})
public class JooqNoTxApplication {

    public static void main(String[] args) {
        SpringApplication.run(JooqNoTxApplication.class, args);
    }
}
