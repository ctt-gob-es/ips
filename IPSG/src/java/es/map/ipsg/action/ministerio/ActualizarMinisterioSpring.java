package es.map.ipsg.action.ministerio;

import java.util.Date;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.util.DateUtil;
import es.map.ipsg.bean.MinisterioBean;
import es.map.ipsg.form.MinisterioForm;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.util.Constantes;



/**
 * El Class ActualizarMinisterioSpring.
 */
public class ActualizarMinisterioSpring extends AbstractSpring {
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ActualizarMinisterioSpring.class);
	
	/** el ministerio manager. */
	private MinisterioManager ministerioManager;
	
	/**
	 * Crea una nueva actualizar ministerio spring.
	 */
	public ActualizarMinisterioSpring() {
		try {
			setMinisterioManager((MinisterioManager) getBean("ministeriosManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ActualizarMinisterioSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_ministerio = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.ministerio");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_ministerio);
		
		getLogger().debug("ActualizarMinisterioSpring -start");
		MinisterioForm formulario = (MinisterioForm) form;
		try{
			String busqueda = formulario.getSubmit();
			
				if("Modificar".equals(busqueda) && formulario.getCodigo() != null && !"".equals(formulario.getCodigo())){
					
							MinisterioBean ministerioBean = cargarFormulario(formulario);
							ministerioManager.actualizarMinisterio(ministerioBean);
			
				}
			
			String titulo = "";
			String mensaje = "";
			if("Modificar".equals(busqueda)){
				mensaje = RESOURCE_BUNDLE.getString("field.ministerio.actualizacion.mensaje");
				titulo = RESOURCE_BUNDLE.getString("field.ministerio.actualizacion.titulo");
				setRequestAttribute("accion","/spring/buscarMinisterios");
			}else{
				mensaje = RESOURCE_BUNDLE.getString("field.ministerio.actualizacion.mensajeError");
				titulo = RESOURCE_BUNDLE.getString("field.ministerio.actualizacion.titulo");
				setRequestAttribute("accion","/spring/modificarMinisterio?id= "+formulario.getId());
			}
			setRequestAttribute("titulo",titulo);
			setRequestAttribute("mensaje",mensaje);
			
			getLogger().debug("ActualizarMinisterioSpring -end");
			return "success";
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error ActualizarMinisterioSpring - doExecute:",e);
			return "nosuccess";
		}
			
	}

	/**
	 * Cargar formulario.
	 *
	 * @param formulario el formulario
	 * @return el ministerio bean
	 */
	private MinisterioBean cargarFormulario(MinisterioForm formulario) {
		MinisterioBean ministerioAux = new MinisterioBean();
		String fechaForm = formulario.getFechaSustitucion();
		Date fecha = new Date();
			
		try{
			if(fechaForm != null && !"".equals(fechaForm)){
				fecha = DateUtil.parse(fechaForm,"dd/MM/yyyy");
				ministerioAux.setFechaSustitucion(fecha);
			}
			ministerioAux.setId(formulario.getId());
			ministerioAux.setCodigo(formulario.getCodigo());
			ministerioAux.setDescripcion(formulario.getDescripcion());
			ministerioAux.setEstado(Constantes.MINISTERIO_ESTADO_ACTIVO);
			ministerioAux.setSiglas(formulario.getSiglas());
			ministerioAux.setUrl(formulario.getUrl());
			ministerioAux.setIdMinisterioPrevio(formulario.getIdMinisterioPrevio());
			if(formulario.getVisibilidad() != null)
			{	
				ministerioAux.setVisibilidad(formulario.getVisibilidad());
			}
			else
			{
				ministerioAux.setVisibilidad(false);
			}	
			return ministerioAux;
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error ActualizarMinisterioSpring - cargarFormulario :",e);
			return null;
		}		
	}

	/**
	 * Obtiene el ministerio manager.
	 *
	 * @return el ministerio manager
	 */
	public MinisterioManager getMinisterioManager() {
		return ministerioManager;
	}

	/**
	 * Establece el ministerio manager.
	 *
	 * @param ministerioManager el nuevo ministerio manager
	 */
	public void setMinisterioManager(MinisterioManager ministerioManager) {
		this.ministerioManager = ministerioManager;
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
