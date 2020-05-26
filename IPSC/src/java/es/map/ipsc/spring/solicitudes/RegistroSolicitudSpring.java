package es.map.ipsc.spring.solicitudes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.KeyStore;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.axis.attachments.OctetStream;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.apache.spring.Globals;
import org.springframework.util.StringUtils;

import com.itextpdf.text.pdf.PdfCopyFields;
import com.itextpdf.text.pdf.PdfReader;

import es.gob.afirma.signature.SignatureConstants;
import es.gob.afirma.signature.Signer;
import es.gob.afirma.signature.SignersFactory;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.Pais;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ips.model.Provincia;
import es.map.ips.model.RegistroSolicitudQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.SolicitudPropiosQuery;
import es.map.ips.model.TipoDocumentoQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.AuditoriaBean;
import es.map.ipsc.bean.CamposPropiosBean;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.ConvocatoriaBean;
import es.map.ipsc.bean.CorreoElectronicoBean;
import es.map.ipsc.bean.DetallePagoSolicitudBean;
import es.map.ipsc.bean.DocumentoBean;
import es.map.ipsc.bean.JustificanteBean;
import es.map.ipsc.bean.JustificantePagoBean;
import es.map.ipsc.bean.LogServicioBean;
import es.map.ipsc.bean.LogSolicitudBean;
import es.map.ipsc.bean.ParametrosConfiguracionBean;
import es.map.ipsc.bean.RegistroSolicitudBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.bean.SolicitudPropiosBean;
import es.map.ipsc.clienteWS.registrarRec3.RegistrarServiceLocator;
import es.map.ipsc.clienteWS.registrarRec3.RegistrarServiceSoapBindingStub;
import es.map.ipsc.clienteWS.registrarRec3.RegistroType;
import es.map.ipsc.clienteWS.registroRec3.type.AutenticacionType;
import es.map.ipsc.clienteWS.registroRec3.type.DocumentoType;
import es.map.ipsc.clienteWS.registroRec3.type.InteresadoType;
import es.map.ipsc.clienteWS.registroRec3.type.JustificanteType;
import es.map.ipsc.clienteWS.registroRec3.type.RespuestaType;
import es.map.ipsc.exception.IpsException;
import es.map.ipsc.form.RegistroSolicitudForm;
import es.map.ipsc.manager.camposPropios.CamposPropiosManager;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.manager.logs.LogServiciosManager;
import es.map.ipsc.manager.registroAuditoria.RegistroAuditoriaManager;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.RegistroSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudPropioManager;
import es.map.ipsc.manager.tablaBase.ParametroConfiguracionManager;
import es.map.ipsc.manager.tablaBase.TablaBaseManager;
import es.map.ipsc.manager.usuario.UsuarioManager;
import es.map.ipsc.res.ResourceLocator;
import es.map.ipsc.spring.AbstractSecureSpring;
import es.map.ipsc.utils.CSVInside;
import es.map.ipsc.utils.CSVStorage;
import es.map.ipsc.utils.EnvioMails;
import es.map.ipsc.utils.IpsUtils;
import es.map.ipsc.utils.Jasper;
import es.map.ipsc.utils.SHA0;
import es.map.ipsc.utils.SistemaFicheros;
import es.map.ipsc.utils.Util;
import es.mpt.dsic.inside.ws.service.EeUtilService;
import es.mpt.dsic.inside.ws.service.EeUtilServiceProxy;
import es.redsara.misim.misim_bus_webapp.EnvioMensajesServiceWSBindingPortType;
import es.redsara.misim.misim_bus_webapp.EnvioMensajesServiceWSBindingPortTypeProxy;
import es.redsara.misim.misim_bus_webapp.peticion.Adjunto;
import es.redsara.misim.misim_bus_webapp.peticion.DestinatarioMail;
import es.redsara.misim.misim_bus_webapp.peticion.Destinatarios;
import es.redsara.misim.misim_bus_webapp.peticion.MensajeEmail;
import es.redsara.misim.misim_bus_webapp.peticion.Mensajes;
import es.redsara.misim.misim_bus_webapp.peticion.Peticion;
import es.redsara.misim.misim_bus_webapp.respuesta.Respuesta;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * El Class RegistroSolicitudSpring.
 */
public class RegistroSolicitudSpring extends AbstractSecureSpring {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(RegistroSolicitudSpring.class);
	
	/** La constante TIMEOUT. */
	private static final int TIMEOUT = 30000;
	
	/** La constante BUNDLE_ERROR. */
	private static final String BUNDLE_ERROR = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_ERROR);
	
	/** La constante STRING_ERRORDESCRIPCION. */
	private static final String STRING_ERRORDESCRIPCION = "errorDescripcion";
	
	/** La constante STRING_ERRORUSUARIO. */
	private static final String STRING_ERRORUSUARIO = "errorUsuario";
	
	/** La constante STRING_FIELD_SOLICITUD_REGISTRO_ERRORREALIZACION. */
	private static final String STRING_FIELD_SOLICITUD_REGISTRO_ERRORREALIZACION = "field.solicitud.registro.errorRealizacion";
	
	/** La constante STRING_NOSUCCESS. */
	private static final String STRING_NOSUCCESS = "nosuccess";
	
	/** La constante STRING_CDASUNTO. */
	private static final String STRING_CDASUNTO = "cdAsunto";
	
	/** La constante STRING_HTML. */
	private static final String STRING_HTML = ".html";
	
	/** La constante STRING_ERROR_HASHFILE. */
	private static final String STRING_ERROR_HASHFILE = "Error hashFile";
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/** La constante STRING_CERTIFICADOS_ERROR_GESTORDOCUMENTAL. */
	private static final String STRING_CERTIFICADOS_ERROR_GESTORDOCUMENTAL = "certificados.error.gestorDocumental";
	
	/** La constante STRING_SIMPLEDATEFORMAT_HHMMSS. */
	private static final String STRING_SIMPLEDATEFORMAT_HHMMSS = "dd/MM/yyyy HH:mm:ss";
	
	/** La constante STRING_REGISTRO. */
	private static final String STRING_REGISTRO = "registro";
	
	/** La constante STRING_ERROR_PRIVATEKEYENTRY. */
	private static final String STRING_ERROR_PRIVATEKEYENTRY = "Error  PrivateKeyEntry: ";
	
	/** La constante STRING_SIMPLEDATEFORMAT_DDMMYYYY. */
	private static final String STRING_SIMPLEDATEFORMAT_DDMMYYYY = "dd/MM/yyyy";
	
	/** La constante STRING_CORRECTO. */
	private static final String STRING_CORRECTO = "correcto";
	
	/** La constante STRING_URL_ENTORNO_IPSC. */
	private static final String STRING_URL_ENTORNO_IPSC = "url.entorno.ipsc";
	
	/** La constante STRING_DIV_CLASS_CLEAR_DIV. */
	private static final String STRING_DIV_CLASS_CLEAR_DIV = "                                    <div class='clear'></div>";
	
	/** La constante STRING_DIV. */
	private static final String STRING_DIV = "                                           </div>";
	
	/** La constante STRING_DIV2. */
	private static final String STRING_DIV2 = "                                    </div>";
	
	/** La constante STRING_DIV3. */
	private static final String STRING_DIV3 = "                                       </div>";
	
	/** La constante STRING_DIV4. */
	private static final String STRING_DIV4 = "                                             </div>";
	
	/** La constante STRING_DIV5. */
	private static final String STRING_DIV5 = "                                                    </div>";
	
	/** La constante STRING_DIV6. */
	private static final String STRING_DIV6 = "                                         </div>";
	
	/** La constante STRING_DIV7. */
	private static final String STRING_DIV7 = "                                                           </div>";
	
	/** La constante STRING_DIV8. */
	private static final String STRING_DIV8 = "                                   </div> ";
	
	/** La constante STRING_DIV9. */
	private static final String STRING_DIV9 = "                                            </div>";
	
	/** La constante STRING_DIV10. */
	private static final String STRING_DIV10 = "                                     </div>";
	
	/** La constante STRING_DIV11. */
	private static final String STRING_DIV11 = "</div>";
	
	/** La constante STRING_DIV_CLASS_CAPA99. */
	private static final String STRING_DIV_CLASS_CAPA99 = "                                    <div class='capa99'>";
	
	/** La constante STRING_DIV_CLASS_CAPA99_2. */
	private static final String STRING_DIV_CLASS_CAPA99_2 = "                                       <div class='capa99'>";
	
	/** La constante STRING_DIV_CLASS_CAPA99_3. */
	private static final String STRING_DIV_CLASS_CAPA99_3 = "                                             <div class='capa99'>";
	
	/** La constante STRING_DIV_CLASS_CAPA99_4. */
	private static final String STRING_DIV_CLASS_CAPA99_4 = "                              <div class='capa99'>";
	
	/** La constante STRING_DIV_CLASS_CAPA99_5. */
	private static final String STRING_DIV_CLASS_CAPA99_5 = "                                     <div class='capa99'>";
	
	/** La constante STRING_DIV_CLASS_LABELIZQDET_ROJO. */
	private static final String STRING_DIV_CLASS_LABELIZQDET_ROJO = "                                           <div class='labelIzqDet_Rojo'>";
	
	/** La constante STRING_DIV_CLASS_LABELIZQDET_ROJO_2. */
	private static final String STRING_DIV_CLASS_LABELIZQDET_ROJO_2 = "                                                    <div class='labelIzqDet_Rojo'>";
	
	/** La constante STRING_DIV_CLASS_LABELIZQDET_ROJO_3. */
	private static final String STRING_DIV_CLASS_LABELIZQDET_ROJO_3 = "                                            <div class='labelIzqDet_Rojo'>";
	
	/** La constante STRING_DIV_CLASS_LABELDRC. */
	private static final String STRING_DIV_CLASS_LABELDRC = "                                           <div class='labelDrc'>";
	
	/** La constante STRING_DIV_CLASS_LABELDRC_2. */
	private static final String STRING_DIV_CLASS_LABELDRC_2 = "                                             <div class='labelDrc'>";
	
	/** La constante STRING_DIV_CLASS_LABELDRC_3. */
	private static final String STRING_DIV_CLASS_LABELDRC_3 = "                                                    <div class='labelDrc'>";
	
	/** La constante STRING_DIV_CLASS_LABELDRC_4. */
	private static final String STRING_DIV_CLASS_LABELDRC_4 = "                                         <div class='labelDrc'>";
	
	/** La constante STRING_DIV_CLASS_LABELDRC_5. */
	private static final String STRING_DIV_CLASS_LABELDRC_5 = "                                            <div class='labelDrc'>";
	
	/** La constante STRING_DIV_CLASS_CLEAR. */
	private static final String STRING_DIV_CLASS_CLEAR = "                                       <div class='clear'></div>";
	
	/** La constante STRING_DIV_CLASS_CLEAR_2. */
	private static final String STRING_DIV_CLASS_CLEAR_2 = "                                                    <div class='clear'></div>";
	
	/** La constante STRING_DIV_CLASS_CLEAR_3. */
	private static final String STRING_DIV_CLASS_CLEAR_3 = "                                    <div class='clear' />";
	
	/** La constante STRING_DIV_CLASS_CLEAR_4. */
	private static final String STRING_DIV_CLASS_CLEAR_4 = "                                     <div class='clear' />";
	
	/** La constante STRING_DIV_CLASS_CLEAR_5. */
	private static final String STRING_DIV_CLASS_CLEAR_5 = "                                             <div class='clear'></div>";
	
	
	
	
	
	/** el registro solicitudes manager. */
	private RegistroSolicitudManager registroSolicitudesManager;
	
	/** el solicitudes manager. */
	private SolicitudManager solicitudesManager;
	
	/** el tabla base manager. */
	private TablaBaseManager tablaBaseManager;
	
	/** el documento convocatorias manager. */
	private DocumentosConvocatoriaManager documentoConvocatoriasManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el parametro configuracion manager. */
	private ParametroConfiguracionManager parametroConfiguracionManager;
	
	/** el log servicios manager. */
	private LogServiciosManager logServiciosManager;
	
	/** el convocatoria manager. */
	private ConvocatoriasManager convocatoriaManager; 
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el solicitud propio manager. */
	private SolicitudPropioManager solicitudPropioManager;
	
	/** el registro auditoria manager. */
	private RegistroAuditoriaManager registroAuditoriaManager;
	
	/** el campos propios manager. */
	private CamposPropiosManager camposPropiosManager;
	
	/** el properties. */
	private Properties properties;
		
	/**
	 * Crea una nueva registro solicitud spring.
	 */
	public RegistroSolicitudSpring() {
		try{			
			setRegistroSolicitudesManager((RegistroSolicitudManager) getBean("registroSolicitudesManager"));
			setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
			setTablaBaseManager((TablaBaseManager) getBean("tablasBaseManager"));
			setDocumentoConvocatoriasManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));
			setLogServiciosManager((LogServiciosManager) getBean("logServiciosManager"));
			setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudesManager"));
			setSolicitudPropioManager((SolicitudPropioManager) getBean("solicitudPropioManager"));
			setRegistroAuditoriaManager((RegistroAuditoriaManager) getBean("registroAuditoriaManager"));
			setCamposPropiosManager((CamposPropiosManager) getBean("camposPropiosManager"));   
			properties = (Properties) getBean("propertiesBean");
		}catch(Exception e){
			logger.error("RegistroSolicitudAction - Error:" ,e);
			logger.warn(e);
			
		}
	}

	/**
	 * Realizar comprobaciones.
	 *
	 * @param registroSolicitudForm el registro solicitud form
	 * @param usuActual el usu actual
	 * @param solicitudAuxRegistrarBean el solicitud aux registrar bean
	 * @return el string
	 */
	private String realizarComprobaciones(RegistroSolicitudForm registroSolicitudForm, CiudadanoBean usuActual, SolicitudBean solicitudAuxRegistrarBean){
		
		// Comprobar si el usuario esta en la sesion		
		if(usuActual == null){
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.usuario"));
			return STRING_ERRORUSUARIO;
		}else{
			if(usuActual.getNif() == null){
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.usuario"));
				return STRING_ERRORUSUARIO;
			}
		}

		// Recuperamos la convocatoria asociada a la solicitud
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(solicitudAuxRegistrarBean.getIdConvocatoria());
		ConvocatoriaBean convocatoriaBean;
		convocatoriaBean = convocatoriaManager.buscarConvocatoriaId(convocatoriaQuery);

		// Verificamos si la convocatoria aun se encuentra en estado Publicada o subsanada
		if(!(convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_PUBLICADA) || convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_SUBSANADA))){
			logger.info("La convocatoria con id: "+convocatoriaBean.getId()+" no esta en estado publicado.");
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.convocatoriaNoPublicada"));
			return STRING_ERRORUSUARIO;
		}		

		// Comprobar si el usuario introducido coincide con el del certificado en caso del registro por parte de un ciudadano
		if (!usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO) && !usuActual.getNif().equals(registroSolicitudForm.getNif())) {
		
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.usuarioSolicitudError"));
			return STRING_ERRORUSUARIO;
		
		}
		

		// Comprobar el tamaño de los ficheros anexados a la solicitud
		int tamanioFichero = calcularTamanioFicheros(registroSolicitudForm);

		Long confTamanioTotal = Long.parseLong(properties.getProperty("conf.tamanioTotalDocumento"))/1000;
		Long confTamanioFichero = Long.parseLong(properties.getProperty("conf.tamanioFichero"))/1000;

		if(tamanioFichero == 1){
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_FIELD_SOLICITUD_REGISTRO_ERRORREALIZACION));
			return STRING_NOSUCCESS;
		}else{
			if(tamanioFichero == 2){
				//El tamaño de uno de los ficheros es mayor de 1Mb
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.registro.tamanioFichero")+" "+confTamanioFichero+"Mb");
				this.setRequestAttribute("id", registroSolicitudForm.getIdSolicitud());

				return "errorDocumentos";
			}else{
				if(tamanioFichero == 3){
					//El tamaño total de los ficheros es superior de 5Mb
					this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.registro.tamanioTotalFicheros")+" "+confTamanioTotal+"Mb");
					this.setRequestAttribute("id", registroSolicitudForm.getIdSolicitud());

					return "errorDocumentos";
				}
			}
		}

		//Si llegamos a este punto es porque se han superado las comprobaciones anteriores
		return "ok";
	}

	/**
	 * Crear registro type.
	 *
	 * @param registroSolicitudForm el registro solicitud form
	 * @param solicitudBean el solicitud bean
	 * @return el registro type
	 */
	private RegistroType crearRegistroType(RegistroSolicitudForm registroSolicitudForm, SolicitudBean solicitudBean){

		RegistroType registroType =  new RegistroType();
		registroType.setCdAsunto(properties.getProperty(STRING_CDASUNTO));
		registroType.setCdOficinaOrigen(properties.getProperty("cdOrOrigen"));
		registroType.setCdUnidadDestino(properties.getProperty("cdUgDestino"));

		//Insertamos el interesado
		InteresadoType interesadoType[] = new InteresadoType[1];
		InteresadoType interesadoAux = new InteresadoType();

		// Se evalua el tipo de documento de identidad del solicitante
		String tipoDocIdentidad = "";

		if(Util.esNie(solicitudBean.getNif())){
			tipoDocIdentidad = Constantes.INTERESADO_TIPO_DOCUMENTO_EXTRANJERO;
		}else{
			tipoDocIdentidad = Constantes.INTERESADO_TIPO_DOCUMENTO_NACIONAL;
		}
		
		interesadoAux.setCdTipoDocIndentificativoInteresado(tipoDocIdentidad);
		interesadoAux.setTlNumDocIdentificativoInteresado(solicitudBean.getNif());
		
		// Nombre
		if(null != solicitudBean.getNombre()){
			if(solicitudBean.getNombre().length()<Constantes.LONGITUD_MAX_CAMPO_REC){
				interesadoAux.setTlNombreInteresado(solicitudBean.getNombre());
			}else{
				interesadoAux.setTlNombreInteresado(solicitudBean.getNombre().substring(0, Constantes.LONGITUD_MAX_CAMPO_REC-1));
			}
		}else{
			interesadoAux.setTlNombreInteresado(" ");
		}
		
		// Apellido 1
		if(solicitudBean.getPrimerApellido() != null){
			if(solicitudBean.getPrimerApellido().length()<Constantes.LONGITUD_MAX_CAMPO_REC){
				interesadoAux.setTlApellido1Interesado(solicitudBean.getPrimerApellido());
			}else{
				interesadoAux.setTlApellido1Interesado(solicitudBean.getPrimerApellido().substring(0, Constantes.LONGITUD_MAX_CAMPO_REC-1));
			}
		}else{
			interesadoAux.setTlApellido1Interesado("");
		}

		// Apellido 2
		if(solicitudBean.getSegundoApellido() != null){
			if(solicitudBean.getSegundoApellido().length()<Constantes.LONGITUD_MAX_CAMPO_REC){
				interesadoAux.setTlApellido2Interesado(solicitudBean.getSegundoApellido());
			}else{
				interesadoAux.setTlApellido2Interesado(solicitudBean.getSegundoApellido().substring(0, Constantes.LONGITUD_MAX_CAMPO_REC-1));
			}
		}else{
			interesadoAux.setTlApellido2Interesado("");
		}

		if(registroSolicitudForm.getCalle() != null){
			interesadoAux.setTlDireccionInteresado(registroSolicitudForm.getCalle());
		}else{
			interesadoAux.setTlDireccionInteresado("");
		}

		if(registroSolicitudForm.getCodPostal() != null){
			interesadoAux.setCdCodigoPostalInteresado(registroSolicitudForm.getCodPostal());
		}else{
			interesadoAux.setCdCodigoPostalInteresado("");
		}

		if(registroSolicitudForm.getProvinciaDomicilio() != null){
			interesadoAux.setCdProvinciaInteresado(registroSolicitudForm.getProvinciaDomicilio());
		}else{
			interesadoAux.setCdProvinciaInteresado("");
		}

		if(registroSolicitudForm.getMunicipioDomicilio() != null){
			interesadoAux.setCdMunicipioInteresado(registroSolicitudForm.getMunicipioDomicilio());
		}else{
			interesadoAux.setCdMunicipioInteresado("");
		}
		
		if (StringUtils.isEmpty(registroSolicitudForm.getTelefono())) {
			registroSolicitudForm.setTelefono("");
		}

		String telefonoAux = null;

		try{
			int numtelefonoAux = registroSolicitudForm.getTelefono().lastIndexOf('/');
			if(numtelefonoAux != -1){
				telefonoAux = registroSolicitudForm.getTelefono().substring(0,numtelefonoAux);
			}else{
				telefonoAux = registroSolicitudForm.getTelefono();
			}
		}catch(Exception e){
			logger.error("Error telefono",e);
			telefonoAux = registroSolicitudForm.getTelefono();
		}

		interesadoAux.setTlTelefonoContactoInteresado(telefonoAux);
		interesadoType[0] = interesadoAux;
		registroType.setInteresados(interesadoType);

		return registroType;
	}

	/**
	 * HTML.
	 *
	 * @param registroSolicitudForm el registro solicitud form
	 * @param registroType el registro type
	 * @return el octet stream[]
	 */
	private OctetStream[] crearDocumentos(RegistroSolicitudForm registroSolicitudForm, RegistroType registroType){

		int numRegistros = 0;

		//Se añade 1 al numRegistros por el html
		numRegistros++;
		logger.info("NumRegistros: "+numRegistros);
		OctetStream[] docs = new OctetStream[numRegistros];

		//Insertamos los documentos
		logger.info("Creando el objeto para enviar al rec");
		DocumentoType documentoTypeAdjunto[] = new DocumentoType[numRegistros];

		Calendar cal1 = Calendar.getInstance();
		StringBuilder dia = new StringBuilder();
		dia.append(cal1.get(Calendar.DATE)).append("");

		StringBuilder mes = new StringBuilder();
		mes.append(cal1.get(Calendar.MONTH)).append("");

		StringBuilder annio = new StringBuilder();
		annio.append(cal1.get(Calendar.YEAR)).append("");

		StringBuilder hora = new StringBuilder();
		hora.append(cal1.get(Calendar.HOUR)).append("");

		StringBuilder minuto = new StringBuilder();
		minuto.append(cal1.get(Calendar.MINUTE)).append("");

		StringBuilder segundo = new StringBuilder();
		segundo.append(cal1.get(Calendar.SECOND)).append("");

		StringBuilder fecha = new StringBuilder();
		fecha.append(dia).append("/").append(mes).append("/").append(annio).append(" ").append(hora).append(":").append(minuto).append(":").append(segundo);
		SHA0 hash = new SHA0();

		//Se añade el html
		DocumentoType registroDocumentoAux = new DocumentoType();
		registroDocumentoAux.setDsNombre(registroSolicitudForm.getNumJustificante()+STRING_HTML);
		// 04-Original
		registroDocumentoAux.setCdValidez("04");
		// 01-Formulario
		registroDocumentoAux.setCdTipo("01");
		registroDocumentoAux.setBlTimeStamp(fecha.toString());
		
		
		// Version sin firma
		registroDocumentoAux.setBlFirma(" ");
		registroDocumentoAux.setCdFirmado("0");

		String hashHtml=registroSolicitudForm.getDocumentoHTML();
		byte[] byteHash = hashHtml.getBytes();
		String hashFile="";

		try {
			hashFile=hash.getHash(byteHash);
		} catch (NoSuchAlgorithmException e1) {
			logger.error(STRING_ERROR_HASHFILE+ hashFile,e1);
		}

		byte[] codigo64 = hashFile.getBytes();
		hashFile = IpsUtils.encodeBase64(codigo64);
		registroDocumentoAux.setBlValidacionOCSP("s");
		registroDocumentoAux.setDsTipoMIME("s");
		registroDocumentoAux.setBlHash(hashFile);
		registroDocumentoAux.setTlObservaciones("");
		registroDocumentoAux.setBlPKCertificado(registroSolicitudForm.getFirmaExtractoRegistro());
		registroDocumentoAux.setBlDocumento(hashHtml);
		documentoTypeAdjunto[numRegistros-1] = registroDocumentoAux;

		byte[] byteAux;

		try {
			byteAux = IpsUtils.decodeBase64(registroSolicitudForm.getDocumentoHTML());
		} catch (Exception e) {
			logger.error("crearDocumentos - Error:",e);
			return null;
		}

		docs[numRegistros-1] = new OctetStream(byteAux);
		registroType.setDocumentos(documentoTypeAdjunto);

		return docs;
	}	

	/**
	 * Crear documentos 1.
	 *
	 * @param registroSolicitudForm el registro solicitud form
	 * @param registroType el registro type
	 * @return el octet stream[]
	 */
	private OctetStream[] crearDocumentos1(RegistroSolicitudForm registroSolicitudForm, RegistroType registroType){
		// Declarar octetStream
		int numRegistros = 0;
		
		// Comprobar cuantos ficheros posee el resgistro para crear el array
		for(int x=0;x<registroSolicitudForm.getDocumentosFicheros().length;x++){
			if(registroSolicitudForm.getDocumentosFicheros(x) != null && !"".equals(registroSolicitudForm.getDocumentosFicheros(x))){
				numRegistros++;
			}
		}

		// Se añade 1 al numRegistros por el html
		numRegistros++;
		logger.info("NumRegistros: "+numRegistros);
		OctetStream[] docs = new OctetStream[numRegistros];

		// Insertamos los documentos
		logger.info("Creando el objeto para enviar al rec");
		DocumentoType documentoTypeAdjunto[] = new DocumentoType[numRegistros];

		Calendar cal1 = Calendar.getInstance();
		StringBuilder dia = new StringBuilder();
		dia.append(cal1.get(Calendar.DATE)).append("");

		StringBuilder mes = new StringBuilder();
		mes.append(cal1.get(Calendar.MONTH)).append("");

		StringBuilder annio = new StringBuilder();
		annio.append(cal1.get(Calendar.YEAR)).append("");

		StringBuilder hora = new StringBuilder();
		hora.append(cal1.get(Calendar.HOUR)).append("");

		StringBuilder minuto = new StringBuilder();
		minuto.append(cal1.get(Calendar.MINUTE)).append("");

		StringBuilder segundo = new StringBuilder();
		segundo.append(cal1.get(Calendar.SECOND)).append("");

		StringBuilder fecha = new StringBuilder();
		fecha.append(dia).append("/").append(mes).append("/").append(annio).append(" ").append(hora).append(":").append(minuto).append(":").append(segundo);
		SHA0 hash = new SHA0();

		String fileCodificado="";

		for(int i=0;i<registroSolicitudForm.getDocumentosFicheros().length;i++){
			if(registroSolicitudForm.getDocumentosFicheros(i) != null && !"".equals(registroSolicitudForm.getDocumentosFicheros(i))){
				DocumentoType registroDocumentoAux = new DocumentoType();
				String ruta;

				if(registroSolicitudForm.getDocumentoFile(i) != null && !"".equals(registroSolicitudForm.getDocumentoFile(i))){
					ruta = registroSolicitudForm.getDocumentoFile(i).toString();
				}else{
					ruta="";
				}
					
				int numNombreOriginal = ruta.lastIndexOf('/');

				if(numNombreOriginal<0){
					numNombreOriginal = ruta.lastIndexOf('\\');
				}

				String nombreOriginal = ruta.substring(numNombreOriginal+1);//-

				// Desde el formulario llegan los documentos cancelados con nombre '-'.
				if(!nombreOriginal.equals(Constantes.NOMBRE_FICHERO_CANCELADO)){
					registroDocumentoAux.setDsNombre(nombreOriginal);
					registroDocumentoAux.setCdValidez("04");
					registroDocumentoAux.setCdTipo("02");
					registroDocumentoAux.setBlTimeStamp(fecha.toString());
					fileCodificado = registroSolicitudForm.getDocumentosFicheros(i);
					
				
					
					// Futura version sin firma
					registroDocumentoAux.setBlFirma(" ");
					registroDocumentoAux.setCdFirmado("0");
					
					registroDocumentoAux.setBlValidacionOCSP("s");
					registroDocumentoAux.setDsTipoMIME("s");
					String hashFile="";
					String html=registroSolicitudForm.getDocumentosFicheros(i);
					byte[] byteHash =html.getBytes();

					try {
						hashFile=hash.getHash(byteHash);
					} catch (NoSuchAlgorithmException e1) {
						logger.error(STRING_ERROR_HASHFILE+ hashFile ,e1);
					}

					registroDocumentoAux.setBlHash(hashFile);
					registroDocumentoAux.setTlObservaciones("");
					registroDocumentoAux.setBlPKCertificado(registroSolicitudForm.getFirmaExtractoRegistro());
					registroDocumentoAux.setBlDocumento(fileCodificado);
					documentoTypeAdjunto[i] = registroDocumentoAux;

					//Construir octetStream				
					byte[] byteAux;

					try {			
						byteAux = IpsUtils.decodeBase64(registroSolicitudForm.getDocumentosFicheros(i));
					} catch (Exception e) {
						logger.error("crearDocumentos1 - Error:",e);
						
						return null;
					}

					registroType.setDocumentos(documentoTypeAdjunto);
					docs[i] = new OctetStream(byteAux);
				}
			}
		}
		

		//Se añade el html
		DocumentoType registroDocumentoAux = new DocumentoType();
		registroDocumentoAux.setDsNombre(registroSolicitudForm.getNumJustificante()+STRING_HTML);
		registroDocumentoAux.setCdValidez("04");
		registroDocumentoAux.setCdTipo("01");
		registroDocumentoAux.setBlTimeStamp(fecha.toString());
		// Futura version sin firma
		registroDocumentoAux.setBlFirma(" ");
		registroDocumentoAux.setCdFirmado("0");

		String hashHtml=registroSolicitudForm.getDocumentoHTML();
		byte[] byteHash = hashHtml.getBytes();
		String hashFile="";
		try {
			hashFile=hash.getHash(byteHash);
		} catch (NoSuchAlgorithmException e1) {
			logger.error(STRING_ERROR_HASHFILE+ hashFile, e1);
		}

		registroDocumentoAux.setBlValidacionOCSP("s");
		registroDocumentoAux.setDsTipoMIME("s");
		registroDocumentoAux.setBlHash(hashFile);
		registroDocumentoAux.setTlObservaciones("");
		registroDocumentoAux.setBlPKCertificado(registroSolicitudForm.getFirmaExtractoRegistro());
		registroDocumentoAux.setBlDocumento(hashHtml);
		documentoTypeAdjunto[numRegistros-1] = registroDocumentoAux;

		byte[] byteAux;

		try {
			byteAux = IpsUtils.decodeBase64(registroSolicitudForm.getDocumentoHTML());
		} catch (Exception e) {
			logger.error("Decode DocumentoHTML - Error:",e);
			return null;
		}
		
		//Carga de los adjuntos en el Justificante
		if (!StringUtils.isEmpty(registroSolicitudForm.getIdSolicitud())) {
			try {
				long idSolicitud = Long.parseLong(registroSolicitudForm.getIdSolicitud());
				ArrayList<DocumentoBean> listaDoc = null;
				
				if (idSolicitud > 0) {
					DocumentoQuery documentoQuery = new DocumentoQuery();
					SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
					solicitudComunQuery.setIdSolicitud(idSolicitud);
					documentoQuery.setSolicitudComun(solicitudComunQuery);
					TipoDocumentoQuery tipoDocumentoQuery = new TipoDocumentoQuery();
					tipoDocumentoQuery.addIdIn(1);
					tipoDocumentoQuery.addIdIn(6);
					tipoDocumentoQuery.addIdIn(10);
					documentoQuery.setTipoDocumento(tipoDocumentoQuery);
					listaDoc = documentoConvocatoriasManager.buscarDocumentosConvocatoria(documentoQuery);
				}
				if (listaDoc != null && listaDoc.size() > 0) {
					
					registroDocumentoAux = new DocumentoType();
					DocumentoType documentoTypeAdjuntoDoc[] = new DocumentoType[listaDoc.size()+1];
					documentoTypeAdjuntoDoc[0]= documentoTypeAdjunto[numRegistros-1];
					for (int i = 0;i <listaDoc.size();i++) {
						registroDocumentoAux = new DocumentoType();
						if (listaDoc.get(i) != null) {
							registroDocumentoAux.setDsNombre(listaDoc.get(i).getNombre() != null ? listaDoc.get(i).getNombre():"");
							registroDocumentoAux.setBlHash(listaDoc.get(i).getHashExtracto() != null ? listaDoc.get(i).getHashExtracto():"");
							documentoTypeAdjuntoDoc [i+1] = registroDocumentoAux;
						}
					}
					registroType.setDocumentos(documentoTypeAdjuntoDoc);
				} else {
					registroType.setDocumentos(documentoTypeAdjunto);	
				}
			} catch (Exception e) {
				logger.error("Error al obtener los adjuntos.");
			}
		} else {
			registroType.setDocumentos(documentoTypeAdjunto);	
		}
		
		docs[numRegistros-1] = new OctetStream(byteAux);		

		return docs;
	}	

	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception, IpsException {
		logger.info("Entra a registrar solicitud");
		RegistroSolicitudForm registroSolicitudForm = (RegistroSolicitudForm) form;
		logger.info("Identificador de la solicitud: "+registroSolicitudForm.getIdSolicitud());
		logger.info("Identificador de la convocatoria: "+registroSolicitudForm.getIdConvocatoria());
		registroSolicitudForm.setMotivoOposicion(registroSolicitudForm.getMotivoOposicion());
		/*if(registroSolicitudForm.getConsentimiento() == null || registroSolicitudForm.getConsentimiento() == false ) {
			registroSolicitudForm.setConsentimiento(true); 
		}
		else registroSolicitudForm.setConsentimiento(false);
			*/
		
		//Variable para pruebas de estress
		String convocatoriaRepetida_Unico = properties.getProperty("altaSolicitud.inscripcionMultiple_Unica");

		
		//Comprobar si el usuario esta en la sesion
		CiudadanoBean usuActual = null;
		if(convocatoriaRepetida_Unico.equals("U")){
			usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute("usuarioClave");
		}else{
			usuActual = new CiudadanoBean();
			usuActual.setNif("12345678Z");
		}			
											
		// ciudadano inscrito por F.H
		CiudadanoBean ciudadanoInscritoAux = (CiudadanoBean) this.getRequest().getSession().getAttribute("ciudadanoInscrito");
											
		//Buscar solicitud
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		ConvocatoriaQuery convocatoria = new ConvocatoriaQuery();
		convocatoria.setId(Long.parseLong(registroSolicitudForm.getIdConvocatoria()));
		solicitudQuery.setConvocatoria(convocatoria);
		solicitudQuery.setNif(registroSolicitudForm.getNif());
		if(!StringUtils.isEmpty(usuActual)) {
			if (usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO) && ciudadanoInscritoAux != null) { 
				solicitudQuery.setNif(ciudadanoInscritoAux.getNif());
			}
		} else {
			throw new IpsException("Error al intentar obtener el usuario asociado a esta sesion");
		}
		SolicitudBean solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);
		if(solicitudBean.getIdConsentimiento()){
			registroSolicitudForm.setConsentimiento(false);
		}else{
			registroSolicitudForm.setConsentimiento(true);
		}
		if(solicitudBean.getIdConsentimientoAEAT()){
			registroSolicitudForm.setIdConsentimientoAEAT(true);
		}else{
			registroSolicitudForm.setIdConsentimientoAEAT(false);
		}
		registroSolicitudForm.setMotivoOposicion(solicitudBean.getMotivoOposicion());
		registroSolicitudForm.setIdSolicitud(solicitudBean.getId().toString());
		registroSolicitudForm.setIdConvocatoria(String.valueOf(solicitudBean.getIdConvocatoria()));
		registroSolicitudForm.setNif(solicitudBean.getNif());
		registroSolicitudForm.setEmail(solicitudBean.getEmail());
		registroSolicitudForm.setCalle(solicitudBean.getCalleDomicilio());
		registroSolicitudForm.setCodPostal(solicitudBean.getCodigoPostalDomicilio());
		registroSolicitudForm.setDatosA(solicitudBean.getDatosA());
		registroSolicitudForm.setDatosB(solicitudBean.getDatosB());
		if (solicitudBean.getFechaNacimiento() != null && !"".equals(solicitudBean.getFechaNacimiento())){
			registroSolicitudForm.setFechaNacimiento(solicitudBean.getFechaNacimiento().toString());
		}
		registroSolicitudForm.setNacionalidad(solicitudBean.getNacionalidad());
		registroSolicitudForm.setMunicipioDomicilio(solicitudBean.getMunicipioDomicilio());
		registroSolicitudForm.setProvinciaDomicilio(solicitudBean.getProvinciaDomicilioDes());
		registroSolicitudForm.setPaisDomicilio(solicitudBean.getPaisDomicilioDes());
		registroSolicitudForm.setNumJustificante(solicitudBean.getNumeroSolicitud());
		registroSolicitudForm.setDetalleDiscapacidad(solicitudBean.getDetalleDiscapacidad());
		registroSolicitudForm.setPorcentajeDiscapacidad(solicitudBean.getPorcentajeDiscapacidad().toString());
		registroSolicitudForm.setReservaDiscapacidad(solicitudBean.getReservaDiscapacidad()+"");
		registroSolicitudForm.setTipoDiscapacidad(solicitudBean.getTipoDiscapacidadDes());
		registroSolicitudForm.setIdSolicitud(solicitudBean.getId().toString());		
		registroSolicitudForm.setNombre(solicitudBean.getNombre());
		registroSolicitudForm.setTelefono(solicitudBean.getTelefono());
		RegistroSolicitudQuery registroSolicitud = new RegistroSolicitudQuery();		
		SolicitudComunQuery solicitudQueryPago = new SolicitudComunQuery();
		solicitudQueryPago.setIdSolicitud(Long.parseLong(solicitudBean.getId().toString()));	
		registroSolicitud.setSolicitudComun(solicitudQueryPago);
		
		// Miramos si se ha hecho algun reintento de registro antes de esa misma solicitud, si es asi lo guardamos en la tabla de registro de auditoria
		try{
			
			if(registroSolicitudesManager.buscarRegistro(registroSolicitud)!=null){
				AuditoriaBean auditoriaBean = new AuditoriaBean();
				auditoriaBean.setIdSolicitud(Long.valueOf(registroSolicitudForm.getIdSolicitud()));	
				auditoriaBean.setFechaAutenticacion(new Date());				
				
				if(usuActual.getNumeroSerie()!=null){
					auditoriaBean.setNumeroSerie(usuActual.getNumeroSerie());
				}else{
					auditoriaBean.setNumeroSerie(" ");
				}
				
				if (usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)){
					auditoriaBean.setTipoPersona(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO);
				} else {
					auditoriaBean.setTipoPersona(Constantes.TIPO_PERSONA_CIUDADANO);
				}
				
				try{
					// Guardamos el registro
					registroAuditoriaManager.guardarRegistroAuditoria(auditoriaBean);
				}catch(Exception e){
					logger.error("Error al insertar el registro en la tabla auditoria",e);	
				}
			}else{
				logger.info("Sin registro. No se guarda en Auditoria.");
			}
		}catch(Exception e){
			logger.error("Error al recuperar el registro de la solicitud con id" +registroSolicitudForm.getIdSolicitud(),e);	
		}
		
		PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();	
		pagoSolicitudQuery.setSolicitudComun(solicitudQueryPago);

		DetallePagoSolicitudBean detallePagoSolicitudBean = null;
		
		// Debemos obtener el ultimo registro insertado en la tabla
		if(convocatoriaRepetida_Unico.equals("U")){
			detallePagoSolicitudBean = pagoSolicitudManager.buscarUltimoPagoSolicitudIdSolicituOK(pagoSolicitudQuery);
		}else{
			detallePagoSolicitudBean = pagoSolicitudManager.buscarPagoSolicitudIdSolicituPruebas(pagoSolicitudQuery);
		}

		long idConvocatoria = Long.valueOf(registroSolicitudForm.getIdConvocatoria());

		ConvocatoriaBean convocatoriaBean = convocatoriaManager.recuperarConvocatoria(idConvocatoria);


		if(convocatoriaBean.getDesMinisterio() != null){
			registroSolicitudForm.setMinisterio(convocatoriaBean.getDesMinisterio());
		}

		String resultadoComprobaciones = this.realizarComprobaciones(registroSolicitudForm, usuActual, solicitudBean);

		if(resultadoComprobaciones==null || !resultadoComprobaciones.equals("ok")){
			//Si no se superan todas las comprobaciones, no se realiza el registro
			return resultadoComprobaciones;
		}

		EstadoSolicitudQuery estadoSolicitudQueryAuxActualizar = new EstadoSolicitudQuery();
		SolicitudComunQuery solicitudQueryAuxActualizarProceso = new SolicitudComunQuery();
		Long idSolicitudActualizar = null;
		String codigoProvincia = null;
		String codigoPais = null;

		if(detallePagoSolicitudBean!=null){
			//Actualizamos el estado de la solicitud a PROCESO_REGISTRO
			estadoSolicitudQueryAuxActualizar.setId(Constantes.SOLICITUD_ID_PROCESO_REGISTRO);
			solicitudQueryAuxActualizarProceso.setIdSolicitud(solicitudBean.getId());
			idSolicitudActualizar = solicitudesManager.actualizarEstadoSolicitud(solicitudQueryAuxActualizarProceso, estadoSolicitudQueryAuxActualizar);
			logger.info("1.RegistroSolicitudAction-Estado actualizado: "+idSolicitudActualizar);

			if(solicitudBean.getProvinciaDomicilio()!=null){
				Provincia cdProvincia=solicitudBean.getProvinciaDomicilio();
				codigoProvincia=cdProvincia.getCodigo();
			}else{
				codigoProvincia="";
			}
			Pais paisDomicilio=solicitudBean.getPaisDomicilio();
			
			if(paisDomicilio != null){
				codigoPais=paisDomicilio.getCodigo();
			}else{
				codigoPais="";
			}
		}

		//Subir documentos a alfresco			
		String resultado = subirDocumentos(registroSolicitudForm);

		if(STRING_ERROR.equals(resultado)){
			try{
				documentoConvocatoriasManager.eliminarDocumentosByIdSolicitud(registroSolicitudForm.getIdSolicitud());
				logger.info("Se han eliminado de la base de datos");
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_FIELD_SOLICITUD_REGISTRO_ERRORREALIZACION));

			}
			catch(Exception e){
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_CERTIFICADOS_ERROR_GESTORDOCUMENTAL));
				logger.warn("Error en la conexion con Alfresco");
				
				logger.error("Conexion con Alfresco - Error:",e);
			
			}
			
			if(detallePagoSolicitudBean!=null){
				//Reestablecemos el estado de la solicitud 
				estadoSolicitudQueryAuxActualizar.setId(Constantes.SOLICITUD_ID_NOREGISTRADO);
				solicitudQueryAuxActualizarProceso.setIdSolicitud(solicitudBean.getId());
				idSolicitudActualizar = solicitudesManager.actualizarEstadoSolicitud(solicitudQueryAuxActualizarProceso, estadoSolicitudQueryAuxActualizar);
				logger.info("2.RegistroSolicitudAction-Estado actualizado: "+idSolicitudActualizar);
			}
			
			logger.info("1. Resultado: nosuccess");
			return STRING_NOSUCCESS;
		}			


		if(detallePagoSolicitudBean==null){
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,"");
			logger.info("2. Resultado: nosuccess");
			
			// Modificacion Firma Basica
			// Si el usuario tiene certificado de autenticacion y firma independientes
			// como es el caso del DNIe, falla el pago, por lo que hay que eliminar
			// el serial number para que deje de filtrar por el.
			logger.info("Reintento. Se elimina el serial number del certificado.");
			usuActual.setNumeroSerie(null);
			this.getSession().setAttribute("usuarioClave", usuActual);
			
			return STRING_NOSUCCESS;
		} 

		registroSolicitudForm.setDocumentoHTML(crearHTML(convocatoriaBean, solicitudBean, detallePagoSolicitudBean, registroSolicitudForm, convocatoriaRepetida_Unico));
		registroSolicitudForm.setMotivoReduccionDes(detallePagoSolicitudBean.getDesMotivoReduccion()); 

		String resul = subirDocumentoRegistro(registroSolicitudForm);
		logger.info("RegistroSolicitudSpring-Subida Doc Registro: "+resul);

		if(null!=resul && resul.equals(STRING_ERROR)){
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("field.javascript.errorFirma"));
			logger.info("5. Resultado: nosuccess");
			return STRING_NOSUCCESS;
		}
		
		RegistroType registroType = this.crearRegistroType(registroSolicitudForm, solicitudBean);
		OctetStream[] docs = this.crearDocumentos(registroSolicitudForm, registroType);
				
		registroType.setCdTipoRegistro("0");
		registroType.setTlResumen(properties.getProperty(STRING_CDASUNTO));

		InteresadoType interesado[]=registroType.getInteresados();
		interesado[0].setCdPaisInteresado(codigoPais);
		interesado[0].setCdProvinciaInteresado(codigoProvincia);
		interesado[0].setCdMunicipioInteresado("");
		interesado[0].setTlCorreoElectronicoInteresado(registroSolicitudForm.getEmail());
		interesado[0].setTlDEHInteresado(registroSolicitudForm.getEmail());
		interesado[0].setCdCanalPreferenteInteresado("02");		
		interesado[0].setTlObservaciones("ninguna");
		
		registroType.setInteresados(interesado);
		registroType.setCdDocumentacionFisicaSoportes("03");
		registroType.setFlJustificante("0");

		// Se llama al webservice
		JustificanteType justificanteType = new JustificanteType();
		RespuestaType respuestaType = new RespuestaType();
		long inicioConexion = System.currentTimeMillis();
		long finConexion;
		long tiempoRespuesta;
		boolean error = false;
		int result = 0;

		try{
			logger.info("Enviando datos al Servicio Web de Rec");
			Date date1 = new Date();

			RegistrarServiceLocator locator = new RegistrarServiceLocator();
			locator.setRegistrarServiceEndpointAddress(properties.getProperty("envioRegistro.url"));

			RegistrarServiceSoapBindingStub binding = (RegistrarServiceSoapBindingStub) locator.getRegistrarService();
			binding.setTimeout(TIMEOUT);

			//Cogemos del properties el IDAplicacion y el password
			String idAplicacion = properties.getProperty("idAplicacion");
			String password = properties.getProperty("password");

			AutenticacionType autentication = new AutenticacionType();
			autentication.setIdAplicacion(idAplicacion);
			autentication.setPassword(password);

		
			
			justificanteType = binding.registrar(autentication, registroType);
			respuestaType=justificanteType.getRespuesta();

			Date date2 = new Date();
			logger.info("Envio REC end: "+(date2.getTime()-date1.getTime()));

			finConexion = System.currentTimeMillis();
			tiempoRespuesta = finConexion - inicioConexion;
			tiempoRespuesta = tiempoRespuesta / 1000;
		}catch(Exception e){
			error = true;
			finConexion = System.currentTimeMillis();
			tiempoRespuesta = finConexion - inicioConexion;
			tiempoRespuesta = tiempoRespuesta / 1000;

			//Comprobar si se ha podido conectar al webservice
			logger.warn("Error en la conexion con el rec");
			logger.error("Conexion con el rec - Error:",e);
	
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_FIELD_SOLICITUD_REGISTRO_ERRORREALIZACION));
			logger.info("4. Resultado: nosuccess");
			

			//Guardar en LOG_SERVICIO como error fisico
			logger.info("Guardando datos de LogServicio");
			LogServicioBean logServicioBean = new LogServicioBean();
			Date fechaActual = new Date();
			logServicioBean.setFecha(fechaActual);
			logServicioBean.setIdTipoServicio(Constantes.LOG_SERVICIO_TIPOSERVICIO_REGISTRO);
			logServicioBean.setResultado(Constantes.LOG_SERVICIO_RESULTADO_ERROR);
			logServicioBean.setTipoError(Constantes.LOG_SERVICIO_TIPO_ERROR_FISICO);
			logServicioBean.setPrueba(Constantes.LOG_SERVICIO_PRUEBA_NO);
			logServicioBean.setTiempoRespuesta(tiempoRespuesta);
			logServicioBean.setDesError(RESOURCE_BUNDLE.getString("registroREC.errorConexion"));

			result = logServiciosManager.insertarLog(logServicioBean);
			logger.info("Guardados datos de LogServicio con id: "+result);		
			
			return STRING_NOSUCCESS;
		}

		if(respuestaType.getCdRespuesta()==null || !respuestaType.getCdRespuesta().equals("00")){
			error=true;
			//Si error es true no entramos en este if porque ya se ha guardado el error en el catch anterior
			if(justificanteType!=null || respuestaType.getCdRespuesta()==null || !respuestaType.getCdRespuesta().equals("00")){
				logger.error("justificanteType.isExisteError() is true");
				logger.error("Mensaje de error: "+respuestaType.getDsRespuesta());

				//Guardar en LOG_SERVICIO como error lógico
				logger.info("Guardando datos de LogServicio");
				LogServicioBean logServicioBean = new LogServicioBean();
				Date fechaActual = new Date();
				logServicioBean.setFecha(fechaActual);
				logServicioBean.setIdTipoServicio(Constantes.LOG_SERVICIO_TIPOSERVICIO_REGISTRO);
				logServicioBean.setResultado(Constantes.LOG_SERVICIO_RESULTADO_ERROR);
				logServicioBean.setTipoError(Constantes.LOG_SERVICIO_TIPO_ERROR_LOGICO);
				logServicioBean.setPrueba(Constantes.LOG_SERVICIO_PRUEBA_NO);

				String mensajeError = respuestaType.getDsRespuesta();

				if(mensajeError!=null){
					if(mensajeError.length()>499){
						logServicioBean.setDesError(mensajeError.substring(0, 499));
					}else{
						logServicioBean.setDesError(mensajeError);
					}
				}

				logServicioBean.setTiempoRespuesta(tiempoRespuesta);

				String timestamp = justificanteType.getBlTimeStamp();
				logger.info("Timestamp Recibido: "+timestamp);

				result = logServiciosManager.insertarLog(logServicioBean);
				logger.info("Guardados datos de LogServicio con id: "+result);
			}

			//SI SE HA PRODUCIDO UN ERROR EN LA CONEXION O SE HA RECIBIDO ERROR DE REL REC SE ENVIA EL CORREO NOTIFICANDO DE LA INCIDENCIA

			//Enviar correo electroncio a los administradores del sistema
			ParametrosConfiguracionQuery paramConfQuery = new ParametrosConfiguracionQuery();
			paramConfQuery.setNombreCampo(Constantes.PARAMETROS_CORREO_INCIDENCIA_INSCRIPCION);
			//Recuperas los parametros de envio
			ParametrosConfiguracionBean parametrosConfiguracion = parametroConfiguracionManager.buscarParametroConfiguracionUnico(paramConfQuery);//("METODO_NUMERO_SOLICITUD");			

			//Rellenamos el CorreoElectronicos
			if(parametrosConfiguracion !=null && parametrosConfiguracion.getValorCampo()!=null){
				String br = RESOURCE_BUNDLE.getString("texto.br");
				StringBuilder mensaje =  new StringBuilder();
				mensaje.append(RESOURCE_BUNDLE.getString("field.registro.correoPendienteRegistro.texto1")).append(br).append(br)
				.append(RESOURCE_BUNDLE.getString("field.registro.correoPendienteRegistro.texto2")).append(br)
				.append(RESOURCE_BUNDLE.getString("field.solicitud.id")).append(" ").append(registroSolicitudForm.getIdSolicitud())
				.append(br).append(RESOURCE_BUNDLE.getString("field.solicitud.numeroJustificante")).append(" ")
				.append(registroSolicitudForm.getNumJustificante()).append(br).append(RESOURCE_BUNDLE.getString("field.registro.correoPendienteRegistro.texto3"))
				.append(br).append(br).append(RESOURCE_BUNDLE.getString("field.solicitud.nif")).append(" ")
				.append(registroSolicitudForm.getNif()).append(br).append(RESOURCE_BUNDLE.getString("field.solicitud.nombre"))
				.append(" ").append(registroSolicitudForm.getNombre()).append(br).append(RESOURCE_BUNDLE.getString("field.solicitud.email"))
				.append(" ").append(registroSolicitudForm.getEmail());

				CorreoElectronicoBean correoElectronicoBean = new CorreoElectronicoBean();
				correoElectronicoBean.setDe(properties.getProperty("mail.de"));
				correoElectronicoBean.setPara(parametrosConfiguracion.getValorCampo());
				correoElectronicoBean.setAsunto(RESOURCE_BUNDLE.getString("field.registro.correoPendienteRegistro")+" - "+registroSolicitudForm.getNombre());
				correoElectronicoBean.setMensaje(mensaje.toString());
				correoElectronicoBean.setEstado(Constantes.CORREO_ELECTRONICO_ESTADO_ENVIADO);

				logger.info("Enviando el error que se ha producido por Correo Electronico a:" + parametrosConfiguracion.getValorCampo());
				boolean res = false;
				res = EnvioMails.envioMail(correoElectronicoBean);
				
				if(res){
					logger.info("Correo Electronico Enviado a: " + parametrosConfiguracion.getValorCampo());
				}else{
					logger.error("No se ha podido mandar el correo Electronico a : " + parametrosConfiguracion.getValorCampo());
				}
			}

			this.getRequest().setAttribute("errorRegistro","true");

		}else{
			logger.info("El registro se ha realizado correctamente");
			//Aquí deberíamos generar el archivo pdf y subirlo a alfresco
			this.crearDocumentos1(registroSolicitudForm, registroType);
			registroType.setCdTipoRegistro("0");
			registroType.setTlResumen(properties.getProperty(STRING_CDASUNTO));
			registroType.setInteresados(interesado);
			registroType.setCdDocumentacionFisicaSoportes("03");
			
			crearPdf(justificanteType,registroType,registroSolicitudForm,usuActual);
			
		}

		if(registroSolicitudForm.getIdSolicitud() != null){


			
			RegistroSolicitudBean registroSolicitudBean;
			registroSolicitudBean = toRegistroBean(registroSolicitudForm);
			registroSolicitudBean.setIdLogServicio(result);

			
			if(registroSolicitudForm.getConsentimiento() == true){	
				registroSolicitudBean.setDesConsentimiento(Constantes.NO);
			}else if(registroSolicitudForm.getConsentimiento() == false){	
				registroSolicitudBean.setDesConsentimiento(Constantes.SI);
			}else{	
				registroSolicitudBean.setDesConsentimiento("");
			} 
			
			//Motivo oposicion
			if (registroSolicitudForm.getMotivoOposicion() != null && !registroSolicitudForm.getMotivoOposicion().equals("")) {
				registroSolicitudBean.setMotivoOposicion(registroSolicitudForm.getMotivoOposicion());
			} else {
				registroSolicitudBean.setMotivoOposicion(" ");
			}

			if(respuestaType.getCdRespuesta() == null || error  && !respuestaType.getCdRespuesta().equals("00")){
				logger.info("Guardando datos de RegistroSolicitud con Resultado Error");
				//Si se ha conectado y devuelve un error
				registroSolicitudBean.setResultado(Constantes.REGISTRO_RESULTADO_ERROR);
				registroSolicitudBean.setDesError(respuestaType.getDsRespuesta());
				Date fechaAuxIntento = new Date();
				registroSolicitudBean.setFechaIntento(fechaAuxIntento);
			}else{
				if(justificanteType.getNmRegistro()!=null && !"".equals(justificanteType.getNmRegistro())){
					logger.info("Guardando datos de RegistroSolicitud con Resultado OK");
					//Si se ha conectado sin dar ningun error
					Date fechaAuxNueva = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT_HHMMSS, Locale.ENGLISH).parse(justificanteType.getFeRegistro());
					registroSolicitudBean.setNumRegistro(justificanteType.getNmRegistro());
					if (usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)) {
						registroSolicitudBean.setSolicitante(Constantes.REGISTRO_SOLICITANTE_FUNCIONARIO);	
					}
					registroSolicitudBean.setResultado(Constantes.REGISTRO_RESULTADO_OK);
					registroSolicitudBean.setOficinaRegistro(justificanteType.getCdOficinaOrigen());
					registroSolicitudBean.setFechaRegistro(fechaAuxNueva);
					Calendar fechaEntrada = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT_HHMMSS);
					fechaEntrada.setTime(sdf.parse(justificanteType.getFeRegistro()));
					
					if(fechaEntrada!=null){
						registroSolicitudBean.setFechaIntento(fechaEntrada.getTime());
					}
					
				}else{
					logger.info("Guardando datos de RegistroSolicitud con Resultado Error");
					//Si no se ha podido conectar al web service
					registroSolicitudBean.setResultado(Constantes.REGISTRO_RESULTADO_ERROR);
					Date fechaAuxIntentoNull = new Date();
					registroSolicitudBean.setFechaIntento(fechaAuxIntentoNull);
				}
			}			
			if(registroSolicitudForm.getIdConsentimientoAEAT() != null){
					registroSolicitudBean.setIdConsentimientoAEAT(registroSolicitudForm.getIdConsentimientoAEAT());
			}else{
				registroSolicitudBean.setIdConsentimientoAEAT(false);
			}
				
			// Se guarda en la tabla RegistroSolicitud
			Long id = registroSolicitudesManager.guardarRegistro(registroSolicitudBean);
			
			if(id != null){
				this.setRequestAttribute(STRING_REGISTRO, registroSolicitudForm.getIdSolicitud());	
				EstadoSolicitudQuery estadoSolicitudQuery = new EstadoSolicitudQuery();
				
				if(respuestaType.getCdRespuesta() == null || (error && !respuestaType.getCdRespuesta().equals("00")) || justificanteType.getNmRegistro() == null){
					logger.info("La solicitud se deja en Estado Pendiente de Registro");
					estadoSolicitudQuery.setId(Constantes.SOLICITUD_ID_NOREGISTRADO);
				}else{
					logger.info("La solicitud se deja en Estado Registrada");
					estadoSolicitudQuery.setId(Constantes.SOLICITUD_ID_REGISTRADO);
				}

				SolicitudComunQuery solicitudQueryAuxActualizar = new SolicitudComunQuery();
				solicitudQueryAuxActualizar.setIdSolicitud(Long.parseLong(registroSolicitudBean.getIdSolicitud()));						
				//Se actualiza el estado solicitud
				Long idAux = solicitudesManager.actualizarEstadoSolicitud(solicitudQueryAuxActualizar, estadoSolicitudQuery);
				logger.info("3.RegistroSolicitudAction-Estado actualizado: "+idAux);

				//Guardar el log de la actualizacion
				LogSolicitudBean logSolicitudBean = new LogSolicitudBean();
				logSolicitudBean.setActor(usuActual.getNif());
				Date fechaAux =new Date();
				logSolicitudBean.setFecha(fechaAux);
				logSolicitudBean.setTipoOperacion(Constantes.LOG_SOLICITUD_CAMBIOESTADO);
				logSolicitudBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("logSolicitud.detalleModificacion"));
				logSolicitudBean.setIdSolicitud(String.valueOf(registroSolicitudForm.getIdSolicitud()));

				if(respuestaType.getCdRespuesta() == null ||(error && !respuestaType.getCdRespuesta().equals("00")) && justificanteType.getNmRegistro() == null){
					logSolicitudBean.setIdEstadoActual(Constantes.LOG_SOLICITUD_ESTADO_NOREGISTRADO);
				}else{
					logSolicitudBean.setIdEstadoActual(Constantes.LOG_SOLICITUD_ESTADO_REGISTRADO);
				}
				logSolicitudBean.setIdEstadoAnterior(Constantes.LOG_SOLICITUD_ESTADO_NOREGISTRADO);
				if (usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)) {
					logSolicitudBean.setTipoActor(Constantes.TIPO_ACTOR_FUNCIONARIO_HABILITADO);
				}

				//Se inserta en la tabla de log
				logger.info("Guardando en la tabla LogSolicitud");
				Integer idLog = tablaBaseManager.insertarLogSolicitud(logSolicitudBean);
				
				if(idLog == null){
					logger.error(RESOURCE_BUNDLE.getString("logSolicitud.error"));
				}
				
				if(error==false){
					logger.info("3. Resultado: success!!!");
					
					// TODO Migracion Alfresco: Guardamos los documentos de la solicitud en su ubicación definitiva de Solo Lectura.
					documentoConvocatoriasManager.copiarFicheros(registroSolicitudForm.getIdSolicitud(), properties.getProperty("sistemaficheros.url.escritura"));
					
					return "success";
				}	
			}
		}

		this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_FIELD_SOLICITUD_REGISTRO_ERRORREALIZACION));
		logger.info("4. Resultado: nosuccess");
		return STRING_NOSUCCESS;
	}

	/**
	 * Enviar mensaje email.
	 *
	 * @param eSignature el e signature
	 * @param direccionEmail el direccion email
	 */
	private void enviarMensajeEmail(byte[] eSignature, String direccionEmail) {
		
		logger.info("-- envio correo inicio --");
		
		// creacion del destinatario
		Destinatarios destinatario = new Destinatarios();
		destinatario.setTo(direccionEmail);
						
		DestinatarioMail destinatarioMail = new DestinatarioMail();
		destinatarioMail.setIdExterno(properties.getProperty("sim.idExterno"));
		destinatarioMail.setDestinatarios(destinatario);
		
		// creacion del contenedor de destinatarios
		DestinatarioMail[] destinatariosMail = {destinatarioMail};
		
		// creacion del documento adjunto que contendrá el justificante de registro
		Adjunto docAdjunto = new Adjunto();
		docAdjunto.setNombre(Constantes.NOMBRE_JUSTIFICANTE_REGISTRO_EMAIL);
		String contenido = Base64.encodeBase64String(eSignature);
		docAdjunto.setContenido(contenido);
		
		// creacion del contenedor de adjuntos
		Adjunto[] adjuntos = {docAdjunto};
		
		// creacion del email
		MensajeEmail correo = new MensajeEmail();
		correo.setAsunto(Constantes.ASUNTO_JUSTIFICANTE_REGISTRO_EMAIL);
		correo.setCuerpo(properties.getProperty("sim.cuerpo.correo"));
		correo.setAdjuntos(adjuntos);
		correo.setDestinatariosMail(destinatariosMail);
		
		// creacion del contenedor de emails
		MensajeEmail[] mensajesEmail = {correo};
				
		// creacion del contenedor de mensajes 
		Mensajes mensajes = new Mensajes();
		mensajes.setMensajeEmail(mensajesEmail);
		
		// creacion de la peticion
		Peticion peticion = new Peticion();
		
		peticion.setUsuario(properties.getProperty("sim.usuario"));
		peticion.setPassword(properties.getProperty("sim.password"));
		peticion.setNombreLote(properties.getProperty("sim.nombreLote"));
		peticion.setServicio(properties.getProperty("sim.servicio"));
		peticion.setCodSia(properties.getProperty("sim.codSia"));
		peticion.setCodOrganismo(properties.getProperty("sim.codOrganismo"));
		peticion.setCodOrganismoPagadorSMS(properties.getProperty("sim.codOrganismoPagadorSMS"));
		peticion.setMensajes(mensajes);
		
		
		// recepcion de la respuesta del WS de SIM
		try {
			logger.info("-- invocando al envioMensajesService --");
			EnvioMensajesServiceWSBindingPortType envioMensajesService =
					new EnvioMensajesServiceWSBindingPortTypeProxy(properties.getProperty("sim.ws")); 
			Respuesta respuesta = envioMensajesService.enviarMensaje(peticion);
			logger.info("Respuesta: " + respuesta.getStatus().getStatusCode() + "-" + respuesta.getStatus().getStatusText());
			logger.info("-- envio correo fin --");
		} catch (Exception e){
			logger.info("Respuesta: " );
			logger.error("Respuesta envio correo - Error:", e);
		}
}
	
	
	
	/**
	 * Método de generación del justificante de inscripción.
	 *
	 * @param justificanteType el justificante type
	 * @param registroType el registro type
	 * @param registroSolicitudForm el registro solicitud form
	 * @param usuarioActual el usuario actual
	 * @return el int
	 * @throws Exception el exception
	 */
	private int crearPdf(JustificanteType justificanteType, RegistroType registroType,
			RegistroSolicitudForm registroSolicitudForm, CiudadanoBean usuarioActual) throws Exception {

		JRBeanCollectionDataSource jrBeanCollectionDataSource = null;
		JRBeanCollectionDataSource jrBeanCollectionDataSource2 = null;

		File sourceFile = null;

		//Creamos los array de bytes de cada documento
		byte[] pdfasbytes = null;
		byte[] pdfasbytes2 = null;

		//Creamos los arrayList para los PDF's
		ArrayList<JustificanteBean> aListPdf = new ArrayList<JustificanteBean>();
		ArrayList<JustificanteBean> aListPdf2 = new ArrayList<JustificanteBean>();

		//Creamos los jasper
		Jasper jasperJustificante = new Jasper();
		Jasper jasperJustificante2 = new Jasper();

		//Obtenemos el idioma en session	
		Locale locale = (Locale) this.getRequest().getSession().getAttribute(Globals.LOCALE_KEY);
		String localeName = locale.toString();	
		logger.info("1.Locale: "+localeName);

		//Enruta los jasper	
		// La primera página del reporte siempre será en español.
		String ficheroPdf = properties.getProperty("jasper.justificanteR");
		String ficheroPdf2 = "";

		// Idioma del encabezado del justificante de registro
		String encabezado = Constantes.ENCABEZADO_GENERICO;
		
		if (localeName.equals(Constantes.CATALAN)) {
			ficheroPdf2 = properties.getProperty("jasper.justificanteCAT");
			encabezado = Constantes.ENCABEZADO_GENERICO_CA;
		} else if (localeName.equals(Constantes.GALLEGO)) {
			ficheroPdf2 = properties.getProperty("jasper.justificanteGAL");
			encabezado = Constantes.ENCABEZADO_GENERICO_GL;
		} else if (localeName.equals(Constantes.EUSKERA)) {
			ficheroPdf2 = properties.getProperty("jasper.justificanteEU");
			encabezado = Constantes.ENCABEZADO_GENERICO_EU;
		} else if (localeName.equals(Constantes.VALENCIANO)) {
			ficheroPdf2 = properties.getProperty("jasper.justificanteVL");
			encabezado = Constantes.ENCABEZADO_GENERICO_VL;
		}
		// Multi-idioma
		String MESSAGE_RESOUCE = "MessageResources";
		if(localeName.equals("ca")){
			MESSAGE_RESOUCE = "MessageResources_ca";
		}else if(localeName.equals("gl")){
			MESSAGE_RESOUCE = "MessageResources_gl";
		}else if(localeName.equals("vl")){
			MESSAGE_RESOUCE = "MessageResources_vl";
		}else if(localeName.equals("eu")){
			MESSAGE_RESOUCE = "MessageResources_eu";
		}
		
		ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
		//Creamos el bean que guarda los datos del primer jasper
		JustificanteBean justificanteBean;
		justificanteBean = toJustificanteBean(justificanteType,registroType,registroSolicitudForm, encabezado,MESSAGE_RESOUCE);
		justificanteBean.setRutaLogo(properties.getProperty("jasper.rutaLogo"));

		//Se añaden los bean a la lista
		aListPdf.add(justificanteBean);
		jrBeanCollectionDataSource = new JRBeanCollectionDataSource((Collection<JustificanteBean>) aListPdf);	

		// Genero el pdf de prueba
		// Obtengo los ficheros implicados en la generacion del pdf
		URL url = ResourceLocator.getInstance().getJasperTemplate(ficheroPdf);			
		String rutaFicheroJasper="";
		if(url!=null){
			rutaFicheroJasper = url.getFile();
		}

		sourceFile = new File(rutaFicheroJasper);

		// Cargo el informe compilado en el objeto jasperreport
		logger.info("Inicio creacion PDF");
		pdfasbytes = jasperJustificante.generarPDF(jrBeanCollectionDataSource, sourceFile, "");
		logger.info("Fin creacion PDF");

		// Genero el pdf de prueba2
		// Obtengo los ficheros implicados en la generacion del pdf
		// Si no es multi-idioma, no debe generar la página 2 del justificante.	
		boolean generarHoja2 = false;

		if(!ficheroPdf2.equals("")){
			generarHoja2 = true;
		}

		if(generarHoja2){
			url = ResourceLocator.getInstance().getJasperTemplate(ficheroPdf2);			
			rutaFicheroJasper="";
			if(url!=null){
				rutaFicheroJasper = url.getFile();
			}

			sourceFile = new File(rutaFicheroJasper);

			// Cargo el informe compilado en el objeto jasperreport
			aListPdf2.add(justificanteBean);
			jrBeanCollectionDataSource2 = new JRBeanCollectionDataSource((Collection<JustificanteBean>) aListPdf2);	
			pdfasbytes2 = jasperJustificante2.generarPDF(jrBeanCollectionDataSource2, sourceFile, "");
		}

		ByteArrayOutputStream pdfCompuesto = new ByteArrayOutputStream();
		PdfReader reader1 = new PdfReader(pdfasbytes);
		PdfCopyFields copy = new PdfCopyFields(pdfCompuesto);
		
		//Si es otro idioma se modifica para solo generar una hoja
		if(generarHoja2){
			PdfReader reader2 = new PdfReader(pdfasbytes2);
			copy.addDocument(reader2);
		} else {
			copy.addDocument(reader1);
		}

		copy.close();

		// Añadimos la firma al documento
		logger.info("Obteniendo certificado de firma de IPS");
		Signer signer = SignersFactory.getInstance().getSigner(SignatureConstants.SIGN_FORMAT_PADES);
		PrivateKeyEntry certificatePrivateKey=getCertificatePrivateKey();
		
		logger.info("Firmando el justificante");
		byte [] eSignature = signer.sign(pdfCompuesto.toByteArray(), SignatureConstants.SIGN_ALGORITHM_SHA512WITHRSA, SignatureConstants.SIGN_MODE_IMPLICIT, certificatePrivateKey, null);	
		
		// CSV INSIDE
		// generacion codigo CSV, necesita el pdf firmado
		EeUtilService csvEeUtilService = new EeUtilServiceProxy(properties.getProperty("url.ws.csv.eeutil"));
		String codigoCSV = CSVInside.generarCodigoCSV(eSignature, properties, csvEeUtilService);
		
		if (codigoCSV!=null && !"".equals(codigoCSV)) {
			logger.info("Codigo CSV generado por CSVInside: " + codigoCSV);
			
			// generacion copia CSV con codigo incrustrado
			Calendar c = Calendar.getInstance();
			Date fechaRegistro = c.getTime();
			byte[] docCsv = CSVInside.generarCopiaCSV(eSignature, usuarioActual, properties, csvEeUtilService, codigoCSV, fechaRegistro);
			//devuelve el documento con CSV y sin firmar
			
			if (docCsv!=null) {
				//Se firma pdf
				byte [] docCsvSignature = signer.sign(docCsv, SignatureConstants.SIGN_ALGORITHM_SHA512WITHRSA, SignatureConstants.SIGN_MODE_IMPLICIT, certificatePrivateKey, null);
				logger.info("Copia pdf con csv incrustado finalizada");
				
/*				byte[] esignaturebase64 = Base64Coder.encodeBase64(docCsvSignature);
				
				//Se añade el sello del timpo (firma PADES LTV)
				Map<String, Object> inParams = new HashMap<String, Object>();		
				inParams.put(DSSTagsRequest.CLAIMED_IDENTITY, properties.getProperty("webservices.idAplicacion"));
				inParams.put(DSSTagsRequest.IGNORE_GRACE_PERIOD, "true"); 
				inParams.put(DSSTagsRequest.RETURN_UPDATED_SIGNATURE_ATR_TYPE, "urn:afirma:dss:1.0:profile:XSS:PAdES:1.1.2:forms:LTV");
				inParams.put(DSSTagsRequest.SIGNATURE_BASE64, new String(esignaturebase64));
			
				String xmlInput = TransformersFacade.getInstance().generateXml(
						inParams, 
						GeneralConstants.DSS_AFIRMA_VERIFY_REQUEST, 
						GeneralConstants.DSS_AFIRMA_VERIFY_METHOD, 
						TransformersConstants.VERSION_10);
				
				String xmlOutput = Afirma5ServiceInvokerFacade.getInstance().invokeService(
						xmlInput, 
						GeneralConstants.DSS_AFIRMA_VERIFY_REQUEST, 
						GeneralConstants.DSS_AFIRMA_VERIFY_METHOD, 
						properties.getProperty("webservices.idAplicacion"));
				
				Map<String, Object>  docCSVFirmado = TransformersFacade.getInstance().parseResponse(
						xmlOutput, 
						GeneralConstants.DSS_AFIRMA_VERIFY_REQUEST, 
						GeneralConstants.DSS_AFIRMA_VERIFY_METHOD, 
						TransformersConstants.VERSION_10);
				
				String docCSVFirma = String.valueOf(docCSVFirmado.get(properties.getProperty("respuesta.integra.updatedSignature")));
				logger.info("Firma con sello de tiempo finalizada");	
				
				byte[] docCSVFirmaSinbase64 = Base64Coder.decodeBase64(docCSVFirma.getBytes());
*/				
				// registro justificante con csv incrustrado en tabla documentos
				long idDocumento = registroPdfCsvDocumentos(docCsvSignature, registroSolicitudForm, codigoCSV);
				
				if (idDocumento>0) {
					// subida justificante a filesystem
					logger.info("Subiendo Justificante a Sistema de ficheros");
					int subida = subirDocumentoPdfCSV(docCsvSignature, idDocumento); 
					if(subida == 0){
						return 0;
					}
					logger.info("Subida PDF a Sistema de ficheros finalizada");
				} else {
					return 0;
				}
											
				// envio justificante email
				enviarMensajeEmail(docCsvSignature, registroSolicitudForm.getEmail());
				
				// CSV STORAGE
				String guardado = CSVStorage.guardarDocumento(codigoCSV, docCsvSignature, properties);
				if (!guardado.equals(Constantes.RESULTADO_OK)){
					return 0;
				}
			} else {
				return 0;
			}
		} else {
			return 0;
		}
		return 1;
	}

	/**
	 * Obtiene el certificate private key.
	 *
	 * @return el certificate private key
	 */
	protected PrivateKeyEntry getCertificatePrivateKey() {
		PrivateKeyEntry certificatePrivateKey = null;
		if (certificatePrivateKey == null) {
			KeyStore.Entry key = null;
			try (InputStream is = new FileInputStream(properties.getProperty("INTEGRAPATH"))
			){				
				KeyStore ks = KeyStore.getInstance(properties.getProperty("KEYSTOREINSTANCE"));
				char [] passwordKS =properties.getProperty("PASSWORDKS").toCharArray();
				char [] passwordCert =properties.getProperty("PASSWORDCERT").toCharArray();
				ks.load(is, passwordKS);
				key = ks.getEntry(properties.getProperty("IPSFIRMA"), new KeyStore.PasswordProtection(passwordCert));
			} catch (NoSuchAlgorithmException e) {
				logger.error(STRING_ERROR_PRIVATEKEYENTRY,e);
			} catch (CertificateException e) {
				logger.error(STRING_ERROR_PRIVATEKEYENTRY,e);
			} catch (IOException e) {
				logger.error(STRING_ERROR_PRIVATEKEYENTRY,e);
			} catch (KeyStoreException e) {
				logger.error(STRING_ERROR_PRIVATEKEYENTRY,e);
			} catch (UnrecoverableEntryException e) {
				logger.error(STRING_ERROR_PRIVATEKEYENTRY,e);
			}
			certificatePrivateKey = (KeyStore.PrivateKeyEntry) key;
		}
		return certificatePrivateKey;
	}

	
	
	/**
	 * Registra el pdf con csv incrustrado en la tabla documentos de bb.dd
	 *
	 * @param pdfasbytes el pdfasbytes
	 * @param registroSolicitudForm el registro solicitud form
	 * @param codigoCSV el codigo CSV
	 * @return el long
	 */
	private long registroPdfCsvDocumentos(byte[] pdfasbytes, RegistroSolicitudForm registroSolicitudForm, String codigoCSV) {

		DocumentoBean docBean = new DocumentoBean(); 
		Locale locale = (Locale) this.getRequest().getSession().getAttribute(Globals.LOCALE_KEY);
		String localeName = locale.toString();
		Calendar c = Calendar.getInstance();
		int mes = c.get(Calendar.MONTH)+1;
		int fileSize = pdfasbytes.length;
		final String separador = File.separator;
				
		docBean.setNombre(Constantes.NOMBRE_JUSTIFICANTE_REGISTRO_CSV.toUpperCase());
		docBean.setFechaCreacion(c.getTime());
		
		// id tipo de documento
		docBean.setIdTipoDocumento(String.valueOf(Constantes.JUSTIFICANTE_DE_INSCRIPCION_SOLICITUD_PDF));
		if (localeName.equals(Constantes.CATALAN)) {
			docBean.setIdTipoDocumento(Constantes.JUSTIFICANTE_REGISTRO_ID_CATALAN);		             
		}else if (localeName.equals(Constantes.EUSKERA)) {
			docBean.setIdTipoDocumento(Constantes.JUSTIFICANTE_REGISTRO_ID_EUSKERA);	             
		}else if (localeName.equals(Constantes.GALLEGO)) {
			docBean.setIdTipoDocumento(Constantes.JUSTIFICANTE_REGISTRO_ID_GALLEGO);	             
		}else if (localeName.equals(Constantes.VALENCIANO)) {
			docBean.setIdTipoDocumento(Constantes.JUSTIFICANTE_REGISTRO_ID_VALENCIANO);
		}
		docBean.setDescripcion(Constantes.NOMBRE_JUSTIFICANTE_REGISTRO_CSV.toUpperCase() + ".PDF");
		docBean.setTamano(safeLongToInt(Math.round(fileSize/1024.0)));
		docBean.setIdSolicitud(Long.parseLong(registroSolicitudForm.getIdSolicitud()));
		
		// ubicacion
		StringBuilder rutaDocumento = new StringBuilder();
		rutaDocumento
				.append(c.get(Calendar.YEAR)).append(separador)
				.append(mes).append(separador)
				.append(c.get(Calendar.DAY_OF_MONTH)).append(separador)
				.append(c.get(Calendar.HOUR_OF_DAY)).append(separador)
				.append(docBean.getIdSolicitud()).append(separador);
	    docBean.setUbicacion(rutaDocumento.toString());
		docBean.setCsv(codigoCSV);
				
		long idDocumento = 0L;
		try{
			idDocumento = documentoConvocatoriasManager.insertarDocumentoCsv(docBean);
			logger.info("Id Justificante con CSV incrustrado registrado: "+ idDocumento);
		} catch(Exception e){
			logger.info("error en la insercion", e);
			logger.error(" registroPdfCsvDocumentos - Error:",e);
		}
		return idDocumento;
		
	}
	
	/**
	 * Sube a filesystem el pdf con csv incrustrado.
	 *
	 * @param pdfasbytes - el contenido del fichero
	 * @param idDocumento - el id del documento en base de datos
	 * @return 0 si hay errores 1 si todo ok
	 */
	private int subirDocumentoPdfCSV(byte[] pdfasbytes, long idDocumento) {
		byte[] fileData = pdfasbytes;
		
		// recupero el documentobean que voy a subir a filesystem
		DocumentoQuery documentoQuery = new DocumentoQuery();
		documentoQuery.setId(idDocumento);
		DocumentoBean docBean = documentoConvocatoriasManager.obtenerDocumento(documentoQuery);
		
		if (docBean!=null) {
			// asigno los valores al documento bean necesarios para el metodo estatico de subida a filesystem
			docBean.setUbicacion(docBean.getUbicacion());
			docBean.setNombreAlfresco(docBean.getNombreAlfresco());
			docBean.setContenidoDocumento(fileData);
		
			SistemaFicheros ges= new SistemaFicheros();
			try{
				ges.insertarContenido(docBean);
			}catch(Exception e){
				logger.error(" Error insertando en filesystem:",e);
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_CERTIFICADOS_ERROR_GESTORDOCUMENTAL));
				try{
					documentoConvocatoriasManager.eliminarDocumentoById(idDocumento);
				}catch(Exception o){
					logger.error(" Error- eliminar documento:",o);
					return 0;				
				}
				return 0;
			}
			return 1;	
		} else {
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_CERTIFICADOS_ERROR_GESTORDOCUMENTAL));
			return 0;
		}
	}


	/**
	 * Método que genera el bean con los atributos necesarios para el reporte.
	 *
	 * @param justificanteType JustificanteType
	 * @param registroType RegistroType
	 * @param registroSolicitudForm RegistroSolicitudForm
	 * @param encabezado el encabezado
	 * @return JustificanteBean
	 */
	private JustificanteBean toJustificanteBean(
			JustificanteType justificanteType, RegistroType registroType,
			RegistroSolicitudForm registroSolicitudForm, String encabezado,String MESSAGE_RESOUCE) {

		JustificanteBean aux = new JustificanteBean();
		// Datos de Registro
		if(justificanteType.getNmRegistro() != null && !"".equals(justificanteType.getNmRegistro())){
			aux.setNumRegistro(justificanteType.getNmRegistro());
		}else{
			aux.setNumRegistro("");
		}

		if(justificanteType.getFeRegistro() != null && !"".equals(justificanteType.getFeRegistro())){
			String fechaRegistro=justificanteType.getFeRegistro();		
			SimpleDateFormat formatter = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT_HHMMSS);
			SimpleDateFormat formatter1 = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT_DDMMYYYY);		
			SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");		

			try {	 
				Date date = formatter.parse(fechaRegistro);
				aux.setFecha(formatter1.format(date));
				aux.setHora(formatter2.format(date));
			} catch (ParseException e) {
				logger.error("Error parsear fecha registro:",e);
			}

		}else{
			aux.setFecha("");
			aux.setHora("");
		}

		if(justificanteType.getDsOficinaOrigen() != null && !"".equals(justificanteType.getDsOficinaOrigen())){
			aux.setOficina(justificanteType.getDsOficinaOrigen());
		}else{
			aux.setOficina("");
		}

		//Buscar convocatoria
		ConvocatoriaBean convocatoriaBean;
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(Long.parseLong(registroSolicitudForm.getIdConvocatoria()));
		convocatoriaBean = tablaBaseManager.buscarConvocatoriaId(convocatoriaQuery);
		
		//Buscar solicitud
		SolicitudBean solicitudBean;
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.setIdSolicitud(Long.parseLong(registroSolicitudForm.getIdSolicitud()));
		solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);

		//Solicitud
		if(convocatoriaBean.getDesMinisterio() != null && !"".equals(convocatoriaBean.getDesMinisterio())){
			aux.setMinisterio(convocatoriaBean.getDesMinisterio());
		}else{
			aux.setMinisterio("");
		}
		
		if(convocatoriaBean.getDesCentroGestor() != null && !"".equals(convocatoriaBean.getDesCentroGestor())){
			aux.setCentroGestor(convocatoriaBean.getDesCentroGestor());
		}else{
			aux.setCentroGestor("");
		}
		
		if(registroSolicitudForm.getNumJustificante() != null && !"".equals(registroSolicitudForm.getNumJustificante())){
			aux.setNumeroJustificante(registroSolicitudForm.getNumJustificante());
			aux.setCodigoTasa(registroSolicitudForm.getNumJustificante().substring(3, 6));
		}else{
			aux.setNumeroJustificante("");
			aux.setCodigoTasa("");
		}
		
		// TODO Encabezado generico o justicia
		// Por defecto aqui llega el generico ya multi-idiomado
		if(aux.getCodigoTasa().equals(Constantes.CODIGO_TASA_JUSTICIA)){
			if(encabezado.equals(Constantes.ENCABEZADO_GENERICO_CA)){
				aux.setEncabezado(Constantes.ENCABEZADO_JUSTICIA_CA);
			}else if(encabezado.equals(Constantes.ENCABEZADO_GENERICO_EU)){
				aux.setEncabezado(Constantes.ENCABEZADO_JUSTICIA_EU);
			}else if(encabezado.equals(Constantes.ENCABEZADO_GENERICO_GL)){
				aux.setEncabezado(Constantes.ENCABEZADO_JUSTICIA_GL);
			}else if(encabezado.equals(Constantes.ENCABEZADO_GENERICO_VL)){
				aux.setEncabezado(Constantes.ENCABEZADO_JUSTICIA_VL);
			}else{
				aux.setEncabezado(Constantes.ENCABEZADO_JUSTICIA);
			}
		}else{
			aux.setEncabezado(encabezado);
		}
		
		if(registroSolicitudForm.getEjercicio() != null && !"".equals(registroSolicitudForm.getEjercicio())){
			aux.setAnioConvocatoria1(registroSolicitudForm.getEjercicio().substring(0, 1));
			aux.setAnioConvocatoria2(registroSolicitudForm.getEjercicio().substring(1, 2));
			aux.setAnioConvocatoria3(registroSolicitudForm.getEjercicio().substring(2, 3));
			aux.setAnioConvocatoria4(registroSolicitudForm.getEjercicio().substring(3));
		}else{
			aux.setAnioConvocatoria1("");
			aux.setAnioConvocatoria2("");
			aux.setAnioConvocatoria3("");
			aux.setAnioConvocatoria4("");
		}

		//Identificacion
		if(registroSolicitudForm.getConsentimiento() == true){
				aux.setConsentimiento("X");
		}else{
			aux.setConsentimiento("");
		}
		
		if(registroSolicitudForm.getNif() != null && !"".equals(registroSolicitudForm.getNif())){
			aux.setNif(registroSolicitudForm.getNif());
		}else{
			aux.setNif("");
		}
		
		if(solicitudBean.getPrimerApellido() != null && !"".equals(solicitudBean.getPrimerApellido())){
			aux.setPrimerApellido(solicitudBean.getPrimerApellido());
		}else{
			aux.setPrimerApellido("");
		}
		
		if(solicitudBean.getSegundoApellido() != null && !"".equals(solicitudBean.getSegundoApellido())){
			aux.setSegundoApellido(solicitudBean.getSegundoApellido());
		}else{
			aux.setSegundoApellido("");
		}
		
		if(solicitudBean.getNombre() != null && !"".equals(solicitudBean.getNombre())){
			aux.setNombre(solicitudBean.getNombre());
		}else{
			aux.setNombre("");
		}
		
		StringBuffer textoNoSonsentimiento = new StringBuffer();
		
		textoNoSonsentimiento.append(ResourceBundle.getBundle(MESSAGE_RESOUCE).getString("formulario790.presencial.consiente2"));
		aux.setNoConsentimiento(textoNoSonsentimiento.toString());
		
		StringBuffer declaracion = new StringBuffer();
		declaracion.append(ResourceBundle.getBundle(MESSAGE_RESOUCE).getString("formulario790.solicita"));
		declaracion.append("\n");
		declaracion.append(ResourceBundle.getBundle(MESSAGE_RESOUCE).getString("formulario790.declara"));
		declaracion.append("\n");
		declaracion.append(ResourceBundle.getBundle(MESSAGE_RESOUCE).getString("formulario790.manifiesta").replaceAll("<br>",""));
//		declaracion.append("\n");
//		declaracion.append(ResourceBundle.getBundle(MESSAGE_RESOUCE).getString("formulario790.opone").replaceAll("<br>",""));
//		declaracion.append("\n");
//		declaracion.append(ResourceBundle.getBundle(MESSAGE_RESOUCE).getString("formulario790.autoriza"));
		aux.setDeclaracion(declaracion.toString());
		
		String enlace = convocatoriaBean.getEnlace();
		if(enlace == null || enlace.isEmpty()) {
			enlace = ResourceBundle.getBundle(MESSAGE_RESOUCE).getString("formulario790.enlace.noIndicado");
		}
		
		String cumplimiento =ResourceBundle.getBundle(MESSAGE_RESOUCE).getString("formulario790.cumplimiento")+enlace+ResourceBundle.getBundle(MESSAGE_RESOUCE).getString("formulario790.cumplimiento2");
		aux.setCumplimiento(cumplimiento);
		
		if(registroSolicitudForm.getMotivoOposicion() != null){
			String motivoOposicion = registroSolicitudForm.getMotivoOposicion();
			aux.setMotivoOposicion(motivoOposicion);
		}else{
			aux.setMotivoOposicion(" ");
		}
		
		StringBuffer textoEjerce = new StringBuffer();
		
		textoEjerce.append(ResourceBundle.getBundle(MESSAGE_RESOUCE).getString("formulario790.ejerce"));
		aux.setEjerce(textoEjerce.toString());
				
		StringBuffer textoEjerce2 = new StringBuffer();
		
		textoEjerce2.append(ResourceBundle.getBundle(MESSAGE_RESOUCE).getString("formulario790.ejerce2"));
		aux.setEjerce2(textoEjerce2.toString());
		
		StringBuffer textoAutorizar = new StringBuffer();
		
		if(registroSolicitudForm.getIdConsentimientoAEAT() != null){
			if(registroSolicitudForm.getIdConsentimientoAEAT() == true){
				aux.setAutorizar("X");
			}else{
				aux.setAutorizar("");
			}
		}	
		textoAutorizar.append(ResourceBundle.getBundle(MESSAGE_RESOUCE).getString("formulario790.autorizar"));
		aux.setAutoriza(textoAutorizar.toString());
		
		StringBuffer textoNoAutorizar = new StringBuffer();
		
		textoNoAutorizar.append(ResourceBundle.getBundle(MESSAGE_RESOUCE).getString("formulario790.noAutorizar"));
		aux.setNoAutorizar(textoNoAutorizar.toString());
		
		
		if(solicitudBean.getFechaNacimiento() != null && !"".equals(solicitudBean.getFechaNacimiento())){
			SimpleDateFormat formatoFechaNac = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT_DDMMYYYY);
			String fechaNac = formatoFechaNac.format(solicitudBean.getFechaNacimiento());
			aux.setDiaNacimiento1(fechaNac.substring(0, 1));
			aux.setDiaNacimiento2(fechaNac.substring(1, 2));
			aux.setMesNacimiento1(fechaNac.substring(3, 4));
			aux.setMesNacimiento2(fechaNac.substring(4, 5));
			aux.setAnioNacimiento1(fechaNac.substring(8, 9));
			aux.setAnioNacimiento2(fechaNac.substring(9, 10));
		}else{
			aux.setDiaNacimiento1("");
			aux.setDiaNacimiento2("");
			aux.setMesNacimiento1("");
			aux.setMesNacimiento2("");
			aux.setAnioNacimiento1("");
			aux.setAnioNacimiento2("");
		}
		
		if(solicitudBean.getSexo() != ' '){
			if(Constantes.SEXO_HOMBRE == solicitudBean.getSexo()){
				aux.setSexoHombre("X");
				aux.setSexoMujer("");
			}else{
				aux.setSexoHombre("");
				aux.setSexoMujer("X");
			}
		}else{
			aux.setSexoHombre("");
			aux.setSexoMujer("");
		}
		
		if(solicitudBean.getNacionalidad() != null && !"".equals(solicitudBean.getNacionalidad())){
			aux.setNacionalidad(solicitudBean.getNacionalidad());
		}else{
			aux.setNacionalidad("");
		}
		
		if(registroSolicitudForm.getEmail() != null && !"".equals(registroSolicitudForm.getEmail())){
			aux.setCorreoElectronicos(solicitudBean.getEmail());
		}else{
			aux.setCorreoElectronicos("");
		}
		
		if(solicitudBean.getTelefono() != null && !"".equals(solicitudBean.getTelefono())){
			if(solicitudBean.getTelefono().length()>10){
				aux.setTelefono(solicitudBean.getTelefono().substring(0, 9));
				aux.setTelefono1(solicitudBean.getTelefono().substring(0, 9));
				aux.setTelefonoAux(solicitudBean.getTelefono().substring(10));
			}else{
				aux.setTelefono(solicitudBean.getTelefono());
				aux.setTelefono1(solicitudBean.getTelefono());
				aux.setTelefonoAux("");
			}
		}else{
			aux.setTelefono("");
			aux.setTelefonoAux("");
			aux.setTelefono1("");
		}
		
		if(solicitudBean.getCalleDomicilio() != null && !"".equals(solicitudBean.getCalleDomicilio())){
			aux.setCalleDomicilio(solicitudBean.getCalleDomicilio());
		}else{
			aux.setCalleDomicilio("");
		}
		
		if(solicitudBean.getCodigoPostalDomicilio() != null && !"".equals(solicitudBean.getCodigoPostalDomicilio())){
			aux.setCodigoPostalDomicilio(solicitudBean.getCodigoPostalDomicilio());
		}else{
			aux.setCodigoPostalDomicilio("");
		}
		
		if(solicitudBean.getMunicipioDomicilio() != null && !"".equals(solicitudBean.getMunicipioDomicilio())){
			aux.setMunicipioDomicilio(solicitudBean.getMunicipioDomicilio());
		}else{
			aux.setMunicipioDomicilio("");
		}
		
		if(solicitudBean.getProvinciaDomicilioDes() != null && !"".equals(solicitudBean.getProvinciaDomicilioDes())){
			aux.setProvinciaDomicilio(solicitudBean.getProvinciaDomicilioDes());
		}else{
			aux.setProvinciaDomicilio("");
		}
		
		if(solicitudBean.getPaisDomicilioDes() != null && !"".equals(solicitudBean.getPaisDomicilioDes())){
			aux.setPais(solicitudBean.getPaisDomicilioDes());
		}else{
			aux.setPais("");
		}
		
		//AutoLiquidacion
		if(convocatoriaBean.getDesCuerpoEscala() != null && !"".equals(convocatoriaBean.getDesCuerpoEscala())){
			aux.setCuerpoEscala(convocatoriaBean.getDesCuerpoEscala());
		}else{
			aux.setCuerpoEscala("");
		}
		
		if(solicitudBean.getDesEspecialidad() != null && !"".equals(solicitudBean.getDesEspecialidad())){
			aux.setEspecialidad(solicitudBean.getDesEspecialidad());
		}else{
			aux.setEspecialidad("");
		}
		
		if(convocatoriaBean.getCodFormaAcceso() != null && !"".equals(convocatoriaBean.getCodFormaAcceso())){
			aux.setFormaAcceso(convocatoriaBean.getCodFormaAcceso());
		}else{
			aux.setFormaAcceso("");
		}
		
		if(convocatoriaBean.getDesMinisterioConvocante() != null && !"".equals(convocatoriaBean.getDesMinisterioConvocante())){
			aux.setMinisterioConvocatoria(convocatoriaBean.getDesMinisterioConvocante());
		}else{
			aux.setMinisterioConvocatoria("");
		}
		
		if(convocatoriaBean.getFechaBoe() != null && !"".equals(convocatoriaBean.getFechaBoe())){
			SimpleDateFormat formatoFechaBoe = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT_DDMMYYYY);
			String fechaBoe = formatoFechaBoe.format(convocatoriaBean.getFechaBoe());
			aux.setDiaFechaBoe1(fechaBoe.substring(0, 1));
			aux.setDiaFechaBoe2(fechaBoe.substring(1, 2));
			aux.setMesFechaBoe1(fechaBoe.substring(3, 4));
			aux.setMesFechaBoe2(fechaBoe.substring(4, 5));
			aux.setAnioFechaBoe1(fechaBoe.substring(8, 9));
			aux.setAnioFechaBoe2(fechaBoe.substring(9, 10));
		}else{
			aux.setDiaFechaBoe1("");
			aux.setDiaFechaBoe2("");
			aux.setMesFechaBoe1("");
			aux.setMesFechaBoe2("");
			aux.setAnioFechaBoe1("");
			aux.setAnioFechaBoe1("");
		}
		
		if(solicitudBean.getDesProvinciaExamen() != null && !"".equals(solicitudBean.getDesProvinciaExamen())){
			aux.setProvinciaExamen(solicitudBean.getDesProvinciaExamen());
		}else{
			aux.setProvinciaExamen("");
		}
		
		if(solicitudBean.getPorcentajeDiscapacidad() != null && !"".equals(solicitudBean.getPorcentajeDiscapacidad())){
			aux.setPorcentajeDiscapacidad(String.valueOf(solicitudBean.getPorcentajeDiscapacidad()));
		}else{
			aux.setPorcentajeDiscapacidad("");
		}
		
		if(solicitudBean.getTipoDiscapacidadDes() != null && !"".equals(solicitudBean.getTipoDiscapacidadDes())){
			aux.setTipoDiscapacidad(solicitudBean.getTipoDiscapacidadDes()+"");
		}else{
			aux.setTipoDiscapacidad("");
		}
		
		if(solicitudBean.getDetalleDiscapacidad() != null && !"".equals(solicitudBean.getDetalleDiscapacidad())){
			aux.setDetalleDiscapacidad(solicitudBean.getDetalleDiscapacidad());
		}else{
			aux.setDetalleDiscapacidad("");
		}
		
		if(solicitudBean.getDesTituloOficial() != null && !"".equals(solicitudBean.getDesTituloOficial())){
			aux.setTituloOficial(solicitudBean.getDesTituloOficial());
		}else{
			aux.setTituloOficial("");
		}
		
		if(solicitudBean.getOtrosTitulos() != null && !"".equals(solicitudBean.getOtrosTitulos())){
			aux.setOtrosTitulos(solicitudBean.getOtrosTitulos());
		}else{
			aux.setOtrosTitulos("");
		}

//		if(solicitudBean.getMotivoOposicion() != null && !"".equals(solicitudBean.getMotivoOposicion())){
//			aux.setMotivoOposicion(solicitudBean.getMotivoOposicion());
//		}else{
//			aux.setMotivoOposicion("");
//		}
		
		// Campos propios
		SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
		solicitudComunQuery.setIdSolicitud(solicitudBean.getId());
		SolicitudPropiosQuery solicitudPropiosQuery = new SolicitudPropiosQuery();
		solicitudPropiosQuery.setSolicitudComun((solicitudComunQuery));
		ArrayList<SolicitudPropiosBean> listaSolicitudPropios = solicitudPropioManager.obtenerCamposPropiosByIdSolicitud(solicitudPropiosQuery);

		ArrayList<CamposPropiosBean> listaCamposPropios = new ArrayList<CamposPropiosBean>();

		for (SolicitudPropiosBean solicitudPropiosBean : listaSolicitudPropios) {
			CamposPropiosBean camposPropiosBean = new CamposPropiosBean();
			camposPropiosBean.setCampo(solicitudPropiosBean.getCamposPropiosBean().getCampo());
			camposPropiosBean.setValorVista(solicitudPropiosBean.getValor());
			listaCamposPropios.add(camposPropiosBean);
		}
				
		aux.setListaCamposPropios(listaCamposPropios);	
		
		// Documentación anexa
		DocumentoType auxDocType[] = registroType.getDocumentos();
		ArrayList<DocumentoBean>listaDocumentos = new ArrayList<DocumentoBean>();

		// Lista auxiliar con el hash de cada documento para la comprobación con bbdd
		// de no incluir documentos repetidos en el justificante.
		ArrayList<String>listaHashes = new ArrayList<String>();

		// Recuperación del formulario html y de los documentos anexos
		if(registroType.getDocumentos()!=null && registroType.getDocumentos().length>0){	
			for (int i=0; i<registroType.getDocumentos().length; i++) {
				if(auxDocType[i]!=null && auxDocType[i].getDsNombre()!=null && !auxDocType[i].getDsNombre().equals("")){
					
						if(auxDocType[i].getDsNombre().endsWith(Constantes.EXTENSION_FORMULARIO)){
							aux.setHtmlRegistrado(auxDocType[i].getDsNombre());
							aux.setHtmlHash(auxDocType[i].getBlHash());
						}else{
							DocumentoBean docAnexo = new DocumentoBean();
							docAnexo.setNombre(auxDocType[i].getDsNombre());
							docAnexo.setHashExtracto(auxDocType[i].getBlHash());
							listaDocumentos.add(docAnexo);
							listaHashes.add(auxDocType[i].getBlHash());
						}
					
				}
			}
			aux.setListaDocumentos(listaDocumentos);
		}

		// Comprobación en bbdd de documentación anexa a la solicitud.
		// Este caso existirá si se almacenaron en Alfresco los documentos 
		// pero el proceso falló en el pago o en el registro.
		SolicitudComunQuery comunQuery = new SolicitudComunQuery();
		comunQuery.setIdSolicitud(solicitudBean.getId());

		DocumentoQuery documentosQuery = new DocumentoQuery();
		documentosQuery.setSolicitudComun(comunQuery);

		TipoDocumentoQuery tipoDocumentoQuery = new TipoDocumentoQuery();
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_ANEXO_SOLICITUD);
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_TASA);
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_ADJUNTO);
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_DISCAPACIDAD);
		documentosQuery.setTipoDocumento(tipoDocumentoQuery);

		ArrayList<DocumentoBean>listaDocumentosAux = documentoConvocatoriasManager.buscarDocumentosConvocatoria(documentosQuery);

		// Si no hay documentos anexos, generamos uno vacío para mejorar la apariencia del pdf.
		if(listaDocumentosAux.isEmpty() && aux.getListaDocumentos().isEmpty()){
			DocumentoBean docAnexo = new DocumentoBean();
			docAnexo.setNombre(Constantes.SIN_DOCUMENTOS_ANEXOS);
			docAnexo.setHashExtracto(Constantes.SIN_DOCUMENTOS_ANEXOS);
			listaDocumentos.add(docAnexo);
		}else{
			for(int i=0; i<listaDocumentosAux.size(); i++){
				// Controlar que el documento no sea el mismo que ya ha sido
				// guardado en bbdd a través de la lista de hashes.
				if(!listaHashes.contains(listaDocumentosAux.get(i).getHashExtracto())){
					DocumentoBean docAnexo = new DocumentoBean();
					docAnexo.setNombre(listaDocumentosAux.get(i).getNombre());
					docAnexo.setHashExtracto(listaDocumentosAux.get(i).getHashExtracto());
					listaDocumentos.add(docAnexo);
				}
			}
		}

		aux.setListaDocumentos(listaDocumentos);

		// Datos del pago
		// los datos del pago se obtienen de base de datos porque en el objeto registroSolicitudForm unicamente tenemos los datos del pago de la ejecucion actual
		// Se obtiene todos los pagos asociados
		SolicitudComunQuery solicitudQueryPago = new SolicitudComunQuery();
		solicitudQueryPago.setIdSolicitud(Long.parseLong(solicitudBean.getId().toString()));	
		PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();	
		pagoSolicitudQuery.setResultado(Constantes.RESULTADO_PAGO_OK);
		pagoSolicitudQuery.setSolicitudComun(solicitudQueryPago);
		pagoSolicitudQuery.addOrder("fechaIntento", OrderType.ASC);
		ArrayList<DetallePagoSolicitudBean> pagosAsociados = pagoSolicitudManager.buscarDetallePagoSolicitud(pagoSolicitudQuery);
		
		ArrayList<JustificantePagoBean> justificantesPagos = new ArrayList<JustificantePagoBean>();
		
		Integer index = 1;
		for(DetallePagoSolicitudBean pago : pagosAsociados){
			JustificantePagoBean justificantePagoBean = new JustificantePagoBean();
			if(pago.getImporte()!=null && !"".equals(pago.getImporte())){
				justificantePagoBean.setImporte(pago.getImporte());
			}else{
				justificantePagoBean.setImporte("");
			}
			
			if(pago.getTipo()!=null && !"".equals(pago.getTipo())){
				justificantePagoBean.setTipoPago(pago.getTipo());
			}else{
				justificantePagoBean.setTipoPago("");
			}
			
			if(pago.getDesMotivoReduccion()!=null && !"".equals(pago.getDesMotivoReduccion())){
				justificantePagoBean.setCausaExencion(pago.getDesMotivoReduccion());
			}else{
				justificantePagoBean.setCausaExencion("");
			}
			
			if(pago.getFechaIntento().toString()!=null && !"".equalsIgnoreCase(pago.getFechaIntento().toString())){
				if(justificantePagoBean.getImporte().equals("0.0")){
					justificantePagoBean.setFechaPago("");
				}else{
					justificantePagoBean.setFechaPago(pago.getFechaIntento().toString());
				}
			}else{
				justificantePagoBean.setFechaPago("");
			}
			
			if(pago.getNrc()!=null && !"".equals(pago.getNrc())){
				justificantePagoBean.setNrc(pago.getNrc());
			}else{
				justificantePagoBean.setNrc("");
			}
			
			if(pago.getDesEntidad()!=null && !"".equals(pago.getDesEntidad())){
				justificantePagoBean.setEntidad(pago.getDesEntidad());
			}else{
				justificantePagoBean.setEntidad("");
			}
			
			justificantePagoBean.setOrden(index.toString());
			
			justificantesPagos.add(justificantePagoBean);
			index++;
		}
		
		aux.setJustificantePagoBean(justificantesPagos);
		

		return aux;
	}

	/**
	 * Calcular tamanio ficheros.
	 *
	 * @param registroForm el registro form
	 * @return el int
	 */
	private int calcularTamanioFicheros(
			RegistroSolicitudForm registroForm) {
		Long tamanioTotal = Long.parseLong(properties.getProperty("conf.tamanioTotalDocumento"));
		Long tamanioReal=0L;
		Long tamanioFichero = Long.parseLong(properties.getProperty("conf.tamanioFichero"));
		
		for(int i =0;i<registroForm.getDocumentosFicheros().length;i++){
			if((registroForm.getExtracto(i) == null || "".equals(registroForm.getExtracto(i)))
					&& (registroForm.getExtractoFirefox(i) == null || "".equals(registroForm.getExtractoFirefox(i)))){
				return 0;
			}
			
			if(registroForm.getExtracto(i) != null) {
				
				String fileName = "";	 
			
			if(registroForm.getExtracto(i) != null && !"".equals(registroForm.getExtracto(i))){
				fileName = registroForm.getDocumentoFile(i);
			}
			logger.info(fileName);
			byte[] fileData;
			
			try {
				fileData = IpsUtils.decodeBase64(registroForm.getDocumentosFicheros(i));
			} catch (Exception e1) {			
				logger.error("calcularTamanioFicheros - Error",e1);
				return 1;
			}
			
			int fileSize = fileData.length;
			int tamanioAux = safeLongToInt(Math.round(fileSize/1024.0));
			tamanioReal = tamanioReal + tamanioAux;
			
			if(tamanioAux >= tamanioFichero){
				return 2;
			}
			
			if(tamanioReal >= tamanioTotal){
				return 3;
			}
		}
		}
		return 0;
	}

	/**
	 * Subir documento registro.
	 *
	 * @param registroForm el registro form
	 * @return el string
	 */
	private String subirDocumentoRegistro(RegistroSolicitudForm registroForm) {
		
		// Incidencia "EL VALOR DEL CAMPO CDFIRMADO DEL REGISTRO DE DOCUMENTOS INDICA QUE DEBE EXISTIR FIRMA."
		// La firma llega vacía.
		//TODO Como ya no se va a firmar, no vamos a valorar la firma
			
			String fileName = "";
			String contentType = "" + registroForm.getNumJustificante();
			fileName = "/"+contentType+STRING_HTML;
			byte[] fileData;

			try {
				fileData = registroForm.getDocumentoHTML().getBytes();
			} catch (Exception e1) {			
				logger.error("subirDocumentoRegistro - Error",e1);
				return STRING_ERROR;
			}
			int fileSize = fileData.length;
			DocumentoBean docBean= new DocumentoBean();
			
			//Asigno los valores al bean
			docBean.setContenidoDocumento(fileData);


			int numAux = fileName.lastIndexOf('/');
			if(numAux<0){
				numAux = fileName.lastIndexOf('\\');
			}
			int numExten = fileName.lastIndexOf('.');
			String nombreAux = fileName.substring(numAux+1,numExten);	        
			String extension=fileName.substring(numExten+1);
			docBean.setExtension(extension);
			docBean.setTamano(safeLongToInt(Math.round(fileSize/1024.0)));
			docBean.setNombre(nombreAux);
			docBean.setDescripcion(contentType);

			if(contentType == null || "".equals(contentType)){

				this.setRequestAttribute(STRING_REGISTRO,registroForm.getIdSolicitud());
				return "verDocumentos";
			}
			
			Calendar c = Calendar.getInstance();
			docBean.setFechaCreacion(c.getTime());      
			
			docBean.setDsNombreDocumento(nombreAux);
			docBean.setIdSolicitud(Long.parseLong(registroForm.getIdSolicitud()));
			docBean.setEntorno(Constantes.ENTORNO_SOLICITUDES);
			try {
				SHA0 hash = new SHA0();
				String hashFile=hash.getHash(fileData);
				docBean.setHashExtracto(hashFile);	
			} catch (NoSuchAlgorithmException e2) {
				logger.error("Error obteniendo hash del documento: ",e2);
			}

			Long id = 0L;
			try{
				id = documentoConvocatoriasManager.insertarDocumento(docBean);
				if(id==0){    			
					this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_CERTIFICADOS_ERROR_GESTORDOCUMENTAL));
					return STRING_ERROR;		        			
				}
				

			}catch(Exception e){
				logger.error("subirDocumentoRegistro - Error",e);
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_CERTIFICADOS_ERROR_GESTORDOCUMENTAL));
				return STRING_ERROR;
			}

			return STRING_CORRECTO;
	}

	/**
	 * Subir documentos.
	 *
	 * @param registroForm el registro form
	 * @return el string
	 */
	private String subirDocumentos(RegistroSolicitudForm registroForm) {

		//Para agregar transpasar los adjuntos de la anterior solicitud a la nueva
		if (!StringUtils.isEmpty(registroForm.getIdSolicitudInicial())) {
			try {
				long idSolicitudOld = Long.parseLong(registroForm.getIdSolicitudInicial());
				
				if (idSolicitudOld > 0) {
					DocumentoQuery documentoQuery = new DocumentoQuery();
					documentoQuery.setId(idSolicitudOld);
					documentoConvocatoriasManager.migrarDocumentosAdjuntos(idSolicitudOld, Long.parseLong(registroForm.getIdSolicitud()));
				}
			} catch (Exception e) {
				logger.error("Error al agregar los adjuntos anteriores.");
			}
		}

		logger.info("Retorno: correcto");
		return STRING_CORRECTO;
	}

	/**
	 * To registro bean.
	 *
	 * @param registroSolicitudForm el registro solicitud form
	 * @return el registro solicitud bean
	 */
	private RegistroSolicitudBean toRegistroBean(
			RegistroSolicitudForm registroSolicitudForm) {
		SimpleDateFormat formatoFecha= new SimpleDateFormat(STRING_SIMPLEDATEFORMAT_DDMMYYYY);
		RegistroSolicitudBean aux = new RegistroSolicitudBean();
		aux.setIdSolicitud(registroSolicitudForm.getIdSolicitud());
		Date fechaActual = new Date();
		aux.setFechaPago(formatoFecha.format(fechaActual));	

		return aux;
	}

	/**
	 * Safe long to int.
	 *
	 * @param l el l
	 * @return el int
	 */
	public static int safeLongToInt(long l) {
		if (l < Integer.MIN_VALUE || l > Integer.MAX_VALUE) {
			throw new IllegalArgumentException
			(l + " cannot be cast to int without changing its value.");
		}
		return (int) l;
	}

	/**
	 * Crear HTML.
	 *
	 * @param convocatoriaBean el convocatoria bean
	 * @param solicitudBean el solicitud bean
	 * @param detallePagoSolicitudBean el detalle pago solicitud bean
	 * @param registroSolicitud el registro solicitud
	 * @param convocatoriaRepetida_Unico el convocatoria repetida unico
	 * @return el string
	 */
	public String crearHTML(ConvocatoriaBean convocatoriaBean, SolicitudBean solicitudBean, DetallePagoSolicitudBean detallePagoSolicitudBean, RegistroSolicitudForm registroSolicitud, String convocatoriaRepetida_Unico){

		StringBuilder pagina= new StringBuilder();
		StringBuilder cabecera = new StringBuilder(); 
		cabecera.append("<html><head><link rel='stylesheet' type='text/css' href='"+properties.getProperty(STRING_URL_ENTORNO_IPSC)+"css/estilos.css'><link rel='stylesheet' type='text/css' href='"+properties.getProperty(STRING_URL_ENTORNO_IPSC)+"css/detalles.css'><link rel='stylesheet' type='text/css' href='"+properties.getProperty(STRING_URL_ENTORNO_IPSC)+"css/default.css'><link rel='stylesheet' type='text/css' href='"+properties.getProperty(STRING_URL_ENTORNO_IPSC)+"css/estilo_060.css'>");
		cabecera.append("<style type='text/css'>");
		cabecera.append("      #contenedor{text-align:left;font-family: Arial, Helvetica, sans-serif;margin-left:1%;      margin-right:2%;min-height:400px;border-bottom:2px solid #758086;}");
		cabecera.append("      #contenedor_principal{clear:left;width:100%;overflow:hidden;padding-bottom:2px;display:table;}");
		cabecera.append("      .texto_introduccion{");
		cabecera.append("             text-align:left;font-family: Arial, Helvetica, sans-serif;");
		cabecera.append("             font-size:14px;");
		cabecera.append("      font-weight: bold;");
		cabecera.append("             color:#979b9c;");
		cabecera.append("             border-bottom:2px solid #90979d;");
		cabecera.append("             margin-left: 12px;");
		cabecera.append("      }");
		cabecera.append("      .cuerpo_central_tituloNivel1{");
		cabecera.append("             font-weight:bold;");
		cabecera.append("             font-size:13px;     ");
		cabecera.append("             padding:5px 20px;");
		cabecera.append("             margin-left:5px;");
		cabecera.append("             margin-bottom:0.1em;");
		cabecera.append("      }");
		cabecera.append("      .cuerpo_central_tituloNivel2{");
		cabecera.append("             font-weight:normal;");
		cabecera.append("             font-size:11px;     ");
		cabecera.append("             padding:2px 20px;");
		cabecera.append("             margin-left:10px;");
		cabecera.append("             margin-bottom:0.5em;");
		cabecera.append("             color:#758086;");
		cabecera.append("      }</style></head><body>");

		StringBuilder centro = new StringBuilder(); 
		centro.append("   <div class='clear'></div>");
		centro.append("                <br/>");
		centro.append("                <div class='capa99'>");
		centro.append("                      <div class='tituloPag' style='width: 45%;float: left' align='left'>");
		centro.append(                               RESOURCE_BUNDLE.getString("field.extracto.datosRegistro"));
		centro.append("                       </div>       ");
		centro.append("                    </div>");
		centro.append("                    <div class='clear'></div><br>"); 
		centro.append("                       <br><br>");
		centro.append("                       <div id='contenedor'>");
		centro.append("                             <div id='contenedor_principal'>");
		centro.append(STRING_DIV_CLASS_CLEAR_DIV);
		centro.append("                                    <div id='content_tituloNivel1'>");
		centro.append("                                           <div id='cuerpo_central_tituloNivel1'>");
		centro.append(                                                   RESOURCE_BUNDLE.getString("field.extracto.datosRegistro"));
		centro.append(STRING_DIV);
		centro.append(STRING_DIV2);
		centro.append(STRING_DIV_CLASS_CLEAR_DIV);       
		centro.append(STRING_DIV_CLASS_CAPA99);
		centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
		centro.append(                                                   RESOURCE_BUNDLE.getString("field.extracto.nombre"));                
		centro.append(STRING_DIV);
		centro.append(STRING_DIV_CLASS_LABELDRC);
		centro.append(                                                  solicitudBean.getNombre());         
		centro.append(STRING_DIV);
		centro.append(STRING_DIV3);
		centro.append(STRING_DIV_CLASS_CLEAR);
		centro.append(STRING_DIV_CLASS_CAPA99_2);
		centro.append("                                             <div class='labelIzqDet_Rojo'>");
		centro.append(                                                     RESOURCE_BUNDLE.getString("field.extracto.nif"));                   
		centro.append(STRING_DIV4);
		centro.append(STRING_DIV_CLASS_LABELDRC_2);
		centro.append(                                                     solicitudBean.getNif());           
		centro.append(STRING_DIV4);
		centro.append(STRING_DIV3);
		centro.append(STRING_DIV_CLASS_CLEAR);
		centro.append(STRING_DIV_CLASS_CAPA99_2);
		centro.append("                                             <div class='labelIzqDet_Rojo'>");
		centro.append(                                                     RESOURCE_BUNDLE.getString("field.extracto.fechaNacimiento"));                     
		centro.append(STRING_DIV4);
		centro.append(STRING_DIV_CLASS_LABELDRC_2);
		centro.append(                                                     solicitudBean.getFechaNacimiento());          
		centro.append(STRING_DIV4);
		centro.append(STRING_DIV3);
		centro.append(STRING_DIV_CLASS_CLEAR);
		centro.append(STRING_DIV_CLASS_CAPA99);
		centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
		centro.append(                                                   RESOURCE_BUNDLE.getString("field.extracto.nacionalidad"));   
		centro.append(STRING_DIV);
		centro.append(STRING_DIV_CLASS_LABELDRC);
		if(solicitudBean.getNacionalidad()!=null)
			centro.append(                                                     solicitudBean.getNacionalidad());
		centro.append(STRING_DIV);
		centro.append(STRING_DIV2);
		centro.append(STRING_DIV_CLASS_CLEAR_DIV);
		centro.append(STRING_DIV_CLASS_CAPA99);
		centro.append("                                    <div class='labelIzqDet_Rojo'>");
		centro.append(                                                   RESOURCE_BUNDLE.getString("field.extracto.telefono")); 
		centro.append(STRING_DIV2);
		centro.append(STRING_DIV_CLASS_LABELDRC);
		centro.append(                                                     solicitudBean.getTelefono());         
		centro.append(STRING_DIV);
		centro.append(STRING_DIV2);
		centro.append(STRING_DIV_CLASS_CLEAR_DIV);

		centro.append(STRING_DIV_CLASS_CAPA99);
		centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
		centro.append(                                                  RESOURCE_BUNDLE.getString("field.extracto.email"));                 
		centro.append(STRING_DIV);
		centro.append(STRING_DIV_CLASS_LABELDRC);
		centro.append(                                                     solicitudBean.getEmail());        
		centro.append(STRING_DIV);
		centro.append(STRING_DIV2);
		centro.append(STRING_DIV_CLASS_CLEAR_DIV);

		centro.append(STRING_DIV_CLASS_CAPA99);
		centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
		centro.append(                                                  RESOURCE_BUNDLE.getString("field.extracto.calle"));                 
		centro.append(STRING_DIV);
		centro.append(STRING_DIV_CLASS_LABELDRC);
		centro.append(                                                     solicitudBean.getCalleDomicilio());         
		centro.append(STRING_DIV);
		centro.append(STRING_DIV2);
		centro.append(STRING_DIV_CLASS_CLEAR_DIV);

		centro.append(STRING_DIV_CLASS_CAPA99);
		centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
		centro.append(                                                  RESOURCE_BUNDLE.getString("field.extracto.codigoPostal"));                 
		centro.append(STRING_DIV);
		centro.append(STRING_DIV_CLASS_LABELDRC_2);
		centro.append(                                                     solicitudBean.getCodigoPostalDomicilio());       
		centro.append(STRING_DIV4);
		centro.append(STRING_DIV3);
		centro.append(STRING_DIV_CLASS_CLEAR);

		centro.append(STRING_DIV_CLASS_CAPA99_2);
		centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO_2);
		centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.municipio"));                    
		centro.append(STRING_DIV5);
		centro.append(STRING_DIV_CLASS_LABELDRC_3);
		centro.append(                                                            solicitudBean.getMunicipioDomicilio());         
		centro.append(STRING_DIV5);
		centro.append(STRING_DIV6);
		centro.append("                                         <div class='clear'></div>");

		centro.append("                                          <div class='capa99'>");
		centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO_2);
		centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.provincia"));      
		centro.append(STRING_DIV4);
		centro.append(STRING_DIV_CLASS_LABELDRC_3);
		centro.append(                                                            solicitudBean.getProvinciaDomicilioDes());
		centro.append(STRING_DIV5);
		centro.append(STRING_DIV4);
		centro.append(STRING_DIV_CLASS_CLEAR_5);

		centro.append(STRING_DIV_CLASS_CAPA99_3);
		centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO_2);
		centro.append(                                                                   RESOURCE_BUNDLE.getString("field.extracto.pais"));     
		centro.append(STRING_DIV5);
		centro.append(STRING_DIV_CLASS_LABELDRC_2);
		centro.append(                                                            solicitudBean.getPaisDomicilioDes());
		centro.append(STRING_DIV4);
		centro.append(STRING_DIV3);
		centro.append(STRING_DIV_CLASS_CLEAR);

		centro.append("                                       <div id='content_tituloNivel1'>");
		centro.append("                                             <div id='cuerpo_central_tituloNivel1'>");
		centro.append(                                                                   RESOURCE_BUNDLE.getString("field.extracto.datosSolicitud")); 
		centro.append("                                             </div> ");
		centro.append(STRING_DIV3);
		centro.append(STRING_DIV_CLASS_CLEAR);

		centro.append(STRING_DIV_CLASS_CAPA99_2);
		centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO_2);
		centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.especialidad"));   
		centro.append(STRING_DIV5);
		centro.append(STRING_DIV_CLASS_LABELDRC_3);
		if(solicitudBean.getDesEspecialidad()!=null)
			centro.append(                                                            solicitudBean.getDesEspecialidad());
		centro.append(STRING_DIV5);
		centro.append(STRING_DIV5);
		centro.append(STRING_DIV_CLASS_CLEAR_2);

		centro.append("                                                    <div class='capa99'>");
		centro.append("                                                           <div class='labelIzqDet_Rojo'>");
		centro.append(                                                                   RESOURCE_BUNDLE.getString("field.extracto.provinciaExamen"));
		centro.append(STRING_DIV7);
		centro.append("                                                           <div class='labelDrc'>");
		centro.append(                                                                   solicitudBean.getDesProvinciaExamen());      
		centro.append(STRING_DIV5);
		centro.append(STRING_DIV5);
		centro.append(STRING_DIV_CLASS_CLEAR_2);

		centro.append("                                                    <div class='capa99'>");
		centro.append("                                                           <div class='labelIzqDet_Rojo'>");
		centro.append(                                                                   RESOURCE_BUNDLE.getString("field.extracto.tituloOficial"));
		centro.append(STRING_DIV7);
		centro.append("                                                           <div class='labelDrc'>");
		centro.append(                                                                   solicitudBean.getDesTituloOficial());
		centro.append(STRING_DIV7);
		centro.append(STRING_DIV5);
		centro.append(STRING_DIV_CLASS_CLEAR_5);

		centro.append(STRING_DIV_CLASS_CAPA99_3);
		centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO_2);
		centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.otrosTitulos"));
		centro.append(STRING_DIV5);
		centro.append(STRING_DIV_CLASS_LABELDRC_3);
		if(solicitudBean.getOtrosTitulos()!=null && !solicitudBean.getOtrosTitulos().equals(""))
			centro.append(                                                                   solicitudBean.getOtrosTitulos());  
		centro.append(STRING_DIV5);
		centro.append(STRING_DIV4);
		centro.append(STRING_DIV_CLASS_CLEAR_2);

		centro.append(STRING_DIV_CLASS_CAPA99_3);
		centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO_2);
		centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.consienteVerificacionDatos"));
		centro.append(STRING_DIV5);
		centro.append(STRING_DIV_CLASS_LABELDRC_3);
		if(solicitudBean.getIdConsentimiento())
			centro.append("                                                           SI");
		else
			centro.append("                                                           NO");
		centro.append(STRING_DIV5);
		centro.append(STRING_DIV4);
		centro.append(STRING_DIV_CLASS_CLEAR_5);

		centro.append("                                             <div id='content_tituloNivel1'>");
		centro.append("                                                    <div id='cuerpo_central_tituloNivel1'>");
		centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.datosConvocatoria"));
		centro.append("                                                    </div> ");
		centro.append(STRING_DIV4);
		centro.append(STRING_DIV_CLASS_CLEAR_5);

		centro.append(STRING_DIV_CLASS_CAPA99_3);
		centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO_2);
		centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.numeroJustificante"));
		centro.append(STRING_DIV5);
		centro.append(STRING_DIV_CLASS_LABELDRC_3);
		centro.append(                                                            solicitudBean.getNumeroSolicitud());        
		centro.append(STRING_DIV5);
		centro.append(STRING_DIV4);
		centro.append(STRING_DIV_CLASS_CLEAR_5);                    

		if(convocatoriaBean.getSiglasCentroGestor().equals("")){
			centro.append(STRING_DIV_CLASS_CAPA99_4);
			centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
			centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.a?oConvocatoria"));
			centro.append(STRING_DIV);
			centro.append(STRING_DIV_CLASS_LABELDRC_4);
			centro.append(                                                 convocatoriaBean.getEjercicio());           
			centro.append(STRING_DIV6);
			centro.append(STRING_DIV8);
		}     

		if(!convocatoriaBean.getSiglasCentroGestor().equals("")){
			centro.append(STRING_DIV_CLASS_CAPA99_4);
			centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
			centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.ejercicio"));
			centro.append(STRING_DIV);
			centro.append(STRING_DIV_CLASS_LABELDRC_4);
			centro.append(                                                 convocatoriaBean.getEjercicio());           
			centro.append(STRING_DIV6);
			centro.append(STRING_DIV8);
		}     

		centro.append("                             <div class='clear'></div>");
		centro.append("                             <div class='capa99'>");
		centro.append("                                          <div class='labelIzqDet_Rojo'>");
		centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.ministerio"));
		centro.append(STRING_DIV2);
		centro.append("                                    <div class='labelDrc'>");
		centro.append(                                                 convocatoriaBean.getDesMinisterio());            
		centro.append(STRING_DIV2);
		centro.append("                             </div>");
		centro.append("                             <div class='clear'></div>");

		centro.append(STRING_DIV_CLASS_CAPA99);
		centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
		centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.tipoDiscapacidad"));
		centro.append(STRING_DIV);
		centro.append(STRING_DIV_CLASS_LABELDRC);
		if(solicitudBean.getTipoDiscapacidadDes()!=null && !solicitudBean.getTipoDiscapacidadDes().equals(""))
			centro.append(                                                        solicitudBean.getTipoDiscapacidadDes()); 
		centro.append(STRING_DIV);
		centro.append(STRING_DIV2);
		centro.append(STRING_DIV_CLASS_CLEAR_3);
		centro.append(STRING_DIV_CLASS_CAPA99);
		centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
		centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.porcentajeDiscapacidad"));
		centro.append(STRING_DIV);
		centro.append(STRING_DIV_CLASS_LABELDRC);
		centro.append(                                                        solicitudBean.getPorcentajeDiscapacidad());
		centro.append(STRING_DIV6);
		centro.append(STRING_DIV2);
		centro.append(STRING_DIV_CLASS_CLEAR_3);
		centro.append(STRING_DIV_CLASS_CAPA99);
		centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
		centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.reservaDiscapacidad"));
		centro.append(STRING_DIV);
		centro.append(STRING_DIV_CLASS_LABELDRC);
		centro.append(                                                        solicitudBean.getReservaDiscapacidad());
		centro.append("                                          </div>");
		centro.append(STRING_DIV2);
		centro.append(STRING_DIV_CLASS_CLEAR_3);
		centro.append(STRING_DIV_CLASS_CAPA99);
		centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
		centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.detalleDiscapacidad"));
		centro.append(STRING_DIV);
		centro.append(STRING_DIV_CLASS_LABELDRC);
		if(solicitudBean.getDetalleDiscapacidad()!=null && !solicitudBean.getDetalleDiscapacidad().equals(""))
			centro.append(                                                        solicitudBean.getDetalleDiscapacidad());
		centro.append(STRING_DIV6);
		centro.append(STRING_DIV2);
		centro.append(STRING_DIV_CLASS_CLEAR_3);


		SolicitudPropiosQuery solicitudPropiosQuery = new SolicitudPropiosQuery();

		SolicitudComunQuery solicitudComun = new SolicitudComunQuery();
		solicitudComun.setIdSolicitud(solicitudBean.getId());
		solicitudPropiosQuery.setSolicitudComun(solicitudComun);

		if(convocatoriaRepetida_Unico.equals("U")){
			ArrayList<SolicitudPropiosBean> solicitudPropios = solicitudPropioManager.obtenerCamposPropiosByIdSolicitud(solicitudPropiosQuery);


			for (SolicitudPropiosBean solicitudPropiosBean : solicitudPropios) {

				centro.append(STRING_DIV_CLASS_CAPA99_4);
				centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
				centro.append(                                                            solicitudPropiosBean.getCamposPropiosBean().getCampo());
				centro.append(STRING_DIV);
				centro.append(STRING_DIV_CLASS_LABELDRC_4);
				if(solicitudPropiosBean.getValor()!=null)
					centro.append(                                                         solicitudPropiosBean.getValor());        
				centro.append(STRING_DIV6);
				centro.append(STRING_DIV8);
				centro.append("                                   <div class='clear' />      ");           

			} 
		}

		
		// Se obtienen todos los pagos asociados
		SolicitudComunQuery solicitudQueryPago = new SolicitudComunQuery();
		solicitudQueryPago.setIdSolicitud(Long.parseLong(solicitudBean.getId().toString()));	
		PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();	
		pagoSolicitudQuery.setResultado(Constantes.RESULTADO_PAGO_OK);
		pagoSolicitudQuery.setSolicitudComun(solicitudQueryPago);
		ArrayList<DetallePagoSolicitudBean> pagosAsociados = pagoSolicitudManager.buscarDetallePagoSolicitud(pagoSolicitudQuery);
		
		for (DetallePagoSolicitudBean pago : pagosAsociados){
			centro.append("                                    <div id='content_tituloNivel1'>");
			centro.append("                                           <div id='cuerpo_central_tituloNivel1'>");
			centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.datosPago"));
			centro.append("                                           </div> ");
			centro.append(STRING_DIV2);
			centro.append(STRING_DIV_CLASS_CLEAR_DIV);
			centro.append(STRING_DIV_CLASS_CAPA99);
			centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
			centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.formaPago"));
			centro.append(STRING_DIV);
			centro.append(STRING_DIV_CLASS_LABELDRC);
			centro.append(                                                 pago.getTipo());              
			centro.append(STRING_DIV);
			centro.append(STRING_DIV2);
			centro.append(STRING_DIV_CLASS_CLEAR_DIV);
			centro.append(STRING_DIV_CLASS_CAPA99);
			centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
			centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.importe"));
			centro.append(STRING_DIV);
			centro.append(STRING_DIV_CLASS_LABELDRC);
			centro.append(                                                 pago.getImporte());                 
			centro.append(STRING_DIV);
			centro.append(STRING_DIV2);
			centro.append(STRING_DIV_CLASS_CLEAR_DIV);
			centro.append(STRING_DIV_CLASS_CAPA99);
			centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
			centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.fechaPago"));
			centro.append(STRING_DIV);
			centro.append(STRING_DIV_CLASS_LABELDRC);
			centro.append(                                                 pago.getFechaIntento());            
			centro.append(STRING_DIV);
			centro.append(STRING_DIV2);
			centro.append(STRING_DIV_CLASS_CLEAR);
			centro.append(STRING_DIV_CLASS_CAPA99);
			centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
			centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.nrc"));
			centro.append(STRING_DIV);
			centro.append(STRING_DIV_CLASS_LABELDRC);
			if(pago.getNrc()!=null && !pago.getNrc().equals(""))
				centro.append(                                                 pago.getNrc());         
			centro.append(STRING_DIV);
			centro.append(STRING_DIV2);
			centro.append(STRING_DIV_CLASS_CLEAR_DIV);

			centro.append(STRING_DIV_CLASS_CAPA99);
			centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
			centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.solicitaReduccion"));
			centro.append(STRING_DIV);
			centro.append(STRING_DIV_CLASS_LABELDRC);
			centro.append(                                                 pago.getReduccion());
			centro.append(STRING_DIV);
			centro.append(STRING_DIV2);
			centro.append(STRING_DIV_CLASS_CLEAR_3);

			if(convocatoriaRepetida_Unico.equals("U")){           

				if(!pago.getTipo().equals("Exento")){
					if(pago.getReduccion().equals("S")){
						centro.append(STRING_DIV_CLASS_CAPA99);
						centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
						centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.motivoReduccion"));
						centro.append(STRING_DIV);
						centro.append(STRING_DIV_CLASS_LABELDRC);
						centro.append(                                                 pago.getDesMotivoReduccion());
						centro.append(STRING_DIV);
						centro.append(STRING_DIV2);
						centro.append(STRING_DIV_CLASS_CLEAR_3);

						centro.append(STRING_DIV_CLASS_CAPA99);
						centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
						centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.entidadFinanciera"));
						centro.append(STRING_DIV);
						centro.append(STRING_DIV_CLASS_LABELDRC);
						if(pago.getDesEntidad()!=null)          
							centro.append(                                                 pago.getDesEntidad());
						centro.append(STRING_DIV);
						centro.append(STRING_DIV2);
						centro.append(STRING_DIV_CLASS_CLEAR_3);

					}else{
						centro.append(STRING_DIV_CLASS_CAPA99);
						centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
						centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.entidadFinanciera"));
						centro.append(STRING_DIV);
						centro.append(STRING_DIV_CLASS_LABELDRC);
						if(pago.getDesEntidad()!=null)    
							centro.append(                                                 pago.getDesEntidad());
						centro.append(STRING_DIV);
						centro.append(STRING_DIV2);
						centro.append(STRING_DIV_CLASS_CLEAR_3);
					}
				}else{
					centro.append(STRING_DIV_CLASS_CAPA99);
					centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
					centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.motivoReduccion"));
					centro.append(STRING_DIV);
					centro.append(STRING_DIV_CLASS_LABELDRC);
					centro.append(                                                 pago.getDesMotivoReduccion());
					centro.append(STRING_DIV);
					centro.append(STRING_DIV2);
					centro.append(STRING_DIV_CLASS_CLEAR_3);
				}
			} 
		}
		
		     
		centro.append("                                     <div class='clear'></div>");
		centro.append("                                     <div id='content_tituloNivel1'>");
		centro.append("                                            <div id='cuerpo_central_tituloNivel1'>");
		centro.append(                                                      RESOURCE_BUNDLE.getString("field.extracto.documentosAnexos"));
		centro.append(STRING_DIV9); 
		centro.append(STRING_DIV10);
		centro.append("                                     <div class='clear'></div>");
		centro.append("                                     <div>");

		DocumentoQuery documentoQuery= new DocumentoQuery();
		if(convocatoriaRepetida_Unico.equals("U")){   
			documentoQuery.addSolicitudIn(Long.parseLong(detallePagoSolicitudBean.getIdSolicitud()));
		}
		else{
			documentoQuery.addSolicitudIn(solicitudBean.getId());
		}
		Integer tpDocumento = Constantes.TIPO_DOCUMENTO_ANEXO_SOLICITUD;
		documentoQuery.addTipoDocumentoIn(tpDocumento);
		tpDocumento = Constantes.TIPO_DOCUMENTO_ADJUNTO;
		documentoQuery.addTipoDocumentoIn(tpDocumento);
		tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_TASA;
		documentoQuery.addTipoDocumentoIn(tpDocumento);
		tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_DISCAPACIDAD;
		documentoQuery.addTipoDocumentoIn(tpDocumento);

		if(convocatoriaRepetida_Unico.equals("U")){ 
			List<DocumentoBean> documentosList;
			documentosList=documentoConvocatoriasManager.buscarDocumentosConvocatoria(documentoQuery); 
			for (DocumentoBean documentoBean : documentosList) {

				centro.append(STRING_DIV_CLASS_CAPA99_5);
				centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO_3);
				centro.append(                                                      RESOURCE_BUNDLE.getString("field.extracto.extractoDocumento"));
				centro.append(STRING_DIV9);
				centro.append(STRING_DIV_CLASS_LABELDRC_5);
				centro.append(                                                  documentoBean.getDsTipoDocumento());
				centro.append(STRING_DIV9);
				centro.append(STRING_DIV10);
				centro.append(STRING_DIV_CLASS_CLEAR_4);

				centro.append(STRING_DIV_CLASS_CAPA99_5);
				centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO_3);
				centro.append(                                                      RESOURCE_BUNDLE.getString("field.extracto.documento"));
				centro.append(STRING_DIV9);
				centro.append(STRING_DIV_CLASS_LABELDRC_5);
				centro.append(                                                  documentoBean.getNombre()+" - "+ documentoBean.getDescripcion());
				centro.append(STRING_DIV9);
				centro.append(STRING_DIV10);
				centro.append(STRING_DIV_CLASS_CLEAR_4);           

				centro.append(STRING_DIV_CLASS_CAPA99_5);
				centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO_3);
				centro.append(                                                      RESOURCE_BUNDLE.getString("field.extracto.hashDocumento"));
				centro.append(STRING_DIV9);
				centro.append(STRING_DIV_CLASS_LABELDRC_5);
				centro.append(                                                  documentoBean.getHashExtracto());
				centro.append(STRING_DIV9);
				centro.append(STRING_DIV10);
				centro.append(STRING_DIV_CLASS_CLEAR_4); 

			}      
		}

		centro.append(STRING_DIV11);
		centro.append("<div class='clear'></div>");                              

		centro.append(STRING_DIV11);         
		centro.append(STRING_DIV11);

		String pie = "<br><br></body></html>";

		pagina.append(cabecera);
		pagina.append(centro);
		pagina.append(pie);

		return pagina.toString();

	}

	/**
	 * Codificar cadena ajax.
	 *
	 * @param cadena el cadena
	 * @return el string
	 */
	public String codificarCadenaAjax(String cadena){
		String resultado = null;

		if(cadena!=null){
			try {
				resultado = new String(cadena.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				logger.error("Error codificarCadena ajax: ",e);
			}
		}

		return resultado;
	}

	/**
	 * Obtiene el registro solicitudes manager.
	 *
	 * @return el registro solicitudes manager
	 */
	public RegistroSolicitudManager getRegistroSolicitudesManager() {
		return registroSolicitudesManager;
	}

	/**
	 * Establece el registro solicitudes manager.
	 *
	 * @param registroSolicitudesManager el nuevo registro solicitudes manager
	 */
	public void setRegistroSolicitudesManager(
			RegistroSolicitudManager registroSolicitudesManager) {
		this.registroSolicitudesManager = registroSolicitudesManager;
	}

	/**
	 * Obtiene el solicitudes manager.
	 *
	 * @return el solicitudes manager
	 */
	public SolicitudManager getSolicitudesManager() {
		return solicitudesManager;
	}

	/**
	 * Establece el solicitudes manager.
	 *
	 * @param solicitudesManager el nuevo solicitudes manager
	 */
	public void setSolicitudesManager(SolicitudManager solicitudesManager) {
		this.solicitudesManager = solicitudesManager;
	}

	/**
	 * Obtiene el tabla base manager.
	 *
	 * @return el tabla base manager
	 */
	public TablaBaseManager getTablaBaseManager() {
		return tablaBaseManager;
	}

	/**
	 * Establece el tabla base manager.
	 *
	 * @param tablaBaseManager el nuevo tabla base manager
	 */
	public void setTablaBaseManager(TablaBaseManager tablaBaseManager) {
		this.tablaBaseManager = tablaBaseManager;
	}

	/**
	 * Obtiene el documento convocatorias manager.
	 *
	 * @return el documento convocatorias manager
	 */
	public DocumentosConvocatoriaManager getDocumentoConvocatoriasManager() {
		return documentoConvocatoriasManager;
	}

	/**
	 * Establece el documento convocatorias manager.
	 *
	 * @param documentoConvocatoriasManager el nuevo documento convocatorias manager
	 */
	public void setDocumentoConvocatoriasManager(
			DocumentosConvocatoriaManager documentoConvocatoriasManager) {
		this.documentoConvocatoriasManager = documentoConvocatoriasManager;
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
	 * Obtiene el parametro configuracion manager.
	 *
	 * @return el parametro configuracion manager
	 */
	public ParametroConfiguracionManager getParametroConfiguracionManager() {
		return parametroConfiguracionManager;
	}

	/**
	 * Establece el parametro configuracion manager.
	 *
	 * @param parametroConfiguracionManager el nuevo parametro configuracion manager
	 */
	public void setParametroConfiguracionManager(
			ParametroConfiguracionManager parametroConfiguracionManager) {
		this.parametroConfiguracionManager = parametroConfiguracionManager;
	}

	/**
	 * Obtiene el log servicios manager.
	 *
	 * @return el log servicios manager
	 */
	public LogServiciosManager getLogServiciosManager() {
		return logServiciosManager;
	}

	/**
	 * Establece el log servicios manager.
	 *
	 * @param logServiciosManager el nuevo log servicios manager
	 */
	public void setLogServiciosManager(LogServiciosManager logServiciosManager) {
		this.logServiciosManager = logServiciosManager;
	}

	/**
	 * Obtiene el convocatoria manager.
	 *
	 * @return el convocatoria manager
	 */
	public ConvocatoriasManager getConvocatoriaManager() {
		return convocatoriaManager;
	}

	/**
	 * Establece el convocatoria manager.
	 *
	 * @param convocatoriaManager el nuevo convocatoria manager
	 */
	public void setConvocatoriaManager(ConvocatoriasManager convocatoriaManager) {
		this.convocatoriaManager = convocatoriaManager;
	}	

	/**
	 * Obtiene el pago solicitud manager.
	 *
	 * @return el pago solicitud manager
	 */
	public PagoSolicitudManager getPagoSolicitudManager() {
		return pagoSolicitudManager;
	}

	/**
	 * Establece el pago solicitud manager.
	 *
	 * @param pagoSolicitudManager el nuevo pago solicitud manager
	 */
	public void setPagoSolicitudManager(PagoSolicitudManager pagoSolicitudManager) {
		this.pagoSolicitudManager = pagoSolicitudManager;
	}

	/**
	 * Obtiene el solicitud propio manager.
	 *
	 * @return el solicitud propio manager
	 */
	public SolicitudPropioManager getSolicitudPropioManager() {
		return solicitudPropioManager;
	}

	/**
	 * Establece el solicitud propio manager.
	 *
	 * @param solicitudPropioManager el nuevo solicitud propio manager
	 */
	public void setSolicitudPropioManager(
			SolicitudPropioManager solicitudPropioManager) {
		this.solicitudPropioManager = solicitudPropioManager;
	}

	/**
	 * Obtiene el registro auditoria manager.
	 *
	 * @return el registro auditoria manager
	 */
	public RegistroAuditoriaManager getRegistroAuditoriaManager() {
		return registroAuditoriaManager;
	}

	/**
	 * Establece el registro auditoria manager.
	 *
	 * @param registroAuditoriaManager el nuevo registro auditoria manager
	 */
	public void setRegistroAuditoriaManager(
			RegistroAuditoriaManager registroAuditoriaManager) {
		this.registroAuditoriaManager = registroAuditoriaManager;
	}

	/**
	 * Obtiene el campos propios manager.
	 *
	 * @return el campos propios manager
	 */
	public CamposPropiosManager getCamposPropiosManager() {
		return camposPropiosManager;
	}

	
	/**
	 * Establece el campos propios manager.
	 *
	 * @param camposPropiosManager el nuevo campos propios manager
	 */
	public void setCamposPropiosManager(CamposPropiosManager camposPropiosManager) {
		this.camposPropiosManager = camposPropiosManager;
	}
	
	
	
}
