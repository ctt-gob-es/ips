/**
 * ConsultarPagoClave.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ePago.gob.es.schemas;

/**
 * El Class ConsultarPagoClave.
 */
public class ConsultarPagoClave  implements java.io.Serializable {
    
    /** el datos pago clave in. */
    private ePago.gob.es.schemas.DefaultDatosPagoClaveIn datosPagoClaveIn;

    /** el id organismo. */
    private int idOrganismo;

    /**
     * Crea una nueva consultar pago clave.
     */
    public ConsultarPagoClave() {
    }

    /**
     * Crea una nueva consultar pago clave.
     *
     * @param datosPagoClaveIn el datos pago clave in
     * @param idOrganismo el id organismo
     */
    public ConsultarPagoClave(
           ePago.gob.es.schemas.DefaultDatosPagoClaveIn datosPagoClaveIn,
           int idOrganismo) {
           this.datosPagoClaveIn = datosPagoClaveIn;
           this.idOrganismo = idOrganismo;
    }


    /**
     * Gets the datosPagoClaveIn value for this ConsultarPagoClave.
     * 
     * @return datosPagoClaveIn
     */
    public ePago.gob.es.schemas.DefaultDatosPagoClaveIn getDatosPagoClaveIn() {
        return datosPagoClaveIn;
    }


    /**
     * Sets the datosPagoClaveIn value for this ConsultarPagoClave.
     *
     * @param datosPagoClaveIn el nuevo datos pago clave in
     */
    public void setDatosPagoClaveIn(ePago.gob.es.schemas.DefaultDatosPagoClaveIn datosPagoClaveIn) {
        this.datosPagoClaveIn = datosPagoClaveIn;
    }


    /**
     * Gets the idOrganismo value for this ConsultarPagoClave.
     * 
     * @return idOrganismo
     */
    public int getIdOrganismo() {
        return idOrganismo;
    }


    /**
     * Sets the idOrganismo value for this ConsultarPagoClave.
     *
     * @param idOrganismo el nuevo id organismo
     */
    public void setIdOrganismo(int idOrganismo) {
        this.idOrganismo = idOrganismo;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultarPagoClave)) return false;
        ConsultarPagoClave other = (ConsultarPagoClave) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.datosPagoClaveIn==null && other.getDatosPagoClaveIn()==null) || 
             (this.datosPagoClaveIn!=null &&
              this.datosPagoClaveIn.equals(other.getDatosPagoClaveIn()))) &&
            this.idOrganismo == other.getIdOrganismo();
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
        if (getDatosPagoClaveIn() != null) {
            _hashCode += getDatosPagoClaveIn().hashCode();
        }
        _hashCode += getIdOrganismo();
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultarPagoClave.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">consultarPagoClave"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosPagoClaveIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "datosPagoClaveIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoClaveIn"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idOrganismo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "idOrganismo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
