package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.TarifaDAO;
import es.map.ips.model.Grupo;
import es.map.ips.model.Tarifa;
import es.map.ips.model.TarifaQuery;
import es.map.ips.model.TipoAcceso;
import es.map.ipsg.bean.TarifaBean;


/**
 * El Class TarifaManagerImpl.
 */
public class TarifaManagerImpl implements TarifaManager {
	
	/** el arrtarifa. */
	private ArrayList<TarifaBean> arrtarifa;
	
	/** el tarifa DAO. */
	private TarifaDAO tarifaDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(TarifaManagerImpl.class);
	
	/** La constante STRING_DESPUESINTRODUCIRDATOSBEAN. */
	private static final String STRING_DESPUESINTRODUCIRDATOSBEAN = "Despues de introducir los datos en el bean";

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.TarifaManager#buscarTarifaAll(es.map.ips.model.TarifaQuery)
	 */
	public List<TarifaBean> buscarTarifaAll(TarifaQuery tarifaQuery) {
		SearchResult<Tarifa> tarifa = buscarTarifa(tarifaQuery);
		arrtarifa= new ArrayList<TarifaBean>();
		for(int i=0;i<tarifa.getResults().size();i++){
			TarifaBean aux;
			aux = toTarifaBean(tarifa.getResults().get(i));
			if(aux != null){
				arrtarifa.add(aux);
			}
		}	
		return arrtarifa;	
	}

	/**
	 * To tarifa bean.
	 *
	 * @param tarifa el tarifa
	 * @return el tarifa bean
	 */
	private TarifaBean toTarifaBean(Tarifa tarifa) {
		int id = tarifa.getId();
		String descripcion = tarifa.getDescripcion();
		Float importe = tarifa.getImporte();
		String ejercicio = tarifa.getEjercicio();
		char estado = tarifa.getEstado();
		
		TarifaBean tarifaBean= new TarifaBean();
		tarifaBean.setDescripcion(descripcion);
		tarifaBean.setId(id);
		tarifaBean.setEstado(estado);
		tarifaBean.setImporte(importe);
		tarifaBean.setEjercicio(ejercicio);
		tarifaBean.setTipoAcceso(tarifa.getTipoAcceso());
		tarifaBean.setGrupo(tarifa.getGrupo());
		tarifaBean.setIdGrupo(tarifa.getGrupo().getId());
		tarifaBean.setIdTipoAcceso(tarifa.getTipoAcceso().getId());
		tarifaBean.setDesGrupo(tarifa.getGrupo().getDescripcion());
		tarifaBean.setDesTipoAcceso(tarifa.getTipoAcceso().getCodigo());
		
		
		
		logger.info(STRING_DESPUESINTRODUCIRDATOSBEAN);

	    return tarifaBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.TarifaManager#buscarTarifaAllNumTotal(es.map.ips.model.TarifaQuery)
	 */
	public List<TarifaBean> buscarTarifaAllNumTotal(TarifaQuery tarifaQuery) {
		SearchResult<Tarifa> tarifa = buscarTarifa(tarifaQuery);
		int numTotal = 0;	
		if(tarifa.getNumResults() != null){
			numTotal = tarifa.getNumResults();
		}
		
		arrtarifa= new ArrayList<TarifaBean>();
		for(int i=0;i<tarifa.getResults().size();i++){
			TarifaBean aux;
			aux = toTarifaBeanNumTotal(tarifa.getResults().get(i),numTotal);
			if(aux != null){
				arrtarifa.add(aux);
			}
		}	
		return arrtarifa;	
	}

	/**
	 * To tarifa bean num total.
	 *
	 * @param tarifa el tarifa
	 * @param numTotal el num total
	 * @return el tarifa bean
	 */
	private TarifaBean toTarifaBeanNumTotal(Tarifa tarifa,int numTotal) {
		int id = tarifa.getId();
		String descripcion = tarifa.getDescripcion();
		Float importe = tarifa.getImporte();
		String ejercicio = tarifa.getEjercicio();
		char estado = tarifa.getEstado();
		
		TarifaBean tarifaBean= new TarifaBean();
		tarifaBean.setDescripcion(descripcion);
		tarifaBean.setId(id);
		tarifaBean.setEstado(estado);
		tarifaBean.setImporte(importe);
		tarifaBean.setEjercicio(ejercicio);
		tarifaBean.setNumTotal(numTotal);
		tarifaBean.setDesGrupo(tarifa.getGrupo().getDescripcion());
		tarifaBean.setDesTipoAcceso(tarifa.getTipoAcceso().getCodigo());
		
		logger.info(STRING_DESPUESINTRODUCIRDATOSBEAN);

	    return tarifaBean;
	}
	

	/**
	 * Buscar tarifa.
	 *
	 * @param tarifaQuery el tarifa query
	 * @return el search result
	 */
	private SearchResult<Tarifa> buscarTarifa(TarifaQuery tarifaQuery) {
			ApplicationException.assertNotNull(tarifaQuery, "tarifaQuery no puede ser null");
		
		return tarifaDAO.search(tarifaQuery);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.TarifaManager#guardarTarifa(es.map.ipsg.bean.TarifaBean)
	 */
	public Integer guardarTarifa(TarifaBean tarifaBean){
		Tarifa tarifa= toTarifa(tarifaBean);
		Integer idTarifa = tarifaDAO.insert(tarifa);
		return idTarifa;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.TarifaManager#modificarTarifa(es.map.ipsg.bean.TarifaBean)
	 */
	public void modificarTarifa(TarifaBean tarifaBean){
		Tarifa tarifa = toTarifa(tarifaBean);
		tarifaDAO.update(tarifa);
	}
	
	
	
	/**
	 * To tarifa.
	 *
	 * @param tarifaBean el tarifa bean
	 * @return el tarifa
	 */
	public Tarifa toTarifa(TarifaBean tarifaBean){
		
		Tarifa tarifa = new Tarifa();
		Grupo grupo = new Grupo();
		TipoAcceso tipoAcceso= new TipoAcceso();
			
		tarifa.setDescripcion(tarifaBean.getDescripcion());
		tarifa.setId(tarifaBean.getId());
		tarifa.setEstado(tarifaBean.getEstado());
		tarifa.setImporte(tarifaBean.getImporte());
		tarifa.setEjercicio(tarifaBean.getEjercicio());
		grupo.setId(tarifaBean.getIdGrupo());
		grupo.setDescripcion(tarifaBean.getDescripcion());
		tarifa.setGrupo(grupo);
		tipoAcceso.setId(tarifaBean.getIdTipoAcceso());
		tipoAcceso.setCodigo(tarifaBean.getDesTipoAcceso());
		tarifa.setTipoAcceso(tipoAcceso);
		
		
		
		logger.info(STRING_DESPUESINTRODUCIRDATOSBEAN);
		
		return tarifa;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.TarifaManager#obtenerTarifa(java.lang.Integer)
	 */
	public TarifaBean obtenerTarifa(Integer idTarifa){
		Tarifa tarifa = tarifaDAO.get(idTarifa);
		return toTarifaBean(tarifa);
	}
	
	/**
	 * Obtiene el tarifa DAO.
	 *
	 * @return el tarifa DAO
	 */
	public TarifaDAO getTarifaDAO() {
		return tarifaDAO;
	}

	/**
	 * Establece el tarifa DAO.
	 *
	 * @param tarifaDAO el nuevo tarifa DAO
	 */
	public void setTarifaDAO(TarifaDAO tarifaDAO) {
		this.tarifaDAO = tarifaDAO;
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
	
