package es.map.ipsg.action.solicitud;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.form.ContactarCiudadanoForm;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;

/**
 * El Class VerContactarCiudadanoSpring.
 */
public class VerContactarCiudadanoSpring extends AbstractSpring {

	//Declarar los resource
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerContactarCiudadanoSpring.class);
	
	/** La constante STRING_IDSOLICITUDESSEL. */
	private static final String STRING_IDSOLICITUDESSEL = "idSolicitudesSel";
	
	/** La constante STRING_LISTAIDDOCUMENTOS. */
	private static final String STRING_LISTAIDDOCUMENTOS = "listaIdDocumentos";
	

	/** el convocatoria manager. */
	//Declaracion de Manager
	private ConvocatoriasManager convocatoriaManager;
	
	/** el solicitudes registradas manager. */
	private SolicitudesRegistradasManager solicitudesRegistradasManager;
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/**
	 * Crea una nueva ver contactar ciudadano spring.
	 */
	public VerContactarCiudadanoSpring() {
		try {
				setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
				setSolicitudesRegistradasManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
				setDocumentoManager((DocumentoManager) getBean("documentoManager"));
			} catch (Exception e) {
				logger.error(" Error VerContactarCiudadanoSpring:" ,e);
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
	
		
		ContactarCiudadanoForm formulario = (ContactarCiudadanoForm) form;
		
		String[] solicitudes;
		String idSolicitudes="";
		String destinatarios = "";
	try{
		// Comprobamos si es la primera vez que entramos, o es una recarga de la página
		//Comprobamos si cargamos este action procedente de una carga o de la validacion de errores.
		//Si viene  de la validacion de errores cogemos las id de solicitudes de una variable de session, sino
		//cogemos las id de solicitudes que han sido pasadas por parametro
		if (this.getRequest().getParameter("error")==null){			
			if (this.getRequest().getParameter("menu")==null){
			
				//reseteamos los campos de, asunto y para del formulario
				formulario.setCuerpoMensaje("");
				formulario.setAsunto("");
				formulario.setDe("");
				
				//Reseteamos la session
				setSessionAttribute(STRING_IDSOLICITUDESSEL, null);
			
				solicitudes = this.getRequest().getParameterValues("sol");
			
				//cargamos la session con las id de las solicitudes seleccionadas
				setSessionAttribute(STRING_IDSOLICITUDESSEL, solicitudes);
			
				//Reseteamos los documentos adjuntos
				ArrayList<Long> listaIdDocumentos = new ArrayList<Long>();
				setSessionAttribute(STRING_LISTAIDDOCUMENTOS, listaIdDocumentos);
			//viene desde subir documento adjunto
			}else{
				
				solicitudes = (String[]) getSessionAttribute(STRING_IDSOLICITUDESSEL);
				
				ArrayList<Long> listaIdDocumentos = (ArrayList<Long>)getSessionAttribute(STRING_LISTAIDDOCUMENTOS);
				if (listaIdDocumentos == null)
				{
					listaIdDocumentos = new ArrayList<Long>();
					setSessionAttribute(STRING_LISTAIDDOCUMENTOS, listaIdDocumentos);
				}
			}
			
		}else{					
			solicitudes = (String[]) getSessionAttribute(STRING_IDSOLICITUDESSEL);
			
				
		}
		ArrayList<SolicitudBean> listadoSolicitudes;
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();		
		
		if(!StringUtils.isEmpty(solicitudes)){
		
			for(int i=0; i < solicitudes.length; i++)
			{
				Long idSolicitudSel = Long.valueOf(0);			
				if(solicitudes[i]!=null){
					idSolicitudSel = Long.valueOf(solicitudes[i]);
				}
				
				solicitudQuery.addIdSolicitudIn(idSolicitudSel);
			}
			//Cogemos solo los que tenga email informado
			solicitudQuery.setEmailIsNotNull(true);
			//Recuperamos todos los datos de las solicitudes seleccionadas		
			listadoSolicitudes = solicitudesRegistradasManager.buscarSolicitudAll(solicitudQuery);
			
			//INI Comprobamos que no existan email nulos
			
			if(listadoSolicitudes!=null && !StringUtils.isEmpty(solicitudes) && listadoSolicitudes.size() != solicitudes.length){
				String errorEmail = "Las siguientes solicitudes no tienen el email informado: ";
				int contador = 0;
				for (int i = 0; i < solicitudes.length; i++) {
					Long idSolicitud = Long.valueOf(solicitudes[i]);
					Iterator<SolicitudBean> it = listadoSolicitudes.iterator();
					boolean encontrado = false;
					while (it.hasNext() && idSolicitud!=null && !encontrado) {
						SolicitudBean solicitudBean = (SolicitudBean) it.next();
						Long idBuscado = solicitudBean.getId();
						if(idBuscado!=null &&  idBuscado.longValue() == idSolicitud.longValue()){
							encontrado=true;
						}
						
					}
					if (!encontrado){
						if(contador==0){
							errorEmail+=idSolicitud.toString();
						}else{
							errorEmail+=", "+idSolicitud.toString();
						}
						contador++;
					}
				}
				
				setRequestAttribute("errorEmail", errorEmail);
				
			}
			
			//FIN Comprobamos que no existan email nulos
			
			for(int i=0;listadoSolicitudes!=null && i<listadoSolicitudes.size();i++){				
				if(i==0){
					idSolicitudes +=listadoSolicitudes.get(i).getId();
					destinatarios += listadoSolicitudes.get(i).getEmail();	
				}else{
					idSolicitudes +=","+listadoSolicitudes.get(i).getId();
					destinatarios += ","+listadoSolicitudes.get(i).getEmail();
				}
			}
			
			// Añadimos la lista de los documentos adjuntos.
			ArrayList<Long> listaIdDocumentos = (ArrayList<Long>) getSessionAttribute(STRING_LISTAIDDOCUMENTOS);
			if(listaIdDocumentos!=null && listaIdDocumentos.size()>0) {
				
				DocumentoQuery documentoQuery= new DocumentoQuery();				
				Iterator<Long> itListaIdDocumentos = listaIdDocumentos.iterator();				
				while (itListaIdDocumentos.hasNext()){
					Long id = (Long) itListaIdDocumentos.next();
					documentoQuery.addIdIn(id);
				}				
				ArrayList<DocumentoBean> listadoDocumentos = documentoManager.buscarDocumentoCombo(documentoQuery);
				setRequestAttribute("documentos", listadoDocumentos);			
			}
		}
		
		setRequestAttribute("idSolicitud", idSolicitudes);
		setRequestAttribute("entorno", "Correos");
		setRequestAttribute("destinatarios", destinatarios);
		
	}catch(Exception eGenerico){
		logger.error(" Error VerContactarCiudadanoSpring - doExecute:" ,eGenerico);
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
	 * Obtiene el documento manager.
	 *
	 * @return the documentoManager
	 */
	public DocumentoManager getDocumentoManager() {
		return documentoManager;
	}

	/**
	 * Establece el documento manager.
	 *
	 * @param documentoManager the documentoManager to set
	 */
	public void setDocumentoManager(DocumentoManager documentoManager) {
		this.documentoManager = documentoManager;
	}
	
}
