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
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.impl.ClassPathResource;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.persistence.jpa.JPAKnowledgeService;
import org.drools.runtime.Environment;
import org.drools.runtime.EnvironmentName;
import org.drools.runtime.StatefulKnowledgeSession;
import org.jbpm.bpmn2.handler.ServiceTaskHandler;
import org.joda.time.DateTime;
import viability.Signal;

/**
 * Project's Main class.
 * @author gregory&alessio&riccardo
 */
public class SignalManager {
    
    static String filePath = "diagram/ViabilityBPMN.bpmn";
    static String processId = "ViabilityBPMN_PID";
   
    private KnowledgeBase createKnowledgeBase(){
       
       /* The KnowledgeBuilder is responsible for taking source files, such as a .drl file, a .bpmn2 file or an .xls file, and turning them into a KnowledgePackage of rule and process definitions which a KnowledgeBase can consume. It uses the ResourceType enum to tell it the type of the resource it is being asked to build.

The ResourceFactory provides capabilities to load Resources from a number of sources; such as Reader, ClassPath, URL, File, ByteArray. Binaries, such as XLS decision tables, should not use a Reader based Resource handler, which is only suitable for text based resources.
       */
       
       KnowledgeBuilder kbuilder = KnowledgeBuilderFactory.newKnowledgeBuilder();
       kbuilder.add(new ClassPathResource(filePath), ResourceType.BPMN2);
       
       /* The KnowledgeBase is a repository of all the application's knowledge definitions. It will contain rules, processes, functions, type models. The KnowledgeBase itself does not contain runtime data, instead sessions are created from the KnowledgeBase in which data can be inserted and process instances started. Creating the KnowledgeBase can be heavy, where as session creation is very light, so it is recommended that KnowledgeBases be cached where possible to allow for repeated session creation. The KnowledgeAgent can be used for this purpose. The KnowledgeBase is created from the KnowledgeBaseFactory, and a KnowledgeBaseConfiguration can be used.

*/
       KnowledgeBase kbase = KnowledgeBaseFactory.newKnowledgeBase();
       kbase.addKnowledgePackages(kbuilder.getKnowledgePackages());
       
       if(kbuilder.hasErrors()){
           StringBuilder errorMessage = new StringBuilder();
           for(KnowledgeBuilderError error : kbuilder.getErrors()){
               errorMessage.append(error.getMessage());
               errorMessage.append(System.getProperty("line.separator"));
               
           }
           System.out.println("errorMessage "  + errorMessage);
           
       }
       return kbase;
   } 
   
   public void testServiceTask () throws Exception{
       
       KnowledgeBase kbase= createKnowledgeBase();
       StatefulKnowledgeSession session = kbase.newStatefulKnowledgeSession();
       session.getWorkItemManager().registerWorkItemHandler("Service Task", new ServiceTaskHandler());
       KnowledgeRuntimeLoggerFactory.newConsoleLogger(session);
       
       //Load Database Procedure
       Database database = loadDatabaseFromFile();
       if(database==null){
           database= new Database();
           System.out.println("Database Created");
       }
       //Load LogFile Procedure
       LogFile logfile= loadLogFileFromFile();
       if(logfile==null){
           logfile= new LogFile();
           System.out.println("LogFile Created");
       }
       //Create Signal Procedure
       DateTime date= new DateTime();
       Signal signal = new Signal(0l,"a", "a", 1, "a",Signal.Type.insert,Signal.Priority.auto,date);
       System.out.println("Signal Created");
       //Create JBPMN instance
       JBPMNMessage message= new JBPMNMessage(signal,database,logfile);
       Map<String , Object> params = new HashMap<String, Object>();
       params.put("message", message);
       System.out.println("Session start");
       session.startProcess(processId, params);
       
   }
   
   /**
    * Main method
    * @param args
    * @throws Exception 
    */
   public static void main(String args[]) throws Exception{
       SignalManager call = new SignalManager();
       System.out.println("SignalManager is starting");
       try{
           call.testServiceTask();
       }catch (Exception ex){
           Logger.getLogger(SignalManager.class.getName()).log(Priority.FATAL, null, ex);
       }
   }
           
    
    /**
     * Load Database from file
     * @return 
     */
    public Database loadDatabaseFromFile(){
        ObjectInputStream ois = null;
        try {
            File file = new File("database");
            if (file.exists()) {
                ois = new ObjectInputStream(new FileInputStream(file));
                Object objectLoaded = ois.readObject();
                ois.close();
                System.out.println("Database Loaded");  
                return (Database)objectLoaded;
            } else {
                System.out.println("File not found for Database- Can't load");
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    
    }
    
     /**
      * Load LogFile from file
      * @return 
      */
    public LogFile loadLogFileFromFile(){
        ObjectInputStream ois = null;
        try {
            File file = new File("logfile");
            if (file.exists()) {
                ois = new ObjectInputStream(new FileInputStream(file));
                Object objectLoaded = ois.readObject();
                ois.close();
                System.out.println("LogFile Loaded");  
                return (LogFile)objectLoaded;
            } else {
                System.out.println("File not found for Log File- Can't load");
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    
    }
}

