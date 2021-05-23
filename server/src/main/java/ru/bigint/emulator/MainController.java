package ru.bigint.emulator;

import org.springframework.web.bind.annotation.*;
import ru.bigint.emulator.model.response.PhoneDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class MainController {

    private static AtomicInteger queryNum = new AtomicInteger(0);


    @GetMapping("/api/v1/phones/{userId}")
    public PhoneDTO postLicences(@PathVariable int userId) {
        PhoneDTO responseDTO = new PhoneDTO();
        List<String> list = new ArrayList<>();
        list.add("+7-123-456-789");
        list.add("+7-999-999");
        responseDTO.setPhones(list);
        return responseDTO;
    }

}
