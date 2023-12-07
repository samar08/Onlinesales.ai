//package com.example.OnlinesalesTask2.Services;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.http.*;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import java.net.URI;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//
//@Service
//public class RapidApiService implements RequestService{
//    private RestTemplateBuilder restTemplateBuilder;
//    @Value("${webapi.url}")
//    private String webApiUrl;
//
//    @Value("${webapi.key.name}")
//    private String webApiKeyName;
//    @Value("${webapi.key.value}")
//    private String webApiKeyValue;
//    @Value("${webapi.host.name}")
//    private String webApiHostName;
//    @Value("${webapi.host.value}")
//    private String webApiHostValue;
//    private URI uri;
//    private HttpHeaders headers;
//    private HttpEntity request;
//    private HttpRequest httpRequest;
//    private ResponseEntity<String> responseEntity;
//    RapidApiService(RestTemplateBuilder restTemplateBuilder){
//        this.restTemplateBuilder=restTemplateBuilder;
//    }
//    @Override
//    public String getExpressionResult(String encodedExpression, String expression) {
//
//        String evaluateExpressionUrl=webApiUrl+encodedExpression;
//        RestTemplate restTemplate=restTemplateBuilder.build();
//
//        try{
//            uri=new URI(evaluateExpressionUrl);
//            headers=new HttpHeaders();
//            headers.set(webApiHostName,webApiHostValue);
//            headers.set(webApiKeyName,webApiKeyValue);
//            request = new HttpEntity<>(headers);
//            responseEntity=restTemplate.exchange(uri, HttpMethod.GET,request,String.class);
//
//        }
//        catch(Exception e){
//            e.printStackTrace();
//        }
//        return (expression + " => "+ responseEntity.getBody());
//
//    }
//}
