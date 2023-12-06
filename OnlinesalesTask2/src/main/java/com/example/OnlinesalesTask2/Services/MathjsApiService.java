package com.example.OnlinesalesTask2.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Primary
@Service
public class MathjsApiService implements RequestService{
    private RestTemplateBuilder restTemplateBuilder;
    @Value("${webapi.url}")
    private String webApiUrl;


    private URI uri;
    private ResponseEntity<String> responseEntity;
    MathjsApiService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
    }
    @Override
    public String getExpressionResult(String expression) {
        String encodedExpression= URLEncoder.encode(expression, StandardCharsets.UTF_8);
        String evaluateExpressionUrl=webApiUrl+encodedExpression;
        RestTemplate restTemplate=restTemplateBuilder.build();
        try{
            uri=new URI(evaluateExpressionUrl);
            responseEntity=restTemplate.getForEntity(uri, String.class);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return (expression + " => "+ responseEntity.getBody());

    }
}
