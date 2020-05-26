package es.map.ipsg.form;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.util.DateUtil;
import es.map.ipsg.util.Validacion;

/**
 * El Class CentroGestorForm.
 */
public class CentroGestorForm extends SpringForm {

	/** el id. */
	private String id;
	
	/** el id modelo. */
	private String idModelo;
	
	/** el ministerio. */
	private String ministerio;
	
	/** el id ministerio. */
	private Integer idMinisterio;
	
	/** el siglas. */
	private String siglas;
	
	/** el descripcion. */
	private String descripcion;
	
	/** el ejercicio. */
	private String ejercicio;
	
	/** el codigo. */
	private String codigo;
	
	/** el accion. */
	private String accion;
	
	/** el estado. */
	private Character estado;
	
	/** el submit. */
	private String submit;
	
	/** el parametro. */
	private String parametro;
	
	/** el parametro 2. */
	private String parametro2;
	
	/** el id plantilla. */
	private Long idPlantilla;
	
	/** el campo. */
	private String campo;
	
	/** el direccion. */
	private String direccion;
	
	/** el pagina actual. */
	private String paginaActual;
	
	/** el paginas totales. */
	private String paginasTotales;
	
	/** el num registro. */
	private String numRegistro;
	
	/** el mensaje. */
	private String mensaje;
	
	/** el cambios. */
	private String cambios;
	
	/** el id centro gestor sust. */
	private Integer idCentroGestorSust;
	
	/** el fecha sustitucion. */
	private String fechaSustitucion;
	
	/** el visibilidad. */
	private Boolean visibilidad;
	
	/** el listar todos. */
	private String listarTodos;
	
	/** el numero pagina ir. */
	private Integer numeroPaginaIr;
	
	/** el pulsa ir. */
	private boolean pulsaIr;
	
	/** el fecha inicio inhabil. */
	private String fechaInicioInhabil;
	
	/** el fecha fin inhabil. */
	private String fechaFinInhabil;

	/** La constante STRING_ERROREJERCICIO. */
	private static final String STRING_ERROREJERCICIO = "errorEjercicio";
	
	/** La constante STRING_ERRORCODIGO. */
	private static final String STRING_ERRORCODIGO = "errorCodigo";
	
	/** La constante STRING_DSERRORCODIGO. */
	private static final String STRING_DSERRORCODIGO = "dsErrorCodigo";
	
	/** La constante STRING_ERRORFECHAINICIOVSFIN. */
	private static final String STRING_ERRORFECHAINICIOVSFIN = "errorFechaInicioVSfin";
	
	/**
	 * Obtiene el id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Establece el id.
	 *
	 * @param pid el nuevo id
	 */
	public void setId(String pid) {
		this.id = pid;
	}
	
	/**
	 * Obtiene el id modelo.
	 *
	 * @return el id modelo
	 */
	public String getIdModelo() {
		return idModelo;
	}

	/**
	 * Establece el id modelo.
	 *
	 * @param idModelo el nuevo id modelo
	 */
	public void setIdModelo(String idModelo) {
		this.idModelo = idModelo;
	}

	/**
	 * Obtiene el ministerio.
	 *
	 * @return the ministerio
	 */
	public String getMinisterio() {
		return ministerio;
	}

	/**
	 * Establece el ministerio.
	 *
	 * @param ministerio            the ministerio to set
	 */
	public void setMinisterio(String ministerio) {
		this.ministerio = ministerio;
	}

	/**
	 * Obtiene el id ministerio.
	 *
	 * @return the idMinisterio
	 */
	public Integer getIdMinisterio() {
		return idMinisterio;
	}

	/**
	 * Establece el id ministerio.
	 *
	 * @param idMinisterio            the idMinisterio to set
	 */
	public void setIdMinisterio(Integer idMinisterio) {
		this.idMinisterio = idMinisterio;
	}

	/**
	 * Obtiene el codigo.
	 *
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * Establece el codigo.
	 *
	 * @param pcodigo el nuevo codigo
	 */
	public void setCodigo(String pcodigo) {
		this.codigo = pcodigo;
	}

	/**
	 * Obtiene el descripcion.
	 *
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return (descripcion!=null ? descripcion.trim() : null);
	}

	/**
	 * Establece el descripcion.
	 *
	 * @param pdescripcion el nuevo descripcion
	 */
	public void setDescripcion(String pdescripcion) {
		this.descripcion = pdescripcion;
	}

	/**
	 * Obtiene el siglas.
	 *
	 * @return the siglas
	 */
	public String getSiglas() {
		return siglas;
	}

	/**
	 * Establece el siglas.
	 *
	 * @param psiglas el nuevo siglas
	 */
	public void setSiglas(String psiglas) {
		this.siglas = psiglas;
	}

	/**
	 * Obtiene el ejercicio.
	 *
	 * @return the ejercicio
	 */
	public String getEjercicio() {
		return ejercicio;
	}

	/**
	 * Establece el ejercicio.
	 *
	 * @param ejercicio            the ejercicio to set
	 */
	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
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
	 * @param accion            the accion to set
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * Obtiene el parametro.
	 *
	 * @return the parametro
	 */
	public String getParametro() {
		return parametro;
	}

	/**
	 * Establece el parametro.
	 *
	 * @param parametro            the parametro to set
	 */
	public void setParametro(String parametro) {
		this.parametro = parametro;
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
	 * Obtiene el id plantilla.
	 *
	 * @return the idPlantilla
	 */
	public Long getIdPlantilla() {
		return idPlantilla;
	}

	/**
	 * Establece el id plantilla.
	 *
	 * @param idPlantilla            the idPlantilla to set
	 */
	public void setIdPlantilla(Long idPlantilla) {
		this.idPlantilla = idPlantilla;
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
	 * Establece el campo.
	 *
	 * @param campo            the campo to set
	 */
	public void setCampo(String campo) {
		this.campo = campo;
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
	 * @param direccion            the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
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
	 * Establece el pagina actual.
	 *
	 * @param paginaActual            the paginaActual to set
	 */
	public void setPaginaActual(String paginaActual) {
		this.paginaActual = paginaActual;
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
	 * Establece el paginas totales.
	 *
	 * @param paginasTotales            the paginasTotales to set
	 */
	public void setPaginasTotales(String paginasTotales) {
		this.paginasTotales = paginasTotales;
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
	 * Establece el num registro.
	 *
	 * @param numRegistro            the numRegistro to set
	 */
	public void setNumRegistro(String numRegistro) {
		this.numRegistro = numRegistro;
	}

	/**
	 * Obtiene el mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Establece el mensaje.
	 *
	 * @param mensaje            the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
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
	 * @param cambios            the cambios to set
	 */
	public void setCambios(String cambios) {
		this.cambios = cambios;
	}

	/**
	 * Obtiene el id centro gestor sust.
	 *
	 * @return el id centro gestor sust
	 */
	public Integer getIdCentroGestorSust() {
		return idCentroGestorSust;
	}

	/**
	 * Establece el id centro gestor sust.
	 *
	 * @param idCentroGestorSust el nuevo id centro gestor sust
	 */
	public void setIdCentroGestorSust(Integer idCentroGestorSust) {
		this.idCentroGestorSust = idCentroGestorSust;
	}

	/**
	 * Obtiene el fecha sustitucion.
	 *
	 * @return el fecha sustitucion
	 */
	public String getFechaSustitucion() {
		return fechaSustitucion;
	}

	/**
	 * Establece el fecha sustitucion.
	 *
	 * @param fechaSustitucion el nuevo fecha sustitucion
	 */
	public void setFechaSustitucion(String fechaSustitucion) {
		this.fechaSustitucion = fechaSustitucion;
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	public SpringErrors validate(SpringMapping mapping,
			HttpServletRequest request) {
		SpringErrors SpringErrors = new SpringErrors();
		Validacion validacion = new Validacion();

		if ("GUARDAR".equalsIgnoreCase(accion)
				|| "MODIFICAR".equalsIgnoreCase(accion)) {

			if (idMinisterio == null || idMinisterio == 0) {
				request.setAttribute("errorIdMinisterio", "errorIdMinisterio");
				SpringErrors.add("dsErrorIdMinisterio", new SpringMessage(
						"field.centroGestor.errores.ministerioRequerido"));
			}

			if (siglas == null || siglas.equalsIgnoreCase("")) {
				request.setAttribute("errorSiglas", "errorSiglas");
				SpringErrors.add("dsErrorSiglas", new SpringMessage(
						"field.centroGestor.errores.siglasRequerido"));
			}

			if (descripcion == null || descripcion.equalsIgnoreCase("")) {

				request.setAttribute("errorDescripcion", "errorDescripcion");
				SpringErrors.add("dsErrorDescripcion", new SpringMessage(
						"field.centroGestor.errores.descripcionRequerido"));
			}

			if (ejercicio == null || ejercicio.equalsIgnoreCase("")) {

				request.setAttribute(STRING_ERROREJERCICIO, STRING_ERROREJERCICIO);
				SpringErrors.add("dsErrorEjercicio", new SpringMessage(
						"field.centroGestor.errores.ejercicioRequerido"));
			} else {
				if (validacion.isNumeric(ejercicio)) {
					if (!validacion.validateTamanioCadena(ejercicio, 4)) {
						request
								.setAttribute(STRING_ERROREJERCICIO,
										STRING_ERROREJERCICIO);
						SpringErrors.add("dsErrorEjercicio", new SpringMessage(
								"field.centroGestor.errores.ejercicioFormato"));
					}
				} else {
					request.setAttribute(STRING_ERRORCODIGO, STRING_ERRORCODIGO);
					SpringErrors.add(STRING_DSERRORCODIGO, new SpringMessage(
							"field.centroGestor.errores.ejercicioNoNumero"));
				}
			}

			if (codigo == null || codigo.equalsIgnoreCase("")) {

				request.setAttribute(STRING_ERRORCODIGO, STRING_ERRORCODIGO);
				SpringErrors.add(STRING_DSERRORCODIGO, new SpringMessage(
						"field.centroGestor.errores.codigoRequerido"));
			} else {
				if (codigo.contains(" ")) {
					request.setAttribute(STRING_ERRORCODIGO, STRING_ERRORCODIGO);
					SpringErrors.add(STRING_DSERRORCODIGO, new SpringMessage(
							"field.centroGestor.errores.codigoBlanco"));
				} else {
					if (!validacion.validateTamanioCadena(codigo, 4)) {
						request.setAttribute(STRING_ERRORCODIGO, STRING_ERRORCODIGO);
						SpringErrors.add(STRING_DSERRORCODIGO, new SpringMessage(
								"field.centroGestor.errores.codigoFormato"));
					}
				}
			}
			
			if (idModelo == null || "".equals(idModelo)) {
				request.setAttribute("errorIdModelo", "errorIdModelo");
				SpringErrors.add("dsErrorIdModelo", new SpringMessage(
						"field.centroGestor.errores.modeloRequerido"));
			}

			if (fechaSustitucion != null && !"".equals(fechaSustitucion)) {
				if (idCentroGestorSust == null || idCentroGestorSust == 0) {
					request.setAttribute("errorCentroGestorSust","errorCentroGestorSust");
					SpringErrors.add("dsErrorCentroGestorSust",new SpringMessage("field.centroGestor.errorFechaSustitucion2"));
				} else {
					if (!validacion.isFechaValida(fechaSustitucion)) {
						request.setAttribute("errorFechaSust","errorFechaSust");
						SpringErrors.add("dsErrorFechaSust", new SpringMessage("field.centroGestor.errorFechaSustitucion"));
					}
				}
			} else {
				if (idCentroGestorSust != null && idCentroGestorSust != 0) {
					request.setAttribute("errorMinisterioSust","errorMinisterioSust");
					SpringErrors.add("dsErrorMinisterioSust",new SpringMessage("field.centroGestor.errorCentroGestorSust"));
				}
			}
			
			if ((fechaInicioInhabil != null && !"".equals(fechaInicioInhabil)) || (fechaFinInhabil != null && !"".equals(fechaFinInhabil))) {
				if (fechaInicioInhabil == null || "".equals(fechaInicioInhabil)) {
					request.setAttribute("errorFechaInicio","errorFechaInicio");
					SpringErrors.add("dsErrorFechaInicio",new SpringMessage("field.centroGestor.errorFechaInicioInhabil"));
				}else if(fechaFinInhabil == null || "".equals(fechaFinInhabil)){
					request.setAttribute("errorFechaFin","errorFechaFin");
					SpringErrors.add("dsErrorFechaFin",new SpringMessage("field.centroGestor.errorFechaFinInhabil"));
				}else{
					Date fechaFInicio = new Date();
					Date fechaFin = new Date();
					try {
						fechaFInicio = DateUtil.parse(fechaInicioInhabil,"dd/MM/yyyy");
						fechaFin = DateUtil.parse(fechaFinInhabil,"dd/MM/yyyy");
					} catch (ParseException e) {
						request.setAttribute(STRING_ERRORFECHAINICIOVSFIN,STRING_ERRORFECHAINICIOVSFIN);		
						SpringErrors.add("dsErrorFechaInicioVSfin",new SpringMessage("field.centroGestor.errorFechaInicioVsFin"));
						e.printStackTrace();
					}
			
					if(fechaFInicio.after(fechaFin)){
						request.setAttribute(STRING_ERRORFECHAINICIOVSFIN,STRING_ERRORFECHAINICIOVSFIN);		
						SpringErrors.add("dsErrorFechaInicioVSfin",new SpringMessage("field.centroGestor.errorFechaInicioVsFin"));
					}
					
				}
			}

		}

		return SpringErrors;
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
	
	/**
	 * Obtiene el fecha inicio inhabil.
	 *
	 * @return el fecha inicio inhabil
	 */
	public String getFechaInicioInhabil() {
		return fechaInicioInhabil;
	}
	
	/**
	 * Establece el fecha inicio inhabil.
	 *
	 * @param fechaInicioInhabil el nuevo fecha inicio inhabil
	 */
	public void setFechaInicioInhabil(String fechaInicioInhabil) {
		this.fechaInicioInhabil = fechaInicioInhabil;
	}
	
	/**
	 * Obtiene el fecha fin inhabil.
	 *
	 * @return el fecha fin inhabil
	 */
	public String getFechaFinInhabil() {
		return fechaFinInhabil;
	}
	
	/**
	 * Establece el fecha fin inhabil.
	 *
	 * @param fechaFinInhabil el nuevo fecha fin inhabil
	 */
	public void setFechaFinInhabil(String fechaFinInhabil) {
		this.fechaFinInhabil = fechaFinInhabil;
	}
	
	
}
