package es.map.ipsg.form;

import javax.servlet.http.HttpServletRequest;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;

/**
 * El Class BuscarEntidadFinancieraForm.
 */
public class BuscarEntidadFinancieraForm extends SpringForm {
	
	/** el id. */
	private String id;
	
	/** el codigo. */
	private String codigo;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el actualizada. */
	private Character actualizada;
	
	/** el id tipo pago. */
	private Integer idTipoPago;
	
	/** el estado. */
	private String estado;
	
	/** el submit. */
	private String submit;	
	
	/** el accion. */
	private String accion;
	
	/** el cambios. */
	private String cambios;
	
	/** el pagina actual. */
	private String paginaActual;	
	
	/** el paginas totales. */
	private String paginasTotales;
	
	/** el campo. */
	private String campo;	
	
	/** el direccion. */
	private String direccion;
	
	/** el num registro. */
	private String numRegistro;
	
	/** el eliminar. */
	private boolean eliminar;	
	
	/** el modificar. */
	private boolean modificar;	
	
	/** el actualizacion. */
	private boolean actualizacion;	
	
	/** el numero pagina ir. */
	private Integer numeroPaginaIr;
	
	/** el pulsa ir. */
	private boolean pulsaIr;
	
	
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
	 * Obtiene el cambios.
	 *
	 * @return el cambios
	 */
	public String getCambios() {
		return cambios;
	}
	
	/**
	 * Establece el cambios.
	 *
	 * @param cambios el nuevo cambios
	 */
	public void setCambios(String cambios) {
		this.cambios = cambios;
	}
	
	/**
	 * Obtiene el id.
	 *
	 * @return el id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id el nuevo id
	 */
	public void setId(String id) {
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
		return (descripcion!=null ? descripcion.trim() : null);
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
	 * Obtiene el accion.
	 *
	 * @return el accion
	 */
	public String getAccion() {
		return accion;
	}
	
	/**
	 * Establece el accion.
	 *
	 * @param accion el nuevo accion
	 */
	public void setAccion(String accion) {
		this.accion = accion;
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
	 * Establece el submit.
	 *
	 * @param submit el nuevo submit
	 */
	public void setSubmit(String submit) {
		this.submit = submit;
	}
	
	/**
	 * Obtiene el submit.
	 *
	 * @return el submit
	 */
	public String getSubmit() {
		return submit;
	}
	
	/**
	 * Establece el pagina actual.
	 *
	 * @param paginaActual el nuevo pagina actual
	 */
	public void setPaginaActual(String paginaActual) {
		this.paginaActual = paginaActual;
	}
	
	/**
	 * Obtiene el pagina actual.
	 *
	 * @return el pagina actual
	 */
	public String getPaginaActual() {
		return paginaActual;
	}
	
	/**
	 * Establece el campo.
	 *
	 * @param campo el nuevo campo
	 */
	public void setCampo(String campo) {
		this.campo = campo;
	}
	
	/**
	 * Obtiene el campo.
	 *
	 * @return el campo
	 */
	public String getCampo() {
		return campo;
	}
	
	/**
	 * Establece el direccion.
	 *
	 * @param direccion el nuevo direccion
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	/**
	 * Obtiene el direccion.
	 *
	 * @return el direccion
	 */
	public String getDireccion() {
		return direccion;
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
	 * Obtiene el num registro.
	 *
	 * @return el num registro
	 */
	public String getNumRegistro() {
		return numRegistro;
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
	 * Comprueba si es eliminar.
	 *
	 * @return verdadero, si es eliminar
	 */
	public boolean isEliminar() {
		return eliminar;
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
	 * Comprueba si es modificar.
	 *
	 * @return verdadero, si es modificar
	 */
	public boolean isModificar() {
		return modificar;
	}
	
	/**
	 * Establece el actualizacion.
	 *
	 * @param actualizacion el nuevo actualizacion
	 */
	public void setActualizacion(boolean actualizacion) {
		this.actualizacion = actualizacion;
	}
	
	/**
	 * Comprueba si es actualizacion.
	 *
	 * @return verdadero, si es actualizacion
	 */
	public boolean isActualizacion() {
		return actualizacion;
	}
	
	/**
	 * Establece el paginas totales.
	 *
	 * @param paginasTotales el nuevo paginas totales
	 */
	public void setPaginasTotales(String paginasTotales) {
		this.paginasTotales = paginasTotales;
	}
	
	/**
	 * Obtiene el paginas totales.
	 *
	 * @return el paginas totales
	 */
	public String getPaginasTotales() {
		return paginasTotales;
	}
		
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	public SpringErrors validate(SpringMapping mapping,	HttpServletRequest request) {
		SpringErrors SpringErrors = new SpringErrors();
		
		if("GUARDAR".equalsIgnoreCase(accion) || "MODIFICAR".equalsIgnoreCase(accion)){
			
			if(codigo == null || codigo.equalsIgnoreCase("")){			
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
			
		}
		
		return SpringErrors;
	}
	
	/**
	 * Obtiene el numero pagina ir.
	 *
	 * @return el numero pagina ir
	 */
	public Integer getNumeroPaginaIr() {
		return numeroPaginaIr;
	}
	
	/**
	 * Establece el numero pagina ir.
	 *
	 * @param numeroPaginaIr el nuevo numero pagina ir
	 */
	public void setNumeroPaginaIr(Integer numeroPaginaIr) {
		this.numeroPaginaIr = numeroPaginaIr;
	}
	
	/**
	 * Comprueba si es pulsa ir.
	 *
	 * @return verdadero, si es pulsa ir
	 */
	public boolean isPulsaIr() {
		return pulsaIr;
	}
	
	/**
	 * Establece el pulsa ir.
	 *
	 * @param pulsaIr el nuevo pulsa ir
	 */
	public void setPulsaIr(boolean pulsaIr) {
		this.pulsaIr = pulsaIr;
	}

}
