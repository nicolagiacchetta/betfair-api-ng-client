package it.nicolagiacchetta.betfair.utils;

import java.util.Map;

public class MiscUtils {

    private MiscUtils() {}

    public static boolean isNullOrEmpty(Map map) {
        return (map == null || map.isEmpty());
    }
}
