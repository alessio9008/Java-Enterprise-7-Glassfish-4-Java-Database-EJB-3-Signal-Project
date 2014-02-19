
package webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per segnalazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="segnalazione">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="age" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="iddistrict" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="namesurname" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tmstampedit" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "segnalazione", propOrder = {
    "age",
    "id",
    "iddistrict",
    "namesurname",
    "priority",
    "tmstampedit"
})
public class Segnalazione {

    protected int age;
    protected Long id;
    protected long iddistrict;
    protected String namesurname;
    protected int priority;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar tmstampedit;

    /**
     * Recupera il valore della proprietà age.
     * 
     */
    public int getAge() {
        return age;
    }

    /**
     * Imposta il valore della proprietà age.
     * 
     */
    public void setAge(int value) {
        this.age = value;
    }

    /**
     * Recupera il valore della proprietà id.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietà id.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Recupera il valore della proprietà iddistrict.
     * 
     */
    public long getIddistrict() {
        return iddistrict;
    }

    /**
     * Imposta il valore della proprietà iddistrict.
     * 
     */
    public void setIddistrict(long value) {
        this.iddistrict = value;
    }

    /**
     * Recupera il valore della proprietà namesurname.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNamesurname() {
        return namesurname;
    }

    /**
     * Imposta il valore della proprietà namesurname.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNamesurname(String value) {
        this.namesurname = value;
    }

    /**
     * Recupera il valore della proprietà priority.
     * 
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Imposta il valore della proprietà priority.
     * 
     */
    public void setPriority(int value) {
        this.priority = value;
    }

    /**
     * Recupera il valore della proprietà tmstampedit.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTmstampedit() {
        return tmstampedit;
    }

    /**
     * Imposta il valore della proprietà tmstampedit.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTmstampedit(XMLGregorianCalendar value) {
        this.tmstampedit = value;
    }

}
