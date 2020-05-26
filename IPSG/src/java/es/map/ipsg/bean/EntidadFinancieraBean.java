package es.map.ipsg.bean;

/**
 * El Class EntidadFinancieraBean.
 */
public class EntidadFinancieraBean {
	
	/** el id. */
	private Integer id;
	
	/** el codigo. */
	private String codigo;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el actualizada. */
	private Character actualizada;
	
	/** el des actualizada. */
	private String desActualizada;
	
	/** el id tipo pago. */
	private Integer idTipoPago;
	
	/** el des tipo pago. */
	private String desTipoPago;	
	
	/** el estado. */
	private Character estado;
	
	/** el des estado. */
	private String desEstado;
	private String apertura;
	private String cierre;
	 
	/** el num total. */
	private int numTotal;	
	
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
	 * Obtiene el estado.
	 *
	 * @return el estado
	 */
	public Character getEstado() {
		return estado;
	}
	
	/**
	 * Establece el estado.
	 *
	 * @param estado el nuevo estado
	 */
	public void setEstado(Character estado) {
		this.estado = estado;
		if (estado != null) {
			if ("A".compareTo(estado.toString()) == 0) {
				setDesEstado("ACTIVO");
			} else if ("D".compareTo(estado.toString()) == 0) {
				setDesEstado("DESACTIVADO");
			} else {
				setDesEstado("");
			}
		} else {
			setDesEstado("");
		}
	}
	
	/**
	 * Establece el des estado.
	 *
	 * @param desEstado el nuevo des estado
	 */
	public void setDesEstado(String desEstado) {
		this.desEstado = desEstado;
	}
	
	/**
	 * Obtiene el des estado.
	 *
	 * @return el des estado
	 */
	public String getDesEstado() {
		return desEstado;
	}		

	/**
	 * Establece el actualizada.
	 *
	 * @param actualizada el nuevo actualizada
	 */
	public void setActualizada(Character actualizada) {
		this.actualizada = actualizada;
		if (actualizada != null) {		
			if ("S".compareTo(actualizada.toString()) == 0) {
				setDesActualizada("SI");
			} else if ("N".compareTo(actualizada.toString()) == 0) {
				setDesActualizada("NO");
			} else {
				setDesActualizada("");
			}
		} else {
			setDesActualizada("");
		}				
	}
	
	/**
	 * Obtiene el actualizada.
	 *
	 * @return el actualizada
	 */
	public Character getActualizada() {
		return actualizada;
	}
	
	/**
	 * Establece el des actualizada.
	 *
	 * @param desActualizada el nuevo des actualizada
	 */
	public void setDesActualizada(String desActualizada) {
		this.desActualizada = desActualizada;
	}
	
	/**
	 * Obtiene el des actualizada.
	 *
	 * @return el des actualizada
	 */
	public String getDesActualizada() {
		return desActualizada;
	}	
	
	/**
	 * Establece el id tipo pago.
	 *
	 * @param idTipoPago el nuevo id tipo pago
	 */
	public void setIdTipoPago(Integer idTipoPago) {
		this.idTipoPago = idTipoPago;
	}
	
	/**
	 * Obtiene el id tipo pago.
	 *
	 * @return el id tipo pago
	 */
	public Integer getIdTipoPago() {
		return idTipoPago;
	}	
	
	/**
	 * Establece el des tipo pago.
	 *
	 * @param desTipoPago el nuevo des tipo pago
	 */
	public void setDesTipoPago(String desTipoPago) {
		this.desTipoPago = desTipoPago;
	}
	
	/**
	 * Obtiene el des tipo pago.
	 *
	 * @return el des tipo pago
	 */
	public String getDesTipoPago() {
		return desTipoPago;
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
	 * Obtiene el num total.
	 *
	 * @return el num total
	 */
	public int getNumTotal() {
		return numTotal;
	}
	public String getApertura() {
		return apertura;
	}
	public void setApertura(String apertura) {
		this.apertura = apertura;
	}
	public String getCierre() {
		return cierre;
	}
	public void setCierre(String cierre) {
		this.cierre = cierre;
	}
	
}
