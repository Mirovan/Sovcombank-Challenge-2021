package ru.bigint.emulator.model.response;

import java.util.List;

public class PhoneDTO {
    private List<String> phones;

    public PhoneDTO() {
    }

    public List<String> getPhones() {
        return phones;
    }

    public void setPhones(List<String> phones) {
        this.phones = phones;
    }
}
