package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.BatchNivelRentaAuxDAO;
import es.map.ips.model.BatchNivelRentaAux;
import es.map.ips.model.BatchNivelRentaAuxQuery;
import es.map.ips.model.SolicitudComunAuxiliar;
import es.map.ipsg.bean.BatchNivelRentaAuxBean;



public class BatchNivelRentaAuxManagerImpl implements BatchNivelRentaAuxManager {

	private BatchNivelRentaAuxDAO batchNivelRentaAuxDAO;

	private SearchResult<BatchNivelRentaAux> buscarPeticion(BatchNivelRentaAuxQuery BatchNivelRentaAuxQuery) {
		ApplicationException.assertNotNull(BatchNivelRentaAuxQuery, "BatchNivelRentaAuxQuery no puede ser null");
	
		return batchNivelRentaAuxDAO.search(BatchNivelRentaAuxQuery);
	}
	
	@Override
	public ArrayList<BatchNivelRentaAuxBean> buscarPeticionesAll(BatchNivelRentaAuxQuery BatchNivelRentaAuxQuery) {
		SearchResult<BatchNivelRentaAux> peticion = buscarPeticion(BatchNivelRentaAuxQuery);
		
		ArrayList<BatchNivelRentaAuxBean> arrPeticionesNivelRenta = new ArrayList<BatchNivelRentaAuxBean>();
		
		for(int i=0;i<peticion.size();i++){
			BatchNivelRentaAuxBean aux;
			aux = toBatchNivelRentaAuxBean(peticion.getResults().get(i));
			if(aux != null){
				arrPeticionesNivelRenta.add(aux);
			}
		}	
		return arrPeticionesNivelRenta;	
	}
	
	private BatchNivelRentaAuxBean toBatchNivelRentaAuxBean(BatchNivelRentaAux batchNivelRentaAux){

		BatchNivelRentaAuxBean auxNivelRentaAux = new BatchNivelRentaAuxBean();
		
		if(batchNivelRentaAux.getIdConsultaAuxiliar() != null){
			auxNivelRentaAux.setIdConsultaAux(batchNivelRentaAux.getIdConsultaAuxiliar());
		}
		
		auxNivelRentaAux.setIdUsuarioConsulta(batchNivelRentaAux.getUsuario());
		auxNivelRentaAux.setFhInicioConsulta(batchNivelRentaAux.getFhInicioConsulta());
		auxNivelRentaAux.setFhFinConsulta(batchNivelRentaAux.getFhFinConsulta());
		auxNivelRentaAux.setReintentos(batchNivelRentaAux.getReintentos());
		auxNivelRentaAux.setProcesado(batchNivelRentaAux.getProcesado());
		auxNivelRentaAux.setNumElementos(batchNivelRentaAux.getNumElementos());
		
		ArrayList<SolicitudComunAuxiliar> listaSolicitudes = new ArrayList<SolicitudComunAuxiliar>();
		for (Iterator<SolicitudComunAuxiliar> iter = batchNivelRentaAux.getSolicitudComunAuxiliar().iterator(); iter.hasNext();) {
			SolicitudComunAuxiliar solicitud = (SolicitudComunAuxiliar) iter.next();
			if (solicitud != null) {
				listaSolicitudes.add(solicitud);
			}		
		}
		auxNivelRentaAux.setSolicitudesAuxiliares(listaSolicitudes);
		
		//Traemos la informacion completa lazy
		batchNivelRentaAux.getUsuario().getPerfil();
		auxNivelRentaAux.setIdUsuarioConsulta(batchNivelRentaAux.getUsuario());
		
		return auxNivelRentaAux;
		
	}
	
	private BatchNivelRentaAux toBatchNivelRenta(BatchNivelRentaAuxBean NivelRentaAuxBean){

		BatchNivelRentaAux batchNivelRenta = new BatchNivelRentaAux();
		
		if(NivelRentaAuxBean.getIdConsultaAux() != null){
			batchNivelRenta.setIdConsultaAuxiliar(NivelRentaAuxBean.getIdConsultaAux());
		}
		
		batchNivelRenta.setUsuario(NivelRentaAuxBean.getIdUsuarioConsulta());
		batchNivelRenta.setFhInicioConsulta(NivelRentaAuxBean.getFhInicioConsulta());
		batchNivelRenta.setFhFinConsulta(NivelRentaAuxBean.getFhFinConsulta());
		batchNivelRenta.setReintentos(NivelRentaAuxBean.getReintentos());
		batchNivelRenta.setProcesado(NivelRentaAuxBean.getProcesado());
		batchNivelRenta.setNumElementos(NivelRentaAuxBean.getNumElementos());
		
		Set<SolicitudComunAuxiliar> listaSolicitudes = new HashSet<SolicitudComunAuxiliar>(NivelRentaAuxBean.getSolicitudesAuxiliares());
		
		batchNivelRenta.setSolicitudComunAuxiliar(listaSolicitudes);
		
		
		return batchNivelRenta;
		
	}

	@Override
	public void actualizarNivelRentaAux(BatchNivelRentaAuxBean BatchNivelRentaAuxBean, int idConsulta) {
		BatchNivelRentaAux batchNivelRentaAux = toBatchNivelRenta(BatchNivelRentaAuxBean);
		batchNivelRentaAux.setIdConsultaAuxiliar(idConsulta);
		batchNivelRentaAuxDAO.update(batchNivelRentaAux);
	}

	@Override
	public Integer guardarBatchNivelRentaAux(BatchNivelRentaAuxBean batchNivelRentaAuxBean) {
		BatchNivelRentaAux batchNivelRentaAux = toBatchNivelRenta(batchNivelRentaAuxBean);
		return batchNivelRentaAuxDAO.insert(batchNivelRentaAux);
	}

	public BatchNivelRentaAuxDAO getBatchNivelRentaAuxDAO() {
		return batchNivelRentaAuxDAO;
	}

	public void setBatchNivelRentaAuxDAO(BatchNivelRentaAuxDAO batchNivelRentaAuxDAO) {
		this.batchNivelRentaAuxDAO = batchNivelRentaAuxDAO;
	}
	
}
