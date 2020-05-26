/**
 * DatosEspecificosImputacionesDatosEconomicos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos;

public class DatosEspecificosImputacionesDatosEconomicos  implements java.io.Serializable {
    private java.lang.String literal;

    private java.lang.String signo;

    private long enteros;

    private long decimales;

    public DatosEspecificosImputacionesDatosEconomicos() {
    }

    public DatosEspecificosImputacionesDatosEconomicos(
           java.lang.String literal,
           java.lang.String signo,
           long enteros,
           long decimales) {
           this.literal = literal;
           this.signo = signo;
           this.enteros = enteros;
           this.decimales = decimales;
    }


    /**
     * Gets the literal value for this DatosEspecificosImputacionesDatosEconomicos.
     * 
     * @return literal
     */
    public java.lang.String getLiteral() {
        return literal;
    }


    /**
     * Sets the literal value for this DatosEspecificosImputacionesDatosEconomicos.
     * 
     * @param literal
     */
    public void setLiteral(java.lang.String literal) {
        this.literal = literal;
    }


    /**
     * Gets the signo value for this DatosEspecificosImputacionesDatosEconomicos.
     * 
     * @return signo
     */
    public java.lang.String getSigno() {
        return signo;
    }


    /**
     * Sets the signo value for this DatosEspecificosImputacionesDatosEconomicos.
     * 
     * @param signo
     */
    public void setSigno(java.lang.String signo) {
        this.signo = signo;
    }


    /**
     * Gets the enteros value for this DatosEspecificosImputacionesDatosEconomicos.
     * 
     * @return enteros
     */
    public long getEnteros() {
        return enteros;
    }


    /**
     * Sets the enteros value for this DatosEspecificosImputacionesDatosEconomicos.
     * 
     * @param enteros
     */
    public void setEnteros(long enteros) {
        this.enteros = enteros;
    }


    /**
     * Gets the decimales value for this DatosEspecificosImputacionesDatosEconomicos.
     * 
     * @return decimales
     */
    public long getDecimales() {
        return decimales;
    }


    /**
     * Sets the decimales value for this DatosEspecificosImputacionesDatosEconomicos.
     * 
     * @param decimales
     */
    public void setDecimales(long decimales) {
        this.decimales = decimales;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosEspecificosImputacionesDatosEconomicos)) return false;
        DatosEspecificosImputacionesDatosEconomicos other = (DatosEspecificosImputacionesDatosEconomicos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.literal==null && other.getLiteral()==null) || 
             (this.literal!=null &&
              this.literal.equals(other.getLiteral()))) &&
            ((this.signo==null && other.getSigno()==null) || 
             (this.signo!=null &&
              this.signo.equals(other.getSigno()))) &&
            this.enteros == other.getEnteros() &&
            this.decimales == other.getDecimales();
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
        if (getLiteral() != null) {
            _hashCode += getLiteral().hashCode();
        }
        if (getSigno() != null) {
            _hashCode += getSigno().hashCode();
        }
        _hashCode += new Long(getEnteros()).hashCode();
        _hashCode += new Long(getDecimales()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosEspecificosImputacionesDatosEconomicos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>>DatosEspecificos>Imputaciones>DatosEconomicos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("literal");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Literal"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Signo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("enteros");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Enteros"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("decimales");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Decimales"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
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
