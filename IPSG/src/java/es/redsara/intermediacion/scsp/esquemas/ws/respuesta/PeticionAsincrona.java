/**
 * PeticionAsincrona.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.ws.respuesta;

/**
 * El Class PeticionAsincrona.
 */
public class PeticionAsincrona  implements java.io.Serializable {
    
    /** el atributos. */
    private es.redsara.intermediacion.scsp.esquemas.ws.respuesta.Atributos atributos;

    /** el solicitudes. */
    private es.redsara.intermediacion.scsp.esquemas.ws.respuesta.Solicitudes solicitudes;

    /**
     * Crea una nueva peticion asincrona.
     */
    public PeticionAsincrona() {
    }

    /**
     * Crea una nueva peticion asincrona.
     *
     * @param atributos el atributos
     * @param solicitudes el solicitudes
     */
    public PeticionAsincrona(
           es.redsara.intermediacion.scsp.esquemas.ws.respuesta.Atributos atributos,
           es.redsara.intermediacion.scsp.esquemas.ws.respuesta.Solicitudes solicitudes) {
           this.atributos = atributos;
           this.solicitudes = solicitudes;
    }


    /**
     * Gets the atributos value for this PeticionAsincrona.
     * 
     * @return atributos
     */
    public es.redsara.intermediacion.scsp.esquemas.ws.respuesta.Atributos getAtributos() {
        return atributos;
    }


    /**
     * Sets the atributos value for this PeticionAsincrona.
     *
     * @param atributos el nuevo atributos
     */
    public void setAtributos(es.redsara.intermediacion.scsp.esquemas.ws.respuesta.Atributos atributos) {
        this.atributos = atributos;
    }


    /**
     * Gets the solicitudes value for this PeticionAsincrona.
     * 
     * @return solicitudes
     */
    public es.redsara.intermediacion.scsp.esquemas.ws.respuesta.Solicitudes getSolicitudes() {
        return solicitudes;
    }


    /**
     * Sets the solicitudes value for this PeticionAsincrona.
     *
     * @param solicitudes el nuevo solicitudes
     */
    public void setSolicitudes(es.redsara.intermediacion.scsp.esquemas.ws.respuesta.Solicitudes solicitudes) {
        this.solicitudes = solicitudes;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PeticionAsincrona)) return false;
        PeticionAsincrona other = (PeticionAsincrona) obj;
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
            ((this.solicitudes==null && other.getSolicitudes()==null) || 
             (this.solicitudes!=null &&
              this.solicitudes.equals(other.getSolicitudes())));
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
        if (getAtributos() != null) {
            _hashCode += getAtributos().hashCode();
        }
        if (getSolicitudes() != null) {
            _hashCode += getSolicitudes().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PeticionAsincrona.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", ">PeticionAsincrona"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("atributos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", "Atributos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", ">Atributos"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("solicitudes");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", "Solicitudes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", ">Solicitudes"));
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
