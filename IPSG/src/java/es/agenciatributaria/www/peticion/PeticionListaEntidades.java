/**
 * PeticionListaEntidades.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.agenciatributaria.www.peticion;

public class PeticionListaEntidades  implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private java.lang.String NIFPeticionario;

    private javax.xml.datatype.XMLGregorianCalendar timestamp;

    private es.agenciatributaria.www.peticion.ClavePaginacion clavePaginacion;

    public PeticionListaEntidades() {
    }

    public PeticionListaEntidades(
           java.lang.String NIFPeticionario,
           javax.xml.datatype.XMLGregorianCalendar timestamp,
           es.agenciatributaria.www.peticion.ClavePaginacion clavePaginacion) {
           this.NIFPeticionario = NIFPeticionario;
           this.timestamp = timestamp;
           this.clavePaginacion = clavePaginacion;
    }


    /**
     * Gets the NIFPeticionario value for this PeticionListaEntidades.
     * 
     * @return NIFPeticionario
     */
    public java.lang.String getNIFPeticionario() {
        return NIFPeticionario;
    }


    /**
     * Sets the NIFPeticionario value for this PeticionListaEntidades.
     * 
     * @param NIFPeticionario
     */
    public void setNIFPeticionario(java.lang.String NIFPeticionario) {
        this.NIFPeticionario = NIFPeticionario;
    }


    /**
     * Gets the timestamp value for this PeticionListaEntidades.
     * 
     * @return timestamp
     */
    public javax.xml.datatype.XMLGregorianCalendar getTimestamp() {
        return timestamp;
    }


    /**
     * Sets the timestamp value for this PeticionListaEntidades.
     * 
     * @param timestamp
     */
    public void setTimestamp(javax.xml.datatype.XMLGregorianCalendar timestamp) {
        this.timestamp = timestamp;
    }


    /**
     * Gets the clavePaginacion value for this PeticionListaEntidades.
     * 
     * @return clavePaginacion
     */
    public es.agenciatributaria.www.peticion.ClavePaginacion getClavePaginacion() {
        return clavePaginacion;
    }


    /**
     * Sets the clavePaginacion value for this PeticionListaEntidades.
     * 
     * @param clavePaginacion
     */
    public void setClavePaginacion(es.agenciatributaria.www.peticion.ClavePaginacion clavePaginacion) {
        this.clavePaginacion = clavePaginacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PeticionListaEntidades)) return false;
        PeticionListaEntidades other = (PeticionListaEntidades) obj;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.NIFPeticionario==null && other.getNIFPeticionario()==null) || 
             (this.NIFPeticionario!=null &&
              this.NIFPeticionario.equals(other.getNIFPeticionario()))) &&
            ((this.timestamp==null && other.getTimestamp()==null) || 
             (this.timestamp!=null &&
              this.timestamp.equals(other.getTimestamp()))) &&
            ((this.clavePaginacion==null && other.getClavePaginacion()==null) || 
             (this.clavePaginacion!=null &&
              this.clavePaginacion.equals(other.getClavePaginacion())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getNIFPeticionario() != null) {
            _hashCode += getNIFPeticionario().hashCode();
        }
        if (getTimestamp() != null) {
            _hashCode += getTimestamp().hashCode();
        }
        if (getClavePaginacion() != null) {
            _hashCode += getClavePaginacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PeticionListaEntidades.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/peticion", ">PeticionListaEntidades"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NIFPeticionario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.agenciatributaria.es/peticion", "NIFPeticionario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/peticion", ">NIFPeticionario"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("timestamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.agenciatributaria.es/peticion", "Timestamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/peticion", ">Timestamp"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clavePaginacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.agenciatributaria.es/peticion", "ClavePaginacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/peticion", ">ClavePaginacion"));
        elemField.setNillable(true);
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
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
     * Get Custom Deserializer
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
