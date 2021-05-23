package ru.bigint.controller;

import org.springframework.web.bind.annotation.*;
import ru.bigint.model.response.PhoneDTO;
import ru.bigint.model.response.PhoneDTOWrapper;
import ru.bigint.model.response.ResponseDTO;
import ru.bigint.service.AppService;
import ru.bigint.util.LoggerUtil;
import ru.bigint.wsdl.GetUserRequest;
import ru.bigint.wsdl.GetUserResponse;
import ru.bigint.wsdl.User;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/user")
public class AppController {

    private final AppService appService;

    public AppController(AppService appService) {
        this.appService = appService;
    }

    @GetMapping("/{id}")
    public ResponseDTO get(@PathVariable int id) {
        //result
        ResponseDTO res = new ResponseDTO();
        res.setCode(1);
        res.setPhone("");
        res.setName("");

        //Call SOAP
        LoggerUtil.log("==> Start SOAP call");
        GetUserRequest getUserRequest = new GetUserRequest();
        getUserRequest.setUserId(id);
        CompletableFuture<GetUserResponse> cfSOAP = appService.getUserFromSOAP(getUserRequest);

        //Call REST
        LoggerUtil.log("==> Start REST call");
        CompletableFuture<PhoneDTOWrapper> cfREST = appService.getUserExtFields(id);


        try {
            GetUserResponse getUserResponse = cfSOAP.get();
            PhoneDTOWrapper phonesWrapper = cfREST.get();

            if (getUserResponse == null) {
                res.setCode(1);
            } else {
                User user = getUserResponse.getUser();
                if (user == null) {
                    res.setCode(2);
                } else {
                    if (phonesWrapper.getError() == 0) {
                        res.setCode(0);
                        res.setName(user.getFirstName() + " " + user.getLastName());
                        res.setPhone(phonesWrapper.getPhoneDTO().getPhones().get(0));
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        LoggerUtil.log(">>> RESULT Response: " + res);

        return res;
    }

}