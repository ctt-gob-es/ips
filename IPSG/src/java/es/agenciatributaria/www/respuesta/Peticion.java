/**
 * Peticion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.agenciatributaria.www.respuesta;

public class Peticion  implements java.io.Serializable {
    private java.lang.String NIFPeticionario;

    private java.util.Calendar timestamp;

    public Peticion() {
    }

    public Peticion(
           java.lang.String NIFPeticionario,
           java.util.Calendar timestamp) {
           this.NIFPeticionario = NIFPeticionario;
           this.timestamp = timestamp;
    }


    /**
     * Gets the NIFPeticionario value for this Peticion.
     * 
     * @return NIFPeticionario
     */
    public java.lang.String getNIFPeticionario() {
        return NIFPeticionario;
    }


    /**
     * Sets the NIFPeticionario value for this Peticion.
     * 
     * @param NIFPeticionario
     */
    public void setNIFPeticionario(java.lang.String NIFPeticionario) {
        this.NIFPeticionario = NIFPeticionario;
    }


    /**
     * Gets the timestamp value for this Peticion.
     * 
     * @return timestamp
     */
    public java.util.Calendar getTimestamp() {
        return timestamp;
    }


    /**
     * Sets the timestamp value for this Peticion.
     * 
     * @param timestamp
     */
    public void setTimestamp(java.util.Calendar timestamp) {
        this.timestamp = timestamp;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Peticion)) return false;
        Peticion other = (Peticion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.NIFPeticionario==null && other.getNIFPeticionario()==null) || 
             (this.NIFPeticionario!=null &&
              this.NIFPeticionario.equals(other.getNIFPeticionario()))) &&
            ((this.timestamp==null && other.getTimestamp()==null) || 
             (this.timestamp!=null &&
              this.timestamp.equals(other.getTimestamp())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getNIFPeticionario() != null) {
            _hashCode += getNIFPeticionario().hashCode();
        }
        if (getTimestamp() != null) {
            _hashCode += getTimestamp().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Peticion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", ">Peticion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NIFPeticionario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", "NIFPeticionario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", ">NIFPeticionario"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", "Timestamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
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
     * Get Custom Deserializer
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
