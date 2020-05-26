package es.map.ipsc.manager.aviso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.AvisoDAO;
import es.map.ips.model.Aviso;
import es.map.ips.model.AvisoQuery;
import es.map.ipsc.bean.AvisoBean;
import es.map.ipsc.utils.IpsUtils;


/**
 * El Class AvisoManagerImpl.
 */
public class AvisoManagerImpl implements AvisoManager {
	
	/** el aviso DAO. */
	private AvisoDAO avisoDAO;

	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.aviso.AvisoManager#buscarAvisos(es.map.ips.model.AvisoQuery)
	 */
	public ArrayList<AvisoBean> buscarAvisos(AvisoQuery avisoQuery) {
		SearchResult<Aviso> avisos = avisoDAO.search(avisoQuery);
		if(avisos == null){
			return null;
		}
		ArrayList<AvisoBean> avisosBean = new ArrayList<AvisoBean>();
		for(int i=0;i<avisos.getResults().size();i++){
			AvisoBean aux;
			aux = toAvisoBean(avisos.getResults().get(i));
			if(aux != null){
				avisosBean.add(aux);
			}else{
				return null;
			}
		}
		return avisosBean;
	}
	
	
	/**
	 * To aviso bean.
	 *
	 * @param aviso el aviso
	 * @return el aviso bean
	 */
	private AvisoBean toAvisoBean(Aviso aviso) {
		SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");
		AvisoBean aux = new AvisoBean();
		aux.setId(aviso.getId());
		if(aviso.getTexto() != null){
			aux.setTexto(IpsUtils.replaceIntrosIgnoreCase(aviso.getTexto()));
		}
		if(aviso.getFechaInicio() != null){
			String fechaInicio = formatoFecha.format(aviso.getFechaInicio());
			aux.setFechaInicio(fechaInicio);
		}
		if(aviso.getFechaFin() != null){
			String fechaFin = formatoFecha.format(aviso.getFechaFin());
			aux.setFechaFin(fechaFin);
		}
		if(aviso.getEstadoAviso() != null){
			aux.setIdEstadoAviso(aviso.getEstadoAviso().getId().toString());
		}
		return aux;
	}


	/**
	 * Obtiene el aviso DAO.
	 *
	 * @return el aviso DAO
	 */
	public AvisoDAO getAvisoDAO() {
		return avisoDAO;
	}

	/**
	 * Establece el aviso DAO.
	 *
	 * @param avisoDAO el nuevo aviso DAO
	 */
	public void setAvisoDAO(AvisoDAO avisoDAO) {
		this.avisoDAO = avisoDAO;
	}

	
	
	
	
	
	
}
