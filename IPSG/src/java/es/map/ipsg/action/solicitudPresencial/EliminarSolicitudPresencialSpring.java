package es.map.ipsg.action.solicitudPresencial;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.EstadoSolicitud;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.LogSolicitudBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.LogSolicitudManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * Clase que implemente EliminarFestivoAction.
 *
 * @author amartinl
 */
public class EliminarSolicitudPresencialSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarSolicitudPresencialSpring.class);	
	
	/** el solicitudes registradas manager. */
	//Declaracion de manager
	private SolicitudesRegistradasManager solicitudesRegistradasManager;
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/** el log solicitud manager. */
	private LogSolicitudManager logSolicitudManager;

	/**
	 * Acción EliminarSolicitudPresencialAction.
	 */
	public EliminarSolicitudPresencialSpring() {
		try {
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setSolicitudesRegistradasManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));
			setLogSolicitudManager((LogSolicitudManager) getBean("logSolicitudManager"));
		} catch (Exception e) {
			
			logger.error("Error EliminarSolicitudPresencialSpring ",e);
		}
	}

	/**
	 * Método doExecute de EliminarSolicitudPresencialAction.
	 *
	 * @param form SpringForm
	 * @return resultado String si no tiene errores nos devuelve success
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_solPresencial = RESOURCE_BUNDLE.getString("field.menu.solicPresenciales");
		this.getRequest().getSession().setAttribute("pagActiva", menu_solPresencial);
		String subMenu_SolPresen = RESOURCE_BUNDLE.getString("field.menuLateral.solicitudesPresenciales.buscar");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_SolPresen);
		
		getLogger().debug("EliminarSolicitudPresencialSpring -start");
		String idSolicitudPresencial = this.getRequest().getParameter("registro");	
		SpringMessages messages = new SpringMessages();		
		String resultado = "";
		
	try{
		if(!StringUtils.isEmpty(idSolicitudPresencial)){	//Dar de Baja la solicitud presencial

			EstadoSolicitud estadoSolicitud = new EstadoSolicitud();
			estadoSolicitud.setId(Constantes.ESTADO_SOLICITUD_ELIMINADO);
			
			// Obtenemos la solicitud que vamos a eliminar
			SolicitudBean solicitudBean = solicitudesRegistradasManager.obtenerSolicitud(Long.valueOf(idSolicitudPresencial));
			
			// Obtenemos el estado anterior de la solicitud
			int idEstadoAnterior = solicitudBean.getEstadoSolicitud().getId();
			solicitudBean.setEstadoSolicitud(estadoSolicitud);
			solicitudBean.setFechaUtlActualizacion(Calendar.getInstance().getTime());
			
			//Modificamos la solicitud anteriormente modificada
			solicitudesRegistradasManager.modificarSolicitudRegistrada(solicitudBean);
			
			// Eliminar documentos del File System y de BBDD
			DocumentoQuery documentoQuery = new DocumentoQuery();
			SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
			solicitudComunQuery.setIdSolicitud(Long.parseLong(idSolicitudPresencial));
			documentoQuery.setSolicitudComun(solicitudComunQuery);
			ArrayList <DocumentoBean> listaDocumentos; 
			listaDocumentos = documentoManager.buscarDocumentoCombo(documentoQuery);
			
			if(!listaDocumentos.isEmpty()){
				for (DocumentoBean documentoBean : listaDocumentos) {
					documentoManager.borrarDocumento(documentoBean);
				}
			}
			
			// Insertamos en el log la eliminación
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			generarRegistroLogSolicitud(idEstadoAnterior,usuarioBean.getLogin(),solicitudBean);
			
			getLogger().debug("EliminarSolicitudPresencialSpring -end");	
			
			resultado = "success";
		} else {
			messages.add("errorEliminarSolicitudPresencial",new SpringMessage("field.solicitudPresencial.error.eliminar"));
			resultado = "error";
		}
		
	}catch(Exception eGenerico){
		logger.error("Error EliminarSolicitudPresencialSpring - doExecute ",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return resultado;
	}
	
	/**
	 * Generar registro log solicitud.
	 *
	 * @param idEstadoAnterior el id estado anterior
	 * @param username el username
	 * @param solicitudBean el solicitud bean
	 */
	public void generarRegistroLogSolicitud(int idEstadoAnterior,String username,SolicitudBean solicitudBean){
		
		//Cargo los datos en el bean del log solicitudes que se usara para crear el registro en la tabla
		LogSolicitudBean logSolicitudBean = new LogSolicitudBean();
		
		logSolicitudBean.setTipoOperacion(Constantes.OPERACION_BAJA);
		logSolicitudBean.setActor(username);
		logSolicitudBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.solicitud.detalleOperacionBaja") + " " + solicitudBean.getId());
		logSolicitudBean.setIdSolicitud(solicitudBean.getId());	
		logSolicitudBean.setId_estado_anterior(idEstadoAnterior);
		logSolicitudBean.setId_estado_actual(solicitudBean.getEstadoSolicitud().getId());
		logSolicitudBean.setTipoActor(Constantes.OPERACION_ACTUALIZAR_ESTADO_SOLICITUD_USUARIO);
		
		logSolicitudManager.generarRegistroLogSolicitud(logSolicitudBean);		
	}

	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param idSolicitudPresencial el id solicitud presencial
	 */
	public void generarRegistroLogGenerico(String username, Long idSolicitudPresencial){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setFecha(new Date());
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_SOLICITUD);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_BAJA);
		logGenericoBean.setDetalleOperacion("Borrado de la solicitud presencial " + idSolicitudPresencial);
		logGenericoBean.setIdTablaOrigen(idSolicitudPresencial);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
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
	 * Obtiene el valor de festivoManager.
	 *
	 * @return festivoManager
	 */
	public SolicitudesRegistradasManager getSolicitudesRegistradasManager() {
		return solicitudesRegistradasManager;
	}
	
	/**
	 * Establece el valor de festivoManager.
	 *
	 * @param solicitudesRegistradasManager el nuevo solicitudes registradas manager
	 */
	public void setSolicitudesRegistradasManager(SolicitudesRegistradasManager solicitudesRegistradasManager) {
		this.solicitudesRegistradasManager = solicitudesRegistradasManager;
	}
	
	/**
	 * Obtiene el valor de LogGenericoManager.
	 *
	 * @return logGenericoManager
	 */
	public LogGenericoManager getLogGenericoManager() {
			return logGenericoManager;
	}
	
	/**
	 * Establece el valor de logGenericoManager.
	 *
	 * @param logGenericoManager LogGenericoManager
	 */
	public void setLogGenericoManager(LogGenericoManager logGenericoManager) {
		this.logGenericoManager = logGenericoManager;
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
	 * Obtiene el log solicitud manager.
	 *
	 * @return the logSolicitudManager
	 */
	public LogSolicitudManager getLogSolicitudManager() {
		return logSolicitudManager;
	}

	/**
	 * Establece el log solicitud manager.
	 *
	 * @param logSolicitudManager the logSolicitudManager to set
	 */
	public void setLogSolicitudManager(LogSolicitudManager logSolicitudManager) {
		this.logSolicitudManager = logSolicitudManager;
	}

	/**
	 * Obtiene el documento manager.
	 *
	 * @return el documento manager
	 */
	public DocumentoManager getDocumentoManager() {
		return documentoManager;
	}

	/**
	 * Establece el documento manager.
	 *
	 * @param documentoManager el nuevo documento manager
	 */
	public void setDocumentoManager(DocumentoManager documentoManager) {
		this.documentoManager = documentoManager;
	}	
}
