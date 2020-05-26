/**
 * DatosEspecificosIrpfDatosColaDCDatosPersonales.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos;

public class DatosEspecificosIrpfDatosColaDCDatosPersonales  implements java.io.Serializable {
    private java.lang.String DCLiteral;

    private es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfDatosColaDCDatosPersonalesDCEstadoCivil DCEstadoCivil;

    private java.lang.String DCFechaNac;

    private java.lang.String DCMinusvalia;

    public DatosEspecificosIrpfDatosColaDCDatosPersonales() {
    }

    public DatosEspecificosIrpfDatosColaDCDatosPersonales(
           java.lang.String DCLiteral,
           es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfDatosColaDCDatosPersonalesDCEstadoCivil DCEstadoCivil,
           java.lang.String DCFechaNac,
           java.lang.String DCMinusvalia) {
           this.DCLiteral = DCLiteral;
           this.DCEstadoCivil = DCEstadoCivil;
           this.DCFechaNac = DCFechaNac;
           this.DCMinusvalia = DCMinusvalia;
    }


    /**
     * Gets the DCLiteral value for this DatosEspecificosIrpfDatosColaDCDatosPersonales.
     * 
     * @return DCLiteral
     */
    public java.lang.String getDCLiteral() {
        return DCLiteral;
    }


    /**
     * Sets the DCLiteral value for this DatosEspecificosIrpfDatosColaDCDatosPersonales.
     * 
     * @param DCLiteral
     */
    public void setDCLiteral(java.lang.String DCLiteral) {
        this.DCLiteral = DCLiteral;
    }


    /**
     * Gets the DCEstadoCivil value for this DatosEspecificosIrpfDatosColaDCDatosPersonales.
     * 
     * @return DCEstadoCivil
     */
    public es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfDatosColaDCDatosPersonalesDCEstadoCivil getDCEstadoCivil() {
        return DCEstadoCivil;
    }


    /**
     * Sets the DCEstadoCivil value for this DatosEspecificosIrpfDatosColaDCDatosPersonales.
     * 
     * @param DCEstadoCivil
     */
    public void setDCEstadoCivil(es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfDatosColaDCDatosPersonalesDCEstadoCivil DCEstadoCivil) {
        this.DCEstadoCivil = DCEstadoCivil;
    }


    /**
     * Gets the DCFechaNac value for this DatosEspecificosIrpfDatosColaDCDatosPersonales.
     * 
     * @return DCFechaNac
     */
    public java.lang.String getDCFechaNac() {
        return DCFechaNac;
    }


    /**
     * Sets the DCFechaNac value for this DatosEspecificosIrpfDatosColaDCDatosPersonales.
     * 
     * @param DCFechaNac
     */
    public void setDCFechaNac(java.lang.String DCFechaNac) {
        this.DCFechaNac = DCFechaNac;
    }


    /**
     * Gets the DCMinusvalia value for this DatosEspecificosIrpfDatosColaDCDatosPersonales.
     * 
     * @return DCMinusvalia
     */
    public java.lang.String getDCMinusvalia() {
        return DCMinusvalia;
    }


    /**
     * Sets the DCMinusvalia value for this DatosEspecificosIrpfDatosColaDCDatosPersonales.
     * 
     * @param DCMinusvalia
     */
    public void setDCMinusvalia(java.lang.String DCMinusvalia) {
        this.DCMinusvalia = DCMinusvalia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosEspecificosIrpfDatosColaDCDatosPersonales)) return false;
        DatosEspecificosIrpfDatosColaDCDatosPersonales other = (DatosEspecificosIrpfDatosColaDCDatosPersonales) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.DCLiteral==null && other.getDCLiteral()==null) || 
             (this.DCLiteral!=null &&
              this.DCLiteral.equals(other.getDCLiteral()))) &&
            ((this.DCEstadoCivil==null && other.getDCEstadoCivil()==null) || 
             (this.DCEstadoCivil!=null &&
              this.DCEstadoCivil.equals(other.getDCEstadoCivil()))) &&
            ((this.DCFechaNac==null && other.getDCFechaNac()==null) || 
             (this.DCFechaNac!=null &&
              this.DCFechaNac.equals(other.getDCFechaNac()))) &&
            ((this.DCMinusvalia==null && other.getDCMinusvalia()==null) || 
             (this.DCMinusvalia!=null &&
              this.DCMinusvalia.equals(other.getDCMinusvalia())));
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
        if (getDCLiteral() != null) {
            _hashCode += getDCLiteral().hashCode();
        }
        if (getDCEstadoCivil() != null) {
            _hashCode += getDCEstadoCivil().hashCode();
        }
        if (getDCFechaNac() != null) {
            _hashCode += getDCFechaNac().hashCode();
        }
        if (getDCMinusvalia() != null) {
            _hashCode += getDCMinusvalia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosEspecificosIrpfDatosColaDCDatosPersonales.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>>>DatosEspecificos>irpf>DatosCola>DCDatosPersonales"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCLiteral");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DCLiteral"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCEstadoCivil");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DCEstadoCivil"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>>>>DatosEspecificos>irpf>DatosCola>DCDatosPersonales>DCEstadoCivil"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCFechaNac");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DCFechaNac"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCMinusvalia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DCMinusvalia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
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
