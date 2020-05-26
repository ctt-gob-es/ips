package es.map.ipsg.form;

import es.map.ips.common.spring.SpringForm;

/**
 * El Class ConsultarRegistrosRecForm.
 */
public class ConsultarRegistrosRecForm extends SpringForm {


	/** el num justificante. */
	private String numJustificante;
	
	/** el perfil usuario. */
	private String perfilUsuario; //Perfil Usuario
	
	/** el fe registro. */
	private String feRegistro;
	
	/** el num registro. */
	private String numRegistro;
	
	/** el cd oficina origen. */
	private String cdOficinaOrigen;



	/**
	 * Obtiene el num justificante.
	 *
	 * @return numJustificante String
	 */
	public String getNumJustificante() {
		return (numJustificante!=null ? numJustificante.trim():null);
	}
	
	/**
	 * Establece el num justificante.
	 *
	 * @param numJustificante String
	 */
	public void setNumJustificante(String numJustificante) {
		this.numJustificante = numJustificante;
	}
	
	/**
	 * Obtiene el perfil usuario.
	 *
	 * @return perfilUsuario String
	 */
	public String getPerfilUsuario() {
		return perfilUsuario;
	}
	
	/**
	 * Establece el perfil usuario.
	 *
	 * @param perfilUsuario String
	 */
	public void setPerfilUsuario(String perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}
	
	/**
	 * Obtiene el fe registro.
	 *
	 * @return el fe registro
	 */
	public String getFeRegistro() {
		return feRegistro;
	}
	
	/**
	 * Establece el fe registro.
	 *
	 * @param feRegistro el nuevo fe registro
	 */
	public void setFeRegistro(String feRegistro) {
		this.feRegistro = feRegistro;
	}
	
	/**
	 * Obtiene el num registro.
	 *
	 * @return el num registro
	 */
	public String getNumRegistro() {
		return numRegistro;
	}
	
	/**
	 * Establece el num registro.
	 *
	 * @param numRegistro el nuevo num registro
	 */
	public void setNumRegistro(String numRegistro) {
		this.numRegistro = numRegistro;
	}
	
	/**
	 * Obtiene el cd oficina origen.
	 *
	 * @return el cd oficina origen
	 */
	public String getCdOficinaOrigen() {
		return cdOficinaOrigen;
	}
	
	/**
	 * Establece el cd oficina origen.
	 *
	 * @param cdOficinaOrigen el nuevo cd oficina origen
	 */
	public void setCdOficinaOrigen(String cdOficinaOrigen) {
		this.cdOficinaOrigen = cdOficinaOrigen;
	}
	


	
	
}

