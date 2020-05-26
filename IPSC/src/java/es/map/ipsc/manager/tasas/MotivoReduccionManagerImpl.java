package es.map.ipsc.manager.tasas;

import java.util.ArrayList;

import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.MotivoReduccionTasaDAO;
import es.map.ips.model.MotivoReduccionTasa;
import es.map.ips.model.MotivoReduccionTasaQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.MotivoReduccionBean;

/**
 * El Class MotivoReduccionManagerImpl.
 */
public class MotivoReduccionManagerImpl implements MotivoReduccionManager {
	
	/** el motivo reduccion tasa DAO. */
	private MotivoReduccionTasaDAO motivoReduccionTasaDAO;

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tasas.MotivoReduccionManager#buscarMotivoReduccionCombo()
	 */
	public ArrayList<MotivoReduccionBean> buscarMotivoReduccionCombo() {
		ArrayList<MotivoReduccionBean> motivoReduccion = new ArrayList<MotivoReduccionBean>();
		
		MotivoReduccionTasaQuery motivoReduccionTasaQuery = new MotivoReduccionTasaQuery();
		motivoReduccionTasaQuery.setEstado(Constantes.MOTIVOTASA_ESTADO_ACTIVO);
		SearchResult<MotivoReduccionTasa> motivos = motivoReduccionTasaDAO.search(motivoReduccionTasaQuery);
		for(int i=0;i<motivos.getResults().size();i++){
			MotivoReduccionBean aux;
			aux = toMotivoBean(motivos.getResults().get(i));
			if(aux != null){
				motivoReduccion.add(aux);
			}
		}
		
		return motivoReduccion;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tasas.MotivoReduccionManager#buscarMotivoReduccionTasaAll()
	 */
	public ArrayList<MotivoReduccionTasa> buscarMotivoReduccionTasaAll(){
		
		ArrayList<MotivoReduccionTasa> motivoReduccionTasa = new ArrayList<MotivoReduccionTasa>();
		MotivoReduccionTasaQuery motivoReduccionTasaQuery = new MotivoReduccionTasaQuery();
		motivoReduccionTasaQuery.setEstado(Constantes.MOTIVOTASA_ESTADO_ACTIVO);
		SearchResult<MotivoReduccionTasa> motivos = motivoReduccionTasaDAO.search(motivoReduccionTasaQuery);
		for(int i=0;i<motivos.getResults().size();i++){
			MotivoReduccionTasa aux = motivos.getResults().get(i);
			motivoReduccionTasa.add(aux);
			}				
		return motivoReduccionTasa;
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tasas.MotivoReduccionManager#buscarMotivoReduccionBeanById(es.map.ips.model.MotivoReduccionTasaQuery)
	 */
	public MotivoReduccionBean buscarMotivoReduccionBeanById(
			MotivoReduccionTasaQuery motivoReduccionTasaQuery) {
		MotivoReduccionTasa motivos = motivoReduccionTasaDAO.searchUnique(motivoReduccionTasaQuery);
		if(motivos != null){
			return toMotivoBean(motivos);
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tasas.MotivoReduccionManager#buscarMotivoReduccionById(es.map.ips.model.MotivoReduccionTasaQuery)
	 */
	public MotivoReduccionTasa buscarMotivoReduccionById(
			MotivoReduccionTasaQuery motivoReduccionQuery) {
		MotivoReduccionTasa motivo = motivoReduccionTasaDAO.searchUnique(motivoReduccionQuery);
		if(motivo != null){
			return motivo;
		}
		return null;
	}

	/**
	 * To motivo bean.
	 *
	 * @param motivo el motivo
	 * @return el motivo reduccion bean
	 */
	private MotivoReduccionBean toMotivoBean(
			MotivoReduccionTasa motivo) {
		MotivoReduccionBean aux = new MotivoReduccionBean();
		if(motivo.getId() != null){
			aux.setIdMotivo(String.valueOf(motivo.getId()));
		}
		if(motivo.getDescripcion() != null){
			aux.setDescripcion(motivo.getDescripcion());
		}
		if(motivo.getEstado() != null){
			aux.setEstado(String.valueOf(motivo.getEstado()));
		}
		if(motivo.getTextoExplicativo() != null){
			aux.setTextoExplicativo(motivo.getTextoExplicativo());
		}
		if(motivo.getPorcentajeDescuento() != null){
			aux.setPorcentajeDescuento(String.valueOf(motivo.getPorcentajeDescuento()));
		}
		return aux;
	}

	/**
	 * Obtiene el motivo reduccion tasa DAO.
	 *
	 * @return el motivo reduccion tasa DAO
	 */
	public MotivoReduccionTasaDAO getMotivoReduccionTasaDAO() {
		return motivoReduccionTasaDAO;
	}

	/**
	 * Establece el motivo reduccion tasa DAO.
	 *
	 * @param motivoReduccionTasaDAO el nuevo motivo reduccion tasa DAO
	 */
	public void setMotivoReduccionTasaDAO(
			MotivoReduccionTasaDAO motivoReduccionTasaDAO) {
		this.motivoReduccionTasaDAO = motivoReduccionTasaDAO;
	}

	

	
	
	
}
