/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package viability;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import org.joda.time.DateTime;

/**
 * ADT for Database object
 * @author gregory&alessio&riccardo
 */
public class Database implements Serializable{
    
    HashMap<Long,Signal> database;
    private Long id;
    //Timestamp that represents creation instant for DB
    DateTime datecreationDB;
    /**
     * Default Constructor
     */
    public Database(){
        database= new HashMap<Long, Signal>();
        id=0l;
        datecreationDB= new DateTime();
    }
    
    
    /**
     * Insert a signal on DB
     * @param signal signal to insert
     * @return 
     */
    public boolean insert(Signal signal){
        if(database.containsKey(signal.getId())){
            return false;
        }else{
            signal.setId(id);
            database.put(id,signal);
            return true;
        }
    }

    /**
     * Get Timestamp for creation DB
     * @return 
     */
    public DateTime getDatecreationDB() {
        return datecreationDB;
    }


    /**
     * Get global ID for DB
     * @return 
     */
    public Long getId() {
        return id;
    }

    /**
     * Set global ID for DB
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Increment global DB.
     */
    public void incrementId(){
        
        this.id++;
        System.out.println("increment database id"+id);
    }
    
    /**
     * Remove Signal from DB
     * @param id id for entry to remove
     * @return 
     */
    public boolean remove(Long id){
        if(database.containsKey(id)){
            database.remove(id);
            return true;
        }else{
            return false;
        }
        
    }
    
    /**
     * Reinsert a signal given an ID 
     * @param id id signal to reinsert
     * @param signal signal to reinsert
     * @return 
     */
    public boolean reinsert(Long id,Signal signal){
        System.out.println("id"+id);
        if(!database.containsKey(id)){
            database.put(id,signal);
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * Print database method
     * @return 
     */
    public String printDB(){
        String content="";
        Collection values= database.values();
        Iterator iterator =values.iterator();
        while(iterator.hasNext()){
            content=content+iterator.next().toString()+"\n";
        }
        return content;
    }

    /**
     * to String method()
     * @return 
     */
    @Override
    public String toString() {
        return printDB();
    }
    
    
    
     
}
