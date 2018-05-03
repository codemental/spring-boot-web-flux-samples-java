package org.codemental.samples.webflux.java.jooq.notx.errorhandling;

import org.codemental.samples.webflux.java.jooq.notx.http.ClientResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class InternalServerErrorExceptionTranslator extends AbstractExceptionTranslator {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ClientResponse handleInternalServerError(Exception exception) {
        return createErrorResponse(exception, null);
    }
}