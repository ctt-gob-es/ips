package es.map.ipsc.spring.solicitudes;

import java.util.ArrayList;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import ePago.gob.es.schemas.TiposCargo;
import ePago.gob.es.schemas.TiposDocumento;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.MotivoReduccionTasa;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.ConvocatoriaBean;
import es.map.ipsc.bean.DetallePasarelaBean;
import es.map.ipsc.bean.EntidadBean;
import es.map.ipsc.bean.MotivoReduccionBean;
import es.map.ipsc.bean.PagoSolicitudBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.form.PagoSolicitudForm;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.entidadFinanciera.EntidadFinancieraManager;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.tasas.MotivoReduccionManager;
import es.map.ipsc.spring.AbstractSecureSpring;

/**
 * El Class VerPasarelaPagoSpring.
 */
public class VerPasarelaPagoSpring extends AbstractSecureSpring {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerPasarelaPagoSpring.class);
	
	/** La constante BUNDLE_ERROR. */
	private static final String BUNDLE_ERROR = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_ERROR);
	
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
	
	/** La constante STRING_ERRORDESCRIPCION. */
	private static final String STRING_ERRORDESCRIPCION = "errorDescripcion";
	
	
	/**
	 * Crea una nueva ver pasarela pago spring.
	 */
	public VerPasarelaPagoSpring() {
		try{
			setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
			setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
			setMotivoReduccionManager((MotivoReduccionManager) getBean("motivoReduccionManager"));
			setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
			setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudesManager"));
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error ver pasarela pago ",e);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		PagoSolicitudForm solicitudForm = (PagoSolicitudForm) form;
		String busqueda = solicitudForm.getSubmit();
		String resultado = null;
		if(busqueda == null){
			resultado =  "show";
			String idSolicitudAux =(String) this.getRequest().getAttribute("id");
			if(idSolicitudAux == null){
				idSolicitudAux = solicitudForm.getIdSolicitud();
			}
			if(!StringUtils.isEmpty(idSolicitudAux)){
				long idSolicitud = Long.valueOf(idSolicitudAux);
				//Comprobar si el usuario esta en la sesion
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
				CiudadanoBean ciudadanoAux = new CiudadanoBean();
				
				ciudadanoAux.setNif(usuActual.getNif());
				
				SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
				solicitudComunQuery.setIdSolicitud(idSolicitud);
				SolicitudBean solicitudBean = solicitudesManager.buscarSolicitudById(solicitudComunQuery);
				ConvocatoriaBean convocatoriaBean = convocatoriaManager.recuperarConvocatoria(solicitudBean.getIdConvocatoria());
				
				if(solicitudBean != null && convocatoriaBean != null){
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
					if(convocatoriaBean.getMotivoReduccionTasas() != null){
						this.setRequestAttribute("motivos", convocatoriaBean.getMotivoReduccionTasas());
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
					//PERSONALIZAR ERROR
					this.setRequestAttribute("idSolicitud", idSolicitudAux);
					resultado="Error";
				}
				
				
			}else{
				resultado = "error";
				this.setRequestAttribute("idSolicitud", idSolicitudAux);
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION,RESOURCE_BUNDLE.getString("solicitud.error.pago.solicitudNull"));
				logger.error(RESOURCE_BUNDLE.getString("solicitud.error.pago.solicitudNull"));
			}
			DetallePasarelaBean detallePasarelaBean = rellenarDetalle(solicitudForm);
			this.setRequestAttribute("detallePasarela", detallePasarelaBean);
		}
		
		return resultado;
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
		aux.setCodigoTasa("");
		aux.setImporte(String.valueOf(solicitudForm.getImporteActual()));
		aux.setTipoDocumento(TiposDocumento.fromValue(1));
		aux.setNif(solicitudForm.getNif());
		aux.setNombre(solicitudForm.getNombreParcial());
		aux.setApellido1(solicitudForm.getApellido1());
		aux.setApellido2(solicitudForm.getApellido2());
		aux.setFormaPago(solicitudForm.getFormPago());
		
		if(solicitudForm.getFormPago() != null && Constantes.FORMA_PAGO_TARJETA_S.equals(solicitudForm.getFormPago())){
			aux.setBancoTarjeta(solicitudForm.getBancoTarjeta());
			
			String numTarjeta2 = "";
			String numTarjeta3 = "";
			String numTarjeta4 = "";
			
			for(int i = 0; i<16; i++) {
				if(i<4) {
					numTarjeta2 += aux.getNumTarjeta2().charAt(i);
				}else if(i<8) {
					numTarjeta3 += aux.getNumTarjeta3().charAt(i);
				}else if(i<12) {
					numTarjeta4 += aux.getNumTarjeta4().charAt(i);
				}
			}
			solicitudForm.setNumTarjeta2(numTarjeta2);
			solicitudForm.setNumTarjeta3(numTarjeta3);
			solicitudForm.setNumTarjeta4(numTarjeta4);
			
			aux.setNumTarjeta2(solicitudForm.getNumTarjeta2());
			aux.setNumTarjeta3(solicitudForm.getNumTarjeta3());
			aux.setNumTarjeta4(solicitudForm.getNumTarjeta4());
			
			
			
			
			aux.setCaducidadTarjeta1(solicitudForm.getCaducidadTarjeta1());
			aux.setCaducidadTarjeta2(solicitudForm.getCaducidadTarjeta2());
			aux.setTipoCargo(TiposCargo.fromValue(1));
			aux.setCodBanco(solicitudForm.getEntidadTarjeta());
			
		}else{
			if(solicitudForm.getBancoAdeudo() != null && !"".equals(solicitudForm.getBancoAdeudo())){
				aux.setBancoAdeudo(solicitudForm.getBancoAdeudo());
				String oficina = "";
				String dc = "";
				String cuenta ="";
				for(int i=8; i<20; i++) {
					if(i<12) {
						oficina += solicitudForm.getIBAN().charAt(i);
					}else if(i<14) {
						dc += solicitudForm.getIBAN().charAt(i);
					}else {
						cuenta += solicitudForm.getIBAN().charAt(i);
					}
				}
				aux.setOficina(oficina);
				aux.setDc(dc);
				aux.setCuenta(cuenta);
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
