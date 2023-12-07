package com.example.OnlinesalesTask2.Controllers;

import com.example.OnlinesalesTask2.Model.OutputObject;
import com.example.OnlinesalesTask2.Services.RequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;

@RestController
@RequestMapping("/api/v1/")
public class RequestController {
    private RequestService requestService;
    private int dataSizeMultiplier=50;
    @Value("${webapi.limitpersecond}")
    private int publicApiLimitPerSecond;
    private String[] data;
    private int dataSize;
    private ArrayList<String> encodedData;
    private ArrayList<String> expressions;
    private int expressionsLength;
    private Map<String, String> encodedToData;


    public RequestController(RequestService requestService){
        int i=0,j=0;
        String encodedExpression;

        this.requestService=requestService;
        this.data=new String[]{"1+1","2*2+4*5+5*6-9*10+11*11","sqrt(3*3+5*5)","sqrt(121)","21^5","100^2 + 21^2 + 24^4","1/0","sqrt(1*1+2*2+3^4+5^2+81^4)","4*6+9+10*7", "4^3+11^5"};
        this.expressions=new ArrayList<>();
        this.encodedData=new ArrayList<>();
        this.dataSize=data.length;
        this.encodedToData = new HashMap<>();
        for(i=0;i<dataSize;i++) {
            encodedExpression = URLEncoder.encode(data[i], StandardCharsets.UTF_8);
            encodedData.add(encodedExpression);
            encodedToData.put(encodedExpression, data[i]);
        }
        for(i=0;i<dataSizeMultiplier;i++){
            for(j=0;j<dataSize;j++){

                expressions.add(encodedData.get(j));
            }
        }
        this.expressionsLength=dataSize*dataSizeMultiplier;

    }
    @GetMapping("/execute")
    public void getExpressionsResult(){

        int i,j;
        try {


            ExecutorService executorService;


            List<OutputObject> responses;
            long curr = System.currentTimeMillis();

            int iterations = expressionsLength / publicApiLimitPerSecond;

            for (i = 0; i < iterations; i++) {
                executorService = Executors.newFixedThreadPool(publicApiLimitPerSecond);
              responses=new ArrayList<>();
                for (j = 0; j < publicApiLimitPerSecond; j++) {

                    String encodedInput = expressions.get(i*publicApiLimitPerSecond +j);
                    Future<ResponseEntity<String>> apiFutureResponse = requestService.getExpressionResult(encodedInput,executorService);
                    OutputObject outputObject=new OutputObject( encodedToData.get(encodedInput), apiFutureResponse);
                    responses.add(outputObject);
                }

                executorService.shutdown();
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                responses.forEach(responseEntityFuture -> {
                try {
                    System.out.println( responseEntityFuture.getExpression() + " => "+ responseEntityFuture.getApiFutureResponse().get().getBody());
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            });

            }

        long timeTaken = (System.currentTimeMillis() - curr);
        System.out.println("time taken: "+timeTaken+"ms");
        System.gc();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}
