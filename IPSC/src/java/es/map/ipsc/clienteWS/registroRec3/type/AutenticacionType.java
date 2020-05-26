/**
 * AutenticacionType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.ipsc.clienteWS.registroRec3.type;

/**
 * El Class AutenticacionType.
 */
public class AutenticacionType  implements java.io.Serializable {
    
    /** el id aplicacion. */
    private java.lang.String idAplicacion;

    /** el password. */
    private java.lang.String password;

    /**
     * Crea una nueva autenticacion type.
     */
    public AutenticacionType() {
    }

    /**
     * Crea una nueva autenticacion type.
     *
     * @param idAplicacion el id aplicacion
     * @param password el password
     */
    public AutenticacionType(
           java.lang.String idAplicacion,
           java.lang.String password) {
           this.idAplicacion = idAplicacion;
           this.password = password;
    }


    /**
     * Gets the idAplicacion value for this AutenticacionType.
     * 
     * @return idAplicacion
     */
    public java.lang.String getIdAplicacion() {
        return idAplicacion;
    }


    /**
     * Sets the idAplicacion value for this AutenticacionType.
     *
     * @param idAplicacion el nuevo id aplicacion
     */
    public void setIdAplicacion(java.lang.String idAplicacion) {
        this.idAplicacion = idAplicacion;
    }


    /**
     * Gets the password value for this AutenticacionType.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this AutenticacionType.
     *
     * @param password el nuevo password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
    	if (obj == null) {
    		return false;
    	}
        if (!(obj instanceof AutenticacionType)) {
        	return false;
        }
        AutenticacionType other = (AutenticacionType) obj;
        
        if (this == obj) {
        	return true;
        }
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals =  
            ((this.idAplicacion==null && other.getIdAplicacion()==null) || 
             (this.idAplicacion!=null &&
              this.idAplicacion.equals(other.getIdAplicacion()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword())));
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
        if (getIdAplicacion() != null) {
            _hashCode += getIdAplicacion().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AutenticacionType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://type.ws2.rec2are.map.es", "AutenticacionType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idAplicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idAplicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("", "password"));
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
