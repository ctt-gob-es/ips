/**
 * Solicitudes.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.ws.respuesta;

/**
 * El Class Solicitudes.
 */
public class Solicitudes  implements java.io.Serializable {
    
    /** el solicitud transmision. */
    private es.redsara.intermediacion.scsp.esquemas.ws.respuesta.SolicitudTransmision[] solicitudTransmision;

    /**
     * Crea una nueva solicitudes.
     */
    public Solicitudes() {
    }

    /**
     * Crea una nueva solicitudes.
     *
     * @param solicitudTransmision el solicitud transmision
     */
    public Solicitudes(
           es.redsara.intermediacion.scsp.esquemas.ws.respuesta.SolicitudTransmision[] solicitudTransmision) {
           this.solicitudTransmision = solicitudTransmision;
    }


    /**
     * Gets the solicitudTransmision value for this Solicitudes.
     * 
     * @return solicitudTransmision
     */
    public es.redsara.intermediacion.scsp.esquemas.ws.respuesta.SolicitudTransmision[] getSolicitudTransmision() {
        return solicitudTransmision;
    }


    /**
     * Sets the solicitudTransmision value for this Solicitudes.
     *
     * @param solicitudTransmision el nuevo solicitud transmision
     */
    public void setSolicitudTransmision(es.redsara.intermediacion.scsp.esquemas.ws.respuesta.SolicitudTransmision[] solicitudTransmision) {
        this.solicitudTransmision = solicitudTransmision;
    }

    /**
     * Obtiene el solicitud transmision.
     *
     * @param i el i
     * @return el solicitud transmision
     */
    public es.redsara.intermediacion.scsp.esquemas.ws.respuesta.SolicitudTransmision getSolicitudTransmision(int i) {
        return this.solicitudTransmision[i];
    }

    /**
     * Establece el solicitud transmision.
     *
     * @param i el i
     * @param _value el value
     */
    public void setSolicitudTransmision(int i, es.redsara.intermediacion.scsp.esquemas.ws.respuesta.SolicitudTransmision _value) {
        this.solicitudTransmision[i] = _value;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Solicitudes)) return false;
        Solicitudes other = (Solicitudes) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.solicitudTransmision==null && other.getSolicitudTransmision()==null) || 
             (this.solicitudTransmision!=null &&
              java.util.Arrays.equals(this.solicitudTransmision, other.getSolicitudTransmision())));
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
        if (getSolicitudTransmision() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getSolicitudTransmision());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getSolicitudTransmision(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Solicitudes.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", ">Solicitudes"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("solicitudTransmision");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", "SolicitudTransmision"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", "SolicitudTransmision"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
