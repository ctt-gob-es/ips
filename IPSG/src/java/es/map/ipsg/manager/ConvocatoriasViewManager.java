package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.ConvocatoriasViewQuery;
import es.map.ipsg.bean.ConvocatoriasViewBean;



/**
 * El Interface ConvocatoriasViewManager.
 *
 * @author amartinl
 */
public interface ConvocatoriasViewManager {
	
	/**
	 * Buscar convocatorias view combo.
	 *
	 * @param convocatoriasViewQuery el convocatorias view query
	 * @return el array list
	 */
	public ArrayList<ConvocatoriasViewBean> buscarConvocatoriasViewCombo(ConvocatoriasViewQuery convocatoriasViewQuery);
	
	/**
	 * Buscar convocatorias view all.
	 *
	 * @param convocatoriasViewQuery el convocatorias view query
	 * @return el array list
	 */
	public ArrayList<ConvocatoriasViewBean> buscarConvocatoriasViewAll(ConvocatoriasViewQuery convocatoriasViewQuery);
	
	
	
}
