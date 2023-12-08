package org.example;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    Main task1;
    MainTest(){
        this.task1=new Main();
    }
    @Test
    public void testcheckProbabilitySumOfInput(){
        HashMap<Integer, Double> inputMap=new HashMap<>();
        Double percentageTotal=100.0;
        inputMap.put(1,10.0);
        inputMap.put(2,15.0);
        inputMap.put(3,40.0);
        inputMap.put(4,5.0);
        inputMap.put(5,30.0);
        inputMap.put(6,0.0);
        assertEquals(task1.checkProbabilitySumOfInput(inputMap,percentageTotal), true);
        HashMap<Integer, Double> inputMap2=new HashMap<>();
        Double percentageTotal2=150.0;
        inputMap2.put(1,10.0);
        inputMap2.put(2,15.0);
        inputMap2.put(3,40.0);
        inputMap2.put(4,5.0);
        inputMap2.put(5,30.0);
        inputMap2.put(6,0.0);
        assertEquals(task1.checkProbabilitySumOfInput(inputMap2,percentageTotal2),false);
        HashMap<Integer, Double> inputMap3=new HashMap<>();
        Double percentageTotal3=100.0;
        inputMap3.put(1,10.0);
        inputMap3.put(2,15.0);
        inputMap3.put(3,40.0);
        inputMap3.put(4,60.0);
        inputMap3.put(5,30.0);
        inputMap3.put(6,0.0);
        assertEquals(task1.checkProbabilitySumOfInput(inputMap3,percentageTotal3),false);

    }
    @Test
    public void testResultMap(){
        int noOfOccurrences=100;
        HashMap<Integer, Double> inputMap=new HashMap<>();
        Double percentageTotal=100.0;
        inputMap.put(1,10.0);
        inputMap.put(2,15.0);
        inputMap.put(3,40.0);
        inputMap.put(4,5.0);
        inputMap.put(5,30.0);
        inputMap.put(6,0.0);
        HashMap<Integer,Integer> resultMap=task1.getResultMap(inputMap,noOfOccurrences,percentageTotal);
        Set<Integer> keySet=resultMap.keySet();
        Iterator it = keySet.iterator();
        int sum=0;

        while(it.hasNext()){
            Integer key=(Integer)it.next();
            sum+=resultMap.get(key);
        }
        assertEquals(noOfOccurrences,sum);

    }



}
