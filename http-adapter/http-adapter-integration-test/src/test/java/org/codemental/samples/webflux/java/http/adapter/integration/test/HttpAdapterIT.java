package org.codemental.samples.webflux.java.http.adapter.integration.test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import com.consol.citrus.http.server.HttpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;

@Test
public class HttpAdapterIT extends TestNGCitrusTestRunner {

    @Autowired
    private HttpClient httpClient;
    @Autowired
    private HttpServer httpServer;

    @CitrusTest
    public void test() {
        http(action -> action.client(httpClient)
                .send()
                .post("inbound")
                .payload("{" +
                            "\"userName\": \"Bruce.Wayne\"," +
                            "\"operation\": \"call-a-pizza\"" +
                        "}")
                .fork(true));

        http(action -> action.server(httpServer)
                .receive()
                .post("/api")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .payload("{" +
                            "\"information\": \"User: Bruce.Wayne called operation: call-a-pizza\"," +
                            "\"timestamp\": \"@ignore@\"" +
                        "}"));

        http(action -> action.server(httpServer)
                .respond(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .payload("{" +
                            "\"allowed\": true" +
                        "}"));

        http(action -> action.client(httpClient)
                .receive()
                .response(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .payload("{" +
                        "\"success\": true" +
                        "}"));
    }
}
