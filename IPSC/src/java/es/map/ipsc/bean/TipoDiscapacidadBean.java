package es.map.ipsc.bean;

/**
 * El Class TipoDiscapacidadBean.
 */
public class TipoDiscapacidadBean{

	/** el id. */
	private Integer id;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el Codigo. */
	private char Codigo;


	

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
	 * Obtiene el descripcion.
	 *
	 * @return el descripcion
	 */
	public String getDescripcion() {
		return this.descripcion;
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
	public char getCodigo() {
		return Codigo;
	}

	/**
	 * Establece el codigo.
	 *
	 * @param codigo el nuevo codigo
	 */
	public void setCodigo(char codigo) {
		Codigo = codigo;
	}

}
