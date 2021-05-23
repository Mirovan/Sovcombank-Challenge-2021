package ru.bigint.model.request;

public class RequestDTO {
    private int x;
    private int y;

    public RequestDTO() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "RequestDTO{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
