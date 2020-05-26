package es.map.ipsg.quartz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.model.SolicitudComunAuxiliarQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsg.bean.ParametrosConfiguracionBean;
import es.map.ipsg.manager.DocumentoManager;
import es.map.ipsg.manager.LogIntermediacionManager;
import es.map.ipsg.manager.LogSolicitudManager;
import es.map.ipsg.manager.PagoSolicitudManager;
import es.map.ipsg.manager.ParametroConfiguracionManager;
import es.map.ipsg.manager.RegistroAuditoriaManager;
import es.map.ipsg.manager.RegistroSolicitudManager;
import es.map.ipsg.manager.SolicitudCcaaAuxiliarManager;
import es.map.ipsg.manager.SolicitudCcaaManager;
import es.map.ipsg.manager.SolicitudComunAuxiliarManager;
import es.map.ipsg.manager.SolicitudCorreoManager;
import es.map.ipsg.manager.SolicitudPropioAuxiliarManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.SolicitudesPropiosManager;
import es.map.ipsg.util.Constantes;

/**
 * Clase encargada de eliminar las solicitudes auxiliares y sus datos asociados con la antiguedad
 * determinada en BBDD (parametros configuracion).
 *
 * @author everis
 */
public class MantenimientoSolicitudesAction {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(MantenimientoSolicitudesAction.class);
	
	/** el solicitudes manager. */
	private SolicitudesManager solicitudesManager;
	
	/** el solicitudes propios manager. */
	private SolicitudesPropiosManager solicitudesPropiosManager;
	
	/** el solicitud ccaa manager. */
	private SolicitudCcaaManager solicitudCcaaManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el documento manager. */
	private DocumentoManager documentoManager;
	
	/** el log intermediacion manager. */
	private LogIntermediacionManager logIntermediacionManager;
	
	/** el log solicitud manager. */
	private LogSolicitudManager logSolicitudManager;
	
	/** el registro auditoria manager. */
	private RegistroAuditoriaManager registroAuditoriaManager;
	
	/** el registro solicitud manager. */
	private RegistroSolicitudManager registroSolicitudManager;	
	
	/** el solicitud correo manager. */
	private SolicitudCorreoManager solicitudCorreoManager;

	/** el properties. */
	private static Properties properties;
	
	/**
	 * Execute.
	 */
	public void execute(){
		
		logger.info("Batch Mantenimiento Solicitudes Comun - inicio");

		try {
			try {
				solicitudesManager = (SolicitudesManager) ApplicationContextProvider.getInstance().getBean("solicitudesManager");
				solicitudCcaaManager = (SolicitudCcaaManager) ApplicationContextProvider.getInstance().getBean("solicitudCcaaManager");
				solicitudesPropiosManager = (SolicitudesPropiosManager) ApplicationContextProvider.getInstance().getBean("solicitudesPropiosManager");
				pagoSolicitudManager = (PagoSolicitudManager) ApplicationContextProvider.getInstance().getBean("pagoSolicitudManager");
				documentoManager = (DocumentoManager) ApplicationContextProvider.getInstance().getBean("documentoManager");
				logIntermediacionManager = (LogIntermediacionManager) ApplicationContextProvider.getInstance().getBean("logIntermediacionManager");
				logSolicitudManager = (LogSolicitudManager) ApplicationContextProvider.getInstance().getBean("logSolicitudManager");
				registroSolicitudManager = (RegistroSolicitudManager) ApplicationContextProvider.getInstance().getBean("registroSolicitudManager");
				registroAuditoriaManager = (RegistroAuditoriaManager) ApplicationContextProvider.getInstance().getBean("registroAuditoriaManager");
				solicitudCorreoManager = (SolicitudCorreoManager) ApplicationContextProvider.getInstance().getBean("solicitudCorreoManager");
				properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
			} catch (Exception e) {
				logger.info("Contexto cargado por ejecucion de Test.");
			}
			// Obtenemos el numero de meses a partir de los cuales se han de eliminar las solicitudes auxiliares
			String tiempoEstanciaSolicitudes= properties.getProperty("tiempoEstanciaSolicitudesComun"); 
			
			logger.info("Numero de meses atras: "+ tiempoEstanciaSolicitudes);
			
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.MONTH,-(Integer.valueOf(tiempoEstanciaSolicitudes)));
			Date fechaSolicitudMax = c.getTime();
			
			logger.info("fechaSolicitudMax: "+ fechaSolicitudMax);
			
			SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
			solicitudComunQuery.setFechaSolicitudMax(fechaSolicitudMax);
			ArrayList<Long> idSolicitudes = solicitudesManager.buscarSolicitudComunByFechaSolicitud(solicitudComunQuery);
			
			if(idSolicitudes !=null){				
				logger.info("Numero de solicitudes a eliminar: "+idSolicitudes.size());				
				for (int i = 0; i < idSolicitudes.size(); i++) {
					logger.info("Comienza los borrados para el idSolicitud="+ idSolicitudes.get(i));
					
					//DOCUMENTO
					documentoManager.borrarSolicitudDocumento(idSolicitudes.get(i));
					logger.info("Se ha borrado de DOCUMENTO");
					
					//LOG_INTERMEDIACION
					logIntermediacionManager.borrarSolicitudLogIntermediacion(idSolicitudes.get(i));
					logger.info("Se ha borrado de LOG_INTERMEDIACION");
					
					//LOG_SOLICITUD
					logSolicitudManager.borrarSolicitudLog(idSolicitudes.get(i));
					logger.info("Se ha borrado de LOG_SOLICITUD");
					
					//PAGO_SOLICITUD
					pagoSolicitudManager.borrarSolicitudPago(idSolicitudes.get(i));
					logger.info("Se ha borrado de PAGO_SOLICITUD");
					
					//REGISTRO_AUDITORIA
					registroAuditoriaManager.borrarSolicitudAuditoria(idSolicitudes.get(i));
					logger.info("Se ha borrado de REGISTRO_AUDITORIA");
					
					//REGISTRO_SOLICITUD
					registroSolicitudManager.borrarSolicitudRegistro(idSolicitudes.get(i));
					logger.info("Se ha borrado de REGISTRO_SOLICITUD");
					
					//CCAA
					solicitudCcaaManager.borrarSolicitudCcaa(idSolicitudes.get(i));
					logger.info("Se ha borrado de SOLICITUD_CCAA");
					
					//SOLICTUD_CORREO
					solicitudCorreoManager.borrarSolicitudCorreo(idSolicitudes.get(i));
					logger.info("Se ha borrado de SOLICITUD_CORREO");
					
					//PROPIOS  (AUNQUE PARECE QUE NO HACE FALTA PORQUE NO TIENE FK)
					solicitudesPropiosManager.borrarSolicitudPropio(idSolicitudes.get(i));
					logger.info("Se ha borrado de SOLICITUD_CAMPOS_PROPIOS");

					//SOLICITUD_COMUN
					solicitudesManager.borrarSolicitudComun(idSolicitudes.get(i));
					logger.info("se ha borrado de SOLICITUD_COMUN el idSolicitud="+ idSolicitudes.get(i));

				}
			}else{
				logger.info("No se han obtenido solicitudes");
			}
			
		}catch (Exception e) {
			logger.error("Entra en el catch despues de intentar borrar");
			logger.error("Error en execute:" + e);
		}
		
		logger.info("Batch Mantenimiento Solicitudes Comun- fin");
	}

	/**
	 * Obtiene el solicitudesr manager.
	 *
	 * @return el solicitudesr manager
	 */
	public SolicitudesManager getSolicitudesrManager() {
		return solicitudesManager;
	}

	/**
	 * Establece el solicitudes manager.
	 *
	 * @param solicitudesManager el nuevo solicitudes manager
	 */
	public void setSolicitudesManager(
			SolicitudesManager solicitudesManager) {
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
	public void setSolicitudCcaaManager(
			SolicitudCcaaManager solicitudCcaaManager) {
		this.solicitudCcaaManager = solicitudCcaaManager;
	}


}
