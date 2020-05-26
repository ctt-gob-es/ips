package es.map.ipsg.quartz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.model.AvisoQuery;
import es.map.ips.model.EstadoAvisoQuery;
import es.map.ipsg.bean.AvisoBean;
import es.map.ipsg.manager.AvisoManager;
import es.map.ipsg.util.Constantes;


/**
 * El Class PublicacionAvisosAutomaticosAction.
 */
public class PublicacionAvisosAutomaticosAction {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(PublicacionAvisosAutomaticosAction.class);
	
	/** el aviso manager. */
	//Managers
	private AvisoManager avisoManager;
	
	
	/**
	 * Execute.
	 */
	public void execute() {
		
		logger.info("execute - inicio");
		
		try {
			avisoManager = (AvisoManager) ApplicationContextProvider.getInstance().getBean("avisoManager");
			
			//Creamos la query para obtener los avisos de estado Activo(1)
			AvisoQuery avisoQuery = new AvisoQuery();			
			EstadoAvisoQuery estadoAviso = new EstadoAvisoQuery();
			estadoAviso.setId(Constantes.ESTADO_AVISO_ACTIVO);
			avisoQuery.setEstadoAviso(estadoAviso);
			
			//Obtenemos la lista de avisos de estado Activo
			ArrayList<AvisoBean> listaAvisosActivos = new ArrayList<AvisoBean>();
			listaAvisosActivos = avisoManager.buscarAvisoAll(avisoQuery);
			
			if (listaAvisosActivos != null && !listaAvisosActivos.isEmpty()) {
			
				//Filtramos los avisos por (FechaInicio < FechaActual < FechaFin)
				ArrayList<AvisoBean> listaAvisos = new ArrayList<AvisoBean>();
				
				for (int i=0; i< listaAvisosActivos.size(); i++) {
					Date fechaInicio = listaAvisosActivos.get(i).getFechaInicio();
					Date fechaFin = listaAvisosActivos.get(i).getFechaFin();
					Date fechaActual = this.dameFechaActual();
					
					if ((fechaActual.after(fechaInicio) || fechaActual.equals(fechaInicio)) 
							&& (fechaActual.before(fechaFin) || fechaActual.equals(fechaFin))) {
						
						listaAvisos.add(listaAvisosActivos.get(i));
					}					
				}//end for
				
				//Cambiamos el estado de la listaAvisos al estado Publicado
				if (listaAvisos != null && !listaAvisos.isEmpty()) {
					
					for (int j=0; j<listaAvisos.size(); j++) {						
						
						AvisoBean avisoBean = new AvisoBean();						
						avisoBean.setIdAviso(listaAvisos.get(j).getIdAviso());
						avisoBean.setTexto(listaAvisos.get(j).getTexto());
						avisoBean.setFechaInicio(listaAvisos.get(j).getFechaInicio());
						avisoBean.setFechaFin(listaAvisos.get(j).getFechaFin());						
						avisoBean.setIdEstadoAviso(Constantes.ESTADO_AVISO_PUBLICADO);
						
						avisoManager.actualizarAviso(avisoBean);
					}
				}
			}
			
			
			/*
			 * Ahora cambiamos los avisos publicados con fecha actual superior a 
			 * la fechaFin del estado publicado al estado inactivo
			 */
			
			//Creamos la query para obtener los avisos de estado Publicado(3)
			AvisoQuery avisoQueryPub = new AvisoQuery();			
			EstadoAvisoQuery estadoAvisoPub = new EstadoAvisoQuery();
			estadoAvisoPub.setId(Constantes.ESTADO_AVISO_PUBLICADO);
			avisoQueryPub.setEstadoAviso(estadoAvisoPub);
			
			//Obtenemos la lista de avisos de estado publicado
			ArrayList<AvisoBean> listaAvisosPublicados = new ArrayList<AvisoBean>();
			listaAvisosPublicados = avisoManager.buscarAvisoAll(avisoQueryPub);
			
			if (listaAvisosPublicados != null && !listaAvisosPublicados.isEmpty()) {
				//Filtramos los avisos por (FechaActual > FechaFin)
				ArrayList<AvisoBean> listaAvisosFinalizados = new ArrayList<AvisoBean>();
				
				for (int i=0; i< listaAvisosPublicados.size(); i++) {					
					Date fechaFin = listaAvisosPublicados.get(i).getFechaFin();
					Date fechaActual = this.dameFechaActual();
					
					if (fechaActual.after(fechaFin)) {
						
						listaAvisosFinalizados.add(listaAvisosPublicados.get(i));
					}					
				}//end for
				
				//Cambiamos el estado de la listaAvisosFinalizados al estado Inactivo
				if (listaAvisosFinalizados != null && !listaAvisosFinalizados.isEmpty()) {
					
					for (int j=0; j<listaAvisosFinalizados.size(); j++) {						
						
						AvisoBean avisoBean = new AvisoBean();						
						avisoBean.setIdAviso(listaAvisosFinalizados.get(j).getIdAviso());
						avisoBean.setTexto(listaAvisosFinalizados.get(j).getTexto());
						avisoBean.setFechaInicio(listaAvisosFinalizados.get(j).getFechaInicio());
						avisoBean.setFechaFin(listaAvisosFinalizados.get(j).getFechaFin());						
						avisoBean.setIdEstadoAviso(Constantes.ESTADO_AVISO_INACTIVO);
						
						avisoManager.actualizarAviso(avisoBean);
					}
				}
			}//end if general avisos finalizados
			
		}catch (Exception e) {
			logger.error("Error en Publicacion avisos automaticos:",e);
			
		}
		
		
		logger.info("execute - fin");
	}

	
	/**
	 * Dame fecha actual.
	 *
	 * @return el date
	 */
	private Date dameFechaActual(){

		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		Date feSistema = null;
		try {
			feSistema = new Date();
			feSistema = fmt.parse(fmt.format(feSistema));			
		} catch (Exception e) {
			logger.error("Error Obteniendo Fecha Actual- ",e);
		
		}
		return feSistema;
	}
	
	
	/*
	 * Getter & Setter
	 */

	/**
	 * Obtiene el aviso manager.
	 *
	 * @return el aviso manager
	 */
	public AvisoManager getAvisoManager() {
		return avisoManager;
	}


	/**
	 * Establece el aviso manager.
	 *
	 * @param avisoManager el nuevo aviso manager
	 */
	public void setAvisoManager(AvisoManager avisoManager) {
		this.avisoManager = avisoManager;
	}
	
}
