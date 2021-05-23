package ru.bigint.emulator;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.bigint.emulator.model.request.RequestDTO;
import ru.bigint.emulator.model.response.ResponseDTO;

import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class MainController {

    private static AtomicInteger queryNum = new AtomicInteger(0);


    @PostMapping("/endpoint")
    public ResponseDTO postLicences(@RequestBody RequestDTO requestDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setId(queryNum.incrementAndGet());
        return responseDTO;
    }

}
