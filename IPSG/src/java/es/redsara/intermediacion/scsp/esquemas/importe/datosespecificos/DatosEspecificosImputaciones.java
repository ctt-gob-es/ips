/**
 * DatosEspecificosImputaciones.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos;

public class DatosEspecificosImputaciones  implements java.io.Serializable {
    private java.lang.String cabecera;

    private es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosImputacionesDatosEconomicos[] datosEconomicos;

    private java.lang.String cola;

    public DatosEspecificosImputaciones() {
    }

    public DatosEspecificosImputaciones(
           java.lang.String cabecera,
           es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosImputacionesDatosEconomicos[] datosEconomicos,
           java.lang.String cola) {
           this.cabecera = cabecera;
           this.datosEconomicos = datosEconomicos;
           this.cola = cola;
    }


    /**
     * Gets the cabecera value for this DatosEspecificosImputaciones.
     * 
     * @return cabecera
     */
    public java.lang.String getCabecera() {
        return cabecera;
    }


    /**
     * Sets the cabecera value for this DatosEspecificosImputaciones.
     * 
     * @param cabecera
     */
    public void setCabecera(java.lang.String cabecera) {
        this.cabecera = cabecera;
    }


    /**
     * Gets the datosEconomicos value for this DatosEspecificosImputaciones.
     * 
     * @return datosEconomicos
     */
    public es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosImputacionesDatosEconomicos[] getDatosEconomicos() {
        return datosEconomicos;
    }


    /**
     * Sets the datosEconomicos value for this DatosEspecificosImputaciones.
     * 
     * @param datosEconomicos
     */
    public void setDatosEconomicos(es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosImputacionesDatosEconomicos[] datosEconomicos) {
        this.datosEconomicos = datosEconomicos;
    }

    public es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosImputacionesDatosEconomicos getDatosEconomicos(int i) {
        return this.datosEconomicos[i];
    }

    public void setDatosEconomicos(int i, es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosImputacionesDatosEconomicos _value) {
        this.datosEconomicos[i] = _value;
    }


    /**
     * Gets the cola value for this DatosEspecificosImputaciones.
     * 
     * @return cola
     */
    public java.lang.String getCola() {
        return cola;
    }


    /**
     * Sets the cola value for this DatosEspecificosImputaciones.
     * 
     * @param cola
     */
    public void setCola(java.lang.String cola) {
        this.cola = cola;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosEspecificosImputaciones)) return false;
        DatosEspecificosImputaciones other = (DatosEspecificosImputaciones) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cabecera==null && other.getCabecera()==null) || 
             (this.cabecera!=null &&
              this.cabecera.equals(other.getCabecera()))) &&
            ((this.datosEconomicos==null && other.getDatosEconomicos()==null) || 
             (this.datosEconomicos!=null &&
              java.util.Arrays.equals(this.datosEconomicos, other.getDatosEconomicos()))) &&
            ((this.cola==null && other.getCola()==null) || 
             (this.cola!=null &&
              this.cola.equals(other.getCola())));
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
        if (getCabecera() != null) {
            _hashCode += getCabecera().hashCode();
        }
        if (getDatosEconomicos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDatosEconomicos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDatosEconomicos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getCola() != null) {
            _hashCode += getCola().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosEspecificosImputaciones.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>DatosEspecificos>Imputaciones"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cabecera");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Cabecera"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosEconomicos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DatosEconomicos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>>DatosEspecificos>Imputaciones>DatosEconomicos"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cola");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "Cola"));
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
