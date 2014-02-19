/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alessio
 */
@Entity
@Table(name = "STATISTICS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Statistics.findAll", query = "SELECT s FROM Statistics s"),
    @NamedQuery(name = "Statistics.findById", query = "SELECT s FROM Statistics s WHERE s.id = :id"),
    @NamedQuery(name = "Statistics.findByAverageweek", query = "SELECT s FROM Statistics s WHERE s.averageweek = :averageweek"),
    @NamedQuery(name = "Statistics.findByAveragemonth", query = "SELECT s FROM Statistics s WHERE s.averagemonth = :averagemonth"),
    @NamedQuery(name = "Statistics.findByAveragemonthhighpriority", query = "SELECT s FROM Statistics s WHERE s.averagemonthhighpriority = :averagemonthhighpriority"),
    @NamedQuery(name = "Statistics.findByAverageageusers", query = "SELECT s FROM Statistics s WHERE s.averageageusers = :averageageusers"),
    @NamedQuery(name = "Statistics.findByTmstampedit", query = "SELECT s FROM Statistics s WHERE s.tmstampedit = :tmstampedit")})
public class Statistics implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AVERAGEWEEK")
    private double averageweek;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AVERAGEMONTH")
    private double averagemonth;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AVERAGEMONTHHIGHPRIORITY")
    private double averagemonthhighpriority;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AVERAGEAGEUSERS")
    private double averageageusers;
    @Column(name = "TMSTAMPEDIT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tmstampedit;

    public Statistics() {
    }

    public Statistics(Long id) {
        this.id = id;
    }

    public Statistics(Long id, double averageweek, double averagemonth, double averagemonthhighpriority, double averageageusers) {
        this.id = id;
        this.averageweek = averageweek;
        this.averagemonth = averagemonth;
        this.averagemonthhighpriority = averagemonthhighpriority;
        this.averageageusers = averageageusers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAverageweek() {
        return averageweek;
    }

    public void setAverageweek(double averageweek) {
        this.averageweek = averageweek;
    }

    public double getAveragemonth() {
        return averagemonth;
    }

    public void setAveragemonth(double averagemonth) {
        this.averagemonth = averagemonth;
    }

    public double getAveragemonthhighpriority() {
        return averagemonthhighpriority;
    }

    public void setAveragemonthhighpriority(double averagemonthhighpriority) {
        this.averagemonthhighpriority = averagemonthhighpriority;
    }

    public double getAverageageusers() {
        return averageageusers;
    }

    public void setAverageageusers(double averageageusers) {
        this.averageageusers = averageageusers;
    }

    public Date getTmstampedit() {
        return tmstampedit;
    }

    public void setTmstampedit(Date tmstampedit) {
        this.tmstampedit = tmstampedit;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.id);
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.averageweek) ^ (Double.doubleToLongBits(this.averageweek) >>> 32));
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.averagemonth) ^ (Double.doubleToLongBits(this.averagemonth) >>> 32));
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.averagemonthhighpriority) ^ (Double.doubleToLongBits(this.averagemonthhighpriority) >>> 32));
        hash = 73 * hash + (int) (Double.doubleToLongBits(this.averageageusers) ^ (Double.doubleToLongBits(this.averageageusers) >>> 32));
        hash = 73 * hash + Objects.hashCode(this.tmstampedit);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Statistics other = (Statistics) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Statistics{" + "id=" + id + ", averageweek=" + averageweek + ", averagemonth=" + averagemonth + ", averagemonthhighpriority=" + averagemonthhighpriority + ", averageageusers=" + averageageusers + ", tmstampedit=" + tmstampedit + '}';
    }

    
}
