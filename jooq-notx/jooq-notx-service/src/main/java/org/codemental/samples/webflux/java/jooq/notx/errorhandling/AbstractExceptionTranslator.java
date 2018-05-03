package org.codemental.samples.webflux.java.jooq.notx.errorhandling;

import org.codemental.samples.webflux.java.jooq.notx.http.ClientResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractExceptionTranslator {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected ClientResponse createErrorResponse(Exception exception, String responseKey) {
        logger.error("Handling {}", exception.getClass());
        logger.debug("Exception message: {}", exception.getMessage());
        return new ClientResponse(responseKey);
    }
}
