/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package viability;

/**
 * ADT for the global messsage form BMPN diagram. This object is the scope and contains all objects 
 * required by system.
 * @author gregory&alessio&riccardo
 */
public class JBPMNMessage {
    //Signal instance
    Signal signal;
    //Database instance
    Database database;
    //LogFile instance
    LogFile logfile;
    //this parameter show if the operation in signal is possible or not
    public int requestPossible;


    /**
     * Default constructor
     * @param signal Signal instance
     * @param database Database instance
     * @param logfile LogFile instance
     */
    public JBPMNMessage(Signal signal, Database database, LogFile logfile) {
        this.signal = signal;
        this.database = database;
        this.logfile = logfile;
    }

    /**
     * Get requestPossible
     * @return requestPossible
     */
    public int getRequestPossible() {
        return requestPossible;
    }

    /**
     * Set requestPossible
     * @param requestPossible 
     */
    public void setRequestPossible(int requestPossible) {
        this.requestPossible = requestPossible;
    }
    
    /**
     * Get LogFile
     * @return LogFile
     */
    public LogFile getLogfile() {
        return logfile;
    }

    /**
     * Set LogFile
     * @param logfile 
     */
    public void setLogfile(LogFile logfile) {
        this.logfile = logfile;
    }

    /**
     * Get Signal
     * @return signal
     */
    public Signal getSignal() {
        return signal;
    }

    /**
     * Set Signal
     * @param signal 
     */
    public void setSignal(Signal signal) {
        this.signal = signal;
    }

    /**
     * Get Database
     * @return database
     */
    public Database getDatabase() {
        return database;
    }

    /**
     * Set Database
     * @param database 
     */
    public void setDatabase(Database database) {
        this.database = database;
    }

    /**
     * toString method
     * @return 
     */
    @Override
    public String toString() {
        return "JBPMNMessage{" + "signal=" + signal + ", database=" + database + ", logfile=" + logfile + '}';
    }

    
    
 
    
}
