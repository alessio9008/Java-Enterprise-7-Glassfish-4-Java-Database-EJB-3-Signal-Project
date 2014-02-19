/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handler;

import dbmanager.SegnalazioneFacadeLocal;
import dbmanager.StatisticsFacadeLocal;
import entity.Segnalazione;
import entity.Statistics;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Alessio
 */
@Stateless
public class handlerBeans implements handlerBeansLocal {
    @EJB
    private StatisticsFacadeLocal statisticsFacade;

    @EJB
    private SegnalazioneFacadeLocal segnalazioneFacade;
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private Segnalazione current;
    private double averageWeek;
    private double averageMonth;
    private double averageMonthHighPriority;
    private double averageAgeUsers;
    
    private PrintWriter out;
    
    @PostConstruct
    private void loadBeans(){
        System.err.println("load Beans");
        initFile();
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
    
    private void average(){
        averageWeek();
        averageMonth();
        averageMonthHighPriority();
        averageAgeUsers();
        System.out.println("\nMedia Settimanale: "+averageWeek+
                "\nMedia Mensile: "+averageMonth+
                "\nMedia Mensile Alta Priorita'"+averageMonthHighPriority+
                "\nMedia Eta' Utenti: "+averageAgeUsers);
        Statistics s=new Statistics(current.getIddistrict(), averageWeek, averageMonth, averageMonthHighPriority, averageAgeUsers);
        s.setTmstampedit(new Date());
        if(statisticsFacade.contains(s)){
            statisticsFacade.edit(s);
        }
        else{
            statisticsFacade.create(s);
        }
    }

    private void averageAgeUsers() {
        if (current != null) {
            long counter = 0;
            long toteta = 0;
            List<Segnalazione> list = segnalazioneFacade.findAll();
            for (Segnalazione s : list) {
                if (s.getIddistrict() == current.getIddistrict()) {
                    counter++;
                    toteta += s.getAge();
                }
            }
            if(counter!=0)averageAgeUsers = ((double) toteta) / counter;
        }
    }

    private void averageMonthHighPriority() {
        if (current != null) {
            long counter = 0;
            long minDate = System.currentTimeMillis();
            List<Segnalazione> list = segnalazioneFacade.findAll();
            for (Segnalazione s : list) {
                if (s.getIddistrict() == current.getIddistrict() && s.getPriority() >= 2) {
                    counter++;
                }
                if (minDate > s.getTmstampedit().getTime()) {
                    minDate = s.getTmstampedit().getTime();
                }
            }
            long now = System.currentTimeMillis();
            double numMonth = (now - minDate) / (30 * 24 * 60 * 60 * 1000);
            if(numMonth!=0)averageMonth = ((double) counter) / numMonth;
        }
    }

    private void averageMonth() {
        if (current != null) {
            long counter = 0;
            long minDate = System.currentTimeMillis();
            List<Segnalazione> list = segnalazioneFacade.findAll();
            for (Segnalazione s : list) {
                if (s.getIddistrict() == current.getIddistrict()) {
                    counter++;
                }
                if (minDate > s.getTmstampedit().getTime()) {
                    minDate = s.getTmstampedit().getTime();
                }
            }
            long now = System.currentTimeMillis();
            double numMonth = (now - minDate) / (30 * 24 * 60 * 60 * 1000);
            if(numMonth!=0)averageMonth = ((double) counter) / numMonth;
        }
    }

    private void averageWeek() {
        if (current != null) {
            long counter = 0;
            long minDate = System.currentTimeMillis();
            List<Segnalazione> list = segnalazioneFacade.findAll();
            for (Segnalazione s : list) {
                if (s.getIddistrict() == current.getIddistrict()) {
                    counter++;
                }
                if (minDate > s.getTmstampedit().getTime()) {
                    minDate = s.getTmstampedit().getTime();
                }
            }
            long now = System.currentTimeMillis();
            double numweek = (now - minDate) / (7 * 24 * 60 * 60 * 1000);
            if(numweek!=0)averageWeek = ((double) counter) / numweek;
        }
    }

    @Override
    public void create(Segnalazione entity) {
        current = entity;
        segnalazioneFacade.create(entity);
        average();
        if(out!=null) out.println("Oggetto creato: " + entity.toString());
    }

    @Override
    public void edit(Segnalazione entity) {
        current = entity;
        if (segnalazioneFacade.contains(entity)) {
            segnalazioneFacade.edit(entity);
            if(out!=null) out.println("Oggetto modificato: " + entity.toString());
        } else {
            segnalazioneFacade.create(entity);
            if(out!=null) out.println("Oggetto da modificare non trovato, oggetto creato: " + entity.toString());
        }
        average();
    }

    @Override
    public void remove(Segnalazione entity) {
        current = entity;
        if (segnalazioneFacade.contains(entity)) {
            segnalazioneFacade.remove(entity);
            if(out!=null) out.println("Oggetto rimosso: " + entity.toString());
        } else {
            if(out!=null) out.println("Oggetto da rimuovere non trovato: " + entity.toString());
        }
        average();
    }
}
