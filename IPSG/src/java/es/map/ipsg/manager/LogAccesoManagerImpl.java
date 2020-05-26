package es.map.ipsg.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.CodigoAccesoDAO;
import es.map.ips.dao.LogAccesoDAO;
import es.map.ips.model.CodigoAcceso;
import es.map.ips.model.CodigoAccesoQuery;
import es.map.ips.model.LogAcceso;
import es.map.ips.model.LogAccesoQuery;
import es.map.ipsg.bean.LogAccesoBean;

/**
 * El Class LogAccesoManagerImpl.
 */
public class LogAccesoManagerImpl implements LogAccesoManager {
	
	/** el codigo acceso DAO. */
	private CodigoAccesoDAO codigoAccesoDAO;
	
	/** el log acceso DAO. */
	private LogAccesoDAO logAccesoDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(LogGenericoManagerImpl.class);
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.LogAccesoManager#guardarAcceso(es.map.ipsg.bean.LogAccesoBean)
	 */
	public Integer guardarAcceso(LogAccesoBean loginBean) {
		LogAcceso login= this.toLogAcceso(loginBean);		
		Integer idLogAcceso = logAccesoDAO.insert(login);
		
		return idLogAcceso;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.LogAccesoManager#actualizarLogAcceso(es.map.ipsg.bean.LogAccesoBean)
	 */
	public void actualizarLogAcceso(LogAccesoBean loginBean) {
		LogAcceso login = this.toLogAcceso(loginBean);
		logAccesoDAO.update(login);
		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.LogAccesoManager#buscarAccesosAll(es.map.ips.model.LogAccesoQuery)
	 */
	public ArrayList<LogAccesoBean> buscarAccesosAll(LogAccesoQuery logAccesoQuery) {
		SearchResult<LogAcceso> logAcceso = buscarLogAcceso(logAccesoQuery);
		Integer numTotal = logAcceso.getNumResults();
		int iNumTotal = 0;
		if (numTotal != null){
			iNumTotal = numTotal.intValue();
		}
		
		ArrayList<LogAccesoBean> arrLogGenerico = new ArrayList<LogAccesoBean>();
		
		for(int i=0;i<logAcceso.getResults().size();i++){
			LogAccesoBean aux;
			aux = toLogAccesoBean(logAcceso.getResults().get(i), iNumTotal);
			if(aux != null){
				arrLogGenerico.add(aux);
			}
		}	
		return arrLogGenerico;		
	}
	
	/**
	 * Buscar log acceso.
	 *
	 * @param logAccesoQuery el log acceso query
	 * @return el search result
	 */
	private SearchResult<LogAcceso> buscarLogAcceso(LogAccesoQuery logAccesoQuery) {
		ApplicationException.assertNotNull(logAccesoQuery, "logAccesoQuery no puede ser null");		
		return logAccesoDAO.search(logAccesoQuery);
	}
	
	/**
	 * To log acceso bean.
	 *
	 * @param logAcceso el log acceso
	 * @param numTotal el num total
	 * @return el log acceso bean
	 */
	private LogAccesoBean toLogAccesoBean(LogAcceso logAcceso, int numTotal) {
		
		LogAccesoBean logAccesoBean = new LogAccesoBean();
		logAccesoBean.setId(logAcceso.getId());
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		String sFecha=formatoDelTexto.format(logAcceso.getFecha());
		logAccesoBean.setFechaFormateada(sFecha);
		logAccesoBean.setFecha(logAcceso.getFecha());
		logAccesoBean.setLoginUsuario(logAcceso.getLoginUsuario());
		CodigoAcceso codigoAcceso=logAcceso.getCodigoAcceso();
		if(codigoAcceso!=null){
			logAccesoBean.setResultadoAcceso(codigoAcceso.getDescripcion());
		}
						
		logAccesoBean.setNumTotal(numTotal);
		
		return logAccesoBean;
	}
	
	/**
	 * To log acceso.
	 *
	 * @param loginBean el login bean
	 * @return el log acceso
	 */
	private LogAcceso toLogAcceso(LogAccesoBean loginBean) {
		LogAcceso login = new LogAcceso();
		CodigoAccesoQuery codigoAccesoQuery = new CodigoAccesoQuery();
		CodigoAcceso codigoAcceso = null;
		
		codigoAccesoQuery.setId(loginBean.getIdCodigoAcceso());
		codigoAcceso = codigoAccesoDAO.searchUnique(codigoAccesoQuery);
		
		login.setId(loginBean.getId());
		login.setFecha(loginBean.getFecha());
		login.setLoginUsuario(loginBean.getLoginUsuario());
		login.setCodigoAcceso(codigoAcceso);				
		
		return login;
	}
	
	/**
	 * Obtiene el codigo acceso DAO.
	 *
	 * @return el codigo acceso DAO
	 */
	public CodigoAccesoDAO getCodigoAccesoDAO() {
		return codigoAccesoDAO;
	}

	/**
	 * Establece el codigo acceso DAO.
	 *
	 * @param codigoAccesoDAO el nuevo codigo acceso DAO
	 */
	public void setCodigoAccesoDAO(CodigoAccesoDAO codigoAccesoDAO) {
		this.codigoAccesoDAO = codigoAccesoDAO;
	}

	/**
	 * Obtiene el log acceso DAO.
	 *
	 * @return el log acceso DAO
	 */
	public LogAccesoDAO getLogAccesoDAO() {
		return logAccesoDAO;
	}

	/**
	 * Establece el log acceso DAO.
	 *
	 * @param logAccesoDAO el nuevo log acceso DAO
	 */
	public void setLogAccesoDAO(LogAccesoDAO logAccesoDAO) {
		this.logAccesoDAO = logAccesoDAO;
	}

}
