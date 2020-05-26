package es.map.ipsg.action.especialidad;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ipsg.bean.CuerpoEscalaBean;
import es.map.ipsg.bean.EspecialidadBean;
import es.map.ipsg.form.EspecialidadForm;
import es.map.ipsg.manager.CuerpoEscalaManager;
import es.map.ipsg.manager.EspecialidadManager;

/**
 * El Class VerModificarEspecialidadSpring.
 */
public class VerModificarEspecialidadSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarEspecialidadSpring.class);

	/** el especialidad manager. */
	private EspecialidadManager especialidadManager;
	
	/** el cuerpo escala manager. */
	private CuerpoEscalaManager cuerpoEscalaManager;
		
	/**
	 * Crea una nueva ver modificar especialidad spring.
	 */
	public VerModificarEspecialidadSpring() {
		try {
			setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
			setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerposEscalaManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerModificarEspecialidadSpring():",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_especialidad = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.especialidad");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_especialidad);
		
		getLogger().debug("VerModificarEspecialidadSpring -start");
		String resultado;
		
		EspecialidadForm formulario = (EspecialidadForm) form;
		
		try{
			
			CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
			List<CuerpoEscalaBean> cuerpoEscala = cuerpoEscalaManager.buscarCuerposEscalaCombo(cuerpoEscalaQuery);
			
			String idEspecialidad = this.getRequest().getParameter("id");
			
			if(idEspecialidad!=null){			
				EspecialidadBean especialidadBean = new EspecialidadBean();
				if(!"Modificar".equals(formulario.getAccion())){
					especialidadBean = especialidadManager.obtenerEspecialidad(Integer.valueOf(idEspecialidad));
					
					formulario.setEstado(especialidadBean.getEstado().toString());
		
					formulario.setCodigo((!StringUtils.isEmpty(especialidadBean.getCodigo())?especialidadBean.getCodigo():""));
					formulario.setDescripcion((!StringUtils.isEmpty(especialidadBean.getDescripcion())?especialidadBean.getDescripcion():""));
					formulario.setCuerpoEscala((!StringUtils.isEmpty(especialidadBean.getCuerpoEscala())?especialidadBean.getCuerpoEscala():""));
					formulario.setVisibilidad(especialidadBean.getVisibilidad());
					
					if(especialidadBean.getIdCuerpoEscala() != null)
						formulario.setIdCuerpoEscala(especialidadBean.getIdCuerpoEscala());		
				}else{
					especialidadBean.setCodigo(formulario.getCodigo());
					especialidadBean.setDescripcion(formulario.getDescripcion());
					int id = comprobarEntero(formulario.getId());
					especialidadBean.setId(id);
				}
				this.setRequestAttribute("especialidad", especialidadBean);			
			}
			
			this.setRequestAttribute("cuerpo",cuerpoEscala);
			resultado = "success";
			getLogger().debug("VerModificarEspecialidadSpring -end");
			return resultado;
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error VerModificarEspecialidadSpring() - doExecute:",e);
			return "nosuccess";
		}
	}
	
	/**
	 * Comprobar entero.
	 *
	 * @param numero el numero
	 * @return el int
	 */
	private int comprobarEntero(String numero) {
		int resultado = 0;
		try{
			resultado = Integer.parseInt(numero);
		}catch(Exception e){
			logger.error("Error comprobarEntero() - error al parsear Id especialidad:"+ numero,e);
		}
		return resultado;
	}

	/**
	 * Obtiene el especialidad manager.
	 *
	 * @return el especialidad manager
	 */
	public EspecialidadManager getEspecialidadManager() {
		return especialidadManager;
	}

	/**
	 * Establece el especialidad manager.
	 *
	 * @param especialidadManager el nuevo especialidad manager
	 */
	public void setEspecialidadManager(EspecialidadManager especialidadManager) {
		this.especialidadManager = especialidadManager;
	}
	
	/**
	 * Obtiene el cuerpo escala manager.
	 *
	 * @return el cuerpo escala manager
	 */
	public CuerpoEscalaManager getCuerpoEscalaManager() {
		return cuerpoEscalaManager;
	}

	/**
	 * Establece el cuerpo escala manager.
	 *
	 * @param cuerpoEscalaManager el nuevo cuerpo escala manager
	 */
	public void setCuerpoEscalaManager(CuerpoEscalaManager cuerpoEscalaManager) {
		this.cuerpoEscalaManager = cuerpoEscalaManager;
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
