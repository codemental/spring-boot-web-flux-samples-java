package org.codemental.samples.webflux.java.jooq.notx.http;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@RestController()
@RequestMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public interface BaseController {

    /*
      HTTP 200 OK -> http://tools.ietf.org/html/rfc7231#section-6.3.1
     */
    default Mono<ServerResponse> ok(Object result) {
        return ServerResponse.ok().body(response -> new ClientResponse(result), ClientResponse.class);
    }

    /*
      HTTP 201 CREATED -> http://tools.ietf.org/html/rfc7231#section-6.3.2
     */
    default ResponseEntity<ClientResponse> created(int id) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new ClientResponse(id));
    }

    /*
      HTTP 204 No Content -> http://tools.ietf.org/html/rfc7231#section-6.3.5
     */
    default <T> ResponseEntity<T> noContent() {
        return ResponseEntity.noContent().build();
    }

    /*
      HTTP 304 Not Modified -> http://tools.ietf.org/html/rfc7232#section-4.1
     */
    default <T> ResponseEntity<T> notModified() {
        return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    /*
      HTTP 400 Bad Request
     */
    default <T> ResponseEntity<T> badRequest() {
        return ResponseEntity.badRequest().build();
    }
}
