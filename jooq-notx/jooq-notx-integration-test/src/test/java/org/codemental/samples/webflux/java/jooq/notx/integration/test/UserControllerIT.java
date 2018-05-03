package org.codemental.samples.webflux.java.jooq.notx.integration.test;

import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.dsl.testng.TestNGCitrusTestRunner;
import com.consol.citrus.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.testng.annotations.Test;

import javax.sql.DataSource;

@Test
public class UserControllerIT extends TestNGCitrusTestRunner {

    @Autowired
    private HttpClient httpClient;
    @Autowired
    private DataSource jooqNotxDatasource;

    @CitrusTest
    public void createUserTest() {
        http(action -> action.client(httpClient)
                .send()
                .post("api/user")
                .payload("{" +
                        "\"firstName\": \"Bruce\"," +
                        "\"lastName\": \"Wayne\"," +
                        "\"title\": \"Batman\"," +
                        "\"email\": \"batman@wayne-enterprises.com\"" +
                        "}")
                .fork(true));

        http(action -> action.client(httpClient)
                .receive()
                .response(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .payload("{" +
                        "\"success\": true" +
                        "}"));
    }
}
