package es.map.ipsg.action.motivoReduccionTasa;

import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.MotivoReduccionTasaQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.MotivoReduccionTasaBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.MotivoReduccionTasaManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class EliminarMotivoReduccionTasaSpring.
 */
public class EliminarMotivoReduccionTasaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EliminarMotivoReduccionTasaSpring.class);
	
	/** La constante ESTADO_INACTIVO. */
	private static final Character ESTADO_INACTIVO = 'D';
	
	/** La constante ESTADO_CONFIGURACION. */
	private static final int ESTADO_CONFIGURACION=4;
	
	/** La constante ESTADO_PUBLICADO. */
	private static final int ESTADO_PUBLICADO=5;
	
	/** La constante ESTADO_APROBADO. */
	private static final int ESTADO_APROBADO=7;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;

	/** el motivo manager. */
	private MotivoReduccionTasaManager motivoManager;
	
		
	/**
	 * Crea una nueva eliminar motivo reduccion tasa spring.
	 */
	public EliminarMotivoReduccionTasaSpring() {
		try {
			setMotivoReduccionTasaManager((MotivoReduccionTasaManager)getBean("motivoReduccionTasaManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			
		} catch (Exception e) {
			logger.error("Error EliminarMotivoReduccionTasaSpring:",e);
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
		
		SpringMessages errors = new SpringMessages();
	try{	
		String idMotivo = this.getRequest().getParameter("id");
		MotivoReduccionTasaBean motivoBean=	motivoManager.obtenerMotivoReduccionTasa(Integer.parseInt(idMotivo));
		motivoBean.setEstado(ESTADO_INACTIVO);

		
		
		boolean bEliminaConvocatoria=compruebaConvocatoria(motivoBean,errors) ;
		
		if (bEliminaConvocatoria){
		motivoManager.modificarMotivoReduccionTasa(motivoBean);
		UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
		generarRegistroLogGenerico(usuarioBean.getLogin(), Long.valueOf(motivoBean.getId()));
		}
		 else{
			saveErrors(this.getRequest(), errors);
			return "errorEliminar";
		} 
		
		String mensaje = RESOURCE_BUNDLE.getString("field.cuerpoEscala.eliminarcuerpoEscalaConfirmacion");
		String titulo = RESOURCE_BUNDLE.getString("field.cuerpoEscala.tituloEliminarcuerpoEscala");			
		
		setRequestAttribute("titulo",titulo);
		setRequestAttribute("mensaje",mensaje);
		setRequestAttribute("accion","/spring/buscarCuerposEscala");	

	}catch(Exception eGenerico){
		logger.error("Error EliminarMotivoReduccionTasaSpring - doExecute:",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return "success";
	}
	
/**
 * Generar registro log generico.
 *
 * @param username el username
 * @param idMotivo el id motivo
 */
public void generarRegistroLogGenerico(String username, Long idMotivo){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_MOTIVO_REDUCCION_TASA);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_BAJA);
		logGenericoBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.motivoReduccionTasa.detalleOperacionBaja") + " "  + idMotivo.toString());
		logGenericoBean.setIdTablaOrigen(idMotivo);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}

	/**
	 * Comprueba convocatoria.
	 *
	 * @param motivoBean el motivo bean
	 * @param errors el errors
	 * @return verdadero, si tiene exito
	 */
	//Comprueba que el Motivo Reducción de Tasas que queremos eliminar no se encuentra en Convocatoria
	private boolean compruebaConvocatoria (MotivoReduccionTasaBean motivoBean, SpringMessages errors){	

		ConvocatoriaQuery convocatoriaQuery= new ConvocatoriaQuery();
		//Son los distintos estados donde queremos buscar 
		convocatoriaQuery.addEstadoConvocatoriaIn(ESTADO_CONFIGURACION);
		convocatoriaQuery.addEstadoConvocatoriaIn(ESTADO_PUBLICADO);
		convocatoriaQuery.addEstadoConvocatoriaIn(ESTADO_APROBADO);
		
		MotivoReduccionTasaQuery motivoReduccionQuery= new MotivoReduccionTasaQuery();
		motivoReduccionQuery.setId(motivoBean.getId());
			
		ArrayList<ConvocatoriasBean> convocatoria = null;
		//Obtengo las convocatorias que están asociadas al Motivo Reducción de Tasas
		convocatoria = motivoManager.compruebaConvocatoria(convocatoriaQuery,motivoReduccionQuery);
		
		if (convocatoria.size()!=0){
			SpringMessage error = new SpringMessage("field.motivoReduccionTasa.errores.convocatoria1");
			errors.add("errorConvocatoria",error);
			
			for (int i=0; i<convocatoria.size();i++){
				error= new SpringMessage("field.cuerpoEscala.errores.convocatoria3",convocatoria.get(i).getIdConvocatoria(), convocatoria.get(i).getEjercicio(), convocatoria.get(i).getEstadoConvocatoria());
				errors.add("errorConvocatoria1",error);
			}
			
			
			return false;
		}
		else		
		return true;
		
	}
	

	/**
	 * Obtiene el motivo manager.
	 *
	 * @return the motivoManager
	 */
	public MotivoReduccionTasaManager getMotivoManager() {
		return motivoManager;
	}

	/**
	 * Establece el motivo reduccion tasa manager.
	 *
	 * @param motivoManager the motivoManager to set
	 */
	public void setMotivoReduccionTasaManager(MotivoReduccionTasaManager motivoManager) {
		this.motivoManager = motivoManager;
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
	 * Obtiene el log generico manager.
	 *
	 * @return el log generico manager
	 */
	public LogGenericoManager getLogGenericoManager() {
		return logGenericoManager;
	}

	/**
	 * Establece el log generico manager.
	 *
	 * @param logGenericoManager el nuevo log generico manager
	 */
	public void setLogGenericoManager(LogGenericoManager logGenericoManager) {
		this.logGenericoManager = logGenericoManager;
		}
}
