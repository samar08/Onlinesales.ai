package com.example.OnlinesalesTask2.Services;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.concurrent.*;

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
    public MathjsApiService(RestTemplateBuilder restTemplateBuilder, @Value("${webapi.url}") String webApiUrl){
        this.restTemplateBuilder=restTemplateBuilder;
        this.webApiUrl=webApiUrl;
        //this.executorService = Executors.newFixedThreadPool(10);
    }
    @Override
    public Future<ResponseEntity<String>> getExpressionResult(String encodedExpression, ExecutorService executorService, HashMap<String,String> encodedToData) throws URISyntaxException {

        String evaluateExpressionUrl = webApiUrl + encodedExpression;
        RestTemplate restTemplate = restTemplateBuilder.build();
        Future<ResponseEntity<String>> response;
        uri = new URI(evaluateExpressionUrl);
        response = CompletableFuture.supplyAsync(() -> {
            try{
                return restTemplate.getForEntity(uri, String.class);
            }
            catch(HttpClientErrorException.BadRequest  e) {

                return new ResponseEntity<>("message from API: "+e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }, executorService);
        return response;


    }
}
