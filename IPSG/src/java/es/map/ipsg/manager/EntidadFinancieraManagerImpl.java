package es.map.ipsg.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.EntidadFinancieraDAO;
import es.map.ips.dao.EstadoProcesoDAO;
import es.map.ips.dao.IpsAuditoriaProcesoEEFFDAO;
import es.map.ips.model.EntidadFinanciera;
import es.map.ips.model.EntidadFinancieraQuery;
import es.map.ips.model.EstadoProceso;
import es.map.ips.model.EstadoProcesoQuery;
import es.map.ips.model.IpsAuditoriaProcesoEEFF;
import es.map.ips.model.TipoPago;
import es.map.ipsg.bean.EntidadFinancieraBean;
import es.map.ipsg.util.Constantes;

 
public class EntidadFinancieraManagerImpl implements EntidadFinancieraManager {

	private EntidadFinancieraDAO entidadFinancieraDAO;
	private IpsAuditoriaProcesoEEFFDAO ipsAuditoriaProcesoEEFFDAO;
	private EstadoProcesoDAO estadoProcesoDAO;
	
	private static final Logger logger = Logger.getLogger(EntidadFinancieraManagerImpl.class);
	
	public ArrayList<EntidadFinancieraBean> buscarEntidadFinancieraCombo(EntidadFinancieraQuery entidadFinancieraQuery) {
		SearchResult<EntidadFinanciera> entidadFinanciera = buscarEntidadFinanciera(entidadFinancieraQuery);
		ArrayList<EntidadFinancieraBean> arrEntidadFinanciera = new ArrayList<EntidadFinancieraBean>();
		for(int i=0; i < entidadFinanciera.getResults().size(); i++){
			EntidadFinancieraBean aux;
			aux = toEntidadFinancieraComboBean(entidadFinanciera.getResults().get(i));
			if(aux != null){
				arrEntidadFinanciera.add(aux);
			}
		}	
		return arrEntidadFinanciera;		
	}
	
	public ArrayList<EntidadFinancieraBean> buscarEntidadFinancieraAll(EntidadFinancieraQuery entidadFinancieraQuery){		
		SearchResult<EntidadFinanciera> entidadFinanciera = buscarEntidadFinanciera(entidadFinancieraQuery);
		int numTotal = entidadFinanciera.getNumResults();
		
		ArrayList<EntidadFinancieraBean> arrEntidadFinanciera = new ArrayList<EntidadFinancieraBean>();
		for(int i=0; i < entidadFinanciera.getResults().size(); i++){
			EntidadFinancieraBean aux;
			aux = toEntidadFinancieraBean(entidadFinanciera.getResults().get(i),numTotal);
			if(aux != null){
				arrEntidadFinanciera.add(aux);
			}
		}	
		return arrEntidadFinanciera;		
	}
	
	private EntidadFinancieraBean toEntidadFinancieraBean(EntidadFinanciera entidadFinanciera, int numTotal) {
		
		int id = entidadFinanciera.getId();
		String codigo = entidadFinanciera.getCodigo();
		String descripcion = entidadFinanciera.getDescripcion();
		Character actualizada = entidadFinanciera.getActualizada();
		TipoPago tipoPago = entidadFinanciera.getTipoPago();
		Character estado = entidadFinanciera.getEstado();
		
		EntidadFinancieraBean auxEntidadFinanciera = new EntidadFinancieraBean();
		auxEntidadFinanciera.setId(id);
		auxEntidadFinanciera.setCodigo(codigo);
		auxEntidadFinanciera.setDescripcion(descripcion);
		auxEntidadFinanciera.setActualizada(actualizada);
		auxEntidadFinanciera.setIdTipoPago(tipoPago.getId());
		auxEntidadFinanciera.setDesTipoPago(tipoPago.getDescripcion());
		auxEntidadFinanciera.setEstado(estado);
		
		auxEntidadFinanciera.setNumTotal(numTotal);
		
		return auxEntidadFinanciera;
	}
	
	private EntidadFinancieraBean toEntidadFinancieraComboBean(EntidadFinanciera entidadFinanciera) {
		int id = entidadFinanciera.getId();
		String codigo = entidadFinanciera.getCodigo();
		String descripcion = entidadFinanciera.getDescripcion();
		Character actualizada = entidadFinanciera.getActualizada();
		TipoPago tipoPago = entidadFinanciera.getTipoPago();
		Character estado = entidadFinanciera.getEstado();
		
		EntidadFinancieraBean auxEntidadFinanciera = new EntidadFinancieraBean();
		auxEntidadFinanciera.setId(id);
		auxEntidadFinanciera.setCodigo(codigo);
		auxEntidadFinanciera.setDescripcion(descripcion);
		auxEntidadFinanciera.setActualizada(actualizada);
		auxEntidadFinanciera.setIdTipoPago(tipoPago.getId());
		auxEntidadFinanciera.setDesTipoPago(tipoPago.getDescripcion());
		auxEntidadFinanciera.setEstado(estado);
		
		return auxEntidadFinanciera;
	}
	
	public ArrayList<EntidadFinancieraBean> buscarEntidadAdeudoCombo() {
		ArrayList<EntidadFinancieraBean> entidad = new ArrayList<EntidadFinancieraBean>();
		
		EntidadFinancieraQuery entidadFinancieraQuery = new EntidadFinancieraQuery();
		entidadFinancieraQuery.setEstado(Constantes.ENTIDAD_FINANCIERA_ESTADO_ACTIVO);
		entidadFinancieraQuery.addTipoPagoIn(Constantes.PAGO_TIPO_ADEUDO_ID);
		entidadFinancieraQuery.addTipoPagoIn(Constantes.PAGO_TIPO_TODOS_ID);
		entidadFinancieraQuery.addOrder(EntidadFinancieraQuery.DESCRIPCION, OrderType.ASC);
		
		SearchResult<EntidadFinanciera> entidades = entidadFinancieraDAO.search(entidadFinancieraQuery);
		for(int i=0;i<entidades.getResults().size();i++){
			EntidadFinancieraBean aux;
			aux = toEntidadFinancieraBean(entidades.getResults().get(i));
			if(aux != null){
				entidad.add(aux);
			}
		}
		return entidad;
	}

	public ArrayList<EntidadFinancieraBean> buscarEntidadTarjetaCombo() {
		ArrayList<EntidadFinancieraBean> entidad = new ArrayList<EntidadFinancieraBean>();
		
		EntidadFinancieraQuery entidadFinancieraQuery = new EntidadFinancieraQuery();
		entidadFinancieraQuery.setEstado(Constantes.ENTIDAD_FINANCIERA_ESTADO_ACTIVO);
		entidadFinancieraQuery.addTipoPagoIn(Constantes.PAGO_TIPO_TARJETA_ID);
		entidadFinancieraQuery.addTipoPagoIn(Constantes.PAGO_TIPO_TODOS_ID);
		entidadFinancieraQuery.addOrder(EntidadFinancieraQuery.DESCRIPCION, OrderType.ASC);
		SearchResult<EntidadFinanciera> entidades = entidadFinancieraDAO.search(entidadFinancieraQuery);
		for(int i=0;i<entidades.getResults().size();i++){
			EntidadFinancieraBean aux;
			aux = toEntidadFinancieraBean(entidades.getResults().get(i));
			if(aux != null){
				entidad.add(aux);
			}
		}
		return entidad;
	}

	private SearchResult<EntidadFinanciera> buscarEntidadFinanciera(EntidadFinancieraQuery entidadFinancieraQuery) {
			ApplicationException.assertNotNull(entidadFinancieraQuery, "entidadFinancieraQuery no puede ser null");
		
		return entidadFinancieraDAO.search(entidadFinancieraQuery);
	}

	public EntidadFinancieraDAO getEntidadFinancieraDAO() {
		return entidadFinancieraDAO;
	}

	public void setEntidadFinancieraDAO(EntidadFinancieraDAO entidadFinancieraDAO) {
		this.entidadFinancieraDAO = entidadFinancieraDAO;
	}

	public EntidadFinanciera toEntidadFinanciera(EntidadFinancieraBean entidadFinancieraBean){
		EntidadFinanciera entidadFinanciera = new EntidadFinanciera();
		
		entidadFinanciera.setId(entidadFinancieraBean.getId());
		entidadFinanciera.setCodigo(entidadFinancieraBean.getCodigo());
		entidadFinanciera.setDescripcion(entidadFinancieraBean.getDescripcion());
		entidadFinanciera.setActualizada(entidadFinancieraBean.getActualizada());
		TipoPago tipoPago = new TipoPago();
		tipoPago.setId(entidadFinancieraBean.getIdTipoPago());
		entidadFinanciera.setTipoPago(tipoPago);
		entidadFinanciera.setEstado(entidadFinancieraBean.getEstado());
		entidadFinanciera.setApertura(entidadFinancieraBean.getApertura());
		entidadFinanciera.setCierre(entidadFinancieraBean.getCierre());
		
		return entidadFinanciera;
	}
	
	public Integer guardarEntidadFinanciera(EntidadFinancieraBean entidadFinancieraBean){
		EntidadFinanciera entidadFinanciera = toEntidadFinanciera(entidadFinancieraBean);
		Integer idEntidadFinanciera = entidadFinancieraDAO.insert(entidadFinanciera);		
		return idEntidadFinanciera;
	}

	public EntidadFinancieraBean obtenerEntidadFinanciera(Integer idEntidadFinanciera){
		EntidadFinanciera entidadFinanciera = entidadFinancieraDAO.get(idEntidadFinanciera);
		return toEntidadFinancieraBean(entidadFinanciera);
	}
	
	public EntidadFinancieraBean toEntidadFinancieraBean(EntidadFinanciera entidadFinanciera){
		EntidadFinancieraBean entidadFinancieraBean = new EntidadFinancieraBean();
		
		entidadFinancieraBean.setId(entidadFinanciera.getId());
		entidadFinancieraBean.setCodigo(entidadFinanciera.getCodigo());
		entidadFinancieraBean.setDescripcion(entidadFinanciera.getDescripcion());
		entidadFinancieraBean.setActualizada(entidadFinanciera.getActualizada());
		
		if(entidadFinanciera.getTipoPago() != null) {
			entidadFinancieraBean.setIdTipoPago(entidadFinanciera.getTipoPago().getId());
			entidadFinancieraBean.setDesTipoPago(entidadFinanciera.getTipoPago().getDescripcion());
		}

		entidadFinancieraBean.setEstado(entidadFinanciera.getEstado());
		
		return entidadFinancieraBean;
	}

	public void modificarEntidadFinanciera(EntidadFinancieraBean entidadFinancieraBean){
		EntidadFinanciera entidadFinanciera = toEntidadFinanciera(entidadFinancieraBean);
		entidadFinancieraDAO.update(entidadFinanciera);
	}

	public static Logger getLogger() {
		return logger;
	}

	@Override
	public Integer guardarAuditoriaProcesoEEFF(IpsAuditoriaProcesoEEFF ipsAuditoriaEEFF) {
		return ipsAuditoriaProcesoEEFFDAO.insert(ipsAuditoriaEEFF);
	}

	@Override
	public EstadoProceso obtenerEstadoProceso(String estado) {
		EstadoProcesoQuery estadoProcesoQuery = new EstadoProcesoQuery();
		estadoProcesoQuery.setDescripcion(estado);
		return estadoProcesoDAO.searchUnique(estadoProcesoQuery);
	}

	public IpsAuditoriaProcesoEEFFDAO getIpsAuditoriaProcesoEEFFDAO() {
		return ipsAuditoriaProcesoEEFFDAO;
	}

	public void setIpsAuditoriaProcesoEEFFDAO(IpsAuditoriaProcesoEEFFDAO ipsAuditoriaProcesoEEFFDAO) {
		this.ipsAuditoriaProcesoEEFFDAO = ipsAuditoriaProcesoEEFFDAO;
	}

	public EstadoProcesoDAO getEstadoProcesoDAO() {
		return estadoProcesoDAO;
	}

	public void setEstadoProcesoDAO(EstadoProcesoDAO estadoProcesoDAO) {
		this.estadoProcesoDAO = estadoProcesoDAO;
	}

}
