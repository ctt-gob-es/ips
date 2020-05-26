/**
 * Estado.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.agenciatributaria.www.respuesta;

public class Estado  implements java.io.Serializable {
    private java.lang.String codigoEst;

    private java.lang.String descripcionEst;

    private es.agenciatributaria.www.respuesta.IndicadorPaginacion indicadorPaginacion;

    public Estado() {
    }

    public Estado(
           java.lang.String codigoEst,
           java.lang.String descripcionEst,
           es.agenciatributaria.www.respuesta.IndicadorPaginacion indicadorPaginacion) {
           this.codigoEst = codigoEst;
           this.descripcionEst = descripcionEst;
           this.indicadorPaginacion = indicadorPaginacion;
    }


    /**
     * Gets the codigoEst value for this Estado.
     * 
     * @return codigoEst
     */
    public java.lang.String getCodigoEst() {
        return codigoEst;
    }


    /**
     * Sets the codigoEst value for this Estado.
     * 
     * @param codigoEst
     */
    public void setCodigoEst(java.lang.String codigoEst) {
        this.codigoEst = codigoEst;
    }


    /**
     * Gets the descripcionEst value for this Estado.
     * 
     * @return descripcionEst
     */
    public java.lang.String getDescripcionEst() {
        return descripcionEst;
    }


    /**
     * Sets the descripcionEst value for this Estado.
     * 
     * @param descripcionEst
     */
    public void setDescripcionEst(java.lang.String descripcionEst) {
        this.descripcionEst = descripcionEst;
    }


    /**
     * Gets the indicadorPaginacion value for this Estado.
     * 
     * @return indicadorPaginacion
     */
    public es.agenciatributaria.www.respuesta.IndicadorPaginacion getIndicadorPaginacion() {
        return indicadorPaginacion;
    }


    /**
     * Sets the indicadorPaginacion value for this Estado.
     * 
     * @param indicadorPaginacion
     */
    public void setIndicadorPaginacion(es.agenciatributaria.www.respuesta.IndicadorPaginacion indicadorPaginacion) {
        this.indicadorPaginacion = indicadorPaginacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Estado)) return false;
        Estado other = (Estado) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoEst==null && other.getCodigoEst()==null) || 
             (this.codigoEst!=null &&
              this.codigoEst.equals(other.getCodigoEst()))) &&
            ((this.descripcionEst==null && other.getDescripcionEst()==null) || 
             (this.descripcionEst!=null &&
              this.descripcionEst.equals(other.getDescripcionEst()))) &&
            ((this.indicadorPaginacion==null && other.getIndicadorPaginacion()==null) || 
             (this.indicadorPaginacion!=null &&
              this.indicadorPaginacion.equals(other.getIndicadorPaginacion())));
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
        if (getCodigoEst() != null) {
            _hashCode += getCodigoEst().hashCode();
        }
        if (getDescripcionEst() != null) {
            _hashCode += getDescripcionEst().hashCode();
        }
        if (getIndicadorPaginacion() != null) {
            _hashCode += getIndicadorPaginacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Estado.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", ">Estado"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoEst");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", "CodigoEst"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", ">CodigoEst"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcionEst");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", "DescripcionEst"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", ">DescripcionEst"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indicadorPaginacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", "IndicadorPaginacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", ">IndicadorPaginacion"));
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
