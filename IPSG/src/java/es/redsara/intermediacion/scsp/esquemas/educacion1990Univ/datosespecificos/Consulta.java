/**
 * Consulta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.educacion1990Univ.datosespecificos;

/**
 * El Class Consulta.
 */
public class Consulta  implements java.io.Serializable {
    
    /** el datos titular. */
    /* Datos del titular a consultar */
    private ConsultaDatosTitular datosTitular;

    /** el datos titulo. */
    /* Datos del título a consultar */
    private ConsultaDatosTitulo datosTitulo;

    /**
     * Crea una nueva consulta.
     */
    public Consulta() {
    }

    /**
     * Crea una nueva consulta.
     *
     * @param datosTitular el datos titular
     * @param datosTitulo el datos titulo
     */
    public Consulta(
           ConsultaDatosTitular datosTitular,
           ConsultaDatosTitulo datosTitulo) {
           this.datosTitular = datosTitular;
           this.datosTitulo = datosTitulo;
    }


    /**
     * Gets the datosTitular value for this Consulta.
     * 
     * @return datosTitular   * Datos del titular a consultar
     */
    public ConsultaDatosTitular getDatosTitular() {
        return datosTitular;
    }


    /**
     * Sets the datosTitular value for this Consulta.
     * 
     * @param datosTitular   * Datos del titular a consultar
     */
    public void setDatosTitular(ConsultaDatosTitular datosTitular) {
        this.datosTitular = datosTitular;
    }


    /**
     * Gets the datosTitulo value for this Consulta.
     * 
     * @return datosTitulo   * Datos del título a consultar
     */
    public ConsultaDatosTitulo getDatosTitulo() {
        return datosTitulo;
    }


    /**
     * Sets the datosTitulo value for this Consulta.
     * 
     * @param datosTitulo   * Datos del título a consultar
     */
    public void setDatosTitulo(ConsultaDatosTitulo datosTitulo) {
        this.datosTitulo = datosTitulo;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Consulta)) return false;
        Consulta other = (Consulta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.datosTitular==null && other.getDatosTitular()==null) || 
             (this.datosTitular!=null &&
              this.datosTitular.equals(other.getDatosTitular()))) &&
            ((this.datosTitulo==null && other.getDatosTitulo()==null) || 
             (this.datosTitulo!=null &&
              this.datosTitulo.equals(other.getDatosTitulo())));
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
        if (getDatosTitular() != null) {
            _hashCode += getDatosTitular().hashCode();
        }
        if (getDatosTitulo() != null) {
            _hashCode += getDatosTitulo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Consulta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">Consulta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosTitular");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DatosTitular"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>Consulta>DatosTitular"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosTitulo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DatosTitulo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>Consulta>DatosTitulo"));
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
