package ru.bigint.util;


import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.stream.Collectors;

public class LoggerUtil {
    private static long TIME;

    public static void log(Object msg) {
        System.out.println(msg);
    }

}
