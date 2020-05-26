/**
 * RespuestaListaEntidades.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.agenciatributaria.www.respuesta;

public class RespuestaListaEntidades  implements java.io.Serializable {
    private es.agenciatributaria.www.respuesta.Peticion peticion;

    private es.agenciatributaria.www.respuesta.Respuesta respuesta;

    public RespuestaListaEntidades() {
    }

    public RespuestaListaEntidades(
           es.agenciatributaria.www.respuesta.Peticion peticion,
           es.agenciatributaria.www.respuesta.Respuesta respuesta) {
           this.peticion = peticion;
           this.respuesta = respuesta;
    }


    /**
     * Gets the peticion value for this RespuestaListaEntidades.
     * 
     * @return peticion
     */
    public es.agenciatributaria.www.respuesta.Peticion getPeticion() {
        return peticion;
    }


    /**
     * Sets the peticion value for this RespuestaListaEntidades.
     * 
     * @param peticion
     */
    public void setPeticion(es.agenciatributaria.www.respuesta.Peticion peticion) {
        this.peticion = peticion;
    }


    /**
     * Gets the respuesta value for this RespuestaListaEntidades.
     * 
     * @return respuesta
     */
    public es.agenciatributaria.www.respuesta.Respuesta getRespuesta() {
        return respuesta;
    }


    /**
     * Sets the respuesta value for this RespuestaListaEntidades.
     * 
     * @param respuesta
     */
    public void setRespuesta(es.agenciatributaria.www.respuesta.Respuesta respuesta) {
        this.respuesta = respuesta;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespuestaListaEntidades)) return false;
        RespuestaListaEntidades other = (RespuestaListaEntidades) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.peticion==null && other.getPeticion()==null) || 
             (this.peticion!=null &&
              this.peticion.equals(other.getPeticion()))) &&
            ((this.respuesta==null && other.getRespuesta()==null) || 
             (this.respuesta!=null &&
              this.respuesta.equals(other.getRespuesta())));
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
        if (getPeticion() != null) {
            _hashCode += getPeticion().hashCode();
        }
        if (getRespuesta() != null) {
            _hashCode += getRespuesta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RespuestaListaEntidades.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", ">RespuestaListaEntidades"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("peticion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", "Peticion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", ">Peticion"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", "Respuesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", ">Respuesta"));
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
