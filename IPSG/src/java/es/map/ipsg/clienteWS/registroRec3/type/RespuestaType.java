/**
 * RespuestaType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.ipsg.clienteWS.registroRec3.type;

/**
 * El Class RespuestaType.
 */
public class RespuestaType  implements java.io.Serializable {
    
    /** el cd respuesta. */
    private java.lang.String cdRespuesta;

    /** el ds respuesta. */
    private java.lang.String dsRespuesta;

    /**
     * Crea una nueva respuesta type.
     */
    public RespuestaType() {
    }

    /**
     * Crea una nueva respuesta type.
     *
     * @param cdRespuesta el cd respuesta
     * @param dsRespuesta el ds respuesta
     */
    public RespuestaType(
           java.lang.String cdRespuesta,
           java.lang.String dsRespuesta) {
           this.cdRespuesta = cdRespuesta;
           this.dsRespuesta = dsRespuesta;
    }


    /**
     * Gets the cdRespuesta value for this RespuestaType.
     * 
     * @return cdRespuesta
     */
    public java.lang.String getCdRespuesta() {
        return cdRespuesta;
    }


    /**
     * Sets the cdRespuesta value for this RespuestaType.
     *
     * @param cdRespuesta el nuevo cd respuesta
     */
    public void setCdRespuesta(java.lang.String cdRespuesta) {
        this.cdRespuesta = cdRespuesta;
    }


    /**
     * Gets the dsRespuesta value for this RespuestaType.
     * 
     * @return dsRespuesta
     */
    public java.lang.String getDsRespuesta() {
        return dsRespuesta;
    }


    /**
     * Sets the dsRespuesta value for this RespuestaType.
     *
     * @param dsRespuesta el nuevo ds respuesta
     */
    public void setDsRespuesta(java.lang.String dsRespuesta) {
        this.dsRespuesta = dsRespuesta;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RespuestaType)) return false;
        RespuestaType other = (RespuestaType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cdRespuesta==null && other.getCdRespuesta()==null) || 
             (this.cdRespuesta!=null &&
              this.cdRespuesta.equals(other.getCdRespuesta()))) &&
            ((this.dsRespuesta==null && other.getDsRespuesta()==null) || 
             (this.dsRespuesta!=null &&
              this.dsRespuesta.equals(other.getDsRespuesta())));
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
        if (getCdRespuesta() != null) {
            _hashCode += getCdRespuesta().hashCode();
        }
        if (getDsRespuesta() != null) {
            _hashCode += getDsRespuesta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RespuestaType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://type.ws2.rec2are.map.es", "RespuestaType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdRespuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdRespuesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsRespuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dsRespuesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
