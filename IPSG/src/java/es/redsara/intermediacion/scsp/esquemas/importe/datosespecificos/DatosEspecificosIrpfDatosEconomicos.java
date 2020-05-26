/**
 * DatosEspecificosIrpfDatosEconomicos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos;

public class DatosEspecificosIrpfDatosEconomicos  implements java.io.Serializable {
    private java.lang.String DEGrupo;

    private long DECasilla;

    private java.lang.String DESigno;

    private long DEEnteros;

    private long DEDecimales;

    public DatosEspecificosIrpfDatosEconomicos() {
    }

    public DatosEspecificosIrpfDatosEconomicos(
           java.lang.String DEGrupo,
           long DECasilla,
           java.lang.String DESigno,
           long DEEnteros,
           long DEDecimales) {
           this.DEGrupo = DEGrupo;
           this.DECasilla = DECasilla;
           this.DESigno = DESigno;
           this.DEEnteros = DEEnteros;
           this.DEDecimales = DEDecimales;
    }


    /**
     * Gets the DEGrupo value for this DatosEspecificosIrpfDatosEconomicos.
     * 
     * @return DEGrupo
     */
    public java.lang.String getDEGrupo() {
        return DEGrupo;
    }


    /**
     * Sets the DEGrupo value for this DatosEspecificosIrpfDatosEconomicos.
     * 
     * @param DEGrupo
     */
    public void setDEGrupo(java.lang.String DEGrupo) {
        this.DEGrupo = DEGrupo;
    }


    /**
     * Gets the DECasilla value for this DatosEspecificosIrpfDatosEconomicos.
     * 
     * @return DECasilla
     */
    public long getDECasilla() {
        return DECasilla;
    }


    /**
     * Sets the DECasilla value for this DatosEspecificosIrpfDatosEconomicos.
     * 
     * @param DECasilla
     */
    public void setDECasilla(long DECasilla) {
        this.DECasilla = DECasilla;
    }


    /**
     * Gets the DESigno value for this DatosEspecificosIrpfDatosEconomicos.
     * 
     * @return DESigno
     */
    public java.lang.String getDESigno() {
        return DESigno;
    }


    /**
     * Sets the DESigno value for this DatosEspecificosIrpfDatosEconomicos.
     * 
     * @param DESigno
     */
    public void setDESigno(java.lang.String DESigno) {
        this.DESigno = DESigno;
    }


    /**
     * Gets the DEEnteros value for this DatosEspecificosIrpfDatosEconomicos.
     * 
     * @return DEEnteros
     */
    public long getDEEnteros() {
        return DEEnteros;
    }


    /**
     * Sets the DEEnteros value for this DatosEspecificosIrpfDatosEconomicos.
     * 
     * @param DEEnteros
     */
    public void setDEEnteros(long DEEnteros) {
        this.DEEnteros = DEEnteros;
    }


    /**
     * Gets the DEDecimales value for this DatosEspecificosIrpfDatosEconomicos.
     * 
     * @return DEDecimales
     */
    public long getDEDecimales() {
        return DEDecimales;
    }


    /**
     * Sets the DEDecimales value for this DatosEspecificosIrpfDatosEconomicos.
     * 
     * @param DEDecimales
     */
    public void setDEDecimales(long DEDecimales) {
        this.DEDecimales = DEDecimales;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosEspecificosIrpfDatosEconomicos)) return false;
        DatosEspecificosIrpfDatosEconomicos other = (DatosEspecificosIrpfDatosEconomicos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.DEGrupo==null && other.getDEGrupo()==null) || 
             (this.DEGrupo!=null &&
              this.DEGrupo.equals(other.getDEGrupo()))) &&
            this.DECasilla == other.getDECasilla() &&
            ((this.DESigno==null && other.getDESigno()==null) || 
             (this.DESigno!=null &&
              this.DESigno.equals(other.getDESigno()))) &&
            this.DEEnteros == other.getDEEnteros() &&
            this.DEDecimales == other.getDEDecimales();
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
        if (getDEGrupo() != null) {
            _hashCode += getDEGrupo().hashCode();
        }
        _hashCode += new Long(getDECasilla()).hashCode();
        if (getDESigno() != null) {
            _hashCode += getDESigno().hashCode();
        }
        _hashCode += new Long(getDEEnteros()).hashCode();
        _hashCode += new Long(getDEDecimales()).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosEspecificosIrpfDatosEconomicos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>>DatosEspecificos>irpf>DatosEconomicos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEGrupo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DEGrupo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DECasilla");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DECasilla"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DESigno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DESigno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEEnteros");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DEEnteros"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DEDecimales");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DEDecimales"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
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
