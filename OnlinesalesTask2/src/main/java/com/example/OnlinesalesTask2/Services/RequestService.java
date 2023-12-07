package com.example.OnlinesalesTask2.Services;


import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public interface RequestService {
    Future<ResponseEntity<String>> getExpressionResult(String encodedExpression, ExecutorService executorService);
}
