package org.codemental.samples.webflux.java.jooq.notx.user.controller;


import org.codemental.samples.webflux.java.jooq.generated.tables.pojos.ImUser;
import org.codemental.samples.webflux.java.jooq.notx.http.BaseController;
import org.codemental.samples.webflux.java.jooq.notx.http.ClientResponse;
import org.codemental.samples.webflux.java.jooq.notx.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@RestController(value = "api/user")
public class UserController implements BaseController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

        Assert.notNull(this.userService, "Warning: UserService cannot be NULL.");
    }

    /**
     * Get one: GET Request URL .../api/user/{id}
     * -> HTTP 200     OK Response
     * -> HTTP 204  EMPTY Response
     */
    @GetMapping("/{id}")
    public Mono<ClientResponse> getOne(@PathVariable int id) {
       /* return userService.fetchOneMapById(id)
                .map(this::ok)
                .orElseGet(this::noContent);*/
       return null;
    }

    /**
     * Get all: GET Request URL .../api/user
     * -> HTTP 200    OK Response
     * -> HTTP 204 EMPTY Response
     */
    @GetMapping()
    public Mono<ClientResponse> getAll() {
       /* return userService.fetchMaps()
                .filter(list -> !list.isEmpty())
                .map(this::ok)
                .orElseGet(this::noContent);*/
        return null;

    }

    /**
     * Insert one: POST Request URL .../api/user
     * -> HTTP 201  OK Response
     * -> HTTP 204 NOK Response
     */
    @PostMapping()
    public Mono<ServerResponse> insert(@RequestBody ImUser user) {
        return userService.insert(user)
                .flatMap(this::ok);

    }

    /**
     * Update one: PUT Request URL .../api/user
     * -> HTTP 204  OK Response
     * -> HTTP 304 NOK Response
     */
    @PutMapping()
    public Mono<ClientResponse> update(@RequestBody ImUser user) {
       /* return userService.update(user)
                .map(this::ok)
                .orElseGet(this::notModified);*/
        return null;

    }

    /**
     * Update one: DELETE Request URL .../clinic/examination/{id}
     * -> HTTP 204  OK Response
     * -> HTTP 304 NOK Response
     */
    @DeleteMapping("/{id}")
    public Mono<ClientResponse> delete(@PathVariable int id) {
       /* return userService.delete(id)
                .filter(count -> count == 1)
                .map(success -> noContent())
                .orElseGet(this::notModified);*/
        return null;

    }
}
