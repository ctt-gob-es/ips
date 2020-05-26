/**
 * DocumentoJustificanteType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.ipsc.clienteWS.registroRec3.type;

/**
 * El Class DocumentoJustificanteType.
 */
public class DocumentoJustificanteType  implements java.io.Serializable {
    
    /** el ds nombre. */
    private java.lang.String dsNombre;

    /** el cd validez. */
    private java.lang.String cdValidez;

    /** el cd tipo. */
    private java.lang.String cdTipo;

    /** el it firmado. */
    private java.lang.String itFirmado;

    /** el bl PK certificado. */
    private java.lang.String blPKCertificado;

    /** el bl time stamp. */
    private java.lang.String blTimeStamp;

    /** el bl validacion OCSP. */
    private java.lang.String blValidacionOCSP;

    /** el bl hash. */
    private java.lang.String blHash;

    /** el ds tipo MIME. */
    private java.lang.String dsTipoMIME;

    /** el id documento. */
    private java.lang.String idDocumento;

    /** el tl observaciones. */
    private java.lang.String tlObservaciones;

    /**
     * Crea una nueva documento justificante type.
     */
    public DocumentoJustificanteType() {
    }

    /**
     * Crea una nueva documento justificante type.
     *
     * @param dsNombre el ds nombre
     * @param cdValidez el cd validez
     * @param cdTipo el cd tipo
     * @param itFirmado el it firmado
     * @param blPKCertificado el bl PK certificado
     * @param blTimeStamp el bl time stamp
     * @param blValidacionOCSP el bl validacion OCSP
     * @param blHash el bl hash
     * @param dsTipoMIME el ds tipo MIME
     * @param idDocumento el id documento
     * @param tlObservaciones el tl observaciones
     */
    public DocumentoJustificanteType(
           java.lang.String dsNombre,
           java.lang.String cdValidez,
           java.lang.String cdTipo,
           java.lang.String itFirmado,
           java.lang.String blPKCertificado,
           java.lang.String blTimeStamp,
           java.lang.String blValidacionOCSP,
           java.lang.String blHash,
           java.lang.String dsTipoMIME,
           java.lang.String idDocumento,
           java.lang.String tlObservaciones) {
           this.dsNombre = dsNombre;
           this.cdValidez = cdValidez;
           this.cdTipo = cdTipo;
           this.itFirmado = itFirmado;
           this.blPKCertificado = blPKCertificado;
           this.blTimeStamp = blTimeStamp;
           this.blValidacionOCSP = blValidacionOCSP;
           this.blHash = blHash;
           this.dsTipoMIME = dsTipoMIME;
           this.idDocumento = idDocumento;
           this.tlObservaciones = tlObservaciones;
    }


    /**
     * Gets the dsNombre value for this DocumentoJustificanteType.
     * 
     * @return dsNombre
     */
    public java.lang.String getDsNombre() {
        return dsNombre;
    }


    /**
     * Sets the dsNombre value for this DocumentoJustificanteType.
     *
     * @param dsNombre el nuevo ds nombre
     */
    public void setDsNombre(java.lang.String dsNombre) {
        this.dsNombre = dsNombre;
    }


    /**
     * Gets the cdValidez value for this DocumentoJustificanteType.
     * 
     * @return cdValidez
     */
    public java.lang.String getCdValidez() {
        return cdValidez;
    }


    /**
     * Sets the cdValidez value for this DocumentoJustificanteType.
     *
     * @param cdValidez el nuevo cd validez
     */
    public void setCdValidez(java.lang.String cdValidez) {
        this.cdValidez = cdValidez;
    }


    /**
     * Gets the cdTipo value for this DocumentoJustificanteType.
     * 
     * @return cdTipo
     */
    public java.lang.String getCdTipo() {
        return cdTipo;
    }


    /**
     * Sets the cdTipo value for this DocumentoJustificanteType.
     *
     * @param cdTipo el nuevo cd tipo
     */
    public void setCdTipo(java.lang.String cdTipo) {
        this.cdTipo = cdTipo;
    }


    /**
     * Gets the itFirmado value for this DocumentoJustificanteType.
     * 
     * @return itFirmado
     */
    public java.lang.String getItFirmado() {
        return itFirmado;
    }


    /**
     * Sets the itFirmado value for this DocumentoJustificanteType.
     *
     * @param itFirmado el nuevo it firmado
     */
    public void setItFirmado(java.lang.String itFirmado) {
        this.itFirmado = itFirmado;
    }


    /**
     * Gets the blPKCertificado value for this DocumentoJustificanteType.
     * 
     * @return blPKCertificado
     */
    public java.lang.String getBlPKCertificado() {
        return blPKCertificado;
    }


    /**
     * Sets the blPKCertificado value for this DocumentoJustificanteType.
     *
     * @param blPKCertificado el nuevo bl PK certificado
     */
    public void setBlPKCertificado(java.lang.String blPKCertificado) {
        this.blPKCertificado = blPKCertificado;
    }


    /**
     * Gets the blTimeStamp value for this DocumentoJustificanteType.
     * 
     * @return blTimeStamp
     */
    public java.lang.String getBlTimeStamp() {
        return blTimeStamp;
    }


    /**
     * Sets the blTimeStamp value for this DocumentoJustificanteType.
     *
     * @param blTimeStamp el nuevo bl time stamp
     */
    public void setBlTimeStamp(java.lang.String blTimeStamp) {
        this.blTimeStamp = blTimeStamp;
    }


    /**
     * Gets the blValidacionOCSP value for this DocumentoJustificanteType.
     * 
     * @return blValidacionOCSP
     */
    public java.lang.String getBlValidacionOCSP() {
        return blValidacionOCSP;
    }


    /**
     * Sets the blValidacionOCSP value for this DocumentoJustificanteType.
     *
     * @param blValidacionOCSP el nuevo bl validacion OCSP
     */
    public void setBlValidacionOCSP(java.lang.String blValidacionOCSP) {
        this.blValidacionOCSP = blValidacionOCSP;
    }


    /**
     * Gets the blHash value for this DocumentoJustificanteType.
     * 
     * @return blHash
     */
    public java.lang.String getBlHash() {
        return blHash;
    }


    /**
     * Sets the blHash value for this DocumentoJustificanteType.
     *
     * @param blHash el nuevo bl hash
     */
    public void setBlHash(java.lang.String blHash) {
        this.blHash = blHash;
    }


    /**
     * Gets the dsTipoMIME value for this DocumentoJustificanteType.
     * 
     * @return dsTipoMIME
     */
    public java.lang.String getDsTipoMIME() {
        return dsTipoMIME;
    }


    /**
     * Sets the dsTipoMIME value for this DocumentoJustificanteType.
     *
     * @param dsTipoMIME el nuevo ds tipo MIME
     */
    public void setDsTipoMIME(java.lang.String dsTipoMIME) {
        this.dsTipoMIME = dsTipoMIME;
    }


    /**
     * Gets the idDocumento value for this DocumentoJustificanteType.
     * 
     * @return idDocumento
     */
    public java.lang.String getIdDocumento() {
        return idDocumento;
    }


    /**
     * Sets the idDocumento value for this DocumentoJustificanteType.
     *
     * @param idDocumento el nuevo id documento
     */
    public void setIdDocumento(java.lang.String idDocumento) {
        this.idDocumento = idDocumento;
    }


    /**
     * Gets the tlObservaciones value for this DocumentoJustificanteType.
     * 
     * @return tlObservaciones
     */
    public java.lang.String getTlObservaciones() {
        return tlObservaciones;
    }


    /**
     * Sets the tlObservaciones value for this DocumentoJustificanteType.
     *
     * @param tlObservaciones el nuevo tl observaciones
     */
    public void setTlObservaciones(java.lang.String tlObservaciones) {
        this.tlObservaciones = tlObservaciones;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DocumentoJustificanteType)) return false;
        DocumentoJustificanteType other = (DocumentoJustificanteType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dsNombre==null && other.getDsNombre()==null) || 
             (this.dsNombre!=null &&
              this.dsNombre.equals(other.getDsNombre()))) &&
            ((this.cdValidez==null && other.getCdValidez()==null) || 
             (this.cdValidez!=null &&
              this.cdValidez.equals(other.getCdValidez()))) &&
            ((this.cdTipo==null && other.getCdTipo()==null) || 
             (this.cdTipo!=null &&
              this.cdTipo.equals(other.getCdTipo()))) &&
            ((this.itFirmado==null && other.getItFirmado()==null) || 
             (this.itFirmado!=null &&
              this.itFirmado.equals(other.getItFirmado()))) &&
            ((this.blPKCertificado==null && other.getBlPKCertificado()==null) || 
             (this.blPKCertificado!=null &&
              this.blPKCertificado.equals(other.getBlPKCertificado()))) &&
            ((this.blTimeStamp==null && other.getBlTimeStamp()==null) || 
             (this.blTimeStamp!=null &&
              this.blTimeStamp.equals(other.getBlTimeStamp()))) &&
            ((this.blValidacionOCSP==null && other.getBlValidacionOCSP()==null) || 
             (this.blValidacionOCSP!=null &&
              this.blValidacionOCSP.equals(other.getBlValidacionOCSP()))) &&
            ((this.blHash==null && other.getBlHash()==null) || 
             (this.blHash!=null &&
              this.blHash.equals(other.getBlHash()))) &&
            ((this.dsTipoMIME==null && other.getDsTipoMIME()==null) || 
             (this.dsTipoMIME!=null &&
              this.dsTipoMIME.equals(other.getDsTipoMIME()))) &&
            ((this.idDocumento==null && other.getIdDocumento()==null) || 
             (this.idDocumento!=null &&
              this.idDocumento.equals(other.getIdDocumento()))) &&
            ((this.tlObservaciones==null && other.getTlObservaciones()==null) || 
             (this.tlObservaciones!=null &&
              this.tlObservaciones.equals(other.getTlObservaciones())));
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
        if (getDsNombre() != null) {
            _hashCode += getDsNombre().hashCode();
        }
        if (getCdValidez() != null) {
            _hashCode += getCdValidez().hashCode();
        }
        if (getCdTipo() != null) {
            _hashCode += getCdTipo().hashCode();
        }
        if (getItFirmado() != null) {
            _hashCode += getItFirmado().hashCode();
        }
        if (getBlPKCertificado() != null) {
            _hashCode += getBlPKCertificado().hashCode();
        }
        if (getBlTimeStamp() != null) {
            _hashCode += getBlTimeStamp().hashCode();
        }
        if (getBlValidacionOCSP() != null) {
            _hashCode += getBlValidacionOCSP().hashCode();
        }
        if (getBlHash() != null) {
            _hashCode += getBlHash().hashCode();
        }
        if (getDsTipoMIME() != null) {
            _hashCode += getDsTipoMIME().hashCode();
        }
        if (getIdDocumento() != null) {
            _hashCode += getIdDocumento().hashCode();
        }
        if (getTlObservaciones() != null) {
            _hashCode += getTlObservaciones().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DocumentoJustificanteType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://type.ws2.rec2are.map.es", "DocumentoJustificanteType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsNombre");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dsNombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdValidez");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdValidez"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdTipo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdTipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("itFirmado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "itFirmado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blPKCertificado");
        elemField.setXmlName(new javax.xml.namespace.QName("", "blPKCertificado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blTimeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "blTimeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blValidacionOCSP");
        elemField.setXmlName(new javax.xml.namespace.QName("", "blValidacionOCSP"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blHash");
        elemField.setXmlName(new javax.xml.namespace.QName("", "blHash"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsTipoMIME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dsTipoMIME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idDocumento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlObservaciones");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlObservaciones"));
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
