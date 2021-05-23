package ru.bigint.util.constant;

public class Constant {
//    public final static String version = "task/stage2_012_new_async_requests";

    //Число потоков для раскопок
//    public final static int threadsCount = 10;


    private static String SERVER_ADDRESS = "localhost";
    static {
        if (System.getenv("ADDRESS") != null) {
            SERVER_ADDRESS = System.getenv("ADDRESS");
        }
    }
    private final static String SERVER_PORT = "8000";
    private final static String SERVER_SCHEMA = "http";
    public final static String SERVER_URI = SERVER_SCHEMA + "://" + SERVER_ADDRESS + ":" + SERVER_PORT;
}
