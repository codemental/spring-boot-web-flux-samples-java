package org.codemental.samples.webflux.java.http.adapter.errorhandling;

import io.netty.handler.timeout.ReadTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.ConnectException;

@ControllerAdvice
public class BackendConnectionExceptionTranslator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler({
            ReadTimeoutException.class,
            ConnectException.class})
    @ResponseStatus(HttpStatus.GATEWAY_TIMEOUT)
    public void handleBadRequest(Exception exception) {
        logger.error(exception.getMessage(), exception);
        ;
    }
}