package es.map.ipsc.bean;

/**
 * El Class FirmaBean.
 */
public class FirmaBean extends DocumentoBean implements java.io.Serializable {
	
	/** el firma base 64. */
	private String firmaBase64;

	/**
	 * Obtiene el firma base 64.
	 *
	 * @return el firma base 64
	 */
	public String getFirmaBase64() {
		return firmaBase64;
	}

	/**
	 * Establece el firma base 64.
	 *
	 * @param firmaBase64 el nuevo firma base 64
	 */
	public void setFirmaBase64(String firmaBase64) {
		this.firmaBase64 = firmaBase64;
	}

}
