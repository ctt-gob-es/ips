package es.map.ipsg.quartz;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.model.SolicitudComunAuxiliarQuery;
import es.map.ipsg.bean.ParametrosConfiguracionBean;
import es.map.ipsg.manager.ParametroConfiguracionManager;
import es.map.ipsg.manager.SolicitudCcaaAuxiliarManager;
import es.map.ipsg.manager.SolicitudComunAuxiliarManager;
import es.map.ipsg.manager.SolicitudPropioAuxiliarManager;
import es.map.ipsg.util.Constantes;

/**
 * Clase encargada de eliminar las solicitudes auxiliares y sus datos asociados con la antiguedad
 * determinada en BBDD (parametros configuracion).
 *
 * @author everis
 */
public class MantenimientoSolicitudesAuxiliaresAction {
	
/** La constante logger. */
private static final Logger logger = Logger.getLogger(MantenimientoSolicitudesAuxiliaresAction.class);
	
	/** el solicitud comun auxiliar manager. */
	private SolicitudComunAuxiliarManager solicitudComunAuxiliarManager;
	
	/** el solicitud propio auxiliar manager. */
	private SolicitudPropioAuxiliarManager solicitudPropioAuxiliarManager;
	
	/** el solicitud ccaa auxiliar manager. */
	private SolicitudCcaaAuxiliarManager solicitudCcaaAuxiliarManager;
	
	/** el parametro configuracion manager. */
	private ParametroConfiguracionManager parametroConfiguracionManager;
	
	/**
	 * Execute.
	 */
	public void execute(){
		
		logger.info("Batch Mantenimiento Solicitudes Auxiliares - inicio");

		try {
			
			try {
				solicitudComunAuxiliarManager = (SolicitudComunAuxiliarManager) ApplicationContextProvider.getInstance().getBean("solicitudComunAuxiliarManager");
				solicitudCcaaAuxiliarManager = (SolicitudCcaaAuxiliarManager) ApplicationContextProvider.getInstance().getBean("solicitudCcaaAuxiliarManager");
				solicitudPropioAuxiliarManager = (SolicitudPropioAuxiliarManager) ApplicationContextProvider.getInstance().getBean("solicitudPropioAuxiliarManager");
				parametroConfiguracionManager = (ParametroConfiguracionManager) ApplicationContextProvider.getInstance().getBean("parametroConfiguracionManager");
			} catch (Exception e) {
				logger.info("Contexto cargado por ejecucion de Test.");
				logger.error("Error Contexto cargado por ejecucion de Test",e);
			}
			
			// Obtenemos el numero de meses a partir de los cuales se han de eliminar las solicitudes auxiliares
			ParametrosConfiguracionBean tiempoEstanciaSolicitudes=parametroConfiguracionManager.obtenerParametrosConfiguracion(Constantes.PARAMETRO_CONFIGURACION_ID_TIEMPO_ESTANCIA_SOLICITUDES_AUXILIAR);

			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			c.add(Calendar.MONTH,-(Integer.valueOf(tiempoEstanciaSolicitudes.getValorCampo())));
			Date fechaSolicitudMax = c.getTime();
			
			SolicitudComunAuxiliarQuery solicitudComunAuxiliarQuery = new SolicitudComunAuxiliarQuery();
			solicitudComunAuxiliarQuery.setFechaSolicitudMax(fechaSolicitudMax);
			ArrayList<Long> idSolicitudes = solicitudComunAuxiliarManager.buscarSolicitudComunAuxiliarByFechaSolicitud(solicitudComunAuxiliarQuery);
			
			if(idSolicitudes !=null){
				
				logger.info("Numero de solicitudes auxiliares a eliminar: "+idSolicitudes.size());
				
				for (int i = 0; i < idSolicitudes.size(); i++) {
					solicitudCcaaAuxiliarManager.borrarSolicitudCcaaAuxiliar(idSolicitudes.get(i));
					solicitudPropioAuxiliarManager.borrarSolicitudPropioAuxiliar(idSolicitudes.get(i));
					solicitudComunAuxiliarManager.borrarSolicitudComunAuxiliar(idSolicitudes.get(i));					
				}
			}else{
				logger.info("No se han obtenido solicitudes auxiliares");
			}
			
		}catch (Exception e) {
			logger.error("Error en mantenimiento solicitudes auxiliares:",e);
			
		}
		
		logger.info("Batch Mantenimiento Solicitudes Auxiliares - fin");
	}

	/**
	 * Obtiene el solicitud comun auxiliar manager.
	 *
	 * @return el solicitud comun auxiliar manager
	 */
	public SolicitudComunAuxiliarManager getSolicitudComunAuxiliarManager() {
		return solicitudComunAuxiliarManager;
	}

	/**
	 * Establece el solicitud comun auxiliar manager.
	 *
	 * @param solicitudComunAuxiliarManager el nuevo solicitud comun auxiliar manager
	 */
	public void setSolicitudComunAuxiliarManager(
			SolicitudComunAuxiliarManager solicitudComunAuxiliarManager) {
		this.solicitudComunAuxiliarManager = solicitudComunAuxiliarManager;
	}

	/**
	 * Obtiene el solicitud propio auxiliar manager.
	 *
	 * @return el solicitud propio auxiliar manager
	 */
	public SolicitudPropioAuxiliarManager getSolicitudPropioAuxiliarManager() {
		return solicitudPropioAuxiliarManager;
	}

	/**
	 * Establece el solicitud propio auxiliar manager.
	 *
	 * @param solicitudPropioAuxiliarManager el nuevo solicitud propio auxiliar manager
	 */
	public void setSolicitudPropioAuxiliarManager(
			SolicitudPropioAuxiliarManager solicitudPropioAuxiliarManager) {
		this.solicitudPropioAuxiliarManager = solicitudPropioAuxiliarManager;
	}

	/**
	 * Obtiene el solicitud ccaa auxiliar manager.
	 *
	 * @return el solicitud ccaa auxiliar manager
	 */
	public SolicitudCcaaAuxiliarManager getSolicitudCcaaAuxiliarManager() {
		return solicitudCcaaAuxiliarManager;
	}

	/**
	 * Establece el solicitud ccaa auxiliar manager.
	 *
	 * @param solicitudCcaaAuxiliarManager el nuevo solicitud ccaa auxiliar manager
	 */
	public void setSolicitudCcaaAuxiliarManager(
			SolicitudCcaaAuxiliarManager solicitudCcaaAuxiliarManager) {
		this.solicitudCcaaAuxiliarManager = solicitudCcaaAuxiliarManager;
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

}
