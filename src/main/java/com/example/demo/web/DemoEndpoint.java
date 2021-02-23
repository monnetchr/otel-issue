package com.example.demo.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
@RequiredArgsConstructor
public class DemoEndpoint {
    private final RestTemplate restTemplate;

    @GetMapping("/demo")
    public String demo() {
        log.info("demo start, we have traceId/spanId in MDC:");

        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        var request = new HttpEntity<>(null, headers);
        var response = restTemplate.exchange("https://httpbin.org/get", HttpMethod.GET, request, String.class);
        log.info("response.headers: {}", response.getHeaders());
        log.info("response.body: {}", response.getBody());

        log.info("demo end, where is traceId/spanId in MDC?");
        return "demo";
    }
}
