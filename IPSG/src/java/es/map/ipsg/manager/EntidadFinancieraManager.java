package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.EntidadFinancieraQuery;
import es.map.ips.model.EstadoProceso;
import es.map.ips.model.IpsAuditoriaProcesoEEFF;
import es.map.ipsg.bean.EntidadFinancieraBean;

public interface EntidadFinancieraManager {
	public ArrayList<EntidadFinancieraBean> buscarEntidadFinancieraCombo(EntidadFinancieraQuery entidadFinancieraQuery);
	public Integer guardarEntidadFinanciera(EntidadFinancieraBean entidadFinancieraBean);
	public ArrayList<EntidadFinancieraBean> buscarEntidadFinancieraAll(EntidadFinancieraQuery entidadFinancieraQuery);
	public EntidadFinancieraBean obtenerEntidadFinanciera(Integer idEntidadFinanciera);
	public void modificarEntidadFinanciera(EntidadFinancieraBean entidadFinancieraBean);
	public ArrayList<EntidadFinancieraBean> buscarEntidadAdeudoCombo();
	public ArrayList<EntidadFinancieraBean> buscarEntidadTarjetaCombo();
	public Integer guardarAuditoriaProcesoEEFF(IpsAuditoriaProcesoEEFF ipsAuditoriaEEFF);
	public EstadoProceso obtenerEstadoProceso(String estado);
} 
