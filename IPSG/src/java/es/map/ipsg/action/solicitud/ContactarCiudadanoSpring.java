package es.map.ipsg.action.solicitud;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CorreoElectronico;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.LogGenerico;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CorreoElectronicoBean;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.ParametrosConfiguracionBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.ContactarCiudadanoForm;
import es.map.ipsg.manager.CorreoElectronicoManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.LogGenericoManager;
import es.map.ipsg.manager.ParametroConfiguracionManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.EnvioMails;
import es.map.ipsg.util.UtilesIPSG;

/**
 * El Class ContactarCiudadanoSpring.
 */
public class ContactarCiudadanoSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOURCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);	
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ContactarCiudadanoSpring.class);
	
	/** el properties. */
	private static Properties properties;
	
	/** el correo electronico manager. */
	//Declaracion de Manager
	private CorreoElectronicoManager correoElectronicoManager;	
	
	/** el log generico manager. */
	private LogGenericoManager logGenericoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el parametro configuracion manager. */
	private ParametroConfiguracionManager parametroConfiguracionManager;
	
	/** el documento manager. */
	private DocumentoManager documentoManager;

	/**
	 * Crea una nueva contactar ciudadano spring.
	 */
	public ContactarCiudadanoSpring() {
		try {
				setCorreoElectronicoManager((CorreoElectronicoManager)getBean("correoElectronicoManager"));
				setLogGenericoManager((LogGenericoManager) getBean("logGenericoManager"));
				setUsuarioManager ((UsuarioManager) getBean ("usuarioManager"));
				setParametroConfiguracionManager((ParametroConfiguracionManager)getBean("parametroConfiguracionManager"));
				setDocumentoManager((DocumentoManager) getBean("documentoManager"));
				properties = (Properties) getBean("propertiesBean");
			} catch (Exception e) {
				logger.error("Error ContactarCiudadanoSpring" ,e);
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
		String menu_solicitudes = RESOURCE_BUNDLE.getString("field.menu.solicitudes");
		this.getRequest().getSession().setAttribute("pagActiva",menu_solicitudes);
		//************************************************************************
		
		//Cogemos el form del jsp
		ContactarCiudadanoForm formulario;
		formulario = (ContactarCiudadanoForm) form;
	try{
		if(formulario.getAccion().equalsIgnoreCase("Enviar")){
			String[] solicitudes = (String[]) getSessionAttribute("idSolicitudesSel");
			
			CorreoElectronicoBean correoElectronicoBean = new CorreoElectronicoBean();
			correoElectronicoBean.setDe(formulario.getDe());
			correoElectronicoBean.setAsunto(formulario.getAsunto());
			
			ParametrosConfiguracionQuery parametrosConfiguracionQuery = new ParametrosConfiguracionQuery();
			//ESTA PUESTO EL DE ALERTAS PORQUE NO HAY NINGUNO ESPECIFICO
			parametrosConfiguracionQuery.setNombreCampo(RESOURCE_BUNDLE.getString("field.parametroConfiguracion.nombreCampo.correoAlertas"));
			ParametrosConfiguracionBean parametroConfig = parametroConfiguracionManager.buscarParametroConfiguracionUnico(parametrosConfiguracionQuery);
			
			String defaultTo = parametroConfig.getValorCampo();			
			String mensaje = formulario.getCuerpoMensaje();
			
			if(mensaje.length()>2000){				
				String mensajeModificado = mensaje.substring(0, 1990);
				correoElectronicoBean.setMensaje(mensajeModificado);
			}else{
				correoElectronicoBean.setMensaje(mensaje);
			}
			
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE,0);
			Date fechaEnvio = c.getTime();
			
			correoElectronicoBean.setFechaEnvio(fechaEnvio);
			
			//Obtenemos la lista de correos de los ciudadanos que van a recibir el correo para mandarlo como copia(bcc)
			String correos = formulario.getPara();
			
			if(correos.length()>1000){
				correos=correos.substring(0, 990);
			}
			
			String[] arrayCorreos = correos.split(",");
			
			InternetAddress[] direccionesBcc = null;
			
			if(arrayCorreos != null && arrayCorreos.length==1){//Solo un destinatario
				//Si el mail se envia a un ciudadano. To = ciudadano, Bcc = correo defecto
				direccionesBcc = new InternetAddress[1];
				direccionesBcc[0] = new InternetAddress(defaultTo);
				correoElectronicoBean.setPara(arrayCorreos[0]);
				correoElectronicoBean.setBcc(direccionesBcc);
			}else{
				//Si el mail se envia a varios ciudadanos. To = correo defecto, Bcc = lista de correos
				direccionesBcc = new InternetAddress[arrayCorreos.length];
				for(int j=0;j<arrayCorreos.length;j++){
					direccionesBcc[j] =  new InternetAddress(arrayCorreos[j]); 
				}
				correoElectronicoBean.setPara(defaultTo);
				correoElectronicoBean.setBcc(direccionesBcc);
			}			
			
			//Meter los adjuntos
			ArrayList<Long> listaIdDocumentos = (ArrayList<Long>) getSessionAttribute("listaIdDocumentos");
			String sRutaCarpeta =null;
			
			if(listaIdDocumentos!=null && listaIdDocumentos.size()>0) {
				DocumentoQuery documentoQuery= new DocumentoQuery();
				Iterator<Long> itListaIdDocumentos = listaIdDocumentos.iterator();
				while (itListaIdDocumentos.hasNext()){
					Long id = (Long) itListaIdDocumentos.next();
					documentoQuery.addIdIn(id);
				}
				ArrayList <DocumentoBean> listDocumentos = documentoManager.buscarDocumentoCombo(documentoQuery);
				
				if(listDocumentos!=null && listDocumentos.size()>0) {
					
					setRequestAttribute("documentos", listDocumentos);	
					
					Iterator<DocumentoBean> itListDocumentos = listDocumentos.iterator();
					ArrayList <File> adjuntos = new ArrayList<File>();
					Long temporalDir = System.currentTimeMillis();	
					sRutaCarpeta = properties.getProperty("sistemaficheros.rutaDocsTemp")+temporalDir;
					File directorio = new File(sRutaCarpeta);
			    	directorio.mkdir();
					while (itListDocumentos.hasNext()){
						DocumentoBean doc = (DocumentoBean) itListDocumentos.next();
						byte[]  salida = documentoManager.obtenerContenidoDocumento(doc);
						adjuntos.add(crearFile(sRutaCarpeta+File.separator+doc.getNombre()+doc.getExtension(),salida));
					}
					
					correoElectronicoBean.setAdjuntosFile(adjuntos);
				}
			}
			
			if(EnvioMails.envioMail(correoElectronicoBean)){
				correoElectronicoBean.setEstado(Constantes.CORREO_ELECTRONICO_ESTADO_ENVIADO);
				setRequestAttribute("mensajeConfirmacion" , RESOURCE_BUNDLE.getString("field.incidencias.enviarMail.correcto"));
				logger.info("El mensaje se ha enviado correctamente");
			}else{
				correoElectronicoBean.setEstado(Constantes.CORREO_ELECTRONICO_ESTADO_PENDIENTE);
				setRequestAttribute("mensajeConfirmacion" , RESOURCE_BUNDLE.getString("field.incidencias.enviarMail.incorrecto"));
				logger.error("El mensaje no se ha podido enviar");
			}		
			
			//Se borra la carpeta con los documentos adjuntos generados.
			if(sRutaCarpeta!=null){
				UtilesIPSG.borraCarpeta(sRutaCarpeta);
			}
			ArrayList<Long> listadoSolicitudes = new ArrayList<Long>();
			
			//Obtenemos un array con las id de las solicitudes para convertir posteriormente el array de String a un array de Long
			for(int i=0;i<solicitudes.length;i++){
				
				listadoSolicitudes.add(Long.valueOf(solicitudes[i]));
			}
			
			Long[] idSolicitudes = new Long[listadoSolicitudes.size()];
			
			for(int i=0;i<solicitudes.length;i++){
				idSolicitudes[i]=listadoSolicitudes.get(i);
			}
			
			correoElectronicoBean.setIdSolicitud(idSolicitudes);
			Long idCorreo = correoElectronicoManager.guardarCorreoElectronico(correoElectronicoBean);
			
			//Actualizar tabla documentos con el idCorreoElectronico
			if(listaIdDocumentos!=null && listaIdDocumentos.size()>0) {
				DocumentoQuery documentoQuery= new DocumentoQuery();
				Iterator<Long> itListaIdDocumentos = listaIdDocumentos.iterator();
				while (itListaIdDocumentos.hasNext()){
					Long id = (Long) itListaIdDocumentos.next();
					documentoQuery.addIdIn(id);
				}
				ArrayList <DocumentoBean> listDocumentos = documentoManager.buscarDocumentoCombo(documentoQuery);
				
				if(listDocumentos!=null && listDocumentos.size()>0) {
					
					Iterator<DocumentoBean> itListDocumentos = listDocumentos.iterator();

					while (itListDocumentos.hasNext()){
						DocumentoBean doc = (DocumentoBean) itListDocumentos.next();
						CorreoElectronico correoElectronico = new CorreoElectronico();
						correoElectronico.setId(idCorreo);
						
						doc.setCorreoElectronico(correoElectronico);
						
						documentoManager.modificarDocumento(doc);
						
					}
				}
			}
				
			//Obtenemos el Usuario que está logueado en la aplicación para insertarlo en el log
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			String username = recuperarUsuario();
			
			if (username.equals("error")) {
				return username;
			}
			
			usuarioQuery.setLogin(username);
			
			UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
			Usuario usuario = usuarioManager.toUsuario(usuarioBean);
			
			//Registramos en el log la operacion realizada
			LogGenerico logGenerico = new LogGenerico();
			Date dHoy = new Date();
				
			logGenerico.setFecha(dHoy);
			logGenerico.setTipoOperacion(Constantes.OPERACION_ALTA);
			logGenerico.setTipoFuncionalidad(Constantes.FUNCIONALIDAD_CONTACTAR_CIUDADANO);
			logGenerico.setUsuario(usuario); 
			logGenerico.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.correoElectronico.contactar.detalleOperacion"));
			logGenerico.setIdTablaOrigen(idCorreo);			
			
			//Guardamos el log
			logGenericoManager.guardarLogGenerico(logGenerico);
			
			setRequestAttribute("entorno", formulario.getEntorno());
			setRequestAttribute("destinatarios", formulario.getPara());
			setRequestAttribute("idSolicitud", formulario.getSolicitudesSel());
			setRequestAttribute("campo", formulario.getAccion());
			formulario.setCampo(formulario.getAccion());
			
			return "success";
		}
		
	}catch(Exception eGenerico){
		logger.error("Error ContactarCiudadanoSpring" ,eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		return null;
	}
	
	/**
	 * Recuperar usuario.
	 *
	 * @return el string
	 */
	private String recuperarUsuario() {
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			String username = usuarioBean.getLogin();
			return username;
		}catch(Exception e){
			logger.error("Error ContactarCiudadanoSpring - recuperarUsuarioSesion" ,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
		}
	}
	
	/**
	 * Crear file.
	 *
	 * @param ruta el ruta
	 * @param salida el salida
	 * @return el file
	 */
	public File crearFile(String ruta,byte[]  salida){
		File adjuntoFile = new File(ruta); 
		try(FileOutputStream fo = new FileOutputStream(adjuntoFile)){			
			fo.write(salida);		
		} catch (FileNotFoundException e1) {
			logger.error("Error ContactarCiudadanoSpring - crearFile - archivo no encontrado" ,e1);
		} catch (IOException e) {
			logger.error("Error ContactarCiudadanoSpring- crearFile" ,e);
		}
		return adjuntoFile;
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
	 * Obtiene el parametro configuracion manager.
	 *
	 * @return the parametroConfiguracionManager
	 */
	public ParametroConfiguracionManager getParametroConfiguracionManager() {
		return parametroConfiguracionManager;
	}

	/**
	 * Establece el parametro configuracion manager.
	 *
	 * @param parametroConfiguracionManager the parametroConfiguracionManager to set
	 */
	public void setParametroConfiguracionManager(
			ParametroConfiguracionManager parametroConfiguracionManager) {
		this.parametroConfiguracionManager = parametroConfiguracionManager;
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
