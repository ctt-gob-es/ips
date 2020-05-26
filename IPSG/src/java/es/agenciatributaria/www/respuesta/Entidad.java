/**
 * Entidad.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.agenciatributaria.www.respuesta;

public class Entidad  implements java.io.Serializable {
    private java.lang.String codigo;

    private java.lang.String descripcion;

    private es.agenciatributaria.www.respuesta.Tipo tipo;

    private es.agenciatributaria.www.respuesta.Medio[] mediosPago;

    private es.agenciatributaria.www.respuesta.Horarios horarios;

    public Entidad() {
    }

    public Entidad(
           java.lang.String codigo,
           java.lang.String descripcion,
           es.agenciatributaria.www.respuesta.Tipo tipo,
           es.agenciatributaria.www.respuesta.Medio[] mediosPago,
           es.agenciatributaria.www.respuesta.Horarios horarios) {
           this.codigo = codigo;
           this.descripcion = descripcion;
           this.tipo = tipo;
           this.mediosPago = mediosPago;
           this.horarios = horarios;
    }


    /**
     * Gets the codigo value for this Entidad.
     * 
     * @return codigo
     */
    public java.lang.String getCodigo() {
        return codigo;
    }


    /**
     * Sets the codigo value for this Entidad.
     * 
     * @param codigo
     */
    public void setCodigo(java.lang.String codigo) {
        this.codigo = codigo;
    }


    /**
     * Gets the descripcion value for this Entidad.
     * 
     * @return descripcion
     */
    public java.lang.String getDescripcion() {
        return descripcion;
    }


    /**
     * Sets the descripcion value for this Entidad.
     * 
     * @param descripcion
     */
    public void setDescripcion(java.lang.String descripcion) {
        this.descripcion = descripcion;
    }


    /**
     * Gets the tipo value for this Entidad.
     * 
     * @return tipo
     */
    public es.agenciatributaria.www.respuesta.Tipo getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this Entidad.
     * 
     * @param tipo
     */
    public void setTipo(es.agenciatributaria.www.respuesta.Tipo tipo) {
        this.tipo = tipo;
    }


    /**
     * Gets the mediosPago value for this Entidad.
     * 
     * @return mediosPago
     */
    public es.agenciatributaria.www.respuesta.Medio[] getMediosPago() {
        return mediosPago;
    }


    /**
     * Sets the mediosPago value for this Entidad.
     * 
     * @param mediosPago
     */
    public void setMediosPago(es.agenciatributaria.www.respuesta.Medio[] mediosPago) {
        this.mediosPago = mediosPago;
    }


    /**
     * Gets the horarios value for this Entidad.
     * 
     * @return horarios
     */
    public es.agenciatributaria.www.respuesta.Horarios getHorarios() {
        return horarios;
    }


    /**
     * Sets the horarios value for this Entidad.
     * 
     * @param horarios
     */
    public void setHorarios(es.agenciatributaria.www.respuesta.Horarios horarios) {
        this.horarios = horarios;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Entidad)) return false;
        Entidad other = (Entidad) obj;
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
            ((this.descripcion==null && other.getDescripcion()==null) || 
             (this.descripcion!=null &&
              this.descripcion.equals(other.getDescripcion()))) &&
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo()))) &&
            ((this.mediosPago==null && other.getMediosPago()==null) || 
             (this.mediosPago!=null &&
              java.util.Arrays.equals(this.mediosPago, other.getMediosPago()))) &&
            ((this.horarios==null && other.getHorarios()==null) || 
             (this.horarios!=null &&
              this.horarios.equals(other.getHorarios())));
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
        if (getDescripcion() != null) {
            _hashCode += getDescripcion().hashCode();
        }
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        if (getMediosPago() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getMediosPago());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getMediosPago(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getHorarios() != null) {
            _hashCode += getHorarios().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Entidad.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", ">Entidad"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", "Codigo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", ">Codigo"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descripcion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", "Descripcion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", ">Descripcion"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", "Tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", ">Tipo"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mediosPago");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", "MediosPago"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", ">MediosPago"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("horarios");
        elemField.setXmlName(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", "Horarios"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.agenciatributaria.es/respuesta", ">Horarios"));
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
