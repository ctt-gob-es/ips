/**
 * Transmision.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.redsara.intermediacion.scsp.esquemas.ws.respuesta;

/**
 * El Class Transmision.
 */
public class Transmision  implements java.io.Serializable {
    
    /** el codigo certificado. */
    private java.lang.String codigoCertificado;

    /** el id solicitud. */
    private java.lang.String idSolicitud;

    /** el id transmision. */
    private java.lang.String idTransmision;

    /** el fecha generacion. */
    private java.lang.String fechaGeneracion;

    /**
     * Crea una nueva transmision.
     */
    public Transmision() {
    }

    /**
     * Crea una nueva transmision.
     *
     * @param codigoCertificado el codigo certificado
     * @param idSolicitud el id solicitud
     * @param idTransmision el id transmision
     * @param fechaGeneracion el fecha generacion
     */
    public Transmision(
           java.lang.String codigoCertificado,
           java.lang.String idSolicitud,
           java.lang.String idTransmision,
           java.lang.String fechaGeneracion) {
           this.codigoCertificado = codigoCertificado;
           this.idSolicitud = idSolicitud;
           this.idTransmision = idTransmision;
           this.fechaGeneracion = fechaGeneracion;
    }


    /**
     * Gets the codigoCertificado value for this Transmision.
     * 
     * @return codigoCertificado
     */
    public java.lang.String getCodigoCertificado() {
        return codigoCertificado;
    }


    /**
     * Sets the codigoCertificado value for this Transmision.
     *
     * @param codigoCertificado el nuevo codigo certificado
     */
    public void setCodigoCertificado(java.lang.String codigoCertificado) {
        this.codigoCertificado = codigoCertificado;
    }


    /**
     * Gets the idSolicitud value for this Transmision.
     * 
     * @return idSolicitud
     */
    public java.lang.String getIdSolicitud() {
        return idSolicitud;
    }


    /**
     * Sets the idSolicitud value for this Transmision.
     *
     * @param idSolicitud el nuevo id solicitud
     */
    public void setIdSolicitud(java.lang.String idSolicitud) {
        this.idSolicitud = idSolicitud;
    }


    /**
     * Gets the idTransmision value for this Transmision.
     * 
     * @return idTransmision
     */
    public java.lang.String getIdTransmision() {
        return idTransmision;
    }


    /**
     * Sets the idTransmision value for this Transmision.
     *
     * @param idTransmision el nuevo id transmision
     */
    public void setIdTransmision(java.lang.String idTransmision) {
        this.idTransmision = idTransmision;
    }


    /**
     * Gets the fechaGeneracion value for this Transmision.
     * 
     * @return fechaGeneracion
     */
    public java.lang.String getFechaGeneracion() {
        return fechaGeneracion;
    }


    /**
     * Sets the fechaGeneracion value for this Transmision.
     *
     * @param fechaGeneracion el nuevo fecha generacion
     */
    public void setFechaGeneracion(java.lang.String fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Transmision)) return false;
        Transmision other = (Transmision) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoCertificado==null && other.getCodigoCertificado()==null) || 
             (this.codigoCertificado!=null &&
              this.codigoCertificado.equals(other.getCodigoCertificado()))) &&
            ((this.idSolicitud==null && other.getIdSolicitud()==null) || 
             (this.idSolicitud!=null &&
              this.idSolicitud.equals(other.getIdSolicitud()))) &&
            ((this.idTransmision==null && other.getIdTransmision()==null) || 
             (this.idTransmision!=null &&
              this.idTransmision.equals(other.getIdTransmision()))) &&
            ((this.fechaGeneracion==null && other.getFechaGeneracion()==null) || 
             (this.fechaGeneracion!=null &&
              this.fechaGeneracion.equals(other.getFechaGeneracion())));
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
        if (getCodigoCertificado() != null) {
            _hashCode += getCodigoCertificado().hashCode();
        }
        if (getIdSolicitud() != null) {
            _hashCode += getIdSolicitud().hashCode();
        }
        if (getIdTransmision() != null) {
            _hashCode += getIdTransmision().hashCode();
        }
        if (getFechaGeneracion() != null) {
            _hashCode += getFechaGeneracion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Transmision.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", ">Transmision"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoCertificado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", "CodigoCertificado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idSolicitud");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", "IdSolicitud"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTransmision");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", "IdTransmision"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaGeneracion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://intermediacion.redsara.es/scsp/esquemas/ws/respuesta", "FechaGeneracion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
