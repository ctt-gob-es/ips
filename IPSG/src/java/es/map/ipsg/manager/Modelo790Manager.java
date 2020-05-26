package es.map.ipsg.manager;

import es.map.ipsg.bean.ModeloBean;


/**
 * El Interface Modelo790Manager.
 */
public interface Modelo790Manager {
	
	/**
	 * Generar modelo 790 masivo.
	 *
	 * @param numModelos el num modelos
	 * @param modeloBean el modelo bean
	 * @return el byte[]
	 */
	public byte[] generarModelo790Masivo(int numModelos, ModeloBean modeloBean);
}
