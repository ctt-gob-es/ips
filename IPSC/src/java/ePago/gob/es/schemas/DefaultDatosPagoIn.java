/**
 * DefaultDatosPagoIn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ePago.gob.es.schemas;

/**
 * El Class DefaultDatosPagoIn.
 */
public class DefaultDatosPagoIn  implements java.io.Serializable {
    
    /** el ccc. */
    private java.lang.String CCC;

    /** el apellido 1. */
    private java.lang.String apellido1;

    /** el apellido 2. */
    private java.lang.String apellido2;

    /** el certificado 1. */
    private java.lang.String certificado1;

    /** el certificado 2. */
    private java.lang.String certificado2;

    /** el codigo banco. */
    private java.lang.String codigoBanco;

    /** el codigo tasa. */
    private java.lang.String codigoTasa;

    /** el documento obligado. */
    private java.lang.String documentoObligado;

    /** el documento pagador. */
    private java.lang.String documentoPagador;

    /** el fecha caducidad tarjeta. */
    private java.util.Calendar fechaCaducidadTarjeta;

    /** el firma 1. */
    private java.lang.String firma1;

    /** el firma 2. */
    private java.lang.String firma2;

    /** el hash origen firma. */
    private java.lang.String hashOrigenFirma;

    /** el importe. */
    private double importe;

    /** el justificante. */
    private java.lang.String justificante;

    /** el nombre. */
    private java.lang.String nombre;

    /** el numero tarjeta. */
    private java.lang.String numeroTarjeta;

    /** el origen firma. */
    private java.lang.String origenFirma;

    /** el tipo cargo. */
    private ePago.gob.es.schemas.TiposCargo tipoCargo;

    /** el tipo documento obligado. */
    private ePago.gob.es.schemas.TiposDocumento tipoDocumentoObligado;

    /** el tipo documento pagador. */
    private ePago.gob.es.schemas.TiposDocumento tipoDocumentoPagador;

    /** el apoderado. */
    private boolean apoderado;

    /**
     * Crea una nueva default datos pago in.
     */
    public DefaultDatosPagoIn() {
    }

    /**
     * Crea una nueva default datos pago in.
     *
     * @param CCC el ccc
     * @param apellido1 el apellido 1
     * @param apellido2 el apellido 2
     * @param certificado1 el certificado 1
     * @param certificado2 el certificado 2
     * @param codigoBanco el codigo banco
     * @param codigoTasa el codigo tasa
     * @param documentoObligado el documento obligado
     * @param documentoPagador el documento pagador
     * @param fechaCaducidadTarjeta el fecha caducidad tarjeta
     * @param firma1 el firma 1
     * @param firma2 el firma 2
     * @param hashOrigenFirma el hash origen firma
     * @param importe el importe
     * @param justificante el justificante
     * @param nombre el nombre
     * @param numeroTarjeta el numero tarjeta
     * @param origenFirma el origen firma
     * @param tipoCargo el tipo cargo
     * @param tipoDocumentoObligado el tipo documento obligado
     * @param tipoDocumentoPagador el tipo documento pagador
     * @param apoderado el apoderado
     */
    public DefaultDatosPagoIn(
           java.lang.String CCC,
           java.lang.String apellido1,
           java.lang.String apellido2,
           java.lang.String certificado1,
           java.lang.String certificado2,
           java.lang.String codigoBanco,
           java.lang.String codigoTasa,
           java.lang.String documentoObligado,
           java.lang.String documentoPagador,
           java.util.Calendar fechaCaducidadTarjeta,
           java.lang.String firma1,
           java.lang.String firma2,
           java.lang.String hashOrigenFirma,
           double importe,
           java.lang.String justificante,
           java.lang.String nombre,
           java.lang.String numeroTarjeta,
           java.lang.String origenFirma,
           ePago.gob.es.schemas.TiposCargo tipoCargo,
           ePago.gob.es.schemas.TiposDocumento tipoDocumentoObligado,
           ePago.gob.es.schemas.TiposDocumento tipoDocumentoPagador,
           boolean apoderado) {
           this.CCC = CCC;
           this.apellido1 = apellido1;
           this.apellido2 = apellido2;
           this.certificado1 = certificado1;
           this.certificado2 = certificado2;
           this.codigoBanco = codigoBanco;
           this.codigoTasa = codigoTasa;
           this.documentoObligado = documentoObligado;
           this.documentoPagador = documentoPagador;
           this.fechaCaducidadTarjeta = fechaCaducidadTarjeta;
           this.firma1 = firma1;
           this.firma2 = firma2;
           this.hashOrigenFirma = hashOrigenFirma;
           this.importe = importe;
           this.justificante = justificante;
           this.nombre = nombre;
           this.numeroTarjeta = numeroTarjeta;
           this.origenFirma = origenFirma;
           this.tipoCargo = tipoCargo;
           this.tipoDocumentoObligado = tipoDocumentoObligado;
           this.tipoDocumentoPagador = tipoDocumentoPagador;
           this.apoderado = apoderado;
    }


    /**
     * Gets the CCC value for this DefaultDatosPagoIn.
     * 
     * @return CCC
     */
    public java.lang.String getCCC() {
        return CCC;
    }


    /**
     * Sets the CCC value for this DefaultDatosPagoIn.
     *
     * @param CCC el nuevo ccc
     */
    public void setCCC(java.lang.String CCC) {
        this.CCC = CCC;
    }


    /**
     * Gets the apellido1 value for this DefaultDatosPagoIn.
     * 
     * @return apellido1
     */
    public java.lang.String getApellido1() {
        return apellido1;
    }


    /**
     * Sets the apellido1 value for this DefaultDatosPagoIn.
     *
     * @param apellido1 el nuevo apellido 1
     */
    public void setApellido1(java.lang.String apellido1) {
        this.apellido1 = apellido1;
    }


    /**
     * Gets the apellido2 value for this DefaultDatosPagoIn.
     * 
     * @return apellido2
     */
    public java.lang.String getApellido2() {
        return apellido2;
    }


    /**
     * Sets the apellido2 value for this DefaultDatosPagoIn.
     *
     * @param apellido2 el nuevo apellido 2
     */
    public void setApellido2(java.lang.String apellido2) {
        this.apellido2 = apellido2;
    }


    /**
     * Gets the certificado1 value for this DefaultDatosPagoIn.
     * 
     * @return certificado1
     */
    public java.lang.String getCertificado1() {
        return certificado1;
    }


    /**
     * Sets the certificado1 value for this DefaultDatosPagoIn.
     *
     * @param certificado1 el nuevo certificado 1
     */
    public void setCertificado1(java.lang.String certificado1) {
        this.certificado1 = certificado1;
    }


    /**
     * Gets the certificado2 value for this DefaultDatosPagoIn.
     * 
     * @return certificado2
     */
    public java.lang.String getCertificado2() {
        return certificado2;
    }


    /**
     * Sets the certificado2 value for this DefaultDatosPagoIn.
     *
     * @param certificado2 el nuevo certificado 2
     */
    public void setCertificado2(java.lang.String certificado2) {
        this.certificado2 = certificado2;
    }


    /**
     * Gets the codigoBanco value for this DefaultDatosPagoIn.
     * 
     * @return codigoBanco
     */
    public java.lang.String getCodigoBanco() {
        return codigoBanco;
    }


    /**
     * Sets the codigoBanco value for this DefaultDatosPagoIn.
     *
     * @param codigoBanco el nuevo codigo banco
     */
    public void setCodigoBanco(java.lang.String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }


    /**
     * Gets the codigoTasa value for this DefaultDatosPagoIn.
     * 
     * @return codigoTasa
     */
    public java.lang.String getCodigoTasa() {
        return codigoTasa;
    }


    /**
     * Sets the codigoTasa value for this DefaultDatosPagoIn.
     *
     * @param codigoTasa el nuevo codigo tasa
     */
    public void setCodigoTasa(java.lang.String codigoTasa) {
        this.codigoTasa = codigoTasa;
    }


    /**
     * Gets the documentoObligado value for this DefaultDatosPagoIn.
     * 
     * @return documentoObligado
     */
    public java.lang.String getDocumentoObligado() {
        return documentoObligado;
    }


    /**
     * Sets the documentoObligado value for this DefaultDatosPagoIn.
     *
     * @param documentoObligado el nuevo documento obligado
     */
    public void setDocumentoObligado(java.lang.String documentoObligado) {
        this.documentoObligado = documentoObligado;
    }


    /**
     * Gets the documentoPagador value for this DefaultDatosPagoIn.
     * 
     * @return documentoPagador
     */
    public java.lang.String getDocumentoPagador() {
        return documentoPagador;
    }


    /**
     * Sets the documentoPagador value for this DefaultDatosPagoIn.
     *
     * @param documentoPagador el nuevo documento pagador
     */
    public void setDocumentoPagador(java.lang.String documentoPagador) {
        this.documentoPagador = documentoPagador;
    }


    /**
     * Gets the fechaCaducidadTarjeta value for this DefaultDatosPagoIn.
     * 
     * @return fechaCaducidadTarjeta
     */
    public java.util.Calendar getFechaCaducidadTarjeta() {
        return fechaCaducidadTarjeta;
    }


    /**
     * Sets the fechaCaducidadTarjeta value for this DefaultDatosPagoIn.
     *
     * @param fechaCaducidadTarjeta el nuevo fecha caducidad tarjeta
     */
    public void setFechaCaducidadTarjeta(java.util.Calendar fechaCaducidadTarjeta) {
        this.fechaCaducidadTarjeta = fechaCaducidadTarjeta;
    }


    /**
     * Gets the firma1 value for this DefaultDatosPagoIn.
     * 
     * @return firma1
     */
    public java.lang.String getFirma1() {
        return firma1;
    }


    /**
     * Sets the firma1 value for this DefaultDatosPagoIn.
     *
     * @param firma1 el nuevo firma 1
     */
    public void setFirma1(java.lang.String firma1) {
        this.firma1 = firma1;
    }


    /**
     * Gets the firma2 value for this DefaultDatosPagoIn.
     * 
     * @return firma2
     */
    public java.lang.String getFirma2() {
        return firma2;
    }


    /**
     * Sets the firma2 value for this DefaultDatosPagoIn.
     *
     * @param firma2 el nuevo firma 2
     */
    public void setFirma2(java.lang.String firma2) {
        this.firma2 = firma2;
    }


    /**
     * Gets the hashOrigenFirma value for this DefaultDatosPagoIn.
     * 
     * @return hashOrigenFirma
     */
    public java.lang.String getHashOrigenFirma() {
        return hashOrigenFirma;
    }


    /**
     * Sets the hashOrigenFirma value for this DefaultDatosPagoIn.
     *
     * @param hashOrigenFirma el nuevo hash origen firma
     */
    public void setHashOrigenFirma(java.lang.String hashOrigenFirma) {
        this.hashOrigenFirma = hashOrigenFirma;
    }


    /**
     * Gets the importe value for this DefaultDatosPagoIn.
     * 
     * @return importe
     */
    public double getImporte() {
        return importe;
    }


    /**
     * Sets the importe value for this DefaultDatosPagoIn.
     *
     * @param importe el nuevo importe
     */
    public void setImporte(double importe) {
        this.importe = importe;
    }


    /**
     * Gets the justificante value for this DefaultDatosPagoIn.
     * 
     * @return justificante
     */
    public java.lang.String getJustificante() {
        return justificante;
    }


    /**
     * Sets the justificante value for this DefaultDatosPagoIn.
     *
     * @param justificante el nuevo justificante
     */
    public void setJustificante(java.lang.String justificante) {
        this.justificante = justificante;
    }


    /**
     * Gets the nombre value for this DefaultDatosPagoIn.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this DefaultDatosPagoIn.
     *
     * @param nombre el nuevo nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the numeroTarjeta value for this DefaultDatosPagoIn.
     * 
     * @return numeroTarjeta
     */
    public java.lang.String getNumeroTarjeta() {
        return numeroTarjeta;
    }


    /**
     * Sets the numeroTarjeta value for this DefaultDatosPagoIn.
     *
     * @param numeroTarjeta el nuevo numero tarjeta
     */
    public void setNumeroTarjeta(java.lang.String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }


    /**
     * Gets the origenFirma value for this DefaultDatosPagoIn.
     * 
     * @return origenFirma
     */
    public java.lang.String getOrigenFirma() {
        return origenFirma;
    }


    /**
     * Sets the origenFirma value for this DefaultDatosPagoIn.
     *
     * @param origenFirma el nuevo origen firma
     */
    public void setOrigenFirma(java.lang.String origenFirma) {
        this.origenFirma = origenFirma;
    }


    /**
     * Gets the tipoCargo value for this DefaultDatosPagoIn.
     * 
     * @return tipoCargo
     */
    public ePago.gob.es.schemas.TiposCargo getTipoCargo() {
        return tipoCargo;
    }


    /**
     * Sets the tipoCargo value for this DefaultDatosPagoIn.
     *
     * @param tipoCargo el nuevo tipo cargo
     */
    public void setTipoCargo(ePago.gob.es.schemas.TiposCargo tipoCargo) {
        this.tipoCargo = tipoCargo;
    }


    /**
     * Gets the tipoDocumentoObligado value for this DefaultDatosPagoIn.
     * 
     * @return tipoDocumentoObligado
     */
    public ePago.gob.es.schemas.TiposDocumento getTipoDocumentoObligado() {
        return tipoDocumentoObligado;
    }


    /**
     * Sets the tipoDocumentoObligado value for this DefaultDatosPagoIn.
     *
     * @param tipoDocumentoObligado el nuevo tipo documento obligado
     */
    public void setTipoDocumentoObligado(ePago.gob.es.schemas.TiposDocumento tipoDocumentoObligado) {
        this.tipoDocumentoObligado = tipoDocumentoObligado;
    }


    /**
     * Gets the tipoDocumentoPagador value for this DefaultDatosPagoIn.
     * 
     * @return tipoDocumentoPagador
     */
    public ePago.gob.es.schemas.TiposDocumento getTipoDocumentoPagador() {
        return tipoDocumentoPagador;
    }


    /**
     * Sets the tipoDocumentoPagador value for this DefaultDatosPagoIn.
     *
     * @param tipoDocumentoPagador el nuevo tipo documento pagador
     */
    public void setTipoDocumentoPagador(ePago.gob.es.schemas.TiposDocumento tipoDocumentoPagador) {
        this.tipoDocumentoPagador = tipoDocumentoPagador;
    }


    /**
     * Gets the apoderado value for this DefaultDatosPagoIn.
     * 
     * @return apoderado
     */
    public boolean isApoderado() {
        return apoderado;
    }


    /**
     * Sets the apoderado value for this DefaultDatosPagoIn.
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
        if (!(obj instanceof DefaultDatosPagoIn)) return false;
        DefaultDatosPagoIn other = (DefaultDatosPagoIn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.CCC==null && other.getCCC()==null) || 
             (this.CCC!=null &&
              this.CCC.equals(other.getCCC()))) &&
            ((this.apellido1==null && other.getApellido1()==null) || 
             (this.apellido1!=null &&
              this.apellido1.equals(other.getApellido1()))) &&
            ((this.apellido2==null && other.getApellido2()==null) || 
             (this.apellido2!=null &&
              this.apellido2.equals(other.getApellido2()))) &&
            ((this.certificado1==null && other.getCertificado1()==null) || 
             (this.certificado1!=null &&
              this.certificado1.equals(other.getCertificado1()))) &&
            ((this.certificado2==null && other.getCertificado2()==null) || 
             (this.certificado2!=null &&
              this.certificado2.equals(other.getCertificado2()))) &&
            ((this.codigoBanco==null && other.getCodigoBanco()==null) || 
             (this.codigoBanco!=null &&
              this.codigoBanco.equals(other.getCodigoBanco()))) &&
            ((this.codigoTasa==null && other.getCodigoTasa()==null) || 
             (this.codigoTasa!=null &&
              this.codigoTasa.equals(other.getCodigoTasa()))) &&
            ((this.documentoObligado==null && other.getDocumentoObligado()==null) || 
             (this.documentoObligado!=null &&
              this.documentoObligado.equals(other.getDocumentoObligado()))) &&
            ((this.documentoPagador==null && other.getDocumentoPagador()==null) || 
             (this.documentoPagador!=null &&
              this.documentoPagador.equals(other.getDocumentoPagador()))) &&
            ((this.fechaCaducidadTarjeta==null && other.getFechaCaducidadTarjeta()==null) || 
             (this.fechaCaducidadTarjeta!=null &&
              this.fechaCaducidadTarjeta.equals(other.getFechaCaducidadTarjeta()))) &&
            ((this.firma1==null && other.getFirma1()==null) || 
             (this.firma1!=null &&
              this.firma1.equals(other.getFirma1()))) &&
            ((this.firma2==null && other.getFirma2()==null) || 
             (this.firma2!=null &&
              this.firma2.equals(other.getFirma2()))) &&
            ((this.hashOrigenFirma==null && other.getHashOrigenFirma()==null) || 
             (this.hashOrigenFirma!=null &&
              this.hashOrigenFirma.equals(other.getHashOrigenFirma()))) &&
            this.importe == other.getImporte() &&
            ((this.justificante==null && other.getJustificante()==null) || 
             (this.justificante!=null &&
              this.justificante.equals(other.getJustificante()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.numeroTarjeta==null && other.getNumeroTarjeta()==null) || 
             (this.numeroTarjeta!=null &&
              this.numeroTarjeta.equals(other.getNumeroTarjeta()))) &&
            ((this.origenFirma==null && other.getOrigenFirma()==null) || 
             (this.origenFirma!=null &&
              this.origenFirma.equals(other.getOrigenFirma()))) &&
            ((this.tipoCargo==null && other.getTipoCargo()==null) || 
             (this.tipoCargo!=null &&
              this.tipoCargo.equals(other.getTipoCargo()))) &&
            ((this.tipoDocumentoObligado==null && other.getTipoDocumentoObligado()==null) || 
             (this.tipoDocumentoObligado!=null &&
              this.tipoDocumentoObligado.equals(other.getTipoDocumentoObligado()))) &&
            ((this.tipoDocumentoPagador==null && other.getTipoDocumentoPagador()==null) || 
             (this.tipoDocumentoPagador!=null &&
              this.tipoDocumentoPagador.equals(other.getTipoDocumentoPagador()))) &&
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
        if (getCCC() != null) {
            _hashCode += getCCC().hashCode();
        }
        if (getApellido1() != null) {
            _hashCode += getApellido1().hashCode();
        }
        if (getApellido2() != null) {
            _hashCode += getApellido2().hashCode();
        }
        if (getCertificado1() != null) {
            _hashCode += getCertificado1().hashCode();
        }
        if (getCertificado2() != null) {
            _hashCode += getCertificado2().hashCode();
        }
        if (getCodigoBanco() != null) {
            _hashCode += getCodigoBanco().hashCode();
        }
        if (getCodigoTasa() != null) {
            _hashCode += getCodigoTasa().hashCode();
        }
        if (getDocumentoObligado() != null) {
            _hashCode += getDocumentoObligado().hashCode();
        }
        if (getDocumentoPagador() != null) {
            _hashCode += getDocumentoPagador().hashCode();
        }
        if (getFechaCaducidadTarjeta() != null) {
            _hashCode += getFechaCaducidadTarjeta().hashCode();
        }
        if (getFirma1() != null) {
            _hashCode += getFirma1().hashCode();
        }
        if (getFirma2() != null) {
            _hashCode += getFirma2().hashCode();
        }
        if (getHashOrigenFirma() != null) {
            _hashCode += getHashOrigenFirma().hashCode();
        }
        _hashCode += new Double(getImporte()).hashCode();
        if (getJustificante() != null) {
            _hashCode += getJustificante().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getNumeroTarjeta() != null) {
            _hashCode += getNumeroTarjeta().hashCode();
        }
        if (getOrigenFirma() != null) {
            _hashCode += getOrigenFirma().hashCode();
        }
        if (getTipoCargo() != null) {
            _hashCode += getTipoCargo().hashCode();
        }
        if (getTipoDocumentoObligado() != null) {
            _hashCode += getTipoDocumentoObligado().hashCode();
        }
        if (getTipoDocumentoPagador() != null) {
            _hashCode += getTipoDocumentoPagador().hashCode();
        }
        _hashCode += (isApoderado() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DefaultDatosPagoIn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoIn"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("CCC");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "CCC"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apellido1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "apellido1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apellido2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "apellido2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("certificado1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "certificado1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("certificado2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "certificado2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoBanco");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "codigoBanco"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codigoTasa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "codigoTasa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentoObligado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "documentoObligado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentoPagador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "documentoPagador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fechaCaducidadTarjeta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "fechaCaducidadTarjeta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firma1");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "firma1"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("firma2");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "firma2"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("hashOrigenFirma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "hashOrigenFirma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importe");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "importe"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("justificante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "justificante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroTarjeta");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "numeroTarjeta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("origenFirma");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "origenFirma"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoCargo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "tipoCargo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "TiposCargo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumentoObligado");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "tipoDocumentoObligado"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "TiposDocumento"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumentoPagador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "tipoDocumentoPagador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "TiposDocumento"));
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
