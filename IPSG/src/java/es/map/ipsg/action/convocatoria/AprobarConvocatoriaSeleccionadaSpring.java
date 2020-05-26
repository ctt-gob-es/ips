package es.map.ipsg.action.convocatoria;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.EstadoConvocatoriaQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.DetalleConvocatoriaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;



/**
 * El Class AprobarConvocatoriaSeleccionadaSpring.
 */
public class AprobarConvocatoriaSeleccionadaSpring extends AbstractSpring {

	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	
	/** La constante BUNDLE_RESOURCE. */
	private static final String BUNDLE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(AprobarConvocatoriaSeleccionadaSpring.class);

	
	
	/**
	 * Crea una nueva aprobar convocatoria seleccionada spring.
	 */
	public AprobarConvocatoriaSeleccionadaSpring() {
		try {
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
	
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error AprobarConvocatoriaSeleccionadaSpring(): ",e);
		}	
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_convocatoria = RESOURCE_BUNDLE.getString("field.menu.convocatorias");
		this.getRequest().getSession().setAttribute("pagActiva", menu_convocatoria);
		String subMenu_convocatoria = RESOURCE_BUNDLE.getString("field.menuLateral.convocatorias.buscar");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_convocatoria);
		
		getLogger().debug("AprobarConvocatoriaSeleccionadaSpring -start");
		
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setLogin(usuarioBean.getLogin());
			
			
			//Recupero los ids de las convocatorias que hay que aprobar
			String[] idsConvocatorias = null;
			setSessionAttribute("convSel", null);
			idsConvocatorias = this.getRequest().getParameterValues("conv");
			setSessionAttribute("convSel", idsConvocatorias);
		
			if (idsConvocatorias != null && idsConvocatorias.length > 0) {
				//Guardaremos la lista de convocatorias que se han aprobado
				List<DetalleConvocatoriaBean> listaConvocatoriasValidas = new ArrayList<DetalleConvocatoriaBean>();
				HashMap<Integer,String> listaConvocatoriasNoValidas = new HashMap<Integer, String>();
				
				for (int i = 0; i<idsConvocatorias.length; i++) {
					//Recuperamos la convocatoria de bbdd
					ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
					convocatoriaQuery.setId(Long.valueOf(idsConvocatorias[i]));
					DetalleConvocatoriaBean convocatoria;
					convocatoria = convocatoriasManager.detalleConvocatoria(convocatoriaQuery);
					
					//Comprobaciones necesarias para poder aprobar la convocatoria
					if (convocatoria.getProvinciasExamen() != null && !convocatoria.getProvinciasExamen().isEmpty()) {
						comprobarConvocatoria(convocatoria,listaConvocatoriasValidas,listaConvocatoriasNoValidas,idsConvocatorias,i);
					}else{
						listaConvocatoriasNoValidas.put(Integer.parseInt(idsConvocatorias[i]),RESOURCE_BUNDLE.getString("field.convocatoria.errorAprobada.provincia"));
					}					
				}//end for
				
				if (!listaConvocatoriasValidas.isEmpty()) {					
					//Recorremos la lista de convocatorias para aprobarlas
					for (int i=0; i<listaConvocatoriasValidas.size(); i++) {						
												
						//Actualizamos el estado
						EstadoConvocatoriaQuery estadoConvocatoriaQuery = new EstadoConvocatoriaQuery();
						estadoConvocatoriaQuery.setId(Constantes.ESTADO_CONVOCATORIA_APROBADO);
						
						//Actualizamos la convocatoria
						ConvocatoriaQuery convocatoriaQ = new ConvocatoriaQuery();
						convocatoriaQ.setId(Long.valueOf(listaConvocatoriasValidas.get(i).getIdConvocatoria()));
						convocatoriasManager.actualizarEstado(convocatoriaQ,estadoConvocatoriaQuery);
						
						//Generar Log						
					}
				}
				
				setRequestAttribute("listaConvocatoriasValidas", listaConvocatoriasValidas);
				setRequestAttribute("listaConvocatoriasNoValidas", listaConvocatoriasNoValidas);
				
			}			
		} catch(Exception e) {
			logger.error("Error AprobarConvocatoriaSeleccionadaSpring() - doExecute: ",e);
		}
		
		return "success";
	}
	
	/**
	 * Comprobar convocatoria.
	 *
	 * @param convocatoria el convocatoria
	 * @param listaConvocatoriasValidas el lista convocatorias validas
	 * @param listaConvocatoriasNoValidas el lista convocatorias no validas
	 * @param idsConvocatorias el ids convocatorias
	 * @param i el i
	 */
	private void comprobarConvocatoria(DetalleConvocatoriaBean convocatoria,List<DetalleConvocatoriaBean> listaConvocatoriasValidas,HashMap<Integer,String> listaConvocatoriasNoValidas,String[] idsConvocatorias,int i) {
		if (StringUtils.isNotEmpty(convocatoria.getFechaBoe())) {
			if(convocatoria.getFechaInicio() != null && !"".equals(convocatoria.getFechaInicio())){ 
				if(convocatoria.getFechaFin() != null && !"".equals(convocatoria.getFechaFin())){
					try{																			
						listaConvocatoriasValidas.add(convocatoria);
					}catch(BusinessException be){
						listaConvocatoriasNoValidas.put(Integer.parseInt(idsConvocatorias[i]),RESOURCE_BUNDLE.getString(be.getKey()));
						logger.error("Error AprobarConvocatoriaSeleccionadaSpring() - listaConvocatoriasNoValidas: ",be);
					}
				}else{
					listaConvocatoriasNoValidas.put(Integer.parseInt(idsConvocatorias[i]),RESOURCE_BUNDLE.getString("field.convocatoria.errorAprobada.fechaFin"));
				}
			}else{
				listaConvocatoriasNoValidas.put(Integer.parseInt(idsConvocatorias[i]),RESOURCE_BUNDLE.getString("field.convocatoria.errorAprobada.fechaInicio"));
			}
		}else{
			listaConvocatoriasNoValidas.put(Integer.parseInt(idsConvocatorias[i]),RESOURCE_BUNDLE.getString("field.convocatoria.errorAprobada.fechaBOE"));
		}
	}
	
	/**
	 * Obtiene el usuario manager.
	 *
	 * @return el usuario manager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager el nuevo usuario manager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
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
	 * Obtiene el convocatorias manager.
	 *
	 * @return el convocatorias manager
	 */
	public ConvocatoriasManager getConvocatoriasManager() {
		return convocatoriasManager;
	}

	/**
	 * Establece el convocatorias manager.
	 *
	 * @param convocatoriasManager el nuevo convocatorias manager
	 */
	public void setConvocatoriasManager(ConvocatoriasManager convocatoriasManager) {
		this.convocatoriasManager = convocatoriasManager;
	}

	
	
	
}
