//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.12.06 at 06:42:00 PM BRST 
//


package temporary.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for tcPedidoCancelamento complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="tcPedidoCancelamento">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="InfPedidoCancelamento" type="{}tcInfPedidoCancelamento"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tcPedidoCancelamento", propOrder = {
    "infPedidoCancelamento"
})
public class TcPedidoCancelamento {

    @XmlElement(name = "InfPedidoCancelamento", required = true)
    protected TcInfPedidoCancelamento infPedidoCancelamento;

    /**
     * Gets the value of the infPedidoCancelamento property.
     * 
     * @return
     *     possible object is
     *     {@link TcInfPedidoCancelamento }
     *     
     */
    public TcInfPedidoCancelamento getInfPedidoCancelamento() {
        return infPedidoCancelamento;
    }

    /**
     * Sets the value of the infPedidoCancelamento property.
     * 
     * @param value
     *     allowed object is
     *     {@link TcInfPedidoCancelamento }
     *     
     */
    public void setInfPedidoCancelamento(TcInfPedidoCancelamento value) {
        this.infPedidoCancelamento = value;
    }

}