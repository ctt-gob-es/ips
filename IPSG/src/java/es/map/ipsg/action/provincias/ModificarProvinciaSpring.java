package es.map.ipsg.action.provincias;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.ProvinciaQuery;
import es.map.ipsg.bean.ProvinciaBean;
import es.map.ipsg.form.ModificarProvinciasForm;
import es.map.ipsg.manager.ProvinciaManager;
 
/**
 * El Class ModificarProvinciaSpring.
 */
public class ModificarProvinciaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarProvinciaSpring.class);
	
	/** el provincia manager. */
	//Declaracion de manager
	private ProvinciaManager provinciaManager;
	
	
	/**
	 * Crea una nueva modificar provincia spring.
	 */
	public ModificarProvinciaSpring() {
		try {
			setProvinciaManager((ProvinciaManager) getBean("provinciaManager"));
		} catch (Exception e) {
			logger.warn(e);
			e.printStackTrace();
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
		
		getLogger().debug("ModificarProvinciaSpring -start");
		ModificarProvinciasForm formulario = (ModificarProvinciasForm) form;
		int id = formulario.getId();
		String codigo = formulario.getCodigo().trim();
		String descripcion = formulario.getDescripcion().trim();
		char estado = formulario.getEstado();
		
		try{
		
			String resultado = "success";
			ProvinciaBean provinciaAnterior = null;
			provinciaAnterior = provinciaActual(codigo);
			
			SpringMessages messages = new SpringMessages();
			
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
			if (provinciaAnterior!= null && provinciaAnterior.getId() != null && id != provinciaAnterior.getId() && !comprobarCodigo(codigo,id)){
				
					SpringMessages errors = new SpringMessages();
					SpringMessage error = new SpringMessage("field.provincia.errorCodigoRepetido");
					errors.add("errorCodigo", error);
					saveErrors(this.getRequest(),errors);
					return "errorCodigo";
				
			}
	
			if (resultado.compareTo("success")==0){
				ProvinciaBean provinciaNueva = new ProvinciaBean();
				provinciaNueva.setId(id);
				provinciaNueva.setCodigo(codigo);
				provinciaNueva.setDescripcion(descripcion);
				provinciaNueva.setEstado(estado);
				provinciaNueva.setVisibilidad(formulario.getVisibilidad());
				String mensaje;
				
				try{
					provinciaManager.modificarProvincia(provinciaNueva);
					mensaje = "La provincia se ha guardado correctamente";
					String titulo = RESOURCE_BUNDLE.getString("field.provincia.modificacion.titulo");
					
					setRequestAttribute("titulo",titulo);
					setRequestAttribute("mensaje",mensaje);
					setRequestAttribute("accion","/spring/buscarProvincias");
				}catch(Exception e){
					resultado="show";
				}
			}else{
				saveErrors(this.getRequest(),messages);
			}
			getLogger().debug("ModificarProvinciaSpring -end");
			return resultado;
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			e.printStackTrace();
			return "nosuccess";
		}
	}
	
	/**
	 * Provincia actual.
	 *
	 * @param codigo el codigo
	 * @return el provincia bean
	 */
	public ProvinciaBean provinciaActual(String codigo){
		ProvinciaBean provincias = null;
		ProvinciaQuery provinciaQuery = new ProvinciaQuery();
		provinciaQuery.setCodigo(codigo);
		provinciaQuery.setEstado('A');		
		provincias = provinciaManager.buscarProvinciaUnique(provinciaQuery);
		
		return provincias;
		
	}

	/**
	 * Comprobar codigo.
	 *
	 * @param codigo el codigo
	 * @param id el id
	 * @return verdadero, si tiene exito
	 */
	public boolean comprobarCodigo(String codigo, int id){
		List<ProvinciaBean> provincias = null;
		ProvinciaQuery provinciaQuery = new ProvinciaQuery();
		provinciaQuery.setCodigo(codigo);
		provinciaQuery.setEstado('A');
		
		provincias = provinciaManager.buscarProvinciaCombo(provinciaQuery);
		
		if(provincias.size()==0||provincias.get(0).getId()==id)
			return true;
		else
			return false;
		
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
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
}
