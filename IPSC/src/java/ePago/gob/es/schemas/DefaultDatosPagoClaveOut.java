/**
 * DefaultDatosPagoClaveOut.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ePago.gob.es.schemas;

/**
 * El Class DefaultDatosPagoClaveOut.
 */
public class DefaultDatosPagoClaveOut  implements java.io.Serializable {
    
    /** el nrc. */
    private java.lang.String NRC;

    /** el error code. */
    private java.lang.String errorCode;

    /** el error description. */
    private java.lang.String errorDescription;

    /** el error origen. */
    private int errorOrigen;

    /** el fecha operacion. */
    private java.util.Calendar fechaOperacion;

    /** el fecha proceso. */
    private java.util.Calendar fechaProceso;

    /** el fue correcto. */
    private boolean fueCorrecto;

    /** el importe operacion. */
    private double importeOperacion;

    /** el merchan. */
    private java.lang.String merchan;

    /** el referencia. */
    private java.lang.String referencia;

    /** el registro AEAT. */
    private java.lang.String registroAEAT;

    /** el error code texto. */
    private java.lang.String errorCodeTexto;

    /** el error code texto usuario. */
    private java.lang.String errorCodeTextoUsuario;

    /** el error origen description. */
    private java.lang.String errorOrigenDescription;

    /** el apoderado. */
    private boolean apoderado;

    /**
     * Crea una nueva default datos pago clave out.
     */
    public DefaultDatosPagoClaveOut() {
    }

    /**
     * Crea una nueva default datos pago clave out.
     *
     * @param NRC el nrc
     * @param errorCode el error code
     * @param errorDescription el error description
     * @param errorOrigen el error origen
     * @param fechaOperacion el fecha operacion
     * @param fechaProceso el fecha proceso
     * @param fueCorrecto el fue correcto
     * @param importeOperacion el importe operacion
     * @param merchan el merchan
     * @param referencia el referencia
     * @param registroAEAT el registro AEAT
     * @param errorCodeTexto el error code texto
     * @param errorCodeTextoUsuario el error code texto usuario
     * @param errorOrigenDescription el error origen description
     * @param apoderado el apoderado
     */
    public DefaultDatosPagoClaveOut(
           java.lang.String NRC,
           java.lang.String errorCode,
           java.lang.String errorDescription,
           int errorOrigen,
           java.util.Calendar fechaOperacion,
           java.util.Calendar fechaProceso,
           boolean fueCorrecto,
           double importeOperacion,
           java.lang.String merchan,
           java.lang.String referencia,
           java.lang.String registroAEAT,
           java.lang.String errorCodeTexto,
           java.lang.String errorCodeTextoUsuario,
           java.lang.String errorOrigenDescription,
           boolean apoderado) {
           this.NRC = NRC;
           this.errorCode = errorCode;
           this.errorDescription = errorDescription;
           this.errorOrigen = errorOrigen;
           this.fechaOperacion = fechaOperacion;
           this.fechaProceso = fechaProceso;
           this.fueCorrecto = fueCorrecto;
           this.importeOperacion = importeOperacion;
           this.merchan = merchan;
           this.referencia = referencia;
           this.registroAEAT = registroAEAT;
           this.errorCodeTexto = errorCodeTexto;
           this.errorCodeTextoUsuario = errorCodeTextoUsuario;
           this.errorOrigenDescription = errorOrigenDescription;
           this.apoderado = apoderado;
    }


    /**
     * Gets the NRC value for this DefaultDatosPagoClaveOut.
     * 
     * @return NRC
     */
    public java.lang.String getNRC() {
        return NRC;
    }


    /**
     * Sets the NRC value for this DefaultDatosPagoClaveOut.
     *
     * @param NRC el nuevo nrc
     */
    public void setNRC(java.lang.String NRC) {
        this.NRC = NRC;
    }


    /**
     * Gets the errorCode value for this DefaultDatosPagoClaveOut.
     * 
     * @return errorCode
     */
    public java.lang.String getErrorCode() {
        return errorCode;
    }


    /**
     * Sets the errorCode value for this DefaultDatosPagoClaveOut.
     *
     * @param errorCode el nuevo error code
     */
    public void setErrorCode(java.lang.String errorCode) {
        this.errorCode = errorCode;
    }


    /**
     * Gets the errorDescription value for this DefaultDatosPagoClaveOut.
     * 
     * @return errorDescription
     */
    public java.lang.String getErrorDescription() {
        return errorDescription;
    }


    /**
     * Sets the errorDescription value for this DefaultDatosPagoClaveOut.
     *
     * @param errorDescription el nuevo error description
     */
    public void setErrorDescription(java.lang.String errorDescription) {
        this.errorDescription = errorDescription;
    }


    /**
     * Gets the errorOrigen value for this DefaultDatosPagoClaveOut.
     * 
     * @return errorOrigen
     */
    public int getErrorOrigen() {
        return errorOrigen;
    }


    /**
     * Sets the errorOrigen value for this DefaultDatosPagoClaveOut.
     *
     * @param errorOrigen el nuevo error origen
     */
    public void setErrorOrigen(int errorOrigen) {
        this.errorOrigen = errorOrigen;
    }


    /**
     * Gets the fechaOperacion value for this DefaultDatosPagoClaveOut.
     * 
     * @return fechaOperacion
     */
    public java.util.Calendar getFechaOperacion() {
        return fechaOperacion;
    }


    /**
     * Sets the fechaOperacion value for this DefaultDatosPagoClaveOut.
     *
     * @param fechaOperacion el nuevo fecha operacion
     */
    public void setFechaOperacion(java.util.Calendar fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }


    /**
     * Gets the fechaProceso value for this DefaultDatosPagoClaveOut.
     * 
     * @return fechaProceso
     */
    public java.util.Calendar getFechaProceso() {
        return fechaProceso;
    }


    /**
     * Sets the fechaProceso value for this DefaultDatosPagoClaveOut.
     *
     * @param fechaProceso el nuevo fecha proceso
     */
    public void setFechaProceso(java.util.Calendar fechaProceso) {
        this.fechaProceso = fechaProceso;
    }


    /**
     * Gets the fueCorrecto value for this DefaultDatosPagoClaveOut.
     * 
     * @return fueCorrecto
     */
    public boolean isFueCorrecto() {
        return fueCorrecto;
    }


    /**
     * Sets the fueCorrecto value for this DefaultDatosPagoClaveOut.
     *
     * @param fueCorrecto el nuevo fue correcto
     */
    public void setFueCorrecto(boolean fueCorrecto) {
        this.fueCorrecto = fueCorrecto;
    }


    /**
     * Gets the importeOperacion value for this DefaultDatosPagoClaveOut.
     * 
     * @return importeOperacion
     */
    public double getImporteOperacion() {
        return importeOperacion;
    }


    /**
     * Sets the importeOperacion value for this DefaultDatosPagoClaveOut.
     *
     * @param importeOperacion el nuevo importe operacion
     */
    public void setImporteOperacion(double importeOperacion) {
        this.importeOperacion = importeOperacion;
    }


    /**
     * Gets the merchan value for this DefaultDatosPagoClaveOut.
     * 
     * @return merchan
     */
    public java.lang.String getMerchan() {
        return merchan;
    }


    /**
     * Sets the merchan value for this DefaultDatosPagoClaveOut.
     *
     * @param merchan el nuevo merchan
     */
    public void setMerchan(java.lang.String merchan) {
        this.merchan = merchan;
    }


    /**
     * Gets the referencia value for this DefaultDatosPagoClaveOut.
     * 
     * @return referencia
     */
    public java.lang.String getReferencia() {
        return referencia;
    }


    /**
     * Sets the referencia value for this DefaultDatosPagoClaveOut.
     *
     * @param referencia el nuevo referencia
     */
    public void setReferencia(java.lang.String referencia) {
        this.referencia = referencia;
    }


    /**
     * Gets the registroAEAT value for this DefaultDatosPagoClaveOut.
     * 
     * @return registroAEAT
     */
    public java.lang.String getRegistroAEAT() {
        return registroAEAT;
    }


    /**
     * Sets the registroAEAT value for this DefaultDatosPagoClaveOut.
     *
     * @param registroAEAT el nuevo registro AEAT
     */
    public void setRegistroAEAT(java.lang.String registroAEAT) {
        this.registroAEAT = registroAEAT;
    }


    /**
     * Gets the errorCodeTexto value for this DefaultDatosPagoClaveOut.
     * 
     * @return errorCodeTexto
     */
    public java.lang.String getErrorCodeTexto() {
        return errorCodeTexto;
    }


    /**
     * Sets the errorCodeTexto value for this DefaultDatosPagoClaveOut.
     *
     * @param errorCodeTexto el nuevo error code texto
     */
    public void setErrorCodeTexto(java.lang.String errorCodeTexto) {
        this.errorCodeTexto = errorCodeTexto;
    }


    /**
     * Gets the errorCodeTextoUsuario value for this DefaultDatosPagoClaveOut.
     * 
     * @return errorCodeTextoUsuario
     */
    public java.lang.String getErrorCodeTextoUsuario() {
        return errorCodeTextoUsuario;
    }


    /**
     * Sets the errorCodeTextoUsuario value for this DefaultDatosPagoClaveOut.
     *
     * @param errorCodeTextoUsuario el nuevo error code texto usuario
     */
    public void setErrorCodeTextoUsuario(java.lang.String errorCodeTextoUsuario) {
        this.errorCodeTextoUsuario = errorCodeTextoUsuario;
    }


    /**
     * Gets the errorOrigenDescription value for this DefaultDatosPagoClaveOut.
     * 
     * @return errorOrigenDescription
     */
    public java.lang.String getErrorOrigenDescription() {
        return errorOrigenDescription;
    }


    /**
     * Sets the errorOrigenDescription value for this DefaultDatosPagoClaveOut.
     *
     * @param errorOrigenDescription el nuevo error origen description
     */
    public void setErrorOrigenDescription(java.lang.String errorOrigenDescription) {
        this.errorOrigenDescription = errorOrigenDescription;
    }


    /**
     * Gets the apoderado value for this DefaultDatosPagoClaveOut.
     * 
     * @return apoderado
     */
    public boolean isApoderado() {
        return apoderado;
    }


    /**
     * Sets the apoderado value for this DefaultDatosPagoClaveOut.
     *
     * @param apoderado el nuevo apoderado
     */
    public void setApoderado(boolean apoderado) {
        this.apoderado = apoderado;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DefaultDatosPagoClaveOut)) return false;
        DefaultDatosPagoClaveOut other = (DefaultDatosPagoClaveOut) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.NRC==null && other.getNRC()==null) || 
             (this.NRC!=null &&
              this.NRC.equals(other.getNRC()))) &&
            ((this.errorCode==null && other.getErrorCode()==null) || 
             (this.errorCode!=null &&
              this.errorCode.equals(other.getErrorCode()))) &&
            ((this.errorDescription==null && other.getErrorDescription()==null) || 
             (this.errorDescription!=null &&
              this.errorDescription.equals(other.getErrorDescription()))) &&
            this.errorOrigen == other.getErrorOrigen() &&
            ((this.fechaOperacion==null && other.getFechaOperacion()==null) || 
             (this.fechaOperacion!=null &&
              this.fechaOperacion.equals(other.getFechaOperacion()))) &&
            ((this.fechaProceso==null && other.getFechaProceso()==null) || 
             (this.fechaProceso!=null &&
              this.fechaProceso.equals(other.getFechaProceso()))) &&
            this.fueCorrecto == other.isFueCorrecto() &&
            this.importeOperacion == other.getImporteOperacion() &&
            ((this.merchan==null && other.getMerchan()==null) || 
             (this.merchan!=null &&
              this.merchan.equals(other.getMerchan()))) &&
            ((this.referencia==null && other.getReferencia()==null) || 
             (this.referencia!=null &&
              this.referencia.equals(other.getReferencia()))) &&
            ((this.registroAEAT==null && other.getRegistroAEAT()==null) || 
             (this.registroAEAT!=null &&
              this.registroAEAT.equals(other.getRegistroAEAT()))) &&
            ((this.errorCodeTexto==null && other.getErrorCodeTexto()==null) || 
             (this.errorCodeTexto!=null &&
              this.errorCodeTexto.equals(other.getErrorCodeTexto()))) &&
            ((this.errorCodeTextoUsuario==null && other.getErrorCodeTextoUsuario()==null) || 
             (this.errorCodeTextoUsuario!=null &&
              this.errorCodeTextoUsuario.equals(other.getErrorCodeTextoUsuario()))) &&
            ((this.errorOrigenDescription==null && other.getErrorOrigenDescription()==null) || 
             (this.errorOrigenDescription!=null &&
              this.errorOrigenDescription.equals(other.getErrorOrigenDescription()))) &&
            this.apoderado == other.isApoderado();
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
        if (getNRC() != null) {
            _hashCode += getNRC().hashCode();
        }
        if (getErrorCode() != null) {
            _hashCode += getErrorCode().hashCode();
        }
        if (getErrorDescription() != null) {
            _hashCode += getErrorDescription().hashCode();
        }
        _hashCode += getErrorOrigen();
        if (getFechaOperacion() != null) {
            _hashCode += getFechaOperacion().hashCode();
        }
        if (getFechaProceso() != null) {
            _hashCode += getFechaProceso().hashCode();
        }
        _hashCode += (isFueCorrecto() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        _hashCode += new Double(getImporteOperacion()).hashCode();
        if (getMerchan() != null) {
            _hashCode += getMerchan().hashCode();
        }
        if (getReferencia() != null) {
            _hashCode += getReferencia().hashCode();
        }
        if (getRegistroAEAT() != null) {
            _hashCode += getRegistroAEAT().hashCode();
        }
        if (getErrorCodeTexto() != null) {
            _hashCode += getErrorCodeTexto().hashCode();
        }
        if (getErrorCodeTextoUsuario() != null) {
            _hashCode += getErrorCodeTextoUsuario().hashCode();
        }
        if (getErrorOrigenDescription() != null) {
            _hashCode += getErrorOrigenDescription().hashCode();
        }
        _hashCode += (isApoderado() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DefaultDatosPagoClaveOut.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoClaveOut"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("NRC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "NRC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errorCode");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "errorCode"));
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
        elemField.setFieldName("fechaOperacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "fechaOperacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
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
        elemField.setFieldName("importeOperacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "importeOperacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("merchan");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "merchan"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
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
        elemField.setFieldName("errorOrigenDescription");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "errorOrigenDescription"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apoderado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "apoderado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(false);
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
