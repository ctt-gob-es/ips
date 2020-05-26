package es.map.ipsg.manager;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.LogGenericoDAO;
import es.map.ips.model.LogGenerico;
import es.map.ips.model.LogGenericoQuery;
import es.map.ips.model.Usuario;
import es.map.ipsg.bean.LogGenericoBean;
import es.map.ipsg.util.Constantes;

/**
 *  Clase que implementa el TituloOficialManager.
 *
 * @author amartinl
 */
public class LogGenericoManagerImpl implements LogGenericoManager {

	/** el log generico DAO. */
	//Variables
	private LogGenericoDAO logGenericoDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(LogGenericoManagerImpl.class);
	
	/**
	 * Metodo que se guarda en la tabla LogGenerico el registro insertado.
	 *
	 * @param logGenerico  LogGenerico
	 * @return idTitulo Integer El ID del Titulo que se ha guardado
	 */
	public Integer guardarLogGenerico (LogGenerico logGenerico){

		Integer idLogGenerico = logGenericoDAO.insert(logGenerico);
		
		return idLogGenerico;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.LogGenericoManager#generarRegistroLog(es.map.ipsg.bean.LogGenericoBean)
	 */
	public void generarRegistroLog(LogGenericoBean logGenericoBean){
		
		LogGenerico registroLog = null;
		Date date = new Date();
		registroLog = toLogGenerico(logGenericoBean);
		registroLog.setFecha(date);
		
		logGenericoDAO.insert(registroLog);
	}
	
	/**
	 * To log generico.
	 *
	 * @param logGenericoBean el log generico bean
	 * @return el log generico
	 */
	public LogGenerico toLogGenerico(LogGenericoBean logGenericoBean){
		LogGenerico logGenerico = new LogGenerico();
		
		logGenerico.setDetalleOperacion(logGenericoBean.getDetalleOperacion());
		logGenerico.setTipoFuncionalidad(logGenericoBean.getTipoFuncionalidad());
		logGenerico.setTipoOperacion(logGenericoBean.getTipoOperacion());
		logGenerico.setIdTablaOrigen(logGenericoBean.getIdTablaOrigen());
		logGenerico.setUsuario(logGenericoBean.getUsuario());
		
		return logGenerico;
	}

	/**
	 * Obtiene el log generico DAO.
	 *
	 * @return logGenericoDAO LogGenericoDAO
	 */
	public LogGenericoDAO getLogGenericoDAO() {
		return logGenericoDAO;
	}

	/**
	 * Establece el log generico DAO.
	 *
	 * @param logGenericoDAO LogGenericoDAO
	 */
	public void setLogGenericoDAO(LogGenericoDAO logGenericoDAO) {
		this.logGenericoDAO = logGenericoDAO;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.LogGenericoManager#buscarLogGenericoCierreReapertura(es.map.ips.model.LogGenericoQuery)
	 */
	public ArrayList<LogGenericoBean> buscarLogGenericoCierreReapertura(
			LogGenericoQuery logGenericoQuery) {
		SearchResult<LogGenerico> logGenerico = buscarLogGenerico(logGenericoQuery);
		Integer numTotal = logGenerico.getNumResults();
		int iNumTotal = 0;
		if (numTotal != null){
			iNumTotal = numTotal.intValue();
		}
		
		ArrayList<LogGenericoBean> arrLogGenerico= new ArrayList<LogGenericoBean>();
		for(int i=0;i<logGenerico.getResults().size();i++){
			LogGenericoBean aux;
			aux = toLogGenericoBean(logGenerico.getResults().get(i), iNumTotal);
			if(aux != null){
				arrLogGenerico.add(aux);
			}
		}	
		return arrLogGenerico;		
	}
	
	/**
	 * Buscar log generico.
	 *
	 * @param logGenericoQuery el log generico query
	 * @return el search result
	 */
	private SearchResult<LogGenerico> buscarLogGenerico(LogGenericoQuery logGenericoQuery) {
		ApplicationException.assertNotNull(logGenericoQuery, "logGenericoQuery no puede ser null");		
		return logGenericoDAO.search(logGenericoQuery);
	}
	
	/**
	 * To log generico bean.
	 *
	 * @param logGenerico el log generico
	 * @param numTotal el num total
	 * @return el log generico bean
	 */
	private LogGenericoBean toLogGenericoBean(LogGenerico logGenerico, int numTotal) {
		
		LogGenericoBean logGenericoBean = new LogGenericoBean();
		logGenericoBean.setId(logGenerico.getId());
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		String sFecha=formatoDelTexto.format(logGenerico.getFecha());
		logGenericoBean.setFechaFormateada(sFecha);		
		logGenericoBean.setFecha(logGenerico.getFecha());
		logGenericoBean.setTipoOperacion(logGenerico.getTipoOperacion());
		
		if(logGenerico.getTipoOperacion()!=null && !logGenerico.getTipoOperacion().equals("")){
			if(logGenerico.getTipoOperacion().charValue() ==  Constantes.OPERACION_CIERRE_EJERCICIO){
				logGenericoBean.setDescripcionTipoOperacion("CIERRE");
			}
			else if(logGenerico.getTipoOperacion().charValue() == Constantes.OPERACION_REAPERTURA_EJERCICIO){
				logGenericoBean.setDescripcionTipoOperacion("REAPERTURA");
			}
		}
		
						
		Usuario usuario = logGenerico.getUsuario();
		logGenericoBean.setUsuario(usuario);
		if (logGenericoBean.getUsuario()!=null){
			logGenericoBean.setLogin(logGenerico.getUsuario().getLogin());			
		}
		
		logGenericoBean.setDetalleOperacion(logGenerico.getDetalleOperacion());
		logGenericoBean.setIdTablaOrigen(logGenerico.getIdTablaOrigen());
		logGenericoBean.setTipoFuncionalidad(logGenerico.getTipoFuncionalidad());

		logGenericoBean.setNumTotal(numTotal);
		
		return logGenericoBean;
	}

}	
