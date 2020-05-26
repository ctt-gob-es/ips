package es.map.ipsg.action.convocatoria;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

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
 * El Class DesactivarConvocatoriasSeleccionadasSpring.
 */
public class DesactivarConvocatoriasSeleccionadasSpring extends AbstractSpring {
	
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
	 * Crea una nueva desactivar convocatorias seleccionadas spring.
	 */
	public DesactivarConvocatoriasSeleccionadasSpring(){
		try {
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
	
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error DesactivarConvocatoriasSeleccionadasSpring(): ",e);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	protected String doExecute(SpringForm form) throws Exception{
		
		String menu_convocatoria = RESOURCE_BUNDLE.getString("field.menu.convocatorias");
		this.getRequest().getSession().setAttribute("pagActiva", menu_convocatoria);
		String subMenu_convocatoria = RESOURCE_BUNDLE.getString("field.menuLateral.convocatorias.buscar");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_convocatoria);
		
		getLogger().debug("DescativarConvocatoriaSeleccionadaSpring -start");
		
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");			
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setLogin(usuarioBean.getLogin());
			
			//Recupero los id de las convocatorias que hay que desactivar
			String [] idsConvocatorias = null;
			setSessionAttribute("convSel", null);
			idsConvocatorias = this.getRequest().getParameterValues("conv");
			setSessionAttribute("convSel", idsConvocatorias);
			if(idsConvocatorias!=null && idsConvocatorias.length>0){
				List<DetalleConvocatoriaBean> listaConvocatoriasDesactivadas = new ArrayList<DetalleConvocatoriaBean>();
				
				for (int i = 0; i<idsConvocatorias.length; i++){
					ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
					convocatoriaQuery.setId(Long.valueOf(idsConvocatorias[i]));
					DetalleConvocatoriaBean convocatoria;
					convocatoria = convocatoriasManager.detalleConvocatoria(convocatoriaQuery);
					
					listaConvocatoriasDesactivadas.add(convocatoria);					
				}
				
				if (!listaConvocatoriasDesactivadas.isEmpty()){
					for (int i=0; i<listaConvocatoriasDesactivadas.size(); i++) {	
						
						//Actualizamos el estado
						EstadoConvocatoriaQuery estadoConvocatoriaQuery = new EstadoConvocatoriaQuery();
						estadoConvocatoriaQuery.setId(Constantes.ESTADO_CONVOCATORIA_DESACTIVADO);
						
						//Actualizamos la convocatoria
						ConvocatoriaQuery convocatoriaQ = new ConvocatoriaQuery();
						convocatoriaQ.setId(Long.valueOf(listaConvocatoriasDesactivadas.get(i).getIdConvocatoria()));
						convocatoriasManager.actualizarEstado(convocatoriaQ,estadoConvocatoriaQuery);
					}
				}
				setRequestAttribute("listaConvocatoriasDesactivadas", listaConvocatoriasDesactivadas);
			}
		}catch (Exception e){
			logger.error("Error DesactivarConvocatoriasSeleccionadasSpring() - doExecute: ",e);
		}
		return "success";
		
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

	/**
	 * Obtiene el bundle resource.
	 *
	 * @return el bundle resource
	 */
	public static String getBUNDLE_RESOURCE() {
		return BUNDLE_RESOURCE;
	}

	/**
	 * Obtiene el resource bundle.
	 *
	 * @return el resource bundle
	 */
	public static ResourceBundle getRESOURCE_BUNDLE() {
		return RESOURCE_BUNDLE;
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
