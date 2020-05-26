package es.map.ipsc.bean;


/**
 * El Class OtrosTitulosBean.
 */
public class OtrosTitulosBean {
	
	/** el id. */
	private Integer id;
	
	/** el centro gestor. */
	private String centroGestor;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el eliminar. */
	private boolean eliminar;
	
	/** el modificar. */
	private boolean modificar;
	
	/** el detalle. */
	private boolean detalle;
	
	/** el num total. */
	private int numTotal;
	
	/** el num registros. */
	private String numRegistros;
	
	/** el id centro gestor. */
	private Integer idCentroGestor;
	
	/** el des centro gestor. */
	private String desCentroGestor;
	
	/** el des grupo. */
	private String desGrupo;
	
	/** el visibilidad. */
	private Boolean visibilidad;
	



	/**
	 * Obtiene el num registros.
	 *
	 * @return el num registros
	 */
	public String getNumRegistros() {
		return numRegistros;
	}

	/**
	 * Establece el num registros.
	 *
	 * @param numRegistros el nuevo num registros
	 */
	public void setNumRegistros(String numRegistros) {
		this.numRegistros = numRegistros;
	}
	
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
	 * Obtiene el centro gestor.
	 *
	 * @return the centroGestor
	 */
	public String getCentroGestor() {
		return centroGestor;
	}
	
	/**
	 * Establece el centro gestor.
	 *
	 * @param centroGestor the centroGestor to set
	 */
	public void setCentroGestor(String centroGestor) {
		this.centroGestor = centroGestor;
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
	 * @param descripcion el nuevo descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	/**
	 * Comprueba si es modificar.
	 *
	 * @return verdadero, si es modificar
	 */
	public boolean isModificar() {
		return modificar;
	}

	/**
	 * Establece el modificar.
	 *
	 * @param modificar el nuevo modificar
	 */
	public void setModificar(boolean modificar) {
		this.modificar = modificar;
	}

	/**
	 * Comprueba si es detalle.
	 *
	 * @return verdadero, si es detalle
	 */
	public boolean isDetalle() {
		return detalle;
	}

	/**
	 * Establece el detalle.
	 *
	 * @param detalle el nuevo detalle
	 */
	public void setDetalle(boolean detalle) {
		this.detalle = detalle;
	}

	/**
	 * Comprueba si es eliminar.
	 *
	 * @return verdadero, si es eliminar
	 */
	public boolean isEliminar() {
		return eliminar;
	}

	/**
	 * Establece el eliminar.
	 *
	 * @param eliminar el nuevo eliminar
	 */
	public void setEliminar(boolean eliminar) {
		this.eliminar = eliminar;
	}
	
	/**
	 * Obtiene el num total.
	 *
	 * @return el num total
	 */
	public int getNumTotal() {
		return numTotal;
	}

	/**
	 * Establece el num total.
	 *
	 * @param numTotal el nuevo num total
	 */
	public void setNumTotal(int numTotal) {
		this.numTotal = numTotal;
	}

	/**
	 * Obtiene el id centro gestor.
	 *
	 * @return the idCentroGestor
	 */
	public Integer getIdCentroGestor() {
		return idCentroGestor;
	}

	/**
	 * Obtiene el des centro gestor.
	 *
	 * @return the desCentroGestor
	 */
	public String getDesCentroGestor() {
		return desCentroGestor;
	}

	/**
	 * Establece el des centro gestor.
	 *
	 * @param desCentroGestor the desCentroGestor to set
	 */
	public void setDesCentroGestor(String desCentroGestor) {
		this.desCentroGestor = desCentroGestor;
	}

	/**
	 * Obtiene el des grupo.
	 *
	 * @return the desGrupo
	 */
	public String getDesGrupo() {
		return desGrupo;
	}

	/**
	 * Establece el des grupo.
	 *
	 * @param desGrupo the desGrupo to set
	 */
	public void setDesGrupo(String desGrupo) {
		this.desGrupo = desGrupo;
	}

	/**
	 * Establece el id centro gestor.
	 *
	 * @param idCentroGestor the idCentroGestor to set
	 */
	public void setIdCentroGestor(Integer idCentroGestor) {
		this.idCentroGestor = idCentroGestor;
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
