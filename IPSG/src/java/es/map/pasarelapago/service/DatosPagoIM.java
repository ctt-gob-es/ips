/**
 * DatosPagoIM.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.pasarelapago.service;

/**
 * El Class DatosPagoIM.
 */
public class DatosPagoIM  implements java.io.Serializable {
    
    /** el cod tasa. */
    private java.lang.String codTasa;

    /** el codigo banco. */
    private java.lang.String codigoBanco;

    /** el documento obligado. */
    private java.lang.String documentoObligado;

    /** el documento pagador. */
    private java.lang.String documentoPagador;

    /** el fecha operacion. */
    private java.util.Calendar fechaOperacion;

    /** el id tramitacion. */
    private java.lang.String idTramitacion;

    /** el importe. */
    private double importe;

    /** el justificante. */
    private java.lang.String justificante;

    /** el nrc. */
    private java.lang.String nrc;

    /**
     * Crea una nueva datos pago IM.
     */
    public DatosPagoIM() {
    }

    /**
     * Crea una nueva datos pago IM.
     *
     * @param codTasa el cod tasa
     * @param codigoBanco el codigo banco
     * @param documentoObligado el documento obligado
     * @param documentoPagador el documento pagador
     * @param fechaOperacion el fecha operacion
     * @param idTramitacion el id tramitacion
     * @param importe el importe
     * @param justificante el justificante
     * @param nrc el nrc
     */
    public DatosPagoIM(
           java.lang.String codTasa,
           java.lang.String codigoBanco,
           java.lang.String documentoObligado,
           java.lang.String documentoPagador,
           java.util.Calendar fechaOperacion,
           java.lang.String idTramitacion,
           double importe,
           java.lang.String justificante,
           java.lang.String nrc) {
           this.codTasa = codTasa;
           this.codigoBanco = codigoBanco;
           this.documentoObligado = documentoObligado;
           this.documentoPagador = documentoPagador;
           this.fechaOperacion = fechaOperacion;
           this.idTramitacion = idTramitacion;
           this.importe = importe;
           this.justificante = justificante;
           this.nrc = nrc;
    }


    /**
     * Gets the codTasa value for this DatosPagoIM.
     * 
     * @return codTasa
     */
    public java.lang.String getCodTasa() {
        return codTasa;
    }


    /**
     * Sets the codTasa value for this DatosPagoIM.
     *
     * @param codTasa el nuevo cod tasa
     */
    public void setCodTasa(java.lang.String codTasa) {
        this.codTasa = codTasa;
    }


    /**
     * Gets the codigoBanco value for this DatosPagoIM.
     * 
     * @return codigoBanco
     */
    public java.lang.String getCodigoBanco() {
        return codigoBanco;
    }


    /**
     * Sets the codigoBanco value for this DatosPagoIM.
     *
     * @param codigoBanco el nuevo codigo banco
     */
    public void setCodigoBanco(java.lang.String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }


    /**
     * Gets the documentoObligado value for this DatosPagoIM.
     * 
     * @return documentoObligado
     */
    public java.lang.String getDocumentoObligado() {
        return documentoObligado;
    }


    /**
     * Sets the documentoObligado value for this DatosPagoIM.
     *
     * @param documentoObligado el nuevo documento obligado
     */
    public void setDocumentoObligado(java.lang.String documentoObligado) {
        this.documentoObligado = documentoObligado;
    }


    /**
     * Gets the documentoPagador value for this DatosPagoIM.
     * 
     * @return documentoPagador
     */
    public java.lang.String getDocumentoPagador() {
        return documentoPagador;
    }


    /**
     * Sets the documentoPagador value for this DatosPagoIM.
     *
     * @param documentoPagador el nuevo documento pagador
     */
    public void setDocumentoPagador(java.lang.String documentoPagador) {
        this.documentoPagador = documentoPagador;
    }


    /**
     * Gets the fechaOperacion value for this DatosPagoIM.
     * 
     * @return fechaOperacion
     */
    public java.util.Calendar getFechaOperacion() {
        return fechaOperacion;
    }


    /**
     * Sets the fechaOperacion value for this DatosPagoIM.
     *
     * @param fechaOperacion el nuevo fecha operacion
     */
    public void setFechaOperacion(java.util.Calendar fechaOperacion) {
        this.fechaOperacion = fechaOperacion;
    }


    /**
     * Gets the idTramitacion value for this DatosPagoIM.
     * 
     * @return idTramitacion
     */
    public java.lang.String getIdTramitacion() {
        return idTramitacion;
    }


    /**
     * Sets the idTramitacion value for this DatosPagoIM.
     *
     * @param idTramitacion el nuevo id tramitacion
     */
    public void setIdTramitacion(java.lang.String idTramitacion) {
        this.idTramitacion = idTramitacion;
    }


    /**
     * Gets the importe value for this DatosPagoIM.
     * 
     * @return importe
     */
    public double getImporte() {
        return importe;
    }


    /**
     * Sets the importe value for this DatosPagoIM.
     *
     * @param importe el nuevo importe
     */
    public void setImporte(double importe) {
        this.importe = importe;
    }


    /**
     * Gets the justificante value for this DatosPagoIM.
     * 
     * @return justificante
     */
    public java.lang.String getJustificante() {
        return justificante;
    }


    /**
     * Sets the justificante value for this DatosPagoIM.
     *
     * @param justificante el nuevo justificante
     */
    public void setJustificante(java.lang.String justificante) {
        this.justificante = justificante;
    }


    /**
     * Gets the nrc value for this DatosPagoIM.
     * 
     * @return nrc
     */
    public java.lang.String getNrc() {
        return nrc;
    }


    /**
     * Sets the nrc value for this DatosPagoIM.
     *
     * @param nrc el nuevo nrc
     */
    public void setNrc(java.lang.String nrc) {
        this.nrc = nrc;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatosPagoIM)) return false;
        DatosPagoIM other = (DatosPagoIM) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codTasa==null && other.getCodTasa()==null) || 
             (this.codTasa!=null &&
              this.codTasa.equals(other.getCodTasa()))) &&
            ((this.codigoBanco==null && other.getCodigoBanco()==null) || 
             (this.codigoBanco!=null &&
              this.codigoBanco.equals(other.getCodigoBanco()))) &&
            ((this.documentoObligado==null && other.getDocumentoObligado()==null) || 
             (this.documentoObligado!=null &&
              this.documentoObligado.equals(other.getDocumentoObligado()))) &&
            ((this.documentoPagador==null && other.getDocumentoPagador()==null) || 
             (this.documentoPagador!=null &&
              this.documentoPagador.equals(other.getDocumentoPagador()))) &&
            ((this.fechaOperacion==null && other.getFechaOperacion()==null) || 
             (this.fechaOperacion!=null &&
              this.fechaOperacion.equals(other.getFechaOperacion()))) &&
            ((this.idTramitacion==null && other.getIdTramitacion()==null) || 
             (this.idTramitacion!=null &&
              this.idTramitacion.equals(other.getIdTramitacion()))) &&
            this.importe == other.getImporte() &&
            ((this.justificante==null && other.getJustificante()==null) || 
             (this.justificante!=null &&
              this.justificante.equals(other.getJustificante()))) &&
            ((this.nrc==null && other.getNrc()==null) || 
             (this.nrc!=null &&
              this.nrc.equals(other.getNrc())));
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
        if (getCodTasa() != null) {
            _hashCode += getCodTasa().hashCode();
        }
        if (getCodigoBanco() != null) {
            _hashCode += getCodigoBanco().hashCode();
        }
        if (getDocumentoObligado() != null) {
            _hashCode += getDocumentoObligado().hashCode();
        }
        if (getDocumentoPagador() != null) {
            _hashCode += getDocumentoPagador().hashCode();
        }
        if (getFechaOperacion() != null) {
            _hashCode += getFechaOperacion().hashCode();
        }
        if (getIdTramitacion() != null) {
            _hashCode += getIdTramitacion().hashCode();
        }
        _hashCode += new Double(getImporte()).hashCode();
        if (getJustificante() != null) {
            _hashCode += getJustificante().hashCode();
        }
        if (getNrc() != null) {
            _hashCode += getNrc().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatosPagoIM.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "DatosPagoIM"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codTasa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "codTasa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoBanco");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "codigoBanco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentoObligado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "documentoObligado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentoPagador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "documentoPagador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaOperacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "fechaOperacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTramitacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "idTramitacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importe");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "importe"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("justificante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "justificante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nrc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://pasarelapago.map.es/service", "nrc"));
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
