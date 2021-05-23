package ru.bigint.service;

import ru.bigint.model.response.PhoneDTOWrapper;
import ru.bigint.wsdl.GetUserRequest;
import ru.bigint.wsdl.GetUserResponse;

import java.util.concurrent.CompletableFuture;

public interface AppService {
    CompletableFuture<GetUserResponse> getUserFromSOAP(GetUserRequest getUserRequest);
    CompletableFuture<PhoneDTOWrapper> getUserExtFields(int user);
}