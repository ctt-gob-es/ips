package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.DescargaModelo790Query;
import es.map.ipsg.bean.DescargaModelo790Bean;



/**
 * DescargaModelo790Manager.
 *
 * @author amartinl
 */
public interface DescargaModelo790Manager {
	
	/**
	 * Buscar descarga modelo 790 combo.
	 *
	 * @param descargaModelo790Query el descarga modelo 790 query
	 * @return el array list
	 */
	public ArrayList<DescargaModelo790Bean> buscarDescargaModelo790Combo(DescargaModelo790Query descargaModelo790Query);
	
	/**
	 * Buscar descarga modelo 790 all.
	 *
	 * @param descargaModelo790Query el descarga modelo 790 query
	 * @return el array list
	 */
	public ArrayList<DescargaModelo790Bean> buscarDescargaModelo790All(DescargaModelo790Query descargaModelo790Query);
	
	
	
}
