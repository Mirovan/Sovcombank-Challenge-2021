package ru.bigint.model.response;

public class ResponseDTO {
    private Integer code;
    private String name;
    private String phone;

    public ResponseDTO() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ResponseDTO{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
