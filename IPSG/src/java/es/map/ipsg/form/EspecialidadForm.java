package es.map.ipsg.form;

import javax.servlet.http.HttpServletRequest;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;

/**
 * El Class EspecialidadForm.
 */
public class EspecialidadForm extends SpringForm {
	
	/** el id. */
	private String id;
	
	/** el codigo. */
	private String codigo;
	
	/** el cuerpo escala. */
	private String cuerpoEscala;
	
	/** el ds cuerpo escala. */
	private String dsCuerpoEscala;
	
	/** el id cuerpo escala. */
	private Integer idCuerpoEscala;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el accion. */
	private String accion;
	
	/** el estado. */
	private String estado;
	
	/** el centro gestor. */
	private String centroGestor;	
	
	/** el ds centro gestor. */
	private String dsCentroGestor;
	
	/** el parametro. */
	private String parametro;
	
	/** el parametro 2. */
	private String parametro2;
	
	/** el centro. */
	private String centro;
	
	/** el listar todos. */
	private String listarTodos;
	

	/** el submit. */
	private String submit;
	
	/** el campo. */
	private String campo;
	
	/** el direccion. */
	private String direccion;
	
	/** el cambios. */
	private String cambios;
	
	/** el pagina actual. */
	private String paginaActual;
	
	/** el paginas totales. */
	private String paginasTotales;
	
	/** el num registro. */
	private String numRegistro;
	
	/** el visibilidad. */
	private Boolean visibilidad;
	
	/** el numero pagina ir. */
	private Integer numeroPaginaIr;
	
	/** el pulsa ir. */
	private boolean pulsaIr;
	
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
	 * Obtiene el direccion.
	 *
	 * @return el direccion
	 */
	public String getDireccion() {
		return direccion;
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
	 * Obtiene el cuerpo escala.
	 *
	 * @return el cuerpo escala
	 */
	public String getCuerpoEscala() {
		return cuerpoEscala;
	}

	/**
	 * Establece el cuerpo escala.
	 *
	 * @param cuerpoEscala el nuevo cuerpo escala
	 */
	public void setCuerpoEscala(String cuerpoEscala) {
		this.cuerpoEscala = cuerpoEscala;
	}

	/**
	 * Obtiene el id cuerpo escala.
	 *
	 * @return the idCuerpoEscala
	 */
	public Integer getIdCuerpoEscala() {
		return idCuerpoEscala;
	}
	
	/**
	 * Establece el id cuerpo escala.
	 *
	 * @param idCuerpoEscala the idCuerpoEscala to set
	 */
	public void setIdCuerpoEscala(Integer idCuerpoEscala) {
		this.idCuerpoEscala = idCuerpoEscala;
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
	 * Obtiene el estado.
	 *
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}
	
	/**
	 * Establece el estado.
	 *
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	public SpringErrors validate(SpringMapping mapping,	HttpServletRequest request) {
		SpringErrors SpringErrors = new SpringErrors();
		
		if("GUARDAR".equalsIgnoreCase(accion) || "MODIFICAR".equalsIgnoreCase(accion)){
			if(descripcion == null || descripcion.equalsIgnoreCase("")){
				
				request.setAttribute("errorDescripcion", "errorDescripcion");
				SpringErrors.add("dsErrorDescripcion", new SpringMessage("field.especialidad.errores.descripcionRequerido"));
			}
			
			if(idCuerpoEscala == null || idCuerpoEscala == 0){
				request.setAttribute("errorIdCuerpoEscala", "errorIdCuerpoEscala");
				SpringErrors.add("dsErrorIdCuerpoEscala", new SpringMessage("field.especialidad.errores.cuerpoEscalaRequerido"));
			}			
		}
		
		return SpringErrors;
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
	 * Obtiene el ds cuerpo escala.
	 *
	 * @return el ds cuerpo escala
	 */
	public String getDsCuerpoEscala() {
		return dsCuerpoEscala;
	}
	
	/**
	 * Establece el ds cuerpo escala.
	 *
	 * @param dsCuerpoEscala el nuevo ds cuerpo escala
	 */
	public void setDsCuerpoEscala(String dsCuerpoEscala) {
		this.dsCuerpoEscala = dsCuerpoEscala;
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
