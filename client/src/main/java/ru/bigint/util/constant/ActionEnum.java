package ru.bigint.util.constant;

public enum ActionEnum {

    ALL("/endpoint");

    private String request;

    ActionEnum(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

}
