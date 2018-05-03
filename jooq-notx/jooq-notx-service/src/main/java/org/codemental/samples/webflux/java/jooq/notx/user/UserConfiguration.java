package org.codemental.samples.webflux.java.jooq.notx.user;

import org.codemental.samples.webflux.java.jooq.notx.user.controller.UserController;
import org.codemental.samples.webflux.java.jooq.notx.user.dao.UserDao;
import org.codemental.samples.webflux.java.jooq.notx.user.service.UserService;
import org.jooq.DSLContext;
import org.springframework.context.annotation.Bean;

public class UserConfiguration {

    @Bean
    public UserDao userDao(DSLContext jooqDslContext) {
        return new UserDao(jooqDslContext);
    }

    @Bean
    public UserService userService(UserDao userDao) {
        return new UserService(userDao);
    }

    @Bean
    public UserController userController(UserService userService) {
        return new UserController(userService);
    }
}
