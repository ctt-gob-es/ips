package es.map.ipsg.bean;


/**
 * TipoSolicitudBean.
 *
 * @author amartinl
 */
public class TipoSolicitudBean {
	
	/** el id. */
	private Integer id;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el codigo. */
	private Character codigo;
	
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
	 * Obtiene el codigo.
	 *
	 * @return codigo Character
	 */
	public Character getCodigo() {
		return codigo;
	}
	
	/**
	 * Establece el codigo.
	 *
	 * @param codigo Character
	 */
	public void setCodigo(Character codigo) {
		this.codigo = codigo;
	}
	

}
