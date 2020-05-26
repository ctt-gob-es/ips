package es.map.ipsg.bean;


/**
 * CategoriaBean.
 */
public class CategoriaBean {
	
	/** el id. */
	private Integer id;
	
	/** el codigo. */
	private String codigo;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el estado. */
	private Character estado;
	
	/** el eliminar. */
	private boolean eliminar;
	
	/** el modificar. */
	private boolean modificar;
	
	/** el detalle. */
	private boolean detalle;
	
	/** el num total. */
	private int numTotal;
	
	/** el visibilidad. */
	private Boolean visibilidad;
	
	/** el num registros. */
	private String numRegistros;
	

	/**
	 * Obtiene el num registros.
	 *
	 * @return numRegistros String
	 */
	public String getNumRegistros() {
		return numRegistros;
	}

	/**
	 * Establece el num registros.
	 *
	 * @param numRegistros String
	 */
	public void setNumRegistros(String numRegistros) {
		this.numRegistros = numRegistros;
	}
	
	/**
	 * Obtiene el id.
	 *
	 * @return id Integer
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el descripcion.
	 *
	 * @return descripcion String
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Establece el descripcion.
	 *
	 * @param descripcion String
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Obtiene el estado.
	 *
	 * @return estado Character
	 */
	public Character getEstado() {
		return estado;
	}
	
	/**
	 * Establece el estado.
	 *
	 * @param estado Character
	 */
	public void setEstado(Character estado) {
		this.estado = estado;
	}
	
	/**
	 * Comprueba si es modificar.
	 *
	 * @return modificar boolean
	 */
	public boolean isModificar() {
		return modificar;
	}

	/**
	 * Establece el modificar.
	 *
	 * @param modificar boolean
	 */
	public void setModificar(boolean modificar) {
		this.modificar = modificar;
	}

	/**
	 * Comprueba si es detalle.
	 *
	 * @return detalle boolean
	 */
	public boolean isDetalle() {
		return detalle;
	}

	/**
	 * Establece el detalle.
	 *
	 * @param detalle boolean
	 */
	public void setDetalle(boolean detalle) {
		this.detalle = detalle;
	}

	/**
	 * Comprueba si es eliminar.
	 *
	 * @return eliminar boolean
	 */
	public boolean isEliminar() {
		return eliminar;
	}

	/**
	 * Establece el eliminar.
	 *
	 * @param eliminar boolean
	 */
	public void setEliminar(boolean eliminar) {
		this.eliminar = eliminar;
	}
	
	/**
	 * Obtiene el num total.
	 *
	 * @return  numTotal int
	 */
	public int getNumTotal() {
		return numTotal;
	}

	/**
	 * Establece el num total.
	 *
	 * @param numTotal int
	 */
	public void setNumTotal(int numTotal) {
		this.numTotal = numTotal;
	}

	/**
	 * Obtiene el codigo.
	 *
	 * @return codigo String
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Establece el codigo.
	 *
	 * @param codigo String
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	/**
	 * Obtiene el visibilidad.
	 *
	 * @return el visibilidad
	 */
	public Boolean getVisibilidad() {
		return visibilidad;
	}

	/**
	 * Establece el visibilidad.
	 *
	 * @param visibilidad el nuevo visibilidad
	 */
	public void setVisibilidad(Boolean visibilidad) {
		this.visibilidad = visibilidad;
	}
	

}
