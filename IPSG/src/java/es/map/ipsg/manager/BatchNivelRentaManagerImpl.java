package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.BatchNivelRentaDAO;
import es.map.ips.model.BatchNivelRenta;
import es.map.ips.model.BatchNivelRentaQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ipsg.bean.BatchNivelRentaBean;



public class BatchNivelRentaManagerImpl implements BatchNivelRentaManager {

	private BatchNivelRentaDAO batchNivelRentaDAO;

	private SearchResult<BatchNivelRenta> buscarPeticion(BatchNivelRentaQuery batchNivelRentaQuery) {
		ApplicationException.assertNotNull(batchNivelRentaQuery, "BatchNivelRentaQuery no puede ser null");
	
		return batchNivelRentaDAO.search(batchNivelRentaQuery);
	}
	
	@Override
	public ArrayList<BatchNivelRentaBean> buscarPeticionesAll(BatchNivelRentaQuery batchNivelRentaQuery) {
		SearchResult<BatchNivelRenta> peticion = buscarPeticion(batchNivelRentaQuery);
		
		ArrayList<BatchNivelRentaBean> arrPeticionesNivelRenta = new ArrayList<BatchNivelRentaBean>();
		
		for(int i=0;i<peticion.size();i++){
			BatchNivelRentaBean aux;
			aux = toBatchNivelRentaBean(peticion.getResults().get(i));
			if(aux != null){
				arrPeticionesNivelRenta.add(aux);
			}
		}	
		return arrPeticionesNivelRenta;	
	}
	
	private BatchNivelRentaBean toBatchNivelRentaBean(BatchNivelRenta batchNivelRenta){

		BatchNivelRentaBean auxNivelRenta = new BatchNivelRentaBean();
		
		if(batchNivelRenta.getIdConsulta() != null){
			auxNivelRenta.setIdConsulta(batchNivelRenta.getIdConsulta());
		}
		
		auxNivelRenta.setIdUsuarioConsulta(batchNivelRenta.getUsuario());
		auxNivelRenta.setFhInicioConsulta(batchNivelRenta.getFhInicioConsulta());
		auxNivelRenta.setFhFinConsulta(batchNivelRenta.getFhFinConsulta());
		auxNivelRenta.setReintentos(batchNivelRenta.getReintentos());
		auxNivelRenta.setProcesado(batchNivelRenta.getProcesado());
		auxNivelRenta.setNumElementos(batchNivelRenta.getNumElementos());
		
		ArrayList<SolicitudComun> listaSolicitudes = new ArrayList<SolicitudComun>();
		for (Iterator<SolicitudComun> iter = batchNivelRenta.getSolicitudComun().iterator(); iter.hasNext();) {
			SolicitudComun solicitud = (SolicitudComun) iter.next();
			if (solicitud != null) {
				listaSolicitudes.add(solicitud);
			}		
		}
		auxNivelRenta.setSolicitudes(listaSolicitudes);
		
		//Traemos la informacion completa lazy
		batchNivelRenta.getUsuario().getPerfil();
		auxNivelRenta.setIdUsuarioConsulta(batchNivelRenta.getUsuario());
		
		return auxNivelRenta;
		
	}
	
	private BatchNivelRenta toBatchNivelRenta(BatchNivelRentaBean NivelRentaBean){

		BatchNivelRenta batchNivelRenta = new BatchNivelRenta();
		
		if(NivelRentaBean.getIdConsulta() != null){
			batchNivelRenta.setIdConsulta(NivelRentaBean.getIdConsulta());
		}
		
		batchNivelRenta.setUsuario(NivelRentaBean.getIdUsuarioConsulta());
		batchNivelRenta.setFhInicioConsulta(NivelRentaBean.getFhInicioConsulta());
		batchNivelRenta.setFhFinConsulta(NivelRentaBean.getFhFinConsulta());
		batchNivelRenta.setReintentos(NivelRentaBean.getReintentos());
		batchNivelRenta.setProcesado(NivelRentaBean.getProcesado());
		batchNivelRenta.setNumElementos(NivelRentaBean.getNumElementos());
		
		Set<SolicitudComun> listaSolicitudes = new HashSet<SolicitudComun>(NivelRentaBean.getSolicitudes());
		
		batchNivelRenta.setSolicitudComun(listaSolicitudes);
		
		
		return batchNivelRenta;
		
	}

	@Override
	public void actualizarNivelRenta(BatchNivelRentaBean batchNivelRentaBean, int idConsulta) {
		BatchNivelRenta batchNivelRenta = toBatchNivelRenta(batchNivelRentaBean);
		batchNivelRenta.setIdConsulta(idConsulta);
		batchNivelRentaDAO.update(batchNivelRenta);
	}

	@Override
	public Integer guardarBatchNivelRenta(BatchNivelRentaBean batchNivelRentaBean) {
		BatchNivelRenta batchNivelRenta = toBatchNivelRenta(batchNivelRentaBean);
		return batchNivelRentaDAO.insert(batchNivelRenta);
	}

	public BatchNivelRentaDAO getBatchNivelRentaDAO() {
		return batchNivelRentaDAO;
	}

	public void setBatchNivelRentaDAO(BatchNivelRentaDAO batchNivelRentaDAO) {
		this.batchNivelRentaDAO = batchNivelRentaDAO;
	}
	
}
