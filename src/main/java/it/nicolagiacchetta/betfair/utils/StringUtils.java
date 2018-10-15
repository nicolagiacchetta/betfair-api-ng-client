package it.nicolagiacchetta.betfair.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class StringUtils {

    private StringUtils() {}

    public static String toString(InputStream inputStream) throws IOException {
        return toString(inputStream, StandardCharsets.UTF_8.name());
    }

    public static String toString(InputStream inputStream, String encoding) throws IOException {
        try(ByteArrayOutputStream result = new ByteArrayOutputStream()){
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                result.write(buffer, 0, length);
            }
            return result.toString(encoding);
        }
    }

    public static boolean isNullOrEmptyString(Object o) {
        if(o == null) return true;
        return o.getClass().isAssignableFrom(String.class) ? ((String) o).trim().isEmpty() : false;
    }

    public static boolean isNullOrEmpty(String s) {
        return (s == null || s.trim().isEmpty());
    }
}
