/**
 * DatosEspecificosIrpfDatosColaDCDatosConyuge.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos;

public class DatosEspecificosIrpfDatosColaDCDatosConyuge  implements java.io.Serializable {
    private java.lang.String DCLiteral;

    private java.lang.String DCFechaNac;

    private java.lang.String DCMinusvalia;

    public DatosEspecificosIrpfDatosColaDCDatosConyuge() {
    }

    public DatosEspecificosIrpfDatosColaDCDatosConyuge(
           java.lang.String DCLiteral,
           java.lang.String DCFechaNac,
           java.lang.String DCMinusvalia) {
           this.DCLiteral = DCLiteral;
           this.DCFechaNac = DCFechaNac;
           this.DCMinusvalia = DCMinusvalia;
    }


    /**
     * Gets the DCLiteral value for this DatosEspecificosIrpfDatosColaDCDatosConyuge.
     * 
     * @return DCLiteral
     */
    public java.lang.String getDCLiteral() {
        return DCLiteral;
    }


    /**
     * Sets the DCLiteral value for this DatosEspecificosIrpfDatosColaDCDatosConyuge.
     * 
     * @param DCLiteral
     */
    public void setDCLiteral(java.lang.String DCLiteral) {
        this.DCLiteral = DCLiteral;
    }


    /**
     * Gets the DCFechaNac value for this DatosEspecificosIrpfDatosColaDCDatosConyuge.
     * 
     * @return DCFechaNac
     */
    public java.lang.String getDCFechaNac() {
        return DCFechaNac;
    }


    /**
     * Sets the DCFechaNac value for this DatosEspecificosIrpfDatosColaDCDatosConyuge.
     * 
     * @param DCFechaNac
     */
    public void setDCFechaNac(java.lang.String DCFechaNac) {
        this.DCFechaNac = DCFechaNac;
    }


    /**
     * Gets the DCMinusvalia value for this DatosEspecificosIrpfDatosColaDCDatosConyuge.
     * 
     * @return DCMinusvalia
     */
    public java.lang.String getDCMinusvalia() {
        return DCMinusvalia;
    }


    /**
     * Sets the DCMinusvalia value for this DatosEspecificosIrpfDatosColaDCDatosConyuge.
     * 
     * @param DCMinusvalia
     */
    public void setDCMinusvalia(java.lang.String DCMinusvalia) {
        this.DCMinusvalia = DCMinusvalia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosEspecificosIrpfDatosColaDCDatosConyuge)) return false;
        DatosEspecificosIrpfDatosColaDCDatosConyuge other = (DatosEspecificosIrpfDatosColaDCDatosConyuge) obj;
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
        new org.apache.axis.description.TypeDesc(DatosEspecificosIrpfDatosColaDCDatosConyuge.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>>>DatosEspecificos>irpf>DatosCola>DCDatosConyuge"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCLiteral");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DCLiteral"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
