package es.map.ipsg.action.aviso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.EstadoAvisoQuery;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.AvisoBean;
import es.map.ipsg.bean.EstadoAvisoBean;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.AvisoForm;
import es.map.ipsg.manager.AvisoManager;
import es.map.ipsg.manager.EstadoAvisoManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class AltaAvisoSpring.
 */
public class AltaAvisoSpring extends AbstractSpring {
	
	/** el aviso manager. */
	private AvisoManager avisoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/** el estado aviso manager. */
	private EstadoAvisoManager estadoAvisoManager;
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(AltaAvisoSpring.class);
	
	
	
	/**
	 * Crea una nueva alta aviso spring.
	 */
	public AltaAvisoSpring() {
		try {
			setAvisoManager((AvisoManager) getBean("avisoManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setEstadoAvisoManager((EstadoAvisoManager) getBean("estadoAvisoManager"));
			setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
			
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error AltaAvisoSpring():", e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********			
		String menu_consultas = RESOURCE_BUNDLE.getString("field.menu.avisos");
		this.getRequest().getSession().setAttribute("pagActiva",menu_consultas);
		String subMenu_aviso = RESOURCE_BUNDLE.getString("field.menuLateral.avisos.alta");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_aviso);
		//****************************************************************** 
		logger.info("AltaAvisoSpring -start");
	try{
		AvisoForm formulario = (AvisoForm) form;		
		AvisoBean avisoBean = new AvisoBean();
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		
		Date fechaSistema = new Date();
		Date fechaInicio = formatoDelTexto.parse(formulario.getFechaInicio());
		Date fechaFin = formatoDelTexto.parse(formulario.getFechaFin());
		
		//Rellenamos el combo de estados.
		EstadoAvisoQuery estadoAvisoQuery= new EstadoAvisoQuery();
		
		estadoAvisoQuery.addIdIn(Constantes.ESTADO_AVISO_ACTIVO);
		estadoAvisoQuery.addIdIn(Constantes.ESTADO_AVISO_INACTIVO);
		ArrayList <EstadoAvisoBean> estadosList;
		
		estadosList=estadoAvisoManager.buscarEstadoAvisoCombo(estadoAvisoQuery);
		
		String s_fecha = formatoDelTexto.format(fechaSistema);
		fechaSistema = formatoDelTexto.parse(s_fecha);
		
		
		setRequestAttribute("estados", estadosList);
		
		//Informamos los valores del Aviso 
		//Si la fecha actual esta entre la fecha de inicio y la fecha de fin y se da de alta como Activa se debe pasar a Publicada
		if (formulario.getIdEstadoAviso()==Constantes.ESTADO_AVISO_ACTIVO && 
				(fechaInicio.before(fechaSistema) || fechaInicio.equals(fechaSistema)) && 
				(fechaFin.after(fechaSistema) || fechaFin.equals(fechaSistema))){
			avisoBean.setIdEstadoAviso(Constantes.ESTADO_AVISO_PUBLICADO);
		}else{
			avisoBean.setIdEstadoAviso(formulario.getIdEstadoAviso());
		}
		avisoBean.setFechaInicio(formatoDelTexto.parse(formulario.getFechaInicio()));
		avisoBean.setFechaFin(formatoDelTexto.parse(formulario.getFechaFin()));
		avisoBean.setTexto(formulario.getTexto());
		
	
		try{
			int idAviso = avisoManager.guardarAviso(avisoBean);
						if (idAviso>0){
							String mensaje = "";
							String titulo = "";
							UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
							generarRegistroLogGenerico(usuarioBean.getLogin(),String.valueOf(idAviso),Long.valueOf(idAviso));
						    mensaje = RESOURCE_BUNDLE.getString("field.aviso.alta.mensaje")+idAviso;
							titulo = RESOURCE_BUNDLE.getString("field.aviso.alta.titulo");
							setRequestAttribute("accion","/spring/buscarAviso?menu=S");
							setRequestAttribute("titulo",titulo);
							setRequestAttribute("mensaje",mensaje);
							return "success";
						}
			
		}catch(Exception e){
			logger.error("Error mensaje alta aviso:", e);
			SpringMessages errors = new SpringMessages();
			SpringMessage error = new SpringMessage("field.aviso.alta.mensajeError");
			errors.add("error",error);
			saveErrors(this.getRequest().getSession(),errors);
			return "error";
		}
			
		logger.info("AltaAvisoSpring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error AltaAviso - doExecute: "+ eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		return "success";
	}

/**
 * Generar registro log generico.
 *
 * @param username el username
 * @param alertaDesc el alerta desc
 * @param resultado el resultado
 */
public void generarRegistroLogGenerico(String username, String alertaDesc, Long resultado){
		
		UsuarioQuery usuarioQuery = new UsuarioQuery();
		usuarioQuery.setLogin(username);
		UsuarioBean usuarioBean2 = usuarioManager.buscarUsuario(usuarioQuery);
		
		//Cargo los datos en el bean del log generico que se usara para crear el registro en la tabla
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_AVISO);
		logGenericoBean.setTipoOperacion(Constantes.OPERACION_ALTA);
		logGenericoBean.setDetalleOperacion("Alta de la aviso " + alertaDesc);
		logGenericoBean.setIdTablaOrigen(resultado);
		logGenericoBean.setUsuario(usuarioManager.toUsuario(usuarioBean2));
		
		logGenericoManager.generarRegistroLog(logGenericoBean);
	}
	
	/**
	 * Obtiene el aviso manager.
	 *
	 * @return the avisoManager
	 */
	public AvisoManager getAvisoManager() {
		return avisoManager;
	}

	/**
	 * Establece el aviso manager.
	 *
	 * @param avisoManager the avisoManager to set
	 */
	public void setAvisoManager(AvisoManager avisoManager) {
		this.avisoManager = avisoManager;
	}

	/**
	 * Obtiene el usuario manager.
	 *
	 * @return the usuarioManager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager the usuarioManager to set
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	/**
	 * Obtiene el estado aviso manager.
	 *
	 * @return the estadoAvisoManager
	 */
	public EstadoAvisoManager getEstadoAvisoManager() {
		return estadoAvisoManager;
	}

	/**
	 * Establece el estado aviso manager.
	 *
	 * @param estadoAvisoManager the estadoAvisoManager to set
	 */
	public void setEstadoAvisoManager(EstadoAvisoManager estadoAvisoManager) {
		this.estadoAvisoManager = estadoAvisoManager;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Obtiene el log generico manager.
	 *
	 * @return the logGenericoManager
	 */
	public LogGenericoManager getLogGenericoManager() {
		return logGenericoManager;
	}

	/**
	 * Establece el log generico manager.
	 *
	 * @param logGenericoManager the logGenericoManager to set
	 */
	public void setLogGenericoManager(LogGenericoManager logGenericoManager) {
		this.logGenericoManager = logGenericoManager;
	}

	
}
