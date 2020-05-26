package es.map.ipsg.action.titulooficial;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.TituloOficialQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.TituloOficialBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.TituloOficialForm;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.TituloOficialManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * Acción CrearTituloOficialAction.
 *
 * @author amartinl
 */
public class CrearTituloOficialSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CrearTituloOficialSpring.class);
	
	/** La constante ESTADO_ACTIVO. */
	private static final Character ESTADO_ACTIVO = 'A';

	/** el titulo oficial manager. */
	private TituloOficialManager tituloOficialManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
		
	/**
	 * Acción CrearTituloOficialAction.
	 */
	public CrearTituloOficialSpring() {
		try {
			setTituloOficialManager((TituloOficialManager) getBean("tituloOficialManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error CrearTituloOficialSpring - crear constructor",e);
		}
	}

/**
 * Método doExecute de CrearTituloOficialAction.
 *
 * @param form SpringForm Pasa los campos del formulario
 * @return resultado String Si todo va bien devuelve success
 * @throws Exception Exception
 */
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablabase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva",menu_tablabase);
		String subMenu_tituloOficial = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.tituloOficial");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_tituloOficial);
		
		getLogger().debug("CrearTituloOficialSpring -start");
		TituloOficialForm formulario = (TituloOficialForm) form;
		TituloOficialBean tituloOficialBean = new TituloOficialBean();
		try{
			//Alta de Título Oficial
			tituloOficialBean.setDescripcion(formulario.getDescripcion());
			tituloOficialBean.setCodigo(formulario.getCodigo());
			tituloOficialBean.setEstado(ESTADO_ACTIVO); //Todos los títulos nuevos tendrán estarán Activo
			tituloOficialBean.setVisibilidad(formulario.getVisibilidad());

			Integer result = tituloOficialManager.guardarTituloOficial(tituloOficialBean);
			
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			generarRegistroLogGenerico(usuarioBean.getLogin(),tituloOficialBean.getDescripcion(),new Long(result));
			
			String mensaje;
			String resultado;
			
			if(result.intValue() > 0){
				resultado="success";
				mensaje = RESOURCE_BUNDLE.getString("field.titulo.crearTituloConfirmacion");
				formulario.setDescripcion(""); //Al dar de alta y volver a la búsqueda dejamos en blanco la descripción
			}else{
				resultado="error";
				mensaje = RESOURCE_BUNDLE.getString("field.titulo.crearTituloError");
			}
			
			String titulo = RESOURCE_BUNDLE.getString("field.titulo.tituloCrearTitulo");
			
			setRequestAttribute("titulo", titulo);
			setRequestAttribute("mensaje", mensaje);
			setRequestAttribute("accion", "/spring/buscarTituloOficial");
	
			return resultado;
			
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error CrearTituloOficialSpring - doExecute",e);
			return "nosuccess";
		}
	}
	

	
	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param tituloDesc el titulo desc
	 * @param resultado el resultado
	 */
	public void generarRegistroLogGenerico(String username, String tituloDesc, Long resultado){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_TITULO);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_ALTA);
		logGenericoBean.setDetalleOperacion("Alta del titulo oficial " + tituloDesc);
		logGenericoBean.setIdTablaOrigen(resultado);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}

	/**
	 * Método getTituloOficialManager.
	 *
	 * @return tituloOficialManager TituloOficialManager
	 */
	public TituloOficialManager getTituloOficialManager() {
		return tituloOficialManager;
	}

	/**
	 * Método setTituloOficialManager.
	 *
	 * @param tituloOficialManager TituloOficialManager
	 */
	public void setTituloOficialManager(TituloOficialManager tituloOficialManager) {
		this.tituloOficialManager = tituloOficialManager;
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
	 * Obtiene el log generico manager.
	 *
	 * @return el log generico manager
	 */
	public LogGenericoManager getLogGenericoManager() {
		return logGenericoManager;
	}

	/**
	 * Establece el log generico manager.
	 *
	 * @param logGenericoManager el nuevo log generico manager
	 */
	public void setLogGenericoManager(LogGenericoManager logGenericoManager) {
		this.logGenericoManager = logGenericoManager;
	}
	

}
