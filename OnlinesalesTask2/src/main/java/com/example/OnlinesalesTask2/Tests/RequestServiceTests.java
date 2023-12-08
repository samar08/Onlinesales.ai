//package com.example.OnlinesalesTask2.Tests;
//
//
//import com.example.OnlinesalesTask2.Services.MathjsApiService;
//import com.example.OnlinesalesTask2.Services.RequestService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.http.ResponseEntity;
//import org.springframework.boot.test.context.SpringBootTest;
//import java.net.URISyntaxException;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.sql.Array;
//import java.util.concurrent.*;
//import java.util.*;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.is;
////import static sun.nio.cs.Surrogate.is;
//
//
//@SpringBootTest
//public class RequestServiceTests {
//    MathjsApiService requestService;
//    RestTemplateBuilder restTemplateBuilder;
//
//    RequestServiceTests(){
//        this.restTemplateBuilder=new RestTemplateBuilder();
//        this.requestService=new MathjsApiService(restTemplateBuilder);
//    }
//@DisplayName("Junit test for testing the getExpressionsResult method of RequestService")
//@Test
//public void test_getExpressionsResult() throws URISyntaxException, ExecutionException, InterruptedException {
//        int testDataSize=0,i;
//        String encodedExpression;
//        String[] testData=new String[]{"(5*4)/0", "sqrt(3*3+4*4)", "25^2", "21*12*53*44","1231+5883+482*3+21^4"};
//        String[] results=new String[]{"Infinity", "5", "625", "5.87664e+5", "2.03041e+5"};
//        testDataSize=testData.length;
//        String[] encodedTestData=new String[testDataSize];
//    ExecutorService executorService= Executors.newFixedThreadPool(10);
//    for(i=0;i<testDataSize;i++) {
//        encodedExpression = URLEncoder.encode(testData[i], StandardCharsets.UTF_8);
//        encodedTestData[i]=encodedExpression;
//        //encodedToData.put(encodedExpression, data[i]);
//    }
//    List<Future<ResponseEntity<String>>> responses =new ArrayList<>();
//    for(i=0;i<testDataSize;i++){
//        Future<ResponseEntity<String>> response= requestService.getExpressionResult(encodedTestData[i],executorService,null);
//        responses.add(response);
//    }
//    for(i=0;i<testDataSize;i++){
//        assertThat(responses.get(i).get().getBody(), is(results[i]));
//    }
//
//}
//
//
//}
