package es.map.ipsg.action.convocatoria;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;


import org.apache.log4j.Logger;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.EstadoConvocatoriaQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.DetalleConvocatoriaBean;
import es.map.ipsg.bean.LogConvocatoriaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.BuscaConvocatoriasForm;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.EstadoConvocatoriaManager;
import es.map.ipsg.manager.LogConvocatoriaManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;


/**
 * El Class ActualizarEstadoConvocatoriaSpring.
 */
public class ActualizarEstadoConvocatoriaSpring extends AbstractSpring {

	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** el estado convocatoria manager. */
	private EstadoConvocatoriaManager estadoConvocatoriaManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log convocatoria manager. */
	private LogConvocatoriaManager logConvocatoriaManager;
	
	/** el properties. */
	private static Properties properties;
	
	/** La constante BUNDLE_RESOURCE. */
	private static final String BUNDLE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ActualizarEstadoConvocatoriaSpring.class);
	
	/** La constante STRING_IDCONVOCATORIA. */
	private static final String STRING_IDCONVOCATORIA = "idConvocatoria";
	
	/** La constante STRING_ACTUALIZACION. */
	private static final String STRING_ACTUALIZACION = "actualizacion";
	
	/**
	 * Crea una nueva actualizar estado convocatoria spring.
	 */
	public ActualizarEstadoConvocatoriaSpring() {
		try {
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setEstadoConvocatoriaManager((EstadoConvocatoriaManager) getBean("estadosConvocatoriaManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogConvocatoriaManager((LogConvocatoriaManager) getBean("logConvocatoriaManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ActualizarEstadoConvocatoriaSpring(): ",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_convocatoria = RESOURCE_BUNDLE.getString("field.menu.convocatorias");
		this.getRequest().getSession().setAttribute("pagActiva", menu_convocatoria);
		String subMenu_convocatoria = RESOURCE_BUNDLE.getString("field.menuLateral.convocatorias.buscar");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_convocatoria);
		
		getLogger().debug("ActualizarEstadoConvocatoriaSpring -start");
		BuscaConvocatoriasForm formulario;
		formulario = (BuscaConvocatoriasForm) form;		

		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setLogin(usuarioBean.getLogin());
			usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
			
			String error="";
			//Compruebo si la llamada llega de eliminar convocatoria
			String codConvocatoria = "";
			Long idConvocatoria;
			if(formulario.getAccion() == null){				
				codConvocatoria = (String) this.getRequest().getParameter("registro");
				idConvocatoria = Long.parseLong(codConvocatoria);
			}else{
				codConvocatoria = formulario.getIdConvocatoria();
				idConvocatoria = Long.parseLong(codConvocatoria);
			}
			
			ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
			convocatoriaQuery.setId(idConvocatoria);
			DetalleConvocatoriaBean convocatoria = convocatoriasManager.detalleConvocatoria(convocatoriaQuery);
			Integer idEstadoAnterior = convocatoria.getIdEstadoConvocatoria();						
			
			SpringMessages messages = new SpringMessages();
	
			EstadoConvocatoriaQuery estadoConvocatoriaQuery = new EstadoConvocatoriaQuery();
			
			if(formulario.getAccion() == null){
				estadoConvocatoriaQuery.setId(Constantes.ESTADO_CONVOCATORIA_ELIMINADO);
				convocatoriasManager.actualizarEstado(convocatoriaQuery,estadoConvocatoriaQuery);
				
				generarRegistroLogGenerico(usuarioBean.getId(), idEstadoAnterior, Constantes.ESTADO_CONVOCATORIA_ELIMINADO, idConvocatoria);
				this.setRequestAttribute("accion", "Buscar");
				return "successEliminar";
			}else{
				if("RECONFIGURAR".equals(formulario.getAccion().toUpperCase())){
					estadoConvocatoriaQuery.setId(Constantes.ESTADO_CONVOCATORIA_CONFIGURACION);
				}else{
					if("PUBLICAR".equals(formulario.getAccion().toUpperCase())){
						SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
						Date fechaActual = new Date();
						logger.info(fechaActual.toString());
						
						if((convocatoria.getFechaInicioSub() != null && !"".equals(convocatoria.getFechaInicioSub())) || (convocatoria.getFechaFinSub() != null && !"".equals(convocatoria.getFechaFinSub()))) {
							
							logger.info("FechaInicioSub: "+convocatoria.getFechaInicioSub());
							if(convocatoria.getFechaInicioSub() == null || "".equals(convocatoria.getFechaInicioSub())) {
								messages.add("ErrorFechaInicioSub", new SpringMessage("field.convocatoria.errores.fechaInicioSubNoValido"));
							}
							logger.info("FechaFinSub: "+convocatoria.getFechaFinSub());
							if(convocatoria.getFechaFinSub() == null || "".equals(convocatoria.getFechaFinSub())) {
								messages.add("ErrorFechaFinSub", new SpringMessage("field.convocatoria.errores.fechaFinSubNoValido"));		
							}
							saveErrors(this.getRequest(), messages);
						}
						
						logger.info("FechaBoe: "+convocatoria.getFechaBoe());								
						if(convocatoria.getFechaBoe() == null || "".equals(convocatoria.getFechaBoe())){					
							messages.add("ErrorFechaBoe", new SpringMessage(
							"field.convocatoria.errores.fechaBoeRequeridoPublicar"));
							saveErrors(this.getRequest(), messages);	
						}
						
						try {
							Date fechaInicio = sdf.parse(formulario.getFechaInicio());
							logger.info(fechaInicio.toString());
							Date fechaFin = sdf.parse(formulario.getFechaFin());
							logger.info(fechaFin.toString());
							boolean fin = fechaActual.before(fechaFin);
							boolean inicio = fechaActual.after(fechaInicio);
							if(inicio == true && fin == true){
								estadoConvocatoriaQuery.setId(Constantes.ESTADO_CONVOCATORIA_PUBLICADO);
							}else{
								
								messages.add("errorFechaActual", new SpringMessage(
										"field.convocatoria.errores.fechaActual"));
								saveErrors(this.getRequest(), messages);							
							}
						} catch (Exception e) {
							messages.add("errorFechaActual", new SpringMessage(
									"field.convocatoria.errores.fechaInicioFinNoValido"));
							saveErrors(this.getRequest(), messages);	
						}
						
					}else{
						if("DESACTIVAR".equals(formulario.getAccion().toUpperCase())){
							estadoConvocatoriaQuery.setId(Constantes.ESTADO_CONVOCATORIA_DESACTIVADO);
						}else{
							if("APROBAR".equals(formulario.getAccion().toUpperCase())){
								
								if(convocatoria.getTipoDocumentacionAnexar().equalsIgnoreCase(Constantes.TIPO_DOC_REDUCCION) &&
										(convocatoria.getMotivosExencion() == null || convocatoria.getMotivosExencion().isEmpty())){
									messages.add("errorMotivosReduccion", new SpringMessage("field.convocatoria.errores.motivosExencionRequerido"));
								}
								
								//Para aprobar la convocatoria debe tener al menos una Provincia Examen
								if (convocatoria.getProvinciasExamen() == null || convocatoria.getProvinciasExamen().isEmpty()) {
										//No se puede dar el alta por no tener provincias asociadas
										messages.add("errorConvocatoriaSinProv", new SpringMessage("field.convocatoria.errores.provinciasNoAsoc"));
										this.getRequest().setAttribute(STRING_IDCONVOCATORIA,codConvocatoria);
										this.getRequest().setAttribute(STRING_ACTUALIZACION,false);
										this.getRequest().setAttribute("error", error);
								}
								
								logger.info("FechaBoe: "+convocatoria.getFechaBoe());								
								if(convocatoria.getFechaBoe() == null || "".equals(convocatoria.getFechaBoe())){					
									messages.add("ErrorFechaBoe", new SpringMessage(
									"field.convocatoria.errores.fechaBoeRequerido"));		
								}
								logger.info("FechaInicio: "+convocatoria.getFechaInicio());
								if(convocatoria.getFechaInicio() == null || "".equals(convocatoria.getFechaInicio())){
									messages.add("ErrorFechaInicio", new SpringMessage(
									"field.convocatoria.errores.fechaInicioRequerido"));
								}
								logger.info("FechaFin: "+convocatoria.getFechaFin());
								if(convocatoria.getFechaFin() == null || "".equals(convocatoria.getFechaFin())){
									messages.add("ErrorFechaFin", new SpringMessage(
									"field.convocatoria.errores.fechaFinRequerido"));
									
								}
																
								if((convocatoria.getFechaInicioSub() != null && !"".equals(convocatoria.getFechaInicioSub())) || (convocatoria.getFechaFinSub() != null && !"".equals(convocatoria.getFechaFinSub()))) {
									
								logger.info("FechaInicioSub: "+convocatoria.getFechaInicioSub());
								if(convocatoria.getFechaInicioSub() == null || "".equals(convocatoria.getFechaInicioSub())) {
									messages.add("ErrorFechaInicioSub", new SpringMessage("field.convocatoria.errores.fechaInicioSubNoValido"));
								}
								logger.info("FechaFinSub: "+convocatoria.getFechaFinSub());
								if(convocatoria.getFechaFinSub() == null || "".equals(convocatoria.getFechaFinSub())) {
									messages.add("ErrorFechaFinSub", new SpringMessage("field.convocatoria.errores.fechaFinSubNoValido"));
								}
							}

								if(convocatoria.getFechaBoe() != null && !"".equals(convocatoria.getFechaBoe()) 
								&& convocatoria.getFechaInicio() != null && !"".equals(convocatoria.getFechaInicio())
								&& convocatoria.getFechaFin() != null && !"".equals(convocatoria.getFechaFin())){
										
									SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy");
									Date fechaActual = new Date();
									logger.info(fechaActual.toString());
									
									
									Date fechaInicio = sdf.parse(convocatoria.getFechaInicio());
									logger.info(fechaInicio.toString());
									

									try{										
										estadoConvocatoriaQuery.setId(Constantes.ESTADO_CONVOCATORIA_APROBADO);
										
									}catch(BusinessException e){
										messages.add("ErrorSIGP", new SpringMessage(
										e.getKey()));
									}
								}else{
									logger.warn("Alguna fecha es nula");
								}
								
								saveErrors(this.getRequest(),messages);
							}
						}
					}
				}
			}
			if(!messages.isEmpty() || estadoConvocatoriaQuery.getId() == null || "".equals(estadoConvocatoriaQuery.getId())){
				this.getRequest().setAttribute(STRING_IDCONVOCATORIA,codConvocatoria);
				this.getRequest().setAttribute(STRING_ACTUALIZACION,false);
				this.getRequest().setAttribute("error", error);
				return "nosuccess";
			}		
			convocatoriasManager.actualizarEstado(convocatoriaQuery,estadoConvocatoriaQuery);
			generarRegistroLogGenerico(usuarioBean.getId(), idEstadoAnterior, estadoConvocatoriaQuery.getId(), idConvocatoria);
			this.getRequest().setAttribute(STRING_IDCONVOCATORIA,codConvocatoria);
			this.getRequest().setAttribute(STRING_ACTUALIZACION,true);
			getLogger().debug("ActualizarEstadoConvocatoriaSpring -end");
			return "success";
			
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error ActualizarEstadoConvocatoriaSpring  - doExecute: ",e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			return "excepcion";
		}
	}
	
	/**
	 * Generar registro log generico.
	 *
	 * @param idUsuario el id usuario
	 * @param estadoAnterior el estado anterior
	 * @param estadoActual el estado actual
	 * @param idConvocatoria el id convocatoria
	 */
	public void generarRegistroLogGenerico(Integer idUsuario, Integer estadoAnterior, Integer estadoActual, Long idConvocatoria){
				
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogConvocatoriaBean logConvocatoriaBean = new LogConvocatoriaBean();		
		
		logConvocatoriaBean.setTipoOperacion(Constantes.OPERACION_CAMBIO_ESTADO);
		logConvocatoriaBean.setDetalle(RESOURCE_BUNDLE.getString("field.logConvocatoria.cambioEstado")+idConvocatoria.toString());
		
		logConvocatoriaBean.setIdConvocatoria(idConvocatoria);
		logConvocatoriaBean.setIdUsuario(idUsuario);
		
		logConvocatoriaBean.setIdEstadoConvocatoriaActual(estadoActual);
		logConvocatoriaBean.setIdEstadoConvocatoriaAnterior(estadoAnterior);
		
		logConvocatoriaManager.guardarLogConvocatoria(logConvocatoriaBean);
	} 

	/**
	 * Recupera id usuario automatico.
	 *
	 * @return el integer
	 */
	public Integer recuperaIdUsuarioAutomatico() {
		String nifProcesoAutomatico = "";
		Integer idProcesoAutomatico = new Integer(0);
		try {
			usuarioManager = (UsuarioManager) ApplicationContextProvider.getInstance().getBean("usuarioManager");
			nifProcesoAutomatico = properties.getProperty("log.usuario.automatico.nif");
		} catch (Exception e) {
			logger.info("Contexto cargado por ejecucion de Test.",e);
		}
		if (nifProcesoAutomatico != null && !"".equals(nifProcesoAutomatico)) {
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setNif(nifProcesoAutomatico); 
			UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
			if (usuarioBean != null && usuarioBean.getId() != null) {
				idProcesoAutomatico = usuarioBean.getId();
			} else {
				UsuarioBean usuarioBeanAux = new UsuarioBean();
				usuarioBeanAux.setNombre(properties.getProperty("log.usuario.automatico.nombre"));
				usuarioBeanAux.setPrimerApellido(properties.getProperty("log.usuario.automatico.nombre"));
				usuarioBeanAux.setNif(properties.getProperty("log.usuario.automatico.nif"));
				usuarioBeanAux.setIdPerfil(new Integer(properties.getProperty("log.usuario.automatico.perfil")));
				usuarioBeanAux.setPublicaConvocatorias(true);
				usuarioBeanAux.setRecibeCorreosIncidencias(false);
				usuarioBeanAux.setEstado(properties.getProperty("log.usuario.automatico.estado").charAt(0));
				usuarioBeanAux.setEmail(properties.getProperty("log.usuario.automatico.email"));
				usuarioBeanAux.setLogin(properties.getProperty("log.usuario.automatico.nombre"));
				Integer idUsuario = usuarioManager.guardarUsuario(usuarioBeanAux);
				idProcesoAutomatico = idUsuario;
			}
		}
		return idProcesoAutomatico;
	}

	/**
	 * Obtiene el convocatorias manager.
	 *
	 * @return el convocatorias manager
	 */
	public ConvocatoriasManager getConvocatoriasManager() {
		return convocatoriasManager;
	}

	/**
	 * Establece el convocatorias manager.
	 *
	 * @param convocatoriasManager el nuevo convocatorias manager
	 */
	public void setConvocatoriasManager(ConvocatoriasManager convocatoriasManager) {
		this.convocatoriasManager = convocatoriasManager;
	}

	/**
	 * Obtiene el estado convocatoria manager.
	 *
	 * @return el estado convocatoria manager
	 */
	public EstadoConvocatoriaManager getEstadoConvocatoriaManager() {
		return estadoConvocatoriaManager;
	}

	/**
	 * Establece el estado convocatoria manager.
	 *
	 * @param estadoConvocatoriaManager el nuevo estado convocatoria manager
	 */
	public void setEstadoConvocatoriaManager(
			EstadoConvocatoriaManager estadoConvocatoriaManager) {
		this.estadoConvocatoriaManager = estadoConvocatoriaManager;
	}

	/**
	 * Obtiene el log convocatoria manager.
	 *
	 * @return el log convocatoria manager
	 */
	public LogConvocatoriaManager getLogConvocatoriaManager() {
		return logConvocatoriaManager;
	}

	/**
	 * Establece el log convocatoria manager.
	 *
	 * @param logConvocatoriaManager el nuevo log convocatoria manager
	 */
	public void setLogConvocatoriaManager(LogConvocatoriaManager logConvocatoriaManager) {
		this.logConvocatoriaManager = logConvocatoriaManager;
	}
	
	/**
	 * Obtiene el usuario manager.
	 *
	 * @return el usuario manager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager el nuevo usuario manager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
}
