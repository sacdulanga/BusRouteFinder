package com.example.busroutefinder;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;

public class RouteFinder {
        private BusHalt from;
        private BusHalt to;
        private String test;
        private boolean flag=false;
        private String []fromlist;
        private String []tolist;
        private ArrayList<String> ar = new ArrayList<String>();
        private ArrayList<BusHalt> temp = new ArrayList<BusHalt>();
        private ArrayList<BusHalt> cor = new ArrayList<BusHalt>();
        private ArrayList<BusHalt> fchop = new ArrayList<BusHalt>();
        private ArrayList<SolutionRoute> results = new ArrayList<SolutionRoute>();
        
        
    
    public void step_01( Place_to_Place_Activity dd,String fromholt,String toholt){        
       
        from=dd.getBusHolt(fromholt.toLowerCase());
        to=dd.getBusHolt(toholt.toLowerCase());
        fromlist=from.getRoteList();
        tolist=to.getRoteList();
        
        SolutionRoute solutionOnetoOne=new SolutionRoute();
        for(int i=0;i<fromlist.length;i++){
            for(int j=0;j<tolist.length;j++){
                if(fromlist[i].equals(tolist[j])){
                    ar.add(tolist[j]);
                     DistanceFinder d=new DistanceFinder();
                     double x=d.distance(from, to);            
                     solutionOnetoOne.setRoutBiginingFrom(tolist[j]);
                    flag=true;
                }
            }
        }
        
       
        // give the bus rote if From bus holt to To bus holt to can go from one bus
        StringBuffer buffer=new StringBuffer();
        if(flag){
        	buffer.append("Get On any Bus from "+from.getName()+" which has any of the following routes numbers "+"\n");            
            for(int x=0;x<solutionOnetoOne.getRoutBiginingFrom().size();x++){
            	buffer.append(solutionOnetoOne.getRoutBiginingFrom().get(x)+" ");
            }
            buffer.append("Get off the bus from "+to.getName()+"\n");
            if(buffer.length()==0){
               	buffer.append("Empty result");
               	dd.setResult(buffer.toString());
               	dd.setAllResult(buffer.toString());
           }
           else{
        	   	dd.setResult(buffer.toString());
        	   	dd.setAllResult(buffer.toString());
           }
        }
        
        
        if(!flag){
            cor=dd.getChangeOverPlaces();
          //Get the changeOver Bus Holt which have equal bus rote to from bus holt route  
            for(int p=0;p<cor.size();p++){
                boolean sflag=false;
                BusHalt choverplace=cor.get(p);
                String []chOlist=choverplace.getRoteList();
                
                for(int q=0;(q<fromlist.length&&!sflag);q++){
                    for(int z=0;z<chOlist.length;z++){
                        if(fromlist[q].equals(chOlist[z])&&!from.getName().equals(choverplace.getName())){
                            temp.add(cor.get(p));
                            sflag=true;
                            break;                          
                        }
                    }            
                }
            }
            
          //get Change over Bus Holt which has same bus rote both from bus holt and To bus holt
            for(int p=0;p<temp.size();p++){
                 boolean tflag=false;
                BusHalt choverplace=temp.get(p);
                String []chOlist=choverplace.getRoteList();
                
                for(int q=0;(q<tolist.length&&!tflag);q++){
                    for(int z=0;z<chOlist.length;z++){
                        if(tolist[q].equals(chOlist[z])){
                            DistanceFinder d=new DistanceFinder();
                            double x=d.distance(from, temp.get(p))+d.distance(to, temp.get(p));
                            SolutionRoute solution=new SolutionRoute();
                            solution.setName(temp.get(p).getName());
                            solution.setDistance(x);
                            tryone(temp.get(p),solution);
                            results.add(solution);
                            tflag=true;
                            break;                           
                        }
                    }            
                } 
                
            }
            printBestSoulution(results,dd);
            printAllSolution(results,dd);
        }

    }
    
    
    public void tryone(BusHalt b,SolutionRoute s){ 
        
       String []chOlist=b.getRoteList();
       
        //Get the Bus Routes which going throug the frome Bus Holt and Change Over Bus holt 
                               
                for(int q=0;q<fromlist.length;q++){
                    for(int z=0;z<chOlist.length;z++){
                        if(fromlist[q].equals(chOlist[z])){
                           s.setRoutBiginingFrom(chOlist[z]);
                                    break;            
                        }
                    }            
                }
                
                
        //Get the Bus Routes which going throug the To Bus Holt and Change Over Bus holt         
                for(int q=0;q<tolist.length;q++){
                    for(int z=0;z<chOlist.length;z++){
                        if(tolist[q].equals(chOlist[z])){
                            s.setRoutBiginingChange(chOlist[z]);
                            break;            
                        }
                    }            
                }
    } 
    
    public void printBestSoulution(ArrayList<SolutionRoute> rslt,Place_to_Place_Activity dd){
        
        SolutionRoute temp=new SolutionRoute();
        double tempD;
        double p=rslt.size();
        
        tempD=rslt.get(0).getDistance();
        temp=rslt.get(0);
        for(int i=1;i<rslt.size();i++){    
            if(tempD>rslt.get(i).getDistance()){
                temp=rslt.get(i);
                tempD=rslt.get(i).getDistance();
            }
        }
        
       
        
        //Print the Frome Bus holt to Change over to bus route  
        StringBuffer buffer=new StringBuffer();
        buffer.append("Get On any Bus from "+from.getName()+" which has any of the following routes numbers "+"\n");
               for(int x=0;x<temp.getRoutBiginingFrom().size();x++){
            	   buffer.append(temp.getRoutBiginingFrom().get(x)+" ");
               }
               buffer.append("\n"+"Get off the bus from "+temp.getName()+" Next you should get the bus which has any of the following routes ");
               
               
          //Print the Change Over Bus Holt to To bus holt to Rote  
               for(int x=0;x<temp.getRoutBiginingChange().size();x++){
            	   buffer.append(temp.getRoutBiginingChange().get(x)+" ");
               }
               buffer.append("Get off from "+to.getName()+"\n");
               if(buffer.length()==0){
	               	buffer.append("Empty result");
	               	dd.setResult(buffer.toString());
               }
               else{
            	   	dd.setResult(buffer.toString());
               }
               
        test=temp.getName();
    }
    
   public String getbest(){
	   return test;
   }
    
    public void printAllSolution(ArrayList<SolutionRoute> rslt,Place_to_Place_Activity dd){
    	StringBuffer buffer=new StringBuffer();
        for(int i=0;i<rslt.size();i++){
            SolutionRoute s=new SolutionRoute();
            s=rslt.get(i);
            
        //Print the Frome Bus holt to Change over to bus route  
            
            buffer.append("Get On any Bus from "+from.getName()+"which has any of the following routes numbers ");
               for(int x=0;x<s.getRoutBiginingFrom().size();x++){
            	   buffer.append(s.getRoutBiginingFrom().get(x)+" ");
               }
               buffer.append("\n"+"Get off the bus from "+s.getName()+" Next you should get the bus which has any of the following routes ");
               
               
          //Print the Change Over Bus Holt to To bus holt to Rote  
               for(int x=0;x<s.getRoutBiginingChange().size();x++){
            	   buffer.append(s.getRoutBiginingChange().get(x)+" ");
               }
               buffer.append("Get off from "+to.getName()+"\n"+"\n");
        }
        if(buffer.length()==0){
        	buffer.append("Empty result");
        	dd.setAllResult(buffer.toString());
        }
        else{
        	dd.setAllResult(buffer.toString());
        }
        
    }
    
}

