package es.map.ipsc.spring.solicitudes;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.ConvocatoriaBean;
import es.map.ipsc.bean.DetallePagoSolicitudBean;
import es.map.ipsc.bean.RegistroSolicitudBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.form.RegistroSolicitudForm;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.spring.AbstractSecureSpring;

/**
 * El Class VerRegistroSolicitudSpring.
 */
public class VerRegistroSolicitudSpring extends AbstractSecureSpring {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerRegistroSolicitudSpring.class);
	
	/** La constante BUNDLE_ERROR. */
	private static final String BUNDLE_ERROR = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_ERROR);
	
	/** La constante BUNDLE_NAME. */
	private static final String BUNDLE_NAME = "file";
	
	/** La constante RESOURCE_BUNDLE_NAME. */
	private static final ResourceBundle RESOURCE_BUNDLE_NAME = ResourceBundle.getBundle(BUNDLE_NAME);
	
	/** el solicitudes manager. */
	private SolicitudManager solicitudesManager;
	
	/** el convocatoria manager. */
	private ConvocatoriasManager convocatoriaManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** La constante STRING_ERRORDESCRIPCION. */
	private static final String STRING_ERRORDESCRIPCION = "errorDescripcion";
	
	/** La constante STRING_ERRORUSUARIO. */
	private static final String STRING_ERRORUSUARIO = "errorUsuario";
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	
	/**
	 * Crea una nueva ver registro solicitud spring.
	 */
	public VerRegistroSolicitudSpring() {
		try{
			setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
			setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudesManager"));
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error ver registro solictud ",e);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		RegistroSolicitudForm solicitudForm = (RegistroSolicitudForm) form;
		String busqueda=null;
		String resultado=null;
		
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_solicitudes = RESOURCE_BUNDLE.getString("field.menu.listaSolic");
		this.getRequest().getSession().setAttribute("pagActiva", menu_solicitudes);
		//****************************************************************** 
		
		if(busqueda == null){
			resultado =  "show";
			String idSolicitudAux =(String) this.getRequest().getAttribute("id");
			if(idSolicitudAux == null){
				idSolicitudAux = this.getRequest().getParameter("id");
			}
			
			if(!StringUtils.isEmpty(idSolicitudAux)){
				
				long idSolicitud = Long.valueOf(idSolicitudAux);
				//Comprobar si el usuario esta en la sesion
				CiudadanoBean usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute("usuarioClave");
				if(usuActual == null){
					this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.usuario"));
					return STRING_ERRORUSUARIO;
				}else{
					if(usuActual.getNif() == null){
						this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.usuario"));
						return STRING_ERRORUSUARIO;
					}
				}
				//Recuperamos las posibles extensiones
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
				
				CiudadanoBean ciudadanoAux = new CiudadanoBean();
				
				ciudadanoAux.setNif(usuActual.getNif());
				SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
				solicitudComunQuery.setIdSolicitud(idSolicitud);
				PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
				pagoSolicitudQuery.setSolicitudComun(solicitudComunQuery);
				pagoSolicitudQuery.setResultado(Constantes.LOG_SERVICIO_RESULTADO_OK);
				SolicitudBean solicitudBean = solicitudesManager.buscarSolicitudById(solicitudComunQuery);
				if(solicitudBean != null)
				{
					solicitudForm.setConsentimiento(solicitudBean.getIdConsentimiento());
					if(solicitudForm.getConsentimiento() == true)
					{	
						solicitudForm.setDesConsentimiento(Constantes.SI);
					}
					else if(solicitudForm.getConsentimiento() == false)
					{
						solicitudForm.setDesConsentimiento(Constantes.NO);
					}	
					
				}
				
				ConvocatoriaBean convocatoriaBean = convocatoriaManager.recuperarConvocatoria(solicitudBean.getIdConvocatoria());
				DetallePagoSolicitudBean pagoSolicitudBean = pagoSolicitudManager.buscarPagoSolicitudIdSolicitu(pagoSolicitudQuery);
				if(solicitudBean != null && convocatoriaBean != null){
					if(convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_PUBLICADA)){
						//Comprobar el estado de la solicitud antes de registrar
						SolicitudComunQuery solicitudQueryAuxRegistrar = new SolicitudComunQuery();
						solicitudQueryAuxRegistrar.setIdSolicitud(solicitudBean.getId());
						SolicitudBean solicitudAuxRegistrarBean = solicitudesManager.buscarSolicitudById(solicitudQueryAuxRegistrar);
						if(!solicitudAuxRegistrarBean.getIdEstadoSolicitud().equals(Constantes.SOLICITUD_ID_NOREGISTRADO_STRING)){
							this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("field.registro.estadoSolicitudError"));
							return STRING_ERRORUSUARIO;
						}
						
						RegistroSolicitudBean registroSolicitudBean = new RegistroSolicitudBean();
						registroSolicitudBean.setNumJustificante(solicitudBean.getNumeroSolicitud());
						registroSolicitudBean.setIdConvocatoria(String.valueOf(solicitudBean.getIdConvocatoria()));
						registroSolicitudBean.setNif(solicitudBean.getNif());
						String nombreCompleto = solicitudBean.getNombre() + " " + solicitudBean.getPrimerApellido() +
							" " + solicitudBean.getSegundoApellido();
						registroSolicitudBean.setIdSolicitud(idSolicitudAux);
						if(nombreCompleto != null && !"".equals(nombreCompleto)){
							registroSolicitudBean.setNombre(nombreCompleto.toUpperCase());
						}else{
							registroSolicitudBean.setNombre(nombreCompleto);
						}
						
						
						if(pagoSolicitudBean.getImporte()!=null){
							registroSolicitudBean.setImporte(String.valueOf(pagoSolicitudBean.getImporte()));
						}
						
						
						registroSolicitudBean.setEjercicio(convocatoriaBean.getEjercicio());
						registroSolicitudBean.setNrc(pagoSolicitudBean.getNrc());
						
						if(pagoSolicitudBean.getFechaIntento() != null){
							registroSolicitudBean.setFechaPago(pagoSolicitudBean.getFechaIntento());
						}
						if(pagoSolicitudBean.getTipo() != null){
							registroSolicitudBean.setFormaPago(pagoSolicitudBean.getTipo());
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
									if(pagoSolicitudBean.getMotivoReduccion() != null || solicitudBean.getReservaDiscapacidad() == 'S'){
										registroSolicitudBean.setMostrarTabla("true");
									}else{
										registroSolicitudBean.setMostrarTabla("false");
									}
								}else{
									if(Constantes.TIPO_DOCUMENTO_CONVOCATORIA_REDUCCION.equals(aniadirDocumento)){
										if(pagoSolicitudBean.getMotivoReduccion() != null && !"".equals(pagoSolicitudBean.getMotivoReduccion()) || solicitudBean.getReservaDiscapacidad() == 'S'){
											registroSolicitudBean.setAniadirDocumentos(Constantes.SI);
										}else{
											registroSolicitudBean.setAniadirDocumentos(Constantes.NO);
										}
									}
								}
							}
						}
						
						
						
						solicitudForm.setIdSolicitud(idSolicitudAux);
						if(pagoSolicitudBean.getDesMotivoReduccion() != null && !"".equals(pagoSolicitudBean.getDesMotivoReduccion())){
							this.setRequestAttribute("motivoReduccion", pagoSolicitudBean.getDesMotivoReduccion());
						}
						if(solicitudBean.getReservaDiscapacidad() == Constantes.PLANTILLA_SI){
							this.setRequestAttribute("reservaDiscapacidad", solicitudBean.getReservaDiscapacidad());
						}
						this.setRequestAttribute("id", String.valueOf(idSolicitud) );
						
						//Cargar Datos Solicitud para el HTML
						SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						if(solicitudBean.getFechaNacimiento() != null){
							try{
								registroSolicitudBean.setFechaNacimiento(df.format(solicitudBean.getFechaNacimiento()));
							}catch(Exception e){
								logger.error("No se ha podido parsear la fecha de nacimiento"+ solicitudBean.getFechaNacimiento( ),e);
								registroSolicitudBean.setFechaNacimiento(null);
							}
						}
						registroSolicitudBean.setNacionalidad(solicitudBean.getNacionalidad());
						registroSolicitudBean.setTelefono1(solicitudBean.getTelefono());
						if(solicitudBean.getTelefonoAux() == null){
							registroSolicitudBean.setTelefono(solicitudBean.getTelefono());
						}else{
							registroSolicitudBean.setTelefono(solicitudBean.getTelefono() + "/" + solicitudBean.getTelefonoAux());
							registroSolicitudBean.setTelefonoAux(solicitudBean.getTelefonoAux());
							
						}	
						registroSolicitudBean.setIdSolicitud(idSolicitudAux);
						registroSolicitudBean.setEmail(solicitudBean.getEmail());
						registroSolicitudBean.setCalle(solicitudBean.getCalleDomicilio());
						registroSolicitudBean.setCodPostal(solicitudBean.getCodigoPostalDomicilio());
						registroSolicitudBean.setMunicipioDomicilio(solicitudBean.getMunicipioDomicilio());
						registroSolicitudBean.setProvinciaDomicilio(solicitudBean.getProvinciaDomicilioDes());
						registroSolicitudBean.setPaisDomicilio(solicitudBean.getPaisDomicilioDes());
						registroSolicitudBean.setTipoDiscapacidad(solicitudBean.getTipoDiscapacidadDes());
						registroSolicitudBean.setReservaDiscapacidad(String.valueOf(solicitudBean.getReservaDiscapacidad()));
						registroSolicitudBean.setPorcentajeDiscapacidad(String.valueOf(solicitudBean.getPorcentajeDiscapacidad()));
						registroSolicitudBean.setDetalleDiscapacidad(solicitudBean.getDetalleDiscapacidad());
						registroSolicitudBean.setDatosA(solicitudBean.getDatosA());
						registroSolicitudBean.setDatosB(solicitudBean.getDatosB());
						registroSolicitudBean.setDatosC(solicitudBean.getDatosC());
						registroSolicitudBean.setFechaSolicitud(String.valueOf(solicitudBean.getFechaSolicitud()));
						registroSolicitudBean.setTipoPago(pagoSolicitudBean.getTipo());
						registroSolicitudBean.setSolicitaReduccion(pagoSolicitudBean.getReduccion());
						registroSolicitudBean.setMotivoReduccionDes(pagoSolicitudBean.getDesMotivoReduccion());
						registroSolicitudBean.setEntidadFinanciera(pagoSolicitudBean.getDesEntidad());
						registroSolicitudBean.setOtrosTitulos(solicitudBean.getOtrosTitulos());
						registroSolicitudBean.setDesEspecialidad(solicitudBean.getDesEspecialidad());
						registroSolicitudBean.setDesProvinciaExamen(solicitudBean.getDesProvinciaExamen());
						registroSolicitudBean.setDesTituloOficial(solicitudBean.getDesTituloOficial());
						registroSolicitudBean.setConsentimiento(solicitudBean.getIdConsentimiento());
						if(registroSolicitudBean.getConsentimiento() == true)
						{	
							registroSolicitudBean.setDesConsentimiento(Constantes.SI);
						}
						else if(registroSolicitudBean.getConsentimiento() == false)
						{	
							registroSolicitudBean.setDesConsentimiento(Constantes.NO);
						}
						this.setRequestAttribute("numDocsAdjuntos", Constantes.NUM_MAX_DOCUMENTOS);
						
						//Comprobar si tiene un justificante de pago
						String tipoPago = pagoSolicitudBean.getTipo();
						if(tipoPago != null && !tipoPago.equals(Constantes.PAGO_TIPO_EXENTO)){
							
							this.setRequestAttribute("certPago", true);
							
							
						}
						
						//////////////////////////////////////
						this.setRequestAttribute("registroSolicitud", registroSolicitudBean);
						
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
				
			}else{
				resultado = STRING_ERROR;
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("field.registro.error.codSolicitud"));
			}

		}
		form = solicitudForm;
		return resultado;
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

}
