package es.map.ipsc.spring.solicitudes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.spring.Globals;
import org.springframework.util.StringUtils;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.spring.SpringForm;
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
import es.map.ips.model.SolicitudCcaaQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.SolicitudPropiosQuery;
import es.map.ips.model.TipoDiscapacidadQuery;
import es.map.ips.model.TipoDocumentoQuery;
import es.map.ipsc.Constantes; 
import es.map.ipsc.bean.AltaSolicitudBean;
import es.map.ipsc.bean.CamposPropiosBean;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.ComunidadesBean;
import es.map.ipsc.bean.ConfiguracionErroresBean;
import es.map.ipsc.bean.ConvocatoriaBean;
import es.map.ipsc.bean.DetallePagoSolicitudBean;
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
import es.map.ipsc.bean.SolicitudPropiosBean;
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
import es.map.ipsc.manager.solicitudes.SolicitudPropioManager;
import es.map.ipsc.manager.tablaBase.ParametroConfiguracionManager;
import es.map.ipsc.manager.tablaBase.TablaBaseManager;
import es.map.ipsc.manager.tasas.MotivoReduccionManager;
import es.map.ipsc.manager.tasas.TarifaManager;
import es.map.ipsc.spring.AbstractSecureSpring;
import es.map.ipsc.utils.IpsUtils;

/**
 * El Class SubsanarSolicitudUnificado.
 */
public class SubsanarSolicitudUnificado extends AbstractSecureSpring  {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SubsanarSolicitudUnificado.class);

	/** La constante BUNDLE_ERROR. */
	private static final String BUNDLE_ERROR = "MessageResources";
	
	/** La constante BUNDLE_MESSAGES. */
	private static final String BUNDLE_MESSAGES = "MessageResources";
	
	/** La constante BUNDLE_NAME. */
	private static final String BUNDLE_NAME = "file";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_ERROR);
	
	/** La constante RESOURCE_MESSAGES. */
	private static final ResourceBundle RESOURCE_MESSAGES = ResourceBundle.getBundle(BUNDLE_MESSAGES);
	
	/** La constante RESOURCE_BUNDLE_NAME. */
	private static final ResourceBundle RESOURCE_BUNDLE_NAME = ResourceBundle.getBundle(BUNDLE_NAME);
	
	/** La constante STRING_USUARIOCLAVE. */
	private static final String STRING_USUARIOCLAVE = "usuarioClave";
	
	/** La constante STRING_ERRORDESCRIPCION. */
	private static final String STRING_ERRORDESCRIPCION = "errorDescripcion";
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/** La constante STRING_PATRONFECHA. */
	private static final String STRING_PATRONFECHA = "dd/MM/yyyy";
	
	/** La constante STRING_ERRORFORM2. */
	private static final String STRING_ERRORFORM2 = "errorForm2";
	
	/** La constante STRING_FALSE. */
	private static final String STRING_FALSE = "false";
	
	/** La constante STRING_DATOSPAGO. */
	private static final String STRING_DATOSPAGO = "inscripcion.datos.pago.mensaje";
	
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
	
	/** el solicitud propio manager. */
	private SolicitudPropioManager solicitudPropioManager;
	
	/** el datos propios configuracion manager. */
	private DatosPropiosConfiguracionManager datosPropiosConfiguracionManager;
	private ConfiguracionErroresManager configuracionErroresManager;

	/**
	 * Crea una nueva subsanar solicitud unificado.
	 */
	public SubsanarSolicitudUnificado() {
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
			setSolicitudPropioManager((SolicitudPropioManager) getBean("solicitudPropioManager"));
			setDatosPropiosConfiguracionManager((DatosPropiosConfiguracionManager) getBean("datosPropiosConfiguracionManager"));
			setConfiguracionErroresManager((ConfiguracionErroresManager) getBean("configuracionErroresManager"));
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
		SolicitudForm solicitudForm = (SolicitudForm) form;
		AltaSolicitudBean altaSolicitudBean = new AltaSolicitudBean();
		String siglasCentroGestorJusticia = "";
		
		String idSolicitudInicial = "";
		// id solicitud inicial
		if ((String)this.getRequest().getParameter("idSolicitudSub") != null) {
			idSolicitudInicial = (String)this.getRequest().getParameter("idSolicitudSub");
			solicitudForm.setIdSolicitud(idSolicitudInicial);
		}
		
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		this.getRequest().getSession().setAttribute("pagActiva", "inscripcionOnline");
		//****************************************************************** 

		//Cargar el porcentaje de discapacidad minimo actual
		ParametrosConfiguracionQuery paramConfQuery = new ParametrosConfiguracionQuery();
		paramConfQuery.setNombreCampo(Constantes.PARAMETROS_PORCENTAJE_DISCAPACIDAD);
		ParametrosConfiguracionBean parametrosConfiguracion = parametroConfiguracionManager.buscarParametroConfiguracionUnico(paramConfQuery);
		solicitudForm.setPorcentajeMinDiscapacidad(parametrosConfiguracion.getValorCampo());
		
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
				
				usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute(STRING_USUARIOCLAVE);
				if(usuActual == null || usuActual.getNif() == null){
					this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.usuario"));
					return "errorUsuario";
				}

				CiudadanoBean ciudadanoAux = new CiudadanoBean();
				ciudadanoAux.setNif(usuActual.getNif());

				//Comprobar si el ciudadano ya se ha inscrito en esa convocatoria
				ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
				convocatoriaQuery.setId(idConvocatoria);			
				SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
				solicitudQuery.setConvocatoria(convocatoriaQuery);
				solicitudQuery.setNif(ciudadanoAux.getNif());
						
				solicitudQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_NOPAGADO);
				solicitudQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_NOREGISTRADO);
				solicitudQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_REGISTRADO);
				solicitudQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_PENDIENTE_REGISTRO);
				solicitudQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_PROCESO_REGISTRO);
				solicitudQuery.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_PROCESO_PAGO);
				

				ConvocatoriaBean convocatoriaBean = convocatoriaManager.recuperarConvocatoria(idConvocatoria);

				 Date today = new Date();			 
				 if( convocatoriaBean.getFechaFinSub() != null && today.after(convocatoriaBean.getFechaFinSub()) && !DateUtils.isSameDay(today, convocatoriaBean.getFechaFinSub()) )  {
					 this.getRequest().setAttribute(STRING_ERRORDESCRIPCION, RESOURCE_MESSAGES.getString("field.formulario790.errorConvocatoria"));
					 return "errorConvocatoriaCaducada";					
					 }
				String siglasCentroGestor = convocatoriaBean.getSiglasCentroGestor();
				solicitudForm.setSiglasCentroGestor(siglasCentroGestor);
				solicitudForm.setSiglasCentroGestorJusticia(siglasCentroGestorJusticia);
				solicitudForm.setImporteOriginal(String.valueOf(convocatoriaBean.getImporte()));

				this.getRequest().getSession().setAttribute("sCG", siglasCentroGestor); 
				this.getRequest().getSession().setAttribute("sCGJ", siglasCentroGestorJusticia); 
				

				SolicitudBean solicitudBean = new SolicitudBean();
				SolicitudComunQuery solicitudBeanQuery = new SolicitudComunQuery();
				solicitudBeanQuery.setNumeroSolicitud(null);
				solicitudBeanQuery.setNif(null);
				solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);
				
				//Carga los documentos adjuntos asociado a la solicitud
				cargarDocumentosAdjuntos(solicitudQuery, solicitudBean);	
				
				
				SolicitudCcaaQuery solicitudCcaaQuery = new SolicitudCcaaQuery();
				solicitudCcaaQuery.setSolicitudComun(solicitudQuery);
				SolicitudCcaaBean solicitudCcaaBean = solicitudCcaaManager.buscarSolicitudCcaaIdSolicitud(solicitudCcaaQuery);

				PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
				pagoSolicitudQuery.setSolicitudComun(solicitudQuery);
				//Busca el pago de la solicitud
				DetallePagoSolicitudBean pagoSolicitud = pagoSolicitudManager.buscarUltimoPagoSolicitudIdSolicitud(pagoSolicitudQuery);
		
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

				// Si el usuario esta inscrito
				if(solicitudBean!=null){
					int idPaisDomicilio=0;

					if (StringUtils.isEmpty(idSolicitudInicial) && solicitudBean.getId() != null) {
						idSolicitudInicial = solicitudBean.getId().toString();
					}
					
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
					
					int idEspecialidad = 0;
					if(solicitudBean.getEspecialidad() != null){
						idEspecialidad= solicitudBean.getEspecialidad().getId();
					}
					solicitudForm.setEspecialidad(String.valueOf(idEspecialidad));
					
					int idProvinciaExamen = 0;
					if(solicitudBean.getProvinciaExamen() != null){
						idProvinciaExamen = solicitudBean.getProvinciaExamen().getId();
					}
					solicitudForm.setProvinciaExamen(String.valueOf(idProvinciaExamen));
					
					int idTitulo = 0;
					if(solicitudBean.getTituloOficial() != null && solicitudBean.getTituloOficial().getId() != null){
						idTitulo = solicitudBean.getTituloOficial().getId();
					}
					solicitudForm.setTitulo(String.valueOf(idTitulo));
					
					String idMotivoReduccion = null;
					if(pagoSolicitud != null && pagoSolicitud.getMotivoReduccion() != null){
						idMotivoReduccion = pagoSolicitud.getMotivoReduccion();
					}
					solicitudForm.setIdMotivoReduccion(idMotivoReduccion);
					
					// Importe pagado
					// Se debe calcular la suma de los importes pagados en los diferentes pagos				
					ArrayList<DetallePagoSolicitudBean> pagosAsociados = pagoSolicitudManager.buscarDetallePagoSolicitud(pagoSolicitudQuery);
					Double importeTotal = 0.0;
					for(DetallePagoSolicitudBean pagoAsociado : pagosAsociados){
						if(pagoAsociado.getResultado() != null && pagoAsociado.getResultado().equals("OK")){
							importeTotal = importeTotal + Double.parseDouble(pagoAsociado.getImporte());
						}
					}
					String importeTotalS = String.valueOf(IpsUtils.fijarNumero(importeTotal, 2));
					solicitudForm.setImportePagado(importeTotalS);
					pagoSolicitud.setImporte(importeTotalS);
					
					
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
						this.getRequest().setAttribute(STRING_ERRORDESCRIPCION, RESOURCE_BUNDLE.getString("field.numeroSolicitud.error"));	
						resultado = STRING_ERROR;						
					}
					
					altaSolicitudBean = toAltaSolicitudBean(convocatoriaBean, usuActual, solicitudBean, solicitudCcaaBean, pagoSolicitud, idSolicitudInicial);	
					
					// Comprobamos el pago que se ha realizado anteriormente:
					
					// 1.	Que solicitara el ciudadano inicialmente exencion del 100
					// Se precarga el check correspondiente e importe 0
					// Si se desmarca se actualiza el importe
					
					// 2.  Que solicitara inicialmente exencion del 50 (o cualquier otra)
					// Se precarga el check correspondiente
					// Si se desmarca, el importeOriginal debe ser el importe que se establece en la convocatoria menos el importe pagado
					if(pagoSolicitud.getMotivoReduccion() != null && pagoSolicitud.getMotivoReduccionTasa() != null &&
							 pagoSolicitud.getMotivoReduccionTasa().getPorcentajeDescuento() != null){
						// Se calcula el importe pagado en base al descuento aplicado
						String importePagado = null;				
						importePagado = pagoSolicitud.getImporte();
						altaSolicitudBean.setImportePagado(importePagado);
						// Exite la posibilidad de modificar el pago
						altaSolicitudBean.setPagoInicialModif("S");
					}
					
					// 3.	Que no solicitara el descuento y pagara el importe completo (mereciendo realmente el descuento)
					// En este caso se le mostrara un mensaje en el apartado de los datos de pago, 
					// que la inscripcion está pagada en su totalidad y que no tiene que realizar ningun pago
					// 4.	Que pagara el importe completo correctamente
					// Ya pago la solicitud inicial y no se han modificado los datos del pago, y ha modificado otros no debera volver a realizar el pago.
					// En este caso se le mostrara un mensaje en el apartado de los datos de pago, 
					// que la inscripcion está pagada en su totalidad y que no tiene que realizar ningun pago
					String mensajePago = "";
					if(pagoSolicitud.getMotivoReduccion() == null 
							|| (!StringUtils.isEmpty(pagoSolicitud.getImporte()) && !StringUtils.isEmpty(convocatoriaBean.getImporte())
									&& pagoSolicitud.getImporte().equals(Float.toString(convocatoriaBean.getImporte())))){
						String importePagado = "";
						Float importeConvocatoriaFloat = null;
						String importeConvocatoria = "";
						
						importePagado = pagoSolicitud.getImporte();
						importeConvocatoriaFloat = convocatoriaBean.getImporte();
						if(importeConvocatoriaFloat != null){
							importeConvocatoria = importeConvocatoriaFloat.toString();
						}
						
						// Comprobamos si se pago el importe completo
						if(importePagado.equals(importeConvocatoria)){
							mensajePago = RESOURCE_BUNDLE.getString(STRING_DATOSPAGO);
						}
						// No existe posibildiad de modificar el pago
						altaSolicitudBean.setPagoInicialModif("N");
						
					}
					
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
					altaSolicitudBean.setListaCamposPropios(listaCamposPropios);

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
					this.setRequestAttribute("listaCamposPropios",listaCamposPropios);
					this.setRequestAttribute("mensajePago", mensajePago);					
				}else{
					this.setRequestAttribute("mensajeNoRegistrado", true);
					return "noRegistrado";
				}

			}else{
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.alta.convocatoriaNull"));
				return STRING_ERROR;
			}


		PagoSolicitudForm solicitudPagoForm = new PagoSolicitudForm();
		String busqueda = solicitudPagoForm.getSubmit();

		if(busqueda == null){
			resultado =  "show";

			long idSolicitud = Long.valueOf(altaSolicitudBean.getIdConvocatoria());

			//Comprobar si el usuario esta en la sesion
			CiudadanoBean usuActual = null;
			
			usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute(STRING_USUARIOCLAVE);
			
			if(usuActual == null || usuActual.getNif() == null){
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.usuario"));
				return "errorUsuario";
			}


			CiudadanoBean ciudadanoAux = new CiudadanoBean();
			ciudadanoAux.setNif(usuActual.getNif());
			ConvocatoriaBean convocatoriaBean = convocatoriaManager.recuperarConvocatoria(idSolicitud);

			if(altaSolicitudBean != null && convocatoriaBean != null){
				if(convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_SUBSANADA)){
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

					
					//solicitudPagoForm.setNumeroTitulo("");
					this.setRequestAttribute("id", String.valueOf(idSolicitud));
					this.setRequestAttribute("entidades", entidadBean);
					this.setRequestAttribute("entidadesTarjetas", entidadBeanTarjeta);

					String motivoTipificado="##";
					if(convocatoriaBean.getMotivoReduccionTasasIncompleto() != null){
						this.setRequestAttribute("motivos2", convocatoriaBean.getMotivoReduccionTasasIncompleto());

						for (MotivoReduccionTasa tasa : convocatoriaBean.getMotivoReduccionTasasIncompleto()) {
							motivoTipificado=motivoTipificado+tasa.getId()+"##"+tasa.getPorcentajeDescuento()+"##";
						} 

					}else{
						ArrayList<MotivoReduccionTasa> arrayTasas = new ArrayList<MotivoReduccionTasa>();
						this.setRequestAttribute("motivos2", arrayTasas);

						for (MotivoReduccionTasa tasa : arrayTasas) {
							motivoTipificado=motivoTipificado+tasa.getId()+"##"+tasa.getPorcentajeDescuento()+"##";
						}

					}
					if(convocatoriaBean.getMotivoReduccionTasasCompleto() != null){
						this.setRequestAttribute("motivosCompletos2", convocatoriaBean.getMotivoReduccionTasasCompleto());

						for (MotivoReduccionTasa tasa : convocatoriaBean.getMotivoReduccionTasasCompleto()) {
							motivoTipificado=motivoTipificado+tasa.getId()+"##"+tasa.getPorcentajeDescuento()+"##";
						}
					}else{
						ArrayList<MotivoReduccionTasa> arrayTasas = new ArrayList<MotivoReduccionTasa>();
						this.setRequestAttribute("motivosCompletos2", arrayTasas);

						for (MotivoReduccionTasa tasa : arrayTasas) {
							motivoTipificado=motivoTipificado+tasa.getId()+"##"+tasa.getPorcentajeDescuento()+"##";
						}

					}

					ArrayList<ComunidadesBean> comunidadesFN = new ArrayList<ComunidadesBean>();
					ComunidadesQuery comunidadesQueryFN = new ComunidadesQuery();
					comunidadesQueryFN.setServicioFamnumerosa(true);
					comunidadesFN = comunidadesManager.buscarComunidadesCombo(comunidadesQueryFN);
					this.setRequestAttribute("listcomunidadesFN", comunidadesFN);
					this.setRequestAttribute("numComunidadesFN",comunidadesFN.size());
					
					// Pasamos al formulario la lista de comunidades aut?nomas que requieren el n?mero de titulo familia numerosa		
					ArrayList<ComunidadesBean> comunidadesReqTitulo = new ArrayList<ComunidadesBean>();
					ComunidadesQuery comunidadesQueryReqTitulo = new ComunidadesQuery();
					comunidadesQueryReqTitulo.setTituloRequerido(true);
					comunidadesReqTitulo = comunidadesManager.buscarComunidadesCombo(comunidadesQueryReqTitulo);

					String comuReqTitulo="##";
					for (ComunidadesBean comunidad : comunidadesReqTitulo) {
						comuReqTitulo=comuReqTitulo+comunidad.getIdComunidad()+"##";
					} 
					this.setRequestAttribute("comunidadesReqTitulo", comuReqTitulo);


					ArrayList<ComunidadesBean> comunidadesDiscapacidad = new ArrayList<ComunidadesBean>();
					ComunidadesQuery comunidadesQueryDD = new ComunidadesQuery();
					comunidadesQueryDD.setServicioDiscapacidad(true);
					comunidadesDiscapacidad = comunidadesManager.buscarComunidadesCombo(comunidadesQueryDD);
					this.setRequestAttribute("listcomunidadesDiscapacidad", comunidadesDiscapacidad);


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
						if(convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_SUBSANADA)){

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
							this.setRequestAttribute("numDocsAdjuntos", Constantes.NUM_MAX_DOCUMENTOS);

							//Comprobar si tiene un justificante de pago
							String tipoPago = ""+pagoSolicitudBean.getTipo();
							if(tipoPago != null){
								if(!tipoPago.equals(Constantes.PAGO_TIPO_EXENTO)){
									this.setRequestAttribute("certPago", true);
								}
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
					this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.convocatoriaNoPublicada"));
					logger.info("La convocatoria con id: "+convocatoriaBean.getId()+" no esta en estado publicado.");
				}

			}else{
				resultado=STRING_ERROR;
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.idErroneo"));
				logger.info("Solicitud o Convocatoria null");
			}				
		}

		if(this.getSessionAttribute(STRING_ERRORFORM2)!=null){
			this.getRequest().setAttribute(STRING_ERRORFORM2,this.getSessionAttribute(STRING_ERRORFORM2));
			this.getSession().removeAttribute(STRING_ERRORFORM2);
		}

		// se setea el usuario de la sesion y el tipo de persona, para que las vistas distingan entre ciudadanos o funcionarios
		CiudadanoBean usuarioActual = (CiudadanoBean) this.getRequest().getSession().getAttribute(STRING_USUARIOCLAVE);
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
			continuar = STRING_FALSE;
		}
		setRequestAttribute("continuar", continuar);
		
		// seteo atributo si proviene la request desde un boton modificar
		String modificar = this.getRequest().getParameter("modificar");
		if (modificar!=null && modificar.equals("true")) {
			modificar = "true";
		} else {
			modificar = STRING_FALSE;
		}
		setRequestAttribute("modificar", modificar);
		setRequestAttribute("subsanarInscripcion", true);		
		
		//Parte Errores Configurados para informar
		cargarErroresConfigurablesInfo();
		
		logger.info("Fin VerAlta");
		
		return resultado;
	}


	/**
	 * Metodo de transformacion.
	 *
	 * @param convocatoria el convocatoria
	 * @param ciudadano el ciudadano
	 * @param solicitud el solicitud
	 * @param solicitudCcaaBean el solicitud ccaa bean
	 * @param pagoSolicitud el pago solicitud
	 * @param idSolicitudInicial el id solicitud inicial
	 * @return el alta solicitud bean
	 */
	private AltaSolicitudBean toAltaSolicitudBean(
			ConvocatoriaBean convocatoria, CiudadanoBean ciudadano,
			SolicitudBean solicitud, SolicitudCcaaBean solicitudCcaaBean, DetallePagoSolicitudBean pagoSolicitud, String idSolicitudInicial) {
		SimpleDateFormat formatoFecha= new SimpleDateFormat(STRING_PATRONFECHA);
		AltaSolicitudBean aux = new AltaSolicitudBean();

		aux.setIdSolicitudInicial(idSolicitudInicial);
		aux.setIdConvocatoria(String.valueOf(convocatoria.getId()));
		//Si ha marcado el consentimiento de los datos
		aux.setIdConsentimiento(solicitud.getIdConsentimiento());
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
		 
		SimpleDateFormat sdf = new SimpleDateFormat(STRING_PATRONFECHA);  
		String nuevaFecha = null;
		if(solicitud.getFechaNacimiento() != null){
			nuevaFecha = sdf.format(solicitud.getFechaNacimiento());  
		}
 
		aux.setFechaNacimiento(nuevaFecha);
		aux.setNacionalidad(solicitud.getNacionalidad());

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
		
		//Otro titulo
		if (!StringUtils.isEmpty(solicitud.getOtrosTitulos())) {
			aux.setOtrosTitulos(solicitud.getOtrosTitulos());
		}
		
		//Discapacidad
		if (!StringUtils.isEmpty(solicitud.getDetalleDiscapacidad())) {
			aux.setDetalleDiscapacidad(solicitud.getDetalleDiscapacidad());
		}
		
		//Bases

		//Plantillas
		if(convocatoria.getPlantillaPropios()!=null && convocatoria.getPlantillaPropios().size()>0){
			camposPropiosMultiIdioma(convocatoria.getPlantillaPropios());
			aux.setPlantillaPropios(convocatoria.getPlantillaPropios());
		}
		
		// Especialidad
		if(solicitud.getEspecialidad() != null){
			aux.setEspecialidad(solicitud.getEspecialidad().getCodigo());
		}
		// Provincia examen
		if(solicitud.getProvinciaExamen() != null && solicitud.getProvinciaExamen().getId() != null){
			aux.setProvinciaExamen(solicitud.getProvinciaExamen().getId().toString());
		}		
		// Discapacidad
		if(solicitud.getPorcentajeDiscapacidad() != null){
			aux.setPorcentajeDiscapacidad(solicitud.getPorcentajeDiscapacidad().toString());
		}
//		// CCAA en la que se reconoce su discapacidad
//		if(solicitudCcaaBean != null && solicitudCcaaBean.getComunidad() != null && solicitudCcaaBean.getComunidad().getIdComunidad() != null){
//			aux.setIdComunidadDDExento(solicitudCcaaBean.getComunidad().getIdComunidad().toString());
//			aux.setIdComunidadFNExento(solicitudCcaaBean.getComunidad().getIdComunidad().toString());
//		}
		// Tipo discapacidad / Reserva
		if(solicitud.getTipoDiscapacidad() != null && solicitud.getTipoDiscapacidad().getId() != null){
			aux.setTipoDiscapacidad(solicitud.getTipoDiscapacidad().getId().toString());
		}
		// Adaptacion que solicita
		if(solicitud.getDetalleDiscapacidad() != null){
			aux.setDetalleDiscapacidad(solicitud.getDetalleDiscapacidad());
		}
		// Titulo exigido en la convocaotria
		if(solicitud.getTituloOficial() != null && solicitud.getTituloOficial().getId() != null){
			aux.setTitulo(solicitud.getTituloOficial().getId().toString());
		}
		// Motivo reduccion
		if(pagoSolicitud.getMotivoReduccion() != null){
			aux.setIdMotivoReduccion(pagoSolicitud.getMotivoReduccion());
		}
		
		// Sexo
		aux.setSexo(String.valueOf(solicitud.getSexo()));

		int idComunidadDDExento = 0;
		if(pagoSolicitud.getMotivoReduccion() != null && pagoSolicitud.getMotivoReduccion().equals("1") && solicitudCcaaBean != null){
			idComunidadDDExento = solicitudCcaaBean.getComunidad().getIdComunidad();
		}
		aux.setIdComunidadDDExento(String.valueOf(idComunidadDDExento));
				
		int idComunidadFNExento = 0;
		if(pagoSolicitud.getMotivoReduccion() != null && (pagoSolicitud.getMotivoReduccion().equals("3")||pagoSolicitud.getMotivoReduccion().equals("5"))){
			if(solicitudCcaaBean != null && solicitudCcaaBean.getComunidad() != null){
				idComunidadFNExento = solicitudCcaaBean.getComunidad().getIdComunidad();
				aux.setIdComunidadFNExento(String.valueOf(idComunidadFNExento));
				if (!StringUtils.isEmpty(solicitudCcaaBean.getTituloFamnumerosa())) {
					aux.setTituloFanumerosa(solicitudCcaaBean.getTituloFamnumerosa());
				}
			}
		}
		
		aux.setIdConsentimientoAEAT(solicitud.getIdConsentimientoAEAT());
		aux.setMotivoOposicion(solicitud.getMotivoOposicion());
		
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
	 * Cargar documentos adjuntos.
	 *
	 * @param solicitudQuery el solicitud query
	 * @param solicitudBean el solicitud bean
	 */
	private void cargarDocumentosAdjuntos(SolicitudComunQuery solicitudQuery, SolicitudBean solicitudBean) {
		if (solicitudBean != null) {
			//Se obtiene documentos di existen
			DocumentoQuery documentoQuery = new DocumentoQuery();
			documentoQuery.setSolicitudComun(solicitudQuery);
			TipoDocumentoQuery tipoDocumentoQuery = new TipoDocumentoQuery();
			tipoDocumentoQuery.addIdIn(1);
			tipoDocumentoQuery.addIdIn(6);
			tipoDocumentoQuery.addIdIn(10);
			documentoQuery.setTipoDocumento(tipoDocumentoQuery);
			List<DocumentoBean> listaDocBean = documentosConvocatoriaManager.buscarDocumentosConvocatoria(documentoQuery);
			if (listaDocBean != null && listaDocBean.size() > 0) {
				this.setRequestAttribute("documentos", listaDocBean);
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
	public void setSolicitudPropioManager(SolicitudPropioManager solicitudPropioManager) {
		this.solicitudPropioManager = solicitudPropioManager;
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
}
