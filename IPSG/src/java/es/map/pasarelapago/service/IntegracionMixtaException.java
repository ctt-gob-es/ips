/**
 * IntegracionMixtaException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.pasarelapago.service;

/**
 * El Class IntegracionMixtaException.
 */
public abstract class IntegracionMixtaException  extends org.apache.axis.AxisFault  implements java.io.Serializable {
    
    /** el error code. */
    private java.lang.String errorCode;

    /** el error code texto. */
    private java.lang.String errorCodeTexto;

    /** el error code texto usuario. */
    private java.lang.String errorCodeTextoUsuario;

    /** el error description. */
    private java.lang.String errorDescription;

    /** el error origen. */
    private java.lang.String errorOrigen;

    /**
     * Crea una nueva integracion mixta exception.
     */
    public IntegracionMixtaException() {
    }

    /**
     * Crea una nueva integracion mixta exception.
     *
     * @param errorCode el error code
     * @param errorCodeTexto el error code texto
     * @param errorCodeTextoUsuario el error code texto usuario
     * @param errorDescription el error description
     * @param errorOrigen el error origen
     */
    public IntegracionMixtaException(
           java.lang.String errorCode,
           java.lang.String errorCodeTexto,
           java.lang.String errorCodeTextoUsuario,
           java.lang.String errorDescription,
           java.lang.String errorOrigen) {
        this.errorCode = errorCode;
        this.errorCodeTexto = errorCodeTexto;
        this.errorCodeTextoUsuario = errorCodeTextoUsuario;
        this.errorDescription = errorDescription;
        this.errorOrigen = errorOrigen;
    }


    /**
     * Gets the errorCode value for this IntegracionMixtaException.
     * 
     * @return errorCode
     */
    public java.lang.String getErrorCode() {
        return errorCode;
    }


    /**
     * Sets the errorCode value for this IntegracionMixtaException.
     *
     * @param errorCode el nuevo error code
     */
    public void setErrorCode(java.lang.String errorCode) {
        this.errorCode = errorCode;
    }


    /**
     * Gets the errorCodeTexto value for this IntegracionMixtaException.
     * 
     * @return errorCodeTexto
     */
    public java.lang.String getErrorCodeTexto() {
        return errorCodeTexto;
    }


    /**
     * Sets the errorCodeTexto value for this IntegracionMixtaException.
     *
     * @param errorCodeTexto el nuevo error code texto
     */
    public void setErrorCodeTexto(java.lang.String errorCodeTexto) {
        this.errorCodeTexto = errorCodeTexto;
    }


    /**
     * Gets the errorCodeTextoUsuario value for this IntegracionMixtaException.
     * 
     * @return errorCodeTextoUsuario
     */
    public java.lang.String getErrorCodeTextoUsuario() {
        return errorCodeTextoUsuario;
    }


    /**
     * Sets the errorCodeTextoUsuario value for this IntegracionMixtaException.
     *
     * @param errorCodeTextoUsuario el nuevo error code texto usuario
     */
    public void setErrorCodeTextoUsuario(java.lang.String errorCodeTextoUsuario) {
        this.errorCodeTextoUsuario = errorCodeTextoUsuario;
    }


    /**
     * Gets the errorDescription value for this IntegracionMixtaException.
     * 
     * @return errorDescription
     */
    public java.lang.String getErrorDescription() {
        return errorDescription;
    }


    /**
     * Sets the errorDescription value for this IntegracionMixtaException.
     *
     * @param errorDescription el nuevo error description
     */
    public void setErrorDescription(java.lang.String errorDescription) {
        this.errorDescription = errorDescription;
    }


    /**
     * Gets the errorOrigen value for this IntegracionMixtaException.
     * 
     * @return errorOrigen
     */
    public java.lang.String getErrorOrigen() {
        return errorOrigen;
    }


    /**
     * Sets the errorOrigen value for this IntegracionMixtaException.
     *
     * @param errorOrigen el nuevo error origen
     */
    public void setErrorOrigen(java.lang.String errorOrigen) {
        this.errorOrigen = errorOrigen;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof IntegracionMixtaException)) return false;
        IntegracionMixtaException other = (IntegracionMixtaException) obj;
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
            ((this.errorOrigen==null && other.getErrorOrigen()==null) || 
             (this.errorOrigen!=null &&
              this.errorOrigen.equals(other.getErrorOrigen())));
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
        if (getErrorOrigen() != null) {
            _hashCode += getErrorOrigen().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(IntegracionMixtaException.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "IntegracionMixtaException"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "errorCode"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCodeTexto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "errorCodeTexto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCodeTextoUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "errorCodeTextoUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "errorDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorOrigen");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "errorOrigen"));
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


    /**
     * Writes the exception data to the faultDetails.
     *
     * @param qname el qname
     * @param context el context
     * @throws IOException Señala que se ha producido una excepción de I/O.
     */
    public void writeDetails(javax.xml.namespace.QName qname, org.apache.axis.encoding.SerializationContext context) throws java.io.IOException {
        context.serialize(qname, null, this);
    }
}
