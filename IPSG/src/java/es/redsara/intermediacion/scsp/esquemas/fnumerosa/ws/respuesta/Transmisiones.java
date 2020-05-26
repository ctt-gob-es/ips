/**
 * Transmision.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.fnumerosa.ws.respuesta;

/**
 * El Class Transmisiones.
 */
public class Transmisiones  implements java.io.Serializable {
    
    /** el transmision datos. */
    private es.redsara.intermediacion.scsp.esquemas.fnumerosa.ws.respuesta.TransmisionesTransmisionDatos[] transmisionDatos;

    /**
     * Crea una nueva transmisiones.
     */
    public Transmisiones() {
    }

    /**
     * Crea una nueva transmisiones.
     *
     * @param transmisionDatos el transmision datos
     */
    public Transmisiones(
           es.redsara.intermediacion.scsp.esquemas.fnumerosa.ws.respuesta.TransmisionesTransmisionDatos[] transmisionDatos) {
           this.transmisionDatos = transmisionDatos;
    }


    /**
     * Gets the transmisionDatos value for this Transmisiones.
     * 
     * @return transmisionDatos
     */
    public es.redsara.intermediacion.scsp.esquemas.fnumerosa.ws.respuesta.TransmisionesTransmisionDatos[] getTransmisionDatos() {
        return transmisionDatos;
    }


    /**
     * Sets the transmisionDatos value for this Transmisiones.
     *
     * @param transmisionDatos el nuevo transmision datos
     */
    public void setTransmisionDatos(es.redsara.intermediacion.scsp.esquemas.fnumerosa.ws.respuesta.TransmisionesTransmisionDatos[] transmisionDatos) {
        this.transmisionDatos = transmisionDatos;
    }

    /**
     * Obtiene el transmision datos.
     *
     * @param i el i
     * @return el transmision datos
     */
    public es.redsara.intermediacion.scsp.esquemas.fnumerosa.ws.respuesta.TransmisionesTransmisionDatos getTransmisionDatos(int i) {
        return this.transmisionDatos[i];
    }

    /**
     * Establece el transmision datos.
     *
     * @param i el i
     * @param _value el value
     */
    public void setTransmisionDatos(int i, es.redsara.intermediacion.scsp.esquemas.fnumerosa.ws.respuesta.TransmisionesTransmisionDatos _value) {
        this.transmisionDatos[i] = _value;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Transmisiones)) return false;
        Transmisiones other = (Transmisiones) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.transmisionDatos==null && other.getTransmisionDatos()==null) || 
             (this.transmisionDatos!=null &&
              java.util.Arrays.equals(this.transmisionDatos, other.getTransmisionDatos())));
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
        if (getTransmisionDatos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTransmisionDatos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTransmisionDatos(), i);
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
        new org.apache.axis.description.TypeDesc(Transmisiones.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", ">Transmisiones"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transmisionDatos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", "TransmisionDatos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", ">>Transmisiones>TransmisionDatos"));
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
