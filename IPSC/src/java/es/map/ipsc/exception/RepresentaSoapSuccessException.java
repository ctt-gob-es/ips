package es.map.ipsc.exception;

import org.apache.axis.AxisFault;

/**
 * El Class RepresentaSoapSuccessException.
 */
public class RepresentaSoapSuccessException extends AxisFault {

	/** La constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** el id representa. */
	private String idRepresenta;
	
	/** el pertenece. */
	private Boolean pertenece;
	
	/** el estado. */
	private String estado;
	
	/** el codigo. */
	private String codigo;
	
	/** el descripcion. */
	private String descripcion;
	
	/**
	 * Crea una nueva representa soap success exception.
	 */
	public RepresentaSoapSuccessException(){
		super();
	}
	
	/**
	 * Obtiene el id representa.
	 *
	 * @return el id representa
	 */
	public String getIdRepresenta() {
		return idRepresenta;
	}
	
	/**
	 * Establece el id representa.
	 *
	 * @param idRepresenta el nuevo id representa
	 */
	public void setIdRepresenta(String idRepresenta) {
		this.idRepresenta = idRepresenta;
	}
	
	/**
	 * Obtiene el pertenece.
	 *
	 * @return el pertenece
	 */
	public Boolean getPertenece() {
		return pertenece;
	}
	
	/**
	 * Establece el pertenece.
	 *
	 * @param pertenece el nuevo pertenece
	 */
	public void setPertenece(Boolean pertenece) {
		this.pertenece = pertenece;
	}
	
	/**
	 * Obtiene el estado.
	 *
	 * @return el estado
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * Establece el estado.
	 *
	 * @param estado el nuevo estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
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
}
