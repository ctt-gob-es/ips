package es.map.ipsg.manager;

import java.util.Date;

import es.map.ips.dao.EstadoProcesoDAO;
import es.map.ips.dao.IpsAuditoriaProcesoEEFFDAO;

public class BatchAuditoriaEntidadesEEFFManagerImpl implements BatchAuditoriaEntidadesEEFFManager {

	private IpsAuditoriaProcesoEEFFDAO ipsAuditoriaProcesoEEFFDAO;
	private EstadoProcesoDAO estadoProcesoDAO;

	@Override
	public void guardarResultado(Date fecha, String resultado, String desResultado) {
		// TODO Auto-generated method stub
		
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
