package es.map.ipsg.action.titulooficial;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.bean.TituloOficialBean;
import es.map.ipsg.form.TituloOficialForm;
import es.map.ipsg.manager.TituloOficialManager;



/**
 * El Class VerModificarTituloOficialSpring.
 *
 * @author amartinl
 * Clase que implementa a VerModificarTituloOficialAction
 */
public class VerModificarTituloOficialSpring extends AbstractSpring {

	/** el titulo oficial manager. */
	//Declaracion de manager
	private TituloOficialManager tituloOficialManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarTituloOficialSpring.class);
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/**
	 * Método VerModificarTituloOficialAction.
	 */
	public VerModificarTituloOficialSpring() {
		try {
			setTituloOficialManager((TituloOficialManager) getBean("tituloOficialManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error VerModificarTituloOficialSpring - crear constructor",e);
		}
	}


	/**
	 * Método doExecute de VerModificarTituloOficialAction.
	 *
	 * @param form SpringForm
	 * @return  resultado String
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablabase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva",menu_tablabase);
		String subMenu_tituloOficial = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.tituloOficial");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_tituloOficial);
		
		getLogger().debug("VerModificarTituloOficialSpring -start");
		String resultado = "";
		String idTitulo = this.getRequest().getParameter("id");
		TituloOficialForm formulario;
		formulario = (TituloOficialForm) form;
		try{
			if (idTitulo != null){
				TituloOficialBean tituloOficialBean = new TituloOficialBean();
				logger.info(formulario.getAccion());
				if(!"Modificar".equals(formulario.getAccion())){
					tituloOficialBean = tituloOficialManager.obtenerTituloOficial(Integer.valueOf(idTitulo)); 
				}else{
					tituloOficialBean.setDescripcion(formulario.getDescripcion());
					int id=0;
					try{
						id= Integer.parseInt(formulario.getId());
					}catch(Exception e){
						logger.error("Error VerModificarTituloOficialSpring - parsear Id"+ id,e);
					}				
					tituloOficialBean.setId(id);
					tituloOficialBean.setCodigo(formulario.getCodigo());
					if(formulario.getVisibilidad() != null && !formulario.getVisibilidad().equals(""))
					{	
						tituloOficialBean.setVisibilidad(formulario.getVisibilidad());
					}
					else
					{
						tituloOficialBean.setVisibilidad(false);
					}	
				}
				
				
				this.setRequestAttribute("titulo", tituloOficialBean);	
				
			}else{
				resultado = "error";
			}
			resultado = "success";
			return resultado;
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error VerModificarTituloOficialSpring - doExecute",e);
			return "nosuccess";}
	}
			
	//Métodos set y get
	/**
	 * Método get de tituloOficialManager.
	 *
	 * @return tituloOficialManager TituloOficialManager
	 */
	public TituloOficialManager getTituloOficialManager() {
		return tituloOficialManager;
	}

	/**
	 * Método set de tituloOficialManager.
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
	
	
}
