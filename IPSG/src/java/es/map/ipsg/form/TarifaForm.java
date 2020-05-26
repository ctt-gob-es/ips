package es.map.ipsg.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.model.Grupo;
import es.map.ips.model.TipoAcceso;
import es.map.ipsg.util.Validacion;

/**
 * El Class TarifaForm.
 */
public class TarifaForm extends SpringForm {
	
	/** el id. */
	private Integer id;
	
	/** el grupo. */
	private Grupo grupo;
	
	/** el tipo acceso. */
	private TipoAcceso tipoAcceso;
	
	/** el importe. */
	//El campo Importe has sido cambiado a String para evitar el formateo automático a 0.0 cuando se introduce un caracter
	private String importe;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el ejercicio. */
	private String ejercicio;
	
	/** el estado. */
	private Character estado;
	
	/** el id grupo. */
	private String idGrupo;
	
	/** el id categoria. */
	private String idCategoria;
	
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
	
	/** el accion. */
	private String accion;
	
	/** el submit. */
	private String submit;
	
	/** el cambios. */
	private String cambios;
	
	/** el id tipo acceso. */
	private String idTipoAcceso;
	
	/** el numero pagina ir. */
	private Integer numeroPaginaIr;
	
	/** el pulsa ir. */
	private boolean pulsaIr;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CuerpoEscalaForm.class);	
	
	/** La constante STRING_ERROREJERCICIO. */
	private static final String STRING_ERROREJERCICIO = "errorEjercicio";
	
	/** La constante STRING_DSERROREJERCICIO. */
	private static final String STRING_DSERROREJERCICIO = "dsErrorEjercicio";
	
	/** La constante STRING_ERRORIMPORTE. */
	private static final String STRING_ERRORIMPORTE = "errorImporte";
	
	/** La constante STRING_DSERRORIMPORTE. */
	private static final String STRING_DSERRORIMPORTE = "dsErrorImporte";
	
	/**
	 * Obtiene el id grupo.
	 *
	 * @return the idGrupo
	 */
	public String getIdGrupo() {
		return idGrupo;
	}
	
	/**
	 * Obtiene el id categoria.
	 *
	 * @return the idCategoria
	 */
	public String getIdCategoria() {
		return idCategoria;
	}
	
	/**
	 * Obtiene el campo.
	 *
	 * @return the campo
	 */
	public String getCampo() {
		return campo;
	}
	
	/**
	 * Obtiene el pagina actual.
	 *
	 * @return the paginaActual
	 */
	public String getPaginaActual() {
		return paginaActual;
	}
	
	/**
	 * Obtiene el paginas totales.
	 *
	 * @return the paginasTotales
	 */
	public String getPaginasTotales() {
		return paginasTotales;
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
	 * Obtiene el num registro.
	 *
	 * @return the numRegistro
	 */
	public String getNumRegistro() {
		return numRegistro;
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
	 * Obtiene el submit.
	 *
	 * @return the submit
	 */
	public String getSubmit() {
		return submit;
	}
	
	/**
	 * Establece el id grupo.
	 *
	 * @param idGrupo the idGrupo to set
	 */
	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}
	
	/**
	 * Establece el id categoria.
	 *
	 * @param idCategoria the idCategoria to set
	 */
	public void setIdCategoria(String idCategoria) {
		this.idCategoria = idCategoria;
	}
	
	/**
	 * Establece el campo.
	 *
	 * @param campo the campo to set
	 */
	public void setCampo(String campo) {
		this.campo = campo;
	}
	
	/**
	 * Establece el pagina actual.
	 *
	 * @param paginaActual the paginaActual to set
	 */
	public void setPaginaActual(String paginaActual) {
		this.paginaActual = paginaActual;
	}
	
	/**
	 * Establece el paginas totales.
	 *
	 * @param paginasTotales the paginasTotales to set
	 */
	public void setPaginasTotales(String paginasTotales) {
		this.paginasTotales = paginasTotales;
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
	 * Establece el num registro.
	 *
	 * @param numRegistro the numRegistro to set
	 */
	public void setNumRegistro(String numRegistro) {
		this.numRegistro = numRegistro;
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
	 * Establece el submit.
	 *
	 * @param submit the submit to set
	 */
	public void setSubmit(String submit) {
		this.submit = submit;
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
	 * Obtiene el grupo.
	 *
	 * @return el grupo
	 */
	public Grupo getGrupo() {
		return grupo;
	}
	
	/**
	 * Establece el grupo.
	 *
	 * @param grupo el nuevo grupo
	 */
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	/**
	 * Obtiene el tipo acceso.
	 *
	 * @return el tipo acceso
	 */
	public TipoAcceso getTipoAcceso() {
		return tipoAcceso;
	}
	
	/**
	 * Establece el tipo acceso.
	 *
	 * @param tipoAcceso el nuevo tipo acceso
	 */
	public void setTipoAcceso(TipoAcceso tipoAcceso) {
		this.tipoAcceso = tipoAcceso;
	}
	
	/**
	 * Obtiene el importe.
	 *
	 * @return el importe
	 */
	public String getImporte() {
		return importe;
	}
	
	/**
	 * Establece el importe.
	 *
	 * @param importe el nuevo importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
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
	 * Obtiene el ejercicio.
	 *
	 * @return el ejercicio
	 */
	public String getEjercicio() {
		return ejercicio;
	}
	
	/**
	 * Establece el ejercicio.
	 *
	 * @param ejercicio el nuevo ejercicio
	 */
	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
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
	 * Obtiene el cambios.
	 *
	 * @return the cambios
	 */
	public String getCambios() {
		return cambios;
	}
	
	/**
	 * Establece el cambios.
	 *
	 * @param cambios the cambios to set
	 */
	public void setCambios(String cambios) {
		this.cambios = cambios;
	}
	
	/**
	 * Obtiene el id tipo acceso.
	 *
	 * @return the idTipoAcceso
	 */
	public String getIdTipoAcceso() {
		return idTipoAcceso;
	}
	
	/**
	 * Establece el id tipo acceso.
	 *
	 * @param idTipoAcceso the idTipoAcceso to set
	 */
	public void setIdTipoAcceso(String idTipoAcceso) {
		this.idTipoAcceso = idTipoAcceso;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	public SpringErrors validate(SpringMapping mapping,	HttpServletRequest request) {
		logger.info("Validando");
		SpringErrors SpringErrors = new SpringErrors();
		Validacion validacion = new Validacion();
		
		if("GUARDAR".equalsIgnoreCase(accion) || "MODIFICAR".equalsIgnoreCase(accion)){
			validateAux(request,SpringErrors,validacion);			
			
			if(importe == null || importe.equals("")){
				request.setAttribute(STRING_ERRORIMPORTE, STRING_ERRORIMPORTE);
				SpringErrors.add(STRING_DSERRORIMPORTE, new SpringMessage("field.tarifa.errores.importeRequerido"));
			}else{				
				if(!validacion.isFLoat(importe)){
					request.setAttribute(STRING_ERRORIMPORTE, STRING_ERRORIMPORTE);
					SpringErrors.add(STRING_DSERRORIMPORTE, new SpringMessage("field.tarifa.errores.importeFormato"));
				}else{
					
					float floatImporte = Float.parseFloat(importe);
					if (floatImporte > 999.99){
						request.setAttribute(STRING_ERRORIMPORTE, STRING_ERRORIMPORTE);
						SpringErrors.add(STRING_DSERRORIMPORTE, new SpringMessage("field.tarifa.errores.importeTamano"));
					}
				}				
			}		
		}
		
		return SpringErrors;
	}

	/**
	 * Validate aux.
	 *
	 * @param request el request
	 * @param SpringErrors el spring errors
	 * @param validacion el validacion
	 */
	public void validateAux(HttpServletRequest request, SpringErrors SpringErrors, Validacion validacion) {
		
		if(descripcion == null || descripcion.equalsIgnoreCase("")){
			request.setAttribute("errorDescripcion", "errorDescripcion");
			SpringErrors.add("dsErrorDescripcion", new SpringMessage("field.tarifa.errores.descripcionRequerido"));
		}
		
		if (idGrupo==null || idGrupo.equalsIgnoreCase("")){
			request.setAttribute("errorGrupo", "errorGrupo");
			SpringErrors.add("dsErrorGrupo", new SpringMessage("field.tarifa.errores.grupoRequerido"));
		}
		
		if (idTipoAcceso==null || idTipoAcceso.equals("")){
			request.setAttribute("errorTipoAcceso", "errorTipoAcceso");
			SpringErrors.add("dsErrorTipoAcceso", new SpringMessage("field.tarifa.errores.tipoAccesoRequerido"));
		}
		
		if(ejercicio == null || ejercicio.equalsIgnoreCase("")){
			request.setAttribute(STRING_ERROREJERCICIO, STRING_ERROREJERCICIO);
			SpringErrors.add(STRING_DSERROREJERCICIO, new SpringMessage("field.tarifa.errores.ejercicioRequerido"));
		}else{
			if(!validacion.isNumeric(ejercicio)){
				request.setAttribute(STRING_ERROREJERCICIO, STRING_ERROREJERCICIO);
				SpringErrors.add(STRING_DSERROREJERCICIO, new SpringMessage("field.tarifa.errores.ejercicioFormato"));
			}else{
				if(!validacion.validateTamanioCadena(ejercicio,4)){
					request.setAttribute(STRING_ERROREJERCICIO, STRING_ERROREJERCICIO);
					SpringErrors.add(STRING_DSERROREJERCICIO, new SpringMessage("field.tarifa.errores.ejercicioTamano"));
				}
			}				
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
