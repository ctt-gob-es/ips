package es.map.ipsc.spring.solicitudes;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.xml.rpc.ServiceException;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.spring.Globals;

import ePago.gob.es.schemas.DefaultDatosPagoIn;
import ePago.gob.es.schemas.DefaultDatosPagoOut;
import ePago.gob.es.schemas.PPServiceInterfaceServiceLocator;
import ePago.gob.es.schemas.PPServiceInterfaceSoap11Stub;
import ePago.gob.es.schemas.TiposCargo;
import ePago.gob.es.schemas.TiposDocumento;
import es.guadaltel.framework.signer.impl.util.Base64Coder;
import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.util.BeanFormatter;
import es.map.ips.model.ComunidadesQuery;
import es.map.ips.model.ConfiguracionErroresQuery;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.DatosPropiosConfiguracion;
import es.map.ips.model.DatosPropiosConfiguracionQuery;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.Grupo;
import es.map.ips.model.MotivoReduccionTasa;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ips.model.PlantillaQuery;
import es.map.ips.model.RegistroSolicitudQuery;
import es.map.ips.model.SolicitudCcaaQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.TipoDiscapacidadQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.AltaSolicitudBean; 
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.ComunidadesBean;
import es.map.ipsc.bean.ConfiguracionErroresBean;
import es.map.ipsc.bean.ConvocatoriaBean;
import es.map.ipsc.bean.DetallePagoSolicitudBean;
import es.map.ipsc.bean.DetalleRegistroSolicitudBean;
import es.map.ipsc.bean.DetalleSolicitudBean;
import es.map.ipsc.bean.DocumentoBean;
import es.map.ipsc.bean.EntidadBean;
import es.map.ipsc.bean.PagoSolicitudBean;
import es.map.ipsc.bean.PaisBean;
import es.map.ipsc.bean.ParametrosConfiguracionBean;
import es.map.ipsc.bean.PlantillaBean;
import es.map.ipsc.bean.PlantillaPropiosBean;
import es.map.ipsc.bean.ProvinciaBean;
import es.map.ipsc.bean.RegistroSolicitudBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.bean.SolicitudCcaaBean;
import es.map.ipsc.bean.TipoDiscapacidadBean;
import es.map.ipsc.form.PagoSolicitudForm;
import es.map.ipsc.form.SolicitudForm;
import es.map.ipsc.manager.comunidades.ComunidadesManager;
import es.map.ipsc.manager.configuracionErrores.ConfiguracionErroresManager;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.manager.cuerpoEscala.CuerpoEscalaManager;
import es.map.ipsc.manager.datosPropiosConfiguracion.DatosPropiosConfiguracionManager;
import es.map.ipsc.manager.entidadFinanciera.EntidadFinancieraManager;
import es.map.ipsc.manager.logs.LogServiciosManager;
import es.map.ipsc.manager.plantilla.PlantillaManager;
import es.map.ipsc.manager.solicitudes.EstadoSolicitudManager;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.RegistroSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudCcaaManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.tablaBase.ParametroConfiguracionManager;
import es.map.ipsc.manager.tablaBase.TablaBaseManager;
import es.map.ipsc.manager.tasas.MotivoReduccionManager;
import es.map.ipsc.manager.tasas.TarifaManager;
import es.map.ipsc.spring.AbstractSecureSpring;
import es.map.ipsc.utils.SignDataUtil;

/**
 * El Class VerSolicitudUnificado.
 */
public class VerSolicitudUnificado extends AbstractSecureSpring  {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerSolicitudUnificado.class);

	/** La constante BUNDLE_ERROR. */
	private static final String BUNDLE_ERROR = "MessageResources";
	
	/** La constante BUNDLE_MESSAGES. */
	private static final String BUNDLE_MESSAGES = "MessageResources";
	
	/** La constante BUNDLE_NAME. */
	private static final String BUNDLE_NAME = "file";
	
	/** La constante STRING_LLAMADA. */
	private static final String STRING_LLAMADA = "llamada";
	
	/** La constante STRING_USUARIO_CLAVE. */
	private static final String STRING_USUARIO_CLAVE = "usuarioClave";
	
	/** La constante STRING_ERROR_DESCRIPCION. */
	private static final String STRING_ERROR_DESCRIPCION = "errorDescripcion";
	
	/** La constante STRING_SOLICITUD_ERROR_USUARIO. */
	private static final String STRING_SOLICITUD_ERROR_USUARIO = "solicitud.error.usuario";
	
	/** La constante STRING_ERROR_USUARIO. */
	private static final String STRING_ERROR_USUARIO = "errorUsuario";
	
	/** La constante STRING_REGISTRO. */
	private static final String STRING_REGISTRO = "registro";
	
	/** La constante STRING_SOLICITUD. */
	private static final String STRING_SOLICITUD = "solicitud";
	
	/** La constante STRING_CERTPAGO. */
	private static final String STRING_CERTPAGO = "certPago";
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/** La constante STRING_SIMPLE_DATE_FORMAT. */
	private static final String STRING_SIMPLE_DATE_FORMAT = "dd/MM/yyyy";
	
	/** La constante STRING_ERROR_FORM2. */
	private static final String STRING_ERROR_FORM2 = "errorForm2";
	
	
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_ERROR);
	
	/** La constante RESOURCE_MESSAGES. */
	private static final ResourceBundle RESOURCE_MESSAGES = ResourceBundle.getBundle(BUNDLE_MESSAGES);
	
	/** La constante RESOURCE_BUNDLE_NAME. */
	private static final ResourceBundle RESOURCE_BUNDLE_NAME = ResourceBundle.getBundle(BUNDLE_NAME);


	/** el no pagado. */
	private static Integer noPagado = Integer.parseInt(RESOURCE_MESSAGES.getString("solicitud.estado.noPagado"));
	
	/** el no registrado. */
	private static Integer noRegistrado = Integer.parseInt(RESOURCE_MESSAGES.getString("solicitud.estado.noRegistrado"));
	
	/** el registrado. */
	private static Integer registrado = Integer.parseInt(RESOURCE_MESSAGES.getString("solicitud.estado.registrado"));
	
	/** el pendiente registro. */
	private static Integer pendienteRegistro = Integer.parseInt(RESOURCE_MESSAGES.getString("solicitud.estado.pendienteRegistro"));
	
	/** el proceso pago. */
	private static Integer procesoPago = Integer.parseInt(RESOURCE_MESSAGES.getString("solicitud.estado.procesoPago"));
	
	/** el proceso registro. */
	private static Integer procesoRegistro = Integer.parseInt(RESOURCE_MESSAGES.getString("solicitud.estado.procesoRegistro"));

	/** el tablas base manager. */
	private TablaBaseManager tablasBaseManager;
	
	/** el plantilla manager. */
	private PlantillaManager plantillaManager;
	
	/** el parametro configuracion manager. */
	private ParametroConfiguracionManager parametroConfiguracionManager;
	
	/** el pago solicitudes manager. */
	private PagoSolicitudManager pagoSolicitudesManager;
	
	/** el solicitudes manager. */
	private SolicitudManager solicitudesManager;
	
	/** el motivo reduccion manager. */
	private MotivoReduccionManager motivoReduccionManager;
	
	/** el comunidades manager. */
	private ComunidadesManager comunidadesManager;
	
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
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el registro solicitud manager. */
	private RegistroSolicitudManager registroSolicitudManager;
	
	/** el documentos convocatoria manager. */
	private DocumentosConvocatoriaManager documentosConvocatoriaManager;
	
	/** el tarifa manager. */
	private TarifaManager tarifaManager;
	
	/** el solicitud ccaa manager. */
	private SolicitudCcaaManager solicitudCcaaManager;
	
	/** el cuerpo escala manager. */
	private CuerpoEscalaManager cuerpoEscalaManager;
	
	/** el datos propios configuracion manager. */
	private DatosPropiosConfiguracionManager datosPropiosConfiguracionManager;
	private ConfiguracionErroresManager configuracionErroresManager;
	private static Properties properties;

	/** La constante TIMEOUT. */
	private static final int TIMEOUT = 90000;

	/**
	 * Crea una nueva ver solicitud unificado.
	 */
	public VerSolicitudUnificado() {
		try{
			setRegistroSolicitudManager((RegistroSolicitudManager) getBean ("registroSolicitudesManager"));
			setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
			setTablasBaseManager((TablaBaseManager) getBean("tablasBaseManager"));
			setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setPlantillaManager((PlantillaManager) getBean("plantillaManager"));
			setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
			setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));
			setDocumentosConvocatoriaManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
			setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudesManager"));
			setTarifaManager((TarifaManager) getBean("tarifaManager"));
			setComunidadesManager((ComunidadesManager) getBean("comunidadesManager"));
			setSolicitudCcaaManager((SolicitudCcaaManager) getBean("solicitudCcaaManager"));
			setCuerpoEscalaManager((CuerpoEscalaManager) getBean("cuerpoEscalaManager"));
			setDatosPropiosConfiguracionManager((DatosPropiosConfiguracionManager) getBean("datosPropiosConfiguracionManager"));
			setConfiguracionErroresManager((ConfiguracionErroresManager) getBean("configuracionErroresManager"));
			properties = (Properties) getBean("propertiesBean");
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error ver solicitud Unificado",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {

		String resultado = null;
		String llamada=null;
		SolicitudForm solicitudForm = (SolicitudForm) form;
		String action = solicitudForm.getAction();
		AltaSolicitudBean altaSolicitudBean;
		String convocatoriaRepetida_Unico = properties.getProperty("altaSolicitud.inscripcionMultiple_Unica");
		String siglasCentroGestorJusticia = "";
		if(this.getRequest().getParameter(STRING_LLAMADA) != null){
			llamada = this.getRequest().getParameter(STRING_LLAMADA);
		}
		
		
		
		
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		this.getRequest().getSession().setAttribute("pagActiva", "inscripcionOnline");
		//****************************************************************** 

		//Cargar el porcentaje de discapacidad minimo actual
		ParametrosConfiguracionQuery paramConfQuery = new ParametrosConfiguracionQuery();
		paramConfQuery.setNombreCampo(Constantes.PARAMETROS_PORCENTAJE_DISCAPACIDAD);
		ParametrosConfiguracionBean parametrosConfiguracion = parametroConfiguracionManager.buscarParametroConfiguracionUnico(paramConfQuery);
		solicitudForm.setPorcentajeMinDiscapacidad(parametrosConfiguracion.getValorCampo());
		
		if(action == null){
			resultado =  "show";			
			String s_idConvocatoria = (String)this.getRequest().getParameter("id");
			logger.info("1-Id. Convocatoria recuperado: "+s_idConvocatoria);
			
			// Si el parametro de id de la convocatoria no existe
			// se intenta recuperar del acceso por Clave.
			if(s_idConvocatoria == null || "".equals(s_idConvocatoria)){
				s_idConvocatoria = (String)this.getRequest().getParameter("convocatoria");
				logger.info("2-Id. Convocatoria recuperado: "+s_idConvocatoria);
			}

			if(s_idConvocatoria != null && !"".equals(s_idConvocatoria)){
				long idConvocatoria = Long.valueOf(s_idConvocatoria);

				CiudadanoBean usuActual = null;
				
				usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute(STRING_USUARIO_CLAVE);
				if(usuActual == null || usuActual.getNif() == null){
					this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_BUNDLE.getString(STRING_SOLICITUD_ERROR_USUARIO));
					return STRING_ERROR_USUARIO;
				}

	

				CiudadanoBean ciudadanoAux = new CiudadanoBean();
				ciudadanoAux.setNif(usuActual.getNif());

				//Comprobar si el ciudadano ya se ha inscrito en esa convocatoria
				ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
				convocatoriaQuery.setId(idConvocatoria);			
				SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
				solicitudQuery.setConvocatoria(convocatoriaQuery);
				solicitudQuery.setNif(ciudadanoAux.getNif());
				
				// si es un F.H el nif es el de un ciudadano inscrito con nif que se introdujo en el buscador o la vuelta de una inscripcion
				if (usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)) {
					String nifCiudadanoBuscado = (String) this.getSessionAttribute("nifCiudadanoBuscado");
					if (nifCiudadanoBuscado!=null) {
						solicitudQuery.setNif(nifCiudadanoBuscado);
					} else {
						solicitudQuery.setNif("321");
					}
				}
							
				solicitudQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_NOPAGADO);
				solicitudQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_NOREGISTRADO);
				solicitudQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_REGISTRADO);
				solicitudQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_PENDIENTE_REGISTRO);
				solicitudQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_PROCESO_REGISTRO);
				solicitudQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_PROCESO_PAGO);
				
				//Se busca en id Solicitud en la base de datos

				ConvocatoriaBean convocatoriaBean = convocatoriaManager.recuperarConvocatoria(idConvocatoria);

				 Date today = new Date();			 
				 if(!convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_SUBSANADA) && convocatoriaBean.getFechaFin() != null && today.after(convocatoriaBean.getFechaFin()) && !DateUtils.isSameDay(today, convocatoriaBean.getFechaFin()) 
						 || (convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_SUBSANADA) 
								 && convocatoriaBean.getFechaFinSub() != null && today.after(convocatoriaBean.getFechaFinSub()) 
								 && !DateUtils.isSameDay(today, convocatoriaBean.getFechaFinSub())))  {
					 this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION, RESOURCE_MESSAGES.getString("field.formulario790.errorConvocatoria"));
					 return "errorConvocatoriaCaducada";					
				 }
				
				//Si se queda en continuar o no pagado la subsanacion
				if(convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_SUBSANADA)) {
					setRequestAttribute("subsanarInscripcion", true);	
				}
				
				String siglasCentroGestor = convocatoriaBean.getSiglasCentroGestor();
				solicitudForm.setSiglasCentroGestor(siglasCentroGestor);
				solicitudForm.setSiglasCentroGestorJusticia(siglasCentroGestorJusticia);
				solicitudForm.setImporteOriginal(String.valueOf(convocatoriaBean.getImporte()));
				
				this.getRequest().getSession().setAttribute("sCG", siglasCentroGestor); 
				this.getRequest().getSession().setAttribute("sCGJ", siglasCentroGestorJusticia); 

				SolicitudBean solicitudRegistroBean = solicitudesManager.buscarRegistroSolicitud(solicitudQuery);

				if(convocatoriaRepetida_Unico.equals("U") && "true".equals(properties.getProperty("conf.validacionInscripcion")) && solicitudRegistroBean!=null){

					
						
						
							this.setRequestAttribute(STRING_REGISTRO, solicitudRegistroBean.getId().toString());
							logger.info(solicitudRegistroBean.getId());

							// Siempre se obtienen los pagos anteriores para pintar en el detalle
							PagoSolicitudQuery pagoSolicitudQuery1 = new PagoSolicitudQuery();
							pagoSolicitudQuery1.setSolicitudComun(solicitudQuery);
							pagoSolicitudQuery1.setResultado(Constantes.RESULTADO_PAGO_OK);
							ArrayList<DetallePagoSolicitudBean> listPagoSolicitud= pagoSolicitudManager.buscarDetallePagoSolicitud(pagoSolicitudQuery1);
							
							this.setRequestAttribute("listaPagosAnteriores", listPagoSolicitud);
							

							if(Integer.parseInt(solicitudRegistroBean.getIdEstadoSolicitud()) == VerSolicitudUnificado.noPagado ||
									Integer.parseInt(solicitudRegistroBean.getIdEstadoSolicitud()) == VerSolicitudUnificado.procesoPago){
								logger.info("Redireccionar pagarSolicitud");

								PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
								pagoSolicitudQuery.setSolicitudComun(solicitudQuery);
								pagoSolicitudQuery.setResultado(Constantes.RESULTADO_PAGO_OK);
								DetalleSolicitudBean solicitudes= solicitudesManager.detalleSolicitud(solicitudQuery);		
								if(solicitudes.getNif().equals(usuActual.getNif()) || usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)){
									DetallePagoSolicitudBean detallePagoSolicitudBean = pagoSolicitudManager.buscarUltimoPagoSolicitudIdSolicitud(pagoSolicitudQuery);
									
									if(detallePagoSolicitudBean!=null){
										// Si hay pagos anteriores correctos, se deben restar al importe										
										solicitudes.setImporte(Float.parseFloat(detallePagoSolicitudBean.getImporte()));
									}else{
										// Modificacion Firma Basica
										// Si el usuario tiene certificado de autenticacion y firma independientes
										// como es el caso del DNIe, falla el pago, por lo que hay que eliminar
										// el serial number para que deje de filtrar por el.
										logger.info("Reintento. Se elimina el serial number del certificado.");
										usuActual.setNumeroSerie(null);
										this.getSession().setAttribute(STRING_USUARIO_CLAVE, usuActual);
									}
									
									
									this.setRequestAttribute(STRING_SOLICITUD, solicitudes);
								}	
								this.setRequestAttribute("pagarSolicitud", solicitudRegistroBean.getId().toString());	
							}



							if(Integer.parseInt(solicitudRegistroBean.getIdEstadoSolicitud()) == VerSolicitudUnificado.noRegistrado || 
									Integer.parseInt(solicitudRegistroBean.getIdEstadoSolicitud()) == VerSolicitudUnificado.procesoRegistro || 
									Integer.parseInt(solicitudRegistroBean.getIdEstadoSolicitud()) == VerSolicitudUnificado.pendienteRegistro){

								this.setRequestAttribute("id", solicitudRegistroBean.getId().toString());	
								solicitudQuery.setIdSolicitud(Long.parseLong(solicitudRegistroBean.getId().toString()));		

								//Buscar la solicitud
								DetalleSolicitudBean solicitudes= solicitudesManager.detalleSolicitud(solicitudQuery);
								this.setRequestAttribute(STRING_SOLICITUD, solicitudes);

								//Comprobar si el usuario de sesion es el mismo del usuario de la solicitud

								if(solicitudes != null){
									if(solicitudes.getNif().equals(usuActual.getNif()) || usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO) ){

										PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
										pagoSolicitudQuery.setSolicitudComun(solicitudQuery);
										pagoSolicitudQuery.setResultado(Constantes.RESULTADO_PAGO_OK);

										//Busca el pago de la solicitud
										DetallePagoSolicitudBean pagoSolicitud= pagoSolicitudManager.buscarUltimoPagoSolicitudIdSolicitud(pagoSolicitudQuery);

										if(pagoSolicitud == null){
											DetallePagoSolicitudBean pagoSolicitudAux = new DetallePagoSolicitudBean();				
											pagoSolicitudAux.setIdSolicitud(solicitudRegistroBean.getId().toString());
											this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_MESSAGES.getString("field.solicitud.pagoSolicitudError"));
											setRequestAttribute("pagosolicitud", pagoSolicitudAux);
											setRequestAttribute("pagosolicitudBean", new DetallePagoSolicitudBean());
										}else{
											pagoSolicitud.setIdSolicitud(solicitudRegistroBean.getId().toString());
											pagoSolicitud.setNumJustificante(solicitudes.getNumJustificante());
											pagoSolicitud.setNif(solicitudes.getNif());

											// Si existe motivo de reduccion o exencion, se recupera la CCAA
											if (pagoSolicitud.getDesMotivoReduccion()!=null && 
													(pagoSolicitud.getDesMotivoReduccion().equals(Constantes.DES_DISCAPACIDAD) || 
															pagoSolicitud.getDesMotivoReduccion().equals(Constantes.DES_FAMILIA_NUMEROSA_ESPECIAL) || 
															pagoSolicitud.getDesMotivoReduccion().equals(Constantes.DES_FAMILIA_NUMEROSA_GENERAL))){
												SolicitudCcaaQuery solicitudCcaaQuery = new SolicitudCcaaQuery();
												solicitudCcaaQuery.setSolicitudComun(solicitudQuery);
												SolicitudCcaaBean solicitudCcaaBean = solicitudCcaaManager.buscarSolicitudCcaaIdSolicitud(solicitudCcaaQuery);
												if (solicitudCcaaBean !=null){
													if(solicitudCcaaBean.getDescripcion() !=null  ){
														pagoSolicitud.setComunidad(solicitudCcaaBean.getDescripcion());										
													}	
													if(solicitudCcaaBean.getTituloFamnumerosa()!=null){
														pagoSolicitud.setNumeroTitulo(solicitudCcaaBean.getTituloFamnumerosa());
													}
												}																							

											}

											//Comprobar si tiene un justificante de pago
											String tipoPago = pagoSolicitud.getTipo();
											if(tipoPago != null && !tipoPago.equals(Constantes.PAGO_TIPO_EXENTO.toUpperCase())){
												
													this.setRequestAttribute(STRING_CERTPAGO, true);
												
											}

											StringBuilder nombreCompleto = new StringBuilder();
											nombreCompleto.append(solicitudes.getNombre()).append(" ").append(solicitudes.getPrimerApellido()).append(" ").append(solicitudes.getSegundoApellido());
											pagoSolicitud.setNombreCompleto(nombreCompleto.toString());
											setRequestAttribute("pagosolicitudBean", pagoSolicitud);
										}
										setRequestAttribute(STRING_REGISTRO, solicitudRegistroBean.getId().toString());
									}else{
										this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_MESSAGES.getString("field.solicitud.pagoSolicitud.usuarioError"));
									}			
								}else{
									this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_MESSAGES.getString("field.solicitud.errorCodSolicitud"));
								}

								logger.info("Redireccionar registrarSolicitud");
								this.setRequestAttribute("registrarSolicitud", solicitudRegistroBean.getId().toString());
							}

							if(Integer.parseInt(solicitudRegistroBean.getIdEstadoSolicitud()) == this.registrado){

								solicitudQuery.setIdSolicitud(Long.parseLong(solicitudRegistroBean.getId().toString()));	
								DetalleSolicitudBean solicitudes= solicitudesManager.detalleSolicitud(solicitudQuery);
								this.setRequestAttribute(STRING_SOLICITUD, solicitudes);

								PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
								pagoSolicitudQuery.setSolicitudComun(solicitudQuery);
								pagoSolicitudQuery.setResultado(Constantes.RESULTADO_PAGO_OK);

								//Busca el pago de la solicitud
								DetallePagoSolicitudBean pagoSolicitud= pagoSolicitudManager.buscarUltimoPagoSolicitudIdSolicitud(pagoSolicitudQuery);

								if(pagoSolicitud == null){
									DetallePagoSolicitudBean pagoSolicitudAux = new DetallePagoSolicitudBean();				
									pagoSolicitudAux.setIdSolicitud(solicitudRegistroBean.getId().toString());
									this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_MESSAGES.getString("field.solicitud.pagoSolicitudError"));
								}		

								pagoSolicitud.setIdSolicitud(solicitudRegistroBean.getId().toString());
								pagoSolicitud.setNumJustificante(solicitudes.getNumJustificante());
								pagoSolicitud.setNif(solicitudes.getNif());

								if (pagoSolicitud.getDesMotivoReduccion()!=null && 
										(pagoSolicitud.getDesMotivoReduccion().equals(Constantes.DES_DISCAPACIDAD) || 
												pagoSolicitud.getDesMotivoReduccion().equals(Constantes.DES_FAMILIA_NUMEROSA_ESPECIAL) || 
												pagoSolicitud.getDesMotivoReduccion().equals(Constantes.DES_FAMILIA_NUMEROSA_GENERAL))){
									SolicitudCcaaQuery solicitudCcaaQuery = new SolicitudCcaaQuery();
									solicitudCcaaQuery.setSolicitudComun(solicitudQuery);
									SolicitudCcaaBean solicitudCcaaBean = solicitudCcaaManager.buscarSolicitudCcaaIdSolicitud(solicitudCcaaQuery);
									if (solicitudCcaaBean !=null){
										if(solicitudCcaaBean.getComunidad() !=null && solicitudCcaaBean.getDescripcion() !=null ){
											pagoSolicitud.setComunidad(solicitudCcaaBean.getDescripcion());										
										}	
										if(solicitudCcaaBean.getTituloFamnumerosa()!=null){
											pagoSolicitud.setNumeroTitulo(solicitudCcaaBean.getTituloFamnumerosa());
										}
									}																							

								}

								//Comprobar si tiene un justificante de pago
								String tipoPago = pagoSolicitud.getTipo();
								if(tipoPago != null && !tipoPago.equals(Constantes.PAGO_TIPO_EXENTO.toUpperCase())){
									
										this.setRequestAttribute(STRING_CERTPAGO, true);
									
								}

								StringBuilder nombreCompleto = new StringBuilder();
								nombreCompleto.append(solicitudes.getNombre()).append(" ").append(solicitudes.getPrimerApellido()).append(" ").append(solicitudes.getSegundoApellido());
								pagoSolicitud.setNombreCompleto(nombreCompleto.toString());
								setRequestAttribute("pagosolicitudBean", pagoSolicitud);
								setRequestAttribute(STRING_REGISTRO, solicitudRegistroBean.getId().toString());

								RegistroSolicitudQuery registroSolicitudQuery = new RegistroSolicitudQuery();
								registroSolicitudQuery.setSolicitudComun(solicitudQuery);

								//Busca los datos del registro
								DetalleRegistroSolicitudBean registroSolicitud = null;

								try{
									registroSolicitud = registroSolicitudManager.buscarRegistroSolicitudIdSolicitud(registroSolicitudQuery);
								}catch(Exception e){		
									
									logger.error("Error registro: ", e);
									this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_BUNDLE.getString("field.solicitud.registroSolicitudError"));
									return "nosuccess";
								}
								if(registroSolicitud == null){
									this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_BUNDLE.getString("field.solicitud.registroSolicitudError"));
									DetalleRegistroSolicitudBean registroAux = new DetalleRegistroSolicitudBean();
									registroAux.setIdSolicitud(solicitudRegistroBean.getId().toString());
									setRequestAttribute("registrosolicitud", registroAux);
									return "success";
								}

								//Busca los datos del pago de la solicitud
								registroSolicitud.setNif(solicitudes.getNif());
								registroSolicitud.setNombre(nombreCompleto.toString());
								registroSolicitud.setNumJustificante(solicitudes.getNumJustificante());
								registroSolicitud.setEjercicio(solicitudes.getEjercicioSolicitud());
								registroSolicitud.setMinisterio(solicitudes.getMinisterio());
								registroSolicitud.setFormaPago(pagoSolicitud.getTipo());
								registroSolicitud.setImporte(pagoSolicitud.getImporte());
								registroSolicitud.setNrc(pagoSolicitud.getNrc());
								registroSolicitud.setFechaPago(pagoSolicitud.getFechaIntento());
								registroSolicitud.setConsentimiento(solicitudes.getConsentimiento());

								//Comprobar si tiene un justificante de pago
								if(tipoPago != null && !tipoPago.equals(Constantes.PAGO_TIPO_EXENTO)){
									
										this.setRequestAttribute(STRING_CERTPAGO, true);
									
								}

								setRequestAttribute("registrosolicitud", registroSolicitud);
								this.setRequestAttribute("registrarSolicitud", solicitudRegistroBean.getId().toString());
								this.setRequestAttribute("detalleRegistrado", solicitudRegistroBean.getId().toString());
							}

							this.setRequestAttribute("detalleRegistro", solicitudRegistroBean.getId().toString());

							//INICIO RECUPERAR DOCUMENTOS 
							logger.info("DocumentosSolicitud REQUEST ID: "+solicitudRegistroBean.getId());

							long idSol = solicitudRegistroBean.getId();
							DocumentoQuery documentoQuery= new DocumentoQuery();
							documentoQuery.addSolicitudIn(idSol);
							Integer tpDocumento = Constantes.TIPO_DOCUMENTO_ANEXO_SOLICITUD;
							documentoQuery.addTipoDocumentoIn(tpDocumento);
							tpDocumento = Constantes.TIPO_DOCUMENTO_ADJUNTO;
							documentoQuery.addTipoDocumentoIn(tpDocumento);
							tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_TASA;
							documentoQuery.addTipoDocumentoIn(tpDocumento);
							tpDocumento = Constantes.TIPO_DOCUMENTO_JUSTIFICANTE_DISCAPACIDAD;
							documentoQuery.addTipoDocumentoIn(tpDocumento);	

							List<DocumentoBean> documentosList;
							documentosList=documentosConvocatoriaManager.buscarDocumentosConvocatoria(documentoQuery);
							for(int i=0;i<documentosList.size();i++){		
								boolean correcto = comprobarDocumento(documentosList.get(i),usuActual);
								if(!correcto){
									this.setRequestAttribute(STRING_ERROR_USUARIO, "El usuario no puede descargar ese documento");
									return "success";
								}
							}

							this.setRequestAttribute("documentos", documentosList);
							this.getResponse().setHeader("Cache-Control", "no-cache");
							this.getResponse().setHeader("Pragma", "no-cache");
							this.getResponse().setDateHeader("Expires", 0);
						
											
				}	

				//Si no se ha registrado aun
				if(convocatoriaBean == null){
					resultado = STRING_ERROR;
					this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.alta.convocatoriaNull"));
					return resultado;
				}else if(!(convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_PUBLICADA) || convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_FINALIZADA)
						|| convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_SUBSANADA))){
					resultado = STRING_ERROR;
					this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.convocatoriaNoPublicada"));
					return resultado;
				}

				SolicitudBean solicitudBean;
				SolicitudComunQuery solicitudBeanQuery = new SolicitudComunQuery();
				solicitudBeanQuery.setNumeroSolicitud(solicitudForm.getNumSolicitud());
				solicitudBeanQuery.setNif(solicitudForm.getNif());
				solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);

				PlantillaQuery plantillaQuery = new PlantillaQuery();
				plantillaQuery.setId(convocatoriaBean.getIdPlantilla());
				PlantillaBean plantillaBean = plantillaManager.buscarPlantillaById(plantillaQuery);

				if(plantillaBean != null){
					this.setRequestAttribute("plantilla", plantillaBean);
				}

				List<ProvinciaBean> provincias = tablasBaseManager.obtenerProvinciasActivas();
				List<PaisBean> paises	= tablasBaseManager.buscarPaises();

				TipoDiscapacidadQuery discapacidadQuery = new TipoDiscapacidadQuery();
				if(convocatoriaBean.getNumModelo().equals(Constantes.MODELO79007)){
					discapacidadQuery.addIdIn(Integer.parseInt(Constantes.TIPO_DISCAPACIDAD_SI));
					discapacidadQuery.addIdIn(Integer.parseInt(Constantes.TIPO_DISCAPACIDAD_NO));
				}else{
					discapacidadQuery.addIdIn(Integer.parseInt(Constantes.TIPO_DISCAPACIDAD_GENERAL));
					discapacidadQuery.addIdIn(Integer.parseInt(Constantes.TIPO_DISCAPACIDAD_INTELECTUAL));
				}
				List<TipoDiscapacidadBean> tiposDiscapacidad = tablasBaseManager.buscarTiposDiscapacidad(discapacidadQuery);

				if(solicitudBean!=null){
					int idPaisDomicilio=0;
				

					if(solicitudBean.getPaisDomicilio() != null){
						try{
							idPaisDomicilio= solicitudBean.getPaisDomicilio().getId();
						}catch(Exception e){
							logger.error("Error pais domicilio"+ idPaisDomicilio ,e);
						}
					}
					int idProvinciaDomicilio=0;
					if(solicitudBean.getProvinciaDomicilio() != null){
						try{
							idProvinciaDomicilio= solicitudBean.getProvinciaDomicilio().getId();
						}catch(Exception e){
							logger.error("Error pais domicilio"+ idProvinciaDomicilio ,e);
						}
					}
					solicitudForm.setPais(String.valueOf(idPaisDomicilio));
					solicitudForm.setProvinciaDomicilio(String.valueOf(idProvinciaDomicilio));
				}else{
					solicitudBean = new SolicitudBean();
				}

				// TODO Correccion generacion numero justificante innecesario.
				if(solicitudRegistroBean==null){

					//Obtienes el numJustificante
					String nSolicitud = "";
					long nSolicitudL = 0 ;

					try{
						boolean is007 = false;
						if(convocatoriaBean.getSiglasCentroGestor().equals("")){
							is007 = true;
						}

						nSolicitud = solicitudesManager.obtenerNumeroSolicitud(is007, convocatoriaBean.getNumModelo());
						// Comprobamos si ya se ha generado un numero de justificante igual
						while (solicitudesManager.existeNumeroSolicitud(nSolicitud)) {
							nSolicitud = solicitudesManager.obtenerNumeroSolicitud(is007, convocatoriaBean.getNumModelo());
						}
												
						nSolicitudL = Long.parseLong(nSolicitud);

						if(nSolicitudL== 0){
							throw new Exception();
						}else{
							solicitudBean.setNumeroSolicitud(String.valueOf(nSolicitudL));
						}

					}catch(Exception e){
						logger.error("Error parseado nSolicitud"+ nSolicitud ,e);
						this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION, RESOURCE_BUNDLE.getString("field.numeroSolicitud.error"));	
						resultado = STRING_ERROR;						
					}
				}

				altaSolicitudBean = toAltaSolicitudBean(convocatoriaBean,usuActual,solicitudBean);

				solicitudForm.setNif(usuActual.getNif());
				solicitudForm.setNombre(usuActual.getNombre());
				solicitudForm.setPrimerApellido(usuActual.getPrimerApellido());
				solicitudForm.setSegundoApellido(usuActual.getSegundoApellido());
				solicitudForm.setNumSolicitud(solicitudBean.getNumeroSolicitud());
				
				//Comprobamos si existe una solicitud anterior con el DNI del usuario actual y obtenemos la fecha Nacimiento
				SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
				solicitudComunQuery.setNif(usuActual.getNif());
				solicitudComunQuery.addOrder(SolicitudComunQuery.IDSOLICITUD,OrderType.DESC);
				
				SolicitudBean solicitud = solicitudesManager.buscarRegistroSolicitud(solicitudComunQuery);
				if(solicitud != null && solicitud.getFechaNacimiento() != null) {
					SimpleDateFormat df = new SimpleDateFormat(STRING_SIMPLE_DATE_FORMAT);
					altaSolicitudBean.setFechaNacimiento(df.format(solicitud.getFechaNacimiento()));
				}
				
				// modificaciones de la solicitud para un F.H
				if (usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)) {
					altaSolicitudBean.setNif(solicitudBean.getNif());
					altaSolicitudBean.setNombre(solicitudBean.getNombre());
					altaSolicitudBean.setPrimerApellido(solicitudBean.getPrimerApellido());
					altaSolicitudBean.setSegundoApellido(solicitudBean.getSegundoApellido());
					altaSolicitudBean.setNumSolicitud(solicitudBean.getNumeroSolicitud());
					
				}

				if(convocatoriaBean.getProvinciasExamen().size()==1){
					altaSolicitudBean.setProvExamenUnica(1);
				}

				//Otros titulos
				if (convocatoriaBean.getOtrosTitulos() != null && convocatoriaBean.getOtrosTitulos().size() > 0) {
					solicitudForm.setOtrosTitulosList(convocatoriaBean.getOtrosTitulos());
				}
				
				//Discapacidad
				if (convocatoriaBean.getDiscapacidad() != null && convocatoriaBean.getDiscapacidad().size() > 0) {
					solicitudForm.setDiscapacidadList(convocatoriaBean.getDiscapacidad());
				}

				this.setRequestAttribute("siglasCentroGestor", siglasCentroGestor);
				this.setRequestAttribute("siglasCentroGestorJusticia", siglasCentroGestorJusticia);
				this.setRequestAttribute("provincias", provincias);
				this.setRequestAttribute("provinciasExamen", convocatoriaBean.getProvinciasExamen());
				this.setRequestAttribute("especialidades", convocatoriaBean.getEspecialidads());
				this.setRequestAttribute("paises", paises);
				this.setRequestAttribute("altaSolicitud", altaSolicitudBean);
				this.setRequestAttribute("tiposDiscapacidad",tiposDiscapacidad);

			}else{
				// TODO ORIGINAL, continuaba la ejecucion.
				resultado = STRING_ERROR;
				this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.alta.convocatoriaNull"));
				return STRING_ERROR;
			}

		}else{
			//Carga los datos de la solicitud
			resultado =  "show";
			Long idConvocatoria = Long.parseLong(solicitudForm.getIdConvocatoria());
			ConvocatoriaBean convocatoriaBean = convocatoriaManager.recuperarConvocatoria(idConvocatoria);
			String siglasCentroGestor = convocatoriaBean.getSiglasCentroGestor();

			CiudadanoBean ciudadanoBean = null;
			
			//Comprobar si el usuario esta en la sesion
			ciudadanoBean = (CiudadanoBean) this.getRequest().getSession().getAttribute(STRING_USUARIO_CLAVE);
			if(ciudadanoBean == null || ciudadanoBean.getNif() == null){
				this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_BUNDLE.getString(STRING_SOLICITUD_ERROR_USUARIO));
				return STRING_ERROR_USUARIO;
			}

		

			List<ProvinciaBean> provincias = tablasBaseManager.obtenerProvinciasActivas();
			List<PaisBean> paises	= tablasBaseManager.buscarPaises();
			List<TipoDiscapacidadBean> tiposDiscapacidad = tablasBaseManager.buscarTiposDiscapacidad();

			logger.info("Recuperando Convocatoria con id: "+idConvocatoria);
			convocatoriaBean = convocatoriaManager.recuperarConvocatoria(idConvocatoria);
			
			if (!ciudadanoBean.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)) {
			logger.info("Recuperando Ulitma Solicitud del Ciudadano con id: "+ciudadanoBean.getNif());
			} else {
				logger.info("Recuperando Ulitma Solicitud del Ciudadano con id: "+solicitudForm.getNif()); 
			}
			
			
			SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
			ConvocatoriaQuery convocatoria = new ConvocatoriaQuery();
			convocatoria.setId(idConvocatoria);
			solicitudQuery.setConvocatoria(convocatoria);
			
			if (!ciudadanoBean.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)) {
			solicitudQuery.setNif(ciudadanoBean.getNif());
			} else {
				solicitudQuery.setNif(solicitudForm.getNif());
			}
			
			
			
			SolicitudBean solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);

			if(solicitudBean==null){
				solicitudBean = new SolicitudBean();
			}

			logger.info("Recuperando Plantilla de la convocatoria. Id de Plantilla "+convocatoriaBean.getIdPlantilla());
			PlantillaQuery plantillaQuery = new PlantillaQuery();
			plantillaQuery.setId(convocatoriaBean.getIdPlantilla());
			PlantillaBean plantillaBean = plantillaManager.buscarPlantillaById(plantillaQuery);
			if(plantillaBean != null){
				this.setRequestAttribute("plantilla", plantillaBean);
			}

			altaSolicitudBean = toAltaSolicitudBeanForm(solicitudForm,convocatoriaBean,solicitudBean,ciudadanoBean);
			solicitudForm.setNif(solicitudBean.getNif());
			solicitudForm.setNombre(solicitudBean.getNombre());
			solicitudForm.setPrimerApellido(solicitudBean.getPrimerApellido());
			solicitudForm.setSegundoApellido(solicitudBean.getSegundoApellido());
			solicitudForm.setNumSolicitud(solicitudBean.getNumeroSolicitud());
			
			// modificacions de la solicitud para un F.H
			if (ciudadanoBean.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)) {
				altaSolicitudBean.setNif(solicitudBean.getNif());
				altaSolicitudBean.setNombre(solicitudBean.getNombre());
				altaSolicitudBean.setPrimerApellido(solicitudBean.getPrimerApellido());
				altaSolicitudBean.setSegundoApellido(solicitudBean.getSegundoApellido());
				altaSolicitudBean.setNumSolicitud(solicitudBean.getNumeroSolicitud());
				
			}

			if(convocatoriaBean.getEspecialidads() != null){
				altaSolicitudBean.setEspecialidades(convocatoriaBean.getEspecialidads());
			}

			if(solicitudForm.getImporteOriginal() != null && !"".equals(solicitudForm.getImporteOriginal())){
				altaSolicitudBean.setImporteOriginal(solicitudForm.getImporteOriginal());
			}

			solicitudForm.setSiglasCentroGestor(siglasCentroGestor);
			solicitudForm.setSiglasCentroGestorJusticia(siglasCentroGestorJusticia);

			this.setRequestAttribute("siglasCentroGestor", siglasCentroGestor);
			this.setRequestAttribute("siglasCentroGestorJusticia", siglasCentroGestorJusticia);
			this.setRequestAttribute("provincias", provincias);
			this.setRequestAttribute("provinciasExamen", convocatoriaBean.getProvinciasExamen());
			this.setRequestAttribute("especialidades", convocatoriaBean.getEspecialidads());
			this.setRequestAttribute("paises", paises);
			this.setRequestAttribute("tiposDiscapacidad",tiposDiscapacidad);
			this.setRequestAttribute("altaSolicitud", altaSolicitudBean);
		}

		PagoSolicitudForm solicitudPagoForm = new PagoSolicitudForm();
		String busqueda = solicitudPagoForm.getSubmit();

		if(busqueda == null){
			resultado =  "show";

			long idSolicitud = Long.valueOf(altaSolicitudBean.getIdConvocatoria());

			//Comprobar si el usuario esta en la sesion
			CiudadanoBean usuActual = null;
			
			usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute(STRING_USUARIO_CLAVE);
			
			if(usuActual == null || usuActual.getNif() == null){
				this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_BUNDLE.getString(STRING_SOLICITUD_ERROR_USUARIO));
				return STRING_ERROR_USUARIO;
			}

		

			CiudadanoBean ciudadanoAux = new CiudadanoBean();
			ciudadanoAux.setNif(usuActual.getNif());
			ConvocatoriaBean convocatoriaBean = convocatoriaManager.recuperarConvocatoria(idSolicitud);

			if(altaSolicitudBean != null && convocatoriaBean != null){
				if(convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_PUBLICADA) || convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_FINALIZADA)
						|| convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_SUBSANADA)){
					PagoSolicitudBean pagoSolicitudBean = new PagoSolicitudBean();
					pagoSolicitudBean.setNumeroSolicitud(altaSolicitudBean.getNumSolicitud());
					pagoSolicitudBean.setNif(altaSolicitudBean.getNif());
					StringBuffer nombreCompleto = new StringBuffer();
					nombreCompleto.append(altaSolicitudBean.getNombre()).append(" ")
					.append(altaSolicitudBean.getPrimerApellido()).append(" ")
					.append(altaSolicitudBean.getSegundoApellido());
					pagoSolicitudBean.setNombre(nombreCompleto.toString());
					pagoSolicitudBean.setNombreParcial(altaSolicitudBean.getNombre());
					pagoSolicitudBean.setApellido1(altaSolicitudBean.getPrimerApellido());
					pagoSolicitudBean.setApellido2(altaSolicitudBean.getSegundoApellido());

					pagoSolicitudBean.setIdConvocatoria(String.valueOf(convocatoriaBean.getId()));
					pagoSolicitudBean.setImporte(convocatoriaBean.getImporte());
					ArrayList<EntidadBean> entidadBean = entidadFinancieraManager.buscarEntidadAdeudoCombo();
					ArrayList<EntidadBean> entidadBeanTarjeta = entidadFinancieraManager.buscarEntidadTarjetaCombo();

					solicitudPagoForm.setNumeroSolicitud(altaSolicitudBean.getNumSolicitud());
					
					this.setRequestAttribute("id", String.valueOf(idSolicitud));
					this.setRequestAttribute("entidades", entidadBean);
					this.setRequestAttribute("entidadesTarjetas", entidadBeanTarjeta);

					String motivoTipificado="##";
					if(convocatoriaBean.getMotivoReduccionTasasIncompleto() != null){
						this.setRequestAttribute("motivos", convocatoriaBean.getMotivoReduccionTasasIncompleto());

						for (MotivoReduccionTasa tasa : convocatoriaBean.getMotivoReduccionTasasIncompleto()) {
							motivoTipificado=motivoTipificado+tasa.getId()+"##"+tasa.getPorcentajeDescuento()+"##";
						} 

					}else{
						ArrayList<MotivoReduccionTasa> arrayTasas = new ArrayList<MotivoReduccionTasa>();
						this.setRequestAttribute("motivos", arrayTasas);

						for (MotivoReduccionTasa tasa : arrayTasas) {
							motivoTipificado=motivoTipificado+tasa.getId()+"##"+tasa.getPorcentajeDescuento()+"##";
						}

					}
					if(convocatoriaBean.getMotivoReduccionTasasCompleto() != null){
						this.setRequestAttribute("motivosCompletos", convocatoriaBean.getMotivoReduccionTasasCompleto());

						for (MotivoReduccionTasa tasa : convocatoriaBean.getMotivoReduccionTasasCompleto()) {
							motivoTipificado=motivoTipificado+tasa.getId()+"##"+tasa.getPorcentajeDescuento()+"##";
						}
					}else{
						ArrayList<MotivoReduccionTasa> arrayTasas = new ArrayList<MotivoReduccionTasa>();
						this.setRequestAttribute("motivosCompletos", arrayTasas);

						for (MotivoReduccionTasa tasa : arrayTasas) {
							motivoTipificado=motivoTipificado+tasa.getId()+"##"+tasa.getPorcentajeDescuento()+"##";
						}

					}

					ArrayList<ComunidadesBean> comunidadesFN;
					ComunidadesQuery comunidadesQueryFN = new ComunidadesQuery();
					comunidadesQueryFN.setServicioFamnumerosa(true);
					comunidadesFN = comunidadesManager.buscarComunidadesCombo(comunidadesQueryFN);
					this.setRequestAttribute("listcomunidadesFN", comunidadesFN);
					this.setRequestAttribute("numComunidadesFN",comunidadesFN.size());
					
					// Pasamos al formulario la lista de comunidades autónomas que requieren el número de titulo familia numerosa		
					ArrayList<ComunidadesBean> comunidadesReqTitulo;
					ComunidadesQuery comunidadesQueryReqTitulo = new ComunidadesQuery();
					comunidadesQueryReqTitulo.setTituloRequerido(true);
					comunidadesReqTitulo = comunidadesManager.buscarComunidadesCombo(comunidadesQueryReqTitulo);

					String comuReqTitulo="##";
					for (ComunidadesBean comunidad : comunidadesReqTitulo) {
						comuReqTitulo=comuReqTitulo+comunidad.getIdComunidad()+"##";
					} 
					this.setRequestAttribute("comunidadesReqTitulo", comuReqTitulo);


					ArrayList<ComunidadesBean> comunidadesDiscapacidad;
					ComunidadesQuery comunidadesQueryDD = new ComunidadesQuery();
					comunidadesQueryDD.setServicioDiscapacidad(true);
					comunidadesDiscapacidad = comunidadesManager.buscarComunidadesCombo(comunidadesQueryDD);
					this.setRequestAttribute("listcomunidadesDiscapacidad", comunidadesDiscapacidad);

					// AQUI: COMPROBAMOS SI PAGOS ANTEIORES PARA RESTARSERLOS AL IMPORTEORIGINAL
					// Importe pagado
					// Se debe calcular la suma de los importes pagados en los diferentes pagos	
					SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
					solicitudQuery.setNumeroSolicitud(altaSolicitudBean.getNumSolicitud());
					PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
					pagoSolicitudQuery.setSolicitudComun(solicitudQuery);
					ArrayList<DetallePagoSolicitudBean> pagosAsociados = pagoSolicitudManager.buscarDetallePagoSolicitud(pagoSolicitudQuery);
					Double importePagado = 0.0;
					for(DetallePagoSolicitudBean pagoAsociado : pagosAsociados){
						if(pagoAsociado.getResultado() != null && pagoAsociado.getResultado().equals("OK")){
							importePagado = importePagado + Double.parseDouble(pagoAsociado.getImporte());
						}
					}
					solicitudForm.setImportePagado(importePagado.toString());

					this.setRequestAttribute("pagoSolicitud", pagoSolicitudBean);
					if(pagoSolicitudBean.getFormaPago() != null && !"".equals(pagoSolicitudBean.getFormaPago())){
						this.setRequestAttribute("formPago", pagoSolicitudBean.getFormaPago());
					}else{
						this.setRequestAttribute("formPago", null);
					}

					this.setRequestAttribute("importeOriginal", pagoSolicitudBean.getImporte());
					convocatoriaBean.setImporte(pagoSolicitudBean.getImporte());
					logger.info("Importe: " +pagoSolicitudBean.getImporte());

					this.setRequestAttribute("motivoTipificado", motivoTipificado);

					///COMIENZO
					Enumeration<String> listaPropiedades;
					List <String> listExt= new ArrayList<String>();
					listaPropiedades=RESOURCE_BUNDLE_NAME.getKeys();
					ApplicationException.assertNotNull(listaPropiedades,"isExtensionValida, listaPropiedades");

					for (Enumeration<String> propiedades = listaPropiedades; propiedades.hasMoreElements();){
						String propiedadExtension= propiedades.nextElement();
						String extensionAux=RESOURCE_BUNDLE_NAME.getString(propiedadExtension);
						if (propiedadExtension.endsWith("."+extensionAux.toLowerCase())){
							listExt.add(extensionAux);
						}
					}
					setRequestAttribute("extensiones", listExt);							

					if(convocatoriaBean != null){
						if(convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_PUBLICADA) || convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_FINALIZADA)
								|| convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_SUBSANADA)){

							RegistroSolicitudBean registroSolicitudBean = new RegistroSolicitudBean();


							//Poner id convocatoria igual que pago
							registroSolicitudBean.setIdConvocatoria(String.valueOf(altaSolicitudBean.getIdConvocatoria()));
							registroSolicitudBean.setNif(altaSolicitudBean.getNif());
							registroSolicitudBean.setEmail(altaSolicitudBean.getEmail());
							registroSolicitudBean.setTelefono(altaSolicitudBean.getTelefono());
							registroSolicitudBean.setTelefonoAux(altaSolicitudBean.getTelefonoAux());
							registroSolicitudBean.setCalle(altaSolicitudBean.getCalleDomicilio());
							registroSolicitudBean.setCodPostal(altaSolicitudBean.getCodigoPostalDomicilio());
							registroSolicitudBean.setDatosA(altaSolicitudBean.getDatosA());
							registroSolicitudBean.setDatosB(altaSolicitudBean.getDatosB());
							registroSolicitudBean.setFechaNacimiento(altaSolicitudBean.getFechaNacimiento());
							registroSolicitudBean.setNacionalidad(altaSolicitudBean.getNacionalidad());
							registroSolicitudBean.setMunicipioDomicilio(altaSolicitudBean.getMunicipioDomicilio());
							registroSolicitudBean.setProvinciaDomicilio(altaSolicitudBean.getProvinciaDomicilio());
							registroSolicitudBean.setPaisDomicilio(altaSolicitudBean.getPaisDomicilio());
							registroSolicitudBean.setNumJustificante(pagoSolicitudBean.getNumeroSolicitud());
							registroSolicitudBean.setDetalleDiscapacidad(altaSolicitudBean.getDetalleDiscapacidad());
							registroSolicitudBean.setPorcentajeDiscapacidad(altaSolicitudBean.getPorcentajeDiscapacidad());
							registroSolicitudBean.setReservaDiscapacidad(altaSolicitudBean.getReservaDiscapacidad());
							registroSolicitudBean.setTipoDiscapacidad(altaSolicitudBean.getTiposDiscapacidad().toString());
							registroSolicitudBean.setIdSolicitud(altaSolicitudBean.getNumSolicitud());

							if(nombreCompleto != null && !"".equals(nombreCompleto)){
								registroSolicitudBean.setNombre(nombreCompleto.toString().toUpperCase());
							}else{
								registroSolicitudBean.setNombre(nombreCompleto.toString());
							}

							if(pagoSolicitudBean.getImporte()!=null){
								registroSolicitudBean.setImporte(String.valueOf(pagoSolicitudBean.getImporte()));
							}

							registroSolicitudBean.setEjercicio(convocatoriaBean.getEjercicio());
							registroSolicitudBean.setNrc(pagoSolicitudBean.getNrc());

							if(pagoSolicitudBean.getFechaIntento() != null){
								registroSolicitudBean.setFechaPago(pagoSolicitudBean.getFechaIntento().toString());
							}
							if(""+pagoSolicitudBean.getTipo() != null){
								registroSolicitudBean.setFormaPago(pagoSolicitudBean.getTipo()+"");
							}
							if(convocatoriaBean.getDesMinisterioConvocante() != null){
								registroSolicitudBean.setMinisterio(convocatoriaBean.getDesMinisterioConvocante());
							}

							if(convocatoriaBean.getTipoDocumentoPermitido() != ' '){
								String aniadirDocumento = String.valueOf(convocatoriaBean.getTipoDocumentoPermitido());
								if(Constantes.TIPO_DOCUMENTO_CONVOCATORIA_NINGUNO.equals(aniadirDocumento)){
									registroSolicitudBean.setAniadirDocumentos(Constantes.NO);
								}else{
									if(Constantes.TIPO_DOCUMENTO_CONVOCATORIA_CUALQUIERA.equals(aniadirDocumento)){
										registroSolicitudBean.setAniadirDocumentos(Constantes.SI);						
									}else{
										if(Constantes.TIPO_DOCUMENTO_CONVOCATORIA_REDUCCION.equals(aniadirDocumento)){
											if(pagoSolicitudBean.getMotivoReduccionTasas() != null && !"".equals(pagoSolicitudBean.getMotivo())){
												registroSolicitudBean.setAniadirDocumentos(Constantes.SI);
											}else{
												registroSolicitudBean.setAniadirDocumentos(Constantes.NO);
											}
										}
									}
								}
							}

							solicitudForm.setId(idSolicitud);

							//Cargar Datos Solicitud para el HTML
							SimpleDateFormat df = new SimpleDateFormat(STRING_SIMPLE_DATE_FORMAT);
							this.setRequestAttribute("numDocsAdjuntos", Constantes.NUM_MAX_DOCUMENTOS);

							//Comprobar si tiene un justificante de pago
							String tipoPago = ""+pagoSolicitudBean.getTipo();
							if(tipoPago != null && !tipoPago.equals(Constantes.PAGO_TIPO_EXENTO)){
								
									this.setRequestAttribute(STRING_CERTPAGO, true);
								
							}

							this.setRequestAttribute("registroSolicitud", registroSolicitudBean);
							
							// carga de campos propios
							List<PlantillaPropiosBean> listaPlantillasPropiasBean = convocatoriaBean.getPlantillaPropios();
							cargarDatosPropiosConfiguracion(listaPlantillasPropiasBean, convocatoriaBean);
							this.setRequestAttribute("listaPlantillasPropias", listaPlantillasPropiasBean);
							this.setRequestAttribute("ocultarDatosPropios", convocatoriaBean.getOcultarDatosPropios());
							
							 // saco el grupo al que pertenece la convocatoaria para imprimirlo en el jsp
							 String idCuerpoEscala = convocatoriaBean.getIdCuerpoEscala();
							 CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
							 cuerpoEscalaQuery.setId(Integer.valueOf(idCuerpoEscala));
							 Grupo grupo = cuerpoEscalaManager.obtenerGrupoByIdGrupoEscala(cuerpoEscalaQuery);
							 if (grupo!=null) {
								 String siglas = grupo.getSiglas();
								 this.setRequestAttribute("siglasGrupoConvocatoria", siglas);
							 }
												
						}
					}

				}else{
					resultado=STRING_ERROR;
					this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.convocatoriaNoPublicada"));
					logger.info("La convocatoria con id: "+convocatoriaBean.getId()+" no esta en estado publicado.");
				}

			}else{
				resultado=STRING_ERROR;
				this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.idErroneo"));
				logger.info("Solicitud o Convocatoria null");
			}				
		}

		if(this.getSessionAttribute(STRING_ERROR_FORM2)!=null){
			this.getRequest().setAttribute(STRING_ERROR_FORM2,this.getSessionAttribute(STRING_ERROR_FORM2));
			this.getSession().removeAttribute(STRING_ERROR_FORM2);
		}

		// se setea el usuario de la sesion y el tipo de persona, para que las vistas distingan entre ciudadanos o funcionarios
		CiudadanoBean usuarioActual = (CiudadanoBean) this.getRequest().getSession().getAttribute(STRING_USUARIO_CLAVE);
		this.getRequest().setAttribute("ciudadanoBean",usuarioActual);
		this.setRequestAttribute("tipoPersonaSolicitudFormGeneral", usuarioActual.getTipoPersona());
		
				
		// ademas cuando es un funcionario habilitado, se setea en sesion el ciudadano al que quiere realizar la inscripcion
		// esto es cuando viene informacion del formulario de solicitud con nif nombre y apellidos y en sesion el tipo de persona es F.H
		// sera util para el flujo de PagoAction y RegistroSolicitudSpring
		if (usuarioActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)) {
			CiudadanoBean ciudadanoInscrito = new CiudadanoBean();
			if (solicitudForm.getNif()!=null ) {
				ciudadanoInscrito.setNif(solicitudForm.getNif().toUpperCase());
				ciudadanoInscrito.setNombre(solicitudForm.getNombre().toUpperCase());
				ciudadanoInscrito.setPrimerApellido(solicitudForm.getPrimerApellido().toUpperCase());
				ciudadanoInscrito.setSegundoApellido(solicitudForm.getSegundoApellido().toUpperCase());
			} else if (altaSolicitudBean.getNif()!=null) {
				ciudadanoInscrito.setNif(altaSolicitudBean.getNif().toUpperCase());
				ciudadanoInscrito.setNombre(altaSolicitudBean.getNombre().toUpperCase());
				ciudadanoInscrito.setPrimerApellido(altaSolicitudBean.getPrimerApellido().toUpperCase());
				ciudadanoInscrito.setSegundoApellido(altaSolicitudBean.getSegundoApellido().toUpperCase());
			}
			this.getSession().setAttribute("ciudadanoInscrito", ciudadanoInscrito);
			this.getSession().setAttribute("numJustCiudadanoInscrito", altaSolicitudBean.getNumSolicitud());
		}
		
		
		
		// seteo atributo si proviene la request desde un boton continuar
		String continuar = this.getRequest().getParameter("continuar");
		if (continuar!=null && continuar.equals("true")) {
			continuar = "true";
		} else {
			continuar = "false";
		}
		setRequestAttribute("continuar", continuar);
				
		//Parte Errores Configurados para informar
		cargarErroresConfigurablesInfo();
		
		logger.info("Fin VerAlta");
		
		return resultado;
	}

	/**
	 * Carga de errores configurables informativos desde IPSG
	 */
	private void cargarErroresConfigurablesInfo() {
		ArrayList<ConfiguracionErroresBean> listaErroresInfo = new ArrayList<ConfiguracionErroresBean>();
		ConfiguracionErroresQuery configuracionErroresQuery = new ConfiguracionErroresQuery();
		configuracionErroresQuery.setVisibilidad('S');
		listaErroresInfo = configuracionErroresManager.buscarConfiguracionErrorCombo(configuracionErroresQuery);
		this.setRequestAttribute("listaErroresInfo", listaErroresInfo);
	}

	/**
	 * To alta solicitud bean form.
	 *
	 * @param solicitudForm el solicitud form
	 * @param convocatoriaBean el convocatoria bean
	 * @param solicitudBean el solicitud bean
	 * @param ciudadanoBean el ciudadano bean
	 * @return el alta solicitud bean
	 */
	private AltaSolicitudBean toAltaSolicitudBeanForm(
			SolicitudForm solicitudForm, ConvocatoriaBean convocatoriaBean, SolicitudBean solicitudBean, CiudadanoBean ciudadanoBean) {
		AltaSolicitudBean aux = new AltaSolicitudBean();
		aux.setNumSolicitud(solicitudForm.getNumSolicitud());
		aux.setIdConvocatoria(String.valueOf(solicitudForm.getId()));

		//Convocatoria
		aux.setDesCentroGestor(convocatoriaBean.getDesCentroGestor());
		aux.setEjercicio(convocatoriaBean.getEjercicio());

		//Personales
		aux.setNif(ciudadanoBean.getNif());
		aux.setNombre(ciudadanoBean.getNombre());
		aux.setPrimerApellido(ciudadanoBean.getPrimerApellido());
		aux.setSegundoApellido(ciudadanoBean.getSegundoApellido());
		aux.setEmail(solicitudForm.getEmail());

		//Direccion
		aux.setCalleDomicilio(solicitudForm.getCalleDomicilio());
		aux.setCodigoPostalDomicilio(solicitudForm.getCodigoPostalDomicilio());
		aux.setMunicipioDomicilio(solicitudForm.getMunicipioDomicilio());
		aux.setTelefono(solicitudForm.getTelefono());
		aux.setTelefonoAux(solicitudForm.getTelefonoAux());

		//Convocatoria
		aux.setDesCuerpoEscala(convocatoriaBean.getDesCuerpoEscala());
		if(convocatoriaBean.getEspecialidads() != null && convocatoriaBean.getEspecialidads().size()>0){
			aux.setEspecialidades(convocatoriaBean.getEspecialidads());
		}
		aux.setDesFormaAcceso(convocatoriaBean.getDesFormaAcceso());
		aux.setDesMinisterio(convocatoriaBean.getDesMinisterioConvocante());
		SimpleDateFormat sdf = new SimpleDateFormat(STRING_SIMPLE_DATE_FORMAT);
		aux.setFechaBOE(sdf.format(convocatoriaBean.getFechaBoe()));

		if(convocatoriaBean.getProvinciasExamen() != null && convocatoriaBean.getProvinciasExamen().size()>0){
			aux.setProvinciasExamen(convocatoriaBean.getProvinciasExamen());
		}
		aux.setImporte(String.valueOf(convocatoriaBean.getImporte()));

		//Titulos
		if(convocatoriaBean.getTituloOficials() != null && convocatoriaBean.getTituloOficials().size()>0){
			aux.setTitulosOficiales(convocatoriaBean.getTituloOficials());
		}
		//Bases
		return aux;
	}

	/**
	 * Metodo de transformacion.
	 *
	 * @param convocatoria el convocatoria
	 * @param ciudadano el ciudadano
	 * @param solicitud el solicitud
	 * @return el alta solicitud bean
	 */
	private AltaSolicitudBean toAltaSolicitudBean(
			ConvocatoriaBean convocatoria, CiudadanoBean ciudadano,
			SolicitudBean solicitud) {
		SimpleDateFormat formatoFecha= new SimpleDateFormat(STRING_SIMPLE_DATE_FORMAT);
		AltaSolicitudBean aux = new AltaSolicitudBean();

		aux.setIdConvocatoria(String.valueOf(convocatoria.getId()));
		//Convocatoria
		aux.setDesCentroGestor(convocatoria.getDesCentroGestor().toUpperCase());
		if(solicitud.getNumeroSolicitud()!=null){
			aux.setNumSolicitud(solicitud.getNumeroSolicitud());
		}
		aux.setEjercicio(convocatoria.getEjercicio());

		//Personales
		aux.setNif(ciudadano.getNif().toUpperCase());
		aux.setNombre(ciudadano.getNombre().toUpperCase());
		aux.setPrimerApellido(ciudadano.getPrimerApellido().toUpperCase());
		aux.setSegundoApellido(ciudadano.getSegundoApellido().toUpperCase());

		if(solicitud!=null){
			if(solicitud.getEmail()!=null){
				aux.setEmail(solicitud.getEmail());
			}

			if(solicitud.getCalleDomicilio()!=null){
				aux.setCalleDomicilio(solicitud.getCalleDomicilio().toUpperCase());
			}

			if(solicitud.getCodigoPostalDomicilio()!=null){
				aux.setCodigoPostalDomicilio(solicitud.getCodigoPostalDomicilio().toUpperCase());
			}

			if(solicitud.getMunicipioDomicilio()!=null){
				aux.setMunicipioDomicilio(solicitud.getMunicipioDomicilio().toUpperCase());
			}

			boolean separador = false;
			String telefono1 = "";
			String telefono2 = "";
			int delimitador = 0;
			if(solicitud.getTelefono() != null){
				for(int i=0;i<solicitud.getTelefono().length();i++){
					delimitador = 0;
					if((solicitud.getTelefono().charAt(i)) == '/'){
						separador = true;
						delimitador=1;
					}
					if(separador == false){
						telefono1 = telefono1 + solicitud.getTelefono().charAt(i);
					}else{
						if(separador == true && delimitador == 0){
							telefono2 = telefono2 + solicitud.getTelefono().charAt(i);
						}
					}
				}
			}

			aux.setTelefono(telefono1);
			aux.setTelefonoAux(telefono2);
			solicitud.setTelefono1(telefono1);
			solicitud.setTelefonoAux(telefono2);
			//Discapacidad
			if (solicitud.getPorcentajeDiscapacidad() != null) {
				aux.setPorcentajeDiscapacidad(solicitud.getPorcentajeDiscapacidad().toString());
			}
			if (solicitud.getDetalleDiscapacidad() != null) {
				aux.setDetalleDiscapacidad(solicitud.getDetalleDiscapacidad());
			}
		}

		//Convocatoria
		aux.setDesCuerpoEscala(convocatoria.getDesCuerpoEscala().toUpperCase());
		if(convocatoria.getEspecialidads() != null && convocatoria.getEspecialidads().size()>0){
			aux.setEspecialidades(convocatoria.getEspecialidads());
		}
		aux.setDesFormaAcceso(convocatoria.getDesFormaAcceso().toUpperCase());
		aux.setIdFormaAcceso(convocatoria.getIdFormaAcceso());
		aux.setDesMinisterio(convocatoria.getDesMinisterioConvocante().toUpperCase());
		if(convocatoria.getFechaBoe() != null){
			String fechaAuxBoe = formatoFecha.format(convocatoria.getFechaBoe());
			aux.setFechaBOE(fechaAuxBoe);
		}
		if(convocatoria.getProvinciasExamen() != null && convocatoria.getProvinciasExamen().size()>0){
			aux.setProvinciasExamen(convocatoria.getProvinciasExamen());
		}
		aux.setImporte(String.valueOf(convocatoria.getImporte()));
		aux.setImporteOriginal(String.valueOf(convocatoria.getImporte()));
		//Titulos
		if(convocatoria.getTituloOficials() != null && convocatoria.getTituloOficials().size()>0){			
			aux.setTitulosOficiales(convocatoria.getTituloOficials());
		}
		//Bases

		//Plantillas
		if(convocatoria.getPlantillaPropios()!=null && convocatoria.getPlantillaPropios().size()>0){
			camposPropiosMultiIdioma(convocatoria.getPlantillaPropios());
			aux.setPlantillaPropios(convocatoria.getPlantillaPropios());
		}

		aux.setEnlace(convocatoria.getEnlace());
		return aux;
	}

	/**
	 * Metodo que carga los campos propios multi-idiomados.
	 *
	 * @param plantillaPropios el plantilla propios
	 */
	private void camposPropiosMultiIdioma(List<PlantillaPropiosBean> plantillaPropios) {

		// TODO BILINGUE - Formulario web de inscripción.

		Locale locale = (Locale)this.getSession().getAttribute(Globals.LOCALE_KEY);
		String idioma = "";
		if(null!=locale){
			idioma = locale.getLanguage();
		}

		for (PlantillaPropiosBean plantillaPropiosBean : plantillaPropios) {
			if(idioma.equals(Constantes.CATALAN)){
				plantillaPropiosBean.getCampoPropioBean().setTituloCampo(plantillaPropiosBean.getCampoPropioBean().getTituloCampoCa());
			}else if(idioma.equals(Constantes.GALLEGO)){
				plantillaPropiosBean.getCampoPropioBean().setTituloCampo(plantillaPropiosBean.getCampoPropioBean().getTituloCampoGl());
			}else if(idioma.equals(Constantes.VALENCIANO)){
				plantillaPropiosBean.getCampoPropioBean().setTituloCampo(plantillaPropiosBean.getCampoPropioBean().getTituloCampoVl());
			}else if(idioma.equals(Constantes.EUSKERA)){
				plantillaPropiosBean.getCampoPropioBean().setTituloCampo(plantillaPropiosBean.getCampoPropioBean().getTituloCampoEu());
			}
		}
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
	 * Obtiene el tablas base manager.
	 *
	 * @return el tablas base manager
	 */
	public TablaBaseManager getTablasBaseManager() {
		return tablasBaseManager;
	}

	/**
	 * Establece el tablas base manager.
	 *
	 * @param tablasBaseManager el nuevo tablas base manager
	 */
	public void setTablasBaseManager(TablaBaseManager tablasBaseManager) {
		this.tablasBaseManager = tablasBaseManager;
	}

	/**
	 * Obtiene el plantilla manager.
	 *
	 * @return el plantilla manager
	 */
	public PlantillaManager getPlantillaManager() {
		return plantillaManager;
	}

	/**
	 * Establece el plantilla manager.
	 *
	 * @param plantillaManager el nuevo plantilla manager
	 */
	public void setPlantillaManager(PlantillaManager plantillaManager) {
		this.plantillaManager = plantillaManager;
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
	 * Conexion pasarela.
	 *
	 * @param pagoSolicitudForm el pago solicitud form
	 * @return el default datos pago out
	 */
	private DefaultDatosPagoOut conexionPasarela(PagoSolicitudForm pagoSolicitudForm){
		logger.info("Entra en conexionPasarela");

		String llamada=null;
		if(this.getRequest().getParameter(STRING_LLAMADA) != null){
			llamada = this.getRequest().getParameter(STRING_LLAMADA);
		}
		//interface para WS
		PPServiceInterfaceServiceLocator locator = new PPServiceInterfaceServiceLocator();
		locator.setPPServiceInterfaceSoap11EndpointAddress(properties.getProperty("direccion.pasarela.ws"));

		//Objeto de pago
		DefaultDatosPagoIn dpi = new DefaultDatosPagoIn();
		DefaultDatosPagoOut defaultDatosPagoOut = new DefaultDatosPagoOut(); 
		
		String convocatoriaRepetida_Unico = properties.getProperty("altaSolicitud.inscripcionMultiple_Unica");

		try {			

			CiudadanoBean usuActual = null;
			//Comprobar si el usuario esta en la sesion
			
			if(convocatoriaRepetida_Unico.equals("U")){

				usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute(STRING_USUARIO_CLAVE);
				if(usuActual == null || usuActual.getNif() == null){
					this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_BUNDLE.getString(STRING_SOLICITUD_ERROR_USUARIO));
					
				}
				
				
			}else{
				usuActual = new CiudadanoBean();
				usuActual.setNif("12345678Z");
				usuActual.setNombre("ANF");
				usuActual.setPrimerApellido("USUARIO");
				usuActual.setSegundoApellido("ACTIVO");
			}

			if(convocatoriaRepetida_Unico.equals("U")){			 
				dpi.setJustificante(pagoSolicitudForm.getNumeroSolicitud());
			}else{
				dpi.setJustificante("7900013559636");
			}
			dpi.setCodigoTasa("001");
			dpi.setOrigenFirma(pagoSolicitudForm.getOrigenFirma().replace("\r\n", "\n"));

			float importeActual = pagoSolicitudForm.getImporteActual();
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

				String str_comun = "JUSTIFICANTE:           " + nJustificante + "\n" +
						"NIF/CIF:                " + nif + "\n" +
						"IMPORTE DEL INGRESO:    " + importe.replace(".", ",") + "\n" +
						"CODIGO DE LA CUENTA:    " + cuenta + "\n";
				dpi.setOrigenFirma(str_comun);
			}		 

			dpi.setTipoDocumentoObligado(TiposDocumento.fromValue(1));
			dpi.setDocumentoObligado(usuActual.getNif());
			dpi.setNombre(usuActual.getNombre());
			dpi.setApellido1(usuActual.getPrimerApellido());
			dpi.setApellido2(usuActual.getSegundoApellido());

			if(pagoSolicitudForm.getFormPago().equalsIgnoreCase(Constantes.PAGO_TIPO_ADEUDO_CHAR)){
				
				String numeroCuentaAux = "";
					for(int i = 0; i < pagoSolicitudForm.getIBAN().length(); i++) {
						if(i<4 || i>7) {
							numeroCuentaAux += pagoSolicitudForm.getIBAN().charAt(i);
						}
					}
				String numeroCuenta = numeroCuentaAux; //IBAN sin el numero del banco

				dpi.setTipoCargo(TiposCargo.fromValue(0));
				dpi.setCCC(numeroCuenta); 
				dpi.setCodigoBanco(pagoSolicitudForm.getBancoAdeudo());	
				
			}else if(pagoSolicitudForm.getFormPago().equalsIgnoreCase(Constantes.PAGO_TIPO_TARJETA_CHAR)){
				String numeroTarjeta = pagoSolicitudForm.getNumTarjeta2() + pagoSolicitudForm.getNumTarjeta3() + pagoSolicitudForm.getNumTarjeta4() + pagoSolicitudForm.getNumTarjeta5();
				Calendar date = Calendar.getInstance();

				int CaducidadMes = Integer.parseInt(pagoSolicitudForm.getCaducidadTarjeta1());
				if(CaducidadMes > 0) CaducidadMes--; //Utilizando CALENDAR Enero corresponde a 0
				date.set(Calendar.MONTH, CaducidadMes);
				date.set(Calendar.YEAR, Integer.parseInt(pagoSolicitudForm.getCaducidadTarjeta2()));				 

				dpi.setTipoCargo(TiposCargo.fromValue(1));
				dpi.setCodigoBanco(pagoSolicitudForm.getBancoTarjeta());
				dpi.setNumeroTarjeta(numeroTarjeta);
				dpi.setFechaCaducidadTarjeta(date);
			}

	
			dpi.setFirma1(pagoSolicitudForm.getSignature());

			try {
				Base64Coder coder = new  Base64Coder ();
				byte[] aCoder = coder.decodeBase64(pagoSolicitudForm.getSignature().getBytes());
				String certificado = SignDataUtil.getInstance().getCertificateFromSignXML(aCoder);
				dpi.setCertificado1(certificado);
			} catch (Exception e1) {
				logger.error("Error  pasarela de pago,pago solicitud,tiempo conexion" ,e1);
				defaultDatosPagoOut = null;
				this.getRequest().setAttribute("errorPasarela", RESOURCE_BUNDLE.getString("field.form.pagoSolicitud.error.tiempoConexion"));
			}

			int idOrganismo = 0;
			try {
				idOrganismo = Integer.parseInt(properties.getProperty("conf.idOrganismo"));
			} catch (NumberFormatException e) {
				logger.error("La propiedad idOrganismo no se encuentra en el fichero " + RESOURCE_BUNDLE + " o no tiene un formato válido.",e);
				logger.error("Se va a indicar como idOrganismo el valor 0 por defecto.", e);
				idOrganismo = 0;
			}

			PPServiceInterfaceSoap11Stub binding = (PPServiceInterfaceSoap11Stub)locator.getPPServiceInterfaceSoap11();
			binding.setTimeout(TIMEOUT);
			logger.info("Lanza llamada al web service");
			logger.info("Datos de Pago enviados a la Pasarela: ");
			logger.info(new BeanFormatter().format(dpi));

			Date date1 = new Date();

			if(!isPrueba){
				defaultDatosPagoOut =  binding.hacerPago(dpi, idOrganismo);
			
			}else{		
				defaultDatosPagoOut =  binding.consultarPago(dpi, idOrganismo);
			}

			Date date2 = new Date();
			logger.warn("Llamada Pasarela end: "+(date2.getTime()-date1.getTime()));


		} catch (ServiceException e) {
			logger.error("Error llamada pasarela",e);
			
			defaultDatosPagoOut = null;
		} catch (RemoteException re) {
			logger.error("Error pago solicitud, tiempo conexion" ,re);
			defaultDatosPagoOut = null;
			this.getRequest().setAttribute("errorPasarela", RESOURCE_BUNDLE.getString("field.form.pagoSolicitud.error.tiempoConexion"));
		} 
		
		logger.info("Fin de conexionPasarela");
		
		return defaultDatosPagoOut;	
	}

	/**
	 * Comprobar documento.
	 *
	 * @param doc el doc
	 * @param usuActual el usu actual
	 * @return verdadero, si tiene exito
	 */
	private boolean comprobarDocumento(DocumentoBean doc,CiudadanoBean usuActual) {

		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.setIdSolicitud(doc.getIdSolicitud());
		SolicitudBean solicitud = solicitudesManager.buscarSolicitudById(solicitudQuery);
		if(solicitud != null && (solicitud.getNif().equals(usuActual.getNif()) || usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO))){
			
				return true;
			
		}
		
		return false;
	}

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
	 * Obtiene el registro solicitud manager.
	 *
	 * @return el registro solicitud manager
	 */
	public RegistroSolicitudManager getRegistroSolicitudManager() {
		return registroSolicitudManager;
	}

	/**
	 * Establece el registro solicitud manager.
	 *
	 * @param registroSolicitudManager el nuevo registro solicitud manager
	 */
	public void setRegistroSolicitudManager(
			RegistroSolicitudManager registroSolicitudManager) {
		this.registroSolicitudManager = registroSolicitudManager;
	}

	/**
	 * Obtiene el documentos convocatoria manager.
	 *
	 * @return el documentos convocatoria manager
	 */
	public DocumentosConvocatoriaManager getDocumentosConvocatoriaManager() {
		return documentosConvocatoriaManager;
	}

	/**
	 * Establece el documentos convocatoria manager.
	 *
	 * @param documentosConvocatoriaManager el nuevo documentos convocatoria manager
	 */
	public void setDocumentosConvocatoriaManager(
			DocumentosConvocatoriaManager documentosConvocatoriaManager) {
		this.documentosConvocatoriaManager = documentosConvocatoriaManager;
	}

	/**
	 * Obtiene el tarifa manager.
	 *
	 * @return el tarifa manager
	 */
	public TarifaManager getTarifaManager() {
		return tarifaManager;
	}

	/**
	 * Establece el tarifa manager.
	 *
	 * @param tarifaManager el nuevo tarifa manager
	 */
	public void setTarifaManager(TarifaManager tarifaManager) {
		this.tarifaManager = tarifaManager;
	}

	/**
	 * Obtiene el comunidades manager.
	 *
	 * @return el comunidades manager
	 */
	public ComunidadesManager getComunidadesManager() {
		return comunidadesManager;
	}

	/**
	 * Establece el comunidades manager.
	 *
	 * @param comunidadesManager el nuevo comunidades manager
	 */
	public void setComunidadesManager(ComunidadesManager comunidadesManager) {
		this.comunidadesManager = comunidadesManager;
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
	 * Obtiene el cuerpo escala manager.
	 *
	 * @return el cuerpo escala manager
	 */
	public CuerpoEscalaManager getCuerpoEscalaManager() {
		return cuerpoEscalaManager;
	}

	/**
	 * Establece el cuerpo escala manager.
	 *
	 * @param cuerpoEscalaManager el nuevo cuerpo escala manager
	 */
	public void setCuerpoEscalaManager(CuerpoEscalaManager cuerpoEscalaManager) {
		this.cuerpoEscalaManager = cuerpoEscalaManager;
	}
	
	/**
	 * Obtiene el datos propios configuracion manager.
	 *
	 * @return el datos propios configuracion manager
	 */
	public DatosPropiosConfiguracionManager getDatosPropiosConfiguracionManager() {
		return datosPropiosConfiguracionManager;
	}

	/**
	 * Establece el datos propios configuracion manager.
	 *
	 * @param datosPropiosConfiguracionManager el nuevo datos propios configuracion manager
	 */
	public void setDatosPropiosConfiguracionManager(DatosPropiosConfiguracionManager datosPropiosConfiguracionManager) {
		this.datosPropiosConfiguracionManager = datosPropiosConfiguracionManager;
	}
	
	public ConfiguracionErroresManager getConfiguracionErroresManager() {
		return configuracionErroresManager;
	}

	public void setConfiguracionErroresManager(ConfiguracionErroresManager configuracionErroresManager) {
		this.configuracionErroresManager = configuracionErroresManager;
	}

	/**
	 * Cargar datos propios configuracion.
	 *
	 * @param plantillaPropios el plantilla propios
	 * @param convocatoria el convocatoria
	 */
	private void cargarDatosPropiosConfiguracion(List<PlantillaPropiosBean> plantillaPropios, ConvocatoriaBean convocatoria) {
		for (PlantillaPropiosBean plantillaBean : plantillaPropios) {
			if (plantillaBean.getCampoPropioBean() != null && plantillaBean.getCampoPropioBean().getId() != null) {
				DatosPropiosConfiguracionQuery datosPropiosConfigQuery = new DatosPropiosConfiguracionQuery();
				datosPropiosConfigQuery.addCampoIn(plantillaBean.getCampoPropioBean().getId().intValue());
				List<DatosPropiosConfiguracion> listaDatosPropiosConf = null;
				
				if (convocatoria.getDatosPropiosConfiguracion() != null && convocatoria.getDatosPropiosConfiguracion().size() > 0) {
					for (DatosPropiosConfiguracion datoPropio : convocatoria.getDatosPropiosConfiguracion()) {
						datosPropiosConfigQuery.addIdDesplegableIn(datoPropio.getIdDesplegable());
					}
					listaDatosPropiosConf = datosPropiosConfiguracionManager.buscarDatosPropiosConfigbyCampo(datosPropiosConfigQuery);
				}
				
				
				if (listaDatosPropiosConf != null && listaDatosPropiosConf.size() > 0) {
					plantillaBean.getCampoPropioBean().setDatosPropiosConfiguracion(listaDatosPropiosConf);
				}
			}
		}
	}
}
