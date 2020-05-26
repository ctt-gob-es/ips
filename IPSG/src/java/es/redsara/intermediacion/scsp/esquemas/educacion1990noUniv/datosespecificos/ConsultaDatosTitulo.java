/**
 * ConsultaDatosTitulo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.educacion1990noUniv.datosespecificos;

/**
 * El Class ConsultaDatosTitulo.
 */
public class ConsultaDatosTitulo  implements java.io.Serializable {
    
    /** el numero titulo. */
    private java.lang.String numeroTitulo;

    /**
     * Crea una nueva consulta datos titulo.
     */
    public ConsultaDatosTitulo() {
    }

    /**
     * Crea una nueva consulta datos titulo.
     *
     * @param numeroTitulo el numero titulo
     */
    public ConsultaDatosTitulo(
           java.lang.String numeroTitulo) {
           this.numeroTitulo = numeroTitulo;
    }


    /**
     * Gets the numeroTitulo value for this ConsultaDatosTitulo.
     * 
     * @return numeroTitulo
     */
    public java.lang.String getNumeroTitulo() {
        return numeroTitulo;
    }


    /**
     * Sets the numeroTitulo value for this ConsultaDatosTitulo.
     *
     * @param numeroTitulo el nuevo numero titulo
     */
    public void setNumeroTitulo(java.lang.String numeroTitulo) {
        this.numeroTitulo = numeroTitulo;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConsultaDatosTitulo)) return false;
        ConsultaDatosTitulo other = (ConsultaDatosTitulo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.numeroTitulo==null && other.getNumeroTitulo()==null) || 
             (this.numeroTitulo!=null &&
              this.numeroTitulo.equals(other.getNumeroTitulo())));
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
        if (getNumeroTitulo() != null) {
            _hashCode += getNumeroTitulo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConsultaDatosTitulo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>Consulta>DatosTitulo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroTitulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "NumeroTitulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">NumeroTitulo"));
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
