/**
 * SolicitudTransmisionDatosGenericos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.ws.peticion;

/**
 * El Class SolicitudTransmisionDatosGenericos.
 */
public class SolicitudTransmisionDatosGenericos  implements java.io.Serializable {
    
    /** el solicitante. */
    private es.redsara.intermediacion.scsp.esquemas.ws.peticion.Solicitante solicitante;

    /** el titular. */
    private es.redsara.intermediacion.scsp.esquemas.ws.peticion.Titular titular;

    /**
     * Crea una nueva solicitud transmision datos genericos.
     */
    public SolicitudTransmisionDatosGenericos() {
    }

    /**
     * Crea una nueva solicitud transmision datos genericos.
     *
     * @param solicitante el solicitante
     * @param titular el titular
     */
    public SolicitudTransmisionDatosGenericos(
           es.redsara.intermediacion.scsp.esquemas.ws.peticion.Solicitante solicitante,
           es.redsara.intermediacion.scsp.esquemas.ws.peticion.Titular titular) {
           this.solicitante = solicitante;
           this.titular = titular;
    }


    /**
     * Gets the solicitante value for this SolicitudTransmisionDatosGenericos.
     * 
     * @return solicitante
     */
    public es.redsara.intermediacion.scsp.esquemas.ws.peticion.Solicitante getSolicitante() {
        return solicitante;
    }


    /**
     * Sets the solicitante value for this SolicitudTransmisionDatosGenericos.
     *
     * @param solicitante el nuevo solicitante
     */
    public void setSolicitante(es.redsara.intermediacion.scsp.esquemas.ws.peticion.Solicitante solicitante) {
        this.solicitante = solicitante;
    }


    /**
     * Gets the titular value for this SolicitudTransmisionDatosGenericos.
     * 
     * @return titular
     */
    public es.redsara.intermediacion.scsp.esquemas.ws.peticion.Titular getTitular() {
        return titular;
    }


    /**
     * Sets the titular value for this SolicitudTransmisionDatosGenericos.
     *
     * @param titular el nuevo titular
     */
    public void setTitular(es.redsara.intermediacion.scsp.esquemas.ws.peticion.Titular titular) {
        this.titular = titular;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof SolicitudTransmisionDatosGenericos)) return false;
        SolicitudTransmisionDatosGenericos other = (SolicitudTransmisionDatosGenericos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.solicitante==null && other.getSolicitante()==null) || 
             (this.solicitante!=null &&
              this.solicitante.equals(other.getSolicitante()))) &&
            ((this.titular==null && other.getTitular()==null) || 
             (this.titular!=null &&
              this.titular.equals(other.getTitular())));
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
        if (getSolicitante() != null) {
            _hashCode += getSolicitante().hashCode();
        }
        if (getTitular() != null) {
            _hashCode += getTitular().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(SolicitudTransmisionDatosGenericos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", ">>SolicitudTransmision>DatosGenericos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("solicitante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", "Solicitante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", ">Solicitante"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", "Titular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/peticion", ">Titular"));
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
