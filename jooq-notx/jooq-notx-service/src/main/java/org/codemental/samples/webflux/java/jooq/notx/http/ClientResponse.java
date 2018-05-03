package org.codemental.samples.webflux.java.jooq.notx.http;

/**
 * This class is used as a template for the response from the backend to the frontend.
 * Jackson serializes this into a JSON String.
 */
public class ClientResponse {

    private Object data;

    public ClientResponse(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }
}
