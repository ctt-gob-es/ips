/**
 * ListaRepresentado.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.minhap.seap.representa;

/**
 * El Class ListaRepresentado.
 */
public class ListaRepresentado  implements java.io.Serializable {
    
    /** el nif representado. */
    private java.lang.String nifRepresentado;

    /** el tramites. */
    private es.minhap.seap.representa.Tramite[] tramites;

    /**
     * Crea una nueva lista representado.
     */
    public ListaRepresentado() {
    }

    /**
     * Crea una nueva lista representado.
     *
     * @param nifRepresentado el nif representado
     * @param tramites el tramites
     */
    public ListaRepresentado(
           java.lang.String nifRepresentado,
           es.minhap.seap.representa.Tramite[] tramites) {
           this.nifRepresentado = nifRepresentado;
           this.tramites = tramites;
    }


    /**
     * Gets the nifRepresentado value for this ListaRepresentado.
     * 
     * @return nifRepresentado
     */
    public java.lang.String getNifRepresentado() {
        return nifRepresentado;
    }


    /**
     * Sets the nifRepresentado value for this ListaRepresentado.
     *
     * @param nifRepresentado el nuevo nif representado
     */
    public void setNifRepresentado(java.lang.String nifRepresentado) {
        this.nifRepresentado = nifRepresentado;
    }


    /**
     * Gets the tramites value for this ListaRepresentado.
     * 
     * @return tramites
     */
    public es.minhap.seap.representa.Tramite[] getTramites() {
        return tramites;
    }


    /**
     * Sets the tramites value for this ListaRepresentado.
     *
     * @param tramites el nuevo tramites
     */
    public void setTramites(es.minhap.seap.representa.Tramite[] tramites) {
        this.tramites = tramites;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
    	if (obj == null) { return false;}
        if (!(obj instanceof ListaRepresentado)) return false;
        ListaRepresentado other = (ListaRepresentado) obj;
        
        if (this == obj) { return true;}
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = 
            ((this.nifRepresentado==null && other.getNifRepresentado()==null) || 
             (this.nifRepresentado!=null &&
              this.nifRepresentado.equals(other.getNifRepresentado()))) &&
            ((this.tramites==null && other.getTramites()==null) || 
             (this.tramites!=null &&
              java.util.Arrays.equals(this.tramites, other.getTramites())));
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
        if (getNifRepresentado() != null) {
            _hashCode += getNifRepresentado().hashCode();
        }
        if (getTramites() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getTramites());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getTramites(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListaRepresentado.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "listaRepresentado"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nifRepresentado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "nifRepresentado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tramites");
        elemField.setXmlName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "tramites"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "tramite"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "tramite"));
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
