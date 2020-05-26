/**
 * DatosEspecificosIrpfDatosColaDCDatosViviendaDCNumViviendas.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos;

public class DatosEspecificosIrpfDatosColaDCDatosViviendaDCNumViviendas  implements java.io.Serializable {
    private java.lang.String DCContrib;

    private java.lang.String DCParticipac;

    private java.lang.String DCRefCatastr;

    private java.lang.String DCSituacion;

    private java.lang.String DCTitularidad;

    public DatosEspecificosIrpfDatosColaDCDatosViviendaDCNumViviendas() {
    }

    public DatosEspecificosIrpfDatosColaDCDatosViviendaDCNumViviendas(
           java.lang.String DCContrib,
           java.lang.String DCParticipac,
           java.lang.String DCRefCatastr,
           java.lang.String DCSituacion,
           java.lang.String DCTitularidad) {
           this.DCContrib = DCContrib;
           this.DCParticipac = DCParticipac;
           this.DCRefCatastr = DCRefCatastr;
           this.DCSituacion = DCSituacion;
           this.DCTitularidad = DCTitularidad;
    }


    /**
     * Gets the DCContrib value for this DatosEspecificosIrpfDatosColaDCDatosViviendaDCNumViviendas.
     * 
     * @return DCContrib
     */
    public java.lang.String getDCContrib() {
        return DCContrib;
    }


    /**
     * Sets the DCContrib value for this DatosEspecificosIrpfDatosColaDCDatosViviendaDCNumViviendas.
     * 
     * @param DCContrib
     */
    public void setDCContrib(java.lang.String DCContrib) {
        this.DCContrib = DCContrib;
    }


    /**
     * Gets the DCParticipac value for this DatosEspecificosIrpfDatosColaDCDatosViviendaDCNumViviendas.
     * 
     * @return DCParticipac
     */
    public java.lang.String getDCParticipac() {
        return DCParticipac;
    }


    /**
     * Sets the DCParticipac value for this DatosEspecificosIrpfDatosColaDCDatosViviendaDCNumViviendas.
     * 
     * @param DCParticipac
     */
    public void setDCParticipac(java.lang.String DCParticipac) {
        this.DCParticipac = DCParticipac;
    }


    /**
     * Gets the DCRefCatastr value for this DatosEspecificosIrpfDatosColaDCDatosViviendaDCNumViviendas.
     * 
     * @return DCRefCatastr
     */
    public java.lang.String getDCRefCatastr() {
        return DCRefCatastr;
    }


    /**
     * Sets the DCRefCatastr value for this DatosEspecificosIrpfDatosColaDCDatosViviendaDCNumViviendas.
     * 
     * @param DCRefCatastr
     */
    public void setDCRefCatastr(java.lang.String DCRefCatastr) {
        this.DCRefCatastr = DCRefCatastr;
    }


    /**
     * Gets the DCSituacion value for this DatosEspecificosIrpfDatosColaDCDatosViviendaDCNumViviendas.
     * 
     * @return DCSituacion
     */
    public java.lang.String getDCSituacion() {
        return DCSituacion;
    }


    /**
     * Sets the DCSituacion value for this DatosEspecificosIrpfDatosColaDCDatosViviendaDCNumViviendas.
     * 
     * @param DCSituacion
     */
    public void setDCSituacion(java.lang.String DCSituacion) {
        this.DCSituacion = DCSituacion;
    }


    /**
     * Gets the DCTitularidad value for this DatosEspecificosIrpfDatosColaDCDatosViviendaDCNumViviendas.
     * 
     * @return DCTitularidad
     */
    public java.lang.String getDCTitularidad() {
        return DCTitularidad;
    }


    /**
     * Sets the DCTitularidad value for this DatosEspecificosIrpfDatosColaDCDatosViviendaDCNumViviendas.
     * 
     * @param DCTitularidad
     */
    public void setDCTitularidad(java.lang.String DCTitularidad) {
        this.DCTitularidad = DCTitularidad;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosEspecificosIrpfDatosColaDCDatosViviendaDCNumViviendas)) return false;
        DatosEspecificosIrpfDatosColaDCDatosViviendaDCNumViviendas other = (DatosEspecificosIrpfDatosColaDCDatosViviendaDCNumViviendas) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.DCContrib==null && other.getDCContrib()==null) || 
             (this.DCContrib!=null &&
              this.DCContrib.equals(other.getDCContrib()))) &&
            ((this.DCParticipac==null && other.getDCParticipac()==null) || 
             (this.DCParticipac!=null &&
              this.DCParticipac.equals(other.getDCParticipac()))) &&
            ((this.DCRefCatastr==null && other.getDCRefCatastr()==null) || 
             (this.DCRefCatastr!=null &&
              this.DCRefCatastr.equals(other.getDCRefCatastr()))) &&
            ((this.DCSituacion==null && other.getDCSituacion()==null) || 
             (this.DCSituacion!=null &&
              this.DCSituacion.equals(other.getDCSituacion()))) &&
            ((this.DCTitularidad==null && other.getDCTitularidad()==null) || 
             (this.DCTitularidad!=null &&
              this.DCTitularidad.equals(other.getDCTitularidad())));
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
        if (getDCContrib() != null) {
            _hashCode += getDCContrib().hashCode();
        }
        if (getDCParticipac() != null) {
            _hashCode += getDCParticipac().hashCode();
        }
        if (getDCRefCatastr() != null) {
            _hashCode += getDCRefCatastr().hashCode();
        }
        if (getDCSituacion() != null) {
            _hashCode += getDCSituacion().hashCode();
        }
        if (getDCTitularidad() != null) {
            _hashCode += getDCTitularidad().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosEspecificosIrpfDatosColaDCDatosViviendaDCNumViviendas.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>>>>DatosEspecificos>irpf>DatosCola>DCDatosVivienda>DCNumViviendas"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCContrib");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DCContrib"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCParticipac");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DCParticipac"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCRefCatastr");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DCRefCatastr"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCSituacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DCSituacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCTitularidad");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DCTitularidad"));
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
