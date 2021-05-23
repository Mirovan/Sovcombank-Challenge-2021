package ru.bigint.service.impl;

import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.transport.WebServiceMessageSender;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import org.springframework.ws.transport.http.HttpUrlConnectionMessageSender;
import ru.bigint.util.LoggerUtil;
import ru.bigint.util.constant.Constant;
import ru.bigint.wsdl.GetUserRequest;
import ru.bigint.wsdl.GetUserResponse;

import java.time.Duration;

//@Service
public class SoapClient extends WebServiceGatewaySupport {
    private static final int CONNECTION_TIMEOUT = (5 * 1000);
    private static final int READ_TIMEOUT = (5 * 1000);

    public GetUserResponse getUserFromSOAP(GetUserRequest request) {
        LoggerUtil.log("SOAP call: " + Constant.SERVER_URI_WS);
        LoggerUtil.log("SOAP request: " + request);


        WebServiceTemplate wsTemplate = this.getWebServiceTemplate();
        WebServiceMessageSender[] senders = wsTemplate.getMessageSenders();
        for (WebServiceMessageSender sender : senders) {
            try {
                HttpUrlConnectionMessageSender httpSender = (HttpUrlConnectionMessageSender) sender;
                httpSender.setReadTimeout(Duration.ofMillis(READ_TIMEOUT));
                httpSender.setConnectionTimeout(Duration.ofMillis(CONNECTION_TIMEOUT));
            } catch (ClassCastException | NumberFormatException cex) {
                LoggerUtil.log("Exception - Cannot set WS timeout: " + cex.getMessage());
            }
        }

        GetUserResponse response = null;
        try {
            response =
                    (GetUserResponse) wsTemplate.marshalSendAndReceive(Constant.SERVER_URI_WS, request);
        } catch (Exception e) {
            LoggerUtil.log("Exception: " + e.getMessage());
            response = new GetUserResponse();
        }


        LoggerUtil.log("SOAP response: " + response);
        return response;
    }

}
