package ru.bigint.model.response;

public class ResponseDTO {
    private Integer id;
    private Integer digAllowed;
    private Integer digUsed;

    public ResponseDTO() {
    }

    public ResponseDTO(Integer id, Integer digAllowed, Integer digUsed) {
        this.id = id;
        this.digAllowed = digAllowed;
        this.digUsed = digUsed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDigAllowed() {
        return digAllowed;
    }

    public void setDigAllowed(Integer digAllowed) {
        this.digAllowed = digAllowed;
    }

    public Integer getDigUsed() {
        return digUsed;
    }

    public void setDigUsed(Integer digUsed) {
        this.digUsed = digUsed;
    }

    @Override
    public String toString() {
        return "License{" +
                "id=" + id +
                ", digAllowed=" + digAllowed +
                ", digUsed=" + digUsed +
                '}';
    }
}
