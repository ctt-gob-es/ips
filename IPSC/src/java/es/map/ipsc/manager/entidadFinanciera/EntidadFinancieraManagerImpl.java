package es.map.ipsc.manager.entidadFinanciera;

import java.util.ArrayList;

import org.springframework.util.StringUtils;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.EntidadFinancieraDAO;
import es.map.ips.model.EntidadFinanciera;
import es.map.ips.model.EntidadFinancieraQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.EntidadBean;

/**
 * El Class EntidadFinancieraManagerImpl.
 */
public class EntidadFinancieraManagerImpl implements EntidadFinancieraManager {
	
	/** el entidad financiera DAO. */
	private EntidadFinancieraDAO entidadFinancieraDAO;

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.entidadFinanciera.EntidadFinancieraManager#buscarEntidadCombo()
	 */
	public ArrayList<EntidadBean> buscarEntidadCombo() {
		ArrayList<EntidadBean> entidad = new ArrayList<EntidadBean>();
		
		EntidadFinancieraQuery entidadFinancieraQuery = new EntidadFinancieraQuery();
		entidadFinancieraQuery.setEstado(Constantes.ENTIDAD_FINANCIERA_ESTADO_ACTIVO);
		SearchResult<EntidadFinanciera> entidades = entidadFinancieraDAO.search(entidadFinancieraQuery);
		for(int i=0;i<entidades.getResults().size();i++){
			EntidadBean aux;
			aux = toEntidadBean(entidades.getResults().get(i));
			if(aux != null){
				entidad.add(aux);
			}
		}
		return entidad;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.entidadFinanciera.EntidadFinancieraManager#buscarEntidadAdeudoCombo()
	 */
	public ArrayList<EntidadBean> buscarEntidadAdeudoCombo() {
		ArrayList<EntidadBean> entidad = new ArrayList<EntidadBean>();
		
		EntidadFinancieraQuery entidadFinancieraQuery = new EntidadFinancieraQuery();
		entidadFinancieraQuery.setEstado(Constantes.ENTIDAD_FINANCIERA_ESTADO_ACTIVO);
		entidadFinancieraQuery.addTipoPagoIn(Constantes.PAGO_TIPO_ADEUDO_ID);
		entidadFinancieraQuery.addTipoPagoIn(Constantes.PAGO_TIPO_TODOS_ID);
		entidadFinancieraQuery.addOrder(EntidadFinancieraQuery.DESCRIPCION, OrderType.ASC);
		
		SearchResult<EntidadFinanciera> entidades = entidadFinancieraDAO.search(entidadFinancieraQuery);
		for(int i=0;i<entidades.getResults().size();i++){
			EntidadBean aux;
			aux = toEntidadBean(entidades.getResults().get(i));
			if(aux != null){
				entidad.add(aux);
			}
		}
		return entidad;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.entidadFinanciera.EntidadFinancieraManager#buscarEntidadTarjetaCombo()
	 */
	public ArrayList<EntidadBean> buscarEntidadTarjetaCombo() {
		ArrayList<EntidadBean> entidad = new ArrayList<EntidadBean>();
		
		EntidadFinancieraQuery entidadFinancieraQuery = new EntidadFinancieraQuery();
		entidadFinancieraQuery.setEstado(Constantes.ENTIDAD_FINANCIERA_ESTADO_ACTIVO);
		entidadFinancieraQuery.addTipoPagoIn(Constantes.PAGO_TIPO_TARJETA_ID);
		entidadFinancieraQuery.addTipoPagoIn(Constantes.PAGO_TIPO_TODOS_ID);
		entidadFinancieraQuery.addOrder(EntidadFinancieraQuery.DESCRIPCION, OrderType.ASC);
		SearchResult<EntidadFinanciera> entidades = entidadFinancieraDAO.search(entidadFinancieraQuery);
		for(int i=0;i<entidades.getResults().size();i++){
			EntidadBean aux;
			aux = toEntidadBean(entidades.getResults().get(i));
			if(aux != null){
				entidad.add(aux);
			}
		}
		return entidad;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.entidadFinanciera.EntidadFinancieraManager#buscarEntidadById(es.map.ips.model.EntidadFinancieraQuery)
	 */
	public EntidadFinanciera buscarEntidadById(
			EntidadFinancieraQuery entidadFinancieraQuery) {
		EntidadFinanciera entidad = entidadFinancieraDAO.searchUnique(entidadFinancieraQuery);
		if(entidad != null){
			return entidad;
		}
		return null;
	}

	/**
	 * To entidad bean.
	 *
	 * @param entidad el entidad
	 * @return el entidad bean
	 */
	private EntidadBean toEntidadBean(EntidadFinanciera entidad) {
		EntidadBean aux = new EntidadBean();
		if(entidad.getId() != null){
			aux.setIdEntidadFinanciera(String.valueOf(entidad.getId()));
		}
		if(entidad.getCodigo() != null){
			aux.setCodigo(entidad.getCodigo());
		}
		if(entidad.getDescripcion() != null){
			aux.setDescripcion(entidad.getDescripcion());
		}
		if(entidad.getEstado() != null){
			aux.setEstado(String.valueOf(entidad.getEstado()));
		}
		if(entidad.getTipoPago() != null){
			aux.setTipoPago(String.valueOf(entidad.getTipoPago()));
		}
		
		aux.setApertura(!StringUtils.isEmpty(entidad.getApertura())?entidad.getApertura():"");
		aux.setCierre(!StringUtils.isEmpty(entidad.getCierre())?entidad.getCierre():"");
		
		return aux;
	}

	/**
	 * Obtiene el entidad financiera DAO.
	 *
	 * @return el entidad financiera DAO
	 */
	public EntidadFinancieraDAO getEntidadFinancieraDAO() {
		return entidadFinancieraDAO;
	}

	/**
	 * Establece el entidad financiera DAO.
	 *
	 * @param entidadFinancieraDAO el nuevo entidad financiera DAO
	 */
	public void setEntidadFinancieraDAO(EntidadFinancieraDAO entidadFinancieraDAO) {
		this.entidadFinancieraDAO = entidadFinancieraDAO;
	}

	

	
	
}
