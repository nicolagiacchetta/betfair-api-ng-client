package it.nicolagiacchetta.betfair.utils;

import org.junit.Assert;
import org.junit.Test;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class HttpUtilsTest {

    public static final String URI = "http://www.nicolagiacchetta.it";
    public static final String PARAM_NAME = "name";
    public static final String PARAM_VALUE = "value";

    public static final String PARAM_NAME_2 = "nametwo";
    public static final String PARAM_VALUE_2 = "valuetwo";
    
    @Test
    public void appendQueryStringTest_OneParam() throws URISyntaxException {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(PARAM_NAME, PARAM_VALUE);
        String actual = HttpUtils.appendQueryString(URI, queryParams);
        String expected = URI + "?" + toQueryStringPair(PARAM_NAME, PARAM_VALUE);
        Assert.assertEquals(expected.trim(), actual.trim());
    }

    @Test
    public void appendQueryStringTest_TwoParams() throws URISyntaxException {
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put(PARAM_NAME, PARAM_VALUE);
        queryParams.put(PARAM_NAME_2, PARAM_VALUE_2);

        String actual = HttpUtils.appendQueryString(URI, queryParams);

        Assert.assertTrue(actual.startsWith(URI + "?"));
        Assert.assertTrue(actual.contains(toQueryStringPair(PARAM_NAME, PARAM_VALUE)));
        Assert.assertTrue(actual.contains(toQueryStringPair(PARAM_NAME_2, PARAM_VALUE_2)));
    }

    @Test
    public void appendQueryStringTest_NoParams() throws URISyntaxException {
        String actual = HttpUtils.appendQueryString(URI, null);
        Assert.assertEquals(URI.trim(), actual.trim());
        actual = HttpUtils.appendQueryString(URI, new HashMap<>());
        Assert.assertEquals(URI.trim(), actual.trim());
    }

    @Test
    public void appendQueryStringTest_NullUri() throws URISyntaxException {
        String actual = HttpUtils.appendQueryString(null, null);
        Assert.assertEquals(null, actual);
    }

    private static String toQueryStringPair(String paramName, String paramValue) {
        return paramName + "=" + paramValue;
    } 
    
}