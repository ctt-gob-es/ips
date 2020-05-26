package es.map.ipsg.action.categoria;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.bean.CategoriaBean;
import es.map.ipsg.form.CategoriaForm;
import es.map.ipsg.manager.CategoriaManager;

/**
 * El Class VerModificarCategoriaSpring.
 */
public class VerModificarCategoriaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerModificarCategoriaSpring.class);
	
	/** el categoria manager. */
	private CategoriaManager categoriaManager;
		
	/**
	 * Crea una nueva ver modificar categoria spring.
	 */
	public VerModificarCategoriaSpring() {
		try {
			setCategoriaManager((CategoriaManager) getBean("categoriaManager"));			
			
		} catch (Exception e) {
			logger.error("Error VerModificarCategoriaSpring():",e );
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {		
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_Categoria = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.categoria");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_Categoria);
		logger.info("Ver Modificar Categoria -start");	
		String resultado;
	try{
		
		CategoriaForm formulario = (CategoriaForm ) form;
		
		String idCategoria = this.getRequest().getParameter("id");
		
		if(idCategoria!=null && formulario.getAccion() == null){			
			CategoriaBean categoriaBean = categoriaManager.obtenerCategoria(Integer.valueOf(idCategoria));			

			this.setRequestAttribute("categoria", categoriaBean);

			resultado = "success";
		}else{
			if (formulario.getAccion() != null){				
				resultado = "success";
			}else{
				resultado = "error";
			}
		}
	
	}catch(Exception eGenerico){
		logger.error("Error VerModificarCategoriaSpring - doExecute : "+ eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		logger.info("Ver Modificar Categoria -end");	
		return resultado;
	}

	/**
	 * Obtiene el categoria manager.
	 *
	 * @return the categoriaManager
	 */
	public CategoriaManager getCategoriaManager() {
		return categoriaManager;
	}

	/**
	 * Establece el categoria manager.
	 *
	 * @param categoriaManager the categoriaManager to set
	 */
	public void setCategoriaManager(CategoriaManager categoriaManager) {
		this.categoriaManager = categoriaManager;
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
