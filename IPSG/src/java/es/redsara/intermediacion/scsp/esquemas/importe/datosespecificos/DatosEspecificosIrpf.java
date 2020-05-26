/**
 * DatosEspecificosIrpf.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos;

public class DatosEspecificosIrpf  implements java.io.Serializable {
    private es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfCabeceraRenta cabeceraRenta;

    private es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfNivelRenta nivelRenta;

    private es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfDatosEconomicos[] datosEconomicos;

    private es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfDatosCola datosCola;

    public DatosEspecificosIrpf() {
    }

    public DatosEspecificosIrpf(
           es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfCabeceraRenta cabeceraRenta,
           es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfNivelRenta nivelRenta,
           es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfDatosEconomicos[] datosEconomicos,
           es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfDatosCola datosCola) {
           this.cabeceraRenta = cabeceraRenta;
           this.nivelRenta = nivelRenta;
           this.datosEconomicos = datosEconomicos;
           this.datosCola = datosCola;
    }


    /**
     * Gets the cabeceraRenta value for this DatosEspecificosIrpf.
     * 
     * @return cabeceraRenta
     */
    public es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfCabeceraRenta getCabeceraRenta() {
        return cabeceraRenta;
    }


    /**
     * Sets the cabeceraRenta value for this DatosEspecificosIrpf.
     * 
     * @param cabeceraRenta
     */
    public void setCabeceraRenta(es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfCabeceraRenta cabeceraRenta) {
        this.cabeceraRenta = cabeceraRenta;
    }


    /**
     * Gets the nivelRenta value for this DatosEspecificosIrpf.
     * 
     * @return nivelRenta
     */
    public es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfNivelRenta getNivelRenta() {
        return nivelRenta;
    }


    /**
     * Sets the nivelRenta value for this DatosEspecificosIrpf.
     * 
     * @param nivelRenta
     */
    public void setNivelRenta(es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfNivelRenta nivelRenta) {
        this.nivelRenta = nivelRenta;
    }


    /**
     * Gets the datosEconomicos value for this DatosEspecificosIrpf.
     * 
     * @return datosEconomicos
     */
    public es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfDatosEconomicos[] getDatosEconomicos() {
        return datosEconomicos;
    }


    /**
     * Sets the datosEconomicos value for this DatosEspecificosIrpf.
     * 
     * @param datosEconomicos
     */
    public void setDatosEconomicos(es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfDatosEconomicos[] datosEconomicos) {
        this.datosEconomicos = datosEconomicos;
    }

    public es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfDatosEconomicos getDatosEconomicos(int i) {
        return this.datosEconomicos[i];
    }

    public void setDatosEconomicos(int i, es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfDatosEconomicos _value) {
        this.datosEconomicos[i] = _value;
    }


    /**
     * Gets the datosCola value for this DatosEspecificosIrpf.
     * 
     * @return datosCola
     */
    public es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfDatosCola getDatosCola() {
        return datosCola;
    }


    /**
     * Sets the datosCola value for this DatosEspecificosIrpf.
     * 
     * @param datosCola
     */
    public void setDatosCola(es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfDatosCola datosCola) {
        this.datosCola = datosCola;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosEspecificosIrpf)) return false;
        DatosEspecificosIrpf other = (DatosEspecificosIrpf) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.cabeceraRenta==null && other.getCabeceraRenta()==null) || 
             (this.cabeceraRenta!=null &&
              this.cabeceraRenta.equals(other.getCabeceraRenta()))) &&
            ((this.nivelRenta==null && other.getNivelRenta()==null) || 
             (this.nivelRenta!=null &&
              this.nivelRenta.equals(other.getNivelRenta()))) &&
            ((this.datosEconomicos==null && other.getDatosEconomicos()==null) || 
             (this.datosEconomicos!=null &&
              java.util.Arrays.equals(this.datosEconomicos, other.getDatosEconomicos()))) &&
            ((this.datosCola==null && other.getDatosCola()==null) || 
             (this.datosCola!=null &&
              this.datosCola.equals(other.getDatosCola())));
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
        if (getCabeceraRenta() != null) {
            _hashCode += getCabeceraRenta().hashCode();
        }
        if (getNivelRenta() != null) {
            _hashCode += getNivelRenta().hashCode();
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
        if (getDatosCola() != null) {
            _hashCode += getDatosCola().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosEspecificosIrpf.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>DatosEspecificos>irpf"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cabeceraRenta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "CabeceraRenta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>>DatosEspecificos>irpf>CabeceraRenta"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nivelRenta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "NivelRenta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>>DatosEspecificos>irpf>NivelRenta"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosEconomicos");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DatosEconomicos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>>DatosEspecificos>irpf>DatosEconomicos"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("datosCola");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DatosCola"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>>DatosEspecificos>irpf>DatosCola"));
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
