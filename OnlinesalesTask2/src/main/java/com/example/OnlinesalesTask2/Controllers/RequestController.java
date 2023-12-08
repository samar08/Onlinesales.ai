package com.example.OnlinesalesTask2.Controllers;


import com.example.OnlinesalesTask2.Model.OutputObject;
import com.example.OnlinesalesTask2.Services.RequestService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    @Value("${datasize.multiplier}")
    private int dataSizeMultiplier;
    @Value("${webapi.limitpersecond}")
    private int publicApiLimitPerSecond;
    private String[] data;
    private int dataSize;
    private ArrayList<String> encodedData;
    private ArrayList<String> expressions;
    private int expressionsLength;
    private HashMap<String, String> encodedToData;


    public RequestController(RequestService requestService, @Value("${datasize.multiplier}") int dataSizeMultiplier){
        int i=0,j=0;
        String encodedExpression;

        this.requestService=requestService;
        /*
         * Fixed data which is used for creating 500 size dataset by concatenating to a list
         */
        this.data=new String[]{"1+1","2*2+4*5+5*6-9*10+11*11","sqrt(3*3+5*5)","sqrt(121)","21^5","100^2 + 21^2 + 24^4","1/0","sqrt(1*1+2*2+3^4+5^2+81^4)","4*6+9+10*7", "4^3+11^5"};
        this.expressions=new ArrayList<>();
        this.encodedData=new ArrayList<>();
        this.dataSize=data.length;
        this.encodedToData = new HashMap<>();
        for(i=0;i<dataSize;i++) {
            /*
             * encoding every expression to pass to the API
             */
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

            for (int iter = 0; iter < iterations; iter++) {
                executorService = Executors.newFixedThreadPool(publicApiLimitPerSecond);
              responses=new ArrayList<>();
                for (j = 0; j < publicApiLimitPerSecond; j++) {

                    String encodedInput = expressions.get(iter*publicApiLimitPerSecond +j);
                    Future<ResponseEntity<String>> apiFutureResponse = requestService.getExpressionResult(encodedInput,executorService,encodedToData);
                    OutputObject outputObject=new OutputObject( encodedToData.get(encodedInput), apiFutureResponse);
                    responses.add(outputObject);
                }

                executorService.shutdown();
                executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
                for(i=0;i<responses.size();i++){

                    if(responses.get(i).getApiFutureResponse().get().getStatusCode()== HttpStatus.BAD_REQUEST){

                        System.out.println("Invalid expression "+ responses.get(i).getExpression());
                        System.out.println("Error: "+responses.get(i).getApiFutureResponse().get().getBody());
                    }
                    else if(responses.get(i).getApiFutureResponse().get().getStatusCode()==HttpStatus.OK){
                        System.out.println( responses.get(i).getExpression() + " => "+ responses.get(i).getApiFutureResponse().get().getBody());
                    }

                }

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
