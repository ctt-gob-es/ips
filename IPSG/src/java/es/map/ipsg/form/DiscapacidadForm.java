package es.map.ipsg.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;

/**
 * El Class DiscapacidadForm.
 */
public class DiscapacidadForm extends SpringForm {

	/** el id. */
	private String id;
	
	/** el centro gestor. */
	private String centroGestor;
	
	/** el ds centro gestor. */
	private String dsCentroGestor;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el submit. */
	private String submit;
	
	/** el centro. */
	private String centro;
	
	/** el parametro. */
	private String parametro;
	
	/** el parametro 2. */
	private String parametro2;
	
	/** el campo. */
	private String campo;
	
	/** el pagina actual. */
	private String paginaActual;
	
	/** el paginas totales. */
	private String paginasTotales;
	
	/** el direccion. */
	private String direccion;
	
	/** el num registro. */
	private String numRegistro;
	
	/** el modificar. */
	public boolean modificar;
	
	/** el eliminar. */
	public boolean eliminar;
	
	/** el forma acceso. */
	public String formaAcceso;
	
	/** el id centro gestor. */
	private String idCentroGestor;
	
	/** el accion. */
	private String accion;
	
	/** el visibilidad. */
	private Boolean visibilidad;
	
	/** el listar todos. */
	private String listarTodos;

	/** el numero pagina ir. */
	private Integer numeroPaginaIr;
	
	/** el pulsa ir. */
	private boolean pulsaIr;

	/** el mensaje. */
	private String mensaje;
	
	/** el cambios. */
	private String cambios;

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DiscapacidadForm.class);



	/**
	 * Obtiene el id centro gestor.
	 *
	 * @return the idCentroGestor
	 */
	public String getIdCentroGestor() {
		return idCentroGestor;
	}

	/**
	 * Establece el id centro gestor.
	 *
	 * @param idCentroGestor the idCentroGestor to set
	 */
	public void setIdCentroGestor(String idCentroGestor) {
		this.idCentroGestor = idCentroGestor;
	}

	/**
	 * Obtiene el mensaje.
	 *
	 * @return el mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Establece el mensaje.
	 *
	 * @param mensaje el nuevo mensaje
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
	 * Obtiene el num registro.
	 *
	 * @return el num registro
	 */
	public String getNumRegistro() {
		return numRegistro;
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
	 * Obtiene el parametro.
	 *
	 * @return el parametro
	 */
	public String getParametro() {
		return parametro;
	}
	
	/**
	 * Establece el parametro.
	 *
	 * @param parametro el nuevo parametro
	 */
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	/**
	 * Obtiene el centro.
	 *
	 * @return el centro
	 */
	public String getCentro() {
		return centro;
	}
	
	/**
	 * Establece el centro.
	 *
	 * @param centro el nuevo centro
	 */
	public void setCentro(String centro) {
		this.centro = centro;
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
	 * Obtiene el centro gestor.
	 *
	 * @return el centro gestor
	 */
	public String getCentroGestor() {
		return centroGestor;
	}
	
	/**
	 * Establece el centro gestor.
	 *
	 * @param centroGestor el nuevo centro gestor
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
	 * Obtiene el submit.
	 *
	 * @return el submit
	 */
	public String getSubmit() {
		return submit;
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
	 * Obtiene el campo.
	 *
	 * @return el campo
	 */
	public String getCampo() {
		return campo;
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
	 * Obtiene el pagina actual.
	 *
	 * @return el pagina actual
	 */
	public String getPaginaActual() {
		return paginaActual;
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
	 * Obtiene el paginas totales.
	 *
	 * @return el paginas totales
	 */
	public String getPaginasTotales() {
		return paginasTotales;
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
	 * Obtiene el direccion.
	 *
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Establece el direccion.
	 *
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
	 * Obtiene el forma acceso.
	 *
	 * @return el forma acceso
	 */
	public String getFormaAcceso() {
		return formaAcceso;
	}

	/**
	 * Establece el forma acceso.
	 *
	 * @param formaAcceso el nuevo forma acceso
	 */
	public void setFormaAcceso(String formaAcceso) {
		this.formaAcceso = formaAcceso;
	}
	
	/**
	 * Obtiene el accion.
	 *
	 * @return the accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * Establece el accion.
	 *
	 * @param accion the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}
	
	

	/**
	 * Obtiene el ds centro gestor.
	 *
	 * @return el ds centro gestor
	 */
	public String getDsCentroGestor() {
		return dsCentroGestor;
	}

	/**
	 * Establece el ds centro gestor.
	 *
	 * @param dsCentroGestor el nuevo ds centro gestor
	 */
	public void setDsCentroGestor(String dsCentroGestor) {
		this.dsCentroGestor = dsCentroGestor;
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	public SpringErrors validate(SpringMapping mapping,	HttpServletRequest request) {
		logger.info("Validando");
		SpringErrors SpringErrors = new SpringErrors();
		
		if("GUARDAR".equalsIgnoreCase(accion) || "MODIFICAR".equalsIgnoreCase(accion)){
			
			validateAux(SpringErrors,request);		
			
			if(centroGestor == null || centroGestor.equalsIgnoreCase("")){
				request.setAttribute("erroridCentroGestor", "erroridCentroGestor");
				SpringErrors.add("dsErroridCentroGestor", new SpringMessage("field.cuerpoEscala.errores.centroGestorRequerido"));
			}
			
		}
		
		return SpringErrors;
	}
	
	/**
	 * Validate aux.
	 *
	 * @param SpringErrors el spring errors
	 * @param request el request
	 */
	public void validateAux(SpringErrors SpringErrors, HttpServletRequest request) {
		if(descripcion == null || descripcion.equalsIgnoreCase("")){
			request.setAttribute("errorDescripcion", "errorDescripcion");
			SpringErrors.add("dsErrorDescripcion", new SpringMessage("field.cuerpoEscala.errores.descripcionRequerido"));
		}
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Obtiene el parametro 2.
	 *
	 * @return el parametro 2
	 */
	public String getParametro2() {
		return parametro2;
	}

	/**
	 * Establece el parametro 2.
	 *
	 * @param parametro2 el nuevo parametro 2
	 */
	public void setParametro2(String parametro2) {
		this.parametro2 = parametro2;
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

	/**
	 * Obtiene el listar todos.
	 *
	 * @return el listar todos
	 */
	public String getListarTodos() {
		return listarTodos;
	}

	/**
	 * Establece el listar todos.
	 *
	 * @param listarTodos el nuevo listar todos
	 */
	public void setListarTodos(String listarTodos) {
		this.listarTodos = listarTodos;
	}
	
}
