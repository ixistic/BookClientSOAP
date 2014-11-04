
package com.williamsportwebdeveloper;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetBooksByTitleResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getBooksByTitleResult"
})
@XmlRootElement(name = "GetBooksByTitleResponse")
public class GetBooksByTitleResponse {

    @XmlElement(name = "GetBooksByTitleResult")
    protected String getBooksByTitleResult;

    /**
     * Gets the value of the getBooksByTitleResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetBooksByTitleResult() {
        return getBooksByTitleResult;
    }

    /**
     * Sets the value of the getBooksByTitleResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetBooksByTitleResult(String value) {
        this.getBooksByTitleResult = value;
    }

}
