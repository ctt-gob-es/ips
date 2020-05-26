/**
 * ConsultarPagoClaveResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ePago.gob.es.schemas;

/**
 * El Class ConsultarPagoClaveResponse.
 */
public class ConsultarPagoClaveResponse  implements java.io.Serializable {
    
    /** el consultar pago clave return. */
    private ePago.gob.es.schemas.DefaultDatosPagoClaveOut consultarPagoClaveReturn;

    /**
     * Crea una nueva consultar pago clave response.
     */
    public ConsultarPagoClaveResponse() {
    }

    /**
     * Crea una nueva consultar pago clave response.
     *
     * @param consultarPagoClaveReturn el consultar pago clave return
     */
    public ConsultarPagoClaveResponse(
           ePago.gob.es.schemas.DefaultDatosPagoClaveOut consultarPagoClaveReturn) {
           this.consultarPagoClaveReturn = consultarPagoClaveReturn;
    }


    /**
     * Gets the consultarPagoClaveReturn value for this ConsultarPagoClaveResponse.
     * 
     * @return consultarPagoClaveReturn
     */
    public ePago.gob.es.schemas.DefaultDatosPagoClaveOut getConsultarPagoClaveReturn() {
        return consultarPagoClaveReturn;
    }


    /**
     * Sets the consultarPagoClaveReturn value for this ConsultarPagoClaveResponse.
     *
     * @param consultarPagoClaveReturn el nuevo consultar pago clave return
     */
    public void setConsultarPagoClaveReturn(ePago.gob.es.schemas.DefaultDatosPagoClaveOut consultarPagoClaveReturn) {
        this.consultarPagoClaveReturn = consultarPagoClaveReturn;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultarPagoClaveResponse)) return false;
        ConsultarPagoClaveResponse other = (ConsultarPagoClaveResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.consultarPagoClaveReturn==null && other.getConsultarPagoClaveReturn()==null) || 
             (this.consultarPagoClaveReturn!=null &&
              this.consultarPagoClaveReturn.equals(other.getConsultarPagoClaveReturn())));
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
        if (getConsultarPagoClaveReturn() != null) {
            _hashCode += getConsultarPagoClaveReturn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultarPagoClaveResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">consultarPagoClaveResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consultarPagoClaveReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "consultarPagoClaveReturn"));
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
