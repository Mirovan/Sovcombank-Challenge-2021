package ru.bigint.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.bigint.util.constant.ActionEnum;
import ru.bigint.util.constant.Constant;
import ru.bigint.model.request.RequestDTO;
import ru.bigint.model.response.ResponseDTO;
import ru.bigint.service.AppService;
import ru.bigint.util.LoggerUtil;
import ru.bigint.util.MapperUtil;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class AppServiceImpl implements AppService {

    private static HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(2))
            .build();

    @Override
    @Async
    public CompletableFuture<ResponseDTO> go(RequestDTO requestDTO) {
        ResponseDTO responseDTO = sendHttpRequest(requestDTO, ActionEnum.ALL);

        return CompletableFuture.completedFuture(responseDTO);
    }


    public static ResponseDTO sendHttpRequest(RequestDTO requestObj, ActionEnum actionEnum) {
        String url = Constant.SERVER_URI + actionEnum.getRequest();

        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = null;
        try {
            requestBody = objectMapper.writeValueAsString(requestObj);
        } catch (JsonProcessingException e) {
            LoggerUtil.log(e.getMessage());
        }

        HttpRequest httpRequest =
                HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .header("Content-Type", "application/json; charset=UTF-8")
                        .timeout(Duration.ofSeconds(2))
                        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                        .build();

        //Отправляем http-запрос
        CompletableFuture<ResponseDTO> cf = httpClient
                .sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(httpResponse -> {
                    ResponseDTO responseObj = null;
                    if (httpResponse != null) {
                        LoggerUtil.logRequestResponse(actionEnum, requestObj, httpResponse);
                        if (httpResponse.statusCode() == 200) {
                            MapperUtil<ResponseDTO> resultMapper = new MapperUtil<>(ResponseDTO.class);
                            responseObj = resultMapper.jsonToObject(httpResponse.body());
                        } else {
                            LoggerUtil.log("Explore Error: " + httpResponse.body());
                        }
                    } else {
                        LoggerUtil.log(actionEnum, "<<< Response: " + actionEnum + "; Response = null");
                    }

                    return responseObj;
                });


        ResponseDTO res = null;
        try {
            res = cf.get();
        } catch (InterruptedException | ExecutionException e) {
            LoggerUtil.log(actionEnum, e.getMessage().substring(0, 40));
        }

        return res;
    }
}
