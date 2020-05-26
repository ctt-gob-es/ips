package es.map.ipsg.action.adaptacionesDiscapacidad;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.DiscapacidadBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.DiscapacidadForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.manager.DiscapacidadManager;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class VerModificarDiscapacidadSpring.
 */
@SuppressWarnings("rawtypes")
public class VerModificarDiscapacidadSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarDiscapacidadSpring.class);

	/** el discapacidad manager. */
	private DiscapacidadManager discapacidadManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el usuario manager. */
//	private UsuarioManager usuarioManager;
	
	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_discapacidad = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.discapacidad");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_discapacidad);
		
		setDiscapacidadManager((DiscapacidadManager) getBean("discapacidadManager"));
		setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
		
		getLogger().debug("VerAltaDiscapacidadSpring -start");
		String resultado;
		
		UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
		/*INI-TRA042*/		
		logger.info("Perfil usuario: "+usuarioBean.getIdPerfil());
		setRequestAttribute("rol", usuarioBean.getIdPerfil());
		/*FIN-TRA042*/		
		try{
		
			DiscapacidadForm formulario = (DiscapacidadForm) form;
			
			String idDiscapacidad = this.getRequest().getParameter("id");
			
			if(idDiscapacidad!=null){
				
				DiscapacidadBean discapacidadBean = discapacidadManager.obtenerDiscapacidad(Integer.valueOf(idDiscapacidad));
				logger.info(formulario.getAccion());
			
				if(!"Modificar".equals(formulario.getAccion())){
					formulario.setDescripcion(discapacidadBean.getDescripcion().toString());
					
					doExecuteAux(formulario,discapacidadBean);
				
					if(discapacidadBean.getVisibilidad() != null && !discapacidadBean.getVisibilidad().toString().equals(""))
					{
						formulario.setVisibilidad(discapacidadBean.getVisibilidad());
					}
					else
					{
						formulario.setVisibilidad(false);
					}
					
					if (discapacidadManager.modificarDiscapacidad(discapacidadBean))
					
					this.setRequestAttribute("discapacidad", discapacidadBean);	
				}
				/*INI-TRA042*/			
				String sPerfilUsuario = comprobarPerfilUsuario(usuarioBean);

				if(sPerfilUsuario.equalsIgnoreCase(Constantes.ROL_GESTOR))
				{
					List<CentroGestorBean> listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuarioBean.getId());
					this.setRequestAttribute("listaCentrosGestores",listaCentrosGestores);
				}
				/*FIN-TRA042*/
			}
			
			resultado = "success";
			
			getLogger().debug("VerAltaDiscapacidadSpring -end");
			return resultado;
			
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error VerModificarDiscapacidadSpring() - doExecute :",e);
			return "nosuccess";
		}
	}

	/*INI-TRA042*/
	/**
	 * Comprobar perfil usuario.
	 *
	 * @param usuarioBean2 el id usuario
	 * @return el string
	 */
	private String comprobarPerfilUsuario(UsuarioBean usuarioBean) {
		String sPerfil = "";
		
		if(usuarioBean.getIdPerfil() != null &&   usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_CONSULTOR))
		{
			sPerfil = Constantes.ROL_CONSULTOR;
		}
		if(usuarioBean.getIdPerfil() != null &&   usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_SOPORTE))
		{
			sPerfil = Constantes.ROL_SOPORTE;
		}
		if(usuarioBean.getIdPerfil() != null && usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_GESTOR))
		{
			sPerfil = Constantes.ROL_GESTOR;
		}
		if(usuarioBean.getIdPerfil() != null && usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_ADMINISTRADOR))
		{
			sPerfil = Constantes.ROL_ADMINISTRADOR;
		}

		return sPerfil;

	}
	/*FIN-TRA042*/
	
	/**
	 * Do execute aux.
	 *
	 * @param formulario el formulario
	 * @param discapacidadBean el discapacidad bean
	 */
	public void doExecuteAux(DiscapacidadForm formulario, DiscapacidadBean discapacidadBean) {
		if(discapacidadBean.getIdCentroGestor() != null){
			formulario.setCentroGestor(discapacidadBean.getIdCentroGestor().toString());
			formulario.setDsCentroGestor(discapacidadBean.getDesCentroGestor());
		}
	}
	
	/**
	 * Obtiene el centro gestor manager.
	 *
	 * @return el centro gestor manager
	 */
	public CentroGestorManager getCentroGestorManager() {
		return centroGestorManager;
	}

	/**
	 * Establece el centro gestor manager.
	 *
	 * @param centroGestorManager el nuevo centro gestor manager
	 */
	public void setCentroGestorManager(CentroGestorManager centroGestorManager) {
		this.centroGestorManager = centroGestorManager;
	}

	/**
	 * Obtiene el discapacidad manager.
	 *
	 * @return the discapacidadaManager
	 */
	public DiscapacidadManager getDiscapacidadManager() {
		return discapacidadManager;
	}

	/**
	 * Establece el discapacidad manager.
	 *
	 * @param discapacidadaManager the discapacidadaManager to set
	 */
	public void setDiscapacidadManager(DiscapacidadManager discapacidadaManager) {
		this.discapacidadManager = discapacidadaManager;
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
	 * @param usuarioCentrogestorManager the usuarioCentrogestorManager to set
	 */
	/*INI-TRA042*/
	public void setUsuarioCentrogestorManager(UsuarioCentrogestorManager usuarioCentrogestorManager) {
		this.usuarioCentrogestorManager = usuarioCentrogestorManager;
	}
	/*FIN-TRA042*/
}
