package it.nicolagiacchetta.betfair.entities;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class LoginResponseTest {

    private final static String TOKEN_VALUE = "token";
    private final static String PRODUCT_VALUE = "product";
    private final static LoginStatus STATUS_VALUE = LoginStatus.SUCCESS;
    private final static String ERROR_VALUE = "";

    @Test
    public void deserializeLoginResponse() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String responseJson =
                "{\n" +
                "    \"token\": \"" + TOKEN_VALUE + "\",\n" +
                "    \"product\": \""+ PRODUCT_VALUE +"\",\n" +
                "    \"status\": \"" + STATUS_VALUE.name() + "\",\n" +
                "    \"error\": \"\"\n" +
                "}";

        LoginResponse loginResponse = objectMapper.readValue(responseJson, LoginResponse.class);
        assertEquals(TOKEN_VALUE, loginResponse.getToken());
        assertEquals(PRODUCT_VALUE, loginResponse.getProduct());
        assertEquals(STATUS_VALUE, loginResponse.getStatus());
        assertEquals(ERROR_VALUE, loginResponse.getError());
    }
}