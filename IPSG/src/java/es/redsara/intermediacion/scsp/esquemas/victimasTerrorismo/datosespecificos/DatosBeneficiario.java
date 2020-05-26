/**
 * DatosBeneficiario.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos;

public class DatosBeneficiario  implements java.io.Serializable {
    /* Fecha de nacimiento del beneficiario */
    private java.util.Date fechaNacimiento;

    private java.lang.String poblacionNacimiento;

    //private es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.RelacionParentesco relacionParentesco;

    private es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.DerechoReclamado derechoReclamado;

    public DatosBeneficiario() {
    }

    public DatosBeneficiario(
           java.util.Date fechaNacimiento,
           java.lang.String poblacionNacimiento,
           //es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.RelacionParentesco relacionParentesco,
           es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.DerechoReclamado derechoReclamado) {
           this.fechaNacimiento = fechaNacimiento;
           this.poblacionNacimiento = poblacionNacimiento;
           //this.relacionParentesco = relacionParentesco;
           this.derechoReclamado = derechoReclamado;
    }


    /**
     * Gets the fechaNacimiento value for this DatosBeneficiario.
     * 
     * @return fechaNacimiento   * Fecha de nacimiento del beneficiario
     */
    public java.util.Date getFechaNacimiento() {
        return fechaNacimiento;
    }


    /**
     * Sets the fechaNacimiento value for this DatosBeneficiario.
     * 
     * @param fechaNacimiento   * Fecha de nacimiento del beneficiario
     */
    public void setFechaNacimiento(java.util.Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }


    /**
     * Gets the poblacionNacimiento value for this DatosBeneficiario.
     * 
     * @return poblacionNacimiento
     */
    public java.lang.String getPoblacionNacimiento() {
        return poblacionNacimiento;
    }


    /**
     * Sets the poblacionNacimiento value for this DatosBeneficiario.
     * 
     * @param poblacionNacimiento
     */
    public void setPoblacionNacimiento(java.lang.String poblacionNacimiento) {
        this.poblacionNacimiento = poblacionNacimiento;
    }


    /**
     * Gets the relacionParentesco value for this DatosBeneficiario.
     * 
     * @return relacionParentesco
     */
//    public es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.RelacionParentesco getRelacionParentesco() {
//        return relacionParentesco;
//    }


    /**
     * Sets the relacionParentesco value for this DatosBeneficiario.
     * 
     * @param relacionParentesco
     */
//    public void setRelacionParentesco(es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.RelacionParentesco relacionParentesco) {
//        this.relacionParentesco = relacionParentesco;
//    }


    /**
     * Gets the derechoReclamado value for this DatosBeneficiario.
     * 
     * @return derechoReclamado
     */
    public es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.DerechoReclamado getDerechoReclamado() {
        return derechoReclamado;
    }


    /**
     * Sets the derechoReclamado value for this DatosBeneficiario.
     * 
     * @param derechoReclamado
     */
    public void setDerechoReclamado(es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.DerechoReclamado derechoReclamado) {
        this.derechoReclamado = derechoReclamado;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosBeneficiario)) return false;
        DatosBeneficiario other = (DatosBeneficiario) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.fechaNacimiento==null && other.getFechaNacimiento()==null) || 
             (this.fechaNacimiento!=null &&
              this.fechaNacimiento.equals(other.getFechaNacimiento()))) &&
            ((this.poblacionNacimiento==null && other.getPoblacionNacimiento()==null) || 
             (this.poblacionNacimiento!=null &&
              this.poblacionNacimiento.equals(other.getPoblacionNacimiento()))) &&
//            ((this.relacionParentesco==null && other.getRelacionParentesco()==null) || 
//             (this.relacionParentesco!=null &&
//              this.relacionParentesco.equals(other.getRelacionParentesco()))) &&
            ((this.derechoReclamado==null && other.getDerechoReclamado()==null) || 
             (this.derechoReclamado!=null &&
              this.derechoReclamado.equals(other.getDerechoReclamado())));
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
        if (getFechaNacimiento() != null) {
            _hashCode += getFechaNacimiento().hashCode();
        }
        if (getPoblacionNacimiento() != null) {
            _hashCode += getPoblacionNacimiento().hashCode();
        }
//        if (getRelacionParentesco() != null) {
//            _hashCode += getRelacionParentesco().hashCode();
//        }
        if (getDerechoReclamado() != null) {
            _hashCode += getDerechoReclamado().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosBeneficiario.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">DatosBeneficiario"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaNacimiento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "FechaNacimiento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("poblacionNacimiento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "PoblacionNacimiento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">PoblacionNacimiento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
//        elemField = new org.apache.axis.description.ElementDesc();
//        elemField.setFieldName("relacionParentesco");
//        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "RelacionParentesco"));
//        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">RelacionParentesco"));
//        elemField.setNillable(false);
//        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("derechoReclamado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DerechoReclamado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">DerechoReclamado"));
        elemField.setNillable(true);
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
