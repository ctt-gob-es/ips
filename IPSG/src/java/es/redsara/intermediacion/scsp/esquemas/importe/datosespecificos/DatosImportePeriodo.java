/**
 * DatosImportePeriodo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos;

public class DatosImportePeriodo  implements java.io.Serializable {
    private java.lang.String IPF;

    private java.lang.String FXINICIO;

    private java.lang.String FXFINAL;

    private java.lang.String IMPBRUTO;

    private java.lang.String IMPREASS;

    private java.lang.String IMPIRPF;

    private java.lang.String IMPSEGSOC;

    public DatosImportePeriodo() {
    }

    public DatosImportePeriodo(
           java.lang.String IPF,
           java.lang.String FXINICIO,
           java.lang.String FXFINAL,
           java.lang.String IMPBRUTO,
           java.lang.String IMPREASS,
           java.lang.String IMPIRPF,
           java.lang.String IMPSEGSOC) {
           this.IPF = IPF;
           this.FXINICIO = FXINICIO;
           this.FXFINAL = FXFINAL;
           this.IMPBRUTO = IMPBRUTO;
           this.IMPREASS = IMPREASS;
           this.IMPIRPF = IMPIRPF;
           this.IMPSEGSOC = IMPSEGSOC;
    }


    /**
     * Gets the IPF value for this DatosImportePeriodo.
     * 
     * @return IPF
     */
    public java.lang.String getIPF() {
        return IPF;
    }


    /**
     * Sets the IPF value for this DatosImportePeriodo.
     * 
     * @param IPF
     */
    public void setIPF(java.lang.String IPF) {
        this.IPF = IPF;
    }


    /**
     * Gets the FXINICIO value for this DatosImportePeriodo.
     * 
     * @return FXINICIO
     */
    public java.lang.String getFXINICIO() {
        return FXINICIO;
    }


    /**
     * Sets the FXINICIO value for this DatosImportePeriodo.
     * 
     * @param FXINICIO
     */
    public void setFXINICIO(java.lang.String FXINICIO) {
        this.FXINICIO = FXINICIO;
    }


    /**
     * Gets the FXFINAL value for this DatosImportePeriodo.
     * 
     * @return FXFINAL
     */
    public java.lang.String getFXFINAL() {
        return FXFINAL;
    }


    /**
     * Sets the FXFINAL value for this DatosImportePeriodo.
     * 
     * @param FXFINAL
     */
    public void setFXFINAL(java.lang.String FXFINAL) {
        this.FXFINAL = FXFINAL;
    }


    /**
     * Gets the IMPBRUTO value for this DatosImportePeriodo.
     * 
     * @return IMPBRUTO
     */
    public java.lang.String getIMPBRUTO() {
        return IMPBRUTO;
    }


    /**
     * Sets the IMPBRUTO value for this DatosImportePeriodo.
     * 
     * @param IMPBRUTO
     */
    public void setIMPBRUTO(java.lang.String IMPBRUTO) {
        this.IMPBRUTO = IMPBRUTO;
    }


    /**
     * Gets the IMPREASS value for this DatosImportePeriodo.
     * 
     * @return IMPREASS
     */
    public java.lang.String getIMPREASS() {
        return IMPREASS;
    }


    /**
     * Sets the IMPREASS value for this DatosImportePeriodo.
     * 
     * @param IMPREASS
     */
    public void setIMPREASS(java.lang.String IMPREASS) {
        this.IMPREASS = IMPREASS;
    }


    /**
     * Gets the IMPIRPF value for this DatosImportePeriodo.
     * 
     * @return IMPIRPF
     */
    public java.lang.String getIMPIRPF() {
        return IMPIRPF;
    }


    /**
     * Sets the IMPIRPF value for this DatosImportePeriodo.
     * 
     * @param IMPIRPF
     */
    public void setIMPIRPF(java.lang.String IMPIRPF) {
        this.IMPIRPF = IMPIRPF;
    }


    /**
     * Gets the IMPSEGSOC value for this DatosImportePeriodo.
     * 
     * @return IMPSEGSOC
     */
    public java.lang.String getIMPSEGSOC() {
        return IMPSEGSOC;
    }


    /**
     * Sets the IMPSEGSOC value for this DatosImportePeriodo.
     * 
     * @param IMPSEGSOC
     */
    public void setIMPSEGSOC(java.lang.String IMPSEGSOC) {
        this.IMPSEGSOC = IMPSEGSOC;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosImportePeriodo)) return false;
        DatosImportePeriodo other = (DatosImportePeriodo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.IPF==null && other.getIPF()==null) || 
             (this.IPF!=null &&
              this.IPF.equals(other.getIPF()))) &&
            ((this.FXINICIO==null && other.getFXINICIO()==null) || 
             (this.FXINICIO!=null &&
              this.FXINICIO.equals(other.getFXINICIO()))) &&
            ((this.FXFINAL==null && other.getFXFINAL()==null) || 
             (this.FXFINAL!=null &&
              this.FXFINAL.equals(other.getFXFINAL()))) &&
            ((this.IMPBRUTO==null && other.getIMPBRUTO()==null) || 
             (this.IMPBRUTO!=null &&
              this.IMPBRUTO.equals(other.getIMPBRUTO()))) &&
            ((this.IMPREASS==null && other.getIMPREASS()==null) || 
             (this.IMPREASS!=null &&
              this.IMPREASS.equals(other.getIMPREASS()))) &&
            ((this.IMPIRPF==null && other.getIMPIRPF()==null) || 
             (this.IMPIRPF!=null &&
              this.IMPIRPF.equals(other.getIMPIRPF()))) &&
            ((this.IMPSEGSOC==null && other.getIMPSEGSOC()==null) || 
             (this.IMPSEGSOC!=null &&
              this.IMPSEGSOC.equals(other.getIMPSEGSOC())));
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
        if (getIPF() != null) {
            _hashCode += getIPF().hashCode();
        }
        if (getFXINICIO() != null) {
            _hashCode += getFXINICIO().hashCode();
        }
        if (getFXFINAL() != null) {
            _hashCode += getFXFINAL().hashCode();
        }
        if (getIMPBRUTO() != null) {
            _hashCode += getIMPBRUTO().hashCode();
        }
        if (getIMPREASS() != null) {
            _hashCode += getIMPREASS().hashCode();
        }
        if (getIMPIRPF() != null) {
            _hashCode += getIMPIRPF().hashCode();
        }
        if (getIMPSEGSOC() != null) {
            _hashCode += getIMPSEGSOC().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosImportePeriodo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">DatosImportePeriodo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IPF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "IPF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">IPF"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FXINICIO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "FXINICIO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">FXINICIO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("FXFINAL");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "FXFINAL"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">FXFINAL"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IMPBRUTO");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "IMPBRUTO"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">IMPBRUTO"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IMPREASS");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "IMPREASS"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">IMPREASS"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IMPIRPF");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "IMPIRPF"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">IMPIRPF"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("IMPSEGSOC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "IMPSEGSOC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">IMPSEGSOC"));
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
