package com.example.OnlinesalesTask2.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Primary
@Service
public class MathjsApiService implements RequestService{
    private RestTemplateBuilder restTemplateBuilder;
   // private AsyncWebRequest asyncWebRequest;
    //AsyncWebRequest
    @Value("${webapi.url}")
    private String webApiUrl;

    //private ExecutorService executorService;


    private URI uri;
//    private ResponseEntity<String> responseEntity;
    MathjsApiService(RestTemplateBuilder restTemplateBuilder){
        this.restTemplateBuilder=restTemplateBuilder;
        //this.executorService = Executors.newFixedThreadPool(10);
    }
    @Override
    public Future<ResponseEntity<String>> getExpressionResult(String encodedExpression, ExecutorService executorService) {

        String evaluateExpressionUrl=webApiUrl+encodedExpression;
        RestTemplate restTemplate=restTemplateBuilder.build();
        Future<ResponseEntity<String>> response = null;
        try{
            uri=new URI(evaluateExpressionUrl);
            response = CompletableFuture.supplyAsync(()->{
               //System.out.println(Thread.currentThread().getName());
                return restTemplate.getForEntity(uri, String.class);
            }, executorService);

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return response;


    }
}
