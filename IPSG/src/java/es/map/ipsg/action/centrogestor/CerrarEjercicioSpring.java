package es.map.ipsg.action.centrogestor;

import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.EstadoConvocatoriaQuery;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.action.convocatoria.ActualizarEstadoConvocatoriaSpring;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.AlertaManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;


/**
 * Clase que implemente EliminarCentroGestorAction.
 *
 * @author amartinl
 */
public class CerrarEjercicioSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CerrarEjercicioSpring.class);
	
	/** el centro gestor manager. */
	//Declaracion de manager
	private CentroGestorManager centroGestorManager;
	
	/** el cuerpo escala manager. */
	//Para comprobar con estas entidades si se permite borrar al Centro
	private CuerpoEscalaManager cuerpoEscalaManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el alerta manager. */
	private AlertaManager alertaManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/** el convocatoria manager. */
	private ConvocatoriasManager convocatoriaManager;
	

	/**
	 * Acción EliminarTituloOficialAction.
	 */
	public CerrarEjercicioSpring() {
		try {
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			setUsuarioManager ((UsuarioManager) getBean ("usuarioManager"));
			setAlertaManager((AlertaManager)getBean ("alertaManager"));
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		} catch (Exception e) {
			logger.error("Error CerrarEjercicioSpring",e );
		}
	}


/**
 * Obtiene el alerta manager.
 *
 * @return el alerta manager
 */
public AlertaManager getAlertaManager() {
		return alertaManager;
	}


	/**
	 * Establece el alerta manager.
	 *
	 * @param alertaManager el nuevo alerta manager
	 */
	public void setAlertaManager(AlertaManager alertaManager) {
		this.alertaManager = alertaManager;
	}


/**
 * Método doExecute de EliminarTituloOficialAction.
 *
 * @param form SpringForm
 * @return resultado String Si no tiene errores nos devuelve success
 * @throws Exception Exception
 */
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_centroGestor = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.centroGestor");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_centroGestor);
		
		SpringMessages errors = new SpringMessages();
		String resultado = "";
		String sUsernameLogin = "";
	try{
				
		Integer idCentroGestor =  Integer.valueOf(this.getRequest().getParameter("id"));
		
		if(idCentroGestor != null){//Dar de Baja el Centro Gestor
			
			//Obtenemos el Usuario que está logueado en la aplicación para insertarlo en el log
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			try{
				UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
				sUsernameLogin = usuarioBean.getLogin();
				usuarioQuery.setLogin(sUsernameLogin);
			}catch(Exception e){
				logger.error("Error CerrarEjercicioSpring -recuperarUsuarioSesion",e );
				new BusinessException("exception.recuperarUsuarioSesion");
				return "error";
			}
						
			boolean bCierraEjercicio = false;
			//Se cierran todas las convocatorias
			//Para cerrar el ejercicio lo primero es obtener todos los datos de ese centro gestor
			CentroGestorBean centroGestorBean=centroGestorManager.obtenerCentroGestor(idCentroGestor);
			
			//Si vienen a falso, no se podrá eliminar porque tiene campos asociados.
			bCierraEjercicio = compruebaCentroGestor(errors,centroGestorBean);
			
				
			if (bCierraEjercicio){
				
				//Actualizamos el ejercicio sumándole un año
				Integer intEjercicio= Integer.valueOf(centroGestorBean.getEjercicio())+1;
				centroGestorBean.setEjercicio(intEjercicio.toString());
				//Modificamos el registro
				centroGestorManager.modificarCentroGestor(centroGestorBean);
				//En caso de poder cerrar el ejercicio
				 cierraEstadoConvocatoria(centroGestorBean);
				UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
				generarRegistroLogGenerico(usuarioBean.getLogin(),Long.valueOf(centroGestorBean.getId()));
				
			}
			else{
				this.setRequestAttribute("org.apache.spring.ERROR", errors);
				return "errorCerrarCentroGestor";
			}
			
			String mensaje = RESOURCE_BUNDLE.getString("field.centroGestor.eliminarCentroGestorConfirmacion");
			String titulo = RESOURCE_BUNDLE.getString("field.centroGestor.centroGestorMantenimientoTitulo");			
			
			setRequestAttribute("titulo",titulo);
			setRequestAttribute("mensaje",mensaje);
			setRequestAttribute("accion","/spring/buscarCentroGestor");
			
			resultado = "success";
		}else{
			resultado = "error";
		}
		
	}catch(Exception eGenerico){
		logger.error("Error cerrarEjercicioSpring- doExecute: "+ eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		return resultado;
	}

/**
 * Generar registro log generico.
 *
 * @param username el username
 * @param idCentroGestor el id centro gestor
 */
public void generarRegistroLogGenerico(String username, Long idCentroGestor){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_CENTRO_GESTOR);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_CIERRE_EJERCICIO);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.centroGestor.detalleOperacionCerrarEjercicio") + " " + idCentroGestor.toString());
		logGenericoBean.setIdTablaOrigen(idCentroGestor);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}
	
	/**
	 * Comprueba centro gestor.
	 *
	 * @param errors el errors
	 * @param centroGestorBean el centro gestor bean
	 * @return verdadero, si tiene exito
	 */
	//Comprueba que el Centro Gestor no tiene asociadas Convocatorias en estado de publicadas o aprobadas
	private boolean compruebaCentroGestor (SpringMessages errors,CentroGestorBean centroGestorBean){	

		
		
		CentroGestorQuery centroQuery = new CentroGestorQuery();
		centroQuery.setId(Integer.valueOf(centroGestorBean.getId()));
		CuerpoEscalaQuery cuerpoQuery = new CuerpoEscalaQuery();
		cuerpoQuery.setCentroGestor(centroQuery);
		
		ConvocatoriaQuery convocatoriaQuery= new ConvocatoriaQuery();
		convocatoriaQuery.setCuerpoEscala(cuerpoQuery);
		convocatoriaQuery.addEstadoConvocatoriaIn(Constantes.ESTADO_CONVOCATORIA_PUBLICADO);
		convocatoriaQuery.addEstadoConvocatoriaIn(Constantes.ESTADO_CONVOCATORIA_APROBADO);
		
		convocatoriaQuery.setCuerpoEscala(cuerpoQuery);
		convocatoriaQuery.setEjercicio(centroGestorBean.getEjercicio());
		ArrayList<ConvocatoriasBean> lConvocatoria = null;
		
		lConvocatoria=convocatoriaManager.buscarConvocatoriasAll(convocatoriaQuery);	
		
		
		//si la consulta ha devuelto algún usuario, el centro está asociado a ellos.
		if (lConvocatoria.size() > 0 )
		{ 	
		
			SpringMessage error = new SpringMessage("field.centroGestor.errores.cerrarEjercicio",centroGestorBean.getEjercicio() );
			errors.add("errorCerrarReabrirEjercicio", error);
			this.setRequestAttribute("errorCerrarReabrirEjercicio", "true");
			
			for (int i=0; i < lConvocatoria.size();i++){
				error = new SpringMessage("field.centroGestor.errores.cerrarEjercicio1",lConvocatoria.get(i).getIdConvocatoria(),lConvocatoria.get(i).getEstadoConvocatoria());
				errors.add("errorCerrarReabrirEjercicio1", error);
			}
			
			return false;// No se puede Cerrar
		}
		else
		{
			
			return true;
		}
	}

/**
 * Cierra estado convocatoria.
 *
 * @param centroGestorBean el centro gestor bean
 */
//Función para cerrar el estado de las convocatorias pertenecientes al ejercicio
private void cierraEstadoConvocatoria(CentroGestorBean centroGestorBean){
	//Obtengo todas las convocatorias asocidas al Centro Gestor
	CentroGestorQuery centroQuery = new CentroGestorQuery();
	
	centroQuery.setId(centroGestorBean.getId());
	CuerpoEscalaQuery cuerpoQuery = new CuerpoEscalaQuery();
	cuerpoQuery.setCentroGestor(centroQuery);
	
	ConvocatoriaQuery convocatoriaQuery= new ConvocatoriaQuery();
	convocatoriaQuery.setCuerpoEscala(cuerpoQuery);
	//El estado debe ser difernte a publicado y aprobado
	convocatoriaQuery.addEstadoConvocatoriaIn(Constantes.ESTADO_CONVOCATORIA_FINALIZADO);
	convocatoriaQuery.addEstadoConvocatoriaIn(Constantes.ESTADO_CONVOCATORIA_CERRADO);
	convocatoriaQuery.addEstadoConvocatoriaIn(Constantes.ESTADO_CONVOCATORIA_CONFIGURACION);
	convocatoriaQuery.addEstadoConvocatoriaIn(Constantes.ESTADO_CONVOCATORIA_DESACTIVADO);
	convocatoriaQuery.addEstadoConvocatoriaIn(Constantes.ESTADO_CONVOCATORIA_ELIMINADO);
	
	convocatoriaQuery.setCuerpoEscala(cuerpoQuery);
	//Resto uno al ejerccio porque ya ha sido actualizado
	Integer intEjercicio=Integer.valueOf(centroGestorBean.getEjercicio())-1;
	convocatoriaQuery.setEjercicio(intEjercicio.toString());
	ArrayList<ConvocatoriasBean> lConvocatoria = null;
	
	lConvocatoria=convocatoriaManager.buscarConvocatoriasAll(convocatoriaQuery);
	
	EstadoConvocatoriaQuery estadoConvocatoriaQuery= new EstadoConvocatoriaQuery();	
	ActualizarEstadoConvocatoriaSpring actualizarEstado = new ActualizarEstadoConvocatoriaSpring();
	Integer idUsuarioAutomático = actualizarEstado.recuperaIdUsuarioAutomatico();
	Convocatoria convocatoria = new Convocatoria();
	ConvocatoriaQuery convocatoriaQueryAux = new ConvocatoriaQuery();
	
	//Recorro todas las convocatorias para actualizar el estado
	for (int i=0; i < lConvocatoria.size();i++){
		convocatoriaQuery.setId(Long.valueOf(lConvocatoria.get(i).getIdConvocatoria()));
		convocatoriaQueryAux.setId(Long.valueOf(lConvocatoria.get(i).getIdConvocatoria()));
		estadoConvocatoriaQuery.setId(Constantes.ESTADO_CONVOCATORIA_CERRADO);
		convocatoria = convocatoriaManager.buscarConvocatoria(convocatoriaQueryAux);
		convocatoriaManager.actualizarEstado(convocatoriaQuery, estadoConvocatoriaQuery);		
		actualizarEstado.generarRegistroLogGenerico(idUsuarioAutomático, convocatoria.getEstadoConvocatoria().getId(), 
				Constantes.ESTADO_CONVOCATORIA_CERRADO, Long.valueOf(lConvocatoria.get(i).getIdConvocatoria()));
	}
	
}
	
/**
 * Obtiene el valor de CuerpoEscalaManager.
 *
 * @return cuerpoEscalaManager CuerpoEscalaManager
 */
public CuerpoEscalaManager getCuerpoEscalaManager() {
	return cuerpoEscalaManager;
}

/**
 * Establece el valor de cuerpoEscalaManager.
 *
 * @param cuerpoEscalaManager CuerpoEscalaManager
 */
public void setCuerpoEscalaManager(CuerpoEscalaManager cuerpoEscalaManager) {
	this.cuerpoEscalaManager = cuerpoEscalaManager;
}

/**
 * Obtiene el valor de usuarioManager.
 *
 * @return usuarioManager UsuarioManager
 */
public UsuarioManager getUsuarioManager() {
	return usuarioManager;
}

/**
 * Establece el valor de usuarioManager.
 *
 * @param usuarioManager UsuarioManager
 */
public void setUsuarioManager(UsuarioManager usuarioManager) {
	this.usuarioManager = usuarioManager;
}

/**
 * Obtiene el valor de centroGestorManager.
 *
 * @return centroGestorManager
 */
public CentroGestorManager getCentroGestorManager() {
	return centroGestorManager;
}

/**
 * Establece el valor de centroGestorManager.
 *
 * @param centroGestorManager CentroGestorManager
 */
public void setCentroGestorManager(CentroGestorManager centroGestorManager) {
	this.centroGestorManager = centroGestorManager;
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
 * Obtiene el convocatorias manager.
 *
 * @return the convocatoriaManager
 */
public ConvocatoriasManager getConvocatoriasManager() {
	return convocatoriaManager;
}


/**
 * Establece el convocatorias manager.
 *
 * @param convocatoriaManager the convocatoriaManager to set
 */
public void setConvocatoriasManager(ConvocatoriasManager convocatoriaManager) {
	this.convocatoriaManager = convocatoriaManager;
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
