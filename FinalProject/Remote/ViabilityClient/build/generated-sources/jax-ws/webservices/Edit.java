
package webservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per edit complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="edit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entity" type="{http://webservice/}segnalazione" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "edit", propOrder = {
    "entity"
})
public class Edit {

    protected Segnalazione entity;

    /**
     * Recupera il valore della proprietà entity.
     * 
     * @return
     *     possible object is
     *     {@link Segnalazione }
     *     
     */
    public Segnalazione getEntity() {
        return entity;
    }

    /**
     * Imposta il valore della proprietà entity.
     * 
     * @param value
     *     allowed object is
     *     {@link Segnalazione }
     *     
     */
    public void setEntity(Segnalazione value) {
        this.entity = value;
    }

}
