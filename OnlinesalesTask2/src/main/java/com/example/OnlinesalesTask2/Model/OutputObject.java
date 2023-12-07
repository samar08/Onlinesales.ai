package com.example.OnlinesalesTask2.Model;

import org.springframework.http.ResponseEntity;

import java.util.concurrent.Future;


public class OutputObject {
    String expression;
    Future<ResponseEntity<String>> apiFutureResponse;

    public OutputObject(String expression, Future<ResponseEntity<String>> apiFutureResponse){
        this.expression=expression;
        this.apiFutureResponse=apiFutureResponse;
    }
    public String getExpression(){
        return this.expression;
    }
    public Future<ResponseEntity<String>> getApiFutureResponse(){
        return this.apiFutureResponse;
    }

    public void setApiFutureResponse(Future<ResponseEntity<String>> apiFutureResponse) {
        this.apiFutureResponse = apiFutureResponse;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
