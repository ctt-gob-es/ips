/**
 * HacerPagoYRegistroResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ePago.gob.es.schemas;

/**
 * El Class HacerPagoYRegistroResponse.
 */
public class HacerPagoYRegistroResponse  implements java.io.Serializable {
    
    /** el hacer pago Y registro return. */
    private ePago.gob.es.schemas.DefaultDatosPagoRegistroOut hacerPagoYRegistroReturn;

    /**
     * Crea una nueva hacer pago Y registro response.
     */
    public HacerPagoYRegistroResponse() {
    }

    /**
     * Crea una nueva hacer pago Y registro response.
     *
     * @param hacerPagoYRegistroReturn el hacer pago Y registro return
     */
    public HacerPagoYRegistroResponse(
           ePago.gob.es.schemas.DefaultDatosPagoRegistroOut hacerPagoYRegistroReturn) {
           this.hacerPagoYRegistroReturn = hacerPagoYRegistroReturn;
    }


    /**
     * Gets the hacerPagoYRegistroReturn value for this HacerPagoYRegistroResponse.
     * 
     * @return hacerPagoYRegistroReturn
     */
    public ePago.gob.es.schemas.DefaultDatosPagoRegistroOut getHacerPagoYRegistroReturn() {
        return hacerPagoYRegistroReturn;
    }


    /**
     * Sets the hacerPagoYRegistroReturn value for this HacerPagoYRegistroResponse.
     *
     * @param hacerPagoYRegistroReturn el nuevo hacer pago Y registro return
     */
    public void setHacerPagoYRegistroReturn(ePago.gob.es.schemas.DefaultDatosPagoRegistroOut hacerPagoYRegistroReturn) {
        this.hacerPagoYRegistroReturn = hacerPagoYRegistroReturn;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof HacerPagoYRegistroResponse)) return false;
        HacerPagoYRegistroResponse other = (HacerPagoYRegistroResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.hacerPagoYRegistroReturn==null && other.getHacerPagoYRegistroReturn()==null) || 
             (this.hacerPagoYRegistroReturn!=null &&
              this.hacerPagoYRegistroReturn.equals(other.getHacerPagoYRegistroReturn())));
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
        if (getHacerPagoYRegistroReturn() != null) {
            _hashCode += getHacerPagoYRegistroReturn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(HacerPagoYRegistroResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">hacerPagoYRegistroResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hacerPagoYRegistroReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "hacerPagoYRegistroReturn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoRegistroOut"));
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
