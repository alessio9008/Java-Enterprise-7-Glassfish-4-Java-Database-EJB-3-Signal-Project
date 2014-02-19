/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package viability;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import org.joda.time.DateTime;
import org.joda.time.Seconds;
import org.joda.time.Seconds;

/**
 * ADT for LogFile object
 * @author gregory&alessio&riccardo
 */
public class LogFile implements Serializable {
    
    //list of signal 
    public LinkedList<Signal> listSignal;
    
    /**
     * Default constructor
     */
    public LogFile(){
        listSignal= new LinkedList<Signal>();
    }
    
    /**
     * Insert a signal on list
     * @param signal 
     */
    public void updateLogFile(Signal signal){
        listSignal.add(signal);
    }
    
    /**
     * get User's Age Average from signal on list
     * @return 
     */
    public double getAgeAverage(){
        int count=0;
        int totage=0;
        Signal tempsignal;
        Iterator iterator = listSignal.iterator();
        while(iterator.hasNext()){
            tempsignal= (Signal)iterator.next();
            totage= totage+tempsignal.getAge();;
            count++;
        }
        return totage/count;
    }
    
    /**
     * Get all signal in the last two seconds on list from statistics request DateTime
     * @param daterequest statistics request DateTime
     * @return 
     */
    public void averageSignalWeekForDistrict(DateTime daterequest,DateTime dateTimeCreationDB){
        //WE ASSUME A WEEK = 15 MINUTES FOR DEMO
        LinkedList<String> listdistricts = getListDistrict();
        Iterator iteratorDistricts = listdistricts.iterator();
        //for each District on List
        while(iteratorDistricts.hasNext()){
            double average=0;
            int countDistrict=0;
            String tempdistrict= (String)iteratorDistricts.next();
            Iterator iterator= listSignal.iterator();
            while(iterator.hasNext()){
                Signal temp= (Signal)iterator.next();
                if(temp.getDistrict().equalsIgnoreCase(tempdistrict)){
                    countDistrict++;
                }
            }
            //get weeks between DB creation date and statistics request date
            int seconds=Seconds.secondsBetween(dateTimeCreationDB,daterequest).getSeconds();
            double countWeeks= seconds/15;
            //calculate average for this District
            average= countDistrict/countWeeks;
            System.out.println("Average Signal Week for District "+tempdistrict+" is "+ average);
        }
    }
    
    /**
     * Get all signal in the last 30 seconds on list from statistics request DateTime
     * @param daterequest statistics request DateTime
     * @return 
     */
    public void averageSignalMonthForDistrict(DateTime daterequest,DateTime dateTimeCreationDB){
         //WE ASSUME A MONTH = 120 SECONDS FOR DEMO
        LinkedList<String> listdistricts = getListDistrict();
        Iterator iteratorDistricts = listdistricts.iterator();
        //for each District on List
        while(iteratorDistricts.hasNext()){
            double average=0;
            int countDistrict=0;
            String tempdistrict= (String)iteratorDistricts.next();
            Iterator iterator= listSignal.iterator();
            while(iterator.hasNext()){
                Signal temp= (Signal)iterator.next();
                if(temp.getDistrict().equalsIgnoreCase(tempdistrict)){
                    countDistrict++;
                }
            }
            //get weeks between DB creation date and statistics request date
            int seconds=Seconds.secondsBetween(dateTimeCreationDB,daterequest).getSeconds();
            double countWeeks= seconds/120;
            //calculate average for this District
            average= countDistrict/countWeeks;
            System.out.println("Average Signal Month for District "+tempdistrict+" is "+ average);
        }
    }
    
    /**
     * Get all signal in the last 30 seconds with High Priority on list from statistics request DateTime
     * @param daterequest statistics request DateTime
     * @return 
     */
    public void averageSignalMonthForDistrictHighPriority(DateTime daterequest,DateTime dateTimeCreationDB){
         //WE ASSUME A MONTH = 120 SECONDS FOR DEMO
        LinkedList<String> listdistricts = getListDistrict();
        Iterator iteratorDistricts = listdistricts.iterator();
        //for each District on List
        while(iteratorDistricts.hasNext()){
            double average=0;
            int countDistrict=0;
            String tempdistrict= (String)iteratorDistricts.next();
            Iterator iterator= listSignal.iterator();
            while(iterator.hasNext()){
                Signal temp= (Signal)iterator.next();
                if(temp.getDistrict().equalsIgnoreCase(tempdistrict) && temp.getPriority().equalsIgnoreCase("high")){
                    countDistrict++;
                }
            }
            //get weeks between DB creation date and statistics request date
            int seconds=Seconds.secondsBetween(dateTimeCreationDB,daterequest).getSeconds();
            double countWeeks= seconds/120;
            //calculate average for this District
            average= countDistrict/countWeeks;
            System.out.println("Average Signal Month for District "+tempdistrict+" is "+ average);
        }
    }
    
    /**
     * Method to print Log 
     * @return string that represents list
     */
    public String printLogFile(){
        String content="";
        Iterator iterator= listSignal.iterator();
        for(int i=0;i<listSignal.size();i++){
            
            content=content+"ID"+i+": At "+listSignal.get(i).getDate()+" "+listSignal.get(i).getName()+" "+listSignal.get(i).getSurname()+" has done an "+listSignal.get(i).getType()+" operation\n";
            
        }
        return content;
    }
    
    public LinkedList<String> getListDistrict(){
        Iterator iterator= listSignal.iterator();
        LinkedList<String> listDistricts= new LinkedList<String>();
        while(iterator.hasNext()){
            Signal temp= (Signal)iterator.next();
            String tempDistrict= temp.getDistrict();
            if(!listDistricts.contains(tempDistrict)){
                listDistricts.add(tempDistrict);
            }
        }
        return listDistricts;
    }

    /**
     * toString method
     * @return 
     */
    @Override
    public String toString() {
        return printLogFile();
    }
    
    
    
}
