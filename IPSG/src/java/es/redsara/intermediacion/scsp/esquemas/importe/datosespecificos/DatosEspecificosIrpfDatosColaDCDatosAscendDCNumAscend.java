/**
 * DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos;

public class DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend  implements java.io.Serializable {
    private java.lang.String DCNombreAscend;

    private java.lang.String DCFechaNacim;

    private java.lang.String DCMinusvalia;

    private java.lang.String DCVinculacion;

    private java.lang.String DCConvivencia;

    public DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend() {
    }

    public DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend(
           java.lang.String DCNombreAscend,
           java.lang.String DCFechaNacim,
           java.lang.String DCMinusvalia,
           java.lang.String DCVinculacion,
           java.lang.String DCConvivencia) {
           this.DCNombreAscend = DCNombreAscend;
           this.DCFechaNacim = DCFechaNacim;
           this.DCMinusvalia = DCMinusvalia;
           this.DCVinculacion = DCVinculacion;
           this.DCConvivencia = DCConvivencia;
    }


    /**
     * Gets the DCNombreAscend value for this DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend.
     * 
     * @return DCNombreAscend
     */
    public java.lang.String getDCNombreAscend() {
        return DCNombreAscend;
    }


    /**
     * Sets the DCNombreAscend value for this DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend.
     * 
     * @param DCNombreAscend
     */
    public void setDCNombreAscend(java.lang.String DCNombreAscend) {
        this.DCNombreAscend = DCNombreAscend;
    }


    /**
     * Gets the DCFechaNacim value for this DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend.
     * 
     * @return DCFechaNacim
     */
    public java.lang.String getDCFechaNacim() {
        return DCFechaNacim;
    }


    /**
     * Sets the DCFechaNacim value for this DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend.
     * 
     * @param DCFechaNacim
     */
    public void setDCFechaNacim(java.lang.String DCFechaNacim) {
        this.DCFechaNacim = DCFechaNacim;
    }


    /**
     * Gets the DCMinusvalia value for this DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend.
     * 
     * @return DCMinusvalia
     */
    public java.lang.String getDCMinusvalia() {
        return DCMinusvalia;
    }


    /**
     * Sets the DCMinusvalia value for this DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend.
     * 
     * @param DCMinusvalia
     */
    public void setDCMinusvalia(java.lang.String DCMinusvalia) {
        this.DCMinusvalia = DCMinusvalia;
    }


    /**
     * Gets the DCVinculacion value for this DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend.
     * 
     * @return DCVinculacion
     */
    public java.lang.String getDCVinculacion() {
        return DCVinculacion;
    }


    /**
     * Sets the DCVinculacion value for this DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend.
     * 
     * @param DCVinculacion
     */
    public void setDCVinculacion(java.lang.String DCVinculacion) {
        this.DCVinculacion = DCVinculacion;
    }


    /**
     * Gets the DCConvivencia value for this DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend.
     * 
     * @return DCConvivencia
     */
    public java.lang.String getDCConvivencia() {
        return DCConvivencia;
    }


    /**
     * Sets the DCConvivencia value for this DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend.
     * 
     * @param DCConvivencia
     */
    public void setDCConvivencia(java.lang.String DCConvivencia) {
        this.DCConvivencia = DCConvivencia;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend)) return false;
        DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend other = (DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.DCNombreAscend==null && other.getDCNombreAscend()==null) || 
             (this.DCNombreAscend!=null &&
              this.DCNombreAscend.equals(other.getDCNombreAscend()))) &&
            ((this.DCFechaNacim==null && other.getDCFechaNacim()==null) || 
             (this.DCFechaNacim!=null &&
              this.DCFechaNacim.equals(other.getDCFechaNacim()))) &&
            ((this.DCMinusvalia==null && other.getDCMinusvalia()==null) || 
             (this.DCMinusvalia!=null &&
              this.DCMinusvalia.equals(other.getDCMinusvalia()))) &&
            ((this.DCVinculacion==null && other.getDCVinculacion()==null) || 
             (this.DCVinculacion!=null &&
              this.DCVinculacion.equals(other.getDCVinculacion()))) &&
            ((this.DCConvivencia==null && other.getDCConvivencia()==null) || 
             (this.DCConvivencia!=null &&
              this.DCConvivencia.equals(other.getDCConvivencia())));
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
        if (getDCNombreAscend() != null) {
            _hashCode += getDCNombreAscend().hashCode();
        }
        if (getDCFechaNacim() != null) {
            _hashCode += getDCFechaNacim().hashCode();
        }
        if (getDCMinusvalia() != null) {
            _hashCode += getDCMinusvalia().hashCode();
        }
        if (getDCVinculacion() != null) {
            _hashCode += getDCVinculacion().hashCode();
        }
        if (getDCConvivencia() != null) {
            _hashCode += getDCConvivencia().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>>>>DatosEspecificos>irpf>DatosCola>DCDatosAscend>DCNumAscend"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCNombreAscend");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DCNombreAscend"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCFechaNacim");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DCFechaNacim"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCMinusvalia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DCMinusvalia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCVinculacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DCVinculacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCConvivencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DCConvivencia"));
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
