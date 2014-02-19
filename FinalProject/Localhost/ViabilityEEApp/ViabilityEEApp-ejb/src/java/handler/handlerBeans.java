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
import java.util.Date;
import java.util.List;
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
    private LoggerTestBeansLocal loggerTestBeans;

    @EJB
    private SegnalazioneFacadeLocal segnalazioneFacade;
    
    

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    private Segnalazione current;
    private double averageWeek;
    private double averageMonth;
    private double averageMonthHighPriority;
    private double averageAgeUsers;
    
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
        loggerTestBeans.add("Oggetto creato: " + entity.toString());
    }

    @Override
    public void edit(Segnalazione entity) {
        current = entity;
        if (segnalazioneFacade.contains(entity)) {
            segnalazioneFacade.edit(entity);
            loggerTestBeans.add("Oggetto modificato: " + entity.toString());
        } else {
            segnalazioneFacade.create(entity);
            loggerTestBeans.add("Oggetto da modificare non trovato, oggetto creato: " + entity.toString());
        }
        average();
    }

    @Override
    public void remove(Segnalazione entity) {
        current = entity;
        if (segnalazioneFacade.contains(entity)) {
            segnalazioneFacade.remove(entity);
            loggerTestBeans.add("Oggetto rimosso: " + entity.toString());
        } else {
            loggerTestBeans.add("Oggetto da rimuovere non trovato: " + entity.toString());
        }
        average();
    }
}
