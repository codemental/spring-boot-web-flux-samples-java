package org.codemental.samples.webflux.java.jooq.notx.errorhandling;

import org.codemental.samples.webflux.java.jooq.notx.http.ClientResponse;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BadRequestExceptionTranslator extends AbstractExceptionTranslator {

    private final static String BAD_REQUEST_KEY = "bad_request";

    @ExceptionHandler({
            HttpMessageNotReadableException.class,
            IllegalArgumentException.class,
            InvalidDataAccessApiUsageException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ClientResponse handleBadRequest(Exception exception) {
        return createErrorResponse(exception, BAD_REQUEST_KEY);
    }
}
