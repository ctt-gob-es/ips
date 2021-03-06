/**
 * HacerPagoClaveResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ePago.gob.es.schemas;

/**
 * El Class HacerPagoClaveResponse.
 */
public class HacerPagoClaveResponse  implements java.io.Serializable {
    
    /** el hacer pago clave return. */
    private ePago.gob.es.schemas.DefaultDatosPagoClaveOut hacerPagoClaveReturn;

    /**
     * Crea una nueva hacer pago clave response.
     */
    public HacerPagoClaveResponse() {
    }

    /**
     * Crea una nueva hacer pago clave response.
     *
     * @param hacerPagoClaveReturn el hacer pago clave return
     */
    public HacerPagoClaveResponse(
           ePago.gob.es.schemas.DefaultDatosPagoClaveOut hacerPagoClaveReturn) {
           this.hacerPagoClaveReturn = hacerPagoClaveReturn;
    }


    /**
     * Gets the hacerPagoClaveReturn value for this HacerPagoClaveResponse.
     * 
     * @return hacerPagoClaveReturn
     */
    public ePago.gob.es.schemas.DefaultDatosPagoClaveOut getHacerPagoClaveReturn() {
        return hacerPagoClaveReturn;
    }


    /**
     * Sets the hacerPagoClaveReturn value for this HacerPagoClaveResponse.
     *
     * @param hacerPagoClaveReturn el nuevo hacer pago clave return
     */
    public void setHacerPagoClaveReturn(ePago.gob.es.schemas.DefaultDatosPagoClaveOut hacerPagoClaveReturn) {
        this.hacerPagoClaveReturn = hacerPagoClaveReturn;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HacerPagoClaveResponse)) return false;
        HacerPagoClaveResponse other = (HacerPagoClaveResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.hacerPagoClaveReturn==null && other.getHacerPagoClaveReturn()==null) || 
             (this.hacerPagoClaveReturn!=null &&
              this.hacerPagoClaveReturn.equals(other.getHacerPagoClaveReturn())));
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
        if (getHacerPagoClaveReturn() != null) {
            _hashCode += getHacerPagoClaveReturn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HacerPagoClaveResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">hacerPagoClaveResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hacerPagoClaveReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "hacerPagoClaveReturn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoClaveOut"));
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
