/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;

/**
 *
 * @author Alessio
 */
@Startup
@Singleton
public class LoggerTestBeans implements LoggerTestBeansLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Resource
    private TimerService tservice;

    private PrintWriter out;
    private static ConcurrentLinkedQueue<String> queue;

    public void add(Object txt) {
        queue.add(txt.toString());
    }

    @PostConstruct
    public void init() {
        initFile();
        if (queue == null) {
            queue = new ConcurrentLinkedQueue<String>();
        }
        tservice.createIntervalTimer(10000, 10000, new TimerConfig());
    }

    @Timeout
    public void timeout() {
        System.out.println("TimeOut");
        if(out!=null){
            while(!queue.isEmpty()){
                String txt=queue.poll();
                out.println(txt);
                System.out.println(txt);
            }
        }
        else{
            System.out.println("Non riesco a scrivere sul file");
        }
    }

    private void initFile() {
        if (out == null) {
            try {
                File fp = new File("log.txt");
                if (!fp.exists() || !fp.isFile()) {
                    fp.createNewFile();
                    System.out.println("File Creato");
                }
                else{
                    System.out.println("File gia' esistente");
                }
                out = new PrintWriter(new FileWriter(fp,true), true);
                System.out.println("Writer creato");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        else{
            System.out.println("Writer gia' presente");
        }
    }
}
