package ru.bigint.controller;

import org.springframework.web.bind.annotation.*;
import ru.bigint.model.request.RequestDTO;
import ru.bigint.model.response.ResponseDTO;
import ru.bigint.service.AppService;
import ru.bigint.util.LoggerUtil;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/go")
public class AppController {

    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/get")
    public ResponseDTO get() {
        ResponseDTO res = null;
        try {
            RequestDTO requestDTO = new RequestDTO();
            requestDTO.setX(1);
            requestDTO.setY(2);
            res = appService.go(requestDTO).get();
        } catch (InterruptedException | ExecutionException e) {
            LoggerUtil.log("Error: " + e.getMessage());
        }
        return res;
    }

    @PostMapping("/post")
    public ResponseDTO post(@RequestBody RequestDTO requestDTO) {
        ResponseDTO res = null;
        try {
            res = appService.go(requestDTO).get();
        } catch (InterruptedException | ExecutionException e) {
            LoggerUtil.log("Error: " + e.getMessage());
        }
        return res;
    }

}