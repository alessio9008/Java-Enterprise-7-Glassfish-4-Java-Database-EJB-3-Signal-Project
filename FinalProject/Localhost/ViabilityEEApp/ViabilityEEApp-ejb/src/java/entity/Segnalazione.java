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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Alessio
 */
@Entity
@Table(name = "SEGNALAZIONE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Segnalazione.findAll", query = "SELECT s FROM Segnalazione s"),
    @NamedQuery(name = "Segnalazione.findById", query = "SELECT s FROM Segnalazione s WHERE s.id = :id"),
    @NamedQuery(name = "Segnalazione.findByNamesurname", query = "SELECT s FROM Segnalazione s WHERE s.namesurname = :namesurname"),
    @NamedQuery(name = "Segnalazione.findByAge", query = "SELECT s FROM Segnalazione s WHERE s.age = :age"),
    @NamedQuery(name = "Segnalazione.findByIddistrict", query = "SELECT s FROM Segnalazione s WHERE s.iddistrict = :iddistrict"),
    @NamedQuery(name = "Segnalazione.findByPriority", query = "SELECT s FROM Segnalazione s WHERE s.priority = :priority"),
    @NamedQuery(name = "Segnalazione.findByTmstampedit", query = "SELECT s FROM Segnalazione s WHERE s.tmstampedit = :tmstampedit")})
public class Segnalazione implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "NAMESURNAME")
    private String namesurname;
    @Basic(optional = false)
    @NotNull
    @Column(name = "AGE")
    private int age;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDDISTRICT")
    private long iddistrict;
    @Basic(optional = false)
    @NotNull
    @Column(name = "PRIORITY")
    private int priority;
    @Column(name = "TMSTAMPEDIT")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tmstampedit;

    public Segnalazione() {
    }

    public Segnalazione(Long id) {
        this.id = id;
    }

    public Segnalazione(Long id, String namesurname, int age, long iddistrict, int priority) {
        this(id);
        this.namesurname = namesurname;
        this.age = age;
        this.iddistrict = iddistrict;
        this.priority = priority;
    }

    public Segnalazione(String namesurname, int age, long iddistrict, int priority) {
        this.namesurname = namesurname;
        this.age = age;
        this.iddistrict = iddistrict;
        this.priority = priority;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamesurname() {
        return namesurname;
    }

    public void setNamesurname(String namesurname) {
        this.namesurname = namesurname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(long iddistrict) {
        this.iddistrict = iddistrict;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getTmstampedit() {
        return tmstampedit;
    }

    public void setTmstampedit(Date tmstampedit) {
        this.tmstampedit = tmstampedit;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.namesurname);
        hash = 47 * hash + this.age;
        hash = 47 * hash + (int) (this.iddistrict ^ (this.iddistrict >>> 32));
        hash = 47 * hash + this.priority;
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
        final Segnalazione other = (Segnalazione) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Segnalazione{" + "id=" + id + ", namesurname=" + namesurname + ", age=" + age + ", iddistrict=" + iddistrict + ", priority=" + priority + ", tmstampedit=" + tmstampedit + '}';
    }    
}
