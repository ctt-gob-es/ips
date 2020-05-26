package es.map.ipsg.action.solicitud;

import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsg.bean.CorreoElectronicoBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.CorreoElectronicoManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class ConsultarEmailEnviadosSpring.
 */
public class ConsultarEmailEnviadosSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ConsultarEmailEnviadosSpring.class);

	/** el convocatoria manager. */
	//Declaracion de Manager
	private ConvocatoriasManager convocatoriaManager;
	
	/** el solicitudes registradas manager. */
	private SolicitudesRegistradasManager solicitudesRegistradasManager;
	
	/** el correo electronico manager. */
	private CorreoElectronicoManager correoElectronicoManager;
	
	/**
	 * Crea una nueva consultar email enviados spring.
	 */
	public ConsultarEmailEnviadosSpring() {
		try {
				setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
				setSolicitudesRegistradasManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
				setCorreoElectronicoManager((CorreoElectronicoManager)getBean("correoElectronicoManager"));
			} catch (Exception e) {
				logger.error("Error ConsultarEmailEnviadosSpring:"  ,e);
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
		
		ArrayList<CorreoElectronicoBean> listadoCorreos = new ArrayList<CorreoElectronicoBean>();
		
		String idSolicitud = this.getRequest().getParameter("solicitud");
	try{
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.setIdSolicitud(Long.valueOf(idSolicitud));
		
		ArrayList<SolicitudBean> listadoSolicitudes = solicitudesRegistradasManager.buscarSolicitudAll(solicitudQuery);
		
		//Como el buscar solo va a devolver un item porque la id es unica, no hacemos un bucle para iterar por cada elemento resultante
		//de la busqueda
		listadoCorreos = listadoSolicitudes.get(0).getListadoCorreosElectronico();
		
		//Filtramos los correos para mostrar sólo los que tengan estado enviado
		ArrayList<CorreoElectronicoBean> listadoCorreosEnviados = new ArrayList<CorreoElectronicoBean>();
		for (int i=0; i<listadoCorreos.size(); i++) {
			if (listadoCorreos.get(i).getEstado().equals(Constantes.CORREO_ELECTRONICO_ESTADO_ENVIADO)) {
				listadoCorreosEnviados.add(listadoCorreos.get(i));
			}
		}
		
		setRequestAttribute("correos", listadoCorreosEnviados);
				
	}catch(Exception eGenerico){
		logger.error("Error ConsultarEmailEnviadosSpring - doExecute :"  ,eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return "success";
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

	/**
	 * Obtiene el correo electronico manager.
	 *
	 * @return the correoElectronicoManager
	 */
	public CorreoElectronicoManager getCorreoElectronicoManager() {
		return correoElectronicoManager;
	}

	/**
	 * Establece el correo electronico manager.
	 *
	 * @param correoElectronicoManager the correoElectronicoManager to set
	 */
	public void setCorreoElectronicoManager(
			CorreoElectronicoManager correoElectronicoManager) {
		this.correoElectronicoManager = correoElectronicoManager;
	}

	
}
