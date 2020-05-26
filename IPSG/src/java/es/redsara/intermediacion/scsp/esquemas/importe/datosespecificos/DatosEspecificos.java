/**
 * DatosEspecificos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos;

public class DatosEspecificos  implements java.io.Serializable {
    private java.lang.Integer ejercicio;

    private es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosCabecera cabecera;

    private es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosImputaciones imputaciones;

    private es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpf irpf;

    private java.lang.String id;  // attribute

    public DatosEspecificos() {
    }

    public DatosEspecificos(
           java.lang.Integer ejercicio,
           es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosCabecera cabecera,
           es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosImputaciones imputaciones,
           es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpf irpf,
           java.lang.String id) {
           this.ejercicio = ejercicio;
           this.cabecera = cabecera;
           this.imputaciones = imputaciones;
           this.irpf = irpf;
           this.id = id;
    }


    /**
     * Gets the ejercicio value for this DatosEspecificos.
     * 
     * @return ejercicio
     */
    public java.lang.Integer getEjercicio() {
        return ejercicio;
    }


    /**
     * Sets the ejercicio value for this DatosEspecificos.
     * 
     * @param ejercicio
     */
    public void setEjercicio(java.lang.Integer ejercicio) {
        this.ejercicio = ejercicio;
    }


    /**
     * Gets the cabecera value for this DatosEspecificos.
     * 
     * @return cabecera
     */
    public es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosCabecera getCabecera() {
        return cabecera;
    }


    /**
     * Sets the cabecera value for this DatosEspecificos.
     * 
     * @param cabecera
     */
    public void setCabecera(es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosCabecera cabecera) {
        this.cabecera = cabecera;
    }


    /**
     * Gets the imputaciones value for this DatosEspecificos.
     * 
     * @return imputaciones
     */
    public es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosImputaciones getImputaciones() {
        return imputaciones;
    }


    /**
     * Sets the imputaciones value for this DatosEspecificos.
     * 
     * @param imputaciones
     */
    public void setImputaciones(es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosImputaciones imputaciones) {
        this.imputaciones = imputaciones;
    }


    /**
     * Gets the irpf value for this DatosEspecificos.
     * 
     * @return irpf
     */
    public es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpf getIrpf() {
        return irpf;
    }


    /**
     * Sets the irpf value for this DatosEspecificos.
     * 
     * @param irpf
     */
    public void setIrpf(es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpf irpf) {
        this.irpf = irpf;
    }


    /**
     * Gets the id value for this DatosEspecificos.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this DatosEspecificos.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
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
            ((this.ejercicio==null && other.getEjercicio()==null) || 
             (this.ejercicio!=null &&
              this.ejercicio.equals(other.getEjercicio()))) &&
            ((this.cabecera==null && other.getCabecera()==null) || 
             (this.cabecera!=null &&
              this.cabecera.equals(other.getCabecera()))) &&
            ((this.imputaciones==null && other.getImputaciones()==null) || 
             (this.imputaciones!=null &&
              this.imputaciones.equals(other.getImputaciones()))) &&
            ((this.irpf==null && other.getIrpf()==null) || 
             (this.irpf!=null &&
              this.irpf.equals(other.getIrpf()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId())));
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
        if (getEjercicio() != null) {
            _hashCode += getEjercicio().hashCode();
        }
        if (getCabecera() != null) {
            _hashCode += getCabecera().hashCode();
        }
        if (getImputaciones() != null) {
            _hashCode += getImputaciones().hashCode();
        }
        if (getIrpf() != null) {
            _hashCode += getIrpf().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosEspecificos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">DatosEspecificos"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("id");
        attrField.setXmlName(new javax.xml.namespace.QName("", "Id"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ejercicio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Ejercicio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cabecera");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Cabecera"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>DatosEspecificos>Cabecera"));
        elemField.setNillable(true);
        elemField.setMinOccurs(0);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("imputaciones");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Imputaciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>DatosEspecificos>Imputaciones"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("irpf");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "irpf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>DatosEspecificos>irpf"));
        elemField.setMinOccurs(0);
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
