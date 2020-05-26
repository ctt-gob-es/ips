/**
 * GetJustificante.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ePago.gob.es.schemas;

/**
 * El Class GetJustificante.
 */
public class GetJustificante  implements java.io.Serializable {
    
    /** el modelo. */
    private java.lang.String modelo;

    /** el codigo tasa. */
    private java.lang.String codigoTasa;

    /** el id organismo. */
    private int idOrganismo;

    /**
     * Crea una nueva obtiene el justificante.
     */
    public GetJustificante() {
    }

    /**
     * Crea una nueva obtiene el justificante.
     *
     * @param modelo el modelo
     * @param codigoTasa el codigo tasa
     * @param idOrganismo el id organismo
     */
    public GetJustificante(
           java.lang.String modelo,
           java.lang.String codigoTasa,
           int idOrganismo) {
           this.modelo = modelo;
           this.codigoTasa = codigoTasa;
           this.idOrganismo = idOrganismo;
    }


    /**
     * Gets the modelo value for this GetJustificante.
     * 
     * @return modelo
     */
    public java.lang.String getModelo() {
        return modelo;
    }


    /**
     * Sets the modelo value for this GetJustificante.
     *
     * @param modelo el nuevo modelo
     */
    public void setModelo(java.lang.String modelo) {
        this.modelo = modelo;
    }


    /**
     * Gets the codigoTasa value for this GetJustificante.
     * 
     * @return codigoTasa
     */
    public java.lang.String getCodigoTasa() {
        return codigoTasa;
    }


    /**
     * Sets the codigoTasa value for this GetJustificante.
     *
     * @param codigoTasa el nuevo codigo tasa
     */
    public void setCodigoTasa(java.lang.String codigoTasa) {
        this.codigoTasa = codigoTasa;
    }


    /**
     * Gets the idOrganismo value for this GetJustificante.
     * 
     * @return idOrganismo
     */
    public int getIdOrganismo() {
        return idOrganismo;
    }


    /**
     * Sets the idOrganismo value for this GetJustificante.
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
        if (!(obj instanceof GetJustificante)) return false;
        GetJustificante other = (GetJustificante) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.modelo==null && other.getModelo()==null) || 
             (this.modelo!=null &&
              this.modelo.equals(other.getModelo()))) &&
            ((this.codigoTasa==null && other.getCodigoTasa()==null) || 
             (this.codigoTasa!=null &&
              this.codigoTasa.equals(other.getCodigoTasa()))) &&
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
        if (getModelo() != null) {
            _hashCode += getModelo().hashCode();
        }
        if (getCodigoTasa() != null) {
            _hashCode += getCodigoTasa().hashCode();
        }
        _hashCode += getIdOrganismo();
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetJustificante.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", ">getJustificante"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modelo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "modelo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTasa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "codigoTasa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
