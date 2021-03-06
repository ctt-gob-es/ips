package es.map.ipsg.action.provincias;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.ProvinciaQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.ProvinciaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.CrearProvinciasForm;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.ProvinciaManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class CrearProvinciaSpring.
 */
public class CrearProvinciaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CrearProvinciaSpring.class);
	
	/** La constante STRING_SUCCESS. */
	private static final String STRING_SUCCESS = "success";
	
	/** el provincia manager. */
	//Declaracion de manager
	private ProvinciaManager provinciaManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	
	/**
	 * Crea una nueva crear provincia spring.
	 */
	public CrearProvinciaSpring() {
		try {
			setProvinciaManager((ProvinciaManager) getBean("provinciaManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error CrearProvinciaSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_provincia = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.provincia");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_provincia);
		
		getLogger().debug("CrearProvinciaSpring -start");
		String accion = this.getRequest().getParameter("accion");
		CrearProvinciasForm formulario = (CrearProvinciasForm) form;
		String codigo = (!StringUtils.isEmpty(formulario.getCodigo()))?formulario.getCodigo().trim():"";
		String descripcion = (!StringUtils.isEmpty(formulario.getDescripcion()))?formulario.getDescripcion().trim():"";
		String resultado = STRING_SUCCESS;
		
		try{
			SpringMessages messages = new SpringMessages();
			
			if (StringUtils.isEmpty(accion)) {
				if ("".compareTo(codigo)==0){
					logger.info("Debe rellenar el campo codigo");
					messages.add("errorCodigoVacio",new SpringMessage("provincia.error.codigo.vacio"));
					resultado = "show";
				}
				if ("".compareTo(descripcion)==0){
					logger.info("Debe rellenar el campo descripcion");
					messages.add("errorProvinciaVacia",new SpringMessage("provincia.error.provincia.vacia"));
					resultado = "show";
				}
				if(existeCodigo(codigo)){
					SpringMessages errors = new SpringMessages();
					SpringMessage error = new SpringMessage("field.provincia.errorCodigoRepetido");
					errors.add("errorCodigo", error);
					saveErrors(this.getRequest(),errors);
					return "errorCodigo";
				}
				if(existeDescripcion(descripcion)){
					logger.info("La provincia ya existe");
					messages.add("errorProvinciaDuplicada",new SpringMessage("provincia.error.provincia.duplicada"));
					resultado = "show";
				}
			} else if (accion.equals(Character.toString(Constantes.OPERACION_ALTA))) {
				logger.info("Primer Acceso");
				resultado = "show";
			}

			if (resultado.compareTo(STRING_SUCCESS)==0){
				ProvinciaBean bean = new ProvinciaBean();
				bean.setCodigo(codigo);
				bean.setDescripcion(descripcion);
				bean.setEstado(formulario.getEstado());
				bean.setVisibilidad(formulario.getVisibilidad());
				
				ProvinciaBean provincias = null;
				ProvinciaQuery provinciaQuery = new ProvinciaQuery();
				provinciaQuery.setCodigo(codigo);
				provinciaQuery.setEstado(Constantes.PROVINCIA_ESTADO_ACTIVO);
				provincias = provinciaManager.buscarProvinciaUnique(provinciaQuery);
				int result = 0;
				if(provincias == null){
					result = provinciaManager.guardarProvincia(bean);
				}
				String mensaje;
				
				if(result>0){
					UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
					generarRegistroLogGenerico(usuarioBean.getLogin(),bean.getDescripcion(), Long.valueOf(result));
					resultado=STRING_SUCCESS;
					mensaje = "La provincia se ha guardado correctamente";
				}else{
					resultado="show";
					mensaje = "La provincia no se ha guardado correctamente";
				}
				
				String titulo = RESOURCE_BUNDLE.getString("field.provincia.alta.titulo");
				
				setRequestAttribute("titulo",titulo);
				setRequestAttribute("mensaje",mensaje);
				setRequestAttribute("accion","/spring/buscarProvincias");
			}else{
				saveErrors(this.getRequest(),messages);
			}
			getLogger().debug("CrearProvinciaSpring -end");
			return resultado;
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", "Se ha producido un error al guardar la provincia.");
			logger.error("Error CrearProvinciaSpring - doExecute:",e);
			return "nosuccess";
		}
	}
	

	
	/**
	 * Generar registro log generico.
	 *
	 * @param username el username
	 * @param provinciaDesc el provincia desc
	 * @param resultado el resultado
	 */
	public void generarRegistroLogGenerico(String username, String provinciaDesc, Long resultado){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_PROVINCIAS);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_ALTA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.provincia.detalleOperacionAlta") + " "  + provinciaDesc);
		logGenericoBean.setIdTablaOrigen(resultado);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}


	/**
	 * Existe codigo.
	 *
	 * @param codigo el codigo
	 * @return verdadero, si tiene exito
	 */
	public boolean existeCodigo(String codigo){
		List<ProvinciaBean> provincias = null;
		ProvinciaQuery provinciaQuery = new ProvinciaQuery();
		provinciaQuery.setCodigo(codigo);
		provinciaQuery.setEstado(Constantes.PROVINCIA_ESTADO_ACTIVO);
		
		provincias = provinciaManager.buscarProvinciaCombo(provinciaQuery);
		return provincias.size()==0 ? false:true;
	}
	
	/**
	 * Existe descripcion.
	 *
	 * @param descripcion el descripcion
	 * @return verdadero, si tiene exito
	 */
	public boolean existeDescripcion(String descripcion){
		List<ProvinciaBean> provincias = null;
		ProvinciaQuery provinciaQuery = new ProvinciaQuery();
		provinciaQuery.setDescripcion(descripcion);
		provinciaQuery.setEstado('A');
		
		provincias = provinciaManager.buscarProvinciaCombo(provinciaQuery);
		return provincias.size()==0 ? false:true;
	}


	/**
	 * Obtiene el provincia manager.
	 *
	 * @return el provincia manager
	 */
	public ProvinciaManager getProvinciaManager() {
		return provinciaManager;
	}

	/**
	 * Establece el provincia manager.
	 *
	 * @param provinciaManager el nuevo provincia manager
	 */
	public void setProvinciaManager(ProvinciaManager provinciaManager) {
		this.provinciaManager = provinciaManager;
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

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
}
