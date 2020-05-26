package es.map.ipsc.spring.solicitudes;


import java.math.BigDecimal;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;

import java.util.Calendar;

import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.xml.rpc.ServiceException;


import org.apache.log4j.Logger;



import ePago.gob.es.schemas.DefaultDatosPagoIn;
import ePago.gob.es.schemas.DefaultDatosPagoOut;
import ePago.gob.es.schemas.PPServiceInterfaceServiceLocator;
import ePago.gob.es.schemas.PPServiceInterfaceSoap11Stub;
import ePago.gob.es.schemas.TiposCargo;
import ePago.gob.es.schemas.TiposDocumento;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.DetallePasarelaBean;


import es.map.ipsc.bean.LogServicioBean;
import es.map.ipsc.bean.LogSolicitudBean;
import es.map.ipsc.bean.PagoSolicitudBean;
import es.map.ipsc.form.PagoSolicitudForm;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.manager.entidadFinanciera.EntidadFinancieraManager;
import es.map.ipsc.manager.logs.LogServiciosManager;
import es.map.ipsc.manager.solicitudes.EstadoSolicitudManager;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.tablaBase.TablaBaseManager;
import es.map.ipsc.manager.tasas.MotivoReduccionManager;
import es.map.ipsc.spring.AbstractSecureSpring;


import es.map.ipsc.utils.Validacion;

/**
 * El Class ConsultaPagoSpring.
 */
public class ConsultaPagoSpring extends AbstractSecureSpring {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SolicitudSpring.class);
	
	/** La constante BUNDLE_ERROR. */
	private static final String BUNDLE_ERROR = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_ERROR);
	
	/** La constante STRING_ERRORPASARELA. */
	private static final String STRING_ERRORPASARELA = "errorPasarela";
	
	/** La constante STRING_ERRORDESCRIPCION. */
	private static final String STRING_ERRORDESCRIPCION = "errorDescripcion";
	
	/** el pago solicitudes manager. */
	private PagoSolicitudManager pagoSolicitudesManager;
	
	/** el solicitudes manager. */
	private SolicitudManager solicitudesManager;
	
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
	
	/** el log servicios manager. */
	private LogServiciosManager logServiciosManager;
	
	/** el documento convocatorias manager. */
	private DocumentosConvocatoriaManager documentoConvocatoriasManager;


	/** el properties. */
	private static Properties properties;

	/**
	 * Crea una nueva consulta pago spring.
	 */
	public ConsultaPagoSpring() {
		try{			
			setPagoSolicitudesManager((PagoSolicitudManager) getBean("pagoSolicitudesManager"));
			setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
			setMotivoReduccionManager((MotivoReduccionManager) getBean("motivoReduccionManager"));
			setEstadoSolicitudManager((EstadoSolicitudManager) getBean("estadoSolicitudManager"));
			setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
			setTablaBaseManager((TablaBaseManager) getBean("tablasBaseManager"));
			setLogServiciosManager((LogServiciosManager) getBean("logServiciosManager"));
			setDocumentoConvocatoriasManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
			properties = (Properties) getBean("propertiesBean");
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error consulta pago",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		logger.info("Entra en ConsultaPagoSpring");
		PagoSolicitudForm pagoSolicitudForm = (PagoSolicitudForm) form;
		String fechaOperacion = "";
		String busqueda = pagoSolicitudForm.getAccion();
		String resultado=null;
		
		if(!"Registrar".equalsIgnoreCase(busqueda)){

			//Si llega al action del submit de guardar
			//interface para WS
			PPServiceInterfaceServiceLocator locator = new PPServiceInterfaceServiceLocator();
			locator.setPPServiceInterfaceSoap11EndpointAddress(properties.getProperty("direccion.pasarela.ws"));

			//Objeto de pago
			DefaultDatosPagoIn dpi = new DefaultDatosPagoIn();
			DefaultDatosPagoOut defaultDatosPagoOut = new DefaultDatosPagoOut(); 

			try {
				dpi.setJustificante(pagoSolicitudForm.getNumeroSolicitud());
				dpi.setCodigoTasa("001");			 

				float importeActual = pagoSolicitudForm.getImporte();
				int decimalPlaces = 2;
				BigDecimal bd = new BigDecimal(importeActual);

				// setScale is immutable
				bd = bd.setScale(decimalPlaces, BigDecimal.ROUND_HALF_UP);
				double importeActual2 = bd.doubleValue();

				dpi.setImporte(importeActual2);

				// TODO Incidencia IPSC-NIE ciudadano.
				Validacion validacion = new Validacion();
				if(validacion.nifValidate(pagoSolicitudForm.getNif())){
					dpi.setTipoDocumentoObligado(TiposDocumento.fromValue(1));
				}else if(validacion.nieValidate(pagoSolicitudForm.getNif())){
					dpi.setTipoDocumentoObligado(TiposDocumento.fromValue(3));
				}

				dpi.setDocumentoObligado(pagoSolicitudForm.getNif());
				dpi.setNombre(pagoSolicitudForm.getNombre());
				dpi.setApellido1(pagoSolicitudForm.getApellido1());
				dpi.setApellido2(pagoSolicitudForm.getApellido2());

				if(pagoSolicitudForm.getFormaPago().equalsIgnoreCase(Constantes.FORMA_PAGO_ADEUDO_S)){
					StringBuffer numeroCuenta = new StringBuffer();
					numeroCuenta.append(pagoSolicitudForm.getIBAN());

					dpi.setTipoCargo(TiposCargo.fromValue(0));
					dpi.setCCC(numeroCuenta.toString()); 
					dpi.setCodigoBanco(pagoSolicitudForm.getBancoAdeudo());	

				}else if(pagoSolicitudForm.getFormaPago().equalsIgnoreCase(Constantes.FORMA_PAGO_TARJETA_S)){
					StringBuffer numeroTarjeta = new StringBuffer();
					numeroTarjeta.append(pagoSolicitudForm.getNumTarjeta2()).append(pagoSolicitudForm.getNumTarjeta3()).append(pagoSolicitudForm.getNumTarjeta4());
					
					Calendar date = Calendar.getInstance();

					int CaducidadMes = Integer.parseInt(pagoSolicitudForm.getCaducidadTarjeta1());
					if(CaducidadMes > 0) CaducidadMes--; //Utilizando CALENDAR Enero corresponde a 0
					date.set(Calendar.MONTH, CaducidadMes);
					date.set(Calendar.YEAR, Integer.parseInt(pagoSolicitudForm.getCaducidadTarjeta2()));				 

					dpi.setTipoCargo(TiposCargo.fromValue(1));
					dpi.setCodigoBanco(pagoSolicitudForm.getBancoTarjeta());
					dpi.setNumeroTarjeta(numeroTarjeta.toString());
					dpi.setFechaCaducidadTarjeta(date);
				}

				dpi.setOrigenFirma(pagoSolicitudForm.getOrigenFirma().replace("\r", ""));//El texto llega con \r\n al final de cada linea. Se eliminan los \r
				dpi.setFirma1(pagoSolicitudForm.getSignature());
				dpi.setCertificado1(pagoSolicitudForm.getSignerCert());

				int idOrganismo = 0;
				try {
					idOrganismo = Integer.parseInt(properties.getProperty("conf.idOrganismo"));
				} catch (NumberFormatException e) {
					logger.error("La propiedad idOrganismo no se encuentra en el fichero " + RESOURCE_BUNDLE + " o no tiene un formato válido.",e);
					logger.error("Se va a indicar como idOrganismo el valor 0 por defecto.", e);
					idOrganismo = 0;
				}

				PPServiceInterfaceSoap11Stub binding = (PPServiceInterfaceSoap11Stub)locator.getPPServiceInterfaceSoap11();
				binding.setTimeout(90000);
				defaultDatosPagoOut =  binding.consultarPago(dpi, idOrganismo);

				if(defaultDatosPagoOut != null ){
					resultado = STRING_ERRORPASARELA;
					this.getRequest().setAttribute("defaultDatosPagoOut",defaultDatosPagoOut);
					
					if(defaultDatosPagoOut.isFueCorrecto()){
						logger.info("Guardando Datos del Error en LogServicio");
						LogServicioBean logServicioBean = new LogServicioBean();
						Date fechaActual = new Date();
						SimpleDateFormat ff = new SimpleDateFormat("dd/MM/yyyy");
						if(defaultDatosPagoOut.getFechaProceso() != null){
							try{
								fechaOperacion = ff.format(defaultDatosPagoOut.getFechaProceso());
							}catch(IllegalArgumentException iae){
								logger.error("No se ha podido parsear la fecha: "+defaultDatosPagoOut.getFechaProceso(),iae);
							}
						}
						logServicioBean.setFecha(fechaActual);
						logServicioBean.setIdTipoServicio(Constantes.LOG_SERVICIO_TIPOSERVICIO_PAGO);
						logServicioBean.setResultado(Constantes.LOG_SERVICIO_RESULTADO_OK);
						logServicioBean.setPrueba(Constantes.LOG_SERVICIO_PRUEBA_NO);
						int result = logServiciosManager.insertarLog(logServicioBean);
						logger.info("Guardados Datos del Error en LogServicio con Id: "+result);
						logger.info("Guardando Datos de Pago en PagoSolicitud");
						PagoSolicitudBean pagoSolicitudBean = toPagoSolicitudBean(pagoSolicitudForm,defaultDatosPagoOut);
						pagoSolicitudBean.setIdLogServicio(result);
						pagoSolicitudesManager.guardarPagoSolicitud(pagoSolicitudBean);
						SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
						solicitudComunQuery.setIdSolicitud(Long.parseLong(pagoSolicitudBean.getIdSolicitud()));
						EstadoSolicitudQuery estadoSolicitudQuery = new EstadoSolicitudQuery();
						estadoSolicitudQuery.setId(Constantes.SOLICITUD_ID_NOREGISTRADO);

						long idActualizado = solicitudesManager.actualizarEstadoSolicitud(solicitudComunQuery, estadoSolicitudQuery);
						logger.info("1.ConsultaPagoAction-Estado actualizado: "+idActualizado);

					}
				}

			} catch (ServiceException e) {
				logger.error("Error ",e);
				resultado = "error";
			} catch (RemoteException re) {
				logger.error("Error pasarela",re);
				resultado = STRING_ERRORPASARELA;
				this.getRequest().setAttribute(STRING_ERRORPASARELA, RESOURCE_BUNDLE.getString("field.form.pagoSolicitud.error.tiempoConexion"));
				logger.info(pagoSolicitudForm.getIdSolicitud());
				this.getRequest().setAttribute("idSolicitud",pagoSolicitudForm.getIdSolicitud());
			}

		}else{
			this.registrar(pagoSolicitudForm);
		}

		DetallePasarelaBean detallePasarelaBean = rellenarDetalle(pagoSolicitudForm);		
		detallePasarelaBean.setFechaOperacion(fechaOperacion);
		this.setRequestAttribute("detallePasarela", detallePasarelaBean);
		return resultado;
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
	 * To pago solicitud bean.
	 *
	 * @param pagoSolicitudForm el pago solicitud form
	 * @param defaultDatosPagoOut el default datos pago out
	 * @return el pago solicitud bean
	 */
	private PagoSolicitudBean toPagoSolicitudBean(
			PagoSolicitudForm pagoSolicitudForm, DefaultDatosPagoOut defaultDatosPagoOut) {
		PagoSolicitudBean aux = new PagoSolicitudBean();
		aux.setIdSolicitud(pagoSolicitudForm.getIdSolicitud());
		Date fechaIntento = new Date();
		aux.setFechaIntento(fechaIntento);
		aux.setFormaPago(pagoSolicitudForm.getFormPago());

		if(pagoSolicitudForm.getFormaPago() != null){
			aux.setTipo(pagoSolicitudForm.getFormaPago().charAt(0));
		}

		if(pagoSolicitudForm.getFormaPago().equals(Constantes.PAGO_TIPO_ADEUDO_CHAR)){
			if(pagoSolicitudForm.getMotivoAdeudo() != null){
				aux.setMotivo(pagoSolicitudForm.getMotivoAdeudo());
				aux.setReduccionPago(Constantes.REDUCCION_PAGO_SI);
			}else{
				aux.setReduccionPago(Constantes.REDUCCION_PAGO_NO);
			}
			aux.setEntidad(pagoSolicitudForm.getBancoAdeudo());
		}else{
			if(pagoSolicitudForm.getFormaPago().equals(Constantes.PAGO_TIPO_TARJETA_CHAR)){
				if(pagoSolicitudForm.getMotivoTarjeta() != null){
					aux.setMotivo(pagoSolicitudForm.getMotivoAdeudo());
					aux.setReduccionPago(Constantes.REDUCCION_PAGO_SI);
				}else{
					aux.setReduccionPago(Constantes.REDUCCION_PAGO_NO);
				}
				aux.setEntidad(pagoSolicitudForm.getBancoTarjeta());
			}
		}

		aux.setImporte(pagoSolicitudForm.getImporte());
		aux.setResultado(Constantes.LOG_SERVICIO_RESULTADO_OK);
		aux.setNrc(defaultDatosPagoOut.getNRC());		

		return aux;
	}

	/**
	 * Rellenar detalle.
	 *
	 * @param solicitudForm el solicitud form
	 * @return el detalle pasarela bean
	 */
	private DetallePasarelaBean rellenarDetalle(PagoSolicitudForm solicitudForm) {

		DetallePasarelaBean aux = new DetallePasarelaBean();
		aux.setIdSolicitud(solicitudForm.getIdSolicitud());
		aux.setNumeroSolicitud(solicitudForm.getNumeroSolicitud());
		aux.setCodigoTasa("001");
		aux.setImporte(String.valueOf(solicitudForm.getImporte()));
		aux.setTipoDocumento(TiposDocumento.fromValue(1));
		aux.setNif(solicitudForm.getNif());
		aux.setNombre(solicitudForm.getNombre());
		aux.setApellido1(solicitudForm.getApellido1());
		aux.setApellido2(solicitudForm.getApellido2());
		aux.setFormaPago(solicitudForm.getFormPago());

		if(solicitudForm.getFormPago() != null && Constantes.FORMA_PAGO_TARJETA_S.equals(solicitudForm.getFormPago())){
			aux.setBancoTarjeta(solicitudForm.getBancoTarjeta());
			aux.setNumTarjeta2(solicitudForm.getNumTarjeta2());
			aux.setNumTarjeta3(solicitudForm.getNumTarjeta3());
			aux.setNumTarjeta4(solicitudForm.getNumTarjeta4());
			aux.setCaducidadTarjeta1(solicitudForm.getCaducidadTarjeta1());
			aux.setCaducidadTarjeta2(solicitudForm.getCaducidadTarjeta2());
			aux.setTipoCargo(TiposCargo.fromValue(0));
			aux.setCodBanco(solicitudForm.getEntidadTarjeta());
		}else{
			if(solicitudForm.getBancoAdeudo() != null && !"".equals(solicitudForm.getBancoAdeudo())){
				//Comprobar resultados
				aux.setBancoAdeudo(solicitudForm.getBancoAdeudo());
				aux.setOficina(solicitudForm.getIBAN().substring(8, 11));
				aux.setDc(solicitudForm.getIBAN().substring(12, 13));
				aux.setCuenta(solicitudForm.getIBAN().substring(14, 23));
				aux.setTipoCargo(TiposCargo.fromValue(0));
				aux.setCodBanco(solicitudForm.getEntidadAdeudo());
			}
		}
		
		aux.setOrigenFirma(solicitudForm.getOrigenFirma());
		aux.setSignature(solicitudForm.getSignature());
		aux.setSignerCert(solicitudForm.getSignerCert());

		return aux;
	}

	/**
	 * Registrar.
	 *
	 * @param pagoSolicitudForm el pago solicitud form
	 * @return el string
	 */
	private String registrar(PagoSolicitudForm pagoSolicitudForm){

		PagoSolicitudBean pagoSolicitudBean = toPagoSolicitudBean(pagoSolicitudForm);	
		String resultado=null;
		String idSolicitudAux = pagoSolicitudForm.getIdSolicitud();
		CiudadanoBean usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute("usuario");

		if(usuActual == null){
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.usuario"));
			return "errorUsuario";
		}else{
			if(usuActual.getNif() == null){
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.usuario"));
				return "errorUsuario";
			}
		}

		try{
			//Guardar en la tabla pago de solicitud
			pagoSolicitudesManager.guardarPagoSolicitud(pagoSolicitudBean);
			EstadoSolicitudQuery estadoSolicitudQuery = new EstadoSolicitudQuery();
			estadoSolicitudQuery.setId(Constantes.SOLICITUD_ID_NOREGISTRADO);
			SolicitudComunQuery solicitudQueryAux = new SolicitudComunQuery();
			solicitudQueryAux.setIdSolicitud(Long.parseLong(pagoSolicitudBean.getIdSolicitud()));				
			//Actualizar el estado de la tabla solicitud
			Long idSolicitud = solicitudesManager.actualizarEstadoSolicitud(solicitudQueryAux, estadoSolicitudQuery);
			logger.info("2.ConsultaPagoAction-Estado actualizado: "+idSolicitud);

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
			Integer idLog = tablaBaseManager.insertarLogSolicitud(logSolicitudBean);
			
			if(idLog == null){
				logger.error(RESOURCE_BUNDLE.getString("logSolicitud.error"));
			}
			
			this.setRequestAttribute("id",idSolicitudAux);
			this.setRequestAttribute("idConvocatoria",pagoSolicitudForm.getIdConvocatoria());
			resultado =  "verPago";
			
		}catch(Exception e){
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.alta.eror"));
			logger.error("Error alta solicitud",e);
			return "error";
		}
		
		return resultado;
	}

	/**
	 * To pago solicitud bean.
	 *
	 * @param pagoSolicitudForm el pago solicitud form
	 * @return el pago solicitud bean
	 */
	private PagoSolicitudBean toPagoSolicitudBean(
			PagoSolicitudForm pagoSolicitudForm) {

		PagoSolicitudBean aux = new PagoSolicitudBean();

		if(pagoSolicitudForm.getIdSolicitud() != null){
			aux.setIdSolicitud(String.valueOf(pagoSolicitudForm.getIdSolicitud()));
		}

		Date fechaAux = new Date();
		aux.setFechaIntento(fechaAux);

		if(pagoSolicitudForm.getFormPago() != null){
			aux.setTipo(pagoSolicitudForm.getFormPago().charAt(0));
		}

		if(pagoSolicitudForm.getImporteActual() != null){
			aux.setImporte(Float.valueOf(pagoSolicitudForm.getImporteActual()));
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

		if(pagoSolicitudForm.getNrc() != null){
			aux.setNrc(pagoSolicitudForm.getNrc());
		}

		return aux;
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
}
