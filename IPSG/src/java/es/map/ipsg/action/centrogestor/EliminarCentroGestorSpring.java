package es.map.ipsg.action.centrogestor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.AlertaQuery;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.LogGenerico;
import es.map.ips.model.PlantillaPropiosQuery;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.AlertaBean;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.bean.PlantillaPropiosBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.AlertaManager;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.PlantillaPropiosManager;
import es.map.ipsg.manager.UsuarioManager;

/**
 * Clase que implemente EliminarCentroGestorAction.
 *
 * @author amartinl
 */
public class EliminarCentroGestorSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante ESTADO_ELIMINADO. */
	private static final Character ESTADO_ELIMINADO = 'D'; //Centro Eliminado
	
	/** La constante ESTADO_ACTIVO. */
	private static final Character ESTADO_ACTIVO = 'A';
	
	/** La constante USUARIO_ACTIVO. */
	private static final Character USUARIO_ACTIVO = '1';
	
	/** La constante TIPO_OPERACION. */
	//Para insertar en el LOG
	private static final Character TIPO_OPERACION = 'B';
	
	/** La constante TIPO_FUNCIONALIDAD. */
	private static final String TIPO_FUNCIONALIDAD = "CENTRO GESTOR";
	
	/** el centro gestor manager. */
	//Declaracion de manager
	private CentroGestorManager centroGestorManager;
	
	/** el plantilla propios manager. */
	private PlantillaPropiosManager plantillaPropiosManager;
	
	/** el cuerpo escala manager. */
	//Para comprobar con estas entidades si se permite borrar al Centro
	private CuerpoEscalaManager cuerpoEscalaManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el alerta manager. */
	private AlertaManager alertaManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarCentroGestorSpring.class);

	/**
	 * Accion EliminarTituloOficialAction.
	 */
	public EliminarCentroGestorSpring() {
		try {
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			setPlantillaPropiosManager((PlantillaPropiosManager) getBean("plantillaPropiosManager"));
			setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			setUsuarioManager ((UsuarioManager) getBean ("usuarioManager"));
			setAlertaManager((AlertaManager)getBean ("alertaManager"));
		} catch (Exception e) {
			logger.error("Error EliminarCentroGestorSpring()",e );
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
 * Metodo doExecute de EliminarTituloOficialAction.
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
		
		try{
			SpringMessages errors = new SpringMessages();
			String resultado = "";
			String sUsernameLogin = "";
					
			String idCentroGestor =  this.getRequest().getParameter("id");
			
			if(idCentroGestor != null){//Dar de Baja el Centro Gestor
				//Obtenemos el centro Gestor que deseamos eliminar
				CentroGestorBean centroGestorBean;
				centroGestorBean = centroGestorManager.obtenerCentroGestor(Integer.valueOf(idCentroGestor));
				centroGestorBean.setEstado(ESTADO_ELIMINADO); //Para Eliminarlo
				//Obtenemos el Usuario que esta logueado en la aplicacion para insertarlo en el log
				UsuarioQuery usuarioQuery = new UsuarioQuery();
				try{
					UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
					sUsernameLogin = usuarioBean.getLogin();
					usuarioQuery.setLogin(sUsernameLogin);
				}catch(Exception e){
					logger.error("Error EliminarCentroGestorSpring() - recuperarUsuarioSesion",e );
					new BusinessException("exception.recuperarUsuarioSesion");
					return "error";
				}
				
				UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
				Usuario usuario = usuarioManager.toUsuario(usuarioBean);
				
				
				
				
				boolean bEliminaUsuario = false;
				boolean bEliminaCuerpoEscala = false;
				boolean bEliminaAlerta = false;
				
				//Si vienen a falso, no se podra eliminar porque tiene campos asociados.
				bEliminaUsuario = compruebaUsuario(centroGestorBean, errors);
				bEliminaCuerpoEscala = compruebaCuerpoEscala(centroGestorBean, errors);
				bEliminaAlerta = compruebaAlerta(centroGestorBean, errors);
					
				if (bEliminaUsuario &&  bEliminaCuerpoEscala && bEliminaAlerta){
					
					//Eliminamos las PlantillasPropios asociadas al centro gestor
					PlantillaPropiosQuery plantillaPropiosQuery = new PlantillaPropiosQuery();
					plantillaPropiosQuery.setTipoPlantilla('G');
					CentroGestorQuery centroGestorQueryAux = new CentroGestorQuery();
					centroGestorQueryAux.setId(centroGestorBean.getId());
					plantillaPropiosQuery.setCentroGestor(centroGestorQueryAux);
					
					//buscamos las plantillas propias y las borramos
					borrarPlantillas(plantillaPropiosQuery);
					
					//Eliminamos el Centro Gestor Una vez validado
					centroGestorManager.modificarEstadoCentroGestor(centroGestorBean); //Modificamos el registro cambiando el Estado a 'D'
					//Insertamos en el log la modificacion
					LogGenerico logGenerico = new LogGenerico();
					Date dHoy = new Date();
				
					logGenerico.setFecha(dHoy);
					logGenerico.setTipoOperacion(TIPO_OPERACION);
					logGenerico.setTipoFuncionalidad(TIPO_FUNCIONALIDAD);
					logGenerico.setUsuario(usuario); 
					logGenerico.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.centroGestor.detalleOperacionBaja") + " " + centroGestorBean.getId());
					logGenerico.setIdTablaOrigen(centroGestorBean.getId().longValue());
					logGenericoManager.guardarLogGenerico(logGenerico);
				}
				else{
					
					setRequestAttribute("org.apache.spring.ERROR", errors);
					return "errorEliminarCentroGestor";
				}
				
				String mensaje = RESOURCE_BUNDLE.getString("field.centroGestor.eliminarCentroGestorConfirmacion");
				String titulo = RESOURCE_BUNDLE.getString("field.centroGestor.centroGestorMantenimientoTitulo");			
				
				setRequestAttribute("titulo",titulo);
				setRequestAttribute("mensaje",mensaje);
				setRequestAttribute("accion","/spring/buscarTituloOficial");
				
				resultado = "success";
			}else{
				resultado = "error";
			}
			return resultado;
		
	}catch(Exception e){		
		this.getRequest().setAttribute("descripcionError", e.getMessage());
		logger.error("Error EliminarCentroGestorSpring- doExecute",e );
		return "nosuccess";
	}
	}
	
	/**
	 * Borrar plantillas.
	 *
	 * @param plantillaPropiosQuery el plantilla propios query
	 */
	private void borrarPlantillas(PlantillaPropiosQuery plantillaPropiosQuery) {
		ArrayList<PlantillaPropiosBean> listaPlantillaPropios = plantillaPropiosManager.buscarPlantillaPropiosAll(plantillaPropiosQuery);
		if(listaPlantillaPropios!=null && !listaPlantillaPropios.isEmpty()){
			for(int i=0; i<listaPlantillaPropios.size();i++){
				plantillaPropiosQuery.setIdPlantillaPropios(Long.valueOf(listaPlantillaPropios.get(i).getId()));
				plantillaPropiosManager.borrarPlantillasPropios(plantillaPropiosQuery);
			}
		}
	}

	/**
	 * Comprueba usuario.
	 *
	 * @param centroGestorBean el centro gestor bean
	 * @param errors el errors
	 * @return verdadero, si tiene exito
	 */
	//Comprueba que el Centro Gestor que queremos eliminar no se encuentra en Usuarios
	private boolean compruebaUsuario (CentroGestorBean centroGestorBean, SpringMessages errors){	

		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.addCentroGestorIn(centroGestorBean.getId());
		usuarioQuery.setEstado(USUARIO_ACTIVO);
		
		
		List<UsuarioBean> usuario = null;
		usuario = usuarioManager.buscarUsuarios(usuarioQuery);
		//si la consulta ha devuelto algun usuario, el centro esta asociado a ellos.
		if (usuario.size() > 0 )
		{ 	
		
			SpringMessage error = new SpringMessage("field.centroGestor.errores.usuario", centroGestorBean.getDescripcion());
			errors.add("errorUsuario", error);
			this.setRequestAttribute("errorUsuario", "true");
			
			for (int i=0; i < usuario.size();i++){
				error = new SpringMessage("field.centroGestor.errores.usuario1", (!StringUtils.isEmpty(usuario.get(i).getLogin())?usuario.get(i).getLogin():"")
						, (!StringUtils.isEmpty(usuario.get(i).getNif())?usuario.get(i).getNif():"")
						, (!StringUtils.isEmpty(usuario.get(i).getNombre())?usuario.get(i).getNombre():""));
				errors.add("errorUsuario1", error);
			}
			
			return false;// No se puede Eliminar
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * Comprueba cuerpo escala.
	 *
	 * @param centroGestorBean el centro gestor bean
	 * @param errors el errors
	 * @return verdadero, si tiene exito
	 */
	private boolean compruebaCuerpoEscala (CentroGestorBean centroGestorBean, SpringMessages errors){	

		CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
		cuerpoEscalaQuery.addCentroGestorIn(centroGestorBean.getId());
		cuerpoEscalaQuery.setEstado(ESTADO_ACTIVO);
		List<CuerpoEscalaBean> lCuerpoEscala = null;
		lCuerpoEscala = cuerpoEscalaManager.buscarCuerpoEscalaAll(cuerpoEscalaQuery);
		//si la consulta ha devuelto algun Cuerpo_Escala, el centro esta asociado a ellos.
		if (lCuerpoEscala.size() > 0 )
		{ 			
			SpringMessage error = new SpringMessage("field.centroGestor.errores.cuerpoEscala", centroGestorBean.getDescripcion());
				
			errors.add("errorCuerpoEscala", error);
			this.setRequestAttribute("errorCuerpoEscala", "true");
			
			for (int i=0; i < lCuerpoEscala.size();i++){
				error = new SpringMessage("field.centroGestor.errores.cuerpoEscala1",  (!StringUtils.isEmpty(lCuerpoEscala.get(i).getCodigo())?lCuerpoEscala.get(i).getCodigo():"")
						, (!StringUtils.isEmpty(lCuerpoEscala.get(i).getDescripcion()))?lCuerpoEscala.get(i).getDescripcion():"");
				errors.add("errorCuerpoEscala1", error);
			}
			
			return false; //No se puede Eliminar
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * Comprueba alerta.
	 *
	 * @param centroGestorBean el centro gestor bean
	 * @param errors el errors
	 * @return verdadero, si tiene exito
	 */
	private boolean compruebaAlerta (CentroGestorBean centroGestorBean, SpringMessages errors){	

		AlertaQuery alertaQuery = new AlertaQuery();
		alertaQuery.addCentroGestorIn(centroGestorBean.getId());
		alertaQuery.setEstado(ESTADO_ACTIVO);

		
		List<AlertaBean> lAlerta = null;
		lAlerta = alertaManager.buscarAlertaAll(alertaQuery);
		//si la consulta ha devuelto alguna Alerta, el centro esta asociado a ellos.
		if (lAlerta.size() > 0 )
		{ 			
			SpringMessage error = new SpringMessage("field.centroGestor.errores.Alerta", centroGestorBean.getDescripcion());			
			errors.add("errorAlerta", error);
			this.setRequestAttribute("errorAlerta", "true");
			
			for (int i=0; i < lAlerta.size();i++){
				error = new SpringMessage("field.centroGestor.errores.Alerta1",  lAlerta.get(i).getId());
				errors.add("errorAlerta1", error);
			}
			
			return false; //No se puede Eliminar
		}
		else
		{
			return true;
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
 * Obtiene el plantilla propios manager.
 *
 * @return the plantillaPropiosManager
 */
public PlantillaPropiosManager getPlantillaPropiosManager() {
	return plantillaPropiosManager;
}

/**
 * Establece el plantilla propios manager.
 *
 * @param plantillaPropiosManager            the plantillaPropiosManager to set
 */
public void setPlantillaPropiosManager(PlantillaPropiosManager plantillaPropiosManager) {
	this.plantillaPropiosManager = plantillaPropiosManager;
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
}
