package es.map.ipsg.action.solicitud;

import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.DocumentoQuery;
import es.map.ipsg.bean.CorreoElectronicoBean;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.form.ContactarCiudadanoForm;
import es.map.ipsg.manager.CorreoElectronicoManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;

/**
 * El Class VerCorreoSpring.
 */
public class VerCorreoSpring extends AbstractSpring {

	//Declarar los resource
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerCorreoSpring.class);

	/** el correo electronico manager. */
	//Declaracion de Manager
	private CorreoElectronicoManager correoElectronicoManager;
	
	/** el solicitudes registradas manager. */
	private SolicitudesRegistradasManager solicitudesRegistradasManager;
	
	/** el documento manager. */
	private DocumentoManager documentoManager;	
	
	/**
	 * Crea una nueva ver correo spring.
	 */
	public VerCorreoSpring() {
		try {				
				setCorreoElectronicoManager((CorreoElectronicoManager)getBean("correoElectronicoManager"));
				setSolicitudesRegistradasManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
				setDocumentoManager((DocumentoManager) getBean("documentoManager"));
			} catch (Exception e) {
				logger.error(" Error VerCorreoSpring:" ,e);
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

		String idCorreo = this.getRequest().getParameter("correo");
	try{	
		CorreoElectronicoBean correoElectronicoBean = correoElectronicoManager.obtenerCorreoElectronico(Long.valueOf(idCorreo));
		
		ContactarCiudadanoForm formulario;
		formulario = (ContactarCiudadanoForm) form;
		
		formulario.setIdCorreo(idCorreo);
		formulario.setDe(correoElectronicoBean.getDe());
		formulario.setPara(correoElectronicoBean.getPara());
		formulario.setAsunto(correoElectronicoBean.getAsunto());
		formulario.setCuerpoMensaje(correoElectronicoBean.getMensaje());
		
		correoElectronicoBean.getDocumentos();
		
		DocumentoQuery documentoQuery= new DocumentoQuery();
		documentoQuery.addCorreoElectronicoIn(Long.valueOf(idCorreo));
		
		ArrayList<DocumentoBean> listadoDocumentos = documentoManager.buscarDocumentoCombo(documentoQuery);
		
		setRequestAttribute("documentos",listadoDocumentos);		
		
		setRequestAttribute("idCorreo",idCorreo);
		setRequestAttribute("entorno","Correos");
		
	}catch(Exception eGenerico){
		logger.error(" Error VerCorreoSpring - doExecute:" ,eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return "success";
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

	/**
	 * Obtiene el documento manager.
	 *
	 * @return el documento manager
	 */
	public DocumentoManager getDocumentoManager() {
		return documentoManager;
	}

	/**
	 * Establece el documento manager.
	 *
	 * @param documentoManager el nuevo documento manager
	 */
	public void setDocumentoManager(DocumentoManager documentoManager) {
		this.documentoManager = documentoManager;
	}
	
}
