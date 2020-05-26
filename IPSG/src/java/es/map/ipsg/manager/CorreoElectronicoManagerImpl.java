package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.mail.internet.InternetAddress;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.AlertaDAO;
import es.map.ips.dao.CorreoElectronicoDAO;
import es.map.ips.dao.SolicitudComunDAO;
import es.map.ips.model.Alerta;
import es.map.ips.model.CorreoElectronico;
import es.map.ips.model.CorreoElectronicoQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ipsg.bean.CorreoElectronicoBean;



/**
 * El Class CorreoElectronicoManagerImpl.
 */
public class CorreoElectronicoManagerImpl implements CorreoElectronicoManager {

	/** el correo electronico DAO. */
	//Variables
	private CorreoElectronicoDAO correoElectronicoDAO;
	
	/** el solicitud comun DAO. */
	private SolicitudComunDAO solicitudComunDAO;
	
	/** el alerta DAO. */
	private AlertaDAO alertaDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CorreoElectronicoManagerImpl.class);
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.CorreoElectronicoManager#guardarCorreoElectronico(es.map.ipsg.bean.CorreoElectronicoBean)
	 */
	public Long guardarCorreoElectronico(CorreoElectronicoBean correoElectronicoBean){
		
		CorreoElectronico correoElectronico = toCorreoElectronico(correoElectronicoBean);
		
		HashSet<SolicitudComun> solicitudes = new HashSet<SolicitudComun>();
		if (correoElectronicoBean.getIdSolicitud()!=null){
		Long idSolicitud[] = correoElectronicoBean.getIdSolicitud();
		
		for (int i = 0; i < correoElectronicoBean.getIdSolicitud().length; i++) {
			solicitudes.add(solicitudComunDAO.get(idSolicitud[i]));
		}
		correoElectronico.setSolicituds(solicitudes);
		}
		
		HashSet<Alerta> alertas = new HashSet<Alerta>();
		Set<Integer> idAlertas = correoElectronicoBean.getIdAlertas();		
		if(idAlertas!=null){
			
			Iterator itListadoIdAlertas = idAlertas.iterator();
			
			while (itListadoIdAlertas.hasNext()){
				Integer id = (Integer)itListadoIdAlertas.next();
				alertas.add(alertaDAO.get(id));				
			}			
			correoElectronico.setAlertas(alertas);	
		}
		
		Long idDevuelto=correoElectronicoDAO.insert(correoElectronico);
		
		return idDevuelto;
				
	}
	

	/**
	 * To correo electronico.
	 *
	 * @param correoElectronicoBean el correo electronico bean
	 * @return el correo electronico
	 */
	public CorreoElectronico toCorreoElectronico(CorreoElectronicoBean correoElectronicoBean){
		CorreoElectronico correoElectronico = new CorreoElectronico();
		
		correoElectronico.setDe(correoElectronicoBean.getDe());
		correoElectronico.setPara(correoElectronicoBean.getPara());
		correoElectronico.setMensaje(correoElectronicoBean.getMensaje());
		correoElectronico.setAsunto(correoElectronicoBean.getAsunto());
		correoElectronico.setFechaEnvio(correoElectronicoBean.getFechaEnvio());
		correoElectronico.setEstado(correoElectronicoBean.getEstado());
		correoElectronico.setSolicituds(correoElectronicoBean.getSolicituds());
		correoElectronico.setAlertas(correoElectronicoBean.getAlertas());
		
		if(correoElectronicoBean.getBcc()!=null && correoElectronicoBean.getBcc().length > 1){
			
			InternetAddress[] direcciones = correoElectronicoBean.getBcc();
			String direcionesPara = "";
			for(int i = 0; i<direcciones.length;i++){
				if((direcciones.length - 1) == i){
					direcionesPara=direcionesPara+direcciones[i].getAddress();
				}else{
					direcionesPara=direcionesPara+direcciones[i].getAddress()+",";
				}
			}
			
			correoElectronico.setPara(direcionesPara);
		}
				
		return correoElectronico;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.CorreoElectronicoManager#toCorreoElectronicoBean(es.map.ips.model.CorreoElectronico)
	 */
	public CorreoElectronicoBean toCorreoElectronicoBean(CorreoElectronico correoElectronico){
		
		CorreoElectronicoBean correoElectronicoBean = new CorreoElectronicoBean();
		
		correoElectronicoBean.setId(correoElectronico.getId());
		correoElectronicoBean.setDe(correoElectronico.getDe());
		correoElectronicoBean.setPara(correoElectronico.getPara());
		correoElectronicoBean.setMensaje(correoElectronico.getMensaje());
		correoElectronicoBean.setAsunto(correoElectronico.getAsunto());
		correoElectronicoBean.setFechaEnvio(correoElectronico.getFechaEnvio());
		correoElectronicoBean.setEstado(correoElectronico.getEstado());
		correoElectronicoBean.setSolicituds(correoElectronico.getSolicituds());
		correoElectronicoBean.setAlertas(correoElectronico.getAlertas());
				
		return correoElectronicoBean;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.CorreoElectronicoManager#BuscarCorreoElectronico(es.map.ips.model.CorreoElectronicoQuery)
	 */
	public ArrayList<CorreoElectronicoBean> BuscarCorreoElectronico(CorreoElectronicoQuery correoElectronicoQuery){
		
		SearchResult<CorreoElectronico> correoElectronico = busquedaCorreoElectronico(correoElectronicoQuery);
		ArrayList<CorreoElectronicoBean> arrCorreoElectronico= new ArrayList<CorreoElectronicoBean>();
		for(int i=0;i<correoElectronico.getResults().size();i++){
			CorreoElectronicoBean aux;
			aux = toCorreoElectronicoBean(correoElectronico.getResults().get(i));
			if(aux != null){
				arrCorreoElectronico.add(aux);
			}
		}	
		return arrCorreoElectronico;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.CorreoElectronicoManager#obtenerCorreoElectronico(java.lang.Long)
	 */
	public CorreoElectronicoBean obtenerCorreoElectronico (Long idCorreo) {
		CorreoElectronico correoElectronico = correoElectronicoDAO.get(idCorreo);
		CorreoElectronicoBean correoElectronicoBean = this.toCorreoElectronicoBean(correoElectronico);
		
		return correoElectronicoBean;
	}
	
	/**
	 * Busqueda correo electronico.
	 *
	 * @param correoElectronicoQuery el correo electronico query
	 * @return el search result
	 */
	private SearchResult<CorreoElectronico> busquedaCorreoElectronico(CorreoElectronicoQuery correoElectronicoQuery) {
		ApplicationException.assertNotNull(correoElectronicoQuery, "correoElectronicoQuery no puede ser null");
	
	return correoElectronicoDAO.search(correoElectronicoQuery);
}
	
	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}


	/**
	 * Obtiene el correo electronico DAO.
	 *
	 * @return the correoElectronicoDAO
	 */
	public CorreoElectronicoDAO getCorreoElectronicoDAO() {
		return correoElectronicoDAO;
	}


	/**
	 * Establece el correo electronico DAO.
	 *
	 * @param correoElectronicoDAO the correoElectronicoDAO to set
	 */
	public void setCorreoElectronicoDAO(CorreoElectronicoDAO correoElectronicoDAO) {
		this.correoElectronicoDAO = correoElectronicoDAO;
	}


	/**
	 * Obtiene el solicitud comun DAO.
	 *
	 * @return el solicitud comun DAO
	 */
	public SolicitudComunDAO getSolicitudComunDAO() {
		return solicitudComunDAO;
	}


	/**
	 * Establece el solicitud comun DAO.
	 *
	 * @param solicitudComunDAO el nuevo solicitud comun DAO
	 */
	public void setSolicitudComunDAO(SolicitudComunDAO solicitudComunDAO) {
		this.solicitudComunDAO = solicitudComunDAO;
	}


	/**
	 * Obtiene el alerta DAO.
	 *
	 * @return the alertaDAO
	 */
	public AlertaDAO getAlertaDAO() {
		return alertaDAO;
	}


	/**
	 * Establece el alerta DAO.
	 *
	 * @param alertaDAO the alertaDAO to set
	 */
	public void setAlertaDAO(AlertaDAO alertaDAO) {
		this.alertaDAO = alertaDAO;
	}
}	
	
