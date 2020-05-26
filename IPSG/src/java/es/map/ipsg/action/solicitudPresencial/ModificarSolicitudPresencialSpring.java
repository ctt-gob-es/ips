package es.map.ipsg.action.solicitudPresencial;

import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.axis.attachments.OctetStream;
import org.apache.log4j.Logger;
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
import es.map.ips.model.SolicitudCcaaQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.SolicitudPropiosQuery;
import es.map.ips.model.TipoDiscapacidad;
import es.map.ips.model.TipoSolicitud;
import es.map.ips.model.TituloOficial;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.CorreoElectronicoBean;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.GenerarJustificanteBean;
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
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.EnvioMails;
import es.map.ipsg.util.IpsUtils;
import es.map.ipsg.util.SHA0;
import es.map.ipsg.util.Util;
import es.map.ipsg.util.UtilesIPSG;

/**
 * Accion ModificarSolicitudPresencialAction.
 *
 * @author mlopgarc
 */
public class ModificarSolicitudPresencialSpring extends AbstractSpring {

	/** el solicitud presencial manager. */
	private SolicitudPresencialManager solicitudPresencialManager;
	
	/** el solicitud manager. */
	private SolicitudesRegistradasManager solicitudManager;
	
	/** el solicitud propios manager. */
	private SolicitudesPropiosManager solicitudPropiosManager;
	
	/** el campos propios manager. */
	private CamposPropiosManager camposPropiosManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el motivo reduccion tasa manager. */
	private MotivoReduccionTasaManager motivoReduccionTasaManager;
	
	/** el registro solicitud manager. */
	private RegistroSolicitudManager registroSolicitudManager;
	
	/** el solicitud ccaa manager. */
	private SolicitudCcaaManager solicitudCcaaManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el log solicitud manager. */
	private LogSolicitudManager logSolicitudManager;
	
	/** el pais manager. */
	private PaisManager paisManager;
	
	/** el provincia manager. */
	private ProvinciaManager provinciaManager;
	
	/** el parametro configuracion manager. */
	private ParametroConfiguracionManager parametroConfiguracionManager;
	
	/** el solicitudes manager. */
	private SolicitudesManager solicitudesManager;
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/** el log servicio manager. */
	private LogServicioManager logServicioManager;
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(MESSAGE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger
			.getLogger(ModificarSolicitudPresencialSpring.class);
	
	/** el properties. */
	private static Properties properties;
	
	/** La constante STRING_CDASUNTO. */
	private static final String STRING_CDASUNTO = "cdAsunto";
	
	/** La constante TIMEOUT. */
	private static final int TIMEOUT = 30000;
	
	/** La constante STRING_FIELD_SOLICITUD_REGISTRO_ERRORREALIZACION. */
	private static final String STRING_FIELD_SOLICITUD_REGISTRO_ERRORREALIZACION = "field.solicitud.registro.errorRealizacion";
	
	/** La constante STRING_SIMPLEDATEFORMAT_HHMMSS. */
	private static final String STRING_SIMPLEDATEFORMAT_HHMMSS = "dd/MM/yyyy HH:mm:ss";
	
	/** La constante STRING_REGISTRO. */
	private static final String STRING_REGISTRO = "registro";
	
	/** La constante STRING_MENSAJEREGISTRO. */
	private static final String STRING_MENSAJEREGISTRO = "mensajeRegistro";
	
	/** La constante STRING_HTML. */
	private static final String STRING_HTML = ".html";
	
	/** La constante STRING_ERROR_HASHFILE. */
	private static final String STRING_ERROR_HASHFILE = "Error hashFile";
	
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
	
	/** La constante STRING_ERRORDESCRIPCION. */
	private static final String STRING_ERRORDESCRIPCION = "errorDescripcion";
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/** La constante STRING_NOSUCCESS. */
	private static final String STRING_NOSUCCESS = "nosuccess";
	
	/** La constante STRING_CERTIFICADOS_ERROR_GESTORDOCUMENTAL. */
	private static final String STRING_CERTIFICADOS_ERROR_GESTORDOCUMENTAL = "certificados.error.gestorDocumental";
	
	/** La constante STRING_CORRECTO. */
	private static final String STRING_CORRECTO = "correcto";

	/**
	 * Accion AltaSolicitudPresencialAction.
	 */
	public ModificarSolicitudPresencialSpring() {
		try {
			setSolicitudPresencialManager((SolicitudPresencialManager) getBean("solicitudPresencialManager"));
			setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudManager"));
			setLogSolicitudManager((LogSolicitudManager) getBean("logSolicitudManager"));
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setRegistroSolicitudManager((RegistroSolicitudManager) getBean("registroSolicitudManager"));
			setSolicitudesRegistradasManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
			setSolicitudPropiosManager((SolicitudesPropiosManager)getBean("solicitudesPropiosManager"));
			setCamposPropiosManager((CamposPropiosManager)getBean("camposPropiosManager"));
			setMotivoReduccionTasaManager((MotivoReduccionTasaManager)getBean("motivoReduccionTasaManager"));
			setSolicitudCcaaManager((SolicitudCcaaManager) getBean("solicitudCcaaManager"));
			setPaisManager((PaisManager)getBean("paisManager"));
			setProvinciaManager((ProvinciaManager)getBean("provinciaManager"));
			setParametroConfiguracionManager((ParametroConfiguracionManager)getBean("parametroConfiguracionManager"));
			setSolicitudesManager((SolicitudesManager) getBean("solicitudesManager"));
			setDocumentoManager((DocumentoManager)getBean("documentoManager"));
			setConvocatoriasManager((ConvocatoriasManager)getBean("convocatoriaManager"));
			setLogServicioManager((LogServicioManager)getBean("logServicioManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.error("Error ModificarSolicitudPresencialSpring ",e);
		}
	}

	/**
	 * Metodo doExecute de ModificarSolicitudPresencialAction.
	 *
	 * @param form            SpringForm Pasa los campos del formulario
	 * @return resultado String Si todo va bien devuelve success
	 * @throws Exception             Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_solPresencial = RESOURCE_BUNDLE.getString("field.menu.solicPresenciales");
		this.getRequest().getSession().setAttribute("pagActiva", menu_solPresencial);
		String subMenu_SolPresen = RESOURCE_BUNDLE.getString("field.menuLateral.solicitudesPresenciales.buscar");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_SolPresen);
		
		
		AltaSolicitudPresencialForm formulario = (AltaSolicitudPresencialForm) form;
		SolicitudBean solicitudBean = new SolicitudBean();
		SolicitudCcaaBean solicitudCcaaBean = new SolicitudCcaaBean();
		Provincia provincia = new Provincia();
		ProvinciaExamen provinciaExamen = new ProvinciaExamen();
		UtilesIPSG utilesIPSG = new UtilesIPSG();
		String resultado = "";
		
	try{
		logger.info("ModificarSolicitudPresencialSpring -start");

		if (formulario.getAccion().equals("VOLVER")) {
			resultado = "list";
		} else {
			
			//extraemos los datos de los campos propios introducidos para modificar los campos propios de la solicitud
			CamposPropiosBean[] listaCamposPropiosBean=formulario.getListaTextAreas();
			ArrayList<String> listaCamposAreaText = new ArrayList<String>();
			if(listaCamposPropiosBean!=null){
				for(CamposPropiosBean campoPropio : listaCamposPropiosBean){
					Long idCampo = campoPropio.getId();
					listaCamposAreaText.add(idCampo+"_"+campoPropio.getCampo());//formato de la lista: idCampo_ValorCampo
				}
			}

			Date dHoy = new Date();
			// Campos Fijos:
			// Tipo: Presencial
			TipoSolicitud tipoSolicitud = new TipoSolicitud();
			tipoSolicitud.setId(Constantes.TIPO_SOLICITUD_PRESENCIAL);
			solicitudBean.setTipo(tipoSolicitud);
			// Estado: Registrada
			EstadoSolicitud estadoSolicitud = new EstadoSolicitud();
			estadoSolicitud.setId(Constantes.ESTADO_SOLICITUD_REGISTRADO);
			solicitudBean.setEstadoSolicitud(estadoSolicitud);
			//IDModelo:
			if (listaCamposPropiosBean != null && listaCamposPropiosBean.length > 0 && Integer.toString(Constantes.ID_MODELO_790007).equals(listaCamposPropiosBean[0].getIdModelo())){
				solicitudBean.setIdModelo(Integer.toString(Constantes.ID_MODELO_790007));
			}
			else{
				solicitudBean.setIdModelo(Integer.toString(Constantes.ID_MODELO_790001));
			}
			// Fecha ultima Actualizacion: Hoy
			solicitudBean.setFechaUtlActualizacion(dHoy);

			// Campos del FORMULARIO
			// Se recuperan todos los datos del formulario
			// Datos de Convocatoria
			solicitudBean.setId(Long.parseLong(formulario.getId()));
			solicitudBean.setNumeroSolicitud(formulario.getNumeroSolicitud());
			if (formulario.getIdConvocatoria() != null)
			{
				Convocatoria convocatoria = new Convocatoria();
				convocatoria.setId(Long.valueOf(formulario.getIdConvocatoria()));
				convocatoria.setEjercicio((formulario.getEjercicio() != null)?formulario.getEjercicio():"");
				solicitudBean.setConvocatoria(convocatoria);
				solicitudBean.setEjercicio((formulario.getEjercicio() != null)?formulario.getEjercicio():"");
			}
			// Datos de Usuario
			solicitudBean.setNif(formulario.getNif());
			solicitudBean.setNombre(formulario.getNombre());
			solicitudBean.setPrimerApellido(formulario.getPrimerApellido());
			solicitudBean.setSegundoApellido(formulario.getSegundoApellido());

			solicitudBean.setFechaNacimiento(utilesIPSG.stringToDate(formulario.getFechaNacimiento()));
			solicitudBean.setLocalidadNacimiento(formulario.getLocalidadNacimiento());
			solicitudBean.setNacionalidad(formulario.getNacionalidad());
			solicitudBean.setIdConsentimiento(formulario.getCkConsentimiento());
			if (formulario.getIdProvinciaNacimiento() != null && !formulario.getIdProvinciaNacimiento().equals(""))
			{
				provincia.setId(Integer.valueOf(formulario.getIdProvinciaNacimiento()));
				solicitudBean.setProvinciaByIdProvinciaNacimiento(provincia);
			}
			solicitudBean.setSexo(utilesIPSG.stringToCharPos(formulario.getSexo(), 0));
			solicitudBean.setEmail(formulario.getEmail());
			// Datos Domicilio
			solicitudBean.setCalleDomicilio(formulario.getCalleDomicilio());
			solicitudBean.setMunicipioDomicilio(formulario.getMunicipioDomicilio());

			if (formulario.getIdPais() != null && !formulario.getIdPais().equals(""))
			{
				Pais pais = new Pais();
				pais.setId(Integer.valueOf(formulario.getIdPais()));
				solicitudBean.setPais(pais);
			}

			if (formulario.getIdProvinciaDomicilio() != null && !formulario.getIdProvinciaDomicilio().equals(""))
			{
				provincia = new Provincia();
				provincia.setId(Integer.valueOf(formulario.getIdProvinciaDomicilio()));
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

			// Datos Solicitud
			if (formulario.getIdEspecialidad() != null && !formulario.getIdEspecialidad().equals(""))
			{
				Especialidad especialidad = new Especialidad();
				especialidad.setId(Integer.valueOf(formulario.getIdEspecialidad()));
				solicitudBean.setEspecialidad(especialidad);
			}
			if (formulario.getIdProvinciaExamen() != null && !formulario.getIdProvinciaExamen().equals(""))
			{
				provinciaExamen = new ProvinciaExamen();
				provinciaExamen.setId(Integer.valueOf(formulario.getIdProvinciaExamen()));
				solicitudBean.setProvinciaByIdProvinciaExamen(provinciaExamen);
			}
			
			solicitudBean.setFechaSolicitud(formulario.getFechaRegistro());
			solicitudBean.setEjercicio(formulario.getEjercicio());

			if (formulario.getIdTipoDiscapacidad() != null && !formulario.getIdTipoDiscapacidad().equals(""))
			{
				TipoDiscapacidad tipoDiscapacidad = new TipoDiscapacidad();
				tipoDiscapacidad.setId(Integer.valueOf(formulario.getIdTipoDiscapacidad()));
				solicitudBean.setTipoDiscapacidad(tipoDiscapacidad);
			}
			if (formulario.getPorcentajeDiscapacidad() != null && !formulario.getPorcentajeDiscapacidad().equals(""))
			{
				solicitudBean.setPorcentajeDiscapacidad(Integer.valueOf(formulario.getPorcentajeDiscapacidad()));
			} 
			else
			{
				solicitudBean.setPorcentajeDiscapacidad(0);
			}

			if (formulario.getCkReservaDiscapacidad() != null && formulario.isCkReservaDiscapacidad())
			{
				solicitudBean.setReservaDiscapacidad('S');
			}
			else
			{
				solicitudBean.setReservaDiscapacidad('N');
			}			
			solicitudBean.setDetalleDiscapacidad(formulario.getAdaptacionDiscapacidad());
			if (formulario.getIdTitulo() != null && !formulario.getIdTitulo().equals(""))
			{
				TituloOficial tituloOficial = new TituloOficial();
				tituloOficial.setId(Integer.valueOf(formulario.getIdTitulo()));
				solicitudBean.setTituloOficial(tituloOficial);
			}

			solicitudBean.setOtrosTitulos(formulario.getOtrosTitulos());
			// Datos a Consignar
			solicitudBean.setDatosA(formulario.getDatosA());
			solicitudBean.setDatosB(formulario.getDatosB());
			solicitudBean.setDatosC(formulario.getDatosC());
			
			// Datos de Pago
			PagoSolicitudBean pagoSolicitudBean = new PagoSolicitudBean();
			boolean datosPagoSolicitud = false;
			if (formulario.getIdPagoSolicitud() != null && !"".equals(formulario.getIdPagoSolicitud()))
			{
				pagoSolicitudBean.setId(Long.parseLong(formulario.getIdPagoSolicitud()));
			}
			if (formulario.getIdTipoPago() != null && !formulario.getIdTipoPago().equals(""))
			{
				pagoSolicitudBean.setTipo(utilesIPSG.stringToCharPos(formulario.getIdTipoPago(), 0));
				datosPagoSolicitud = true;
			}
			if (formulario.getFechaPago() != null && !formulario.getFechaPago().equals(""))
			{
				pagoSolicitudBean.setFechaIntento(utilesIPSG.stringToDate(formulario.getFechaPago()));
				datosPagoSolicitud = true;
			}
			else{
				pagoSolicitudBean.setFechaIntento(new Date());
				datosPagoSolicitud = true;
			}
			if (formulario.getImporte() != null && !formulario.getImporte().equals(""))
			{
				pagoSolicitudBean.setImporte(Float.valueOf(formulario.getImporte()));
				datosPagoSolicitud = true;
			}
			
            // Obtenemos el identificador de los motivos de exencion de tasa
			if(formulario.getIdMotivosEx() != null && !formulario.getIdMotivosEx().equals("")){
                MotivoReduccionTasa motivoReduccionTasa;
				MotivoReduccionTasaBean motivoReduccionTasaBean;
				motivoReduccionTasaBean = motivoReduccionTasaManager.obtenerMotivoReduccionTasa(Integer.valueOf(formulario.getIdMotivosEx()));
				if(null !=motivoReduccionTasaBean){
				motivoReduccionTasa=motivoReduccionTasaManager.toMotivoReduccionTasa(motivoReduccionTasaBean);
				pagoSolicitudBean.setMotivoReduccionTasa(motivoReduccionTasa);	
				}
				datosPagoSolicitud = true;
			}
			
            // Obtenemos el identificador de los motivos de reduccion
			if(formulario.getIdMotivosRed() != null && !formulario.getIdMotivosRed().equals("")){
                MotivoReduccionTasa motivoReduccionTasa;
				MotivoReduccionTasaBean motivoReduccionTasaBean;
				motivoReduccionTasaBean = motivoReduccionTasaManager.obtenerMotivoReduccionTasa(Integer.valueOf(formulario.getIdMotivosRed()));
				if(null !=motivoReduccionTasaBean){
				motivoReduccionTasa=motivoReduccionTasaManager.toMotivoReduccionTasa(motivoReduccionTasaBean);
				pagoSolicitudBean.setMotivoReduccionTasa(motivoReduccionTasa);	
				}
				datosPagoSolicitud = true;
			}
			if (formulario.getIdEntidadBancaria() != null && !formulario.getIdEntidadBancaria().equals(""))
			{
				EntidadFinanciera entidadFinanciera = new EntidadFinanciera();
				entidadFinanciera.setId(Integer.valueOf(formulario.getIdEntidadBancaria()));
				pagoSolicitudBean.setEntidadFinanciera(entidadFinanciera);
				datosPagoSolicitud = true;
			}
			if (formulario.getNrcPago() != null && !formulario.getNrcPago().equals(""))
			{
				pagoSolicitudBean.setNrc(formulario.getNrcPago());
				datosPagoSolicitud = true;
			}
			
			// Guardamos los datos de la Comunidad Autonoma 
			
			// Comunidad Autonoma debido a discapacidad
			if(formulario.getIdMotivosEx().equals(Constantes.MOTIVO_DISCAPACIDAD)){
			if(formulario.getComunidadDD() !=null && !formulario.getComunidadDD().equals("")){
				solicitudCcaaBean.setIdComunidadDD(formulario.getComunidadDD());
				if(formulario.getIdProvinciaDomicilio()!=null && !formulario.getIdProvinciaDomicilio().equals("")){
				provincia = new Provincia();
				provincia.setId(Integer.valueOf(formulario.getIdProvinciaDomicilio()));
				solicitudCcaaBean.setProvincia(provincia);
				}
			}
			}else if (formulario.getIdMotivosEx().equals(Constantes.MOTIVO_FNUMEROSAESPECIAL) || formulario.getIdMotivosRed().equals(Constantes.MOTIVO_FNUMEROSAGENERAL) ){
			
			//Comunidad Autonoma debido a Familia Numerosa
			if(formulario.getComunidadFN() !=null && !formulario.getComunidadFN().equals("")){
				solicitudCcaaBean.setIdComunidadFN(formulario.getComunidadFN());
			}
						// Numero de Titulo de Familia Numerosa
			if(formulario.getNumeroTituloFN() !=null && !formulario.getNumeroTituloFN().equals("")){
				solicitudCcaaBean.setTituloFamnumerosa(formulario.getNumeroTituloFN());
			}
			}

			// Datos de Registro
			RegistroSolicitudBean registroSolicitudBean = new RegistroSolicitudBean();
			boolean datosRegistroSolicitud = false;
			if (formulario.getIdRegistroSolicitud() != null && !"".equals(formulario.getIdRegistroSolicitud()))
			{
				registroSolicitudBean.setId(Long.parseLong(formulario.getIdRegistroSolicitud()));
			}

			if(formulario.getNumeroRegistro() != null && !formulario.getNumeroRegistro().equals(""))
			{	
				registroSolicitudBean.setNumeroRegistro(formulario.getNumeroRegistro());
				datosRegistroSolicitud = true;
			}else
			{
				// si no viene informado el numero de registro,se coge por defecto
				registroSolicitudBean.setNumeroRegistro(properties.getProperty("numeroRegistro"));
				datosRegistroSolicitud = true;
			}
			
			if (formulario.getFechaRegistro() != null && !"".equals(formulario.getFechaRegistro()))
			{
				registroSolicitudBean.setFechaRegistro(utilesIPSG.stringToDate(formulario.getFechaRegistro()));
				datosRegistroSolicitud = true;
			}
						
			if(formulario.getOficinaRegistro() != null && !formulario.getOficinaRegistro().equals(""))
			{	
				registroSolicitudBean.setOficinaRegistro(formulario.getOficinaRegistro());
				datosRegistroSolicitud = true;
			}
			else
			{
				// si la oficina de registro no viene informada, se coge por defecto
				registroSolicitudBean.setOficinaRegistro(properties.getProperty("cdOrOrigen"));
				datosRegistroSolicitud = true;
			}
			
			
			if ((formulario.getIdRegistroSolicitud() != null && !"".equals(formulario.getIdRegistroSolicitud())) || datosRegistroSolicitud == true)
			{
				// Anyadimos los datos constantes
				registroSolicitudBean.setSolicitante(Constantes.CIUDADANO);
				registroSolicitudBean.setFechaIntento(dHoy);
			}
			
			// Se guarda la Solicitud
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			solicitudManager.modificarSolicitudRegistrada(solicitudBean);
			
			//se modifica Solicitud Propios
			SolicitudPropiosQuery solicitudPropiosQuery = new SolicitudPropiosQuery();			
			SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
			solicitudComunQuery.setIdSolicitud(Long.valueOf(solicitudBean.getId()));
			solicitudPropiosQuery.setSolicitudComun(solicitudComunQuery);
			
			ArrayList<SolicitudPropiosBean> listaSolicitudPropiosBean = 
					solicitudPropiosManager.obtenerCamposPropiosByIdSolicitud(solicitudPropiosQuery);
			if(listaSolicitudPropiosBean!=null){
				for(int i=0;i<listaSolicitudPropiosBean.size();i++){
					
					Long idCampoSolicitud = listaSolicitudPropiosBean.get(i).getIdCampo();
					//guardamos tb el correspondiente registro en solicitud_propios
					for(int j=0;j<listaCamposAreaText.size();j++){
						SolicitudPropiosBean solicitudPropiosBean;
						String listaAreaText = listaCamposAreaText.get(j);
						int tam = listaAreaText.length();
						int position=listaAreaText.indexOf('_');
						Long idCampo=Long.valueOf(listaAreaText.substring(0, position));
						if(idCampo==idCampoSolicitud){
							solicitudPropiosBean=listaSolicitudPropiosBean.get(i);
							solicitudPropiosBean.setValor(listaAreaText.substring(position+1,tam));
							solicitudPropiosBean.setIdSolicitud(Long.valueOf(solicitudBean.getId()));
							CamposPropiosQuery camposPropiosQuery = new CamposPropiosQuery();
							camposPropiosQuery.setIdCampo(idCampo.intValue());
							CamposPropiosBean camposPropiosBean = camposPropiosManager.buscarCamposPropiosUnico(camposPropiosQuery);
							solicitudPropiosBean.setCamposPropiosBean(camposPropiosBean);
							solicitudPropiosManager.modificarSolicitudPropiosBean(solicitudPropiosBean);
						}
					}
				}
			}
			if(listaSolicitudPropiosBean!=null){
				setRequestAttribute("listaSolicitudPropiosBean",listaSolicitudPropiosBean);
			}

			// Modificamos los valores de Solicitud_CCAA
			SolicitudCcaaQuery solicitudCcaaQuery = new SolicitudCcaaQuery ();
			solicitudCcaaQuery.setSolicitudComun(solicitudComunQuery);
			// Obtenemos los valores que tenemos almacenados en la tabla de Solicitud_CCAA con ese IdSolicitud
			SolicitudCcaaBean solicitudCcaaBeanAux=solicitudCcaaManager.obtenerSolicitudCcaaByIdSolicitud(solicitudCcaaQuery);
			// Modificamos los valores de la solicitud
			if(solicitudCcaaBeanAux!=null){
			solicitudCcaaBean.setIdSolicitudCcaa(solicitudCcaaBeanAux.getIdSolicitudCcaa());
			solicitudCcaaBean.setIdSolicitud(solicitudCcaaBeanAux.getIdSolicitud());
			solicitudCcaaManager.modificarSolicitudCcaaBean(solicitudCcaaBean);
			}else{
				// Si no tenemos ningun dato asociado a ese id de solicitud lo guardamos
				solicitudCcaaManager.guardarSolicitudCcaa(solicitudCcaaBean);
			}
			
			// Se dan de alta o modifican en Pago_Solicitud y Registro Solicitud
			SolicitudComun solicitud = new SolicitudComun();
			solicitud.setIdSolicitud(Long.parseLong(formulario.getId()));
			pagoSolicitudBean.setResultado(Constantes.RESULTADO_OK);
			pagoSolicitudBean.setSolicitud(solicitud);
			if ((formulario.getIdPagoSolicitud() == null || "".equals(formulario.getIdPagoSolicitud())) && datosPagoSolicitud == true)
			{
				pagoSolicitudManager.guardarPagoSolicitudBean(pagoSolicitudBean);
			}
			else if (formulario.getIdPagoSolicitud() != null && !"".equals(formulario.getIdPagoSolicitud()) && datosPagoSolicitud == true)
			{
				pagoSolicitudManager.modificarPagoSolicitudBean(pagoSolicitudBean);
			}
			// Si hemos eliminado todos los campo borramos el registro
			else if (formulario.getIdPagoSolicitud() != null && !"".equals(formulario.getIdPagoSolicitud()) && datosPagoSolicitud == false)
			{
				pagoSolicitudManager.eliminarPagoSolicitud(Long.parseLong(formulario.getIdPagoSolicitud()));
			}
			
			// Damos de alta o modificamos el Registro de Solicitud.
			registroSolicitudBean.setSolicitud(solicitud);
			registroSolicitudBean.setResultado(Constantes.RESULTADO_OK);
			registroSolicitudBean.setIdSolicitud(solicitud.getIdSolicitud());
			if ((formulario.getIdRegistroSolicitud() == null || "".equals(formulario.getIdRegistroSolicitud())) )
			{
				registroSolicitudManager.guardarRegistroSolicitud(registroSolicitudBean);
			}
			else if (formulario.getIdRegistroSolicitud() != null && !"".equals(formulario.getIdRegistroSolicitud()) )
			{
				registroSolicitudManager.modificarRegistroSolicitud(registroSolicitudBean);
			}			

			ArrayList<PagoSolicitudBean> arrPagoSolicitudBean = new ArrayList<PagoSolicitudBean>();
			arrPagoSolicitudBean.add(pagoSolicitudBean);
			solicitudBean.setPagoSolicitudes(arrPagoSolicitudBean);	
			
			ArrayList<RegistroSolicitudBean> arrRegistroSolicitudes = new ArrayList<RegistroSolicitudBean> ();
			arrRegistroSolicitudes.add(registroSolicitudBean);
			solicitudBean.setRegistroSolicitudes(arrRegistroSolicitudes);			
			generarRegistroLogSolicitud(solicitudBean, usuarioBean.getLogin(), Long.parseLong(formulario.getId())); 			
			
			String mensaje;
			if(formulario.getAccion().equals("MODIFICAR")){
				resultado = "success";
				mensaje = RESOURCE_BUNDLE.getString("field.solicitudPresencial.mensaje.confirmacionMoficiacionPresencial");
				String titulo = RESOURCE_BUNDLE.getString("field.solicitudPresencial.tituloModificacion");
	
				setRequestAttribute("titulo", titulo);
				setRequestAttribute("mensaje", mensaje);
				setRequestAttribute("accion", "/spring/buscarSolicitudPresencial?accion=Modificar");
				logger.info("ModificarSolicitudPresencialSpring -end " + resultado);
			}else if(formulario.getAccion().equals("REGISTRARREC")){
				
				// Se realiza el registro de la solicitud en REC
				RegistroType registroType = this.crearRegistroType(formulario, solicitudBean);
				
				ConvocatoriasBean convocatoriaBean = convocatoriasManager.recuperarConvocatoria(Long.parseLong(formulario.getIdConvocatoria()));
				//Comprobar si el usuario esta en la sesion
				//Variable para pruebas de estress
				String convocatoriaRepetida_Unico = properties.getProperty("altaSolicitud.inscripcionMultiple_Unica");
				formulario.setDocumentoHTML(crearHTML(convocatoriaBean, solicitudBean, pagoSolicitudBean, formulario, convocatoriaRepetida_Unico));
				String resulSubirDoc = subirDocumentoRegistro(formulario);
				logger.info("RegistroSolicitudSpring-Subida Doc Registro: "+resulSubirDoc);

				if(null!=resulSubirDoc && resulSubirDoc.equals(STRING_ERROR)){
					this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("field.javascript.errorFirma"));
					logger.info("5. Resultado: successRegistro");
					resultado="successRegistro";	
					mensaje = RESOURCE_BUNDLE.getString("field.javascript.errorFirma");
					this.setRequestAttribute(STRING_MENSAJEREGISTRO, mensaje);
					return resultado;
				}
				
				OctetStream[] docs = this.crearDocumentos(formulario, registroType);
				
				registroType.setCdTipoRegistro("0");
				registroType.setTlResumen(properties.getProperty(STRING_CDASUNTO));
				
				InteresadoType interesado[]=registroType.getInteresados();
				if(solicitudBean.getPais() != null && solicitudBean.getPais().getId() != null){
					PaisQuery paisQuery = new PaisQuery();
					paisQuery.setId(Integer.valueOf(formulario.getIdPais()));
					PaisBean paisBean = paisManager.buscarPaisUnique(paisQuery);
					
					Pais pais = new Pais();
					pais.setId(Integer.valueOf(formulario.getIdPais()));
					pais.setCodigo(paisBean.getCodigo());
					solicitudBean.setPais(pais);
					interesado[0].setCdPaisInteresado(solicitudBean.getPais().getCodigo());
				}
				if(solicitudBean.getProvinciaByIdProvinciaDomicilio() != null && solicitudBean.getProvinciaByIdProvinciaDomicilio().getId() != null){
					ProvinciaQuery provinciaQuery = new ProvinciaQuery();
					provinciaQuery.setId(Integer.valueOf(formulario.getIdProvinciaDomicilio()));
					ProvinciaBean provinciaBean = provinciaManager.buscarProvinciaUnique(provinciaQuery);
					
					provincia = new Provincia();
					provincia.setId(Integer.valueOf(formulario.getIdProvinciaDomicilio()));
					provincia.setCodigo(provinciaBean.getCodigo());
					solicitudBean.setProvinciaByIdProvinciaDomicilio(provincia);
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
					
//					Forzamos el catch para probar el error
				}catch(Exception e){
					error = true;
					finConexion = System.currentTimeMillis();
					tiempoRespuesta = finConexion - inicioConexion;
					tiempoRespuesta = tiempoRespuesta / 1000;

					//Comprobar si se ha podido conectar al webservice
					logger.warn("Error en la conexion con el rec");
					logger.error("Conexion con el rec - Error:",e);
			
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
					
					logger.info("Error ModificarSolicitudPresencialSpring - doExecute "+RESOURCE_BUNDLE.getString(STRING_FIELD_SOLICITUD_REGISTRO_ERRORREALIZACION));
					this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString(STRING_FIELD_SOLICITUD_REGISTRO_ERRORREALIZACION));
					
					resultado="successRegistro";	
					mensaje = RESOURCE_BUNDLE.getString(STRING_FIELD_SOLICITUD_REGISTRO_ERRORREALIZACION);
					this.setRequestAttribute(STRING_MENSAJEREGISTRO, mensaje);
					return resultado;
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
						// Se guarda en la tabla RegistroSolicitud
						// Damos de alta o modificamos el Registro de Solicitud.
						registroSolicitudBean.setSolicitud(solicitud);
						registroSolicitudBean.setResultado(Constantes.RESULTADO_OK);
						registroSolicitudBean.setIdSolicitud(solicitud.getIdSolicitud());
						if ((formulario.getIdRegistroSolicitud() == null || "".equals(formulario.getIdRegistroSolicitud())) )
						{
							Integer id = registroSolicitudManager.guardarRegistroSolicitud(registroSolicitudBean);
						}
						else if (formulario.getIdRegistroSolicitud() != null && !"".equals(formulario.getIdRegistroSolicitud()) )
						{
							registroSolicitudBean.setId(Long.parseLong(formulario.getIdRegistroSolicitud()));
							registroSolicitudManager.modificarRegistroSolicitud(registroSolicitudBean);
						}		
						
						if(formulario.getIdRegistroSolicitud() != null){
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
//							if (usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)) {
								logSolicitudBean.setTipoActor(Constantes.OPERACION_ACTUALIZAR_ESTADO_SOLICITUD_USUARIO);
//							}

							//Se inserta en la tabla de log
							logger.info("Guardando en la tabla LogSolicitud");
							Integer idLog = logSolicitudManager.insertarLogSolicitud(logSolicitudBean);
							
							if(idLog == null){
								logger.error(RESOURCE_BUNDLE.getString("logSolicitud.error"));
							}
							
							if(error==false){
								logger.info("3. Resultado: success!!!");
//								
								// TODO Migracion Alfresco: Guardamos los documentos de la solicitud en su ubicación definitiva de Solo Lectura.
								documentoManager.copiarFicheros(formulario.getId(), properties.getProperty("sistemaficheros.url.escritura"));
//								
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

					GenerarJustificanteBean generarJustificanteBean = documentoManager.generarJustificante(solicitud.getIdSolicitud().toString());
					if (generarJustificanteBean.getMsgError() != null && !("").equals(generarJustificanteBean.getMsgError())) {
						this.getRequest().setAttribute("errorGeneracion", RESOURCE_BUNDLE.getString(generarJustificanteBean.getMsgError()));						
						resultado="successRegistro";	
						mensaje = RESOURCE_BUNDLE.getString(generarJustificanteBean.getMsgError());
						this.setRequestAttribute(STRING_MENSAJEREGISTRO, mensaje);
						return resultado;
					}

				}
				
				resultado = "successRegistro";
				String titulo = RESOURCE_BUNDLE.getString("field.solicitudPresencial.tituloModificacion");
				
				mensaje = RESOURCE_BUNDLE.getString("field.solicitudPresencial.mensaje.confirmacionMoficiacionRegistroPresencial");
				this.setRequestAttribute(STRING_MENSAJEREGISTRO, mensaje);
	
				setRequestAttribute("titulo", titulo);
				setRequestAttribute("mensaje", mensaje);
				setRequestAttribute("accion", "/spring/buscarSolicitudPresencial?accion=Modificar");
				logger.info("ModificarSolicitudPresencialSpring -end " + resultado);
			}
		}
		
	}catch(Exception eGenerico){
		logger.error("Error ModificarSolicitudPresencialSpring - doExecute ",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		return resultado;

	}
	
	/**
	 * Generar registro log solicitud.
	 *
	 * @param solicitudBean el solicitud bean
	 * @param username el username
	 * @param idSolicitud el id solicitud
	 */
	public void generarRegistroLogSolicitud(SolicitudBean solicitudBean, String username, Long idSolicitud){
		
		//Cargo los datos en el bean del log solicitudes que se usara para crear el registro en la tabla
		LogSolicitudBean logSolicitudBean = new LogSolicitudBean();
		
		logSolicitudBean.setTipoOperacion(Constantes.OPERACION_MODIFICACION);
		logSolicitudBean.setTipoActor(Constantes.OPERACION_ACTUALIZAR_ESTADO_SOLICITUD_USUARIO);
		logSolicitudBean.setActor(username);
		logSolicitudBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("field.solicitudPresencial.logModificacion") + idSolicitud);
		logSolicitudBean.setIdSolicitud(idSolicitud);
		
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
//				for(int x=0;x<altaSolicitudPresencialForm.getDocumentosFicheros().length;x++){
//					if(altaSolicitudPresencialForm.getDocumentosFicheros(x) != null && !"".equals(altaSolicitudPresencialForm.getDocumentosFicheros(x))){
//						numRegistros++;
//					}
//				}

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
		
		// Incidencia "EL VALOR DEL CAMPO CDFIRMADO DEL REGISTRO DE DOCUMENTOS INDICA QUE DEBE EXISTIR FIRMA."
				// La firma llega vacía.
				//TODO Como ya no se va a firmar, no vamos a valorar la firma
					
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
					try {
						SHA0 hash = new SHA0();
						String hashFile=hash.getHash(fileData);
						docBean.setHashExtracto(hashFile);	
					} catch (NoSuchAlgorithmException e2) {
						logger.error("Error obteniendo hash del documento: ",e2);
					}
					
					DocumentoQuery documentoQuery= new DocumentoQuery();
					documentoQuery.setDescripcion(contentType);
					
					// Comprobamos si ya existe un registro para este documento
					Set<DocumentoBean> listaDocs = documentoManager.buscarDocumentos(documentoQuery);
					
					// Si existe se modifica
					if(listaDocs != null && !listaDocs.isEmpty()){
						Long id = 0L;
						try{
							for (DocumentoBean docBorrar : listaDocs){
								documentoManager.borrarDocumento(docBorrar);
							}
							
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
					}else{
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
	 * @return solicitudPresencialManager SolicitudPresencialManager
	 */
	public SolicitudPresencialManager getSolicitudPresencialManager() {
		return solicitudPresencialManager;
	}

	/**
	 * Establece el solicitud presencial manager.
	 *
	 * @param solicitudPresencialManager            SolicitudPresencialManager
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
	 * @param pagoSolicitudManager            PagoSolicitudManager
	 */
	public void setPagoSolicitudManager(
			PagoSolicitudManager pagoSolicitudManager) {
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
	 * Establece el solicitudes registradas manager.
	 *
	 * @param solicitudManager el nuevo solicitudes registradas manager
	 */
	public void setSolicitudesRegistradasManager(SolicitudesRegistradasManager solicitudManager) {
		this.solicitudManager = solicitudManager;
	}

	/**
	 * Obtiene el solicitudes registradas manager.
	 *
	 * @return registroSolicitudManager SolicitudesRegistradasManager
	 */
	public SolicitudesRegistradasManager getSolicitudesRegistradasManager() {
		return solicitudManager;
	}	

	/**
	 * Establece el registro solicitud manager.
	 *
	 * @param registroSolicitudManager            RegistroSolicitudManager
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

}
