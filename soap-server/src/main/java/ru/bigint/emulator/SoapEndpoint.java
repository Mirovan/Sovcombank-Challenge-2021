package ru.bigint.emulator;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.bigint.gen.Gender;
import ru.bigint.gen.GetUserRequest;
import ru.bigint.gen.GetUserResponse;
import ru.bigint.gen.User;

@Endpoint
public class SoapEndpoint {

    private static final String NAMESPACE_URI = "http://localhost";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
    @ResponsePayload
    public GetUserResponse getResponse(@RequestPayload GetUserRequest request) {
        GetUserResponse response = new GetUserResponse();
        User user = new User();
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        user.setGender(Gender.MALE);
        response.setUser(user);

        return response;
    }
}