/**
 * Horarios.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.agenciatributaria.www.respuesta;

public class Horarios  implements java.io.Serializable {
    private org.apache.axis.types.Time apertura;

    private org.apache.axis.types.Time cierre;

    public Horarios() {
    }

    public Horarios(
           org.apache.axis.types.Time apertura,
           org.apache.axis.types.Time cierre) {
           this.apertura = apertura;
           this.cierre = cierre;
    }


    /**
     * Gets the apertura value for this Horarios.
     * 
     * @return apertura
     */
    public org.apache.axis.types.Time getApertura() {
        return apertura;
    }


    /**
     * Sets the apertura value for this Horarios.
     * 
     * @param apertura
     */
    public void setApertura(org.apache.axis.types.Time apertura) {
        this.apertura = apertura;
    }


    /**
     * Gets the cierre value for this Horarios.
     * 
     * @return cierre
     */
    public org.apache.axis.types.Time getCierre() {
        return cierre;
    }


    /**
     * Sets the cierre value for this Horarios.
     * 
     * @param cierre
     */
    public void setCierre(org.apache.axis.types.Time cierre) {
        this.cierre = cierre;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Horarios)) return false;
        Horarios other = (Horarios) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.apertura==null && other.getApertura()==null) || 
             (this.apertura!=null &&
              this.apertura.equals(other.getApertura()))) &&
            ((this.cierre==null && other.getCierre()==null) || 
             (this.cierre!=null &&
              this.cierre.equals(other.getCierre())));
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
        if (getApertura() != null) {
            _hashCode += getApertura().hashCode();
        }
        if (getCierre() != null) {
            _hashCode += getCierre().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Horarios.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", ">Horarios"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apertura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", "Apertura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "time"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cierre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", "Cierre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "time"));
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
