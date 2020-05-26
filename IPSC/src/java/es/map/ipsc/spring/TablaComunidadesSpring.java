package es.map.ipsc.spring;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.ComunidadesQuery;
import es.map.ipsc.bean.ComunidadesBean;
import es.map.ipsc.manager.comunidades.ComunidadesManager;

/**
 * El Class TablaComunidadesSpring.
 */
public class TablaComunidadesSpring extends AbstractSpring{
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(TablaComunidadesSpring.class);
	
	/** el comunidades manager. */
	private ComunidadesManager comunidadesManager;
	
	/**
	 * Crea una nueva tabla comunidades spring.
	 */
	public TablaComunidadesSpring() {
		try{
			setComunidadesManager((ComunidadesManager) getBean("comunidadesManager"));
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error tabla comunidades",e);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String mostrar = "show";
					
		ArrayList<ComunidadesBean> listcomunidades;
		ComunidadesQuery comunidadesQuerytotal = new ComunidadesQuery();
		comunidadesQuerytotal.addOrder(ComunidadesQuery.DESCRIPCION, OrderType.ASC);
		listcomunidades = comunidadesManager.buscarComunidadesCombo(comunidadesQuerytotal);
		this.setRequestAttribute("listcomunidades", listcomunidades);
	
		return mostrar;
	}

	/**
	 * Obtiene el comunidades manager.
	 *
	 * @return el comunidades manager
	 */
	public ComunidadesManager getComunidadesManager() {
		return comunidadesManager;
	}

	/**
	 * Establece el comunidades manager.
	 *
	 * @param comunidadesManager el nuevo comunidades manager
	 */
	public void setComunidadesManager(ComunidadesManager comunidadesManager) {
		this.comunidadesManager = comunidadesManager;
	}
}

