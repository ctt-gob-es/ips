package es.map.ipsg.util;

/**
 * El Class SignData.
 */
public class SignData {

	/** el tipo mime. */
	private String tipoMime;
	
	/** el document. */
	private byte[] document;
	
	/** el tipo firma. */
	private String tipoFirma;
	
	/**
	 * Crea una nueva sign data.
	 *
	 * @param tipoMime el tipo mime
	 * @param document el document
	 */
	public SignData(String tipoMime, byte[] document) {
		super();
		this.tipoMime = tipoMime;
		this.document = document;
	}
	
	/**
	 * Obtiene el tipo mime.
	 *
	 * @return el tipo mime
	 */
	public String getTipoMime() {
		return tipoMime;
	}
	
	/**
	 * Establece el tipo mime.
	 *
	 * @param tipoMime el nuevo tipo mime
	 */
	public void setTipoMime(String tipoMime) {
		this.tipoMime = tipoMime;
	}
	
	/**
	 * Obtiene el document.
	 *
	 * @return el document
	 */
	public byte[] getDocument() {
		return document;
	}
	
	/**
	 * Establece el document.
	 *
	 * @param document el nuevo document
	 */
	public void setDocument(byte[] document) {
		this.document = document;
	}
	
	/**
	 * Obtiene el tipo firma.
	 *
	 * @return el tipo firma
	 */
	public String getTipoFirma() {
		return tipoFirma;
	}
	
	/**
	 * Establece el tipo firma.
	 *
	 * @param tipoFirma el nuevo tipo firma
	 */
	public void setTipoFirma(String tipoFirma) {
		this.tipoFirma = tipoFirma;
	}
	
	
}
