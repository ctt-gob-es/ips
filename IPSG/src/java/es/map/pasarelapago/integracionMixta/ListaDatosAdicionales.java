/**
 * ListaDatosAdicionales.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.pasarelapago.integracionMixta;

/**
 * El Class ListaDatosAdicionales.
 */
public class ListaDatosAdicionales  implements java.io.Serializable {
    
    /** el datos adicionales. */
    private es.map.pasarelapago.service.DatoAdicional[] datosAdicionales;

    /**
     * Crea una nueva lista datos adicionales.
     */
    public ListaDatosAdicionales() {
    }

    /**
     * Crea una nueva lista datos adicionales.
     *
     * @param datosAdicionales el datos adicionales
     */
    public ListaDatosAdicionales(
           es.map.pasarelapago.service.DatoAdicional[] datosAdicionales) {
           this.datosAdicionales = datosAdicionales;
    }


    /**
     * Gets the datosAdicionales value for this ListaDatosAdicionales.
     * 
     * @return datosAdicionales
     */
    public es.map.pasarelapago.service.DatoAdicional[] getDatosAdicionales() {
        return datosAdicionales;
    }


    /**
     * Sets the datosAdicionales value for this ListaDatosAdicionales.
     *
     * @param datosAdicionales el nuevo datos adicionales
     */
    public void setDatosAdicionales(es.map.pasarelapago.service.DatoAdicional[] datosAdicionales) {
        this.datosAdicionales = datosAdicionales;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaDatosAdicionales)) return false;
        ListaDatosAdicionales other = (ListaDatosAdicionales) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.datosAdicionales==null && other.getDatosAdicionales()==null) || 
             (this.datosAdicionales!=null &&
              java.util.Arrays.equals(this.datosAdicionales, other.getDatosAdicionales())));
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
        if (getDatosAdicionales() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDatosAdicionales());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDatosAdicionales(), i);
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
        new org.apache.axis.description.TypeDesc(ListaDatosAdicionales.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://pasarelapago.map.es/integracionMixta", "ListaDatosAdicionales"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosAdicionales");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.map.es/integracionMixta", "datosAdicionales"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "DatoAdicional"));
        elemField.setNillable(true);
        elemField.setItemQName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "item"));
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
