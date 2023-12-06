package com.example.OnlinesalesTask2.Controllers;

import com.example.OnlinesalesTask2.Services.RequestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/")
public class RequestController {
    private RequestService requestService;
    private String[] data;
    private ArrayList<String> expressions;
    private int expressionsLength;
    public RequestController(RequestService requestService){
        int i=0,dataSize=0,j=0;
        this.requestService=requestService;
        this.data=new String[]{"1+1","2*2+4*5+5*6-9*10+11*11","sqrt(3*3+5*5)","sqrt(1*1+2*2+3^4+5^2+81^4)","21^5","100^2 + 21^2 + 24^4","sqrt(1*1+2*2+3^4+5^2+81^4)","sqrt(1*1+2*2+3^4+5^2+81^4)","sqrt(1*1+2*2+3^4+5^2+81^4)", "sqrt(1*1+2*2+3^4+5^2+81^4)"};
        this.expressions=new ArrayList<>();
        dataSize=data.length;

        for(i=0;i<50;i++){
            for(j=0;j<dataSize;j++){
                expressions.add(data[j]);
            }
        }
        this.expressionsLength=expressions.size();

    }
    @GetMapping("/execute")
    public void getExpressionsResult(){
        int i;
        for(i=0;i<expressionsLength;i++){
            System.out.println(requestService.getExpressionResult(expressions.get(i)));
        }

    }

}
