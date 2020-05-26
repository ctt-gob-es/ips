/**
 * DatosEspecificosIrpfDatosColaDCDatosPersonalesDCEstadoCivil.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos;

public class DatosEspecificosIrpfDatosColaDCDatosPersonalesDCEstadoCivil  implements java.io.Serializable {
    private java.lang.String DCFecha;

    private java.lang.String DCContenido;

    public DatosEspecificosIrpfDatosColaDCDatosPersonalesDCEstadoCivil() {
    }

    public DatosEspecificosIrpfDatosColaDCDatosPersonalesDCEstadoCivil(
           java.lang.String DCFecha,
           java.lang.String DCContenido) {
           this.DCFecha = DCFecha;
           this.DCContenido = DCContenido;
    }


    /**
     * Gets the DCFecha value for this DatosEspecificosIrpfDatosColaDCDatosPersonalesDCEstadoCivil.
     * 
     * @return DCFecha
     */
    public java.lang.String getDCFecha() {
        return DCFecha;
    }


    /**
     * Sets the DCFecha value for this DatosEspecificosIrpfDatosColaDCDatosPersonalesDCEstadoCivil.
     * 
     * @param DCFecha
     */
    public void setDCFecha(java.lang.String DCFecha) {
        this.DCFecha = DCFecha;
    }


    /**
     * Gets the DCContenido value for this DatosEspecificosIrpfDatosColaDCDatosPersonalesDCEstadoCivil.
     * 
     * @return DCContenido
     */
    public java.lang.String getDCContenido() {
        return DCContenido;
    }


    /**
     * Sets the DCContenido value for this DatosEspecificosIrpfDatosColaDCDatosPersonalesDCEstadoCivil.
     * 
     * @param DCContenido
     */
    public void setDCContenido(java.lang.String DCContenido) {
        this.DCContenido = DCContenido;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosEspecificosIrpfDatosColaDCDatosPersonalesDCEstadoCivil)) return false;
        DatosEspecificosIrpfDatosColaDCDatosPersonalesDCEstadoCivil other = (DatosEspecificosIrpfDatosColaDCDatosPersonalesDCEstadoCivil) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.DCFecha==null && other.getDCFecha()==null) || 
             (this.DCFecha!=null &&
              this.DCFecha.equals(other.getDCFecha()))) &&
            ((this.DCContenido==null && other.getDCContenido()==null) || 
             (this.DCContenido!=null &&
              this.DCContenido.equals(other.getDCContenido())));
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
        if (getDCFecha() != null) {
            _hashCode += getDCFecha().hashCode();
        }
        if (getDCContenido() != null) {
            _hashCode += getDCContenido().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosEspecificosIrpfDatosColaDCDatosPersonalesDCEstadoCivil.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>>>>DatosEspecificos>irpf>DatosCola>DCDatosPersonales>DCEstadoCivil"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCFecha");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DCFecha"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCContenido");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DCContenido"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
