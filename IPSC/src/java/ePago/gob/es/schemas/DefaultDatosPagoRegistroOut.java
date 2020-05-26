/**
 * DefaultDatosPagoRegistroOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ePago.gob.es.schemas;

/**
 * El Class DefaultDatosPagoRegistroOut.
 */
public class DefaultDatosPagoRegistroOut  implements java.io.Serializable {
    
    /** el datos pago out. */
    private ePago.gob.es.schemas.DefaultDatosPagoOut datosPagoOut;

    /** el datos registro out. */
    private ePago.gob.es.schemas.DefaultDatosRegistroOut datosRegistroOut;

    /**
     * Crea una nueva default datos pago registro out.
     */
    public DefaultDatosPagoRegistroOut() {
    }

    /**
     * Crea una nueva default datos pago registro out.
     *
     * @param datosPagoOut el datos pago out
     * @param datosRegistroOut el datos registro out
     */
    public DefaultDatosPagoRegistroOut(
           ePago.gob.es.schemas.DefaultDatosPagoOut datosPagoOut,
           ePago.gob.es.schemas.DefaultDatosRegistroOut datosRegistroOut) {
           this.datosPagoOut = datosPagoOut;
           this.datosRegistroOut = datosRegistroOut;
    }


    /**
     * Gets the datosPagoOut value for this DefaultDatosPagoRegistroOut.
     * 
     * @return datosPagoOut
     */
    public ePago.gob.es.schemas.DefaultDatosPagoOut getDatosPagoOut() {
        return datosPagoOut;
    }


    /**
     * Sets the datosPagoOut value for this DefaultDatosPagoRegistroOut.
     *
     * @param datosPagoOut el nuevo datos pago out
     */
    public void setDatosPagoOut(ePago.gob.es.schemas.DefaultDatosPagoOut datosPagoOut) {
        this.datosPagoOut = datosPagoOut;
    }


    /**
     * Gets the datosRegistroOut value for this DefaultDatosPagoRegistroOut.
     * 
     * @return datosRegistroOut
     */
    public ePago.gob.es.schemas.DefaultDatosRegistroOut getDatosRegistroOut() {
        return datosRegistroOut;
    }


    /**
     * Sets the datosRegistroOut value for this DefaultDatosPagoRegistroOut.
     *
     * @param datosRegistroOut el nuevo datos registro out
     */
    public void setDatosRegistroOut(ePago.gob.es.schemas.DefaultDatosRegistroOut datosRegistroOut) {
        this.datosRegistroOut = datosRegistroOut;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DefaultDatosPagoRegistroOut)) return false;
        DefaultDatosPagoRegistroOut other = (DefaultDatosPagoRegistroOut) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.datosPagoOut==null && other.getDatosPagoOut()==null) || 
             (this.datosPagoOut!=null &&
              this.datosPagoOut.equals(other.getDatosPagoOut()))) &&
            ((this.datosRegistroOut==null && other.getDatosRegistroOut()==null) || 
             (this.datosRegistroOut!=null &&
              this.datosRegistroOut.equals(other.getDatosRegistroOut())));
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
        if (getDatosPagoOut() != null) {
            _hashCode += getDatosPagoOut().hashCode();
        }
        if (getDatosRegistroOut() != null) {
            _hashCode += getDatosRegistroOut().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DefaultDatosPagoRegistroOut.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoRegistroOut"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosPagoOut");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "datosPagoOut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoOut"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosRegistroOut");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "datosRegistroOut"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosRegistroOut"));
        elemField.setNillable(true);
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
