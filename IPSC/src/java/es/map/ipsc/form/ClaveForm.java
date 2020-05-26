package es.map.ipsc.form;

import es.map.ips.common.spring.SpringForm;

/**
 * El Class ClaveForm.
 */
public class ClaveForm extends SpringForm{

	/** el peps url. */
	private String pepsUrl;
	
	/** el SAM lxml qaa. */
	private String SAMLxmlQaa;
	
	/** el excluded id P list. */
	private String excludedIdPList;
	
	/** el allow legal person. */
	private String allowLegalPerson;
	
	/** el llamada. */
	private String llamada;
	
	/** el convocatoria. */
	private String convocatoria;
	
	/**
	 * Obtiene el peps url.
	 *
	 * @return el peps url
	 */
	public String getPepsUrl() {
		return pepsUrl;
	}
	
	/**
	 * Establece el peps url.
	 *
	 * @param pepsUrl el nuevo peps url
	 */
	public void setPepsUrl(String pepsUrl) {
		this.pepsUrl = pepsUrl;
	}
	
	/**
	 * Obtiene el SAM lxml qaa.
	 *
	 * @return el SAM lxml qaa
	 */
	public String getSAMLxmlQaa() {
		return SAMLxmlQaa;
	}
	
	/**
	 * Establece el SAM lxml qaa.
	 *
	 * @param sAMLxmlQaa el nuevo SAM lxml qaa
	 */
	public void setSAMLxmlQaa(String sAMLxmlQaa) {
		SAMLxmlQaa = sAMLxmlQaa;
	}
	
	/**
	 * Obtiene el llamada.
	 *
	 * @return el llamada
	 */
	public String getLlamada() {
		return llamada;
	}
	
	/**
	 * Establece el llamada.
	 *
	 * @param llamada el nuevo llamada
	 */
	public void setLlamada(String llamada) {
		this.llamada = llamada;
	}
	
	/**
	 * Obtiene el excluded id P list.
	 *
	 * @return el excluded id P list
	 */
	public String getExcludedIdPList() {
		return excludedIdPList;
	}
	
	/**
	 * Establece el excluded id P list.
	 *
	 * @param excludedIdPList el nuevo excluded id P list
	 */
	public void setExcludedIdPList(String excludedIdPList) {
		this.excludedIdPList = excludedIdPList;
	}
	
	/**
	 * Obtiene el allow legal person.
	 *
	 * @return el allow legal person
	 */
	public String getAllowLegalPerson() {
		return allowLegalPerson;
	}
	
	/**
	 * Establece el allow legal person.
	 *
	 * @param allowLegalPerson el nuevo allow legal person
	 */
	public void setAllowLegalPerson(String allowLegalPerson) {
		this.allowLegalPerson = allowLegalPerson;
	}
	
	/**
	 * Obtiene el convocatoria.
	 *
	 * @return el convocatoria
	 */
	public String getConvocatoria() {
		return convocatoria;
	}
	
	/**
	 * Establece el convocatoria.
	 *
	 * @param convocatoria el nuevo convocatoria
	 */
	public void setConvocatoria(String convocatoria) {
		this.convocatoria = convocatoria;
	}

}
