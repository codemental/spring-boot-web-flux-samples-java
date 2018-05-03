package org.codemental.samples.webflux.java.jooq.notx.user.service;

import org.codemental.samples.webflux.java.jooq.generated.tables.pojos.ImUser;
import org.codemental.samples.webflux.java.jooq.notx.user.dao.UserDao;
import org.springframework.util.Assert;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class UserService {

    private UserDao userDao;

    public UserService(UserDao userDao) {
        this.userDao = userDao;

        Assert.notNull(this.userDao, "Warning: UserDao cannot be NULL.");
    }

    public Optional<Map<String, Object>> fetchOneMapById(int id) {
        return userDao.fetchOneMapById(id);
    }

    public Optional<List<Map<String, Object>>> fetchMaps() {
        return userDao.fetchMaps();
    }

    public Mono<Integer> insert(ImUser user) {
       return Mono.just(user)
                .subscribeOn(Schedulers.elastic())
                .map(userDao::insert);
    }

    public Optional<LocalDateTime> update(ImUser user) {
        return userDao.update(user);
    }

    public Optional<Integer> delete(int id) {
        return userDao.delete(id);
    }
}
