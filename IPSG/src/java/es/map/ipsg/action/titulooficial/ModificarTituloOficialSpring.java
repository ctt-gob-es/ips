package es.map.ipsg.action.titulooficial;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.TituloOficialQuery;
import es.map.ipsg.bean.TituloOficialBean;
import es.map.ipsg.form.TituloOficialForm;
import es.map.ipsg.manager.TituloOficialManager;
import es.map.ipsg.util.Constantes;

/**
 * Implementación de la clase ModificarTituloOficialAction.
 *
 * @author amartinl
 */
public class ModificarTituloOficialSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarTituloOficialSpring.class);
	
	/** el titulo oficial manager. */
	//Declaracion de manager
	private TituloOficialManager tituloOficialManager;

	/**
	 *  Método ModificarTituloOficialAction.
	 */
	public ModificarTituloOficialSpring() {
		try {
			setTituloOficialManager((TituloOficialManager) getBean("tituloOficialManager"));

		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ModificarTituloOficialSpring - crear constructor",e);
		}
	}

	/**
	 * doExecute.
	 *
	 * @param form SpringForm
	 * @return resultado String
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablabase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva",menu_tablabase);
		String subMenu_tituloOficial = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.tituloOficial");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_tituloOficial);
		
		getLogger().debug("ModificarTituloOficialSpring -start");
		String resultado;
		
			TituloOficialForm formulario = (TituloOficialForm) form;
			String idTitulo = formulario.getId();
			String accion = formulario.getAccion();
		try{
			if("VOLVER".equalsIgnoreCase(accion)){
				resultado = "list";
			}else if(idTitulo != null){//Modificacion de Titulo
				TituloOficialBean tituloOficialBean = new TituloOficialBean();
				//Recogemos del formulario los valores de los formularios
				tituloOficialBean.setId(Integer.valueOf(this.getRequest().getParameter("id")));
				tituloOficialBean.setDescripcion(formulario.getDescripcion());
				tituloOficialBean.setEstado(/*formulario.getEstado().charAt(0)*/Constantes.TITULOOFICIAL_ESTADO_ACTIVO);
				tituloOficialBean.setCodigo(formulario.getCodigo());
				if(formulario.getVisibilidad() != null && !formulario.getVisibilidad().equals(""))
				{	
					tituloOficialBean.setVisibilidad(formulario.getVisibilidad());
				}
				else
				{
					tituloOficialBean.setVisibilidad(false);
				}	
				tituloOficialManager.modificarTitulo(tituloOficialBean);
				
				String mensaje = RESOURCE_BUNDLE.getString("field.titulo.modificarTituloConfirmacion");
				String titulo = RESOURCE_BUNDLE.getString("field.titulo.tituloMantenimientoTitulo");			
				
				setRequestAttribute("titulo",titulo);
				setRequestAttribute("mensaje",mensaje);
				setRequestAttribute("accion","/spring/buscarTituloOficial");
				
				resultado = "success";
			}else{
				resultado = "error";
			}
	
			return resultado;
			
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error ModificarTituloOficialSpring - doExecute",e);
			return "nosuccess"; }
		
	}
	

	
	/**
	 * Toma el valor de tituloOficialManager.
	 *
	 * @return tituloOficialManager TituloOficialManager
	 */
	public TituloOficialManager getTituloOficialManager() {
		return tituloOficialManager;
	}

	/**
	 * Establecemos el valor de tituloOficialManager.
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
