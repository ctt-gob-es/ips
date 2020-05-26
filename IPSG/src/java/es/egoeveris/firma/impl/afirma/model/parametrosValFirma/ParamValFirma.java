package es.egoeveris.firma.impl.afirma.model.parametrosValFirma;

import java.io.Serializable;


/**
 * El Class ParamValFirma.
 */
public class ParamValFirma implements Serializable{


	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1121289372992259098L;

	/** el id aplicacion. */
	private String idAplicacion;

	/** el firma electronica. */
	private String firmaElectronica;
	
	/** el formato firma. */
	private String formatoFirma;
	
	/** el hash. */
	private String hash;
	
	/** el algoritmo hash. */
	private String algoritmoHash;
	
	/** el datos. */
	private String datos;
	

	
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
	 * Obtiene el firma electronica.
	 *
	 * @return the firmaElectronica
	 */
	public String getFirmaElectronica() {
		return firmaElectronica;
	}



	/**
	 * Obtiene el datos.
	 *
	 * @return the datos
	 */
	public String getDatos() {
		return datos;
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
	 * Obtiene el hash.
	 *
	 * @return the hash
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * Establece el hash.
	 *
	 * @param hash the hash to set
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * Establece el datos.
	 *
	 * @param datos the datos to set
	 */
	public void setDatos(String datos) {
		this.datos = datos;
	}

	/**
	 * Establece el firma electronica.
	 *
	 * @param firmaElectronica the firmaElectronica to set
	 */
	public void setFirmaElectronica(String firmaElectronica) {
		this.firmaElectronica = firmaElectronica;
	}

}
