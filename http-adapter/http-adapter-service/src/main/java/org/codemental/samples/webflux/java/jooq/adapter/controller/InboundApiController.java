package org.codemental.samples.webflux.java.jooq.adapter.controller;


import org.codemental.samples.webflux.java.jooq.adapter.model.inbound.NotificationRequest;
import org.codemental.samples.webflux.java.jooq.adapter.model.inbound.NotificationResponse;
import org.codemental.samples.webflux.java.jooq.adapter.model.outbound.ReportRequest;
import org.codemental.samples.webflux.java.jooq.adapter.model.outbound.ReportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class InboundApiController {

    @Autowired
    private WebClient outboundApiClient;

    @PostMapping("/inbound")
    public Mono<NotificationResponse> notify(@RequestBody NotificationRequest notificationRequest) {
        return Mono.justOrEmpty(notificationRequest)
                .log()
                .map(this::mapToReqOut)
                .flatMap(reqOut -> outboundApiClient.post()
                        .uri("/api")
                        .body(BodyInserters.fromObject(reqOut))
                        .accept(APPLICATION_JSON_UTF8)
                        .exchange()
                        .filter(resIn -> resIn.statusCode() == HttpStatus.OK)
                        .flatMap(resIn -> resIn.bodyToMono(ReportResponse.class))
                        .map(this::mapToResOut));
    }

    private ReportRequest mapToReqOut(NotificationRequest inboundReq) {
        ReportRequest reqOut = new ReportRequest();
        reqOut.setInformation("User: " + inboundReq.getUserName()
                + " called operation: " + inboundReq.getOperation());
        reqOut.setTimestamp(LocalDateTime.now());
        return reqOut;
    }

    private NotificationResponse mapToResOut(ReportResponse inboundRes) {
        NotificationResponse resOut = new NotificationResponse();
        resOut.setSuccess(inboundRes.isAllowed());

        return resOut;
    }
}
