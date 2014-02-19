/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package viability;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import org.joda.time.DateTime;

/**
 * ADT for signal object
 * @author gregory&alessio&riccardo
 */
public class Signal implements Serializable {
    
    public Long id;
    public String name;
    public String surname;
    public int age;
    public String district;
    public String priority;
    public String type;
    public DateTime date;
   
    public enum Priority{
        High,low,auto
    };
    
    public enum Type{
        insert,modify,remove,statistics
    };

    /**
     * Standard constructor
     * @param id
     * @param name user name
     * @param surname user surname
     * @param age user age
     * @param district user district
     * @param type user signal request type
     * @param priority user signal request priority
     * @param date user signal request timestamp
     */
    public Signal(Long id, String name, String surname, int age, String district, Enum type, Enum priority,DateTime date) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.district = district;
        this.priority = priority.toString();
        this.type = type.toString();
        this.date = date;
        
    }

    /**
     * Get user signal request timestamp
     * @return 
     */
    public DateTime getDate() {
        return date;
    }

    /**
     * Set user signal request timestamp
     * @param date 
     */
    public void setDate(DateTime date) {
        this.date = date;
    }


    /**
     * Get signal ID
     * @return 
     */
    public Long getId() {
        return id;
    }

    /**
     * Set signal ID
     * @param id 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get user name
     * @return 
     */
    public String getName() {
        return name;
    }

    /**
     * Set user name
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get user surname
     * @return 
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Set user surname
     * @param surname 
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Get user age
     * @return 
     */
    public int getAge() {
        return age;
    }

    /**
     * Set user age
     * @param age 
     */
    public void setAge(int age) {
        this.age = age;
    }


    /**
     * Get user signal request priority
     * @return 
     */
    public String getPriority() {
        return priority;
    }

    /**
     * Set user signal request priority
     * @param priority 
     */
    public void setPriority(Priority priority) {
        this.priority = priority.toString();
    }

    /**
     * Get user signal request type
     * @return 
     */
    public String getType() {
        return type;
    }

    /**
     * Set user signal request type
     * @param type 
     */
    public void setType(Type type) {
        this.type = type.toString();
    }

    /**
     * Get user signal request priority
     * @return 
     */
    public String getDistrict() {
        return district;
    }
    
    /**
     * Set user district
     * @param quartiere 
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * toString method
     * @return 
     */
    @Override
    public String toString() {
        return "Signal{" + "id=" + id + ", name=" + name + ", surname=" + surname + ", age=" + age + ", district=" + district + ", priority=" + priority + ", type=" + type + ", timestamp=" + date + '}';
    }

    
    
}
