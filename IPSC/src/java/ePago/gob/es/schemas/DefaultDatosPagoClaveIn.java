/**
 * DefaultDatosPagoClaveIn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package ePago.gob.es.schemas;

/**
 * El Class DefaultDatosPagoClaveIn.
 */
public class DefaultDatosPagoClaveIn  implements java.io.Serializable {
    
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

    /** el apellido 1 pagador. */
    private java.lang.String apellido1Pagador;

    /** el apellido 2 pagador. */
    private java.lang.String apellido2Pagador;

    /** el nombre pagador. */
    private java.lang.String nombrePagador;

    /** el documento primer representante. */
    private java.lang.String documentoPrimerRepresentante;

    /** el apellido 1 primer representante. */
    private java.lang.String apellido1PrimerRepresentante;

    /** el apellido 2 primer representante. */
    private java.lang.String apellido2PrimerRepresentante;

    /** el nombre primer representante. */
    private java.lang.String nombrePrimerRepresentante;

    /** el documento segundo representante. */
    private java.lang.String documentoSegundoRepresentante;

    /** el apellido 1 segundo representante. */
    private java.lang.String apellido1SegundoRepresentante;

    /** el apellido 2 segundo representante. */
    private java.lang.String apellido2SegundoRepresentante;

    /** el nombre segundo representante. */
    private java.lang.String nombreSegundoRepresentante;

    /** el fecha caducidad tarjeta. */
    private java.util.Calendar fechaCaducidadTarjeta;

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

    /** el tipo cargo. */
    private ePago.gob.es.schemas.TiposCargo tipoCargo;

    /** el tipo documento obligado. */
    private ePago.gob.es.schemas.TiposDocumento tipoDocumentoObligado;

    /** el tipo documento pagador. */
    private ePago.gob.es.schemas.TiposDocumento tipoDocumentoPagador;

    /** el apoderado. */
    private boolean apoderado;

    /** el funcionario. */
    private ePago.gob.es.schemas.Funcionario funcionario;

    /** el tipo autenticacion. */
    private java.lang.String tipoAutenticacion;

    /**
     * Crea una nueva default datos pago clave in.
     */
    public DefaultDatosPagoClaveIn() {
    }

    /**
     * Crea una nueva default datos pago clave in.
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
     * @param apellido1Pagador el apellido 1 pagador
     * @param apellido2Pagador el apellido 2 pagador
     * @param nombrePagador el nombre pagador
     * @param documentoPrimerRepresentante el documento primer representante
     * @param apellido1PrimerRepresentante el apellido 1 primer representante
     * @param apellido2PrimerRepresentante el apellido 2 primer representante
     * @param nombrePrimerRepresentante el nombre primer representante
     * @param documentoSegundoRepresentante el documento segundo representante
     * @param apellido1SegundoRepresentante el apellido 1 segundo representante
     * @param apellido2SegundoRepresentante el apellido 2 segundo representante
     * @param nombreSegundoRepresentante el nombre segundo representante
     * @param fechaCaducidadTarjeta el fecha caducidad tarjeta
     * @param hashOrigenFirma el hash origen firma
     * @param importe el importe
     * @param justificante el justificante
     * @param nombre el nombre
     * @param numeroTarjeta el numero tarjeta
     * @param tipoCargo el tipo cargo
     * @param tipoDocumentoObligado el tipo documento obligado
     * @param tipoDocumentoPagador el tipo documento pagador
     * @param apoderado el apoderado
     * @param funcionario el funcionario
     * @param tipoAutenticacion el tipo autenticacion
     */
    public DefaultDatosPagoClaveIn(
           java.lang.String CCC,
           java.lang.String apellido1,
           java.lang.String apellido2,
           java.lang.String certificado1,
           java.lang.String certificado2,
           java.lang.String codigoBanco,
           java.lang.String codigoTasa,
           java.lang.String documentoObligado,
           java.lang.String documentoPagador,
           java.lang.String apellido1Pagador,
           java.lang.String apellido2Pagador,
           java.lang.String nombrePagador,
           java.lang.String documentoPrimerRepresentante,
           java.lang.String apellido1PrimerRepresentante,
           java.lang.String apellido2PrimerRepresentante,
           java.lang.String nombrePrimerRepresentante,
           java.lang.String documentoSegundoRepresentante,
           java.lang.String apellido1SegundoRepresentante,
           java.lang.String apellido2SegundoRepresentante,
           java.lang.String nombreSegundoRepresentante,
           java.util.Calendar fechaCaducidadTarjeta,
           java.lang.String hashOrigenFirma,
           double importe,
           java.lang.String justificante,
           java.lang.String nombre,
           java.lang.String numeroTarjeta,
           ePago.gob.es.schemas.TiposCargo tipoCargo,
           ePago.gob.es.schemas.TiposDocumento tipoDocumentoObligado,
           ePago.gob.es.schemas.TiposDocumento tipoDocumentoPagador,
           boolean apoderado,
           ePago.gob.es.schemas.Funcionario funcionario,
           java.lang.String tipoAutenticacion) {
           this.CCC = CCC;
           this.apellido1 = apellido1;
           this.apellido2 = apellido2;
           this.certificado1 = certificado1;
           this.certificado2 = certificado2;
           this.codigoBanco = codigoBanco;
           this.codigoTasa = codigoTasa;
           this.documentoObligado = documentoObligado;
           this.documentoPagador = documentoPagador;
           this.apellido1Pagador = apellido1Pagador;
           this.apellido2Pagador = apellido2Pagador;
           this.nombrePagador = nombrePagador;
           this.documentoPrimerRepresentante = documentoPrimerRepresentante;
           this.apellido1PrimerRepresentante = apellido1PrimerRepresentante;
           this.apellido2PrimerRepresentante = apellido2PrimerRepresentante;
           this.nombrePrimerRepresentante = nombrePrimerRepresentante;
           this.documentoSegundoRepresentante = documentoSegundoRepresentante;
           this.apellido1SegundoRepresentante = apellido1SegundoRepresentante;
           this.apellido2SegundoRepresentante = apellido2SegundoRepresentante;
           this.nombreSegundoRepresentante = nombreSegundoRepresentante;
           this.fechaCaducidadTarjeta = fechaCaducidadTarjeta;
           this.hashOrigenFirma = hashOrigenFirma;
           this.importe = importe;
           this.justificante = justificante;
           this.nombre = nombre;
           this.numeroTarjeta = numeroTarjeta;
           this.tipoCargo = tipoCargo;
           this.tipoDocumentoObligado = tipoDocumentoObligado;
           this.tipoDocumentoPagador = tipoDocumentoPagador;
           this.apoderado = apoderado;
           this.funcionario = funcionario;
           this.tipoAutenticacion = tipoAutenticacion;
    }


    /**
     * Gets the CCC value for this DefaultDatosPagoClaveIn.
     * 
     * @return CCC
     */
    public java.lang.String getCCC() {
        return CCC;
    }


    /**
     * Sets the CCC value for this DefaultDatosPagoClaveIn.
     *
     * @param CCC el nuevo ccc
     */
    public void setCCC(java.lang.String CCC) {
        this.CCC = CCC;
    }


    /**
     * Gets the apellido1 value for this DefaultDatosPagoClaveIn.
     * 
     * @return apellido1
     */
    public java.lang.String getApellido1() {
        return apellido1;
    }


    /**
     * Sets the apellido1 value for this DefaultDatosPagoClaveIn.
     *
     * @param apellido1 el nuevo apellido 1
     */
    public void setApellido1(java.lang.String apellido1) {
        this.apellido1 = apellido1;
    }


    /**
     * Gets the apellido2 value for this DefaultDatosPagoClaveIn.
     * 
     * @return apellido2
     */
    public java.lang.String getApellido2() {
        return apellido2;
    }


    /**
     * Sets the apellido2 value for this DefaultDatosPagoClaveIn.
     *
     * @param apellido2 el nuevo apellido 2
     */
    public void setApellido2(java.lang.String apellido2) {
        this.apellido2 = apellido2;
    }


    /**
     * Gets the certificado1 value for this DefaultDatosPagoClaveIn.
     * 
     * @return certificado1
     */
    public java.lang.String getCertificado1() {
        return certificado1;
    }


    /**
     * Sets the certificado1 value for this DefaultDatosPagoClaveIn.
     *
     * @param certificado1 el nuevo certificado 1
     */
    public void setCertificado1(java.lang.String certificado1) {
        this.certificado1 = certificado1;
    }


    /**
     * Gets the certificado2 value for this DefaultDatosPagoClaveIn.
     * 
     * @return certificado2
     */
    public java.lang.String getCertificado2() {
        return certificado2;
    }


    /**
     * Sets the certificado2 value for this DefaultDatosPagoClaveIn.
     *
     * @param certificado2 el nuevo certificado 2
     */
    public void setCertificado2(java.lang.String certificado2) {
        this.certificado2 = certificado2;
    }


    /**
     * Gets the codigoBanco value for this DefaultDatosPagoClaveIn.
     * 
     * @return codigoBanco
     */
    public java.lang.String getCodigoBanco() {
        return codigoBanco;
    }


    /**
     * Sets the codigoBanco value for this DefaultDatosPagoClaveIn.
     *
     * @param codigoBanco el nuevo codigo banco
     */
    public void setCodigoBanco(java.lang.String codigoBanco) {
        this.codigoBanco = codigoBanco;
    }


    /**
     * Gets the codigoTasa value for this DefaultDatosPagoClaveIn.
     * 
     * @return codigoTasa
     */
    public java.lang.String getCodigoTasa() {
        return codigoTasa;
    }


    /**
     * Sets the codigoTasa value for this DefaultDatosPagoClaveIn.
     *
     * @param codigoTasa el nuevo codigo tasa
     */
    public void setCodigoTasa(java.lang.String codigoTasa) {
        this.codigoTasa = codigoTasa;
    }


    /**
     * Gets the documentoObligado value for this DefaultDatosPagoClaveIn.
     * 
     * @return documentoObligado
     */
    public java.lang.String getDocumentoObligado() {
        return documentoObligado;
    }


    /**
     * Sets the documentoObligado value for this DefaultDatosPagoClaveIn.
     *
     * @param documentoObligado el nuevo documento obligado
     */
    public void setDocumentoObligado(java.lang.String documentoObligado) {
        this.documentoObligado = documentoObligado;
    }


    /**
     * Gets the documentoPagador value for this DefaultDatosPagoClaveIn.
     * 
     * @return documentoPagador
     */
    public java.lang.String getDocumentoPagador() {
        return documentoPagador;
    }


    /**
     * Sets the documentoPagador value for this DefaultDatosPagoClaveIn.
     *
     * @param documentoPagador el nuevo documento pagador
     */
    public void setDocumentoPagador(java.lang.String documentoPagador) {
        this.documentoPagador = documentoPagador;
    }


    /**
     * Gets the apellido1Pagador value for this DefaultDatosPagoClaveIn.
     * 
     * @return apellido1Pagador
     */
    public java.lang.String getApellido1Pagador() {
        return apellido1Pagador;
    }


    /**
     * Sets the apellido1Pagador value for this DefaultDatosPagoClaveIn.
     *
     * @param apellido1Pagador el nuevo apellido 1 pagador
     */
    public void setApellido1Pagador(java.lang.String apellido1Pagador) {
        this.apellido1Pagador = apellido1Pagador;
    }


    /**
     * Gets the apellido2Pagador value for this DefaultDatosPagoClaveIn.
     * 
     * @return apellido2Pagador
     */
    public java.lang.String getApellido2Pagador() {
        return apellido2Pagador;
    }


    /**
     * Sets the apellido2Pagador value for this DefaultDatosPagoClaveIn.
     *
     * @param apellido2Pagador el nuevo apellido 2 pagador
     */
    public void setApellido2Pagador(java.lang.String apellido2Pagador) {
        this.apellido2Pagador = apellido2Pagador;
    }


    /**
     * Gets the nombrePagador value for this DefaultDatosPagoClaveIn.
     * 
     * @return nombrePagador
     */
    public java.lang.String getNombrePagador() {
        return nombrePagador;
    }


    /**
     * Sets the nombrePagador value for this DefaultDatosPagoClaveIn.
     *
     * @param nombrePagador el nuevo nombre pagador
     */
    public void setNombrePagador(java.lang.String nombrePagador) {
        this.nombrePagador = nombrePagador;
    }


    /**
     * Gets the documentoPrimerRepresentante value for this DefaultDatosPagoClaveIn.
     * 
     * @return documentoPrimerRepresentante
     */
    public java.lang.String getDocumentoPrimerRepresentante() {
        return documentoPrimerRepresentante;
    }


    /**
     * Sets the documentoPrimerRepresentante value for this DefaultDatosPagoClaveIn.
     *
     * @param documentoPrimerRepresentante el nuevo documento primer representante
     */
    public void setDocumentoPrimerRepresentante(java.lang.String documentoPrimerRepresentante) {
        this.documentoPrimerRepresentante = documentoPrimerRepresentante;
    }


    /**
     * Gets the apellido1PrimerRepresentante value for this DefaultDatosPagoClaveIn.
     * 
     * @return apellido1PrimerRepresentante
     */
    public java.lang.String getApellido1PrimerRepresentante() {
        return apellido1PrimerRepresentante;
    }


    /**
     * Sets the apellido1PrimerRepresentante value for this DefaultDatosPagoClaveIn.
     *
     * @param apellido1PrimerRepresentante el nuevo apellido 1 primer representante
     */
    public void setApellido1PrimerRepresentante(java.lang.String apellido1PrimerRepresentante) {
        this.apellido1PrimerRepresentante = apellido1PrimerRepresentante;
    }


    /**
     * Gets the apellido2PrimerRepresentante value for this DefaultDatosPagoClaveIn.
     * 
     * @return apellido2PrimerRepresentante
     */
    public java.lang.String getApellido2PrimerRepresentante() {
        return apellido2PrimerRepresentante;
    }


    /**
     * Sets the apellido2PrimerRepresentante value for this DefaultDatosPagoClaveIn.
     *
     * @param apellido2PrimerRepresentante el nuevo apellido 2 primer representante
     */
    public void setApellido2PrimerRepresentante(java.lang.String apellido2PrimerRepresentante) {
        this.apellido2PrimerRepresentante = apellido2PrimerRepresentante;
    }


    /**
     * Gets the nombrePrimerRepresentante value for this DefaultDatosPagoClaveIn.
     * 
     * @return nombrePrimerRepresentante
     */
    public java.lang.String getNombrePrimerRepresentante() {
        return nombrePrimerRepresentante;
    }


    /**
     * Sets the nombrePrimerRepresentante value for this DefaultDatosPagoClaveIn.
     *
     * @param nombrePrimerRepresentante el nuevo nombre primer representante
     */
    public void setNombrePrimerRepresentante(java.lang.String nombrePrimerRepresentante) {
        this.nombrePrimerRepresentante = nombrePrimerRepresentante;
    }


    /**
     * Gets the documentoSegundoRepresentante value for this DefaultDatosPagoClaveIn.
     * 
     * @return documentoSegundoRepresentante
     */
    public java.lang.String getDocumentoSegundoRepresentante() {
        return documentoSegundoRepresentante;
    }


    /**
     * Sets the documentoSegundoRepresentante value for this DefaultDatosPagoClaveIn.
     *
     * @param documentoSegundoRepresentante el nuevo documento segundo representante
     */
    public void setDocumentoSegundoRepresentante(java.lang.String documentoSegundoRepresentante) {
        this.documentoSegundoRepresentante = documentoSegundoRepresentante;
    }


    /**
     * Gets the apellido1SegundoRepresentante value for this DefaultDatosPagoClaveIn.
     * 
     * @return apellido1SegundoRepresentante
     */
    public java.lang.String getApellido1SegundoRepresentante() {
        return apellido1SegundoRepresentante;
    }


    /**
     * Sets the apellido1SegundoRepresentante value for this DefaultDatosPagoClaveIn.
     *
     * @param apellido1SegundoRepresentante el nuevo apellido 1 segundo representante
     */
    public void setApellido1SegundoRepresentante(java.lang.String apellido1SegundoRepresentante) {
        this.apellido1SegundoRepresentante = apellido1SegundoRepresentante;
    }


    /**
     * Gets the apellido2SegundoRepresentante value for this DefaultDatosPagoClaveIn.
     * 
     * @return apellido2SegundoRepresentante
     */
    public java.lang.String getApellido2SegundoRepresentante() {
        return apellido2SegundoRepresentante;
    }


    /**
     * Sets the apellido2SegundoRepresentante value for this DefaultDatosPagoClaveIn.
     *
     * @param apellido2SegundoRepresentante el nuevo apellido 2 segundo representante
     */
    public void setApellido2SegundoRepresentante(java.lang.String apellido2SegundoRepresentante) {
        this.apellido2SegundoRepresentante = apellido2SegundoRepresentante;
    }


    /**
     * Gets the nombreSegundoRepresentante value for this DefaultDatosPagoClaveIn.
     * 
     * @return nombreSegundoRepresentante
     */
    public java.lang.String getNombreSegundoRepresentante() {
        return nombreSegundoRepresentante;
    }


    /**
     * Sets the nombreSegundoRepresentante value for this DefaultDatosPagoClaveIn.
     *
     * @param nombreSegundoRepresentante el nuevo nombre segundo representante
     */
    public void setNombreSegundoRepresentante(java.lang.String nombreSegundoRepresentante) {
        this.nombreSegundoRepresentante = nombreSegundoRepresentante;
    }


    /**
     * Gets the fechaCaducidadTarjeta value for this DefaultDatosPagoClaveIn.
     * 
     * @return fechaCaducidadTarjeta
     */
    public java.util.Calendar getFechaCaducidadTarjeta() {
        return fechaCaducidadTarjeta;
    }


    /**
     * Sets the fechaCaducidadTarjeta value for this DefaultDatosPagoClaveIn.
     *
     * @param fechaCaducidadTarjeta el nuevo fecha caducidad tarjeta
     */
    public void setFechaCaducidadTarjeta(java.util.Calendar fechaCaducidadTarjeta) {
        this.fechaCaducidadTarjeta = fechaCaducidadTarjeta;
    }


    /**
     * Gets the hashOrigenFirma value for this DefaultDatosPagoClaveIn.
     * 
     * @return hashOrigenFirma
     */
    public java.lang.String getHashOrigenFirma() {
        return hashOrigenFirma;
    }


    /**
     * Sets the hashOrigenFirma value for this DefaultDatosPagoClaveIn.
     *
     * @param hashOrigenFirma el nuevo hash origen firma
     */
    public void setHashOrigenFirma(java.lang.String hashOrigenFirma) {
        this.hashOrigenFirma = hashOrigenFirma;
    }


    /**
     * Gets the importe value for this DefaultDatosPagoClaveIn.
     * 
     * @return importe
     */
    public double getImporte() {
        return importe;
    }


    /**
     * Sets the importe value for this DefaultDatosPagoClaveIn.
     *
     * @param importe el nuevo importe
     */
    public void setImporte(double importe) {
        this.importe = importe;
    }


    /**
     * Gets the justificante value for this DefaultDatosPagoClaveIn.
     * 
     * @return justificante
     */
    public java.lang.String getJustificante() {
        return justificante;
    }


    /**
     * Sets the justificante value for this DefaultDatosPagoClaveIn.
     *
     * @param justificante el nuevo justificante
     */
    public void setJustificante(java.lang.String justificante) {
        this.justificante = justificante;
    }


    /**
     * Gets the nombre value for this DefaultDatosPagoClaveIn.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this DefaultDatosPagoClaveIn.
     *
     * @param nombre el nuevo nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the numeroTarjeta value for this DefaultDatosPagoClaveIn.
     * 
     * @return numeroTarjeta
     */
    public java.lang.String getNumeroTarjeta() {
        return numeroTarjeta;
    }


    /**
     * Sets the numeroTarjeta value for this DefaultDatosPagoClaveIn.
     *
     * @param numeroTarjeta el nuevo numero tarjeta
     */
    public void setNumeroTarjeta(java.lang.String numeroTarjeta) {
        this.numeroTarjeta = numeroTarjeta;
    }


    /**
     * Gets the tipoCargo value for this DefaultDatosPagoClaveIn.
     * 
     * @return tipoCargo
     */
    public ePago.gob.es.schemas.TiposCargo getTipoCargo() {
        return tipoCargo;
    }


    /**
     * Sets the tipoCargo value for this DefaultDatosPagoClaveIn.
     *
     * @param tipoCargo el nuevo tipo cargo
     */
    public void setTipoCargo(ePago.gob.es.schemas.TiposCargo tipoCargo) {
        this.tipoCargo = tipoCargo;
    }


    /**
     * Gets the tipoDocumentoObligado value for this DefaultDatosPagoClaveIn.
     * 
     * @return tipoDocumentoObligado
     */
    public ePago.gob.es.schemas.TiposDocumento getTipoDocumentoObligado() {
        return tipoDocumentoObligado;
    }


    /**
     * Sets the tipoDocumentoObligado value for this DefaultDatosPagoClaveIn.
     *
     * @param tipoDocumentoObligado el nuevo tipo documento obligado
     */
    public void setTipoDocumentoObligado(ePago.gob.es.schemas.TiposDocumento tipoDocumentoObligado) {
        this.tipoDocumentoObligado = tipoDocumentoObligado;
    }


    /**
     * Gets the tipoDocumentoPagador value for this DefaultDatosPagoClaveIn.
     * 
     * @return tipoDocumentoPagador
     */
    public ePago.gob.es.schemas.TiposDocumento getTipoDocumentoPagador() {
        return tipoDocumentoPagador;
    }


    /**
     * Sets the tipoDocumentoPagador value for this DefaultDatosPagoClaveIn.
     *
     * @param tipoDocumentoPagador el nuevo tipo documento pagador
     */
    public void setTipoDocumentoPagador(ePago.gob.es.schemas.TiposDocumento tipoDocumentoPagador) {
        this.tipoDocumentoPagador = tipoDocumentoPagador;
    }


    /**
     * Gets the apoderado value for this DefaultDatosPagoClaveIn.
     * 
     * @return apoderado
     */
    public boolean isApoderado() {
        return apoderado;
    }


    /**
     * Sets the apoderado value for this DefaultDatosPagoClaveIn.
     *
     * @param apoderado el nuevo apoderado
     */
    public void setApoderado(boolean apoderado) {
        this.apoderado = apoderado;
    }


    /**
     * Gets the funcionario value for this DefaultDatosPagoClaveIn.
     * 
     * @return funcionario
     */
    public ePago.gob.es.schemas.Funcionario getFuncionario() {
        return funcionario;
    }


    /**
     * Sets the funcionario value for this DefaultDatosPagoClaveIn.
     *
     * @param funcionario el nuevo funcionario
     */
    public void setFuncionario(ePago.gob.es.schemas.Funcionario funcionario) {
        this.funcionario = funcionario;
    }


    /**
     * Gets the tipoAutenticacion value for this DefaultDatosPagoClaveIn.
     * 
     * @return tipoAutenticacion
     */
    public java.lang.String getTipoAutenticacion() {
        return tipoAutenticacion;
    }


    /**
     * Sets the tipoAutenticacion value for this DefaultDatosPagoClaveIn.
     *
     * @param tipoAutenticacion el nuevo tipo autenticacion
     */
    public void setTipoAutenticacion(java.lang.String tipoAutenticacion) {
        this.tipoAutenticacion = tipoAutenticacion;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DefaultDatosPagoClaveIn)) return false;
        DefaultDatosPagoClaveIn other = (DefaultDatosPagoClaveIn) obj;
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
            ((this.apellido1Pagador==null && other.getApellido1Pagador()==null) || 
             (this.apellido1Pagador!=null &&
              this.apellido1Pagador.equals(other.getApellido1Pagador()))) &&
            ((this.apellido2Pagador==null && other.getApellido2Pagador()==null) || 
             (this.apellido2Pagador!=null &&
              this.apellido2Pagador.equals(other.getApellido2Pagador()))) &&
            ((this.nombrePagador==null && other.getNombrePagador()==null) || 
             (this.nombrePagador!=null &&
              this.nombrePagador.equals(other.getNombrePagador()))) &&
            ((this.documentoPrimerRepresentante==null && other.getDocumentoPrimerRepresentante()==null) || 
             (this.documentoPrimerRepresentante!=null &&
              this.documentoPrimerRepresentante.equals(other.getDocumentoPrimerRepresentante()))) &&
            ((this.apellido1PrimerRepresentante==null && other.getApellido1PrimerRepresentante()==null) || 
             (this.apellido1PrimerRepresentante!=null &&
              this.apellido1PrimerRepresentante.equals(other.getApellido1PrimerRepresentante()))) &&
            ((this.apellido2PrimerRepresentante==null && other.getApellido2PrimerRepresentante()==null) || 
             (this.apellido2PrimerRepresentante!=null &&
              this.apellido2PrimerRepresentante.equals(other.getApellido2PrimerRepresentante()))) &&
            ((this.nombrePrimerRepresentante==null && other.getNombrePrimerRepresentante()==null) || 
             (this.nombrePrimerRepresentante!=null &&
              this.nombrePrimerRepresentante.equals(other.getNombrePrimerRepresentante()))) &&
            ((this.documentoSegundoRepresentante==null && other.getDocumentoSegundoRepresentante()==null) || 
             (this.documentoSegundoRepresentante!=null &&
              this.documentoSegundoRepresentante.equals(other.getDocumentoSegundoRepresentante()))) &&
            ((this.apellido1SegundoRepresentante==null && other.getApellido1SegundoRepresentante()==null) || 
             (this.apellido1SegundoRepresentante!=null &&
              this.apellido1SegundoRepresentante.equals(other.getApellido1SegundoRepresentante()))) &&
            ((this.apellido2SegundoRepresentante==null && other.getApellido2SegundoRepresentante()==null) || 
             (this.apellido2SegundoRepresentante!=null &&
              this.apellido2SegundoRepresentante.equals(other.getApellido2SegundoRepresentante()))) &&
            ((this.nombreSegundoRepresentante==null && other.getNombreSegundoRepresentante()==null) || 
             (this.nombreSegundoRepresentante!=null &&
              this.nombreSegundoRepresentante.equals(other.getNombreSegundoRepresentante()))) &&
            ((this.fechaCaducidadTarjeta==null && other.getFechaCaducidadTarjeta()==null) || 
             (this.fechaCaducidadTarjeta!=null &&
              this.fechaCaducidadTarjeta.equals(other.getFechaCaducidadTarjeta()))) &&
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
            ((this.tipoCargo==null && other.getTipoCargo()==null) || 
             (this.tipoCargo!=null &&
              this.tipoCargo.equals(other.getTipoCargo()))) &&
            ((this.tipoDocumentoObligado==null && other.getTipoDocumentoObligado()==null) || 
             (this.tipoDocumentoObligado!=null &&
              this.tipoDocumentoObligado.equals(other.getTipoDocumentoObligado()))) &&
            ((this.tipoDocumentoPagador==null && other.getTipoDocumentoPagador()==null) || 
             (this.tipoDocumentoPagador!=null &&
              this.tipoDocumentoPagador.equals(other.getTipoDocumentoPagador()))) &&
            this.apoderado == other.isApoderado() &&
            ((this.funcionario==null && other.getFuncionario()==null) || 
             (this.funcionario!=null &&
              this.funcionario.equals(other.getFuncionario()))) &&
            ((this.tipoAutenticacion==null && other.getTipoAutenticacion()==null) || 
             (this.tipoAutenticacion!=null &&
              this.tipoAutenticacion.equals(other.getTipoAutenticacion())));
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
        if (getApellido1Pagador() != null) {
            _hashCode += getApellido1Pagador().hashCode();
        }
        if (getApellido2Pagador() != null) {
            _hashCode += getApellido2Pagador().hashCode();
        }
        if (getNombrePagador() != null) {
            _hashCode += getNombrePagador().hashCode();
        }
        if (getDocumentoPrimerRepresentante() != null) {
            _hashCode += getDocumentoPrimerRepresentante().hashCode();
        }
        if (getApellido1PrimerRepresentante() != null) {
            _hashCode += getApellido1PrimerRepresentante().hashCode();
        }
        if (getApellido2PrimerRepresentante() != null) {
            _hashCode += getApellido2PrimerRepresentante().hashCode();
        }
        if (getNombrePrimerRepresentante() != null) {
            _hashCode += getNombrePrimerRepresentante().hashCode();
        }
        if (getDocumentoSegundoRepresentante() != null) {
            _hashCode += getDocumentoSegundoRepresentante().hashCode();
        }
        if (getApellido1SegundoRepresentante() != null) {
            _hashCode += getApellido1SegundoRepresentante().hashCode();
        }
        if (getApellido2SegundoRepresentante() != null) {
            _hashCode += getApellido2SegundoRepresentante().hashCode();
        }
        if (getNombreSegundoRepresentante() != null) {
            _hashCode += getNombreSegundoRepresentante().hashCode();
        }
        if (getFechaCaducidadTarjeta() != null) {
            _hashCode += getFechaCaducidadTarjeta().hashCode();
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
        if (getFuncionario() != null) {
            _hashCode += getFuncionario().hashCode();
        }
        if (getTipoAutenticacion() != null) {
            _hashCode += getTipoAutenticacion().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DefaultDatosPagoClaveIn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "DefaultDatosPagoClaveIn"));
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
        elemField.setFieldName("apellido1Pagador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "apellido1Pagador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apellido2Pagador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "apellido2Pagador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombrePagador");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "nombrePagador"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentoPrimerRepresentante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "documentoPrimerRepresentante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apellido1PrimerRepresentante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "apellido1PrimerRepresentante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apellido2PrimerRepresentante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "apellido2PrimerRepresentante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombrePrimerRepresentante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "nombrePrimerRepresentante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentoSegundoRepresentante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "documentoSegundoRepresentante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apellido1SegundoRepresentante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "apellido1SegundoRepresentante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("apellido2SegundoRepresentante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "apellido2SegundoRepresentante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombreSegundoRepresentante");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "nombreSegundoRepresentante"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("funcionario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "funcionario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "Funcionario"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoAutenticacion");
        elemField.setXmlName(new javax.xml.namespace.QName("http://es.gob.ePago/schemas", "tipoAutenticacion"));
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
