package es.map.ipsg.action.provincias;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.ProvinciaExamenQuery;
import es.map.ipsg.bean.ProvinciaExamenBean;
import es.map.ipsg.form.ModificarProvinciasExamenForm;
import es.map.ipsg.manager.ProvinciaExamenManager;


/**
 * El Class ModificarProvinciaExamenSpring.
 */
public class ModificarProvinciaExamenSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ModificarProvinciaExamenSpring.class);
	
	/** el provincia examen manager. */
	//Declaracion de manager
	private ProvinciaExamenManager provinciaExamenManager;
	
	
	/**
	 * Crea una nueva modificar provincia examen spring.
	 */
	public ModificarProvinciaExamenSpring() {
		try {
			setProvinciaExamenManager((ProvinciaExamenManager) getBean("provinciaExamenManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error ModificarProvinciaExamenSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_provincia = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.provinciaExamen");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_provincia);
		
		getLogger().debug("ModificarProvinciaExamenSpring -start");
		ModificarProvinciasExamenForm formulario = (ModificarProvinciasExamenForm) form;
		int id = formulario.getId();
		String codigo = formulario.getCodigo().trim();
		String descripcion = formulario.getDescripcion().trim();
		char estado = formulario.getEstado();
		
		try{
		
			String resultado = "success";
			ProvinciaExamenBean provinciaAnterior = null;
			provinciaAnterior = provinciaActual(codigo);
			
			SpringMessages messages = new SpringMessages();
			
			if ("".compareTo(codigo)==0){
				logger.info("Debe rellenar el campo codigo");
				messages.add("errorCodigoVacio",new SpringMessage("provincia.error.codigo.vacio"));
				resultado = "show";
			}
			if ("".compareTo(descripcion)==0){
				logger.info("Debe rellenar el campo descripcion");
				messages.add("errorProvinciaExamenVacia",new SpringMessage("provincia.error.provincia.vacia"));
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
				ProvinciaExamenBean provinciaNueva = new ProvinciaExamenBean();
				provinciaNueva.setId(id);
				provinciaNueva.setCodigo(codigo);
				provinciaNueva.setDescripcion(descripcion);
				provinciaNueva.setEstado(estado);
				provinciaNueva.setVisibilidad(formulario.getVisibilidad());
				String mensaje;
				
				try{
					provinciaExamenManager.modificarProvinciaExamen(provinciaNueva);
					mensaje = "La provincia se ha guardado correctamente";
					String titulo = RESOURCE_BUNDLE.getString("field.provincia.modificacion.titulo");
					
					setRequestAttribute("titulo",titulo);
					setRequestAttribute("mensaje",mensaje);
					setRequestAttribute("accion","/spring/buscarProvinciasExamen");
				}catch(Exception e){
					logger.error("Error ModificarProvinciaExamenSpring - modificarProvinciaExamen:",e);
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
			logger.error("Error ModificarProvinciaExamenSpring - doExecute:",e);
			return "nosuccess";
		}
	}
	
	/**
	 * Provincia actual.
	 *
	 * @param codigo el codigo
	 * @return el provincia examen bean
	 */
	public ProvinciaExamenBean provinciaActual(String codigo){
		ProvinciaExamenBean provincias = null;
		ProvinciaExamenQuery provinciaExamenQuery = new ProvinciaExamenQuery();
		provinciaExamenQuery.setCodigo(codigo);
		provinciaExamenQuery.setEstado('A');		
		provincias = provinciaExamenManager.buscarProvinciaExamenUnique(provinciaExamenQuery);
		
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
		List<ProvinciaExamenBean> provincias = null;
		ProvinciaExamenQuery provinciaExamenQuery = new ProvinciaExamenQuery();
		provinciaExamenQuery.setCodigo(codigo);
		provinciaExamenQuery.setEstado('A');
		
		provincias = provinciaExamenManager.buscarProvinciaExamenCombo(provinciaExamenQuery);
		
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
		List<ProvinciaExamenBean> provincias = null;
		ProvinciaExamenQuery provinciaExamenQuery = new ProvinciaExamenQuery();
		provinciaExamenQuery.setDescripcion(descripcion);
		provinciaExamenQuery.setEstado('A');
		
		provincias = provinciaExamenManager.buscarProvinciaExamenCombo(provinciaExamenQuery);
		return provincias.size()==0 ? false:true;
	}


	/**
	 * Obtiene el provincia examen manager.
	 *
	 * @return el provincia examen manager
	 */
	public ProvinciaExamenManager getProvinciaExamenManager() {
		return provinciaExamenManager;
	}

	/**
	 * Establece el provincia examen manager.
	 *
	 * @param provinciaExamenManager el nuevo provincia examen manager
	 */
	public void setProvinciaExamenManager(ProvinciaExamenManager provinciaExamenManager) {
		this.provinciaExamenManager = provinciaExamenManager;
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