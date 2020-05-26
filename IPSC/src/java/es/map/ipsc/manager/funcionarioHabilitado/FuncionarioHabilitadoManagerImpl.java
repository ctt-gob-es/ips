package es.map.ipsc.manager.funcionarioHabilitado;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import es.map.ips.dao.FuncionarioHabilitadoDAO;
import es.map.ips.model.FuncionarioHabilitado;
import es.map.ips.model.FuncionarioHabilitadoQuery;
import es.map.ipsc.manager.convocatorias.DocumentosConvocatoriaManagerImpl;

/**
 * El Class FuncionarioHabilitadoManagerImpl.
 */
public class FuncionarioHabilitadoManagerImpl implements FuncionarioHabilitadoManager {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(DocumentosConvocatoriaManagerImpl.class);
	
	/** el funcionario habilitado DAO. */
	private FuncionarioHabilitadoDAO funcionarioHabilitadoDAO;
	
	 
	/**
	 *  Obtiene el funcionario habilitado correspondiente en funcion de los parametros introducidos en la query.
	 *
	 * @param funcionarioHabilitadoQuery el funcionario habilitado query
	 * @return el funcionario habilitado
	 */
	public FuncionarioHabilitado obtenerFuncionario(FuncionarioHabilitadoQuery funcionarioHabilitadoQuery) {
		try {
			return funcionarioHabilitadoDAO.searchUnique(funcionarioHabilitadoQuery);
		} catch (HibernateException e) {
			logger.info(e);
			logger.error("Error obtener funcionario",e);
			return null;
			
		}
	}
	
	/**
	 * Registra en bb.dd un funcionario habilitado nuevo
	 *
	 * @param funcionarioHabilitado el funcionario habilitado
	 * @return el long
	 */
	public Long guardarFuncionario(FuncionarioHabilitado funcionarioHabilitado) {
		try {
			return funcionarioHabilitadoDAO.insert(funcionarioHabilitado);
		} catch (HibernateException e) {
			logger.info(e);
			logger.error("Error guardar funcionario",e);
			return null;
		}
	}

	/**
	 * Obtiene el funcionario habilitado DAO.
	 *
	 * @return el funcionario habilitado DAO
	 */
	public FuncionarioHabilitadoDAO getFuncionarioHabilitadoDAO() {
		return funcionarioHabilitadoDAO;
	}

	/**
	 * Establece el funcionario habilitado DAO.
	 *
	 * @param funcionarioHabilitadoDAO el nuevo funcionario habilitado DAO
	 */
	public void setFuncionarioHabilitadoDAO(FuncionarioHabilitadoDAO funcionarioHabilitadoDAO) {
		this.funcionarioHabilitadoDAO = funcionarioHabilitadoDAO;
	}
	
	
	
}
