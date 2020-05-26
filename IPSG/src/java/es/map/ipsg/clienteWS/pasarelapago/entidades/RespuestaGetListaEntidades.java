/**
 * RespuestaGetListaEntidades.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.ipsg.clienteWS.pasarelapago.entidades;

/**
 * El Class RespuestaGetListaEntidades.
 */
public class RespuestaGetListaEntidades  implements java.io.Serializable {
    
    /** el entidades. */
    private es.map.ipsg.clienteWS.pasarelapago.entidades.Entidad[] entidades;

    /**
     * Crea una nueva respuesta obtiene el lista entidades.
     */
    public RespuestaGetListaEntidades() {
    }

    /**
     * Crea una nueva respuesta obtiene el lista entidades.
     *
     * @param entidades el entidades
     */
    public RespuestaGetListaEntidades(
           es.map.ipsg.clienteWS.pasarelapago.entidades.Entidad[] entidades) {
           this.entidades = entidades;
    }


    /**
     * Gets the entidades value for this RespuestaGetListaEntidades.
     * 
     * @return entidades
     */
    public es.map.ipsg.clienteWS.pasarelapago.entidades.Entidad[] getEntidades() {
        return entidades;
    }


    /**
     * Sets the entidades value for this RespuestaGetListaEntidades.
     *
     * @param entidades el nuevo entidades
     */
    public void setEntidades(es.map.ipsg.clienteWS.pasarelapago.entidades.Entidad[] entidades) {
        this.entidades = entidades;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespuestaGetListaEntidades)) return false;
        RespuestaGetListaEntidades other = (RespuestaGetListaEntidades) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.entidades==null && other.getEntidades()==null) || 
             (this.entidades!=null &&
              java.util.Arrays.equals(this.entidades, other.getEntidades())));
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
        if (getEntidades() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getEntidades());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getEntidades(), i);
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
        new org.apache.axis.description.TypeDesc(RespuestaGetListaEntidades.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "RespuestaGetListaEntidades"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("entidades");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "entidades"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "Entidad"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://pasarelapago.clienteWS.ipsg.map.es/entidades", "item"));
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
