/**
 * DatosEspecificosIrpfDatosColaDCDatosHijosDCNumHijos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos;

public class DatosEspecificosIrpfDatosColaDCDatosHijosDCNumHijos  implements java.io.Serializable {
    private java.lang.String DCNombreHijo;

    private java.lang.String DCFechaNacim;

    private java.lang.String DCFechaAdopc;

    private java.lang.String DCMinusvalia;

    private java.lang.String DCVinculacion;

    public DatosEspecificosIrpfDatosColaDCDatosHijosDCNumHijos() {
    }

    public DatosEspecificosIrpfDatosColaDCDatosHijosDCNumHijos(
           java.lang.String DCNombreHijo,
           java.lang.String DCFechaNacim,
           java.lang.String DCFechaAdopc,
           java.lang.String DCMinusvalia,
           java.lang.String DCVinculacion) {
           this.DCNombreHijo = DCNombreHijo;
           this.DCFechaNacim = DCFechaNacim;
           this.DCFechaAdopc = DCFechaAdopc;
           this.DCMinusvalia = DCMinusvalia;
           this.DCVinculacion = DCVinculacion;
    }


    /**
     * Gets the DCNombreHijo value for this DatosEspecificosIrpfDatosColaDCDatosHijosDCNumHijos.
     * 
     * @return DCNombreHijo
     */
    public java.lang.String getDCNombreHijo() {
        return DCNombreHijo;
    }


    /**
     * Sets the DCNombreHijo value for this DatosEspecificosIrpfDatosColaDCDatosHijosDCNumHijos.
     * 
     * @param DCNombreHijo
     */
    public void setDCNombreHijo(java.lang.String DCNombreHijo) {
        this.DCNombreHijo = DCNombreHijo;
    }


    /**
     * Gets the DCFechaNacim value for this DatosEspecificosIrpfDatosColaDCDatosHijosDCNumHijos.
     * 
     * @return DCFechaNacim
     */
    public java.lang.String getDCFechaNacim() {
        return DCFechaNacim;
    }


    /**
     * Sets the DCFechaNacim value for this DatosEspecificosIrpfDatosColaDCDatosHijosDCNumHijos.
     * 
     * @param DCFechaNacim
     */
    public void setDCFechaNacim(java.lang.String DCFechaNacim) {
        this.DCFechaNacim = DCFechaNacim;
    }


    /**
     * Gets the DCFechaAdopc value for this DatosEspecificosIrpfDatosColaDCDatosHijosDCNumHijos.
     * 
     * @return DCFechaAdopc
     */
    public java.lang.String getDCFechaAdopc() {
        return DCFechaAdopc;
    }


    /**
     * Sets the DCFechaAdopc value for this DatosEspecificosIrpfDatosColaDCDatosHijosDCNumHijos.
     * 
     * @param DCFechaAdopc
     */
    public void setDCFechaAdopc(java.lang.String DCFechaAdopc) {
        this.DCFechaAdopc = DCFechaAdopc;
    }


    /**
     * Gets the DCMinusvalia value for this DatosEspecificosIrpfDatosColaDCDatosHijosDCNumHijos.
     * 
     * @return DCMinusvalia
     */
    public java.lang.String getDCMinusvalia() {
        return DCMinusvalia;
    }


    /**
     * Sets the DCMinusvalia value for this DatosEspecificosIrpfDatosColaDCDatosHijosDCNumHijos.
     * 
     * @param DCMinusvalia
     */
    public void setDCMinusvalia(java.lang.String DCMinusvalia) {
        this.DCMinusvalia = DCMinusvalia;
    }


    /**
     * Gets the DCVinculacion value for this DatosEspecificosIrpfDatosColaDCDatosHijosDCNumHijos.
     * 
     * @return DCVinculacion
     */
    public java.lang.String getDCVinculacion() {
        return DCVinculacion;
    }


    /**
     * Sets the DCVinculacion value for this DatosEspecificosIrpfDatosColaDCDatosHijosDCNumHijos.
     * 
     * @param DCVinculacion
     */
    public void setDCVinculacion(java.lang.String DCVinculacion) {
        this.DCVinculacion = DCVinculacion;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosEspecificosIrpfDatosColaDCDatosHijosDCNumHijos)) return false;
        DatosEspecificosIrpfDatosColaDCDatosHijosDCNumHijos other = (DatosEspecificosIrpfDatosColaDCDatosHijosDCNumHijos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.DCNombreHijo==null && other.getDCNombreHijo()==null) || 
             (this.DCNombreHijo!=null &&
              this.DCNombreHijo.equals(other.getDCNombreHijo()))) &&
            ((this.DCFechaNacim==null && other.getDCFechaNacim()==null) || 
             (this.DCFechaNacim!=null &&
              this.DCFechaNacim.equals(other.getDCFechaNacim()))) &&
            ((this.DCFechaAdopc==null && other.getDCFechaAdopc()==null) || 
             (this.DCFechaAdopc!=null &&
              this.DCFechaAdopc.equals(other.getDCFechaAdopc()))) &&
            ((this.DCMinusvalia==null && other.getDCMinusvalia()==null) || 
             (this.DCMinusvalia!=null &&
              this.DCMinusvalia.equals(other.getDCMinusvalia()))) &&
            ((this.DCVinculacion==null && other.getDCVinculacion()==null) || 
             (this.DCVinculacion!=null &&
              this.DCVinculacion.equals(other.getDCVinculacion())));
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
        if (getDCNombreHijo() != null) {
            _hashCode += getDCNombreHijo().hashCode();
        }
        if (getDCFechaNacim() != null) {
            _hashCode += getDCFechaNacim().hashCode();
        }
        if (getDCFechaAdopc() != null) {
            _hashCode += getDCFechaAdopc().hashCode();
        }
        if (getDCMinusvalia() != null) {
            _hashCode += getDCMinusvalia().hashCode();
        }
        if (getDCVinculacion() != null) {
            _hashCode += getDCVinculacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosEspecificosIrpfDatosColaDCDatosHijosDCNumHijos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>>>>DatosEspecificos>irpf>DatosCola>DCDatosHijos>DCNumHijos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCNombreHijo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DCNombreHijo"));
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
        elemField.setFieldName("DCFechaAdopc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DCFechaAdopc"));
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
