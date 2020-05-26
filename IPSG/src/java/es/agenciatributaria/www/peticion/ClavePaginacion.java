/**
 * ClavePaginacion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.agenciatributaria.www.peticion;

public class ClavePaginacion  implements java.io.Serializable {
    private java.lang.String codigo;

    private es.agenciatributaria.www.peticion.Tipo tipo;

    public ClavePaginacion() {
    }

    public ClavePaginacion(
           java.lang.String codigo,
           es.agenciatributaria.www.peticion.Tipo tipo) {
           this.codigo = codigo;
           this.tipo = tipo;
    }


    /**
     * Gets the codigo value for this ClavePaginacion.
     * 
     * @return codigo
     */
    public java.lang.String getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this ClavePaginacion.
     * 
     * @param codigo
     */
    public void setCodigo(java.lang.String codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the tipo value for this ClavePaginacion.
     * 
     * @return tipo
     */
    public es.agenciatributaria.www.peticion.Tipo getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this ClavePaginacion.
     * 
     * @param tipo
     */
    public void setTipo(es.agenciatributaria.www.peticion.Tipo tipo) {
        this.tipo = tipo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ClavePaginacion)) return false;
        ClavePaginacion other = (ClavePaginacion) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigo==null && other.getCodigo()==null) || 
             (this.codigo!=null &&
              this.codigo.equals(other.getCodigo()))) &&
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo())));
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
        if (getCodigo() != null) {
            _hashCode += getCodigo().hashCode();
        }
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ClavePaginacion.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/peticion", ">ClavePaginacion"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.agenciatributaria.es/peticion", "Codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/peticion", ">Codigo"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.agenciatributaria.es/peticion", "Tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/peticion", ">Tipo"));
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
