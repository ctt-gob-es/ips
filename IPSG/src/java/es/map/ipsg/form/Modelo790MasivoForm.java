package es.map.ipsg.form;

import javax.servlet.http.HttpServletRequest;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;


/**
 * El Class Modelo790MasivoForm.
 */
public class Modelo790MasivoForm extends SpringForm {

	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** La constante STRING_NUMMODELOSERROR. */
	private static final String STRING_NUMMODELOSERROR = "numModelosError";

	/** el num modelos. */
	private String numModelos;
	
	/** el contenido documento. */
	private byte[] contenidoDocumento;
	
	/** el modelo. */
	private String modelo;

	/**
	 * Obtiene el num modelos.
	 *
	 * @return el num modelos
	 */
	public String getNumModelos() {
		return numModelos;
	}

	/**
	 * Establece el num modelos.
	 *
	 * @param numModelos el nuevo num modelos
	 */
	public void setNumModelos(String numModelos) {
		this.numModelos = numModelos;
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public SpringErrors validate(SpringMapping mapping,
			HttpServletRequest request) {
		SpringErrors errors = new SpringErrors();

		if(numModelos!=null && !numModelos.equals("")){

			try{
				if(numModelos.equals("0") || numModelos.equals("00") || numModelos.substring(0,1).equals("-"))
				{
					errors.add(STRING_NUMMODELOSERROR, new SpringMessage("field.modelo790Masivo.numModelos.noNumero"));
				}
				else
				{	
					Integer numModelosInt = Integer.parseInt(numModelos);
				}	


			}catch(NumberFormatException nfe){
				errors.add(STRING_NUMMODELOSERROR, new SpringMessage("field.modelo790Masivo.numModelos.noNumero"));
			}
		}else{
			errors.add(STRING_NUMMODELOSERROR, new SpringMessage("field.modelo790Masivo.numModelos.null"));
		}

		if(modelo==null || modelo.equals("")){
			errors.add(STRING_NUMMODELOSERROR, new SpringMessage("field.modelo790Masivo.modelos.null"));
		}

		return errors;
	}

	/**
	 * Obtiene el contenido documento.
	 *
	 * @return el contenido documento
	 */
	public byte[] getContenidoDocumento() {
		return contenidoDocumento;
	}

	/**
	 * Establece el contenido documento.
	 *
	 * @param contenidoDocumento el nuevo contenido documento
	 */
	public void setContenidoDocumento(byte[] contenidoDocumento) {
		this.contenidoDocumento = contenidoDocumento;
	}

	/**
	 * Obtiene el modelo.
	 *
	 * @return el modelo
	 */
	public String getModelo() {
		return modelo;
	}

	/**
	 * Establece el modelo.
	 *
	 * @param modelo el nuevo modelo
	 */
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

}
