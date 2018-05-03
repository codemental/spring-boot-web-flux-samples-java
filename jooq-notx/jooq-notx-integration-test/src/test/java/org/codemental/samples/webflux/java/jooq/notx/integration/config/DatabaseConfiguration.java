package org.codemental.samples.webflux.java.jooq.notx.integration.config;


import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class DatabaseConfiguration {

    @Value("${jooq.notx.db.host}")
    private String dbHost;
    @Value("${jooq.notx.db.port}")
    private int dbPort;
    @Value("${jooq.notx.db.user}")
    private String dbUser;
    @Value("${jooq.notx.db.pass}")
    private String dbPass;

    @Bean()
    public BasicDataSource jooqNotxDatasource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl(String.format("jdbc:postgresql://%s:%d/vps_prov", dbHost, dbPort));
        dataSource.setUsername(dbUser);
        dataSource.setPassword(dbPass);
        dataSource.setInitialSize(1);
        dataSource.setMaxActive(5);

        return dataSource;
    }
}
