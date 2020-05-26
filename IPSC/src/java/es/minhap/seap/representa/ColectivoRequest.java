/**
 * ColectivoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.minhap.seap.representa;

/**
 * El Class ColectivoRequest.
 */
public class ColectivoRequest  implements java.io.Serializable {
    
    /** el id colectivo. */
    private es.minhap.seap.representa.TipoColectivoEnum id_colectivo;

    /** el convenio. */
    private java.lang.String convenio;

    /**
     * Crea una nueva colectivo request.
     */
    public ColectivoRequest() {
    }

    /**
     * Crea una nueva colectivo request.
     *
     * @param id_colectivo el id colectivo
     * @param convenio el convenio
     */
    public ColectivoRequest(
           es.minhap.seap.representa.TipoColectivoEnum id_colectivo,
           java.lang.String convenio) {
           this.id_colectivo = id_colectivo;
           this.convenio = convenio;
    }


    /**
     * Gets the id_colectivo value for this ColectivoRequest.
     * 
     * @return id_colectivo
     */
    public es.minhap.seap.representa.TipoColectivoEnum getId_colectivo() {
        return id_colectivo;
    }


    /**
     * Sets the id_colectivo value for this ColectivoRequest.
     *
     * @param id_colectivo el nuevo id colectivo
     */
    public void setId_colectivo(es.minhap.seap.representa.TipoColectivoEnum id_colectivo) {
        this.id_colectivo = id_colectivo;
    }


    /**
     * Gets the convenio value for this ColectivoRequest.
     * 
     * @return convenio
     */
    public java.lang.String getConvenio() {
        return convenio;
    }


    /**
     * Sets the convenio value for this ColectivoRequest.
     *
     * @param convenio el nuevo convenio
     */
    public void setConvenio(java.lang.String convenio) {
        this.convenio = convenio;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
    	if (obj == null) {return false;}
        if (!(obj instanceof ColectivoRequest)) return false;
        ColectivoRequest other = (ColectivoRequest) obj;
        
        if (this == obj) {return true;}
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals =
            ((this.id_colectivo==null && other.getId_colectivo()==null) || 
             (this.id_colectivo!=null &&
              this.id_colectivo.equals(other.getId_colectivo()))) &&
            ((this.convenio==null && other.getConvenio()==null) || 
             (this.convenio!=null &&
              this.convenio.equals(other.getConvenio())));
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
        if (getId_colectivo() != null) {
            _hashCode += getId_colectivo().hashCode();
        }
        if (getConvenio() != null) {
            _hashCode += getConvenio().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ColectivoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "ColectivoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id_colectivo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "id_colectivo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "tipoColectivoEnum"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("convenio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "convenio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
