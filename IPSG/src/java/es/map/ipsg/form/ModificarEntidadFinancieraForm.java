package es.map.ipsg.form;

import javax.servlet.http.HttpServletRequest;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;

/**
 * El Class ModificarEntidadFinancieraForm.
 */
public class ModificarEntidadFinancieraForm extends SpringForm {
	
	/** el id. */
	private Integer id;
	
	/** el codigo. */
	private String codigo;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el actualizada. */
	private Character actualizada;
	
	/** el id tipo pago. */
	private Integer idTipoPago;
	
	/** el des tipo pago. */
	private String desTipoPago;
	
	/** el estado. */
	private Character estado;
	
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
	}
	
	/**
	 * Establece el actualizada.
	 *
	 * @param actualizada el nuevo actualizada
	 */
	public void setActualizada(Character actualizada) {
		this.actualizada = actualizada;		
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
		
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	public SpringErrors validate(SpringMapping mapping,	HttpServletRequest request) {
		SpringErrors SpringErrors = new SpringErrors();
		
		if(codigo == null || codigo.equalsIgnoreCase("")) {
			request.setAttribute("errorCodigo", "errorCodigo");
			SpringErrors.add("dsErrorCodigo", new SpringMessage("field.entidadFinanciera.errores.codigoRequerido"));
		}			
			
		if(descripcion == null || descripcion.equalsIgnoreCase("")){				
			request.setAttribute("errorDescripcion", "errorDescripcion");
			SpringErrors.add("dsErrorDescripcion", new SpringMessage("field.entidadFinanciera.errores.descripcionRequerido"));
		}
			
		if(idTipoPago == null || idTipoPago == 0){
			request.setAttribute("errorIdTipoPago", "errorIdTipoPago");
			SpringErrors.add("dsErrorIdTipoPago", new SpringMessage("field.entidadFinanciera.errores.tipoPagoRequerido"));
		}
		
		if(estado == null || estado.equals(' ')) {
			request.setAttribute("errorEstado", "errorEstado");
			SpringErrors.add("dsErrorEstado", new SpringMessage("field.entidadFinanciera.errores.estadoRequerido"));
		}		
		
		return SpringErrors;
	}
	
}
