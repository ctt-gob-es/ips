/**
 * RepresentaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.minhap.seap.representa;

/**
 * El Class RepresentaResponse.
 */
public class RepresentaResponse  implements java.io.Serializable {
    
    /** el error. */
    private es.minhap.seap.representa.Error error;

    /** el colectivos. */
    private es.minhap.seap.representa.Colectivo[] colectivos;

    /**
     * Crea una nueva representa response.
     */
    public RepresentaResponse() {
    }

    /**
     * Crea una nueva representa response.
     *
     * @param error el error
     * @param colectivos el colectivos
     */
    public RepresentaResponse(
           es.minhap.seap.representa.Error error,
           es.minhap.seap.representa.Colectivo[] colectivos) {
           this.error = error;
           this.colectivos = colectivos;
    }


    /**
     * Gets the error value for this RepresentaResponse.
     * 
     * @return error
     */
    public es.minhap.seap.representa.Error getError() {
        return error;
    }


    /**
     * Sets the error value for this RepresentaResponse.
     *
     * @param error el nuevo error
     */
    public void setError(es.minhap.seap.representa.Error error) {
        this.error = error;
    }


    /**
     * Gets the colectivos value for this RepresentaResponse.
     * 
     * @return colectivos
     */
    public es.minhap.seap.representa.Colectivo[] getColectivos() {
        return colectivos;
    }


    /**
     * Sets the colectivos value for this RepresentaResponse.
     *
     * @param colectivos el nuevo colectivos
     */
    public void setColectivos(es.minhap.seap.representa.Colectivo[] colectivos) {
        this.colectivos = colectivos;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
    	if (obj == null) {return false;}
        if (!(obj instanceof RepresentaResponse)) return false;
        RepresentaResponse other = (RepresentaResponse) obj;
       
        if (this == obj) {return true;}
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals =  
            ((this.error==null && other.getError()==null) || 
             (this.error!=null &&
              this.error.equals(other.getError()))) &&
            ((this.colectivos==null && other.getColectivos()==null) || 
             (this.colectivos!=null &&
              java.util.Arrays.equals(this.colectivos, other.getColectivos())));
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
        if (getError() != null) {
            _hashCode += getError().hashCode();
        }
        if (getColectivos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getColectivos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getColectivos(), i);
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
        new org.apache.axis.description.TypeDesc(RepresentaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://representa.seap.minhap.es", ">RepresentaResponse"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("error");
        elemField.setXmlName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "error"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "error"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("colectivos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "colectivos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "colectivo"));
        elemField.setNillable(false);
        elemField.setItemQName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "colectivo"));
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
