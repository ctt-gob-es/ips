package es.map.ipsg.quartz;

import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.AlertaQuery;
import es.map.ips.model.ModoAlertaQuery;
import es.map.ipsg.bean.AlertaBean;
import es.map.ipsg.manager.AlertaManager;
import es.map.ipsg.util.Constantes;



/**
 * El Class EnvioMailsAutomaticosAction.
 */
public class EnvioMailsAutomaticosAction {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(EnvioMailsAutomaticosAction.class);
	
	/** el alerta manager. */
	//Managers
	private AlertaManager alertaManager;
	
	/**
	 * Execute.
	 */
	public void execute() {
		
		logger.info("execute - inicio");
		
		try {
			alertaManager = (AlertaManager) ApplicationContextProvider.getInstance().getBean("alertaManager");
			
			AlertaQuery alertaQuery = new AlertaQuery();
			alertaQuery.setEstado(Constantes.ALERTA_ESTADO_ACTIVO);
			
			ModoAlertaQuery modoAlerta = new ModoAlertaQuery();
			modoAlerta.setId(Constantes.MODO_ALERTA_CORREO_ELECTRONICO);
			alertaQuery.setModoAlerta(modoAlerta);
			
			ArrayList<AlertaBean> listadoAlertas = alertaManager.buscarAlertaAll(alertaQuery);
			
			if (listadoAlertas != null && !listadoAlertas.isEmpty()) {
				
				Iterator itListadoAlertas = listadoAlertas.iterator();
				while (itListadoAlertas.hasNext()){
					AlertaBean alertaBean = (AlertaBean)itListadoAlertas.next();
					
					SpringMessages mensajes = new SpringMessages();
					alertaManager.comprobarTipoAlerta(alertaBean,mensajes);
				}
				
			}
			
		}catch (Exception e) {
			logger.error("Error envio de email automatico:",e);
		}
		
		
		logger.info("execute - fin");
	}


}
