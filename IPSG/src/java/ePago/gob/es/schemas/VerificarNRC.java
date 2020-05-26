/**
 * VerificarNRC.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ePago.gob.es.schemas;

/**
 * El Class VerificarNRC.
 */
public class VerificarNRC  implements java.io.Serializable {
    
    /** el datos NRC in. */
    private ePago.gob.es.schemas.DefaultDatosNRCIn datosNRCIn;

    /** el id organismo. */
    private int idOrganismo;

    /**
     * Crea una nueva verificar NRC.
     */
    public VerificarNRC() {
    }

    /**
     * Crea una nueva verificar NRC.
     *
     * @param datosNRCIn el datos NRC in
     * @param idOrganismo el id organismo
     */
    public VerificarNRC(
           ePago.gob.es.schemas.DefaultDatosNRCIn datosNRCIn,
           int idOrganismo) {
           this.datosNRCIn = datosNRCIn;
           this.idOrganismo = idOrganismo;
    }


    /**
     * Gets the datosNRCIn value for this VerificarNRC.
     * 
     * @return datosNRCIn
     */
    public ePago.gob.es.schemas.DefaultDatosNRCIn getDatosNRCIn() {
        return datosNRCIn;
    }


    /**
     * Sets the datosNRCIn value for this VerificarNRC.
     *
     * @param datosNRCIn el nuevo datos NRC in
     */
    public void setDatosNRCIn(ePago.gob.es.schemas.DefaultDatosNRCIn datosNRCIn) {
        this.datosNRCIn = datosNRCIn;
    }


    /**
     * Gets the idOrganismo value for this VerificarNRC.
     * 
     * @return idOrganismo
     */
    public int getIdOrganismo() {
        return idOrganismo;
    }


    /**
     * Sets the idOrganismo value for this VerificarNRC.
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
        if (!(obj instanceof VerificarNRC)) return false;
        VerificarNRC other = (VerificarNRC) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.datosNRCIn==null && other.getDatosNRCIn()==null) || 
             (this.datosNRCIn!=null &&
              this.datosNRCIn.equals(other.getDatosNRCIn()))) &&
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
        if (getDatosNRCIn() != null) {
            _hashCode += getDatosNRCIn().hashCode();
        }
        _hashCode += getIdOrganismo();
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VerificarNRC.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">verificarNRC"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosNRCIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "datosNRCIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosNRCIn"));
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
