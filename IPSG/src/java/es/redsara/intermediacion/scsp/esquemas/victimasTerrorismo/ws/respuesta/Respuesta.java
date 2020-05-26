package es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.ws.respuesta;

/**
 * Respuesta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

public class Respuesta  implements java.io.Serializable {
	
	private es.redsara.intermediacion.scsp.esquemas.ws.respuesta.RespuestaAtributos atributos;

    private es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.ws.respuesta.Transmisiones transmisiones;

    public Respuesta() {
    }

    public Respuesta(
           es.redsara.intermediacion.scsp.esquemas.ws.respuesta.RespuestaAtributos atributos,
           es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.ws.respuesta.Transmisiones transmisiones) {
           this.atributos = atributos;
           this.transmisiones = transmisiones;
    }


    /**
     * Gets the atributos value for this Respuesta.
     * 
     * @return atributos
     */
    public es.redsara.intermediacion.scsp.esquemas.ws.respuesta.RespuestaAtributos getAtributos() {
        return atributos;
    }


    /**
     * Sets the atributos value for this Respuesta.
     * 
     * @param atributos
     */
    public void setAtributos(es.redsara.intermediacion.scsp.esquemas.ws.respuesta.RespuestaAtributos atributos) {
        this.atributos = atributos;
    }


    /**
     * Gets the transmisiones value for this Respuesta.
     * 
     * @return transmisiones
     */
    public es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.ws.respuesta.Transmisiones getTransmisiones() {
        return transmisiones;
    }


    /**
     * Sets the transmisiones value for this Respuesta.
     * 
     * @param transmisiones
     */
    public void setTransmisiones(es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.ws.respuesta.Transmisiones transmisiones) {
        this.transmisiones = transmisiones;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Respuesta)) return false;
        Respuesta other = (Respuesta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.atributos==null && other.getAtributos()==null) || 
             (this.atributos!=null &&
              this.atributos.equals(other.getAtributos()))) &&
            ((this.transmisiones==null && other.getTransmisiones()==null) || 
             (this.transmisiones!=null &&
              this.transmisiones.equals(other.getTransmisiones())));
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
        if (getAtributos() != null) {
            _hashCode += getAtributos().hashCode();
        }
        if (getTransmisiones() != null) {
            _hashCode += getTransmisiones().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Respuesta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", ">Respuesta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("atributos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", "Atributos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", ">>Respuesta>Atributos"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transmisiones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", "Transmisiones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", ">Transmisiones"));
        elemField.setMinOccurs(0);
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


