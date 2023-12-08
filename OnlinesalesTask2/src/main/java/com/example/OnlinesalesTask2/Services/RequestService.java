package com.example.OnlinesalesTask2.Services;


import com.example.OnlinesalesTask2.Exceptions.BadRequestException;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public interface RequestService {
    Future<ResponseEntity<String>> getExpressionResult(String encodedExpression, ExecutorService executorService, HashMap<String,String> encodedtoData) throws URISyntaxException;
}
