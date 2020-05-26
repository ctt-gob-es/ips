/**
 * RepresentaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.minhap.seap.representa;

/**
 * El Class RepresentaRequest.
 */
public class RepresentaRequest  implements java.io.Serializable {
    
    /** el nif. */
    private java.lang.String nif;

    /** el colectivos. */
    private es.minhap.seap.representa.ColectivoRequest[] colectivos;

    /**
     * Crea una nueva representa request.
     */
    public RepresentaRequest() {
    }

    /**
     * Crea una nueva representa request.
     *
     * @param nif el nif
     * @param colectivos el colectivos
     */
    public RepresentaRequest(
           java.lang.String nif,
           es.minhap.seap.representa.ColectivoRequest[] colectivos) {
           this.nif = nif;
           this.colectivos = colectivos;
    }


    /**
     * Gets the nif value for this RepresentaRequest.
     * 
     * @return nif
     */
    public java.lang.String getNif() {
        return nif;
    }


    /**
     * Sets the nif value for this RepresentaRequest.
     *
     * @param nif el nuevo nif
     */
    public void setNif(java.lang.String nif) {
        this.nif = nif;
    }


    /**
     * Gets the colectivos value for this RepresentaRequest.
     * 
     * @return colectivos
     */
    public es.minhap.seap.representa.ColectivoRequest[] getColectivos() {
        return colectivos;
    }


    /**
     * Sets the colectivos value for this RepresentaRequest.
     *
     * @param colectivos el nuevo colectivos
     */
    public void setColectivos(es.minhap.seap.representa.ColectivoRequest[] colectivos) {
        this.colectivos = colectivos;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
    	if (obj == null) {return false;}
        if (!(obj instanceof RepresentaRequest)) return false;
        RepresentaRequest other = (RepresentaRequest) obj;
        
        if (this == obj) {return true;}
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals =  
            ((this.nif==null && other.getNif()==null) || 
             (this.nif!=null &&
              this.nif.equals(other.getNif()))) &&
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
        if (getNif() != null) {
            _hashCode += getNif().hashCode();
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
        new org.apache.axis.description.TypeDesc(RepresentaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://representa.seap.minhap.es", ">RepresentaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nif");
        elemField.setXmlName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "nif"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("colectivos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "colectivos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://representa.seap.minhap.es", "ColectivoRequest"));
        elemField.setMinOccurs(0);
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
