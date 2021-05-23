package ru.bigint.service;

import ru.bigint.model.request.RequestDTO;
import ru.bigint.model.response.ResponseDTO;

import java.util.concurrent.CompletableFuture;

public interface AppService {
    CompletableFuture<ResponseDTO> go(RequestDTO appDTO);
}