package es.map.ipsc.bean;


/**
 * El Class ProvinciaBean.
 */
public class ProvinciaBean {
	
	/** el id. */
	private Integer id;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el estado. */
	private char estado;
	
	/** el codigo. */
	private String codigo;
	
	
	
	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el estado.
	 *
	 * @return el estado
	 */
	public char getEstado() {
		return estado;
	}
	
	/**
	 * Establece el estado.
	 *
	 * @param estado el nuevo estado
	 */
	public void setEstado(char estado) {
		this.estado = estado;
	}
	
	/**
	 * Obtiene el descripcion.
	 *
	 * @return el descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Establece el descripcion.
	 *
	 * @param pDescripcion el nuevo descripcion
	 */
	public void setDescripcion(String pDescripcion) {
		this.descripcion = pDescripcion;
	}
	
	/**
	 * Obtiene el codigo.
	 *
	 * @return el codigo
	 */
	public String getCodigo() {
		return codigo;
	}
	
	/**
	 * Establece el codigo.
	 *
	 * @param codigo el nuevo codigo
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	

}
