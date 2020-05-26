/**
 * PagoPendiente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.pasarelapago.service;

/**
 * El Class PagoPendiente.
 */
public class PagoPendiente  implements java.io.Serializable {
    
    /** el codigo tasa. */
    private java.lang.String codigoTasa;

    /** el documento obligado. */
    private java.lang.String documentoObligado;

    /** el id tramitacion. */
    private java.lang.String idTramitacion;

    /** el fecha limite. */
    private java.util.Calendar fechaLimite;

    /** el justificante. */
    private java.lang.String justificante;

    /** el mail obligado. */
    private java.lang.String mailObligado;

    /** el lista datos adicionales. */
    private es.map.pasarelapago.integracionMixta.ListaDatosAdicionales listaDatosAdicionales;

    /**
     * Crea una nueva pago pendiente.
     */
    public PagoPendiente() {
    }

    /**
     * Crea una nueva pago pendiente.
     *
     * @param codigoTasa el codigo tasa
     * @param documentoObligado el documento obligado
     * @param idTramitacion el id tramitacion
     * @param fechaLimite el fecha limite
     * @param justificante el justificante
     * @param mailObligado el mail obligado
     * @param listaDatosAdicionales el lista datos adicionales
     */
    public PagoPendiente(
           java.lang.String codigoTasa,
           java.lang.String documentoObligado,
           java.lang.String idTramitacion,
           java.util.Calendar fechaLimite,
           java.lang.String justificante,
           java.lang.String mailObligado,
           es.map.pasarelapago.integracionMixta.ListaDatosAdicionales listaDatosAdicionales) {
           this.codigoTasa = codigoTasa;
           this.documentoObligado = documentoObligado;
           this.idTramitacion = idTramitacion;
           this.fechaLimite = fechaLimite;
           this.justificante = justificante;
           this.mailObligado = mailObligado;
           this.listaDatosAdicionales = listaDatosAdicionales;
    }


    /**
     * Gets the codigoTasa value for this PagoPendiente.
     * 
     * @return codigoTasa
     */
    public java.lang.String getCodigoTasa() {
        return codigoTasa;
    }


    /**
     * Sets the codigoTasa value for this PagoPendiente.
     *
     * @param codigoTasa el nuevo codigo tasa
     */
    public void setCodigoTasa(java.lang.String codigoTasa) {
        this.codigoTasa = codigoTasa;
    }


    /**
     * Gets the documentoObligado value for this PagoPendiente.
     * 
     * @return documentoObligado
     */
    public java.lang.String getDocumentoObligado() {
        return documentoObligado;
    }


    /**
     * Sets the documentoObligado value for this PagoPendiente.
     *
     * @param documentoObligado el nuevo documento obligado
     */
    public void setDocumentoObligado(java.lang.String documentoObligado) {
        this.documentoObligado = documentoObligado;
    }


    /**
     * Gets the idTramitacion value for this PagoPendiente.
     * 
     * @return idTramitacion
     */
    public java.lang.String getIdTramitacion() {
        return idTramitacion;
    }


    /**
     * Sets the idTramitacion value for this PagoPendiente.
     *
     * @param idTramitacion el nuevo id tramitacion
     */
    public void setIdTramitacion(java.lang.String idTramitacion) {
        this.idTramitacion = idTramitacion;
    }


    /**
     * Gets the fechaLimite value for this PagoPendiente.
     * 
     * @return fechaLimite
     */
    public java.util.Calendar getFechaLimite() {
        return fechaLimite;
    }


    /**
     * Sets the fechaLimite value for this PagoPendiente.
     *
     * @param fechaLimite el nuevo fecha limite
     */
    public void setFechaLimite(java.util.Calendar fechaLimite) {
        this.fechaLimite = fechaLimite;
    }


    /**
     * Gets the justificante value for this PagoPendiente.
     * 
     * @return justificante
     */
    public java.lang.String getJustificante() {
        return justificante;
    }


    /**
     * Sets the justificante value for this PagoPendiente.
     *
     * @param justificante el nuevo justificante
     */
    public void setJustificante(java.lang.String justificante) {
        this.justificante = justificante;
    }


    /**
     * Gets the mailObligado value for this PagoPendiente.
     * 
     * @return mailObligado
     */
    public java.lang.String getMailObligado() {
        return mailObligado;
    }


    /**
     * Sets the mailObligado value for this PagoPendiente.
     *
     * @param mailObligado el nuevo mail obligado
     */
    public void setMailObligado(java.lang.String mailObligado) {
        this.mailObligado = mailObligado;
    }


    /**
     * Gets the listaDatosAdicionales value for this PagoPendiente.
     * 
     * @return listaDatosAdicionales
     */
    public es.map.pasarelapago.integracionMixta.ListaDatosAdicionales getListaDatosAdicionales() {
        return listaDatosAdicionales;
    }


    /**
     * Sets the listaDatosAdicionales value for this PagoPendiente.
     *
     * @param listaDatosAdicionales el nuevo lista datos adicionales
     */
    public void setListaDatosAdicionales(es.map.pasarelapago.integracionMixta.ListaDatosAdicionales listaDatosAdicionales) {
        this.listaDatosAdicionales = listaDatosAdicionales;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PagoPendiente)) return false;
        PagoPendiente other = (PagoPendiente) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codigoTasa==null && other.getCodigoTasa()==null) || 
             (this.codigoTasa!=null &&
              this.codigoTasa.equals(other.getCodigoTasa()))) &&
            ((this.documentoObligado==null && other.getDocumentoObligado()==null) || 
             (this.documentoObligado!=null &&
              this.documentoObligado.equals(other.getDocumentoObligado()))) &&
            ((this.idTramitacion==null && other.getIdTramitacion()==null) || 
             (this.idTramitacion!=null &&
              this.idTramitacion.equals(other.getIdTramitacion()))) &&
            ((this.fechaLimite==null && other.getFechaLimite()==null) || 
             (this.fechaLimite!=null &&
              this.fechaLimite.equals(other.getFechaLimite()))) &&
            ((this.justificante==null && other.getJustificante()==null) || 
             (this.justificante!=null &&
              this.justificante.equals(other.getJustificante()))) &&
            ((this.mailObligado==null && other.getMailObligado()==null) || 
             (this.mailObligado!=null &&
              this.mailObligado.equals(other.getMailObligado()))) &&
            ((this.listaDatosAdicionales==null && other.getListaDatosAdicionales()==null) || 
             (this.listaDatosAdicionales!=null &&
              this.listaDatosAdicionales.equals(other.getListaDatosAdicionales())));
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
        if (getCodigoTasa() != null) {
            _hashCode += getCodigoTasa().hashCode();
        }
        if (getDocumentoObligado() != null) {
            _hashCode += getDocumentoObligado().hashCode();
        }
        if (getIdTramitacion() != null) {
            _hashCode += getIdTramitacion().hashCode();
        }
        if (getFechaLimite() != null) {
            _hashCode += getFechaLimite().hashCode();
        }
        if (getJustificante() != null) {
            _hashCode += getJustificante().hashCode();
        }
        if (getMailObligado() != null) {
            _hashCode += getMailObligado().hashCode();
        }
        if (getListaDatosAdicionales() != null) {
            _hashCode += getListaDatosAdicionales().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PagoPendiente.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "PagoPendiente"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTasa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "codigoTasa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentoObligado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "documentoObligado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTramitacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "idTramitacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaLimite");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "fechaLimite"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("justificante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "justificante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mailObligado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "mailObligado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("listaDatosAdicionales");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "listaDatosAdicionales"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://pasarelapago.map.es/integracionMixta", "ListaDatosAdicionales"));
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
