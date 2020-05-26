/**
 * DatosEspecificosIrpfDatosColaDCDatosAscend.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos;

public class DatosEspecificosIrpfDatosColaDCDatosAscend  implements java.io.Serializable {
    private java.lang.String DCLiteral;

    private es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend[] DCNumAscend;

    public DatosEspecificosIrpfDatosColaDCDatosAscend() {
    }

    public DatosEspecificosIrpfDatosColaDCDatosAscend(
           java.lang.String DCLiteral,
           es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend[] DCNumAscend) {
           this.DCLiteral = DCLiteral;
           this.DCNumAscend = DCNumAscend;
    }


    /**
     * Gets the DCLiteral value for this DatosEspecificosIrpfDatosColaDCDatosAscend.
     * 
     * @return DCLiteral
     */
    public java.lang.String getDCLiteral() {
        return DCLiteral;
    }


    /**
     * Sets the DCLiteral value for this DatosEspecificosIrpfDatosColaDCDatosAscend.
     * 
     * @param DCLiteral
     */
    public void setDCLiteral(java.lang.String DCLiteral) {
        this.DCLiteral = DCLiteral;
    }


    /**
     * Gets the DCNumAscend value for this DatosEspecificosIrpfDatosColaDCDatosAscend.
     * 
     * @return DCNumAscend
     */
    public es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend[] getDCNumAscend() {
        return DCNumAscend;
    }


    /**
     * Sets the DCNumAscend value for this DatosEspecificosIrpfDatosColaDCDatosAscend.
     * 
     * @param DCNumAscend
     */
    public void setDCNumAscend(es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend[] DCNumAscend) {
        this.DCNumAscend = DCNumAscend;
    }

    public es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend getDCNumAscend(int i) {
        return this.DCNumAscend[i];
    }

    public void setDCNumAscend(int i, es.redsara.intermediacion.scsp.esquemas.importe.datosespecificos.DatosEspecificosIrpfDatosColaDCDatosAscendDCNumAscend _value) {
        this.DCNumAscend[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosEspecificosIrpfDatosColaDCDatosAscend)) return false;
        DatosEspecificosIrpfDatosColaDCDatosAscend other = (DatosEspecificosIrpfDatosColaDCDatosAscend) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.DCLiteral==null && other.getDCLiteral()==null) || 
             (this.DCLiteral!=null &&
              this.DCLiteral.equals(other.getDCLiteral()))) &&
            ((this.DCNumAscend==null && other.getDCNumAscend()==null) || 
             (this.DCNumAscend!=null &&
              java.util.Arrays.equals(this.DCNumAscend, other.getDCNumAscend())));
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
        if (getDCLiteral() != null) {
            _hashCode += getDCLiteral().hashCode();
        }
        if (getDCNumAscend() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDCNumAscend());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDCNumAscend(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosEspecificosIrpfDatosColaDCDatosAscend.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>>>DatosEspecificos>irpf>DatosCola>DCDatosAscend"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCLiteral");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DCLiteral"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("DCNumAscend");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", "DCNumAscend"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/datosespecificos", ">>>>>DatosEspecificos>irpf>DatosCola>DCDatosAscend>DCNumAscend"));
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
