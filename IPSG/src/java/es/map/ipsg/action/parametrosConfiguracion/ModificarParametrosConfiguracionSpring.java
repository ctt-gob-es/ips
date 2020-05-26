package es.map.ipsg.action.parametrosConfiguracion;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ipsg.bean.ContadorNumSolicitudBean;
import es.map.ipsg.bean.ParametrosConfiguracionBean;
import es.map.ipsg.form.ParametrosConfiguracionForm;
import es.map.ipsg.manager.ContadorNumSolicitudManager;
import es.map.ipsg.manager.ParametroConfiguracionManager;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.CryptUtil;

/**
 * El Class ModificarParametrosConfiguracionSpring.
 */
public class ModificarParametrosConfiguracionSpring extends AbstractSpring{
	
	/** La constante MESSAGE_RESOURCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarParametrosConfiguracionSpring.class);
	
	/** el parametro configuracion manager. */
	//Declaracion de los managers
	private ParametroConfiguracionManager parametroConfiguracionManager;
	
	/** el contador num solicitud manager. */
	private ContadorNumSolicitudManager contadorNumSolicitudManager;

	/**
	 * Crea una nueva modificar parametros configuracion spring.
	 */
	public ModificarParametrosConfiguracionSpring() {
		try {			
			setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));		
			setContadorNumSolicitudManager((ContadorNumSolicitudManager) getBean("contadorNumSolicitudManager"));
		} catch (Exception e) {
			logger.warn(e);	
			logger.error("Error ModificarParametrosConfiguracionSpring:",e);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_parametros = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.actualizarParametrosConfiguracion");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_parametros);
		
		String resultado="";
	try{	
		ParametrosConfiguracionForm formulario;
		formulario = (ParametrosConfiguracionForm) form;
		
		String[] id = formulario.getId();
		String[] valorCampo = formulario.getValorCampos();
		
		if(id!=null){
			//Creamos el objeto parametros configuracion y lo cargamos con los datos del formulario	
			boolean isEJB=false;
			for (int i = 0; i < id.length; i++) {
				
				ParametrosConfiguracionBean parametrosConfiguracionBean = parametroConfiguracionManager.obtenerParametrosConfiguracion(Integer.parseInt(id[i]));
				
				if(parametrosConfiguracionBean!=null){
					if(parametrosConfiguracionBean.getId()==Constantes.PARAMETRO_CONFIGURACION_ID_METODO_SOLICITUD){
						parametrosConfiguracionBean.setValorCampo(valorCampo[i]);				
						parametroConfiguracionManager.modificarParametroConfiguracion(parametrosConfiguracionBean);
						if(valorCampo[i].equals("EJB")){
							isEJB = true;
						}
					}else if(parametrosConfiguracionBean.getId()!=Constantes.PARAMETRO_CONFIGURACION_ID_PASSWORD){
						parametrosConfiguracionBean.setValorCampo(valorCampo[i]);				
						parametroConfiguracionManager.modificarParametroConfiguracion(parametrosConfiguracionBean);
					}else{
						formulario.setValorContraseniaNueva(valorCampo[i]);
					}
				}
			}
			
			if(formulario.getContador()!=null && !"".equals(formulario.getContador()) && !isEJB){
				ContadorNumSolicitudBean contadorNumSolicitudBean;
				contadorNumSolicitudBean = contadorNumSolicitudManager.obtenerContadorNumSolicitud();
				Long contador = Long.valueOf(formulario.getContador());
				int maximo = contadorNumSolicitudBean.getMaximo();
				
				if(contador<maximo){				
					contadorNumSolicitudBean.setContador(contador);
					
					contadorNumSolicitudManager.modificarContadorSolicitud(contadorNumSolicitudBean);
				}
				formulario.setContador(null);
			}
			
			if(formulario.getValorContraseniaNueva()!= null && !formulario.getValorContraseniaNueva().equals("")){
				// TODO:añadir modificar contraseña
				logger.info("entra en la contrasenia");
				
				ParametrosConfiguracionQuery parametroConfiguracionQuery = new ParametrosConfiguracionQuery();
				
				parametroConfiguracionQuery.setNombreCampo(Constantes.WS_TOKEN);
				ParametrosConfiguracionBean paramToken =parametroConfiguracionManager.buscarParametroConfiguracionUnico(parametroConfiguracionQuery);
				String token = paramToken.getValorCampo();
				
				CryptUtil cryptUtil = new CryptUtil();
				
				String passwordNuevaCifrada = null;
				String passwordNueva = formulario.getValorContraseniaNueva();
				if(token != null)
				{	
					String passSalt = passwordNueva.concat(token);
					passwordNuevaCifrada = cryptUtil.cifrar(passSalt);
					
					logger.info("MD5(passwordNueva+token):"+passwordNuevaCifrada);
					
					parametroConfiguracionQuery= new ParametrosConfiguracionQuery();
					parametroConfiguracionQuery.setNombreCampo(Constantes.WS_PASS);
					
					paramToken =parametroConfiguracionManager.buscarParametroConfiguracionUnico(parametroConfiguracionQuery);
					

					ParametrosConfiguracionBean paramModificar = new ParametrosConfiguracionBean();
					paramModificar.setValorCampo(passwordNuevaCifrada);
					paramModificar.setDescripcion(paramToken.getDescripcion());
					paramModificar.setId(paramToken.getId());
					paramModificar.setNombreCampo(paramToken.getNombreCampo());
					parametroConfiguracionManager.modificarParametroConfiguracion(paramModificar);
					
				}
				else
				{
					logger.error(RESOURCE_BUNDLE.getString("field.error.recuperarToken"));
					this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.error.recuperarToken"));
					return "nosuccess";
				}	
				
				
			}	
			
			
			String mensaje = RESOURCE_BUNDLE.getString("field.parametroConfiguracion.actualizarParametroConfirmacion");
			String titulo = RESOURCE_BUNDLE.getString("field.parametroConfiguracion.tituloActualizar");
			
			setRequestAttribute("titulo",titulo);
			setRequestAttribute("mensaje",mensaje);
			setRequestAttribute("accion","/spring/buscarParametrosConfiguracion");
			
			resultado = "success";
		}
	
	}catch(Exception eGenerico){
		logger.error("Error ModificarParametrosConfiguracionSpring - doExecute:",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return resultado;
	}
	
	/**
	 * Obtiene el parametro configuracion manager.
	 *
	 * @return the parametroConfiguracionManager
	 */
	public ParametroConfiguracionManager getParametroConfiguracionManager() {
		return parametroConfiguracionManager;
	}

	/**
	 * Establece el parametro configuracion manager.
	 *
	 * @param parametroConfiguracionManager the parametroConfiguracionManager to set
	 */
	public void setParametroConfiguracionManager(
			ParametroConfiguracionManager parametroConfiguracionManager) {
		this.parametroConfiguracionManager = parametroConfiguracionManager;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Obtiene el contador num solicitud manager.
	 *
	 * @return el contador num solicitud manager
	 */
	public ContadorNumSolicitudManager getContadorNumSolicitudManager() {
		return contadorNumSolicitudManager;
	}

	/**
	 * Establece el contador num solicitud manager.
	 *
	 * @param contadorNumSolicitudManager el nuevo contador num solicitud manager
	 */
	public void setContadorNumSolicitudManager(ContadorNumSolicitudManager contadorNumSolicitudManager){
		this.contadorNumSolicitudManager = contadorNumSolicitudManager;
	}
	
	
}
