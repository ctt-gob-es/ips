/**
 * DefaultDatosNRCOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ePago.gob.es.schemas;

/**
 * El Class DefaultDatosNRCOut.
 */
public class DefaultDatosNRCOut  implements java.io.Serializable {
    
    /** el error code. */
    private java.lang.String errorCode;

    /** el error code texto. */
    private java.lang.String errorCodeTexto;

    /** el error code texto usuario. */
    private java.lang.String errorCodeTextoUsuario;

    /** el error description. */
    private java.lang.String errorDescription;

    /** el error origen. */
    private int errorOrigen;

    /** el error origen description. */
    private java.lang.String errorOrigenDescription;

    /** el fecha proceso. */
    private java.util.Calendar fechaProceso;

    /** el fue correcto. */
    private boolean fueCorrecto;

    /** el referencia. */
    private java.lang.String referencia;

    /** el registro AEAT. */
    private java.lang.String registroAEAT;

    /**
     * Crea una nueva default datos NRC out.
     */
    public DefaultDatosNRCOut() {
    }

    /**
     * Crea una nueva default datos NRC out.
     *
     * @param errorCode el error code
     * @param errorCodeTexto el error code texto
     * @param errorCodeTextoUsuario el error code texto usuario
     * @param errorDescription el error description
     * @param errorOrigen el error origen
     * @param errorOrigenDescription el error origen description
     * @param fechaProceso el fecha proceso
     * @param fueCorrecto el fue correcto
     * @param referencia el referencia
     * @param registroAEAT el registro AEAT
     */
    public DefaultDatosNRCOut(
           java.lang.String errorCode,
           java.lang.String errorCodeTexto,
           java.lang.String errorCodeTextoUsuario,
           java.lang.String errorDescription,
           int errorOrigen,
           java.lang.String errorOrigenDescription,
           java.util.Calendar fechaProceso,
           boolean fueCorrecto,
           java.lang.String referencia,
           java.lang.String registroAEAT) {
           this.errorCode = errorCode;
           this.errorCodeTexto = errorCodeTexto;
           this.errorCodeTextoUsuario = errorCodeTextoUsuario;
           this.errorDescription = errorDescription;
           this.errorOrigen = errorOrigen;
           this.errorOrigenDescription = errorOrigenDescription;
           this.fechaProceso = fechaProceso;
           this.fueCorrecto = fueCorrecto;
           this.referencia = referencia;
           this.registroAEAT = registroAEAT;
    }


    /**
     * Gets the errorCode value for this DefaultDatosNRCOut.
     * 
     * @return errorCode
     */
    public java.lang.String getErrorCode() {
        return errorCode;
    }


    /**
     * Sets the errorCode value for this DefaultDatosNRCOut.
     *
     * @param errorCode el nuevo error code
     */
    public void setErrorCode(java.lang.String errorCode) {
        this.errorCode = errorCode;
    }


    /**
     * Gets the errorCodeTexto value for this DefaultDatosNRCOut.
     * 
     * @return errorCodeTexto
     */
    public java.lang.String getErrorCodeTexto() {
        return errorCodeTexto;
    }


    /**
     * Sets the errorCodeTexto value for this DefaultDatosNRCOut.
     *
     * @param errorCodeTexto el nuevo error code texto
     */
    public void setErrorCodeTexto(java.lang.String errorCodeTexto) {
        this.errorCodeTexto = errorCodeTexto;
    }


    /**
     * Gets the errorCodeTextoUsuario value for this DefaultDatosNRCOut.
     * 
     * @return errorCodeTextoUsuario
     */
    public java.lang.String getErrorCodeTextoUsuario() {
        return errorCodeTextoUsuario;
    }


    /**
     * Sets the errorCodeTextoUsuario value for this DefaultDatosNRCOut.
     *
     * @param errorCodeTextoUsuario el nuevo error code texto usuario
     */
    public void setErrorCodeTextoUsuario(java.lang.String errorCodeTextoUsuario) {
        this.errorCodeTextoUsuario = errorCodeTextoUsuario;
    }


    /**
     * Gets the errorDescription value for this DefaultDatosNRCOut.
     * 
     * @return errorDescription
     */
    public java.lang.String getErrorDescription() {
        return errorDescription;
    }


    /**
     * Sets the errorDescription value for this DefaultDatosNRCOut.
     *
     * @param errorDescription el nuevo error description
     */
    public void setErrorDescription(java.lang.String errorDescription) {
        this.errorDescription = errorDescription;
    }


    /**
     * Gets the errorOrigen value for this DefaultDatosNRCOut.
     * 
     * @return errorOrigen
     */
    public int getErrorOrigen() {
        return errorOrigen;
    }


    /**
     * Sets the errorOrigen value for this DefaultDatosNRCOut.
     *
     * @param errorOrigen el nuevo error origen
     */
    public void setErrorOrigen(int errorOrigen) {
        this.errorOrigen = errorOrigen;
    }


    /**
     * Gets the errorOrigenDescription value for this DefaultDatosNRCOut.
     * 
     * @return errorOrigenDescription
     */
    public java.lang.String getErrorOrigenDescription() {
        return errorOrigenDescription;
    }


    /**
     * Sets the errorOrigenDescription value for this DefaultDatosNRCOut.
     *
     * @param errorOrigenDescription el nuevo error origen description
     */
    public void setErrorOrigenDescription(java.lang.String errorOrigenDescription) {
        this.errorOrigenDescription = errorOrigenDescription;
    }


    /**
     * Gets the fechaProceso value for this DefaultDatosNRCOut.
     * 
     * @return fechaProceso
     */
    public java.util.Calendar getFechaProceso() {
        return fechaProceso;
    }


    /**
     * Sets the fechaProceso value for this DefaultDatosNRCOut.
     *
     * @param fechaProceso el nuevo fecha proceso
     */
    public void setFechaProceso(java.util.Calendar fechaProceso) {
        this.fechaProceso = fechaProceso;
    }


    /**
     * Gets the fueCorrecto value for this DefaultDatosNRCOut.
     * 
     * @return fueCorrecto
     */
    public boolean isFueCorrecto() {
        return fueCorrecto;
    }


    /**
     * Sets the fueCorrecto value for this DefaultDatosNRCOut.
     *
     * @param fueCorrecto el nuevo fue correcto
     */
    public void setFueCorrecto(boolean fueCorrecto) {
        this.fueCorrecto = fueCorrecto;
    }


    /**
     * Gets the referencia value for this DefaultDatosNRCOut.
     * 
     * @return referencia
     */
    public java.lang.String getReferencia() {
        return referencia;
    }


    /**
     * Sets the referencia value for this DefaultDatosNRCOut.
     *
     * @param referencia el nuevo referencia
     */
    public void setReferencia(java.lang.String referencia) {
        this.referencia = referencia;
    }


    /**
     * Gets the registroAEAT value for this DefaultDatosNRCOut.
     * 
     * @return registroAEAT
     */
    public java.lang.String getRegistroAEAT() {
        return registroAEAT;
    }


    /**
     * Sets the registroAEAT value for this DefaultDatosNRCOut.
     *
     * @param registroAEAT el nuevo registro AEAT
     */
    public void setRegistroAEAT(java.lang.String registroAEAT) {
        this.registroAEAT = registroAEAT;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DefaultDatosNRCOut)) return false;
        DefaultDatosNRCOut other = (DefaultDatosNRCOut) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.errorCode==null && other.getErrorCode()==null) || 
             (this.errorCode!=null &&
              this.errorCode.equals(other.getErrorCode()))) &&
            ((this.errorCodeTexto==null && other.getErrorCodeTexto()==null) || 
             (this.errorCodeTexto!=null &&
              this.errorCodeTexto.equals(other.getErrorCodeTexto()))) &&
            ((this.errorCodeTextoUsuario==null && other.getErrorCodeTextoUsuario()==null) || 
             (this.errorCodeTextoUsuario!=null &&
              this.errorCodeTextoUsuario.equals(other.getErrorCodeTextoUsuario()))) &&
            ((this.errorDescription==null && other.getErrorDescription()==null) || 
             (this.errorDescription!=null &&
              this.errorDescription.equals(other.getErrorDescription()))) &&
            this.errorOrigen == other.getErrorOrigen() &&
            ((this.errorOrigenDescription==null && other.getErrorOrigenDescription()==null) || 
             (this.errorOrigenDescription!=null &&
              this.errorOrigenDescription.equals(other.getErrorOrigenDescription()))) &&
            ((this.fechaProceso==null && other.getFechaProceso()==null) || 
             (this.fechaProceso!=null &&
              this.fechaProceso.equals(other.getFechaProceso()))) &&
            this.fueCorrecto == other.isFueCorrecto() &&
            ((this.referencia==null && other.getReferencia()==null) || 
             (this.referencia!=null &&
              this.referencia.equals(other.getReferencia()))) &&
            ((this.registroAEAT==null && other.getRegistroAEAT()==null) || 
             (this.registroAEAT!=null &&
              this.registroAEAT.equals(other.getRegistroAEAT())));
        __equalsCalc = null;
        return _equals;
    }

    /** el hash code calc. */
    private boolean __hashCodeCalc = false;
    
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getErrorCode() != null) {
            _hashCode += getErrorCode().hashCode();
        }
        if (getErrorCodeTexto() != null) {
            _hashCode += getErrorCodeTexto().hashCode();
        }
        if (getErrorCodeTextoUsuario() != null) {
            _hashCode += getErrorCodeTextoUsuario().hashCode();
        }
        if (getErrorDescription() != null) {
            _hashCode += getErrorDescription().hashCode();
        }
        _hashCode += getErrorOrigen();
        if (getErrorOrigenDescription() != null) {
            _hashCode += getErrorOrigenDescription().hashCode();
        }
        if (getFechaProceso() != null) {
            _hashCode += getFechaProceso().hashCode();
        }
        _hashCode += (isFueCorrecto() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        if (getReferencia() != null) {
            _hashCode += getReferencia().hashCode();
        }
        if (getRegistroAEAT() != null) {
            _hashCode += getRegistroAEAT().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DefaultDatosNRCOut.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosNRCOut"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "errorCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCodeTexto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "errorCodeTexto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCodeTextoUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "errorCodeTextoUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "errorDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorOrigen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "errorOrigen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorOrigenDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "errorOrigenDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaProceso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "fechaProceso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fueCorrecto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "fueCorrecto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("referencia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "referencia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("registroAEAT");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "registroAEAT"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object.
     *
     * @return el type desc
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer.
     *
     * @param mechType el mech type
     * @param _javaType el java type
     * @param _xmlType el xml type
     * @return el serializer
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
     * Get Custom Deserializer.
     *
     * @param mechType el mech type
     * @param _javaType el java type
     * @param _xmlType el xml type
     * @return el deserializer
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
