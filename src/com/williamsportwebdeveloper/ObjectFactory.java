
package com.williamsportwebdeveloper;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.williamsportwebdeveloper package. 
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

    private final static QName _String_QNAME = new QName("http://www.williamsportwebdeveloper.com/", "string");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.williamsportwebdeveloper
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetBooksByTitleResponse }
     * 
     */
    public GetBooksByTitleResponse createGetBooksByTitleResponse() {
        return new GetBooksByTitleResponse();
    }

    /**
     * Create an instance of {@link GetBooksByTitle }
     * 
     */
    public GetBooksByTitle createGetBooksByTitle() {
        return new GetBooksByTitle();
    }

    /**
     * Create an instance of {@link GetBooksByAuthor }
     * 
     */
    public GetBooksByAuthor createGetBooksByAuthor() {
        return new GetBooksByAuthor();
    }

    /**
     * Create an instance of {@link GetBooksByAuthorResponse }
     * 
     */
    public GetBooksByAuthorResponse createGetBooksByAuthorResponse() {
        return new GetBooksByAuthorResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.williamsportwebdeveloper.com/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

}
