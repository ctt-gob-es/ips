package es.map.ipsc.form;

import es.map.ips.common.spring.SpringForm;

/**
 * El Class CargaJustificanteAjaxForm.
 */
public class CargaJustificanteAjaxForm extends SpringForm {

	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 5311696132794036570L;
	
	/** el num justificante presencial. */
	private String numJustificantePresencial;
	
	/** el id convocatoria. */
	private String idConvocatoria;

	/**
	 * Obtiene el num justificante presencial.
	 *
	 * @return el num justificante presencial
	 */
	public String getNumJustificantePresencial() {
		return numJustificantePresencial;
	}

	/**
	 * Establece el num justificante presencial.
	 *
	 * @param numJustificantePresencial el nuevo num justificante presencial
	 */
	public void setNumJustificantePresencial(String numJustificantePresencial) {
		this.numJustificantePresencial = numJustificantePresencial;
	}

	/**
	 * Obtiene el id convocatoria.
	 *
	 * @return el id convocatoria
	 */
	public String getIdConvocatoria() {
		return idConvocatoria;
	}

	/**
	 * Establece el id convocatoria.
	 *
	 * @param idConvocatoria el nuevo id convocatoria
	 */
	public void setIdConvocatoria(String idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
	}

}
