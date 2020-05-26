package es.egoeveris.firma.impl.afirma.model.parametrosFirmaServidor;

import java.io.Serializable;


/**
 * El Class ParamFirma.
 */
public class ParamFirma implements Serializable {
	
	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 3021111115975596634L;

	/** el id aplicacion. */
	private String idAplicacion;
	
	/** el id documento. */
	private String idDocumento;
	
	/** el documento. */
	private String documento;
	
	/** el nombre documento. */
	private String nombreDocumento;
	
	/** el tipo documento. */
	private String tipoDocumento;
	
	/** el firmante. */
	private String firmante;
	
	/** el id referencia. */
	private String idReferencia;
	
	/** el algoritmo hash. */
	private String algoritmoHash;
	
	/** el formato firma. */
	private String formatoFirma;

	/**
	 * Obtiene el id aplicacion.
	 *
	 * @return el id aplicacion
	 */
	public String getIdAplicacion() {
		return idAplicacion;
	}

	/**
	 * Establece el id aplicacion.
	 *
	 * @param idAplicacion el nuevo id aplicacion
	 */
	public void setIdAplicacion(String idAplicacion) {
		this.idAplicacion = idAplicacion;
	}

	/**
	 * Obtiene el id documento.
	 *
	 * @return el id documento
	 */
	public String getIdDocumento() {
		return idDocumento;
	}

	/**
	 * Establece el id documento.
	 *
	 * @param idDocumento el nuevo id documento
	 */
	public void setIdDocumento(String idDocumento) {
		this.idDocumento = idDocumento;
	}

	/**
	 * Establece el documento.
	 *
	 * @param documento el nuevo documento
	 */
	public void setDocumento(String documento) {
		this.documento = documento;
	}

	/**
	 * Obtiene el documento.
	 *
	 * @return el documento
	 */
	public String getDocumento() {
		return documento;
	}
	
	/**
	 * Obtiene el nombre documento.
	 *
	 * @return el nombre documento
	 */
	public String getNombreDocumento() {
		return nombreDocumento;
	}

	/**
	 * Establece el nombre documento.
	 *
	 * @param nombreDocumento el nuevo nombre documento
	 */
	public void setNombreDocumento(String nombreDocumento) {
		this.nombreDocumento = nombreDocumento;
	}

	/**
	 * Obtiene el tipo documento.
	 *
	 * @return el tipo documento
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}

	/**
	 * Establece el tipo documento.
	 *
	 * @param tipoDocumento el nuevo tipo documento
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Obtiene el firmante.
	 *
	 * @return el firmante
	 */
	public String getFirmante() {
		return firmante;
	}

	/**
	 * Establece el firmante.
	 *
	 * @param firmante el nuevo firmante
	 */
	public void setFirmante(String firmante) {
		this.firmante = firmante;
	}

	/**
	 * Obtiene el id referencia.
	 *
	 * @return el id referencia
	 */
	public String getIdReferencia() {
		return idReferencia;
	}

	/**
	 * Establece el id referencia.
	 *
	 * @param idReferencia el nuevo id referencia
	 */
	public void setIdReferencia(String idReferencia) {
		this.idReferencia = idReferencia;
	}

	/**
	 * Obtiene el algoritmo hash.
	 *
	 * @return el algoritmo hash
	 */
	public String getAlgoritmoHash() {
		return algoritmoHash;
	}

	/**
	 * Establece el algoritmo hash.
	 *
	 * @param algoritmoHash el nuevo algoritmo hash
	 */
	public void setAlgoritmoHash(String algoritmoHash) {
		this.algoritmoHash = algoritmoHash;
	}

	/**
	 * Obtiene el formato firma.
	 *
	 * @return el formato firma
	 */
	public String getFormatoFirma() {
		return formatoFirma;
	}

	/**
	 * Establece el formato firma.
	 *
	 * @param formatoFirma el nuevo formato firma
	 */
	public void setFormatoFirma(String formatoFirma) {
		this.formatoFirma = formatoFirma;
	}

	/**
	 * Obtiene el serial version UID.
	 *
	 * @return el serial version UID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}



}
