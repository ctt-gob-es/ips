/**
 * VerificarNRCResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ePago.gob.es.schemas;

/**
 * El Class VerificarNRCResponse.
 */
public class VerificarNRCResponse  implements java.io.Serializable {
    
    /** el verificar NRC return. */
    private ePago.gob.es.schemas.DefaultDatosNRCOut verificarNRCReturn;

    /**
     * Crea una nueva verificar NRC response.
     */
    public VerificarNRCResponse() {
    }

    /**
     * Crea una nueva verificar NRC response.
     *
     * @param verificarNRCReturn el verificar NRC return
     */
    public VerificarNRCResponse(
           ePago.gob.es.schemas.DefaultDatosNRCOut verificarNRCReturn) {
           this.verificarNRCReturn = verificarNRCReturn;
    }


    /**
     * Gets the verificarNRCReturn value for this VerificarNRCResponse.
     * 
     * @return verificarNRCReturn
     */
    public ePago.gob.es.schemas.DefaultDatosNRCOut getVerificarNRCReturn() {
        return verificarNRCReturn;
    }


    /**
     * Sets the verificarNRCReturn value for this VerificarNRCResponse.
     *
     * @param verificarNRCReturn el nuevo verificar NRC return
     */
    public void setVerificarNRCReturn(ePago.gob.es.schemas.DefaultDatosNRCOut verificarNRCReturn) {
        this.verificarNRCReturn = verificarNRCReturn;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VerificarNRCResponse)) return false;
        VerificarNRCResponse other = (VerificarNRCResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.verificarNRCReturn==null && other.getVerificarNRCReturn()==null) || 
             (this.verificarNRCReturn!=null &&
              this.verificarNRCReturn.equals(other.getVerificarNRCReturn())));
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
        if (getVerificarNRCReturn() != null) {
            _hashCode += getVerificarNRCReturn().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VerificarNRCResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">verificarNRCResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("verificarNRCReturn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "verificarNRCReturn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosNRCOut"));
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
