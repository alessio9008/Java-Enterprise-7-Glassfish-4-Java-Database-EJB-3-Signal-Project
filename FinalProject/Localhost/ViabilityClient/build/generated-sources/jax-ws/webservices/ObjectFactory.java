
package webservices;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webservices package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Remove_QNAME = new QName("http://webservice/", "remove");
    private final static QName _Segnalazione_QNAME = new QName("http://webservice/", "segnalazione");
    private final static QName _Create_QNAME = new QName("http://webservice/", "create");
    private final static QName _Edit_QNAME = new QName("http://webservice/", "edit");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webservices
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Remove }
     * 
     */
    public Remove createRemove() {
        return new Remove();
    }

    /**
     * Create an instance of {@link Segnalazione }
     * 
     */
    public Segnalazione createSegnalazione() {
        return new Segnalazione();
    }

    /**
     * Create an instance of {@link Edit }
     * 
     */
    public Edit createEdit() {
        return new Edit();
    }

    /**
     * Create an instance of {@link Create }
     * 
     */
    public Create createCreate() {
        return new Create();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Remove }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "remove")
    public JAXBElement<Remove> createRemove(Remove value) {
        return new JAXBElement<Remove>(_Remove_QNAME, Remove.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Segnalazione }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "segnalazione")
    public JAXBElement<Segnalazione> createSegnalazione(Segnalazione value) {
        return new JAXBElement<Segnalazione>(_Segnalazione_QNAME, Segnalazione.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Create }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "create")
    public JAXBElement<Create> createCreate(Create value) {
        return new JAXBElement<Create>(_Create_QNAME, Create.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Edit }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "edit")
    public JAXBElement<Edit> createEdit(Edit value) {
        return new JAXBElement<Edit>(_Edit_QNAME, Edit.class, null, value);
    }

}
