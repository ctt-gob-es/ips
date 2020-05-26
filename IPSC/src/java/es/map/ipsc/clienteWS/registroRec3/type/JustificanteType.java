/**
 * JustificanteType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package es.map.ipsc.clienteWS.registroRec3.type;

/**
 * El Class JustificanteType.
 */
public class JustificanteType  implements java.io.Serializable {
    
    /** el nm registro. */
    private java.lang.String nmRegistro;

    /** el fe registro. */
    private java.lang.String feRegistro;

    /** el bl time stamp. */
    private java.lang.String blTimeStamp;

    /** el id intercambio. */
    private java.lang.String idIntercambio;

    /** el cd oficina origen. */
    private java.lang.String cdOficinaOrigen;

    /** el ds oficina origen. */
    private java.lang.String dsOficinaOrigen;

    /** el cd tipo registro. */
    private java.lang.String cdTipoRegistro;

    /** el cd oficina destino. */
    private java.lang.String cdOficinaDestino;

    /** el ds oficina destino. */
    private java.lang.String dsOficinaDestino;

    /** el cd unidad destino. */
    private java.lang.String cdUnidadDestino;

    /** el ds unidad destino. */
    private java.lang.String dsUnidadDestino;

    /** el cd unidad origen. */
    private java.lang.String cdUnidadOrigen;

    /** el ds unidad origen. */
    private java.lang.String dsUnidadOrigen;

    /** el interesados. */
    private es.map.ipsc.clienteWS.registroRec3.type.InteresadoJustificanteType[] interesados;

    /** el documentos. */
    private es.map.ipsc.clienteWS.registroRec3.type.DocumentoJustificanteType[] documentos;

    /** el tl resumen. */
    private java.lang.String tlResumen;

    /** el cd asunto. */
    private java.lang.String cdAsunto;

    /** el ds asunto. */
    private java.lang.String dsAsunto;

    /** el tl referencia externa. */
    private java.lang.String tlReferenciaExterna;

    /** el tl numero expediente. */
    private java.lang.String tlNumeroExpediente;

    /** el cd tipo transporte. */
    private java.lang.String cdTipoTransporte;

    /** el tl numero transporte. */
    private java.lang.String tlNumeroTransporte;

    /** el tl nombre usuario. */
    private java.lang.String tlNombreUsuario;

    /** el tl contacto usuario. */
    private java.lang.String tlContactoUsuario;

    /** el cd documentacion fisica soportes. */
    private java.lang.String cdDocumentacionFisicaSoportes;

    /** el tl expone. */
    private java.lang.String tlExpone;

    /** el tl solicita. */
    private java.lang.String tlSolicita;

    /** el tl aplicacion. */
    private java.lang.String tlAplicacion;

    /** el tl observaciones. */
    private java.lang.String tlObservaciones;

    /** el bl justificante. */
    private java.lang.String blJustificante;

    /** el respuesta. */
    private es.map.ipsc.clienteWS.registroRec3.type.RespuestaType respuesta;

    /**
     * Crea una nueva justificante type.
     */
    public JustificanteType() {
    }

    /**
     * Crea una nueva justificante type.
     *
     * @param nmRegistro el nm registro
     * @param feRegistro el fe registro
     * @param blTimeStamp el bl time stamp
     * @param idIntercambio el id intercambio
     * @param cdOficinaOrigen el cd oficina origen
     * @param dsOficinaOrigen el ds oficina origen
     * @param cdTipoRegistro el cd tipo registro
     * @param cdOficinaDestino el cd oficina destino
     * @param dsOficinaDestino el ds oficina destino
     * @param cdUnidadDestino el cd unidad destino
     * @param dsUnidadDestino el ds unidad destino
     * @param cdUnidadOrigen el cd unidad origen
     * @param dsUnidadOrigen el ds unidad origen
     * @param interesados el interesados
     * @param documentos el documentos
     * @param tlResumen el tl resumen
     * @param cdAsunto el cd asunto
     * @param dsAsunto el ds asunto
     * @param tlReferenciaExterna el tl referencia externa
     * @param tlNumeroExpediente el tl numero expediente
     * @param cdTipoTransporte el cd tipo transporte
     * @param tlNumeroTransporte el tl numero transporte
     * @param tlNombreUsuario el tl nombre usuario
     * @param tlContactoUsuario el tl contacto usuario
     * @param cdDocumentacionFisicaSoportes el cd documentacion fisica soportes
     * @param tlExpone el tl expone
     * @param tlSolicita el tl solicita
     * @param tlAplicacion el tl aplicacion
     * @param tlObservaciones el tl observaciones
     * @param blJustificante el bl justificante
     * @param respuesta el respuesta
     */
    public JustificanteType(
           java.lang.String nmRegistro,
           java.lang.String feRegistro,
           java.lang.String blTimeStamp,
           java.lang.String idIntercambio,
           java.lang.String cdOficinaOrigen,
           java.lang.String dsOficinaOrigen,
           java.lang.String cdTipoRegistro,
           java.lang.String cdOficinaDestino,
           java.lang.String dsOficinaDestino,
           java.lang.String cdUnidadDestino,
           java.lang.String dsUnidadDestino,
           java.lang.String cdUnidadOrigen,
           java.lang.String dsUnidadOrigen,
           es.map.ipsc.clienteWS.registroRec3.type.InteresadoJustificanteType[] interesados,
           es.map.ipsc.clienteWS.registroRec3.type.DocumentoJustificanteType[] documentos,
           java.lang.String tlResumen,
           java.lang.String cdAsunto,
           java.lang.String dsAsunto,
           java.lang.String tlReferenciaExterna,
           java.lang.String tlNumeroExpediente,
           java.lang.String cdTipoTransporte,
           java.lang.String tlNumeroTransporte,
           java.lang.String tlNombreUsuario,
           java.lang.String tlContactoUsuario,
           java.lang.String cdDocumentacionFisicaSoportes,
           java.lang.String tlExpone,
           java.lang.String tlSolicita,
           java.lang.String tlAplicacion,
           java.lang.String tlObservaciones,
           java.lang.String blJustificante,
           es.map.ipsc.clienteWS.registroRec3.type.RespuestaType respuesta) {
           this.nmRegistro = nmRegistro;
           this.feRegistro = feRegistro;
           this.blTimeStamp = blTimeStamp;
           this.idIntercambio = idIntercambio;
           this.cdOficinaOrigen = cdOficinaOrigen;
           this.dsOficinaOrigen = dsOficinaOrigen;
           this.cdTipoRegistro = cdTipoRegistro;
           this.cdOficinaDestino = cdOficinaDestino;
           this.dsOficinaDestino = dsOficinaDestino;
           this.cdUnidadDestino = cdUnidadDestino;
           this.dsUnidadDestino = dsUnidadDestino;
           this.cdUnidadOrigen = cdUnidadOrigen;
           this.dsUnidadOrigen = dsUnidadOrigen;
           this.interesados = interesados;
           this.documentos = documentos;
           this.tlResumen = tlResumen;
           this.cdAsunto = cdAsunto;
           this.dsAsunto = dsAsunto;
           this.tlReferenciaExterna = tlReferenciaExterna;
           this.tlNumeroExpediente = tlNumeroExpediente;
           this.cdTipoTransporte = cdTipoTransporte;
           this.tlNumeroTransporte = tlNumeroTransporte;
           this.tlNombreUsuario = tlNombreUsuario;
           this.tlContactoUsuario = tlContactoUsuario;
           this.cdDocumentacionFisicaSoportes = cdDocumentacionFisicaSoportes;
           this.tlExpone = tlExpone;
           this.tlSolicita = tlSolicita;
           this.tlAplicacion = tlAplicacion;
           this.tlObservaciones = tlObservaciones;
           this.blJustificante = blJustificante;
           this.respuesta = respuesta;
    }


    /**
     * Gets the nmRegistro value for this JustificanteType.
     * 
     * @return nmRegistro
     */
    public java.lang.String getNmRegistro() {
        return nmRegistro;
    }


    /**
     * Sets the nmRegistro value for this JustificanteType.
     *
     * @param nmRegistro el nuevo nm registro
     */
    public void setNmRegistro(java.lang.String nmRegistro) {
        this.nmRegistro = nmRegistro;
    }


    /**
     * Gets the feRegistro value for this JustificanteType.
     * 
     * @return feRegistro
     */
    public java.lang.String getFeRegistro() {
        return feRegistro;
    }


    /**
     * Sets the feRegistro value for this JustificanteType.
     *
     * @param feRegistro el nuevo fe registro
     */
    public void setFeRegistro(java.lang.String feRegistro) {
        this.feRegistro = feRegistro;
    }


    /**
     * Gets the blTimeStamp value for this JustificanteType.
     * 
     * @return blTimeStamp
     */
    public java.lang.String getBlTimeStamp() {
        return blTimeStamp;
    }


    /**
     * Sets the blTimeStamp value for this JustificanteType.
     *
     * @param blTimeStamp el nuevo bl time stamp
     */
    public void setBlTimeStamp(java.lang.String blTimeStamp) {
        this.blTimeStamp = blTimeStamp;
    }


    /**
     * Gets the idIntercambio value for this JustificanteType.
     * 
     * @return idIntercambio
     */
    public java.lang.String getIdIntercambio() {
        return idIntercambio;
    }


    /**
     * Sets the idIntercambio value for this JustificanteType.
     *
     * @param idIntercambio el nuevo id intercambio
     */
    public void setIdIntercambio(java.lang.String idIntercambio) {
        this.idIntercambio = idIntercambio;
    }


    /**
     * Gets the cdOficinaOrigen value for this JustificanteType.
     * 
     * @return cdOficinaOrigen
     */
    public java.lang.String getCdOficinaOrigen() {
        return cdOficinaOrigen;
    }


    /**
     * Sets the cdOficinaOrigen value for this JustificanteType.
     *
     * @param cdOficinaOrigen el nuevo cd oficina origen
     */
    public void setCdOficinaOrigen(java.lang.String cdOficinaOrigen) {
        this.cdOficinaOrigen = cdOficinaOrigen;
    }


    /**
     * Gets the dsOficinaOrigen value for this JustificanteType.
     * 
     * @return dsOficinaOrigen
     */
    public java.lang.String getDsOficinaOrigen() {
        return dsOficinaOrigen;
    }


    /**
     * Sets the dsOficinaOrigen value for this JustificanteType.
     *
     * @param dsOficinaOrigen el nuevo ds oficina origen
     */
    public void setDsOficinaOrigen(java.lang.String dsOficinaOrigen) {
        this.dsOficinaOrigen = dsOficinaOrigen;
    }


    /**
     * Gets the cdTipoRegistro value for this JustificanteType.
     * 
     * @return cdTipoRegistro
     */
    public java.lang.String getCdTipoRegistro() {
        return cdTipoRegistro;
    }


    /**
     * Sets the cdTipoRegistro value for this JustificanteType.
     *
     * @param cdTipoRegistro el nuevo cd tipo registro
     */
    public void setCdTipoRegistro(java.lang.String cdTipoRegistro) {
        this.cdTipoRegistro = cdTipoRegistro;
    }


    /**
     * Gets the cdOficinaDestino value for this JustificanteType.
     * 
     * @return cdOficinaDestino
     */
    public java.lang.String getCdOficinaDestino() {
        return cdOficinaDestino;
    }


    /**
     * Sets the cdOficinaDestino value for this JustificanteType.
     *
     * @param cdOficinaDestino el nuevo cd oficina destino
     */
    public void setCdOficinaDestino(java.lang.String cdOficinaDestino) {
        this.cdOficinaDestino = cdOficinaDestino;
    }


    /**
     * Gets the dsOficinaDestino value for this JustificanteType.
     * 
     * @return dsOficinaDestino
     */
    public java.lang.String getDsOficinaDestino() {
        return dsOficinaDestino;
    }


    /**
     * Sets the dsOficinaDestino value for this JustificanteType.
     *
     * @param dsOficinaDestino el nuevo ds oficina destino
     */
    public void setDsOficinaDestino(java.lang.String dsOficinaDestino) {
        this.dsOficinaDestino = dsOficinaDestino;
    }


    /**
     * Gets the cdUnidadDestino value for this JustificanteType.
     * 
     * @return cdUnidadDestino
     */
    public java.lang.String getCdUnidadDestino() {
        return cdUnidadDestino;
    }


    /**
     * Sets the cdUnidadDestino value for this JustificanteType.
     *
     * @param cdUnidadDestino el nuevo cd unidad destino
     */
    public void setCdUnidadDestino(java.lang.String cdUnidadDestino) {
        this.cdUnidadDestino = cdUnidadDestino;
    }


    /**
     * Gets the dsUnidadDestino value for this JustificanteType.
     * 
     * @return dsUnidadDestino
     */
    public java.lang.String getDsUnidadDestino() {
        return dsUnidadDestino;
    }


    /**
     * Sets the dsUnidadDestino value for this JustificanteType.
     *
     * @param dsUnidadDestino el nuevo ds unidad destino
     */
    public void setDsUnidadDestino(java.lang.String dsUnidadDestino) {
        this.dsUnidadDestino = dsUnidadDestino;
    }


    /**
     * Gets the cdUnidadOrigen value for this JustificanteType.
     * 
     * @return cdUnidadOrigen
     */
    public java.lang.String getCdUnidadOrigen() {
        return cdUnidadOrigen;
    }


    /**
     * Sets the cdUnidadOrigen value for this JustificanteType.
     *
     * @param cdUnidadOrigen el nuevo cd unidad origen
     */
    public void setCdUnidadOrigen(java.lang.String cdUnidadOrigen) {
        this.cdUnidadOrigen = cdUnidadOrigen;
    }


    /**
     * Gets the dsUnidadOrigen value for this JustificanteType.
     * 
     * @return dsUnidadOrigen
     */
    public java.lang.String getDsUnidadOrigen() {
        return dsUnidadOrigen;
    }


    /**
     * Sets the dsUnidadOrigen value for this JustificanteType.
     *
     * @param dsUnidadOrigen el nuevo ds unidad origen
     */
    public void setDsUnidadOrigen(java.lang.String dsUnidadOrigen) {
        this.dsUnidadOrigen = dsUnidadOrigen;
    }


    /**
     * Gets the interesados value for this JustificanteType.
     * 
     * @return interesados
     */
    public es.map.ipsc.clienteWS.registroRec3.type.InteresadoJustificanteType[] getInteresados() {
        return interesados;
    }


    /**
     * Sets the interesados value for this JustificanteType.
     *
     * @param interesados el nuevo interesados
     */
    public void setInteresados(es.map.ipsc.clienteWS.registroRec3.type.InteresadoJustificanteType[] interesados) {
        this.interesados = interesados;
    }


    /**
     * Gets the documentos value for this JustificanteType.
     * 
     * @return documentos
     */
    public es.map.ipsc.clienteWS.registroRec3.type.DocumentoJustificanteType[] getDocumentos() {
        return documentos;
    }


    /**
     * Sets the documentos value for this JustificanteType.
     *
     * @param documentos el nuevo documentos
     */
    public void setDocumentos(es.map.ipsc.clienteWS.registroRec3.type.DocumentoJustificanteType[] documentos) {
        this.documentos = documentos;
    }


    /**
     * Gets the tlResumen value for this JustificanteType.
     * 
     * @return tlResumen
     */
    public java.lang.String getTlResumen() {
        return tlResumen;
    }


    /**
     * Sets the tlResumen value for this JustificanteType.
     *
     * @param tlResumen el nuevo tl resumen
     */
    public void setTlResumen(java.lang.String tlResumen) {
        this.tlResumen = tlResumen;
    }


    /**
     * Gets the cdAsunto value for this JustificanteType.
     * 
     * @return cdAsunto
     */
    public java.lang.String getCdAsunto() {
        return cdAsunto;
    }


    /**
     * Sets the cdAsunto value for this JustificanteType.
     *
     * @param cdAsunto el nuevo cd asunto
     */
    public void setCdAsunto(java.lang.String cdAsunto) {
        this.cdAsunto = cdAsunto;
    }


    /**
     * Gets the dsAsunto value for this JustificanteType.
     * 
     * @return dsAsunto
     */
    public java.lang.String getDsAsunto() {
        return dsAsunto;
    }


    /**
     * Sets the dsAsunto value for this JustificanteType.
     *
     * @param dsAsunto el nuevo ds asunto
     */
    public void setDsAsunto(java.lang.String dsAsunto) {
        this.dsAsunto = dsAsunto;
    }


    /**
     * Gets the tlReferenciaExterna value for this JustificanteType.
     * 
     * @return tlReferenciaExterna
     */
    public java.lang.String getTlReferenciaExterna() {
        return tlReferenciaExterna;
    }


    /**
     * Sets the tlReferenciaExterna value for this JustificanteType.
     *
     * @param tlReferenciaExterna el nuevo tl referencia externa
     */
    public void setTlReferenciaExterna(java.lang.String tlReferenciaExterna) {
        this.tlReferenciaExterna = tlReferenciaExterna;
    }


    /**
     * Gets the tlNumeroExpediente value for this JustificanteType.
     * 
     * @return tlNumeroExpediente
     */
    public java.lang.String getTlNumeroExpediente() {
        return tlNumeroExpediente;
    }


    /**
     * Sets the tlNumeroExpediente value for this JustificanteType.
     *
     * @param tlNumeroExpediente el nuevo tl numero expediente
     */
    public void setTlNumeroExpediente(java.lang.String tlNumeroExpediente) {
        this.tlNumeroExpediente = tlNumeroExpediente;
    }


    /**
     * Gets the cdTipoTransporte value for this JustificanteType.
     * 
     * @return cdTipoTransporte
     */
    public java.lang.String getCdTipoTransporte() {
        return cdTipoTransporte;
    }


    /**
     * Sets the cdTipoTransporte value for this JustificanteType.
     *
     * @param cdTipoTransporte el nuevo cd tipo transporte
     */
    public void setCdTipoTransporte(java.lang.String cdTipoTransporte) {
        this.cdTipoTransporte = cdTipoTransporte;
    }


    /**
     * Gets the tlNumeroTransporte value for this JustificanteType.
     * 
     * @return tlNumeroTransporte
     */
    public java.lang.String getTlNumeroTransporte() {
        return tlNumeroTransporte;
    }


    /**
     * Sets the tlNumeroTransporte value for this JustificanteType.
     *
     * @param tlNumeroTransporte el nuevo tl numero transporte
     */
    public void setTlNumeroTransporte(java.lang.String tlNumeroTransporte) {
        this.tlNumeroTransporte = tlNumeroTransporte;
    }


    /**
     * Gets the tlNombreUsuario value for this JustificanteType.
     * 
     * @return tlNombreUsuario
     */
    public java.lang.String getTlNombreUsuario() {
        return tlNombreUsuario;
    }


    /**
     * Sets the tlNombreUsuario value for this JustificanteType.
     *
     * @param tlNombreUsuario el nuevo tl nombre usuario
     */
    public void setTlNombreUsuario(java.lang.String tlNombreUsuario) {
        this.tlNombreUsuario = tlNombreUsuario;
    }


    /**
     * Gets the tlContactoUsuario value for this JustificanteType.
     * 
     * @return tlContactoUsuario
     */
    public java.lang.String getTlContactoUsuario() {
        return tlContactoUsuario;
    }


    /**
     * Sets the tlContactoUsuario value for this JustificanteType.
     *
     * @param tlContactoUsuario el nuevo tl contacto usuario
     */
    public void setTlContactoUsuario(java.lang.String tlContactoUsuario) {
        this.tlContactoUsuario = tlContactoUsuario;
    }


    /**
     * Gets the cdDocumentacionFisicaSoportes value for this JustificanteType.
     * 
     * @return cdDocumentacionFisicaSoportes
     */
    public java.lang.String getCdDocumentacionFisicaSoportes() {
        return cdDocumentacionFisicaSoportes;
    }


    /**
     * Sets the cdDocumentacionFisicaSoportes value for this JustificanteType.
     *
     * @param cdDocumentacionFisicaSoportes el nuevo cd documentacion fisica soportes
     */
    public void setCdDocumentacionFisicaSoportes(java.lang.String cdDocumentacionFisicaSoportes) {
        this.cdDocumentacionFisicaSoportes = cdDocumentacionFisicaSoportes;
    }


    /**
     * Gets the tlExpone value for this JustificanteType.
     * 
     * @return tlExpone
     */
    public java.lang.String getTlExpone() {
        return tlExpone;
    }


    /**
     * Sets the tlExpone value for this JustificanteType.
     *
     * @param tlExpone el nuevo tl expone
     */
    public void setTlExpone(java.lang.String tlExpone) {
        this.tlExpone = tlExpone;
    }


    /**
     * Gets the tlSolicita value for this JustificanteType.
     * 
     * @return tlSolicita
     */
    public java.lang.String getTlSolicita() {
        return tlSolicita;
    }


    /**
     * Sets the tlSolicita value for this JustificanteType.
     *
     * @param tlSolicita el nuevo tl solicita
     */
    public void setTlSolicita(java.lang.String tlSolicita) {
        this.tlSolicita = tlSolicita;
    }


    /**
     * Gets the tlAplicacion value for this JustificanteType.
     * 
     * @return tlAplicacion
     */
    public java.lang.String getTlAplicacion() {
        return tlAplicacion;
    }


    /**
     * Sets the tlAplicacion value for this JustificanteType.
     *
     * @param tlAplicacion el nuevo tl aplicacion
     */
    public void setTlAplicacion(java.lang.String tlAplicacion) {
        this.tlAplicacion = tlAplicacion;
    }


    /**
     * Gets the tlObservaciones value for this JustificanteType.
     * 
     * @return tlObservaciones
     */
    public java.lang.String getTlObservaciones() {
        return tlObservaciones;
    }


    /**
     * Sets the tlObservaciones value for this JustificanteType.
     *
     * @param tlObservaciones el nuevo tl observaciones
     */
    public void setTlObservaciones(java.lang.String tlObservaciones) {
        this.tlObservaciones = tlObservaciones;
    }


    /**
     * Gets the blJustificante value for this JustificanteType.
     * 
     * @return blJustificante
     */
    public java.lang.String getBlJustificante() {
        return blJustificante;
    }


    /**
     * Sets the blJustificante value for this JustificanteType.
     *
     * @param blJustificante el nuevo bl justificante
     */
    public void setBlJustificante(java.lang.String blJustificante) {
        this.blJustificante = blJustificante;
    }


    /**
     * Gets the respuesta value for this JustificanteType.
     * 
     * @return respuesta
     */
    public es.map.ipsc.clienteWS.registroRec3.type.RespuestaType getRespuesta() {
        return respuesta;
    }


    /**
     * Sets the respuesta value for this JustificanteType.
     *
     * @param respuesta el nuevo respuesta
     */
    public void setRespuesta(es.map.ipsc.clienteWS.registroRec3.type.RespuestaType respuesta) {
        this.respuesta = respuesta;
    }

    /** el equals calc. */
    private java.lang.Object __equalsCalc = null;
    
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof JustificanteType)) return false;
        JustificanteType other = (JustificanteType) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.nmRegistro==null && other.getNmRegistro()==null) || 
             (this.nmRegistro!=null &&
              this.nmRegistro.equals(other.getNmRegistro()))) &&
            ((this.feRegistro==null && other.getFeRegistro()==null) || 
             (this.feRegistro!=null &&
              this.feRegistro.equals(other.getFeRegistro()))) &&
            ((this.blTimeStamp==null && other.getBlTimeStamp()==null) || 
             (this.blTimeStamp!=null &&
              this.blTimeStamp.equals(other.getBlTimeStamp()))) &&
            ((this.idIntercambio==null && other.getIdIntercambio()==null) || 
             (this.idIntercambio!=null &&
              this.idIntercambio.equals(other.getIdIntercambio()))) &&
            ((this.cdOficinaOrigen==null && other.getCdOficinaOrigen()==null) || 
             (this.cdOficinaOrigen!=null &&
              this.cdOficinaOrigen.equals(other.getCdOficinaOrigen()))) &&
            ((this.dsOficinaOrigen==null && other.getDsOficinaOrigen()==null) || 
             (this.dsOficinaOrigen!=null &&
              this.dsOficinaOrigen.equals(other.getDsOficinaOrigen()))) &&
            ((this.cdTipoRegistro==null && other.getCdTipoRegistro()==null) || 
             (this.cdTipoRegistro!=null &&
              this.cdTipoRegistro.equals(other.getCdTipoRegistro()))) &&
            ((this.cdOficinaDestino==null && other.getCdOficinaDestino()==null) || 
             (this.cdOficinaDestino!=null &&
              this.cdOficinaDestino.equals(other.getCdOficinaDestino()))) &&
            ((this.dsOficinaDestino==null && other.getDsOficinaDestino()==null) || 
             (this.dsOficinaDestino!=null &&
              this.dsOficinaDestino.equals(other.getDsOficinaDestino()))) &&
            ((this.cdUnidadDestino==null && other.getCdUnidadDestino()==null) || 
             (this.cdUnidadDestino!=null &&
              this.cdUnidadDestino.equals(other.getCdUnidadDestino()))) &&
            ((this.dsUnidadDestino==null && other.getDsUnidadDestino()==null) || 
             (this.dsUnidadDestino!=null &&
              this.dsUnidadDestino.equals(other.getDsUnidadDestino()))) &&
            ((this.cdUnidadOrigen==null && other.getCdUnidadOrigen()==null) || 
             (this.cdUnidadOrigen!=null &&
              this.cdUnidadOrigen.equals(other.getCdUnidadOrigen()))) &&
            ((this.dsUnidadOrigen==null && other.getDsUnidadOrigen()==null) || 
             (this.dsUnidadOrigen!=null &&
              this.dsUnidadOrigen.equals(other.getDsUnidadOrigen()))) &&
            ((this.interesados==null && other.getInteresados()==null) || 
             (this.interesados!=null &&
              java.util.Arrays.equals(this.interesados, other.getInteresados()))) &&
            ((this.documentos==null && other.getDocumentos()==null) || 
             (this.documentos!=null &&
              java.util.Arrays.equals(this.documentos, other.getDocumentos()))) &&
            ((this.tlResumen==null && other.getTlResumen()==null) || 
             (this.tlResumen!=null &&
              this.tlResumen.equals(other.getTlResumen()))) &&
            ((this.cdAsunto==null && other.getCdAsunto()==null) || 
             (this.cdAsunto!=null &&
              this.cdAsunto.equals(other.getCdAsunto()))) &&
            ((this.dsAsunto==null && other.getDsAsunto()==null) || 
             (this.dsAsunto!=null &&
              this.dsAsunto.equals(other.getDsAsunto()))) &&
            ((this.tlReferenciaExterna==null && other.getTlReferenciaExterna()==null) || 
             (this.tlReferenciaExterna!=null &&
              this.tlReferenciaExterna.equals(other.getTlReferenciaExterna()))) &&
            ((this.tlNumeroExpediente==null && other.getTlNumeroExpediente()==null) || 
             (this.tlNumeroExpediente!=null &&
              this.tlNumeroExpediente.equals(other.getTlNumeroExpediente()))) &&
            ((this.cdTipoTransporte==null && other.getCdTipoTransporte()==null) || 
             (this.cdTipoTransporte!=null &&
              this.cdTipoTransporte.equals(other.getCdTipoTransporte()))) &&
            ((this.tlNumeroTransporte==null && other.getTlNumeroTransporte()==null) || 
             (this.tlNumeroTransporte!=null &&
              this.tlNumeroTransporte.equals(other.getTlNumeroTransporte()))) &&
            ((this.tlNombreUsuario==null && other.getTlNombreUsuario()==null) || 
             (this.tlNombreUsuario!=null &&
              this.tlNombreUsuario.equals(other.getTlNombreUsuario()))) &&
            ((this.tlContactoUsuario==null && other.getTlContactoUsuario()==null) || 
             (this.tlContactoUsuario!=null &&
              this.tlContactoUsuario.equals(other.getTlContactoUsuario()))) &&
            ((this.cdDocumentacionFisicaSoportes==null && other.getCdDocumentacionFisicaSoportes()==null) || 
             (this.cdDocumentacionFisicaSoportes!=null &&
              this.cdDocumentacionFisicaSoportes.equals(other.getCdDocumentacionFisicaSoportes()))) &&
            ((this.tlExpone==null && other.getTlExpone()==null) || 
             (this.tlExpone!=null &&
              this.tlExpone.equals(other.getTlExpone()))) &&
            ((this.tlSolicita==null && other.getTlSolicita()==null) || 
             (this.tlSolicita!=null &&
              this.tlSolicita.equals(other.getTlSolicita()))) &&
            ((this.tlAplicacion==null && other.getTlAplicacion()==null) || 
             (this.tlAplicacion!=null &&
              this.tlAplicacion.equals(other.getTlAplicacion()))) &&
            ((this.tlObservaciones==null && other.getTlObservaciones()==null) || 
             (this.tlObservaciones!=null &&
              this.tlObservaciones.equals(other.getTlObservaciones()))) &&
            ((this.blJustificante==null && other.getBlJustificante()==null) || 
             (this.blJustificante!=null &&
              this.blJustificante.equals(other.getBlJustificante()))) &&
            ((this.respuesta==null && other.getRespuesta()==null) || 
             (this.respuesta!=null &&
              this.respuesta.equals(other.getRespuesta())));
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
        if (getNmRegistro() != null) {
            _hashCode += getNmRegistro().hashCode();
        }
        if (getFeRegistro() != null) {
            _hashCode += getFeRegistro().hashCode();
        }
        if (getBlTimeStamp() != null) {
            _hashCode += getBlTimeStamp().hashCode();
        }
        if (getIdIntercambio() != null) {
            _hashCode += getIdIntercambio().hashCode();
        }
        if (getCdOficinaOrigen() != null) {
            _hashCode += getCdOficinaOrigen().hashCode();
        }
        if (getDsOficinaOrigen() != null) {
            _hashCode += getDsOficinaOrigen().hashCode();
        }
        if (getCdTipoRegistro() != null) {
            _hashCode += getCdTipoRegistro().hashCode();
        }
        if (getCdOficinaDestino() != null) {
            _hashCode += getCdOficinaDestino().hashCode();
        }
        if (getDsOficinaDestino() != null) {
            _hashCode += getDsOficinaDestino().hashCode();
        }
        if (getCdUnidadDestino() != null) {
            _hashCode += getCdUnidadDestino().hashCode();
        }
        if (getDsUnidadDestino() != null) {
            _hashCode += getDsUnidadDestino().hashCode();
        }
        if (getCdUnidadOrigen() != null) {
            _hashCode += getCdUnidadOrigen().hashCode();
        }
        if (getDsUnidadOrigen() != null) {
            _hashCode += getDsUnidadOrigen().hashCode();
        }
        if (getInteresados() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getInteresados());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getInteresados(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDocumentos() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDocumentos());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDocumentos(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getTlResumen() != null) {
            _hashCode += getTlResumen().hashCode();
        }
        if (getCdAsunto() != null) {
            _hashCode += getCdAsunto().hashCode();
        }
        if (getDsAsunto() != null) {
            _hashCode += getDsAsunto().hashCode();
        }
        if (getTlReferenciaExterna() != null) {
            _hashCode += getTlReferenciaExterna().hashCode();
        }
        if (getTlNumeroExpediente() != null) {
            _hashCode += getTlNumeroExpediente().hashCode();
        }
        if (getCdTipoTransporte() != null) {
            _hashCode += getCdTipoTransporte().hashCode();
        }
        if (getTlNumeroTransporte() != null) {
            _hashCode += getTlNumeroTransporte().hashCode();
        }
        if (getTlNombreUsuario() != null) {
            _hashCode += getTlNombreUsuario().hashCode();
        }
        if (getTlContactoUsuario() != null) {
            _hashCode += getTlContactoUsuario().hashCode();
        }
        if (getCdDocumentacionFisicaSoportes() != null) {
            _hashCode += getCdDocumentacionFisicaSoportes().hashCode();
        }
        if (getTlExpone() != null) {
            _hashCode += getTlExpone().hashCode();
        }
        if (getTlSolicita() != null) {
            _hashCode += getTlSolicita().hashCode();
        }
        if (getTlAplicacion() != null) {
            _hashCode += getTlAplicacion().hashCode();
        }
        if (getTlObservaciones() != null) {
            _hashCode += getTlObservaciones().hashCode();
        }
        if (getBlJustificante() != null) {
            _hashCode += getBlJustificante().hashCode();
        }
        if (getRespuesta() != null) {
            _hashCode += getRespuesta().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    /** el type desc. */
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(JustificanteType.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://type.ws2.rec2are.map.es", "JustificanteType"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nmRegistro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nmRegistro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("feRegistro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "feRegistro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blTimeStamp");
        elemField.setXmlName(new javax.xml.namespace.QName("", "blTimeStamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idIntercambio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idIntercambio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdOficinaOrigen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdOficinaOrigen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsOficinaOrigen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dsOficinaOrigen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdTipoRegistro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdTipoRegistro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdOficinaDestino");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdOficinaDestino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsOficinaDestino");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dsOficinaDestino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdUnidadDestino");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdUnidadDestino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsUnidadDestino");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dsUnidadDestino"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdUnidadOrigen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdUnidadOrigen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsUnidadOrigen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dsUnidadOrigen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("interesados");
        elemField.setXmlName(new javax.xml.namespace.QName("", "interesados"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://type.ws2.rec2are.map.es", "InteresadoJustificanteType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentos");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documentos"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://type.ws2.rec2are.map.es", "DocumentoJustificanteType"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlResumen");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlResumen"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdAsunto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdAsunto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dsAsunto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dsAsunto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlReferenciaExterna");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlReferenciaExterna"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlNumeroExpediente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlNumeroExpediente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdTipoTransporte");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdTipoTransporte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlNumeroTransporte");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlNumeroTransporte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlNombreUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlNombreUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlContactoUsuario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlContactoUsuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cdDocumentacionFisicaSoportes");
        elemField.setXmlName(new javax.xml.namespace.QName("", "cdDocumentacionFisicaSoportes"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlExpone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlExpone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlSolicita");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlSolicita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlAplicacion");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlAplicacion"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tlObservaciones");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tlObservaciones"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("blJustificante");
        elemField.setXmlName(new javax.xml.namespace.QName("", "blJustificante"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("respuesta");
        elemField.setXmlName(new javax.xml.namespace.QName("", "respuesta"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://type.ws2.rec2are.map.es", "RespuestaType"));
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
