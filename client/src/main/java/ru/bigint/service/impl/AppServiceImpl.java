package ru.bigint.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.bigint.model.response.PhoneDTO;
import ru.bigint.model.response.PhoneDTOWrapper;
import ru.bigint.service.AppService;
import ru.bigint.util.LoggerUtil;
import ru.bigint.util.MapperUtil;
import ru.bigint.util.constant.Constant;
import ru.bigint.wsdl.GetUserRequest;
import ru.bigint.wsdl.GetUserResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class AppServiceImpl implements AppService {

    final SoapClient soapClient;

    private static int TIMEOUT = 5;

    private static HttpClient httpClient = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(TIMEOUT))
            .build();

    public AppServiceImpl(SoapClient soapClient) {
        this.soapClient = soapClient;
    }

    @Override
    @Async
    public CompletableFuture<GetUserResponse> getUserFromSOAP(GetUserRequest request) {
        return CompletableFuture.completedFuture(soapClient.getUserFromSOAP(request));
    }

    @Override
    @Async
    public CompletableFuture<PhoneDTOWrapper> getUserExtFields(int userId) {
        PhoneDTOWrapper responseDTO = sendHttpRequest(userId);

        return CompletableFuture.completedFuture(responseDTO);
    }


    public static PhoneDTOWrapper sendHttpRequest(int userId) {
        String url = Constant.SERVER_URI_RS + "/" + userId;

        LoggerUtil.log("REST response: " + url);

        HttpRequest httpRequest =
                HttpRequest.newBuilder()
                        .uri(URI.create(url))
                        .header("Content-Type", "application/json; charset=UTF-8")
                        .timeout(Duration.ofSeconds(TIMEOUT))
                        .GET()
                        .build();

        //Отправляем http-запрос
        CompletableFuture<PhoneDTO> cf = httpClient
                .sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(httpResponse -> {
                    PhoneDTO responseObj = null;
                    if (httpResponse != null) {
                        LoggerUtil.log("REST response: " + httpResponse);
                        if (httpResponse.statusCode() == 200) {
                            MapperUtil<PhoneDTO> resultMapper = new MapperUtil<>(PhoneDTO.class);
                            responseObj = resultMapper.jsonToObject(httpResponse.body());
                        } else {
                            LoggerUtil.log("REST Error: " + httpResponse.body());
                        }
                    } else {
                        LoggerUtil.log("<<< REST Response = null");
                    }

                    return responseObj;
                });


        PhoneDTOWrapper res = new PhoneDTOWrapper();
        try {
            PhoneDTO phoneDTO = cf.get();
            res.setPhoneDTO(phoneDTO);
            res.setError(0);
        } catch (ClassCastException e) {
            res.setError(2);
            LoggerUtil.log("REST ERROR: " + e.getMessage());
        } catch (ExecutionException | InterruptedException e) {
            res.setError(1);
            LoggerUtil.log("REST ERROR: " + e.getMessage());
        }

        return res;
    }

}
