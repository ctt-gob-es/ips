/**
 * Convenio.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.minhap.seap.representa;

/**
 * El Class Convenio.
 */
public class Convenio  implements java.io.Serializable {
    
    /** el id convenio. */
    private java.lang.String idConvenio;

    /** el adherido. */
    private boolean adherido;

    /**
     * Crea una nueva convenio.
     */
    public Convenio() {
    }

    /**
     * Crea una nueva convenio.
     *
     * @param idConvenio el id convenio
     * @param adherido el adherido
     */
    public Convenio(
           java.lang.String idConvenio,
           boolean adherido) {
           this.idConvenio = idConvenio;
           this.adherido = adherido;
    }


    /**
     * Gets the idConvenio value for this Convenio.
     * 
     * @return idConvenio
     */
    public java.lang.String getIdConvenio() {
        return idConvenio;
    }


    /**
     * Sets the idConvenio value for this Convenio.
     *
     * @param idConvenio el nuevo id convenio
     */
    public void setIdConvenio(java.lang.String idConvenio) {
        this.idConvenio = idConvenio;
    }


    /**
     * Gets the adherido value for this Convenio.
     * 
     * @return adherido
     */
    public boolean isAdherido() {
        return adherido;
    }


    /**
     * Sets the adherido value for this Convenio.
     *
     * @param adherido el nuevo adherido
     */
    public void setAdherido(boolean adherido) {
        this.adherido = adherido;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
    	if (obj == null) {return false;}
        if (!(obj instanceof Convenio)) return false;
        Convenio other = (Convenio) obj;
        
        if (this == obj) {return true;}
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = 
            ((this.idConvenio==null && other.getIdConvenio()==null) || 
             (this.idConvenio!=null &&
              this.idConvenio.equals(other.getIdConvenio()))) &&
            this.adherido == other.isAdherido();
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
        if (getIdConvenio() != null) {
            _hashCode += getIdConvenio().hashCode();
        }
        _hashCode += (isAdherido() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Convenio.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "convenio"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idConvenio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "idConvenio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("adherido");
        elemField.setXmlName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "adherido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
