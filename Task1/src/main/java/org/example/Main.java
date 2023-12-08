package org.example;
/*
 * 
 * 
 */
import java.util.*;
import java.lang.*;
public class Main{

    public static boolean checkProbabilitySumOfInput(HashMap<Integer, Double> map,Double percentageTotal){
        boolean flag=false;
        Set<Integer> keys=map.keySet();
        Iterator it =keys.iterator();
        Double sum=0.0;
        while(it.hasNext()){
            sum+=map.get(it.next());
        }
       
        if(Double.compare(sum, percentageTotal)==0){
            
            flag=true;
        }
      
        return flag;
    }
    public static int toss(HashMap<Integer, Double> map,ArrayList<Double> newArray, int newArraySize,HashMap<Double, Integer> outputmap, Double percentageTotal){
        int i;
        double random=Math.random()*percentageTotal;
        for(i=0;i<newArraySize;i++){
            if( random<=newArray.get(i)){
                
                return outputmap.get(newArray.get(i));
            }
        }
       return 1;
    }
   public static HashMap<Integer,Integer> getResultMap(HashMap<Integer, Double> map, int occurrences,Double percentageTotal)
    {
        HashMap<Integer,Integer> resultmap=new HashMap<>();

            int i=0, newArraySize=0,size=0,total=0,outputOfOccurrence;
            Scanner sc=new Scanner(System.in);
            Set<Map.Entry<Integer,Double>> entrySet=map.entrySet();
            size=entrySet.size();
            Set<Integer> keySet=map.keySet();
            Iterator it=keySet.iterator();
            HashMap<Double,Integer> outputmap=new HashMap<>();

            double[] arr=new double[size];
            ArrayList<Double>newArray=new ArrayList<>();
            
            for(Map.Entry<Integer,Double> entry: entrySet){
                if(Double.compare(entry.getValue(), 0.0)==0){
                    resultmap.put(entry.getKey(),0);  
                }
                else{
                    newArraySize=newArray.size();
                    if(newArraySize==0){
                        newArray.add(entry.getValue());
                    }
                    else{
                        newArray.add(newArray.get(newArraySize-1)+entry.getValue());
                    }
                    outputmap.put(newArray.get(newArraySize),entry.getKey());
                }
            }
            
            newArraySize=newArray.size();


            total=occurrences;
            while(occurrences>0){
                    outputOfOccurrence=toss(map,newArray,newArraySize,outputmap,percentageTotal);
                    resultmap.put(outputOfOccurrence,resultmap.getOrDefault(outputOfOccurrence,0)+1);
                    occurrences--;
                }
            it= keySet.iterator();
            while(it.hasNext()){
                Integer key=(Integer)it.next();
                if(resultmap.containsKey(key)!=true){
                    resultmap.put(key,0);
                }
            }


        return resultmap;
        }
       public static void main(String[] args){
           /*
            * considering the input [{1,10.0},{2,20.0},{3,25.0},{4,45.0},{5,0.0}]
            * and percentageTotal=100.0
            */
           Scanner sc=new Scanner(System.in);
           HashMap<Integer,Double>map=new HashMap<>();
           map.put(1,10.0);
           map.put(2,20.0);
           map.put(3,25.0);
           map.put(4,45.0);
           map.put(5,0.0);
           Double percentageTotal=100.0;
           HashMap<Integer,Integer> resultmap=new HashMap<>();
            int occurrences=0;
           if(checkProbabilitySumOfInput(map,percentageTotal)!=true){
               System.out.println("The total sum of probabilities in input "+ map.toString()+" should be "+percentageTotal +" !!");
           }
           else{
               if(args.length>0){
                   occurrences=Integer.parseInt(args[0]);
               }
               else{
                   System.out.println("Enter number of occurrences");
                   occurrences=sc.nextInt();
               }

               resultmap=getResultMap(map,occurrences,percentageTotal);
           }
           System.out.println("Number of occurrences of each key: "+ resultmap.toString());
           System.out.println("Total tosses: "+occurrences);
       }

    } 

