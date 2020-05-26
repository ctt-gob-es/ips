package es.map.ipsc.spring.solicitudes;

import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.MotivoReduccionTasa;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.ConvocatoriaBean;
import es.map.ipsc.bean.DetallePagoSolicitudBean;
import es.map.ipsc.bean.DetalleSolicitudBean;
import es.map.ipsc.bean.EntidadBean;
import es.map.ipsc.bean.MotivoReduccionBean;
import es.map.ipsc.bean.PagoSolicitudBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.form.DetalleSolicitudForm;
import es.map.ipsc.form.PagoSolicitudForm;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.entidadFinanciera.EntidadFinancieraManager;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.tasas.MotivoReduccionManager;
import es.map.ipsc.spring.AbstractSecureSpring;

/**
 * El Class VerPagoSolicitudSpring.
 */
public class VerPagoSolicitudSpring extends AbstractSecureSpring {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerPagoSolicitudSpring.class);
	
	/** La constante BUNDLE_ERROR. */
	private static final String BUNDLE_ERROR = "MessageResources";	
	
	/** La constante BUNDLE_MESSAGES. */
	private static final String BUNDLE_MESSAGES = "MessageResources";
	
	/** La constante STRING_REGISTRO. */
	private static final String STRING_REGISTRO = "registro";
	
	/** La constante STRING_ERROR_DESCRIPCION. */
	private static final String STRING_ERROR_DESCRIPCION = "errorDescripcion";
	
	/** La constante STRING_ERROR_USUARIO. */
	private static final String STRING_ERROR_USUARIO = "errorUsuario";
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_ERROR);
	
	/** La constante RESOURCE_MESSAGES. */
	private static final ResourceBundle RESOURCE_MESSAGES = ResourceBundle.getBundle(BUNDLE_MESSAGES);
	
	/** el solicitudes manager. */
	private SolicitudManager solicitudesManager;
	
	/** el convocatoria manager. */
	private ConvocatoriasManager convocatoriaManager;
	
	/** el motivo reduccion manager. */
	private MotivoReduccionManager motivoReduccionManager;
	
	/** el entidad financiera manager. */
	private EntidadFinancieraManager entidadFinancieraManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el properties. */
	private static Properties properties;
	
	
	/**
	 * Crea una nueva ver pago solicitud spring.
	 */
	public VerPagoSolicitudSpring() {
		try{
			setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
			setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setMotivoReduccionManager((MotivoReduccionManager) getBean("motivoReduccionManager"));
			setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
			setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudesManager"));
			properties = (Properties) getBean("propertiesBean");
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error ver pago solicitud ",e);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		DetalleSolicitudForm formulario;
		formulario = (DetalleSolicitudForm) form;
		String siglasCentroGestor = null;
		
		//Obtengo las siglas del Centro Gestor 
		String siglasCentroGestorJusticia = "";		
	    siglasCentroGestor = (String)this.getRequest().getParameter("siglas");
	     
		
		if(siglasCentroGestor != null){
			
			this.getRequest().getSession().setAttribute("sCG", siglasCentroGestor); 
			this.getRequest().getSession().setAttribute("sCGJ", siglasCentroGestorJusticia); 
			
		}
		
		
		
		
		String prueba = formulario.getRegistro();
		String codSolicitud = (String) this.getRequest().getParameter(STRING_REGISTRO);
		logger.info("CodSolicitud: "+codSolicitud);
		String auxCodSolicitud = (String) this.getRequest().getAttribute(STRING_REGISTRO);
		logger.info("AuxSolicitud: "+auxCodSolicitud);
		if(auxCodSolicitud != null){
			codSolicitud = auxCodSolicitud;
		}
		logger.info("CodSolicitud: "+codSolicitud);
		//Comprueba que el usuario este en sesion
		CiudadanoBean usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute("usuarioClave");
		if(usuActual == null){
			this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_MESSAGES.getString("solicitud.error.usuario"));
			return STRING_ERROR_USUARIO;
		}else{
			if(usuActual.getNif() == null){
				this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_MESSAGES.getString("solicitud.error.usuario"));
				return STRING_ERROR_USUARIO;
			}
		}
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.setIdSolicitud(Long.parseLong(codSolicitud));

		//Busca los datos de la solicitud
		PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
		pagoSolicitudQuery.setSolicitudComun(solicitudQuery);
		pagoSolicitudQuery.setResultado(Constantes.RESULTADO_PAGO_OK);
		
		DetalleSolicitudBean solicitudes= solicitudesManager.detalleSolicitud(solicitudQuery);		
		if(solicitudes != null ){
			if(solicitudes.getNif().equals(usuActual.getNif())){
				DetallePagoSolicitudBean detallePagoSolicitudBean = pagoSolicitudManager.buscarPagoSolicitudIdSolicitu(pagoSolicitudQuery);
				if(detallePagoSolicitudBean!=null){
					solicitudes.setImporte(Float.parseFloat(detallePagoSolicitudBean.getImporte()));
				}
				setRequestAttribute("solicitud", solicitudes);
				setRequestAttribute(STRING_REGISTRO, codSolicitud);
			}else{
				this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_MESSAGES.getString("field.solicitud.pagoSolicitud.usuarioError"));
				return STRING_ERROR_USUARIO;
			}
		}else{
			this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_MESSAGES.getString("field.solicitud.errorCodSolicitud"));
			return STRING_ERROR_USUARIO;
		}
		
		//VER PAGO
		PagoSolicitudForm solicitudForm = (PagoSolicitudForm) form;
		String busqueda = solicitudForm.getSubmit();
				
		String resultado = null;
		if(busqueda == null){
			resultado =  "show";
			String idSolicitudAux =(String) this.getRequest().getAttribute("id");
			if(idSolicitudAux == null){
				idSolicitudAux = this.getRequest().getParameter("id");
				if(idSolicitudAux == null){
					idSolicitudAux = solicitudForm.getIdSolicitud();
				}
			}
			if(!StringUtils.isEmpty(idSolicitudAux)){
				long idSolicitud = Long.valueOf(idSolicitudAux);
				//Comprobar si el usuario esta en la sesion
				
				CiudadanoBean ciudadanoAux = new CiudadanoBean();
				
				ciudadanoAux.setNif(usuActual.getNif());
				
				solicitudQuery = new SolicitudComunQuery();
				solicitudQuery.setIdSolicitud(idSolicitud);
				SolicitudBean solicitudBean = solicitudesManager.buscarSolicitudById(solicitudQuery);
					
				ConvocatoriaBean convocatoriaBean = convocatoriaManager.recuperarConvocatoria(solicitudBean.getIdConvocatoria());
				
				if(solicitudBean != null && convocatoriaBean != null){
					if(convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_PUBLICADA)){
						PagoSolicitudBean pagoSolicitudBean = new PagoSolicitudBean();
						pagoSolicitudBean.setNumeroSolicitud(solicitudBean.getNumeroSolicitud());
						pagoSolicitudBean.setNif(solicitudBean.getNif());
						String nombreCompleto = solicitudBean.getNombre() + " " + solicitudBean.getPrimerApellido() +
							" " + solicitudBean.getSegundoApellido();
						pagoSolicitudBean.setIdSolicitud(idSolicitudAux);
						pagoSolicitudBean.setNombre(nombreCompleto);
						pagoSolicitudBean.setNombreParcial(solicitudBean.getNombre());
						pagoSolicitudBean.setApellido1(solicitudBean.getPrimerApellido());
						pagoSolicitudBean.setApellido2(solicitudBean.getSegundoApellido());
						
						pagoSolicitudBean.setIdConvocatoria(String.valueOf(convocatoriaBean.getId()));
						pagoSolicitudBean.setImporte(convocatoriaBean.getImporte());
						ArrayList<MotivoReduccionBean> motivoReduccionBean = motivoReduccionManager.buscarMotivoReduccionCombo();
						ArrayList<EntidadBean> entidadBean = entidadFinancieraManager.buscarEntidadAdeudoCombo();
						ArrayList<EntidadBean> entidadBeanTarjeta = entidadFinancieraManager.buscarEntidadTarjetaCombo();
						
						solicitudForm.setIdSolicitud(idSolicitudAux);
						solicitudForm.setNumeroSolicitud(solicitudBean.getNumeroSolicitud());
						this.setRequestAttribute("id", String.valueOf(idSolicitud));
						this.setRequestAttribute("entidades", entidadBean);
						this.setRequestAttribute("entidadesTarjetas", entidadBeanTarjeta);
						if(convocatoriaBean.getMotivoReduccionTasasIncompleto() != null){
							this.setRequestAttribute("motivos", convocatoriaBean.getMotivoReduccionTasasIncompleto());
						}else{
							ArrayList<MotivoReduccionTasa> arrayTasas = new ArrayList<MotivoReduccionTasa>();
							this.setRequestAttribute("motivos", arrayTasas);
						}
						if(convocatoriaBean.getMotivoReduccionTasasCompleto() != null){
							this.setRequestAttribute("motivosCompletos", convocatoriaBean.getMotivoReduccionTasasCompleto());
						}else{
							ArrayList<MotivoReduccionTasa> arrayTasas = new ArrayList<MotivoReduccionTasa>();
							this.setRequestAttribute("motivosCompletos", arrayTasas);
							
						}
						this.setRequestAttribute("pagoSolicitud", pagoSolicitudBean);
						if(solicitudForm.getFormPago() != null && !"".equals(solicitudForm.getFormPago())){
							this.setRequestAttribute("formPago", solicitudForm.getFormPago());
						}else{
							this.setRequestAttribute("formPago", null);
						}
						this.setRequestAttribute("importeActual", pagoSolicitudBean.getImporte());
						this.setRequestAttribute("importeOriginal", pagoSolicitudBean.getImporte());
						solicitudForm.setImporteActual(pagoSolicitudBean.getImporte());
						logger.info("Importe: " +pagoSolicitudBean.getImporte());
						
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
				
				
			}else{
				resultado = STRING_ERROR;
				this.getRequest().setAttribute(STRING_ERROR_DESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.pago.solicitudNull"));
				logger.error(RESOURCE_BUNDLE.getString("solicitud.error.pago.solicitudNull"));
			}	
		}
		
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
