package es.map.ipsg.action.motivoReduccionTasa;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.bean.MotivoReduccionTasaBean;
import es.map.ipsg.form.MotivoReduccionTasaForm;
import es.map.ipsg.manager.MotivoReduccionTasaManager;

/**
 * El Class VerModificarMotivoReduccionTasaSpring.
 */
public class VerModificarMotivoReduccionTasaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarMotivoReduccionTasaSpring.class);
	
	/** el motivo reduccion tasa manager. */
	private MotivoReduccionTasaManager motivoReduccionTasaManager;
	
		
	/**
	 * Crea una nueva ver modificar motivo reduccion tasa spring.
	 */
	public VerModificarMotivoReduccionTasaSpring() {
		try {
			setMotivoReduccionTasaManager((MotivoReduccionTasaManager)getBean("motivoReduccionTasaManager"));
		} catch (Exception e) {
			logger.error("Error VerModificarMotivoReduccionTasaSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_motivos = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.motivoReduccion");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_motivos);
		
		String resultado;
	try{	
		MotivoReduccionTasaForm formulario = (MotivoReduccionTasaForm) form;	
				
		String idMotivo = this.getRequest().getParameter("id");
		
		if(idMotivo!=null && formulario.getAccion() == null){
			
			
			MotivoReduccionTasaBean motivoBean = motivoReduccionTasaManager.obtenerMotivoReduccionTasa(Integer.valueOf(idMotivo));
			
			
		formulario.setDescripcion(motivoBean.getDescripcion().toString());
		formulario.setTextoExplicativo(motivoBean.getTextoExplicativo().toString());
		formulario.setPorcentajeDescuento(motivoBean.getPorcentajeDescuento().toString());
		formulario.setId(idMotivo);	
		formulario.setCodigo(motivoBean.getCodigo());
		if(motivoBean.getVisibilidad() != null)
		{	
			formulario.setVisibilidad(motivoBean.getVisibilidad());
		}
		else
		{
			formulario.setVisibilidad(false);
		}	
		this.setRequestAttribute("motivo", motivoBean);				
		}
				
		resultado = "success";
		
	}catch(Exception eGenerico){
		logger.error("Error VerModificarMotivoReduccionTasaSpring - doExecute:",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return resultado;
	}

	


	/**
	 * Obtiene el motivo reduccion tasa manager.
	 *
	 * @return the motivoReduccionTasaManager
	 */
	public MotivoReduccionTasaManager getMotivoReduccionTasaManager() {
		return motivoReduccionTasaManager;
	}

	/**
	 * Establece el motivo reduccion tasa manager.
	 *
	 * @param motivoReduccionTasaManager the motivoReduccionTasaManager to set
	 */
	public void setMotivoReduccionTasaManager(
			MotivoReduccionTasaManager motivoReduccionTasaManager) {
		this.motivoReduccionTasaManager = motivoReduccionTasaManager;
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
