package es.map.ipsc.spring.solicitudes;

import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.DocumentoQuery;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.DetallePagoSolicitudBean;
import es.map.ipsc.bean.DocumentoBean;
import es.map.ipsc.bean.LogSolicitudBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManager;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.tablaBase.TablaBaseManager;
import es.map.ipsc.spring.AbstractSecureSpring;

/**
 * El Class ActualizarEstadoSolicitudesSpring.
 */
public class ActualizarEstadoSolicitudesSpring extends AbstractSecureSpring {

	/** el solicitudes manager. */
	private SolicitudManager solicitudesManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ActualizarEstadoSolicitudesSpring.class);
	
	/** La constante BUNDLE_MESSAGE. */
	private static final String BUNDLE_MESSAGE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(BUNDLE_MESSAGE);
	
	/** La constante STRING_ERRORUSUARIO. */
	private static final String STRING_ERRORUSUARIO = "errorUsuario";
	
	/** La constante STRING_SUCCESS. */
	private static final String STRING_SUCCESS = "success";
	
	/** el tabla base manager. */
	private TablaBaseManager tablaBaseManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el documento convocatorias manager. */
	private DocumentosConvocatoriaManager documentoConvocatoriasManager;
	
	/**
	 * Crea una nueva actualizar estado solicitudes spring.
	 */
	public ActualizarEstadoSolicitudesSpring() {
		try {
			setSolicitudesManager((SolicitudManager) getBean ("solicitudesManager"));
			setTablaBaseManager((TablaBaseManager) getBean("tablasBaseManager"));
			setPagoSolicitudManager((PagoSolicitudManager) getBean("pagoSolicitudesManager"));
			setDocumentoConvocatoriasManager((DocumentosConvocatoriaManager) getBean("documentoConvocatoriasManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error actualizar estado solictudes",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String idSolicitud = (String) this.getRequest().getParameter("registro");
		logger.info(idSolicitud);
		CiudadanoBean usuActual=null;

		// Cogemos la sesión de cl@ve
		 usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute("usuarioClave");
			if(usuActual == null){
				this.getRequest().setAttribute(STRING_ERRORUSUARIO,RESOURCE_MESSAGE.getString("solicitud.error.usuario"));
				return STRING_SUCCESS;
			}else{
				if(usuActual.getNif() == null){
					this.getRequest().setAttribute(STRING_ERRORUSUARIO,RESOURCE_MESSAGE.getString("solicitud.error.usuario"));
					return STRING_SUCCESS;
				}
			}
		
		
		SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
		EstadoSolicitudQuery estadoSolicitudQuery = new EstadoSolicitudQuery();
		estadoSolicitudQuery.setId(Constantes.SOLICITUD_ID_ELIMINADO);
		solicitudComunQuery.setIdSolicitud(Long.parseLong(idSolicitud));
		SolicitudBean solicitudBean = solicitudesManager.buscarSolicitudById(solicitudComunQuery);
		
		if(solicitudBean != null){
			Long id ;
			if(!solicitudBean.getNif().equals(usuActual.getNif()) && !usuActual.getTipoPersona().equals(Constantes.TIPO_PERSONA_FUNCIONARIO_HABILITADO)  ){
				this.getRequest().setAttribute("errorDescripcion",RESOURCE_MESSAGE.getString("field.solicitud.pagoSolicitud.usuarioError"));
				return STRING_ERRORUSUARIO;
			}else{
				if(solicitudBean.getIdEstadoSolicitud().equals(Constantes.SOLICITUD_ID_NOPAGADO_STRING)){
					id =solicitudesManager.actualizarEstadoSolicitud(solicitudComunQuery,estadoSolicitudQuery);
					logger.info("1.ActualizarEstadoSolicitudesAction-Estado actualizado: "+id);
					
					// Borrar documentos asociados a la solicitud eliminada
					borrarDocumentosSolicitud(idSolicitud);
				}else{
					DetallePagoSolicitudBean detallepagoSolicitudBean;
					PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
					pagoSolicitudQuery.setSolicitudComun(solicitudComunQuery);
					pagoSolicitudQuery.setResultado(Constantes.RESULTADO_OK);
					detallepagoSolicitudBean = pagoSolicitudManager.buscarPagoSolicitudIdSolicitu(pagoSolicitudQuery);
					if(detallepagoSolicitudBean != null){
						if(solicitudBean.getIdEstadoSolicitud().equals(Constantes.SOLICITUD_ID_NOREGISTRADO_STRING) && detallepagoSolicitudBean.getTipo().equals(Constantes.PAGO_TIPO_EXENTO.toUpperCase())){
							id =solicitudesManager.actualizarEstadoSolicitud(solicitudComunQuery,estadoSolicitudQuery);
							logger.info("2.ActualizarEstadoSolicitudesAction-Estado actualizado: "+id);
							
							// Borrar documentos asociados a la solicitud eliminada
							borrarDocumentosSolicitud(idSolicitud);
						}else{
							SpringMessages messages = new SpringMessages();
							messages.add("dsErrorEstadoSolicitud", new SpringMessage("field.solicitud.actualizarEstadoError"));
							saveErrors(this.getRequest(), messages);
							return STRING_SUCCESS;						
						}
					}else{
						SpringMessages messages = new SpringMessages();
						messages.add("dsErrorEstadoSolicitud", new SpringMessage("field.solicitud.actualizarEstadoError"));
						saveErrors(this.getRequest(), messages);
						return STRING_SUCCESS;	
					}
					
				}
				
				//Guardar el log de la actualizacion
				LogSolicitudBean logSolicitudBean = new LogSolicitudBean();
				logSolicitudBean.setActor(usuActual.getNif());
				Date fechaAux =new Date();
				logSolicitudBean.setFecha(fechaAux);
				logSolicitudBean.setTipoOperacion(Constantes.LOG_SOLICITUD_BAJA);
				logSolicitudBean.setDetalleOperacion(RESOURCE_MESSAGE.getString("logSolicitud.detalleEliminar"));
				logSolicitudBean.setIdSolicitud(idSolicitud);
					logSolicitudBean.setIdEstadoActual(Constantes.LOG_SOLICITUD_ESTADO_ELIMINADO);
					logSolicitudBean.setIdEstadoAnterior(solicitudBean.getIdEstadoSolicitud());
					Integer idLog = tablaBaseManager.insertarLogSolicitud(logSolicitudBean);
					if(idLog == null){
						logger.error(RESOURCE_MESSAGE.getString("logSolicitud.error"));
					}
				if(id == 1){
					return STRING_SUCCESS;
				}
			}
		}else{
			this.getRequest().setAttribute("errorDescripcion",RESOURCE_MESSAGE.getString("field.solicitud.errorCodSolicitud"));
			return STRING_ERRORUSUARIO;
		}
		return "nosuccess";
	}

	/**
	 * Metodo que borra los documentos asociados a una solicitud eliminada.
	 *
	 * @param idSolicitud el id solicitud
	 */
	private void borrarDocumentosSolicitud(String idSolicitud) {
		
		DocumentoQuery documentosQuery = new DocumentoQuery();
		SolicitudComunQuery solicitudComun = new SolicitudComunQuery();
		solicitudComun.setIdSolicitud(Long.parseLong(idSolicitud));
		documentosQuery.setSolicitudComun(solicitudComun);
		ArrayList<DocumentoBean> listaDocumentos;
		listaDocumentos = documentoConvocatoriasManager.buscarDocumentosConvocatoria(documentosQuery);
		
		if(!listaDocumentos.isEmpty()){
			for (DocumentoBean documentoBean : listaDocumentos) {
				try {
					documentoConvocatoriasManager.borrarDocumento(documentoBean);
				} catch (Exception e) {
					logger.error("El documento no ha podido ser eliminado: "+documentoBean.getNombreAlfresco(),e);
				
				}
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
	 * @param pSolicitudManager el nuevo solicitudes manager
	 */
	public void setSolicitudesManager(SolicitudManager pSolicitudManager) {
		this.solicitudesManager = pSolicitudManager;
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
