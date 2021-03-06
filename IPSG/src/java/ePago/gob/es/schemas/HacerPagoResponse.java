/**
 * HacerPagoResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ePago.gob.es.schemas;

/**
 * El Class HacerPagoResponse.
 */
public class HacerPagoResponse  implements java.io.Serializable {
    
    /** el hacer pago return. */
    private ePago.gob.es.schemas.DefaultDatosPagoOut hacerPagoReturn;

    /**
     * Crea una nueva hacer pago response.
     */
    public HacerPagoResponse() {
    }

    /**
     * Crea una nueva hacer pago response.
     *
     * @param hacerPagoReturn el hacer pago return
     */
    public HacerPagoResponse(
           ePago.gob.es.schemas.DefaultDatosPagoOut hacerPagoReturn) {
           this.hacerPagoReturn = hacerPagoReturn;
    }


    /**
     * Gets the hacerPagoReturn value for this HacerPagoResponse.
     * 
     * @return hacerPagoReturn
     */
    public ePago.gob.es.schemas.DefaultDatosPagoOut getHacerPagoReturn() {
        return hacerPagoReturn;
    }


    /**
     * Sets the hacerPagoReturn value for this HacerPagoResponse.
     *
     * @param hacerPagoReturn el nuevo hacer pago return
     */
    public void setHacerPagoReturn(ePago.gob.es.schemas.DefaultDatosPagoOut hacerPagoReturn) {
        this.hacerPagoReturn = hacerPagoReturn;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HacerPagoResponse)) return false;
        HacerPagoResponse other = (HacerPagoResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.hacerPagoReturn==null && other.getHacerPagoReturn()==null) || 
             (this.hacerPagoReturn!=null &&
              this.hacerPagoReturn.equals(other.getHacerPagoReturn())));
        __equalsCalc = null;
        return _equals;
    }

    /** el hash code calc. */
    private boolean __hashCodeCalc = false;
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getHacerPagoReturn() != null) {
            _hashCode += getHacerPagoReturn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HacerPagoResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">hacerPagoResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hacerPagoReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "hacerPagoReturn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoOut"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object.
     *
     * @return el type desc
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer.
     *
     * @param mechType el mech type
     * @param _javaType el java type
     * @param _xmlType el xml type
     * @return el serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer.
     *
     * @param mechType el mech type
     * @param _javaType el java type
     * @param _xmlType el xml type
     * @return el deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
