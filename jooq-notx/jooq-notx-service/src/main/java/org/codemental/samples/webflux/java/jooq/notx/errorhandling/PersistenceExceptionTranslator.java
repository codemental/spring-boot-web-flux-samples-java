package org.codemental.samples.webflux.java.jooq.notx.errorhandling;

import org.codemental.samples.webflux.java.jooq.notx.http.ClientResponse;
import org.jooq.exception.DataChangedException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.codemental.samples.webflux.java.jooq.notx.http.ErrorResponseKeys.*;


@ControllerAdvice
public class PersistenceExceptionTranslator extends AbstractExceptionTranslator {

    @ExceptionHandler({DuplicateKeyException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ClientResponse handleDuplicateKey(Exception exception) {
        return createErrorResponse(exception, ENTITY_ALREADY_EXISTS_KEY);
    }

    @ExceptionHandler({DataChangedException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody
    public ClientResponse handleDataChanged(Exception exception) {
        return createErrorResponse(exception, ENTITY_CHANGED_KEY);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ClientResponse handleDataIntegrityViolation(Exception exception) {
        return createErrorResponse(exception, ENTITY_INVALID_KEY);
    }
}
