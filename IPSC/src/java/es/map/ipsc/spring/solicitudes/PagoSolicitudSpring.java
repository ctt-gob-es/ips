package es.map.ipsc.spring.solicitudes;


import java.math.BigDecimal;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.util.HtmlUtils;

import ePago.gob.es.schemas.DefaultDatosPagoClaveIn;
import ePago.gob.es.schemas.DefaultDatosPagoClaveOut;
import ePago.gob.es.schemas.PPServiceInterfaceServiceLocator;
import ePago.gob.es.schemas.PPServiceInterfaceSoap11Stub;
import ePago.gob.es.schemas.TiposCargo;
import ePago.gob.es.schemas.TiposDocumento;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.common.util.BeanFormatter;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.EntidadFinanciera;
import es.map.ips.model.EntidadFinancieraQuery;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ips.model.MotivoReduccionTasa;
import es.map.ips.model.MotivoReduccionTasaQuery;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.AuditoriaBean;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.ConvocatoriaBean;
import es.map.ipsc.bean.DetallePagoSolicitudBean;
import es.map.ipsc.bean.EntidadBean;
import es.map.ipsc.bean.LogServicioBean;
import es.map.ipsc.bean.LogSolicitudBean;
import es.map.ipsc.bean.MotivoReduccionBean;
import es.map.ipsc.bean.PagoSolicitudBean;
import es.map.ipsc.bean.ParametrosConfiguracionBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.bean.SolicitudCcaaBean;
import es.map.ipsc.form.PagoSolicitudForm;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.manager.entidadFinanciera.EntidadFinancieraManager;
import es.map.ipsc.manager.logs.LogServiciosManager;
import es.map.ipsc.manager.registroAuditoria.RegistroAuditoriaManager;
import es.map.ipsc.manager.solicitudes.EstadoSolicitudManager;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudCcaaManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.tablaBase.ParametroConfiguracionManager;
import es.map.ipsc.manager.tablaBase.TablaBaseManager;
import es.map.ipsc.manager.tasas.MotivoReduccionManager;
import es.map.ipsc.spring.AbstractSecureSpring;
import es.map.ipsc.utils.Validacion;

/**
 * El Class PagoSolicitudSpring.
 */
public class PagoSolicitudSpring extends AbstractSecureSpring {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(PagoSolicitudSpring.class);
	
	/** La constante BUNDLE_ERROR. */
	private static final String BUNDLE_ERROR = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_ERROR);

	/** el pago solicitudes manager. */
	private PagoSolicitudManager pagoSolicitudesManager;
	
	/** el solicitudes manager. */
	private SolicitudManager solicitudesManager;
	
	/** el solicitud ccaa manager. */
	private SolicitudCcaaManager solicitudCcaaManager;
	
	/** el motivo reduccion manager. */
	private MotivoReduccionManager motivoReduccionManager;
	
	/** el estado solicitud manager. */
	private EstadoSolicitudManager estadoSolicitudManager;
	
	/** el convocatoria manager. */
	private ConvocatoriasManager convocatoriaManager;
	
	/** el entidad financiera manager. */
	private EntidadFinancieraManager entidadFinancieraManager;
	
	/** el tabla base manager. */
	private TablaBaseManager tablaBaseManager;
	
	/** el documento convocatorias manager. */
	private DocumentosConvocatoriaManager documentoConvocatoriasManager;
	
	/** el log servicios manager. */
	private LogServiciosManager logServiciosManager;
	
	/** el parametro configuracion manager. */
	private ParametroConfiguracionManager parametroConfiguracionManager;
	
	/** el registro auditoria manager. */
	private RegistroAuditoriaManager registroAuditoriaManager;
	
	/** el properties. */
	private static Properties properties;

	/** La constante IBANNUMBER_MIN_SIZE. */
	public static final int IBANNUMBER_MIN_SIZE = 15;
	
	/** La constante IBANNUMBER_MAX_SIZE. */
	public static final int IBANNUMBER_MAX_SIZE = 34;
	
	/** La constante IBANNUMBER_MAGIC_NUMBER. */
	public static final BigInteger IBANNUMBER_MAGIC_NUMBER = new BigInteger("97"); 
	
	/** La constante TIMEOUT. */
	private static final int TIMEOUT = 90000;
	
	/** La constante STRING_USUARIOCLAVE. */
	private static final String STRING_USUARIOCLAVE = "usuarioClave";
	
	/** La constante STRING_ERRORFORM2. */
	private static final String STRING_ERRORFORM2 = "stringErrorForm2";
	
	/** La constante STRING_SOLICITUDERRORUSUARIO. */
	private static final String STRING_SOLICITUDERRORUSUARIO = "solicitud.error.usuario";
	
	/** La constante STRING_ERRORDESCRIPCION. */
	private static final String STRING_ERRORDESCRIPCION = "errorDescripcion";
	
	/** La constante STRING_ERRORUSUARIO. */
	private static final String STRING_ERRORUSUARIO = "errorUsuario";
	
	/** La constante STRING_REDIRECCIONALTA. */
	private static final String STRING_REDIRECCIONALTA = "Se redirecciona al alta";
	
	/** La constante STRING_DSERRORFORMPAGO. */
	private static final String STRING_DSERRORFORMPAGO = "dsErrorFormPago";
	
	/** La constante STRING_ERRORPAGOTIPONULL. */
	private static final String STRING_ERRORPAGOTIPONULL = "solicitud.error.pago.tipoPagoNull";
	
	/** La constante STRING_PAGARSOLICITUD. */
	private static final String STRING_PAGARSOLICITUD = "pagarSolicitud";
	
	/** La constante STRING_CORRECTO. */
	private static final String STRING_CORRECTO = "Correcto";
	
	/** La constante STRING_SOLICITUDERRORIMPORTEMODIFICADO. */
	private static final String STRING_SOLICITUDERRORIMPORTEMODIFICADO = "solicitud.error.importeModificado";
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/** La constante STRING_PAGOPPERRORCONEXION. */
	private static final String STRING_PAGOPPERRORCONEXION = "pagoPP.errorConexion";
	
	/** La constante STRING_FORMPAGO. */
	private static final String STRING_FORMPAGO = "formPago";
	
	/** La constante STRING_PAGOSOLICITUD. */
	private static final String STRING_PAGOSOLICITUD = "pagoSolicitud";
	
	/** La constante STRING_SUCCESS. */
	private static final String STRING_SUCCESS = "success";
	
	/** La constante STRING_SOLICITUDERRORPAGO. */
	private static final String STRING_SOLICITUDERRORPAGO = "solicitud.error.pago";
	
	/** La constante STRING_REGISTRO. */
	private static final String STRING_REGISTRO = "registro";
	
	/** La constante STRING_PAGOINCORRECTO. */
	private static final String STRING_PAGOINCORRECTO = "solicitud.error.pago.incorrecto";
	
	/** La constante PAGO. */
	//private static boolean PAGO = true;

	/**
	 * Crea una nueva pago solicitud spring.
	 */
	public PagoSolicitudSpring() {
		try{			
			setPagoSolicitudesManager((PagoSolicitudManager) getBean("pagoSolicitudesManager"));
			setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
			setSolicitudCcaaManager((SolicitudCcaaManager) getBean("solicitudCcaaManager"));
			setMotivoReduccionManager((MotivoReduccionManager) getBean("motivoReduccionManager"));
			setEstadoSolicitudManager((EstadoSolicitudManager) getBean("estadoSolicitudManager"));
			setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
			setTablaBaseManager((TablaBaseManager) getBean("tablasBaseManager"));
			setDocumentoConvocatoriasManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
			setLogServiciosManager((LogServiciosManager) getBean("logServiciosManager"));
			setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));
			setRegistroAuditoriaManager((RegistroAuditoriaManager) getBean("registroAuditoriaManager"));
			properties = (Properties) getBean("propertiesBean");
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error pago solicitud",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {

		logger.info("Entra en pagoSolicitud");
		PagoSolicitudForm pagoSolicitudForm = (PagoSolicitudForm) form;

		// si el tipo de persona es F.H y en sesion contenemos informacion del ciudadano a inscribir
		// hay que modificar los datos de pago al ciudadano que se va a inscribir
		CiudadanoBean usuarioActualAux = (CiudadanoBean) this.getRequest().getSession().getAttribute(STRING_USUARIOCLAVE);
		CiudadanoBean ciudadanoInscritoAux = (CiudadanoBean) this.getRequest().getSession().getAttribute("ciudadanoInscrito");
		String numJustAux = (String) this.getRequest().getSession().getAttribute("numJustCiudadanoInscrito"); 
		if (usuarioActualAux.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO) && ciudadanoInscritoAux != null ) {
			pagoSolicitudForm.setNif(ciudadanoInscritoAux.getNif().toUpperCase());
			pagoSolicitudForm.setNombre(ciudadanoInscritoAux.getNombre().toUpperCase());
			pagoSolicitudForm.setApellido1(ciudadanoInscritoAux.getPrimerApellido().toUpperCase());
			pagoSolicitudForm.setApellido2(ciudadanoInscritoAux.getSegundoApellido().toUpperCase());
			pagoSolicitudForm.setNumeroSolicitud(numJustAux);
			pagoSolicitudForm.setFormPago(String.valueOf(Constantes.FORMA_PAGO_EXENTO));
		}
		
		// Comprobamos si se esta modificando la solicitud
		// De ser asi comprobamos si existe posibilidad de modificar el pago
		String posibilidadModifPago = "";
		if(pagoSolicitudForm.getPagoInicialModif() != null){
			// Una vez generado el nuevo justificante, se actualiza el estado de la solicitud antigua a eliminada (4)
			Long idSolicitudActualizar = null;
			SolicitudComunQuery solicitudQueryAuxActualizarProceso = new SolicitudComunQuery();
			EstadoSolicitudQuery estadoSolicitudQueryAuxActualizar = new EstadoSolicitudQuery();
			estadoSolicitudQueryAuxActualizar.setId(Constantes.SOLICITUD_ID_ELIMINADO);
			solicitudQueryAuxActualizarProceso.setIdSolicitud(Long.parseLong(pagoSolicitudForm.getIdSolicitudInicial()));

			idSolicitudActualizar = solicitudesManager.actualizarEstadoSolicitud(solicitudQueryAuxActualizarProceso, estadoSolicitudQueryAuxActualizar);
			logger.info("1.RegistroSolicitudAction-Estado actualizado: "+idSolicitudActualizar);
			// Si existe la posibilidad, se modifica el pago
			// Sino, cogemos el pago realizado en la solicitud anterior y se asocia a la solicitud nueva
			posibilidadModifPago = pagoSolicitudForm.getPagoInicialModif();		

			// Se obtiene el idSolicitud asociado al numSolicitud
			SolicitudComunQuery solicitudComunQuery7 = new SolicitudComunQuery();
			solicitudComunQuery7.setNumeroSolicitud(pagoSolicitudForm.getNumSolicitud());
			SolicitudBean solicitudBean7 = solicitudesManager.buscarRegistroSolicitud(solicitudComunQuery7);
			// Se obtiene el pago inicial
			PagoSolicitudQuery pagoSolicitudQuery7 = new PagoSolicitudQuery();
			SolicitudComunQuery solicitudComunQuery8 = new SolicitudComunQuery();
			solicitudComunQuery8.setIdSolicitud(Long.parseLong(pagoSolicitudForm.getIdSolicitudInicial()));
			pagoSolicitudQuery7.setSolicitudComun(solicitudComunQuery8);
			ArrayList<PagoSolicitudBean>  listaPagoSolicitudBean = pagoSolicitudesManager.buscarPagoSolicitud(pagoSolicitudQuery7);
			// Se guarda en un nuevo registro de la tabla PAGO_SOLICITUD asociado a la solicitud nueva (modificada)
			for(PagoSolicitudBean pagoGuardar : listaPagoSolicitudBean){
				PagoSolicitudBean pagoSolicitudBean7 = pagoGuardar;
				pagoSolicitudBean7.setIdSolicitud(Long.toString(solicitudBean7.getId()));
				//Si ha pagado todo y ha seleccionado una exencion Se refleja en el pago
				if (pagoSolicitudBean7.getResultado().equals("OK") 
						&& !StringUtils.isEmpty(pagoSolicitudForm.getMotivo())
						&& (pagoSolicitudBean7.getTipo() == 'C' || pagoSolicitudBean7.getTipo() == 'T')) {
					pagoSolicitudBean7.setMotivo(pagoSolicitudForm.getMotivo());
					if (!"0".equals(pagoSolicitudForm.getMotivo())) {
						pagoSolicitudBean7.setReduccionPago(Constantes.REDUCCION_PAGO_SI);
					} else {
						pagoSolicitudBean7.setReduccionPago(Constantes.REDUCCION_PAGO_NO);
					}		
				}
				pagoSolicitudesManager.guardarPagoSolicitud(pagoSolicitudBean7);
			}
			
		}
		
		SolicitudBean solicitud;
		SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
		solicitudComunQuery.setNumeroSolicitud(pagoSolicitudForm.getNumeroSolicitud());
		solicitudComunQuery.setNif(pagoSolicitudForm.getNif());
		solicitud = solicitudesManager.buscarSolicitudById(solicitudComunQuery);
		//Guardamos la comunidad 
		guardarSolicitudCcaaFuncionario(pagoSolicitudForm, solicitud);
		
		if(posibilidadModifPago.equals("N")){
			return "pagoExento";
		}
		
		String busqueda = pagoSolicitudForm.getAccion();
		String idSolicitudAux = Long.toString(solicitud.getId());
		pagoSolicitudForm.setIdSolicitud(idSolicitudAux);

		String sCG = (String) this.getRequest().getSession().getAttribute("sCG");
		String sCGJ = (String) this.getRequest().getSession().getAttribute("sCGJ");

		this.setRequestAttribute("sCG", sCG);
		this.setRequestAttribute("sCGJ", sCGJ);

		//Variable para pruebas de estress
		String convocatoriaRepetida_Unico = properties.getProperty("altaSolicitud.inscripcionMultiple_Unica");

		String resultado = null;
		try{	
			//Si llega al action del submit de guardar
			if(!"Importe".equalsIgnoreCase(busqueda) && !"Entidad".equalsIgnoreCase(busqueda)){
				//Compruebo si el usuario se encuentra en sesion
				CiudadanoBean usuActual = null;
				
				if(convocatoriaRepetida_Unico.equals("U")){
					usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute(STRING_USUARIOCLAVE);
				}else{
					usuActual = new CiudadanoBean();
					usuActual.setNif("12345678Z");
				}
				
				if(usuActual == null){
					this.setSessionAttribute(STRING_ERRORFORM2, RESOURCE_BUNDLE.getString(STRING_SOLICITUDERRORUSUARIO));		
					this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_SOLICITUDERRORUSUARIO));
					return STRING_ERRORUSUARIO;
				}else{
					if(usuActual.getNif() == null){
						this.setSessionAttribute(STRING_ERRORFORM2, RESOURCE_BUNDLE.getString(STRING_SOLICITUDERRORUSUARIO));		
						this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_SOLICITUDERRORUSUARIO));
						return STRING_ERRORUSUARIO;
					}
				}				

				//Comprobar si el usuario introducido coincide con el del certificado en caso de la inscripcion por parte de un ciudadano
				if (!usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO) && !usuActual.getNif().equals(pagoSolicitudForm.getNif())) {
				
					this.setSessionAttribute(STRING_ERRORFORM2, RESOURCE_BUNDLE.getString("solicitud.error.usuarioSolicitudError"));		
					this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.usuarioSolicitudError"));
					return STRING_ERRORUSUARIO;
				
				}

				if(pagoSolicitudForm.getFormPago() == null || "".equals(pagoSolicitudForm.getFormPago())){
					logger.warn(STRING_REDIRECCIONALTA);
					SpringMessages messages = new SpringMessages();
					messages.add(STRING_DSERRORFORMPAGO, new SpringMessage(STRING_ERRORPAGOTIPONULL));
					saveErrors(this.getRequest(), messages);
					this.setRequestAttribute("id", idSolicitudAux);
					return STRING_PAGARSOLICITUD;
				}

				//Comprobar si el importe que llega del form es correcto
				if(solicitud != null){
					ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
					convocatoriaQuery.setId(solicitud.getIdConvocatoria());
					ConvocatoriaBean convocatoriaBean;
					convocatoriaBean = convocatoriaManager.buscarConvocatoriaId(convocatoriaQuery);

					if(convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_PUBLICADA) || convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_SUBSANADA)){
						
						ArrayList<MotivoReduccionTasa> motivosCompletosAux = convocatoriaBean.getMotivoReduccionTasasCompleto();

						if(pagoSolicitudForm.getFormPago() != null){
							
							// Exentas
							if(pagoSolicitudForm.getFormPago().equals(Constantes.FORMA_PAGO_EXENTO_S)){
								
								// Convocatorias sin motivos de exencion asociados
								
								// Recuperar porcentaje de discapacidad que supone la exencion.
								ParametrosConfiguracionQuery paramConfQuery = new ParametrosConfiguracionQuery();
								paramConfQuery.setNombreCampo(Constantes.PARAMETROS_PORCENTAJE_DISCAPACIDAD);
								ParametrosConfiguracionBean parametrosConfiguracion = parametroConfiguracionManager.buscarParametroConfiguracionUnico(paramConfQuery);//("METODO_NUMERO_SOLICITUD");			
								Long porcentajeDiscap = 0l;
								
								if(!StringUtils.isEmpty(parametrosConfiguracion.getValorCampo())){
									porcentajeDiscap = Long.valueOf(parametrosConfiguracion.getValorCampo());
								}
								
								// En el caso de que el porcentaje informado sea el establecido en la configuracion
								// no se validara si existen motivos, ya que la convocatoria puede no tener nunguno asociado.
								if(null!=solicitud.getPorcentajeDiscapacidad() && solicitud.getPorcentajeDiscapacidad()<porcentajeDiscap && motivosCompletosAux.size()<1){
									
										this.setSessionAttribute(STRING_ERRORFORM2, RESOURCE_BUNDLE.getString("solicitud.error.formaPagoExento"));		
										this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.formaPagoExento"));
										return STRING_ERRORUSUARIO;
									
								}
								// Si tenemos porcentaje de discapacidad superior al 33%, el motivo de exencion va a ser discapacidad
								if(null!=solicitud.getPorcentajeDiscapacidad() && solicitud.getPorcentajeDiscapacidad()>=porcentajeDiscap){
									pagoSolicitudForm.setMotivo(Constantes.MOTIVOTASA_DISCAPACIDAD);
								}																	
							}else{
								String idMotivo;
								String porcentaje;
								MotivoReduccionBean motivoBean = new MotivoReduccionBean();
								MotivoReduccionTasaQuery motivoReduccionTasaQuery = new MotivoReduccionTasaQuery();
								
								if(pagoSolicitudForm.getMotivo() != null && !"0".equals(pagoSolicitudForm.getMotivo())){
									idMotivo = pagoSolicitudForm.getMotivo();
									motivoReduccionTasaQuery.setId(Integer.parseInt(idMotivo));				
									motivoBean = motivoReduccionManager.buscarMotivoReduccionBeanById(motivoReduccionTasaQuery);
								}else{
									if(pagoSolicitudForm.getMotivoAdeudo() != null && !"0".equals(pagoSolicitudForm.getMotivoAdeudo())){
										idMotivo = pagoSolicitudForm.getMotivoAdeudo();
										motivoReduccionTasaQuery.setId(Integer.parseInt(idMotivo));
										motivoBean = motivoReduccionManager.buscarMotivoReduccionBeanById(motivoReduccionTasaQuery);
									}else{
										if(pagoSolicitudForm.getMotivoTarjeta() != null && !"0".equals(pagoSolicitudForm.getMotivoTarjeta())){
											idMotivo = pagoSolicitudForm.getMotivoTarjeta();
											motivoReduccionTasaQuery.setId(Integer.parseInt(idMotivo));
											motivoBean = motivoReduccionManager.buscarMotivoReduccionBeanById(motivoReduccionTasaQuery);
										}
									}
								}						
								
								if(motivoBean != null && motivoBean.getIdMotivo() != null){
									ArrayList<MotivoReduccionTasa> motivosCompletos = convocatoriaBean.getMotivoReduccionTasasCompleto();
									ArrayList<MotivoReduccionTasa> motivosIncompletos = convocatoriaBean.getMotivoReduccionTasasIncompleto();
									String resultMotivo = "";
									
									for(int i=0;i<motivosCompletos.size();i++){
										if(motivosCompletos.get(i).getId().toString().equals(motivoBean.getIdMotivo())){
											resultMotivo = STRING_CORRECTO;
										}
									}
									
									for(int i=0;i<motivosIncompletos.size();i++){
										if(motivosIncompletos.get(i).getId().toString().equals(motivoBean.getIdMotivo())){
											resultMotivo = STRING_CORRECTO;
										}
									}
									
									if(resultMotivo.equals(STRING_CORRECTO)){
										porcentaje = motivoBean.getPorcentajeDescuento();
										Float porcentajeNum = Float.parseFloat(porcentaje);
										Float importeConvocatoria = convocatoriaBean.getImporte();
										Float importeCalculado = importeConvocatoria - (importeConvocatoria * porcentajeNum)/100;
										Float importeFormulario = pagoSolicitudForm.getImporte();

										NumberFormat nf = NumberFormat.getInstance(); 
										nf.setMaximumFractionDigits(1);
										String importeCalculadoAux = nf.format(importeCalculado);
										String importeFormularioAux = nf.format(importeFormulario);

										if(importeCalculadoAux.equals(importeFormularioAux) || posibilidadModifPago.equals("S")){
											logger.info(STRING_CORRECTO);
										}else{
											this.setSessionAttribute(STRING_ERRORFORM2, RESOURCE_BUNDLE.getString(STRING_SOLICITUDERRORIMPORTEMODIFICADO));		
											this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_SOLICITUDERRORIMPORTEMODIFICADO));
											return STRING_ERRORUSUARIO;
										}
									}else{
										this.setSessionAttribute(STRING_ERRORFORM2, RESOURCE_BUNDLE.getString("solicitud.error.motivoReduccionModificado"));
										this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.motivoReduccionModificado"));
										return STRING_ERRORUSUARIO;
									}
								}else{
									logger.info("No se encuentra el motivo");
									
									// Ya no validamos si el importe de la convocatoria es el mismo que el importe del formulario porque puede variar
									logger.info(STRING_CORRECTO);
									
								}
							}
						}
					}else{
						logger.info("La convocatoria con id: "+convocatoriaBean.getId()+" no esta en estado publicado.");
						this.setSessionAttribute(STRING_ERRORFORM2, RESOURCE_BUNDLE.getString("solicitud.error.convocatoriaNoPublicada"));
						this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.convocatoriaNoPublicada"));
						return STRING_ERRORUSUARIO;
					}
				}

				// FIN COMPROBACIONES DE DATOS ANTES DE PAGAR, CREAR PDF y GUARDAR SOLICITUD DE PAGO
				logger.info("Se procede al pago de la Solicitud con Id: "+solicitud.getId());
				
				PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
				SolicitudComunQuery solicitudComunQuery1 = new SolicitudComunQuery();
				solicitudComunQuery1.setIdSolicitud(solicitud.getId());
				pagoSolicitudQuery.setSolicitudComun(solicitudComunQuery1);
				
				// Miramos si se ha hecho algun reintento de pago antes de esa misma solicitud, si es asi lo guardamos en la tabla de registro de auditoria
				try{
					
					if(pagoSolicitudesManager.buscarPago(pagoSolicitudQuery)!=null){
						AuditoriaBean auditoriaBean = new AuditoriaBean();
						auditoriaBean.setIdSolicitud(solicitud.getId());	
						auditoriaBean.setFechaAutenticacion(new Date());				
						
						if(usuActual.getNumeroSerie()!=null){
							auditoriaBean.setNumeroSerie(usuActual.getNumeroSerie());
						}else{
							auditoriaBean.setNumeroSerie(" ");
						}
						
						if (usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)) {
							auditoriaBean.setNombre(usuActual.getNombre());
							auditoriaBean.setPrimerApellido(usuActual.getPrimerApellido());
							auditoriaBean.setSegundoApellido(usuActual.getSegundoApellido());
							auditoriaBean.setTipoPersona(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO);
						}
						
						try{
							// Guardamos los datos en la tabla de registro de auditoria
							registroAuditoriaManager.guardarRegistroAuditoria(auditoriaBean);
						}catch(Exception e){
							logger.error("Error al insertar el registro en la tabla auditoria",e);	
						}
					}else{
						logger.info("No existen datos de pago previos en BBDD. No se guarda en Auditoria.");
					}
				}catch(Exception e){
					logger.error("Error al recuperar el pago con id de solicitud" +solicitud.getId(),e);	
				}
			
				//Comprueba si el estado de la solicitud es no pagado
				if(solicitud.getIdEstadoSolicitud().equals(Constantes.SOLICITUD_ID_NOPAGADO_STRING) || 
						solicitud.getIdEstadoSolicitud().equals(Constantes.SOLICITUD_ID_PROCESO_PAGO_STRING) || !convocatoriaRepetida_Unico.equals("U")){
					if(pagoSolicitudForm.getFormPago().charAt(0) == Constantes.FORMA_PAGO_ADEUDO){
						StringBuffer IBAN = new StringBuffer();
						IBAN.append(pagoSolicitudForm.getIBAN());
						
						if(!ibanTest(IBAN.toString())){
							this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("field.pagoSolicitud.incorrectoIBAN"));
							logger.info("El código IBAN no es correcto");
							return STRING_ERROR;
						}
					}

					EstadoSolicitudQuery estadoSolicitudQueryAuxActualizar = new EstadoSolicitudQuery();
					estadoSolicitudQueryAuxActualizar.setId(Constantes.SOLICITUD_ID_PROCESO_PAGO);
					SolicitudComunQuery solicitudQueryAuxActualizar = new SolicitudComunQuery();
					solicitudQueryAuxActualizar.setIdSolicitud(solicitud.getId());

					Long idSolicitudActualizar = solicitudesManager.actualizarEstadoSolicitud(solicitudQueryAuxActualizar, estadoSolicitudQueryAuxActualizar);
					logger.info("1.PagoSolicitudAction-Actualizar Estado: "+idSolicitudActualizar);

					//Comprobar que radio button esta activo en el pago
					if(pagoSolicitudForm.getFormPago() !=  null && !"".equals(pagoSolicitudForm.getFormPago())){
						if(pagoSolicitudForm.getFormPago().charAt(0) == Constantes.FORMA_PAGO_ADEUDO || 
								pagoSolicitudForm.getFormPago().charAt(0) == Constantes.FORMA_PAGO_TARJETA ||
								pagoSolicitudForm.getFormPago().charAt(0) == Constantes.FORMA_PAGO_EXENTO ||
								pagoSolicitudForm.getFormPago().charAt(0) == Constantes.FORMA_PAGO_NINGUNO){
							
							DefaultDatosPagoClaveOut defaultDatosPagoClaveOut = null;
							
							if(!"5".equals(pagoSolicitudForm.getMotivoTarjeta()) || pagoSolicitudForm.getImporte() == 0)  {
								if(!(importesPagoSolicitud(pagoSolicitudForm, solicitud))) {
									this.setSessionAttribute(STRING_ERRORFORM2, RESOURCE_BUNDLE.getString(STRING_PAGOINCORRECTO));
									this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_PAGOINCORRECTO));
	
									return STRING_ERROR;
								}
							}
							
							//Se incluye una nueva validacion. Si el importe original es > 0 y no hay seleccionada ninguna exencion se llama a EPAGO.
							if(!pagoSolicitudForm.getFormPago().equalsIgnoreCase(Constantes.FORMA_PAGO_EXENTO_S) && 
									pagoSolicitudForm.getImporte() > 0 ) {// || ( pagoSolicitudForm.getImporteOriginal()!=null  && (pagoSolicitudForm.getImporteOriginal() > 0 && "0".equals(pagoSolicitudForm.getMotivo()))))){
								
								// INICIO: BLOQUE EN EL QUE SE HACE EL PAGO EN EL CASO DE QUE EL USUARIO NO ESTÉ EXENTO DE PAGAR
								logger.info("conexion con pasarela");		
								long inicioConexion = System.currentTimeMillis();
								
								defaultDatosPagoClaveOut =	this.conexionPasarela(pagoSolicitudForm);

								long finConexion = System.currentTimeMillis();

								logger.info("Datos Recibidos de la Pasarela de Pagos:");	
								logger.info(new BeanFormatter().format(defaultDatosPagoClaveOut));

								long tiempoRespuesta = finConexion - inicioConexion;
								tiempoRespuesta = tiempoRespuesta / 1000;
								
								// INICIO: SI NO CONECTAMOS CON LA PASARELA, LANZAMOS ERROR Y PONEMOS ESTADO 'NO PAGADO'								
								if(defaultDatosPagoClaveOut == null){
									logger.error("Error Físico en Conexion con Pasarela");	
									logger.info("Guardando Datos del Error en LogServicio");
									LogServicioBean logServicioBean = new LogServicioBean();
									Date fechaActual = new Date();
									logServicioBean.setFecha(fechaActual);
									logServicioBean.setIdTipoServicio(Constantes.LOG_SERVICIO_TIPOSERVICIO_PAGO);
									logServicioBean.setResultado(Constantes.LOG_SERVICIO_RESULTADO_ERROR);
									logServicioBean.setTipoError(Constantes.LOG_SERVICIO_TIPO_ERROR_FISICO);
									logServicioBean.setPrueba(Constantes.LOG_SERVICIO_PRUEBA_NO);
									logServicioBean.setTiempoRespuesta(tiempoRespuesta);
									logServicioBean.setDesError(RESOURCE_BUNDLE.getString(STRING_PAGOPPERRORCONEXION));

									int result = logServiciosManager.insertarLog(logServicioBean);		

									logger.info("Guardando Datos de Pago en PagoSolicitud");
									PagoSolicitudBean pagoSolicitudBeanError = toPagoSolicitudBeanError(pagoSolicitudForm,solicitud.getId(),defaultDatosPagoClaveOut);
									pagoSolicitudBeanError.setIdLogServicio(result);
									pagoSolicitudesManager.guardarPagoSolicitud(pagoSolicitudBeanError);	
									
									//Actualizar estado a noPagado
									EstadoSolicitudQuery estadoSolicitudQueryAuxActualizarNoPagado = new EstadoSolicitudQuery();
									estadoSolicitudQueryAuxActualizarNoPagado.setId(Constantes.SOLICITUD_ID_NOPAGADO);
									SolicitudComunQuery solicitudQueryAuxActualizarNoPagado = new SolicitudComunQuery();
									solicitudQueryAuxActualizarNoPagado.setIdSolicitud(solicitud.getId());

									Long idSolicitudActualizarNoPagado = solicitudesManager.actualizarEstadoSolicitud(solicitudQueryAuxActualizarNoPagado, estadoSolicitudQueryAuxActualizarNoPagado);
									logger.info("3.PagoSolicitudAction-Actualizar Estado: "+idSolicitudActualizarNoPagado);

									this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_PAGOPPERRORCONEXION));

									return STRING_ERROR;
									// FIN: SI NO CONECTAMOS CON LA PASARELA, LANZAMOS ERROR Y PONEMOS ESTADO 'NO PAGADO'
								}else{
									// INICIO: PASARELA DE PAGO NO DISPONIBLE Y PONEMOS PAGO A 'NO PAGADO'
									if(defaultDatosPagoClaveOut.isFueCorrecto() == false ){
										logger.error("Error Logico en Conexion con Pasarela -> Codigo Error: "+defaultDatosPagoClaveOut.getErrorCode()+" ,Solicitud: "+solicitud.getId());

										if(defaultDatosPagoClaveOut.getErrorCode() != null){
											//Guardar en LOG_SERVICIO
											logger.info("Guardando Datos del Error en LogServicio");

											LogServicioBean logServicioBean = new LogServicioBean();
											String error = defaultDatosPagoClaveOut.getErrorCodeTexto();
											error = (error==null) ? "":error;

											Date fechaActual = new Date();
											logServicioBean.setFecha(fechaActual);
											logServicioBean.setIdTipoServicio(Constantes.LOG_SERVICIO_TIPOSERVICIO_PAGO);
											logServicioBean.setResultado(Constantes.LOG_SERVICIO_RESULTADO_ERROR);
											logServicioBean.setTipoError(Constantes.LOG_SERVICIO_TIPO_ERROR_LOGICO);
											logServicioBean.setPrueba(Constantes.LOG_SERVICIO_PRUEBA_NO);
											logServicioBean.setCodError(defaultDatosPagoClaveOut.getErrorCode());
											logServicioBean.setDesError((error.length()>500) ? error.substring(0,499) : error);
											logServicioBean.setTiempoRespuesta(tiempoRespuesta);
											int result = logServiciosManager.insertarLog(logServicioBean);

											logger.info("Guardados Datos del Error en LogServicio con Id: "+result);
											logger.info("Guardando Datos de Pago en PagoSolicitud");
											
											PagoSolicitudBean pagoSolicitudBeanError = toPagoSolicitudBeanError(pagoSolicitudForm,solicitud.getId(),defaultDatosPagoClaveOut);
											pagoSolicitudBeanError.setIdLogServicio(result);
											pagoSolicitudesManager.guardarPagoSolicitud(pagoSolicitudBeanError);

											this.setRequestAttribute("id", idSolicitudAux);
											this.setRequestAttribute(STRING_FORMPAGO,pagoSolicitudForm.getFormPago());
											this.setRequestAttribute(STRING_PAGOSOLICITUD, pagoSolicitudBeanError);

											//Actualizar estado a noPagado
											EstadoSolicitudQuery estadoSolicitudQueryAuxActualizarNoPagado = new EstadoSolicitudQuery();
											estadoSolicitudQueryAuxActualizarNoPagado.setId(Constantes.SOLICITUD_ID_NOPAGADO);
											SolicitudComunQuery solicitudQueryAuxActualizarNoPagado = new SolicitudComunQuery();
											solicitudQueryAuxActualizarNoPagado.setIdSolicitud(solicitud.getId());

											Long idSolicitudActualizarNoPagado = solicitudesManager.actualizarEstadoSolicitud(solicitudQueryAuxActualizarNoPagado, estadoSolicitudQueryAuxActualizarNoPagado);
											logger.info("4.PagoSolicitudAction-Actualizar Estado: "+idSolicitudActualizarNoPagado);

											this.setSessionAttribute(STRING_ERRORFORM2, error);		
											this.getRequest().setAttribute(STRING_ERRORDESCRIPCION, error);

											return STRING_ERROR;
										}								

										//Actualizar estado a noPagado
										EstadoSolicitudQuery estadoSolicitudQueryAuxActualizarNoPagado = new EstadoSolicitudQuery();
										estadoSolicitudQueryAuxActualizarNoPagado.setId(Constantes.SOLICITUD_ID_NOPAGADO);
										SolicitudComunQuery solicitudQueryAuxActualizarNoPagado = new SolicitudComunQuery();
										solicitudQueryAuxActualizarNoPagado.setIdSolicitud(solicitud.getId());

										Long idSolicitudActualizarNoPagado = solicitudesManager.actualizarEstadoSolicitud(solicitudQueryAuxActualizarNoPagado, estadoSolicitudQueryAuxActualizarNoPagado);
										logger.info("5.PagoSolicitudAction-Actualizar Estado: "+idSolicitudActualizarNoPagado);

										this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_PAGOPPERRORCONEXION));

										return STRING_ERROR;
										// FIN: PASARELA DE PAGO NO DISPONIBLE Y PONEMOS PAGO A 'NO PAGADO'
									}else{
										logger.info("La conexion con la pasarela ha sido exitosa");
										logger.info("NRC: "+defaultDatosPagoClaveOut.getNRC());
										
									}
								}
							}
							// FIN BLOQUE EN EL QUE SE HACE EL PAGO EN EL CASO DE QUE EL USUARIO NO ESTÉ EXENTO DE PAGAR					
							//Si llega a este punto es porque el pago ha sido exitoso
							//Buscar si hay un pagoSolicitud registrado
							SolicitudComunQuery solicitudQueryActualizarAux = new SolicitudComunQuery();
							solicitudQueryActualizarAux.setIdSolicitud(Long.parseLong(idSolicitudAux));

							PagoSolicitudQuery pagoSolicitudAux = new PagoSolicitudQuery();
							pagoSolicitudAux.setSolicitudComun(solicitudQueryActualizarAux);

							
							PagoSolicitudBean pagoSolicitudBean = null;
							if (!usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)) {
							
								pagoSolicitudBean = toPagoSolicitudBean(pagoSolicitudForm,solicitud.getId(),defaultDatosPagoClaveOut);
							} else {
								pagoSolicitudBean = toPagoSolicitudBeanFuncionario(pagoSolicitudForm,solicitud.getId());
							}
							

							try{
								//Guardar en la tabla pago de solicitud
								logger.info("Guardando datos de Pago en PagoSolicitud");
								pagoSolicitudesManager.guardarPagoSolicitud(pagoSolicitudBean);


								EstadoSolicitudQuery estadoSolicitudQuery = new EstadoSolicitudQuery();
								estadoSolicitudQuery.setId(Constantes.SOLICITUD_ID_NOREGISTRADO);
								SolicitudComunQuery solicitudQueryAux = new SolicitudComunQuery();
								solicitudQueryAux.setIdSolicitud(Long.parseLong(idSolicitudAux));

								//Actualizar el estado de la tabla solicitud
								logger.info("Actualizando estado de la solicitud");

								Long idSolicitud = solicitudesManager.actualizarEstadoSolicitud(solicitudQueryAux, estadoSolicitudQuery);
								logger.info("6.PagoSolicitudAction-Resultado Actualizar Estado: "+idSolicitud);
								
								//Guardar el log de la actualizacion
								LogSolicitudBean logSolicitudBean = new LogSolicitudBean();
								logSolicitudBean.setActor(usuActual.getNif());
								Date fechaAux =new Date();
								logSolicitudBean.setFecha(fechaAux);
								logSolicitudBean.setTipoOperacion(Constantes.LOG_SOLICITUD_CAMBIOESTADO);
								logSolicitudBean.setDetalleOperacion(RESOURCE_BUNDLE.getString("logSolicitud.detalleModificacion"));
								logSolicitudBean.setIdSolicitud(String.valueOf(idSolicitudAux));
								logSolicitudBean.setIdEstadoActual(Constantes.LOG_SOLICITUD_ESTADO_NOREGISTRADO);
								logSolicitudBean.setIdEstadoAnterior(Constantes.LOG_SOLICITUD_ESTADO_NOPAGADO);
								if (usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)) {
									logSolicitudBean.setTipoActor(Constantes.TIPO_ACTOR_FUNCIONARIO_HABILITADO);
								}
								Integer idLog = tablaBaseManager.insertarLogSolicitud(logSolicitudBean);

								if(idLog == null){
									logger.error(RESOURCE_BUNDLE.getString("logSolicitud.error"));
								}

								this.setRequestAttribute("id",idSolicitudAux);
								this.setRequestAttribute("idConvocatoria",pagoSolicitudForm.getIdConvocatoria());
								resultado =  STRING_SUCCESS;
								
							}catch(Exception e){
								logger.error("Error solicitud pago",e);
								this.setSessionAttribute(STRING_ERRORFORM2, RESOURCE_BUNDLE.getString(STRING_SOLICITUDERRORPAGO));
								this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_SOLICITUDERRORPAGO));
								

								//Actualizar estado a noPagado
								EstadoSolicitudQuery estadoSolicitudQueryAuxActualizarNoPagado = new EstadoSolicitudQuery();
								estadoSolicitudQueryAuxActualizarNoPagado.setId(Constantes.SOLICITUD_ID_NOPAGADO);
								SolicitudComunQuery solicitudQueryAuxActualizarNoPagado = new SolicitudComunQuery();
								solicitudQueryAuxActualizarNoPagado.setIdSolicitud(solicitud.getId());

								Long idSolicitudActualizarNoPagado = solicitudesManager.actualizarEstadoSolicitud(solicitudQueryAuxActualizarNoPagado, estadoSolicitudQueryAuxActualizarNoPagado);
								logger.info("7.PagoSolicitudAction-Actualizar Estado: "+idSolicitudActualizarNoPagado);

								return STRING_ERROR;			
							}
						}else{
							logger.info(STRING_REDIRECCIONALTA);
							SpringMessages messages = new SpringMessages();
							messages.add(STRING_DSERRORFORMPAGO, new SpringMessage(STRING_ERRORPAGOTIPONULL));
							saveErrors(this.getRequest(), messages);

							//Actualizar estado a noPagado
							EstadoSolicitudQuery estadoSolicitudQueryAuxActualizarNoPagado = new EstadoSolicitudQuery();
							estadoSolicitudQueryAuxActualizarNoPagado.setId(Constantes.SOLICITUD_ID_NOPAGADO);
							SolicitudComunQuery solicitudQueryAuxActualizarNoPagado = new SolicitudComunQuery();
							solicitudQueryAuxActualizarNoPagado.setIdSolicitud(solicitud.getId());

							Long idSolicitudActualizarNoPagado = solicitudesManager.actualizarEstadoSolicitud(solicitudQueryAuxActualizarNoPagado, estadoSolicitudQueryAuxActualizarNoPagado);
							logger.info("8.PagoSolicitudAction-Actualizar Estado: "+idSolicitudActualizarNoPagado);

							this.setRequestAttribute("id", idSolicitudAux);
							return STRING_PAGARSOLICITUD;	
						}
					}else{
						logger.info(STRING_REDIRECCIONALTA);
						SpringMessages messages = new SpringMessages();
						messages.add(STRING_DSERRORFORMPAGO, new SpringMessage(STRING_ERRORPAGOTIPONULL));
						saveErrors(this.getRequest(), messages);

						//Actualizar estado a noPagado
						EstadoSolicitudQuery estadoSolicitudQueryAuxActualizarNoPagado = new EstadoSolicitudQuery();
						estadoSolicitudQueryAuxActualizarNoPagado.setId(Constantes.SOLICITUD_ID_NOPAGADO);
						SolicitudComunQuery solicitudQueryAuxActualizarNoPagado = new SolicitudComunQuery();
						solicitudQueryAuxActualizarNoPagado.setIdSolicitud(solicitud.getId());

						Long idSolicitudActualizarNoPagado = solicitudesManager.actualizarEstadoSolicitud(solicitudQueryAuxActualizarNoPagado, estadoSolicitudQueryAuxActualizarNoPagado);
						logger.info("9.PagoSolicitudAction-Actualizar Estado: "+idSolicitudActualizarNoPagado);

						this.setRequestAttribute("id", idSolicitudAux);
						return STRING_PAGARSOLICITUD;	
					}
				}else{

					if(solicitud.getIdEstadoSolicitud().equals(Constantes.SOLICITUD_ID_NOREGISTRADO_STRING) || 
							solicitud.getIdEstadoSolicitud().equals(Constantes.SOLICITUD_ID_REGISTRADO_STRING) ||
							solicitud.getIdEstadoSolicitud().equals(Constantes.SOLICITUD_ID_PENDIENTE_REGISTRO_STRING) ||
							solicitud.getIdEstadoSolicitud().equals(Constantes.SOLICITUD_ID_PROCESO_REGISTRO_STRING)){
						this.setRequestAttribute(STRING_REGISTRO, solicitud.getId().toString());
						logger.info("Reanudar Solicitud");												
						resultado =  STRING_SUCCESS;

					}else if(solicitud.getIdEstadoSolicitud().equals(Constantes.SOLICITUD_ID_ELIMINADO_STRING)){
						SpringMessages messages = new SpringMessages();
						logger.error("La solicitud no se encuentra en estado no pagado");
						messages.add("dsErrorPago", new SpringMessage("solicitud.error.solicitudEliminada"));
						saveErrors(this.getRequest(), messages);

						this.setRequestAttribute(STRING_REGISTRO, idSolicitudAux);
						resultado =  STRING_SUCCESS;
					}else {
						logger.warn(STRING_REDIRECCIONALTA);
						SpringMessages messages = new SpringMessages();
						logger.error("La solicitud no se encuentra en estado no pagado");
						messages.add("dsErrorPago", new SpringMessage("solicitud.error.procesoPago"));
						saveErrors(this.getRequest(), messages);

						this.setRequestAttribute(STRING_REGISTRO, idSolicitudAux);
						resultado =  STRING_SUCCESS;
					}
					if (!usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)) {
						guardarSolicitudCcaa( pagoSolicitudForm, solicitud);
					} else {
						guardarSolicitudCcaaFuncionario(pagoSolicitudForm, solicitud);
					}
					
				}
								
				resultado = "pagoExento";

				//Si llega al action para calcular el importe de la solicitud
			}else if("Importe".equalsIgnoreCase(busqueda)){
				boolean vacio = false;
				String codMotivo = "";
				String tipoMotivo = "";
				
				//Asigna valor a las variables dependiendo del tipo de pago seleccionado
				if(pagoSolicitudForm.getMotivo() != null && !"0".equals(pagoSolicitudForm.getMotivo())){
					codMotivo = pagoSolicitudForm.getMotivo();
					pagoSolicitudForm.setFormaPago(Constantes.PAGO_TIPO_EXENTO_CHAR);
					tipoMotivo = Constantes.PAGO_TIPO_EXENTO;
				}else{
					if(pagoSolicitudForm.getMotivoAdeudo() != null && !"0".equals(pagoSolicitudForm.getMotivoAdeudo())){
						codMotivo= pagoSolicitudForm.getMotivoAdeudo();
						pagoSolicitudForm.setFormaPago(Constantes.PAGO_TIPO_ADEUDO_CHAR);
						tipoMotivo = Constantes.PAGO_TIPO_ADEUDO_ABRE;
					}else{
						if(pagoSolicitudForm.getMotivoTarjeta() != null && !"0".equals(pagoSolicitudForm.getMotivoTarjeta())){
							codMotivo = pagoSolicitudForm.getMotivoTarjeta();
							pagoSolicitudForm.setFormaPago(Constantes.PAGO_TIPO_TARJETA_CHAR);
							tipoMotivo = Constantes.PAGO_TIPO_TARJETA_ABRE;
						}
					}
				}

				//Busca la solicitud
				ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
				convocatoriaQuery.setId(solicitud.getIdConvocatoria());
				//Buscar la convocartoria
				Convocatoria convocatoriaAux = convocatoriaManager.buscarConvocatoriaIdModel(convocatoriaQuery);
				ConvocatoriaBean convocatoriaBean = convocatoriaManager.recuperarConvocatoria(solicitud.getIdConvocatoria());

				//Si el importe de la convocatoria es mayor de 0 se realiza el calculo
				if(convocatoriaAux.getImporte() >= 0){
					MotivoReduccionTasaQuery motivoReduccionQuery = new MotivoReduccionTasaQuery();
					try{
						int numCodMotivo = Integer.parseInt(codMotivo);
						motivoReduccionQuery.setId(numCodMotivo);
					}catch(Exception e){
						logger.error("Error parsear motivo reduccion de tasas",e);
						vacio = true;
						if(Constantes.PAGO_TIPO_EXENTO_CHAR.equals(pagoSolicitudForm.getFormPago())){
							pagoSolicitudForm.setFormaPago(Constantes.PAGO_TIPO_EXENTO_CHAR);
						}else{
							if(Constantes.PAGO_TIPO_ADEUDO_CHAR.equals(pagoSolicitudForm.getFormPago())){
								pagoSolicitudForm.setFormaPago(Constantes.PAGO_TIPO_ADEUDO_CHAR);
							}else{
								if(Constantes.PAGO_TIPO_TARJETA_CHAR.equals(pagoSolicitudForm.getFormPago())){
									pagoSolicitudForm.setFormaPago(Constantes.PAGO_TIPO_TARJETA_CHAR);
								}
							}
						}
					}
					MotivoReduccionTasa motivoAux;
					try{
						//Buscar el motivo de la reduccion
						motivoAux = motivoReduccionManager.buscarMotivoReduccionById(motivoReduccionQuery);
					}catch(Exception e){
						motivoAux = null;
						logger.error("Error buscar motivo reduccion tasa",e);
					}
					Float importeTotal = 0f;
					Float numAux = 0f;

					if(motivoAux != null ){
						try{
							//Se realiza el calculo para asignar el importe
							importeTotal = convocatoriaAux.getImporte() - ((convocatoriaAux.getImporte() * motivoAux.getPorcentajeDescuento()) / 100);
							Float redondeoAlza = Math.round(Double.valueOf(String.valueOf(importeTotal))*100)/100f;							
							NumberFormat nf = NumberFormat.getInstance(); 
							nf.setMaximumFractionDigits(2);
							String stringAux = nf.format(redondeoAlza);

							String fdistribuciontemp = stringAux.replace(',','.');
							numAux = Float.parseFloat(fdistribuciontemp);

							logger.info("NumRedondeado: " + numAux);
						}catch(Exception e){
							numAux = 0.0f;
							logger.error("Error parsear distribucion",e);
						}
					}else{
						//Si el motivo no es correcto muestra un error
						if(vacio == false){
							this.setSessionAttribute(STRING_ERRORFORM2, RESOURCE_BUNDLE.getString("solicitud.error.pago.motivoNull"));
							this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.pago.motivoNull"));
							return "errorGenerico";
						}else{

							numAux = convocatoriaAux.getImporte();
						}
					}
					PagoSolicitudBean pagoSolicitudBean = new PagoSolicitudBean();
					pagoSolicitudBean.setNumeroSolicitud(pagoSolicitudForm.getNumeroSolicitud());
					pagoSolicitudBean.setNif(pagoSolicitudForm.getNif());
					String nombreCompleto = pagoSolicitudForm.getNombre();
					pagoSolicitudBean.setIdSolicitud(pagoSolicitudForm.getId());
					pagoSolicitudBean.setNombre(nombreCompleto);
					pagoSolicitudBean.setIdConvocatoria(String.valueOf(pagoSolicitudForm.getId()));
					
					//Busca los datos para los combos
					ArrayList<EntidadBean> entidadBean = entidadFinancieraManager.buscarEntidadAdeudoCombo();
					ArrayList<EntidadBean> entidadBeanTarjeta = entidadFinancieraManager.buscarEntidadTarjetaCombo();
					this.setRequestAttribute("id", String.valueOf(pagoSolicitudForm.getId()));
					this.setRequestAttribute("entidades", entidadBean);
					this.setRequestAttribute("entidadesTarjetas", entidadBeanTarjeta);
					
					if(convocatoriaBean.getMotivoReduccionTasasIncompleto() != null){
						this.setRequestAttribute("motivos", convocatoriaBean.getMotivoReduccionTasasIncompleto());
					}
					
					if(convocatoriaBean.getMotivoReduccionTasasCompleto() != null){
						this.setRequestAttribute("motivosCompletos", convocatoriaBean.getMotivoReduccionTasasCompleto());
					}
					
					if(motivoAux != null){
						if(Constantes.PAGO_TIPO_EXENTO.equals(tipoMotivo)){
							if(motivoAux.getDescripcion() != null){
								this.setRequestAttribute("motivo", motivoAux.getDescripcion());
							}
						}else{
							if(Constantes.PAGO_TIPO_ADEUDO_ABRE.equals(tipoMotivo)){
								if(motivoAux.getDescripcion() != null){
									this.setRequestAttribute("motivoAdeudo", motivoAux.getDescripcion());
								}
							}else{
								if(Constantes.PAGO_TIPO_TARJETA_ABRE.equals(tipoMotivo) && motivoAux.getDescripcion() != null){
									this.setRequestAttribute("motivoTarjeta", motivoAux.getDescripcion());
								}
							}
						}
						
					}
					
					this.setRequestAttribute(STRING_PAGOSOLICITUD, pagoSolicitudBean);
					logger.info("TipoPago: " + pagoSolicitudForm.getFormPago());
					this.setRequestAttribute(STRING_FORMPAGO,pagoSolicitudForm.getFormPago());
					pagoSolicitudForm.setImporteActual(numAux);
					String detalleVacio = "";
					this.setRequestAttribute("detalleVacio", detalleVacio);
					this.setRequestAttribute("importeOriginal", convocatoriaAux.getImporte());
					resultado =  STRING_SUCCESS;
				}
			}else if("Entidad".equalsIgnoreCase(busqueda)){

				//Recogemos la entidad bancaria correspondiente

				EntidadFinanciera entidadFinanciera;
				EntidadFinancieraQuery entidadFinancieraQuery = new EntidadFinancieraQuery();

				if(Constantes.FORMA_PAGO_TARJETA_S.equals(pagoSolicitudForm.getFormPago())){
					entidadFinancieraQuery.setId(Integer.parseInt(pagoSolicitudForm.getEntidadTarjeta()));
				}else{
					entidadFinancieraQuery.setId(Integer.parseInt(pagoSolicitudForm.getEntidadAdeudo()));
				}

				entidadFinanciera = entidadFinancieraManager.buscarEntidadById(entidadFinancieraQuery);

				if(Constantes.FORMA_PAGO_TARJETA_S.equals(pagoSolicitudForm.getFormPago())){
					pagoSolicitudForm.setBancoTarjeta(entidadFinanciera.getCodigo());
				}else{
					pagoSolicitudForm.setBancoAdeudo(entidadFinanciera.getCodigo());
				}

				ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
				convocatoriaQuery.setId(solicitud.getIdConvocatoria());
				//Buscar la convocartoria
				Convocatoria convocatoriaAux = convocatoriaManager.buscarConvocatoriaIdModel(convocatoriaQuery);
				ConvocatoriaBean convocatoriaBean = convocatoriaManager.recuperarConvocatoria(solicitud.getIdConvocatoria());

				//Siempre sera adeudo
				String codMotivo= pagoSolicitudForm.getMotivoAdeudo();
				pagoSolicitudForm.setFormaPago(Constantes.PAGO_TIPO_ADEUDO_CHAR);
				MotivoReduccionTasa motivoAux;
				MotivoReduccionTasaQuery motivoReduccionQuery = new MotivoReduccionTasaQuery();
				int numCodMotivo = Integer.parseInt(codMotivo);
				motivoReduccionQuery.setId(numCodMotivo);			
				
				try{
					//Buscar el motivo de la reduccion
					motivoAux = motivoReduccionManager.buscarMotivoReduccionById(motivoReduccionQuery);
				}catch(Exception e){
					motivoAux = null;
					logger.error("Error buscar motivo reduccion",e);
				}			

				PagoSolicitudBean pagoSolicitudBean = new PagoSolicitudBean();
				pagoSolicitudBean.setBanco(entidadFinanciera.getCodigo());
				pagoSolicitudBean.setNumeroSolicitud(pagoSolicitudForm.getNumeroSolicitud());
				pagoSolicitudBean.setNif(pagoSolicitudForm.getNif());
				String nombreCompleto = pagoSolicitudForm.getNombre();
				pagoSolicitudBean.setIdSolicitud(pagoSolicitudForm.getId());
				pagoSolicitudBean.setNombre(nombreCompleto);
				pagoSolicitudBean.setIdConvocatoria(String.valueOf(pagoSolicitudForm.getId()));
				
				//Busca los datos para los combos
				ArrayList<EntidadBean> entidadBean = entidadFinancieraManager.buscarEntidadAdeudoCombo();
				ArrayList<EntidadBean> entidadBeanTarjeta = entidadFinancieraManager.buscarEntidadTarjetaCombo();
				this.setRequestAttribute("id", String.valueOf(pagoSolicitudForm.getId()));
				this.setRequestAttribute("entidades", entidadBean);
				this.setRequestAttribute("entidadesTarjetas", entidadBeanTarjeta);
				
				if(convocatoriaBean.getMotivoReduccionTasas() != null){
					this.setRequestAttribute("motivos", convocatoriaBean.getMotivoReduccionTasas());
				}
				
				if(convocatoriaBean.getMotivoReduccionTasasCompleto() != null){
					this.setRequestAttribute("motivosCompletos", convocatoriaBean.getMotivoReduccionTasasCompleto());
				}

				
				if(motivoAux != null && motivoAux.getDescripcion() != null){
					
						this.setRequestAttribute("motivoAdeudo", motivoAux.getDescripcion());
					
				}

				this.setRequestAttribute(STRING_PAGOSOLICITUD, pagoSolicitudBean);
				logger.info("TipoPago: " + pagoSolicitudForm.getFormPago());
				this.setRequestAttribute(STRING_FORMPAGO,pagoSolicitudForm.getFormPago());
				String detalleVacio = "";
				this.setRequestAttribute("detalleVacio", detalleVacio);
				this.setRequestAttribute("importeOriginal", convocatoriaAux.getImporte());			
				logger.info("Codigo Entidad: " + pagoSolicitudForm.getEntidadAdeudo());
				
				resultado =  STRING_SUCCESS;
			}
		}catch(Exception e){
			EstadoSolicitudQuery estadoSolicitudQueryAuxActualizarNoPagado = new EstadoSolicitudQuery();
			estadoSolicitudQueryAuxActualizarNoPagado.setId(Constantes.SOLICITUD_ID_NOPAGADO);
			SolicitudComunQuery solicitudQueryAuxActualizarNoPagado = new SolicitudComunQuery();
			solicitudQueryAuxActualizarNoPagado.setIdSolicitud(Long.valueOf(idSolicitudAux));

			Long idSolicitudActualizarNoPagado = solicitudesManager.actualizarEstadoSolicitud(solicitudQueryAuxActualizarNoPagado, estadoSolicitudQueryAuxActualizarNoPagado);
			logger.info("10.PagoSolicitudAction-Actualizar Estado: "+idSolicitudActualizarNoPagado);
			
			logger.error("Error de pago",e);
			this.setSessionAttribute(STRING_ERRORFORM2, RESOURCE_BUNDLE.getString(STRING_SOLICITUDERRORPAGO));
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString(STRING_SOLICITUDERRORPAGO));
			

			return STRING_ERROR;
		}	

		logger.info("Resultado: "+resultado);
		return resultado;
	}

	/**
	 * Obtenemos el importe de la convocatoria y el ultimo importe con "OK" de BBBDD
	 * @param pagoSolicitudForm
	 * @param solicitud
	 * @return 
	 * @return 
	 */
	private boolean importesPagoSolicitud(PagoSolicitudForm pagoSolicitudForm, SolicitudBean solicitud) {
		float importePagoSolicitud = pagoSolicitudForm.getImporte();
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(solicitud.getIdConvocatoria());
		ConvocatoriaBean convocatoriaBean = convocatoriaManager.buscarConvocatoriaId(convocatoriaQuery);
		float importeConvocatoria = convocatoriaBean.getImporte();
		
		PagoSolicitudQuery pagoSolicitudQuery2 = new PagoSolicitudQuery();
		SolicitudComunQuery solicitudComunQuery2 = new SolicitudComunQuery();
		solicitudComunQuery2.setIdSolicitud(solicitud.getId());
		pagoSolicitudQuery2.setResultado("OK");
		pagoSolicitudQuery2.setSolicitudComun(solicitudComunQuery2);
		
		//se elimina la variable global static.
		boolean pago = true;

		try {
			DetallePagoSolicitudBean detallePagoSolicitudBean = pagoSolicitudesManager.buscarUltimoPagoSolicitudIdSolicituOK(pagoSolicitudQuery2);
			
 			if (detallePagoSolicitudBean == null) {
				float resultadoSolicitudN = importeConvocatoria - importePagoSolicitud;
				
				if (!pagoSolicitudForm.getFormPago().equalsIgnoreCase(Constantes.FORMA_PAGO_EXENTO_S) 
						&& (resultadoSolicitudN!=importeConvocatoria && resultadoSolicitudN != 0)) {
					if(!"3".equals(pagoSolicitudForm.getMotivo())
							&& (!Constantes.MOTIVO_FNUMEROSA.equals(pagoSolicitudForm.getMotivoAdeudo()) 
									&& !Constantes.MOTIVO_FNUMEROSA.equals(pagoSolicitudForm.getMotivoTarjeta()))) {
						logger.info("El importe de la solicud no coincide con el obtenido de la convocatoria");
						pago = false;
					}
				}
			} else if (Float.parseFloat(detallePagoSolicitudBean.getImporte()) == 0) { 
				pago = true;
			} else {
				float importeSolicitud = Float.parseFloat(detallePagoSolicitudBean.getImporte());
				float resultadoSolicitud = importeConvocatoria - importeSolicitud;
				
				if (resultadoSolicitud != importePagoSolicitud) {
					logger.info("El pago de la solicud no coincide con el obtenido por Base de Datos");
					pago = false;
				}
			}
		} catch(Exception ex) {
			logger.info("Error al realizar la busquedad del pago solicitud");
			pago = false;
		}
		
		return pago;
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
	 * Conexion pasarela.
	 *
	 * @param pagoSolicitudForm el pago solicitud form
	 * @return el default datos pago clave out
	 */
	// INI - cpasculi - IPS-147 - Actualización cliente ePago
	
	private DefaultDatosPagoClaveOut conexionPasarela(PagoSolicitudForm pagoSolicitudForm){
		logger.info("Entra en conexionPasarela");
		//interface para WS
		PPServiceInterfaceServiceLocator locator = new PPServiceInterfaceServiceLocator();
		locator.setPPServiceInterfaceSoap11EndpointAddress(properties.getProperty("direccion.pasarela.ws"));

		//Objeto de pago


		DefaultDatosPagoClaveIn dpi = new DefaultDatosPagoClaveIn();
		DefaultDatosPagoClaveOut defaultDatosPagoClaveOut = new DefaultDatosPagoClaveOut();
	
		String convocatoriaRepetida_Unico = properties.getProperty("altaSolicitud.inscripcionMultiple_Unica");

		try {

			//Comprobar si el usuario esta en la sesion
			CiudadanoBean usuActual = null;
			
			if(convocatoriaRepetida_Unico.equals("U")){
				usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute(STRING_USUARIOCLAVE);
			}else{
				usuActual = new CiudadanoBean();
				usuActual.setNif("12345678Z");
				usuActual.setNombre("ANF");
				usuActual.setPrimerApellido("Usuario");
				usuActual.setSegundoApellido("Activo");
			}
		 
			dpi.setJustificante(pagoSolicitudForm.getNumeroSolicitud());
			dpi.setCodigoTasa(pagoSolicitudForm.getNumeroSolicitud().substring(3, 6));

			float importeActual =  pagoSolicitudForm.getImporteOriginal()!=null && pagoSolicitudForm.getImporteOriginal()>0 && !"5".equals(pagoSolicitudForm.getMotivoAdeudo()) ? pagoSolicitudForm.getImporteOriginal() : pagoSolicitudForm.getImporte();
			int decimalPlaces = 2;
			BigDecimal bd = new BigDecimal(importeActual);

			// setScale is immutable
			bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
			double importeActual2 = bd.doubleValue();

			dpi.setImporte(importeActual2);

			String isPruebaStr = properties.getProperty("conf.validacionInscripcion");
			boolean isPrueba = "false".equals(isPruebaStr);
			

			if(isPrueba){
				String nJustificante = properties.getProperty("conf.nJustificantePrueba");
				String importe = properties.getProperty("conf.importePrueba");
				String nif = properties.getProperty("conf.nifPrueba");
				String cuenta = properties.getProperty("conf.cuentaPrueba");
				double dImporte = Double.valueOf(importe);
				BigDecimal bdp = new BigDecimal(dImporte);

				// setScale is immutable
				bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
				double importeActualPrueba = bdp.doubleValue();

				dpi.setJustificante(nJustificante);
				dpi.setImporte(importeActualPrueba);
			}		 

			// TODO Incidencia IPSC-NIE ciudadano.
			Validacion validacion = new Validacion();
			
			
			if(validacion.nifValidate(pagoSolicitudForm.getNif())){
				dpi.setTipoDocumentoObligado(TiposDocumento.fromValue(1));
			}else if(validacion.nieValidate(pagoSolicitudForm.getNif())){
				dpi.setTipoDocumentoObligado(TiposDocumento.fromValue(3));
			}
			dpi.setDocumentoObligado(pagoSolicitudForm.getNif());
			dpi.setNombre(HtmlUtils.htmlEscape(pagoSolicitudForm.getNombre()));			
			dpi.setApellido1(HtmlUtils.htmlEscape(pagoSolicitudForm.getApellido1()));
			dpi.setApellido2(HtmlUtils.htmlEscape(pagoSolicitudForm.getApellido2()));
			
			// Datos del pagador, titular de la tarjeta
			if(validacion.nifValidate(pagoSolicitudForm.getNifTitular())){
				dpi.setTipoDocumentoPagador(TiposDocumento.fromValue(1));
			}else if(validacion.nieValidate(pagoSolicitudForm.getNifTitular())){
				dpi.setTipoDocumentoPagador(TiposDocumento.fromValue(3));
			}
			dpi.setDocumentoPagador(pagoSolicitudForm.getNifTitular().toUpperCase());
			// Descomentar cuando EPAGO anyada los campos a la peticion de entrada
			dpi.setNombrePagador(HtmlUtils.htmlEscape(pagoSolicitudForm.getNombreTitular()));
			dpi.setApellido1Pagador(HtmlUtils.htmlEscape(pagoSolicitudForm.getApellido1Titular()));
			dpi.setApellido2Pagador(HtmlUtils.htmlEscape(pagoSolicitudForm.getApellido2Titular()));

			if(pagoSolicitudForm.getFormPago().equalsIgnoreCase(Constantes.PAGO_TIPO_ADEUDO_CHAR)){
				StringBuffer numeroCuenta = new StringBuffer();
				
				ParametrosConfiguracionBean parametros = parametroConfiguracionManager.obtenerParametrosConfiguracion(Constantes. PARAMETRO_CONFIGURACION_CAMBIO_EPAGO);
				// Comprobamos si se ha produccido cambios en ePago para que nosotros introduzcamos los espacios en el campo CCC o no
				if(parametros.getValorCampo().equals("0")){
				// TODO Adaptacion IBAN 2015
					numeroCuenta.append(pagoSolicitudForm.getIBAN());
				}else{
					numeroCuenta.append(pagoSolicitudForm.getIBAN()).append("          ");
				}		
				
				dpi.setTipoCargo(TiposCargo.fromValue(0));
				dpi.setCCC(numeroCuenta.toString()); 
				dpi.setCodigoBanco(pagoSolicitudForm.getBancoAdeudo());				 
			}
			else if(pagoSolicitudForm.getFormPago().equalsIgnoreCase(Constantes.PAGO_TIPO_TARJETA_CHAR)){
				StringBuffer numeroTarjeta = new StringBuffer();
				numeroTarjeta.append(pagoSolicitudForm.getNumTarjeta2()).append(pagoSolicitudForm.getNumTarjeta3())
					.append(pagoSolicitudForm.getNumTarjeta4()).append(pagoSolicitudForm.getNumTarjeta5());
				Calendar date = Calendar.getInstance();

				int CaducidadMes = Integer.parseInt(pagoSolicitudForm.getCaducidadTarjeta1());
				if(CaducidadMes > 0) CaducidadMes--; //Utilizando CALENDAR Enero corresponde a 0
				date.set(Calendar.MONTH, CaducidadMes);
				date.set(Calendar.YEAR, Integer.parseInt(pagoSolicitudForm.getCaducidadTarjeta2()));				 

				dpi.setTipoCargo(TiposCargo.fromValue(1));
				dpi.setCodigoBanco(pagoSolicitudForm.getEntidadTarjeta());
				dpi.setNumeroTarjeta(numeroTarjeta.toString());
				dpi.setFechaCaducidadTarjeta(date);
			}
			
			// se indica a la petición de ePago el tipo de autenticación que utilizó el usuario
			if (Constantes.AUTENTICACION_CERTIFICADOE.equals(usuActual.getTipoAutenticacion().toUpperCase())) {
				dpi.setTipoAutenticacion(Constantes.CODIGO_AUTENTICACION_CERTIFICADOE);
			} else if (Constantes.AUTENTICACION_CLAVE_PIN.equals(usuActual.getTipoAutenticacion().toUpperCase())) {
				dpi.setTipoAutenticacion(Constantes.CODIGO_AUTENTICACION_CLAVE_PIN);
			} else if (Constantes.AUTENTICACION_CLAVE_PERMANENTE.equals(usuActual.getTipoAutenticacion().toUpperCase())) {
				dpi.setTipoAutenticacion(Constantes.CODIGO_AUTENTICACION_CLAVE_PERMANENTE);
			}
								
			int idOrganismo = 0;
			
			try {
				idOrganismo = Integer.parseInt(properties.getProperty("conf.idOrganismo"));
			} catch (NumberFormatException e) {
				logger.error("La propiedad idOrganismo no se encuentra en el fichero " + RESOURCE_BUNDLE + " o no tiene un formato válido.");
				logger.error("Se va a indicar como idOrganismo el valor 0 por defecto.", e);
				idOrganismo = 0;
			}
			
			PPServiceInterfaceSoap11Stub binding = (PPServiceInterfaceSoap11Stub)locator.getPPServiceInterfaceSoap11();
			binding.setTimeout(TIMEOUT);
			logger.info("Lanza llamada al web service");
			logger.info("Datos de Pago enviados a la Pasarela: ");

			mostrarInfoPago(dpi);

			Date date1 = new Date();

			if(!isPrueba){
				defaultDatosPagoClaveOut =  binding.hacerPagoClave(dpi, idOrganismo);
				// TODO Necesario por los errores en la pasarela de pago.
			
			}else{		
				defaultDatosPagoClaveOut =  binding.consultarPagoClave(dpi, idOrganismo);
			}

			Date date2 = new Date();
			logger.warn("Llamada Pasarela end: "+(date2.getTime()-date1.getTime()));


		} catch (ServiceException e) {
			logger.error("Error datos pago clave",e);
			defaultDatosPagoClaveOut = null;
		} catch (RemoteException re) {
			logger.error("Error pago solicitud tiempo conexion",re);
			defaultDatosPagoClaveOut = null;
			this.setSessionAttribute("errorForm2", RESOURCE_BUNDLE.getString("field.form.pagoSolicitud.error.tiempoConexion"));
			this.getRequest().setAttribute("errorPasarela", RESOURCE_BUNDLE.getString("field.form.pagoSolicitud.error.tiempoConexion"));
		} 
		
		logger.info("Fin de conexionPasarela");
		
		return defaultDatosPagoClaveOut;	
	}
	// FIN - cpasculi - IPS-147 - Actualizacion cliente ePago
	/**
	 * Iban test.
	 *
	 * @param accountNumber el account number
	 * @return verdadero, si tiene exito
	 */
	public static boolean ibanTest(String accountNumber) {
		String newAccountNumber = accountNumber.trim();

		// Check that the total IBAN length is correct as per the country. If not, the IBAN is invalid. We could also check
		// for specific length according to country, but for now we won't
		if (newAccountNumber.length() < IBANNUMBER_MIN_SIZE || newAccountNumber.length() > IBANNUMBER_MAX_SIZE) {
			return false;
		}

		// Move the four initial characters to the end of the string.
		newAccountNumber = newAccountNumber.substring(4) + newAccountNumber.substring(0, 4);

		// Replace each letter in the string with two digits, thereby expanding the string, where A = 10, B = 11, ..., Z = 35.
		StringBuilder numericAccountNumber = new StringBuilder();
		for (int i = 0;i < newAccountNumber.length();i++) {
			numericAccountNumber.append(Character.getNumericValue(newAccountNumber.charAt(i)));
		}

		// Interpret the string as a decimal integer and compute the remainder of that number on division by 97.
		BigInteger ibanNumber = new BigInteger(numericAccountNumber.toString());
		
		return ibanNumber.mod(IBANNUMBER_MAGIC_NUMBER).intValue() == 1;
	}

	/**
	 * To pago solicitud bean.
	 *
	 * @param pagoSolicitudForm el pago solicitud form
	 * @param idSolicitud el id solicitud
	 * @param defaultDatosPagoClaveOut el default datos pago clave out
	 * @return el pago solicitud bean
	 */
	private PagoSolicitudBean toPagoSolicitudBean(
			PagoSolicitudForm pagoSolicitudForm, Long idSolicitud, DefaultDatosPagoClaveOut defaultDatosPagoClaveOut) {
		
		PagoSolicitudBean aux = new PagoSolicitudBean();
		
		if(idSolicitud != null){
			aux.setIdSolicitud(String.valueOf(idSolicitud));
		}
		Date fechaAux = new Date();

		aux.setFechaIntento(fechaAux);
		
		if(pagoSolicitudForm.getFormPago() != null){
			aux.setTipo(pagoSolicitudForm.getFormPago().charAt(0));
		}
		
		if(pagoSolicitudForm.getImporte() != null && !pagoSolicitudForm.getFormPago().equalsIgnoreCase(Constantes.FORMA_PAGO_EXENTO_S)){
			//se cambia para igualar el importe envido a epago con el almacenado en BDDD.
			aux.setImporte(Float.valueOf( pagoSolicitudForm.getImporteOriginal()!=null && pagoSolicitudForm.getImporteOriginal()>0 && !"5".equals(pagoSolicitudForm.getMotivoAdeudo()) ? pagoSolicitudForm.getImporteOriginal() : pagoSolicitudForm.getImporte()));
			//aux.setImporte(Float.valueOf(pagoSolicitudForm.getImporte()));
		}else{
			Float auxImporte = 0f;
			aux.setImporte(auxImporte);
		}
		aux.setResultado(Constantes.RESULTADO_PAGO_OK);
		aux.setNumeroSolicitud(pagoSolicitudForm.getNumeroSolicitud());
		
		if(pagoSolicitudForm.getMotivo()!= null && !"0".equals(pagoSolicitudForm.getMotivo())
				&& !"".equals(pagoSolicitudForm.getMotivo())){
			aux.setReduccionPago(Constantes.REDUCCION_PAGO_SI);
			aux.setMotivo(pagoSolicitudForm.getMotivo());
		}
		
		if(pagoSolicitudForm.getMotivoAdeudo()!= null && !"0".equals(pagoSolicitudForm.getMotivoAdeudo())
				&& !"".equals(pagoSolicitudForm.getMotivoAdeudo())){
			aux.setReduccionPago(Constantes.REDUCCION_PAGO_SI);
			aux.setMotivo(pagoSolicitudForm.getMotivoAdeudo());
		}
		
		if(pagoSolicitudForm.getMotivoTarjeta()!= null && !"0".equals(pagoSolicitudForm.getMotivoTarjeta())
				&& !"".equals(pagoSolicitudForm.getMotivoTarjeta())){
			aux.setReduccionPago(Constantes.REDUCCION_PAGO_SI);
			aux.setMotivo(pagoSolicitudForm.getMotivoTarjeta());
		}
		
		if(pagoSolicitudForm.getEntidadAdeudo() != null && !"".equals(pagoSolicitudForm.getEntidadAdeudo())
				&& !"0".equals(pagoSolicitudForm.getEntidadAdeudo())){
			aux.setEntidad(pagoSolicitudForm.getEntidadAdeudo());
		}
		
		if(pagoSolicitudForm.getEntidadTarjeta() != null && !"".equals(pagoSolicitudForm.getEntidadTarjeta())
				&& !"0".equals(pagoSolicitudForm.getEntidadTarjeta())){
			aux.setEntidad(pagoSolicitudForm.getEntidadTarjeta());
		}
		
		// INI - cpasculi - IPS-147 - Actualizar cliente ePago
		
		if(!pagoSolicitudForm.getFormPago().equals(Constantes.FORMA_PAGO_EXENTO_S) && defaultDatosPagoClaveOut!=null){
			if(defaultDatosPagoClaveOut.getFechaOperacion() != null){
				aux.setFechaPago(defaultDatosPagoClaveOut.getFechaOperacion().getTime());
			}

			if(defaultDatosPagoClaveOut.getNRC() != null){
				aux.setNrc(defaultDatosPagoClaveOut.getNRC().trim());
			}else{
				aux.setNrc("");
			}
		// FIN - cpasculi - IPS-147 - Actualizar cliente ePago

			if(pagoSolicitudForm.getEntidadAdeudo() != null && !"".equals(pagoSolicitudForm.getEntidadAdeudo()) && !"0".equals(pagoSolicitudForm.getEntidadAdeudo())){
				aux.setEntidad(pagoSolicitudForm.getEntidadAdeudo() );
			}else{
				if(pagoSolicitudForm.getEntidadTarjeta() != null && !"".equals(pagoSolicitudForm.getEntidadTarjeta()) && !"0".equals(pagoSolicitudForm.getEntidadTarjeta())){
					aux.setEntidad(pagoSolicitudForm.getEntidadTarjeta());
				}
			}
		}

		return aux;
	}

	/**
	 * To pago solicitud bean funcionario.
	 *
	 * @param pagoSolicitudForm el pago solicitud form
	 * @param idSolicitud el id solicitud
	 * @return el pago solicitud bean
	 */
	private PagoSolicitudBean toPagoSolicitudBeanFuncionario(PagoSolicitudForm pagoSolicitudForm, Long idSolicitud) {
		
		PagoSolicitudBean aux = new PagoSolicitudBean();
		
		// id solicitud
		if(idSolicitud != null){
			aux.setIdSolicitud(String.valueOf(idSolicitud));
		}
		
		// fecha registro pago bb.dd
		Date fechaAux = new Date();
		aux.setFechaIntento(fechaAux);

		// tipo de pago
		if (pagoSolicitudForm.getFormPago()!=null){
			aux.setTipo(pagoSolicitudForm.getFormPago().charAt(0));
		}
						
		// importe pago
		if(pagoSolicitudForm.getImporte() != null){
			aux.setImporte(Float.valueOf(pagoSolicitudForm.getImporte()));
		}else{
			Float auxImporte = 0f;
			aux.setImporte(auxImporte);
		}
		
		// resultado OK
		aux.setResultado(Constantes.RESULTADO_PAGO_OK);
				
		if(pagoSolicitudForm.getMotivo()!= null && !"0".equals(pagoSolicitudForm.getMotivo())
				&& !"".equals(pagoSolicitudForm.getMotivo())){
			aux.setReduccionPago(Constantes.REDUCCION_PAGO_SI);
			aux.setMotivo(pagoSolicitudForm.getMotivo());
		}
		if(pagoSolicitudForm.getMotivoAdeudo()!= null && !"0".equals(pagoSolicitudForm.getMotivoAdeudo())
				&& !"".equals(pagoSolicitudForm.getMotivoAdeudo())){
			aux.setReduccionPago(Constantes.REDUCCION_PAGO_SI);
			aux.setMotivo(pagoSolicitudForm.getMotivoAdeudo());
		}
		
		if(pagoSolicitudForm.getMotivoTarjeta()!= null && !"0".equals(pagoSolicitudForm.getMotivoTarjeta())
				&& !"".equals(pagoSolicitudForm.getMotivoTarjeta())){
			aux.setReduccionPago(Constantes.REDUCCION_PAGO_SI);
			aux.setMotivo(pagoSolicitudForm.getMotivoTarjeta());
		}

		if(pagoSolicitudForm.getNrcCiudadano() != null && !"".equals(pagoSolicitudForm.getNrcCiudadano())){
			aux.setNrcCiudadano(pagoSolicitudForm.getNrcCiudadano().trim());
		}
		
		return aux;
	}

	/**
	 * To pago solicitud bean error.
	 *
	 * @param pagoSolicitudForm el pago solicitud form
	 * @param idSolicitud el id solicitud
	 * @param defaultDatosPagoClaveOut el default datos pago clave out
	 * @return el pago solicitud bean
	 */
	private PagoSolicitudBean toPagoSolicitudBeanError(
			PagoSolicitudForm pagoSolicitudForm, Long idSolicitud, DefaultDatosPagoClaveOut defaultDatosPagoClaveOut) {
		
		PagoSolicitudBean aux = new PagoSolicitudBean();
		
		if(idSolicitud != null){
			aux.setIdSolicitud(String.valueOf(idSolicitud));
		}
		Date fechaAux = new Date();

		aux.setFechaIntento(fechaAux);
		
		if (pagoSolicitudForm != null) {
			if(pagoSolicitudForm.getFormPago() != null){
				aux.setTipo(pagoSolicitudForm.getFormPago().charAt(0));
			}
			
			if(pagoSolicitudForm.getImporte()!= null){
				aux.setImporte(Float.valueOf(pagoSolicitudForm.getImporte()));
			}else{
				Float auxImporte = 0f;
				aux.setImporte(auxImporte);
			}
			aux.setResultado(Constantes.RESULTADO_PAGO_SOLICITUD_ER);
			aux.setNumeroSolicitud(pagoSolicitudForm.getNumeroSolicitud());
			
			if(pagoSolicitudForm.getMotivo()!= null && !"0".equals(pagoSolicitudForm.getMotivo())
					&& !"".equals(pagoSolicitudForm.getMotivo())){
				aux.setReduccionPago(Constantes.REDUCCION_PAGO_SI);
				aux.setMotivo(pagoSolicitudForm.getMotivo());
			}
			
			if(pagoSolicitudForm.getMotivoAdeudo()!= null && !"0".equals(pagoSolicitudForm.getMotivoAdeudo())
					&& !"".equals(pagoSolicitudForm.getMotivoAdeudo())){
				aux.setReduccionPago(Constantes.REDUCCION_PAGO_SI);
				aux.setMotivo(pagoSolicitudForm.getMotivoAdeudo());
			}
			
			if(pagoSolicitudForm.getMotivoTarjeta()!= null && !"0".equals(pagoSolicitudForm.getMotivoTarjeta())
					&& !"".equals(pagoSolicitudForm.getMotivoTarjeta())){
				aux.setReduccionPago(Constantes.REDUCCION_PAGO_SI);
				aux.setMotivo(pagoSolicitudForm.getMotivoTarjeta());
			}
			
			if(pagoSolicitudForm.getEntidadAdeudo() != null && !"".equals(pagoSolicitudForm.getEntidadAdeudo())
					&& !"0".equals(pagoSolicitudForm.getEntidadAdeudo())){
				aux.setEntidad(pagoSolicitudForm.getEntidadAdeudo());
			}
			
			if(pagoSolicitudForm.getEntidadTarjeta() != null && !"".equals(pagoSolicitudForm.getEntidadTarjeta())
					&& !"0".equals(pagoSolicitudForm.getEntidadTarjeta())){
				aux.setEntidad(pagoSolicitudForm.getEntidadTarjeta());
			}
			
			if(pagoSolicitudForm.getNrc() != null){
				aux.setNrc(pagoSolicitudForm.getNrc().trim());
			}
		}
		return aux;
	}
	
	/**
	 * Metodo que guarda los datos relacionados de cada solicitud a su CCAA .
	 *
	 * @param pagoSolicitudForm el pago solicitud form
	 * @param solicitud el solicitud
	 */
	private void guardarSolicitudCcaa(PagoSolicitudForm pagoSolicitudForm, SolicitudBean solicitud){
		
		SolicitudCcaaBean  solicitudCcaaBean = null;

		// Guardamos en la tabla SolicitudCCAA si es Familia Numerosa,General o Discapacidad
		if(pagoSolicitudForm.getMotivo() != null && (Integer.valueOf(pagoSolicitudForm.getMotivo())==1 || Integer.valueOf(pagoSolicitudForm.getMotivo())==3 || Integer.valueOf(pagoSolicitudForm.getMotivo())==5)){
			solicitudCcaaBean = toSolicitudCcaaBean(pagoSolicitudForm,solicitud.getId(),pagoSolicitudForm.getMotivo());
		}

		// Guardamos en la tabla SolicitudCCAA si es Familia Numerosa,General o Discapacidad
		if(pagoSolicitudForm.getMotivoAdeudo() != null && (Integer.valueOf(pagoSolicitudForm.getMotivoAdeudo())==1 || Integer.valueOf(pagoSolicitudForm.getMotivoAdeudo())==3 || Integer.valueOf(pagoSolicitudForm.getMotivoAdeudo())==5)){
			solicitudCcaaBean = toSolicitudCcaaBean(pagoSolicitudForm,solicitud.getId(),pagoSolicitudForm.getMotivoAdeudo());
		}

		// Guardamos en la tabla SolicitudCCAA si es Familia Numerosa,General o Discapacidad
		if(pagoSolicitudForm.getMotivoTarjeta() != null && (Integer.valueOf(pagoSolicitudForm.getMotivoTarjeta())==1 || Integer.valueOf(pagoSolicitudForm.getMotivoTarjeta())==3 || Integer.valueOf(pagoSolicitudForm.getMotivoTarjeta())==5)){
			solicitudCcaaBean = toSolicitudCcaaBean(pagoSolicitudForm,solicitud.getId(),pagoSolicitudForm.getMotivoTarjeta());
		}

		if(null != solicitudCcaaBean){
			solicitudCcaaManager.guardarSolicitudCcaa(solicitudCcaaBean);
		}
	}
	
	/**
	 * Metodo que guarda los datos relacionados de cada solicitud a su CCAA para funcionario habilitado.
	 *
	 * @param pagoSolicitudForm el pago solicitud form
	 * @param solicitud el solicitud
	 */
	private void guardarSolicitudCcaaFuncionario(PagoSolicitudForm pagoSolicitudForm, SolicitudBean solicitud){
	
		SolicitudCcaaBean  solicitudCcaaBean = null;
						
		if(solicitud.getId()!=null && (pagoSolicitudForm.getMotivo()!=null || pagoSolicitudForm.getMotivoTarjeta() !=null || pagoSolicitudForm.getMotivoAdeudo()!=null )) {
			
			if (Integer.valueOf(pagoSolicitudForm.getMotivo())==1 || Integer.valueOf(pagoSolicitudForm.getMotivo())==3 || Integer.valueOf(pagoSolicitudForm.getMotivo())==5
					|| Integer.valueOf(pagoSolicitudForm.getMotivoAdeudo())==1 || Integer.valueOf(pagoSolicitudForm.getMotivoAdeudo())==3 || Integer.valueOf(pagoSolicitudForm.getMotivoAdeudo())==5
					|| Integer.valueOf(pagoSolicitudForm.getMotivoTarjeta())==1 || Integer.valueOf(pagoSolicitudForm.getMotivoTarjeta())==3 || Integer.valueOf(pagoSolicitudForm.getMotivoTarjeta())==5){
			
				solicitudCcaaBean = new SolicitudCcaaBean();
				solicitudCcaaBean.setIdSolicitud(solicitud.getId());
							
				if(Integer.valueOf(pagoSolicitudForm.getMotivo())==1 || Integer.valueOf(pagoSolicitudForm.getMotivoAdeudo())==1 || Integer.valueOf(pagoSolicitudForm.getMotivoTarjeta())==1){
					if(pagoSolicitudForm.getIdComunidadDDExento()!=null && !"".equals(pagoSolicitudForm.getIdComunidadDDExento())){
						solicitudCcaaBean.setIdComunidadDD(pagoSolicitudForm.getIdComunidadDDExento());
					}
				} else if (Integer.valueOf(pagoSolicitudForm.getMotivo())==3 || Integer.valueOf(pagoSolicitudForm.getMotivo())==5
						|| (Integer.valueOf(pagoSolicitudForm.getMotivoAdeudo())==3 || Integer.valueOf(pagoSolicitudForm.getMotivoAdeudo())==5)
						|| (Integer.valueOf(pagoSolicitudForm.getMotivoTarjeta())==3 || Integer.valueOf(pagoSolicitudForm.getMotivoTarjeta())==5)) {
					if(pagoSolicitudForm.getIdComunidadFNExento()!=null && !"".equals(pagoSolicitudForm.getIdComunidadFNExento())){
						solicitudCcaaBean.setIdComunidadFN(pagoSolicitudForm.getIdComunidadFNExento());
					}
				}
			}
		}
		
		if( (pagoSolicitudForm.getTituloFNExento() !=null && !"".equals(pagoSolicitudForm.getTituloFNExento()) && solicitudCcaaBean != null) || (pagoSolicitudForm.getIdComunidadFNExento() != null && !"".equals(pagoSolicitudForm.getIdComunidadFNExento()) && solicitudCcaaBean != null)){
			solicitudCcaaBean.setTituloFamnumerosa(pagoSolicitudForm.getTituloFNExento());
			solicitudCcaaManager.guardarSolicitudCcaa(solicitudCcaaBean);
		}
		
	}
	
	/**
	 * To solicitud ccaa bean.
	 *
	 * @param pagoSolicitudForm el pago solicitud form
	 * @param idSolicitud el id solicitud
	 * @param motivoReduccionTasa el motivo reduccion tasa
	 * @return el solicitud ccaa bean
	 */
	private SolicitudCcaaBean toSolicitudCcaaBean(PagoSolicitudForm pagoSolicitudForm, Long idSolicitud,String motivoReduccionTasa) {
		
		SolicitudCcaaBean aux = new SolicitudCcaaBean();
		
		if(idSolicitud != null){
			aux.setIdSolicitud(idSolicitud);
		}

		if(Integer.valueOf(motivoReduccionTasa)==1){
			if(pagoSolicitudForm.getIdComunidadDD()!=null && !"".equals(pagoSolicitudForm.getIdComunidadDD())){
				aux.setIdComunidadDD(pagoSolicitudForm.getIdComunidadDD());
			}
			if(pagoSolicitudForm.getIdComunidadDDExento()!=null && !"".equals(pagoSolicitudForm.getIdComunidadDDExento())){
				aux.setIdComunidadDD(pagoSolicitudForm.getIdComunidadDDExento());
			}
		}
		
		if(Integer.valueOf(motivoReduccionTasa)==3 || Integer.valueOf(motivoReduccionTasa)==5){
			if(pagoSolicitudForm.getIdComunidadFNExento()!=null && !"".equals(pagoSolicitudForm.getIdComunidadFNExento())){
				aux.setIdComunidadFN(pagoSolicitudForm.getIdComunidadFNExento());
			}
			if(pagoSolicitudForm.getIdComunidadFNTarjeta()!=null && !"".equals(pagoSolicitudForm.getIdComunidadFNTarjeta())){
				aux.setIdComunidadFN(pagoSolicitudForm.getIdComunidadFNTarjeta());
			}
			if(pagoSolicitudForm.getIdComunidadFNCuenta()!=null && !"".equals(pagoSolicitudForm.getIdComunidadFNCuenta())){
				aux.setIdComunidadFN(pagoSolicitudForm.getIdComunidadFNCuenta());
			}
		}
				
		if(pagoSolicitudForm.getIdtituloFNExento()!=null && !"".equals(pagoSolicitudForm.getIdtituloFNExento())){
			aux.setTituloFamnumerosa(pagoSolicitudForm.getIdtituloFNExento());
		}
		
		if(pagoSolicitudForm.getIdtituloFNTarjeta()!=null && !"".equals(pagoSolicitudForm.getIdtituloFNTarjeta())){
			aux.setTituloFamnumerosa(pagoSolicitudForm.getIdtituloFNTarjeta());
		}
		
		if(pagoSolicitudForm.getIdtituloFNCuenta()!=null && !"".equals(pagoSolicitudForm.getIdtituloFNCuenta())){
			aux.setTituloFamnumerosa(pagoSolicitudForm.getIdtituloFNCuenta());
		}

		return aux;
	}

	/**
	 * Metodo que muestra los datos enviados a la pasarela de pago.
	 *
	 * @param dpi DefaultDatosPagoIn
	 */
	
	// INI - cpasculi - IPS-147 - Actualizacion cliente ePago
	private void mostrarInfoPago(DefaultDatosPagoClaveIn dpi) {
		try {
			if(dpi!=null){
				if(dpi.getNombre()!=null){
					logger.info("_Nombre: "+dpi.getNombre());
				}
				if(dpi.getApellido1()!=null){
					logger.info("_Apellido1: "+dpi.getApellido1());
				}
				if(dpi.getApellido2()!=null){
					logger.info("_Apellido2: "+dpi.getApellido2());
				}
				if(dpi.getDocumentoObligado()!=null){
					logger.info("_NIF: "+dpi.getDocumentoObligado());
				}
				
				logger.info("_Importe: " + String.valueOf(dpi.getImporte()));
				
			}
		} catch (Exception e) {
			logger.error("Error mostrar info pago",e);
		}
		
	}
	
	// FIN - cpasculi - IPS-147 - Actualizar cliente ePago
	
	/**
	 * Obtiene el pago solicitudes manager.
	 *
	 * @return el pago solicitudes manager
	 */
	public PagoSolicitudManager getPagoSolicitudesManager() {
		return pagoSolicitudesManager;
	}

	/**
	 * Establece el pago solicitudes manager.
	 *
	 * @param pagoSolicitudesManager el nuevo pago solicitudes manager
	 */
	public void setPagoSolicitudesManager(
			PagoSolicitudManager pagoSolicitudesManager) {
		this.pagoSolicitudesManager = pagoSolicitudesManager;
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
	 * Obtiene el motivo reduccion manager.
	 *
	 * @return el motivo reduccion manager
	 */
	public MotivoReduccionManager getMotivoReduccionManager() {
		return motivoReduccionManager;
	}

	/**
	 * Establece el motivo reduccion manager.
	 *
	 * @param motivoReduccionManager el nuevo motivo reduccion manager
	 */
	public void setMotivoReduccionManager(
			MotivoReduccionManager motivoReduccionManager) {
		this.motivoReduccionManager = motivoReduccionManager;
	}

	/**
	 * Obtiene el estado solicitud manager.
	 *
	 * @return el estado solicitud manager
	 */
	public EstadoSolicitudManager getEstadoSolicitudManager() {
		return estadoSolicitudManager;
	}

	/**
	 * Establece el estado solicitud manager.
	 *
	 * @param estadoSolicitudManager el nuevo estado solicitud manager
	 */
	public void setEstadoSolicitudManager(
			EstadoSolicitudManager estadoSolicitudManager) {
		this.estadoSolicitudManager = estadoSolicitudManager;
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
	 * Obtiene el entidad financiera manager.
	 *
	 * @return el entidad financiera manager
	 */
	public EntidadFinancieraManager getEntidadFinancieraManager() {
		return entidadFinancieraManager;
	}

	/**
	 * Establece el entidad financiera manager.
	 *
	 * @param entidadFinancieraManager el nuevo entidad financiera manager
	 */
	public void setEntidadFinancieraManager(
			EntidadFinancieraManager entidadFinancieraManager) {
		this.entidadFinancieraManager = entidadFinancieraManager;
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

}
