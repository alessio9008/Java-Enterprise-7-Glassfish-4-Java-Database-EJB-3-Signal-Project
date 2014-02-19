/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package viability;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import org.joda.time.DateTime;

/**
 * SignalService class used by ViabilityBPMN.bpmn services
 * @author gregory&alessio&ricky
 */
public class SignalService {

    /**
     * Method that simulate User Request at Web Service Application Client ( local machine )
     * @param message
     * @return 
     */
    public JBPMNMessage sendSignal(JBPMNMessage message){
        if(message==null){
                    System.out.println("null");
        }
        Signal signal= message.getSignal();
        /*-------------------------------DEMO USER INPUT PARAMETERS------------------------------------*/
        //Change this parameters to simulate input from user
        Long id=1l; //id is requiered only for modify or for remove request
        String name="Dario";
        String surname="Fo";
        int age=41;
        String district="Catania";
        signal.setType(Signal.Type.statistics);
        signal.setPriority(Signal.Priority.High);
        /*---------------------------------------------------------------------------------------------*/
        signal.setName(name);
        signal.setSurname(surname);
        signal.setAge(age);
        signal.setDistrict(district);
        if(signal.getType().equalsIgnoreCase("remove")||signal.getType().equalsIgnoreCase("modify")){
            signal.setId(id);
        }
        if(signal.getType().equalsIgnoreCase("insert")){
            message.getDatabase().incrementId();
            signal.setId(message.getDatabase().getId());
        }
        if(signal.getType().equalsIgnoreCase("statistics")){
            message.getDatabase().incrementId();
            signal.setId(message.getDatabase().getId());
        }
        
        System.out.println("User send a signal:\n"+signal);
        message.setSignal(signal);
        return message;
    }
    
    /**
     * Method that simulate insert procedure on database by Statefull Bean on Enterprise Application ( remote on AWS Amazon )
     * @param message
     * @return 
     */
    public JBPMNMessage insertSignalToDatabase(JBPMNMessage message){
        System.out.println("Insert signal to Database");
        Database database= message.getDatabase();
        boolean isInserted =database.insert(message.getSignal());
        if(isInserted){
            message.setRequestPossible(1);
        }else{
             message.setRequestPossible(0);
        }
        return message;
    }
    
    /**
     * Method that simulate remove procedure from database by Statefull Bean on Enterprise Application ( remote on AWS Amazon )
     * @param message
     * @return 
     */
     public JBPMNMessage removeSignalToDatabase(JBPMNMessage message){
        System.out.println("Remove Signal from Database with ID"+message.getSignal().getId());
        Database database= message.getDatabase();
        boolean isDeleted=database.remove(message.getSignal().getId());
        if(isDeleted){
            message.setRequestPossible(1);
        }else{
           message.setRequestPossible(0);
        }
        return message;
    }
     
    /**
     * Method that simulate reinsert procedure on database by Statefull Bean on Enterprise Application ( remote on AWS Amazon )
     * @param message
     * @return 
     */
    public JBPMNMessage reinsertSignalToDatabase(JBPMNMessage message){
        System.out.println("Reinsert Signal Procedure");
        Database database= message.getDatabase();
        boolean isReinserted=database.reinsert(message.getSignal().getId(),message.getSignal());
        if(isReinserted){
            saveDatabaseToFile(database);
            System.out.println("DBContents:\n"+message);
            //updateLogFile
            message.getLogfile().updateLogFile(message.getSignal());
            saveLogFileToFile(message.getLogfile());
        }else{
           System.out.println("ID to modify doesn't exist on database");
        }
        return message;
    }
    
    /**
     * Method that simulate database save procedure  by Statefull Bean on Enterprise Application ( remote on AWS Amazon )
     * @param message
     * @return 
     */
    public JBPMNMessage saveDatabase(JBPMNMessage message){
        saveDatabaseToFile(message.getDatabase());
        return message;
    }
    
    /**
     * Method that simulate logfile save procedure by Statefull Bean on Enterprise Application ( remote on AWS Amazon )
     * @param message
     * @return 
     */
    public JBPMNMessage saveLogFile(JBPMNMessage message){
        message.getLogfile().updateLogFile(message.getSignal());
        saveLogFileToFile(message.getLogfile());
        return message;
    }
    
    /**
     * Method that simulate average age for users on log file calculation by Statefull Bean on Enterprise Application ( remote on AWS Amazon )
     * @param message
     * @return 
     */
     public JBPMNMessage seeAverageAge(JBPMNMessage message){
        double ageaverage= message.getLogfile().getAgeAverage();
        //Print age average
        if(ageaverage==0){
            System.out.println("Database is empty. It's not possible to calculate average age");
        }else{
           System.out.println("Average age:"+ageaverage);
        }
         return message;
     }
     
     /**
      * Method that simulate average signal for week by district calculation by Statefull Bean on Enterprise Application ( remote on AWS Amazon )
      * @param message
      * @return 
      */
      public JBPMNMessage averageSignalForWeeksByDistrict(JBPMNMessage message){
        DateTime daterequest= message.getSignal().getDate();
        DateTime datecreationDB= message.getDatabase().getDatecreationDB();
        System.out.println("Average Signal for Week ( DEMO: week = 2 minutes ):\n");
        message.getLogfile().averageSignalWeekForDistrict(daterequest,datecreationDB);
        return message;
     }
      
      /**
       * Method that simulate average signal for month by district calculation by Statefull Bean on Enterprise Application ( remote on AWS Amazon )
       * @param message
       * @return 
       */
      public JBPMNMessage averageSignalForMonthsByDistrict(JBPMNMessage message){
        DateTime daterequest= message.getSignal().getDate();
        DateTime datecreationDB= message.getDatabase().getDatecreationDB();
        System.out.println("Average Signal for Month ( DEMO: week = 10 minutes )\n)");
        message.getLogfile().averageSignalMonthForDistrict(daterequest,datecreationDB);
        return message;
     }
      
      /**
       * Method that simulate average signal with high priority for months by district calculation by Statefull Bean on Enterprise Application ( remote on AWS Amazon )
       * @param message
       * @return 
       */
      public JBPMNMessage averageSignalForMonthsHighPriorityByDistrict(JBPMNMessage message){
        DateTime daterequest= message.getSignal().getDate();
        DateTime datecreationDB= message.getDatabase().getDatecreationDB();
        System.out.println("Average Signal for Month with High Priority ( DEMO: week = 10 minutes )\n");
        message.getLogfile().averageSignalMonthForDistrictHighPriority(daterequest,datecreationDB);
        
        return message;
     }
        
    /**
     * Method tha save database object on file
     * @param database
     * @return 
     */
     public static boolean saveDatabaseToFile(Database database) {
        ObjectOutputStream oos = null;
        try {
            File file = new File("database");
            if (!file.exists()) {
                file.createNewFile();
            }
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(database);
            oos.close();
            System.out.println("Database changes saved correctly on File");
            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();

        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return false;
    }

     /**
      * method that save logFile object on file
      * @param logfile
      * @return 
      */
     public static boolean saveLogFileToFile(LogFile logfile) {
        ObjectOutputStream oos = null;
        try {
            File file = new File("logfile");
            if (!file.exists()) {
                file.createNewFile();
            }
            oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(logfile);
            oos.close();
            System.out.println("LogFile changes saved correctly on File");
            return true;
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();

        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return false;
    }
   
}
