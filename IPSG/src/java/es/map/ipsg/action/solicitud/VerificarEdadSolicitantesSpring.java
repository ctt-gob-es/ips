package es.map.ipsg.action.solicitud;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.map.j2ee.util.CollectionUtils;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.VerificarEdadSolicitantesForm;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class VerificarEdadSolicitantesSpring.
 *
 * @author amartinl
 */
public class VerificarEdadSolicitantesSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerificarEdadSolicitantesSpring.class);

	/** el convocatoria manager. */
	//Declaracion de Manager
	private ConvocatoriasManager convocatoriaManager;
	
	/** el solicitudes registradas manager. */
	private SolicitudesRegistradasManager solicitudesRegistradasManager;
		
	/**
	 * Crea una nueva verificar edad solicitantes spring.
	 */
	public VerificarEdadSolicitantesSpring() {
		try {
				setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
				setSolicitudesRegistradasManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
			} catch (Exception e) {
				logger.error("Error  VerificarEdadSolicitantesSpring:" ,e);
		}
	}

	/**
	 * Do execute.
	 *
	 * @param form  SpringForm
	 * @return resultado
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
	
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		final ResourceBundle RESOURCE_BUNDLE_2 = ResourceBundle.getBundle(MESSAGE_RESOUCE);	
		String menu_solicitudes = RESOURCE_BUNDLE_2.getString("field.menu.solicitudes");
		this.getRequest().getSession().setAttribute("pagActiva",menu_solicitudes);
		//******************************************************************
		String resultado = "";
	try{	
		//Tomamos el usuario que se ha logueado
		String sUsernameLogin = recuperarUsuario();
		
		if (sUsernameLogin.equals("error")) {
			return sUsernameLogin;
		}

		
	
		//Cogemos el form del jsp
		VerificarEdadSolicitantesForm formulario;
		formulario = (VerificarEdadSolicitantesForm) form;
		
		String accion = formulario.getAccion();
		
		if("VOLVER".equalsIgnoreCase(accion))
		{
			resultado = "list";
		}else
		{
			//Pasamos la fecha de Nacimiento de String a Date para comprarla
			SolicitudBean solicitudBean;
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
			Date dFechaMin = null;
			Date dFechaMax = null;
			Date dFechaNacimiento;
			dFechaMin = formatoDelTexto.parse(formulario.getFechaMin());
			dFechaMax = formatoDelTexto.parse(formulario.getFechaMax());
			
			//Convocatorias a las que modificarle la fecha
			String convocatorias [] = formulario.getIdConvocatoria();
			//Solicitudes que se revisarán la Fecha de Nacimiento si está entre los limites
			ArrayList<SolicitudBean> aSolicitudes; 
			
			
			//INI Solo verificará la edad de las solicitudes seleccionadas IPSG-35
			for (int i=0; i < convocatorias.length; i++)
			{
				String IdConvocatoria = convocatorias[i];
	
				
				ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
				logger.info("Long " + Long.parseLong((IdConvocatoria)));
				convocatoriaQuery.setId(Long.parseLong(IdConvocatoria));
				
		
				//Recuperamos una Convocatoria para modificarle sus fechas Min y Max
				Convocatoria convocatoria = convocatoriaManager.buscarConvocatoria(convocatoriaQuery);
				convocatoria.setFechaNacimientoMinima(dFechaMin);
				convocatoria.setFechaNacimientoMaxima(dFechaMax);
				
				convocatoriaManager.modificarCamposConvocatoria(convocatoria);
	
			}
			
			
			SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
			String[] solicitudes = (String[]) getSessionAttribute("solSel");
			ArrayList<String> listSolicitudes = new ArrayList<String>();
			CollectionUtils.addAll(listSolicitudes, solicitudes);
			for (String id : listSolicitudes) {
				solicitudQuery.addIdSolicitudIn(Long.valueOf(id));
			}
			
			//Solicitudes Registradas
			EstadoSolicitudQuery estadoSolicitudQuery = new EstadoSolicitudQuery();
			estadoSolicitudQuery.setId(Constantes.ESTADO_SOLICITUD_REGISTRADO); 
			solicitudQuery.setEstadoSolicitud(estadoSolicitudQuery);
			
			//Obtenemos los Bean de todas las solicitudes seleccionadas para comprobar las fechas de nacimiento
			aSolicitudes = solicitudesRegistradasManager.buscarSolicitudesFiltro(solicitudQuery);
			
			Iterator<SolicitudBean> it = aSolicitudes.iterator();
			//Recorremos todas las solicitudes seleccionadas.
			while (it.hasNext())
			{
				solicitudBean = (SolicitudBean) it.next();
				if(solicitudBean.getFechaNacimiento() == null || solicitudBean.getFechaNacimiento().toString().equals("")){
					dFechaNacimiento = null;
					//No está verificada
					solicitudBean.setEdadVerificada(Constantes.EDAD_NO_COMPROBADA);
				}else{
					dFechaNacimiento = solicitudBean.getFechaNacimiento();
				}
				
				if (dFechaMin != null && dFechaMax != null && dFechaNacimiento != null)	{
					//fechamin < nacimiento < fechaMax = OK
					//Si Fecha de Nacimiento está después de dFechaMin
					//y Fecha Nacimiento antes que la Fecha Máxima (incluidas las fechas Máxima y Mínima
					if(dFechaNacimiento.after(dFechaMin) && dFechaNacimiento.before(dFechaMax)
						|| (dFechaNacimiento.equals(dFechaMin) || dFechaNacimiento.equals(dFechaMax)))
					{
						//Sí está verificada
						solicitudBean.setEdadVerificada(Constantes.EDAD_VERIFICADA);
						
					}else{
						//No está verificada
						solicitudBean.setEdadVerificada(Constantes.EDAD_NO_VERIFICADA);
					}
				}
				solicitudesRegistradasManager.modificarSolicitudRegistrada(solicitudBean);
			}
			
			//FIN Solo verificará la edad de las solicitudes seleccionadas IPSG-35
			
			
			setSessionAttribute("solSel", null);
			setRequestAttribute("accion", "../spring/buscarSolicitudesRegistradas.do");
			resultado="success";
		}
		
	}catch(Exception eGenerico){
		logger.error("Error  VerificarEdadSolicitantesSpring - doExecute:" ,eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
		return "nosuccess";
	}	
		
		return resultado;
	}
	
	/**
	 * Recuperar usuario.
	 *
	 * @return el string
	 */
	private String recuperarUsuario() {
		String sUsernameLogin = "";
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			sUsernameLogin = usuarioBean.getLogin();
			return sUsernameLogin;
		}catch(Exception e){
			logger.error("Error  VerificarEdadSolicitantesSpring - recuperar usuarioSesion:"+ sUsernameLogin ,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
		}
	}

	/**
	 * Obtiene el convocatoria manager.
	 *
	 * @return convocatoriaManager ConvocatoriasManager
	 */
	public ConvocatoriasManager getConvocatoriaManager() {
		return convocatoriaManager;
	}

	/**
	 * Establece el convocatoria manager.
	 *
	 * @param convocatoriaManager ConvocatoriasManager
	 */
	public void setConvocatoriaManager(ConvocatoriasManager convocatoriaManager) {
		this.convocatoriaManager = convocatoriaManager;
	}

	/**
	 * Obtiene el solicitudes registradas manager.
	 *
	 * @return solicitudesRegistradasManager SolicitudesRegistradasManager
	 */
	public SolicitudesRegistradasManager getSolicitudesRegistradasManager() {
		return solicitudesRegistradasManager;
	}

	/**
	 * Establece el solicitudes registradas manager.
	 *
	 * @param solicitudesRegistradasManager SolicitudesRegistradasManager
	 */
	public void setSolicitudesRegistradasManager(
			SolicitudesRegistradasManager solicitudesRegistradasManager) {
		this.solicitudesRegistradasManager = solicitudesRegistradasManager;
	}

	
}
