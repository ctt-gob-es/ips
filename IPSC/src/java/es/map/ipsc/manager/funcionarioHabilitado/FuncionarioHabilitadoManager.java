package es.map.ipsc.manager.funcionarioHabilitado;

import es.map.ips.model.FuncionarioHabilitado;
import es.map.ips.model.FuncionarioHabilitadoQuery;

/**
 * El Interface FuncionarioHabilitadoManager.
 */
public interface FuncionarioHabilitadoManager {

	
	/**
	 * Obtener funcionario.
	 *
	 * @param funcionarioHabilitadoQuery el funcionario habilitado query
	 * @return el funcionario habilitado
	 */
	FuncionarioHabilitado obtenerFuncionario(FuncionarioHabilitadoQuery funcionarioHabilitadoQuery);
	
	/**
	 * Guardar funcionario.
	 *
	 * @param funcionarioHabilitado el funcionario habilitado
	 * @return el long
	 */
	Long guardarFuncionario(FuncionarioHabilitado funcionarioHabilitado);
}
