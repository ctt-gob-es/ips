package es.egoeveris.firma.params;

import java.io.Serializable;

/**
 * El Class FirmaParam.
 */
public class FirmaParam implements Serializable {

	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 7068765183767084213L;

	/** el datos. */
	private byte[] datos;

	/** el datos firmados. */
	private String datosFirmados;

	/** el alias cert. */
	private String aliasCert;

	/** el algoritmo hash. */
	private String algoritmoHash;

	/** el formato firma. */
	private String formatoFirma;               

	/** el id aplicacion. */
	private String idAplicacion;

	/** el nombre fichero. */
	private String nombreFichero;

	/** el id transaccion. */
	private String idTransaccion;

	/** el firmante. */
	private String firmante;

	/** el id referencia. */
	private String idReferencia;

	/** el ticket. */
	private String ticket;

	/** el firmante objetivo. */
	private String firmanteObjetivo;

	/** el tipo documento. */
	private String tipoDocumento;


	/**
	 * Obtiene el tipo documento.
	 *
	 * @return the tipoDocumento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Establece el tipo documento.
	 *
	 * @param tipoDocumento the tipoDocumento to set
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Obtiene el firmante.
	 *
	 * @return the firmante
	 */
	public String getFirmante() {
		return firmante;
	}

	/**
	 * Establece el firmante.
	 *
	 * @param firmante the firmante to set
	 */
	public void setFirmante(String firmante) {
		this.firmante = firmante;
	}

	/**
	 * Obtiene el algoritmo hash.
	 *
	 * @return the algoritmoHash
	 */
	public String getAlgoritmoHash() {
		return algoritmoHash;
	}

	/**
	 * Establece el algoritmo hash.
	 *
	 * @param algoritmoHash the algoritmoHash to set
	 */
	public void setAlgoritmoHash(String algoritmoHash) {
		this.algoritmoHash = algoritmoHash;
	}

	/**
	 * Obtiene el alias cert.
	 *
	 * @return the aliasCert
	 */
	public String getAliasCert() {
		return aliasCert;
	}

	/**
	 * Establece el alias cert.
	 *
	 * @param aliasCert the aliasCert to set
	 */
	public void setAliasCert(String aliasCert) {
		this.aliasCert = aliasCert;
	}

	/**
	 * Obtiene el datos.
	 *
	 * @return the datos
	 */
	public byte[] getDatos() {
		return datos;
	}

	/**
	 * Establece el datos.
	 *
	 * @param datos the datos to set
	 */
	public void setDatos(byte[] datos) {
		this.datos = datos;
	}

	/**
	 * Obtiene el datos firmados.
	 *
	 * @return the datosFirmados
	 */
	public String getDatosFirmados() {
		return datosFirmados;
	}

	/**
	 * Establece el datos firmados.
	 *
	 * @param datosFirmados the datosFirmados to set
	 */
	public void setDatosFirmados(String datosFirmados) {
		this.datosFirmados = datosFirmados;
	}

	/**
	 * Obtiene el formato firma.
	 *
	 * @return the formatoFirma
	 */
	public String getFormatoFirma() {
		return formatoFirma;
	}

	/**
	 * Establece el formato firma.
	 *
	 * @param formatoFirma the formatoFirma to set
	 */
	public void setFormatoFirma(String formatoFirma) {
		this.formatoFirma = formatoFirma;
	}

	/**
	 * Obtiene el id aplicacion.
	 *
	 * @return the idAplicacion
	 */
	public String getIdAplicacion() {
		return idAplicacion;
	}

	/**
	 * Establece el id aplicacion.
	 *
	 * @param idAplicacion the idAplicacion to set
	 */
	public void setIdAplicacion(String idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

	/**
	 * Obtiene el id transaccion.
	 *
	 * @return the idTransaccion
	 */
	public String getIdTransaccion() {
		return idTransaccion;
	}

	/**
	 * Establece el id transaccion.
	 *
	 * @param idTransaccion the idTransaccion to set
	 */
	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	/**
	 * Obtiene el nombre fichero.
	 *
	 * @return the nombreFichero
	 */
	public String getNombreFichero() {
		return nombreFichero;
	}

	/**
	 * Establece el nombre fichero.
	 *
	 * @param nombreFichero the nombreFichero to set
	 */
	public void setNombreFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}

	/**
	 * Obtiene el ticket.
	 *
	 * @return the ticket
	 */
	public String getTicket() {
		return ticket;
	}

	/**
	 * Establece el ticket.
	 *
	 * @param ticket the ticket to set
	 */
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	/**
	 * Obtiene el id referencia.
	 *
	 * @return the idReferencia
	 */
	public String getIdReferencia() {
		return idReferencia;
	}

	/**
	 * Establece el id referencia.
	 *
	 * @param idReferencia the idReferencia to set
	 */
	public void setIdReferencia(String idReferencia) {
		this.idReferencia = idReferencia;
	}

	/**
	 * To string data.
	 *
	 * @return el string
	 */
	public String toStringData(){
		StringBuffer buffer = new StringBuffer();
		buffer.append("FirmaParam");
		buffer.append("AlgoritmoHash ").append(getAlgoritmoHash());
		buffer.append("FormatoFirma ").append(getFormatoFirma());
		buffer.append("AliasCert ").append(getAliasCert());
		buffer.append("IdAplicacion ").append(getIdAplicacion());	
		buffer.append("Ticket ").append(getTicket());

		return buffer.toString();
	}

	/**
	 * Obtiene el firmante objetivo.
	 *
	 * @return the firmanteObjetivo
	 */
	public String getFirmanteObjetivo() {
		return firmanteObjetivo;
	}

	/**
	 * Establece el firmante objetivo.
	 *
	 * @param firmanteObjetivo the firmanteObjetivo to set
	 */
	public void setFirmanteObjetivo(String firmanteObjetivo) {
		this.firmanteObjetivo = firmanteObjetivo;
	}


}
