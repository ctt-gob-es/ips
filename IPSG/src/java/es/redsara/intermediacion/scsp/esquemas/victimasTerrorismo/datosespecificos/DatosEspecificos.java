/**
 * DatosEspecificos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos;

public class DatosEspecificos  implements java.io.Serializable {
    private es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.DatosEspecificosConsulta consulta;

    private es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.DatosEspecificosRetorno retorno;

    public DatosEspecificos() {
    }

    public DatosEspecificos(
           es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.DatosEspecificosConsulta consulta,
           es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.DatosEspecificosRetorno retorno) {
           this.consulta = consulta;
           this.retorno = retorno;
    }


    /**
     * Gets the consulta value for this DatosEspecificos.
     * 
     * @return consulta
     */
    public es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.DatosEspecificosConsulta getConsulta() {
        return consulta;
    }


    /**
     * Sets the consulta value for this DatosEspecificos.
     * 
     * @param consulta
     */
    public void setConsulta(es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.DatosEspecificosConsulta consulta) {
        this.consulta = consulta;
    }


    /**
     * Gets the retorno value for this DatosEspecificos.
     * 
     * @return retorno
     */
    public es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.DatosEspecificosRetorno getRetorno() {
        return retorno;
    }


    /**
     * Sets the retorno value for this DatosEspecificos.
     * 
     * @param retorno
     */
    public void setRetorno(es.redsara.intermediacion.scsp.esquemas.victimasTerrorismo.datosespecificos.DatosEspecificosRetorno retorno) {
        this.retorno = retorno;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosEspecificos)) return false;
        DatosEspecificos other = (DatosEspecificos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.consulta==null && other.getConsulta()==null) || 
             (this.consulta!=null &&
              this.consulta.equals(other.getConsulta()))) &&
            ((this.retorno==null && other.getRetorno()==null) || 
             (this.retorno!=null &&
              this.retorno.equals(other.getRetorno())));
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
        if (getConsulta() != null) {
            _hashCode += getConsulta().hashCode();
        }
        if (getRetorno() != null) {
            _hashCode += getRetorno().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosEspecificos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DatosEspecificos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("consulta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Consulta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">DatosEspecificos>Consulta"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("retorno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Retorno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">DatosEspecificos>Retorno"));
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
