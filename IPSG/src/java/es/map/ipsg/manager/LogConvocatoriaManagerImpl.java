package es.map.ipsg.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.ConvocatoriaDAO;
import es.map.ips.dao.EstadoConvocatoriaDAO;
import es.map.ips.dao.LogConvocatoriaDAO;
import es.map.ips.dao.UsuarioDAO;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.EstadoConvocatoria;
import es.map.ips.model.LogConvocatoria;
import es.map.ips.model.LogConvocatoriaQuery;
import es.map.ips.model.Usuario;
import es.map.ipsg.bean.LogConvocatoriaBean;

/**
 *  Clase que implementa el TituloOficialManager.
 *
 * @author amartinl
 */
public class LogConvocatoriaManagerImpl implements LogConvocatoriaManager {

	/** el log convocatoria DAO. */
	//Variables
	private LogConvocatoriaDAO logConvocatoriaDAO;
	
	/** el usuario DAO. */
	private UsuarioDAO usuarioDAO;
	
	/** el convocatoria DAO. */
	private ConvocatoriaDAO convocatoriaDAO;
	
	/** el estado convocatoria DAO. */
	private EstadoConvocatoriaDAO estadoConvocatoriaDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(LogConvocatoriaManagerImpl.class);
	
	/**
	 * Método que se guarda en la tabla LogConvocatoria el registro insertado.
	 *
	 * @param logConvocatoriaBean  LogConvocatoria
	 * @return idLog Integer El ID del Log que se ha guardado
	 */
	public Integer guardarLogConvocatoria (LogConvocatoriaBean logConvocatoriaBean){
		LogConvocatoria logConvocatoria = toLogConvocatoria(logConvocatoriaBean);

		Integer idLogGenerico = logConvocatoriaDAO.insert(logConvocatoria);
		
		return idLogGenerico;
	}
	
	/**
	 * To log convocatoria.
	 *
	 * @param logConvocatoriaBean el log convocatoria bean
	 * @return el log convocatoria
	 */
	public LogConvocatoria toLogConvocatoria(LogConvocatoriaBean logConvocatoriaBean){
		LogConvocatoria logConvocatoria = new LogConvocatoria();
		
		Usuario usuario = usuarioDAO.get(logConvocatoriaBean.getIdUsuario());
		Convocatoria convocatoria = convocatoriaDAO.get(logConvocatoriaBean.getIdConvocatoria());
		
		if(logConvocatoriaBean.getIdEstadoConvocatoriaActual()!=null){
			EstadoConvocatoria estadoConvocatoriaActual = estadoConvocatoriaDAO.get(logConvocatoriaBean.getIdEstadoConvocatoriaActual());
			logConvocatoria.setEstadoConvocatoriaByIdEstadoActual(estadoConvocatoriaActual);
		}
		
		if(logConvocatoriaBean.getIdEstadoConvocatoriaAnterior()!=null){
			EstadoConvocatoria estadoConvocatoriaAnterior = estadoConvocatoriaDAO.get(logConvocatoriaBean.getIdEstadoConvocatoriaAnterior());
			logConvocatoria.setEstadoConvocatoriaByIdEstadoAnterior(estadoConvocatoriaAnterior);
		}
		
		logConvocatoria.setConvocatoria(convocatoria);
		logConvocatoria.setUsuario(usuario);		
		
		logConvocatoria.setDetalle(logConvocatoriaBean.getDetalle());
		logConvocatoria.setFecha(new Date());
		logConvocatoria.setTipoOperacion(logConvocatoriaBean.getTipoOperacion());		
		
		return logConvocatoria;
	}	

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.LogConvocatoriaManager#buscarConvocatoriaAll(es.map.ips.model.LogConvocatoriaQuery)
	 */
	public ArrayList<LogConvocatoriaBean> buscarConvocatoriaAll(LogConvocatoriaQuery logConvocatoriaQuery){		
		
		SearchResult<LogConvocatoria> logConvocatoria = buscarLogConvocatoria(logConvocatoriaQuery);
		Integer numTotal = logConvocatoria.getNumResults();
		int iNumTotal = 0;
		if (numTotal != null){
			iNumTotal = numTotal.intValue();
		}
		
		ArrayList<LogConvocatoriaBean> arrLogConvocatoria= new ArrayList<LogConvocatoriaBean>();
		for(int i=0;i<logConvocatoria.getResults().size();i++){
			LogConvocatoriaBean aux;
			aux = toLogConvocatoriaBean(logConvocatoria.getResults().get(i), iNumTotal);
			if(aux != null){
				arrLogConvocatoria.add(aux);
			}
		}	
		return arrLogConvocatoria;		
	}
	
	/**
	 * Buscar log convocatoria.
	 *
	 * @param logConvocatoriaQuery el log convocatoria query
	 * @return el search result
	 */
	private SearchResult<LogConvocatoria> buscarLogConvocatoria(LogConvocatoriaQuery logConvocatoriaQuery) {
		ApplicationException.assertNotNull(logConvocatoriaQuery, "logConvocatoriaQuery no puede ser null");
		return logConvocatoriaDAO.search(logConvocatoriaQuery);
	}
	
	/**
	 * To log convocatoria bean.
	 *
	 * @param logConvocatoria el log convocatoria
	 * @param numTotal el num total
	 * @return el log convocatoria bean
	 */
	private LogConvocatoriaBean toLogConvocatoriaBean(LogConvocatoria logConvocatoria, int numTotal) {
		
		LogConvocatoriaBean logConvocatoriaBean = new LogConvocatoriaBean();
		logConvocatoriaBean.setIdLogConvocatoria(logConvocatoria.getIdLogConvocatoria());
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		String sFecha=formatoDelTexto.format(logConvocatoria.getFecha());
		logConvocatoriaBean.setFechaFormateada(sFecha);
		logConvocatoriaBean.setFecha(logConvocatoria.getFecha());
		logConvocatoriaBean.setTipoOperacion(logConvocatoria.getTipoOperacion());
		
		Usuario usuario = logConvocatoria.getUsuario();
		
		String login="";
		if (usuario!=null){
			logConvocatoriaBean.setIdUsuario(usuario.getId());
			login= usuario.getLogin();			
		}
		
		Convocatoria convocatoria = logConvocatoria.getConvocatoria();
		if(convocatoria!=null){
			logConvocatoriaBean.setNombreConvocatoria(convocatoria.getId().toString());
		}
		
		logConvocatoriaBean.setDetalle(logConvocatoria.getDetalle());
		
		EstadoConvocatoria estadoConvocatoriaByIdEstadoAnterior=logConvocatoria.getEstadoConvocatoriaByIdEstadoAnterior();
				
		if(estadoConvocatoriaByIdEstadoAnterior!=null){
			logConvocatoriaBean.setIdEstadoConvocatoriaAnterior(estadoConvocatoriaByIdEstadoAnterior.getId());
			logConvocatoriaBean.setEstadoConvocatoriaAnterior(estadoConvocatoriaByIdEstadoAnterior.getDescripcion());
		}
				
		EstadoConvocatoria estadoConvocatoriaByIdEstadoActual = logConvocatoria.getEstadoConvocatoriaByIdEstadoActual();
				
		if(estadoConvocatoriaByIdEstadoActual!=null){
			logConvocatoriaBean.setIdEstadoConvocatoriaActual(estadoConvocatoriaByIdEstadoActual.getId());
			logConvocatoriaBean.setEstadoConvocatoriaActual(estadoConvocatoriaByIdEstadoActual.getDescripcion());
		}
				
		logConvocatoriaBean.setLogin(login);
		logConvocatoriaBean.setNumTotal(numTotal);
		
		return logConvocatoriaBean;
	}

	/**
	 * Obtiene el log convocatoria DAO.
	 *
	 * @return el log convocatoria DAO
	 */
	public LogConvocatoriaDAO getLogConvocatoriaDAO() {
		return logConvocatoriaDAO;
	}
	
	/**
	 * Establece el log convocatoria DAO.
	 *
	 * @param logConvocatoriaDAO el nuevo log convocatoria DAO
	 */
	public void setLogConvocatoriaDAO(LogConvocatoriaDAO logConvocatoriaDAO) {
		this.logConvocatoriaDAO = logConvocatoriaDAO;
	}	
	
	/**
	 * Obtiene el usuario DAO.
	 *
	 * @return el usuario DAO
	 */
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	/**
	 * Establece el usuario DAO.
	 *
	 * @param usuarioDAO el nuevo usuario DAO
	 */
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	/**
	 * Obtiene el convocatoria DAO.
	 *
	 * @return el convocatoria DAO
	 */
	public ConvocatoriaDAO getConvocatoriaDAO() {
		return convocatoriaDAO;
	}

	/**
	 * Establece el convocatoria DAO.
	 *
	 * @param convocatoriaDAO el nuevo convocatoria DAO
	 */
	public void setConvocatoriaDAO(ConvocatoriaDAO convocatoriaDAO) {
		this.convocatoriaDAO = convocatoriaDAO;
	}

	/**
	 * Obtiene el estado convocatoria DAO.
	 *
	 * @return el estado convocatoria DAO
	 */
	public EstadoConvocatoriaDAO getEstadoConvocatoriaDAO() {
		return estadoConvocatoriaDAO;
	}

	/**
	 * Establece el estado convocatoria DAO.
	 *
	 * @param estadoConvocatoriaDAO el nuevo estado convocatoria DAO
	 */
	public void setEstadoConvocatoriaDAO(EstadoConvocatoriaDAO estadoConvocatoriaDAO) {
		this.estadoConvocatoriaDAO = estadoConvocatoriaDAO;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}

}	