package es.map.ipsc.bean;

/**
 * El Class RelacionDocFirmaBean.
 */
public class RelacionDocFirmaBean implements java.io.Serializable {
	
	/** el documento. */
	transient DocumentoBean documento;
	
	/** el firma. */
	FirmaBean firma;
	
	/**
	 * Obtiene el documento.
	 *
	 * @return el documento
	 */
	public DocumentoBean getDocumento() {
		return documento;
	}
	
	/**
	 * Establece el documento.
	 *
	 * @param documento el nuevo documento
	 */
	public void setDocumento(DocumentoBean documento) {
		this.documento = documento;
	}
	
	/**
	 * Obtiene el firma.
	 *
	 * @return el firma
	 */
	public FirmaBean getFirma() {
		return firma;
	}
	
	/**
	 * Establece el firma.
	 *
	 * @param firma el nuevo firma
	 */
	public void setFirma(FirmaBean firma) {
		this.firma = firma;
	}
	
}
