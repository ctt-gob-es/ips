package es.map.ipsc.form;

import es.map.ips.common.spring.SpringForm;

/**
 * El Class DescargarPdfForm.
 */
public class DescargarPdfForm extends SpringForm {

	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** el idiomas. */
	private String idiomas;
    
    /** el id ministerio. */
    private String idMinisterio;
    

	/**
	 * Obtiene el idiomas.
	 *
	 * @return el idiomas
	 */
	public String getIdiomas() {
		return idiomas;
	}

	/**
	 * Establece el idiomas.
	 *
	 * @param idiomas el nuevo idiomas
	 */
	public void setIdiomas(String idiomas) {
		this.idiomas = idiomas;
	}

	/**
	 * Obtiene el id ministerio.
	 *
	 * @return el id ministerio
	 */
	public String getIdMinisterio() {
		return idMinisterio;
	}

	/**
	 * Establece el id ministerio.
	 *
	 * @param idMinisterio el nuevo id ministerio
	 */
	public void setIdMinisterio(String idMinisterio) {
		this.idMinisterio = idMinisterio;
	}

	
	
	
	
	
}
