package es.map.ipsg.action.solicitudPresencial;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.axis.attachments.OctetStream;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.spring.Globals;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.lowagie.text.pdf.PdfCopyFields;
import com.lowagie.text.pdf.PdfReader;

import es.gob.afirma.afirma5ServiceInvoker.Afirma5ServiceInvokerFacade;
import es.gob.afirma.signature.SignatureConstants;
import es.gob.afirma.signature.Signer;
import es.gob.afirma.signature.SignersFactory;
import es.gob.afirma.signature.SigningException;
import es.gob.afirma.transformers.TransformersConstants;
import es.gob.afirma.transformers.TransformersFacade;
import es.gob.afirma.utils.Base64Coder;
import es.gob.afirma.utils.DSSConstants.DSSTagsRequest;
import es.gob.afirma.utils.GeneralConstants;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CamposPropiosQuery;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.EntidadFinanciera;
import es.map.ips.model.Especialidad;
import es.map.ips.model.EstadoSolicitud;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ips.model.MotivoReduccionTasa;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.Pais;
import es.map.ips.model.PaisQuery;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ips.model.Provincia;
import es.map.ips.model.ProvinciaExamen;
import es.map.ips.model.ProvinciaQuery;
import es.map.ips.model.RegistroSolicitudQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.SolicitudPropiosQuery;
import es.map.ips.model.TipoDiscapacidad;
import es.map.ips.model.TipoDocumento;
import es.map.ips.model.TipoDocumentoQuery;
import es.map.ips.model.TipoSolicitud;
import es.map.ips.model.TituloOficial;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.CiudadanoBean;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.CorreoElectronicoBean;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.DocumentoSolicitudPresencialBean;
import es.map.ipsg.bean.GenerarJustificanteBean;
import es.map.ipsg.bean.JustificanteBean;
import es.map.ipsg.bean.LogServicioBean;
import es.map.ipsg.bean.LogSolicitudBean;
import es.map.ipsg.bean.MotivoReduccionTasaBean;
import es.map.ipsg.bean.PagoSolicitudBean;
import es.map.ipsg.bean.PaisBean;
import es.map.ipsg.bean.ParametrosConfiguracionBean;
import es.map.ipsg.bean.ProvinciaBean;
import es.map.ipsg.bean.RegistroSolicitudBean;
import es.map.ipsg.bean.RegistroSolicitudJustificanteBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudCcaaBean;
import es.map.ipsg.bean.SolicitudPropiosBean;
import es.map.ipsg.bean.TipoDocumentoBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.clienteWS.registroRec3.RegistrarServiceLocator;
import es.map.ipsg.clienteWS.registroRec3.RegistrarServiceSoapBindingStub;
import es.map.ipsg.clienteWS.registroRec3.RegistroType;
import es.map.ipsg.clienteWS.registroRec3.type.AutenticacionType;
import es.map.ipsg.clienteWS.registroRec3.type.DocumentoType;
import es.map.ipsg.clienteWS.registroRec3.type.InteresadoType;
import es.map.ipsg.clienteWS.registroRec3.type.JustificanteType;
import es.map.ipsg.clienteWS.registroRec3.type.RespuestaType;
import es.map.ipsg.form.AltaSolicitudPresencialForm;
import es.map.ipsg.manager.CamposPropiosManager;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.LogServicioManager;
import es.map.ipsg.manager.LogSolicitudManager;
import es.map.ipsg.manager.MotivoReduccionTasaManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.PaisManager;
import es.map.ipsg.manager.ParametroConfiguracionManager;
import es.map.ipsg.manager.ProvinciaManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudCcaaManager;
import es.map.ipsg.manager.SolicitudPresencialManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.SolicitudesPropiosManager;
import es.map.ipsg.manager.TipoDocumentoManager;
import es.map.ipsg.res.ResourceLocator;
import es.map.ipsg.util.CSVInside;
import es.map.ipsg.util.CSVStorage;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.EnvioMails;
import es.map.ipsg.util.IpsUtils;
import es.map.ipsg.util.Jasper;
import es.map.ipsg.util.SHA0;
import es.map.ipsg.util.SistemaFicheros;
import es.map.ipsg.util.Util;
import es.map.ipsg.util.UtilesIPSG;
import es.mpt.dsic.inside.ws.service.EeUtilService;
import es.mpt.dsic.inside.ws.service.EeUtilServiceProxy;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Accion RegistroAltaSolicitudPresencialAction.
 *
 * @author mlopgarc
 */
public class RegistroAltaSolicitudPresencialSpring extends AbstractSpring {

	/** el solicitud presencial manager. */
	private SolicitudPresencialManager solicitudPresencialManager;
	
	/** el solicitud propios manager. */
	private SolicitudesPropiosManager solicitudPropiosManager;
	
	/** el solicitud ccaa manager. */
	private SolicitudCcaaManager solicitudCcaaManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el registro solicitud manager. */
	private RegistroSolicitudManager  registroSolicitudManager;
	
	/** el campos propios manager. */
	private CamposPropiosManager camposPropiosManager;
	
	/** el motivo reduccion tasa manager. */
	private MotivoReduccionTasaManager motivoReduccionTasaManager;
	
	/** el log solicitud manager. */
	private LogSolicitudManager logSolicitudManager;
	
	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** el parametro configuracion manager. */
	private ParametroConfiguracionManager parametroConfiguracionManager;
	
	/** el tipo documento manager. */
	private TipoDocumentoManager tipoDocumentoManager;
	
	/** el pais manager. */
	private PaisManager paisManager;
	
	/** el provincia manager. */
	private ProvinciaManager provinciaManager;
	
	/** el solicitudes manager. */
	private SolicitudesManager solicitudesManager;
	
	/** el solicitudes propios manager. */
	private SolicitudesPropiosManager solicitudesPropiosManager;
	
	/** el log servicio manager. */
	private LogServicioManager logServicioManager;
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(RegistroAltaSolicitudPresencialSpring.class);
	
	/** La constante TIMEOUT. */
	private static final int TIMEOUT = 30000;
	
	/** el properties. */
	private static Properties properties;
	
	/** La constante STRING_CDASUNTO. */
	private static final String STRING_CDASUNTO = "cdAsunto";
	
	/** La constante STRING_HTML. */
	private static final String STRING_HTML = ".html";
	
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
	
	/** La constante STRING_ERROR_HASHFILE. */
	private static final String STRING_ERROR_HASHFILE = "Error hashFile";
	
	/** La constante STRING_ERRORDESCRIPCION. */
	private static final String STRING_ERRORDESCRIPCION = "errorDescripcion";
	
	/** La constante STRING_FIELD_SOLICITUD_REGISTRO_ERRORREALIZACION. */
	private static final String STRING_FIELD_SOLICITUD_REGISTRO_ERRORREALIZACION = "field.solicitud.registro.errorRealizacion";
	
	/** La constante STRING_MENSAJEREGISTRO. */
	private static final String STRING_MENSAJEREGISTRO = "mensajeRegistro";
	
	/** La constante STRING_SIMPLEDATEFORMAT. */
	private static final String STRING_SIMPLEDATEFORMAT = "dd/MM/yyyy HH:mm:ss";
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/** La constante STRING_NOSUCCESS. */
	private static final String STRING_NOSUCCESS = "nosuccess";
	
	/** La constante STRING_REGISTRO. */
	private static final String STRING_REGISTRO = "registro";
	
	/** La constante STRING_CERTIFICADOS_ERROR_GESTORDOCUMENTAL. */
	private static final String STRING_CERTIFICADOS_ERROR_GESTORDOCUMENTAL = "certificados.error.gestorDocumental";
	
	/** La constante STRING_CORRECTO. */
	private static final String STRING_CORRECTO = "correcto";
	
	/** La constante STRING_SIMPLEDATEFORMAT_HHMMSS. */
	private static final String STRING_SIMPLEDATEFORMAT_HHMMSS = "dd/MM/yyyy HH:mm:ss";
	
	/**
	 * Acción AltaSolicitudPresencialAction.
	 */
	public RegistroAltaSolicitudPresencialSpring() {
		try {
			setSolicitudPresencialManager((SolicitudPresencialManager) getBean("solicitudPresencialManager"));
			setSolicitudCcaaManager((SolicitudCcaaManager) getBean("solicitudCcaaManager"));
			setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
			setLogSolicitudManager((LogSolicitudManager) getBean("logSolicitudManager"));
			setRegistroSolicitudManager((RegistroSolicitudManager) getBean("registroSolicitudManager"));
			setSolicitudPropiosManager((SolicitudesPropiosManager)getBean("solicitudesPropiosManager"));
			setCamposPropiosManager((CamposPropiosManager)getBean("camposPropiosManager"));
			setMotivoReduccionTasaManager((MotivoReduccionTasaManager)getBean("motivoReduccionTasaManager"));
			setConvocatoriasManager((ConvocatoriasManager)getBean("convocatoriaManager"));
			setDocumentoManager((DocumentoManager)getBean("documentoManager"));
			setParametroConfiguracionManager((ParametroConfiguracionManager)getBean("parametroConfiguracionManager"));
			setPaisManager((PaisManager)getBean("paisManager"));
			setTipoDocumentoManager((TipoDocumentoManager) getBean("tipoDocumentoManager"));
			setProvinciaManager((ProvinciaManager)getBean("provinciaManager"));
			setSolicitudesManager((SolicitudesManager) getBean("solicitudesManager"));
			setSolicitudesPropiosManager((SolicitudesPropiosManager) getBean("solicitudesPropiosManager"));
			setLogServicioManager((LogServicioManager)getBean("logServicioManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.error("Error AltaSolicitudPresencialSpring ", e);
		}
	}

	/**
	 * Método doExecute de AltaSolicitudPresencialAction.
	 *
	 * @param form SpringForm Pasa los campos del formulario
	 * @return resultado String Si todo va bien devuelve success
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_solPresencial = RESOURCE_BUNDLE.getString("field.menu.solicPresenciales");
		this.getRequest().getSession().setAttribute("pagActiva", menu_solPresencial);
		String subMenu_SolPresen = RESOURCE_BUNDLE.getString("field.menuLateral.solicitudesPresenciales.alta");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_SolPresen);
		String resultado = "";

	try{
		
		AltaSolicitudPresencialForm formulario = (AltaSolicitudPresencialForm) form;
		//extraemos los datos de los campos propios introducidos para modificar los campos propios de la solicitud
		CamposPropiosBean[] listaCamposPropiosBean = formulario.getListaTextAreas();
		ArrayList<String> listaCamposAreaText = new ArrayList<String>();
		if(listaCamposPropiosBean!=null){
			for(CamposPropiosBean campoPropio : listaCamposPropiosBean){
				Long idCampo = campoPropio.getId();
				listaCamposAreaText.add(idCampo+"_"+campoPropio.getCampo());//formato de la lista: idCampo_ValorCampo
			}
		}
		SolicitudBean solicitudBean = new SolicitudBean();
		SolicitudCcaaBean solicitudCcaaBean = new SolicitudCcaaBean();
		Provincia provincia = new Provincia();
		ProvinciaExamen provinciaExamen;
		UtilesIPSG utilesIPSG = new UtilesIPSG();
		
		//Comprobar si el usuario esta en la sesion
		//Variable para pruebas de estress
		String convocatoriaRepetida_Unico = properties.getProperty("altaSolicitud.inscripcionMultiple_Unica");
		CiudadanoBean usuActual = null;
		if(convocatoriaRepetida_Unico.equals("U")){
			usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute("usuarioClave");
		}else{
			usuActual = new CiudadanoBean();
			usuActual.setNif("12345678Z");
		}	
		
		logger.info("-------Entramos en AltaSolicitud Presencial");

		Date dHoy = new Date();
		//Campos Fijos:
		//Tipo: Presencial
		TipoSolicitud tipoSolicitud = new TipoSolicitud();
		tipoSolicitud.setId(Constantes.TIPO_SOLICITUD_PRESENCIAL);
		solicitudBean.setTipo(tipoSolicitud);
		//Estado: Registrada
		EstadoSolicitud estadoSolicitud = new EstadoSolicitud();
		estadoSolicitud.setId(Constantes.ESTADO_SOLICITUD_REGISTRADO);
		solicitudBean.setEstadoSolicitud(estadoSolicitud);
		//IDModelo: Recuperado de los campos propios
		if (listaCamposPropiosBean != null && listaCamposPropiosBean[0] != null && !StringUtils.isEmpty(listaCamposPropiosBean[0].getIdModelo())) {
			solicitudBean.setIdModelo(listaCamposPropiosBean[0].getIdModelo());
		}
		//Fecha Última Actualización: Hoy
		solicitudBean.setFechaUtlActualizacion(dHoy);
		
		//Campos del FORMULARIO
		//Se recuperan todos los datos del formulario
		//Datos de Convocatoria
		solicitudBean.setNumeroSolicitud(formulario.getNumeroSolicitud());
		
		if(!StringUtils.isEmpty(formulario.getIdConvocatoria())){
			Convocatoria convocatoria = new Convocatoria();
			convocatoria.setId(Long.valueOf(formulario.getIdConvocatoria()));
			solicitudBean.setConvocatoria(convocatoria);
			solicitudBean.setIdConvocatoria(Long.valueOf(formulario.getIdConvocatoria()));
		}
		//Datos de Usuario
		String[] result = Util.comprobarAppellidoCompuesto(formulario.getNombre(),formulario.getPrimerApellido());
		solicitudBean.setNif(formulario.getNif());
		solicitudBean.setNombre(result[0]);
		solicitudBean.setPrimerApellido(result[1]);
		solicitudBean.setSegundoApellido(formulario.getSegundoApellido());
		solicitudBean.setIdConsentimiento(formulario.getCkConsentimiento());
		solicitudBean.setFechaNacimiento(utilesIPSG.stringToDate(formulario.getFechaNacimiento()));
		solicitudBean.setLocalidadNacimiento(formulario.getLocalidadNacimiento());
		solicitudBean.setNacionalidad(formulario.getNacionalidad());
		solicitudBean.setCodigoPostalDomicilio(formulario.getCodigoPostal());
		
		if(formulario.getIdProvinciaNacimiento() != null && !formulario.getIdProvinciaNacimiento().equals("")){
			
			provincia.setId(Integer.valueOf(formulario.getIdProvinciaNacimiento()));
			solicitudBean.setProvinciaByIdProvinciaNacimiento(provincia);
		}
		
		solicitudBean.setSexo(utilesIPSG.stringToCharPos(formulario.getSexo(), 0));
		solicitudBean.setEmail(formulario.getEmail());
		//Datos Domicilio
		solicitudBean.setCalleDomicilio(formulario.getCalleDomicilio());
		solicitudBean.setMunicipioDomicilio(formulario.getMunicipioDomicilio());
		solicitudBean.setCodigoPostalDomicilio(formulario.getCodigoPostal());
		
		if(formulario.getIdPais() != null && !formulario.getIdPais().equals("")){				
			PaisQuery paisQuery = new PaisQuery();
			paisQuery.setId(Integer.valueOf(formulario.getIdPais()));
			PaisBean paisBean = paisManager.buscarPaisUnique(paisQuery);
			
			Pais pais = new Pais();
			pais.setId(Integer.valueOf(formulario.getIdPais()));
			pais.setCodigo(paisBean.getCodigo());
			solicitudBean.setPais(pais);
		}
		
		if(formulario.getIdProvinciaDomicilio() != null && !formulario.getIdProvinciaDomicilio().equals("")){
			ProvinciaQuery provinciaQuery = new ProvinciaQuery();
			provinciaQuery.setId(Integer.valueOf(formulario.getIdProvinciaDomicilio()));
			ProvinciaBean provinciaBean = provinciaManager.buscarProvinciaUnique(provinciaQuery);
			
			provincia = new Provincia();
			provincia.setId(Integer.valueOf(formulario.getIdProvinciaDomicilio()));
			provincia.setCodigo(provinciaBean.getCodigo());
			solicitudBean.setProvinciaByIdProvinciaDomicilio(provincia);
		}
		
		String telefono = null;
		if(formulario.getTelefono1()!=null && !formulario.getTelefono1().trim().equals("")){
			telefono = formulario.getTelefono1();
		}
		
		if(formulario.getTelefono2()!=null && !formulario.getTelefono2().trim().equals("")){
			if(telefono==null){
				//Telefono 1 no se ha informado
				telefono = formulario.getTelefono2(); 
			}else{
				//Telefono 1 se ha informado
				telefono = telefono+"-"+formulario.getTelefono2();
			}
		}
		solicitudBean.setTelefono(telefono);
		
		//Datos Solicitud
		if(formulario.getIdEspecialidad() != null && !formulario.getIdEspecialidad().equals("")){
			Especialidad especialidad = new Especialidad();
			especialidad.setId(Integer.valueOf(formulario.getIdEspecialidad()));
			solicitudBean.setEspecialidad(especialidad);
		}
		
		if(formulario.getIdProvinciaExamen() != null && !formulario.getIdProvinciaExamen().equals("")){
			provinciaExamen = new ProvinciaExamen();
			provinciaExamen.setId(Integer.valueOf(formulario.getIdProvinciaExamen()));
			solicitudBean.setProvinciaByIdProvinciaExamen(provinciaExamen);
		}
					
			SimpleDateFormat sdf = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
			String dHoyString = sdf.format(dHoy);
			solicitudBean.setFechaSolicitud(dHoyString);
		
		solicitudBean.setEjercicio(formulario.getEjercicio());
		
		if(formulario.getIdTipoDiscapacidad() != null && !formulario.getIdTipoDiscapacidad().equals("")){
			TipoDiscapacidad tipoDiscapacidad = new TipoDiscapacidad();
			tipoDiscapacidad.setId(Integer.valueOf(formulario.getIdTipoDiscapacidad()));
			solicitudBean.setTipoDiscapacidad(tipoDiscapacidad);
		}
		
		if(formulario.getPorcentajeDiscapacidad() != null && !formulario.getPorcentajeDiscapacidad().equals("")){
			solicitudBean.setPorcentajeDiscapacidad(Integer.valueOf(formulario.getPorcentajeDiscapacidad()));
		}else{
			solicitudBean.setPorcentajeDiscapacidad(0);
		}
		
		if(formulario.getCkReservaDiscapacidad() !=null && formulario.isCkReservaDiscapacidad()){
			solicitudBean.setReservaDiscapacidad('S'); 
		}else{
			solicitudBean.setReservaDiscapacidad('N');
		}
		
		solicitudBean.setDetalleDiscapacidad(formulario.getAdaptacionDiscapacidad());
		if(formulario.getIdTitulo() != null && !formulario.getIdTitulo().equals("")){
			TituloOficial tituloOficial = new TituloOficial();
			tituloOficial.setId(Integer.valueOf(formulario.getIdTitulo()));
			solicitudBean.setTituloOficial(tituloOficial);
		}
		
		solicitudBean.setOtrosTitulos(formulario.getOtrosTitulos());
		
		//Datos a Consignar
		solicitudBean.setDatosA(formulario.getDatosA());
		solicitudBean.setDatosB(formulario.getDatosB());
		solicitudBean.setDatosC(formulario.getDatosC());
		
		//Datos de Pago
		PagoSolicitudBean pagoSolicitudBean = new PagoSolicitudBean();
	
		if(formulario.getIdTipoPago() != null){
			pagoSolicitudBean.setTipo(utilesIPSG.stringToCharPos(formulario.getIdTipoPago(), 0));
		}
		
		//Si no ha informado la fecha de Pago, se le incluye el día de Hoy.
		if(formulario.getFechaPago() != null && !formulario.getFechaPago().equals("")){
			pagoSolicitudBean.setFechaIntento(utilesIPSG.stringToDate(formulario.getFechaPago()));
		}else				
			pagoSolicitudBean.setFechaIntento(new Date());
		
		if(formulario.getImporte() != null && !formulario.getImporte().equals("")){
			String importe = null;
			importe = formulario.getImporte();
			if(formulario.getImporteDecimal() != null && !formulario.getImporteDecimal().equals("")){
				importe = importe  + "." + formulario.getImporteDecimal();
				pagoSolicitudBean.setImporte(Float.parseFloat(importe));
			}
		}
        // Obtenemos el identificador de los motivos de exención de tasa
		if(formulario.getIdMotivosEx() != null && !formulario.getIdMotivosEx().equals("")){
            MotivoReduccionTasa motivoReduccionTasa;
			MotivoReduccionTasaBean motivoReduccionTasaBean;
			motivoReduccionTasaBean = motivoReduccionTasaManager.obtenerMotivoReduccionTasa(Integer.valueOf(formulario.getIdMotivosEx()));
			motivoReduccionTasa=motivoReduccionTasaManager.toMotivoReduccionTasa(motivoReduccionTasaBean);
			pagoSolicitudBean.setMotivoReduccionTasa(motivoReduccionTasa);				
		}
		
        // Obtenemos el identificador de los motivos de reducción
		if(formulario.getIdMotivosRed() != null && !formulario.getIdMotivosRed().equals("")){
            MotivoReduccionTasa motivoReduccionTasa;
			MotivoReduccionTasaBean motivoReduccionTasaBean;
			motivoReduccionTasaBean = motivoReduccionTasaManager.obtenerMotivoReduccionTasa(Integer.valueOf(formulario.getIdMotivosRed()));
			motivoReduccionTasa=motivoReduccionTasaManager.toMotivoReduccionTasa(motivoReduccionTasaBean);
			pagoSolicitudBean.setMotivoReduccionTasa(motivoReduccionTasa);			
		}
																
		if(formulario.getIdEntidadBancaria() != null && !formulario.getIdEntidadBancaria().equals("")){
			EntidadFinanciera entidadFinanciera = new EntidadFinanciera();
			entidadFinanciera.setId(Integer.valueOf(formulario.getIdEntidadBancaria()));
			pagoSolicitudBean.setEntidadFinanciera(entidadFinanciera);
		}
			
		pagoSolicitudBean.setNrc(formulario.getNrcPago());
		ArrayList<PagoSolicitudBean> arrPagoSolicitudBean = new ArrayList<PagoSolicitudBean>();
		arrPagoSolicitudBean.add(pagoSolicitudBean);
		solicitudBean.setPagoSolicitudes(arrPagoSolicitudBean);
		
		// Guardamos los datos de la Comunidad Autónoma 
		
		// Comunidad Autónoma debido a discapacidad
		if(formulario.getComunidadDD() !=null && !formulario.getComunidadDD().equals("")){
			solicitudCcaaBean.setIdComunidadDD(formulario.getComunidadDD());
			if(formulario.getIdProvinciaDomicilio()!=null && !formulario.getIdProvinciaDomicilio().equals("")){
				provincia = new Provincia();
				provincia.setId(Integer.valueOf(formulario.getIdProvinciaDomicilio()));
				solicitudCcaaBean.setProvincia(provincia);
			}
		}
		//Comunidad Autónoma Debido a Familia Numerosa
		if(formulario.getComunidadFN() !=null && !formulario.getComunidadFN().equals("")){
			solicitudCcaaBean.setIdComunidadFN(formulario.getComunidadFN());
		}
		// Número de Título de Familia Numerosa
		if(formulario.getNumeroTituloFN() !=null && !formulario.getNumeroTituloFN().equals("")){
			solicitudCcaaBean.setTituloFamnumerosa(formulario.getNumeroTituloFN());
		}
		//Datos de Registro
		RegistroSolicitudBean registroSolicitudBean = new RegistroSolicitudBean();
		registroSolicitudBean.setFechaIntento(utilesIPSG.stringToDate(formulario.getFechaRegistro()));
		if(formulario.getNumeroRegistro() != null && !formulario.getNumeroRegistro().equals("")){	
			registroSolicitudBean.setNumeroRegistro(formulario.getNumeroRegistro());
		}else{
			// si no viene informado el numero de registro,se coge por defecto
			registroSolicitudBean.setNumeroRegistro(properties.getProperty("numeroRegistro"));
		}
		
		registroSolicitudBean.setFechaRegistro(utilesIPSG.stringToDate(formulario.getFechaRegistro()));
		registroSolicitudBean.setSolicitante(Constantes.CIUDADANO);
		
		if(formulario.getOficinaRegistro() != null && !formulario.getOficinaRegistro().equals("")){	
			registroSolicitudBean.setOficinaRegistro(formulario.getOficinaRegistro());
		}else{
			// si la oficina de registro no viene informada, se coge por defecto
			registroSolicitudBean.setOficinaRegistro(properties.getProperty("cdOrOrigen"));
		}	
		
		ArrayList<RegistroSolicitudBean> arrRegistroSolicitudes = new ArrayList<RegistroSolicitudBean>();
		arrRegistroSolicitudes.add(registroSolicitudBean);
		solicitudBean.setRegistroSolicitudes(arrRegistroSolicitudes);
		solicitudBean.setEdadVerificada(Constantes.EDAD_NO_COMPROBADA);
		solicitudBean.setFechaNacimientoVerificada(Constantes.EDAD_NO_COMPROBADA);
		solicitudBean.setTituloVerificado(Constantes.EDAD_NO_COMPROBADA);
		
		solicitudBean.setDesempleoVerificado(Constantes.EDAD_NO_COMPROBADA);
		solicitudBean.setFnumerosaVerificado(Constantes.EDAD_NO_COMPROBADA);
		solicitudBean.setDiscapacidadVerificado(Constantes.EDAD_NO_COMPROBADA);
		
		String mensaje;
		
		//Comprobamos que la solicitud no existe
		boolean existeSolicitud = solicitudPresencialManager.existeSolicitudPresencial(solicitudBean);
		
		if(existeSolicitud){
			mensaje = RESOURCE_BUNDLE.getString("field.solicitudPresencial.mensaje.altaPresencialSolicitudExiste");
			String titulo = RESOURCE_BUNDLE.getString("field.solicitudPresencial.tituloAlta");
			setRequestAttribute("titulo", titulo);
			setRequestAttribute("mensaje", mensaje);
			setRequestAttribute("accion", "/spring/verCrearSolicitudPresencial");
			logger.info("La solicitud presencial ya existe.");

			this.setRequestAttribute(STRING_MENSAJEREGISTRO, mensaje);
			resultado = STRING_ERROR;
			return resultado;
		}else{
			Integer idSolicitud = 0;
			
			
			
			
			
			//Se guarda la Solicitud
			if (solicitudBean != null && solicitudBean.getConvocatoria() != null && solicitudBean.getConvocatoria().getId() != null) {
				idSolicitud = solicitudPresencialManager.guardarSolicitudPresencial(solicitudBean);
			}
			
			
			//Se dan de alta en Pago_Solicitud y Registro Solicitud
			if(idSolicitud != null && !idSolicitud.equals(0))
			{
				SolicitudComun solicitud = new SolicitudComun();
				solicitud.setIdSolicitud(idSolicitud.longValue());
				UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
				generarRegistroLogSolicitud(formulario,usuarioBean.getLogin(), idSolicitud.longValue() );

				// 	PAGO_SOLICITUD
				if(formulario.getIdTipoPago() != null && !formulario.getIdTipoPago().equals("") 							
						&& (formulario.getIdTipoPago().equals(String.valueOf(Constantes.TIPO_PAGO_EFECTIVO_INT)))
						&& formulario.getFechaPago() != null && !formulario.getFechaPago().equals("")
						&& formulario.getImporte() != null && !formulario.getImporte().equals("")){
					pagoSolicitudBean.setSolicitud(solicitud);
					pagoSolicitudBean.setCodEntidadFinanciera(formulario.getIdEntidadBancaria());
					pagoSolicitudBean.setResultado(Constantes.RESULTADO_OK);
					pagoSolicitudManager.guardarPagoSolicitudBean(pagoSolicitudBean);
				}else if(formulario.getIdTipoPago() != null && !formulario.getIdTipoPago().equals("") 							
				&& formulario.getIdTipoPago().equals(String.valueOf(Constantes.TIPO_PAGO_EXENTO_INT))
				&& formulario.getImporte() != null && !formulario.getImporte().equals("")){
					pagoSolicitudBean.setSolicitud(solicitud);
					pagoSolicitudBean.setResultado(Constantes.RESULTADO_OK);
					pagoSolicitudManager.guardarPagoSolicitudBean(pagoSolicitudBean);
				}else if(formulario.getIdTipoPago() != null && !formulario.getIdTipoPago().equals("")
				&& formulario.getFechaPago() != null && !formulario.getFechaPago().equals("")
 				&& formulario.getImporte() != null && !formulario.getImporte().equals("")
				&& formulario.getIdEntidadBancaria() != null && !formulario.getIdEntidadBancaria().equals("")){
					pagoSolicitudBean.setSolicitud(solicitud);
					pagoSolicitudBean.setCodEntidadFinanciera(formulario.getIdEntidadBancaria());
					pagoSolicitudBean.setResultado(Constantes.RESULTADO_OK);
					pagoSolicitudManager.guardarPagoSolicitudBean(pagoSolicitudBean);
				}
				
				// COMUNIDAD AUTÓNOMA
				
				solicitudCcaaBean.setIdSolicitud(idSolicitud.longValue());
				solicitudCcaaManager.guardarSolicitudCcaa(solicitudCcaaBean);
				
				
				//guardamos tb el correspondiente registro en solicitud_propios
				for(int i=0;i<listaCamposAreaText.size();i++){
					SolicitudPropiosBean solicitudPropiosBean = new SolicitudPropiosBean();
					String listaAreaText = listaCamposAreaText.get(i);
					int tam = listaAreaText.length();
					int position=listaAreaText.indexOf('_');
					String idCampo=listaAreaText.substring(0, position);
					String valorCampoPropio=listaAreaText.substring(position+1,tam);
					solicitudPropiosBean.setIdCampo(Long.valueOf(idCampo));
					solicitudPropiosBean.setValor(valorCampoPropio);
					solicitudPropiosBean.setIdSolicitud(Long.valueOf(idSolicitud));
					CamposPropiosQuery camposPropiosQuery = new CamposPropiosQuery();
					camposPropiosQuery.setIdCampo(Integer.parseInt(idCampo));
					CamposPropiosBean camposPropiosBean = camposPropiosManager.buscarCamposPropiosUnico(camposPropiosQuery);
					solicitudPropiosBean.setCamposPropiosBean(camposPropiosBean);
					solicitudPropiosManager.guardarSolicitudPropiosBean(solicitudPropiosBean);
				}
			}
			
			solicitudBean.setId(Long.valueOf(idSolicitud));
			formulario.setId(idSolicitud.toString());
			// Guardamos los datos del pago
			
			
			RegistroSolicitudQuery registroSolicitud = new RegistroSolicitudQuery();
			SolicitudComunQuery solicitudQueryPago = new SolicitudComunQuery();
			solicitudQueryPago.setIdSolicitud(Long.parseLong(idSolicitud.toString()));	
			registroSolicitud.setSolicitudComun(solicitudQueryPago);
			PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();	
			pagoSolicitudQuery.setSolicitudComun(solicitudQueryPago);

			PagoSolicitudBean detallePagoSolicitudBean = null;
			
			
			// Debemos obtener el ultimo registro insertado en la tabla
			if(convocatoriaRepetida_Unico.equals("U")){
				detallePagoSolicitudBean = pagoSolicitudManager.buscarUltimoPagoSolicitudIdSolicituOK(pagoSolicitudQuery);
			}else{
				detallePagoSolicitudBean = pagoSolicitudManager.buscarPagoSolicitudIdSolicituPruebas(pagoSolicitudQuery);
			}
			
			long idConvocatoria = Long.valueOf(formulario.getIdConvocatoria());

			ConvocatoriasBean convocatoriaBean = convocatoriasManager.recuperarConvocatoria(idConvocatoria);
			
			if(formulario.getDocumentoSolicitudPresencialBean() != null && formulario.getDocumentoSolicitudPresencialBean().length > 0 ){
				// modificar esta funcion para guardar los documentos adjuntos
				String resulDoc = subirDocumentoAdjuntoRegistro(formulario);
				logger.info("RegistroSolicitudSpring-Subida Doc Registro: "+resulDoc);
			}
			
			// Se realiza el registro de la solicitud en REC
			RegistroType registroType = this.crearRegistroType(formulario, solicitudBean);				
			
			formulario.setDocumentoHTML(crearHTML(convocatoriaBean, solicitudBean, detallePagoSolicitudBean, formulario, convocatoriaRepetida_Unico));
			String resulSubirDoc = subirDocumentoRegistro(formulario);
			logger.info("RegistroSolicitudSpring-Subida Doc Registro: "+resulSubirDoc);

			if(null!=resulSubirDoc && resulSubirDoc.equals(STRING_ERROR)){
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("field.javascript.errorFirma"));
				logger.info("5. Resultado: nosuccess");
				return STRING_NOSUCCESS;
			}
			
			OctetStream[] docs = this.crearDocumentos(formulario, registroType);
			
			registroType.setCdTipoRegistro("0");
			registroType.setTlResumen(properties.getProperty(STRING_CDASUNTO));
			
			InteresadoType interesado[]=registroType.getInteresados();
			if(solicitudBean.getPais() != null && solicitudBean.getPais().getCodigo() != null){
				interesado[0].setCdPaisInteresado(solicitudBean.getPais().getCodigo());
			}
			if(solicitudBean.getProvinciaByIdProvinciaDomicilio() != null && solicitudBean.getProvinciaByIdProvinciaDomicilio().getCodigo() != null){
				interesado[0].setCdProvinciaInteresado(solicitudBean.getProvinciaByIdProvinciaDomicilio().getCodigo());
			}
			interesado[0].setCdMunicipioInteresado("");
			interesado[0].setTlCorreoElectronicoInteresado(formulario.getEmail());
			interesado[0].setTlDEHInteresado(formulario.getEmail());
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
			int res = 0;
			
			// Se realiza el registro de la solicitud en REC
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
				logServicioBean.setIdTipoError(Constantes.LOG_SERVICIO_TIPO_ERROR_FISICO);
				logServicioBean.setPrueba(Constantes.LOG_SERVICIO_PRUEBA_NO);
				logServicioBean.setTiempoRespuesta(tiempoRespuesta);
				logServicioBean.setDescripcionError(RESOURCE_BUNDLE.getString("registroREC.errorConexion"));

				res = logServicioManager.guardarLogServicioBean(logServicioBean);
				logger.info("Guardados datos de LogServicio con id: "+res);		
				this.setRequestAttribute(STRING_MENSAJEREGISTRO, RESOURCE_BUNDLE.getString(STRING_FIELD_SOLICITUD_REGISTRO_ERRORREALIZACION));
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
					logServicioBean.setIdTipoError(Constantes.LOG_SERVICIO_TIPO_ERROR_LOGICO);
					logServicioBean.setPrueba(Constantes.LOG_SERVICIO_PRUEBA_NO);

					String mensajeError = respuestaType.getDsRespuesta();

					if(mensajeError!=null){
						if(mensajeError.length()>499){
							logServicioBean.setDescripcionError(mensajeError.substring(0, 499));
						}else{
							logServicioBean.setDescripcionError(mensajeError);
						}
					}

					logServicioBean.setTiempoRespuesta(tiempoRespuesta);

					String timestamp = justificanteType.getBlTimeStamp();
					logger.info("Timestamp Recibido: "+timestamp);

					res = logServicioManager.guardarLogServicioBean(logServicioBean);
					logger.info("Guardados datos de LogServicio con id: "+res);
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
					StringBuilder mensaje2 =  new StringBuilder();
					mensaje2.append(RESOURCE_BUNDLE.getString("field.registro.correoPendienteRegistro.texto1")).append(br).append(br)
					.append(RESOURCE_BUNDLE.getString("field.registro.correoPendienteRegistro.texto2")).append(br)
					.append(RESOURCE_BUNDLE.getString("field.solicitud.id")).append(" ").append(solicitudBean.getId())
					.append(br).append(RESOURCE_BUNDLE.getString("field.solicitud.numeroJustificante")).append(" ")
					.append(formulario.getNumeroSolicitud()).append(br).append(RESOURCE_BUNDLE.getString("field.registro.correoPendienteRegistro.texto3"))
					.append(br).append(br).append(RESOURCE_BUNDLE.getString("field.solicitud.nif")).append(" ")
					.append(formulario.getNif()).append(br).append(RESOURCE_BUNDLE.getString("field.solicitud.nombre"))
					.append(" ").append(formulario.getNombre()).append(br).append(RESOURCE_BUNDLE.getString("field.solicitud.email"))
					.append(" ").append(formulario.getEmail());

					CorreoElectronicoBean correoElectronicoBean = new CorreoElectronicoBean();
					correoElectronicoBean.setDe(properties.getProperty("mail.de"));
					correoElectronicoBean.setPara(parametrosConfiguracion.getValorCampo());
					correoElectronicoBean.setAsunto(RESOURCE_BUNDLE.getString("field.registro.correoPendienteRegistro")+" - "+formulario.getNombre());
					correoElectronicoBean.setMensaje(mensaje2.toString());
					correoElectronicoBean.setEstado(Constantes.CORREO_ELECTRONICO_ESTADO_ENVIADO);

					logger.info("Enviando el error que se ha producido por Correo Electronico a:" + parametrosConfiguracion.getValorCampo());
					boolean resul = false;
					resul = EnvioMails.envioMail(correoElectronicoBean);
					
					if(resul){
						logger.info("Correo Electronico Enviado a: " + parametrosConfiguracion.getValorCampo());
					}else{
						logger.error("No se ha podido mandar el correo Electronico a : " + parametrosConfiguracion.getValorCampo());
					}
				}

				this.getRequest().setAttribute("errorRegistro","true");
				
				
				

			}else{
				
				if(formulario.getId() != null){

					registroSolicitudBean = toRegistroBean(formulario);
					String resString = String.valueOf(res);
					registroSolicitudBean.setId(Long.parseLong(resString));

					if(formulario.getCkConsentimiento() == true){	
						registroSolicitudBean.setDesConsentimiento(Constantes.SI);
					}else if(formulario.getCkConsentimiento() == false){	
						registroSolicitudBean.setDesConsentimiento(Constantes.NO);
					}else{	
						registroSolicitudBean.setDesConsentimiento("");
					} 

					if(respuestaType.getCdRespuesta() == null || error  && !respuestaType.getCdRespuesta().equals("00")){
						logger.info("Guardando datos de RegistroSolicitud con Resultado Error");
						//Si se ha conectado y devuelve un error
						registroSolicitudBean.setResultado(Constantes.REGISTRO_RESULTADO_ERROR);
						registroSolicitudBean.setDescripcionError(respuestaType.getDsRespuesta());
						Date fechaAuxIntento = new Date();
						registroSolicitudBean.setFechaIntento(fechaAuxIntento);
					}else{
						if(justificanteType.getNmRegistro()!=null && !"".equals(justificanteType.getNmRegistro())){
							logger.info("Guardando datos de RegistroSolicitud con Resultado OK");
							//Si se ha conectado sin dar ningun error
							Date fechaAuxNueva = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT_HHMMSS, Locale.ENGLISH).parse(justificanteType.getFeRegistro());
							registroSolicitudBean.setNumeroRegistro(justificanteType.getNmRegistro());
							registroSolicitudBean.setResultado(Constantes.REGISTRO_RESULTADO_OK);
							registroSolicitudBean.setOficinaRegistro(justificanteType.getCdOficinaOrigen());
							registroSolicitudBean.setFechaRegistro(fechaAuxNueva);
							Calendar fechaEntrada = Calendar.getInstance();
							sdf = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT_HHMMSS);
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
					// Se guarda en la tabla RegistroSolicitud
					Integer id = registroSolicitudManager.guardarRegistroSolicitud(registroSolicitudBean);
					
					if(id != null){
						this.setRequestAttribute(STRING_REGISTRO, formulario.getId());	
						EstadoSolicitudQuery estadoSolicitudQuery = new EstadoSolicitudQuery();
						
						if(respuestaType.getCdRespuesta() == null || (error && !respuestaType.getCdRespuesta().equals("00")) || justificanteType.getNmRegistro() == null){
							logger.info("La solicitud se deja en Estado Pendiente de Registro");
							estadoSolicitudQuery.setId(Constantes.ESTADO_SOLICITUD_NO_REGISTRADO);
						}else{
							logger.info("La solicitud se deja en Estado Registrada");
							estadoSolicitudQuery.setId(Constantes.ESTADO_SOLICITUD_REGISTRADO);
						}

						SolicitudComunQuery solicitudQueryAuxActualizar = new SolicitudComunQuery();
						solicitudQueryAuxActualizar.setIdSolicitud(registroSolicitudBean.getIdSolicitud());						
						//Se actualiza el estado solicitud
						Long idAux = solicitudesManager.actualizarEstadoSolicitudRegistro(solicitudQueryAuxActualizar, estadoSolicitudQuery);
						logger.info("3.RegistroSolicitudAction-Estado actualizado: "+idAux);

						//Guardar el log de la actualizacion
						LogSolicitudBean logSolicitudBean = new LogSolicitudBean();
						UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
						logSolicitudBean.setActor(usuarioBean.getLogin());
						Date fechaAux =new Date();
						logSolicitudBean.setFecha(fechaAux);
						logSolicitudBean.setTipoOperacion('E');
						logSolicitudBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("logSolicitud.detalleModificacion"));
						logSolicitudBean.setIdSolicitud(Long.parseLong(formulario.getId()));

						if(respuestaType.getCdRespuesta() == null ||(error && !respuestaType.getCdRespuesta().equals("00")) && justificanteType.getNmRegistro() == null){
							logSolicitudBean.setIdEstadoActual(Constantes.LOG_SOLICITUD_ESTADO_NOREGISTRADO);
						}else{
							logSolicitudBean.setIdEstadoActual(Constantes.LOG_SOLICITUD_ESTADO_REGISTRADO);
						}
						logSolicitudBean.setIdEstadoAnterior(Constantes.LOG_SOLICITUD_ESTADO_NOREGISTRADO);
						logSolicitudBean.setTipoActor(Constantes.OPERACION_ACTUALIZAR_ESTADO_SOLICITUD_USUARIO);

						//Se inserta en la tabla de log
						logger.info("Guardando en la tabla LogSolicitud");
						Integer idLog = logSolicitudManager.insertarLogSolicitud(logSolicitudBean);
						
						if(idLog == null){
							logger.error(RESOURCE_BUNDLE.getString("logSolicitud.error"));
						}
						
						if(error==false){
							logger.info("3. Resultado: success!!!");							
							documentoManager.copiarFicheros(formulario.getId(), properties.getProperty("sistemaficheros.url.escritura"));							
						}	
					}
				}
				
				logger.info("El registro se ha realizado correctamente");
				//Aquí deberíamos generar el archivo pdf y subirlo a alfresco
				this.crearDocumentos1(formulario, registroType);
				registroType.setCdTipoRegistro("0");
				registroType.setTlResumen(properties.getProperty(STRING_CDASUNTO));
				registroType.setInteresados(interesado);
				registroType.setCdDocumentacionFisicaSoportes("03");
				GenerarJustificanteBean generarJustificanteBean = documentoManager.generarJustificanteRegistro(idSolicitud.toString());
				if (generarJustificanteBean.getMsgError() != null && !("").equals(generarJustificanteBean.getMsgError())) {
					this.getRequest().setAttribute("errorGeneracion", RESOURCE_BUNDLE.getString(generarJustificanteBean.getMsgError()));
					this.setRequestAttribute(STRING_MENSAJEREGISTRO, RESOURCE_BUNDLE.getString(generarJustificanteBean.getMsgError()));
					return "errorGenerico";
				}
					
				}
	
				if(idSolicitud.intValue() > 0){
					resultado="success";	
					mensaje = RESOURCE_BUNDLE.getString("field.solicitudPresencial.mensaje.confirmacionAltaRegistroPresencial");
					this.setRequestAttribute(STRING_MENSAJEREGISTRO, mensaje);
					this.setRequestAttribute("idSolicitud", idSolicitud);
				}else{
					resultado="error";
					mensaje = RESOURCE_BUNDLE.getString("field.solicitudPresencial.mensaje.altaPresencialError");
				}
				
				String titulo = RESOURCE_BUNDLE.getString("field.solicitudPresencial.tituloAlta");
				
				setRequestAttribute("titulo", titulo);
				setRequestAttribute("mensaje", mensaje);
				setRequestAttribute("accion", "/spring/verBuscarSolicitudPresencial?accion=Alta");
			}		
			
		logger.info("-------Salimos de AltaSolicitud Presencial " + resultado);
		
		}catch(Exception eGenerico){
			logger.error("Error AltaSolicitudPresencialSpring - doExecute", eGenerico);
			this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
			return "errorGenerico";
		}
		return resultado;		
	}

	/**
	 * Generar registro log solicitud.
	 *
	 * @param formulario el formulario
	 * @param username el username
	 * @param idSolicitud el id solicitud
	 */
	public void generarRegistroLogSolicitud(AltaSolicitudPresencialForm formulario,String username, Long idSolicitud){
		
		//Cargo los datos en el bean del log solicitudes que se usara para crear el registro en la tabla
		LogSolicitudBean logSolicitudBean = new LogSolicitudBean();
		
		logSolicitudBean.setTipoOperacion(Constantes.OPERACION_ALTA);
		logSolicitudBean.setTipoActor(Constantes.OPERACION_ACTUALIZAR_ESTADO_SOLICITUD_USUARIO);
		logSolicitudBean.setActor(username);
		logSolicitudBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.solicitudPresencial.logAlta") + idSolicitud);
		logSolicitudBean.setIdSolicitud(idSolicitud);
		//añadimos los estados de solicitud
		logSolicitudBean.setId_estado_actual(Constantes.ESTADO_SOLICITUD_REGISTRADO);
		
		logSolicitudManager.generarRegistroLogSolicitud(logSolicitudBean);		
	}
	
	/**
	 * Crear registro type.
	 *
	 * @param altaSolicitudPresencialForm el alta solicitud presencial form
	 * @param solicitudBean el solicitud bean
	 * @return el registro type
	 */
	private RegistroType crearRegistroType(AltaSolicitudPresencialForm altaSolicitudPresencialForm, SolicitudBean solicitudBean){

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

		if(altaSolicitudPresencialForm.getCalleDomicilio() != null){
			interesadoAux.setTlDireccionInteresado(altaSolicitudPresencialForm.getCalleDomicilio());
		}else{
			interesadoAux.setTlDireccionInteresado("");
		}

		if(altaSolicitudPresencialForm.getCodigoPostal() != null){
			interesadoAux.setCdCodigoPostalInteresado(altaSolicitudPresencialForm.getCodigoPostal());
		}else{
			interesadoAux.setCdCodigoPostalInteresado("");
		}

		if(altaSolicitudPresencialForm.getIdProvinciaDomicilio() != null){
			interesadoAux.setCdProvinciaInteresado(altaSolicitudPresencialForm.getIdProvinciaDomicilio());
		}else{
			interesadoAux.setCdProvinciaInteresado("");
		}

		if(altaSolicitudPresencialForm.getMunicipioDomicilio() != null){
			interesadoAux.setCdMunicipioInteresado(altaSolicitudPresencialForm.getMunicipioDomicilio());
		}else{
			interesadoAux.setCdMunicipioInteresado("");
		}

		String telefonoAux = null;

		try{
			int numtelefonoAux = altaSolicitudPresencialForm.getTelefono1().lastIndexOf('/');
			if(numtelefonoAux != -1){
				telefonoAux = altaSolicitudPresencialForm.getTelefono1().substring(0,numtelefonoAux);
			}else{
				telefonoAux = altaSolicitudPresencialForm.getTelefono1();
			}
		}catch(Exception e){
			logger.error("Error telefono",e);
			telefonoAux = altaSolicitudPresencialForm.getTelefono1();
		}

		interesadoAux.setTlTelefonoContactoInteresado(telefonoAux);
		interesadoType[0] = interesadoAux;
		registroType.setInteresados(interesadoType);

		return registroType;
	}
	
	/**
	 * HTML.
	 *
	 * @param altaSolicitudPresencialForm el alta solicitud presencial form
	 * @param registroType el registro type
	 * @return el octet stream[]
	 */
	private OctetStream[] crearDocumentos(AltaSolicitudPresencialForm altaSolicitudPresencialForm, RegistroType registroType){

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
		registroDocumentoAux.setDsNombre(altaSolicitudPresencialForm.getNumeroSolicitud()+STRING_HTML);
		// 04-Original
		registroDocumentoAux.setCdValidez("04");
		// 01-Formulario
		registroDocumentoAux.setCdTipo("01");
		registroDocumentoAux.setBlTimeStamp(fecha.toString());
		
		
		// Version sin firma
		registroDocumentoAux.setBlFirma(" ");
		registroDocumentoAux.setCdFirmado("0");

		String hashHtml=altaSolicitudPresencialForm.getDocumentoHTML();
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
		registroDocumentoAux.setBlPKCertificado("");
		registroDocumentoAux.setBlDocumento(hashHtml);
		documentoTypeAdjunto[numRegistros-1] = registroDocumentoAux;

		byte[] byteAux;

		try {
			byteAux = IpsUtils.decodeBase64(altaSolicitudPresencialForm.getDocumentoHTML());
		} catch (Exception e) {
			logger.error("crearDocumentos - Error:",e);
			return null;
		}

		docs[numRegistros-1] = new OctetStream(byteAux);
		registroType.setDocumentos(documentoTypeAdjunto);

		return docs;
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
	public String crearHTML(ConvocatoriasBean convocatoriaBean, SolicitudBean solicitudBean, PagoSolicitudBean detallePagoSolicitudBean, AltaSolicitudPresencialForm registroSolicitud, String convocatoriaRepetida_Unico){

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
		centro.append(                                                            solicitudBean.getDesProvinciaDomicilio());
		centro.append(STRING_DIV5);
		centro.append(STRING_DIV4);
		centro.append(STRING_DIV_CLASS_CLEAR_5);

		centro.append(STRING_DIV_CLASS_CAPA99_3);
		centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO_2);
		centro.append(                                                                   RESOURCE_BUNDLE.getString("field.extracto.pais"));     
		centro.append(STRING_DIV5);
		centro.append(STRING_DIV_CLASS_LABELDRC_2);
		centro.append(                                                            solicitudBean.getPais().getDescripcion());
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
		if(solicitudBean.getEspecialidad()!=null && solicitudBean.getEspecialidad().getDescripcion() != null)
			centro.append(                                                            solicitudBean.getEspecialidad().getDescripcion());
		centro.append(STRING_DIV5);
		centro.append(STRING_DIV5);
		centro.append(STRING_DIV_CLASS_CLEAR_2);

		centro.append("                                                    <div class='capa99'>");
		centro.append("                                                           <div class='labelIzqDet_Rojo'>");
		centro.append(                                                                   RESOURCE_BUNDLE.getString("field.extracto.provinciaExamen"));
		centro.append(STRING_DIV7);
		centro.append("                                                           <div class='labelDrc'>");
		centro.append(                                                                   solicitudBean.getDescripcionIdProvinciaExamen());      
		centro.append(STRING_DIV5);
		centro.append(STRING_DIV5);
		centro.append(STRING_DIV_CLASS_CLEAR_2);

		centro.append("                                                    <div class='capa99'>");
		centro.append("                                                           <div class='labelIzqDet_Rojo'>");
		centro.append(                                                                   RESOURCE_BUNDLE.getString("field.extracto.tituloOficial"));
		centro.append(STRING_DIV7);
		centro.append("                                                           <div class='labelDrc'>");
		centro.append(                                                                   solicitudBean.getDescripcionTituloOficial());
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
			centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.añoConvocatoria"));
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
		centro.append(                                                 convocatoriaBean.getMinisterioConvocatoria());            
		centro.append(STRING_DIV2);
		centro.append("                             </div>");
		centro.append("                             <div class='clear'></div>");

		centro.append(STRING_DIV_CLASS_CAPA99);
		centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
		centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.tipoDiscapacidad"));
		centro.append(STRING_DIV);
		centro.append(STRING_DIV_CLASS_LABELDRC);
		if(solicitudBean.getDescripcionTipoDiscapacidad()!=null && !solicitudBean.getDescripcionTipoDiscapacidad().equals(""))
			centro.append(                                                        solicitudBean.getDescripcionTipoDiscapacidad()); 
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
			ArrayList<SolicitudPropiosBean> solicitudPropios = solicitudPropiosManager.obtenerCamposPropiosByIdSolicitud(solicitudPropiosQuery);


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
		ArrayList<PagoSolicitudBean> pagosAsociados = pagoSolicitudManager.buscarPagoSolicitudAll(pagoSolicitudQuery);
		
		for (PagoSolicitudBean pago : pagosAsociados){
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
			centro.append(                                                 pago.getSolicitaReduccion());
			centro.append(STRING_DIV);
			centro.append(STRING_DIV2);
			centro.append(STRING_DIV_CLASS_CLEAR_3);

			if(convocatoriaRepetida_Unico.equals("U")){           

				if(pago.getTipo() != null && !pago.getTipo().equals('E')){
					if(pago.getDesMotivoReduccionTasa() != null && pago.getDesMotivoReduccionTasa().equals("S")){
						centro.append(STRING_DIV_CLASS_CAPA99);
						centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
						centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.motivoReduccion"));
						centro.append(STRING_DIV);
						centro.append(STRING_DIV_CLASS_LABELDRC);
						centro.append(                                                 pago.getDesMotivoReduccionTasa());
						centro.append(STRING_DIV);
						centro.append(STRING_DIV2);
						centro.append(STRING_DIV_CLASS_CLEAR_3);

						centro.append(STRING_DIV_CLASS_CAPA99);
						centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
						centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.entidadFinanciera"));
						centro.append(STRING_DIV);
						centro.append(STRING_DIV_CLASS_LABELDRC);
						if(pago.getDesEntidadFinanciera()!=null)          
							centro.append(                                                 pago.getDesEntidadFinanciera());
						centro.append(STRING_DIV);
						centro.append(STRING_DIV2);
						centro.append(STRING_DIV_CLASS_CLEAR_3);

					}else{
						centro.append(STRING_DIV_CLASS_CAPA99);
						centro.append(STRING_DIV_CLASS_LABELIZQDET_ROJO);
						centro.append(                                                            RESOURCE_BUNDLE.getString("field.extracto.entidadFinanciera"));
						centro.append(STRING_DIV);
						centro.append(STRING_DIV_CLASS_LABELDRC);
						if(pago.getDesEntidadFinanciera()!=null)    
							centro.append(                                                 pago.getDesEntidadFinanciera());
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
					centro.append(                                                 pago.getDesMotivoReduccionTasa());
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
		documentoQuery.addSolicitudIn(solicitudBean.getId());

		Integer tpDocumento = Constantes.TIPO_DOCUMENTO_ANEXO_SOLICITUD;
		documentoQuery.addTipoDocumentoIn(tpDocumento);
		tpDocumento = Constantes.TIPO_DOCUMENTO_ADJUNTO;
		documentoQuery.addTipoDocumentoIn(tpDocumento);
		tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_TASA;
		documentoQuery.addTipoDocumentoIn(tpDocumento);
		tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_DISCAPACIDAD;
		documentoQuery.addTipoDocumentoIn(tpDocumento);
		tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_PAGO;
		documentoQuery.addTipoDocumentoIn(tpDocumento);

		if(convocatoriaRepetida_Unico.equals("U")){ 
			List<DocumentoBean> documentosList;
			documentosList=documentoManager.buscarDocumentosByIdSolicitud(documentoQuery); 
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
	 * Subir documento registro.
	 *
	 * @param formulario el formulario
	 * @return el string
	 */
	public String subirDocumentoRegistro (AltaSolicitudPresencialForm formulario){
		
		String fileName = "";
		String contentType = "" + formulario.getNumeroSolicitud();
		fileName = "/"+contentType+STRING_HTML;
		byte[] fileData;

		try {
			fileData = formulario.getDocumentoHTML().getBytes();
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

			this.setRequestAttribute(STRING_REGISTRO,formulario.getId());
			return "verDocumentos";
		}
		
		Calendar c = Calendar.getInstance();
		docBean.setFechaCreacion(c.getTime());      
		
		docBean.setDsNombreDocumento(nombreAux);
		docBean.setIdSolicitud(Long.parseLong(formulario.getId()));
		docBean.setEntorno(Constantes.ENTORNO_SOLICITUDES);
		docBean.setIdConvocatoria(Long.parseLong(formulario.getIdConvocatoria()));
		
		try {
			SHA0 hash = new SHA0();
			String hashFile=hash.getHash(fileData);
			docBean.setHashExtracto(hashFile);	
		} catch (NoSuchAlgorithmException e2) {
			logger.error("Error obteniendo hash del documento: ",e2);
		}

		Long id = 0L;
		try{
			id = documentoManager.insertarDocumentoRegistro(docBean);
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
	 * Subir documento adjunto registro.
	 *
	 * @param formulario el formulario
	 * @return el string
	 */
	public String subirDocumentoAdjuntoRegistro (AltaSolicitudPresencialForm formulario){
		
		for(int i =0;i<formulario.getDocumentoSolicitudPresencialBean().length;i++){
			DocumentoSolicitudPresencialBean doc = formulario.getDocumentoSolicitudPresencialBean()[i];
			//Coger datos del fichero
			String contentType = "";
			String fileName = "";	
			
			contentType = "" + doc.getFile().getOriginalFilename();
			fileName = contentType;
			
			logger.info(fileName);
			byte[] fileData;
			
			try {
				fileData = doc.getFile().getBytes();

			} catch (Exception e1) {			
				logger.error("Decode DocumentosFicheros - Error:" ,e1);
				return STRING_ERROR;
			}
			
			int fileSize = fileData.length;
			logger.info(fileSize);

			DocumentoBean docBean= new DocumentoBean();
			//Asigno los valores al bean
			docBean.setContenidoDocumento(fileData);
			
			String[] arrNombre=fileName.split("\\.");
			int numAux = fileName.lastIndexOf('/');
			
			if(numAux<0){
				numAux = fileName.lastIndexOf('\\');
			}
			
//			int numExten = fileName.lastIndexOf('.');
//			String nombreAux = fileName.substring(numAux+1,numExten);	        
			String extension="";
			
			if(arrNombre.length>1){
				extension=arrNombre[arrNombre.length-1];
			}
			logger.info("Extension: "+extension);
			docBean.setExtension(extension);
			docBean.setTamano(safeLongToInt(Math.round(fileSize/1024.0)));
			docBean.setNombre(doc.getNombre());
			docBean.setDescripcion(doc.getDescripcion());

			if(contentType == null || "".equals(contentType)){

				this.setRequestAttribute(STRING_REGISTRO,formulario.getNumeroSolicitud());
				logger.info("Retorno: verDocumentos");
				return "verDocumentos";
			}
			Calendar c = Calendar.getInstance();
			docBean.setFechaCreacion(c.getTime());

			//Tipo de documento adjunto
			docBean.setIdTipoDocumento(doc.getIdTipoDocumento());
			logger.info(doc.getIdTipoDocumento());

			docBean.setIdSolicitud(Long.parseLong(formulario.getId()));
			docBean.setEntorno(Constantes.ENTORNO_SOLICITUDES);
			docBean.setIdConvocatoria(Long.parseLong(formulario.getIdConvocatoria()));
			
			// Generación del hash del documento para almacenarlo en bbdd.
			try {
				SHA0 hash = new SHA0();
				byte[] byteHash = doc.getFile().getBytes();
				String hashFile=hash.getHash(byteHash);
				docBean.setHashExtracto(hashFile);

			} catch (NoSuchAlgorithmException e2) {
				logger.error("Error obteniendo hash del documento: ",e2);
			} catch (Exception e) {
				logger.error("SubirDocumentos - Error ",e);
			}
			
			Long id = 0L;
			
			try{
				id = documentoManager.insertarDocumentoRegistro(docBean);
				if(id==0)
				{    			
					this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_CERTIFICADOS_ERROR_GESTORDOCUMENTAL));
					return STRING_ERROR;		        			
				}

			}catch(Exception e){
				logger.error("insertarDocumento- Error ",e);
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_CERTIFICADOS_ERROR_GESTORDOCUMENTAL));
				return STRING_ERROR;
			}
			if(id == 0){
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_CERTIFICADOS_ERROR_GESTORDOCUMENTAL));
				return STRING_ERROR;
			}
			
		}	
		
		logger.info("Retorno: correcto");
		return STRING_CORRECTO;
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
	 * Convertir.
	 *
	 * @param tipoDocumentoBean el tipo documento bean
	 * @return el tipo documento
	 */
	public TipoDocumento convertir(TipoDocumentoBean tipoDocumentoBean) {
		
		TipoDocumento tipoDocumento = new TipoDocumento();
		
		tipoDocumento.setCodigo(tipoDocumentoBean.getCodigo());
		tipoDocumento.setDescripcion(tipoDocumentoBean.getDescripcion());
		tipoDocumento.setId(Integer.parseInt(tipoDocumentoBean.getId()));
		
		return tipoDocumento;
	}
	
	/**
	 * Crear documentos 1.
	 *
	 * @param altaSolicitudPresencialForm el alta solicitud presencial form
	 * @param registroType el registro type
	 * @return el octet stream[]
	 */
	private OctetStream[] crearDocumentos1(AltaSolicitudPresencialForm altaSolicitudPresencialForm, RegistroType registroType){
		// Declarar octetStream
		int numRegistros = 0;
		
		// Comprobar cuantos ficheros posee el resgistro para crear el array
		if(altaSolicitudPresencialForm.getDocumentoSolicitudPresencialBean() != null){
			for(int x=0;x<altaSolicitudPresencialForm.getDocumentoSolicitudPresencialBean().length;x++){
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
		
		
ArrayList<DocumentoBean> documentosAdjuntos;
		
		SolicitudComunQuery solicitudAux = new SolicitudComunQuery();
		solicitudAux.setIdSolicitud(Long.parseLong(altaSolicitudPresencialForm.getId()));
		
		TipoDocumentoQuery tipoDocumentoQuery = new TipoDocumentoQuery();
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_ANEXO_SOLICITUD);
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_FORMULARIO);
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REDUCCION_TASA);
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_DISCAPACIDAD);
		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_PAGO);
		
		DocumentoQuery documentoQuery = new DocumentoQuery();
		documentoQuery.setSolicitudComun(solicitudAux);
		documentoQuery.setTipoDocumento(tipoDocumentoQuery);
		//Busco los documentos anexados a la solicitud (1) y el formulario html (9)
		documentosAdjuntos=documentoManager.buscarDocumentosByIdSolicitud(documentoQuery);
		
				
		//Creamos el justificantePDF y el registro de solicitud PDF //XML
		RegistroSolicitudJustificanteBean registroSolicitudJustificante = toRegistroCompleto(altaSolicitudPresencialForm.getId());

		//Añadimos los documentos adjuntados y el html al registroType para crear el pdf solicitud
		DocumentoType registroDocumentoType2[] = new DocumentoType[numRegistros];
		//metodo para añadir los documentos adjuntos al registroType y sacarlos en el justificante solicitud
		for(int x=0;x<documentosAdjuntos.size();x++){
			byte[] salida;
			String idAlfresco = null;
			DocumentoType registroDocumentoAux = new DocumentoType();
			DocumentoBean ficheroHash=documentosAdjuntos.get(x);
			TipoDocumento tipoDocumento=ficheroHash.getTipoDocumento();
			tipoDocumento.getDescripcion();
			String nombreAlfresco = documentosAdjuntos.get(x).getNombreAlfresco();
			logger.info("NombreDocumento: "+nombreAlfresco);
			int numNombre = nombreAlfresco.lastIndexOf('.');
			String nombre = nombreAlfresco.substring(0,numNombre);

			try{
				logger.info("Recuperando el documento: "+nombreAlfresco);
				
				// TODO ALFRESCO
				byte[] salidaDoc = documentoManager.obtenerContenidoDocumento(ficheroHash);	
				
				String extension = FilenameUtils.getExtension(documentosAdjuntos.get(x).getNombreAlfresco());
				if("html".equals(extension)){
					
					idAlfresco = nombre + "Firma.XML";
					logger.info("Recuperando la firma del documento: "+idAlfresco);								
					
					// TODO ALFRESCO
					salida = documentoManager.obtenerContenidoDocumento(ficheroHash);
					
					registroDocumentoAux.setCdTipo("02");
					registroDocumentoAux.setDsNombre(documentosAdjuntos.get(x).getNombreAlfresco());
					String nombreHash=documentosAdjuntos.get(x).getNombreAlfresco();
					byte[] byteHash = nombreHash.getBytes();
					String hashFile=obtenerHash(hash, byteHash,1);
					byte[] codigo64 = hashFile.getBytes();
					hashFile = IpsUtils.encodeBase64(codigo64);
					registroDocumentoAux.setBlHash(hashFile);
					registroDocumentoAux.setBlFirma(UtilesIPSG.encodeBase64(salida));
				}else{
					registroDocumentoAux.setDsNombre(documentosAdjuntos.get(x).getNombre()+"."+extension);
					registroDocumentoAux.setCdTipo("01");
					String nombreHash=documentosAdjuntos.get(x).getNombreAlfresco();
					byte[] byteHash = nombreHash.getBytes();
					String hashFile=obtenerHash(hash, byteHash, 1);
					byte[] codigo64 = hashFile.getBytes();
					hashFile = IpsUtils.encodeBase64(codigo64);
					registroDocumentoAux.setBlHash(hashFile);
				}	

				registroDocumentoAux.setCdValidez("04");
				registroDocumentoAux.setBlTimeStamp(fecha.toString());
				registroDocumentoAux.setBlDocumento(documentosAdjuntos.get(x).getNombreAlfresco());
				registroDocumentoAux.setCdFirmado("1");
				registroDocumentoAux.setBlValidacionOCSP("s");
				registroDocumentoAux.setDsTipoMIME("s");
				registroDocumentoAux.setBlPKCertificado("");
				registroDocumentoAux.setTlObservaciones("");
				registroDocumentoType2[x] = registroDocumentoAux;
				docs[x] = new OctetStream(salidaDoc);	
			}catch(Exception e){
				logger.error(" error ReintentarRegistroSpring - Error recuperando el documento de firma de la solicitud: "+idAlfresco,e);
				
			}
		}
		
		
		//Se añade el html
		DocumentoType registroDocumentoAux = new DocumentoType();
		registroDocumentoAux.setDsNombre(altaSolicitudPresencialForm.getNumeroSolicitud()+STRING_HTML);
		registroDocumentoAux.setCdValidez("04");
		registroDocumentoAux.setCdTipo("01");
		registroDocumentoAux.setBlTimeStamp(fecha.toString());
		// Futura version sin firma
		registroDocumentoAux.setBlFirma(" ");
		registroDocumentoAux.setCdFirmado("0");

		String hashHtml=altaSolicitudPresencialForm.getDocumentoHTML();
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
		registroDocumentoAux.setBlPKCertificado("");
		registroDocumentoAux.setBlDocumento(hashHtml);
		documentoTypeAdjunto[numRegistros-1] = registroDocumentoAux;

		byte[] byteAux;

		try {
			byteAux = IpsUtils.decodeBase64(altaSolicitudPresencialForm.getDocumentoHTML());
		} catch (Exception e) {
			logger.error("Decode DocumentoHTML - Error:",e);
			return null;
		}
		
		docs[numRegistros-1] = new OctetStream(byteAux);
		registroType.setDocumentos(documentoTypeAdjunto);

		return docs;

	}	
	
	/**
	 * To registro completo.
	 *
	 * @param idRegistro el id registro
	 * @return el registro solicitud justificante bean
	 */
	private RegistroSolicitudJustificanteBean toRegistroCompleto(
			String idRegistro) {

		//Obtengo los datos de solicitud
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		Long idSolicitud = Long.parseLong(idRegistro);
		solicitudQuery.setIdSolicitud(idSolicitud);
		SolicitudBean solicitud;
		solicitud = solicitudesManager.buscarSolicitudById(solicitudQuery);

		//Obtengo los datos de la convocatoria
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(solicitud.getIdConvocatoria());
		ConvocatoriasBean convocatoriaBean;
		convocatoriaBean = convocatoriasManager.buscarConvocatoriaById(convocatoriaQuery);

		//Obtengo los datos del pagoSolicitud
		PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
		pagoSolicitudQuery.setSolicitudComun(solicitudQuery);
		pagoSolicitudQuery.setResultado(Constantes.RESULTADO_OK);
		PagoSolicitudBean pagoSolicitud;
		pagoSolicitud = pagoSolicitudManager.buscarPagoSolicitudByIdSolicitud(pagoSolicitudQuery);

		//Obtengo los datos del registroSolicitud
		RegistroSolicitudQuery registroSolicitudQuery = new RegistroSolicitudQuery();
		registroSolicitudQuery.setSolicitudComun(solicitudQuery);
		registroSolicitudQuery.setResultado(Constantes.RESULTADO_OK);

		//Crear el bean del justificante
		RegistroSolicitudJustificanteBean aux = new RegistroSolicitudJustificanteBean();
		aux.setIdSolicitud(idRegistro);
		aux.setIdConvocatoria(convocatoriaBean.getIdConvocatoria());
		aux.setNombre(solicitud.getNombre());
		aux.setPrimerApellido(solicitud.getPrimerApellido());
		aux.setSegundoApellido(solicitud.getSegundoApellido());
		aux.setNif(solicitud.getNif());
		aux.setEmail(solicitud.getEmail());
		aux.setNumJustificante(solicitud.getNumeroSolicitud());
		aux.setEjercicio(solicitud.getEjercicio());
		aux.setMinisterio(convocatoriaBean.getMinisterio());
		aux.setFormaPago(String.valueOf(pagoSolicitud.getTipo()));
		aux.setNrc(pagoSolicitud.getNrc());
		aux.setImporte(String.valueOf(pagoSolicitud.getImporte()));

		EntidadFinanciera entidadFinanciera = pagoSolicitud.getEntidadFinanciera();
		if(entidadFinanciera!=null){
			aux.setEntidadFinanciera(entidadFinanciera.getDescripcion());
		}

		return aux;
	}
	
	/**
	 * Obtener hash.
	 *
	 * @param hash el hash
	 * @param byteHash el byte hash
	 * @param mensajeError el mensaje error
	 * @return el string
	 */
	private String obtenerHash(SHA0 hash, byte[] byteHash, int mensajeError) {
		String hashFile = "";
		try {
			hashFile=hash.getHash(byteHash);
		} catch (NoSuchAlgorithmException e1) {
			switch (mensajeError) {
			case 0: 
				logger.error("Error ReintentarRegistroSpring - Error recuperar firma del documento: " ,e1);
				break;
			case 1:
				logger.error("Error ReintentarRegistroSpring - Error al recuperar el documento: " ,e1);
				break;
			default:
				break;
			}			
			
		}
		return hashFile;
	}
	
	/**
	 * Crear pdf.
	 *
	 * @param justificanteType el justificante type
	 * @param registroType el registro type
	 * @param altaSolicitudPresencialForm el alta solicitud presencial form
	 * @return el int
	 * @throws Exception el exception
	 */
//	private int crearPdf(JustificanteType justificanteType,RegistroType registroType,
//			AltaSolicitudPresencialForm altaSolicitudPresencialForm) throws Exception {
//
//		JRBeanCollectionDataSource jrBeanCollectionDataSource = null;
//		File sourceFile = null;
//
//		//Creamos los array de bytes de cada documento
//		byte[] pdfasbytes = null;
//
//		//Creamos los arrayList para los PDF's
//		ArrayList <JustificanteBean> aListPdfPrueba = new ArrayList<JustificanteBean>();
//
//		//Creamos los jasper
//		Jasper jasperJustificante = new Jasper();
//
//		//Enruta los jasper	
//		String ficheroPdf = properties.getProperty("jasper.JustificanteR");
//
//		//Creamos el bean que guarda los datos del primer jasper
//		JustificanteBean justificanteBean;
//		justificanteBean = toJustificanteBean(justificanteType,registroType,altaSolicitudPresencialForm);
//		
//		// Logo y encabezado por defecto: 790001
//		justificanteBean.setRutaLogo(properties.getProperty("jasper.rutaLogo"));
//		justificanteBean.setEncabezado(Constantes.ENCABEZADO_GENERICO);
//
//		String idMJUST = properties.getProperty("jasper.justicia.id");
//		String[] idMJUSTAux = idMJUST.split(";"); 
//		List<String> listaMinisterio = Arrays.asList(idMJUSTAux);
//			
//		if(null!=justificanteBean.getMinisterio()){
//			for(int i=0; i<listaMinisterio.size(); i++){
//				if (justificanteBean.getMinisterio().equals(listaMinisterio.get(i))){
//					justificanteBean.setRutaLogo(properties.getProperty("jasper.rutaLogo.justicia"));
//					justificanteBean.setEncabezado(Constantes.ENCABEZADO_JUSTICIA);
//					break;
//				}
//			}				
//		}
//		
////		if(null != justificanteBean.getMinisterio() && justificanteBean.getMinisterio().equals(idMJUST)){
////			justificanteBean.setRutaLogo(properties.getProperty("jasper.rutaLogo.justicia"));
////			justificanteBean.setEncabezado(Constantes.ENCABEZADO_JUSTICIA);
////		}
//		
//		//Se añaden los bean a la lista
//		aListPdfPrueba.add(justificanteBean);
//
//		jrBeanCollectionDataSource = new JRBeanCollectionDataSource((Collection<JustificanteBean>) aListPdfPrueba);
//
//		URL url = ResourceLocator.getInstance().getJasperTemplate(ficheroPdf);			
//		String rutaFicheroJasper="";
//		if(url!=null){
//			rutaFicheroJasper = url.getFile();
//		}
//		sourceFile = new File(rutaFicheroJasper);
//
//		// Cargo el informe compilado en el objeto jasperreport
//		pdfasbytes = jasperJustificante.generarPDF(jrBeanCollectionDataSource, sourceFile, "");
//
//		ByteArrayOutputStream pdfCompuesto = new ByteArrayOutputStream();
//		PdfReader reader1 = new PdfReader(pdfasbytes);
//		PdfCopyFields copy = new PdfCopyFields(pdfCompuesto);
//
//		copy.addDocument(reader1);
//		copy.close();
//		
//		try{
//			//añadimos la firma al documento
//			Signer signer = SignersFactory.getInstance().getSigner(SignatureConstants.SIGN_FORMAT_PADES);
//			PrivateKeyEntry certificatePrivateKey = UtilesIPSG.getCertificatePrivateKey();
//			byte [] eSignature = signer.sign(pdfCompuesto.toByteArray(), SignatureConstants.SIGN_ALGORITHM_SHA512WITHRSA, SignatureConstants.SIGN_MODE_IMPLICIT, certificatePrivateKey, null);
//			
//			// CSV INSIDE
//			
//			// generacion codigo CSV
//			EeUtilService csvEeUtilService = new EeUtilServiceProxy(properties.getProperty("url.ws.csv.eeutil"));
//			String codigoCSV = es.map.ipsg.util.CSVInside.generarCodigoCSV(eSignature, properties, csvEeUtilService);
//			
//			if (codigoCSV!=null && !"".equals(codigoCSV)) {
//				logger.info("Codigo CSV generado por CSVInside: " + codigoCSV);
//				
//				// generacion copia CSV con codigo incrustrado
//				Calendar c = Calendar.getInstance();
//				Date fechaRegistro = c.getTime();
//				CiudadanoBean ciudadanoSolicitud = new CiudadanoBean();
//				ciudadanoSolicitud.setNif(altaSolicitudPresencialForm.getNif());
//				ciudadanoSolicitud.setNombre(altaSolicitudPresencialForm.getNombre());
//				ciudadanoSolicitud.setPrimerApellido(altaSolicitudPresencialForm.getPrimerApellido());
//				ciudadanoSolicitud.setSegundoApellido(altaSolicitudPresencialForm.getSegundoApellido());
//				
//				byte[] docCsv =
//						CSVInside.generarCopiaCSV(eSignature,ciudadanoSolicitud,properties,csvEeUtilService,codigoCSV,fechaRegistro);
//				
//				if (docCsv!=null) {
//					// firma pdf
//					byte [] docCsvSignature =
//							signer.sign(docCsv, SignatureConstants.SIGN_ALGORITHM_SHA512WITHRSA,
//									    SignatureConstants.SIGN_MODE_IMPLICIT, certificatePrivateKey, null);
//					logger.info("Copia pdf con csv incrustado finalizada");
//					
///*					byte[] esignaturebase64 = Base64Coder.encodeBase64(docCsvSignature);
//					
//					//Se añade el sello del timpo (firma PADES LTV)
//					Map<String, Object> inParams = new HashMap<String, Object>();		
//					inParams.put(DSSTagsRequest.CLAIMED_IDENTITY, properties.getProperty("webservices.idAplicacion"));
//					inParams.put(DSSTagsRequest.IGNORE_GRACE_PERIOD, "true"); 
//					inParams.put(DSSTagsRequest.RETURN_UPDATED_SIGNATURE_ATR_TYPE, "urn:afirma:dss:1.0:profile:XSS:PAdES:1.1.2:forms:LTV");
//					inParams.put(DSSTagsRequest.SIGNATURE_BASE64, new String(esignaturebase64));
//				
//					String xmlInput = TransformersFacade.getInstance().generateXml(
//							inParams, 
//							GeneralConstants.DSS_AFIRMA_VERIFY_REQUEST, 
//							GeneralConstants.DSS_AFIRMA_VERIFY_METHOD, 
//							TransformersConstants.VERSION_10);
//					
//					String xmlOutput = Afirma5ServiceInvokerFacade.getInstance().invokeService(
//							xmlInput, 
//							GeneralConstants.DSS_AFIRMA_VERIFY_REQUEST, 
//							GeneralConstants.DSS_AFIRMA_VERIFY_METHOD, 
//							properties.getProperty("webservices.idAplicacion"));
//					
//					Map<String, Object>  docCSVFirmado = TransformersFacade.getInstance().parseResponse(
//							xmlOutput, 
//							GeneralConstants.DSS_AFIRMA_VERIFY_REQUEST, 
//							GeneralConstants.DSS_AFIRMA_VERIFY_METHOD, 
//							TransformersConstants.VERSION_10);
//					
//					String docCSVFirma = String.valueOf(docCSVFirmado.get(properties.getProperty("respuesta.integra.updatedSignature")));
//					logger.info("Firma con sello de tiempo finalizada");	
//					
//					byte[] docCSVFirmaSinbase64 = Base64Coder.decodeBase64(docCSVFirma.getBytes());
//*/					
//					// registro justificante con csv incrustrado en tabla documentos
//					String idSolicitud = altaSolicitudPresencialForm.getId();
//					long idDocumento = registroPdfCsvDocumentos(docCsvSignature, idSolicitud, codigoCSV);
//					
//					if (idDocumento>0) {
//						// subida justificante a filesystem
//						logger.info("Subiendo Justificante a Sistema de ficheros");
//						int subida = subirDocumentoPdfCSV(docCsvSignature, idDocumento); 
//						if(subida == 0){
//							logger.info("error subiendo el documento con csv incrustrado al filesystem");
//							this.getRequest().setAttribute(STRING_MENSAJEREGISTRO, RESOURCE_BUNDLE.getString("field.errorCsv.filesystem"));
//							return 0;
//						}
//						logger.info("Subida PDF a Sistema de ficheros finalizada");
//					} else {
//						logger.info("error registrando el documento con csv incrustrado en la tabla documentos");
//						this.getRequest().setAttribute(STRING_MENSAJEREGISTRO, RESOURCE_BUNDLE.getString("field.errorCsv.documento"));
//						return 0;
//					}
//					
//					// CSV STORAGE
//					String guardado = CSVStorage.guardarDocumento(codigoCSV, docCsvSignature, properties);
//					if (!guardado.equals(Constantes.RESULTADO_OK)){
//						this.getRequest().setAttribute(STRING_MENSAJEREGISTRO, RESOURCE_BUNDLE.getString("field.errorCsv.storage"));
//						return 0;
//					}
//				} else {
//					logger.info("Error obteniendo la copia con el codigo csv incrustrado");
//					this.getRequest().setAttribute(STRING_MENSAJEREGISTRO, RESOURCE_BUNDLE.getString("field.errorCsv.codigo"));
//					return 0;
//				}
//			} else {
//				logger.info("Error obteniendo el codigo csv");
//				this.getRequest().setAttribute(STRING_MENSAJEREGISTRO, RESOURCE_BUNDLE.getString("field.errorCsv.copia"));
//				return 0;
//			}
//		
//				
//		} catch (SigningException e){
//			logger.info("Error firmando documento");
//			logger.error("Error ReintentarRegistroSpring - Error firmando documento: ",e);
//			return 0;
//		}
//		return 1;
//	}
	
	
	/**
	 * To justificante bean.
	 *
	 * @param justificanteType el justificante type
	 * @param registroType el registro type
	 * @param altaSolicitudPresencialForm el alta solicitud presencial form
	 * @return el justificante bean
	 */
//	private JustificanteBean toJustificanteBean(
//			JustificanteType justificanteType, RegistroType registroType,
//			AltaSolicitudPresencialForm altaSolicitudPresencialForm ) {
//
//		JustificanteBean aux = new JustificanteBean();
//
//		// Datos de Registro
//		if(justificanteType.getNmRegistro() != null && !"".equals(justificanteType.getNmRegistro())){
//			aux.setNumRegistro(justificanteType.getNmRegistro());
//		}else{
//			aux.setNumRegistro("");
//		}
//		if(justificanteType.getFeRegistro() != null && !"".equals(justificanteType.getFeRegistro())){
//			String fechaRegistro=justificanteType.getFeRegistro();	
//			SimpleDateFormat formatter = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
//			SimpleDateFormat formatter1 = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);		
//			SimpleDateFormat formatter2 = new SimpleDateFormat("HH:mm:ss");		
//
//			try {	 
//				Date date = formatter.parse(fechaRegistro);
//				aux.setFecha(formatter1.format(date));
//				aux.setHora(formatter2.format(date));
//			} catch (ParseException e) {
//				logger.error("Error ReintentarRegistroSpring - toJustificanteBean - parsear fecharegistro : "+fechaRegistro ,e);
//			}
//
//		}else{
//			aux.setFecha("");
//			aux.setHora("");
//		}
//
//		if(justificanteType.getDsOficinaOrigen() != null && !"".equals(justificanteType.getDsOficinaOrigen())){
//			aux.setOficina(justificanteType.getDsOficinaOrigen());
//		}else{
//			aux.setOficina("");
//		}
//
//		// Buscar convocatoria
//		ConvocatoriasBean convocatoriaBean;
//		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
//		convocatoriaQuery.setId(Long.parseLong(altaSolicitudPresencialForm.getIdConvocatoria()));
//		convocatoriaBean = convocatoriasManager.buscarConvocatoriaById(convocatoriaQuery);
//
//		// Buscar solicitud
//		SolicitudBean solicitudBean;
//		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
//		solicitudQuery.setIdSolicitud(Long.parseLong(altaSolicitudPresencialForm.getId()));
//		solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);
//
//		// Solicitud
//		if(convocatoriaBean.getMinisterio() != null && !"".equals(convocatoriaBean.getMinisterio())){
//			aux.setMinisterio(convocatoriaBean.getMinisterio());
//		}else{
//			aux.setMinisterio("");
//		}
//		if(convocatoriaBean.getCentroGestor() != null && !"".equals(convocatoriaBean.getCentroGestor())){
//			aux.setCentroGestor(convocatoriaBean.getCentroGestor());
//		}else{
//			aux.setCentroGestor("");
//		}
//		if(solicitudBean.getNumeroSolicitud() != null && !"".equals(solicitudBean.getNumeroSolicitud())){
//			aux.setNumeroJustificante(solicitudBean.getNumeroSolicitud());
//			aux.setCodigoTasa(solicitudBean.getNumeroSolicitud().substring(3, 6));
//		}else{
//			aux.setNumeroJustificante("");
//			aux.setCodigoTasa("");
//		}
//		if(solicitudBean.getEjercicio() != null && !"".equals(solicitudBean.getEjercicio())){
//			aux.setAnioConvocatoria1(solicitudBean.getEjercicio().substring(0, 1));
//			aux.setAnioConvocatoria2(solicitudBean.getEjercicio().substring(1, 2));
//			aux.setAnioConvocatoria3(solicitudBean.getEjercicio().substring(2, 3));
//			aux.setAnioConvocatoria4(solicitudBean.getEjercicio().substring(3));
//		}else{
//			aux.setAnioConvocatoria1("");
//			aux.setAnioConvocatoria2("");
//			aux.setAnioConvocatoria3("");
//			aux.setAnioConvocatoria4("");
//		}
//
//		// Identificacion
//		if(solicitudBean.getIdConsentimiento() != null){
//			if(solicitudBean.getIdConsentimiento() == true)
//			{
//				aux.setConsentimiento(Constantes.SI);
//			}	
//			else if(solicitudBean.getIdConsentimiento() == false)
//			{
//				aux.setConsentimiento(Constantes.NO);
//			} 
//		}else{
//			aux.setConsentimiento("");
//		}
//		if(solicitudBean.getNif() != null && !"".equals(solicitudBean.getNif())){
//			aux.setNif(solicitudBean.getNif());
//		}else{
//			aux.setNif("");
//		}
//		if(solicitudBean.getPrimerApellido() != null && !"".equals(solicitudBean.getPrimerApellido())){
//			aux.setPrimerApellido(solicitudBean.getPrimerApellido());
//		}else{
//			aux.setPrimerApellido("");
//		}
//		if(solicitudBean.getSegundoApellido() != null && !"".equals(solicitudBean.getSegundoApellido())){
//			aux.setSegundoApellido(solicitudBean.getSegundoApellido());
//		}else{
//			aux.setSegundoApellido("");
//		}
//		if(solicitudBean.getNombre() != null && !"".equals(solicitudBean.getNombre())){
//			aux.setNombre(solicitudBean.getNombre());
//		}else{
//			aux.setNombre("");
//		}
//		if(solicitudBean.getFechaNacimiento() != null && !"".equals(solicitudBean.getFechaNacimiento().clone().toString())){
//			SimpleDateFormat formatoFechaNac = new SimpleDateFormat("dd/MM/yyyy");
//			String fechaNac = formatoFechaNac.format(solicitudBean.getFechaNacimiento());
//			aux.setDiaNacimiento1(fechaNac.substring(0, 1));
//			aux.setDiaNacimiento2(fechaNac.substring(1, 2));
//			aux.setMesNacimiento1(fechaNac.substring(3, 4));
//			aux.setMesNacimiento2(fechaNac.substring(4, 5));
//			aux.setAnioNacimiento1(fechaNac.substring(8, 9));
//			aux.setAnioNacimiento2(fechaNac.substring(9, 10));
//		}else{
//			aux.setDiaNacimiento1("");
//			aux.setDiaNacimiento2("");
//			aux.setMesNacimiento1("");
//			aux.setMesNacimiento2("");
//			aux.setAnioNacimiento1("");
//			aux.setAnioNacimiento2("");
//		}
//		if(solicitudBean.getSexo() != ' '){
//			if(Constantes.SEXO_HOMBRE.equals(solicitudBean.getSexo().toString())){
//				aux.setSexoHombre("X");
//				aux.setSexoMujer("");
//			}else{
//				aux.setSexoHombre("");
//				aux.setSexoMujer("X");
//			}
//		}else{
//			aux.setSexoHombre("");
//			aux.setSexoMujer("");
//		}
//		if(solicitudBean.getNacionalidad() != null && !"".equals(solicitudBean.getNacionalidad())){
//			aux.setNacionalidad(solicitudBean.getNacionalidad());
//		}else{
//			aux.setNacionalidad("");
//		}
//		if(solicitudBean.getEmail() != null && !"".equals(solicitudBean.getEmail())){
//			aux.setCorreoElectronicos(solicitudBean.getEmail());
//		}else{
//			aux.setCorreoElectronicos("");
//		}
//		if(solicitudBean.getTelefono() != null && !"".equals(solicitudBean.getTelefono())){
//			if(solicitudBean.getTelefono().length()>10){
//				aux.setTelefono(solicitudBean.getTelefono().substring(0, 9));
//				aux.setTelefonoAux(solicitudBean.getTelefono().substring(10));
//			}else{
//				aux.setTelefono(solicitudBean.getTelefono());
//				aux.setTelefonoAux("");
//			}
//		}else{
//			aux.setTelefono("");
//			aux.setTelefonoAux("");
//		}
//
//		if(solicitudBean.getCalleDomicilio() != null && !"".equals(solicitudBean.getCalleDomicilio())){
//			aux.setCalleDomicilio(solicitudBean.getCalleDomicilio());
//		}else{
//			aux.setCalleDomicilio("");
//		}
//		if(solicitudBean.getCodigoPostalDomicilio() != null && !"".equals(solicitudBean.getCodigoPostalDomicilio())){
//			aux.setCodigoPostalDomicilio(solicitudBean.getCodigoPostalDomicilio());
//		}else{
//			aux.setCodigoPostalDomicilio("");
//		}
//		if(solicitudBean.getMunicipioDomicilio() != null && !"".equals(solicitudBean.getMunicipioDomicilio())){
//			aux.setMunicipioDomicilio(solicitudBean.getMunicipioDomicilio());
//		}else{
//			aux.setMunicipioDomicilio("");
//		}
//		if(solicitudBean.getDescripcionIdProvinciaDomicilio() != null && !"".equals(solicitudBean.getDescripcionIdProvinciaDomicilio())){
//			aux.setProvinciaDomicilio(solicitudBean.getDescripcionIdProvinciaDomicilio());
//		}else{
//			aux.setProvinciaDomicilio("");
//		}
//		if(solicitudBean.getNacionPaisDomicilio() != null && !"".equals(solicitudBean.getNacionPaisDomicilio())){
//			aux.setPais(solicitudBean.getNacionPaisDomicilio());
//		}else{
//			aux.setPais("");
//		}
//
//		// AutoLiquidacion
//		if(convocatoriaBean.getCuerpoEscala() != null && !"".equals(convocatoriaBean.getCuerpoEscala())){
//			aux.setCuerpoEscala(convocatoriaBean.getCuerpoEscala());
//		}else{
//			aux.setCuerpoEscala("");
//		}
//		if(solicitudBean.getDescripcionEspecialidad() != null && !"".equals(solicitudBean.getDescripcionEspecialidad())){
//			aux.setEspecialidad(solicitudBean.getDescripcionEspecialidad());
//		}else{
//			aux.setEspecialidad("");
//		}
//		if(convocatoriaBean.getFormaAcceso() != null && !"".equals(convocatoriaBean.getFormaAcceso())){
//			aux.setFormaAcceso(convocatoriaBean.getFormaAcceso());
//		}else{
//			aux.setFormaAcceso("");
//		}
//		//Ministerio Convocatoria
//		if(convocatoriaBean.getMinisterioConvocatoria() != null && !"".equals(convocatoriaBean.getMinisterioConvocatoria())){
//			aux.setMinisterioConvocatoria(convocatoriaBean.getMinisterioConvocatoria());
//		}else{
//			if(convocatoriaBean.getMinisterio() != null && !"".equals(convocatoriaBean.getMinisterio())){
//				aux.setMinisterioConvocatoria(convocatoriaBean.getMinisterio());
//			}else{
//			aux.setMinisterioConvocatoria("");
//			}
//		}
//			
//		if(convocatoriaBean.getFechaBoe() != null && !"".equals(convocatoriaBean.getFechaBoe())){
//			String fechaBoe = convocatoriaBean.getFechaBoe();
//			aux.setDiaFechaBoe1(fechaBoe.substring(0, 1));
//			aux.setDiaFechaBoe2(fechaBoe.substring(1, 2));
//			aux.setMesFechaBoe1(fechaBoe.substring(3, 4));
//			aux.setMesFechaBoe2(fechaBoe.substring(4, 5));
//			aux.setAnioFechaBoe1(fechaBoe.substring(8, 9));
//			aux.setAnioFechaBoe2(fechaBoe.substring(9, 10));
//		}else{
//			aux.setDiaFechaBoe1("");
//			aux.setDiaFechaBoe2("");
//			aux.setMesFechaBoe1("");
//			aux.setMesFechaBoe2("");
//			aux.setAnioFechaBoe1("");
//			aux.setAnioFechaBoe1("");
//		}
//		if(solicitudBean.getDescripcionIdProvinciaExamen() != null && !"".equals(solicitudBean.getDescripcionIdProvinciaExamen())){
//			aux.setProvinciaExamen(solicitudBean.getDescripcionIdProvinciaExamen());
//		}else{
//			aux.setProvinciaExamen("");
//		}
//		if(solicitudBean.getPorcentajeDiscapacidad() != null && !"".equals(solicitudBean.getPorcentajeDiscapacidad().toString())){
//			aux.setPorcentajeDiscapacidad(String.valueOf(solicitudBean.getPorcentajeDiscapacidad()));
//		}else{
//			aux.setPorcentajeDiscapacidad("");
//		}
//		if(solicitudBean.getReservaDiscapacidad() != null && solicitudBean.getReservaDiscapacidad() != ' ' ){
//			aux.setTipoDiscapacidad(solicitudBean.getReservaDiscapacidad()+"");
//		}else{
//			aux.setTipoDiscapacidad("");
//		}
//		if(solicitudBean.getDetalleDiscapacidad() != null && !"".equals(solicitudBean.getDetalleDiscapacidad())){
//			aux.setDetalleDiscapacidad(solicitudBean.getDetalleDiscapacidad());
//		}else{
//			aux.setDetalleDiscapacidad("");
//		}
//		
//		if(null != solicitudBean.getDescripcionTipoDiscapacidad()){
//			aux.setDetalleDiscapacidad(solicitudBean.getDescripcionTipoDiscapacidad());
//		}
//		
//		if(solicitudBean.getDescripcionTituloOficial() != null && !"".equals(solicitudBean.getDescripcionTituloOficial())){
//			aux.setTituloOficial(solicitudBean.getDescripcionTituloOficial());
//		}else{
//			aux.setTituloOficial("");
//		}
//		if(solicitudBean.getOtrosTitulos() != null && !"".equals(solicitudBean.getOtrosTitulos())){
//			aux.setOtrosTitulos(solicitudBean.getOtrosTitulos());
//		}else{
//			aux.setOtrosTitulos("");
//		}
//
//		// Campos propios
//		SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
//		solicitudComunQuery.setIdSolicitud(solicitudBean.getId());
//		SolicitudPropiosQuery solicitudPropiosQuery = new SolicitudPropiosQuery();
//		solicitudPropiosQuery.setSolicitudComun((solicitudComunQuery));
//		ArrayList<SolicitudPropiosBean> listaSolicitudPropios = solicitudesPropiosManager.obtenerCamposPropiosByIdSolicitud(solicitudPropiosQuery);
//
//		ArrayList<CamposPropiosBean> listaCamposPropios = new ArrayList<CamposPropiosBean>();
//
//		for (SolicitudPropiosBean solicitudPropiosBean : listaSolicitudPropios) {
//			CamposPropiosBean camposPropiosBean = new CamposPropiosBean();
//			camposPropiosBean.setCampo(solicitudPropiosBean.getCamposPropiosBean().getCampo());
//			camposPropiosBean.setValorVista(solicitudPropiosBean.getValor());
//			listaCamposPropios.add(camposPropiosBean);
//		}
//
//		aux.setListaCamposPropios(listaCamposPropios);
//
//		// Documentación asociada
//		ArrayList<DocumentoBean>listaDocumentos = new ArrayList<DocumentoBean>();
//
//		SolicitudComunQuery comunQuery = new SolicitudComunQuery();
//		comunQuery.setIdSolicitud(solicitudBean.getId());
//
//		DocumentoQuery documentosQuery = new DocumentoQuery();
//		documentosQuery.setSolicitudComun(comunQuery);
//
//		TipoDocumentoQuery tipoDocumentoQuery = new TipoDocumentoQuery();
//		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_ANEXO_SOLICITUD);
//		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_REDUCCION_TASA);
//		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_ADJUNTO);
//		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_DISCAPACIDAD);
//		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_FORMULARIO);
//		tipoDocumentoQuery.addIdIn(Constantes.TIPO_DOCUMENTO_ANEXO_CONVOCATORIA);
//		documentosQuery.setTipoDocumento(tipoDocumentoQuery);
//
//		ArrayList<DocumentoBean>listaDocumentosAux = documentoManager.buscarDocumentosByIdSolicitud(documentosQuery);
//
//		// Si no hay documentos anexos, generamos uno vacío para mejorar la apariencia del pdf.
//		if(listaDocumentosAux.isEmpty() ){
//			DocumentoBean docAnexo = new DocumentoBean();
//			docAnexo.setNombre(Constantes.SIN_DOCUMENTOS_ANEXOS);
//			docAnexo.setHashExtracto(Constantes.SIN_DOCUMENTOS_ANEXOS);
//			listaDocumentos.add(docAnexo);
//		}else{
//			for(int i=0; i<listaDocumentosAux.size(); i++){		
//				// Formulario html
//				if(listaDocumentosAux.get(i).getExtension().endsWith(Constantes.EXTENSION_FORMULARIO)){
//					aux.setHtmlRegistrado(listaDocumentosAux.get(i).getNombreAlfresco());
//					aux.setHtmlHash(listaDocumentosAux.get(i).getHashExtracto());
//					// Docs anexos
//				}else{
//					DocumentoBean docAnexo = new DocumentoBean();
//					docAnexo.setNombre(listaDocumentosAux.get(i).getNombre());
//					docAnexo.setHashExtracto(listaDocumentosAux.get(i).getHashExtracto());
//					listaDocumentos.add(docAnexo);
//				}
//			}
//		}
//
//		aux.setListaDocumentos(listaDocumentos);
//
//		// Datos del pago
//		if(altaSolicitudPresencialForm.getImporte()!=null && !"".equals(altaSolicitudPresencialForm.getImporte())){
//			aux.setImporte(altaSolicitudPresencialForm.getImporte());
//		}else{
//			aux.setImporte("");
//		}
//		if(altaSolicitudPresencialForm.getIdTipoPago() !=null && !"".equals(altaSolicitudPresencialForm.getIdTipoPago())){
//			if(altaSolicitudPresencialForm.getIdTipoPago().equals("2")){
//				aux.setTipoPago(Constantes.PAGO_TIPO_ADEUDO);
//			}
//			else if(altaSolicitudPresencialForm.getIdTipoPago().equals("1")){
//				aux.setTipoPago(Constantes.PAGO_TIPO_TARJETA);
//			}
//			else if(altaSolicitudPresencialForm.getIdTipoPago().equals("0")){
//				aux.setTipoPago(Constantes.PAGO_TIPO_EXENTO);
//			}else if(altaSolicitudPresencialForm.getIdTipoPago().equals("4")){
//				aux.setTipoPago(Constantes.PAGO_TIPO_EFECTIVO);
//			}else{
//				aux.setTipoPago("");
//			}
//		}else{
//			aux.setTipoPago("");
//		}
//		if(altaSolicitudPresencialForm.getIdMotivosEx()!=null && !"".equals(altaSolicitudPresencialForm.getIdMotivosEx())){
//			aux.setCausaExencion(altaSolicitudPresencialForm.getIdMotivosEx());
//			
////			if(altaSolicitudPresencialForm.getIdTipoPago().equals("2")){
////				aux.setTipoPago(Constantes.PAGO_TIPO_ADEUDO);
////			}
////			else if(altaSolicitudPresencialForm.getIdTipoPago().equals("1")){
////				aux.setTipoPago(Constantes.PAGO_TIPO_TARJETA);
////			}
////			else if(altaSolicitudPresencialForm.getIdTipoPago().equals("0")){
////				aux.setTipoPago(Constantes.PAGO_TIPO_EXENTO);
////			}else if(altaSolicitudPresencialForm.getIdTipoPago().equals("4")){
////				aux.setTipoPago(Constantes.PAGO_TIPO_EFECTIVO);
////			}else{
////				aux.setTipoPago("");
////			}
//			
//		}else{
//			aux.setCausaExencion("");
//		}
//		if(altaSolicitudPresencialForm.getFechaPago()!=null && !"".equalsIgnoreCase(altaSolicitudPresencialForm.getFechaPago())){
//			aux.setFechaPago(altaSolicitudPresencialForm.getFechaPago());
//		}else{
//			aux.setFechaPago(Constantes.NO_APLICA);
//		}
//		if(altaSolicitudPresencialForm.getNrcPago()!=null && !"".equals(altaSolicitudPresencialForm.getNrcPago())){
//			aux.setNrc(altaSolicitudPresencialForm.getNrcPago());
//		}else{
//			aux.setNrc(Constantes.NO_APLICA);
//		}
//		if(altaSolicitudPresencialForm.getIdEntidadBancaria()!=null && !"".equals(altaSolicitudPresencialForm.getIdEntidadBancaria())){
//			aux.setEntidad(altaSolicitudPresencialForm.getIdEntidadBancaria());
//		}else{
//			aux.setEntidad(Constantes.NO_APLICA);
//		}
//
//		if(null != solicitudBean.getNumModelo()){
//			aux.setModelo(solicitudBean.getNumModelo());
//		}
//		
//		return aux;
//	}
	
	/**
	 * Registra el pdf con csv incrustrado en la tabla documentos de bb.dd
	 *
	 * @param pdfasbytes - el contenido del fichero
	 * @param idSolicitud el id solicitud
	 * @param codigoCSV - un codigo CSV
	 * @return idDocumento - el id con el que se inserta el documento en la bb.dd
	 */
//	private long registroPdfCsvDocumentos(byte[] pdfasbytes, String idSolicitud, String codigoCSV) {
//
//		DocumentoBean docBean = new DocumentoBean(); 
//		Locale locale = (Locale) this.getRequest().getSession().getAttribute(Globals.LOCALE_KEY);
//		String localeName = locale.toString();
//		Calendar c = Calendar.getInstance();
//		int mes = c.get(Calendar.MONTH)+1;
//		int fileSize = pdfasbytes.length;
//		final String separador = File.separator;
//		
//		docBean.setNombre(Constantes.DESCRIPCIONSOLICITUDCSV);
//		docBean.setFechaCreacion(c.getTime());
//		
//		// id tipo de documento
//		docBean.setIdTipoDocumento(String.valueOf(Constantes.JUSTIFICANTE_REGISTRO_ID));
//		if (localeName.equals(Constantes.CATALAN)) {
//			docBean.setIdTipoDocumento(Constantes.JUSTIFICANTE_REGISTRO_ID_CATALAN);		             
//		}else if (localeName.equals(Constantes.EUSKERA)) {
//			docBean.setIdTipoDocumento(Constantes.JUSTIFICANTE_REGISTRO_ID_EUSKERA);	             
//		}else if (localeName.equals(Constantes.GALLEGO)) {
//			docBean.setIdTipoDocumento(Constantes.JUSTIFICANTE_REGISTRO_ID_GALLEGO);	             
//		}else if (localeName.equals(Constantes.VALENCIANO)) {
//			docBean.setIdTipoDocumento(Constantes.JUSTIFICANTE_REGISTRO_ID_VALENCIANO);
//		}
//		docBean.setDescripcion(Constantes.DESCRIPCIONSOLICITUDCSV + ".PDF");
//		docBean.setTamano(safeLongToInt(Math.round(fileSize/1024.0)));
//		docBean.setIdSolicitud(Long.parseLong(idSolicitud));
//		
//		// ubicacion
//		StringBuilder rutaDocumento = new StringBuilder();
//		rutaDocumento
//				.append(c.get(Calendar.YEAR)).append(separador)
//				.append(mes).append(separador)
//				.append(c.get(Calendar.DAY_OF_MONTH)).append(separador)
//				.append(c.get(Calendar.HOUR_OF_DAY)).append(separador)
//				.append(docBean.getIdSolicitud()).append(separador);
//	    docBean.setUbicacion(rutaDocumento.toString());
//		docBean.setCsv(codigoCSV);
//				
//		long idDocumento = 0L;
//		try{
//			idDocumento = documentoManager.insertarDocumentoCsv(docBean);
//			logger.info("Id Justificante con CSV incrustrado registrado: "+ idDocumento);
//		} catch(Exception e){
//			logger.info("error en la insercion", e);
//			logger.error("Error ReintentarRegistroSpring - Error en la insercion: ",e);
//		}
//		return idDocumento;
//	}
	
	/**
	 * Sube a filesystem el pdf con csv incrustrado.
	 *
	 * @param pdfasbytes - el contenido del fichero
	 * @param idDocumento - el id del documento en base de datos
	 * @return 0 si hay errores, 1 si todo ok
	 */
//	private int subirDocumentoPdfCSV(byte[] pdfasbytes, long idDocumento) {
//		byte[] fileData = pdfasbytes;
//		
//		// recupero el documentobean que voy a subir a filesystem
//		DocumentoQuery documentoQuery = new DocumentoQuery();
//		documentoQuery.setId(idDocumento);
//		DocumentoBean docBean = documentoManager.obtenerDocumento(documentoQuery);
//		
//		if (docBean!=null) {
//			// asigno los valores al documento bean necesarios para el metodo estatico de subida a filesystem
//			docBean.setUbicacion(docBean.getUbicacion());
//			docBean.setNombreAlfresco(docBean.getNombreAlfresco());
//			docBean.setContenidoDocumento(fileData);
//			
//			// almaceno en filesystem
//			SistemaFicheros ges= new SistemaFicheros();
//			try{
//				ges.insertarContenido(docBean, properties.getProperty("sistemaficheros.url.final"));
//			} catch(Exception e) {
//				logger.info("error insertando en filesystem", e);
//				logger.error("Error ReintentarRegistroSpring - Error insertando en filesystem: ",e);
//				try{
//					documentoManager.eliminarDocumentoById(idDocumento);
//				}catch(Exception o){
//					logger.error("Error ReintentarRegistroSpring - Error eliminar documento: ",o);
//					return 0;				
//				}
//				return 0;
//			}
//			return 1;	
//		} else {
//			logger.info("error insertando en filesystem");
//			return 0;
//		}
//	}
	
	/**
	 * To registro bean.
	 *
	 * @param altaSolicitudPresencialForm el alta solicitud presencial form
	 * @return el registro solicitud bean
	 */
	private RegistroSolicitudBean toRegistroBean(
			AltaSolicitudPresencialForm altaSolicitudPresencialForm) {
		RegistroSolicitudBean aux = new RegistroSolicitudBean();
		aux.setIdSolicitud(Long.parseLong(altaSolicitudPresencialForm.getId()));
		return aux;
	}
	
	/**
	 * Obtiene el logger.
	 *
	 * @return logger Logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Obtiene el solicitud presencial manager.
	 *
	 * @return  solicitudPresencialManager SolicitudPresencialManager
	 */
	public SolicitudPresencialManager getSolicitudPresencialManager() {
		return solicitudPresencialManager;
	}

	/**
	 * Establece el solicitud presencial manager.
	 *
	 * @param solicitudPresencialManager SolicitudPresencialManager
	 */
	public void setSolicitudPresencialManager(
			SolicitudPresencialManager solicitudPresencialManager) {
		this.solicitudPresencialManager = solicitudPresencialManager;
	}

	/**
	 * Obtiene el message resource.
	 *
	 * @return MESSAGE_RESOURCE
	 */
	public static String getMESSAGE_RESOURCE() {
		return MESSAGE_RESOURCE;
	}

	/**
	 * Obtiene el resource bundle.
	 *
	 * @return RESOURCE_BUNDLE
	 */
	public static ResourceBundle getRESOURCE_BUNDLE() {
		return RESOURCE_BUNDLE;
	}

	/**
	 * Obtiene el pago solicitud manager.
	 *
	 * @return pagoSolicitudManager PagoSolicitudManager
	 */
	public PagoSolicitudManager getPagoSolicitudManager() {
		return pagoSolicitudManager;
	}

	/**
	 * Establece el pago solicitud manager.
	 *
	 * @param pagoSolicitudManager PagoSolicitudManager
	 */
	public void setPagoSolicitudManager(PagoSolicitudManager pagoSolicitudManager) {
		this.pagoSolicitudManager = pagoSolicitudManager;
	}

	/**
	 * Obtiene el registro solicitud manager.
	 *
	 * @return registroSolicitudManager RegistroSolicitudManager
	 */
	public RegistroSolicitudManager getRegistroSolicitudManager() {
		return registroSolicitudManager;
	}

	/**
	 * Establece el registro solicitud manager.
	 *
	 * @param registroSolicitudManager RegistroSolicitudManager
	 */
	public void setRegistroSolicitudManager(
			RegistroSolicitudManager registroSolicitudManager) {
		this.registroSolicitudManager = registroSolicitudManager;
	}

	/**
	 * Obtiene el log solicitud manager.
	 *
	 * @return el log solicitud manager
	 */
	public LogSolicitudManager getLogSolicitudManager() {
		return logSolicitudManager;
	}

	/**
	 * Establece el log solicitud manager.
	 *
	 * @param logSolicitudManager el nuevo log solicitud manager
	 */
	public void setLogSolicitudManager(LogSolicitudManager logSolicitudManager) {
		this.logSolicitudManager = logSolicitudManager;
	}

	/**
	 * Obtiene el solicitud propios manager.
	 *
	 * @return el solicitud propios manager
	 */
	public SolicitudesPropiosManager getSolicitudPropiosManager() {
		return solicitudPropiosManager;
	}

	/**
	 * Establece el solicitud propios manager.
	 *
	 * @param solicitudPropiosManager el nuevo solicitud propios manager
	 */
	public void setSolicitudPropiosManager(
			SolicitudesPropiosManager solicitudPropiosManager) {
		this.solicitudPropiosManager = solicitudPropiosManager;
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

	/**
	 * Obtiene el motivo reduccion tasa manager.
	 *
	 * @return el motivo reduccion tasa manager
	 */
	public MotivoReduccionTasaManager getMotivoReduccionTasaManager() {
		return motivoReduccionTasaManager;
	}

	/**
	 * Establece el motivo reduccion tasa manager.
	 *
	 * @param motivoReduccionTasaManager el nuevo motivo reduccion tasa manager
	 */
	public void setMotivoReduccionTasaManager(
			MotivoReduccionTasaManager motivoReduccionTasaManager) {
		this.motivoReduccionTasaManager = motivoReduccionTasaManager;
	}

	/**
	 * Obtiene el solicitud ccaa manager.
	 *
	 * @return el solicitud ccaa manager
	 */
	public SolicitudCcaaManager getSolicitudCcaaManager() {
		return solicitudCcaaManager;
	}

	/**
	 * Establece el solicitud ccaa manager.
	 *
	 * @param solicitudCcaaManager el nuevo solicitud ccaa manager
	 */
	public void setSolicitudCcaaManager(SolicitudCcaaManager solicitudCcaaManager) {
		this.solicitudCcaaManager = solicitudCcaaManager;
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
	public void setParametroConfiguracionManager(ParametroConfiguracionManager parametroConfiguracionManager) {
		this.parametroConfiguracionManager = parametroConfiguracionManager;
	}	

	/**
	 * Obtiene el tipo documento manager.
	 *
	 * @return el tipo documento manager
	 */
	public TipoDocumentoManager getTipoDocumentoManager() {
		return tipoDocumentoManager;
	}

	/**
	 * Establece el tipo documento manager.
	 *
	 * @param tipoDocumentoManager el nuevo tipo documento manager
	 */
	public void setTipoDocumentoManager(TipoDocumentoManager tipoDocumentoManager) {
		this.tipoDocumentoManager = tipoDocumentoManager;
	}

	/**
	 * Obtiene el pais manager.
	 *
	 * @return el pais manager
	 */
	public PaisManager getPaisManager() {
		return paisManager;
	}

	/**
	 * Establece el pais manager.
	 *
	 * @param paisManager el nuevo pais manager
	 */
	public void setPaisManager(PaisManager paisManager) {
		this.paisManager = paisManager;
	}
	
	/**
	 * Obtiene el provincia manager.
	 *
	 * @return el provincia manager
	 */
	public ProvinciaManager getProvinciaManager() {
		return provinciaManager;
	}

	/**
	 * Establece el provincia manager.
	 *
	 * @param provinciaManager el nuevo provincia manager
	 */
	public void setProvinciaManager(ProvinciaManager provinciaManager) {
		this.provinciaManager = provinciaManager;
	}

	/**
	 * Obtiene el solicitudes manager.
	 *
	 * @return el solicitudes manager
	 */
	public SolicitudesManager getSolicitudesManager() {
		return solicitudesManager;
	}

	/**
	 * Establece el solicitudes manager.
	 *
	 * @param solicitudesManager el nuevo solicitudes manager
	 */
	public void setSolicitudesManager(SolicitudesManager solicitudesManager) {
		this.solicitudesManager = solicitudesManager;
	}
	
	/**
	 * Obtiene el solicitudes propios manager.
	 *
	 * @return el solicitudes propios manager
	 */
	public SolicitudesPropiosManager getSolicitudesPropiosManager() {
		return solicitudesPropiosManager;
	}

	/**
	 * Establece el solicitudes propios manager.
	 *
	 * @param solicitudesPropiosManager el nuevo solicitudes propios manager
	 */
	public void setSolicitudesPropiosManager(
			SolicitudesPropiosManager solicitudesPropiosManager) {
		this.solicitudesPropiosManager = solicitudesPropiosManager;
	}

	/**
	 * Obtiene el log servicio manager.
	 *
	 * @return el log servicio manager
	 */
	public LogServicioManager getLogServicioManager() {
		return logServicioManager;
	}

	/**
	 * Establece el log servicio manager.
	 *
	 * @param logServicioManager el nuevo log servicio manager
	 */
	public void setLogServicioManager(LogServicioManager logServicioManager) {
		this.logServicioManager = logServicioManager;
	}
	
}
