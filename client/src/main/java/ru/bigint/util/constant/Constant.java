package ru.bigint.util.constant;

public class Constant {
//    public final static String version = "task/stage2_012_new_async_requests";

    private static String SERVER_ADDRESS_WS = "https://sovkombank.getsandbox.com:443";
    private static String SERVER_ADDRESS_RS = "http://localhost:8000";
    static {
        if (System.getenv("ws.endpoint") != null) {
            SERVER_ADDRESS_WS = System.getenv("ws.endpoint");
        }
        if (System.getenv("rs.endpoint") != null) {
            SERVER_ADDRESS_RS = System.getenv("rs.endpoint");
        }
    }
//    private final static String SERVER_PORT = "8000";
//    private final static String SERVER_SCHEMA = "http";
//    public final static String SERVER_URI = SERVER_SCHEMA + "://" + SERVER_ADDRESS + ":" + SERVER_PORT;
    public final static String SERVER_URI_WS = SERVER_ADDRESS_WS;
    public final static String SERVER_URI_RS = SERVER_ADDRESS_RS + "/api/v1/phones";
}
