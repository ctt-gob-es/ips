package es.map.ipsg.bean;

/**
 * El Class TipoPagoBean.
 */
public class TipoPagoBean {

	/** el id. */
	private Integer id;
	
	/** el codigo. */
	private Character codigo;
	
	/** el descripcion. */
	private String descripcion;

	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public Integer getId() {
		return this.id;
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
	 * Obtiene el codigo.
	 *
	 * @return el codigo
	 */
	public Character getCodigo() {
		return this.codigo;
	}

	/**
	 * Establece el codigo.
	 *
	 * @param codigo el nuevo codigo
	 */
	public void setCodigo(Character codigo) {
		this.codigo = codigo;
	}

	/**
	 * Establece el descripcion.
	 *
	 * @param descripcion el nuevo descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Obtiene el descripcion.
	 *
	 * @return el descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

}
