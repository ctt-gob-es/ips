/**
 * DatosEspecificosIrpfNivelRenta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos;

public class DatosEspecificosIrpfNivelRenta  implements java.io.Serializable {
    private java.lang.String NRLiteral;

    private java.lang.String NRSigno;

    private long NREnteros;

    private long NRDecimales;

    public DatosEspecificosIrpfNivelRenta() {
    }

    public DatosEspecificosIrpfNivelRenta(
           java.lang.String NRLiteral,
           java.lang.String NRSigno,
           long NREnteros,
           long NRDecimales) {
           this.NRLiteral = NRLiteral;
           this.NRSigno = NRSigno;
           this.NREnteros = NREnteros;
           this.NRDecimales = NRDecimales;
    }


    /**
     * Gets the NRLiteral value for this DatosEspecificosIrpfNivelRenta.
     * 
     * @return NRLiteral
     */
    public java.lang.String getNRLiteral() {
        return NRLiteral;
    }


    /**
     * Sets the NRLiteral value for this DatosEspecificosIrpfNivelRenta.
     * 
     * @param NRLiteral
     */
    public void setNRLiteral(java.lang.String NRLiteral) {
        this.NRLiteral = NRLiteral;
    }


    /**
     * Gets the NRSigno value for this DatosEspecificosIrpfNivelRenta.
     * 
     * @return NRSigno
     */
    public java.lang.String getNRSigno() {
        return NRSigno;
    }


    /**
     * Sets the NRSigno value for this DatosEspecificosIrpfNivelRenta.
     * 
     * @param NRSigno
     */
    public void setNRSigno(java.lang.String NRSigno) {
        this.NRSigno = NRSigno;
    }


    /**
     * Gets the NREnteros value for this DatosEspecificosIrpfNivelRenta.
     * 
     * @return NREnteros
     */
    public long getNREnteros() {
        return NREnteros;
    }


    /**
     * Sets the NREnteros value for this DatosEspecificosIrpfNivelRenta.
     * 
     * @param NREnteros
     */
    public void setNREnteros(long NREnteros) {
        this.NREnteros = NREnteros;
    }


    /**
     * Gets the NRDecimales value for this DatosEspecificosIrpfNivelRenta.
     * 
     * @return NRDecimales
     */
    public long getNRDecimales() {
        return NRDecimales;
    }


    /**
     * Sets the NRDecimales value for this DatosEspecificosIrpfNivelRenta.
     * 
     * @param NRDecimales
     */
    public void setNRDecimales(long NRDecimales) {
        this.NRDecimales = NRDecimales;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosEspecificosIrpfNivelRenta)) return false;
        DatosEspecificosIrpfNivelRenta other = (DatosEspecificosIrpfNivelRenta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.NRLiteral==null && other.getNRLiteral()==null) || 
             (this.NRLiteral!=null &&
              this.NRLiteral.equals(other.getNRLiteral()))) &&
            ((this.NRSigno==null && other.getNRSigno()==null) || 
             (this.NRSigno!=null &&
              this.NRSigno.equals(other.getNRSigno()))) &&
            this.NREnteros == other.getNREnteros() &&
            this.NRDecimales == other.getNRDecimales();
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
        if (getNRLiteral() != null) {
            _hashCode += getNRLiteral().hashCode();
        }
        if (getNRSigno() != null) {
            _hashCode += getNRSigno().hashCode();
        }
        _hashCode += new Long(getNREnteros()).hashCode();
        _hashCode += new Long(getNRDecimales()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosEspecificosIrpfNivelRenta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>>DatosEspecificos>irpf>NivelRenta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NRLiteral");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "NRLiteral"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NRSigno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "NRSigno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NREnteros");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "NREnteros"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NRDecimales");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "NRDecimales"));
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
