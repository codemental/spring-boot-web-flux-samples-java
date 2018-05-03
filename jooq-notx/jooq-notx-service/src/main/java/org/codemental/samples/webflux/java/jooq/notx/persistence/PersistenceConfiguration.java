package org.codemental.samples.webflux.java.jooq.notx.persistence;

import com.zaxxer.hikari.HikariDataSource;
import org.jooq.ConnectionProvider;
import org.jooq.DSLContext;
import org.jooq.ExecuteListenerProvider;
import org.jooq.SQLDialect;
import org.jooq.conf.SettingsTools;
import org.jooq.impl.DSL;
import org.jooq.impl.DataSourceConnectionProvider;
import org.jooq.impl.DefaultConfiguration;
import org.jooq.impl.DefaultExecuteListenerProvider;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

public class PersistenceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "datasource")
    public HikariDataSource dataSource() {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean
    public ConnectionProvider connectionProvider(DataSource dataSource) {
        return new DataSourceConnectionProvider(dataSource);
    }

    @Bean
    public SqlExceptionTranslator exceptionTranslator() {
        return new SqlExceptionTranslator();
    }

    @Bean
    public ExecuteListenerProvider executeListenerProvider(SqlExceptionTranslator sqlExceptionTranslator) {
        return new DefaultExecuteListenerProvider(sqlExceptionTranslator);
    }

    @Bean
    @Primary
    public DSLContext jooqDslContext(ConnectionProvider connectionProvider, ExecuteListenerProvider executeListenerProvider) {
        return DSL.using(new DefaultConfiguration()
                .derive(connectionProvider)
                .derive(executeListenerProvider)
                .derive(SQLDialect.POSTGRES_9_5)
                .set(SettingsTools.defaultSettings()
                        .withExecuteWithOptimisticLocking(true)
                        .withRenderSchema(false)));
    }
}
