package es.map.ipsg.action.solicitud;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.TipoDocumento;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.CrearDocumentoForm;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class DocumentosJustificanteSolicitudSpring.
 */
public class DocumentosJustificanteSolicitudSpring extends AbstractSpring{

	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DocumentosJustificanteSolicitudSpring.class);
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante STRING_ERRORDOCUMENTOSJUSTIFICANTESOLICITUD. */
	private static final String STRING_ERRORDOCUMENTOSJUSTIFICANTESOLICITUD = " Error DocumentosJustificanteSolicitudSpring";
	
	/** La constante STRING_SUCCESS. */
	private static final String STRING_SUCCESS = "success";
	
	/** La constante STRING_NOSUCCESS. */
	private static final String STRING_NOSUCCESS = "nosuccess";
	
	/** La constante STRING_DESCRIPCIONERROR. */
	private static final String STRING_DESCRIPCIONERROR = "descripcionError";

	/** el properties. */
	private static Properties properties;


	/**
	 * Crea una nueva documentos justificante solicitud spring.
	 */
	public DocumentosJustificanteSolicitudSpring() {
		try {
			setDocumentoManager((DocumentoManager) getBean("documentoManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.warn(e);
			logger.error(STRING_ERRORDOCUMENTOSJUSTIFICANTESOLICITUD,e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("DocumentosJustificanteSolicitudSpring -start");
		CrearDocumentoForm theForm = (CrearDocumentoForm) form;
		boolean exito = false;
		String retn="";

		logger.info("Entra en el Action");
		try{

			// Rol del usuario
			String sUsernameLogin = recuperarUsuario();
			
			if (sUsernameLogin.equals("error")) {
				return sUsernameLogin;
			}

			//Obtenemos el Usuario que está logueado en la aplicación para insertarlo en el log
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que está logueado

			UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
			Usuario usuario = usuarioManager.toUsuario(usuarioBean);
			Integer idUsuario = usuario.getId();

			String sPerfilUsuario = "";
			sPerfilUsuario = comprobarPerfilUsuario(idUsuario);

			setRequestAttribute("sPerfilUsuario", sPerfilUsuario);

			//Este es el que viene informado por defecto (URL)
			String idSolicitud = this.getRequest().getParameter("id");
			logger.info("DocumentosSolicitud REQUEST ID: "+idSolicitud);

			// Confirmación para guardar el justificante de registro generado
			String accion = this.getRequest().getParameter("accion");
			
			if(accion != null && accion.equals("Cancelar")){
				// Borrar justificante de la carpeta de escritura
				if(idSolicitud != null){
					ArrayList<DocumentoBean> listaDocumentosBean = documentoManager.buscarDocumentoCombo(cargarBusquedaJustificante(Long.parseLong(idSolicitud)));
					for (DocumentoBean documentoBean : listaDocumentosBean) {
						documentoManager.borrarDocumento(documentoBean);
					}
					retn=STRING_SUCCESS;
				}else{
					logger.error("No se ha podido borrar el documento generado");
					retn=STRING_NOSUCCESS;
				}		
			}else if(accion != null && accion.equals("Aceptar")){
				// Mover Justificante generado a la carpeta de solo Lectura
				if(idSolicitud != null){
					ArrayList<DocumentoBean> listaDocumentosBean = documentoManager.buscarDocumentoCombo(cargarBusquedaJustificante(Long.parseLong(idSolicitud)));
					documentoManager.copiarFicheros(listaDocumentosBean, properties.getProperty("sistemaficheros.url.escritura"));
					retn=STRING_SUCCESS;
				}else{
					logger.error("No se ha podido mover el documento generado a la ubicacion definitiva");
					retn=STRING_NOSUCCESS;
				}
			}else{
				if(idSolicitud==null||idSolicitud.equalsIgnoreCase("")){
					//Para cuando se Sube un documento se almacena el idSolicitud en el FORM
					idSolicitud=theForm.getIdSolicitud();
				}
				if(!StringUtils.isEmpty(idSolicitud)){	
					Long idSol = Long.parseLong(idSolicitud);
					
					// Variable que indica el tipo de documento/s que se esta/n pasando por request
					// 1-PDF 2-XML 3-AMBOS
					Integer tipoDoc =0;
					ArrayList<DocumentoBean> documentosList;

					ArrayList<DocumentoBean> documentosListAux = new ArrayList<DocumentoBean>();
					DocumentoBean documentoAux, documentoAux1 = new DocumentoBean();
					
					documentoAux1.setRegistroSolicitud(true);
					documentosListAux.add(documentoAux1);				
					
					documentosList = documentoManager.buscarDocumentoCombo(cargarBusquedaJustificante(idSol));
					
					String justificanteREC="0";
					String justificanteSOLICITUD="0";

					//SETEO DE ATRIBUTOS AL OBJETO 1 DE LA LISTA
					TipoDocumento tipoDocAux = new TipoDocumento();
					tipoDocAux.setId(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF);
					tipoDocAux.setDescripcion(Constantes.DESCRIPCION_JUSTIFICANTE_SOLICITUD);
					documentosListAux.get(0).setIdTipoDocumento(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF+"");
					documentosListAux.get(0).setDescripcion(Constantes.DESCRIPCIONSOLICITUD);
					documentosListAux.get(0).setDsTipoDocumento(Constantes.DESCRIPCION_JUSTIFICANTE_SOLICITUD);
					documentosListAux.get(0).setDesTipoDocumento(Constantes.DESCRIPCION_JUSTIFICANTE_SOLICITUD);
					documentosListAux.get(0).setIdSolicitud(Long.valueOf(idSolicitud));
					documentosListAux.get(0).setTipoDocumento(tipoDocAux);
					documentosListAux.get(0).setRegistroRec(false);

					if(documentosList!=null && !documentosList.isEmpty()){
						for(int i=0; i<documentosList.size(); i++){
							DocumentoBean docREG = documentosList.get(i);
							docREG.getId();
							
			
							//						
							//Buscamos los archivos justificantes en alfresco
							String nombreAlfresco = docREG.getNombreAlfresco();
							logger.info("Recuperando el documento: "+nombreAlfresco);
							String entorno = Constantes.ENTORNO_SOLICITUDES;

							//Comprobamos que existe el fichero en alfresco
							boolean salidaDoc = false;
							byte[] salida=null;
							try{
								String ruta = properties.getProperty("sistemaficheros.url.final")+docREG.getUbicacion()+docREG.getNombreAlfresco();
								salidaDoc = documentoManager.existeDocumento(ruta);

								//Si exite documento el alfresco nos lo descargamos directamente
								if (salidaDoc==true){	
									// Obtenemos el flujo de bytes
									salida = obtenerDocumentoSolicitudSalida(docREG,entorno);
									exito= salida!=null;
									logger.info("depues de la descarga, exito= " + exito);
								}
								if (exito) {
									enviarSalida(docREG,salida);
									getLogger().debug("DescargarDocumentoSolicitudSpring -end");
									
								}else{
									logger.error(" Error Documento no existente: "+ruta);
									this.getRequest().setAttribute(STRING_DESCRIPCIONERROR, RESOURCE_BUNDLE.getString("field.documento.sinJustificanteRegistro"));
									
								}

							}catch(Exception eGenerico){						
								this.getRequest().setAttribute(STRING_DESCRIPCIONERROR, RESOURCE_BUNDLE.getString("field.errorGenerico"));
								logger.error(STRING_ERRORDOCUMENTOSJUSTIFICANTESOLICITUD,eGenerico);

							}

							documentoAux=documentosList.get(i);

							if(salidaDoc){
								justificanteSOLICITUD="1";
								documentosListAux.get(0).setId(documentoAux.getId());
							}else{
								justificanteSOLICITUD="0";
								documentosListAux.get(0).setId(0L);
							}
						}
					}
					try{
						Long valor2=documentosListAux.get(0).getId();

						if(valor2==null){
							tipoDocAux = new TipoDocumento();
							tipoDocAux.setId(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF);
							tipoDocAux.setDescripcion(Constantes.DESCRIPCION_JUSTIFICANTE_SOLICITUD);
							documentosListAux.get(0).setIdTipoDocumento(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF+"");
							documentosListAux.get(0).setDescripcion(Constantes.DESCRIPCIONSOLICITUD);
							documentosListAux.get(0).setDsTipoDocumento(Constantes.DESCRIPCION_JUSTIFICANTE_SOLICITUD);
							documentosListAux.get(0).setDesTipoDocumento(Constantes.DESCRIPCION_JUSTIFICANTE_SOLICITUD);
							documentosListAux.get(0).setIdSolicitud(Long.valueOf(idSolicitud));
							documentosListAux.get(0).setTipoDocumento(tipoDocAux);
							documentosListAux.get(0).setId(0L);
						}
					}catch(Exception eGenerico){
						logger.error(STRING_ERRORDOCUMENTOSJUSTIFICANTESOLICITUD,eGenerico);
					}

					setRequestAttribute("justificanteREC", justificanteREC);
					setRequestAttribute("justificanteSOLICITUD", justificanteSOLICITUD);
					setRequestAttribute("idSolicitud", idSol);
					setRequestAttribute("documentosAux", documentosListAux);
					setRequestAttribute("documentos", documentosList);
					setRequestAttribute("tipoDocumentos", tipoDoc);
					theForm.setEntorno("Solicitudes");
					theForm.setIdSolicitud(idSolicitud);
					if(exito)
						retn=STRING_SUCCESS;
					else
						retn=STRING_NOSUCCESS;

				}	
				this.getResponse().setHeader("Cache-Control", "no-cache");
				this.getResponse().setHeader("Pragma", "no-cache");
				this.getResponse().setDateHeader("Expires", 0);
				getLogger().debug("DocumentosJustificanteSolicitudSpring -end");	
			}

					
		}catch(Exception eGenerico){
			logger.error(STRING_ERRORDOCUMENTOSJUSTIFICANTESOLICITUD,eGenerico);
			this.getRequest().setAttribute(STRING_DESCRIPCIONERROR, RESOURCE_BUNDLE.getString("field.errorGenerico"));
			retn="errorGenerico";
		}		
		return retn;
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
			logger.error("Error DocumentosJustificanteSolicitudSpring - recuperarUsuarioSesion"+ sUsernameLogin,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return "error";
		}
	}

	/**
	 * Enviar salida.
	 *
	 * @param doc el doc
	 * @param salida el salida
	 */
	public void enviarSalida(DocumentoBean doc,byte[] salida) {
		String idAlfresco = doc.getNombreAlfresco();
		javax.servlet.ServletOutputStream stream;
		try {
			stream = this.getResponse().getOutputStream();
			StringBuilder contentDisposition = new StringBuilder();
			contentDisposition.append("attachment; filename=\"").append(idAlfresco).append("\"");
			HttpServletResponse resp = this.getResponse();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", contentDisposition.toString());
			stream.write(salida);
			stream.flush();
			stream.close();
		} catch (IOException e) {
			logger.error(" Error DocumentosJustificanteSolicitudSpring - enviarSalida",e);
		}
	}

	/**
	 * Obtener documento solicitud salida.
	 *
	 * @param doc el doc
	 * @param entorno el entorno
	 * @return el byte[]
	 * @throws Exception el exception
	 */
	public byte[] obtenerDocumentoSolicitudSalida(DocumentoBean doc,String entorno) throws Exception {

		byte[] salida = null;

		try {
			salida = documentoManager.obtenerContenidoDocumento(doc);

			return salida;
		} catch (NumberFormatException e) {
			logger.error(" Error DocumentosJustificanteSolicitudSpring - DescargarDocumentoSolicitudServlet",e);
			throw new ApplicationException("NumberFormatException DescargarDocumentoSolicitudServlet");
		} catch (ApplicationException e) {
			logger.error("Error DocumentosJustificanteSolicitudSpring - exception obtenerContenidoDocumentoSolicitud en ACTION",e);
			
			return salida;
		} catch (Exception e) {
			logger.error("Error DocumentosJustificanteSolicitudSpring - Exception DescargarDocumentoSolicitudAction",e);
			throw new Exception("Exception DescargarDocumentoSolicitudAction");
		} finally {
			logger.debug("doGet ObtenerDocumentoSolicitudAction - end");
		}

	}

	/**
	 * Comprobar perfil usuario.
	 *
	 * @param idUsuario el id usuario
	 * @return el string
	 */
	private String comprobarPerfilUsuario(Integer idUsuario) {
		String sPerfil = "";
		UsuarioBean usuarioBean;
		usuarioBean = usuarioManager.obtenerUsuario(idUsuario);

		if(usuarioBean.getIdPerfil() != null &&   usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_CONSULTOR)){
			sPerfil = Constantes.ROL_CONSULTOR;
		}
		if(usuarioBean.getIdPerfil() != null &&   usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_SOPORTE)){
			sPerfil = Constantes.ROL_SOPORTE;
		}
		if(usuarioBean.getIdPerfil() != null && usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_GESTOR)){
			sPerfil = Constantes.ROL_GESTOR;
		}
		if(usuarioBean.getIdPerfil() != null && usuarioBean.getIdPerfil().toString().equals(Constantes.PERFIL_ADMINISTRADOR)){
			sPerfil = Constantes.ROL_ADMINISTRADOR;
		}

		return sPerfil;
	}
	
	/**
	 * Metodo que carga las condiciones para la busqueda de justificantes de registro.
	 *
	 * @param idSol el id sol
	 * @return documentoQuery
	 */
	private DocumentoQuery cargarBusquedaJustificante(Long idSol) {
		
		DocumentoQuery documentoQuery = new DocumentoQuery();
		documentoQuery.addSolicitudIn(idSol);
		Integer tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF;
		documentoQuery.addTipoDocumentoIn(tpDocumento);
		tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_CATALAN;
		documentoQuery.addTipoDocumentoIn(tpDocumento);
		tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_EUSKERA;
		documentoQuery.addTipoDocumentoIn(tpDocumento);
		tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_GALLEGO;
		documentoQuery.addTipoDocumentoIn(tpDocumento);
		tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_VALENCIANO;
		documentoQuery.addTipoDocumentoIn(tpDocumento);
		
		return documentoQuery;
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

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
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

}