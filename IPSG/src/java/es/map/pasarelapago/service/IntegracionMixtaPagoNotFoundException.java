/**
 * IntegracionMixtaPagoNotFoundException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.pasarelapago.service;

/**
 * El Class IntegracionMixtaPagoNotFoundException.
 */
public class IntegracionMixtaPagoNotFoundException  extends es.map.pasarelapago.service.IntegracionMixtaException  implements java.io.Serializable {
    
    /**
     * Crea una nueva integracion mixta pago not found exception.
     */
    public IntegracionMixtaPagoNotFoundException() {
    }

    /**
     * Crea una nueva integracion mixta pago not found exception.
     *
     * @param errorCode el error code
     * @param errorCodeTexto el error code texto
     * @param errorCodeTextoUsuario el error code texto usuario
     * @param errorDescription el error description
     * @param errorOrigen el error origen
     */
    public IntegracionMixtaPagoNotFoundException(
           java.lang.String errorCode,
           java.lang.String errorCodeTexto,
           java.lang.String errorCodeTextoUsuario,
           java.lang.String errorDescription,
           java.lang.String errorOrigen) {
        super(
            errorCode,
            errorCodeTexto,
            errorCodeTextoUsuario,
            errorDescription,
            errorOrigen);
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see es.map.pasarelapago.service.IntegracionMixtaException#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IntegracionMixtaPagoNotFoundException)) return false;
        IntegracionMixtaPagoNotFoundException other = (IntegracionMixtaPagoNotFoundException) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj);
        __equalsCalc = null;
        return _equals;
    }

    /** el hash code calc. */
    private boolean __hashCodeCalc = false;
    
    /* (non-Javadoc)
     * @see es.map.pasarelapago.service.IntegracionMixtaException#hashCode()
     */
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IntegracionMixtaPagoNotFoundException.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "IntegracionMixtaPagoNotFoundException"));
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


    /**
     * Writes the exception data to the faultDetails.
     *
     * @param qname el qname
     * @param context el context
     * @throws IOException Señala que se ha producido una excepción de I/O.
     */
    public void writeDetails(javax.xml.namespace.QName qname, org.apache.axis.encoding.SerializationContext context) throws java.io.IOException {
        context.serialize(qname, null, this);
    }
}
