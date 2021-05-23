package ru.bigint.model.response;

import java.util.List;

public class PhoneDTOWrapper {
    private PhoneDTO phoneDTO;
    private int error;

    public PhoneDTOWrapper() {
    }

    public PhoneDTO getPhoneDTO() {
        return phoneDTO;
    }

    public void setPhoneDTO(PhoneDTO phoneDTO) {
        this.phoneDTO = phoneDTO;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }
}
