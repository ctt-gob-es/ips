package es.map.ipsg.quartz;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import es.agenciatributaria.www.respuesta.Entidad;
import es.agenciatributaria.www.respuesta.Medio;
import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ips.model.EntidadFinancieraQuery;
import es.map.ips.model.IpsAuditoriaProcesoEEFF;
import es.map.ipsg.action.entidadFinanciera.ActualizarEntidadFinancieraSpring;
import es.map.ipsg.agenciaTributariaWS.entidades.GetListEntidades;
import es.map.ipsg.agenciaTributariaWS.entidades.GetListEntidadesImpl;
import es.map.ipsg.bean.EntidadFinancieraBean;
import es.map.ipsg.manager.EntidadFinancieraManager;
import es.map.ipsg.util.Constantes;


public class ActualizarEntidadFinancieraAutomaticasAction {	
	
			
	private static final String PROCESADO = "PROCESADO";
	private static final String PROCESO_ACTUALIZACION_EEFF = "PROCESO ACTUALIZACION EEFF AUTOMATICA";
	private static final String NO_PROCESADO = "NO PROCESADO";
	private static final Logger logger = Logger.getLogger(ActualizarEntidadFinancieraSpring.class);
	private static final String STRING_CON_CODIGO = " con código ";

	// Declaracion de manager
	private EntidadFinancieraManager entidadFinancieraManager;
	
	public void executeTest(EntidadFinancieraManager entidadFinancieraManager){
		setEntidadFinancieraManager(entidadFinancieraManager);
	}
	
	public void execute() {
		getLogger().debug("Batch de ActualizarEntidadFinancieraAutomaticasAction -start");

		try {
			entidadFinancieraManager = (EntidadFinancieraManager) ApplicationContextProvider.getInstance().getBean("entidadFinancieraManager");
		} catch (Exception e) {
			logger.info("Contexto cargado por ejecucion de Test.",e);
		}
		

		EntidadFinancieraQuery entidadFinancieraQuery = new EntidadFinancieraQuery();
		

		//interface para WS
		//Llamada al WebService
		ArrayList<EntidadFinancieraBean> entidadFinancieraLista = entidadFinancieraManager.buscarEntidadFinancieraCombo(entidadFinancieraQuery);
		ArrayList<EntidadFinancieraBean> entidadFinancieraAlta = new ArrayList<EntidadFinancieraBean>();
		ArrayList<EntidadFinancieraBean> entidadFinancieraMod = new ArrayList<EntidadFinancieraBean>();
		ArrayList<EntidadFinancieraBean> entidadFinancieraBajas = new ArrayList<EntidadFinancieraBean>();
		ArrayList<Entidad> entidad = new ArrayList<Entidad>();
		
		try {
			
			GetListEntidades resultado = new GetListEntidadesImpl();			
		    entidad = resultado.peticion();
		    
		    for (int i=0; i<entidad.size(); i++) {
				logger.info("Entidad "+entidad.get(i).getDescripcion()+" "+entidad.get(i).getCodigo());
			}
		    
		    
		    List<Medio> listaMediosPago = null;
		    
		    logger.info("*******Se han recuperado "+ entidad.size()+" entidades a traves del WS************");
		    
			for(int i = 0; i < entidad.size() ; i++ ){	
				logger.info("******* Comprobando Entidad "+ entidad.get(i).getDescripcion()+STRING_CON_CODIGO+entidad.get(i).getCodigo()+" ************");
				boolean alta = true;
				for(int a = 0; a < entidadFinancieraLista.size() ; a++){
					
					EntidadFinancieraBean entidadFinancieraBeanAux = entidadFinancieraLista.get(a);
					//Si  la entidad se encuentra la actualizamos con los datos 
					if(entidad.get(i).getCodigo().equalsIgnoreCase(entidadFinancieraBeanAux.getCodigo())){
						logger.info("******* ENCONTRADA ******");					
						entidadFinancieraBeanAux.setDescripcion(entidad.get(i).getDescripcion());
						
						 					
						
						Medio[] mediosPago;
				
						mediosPago = entidad.get(i).getMediosPago();
						listaMediosPago = Arrays.asList(mediosPago);
						
						if(!listaMediosPago.isEmpty()){
							Medio elemento1 = listaMediosPago.get(0);
							Medio elemento2 = null;
							
							if(listaMediosPago.size() > 1){
								elemento2 = listaMediosPago.get(1);
								if(elemento1.getValue().equals(Constantes.EEFF_TIPO_PAGO_CARGO_CUENTA) && elemento2.getValue().equals(Constantes.EEFF_TIPO_PAGO_TARJETA))
									entidadFinancieraBeanAux.setIdTipoPago(3);
								
							}
							else{
							 if (elemento1.getValue().equals(Constantes.EEFF_TIPO_PAGO_TARJETA))
									entidadFinancieraBeanAux.setIdTipoPago(1);
								
							 if (elemento1.getValue().equals(Constantes.EEFF_TIPO_PAGO_CARGO_CUENTA)) 
								entidadFinancieraBeanAux.setIdTipoPago(2);
							 
							}
							
						}

						entidadFinancieraBeanAux.setEstado(Character.valueOf(Constantes.ENTIDAD_FINANCIERA_ESTADO_ACTIVO));
						entidadFinancieraBeanAux.setActualizada(Character.valueOf(Constantes.ENTIDAD_FINANCIERA_ACTUALIZADA));
						
						//Actualizar Horarios
						actualizarHorarios(entidad, i, entidadFinancieraBeanAux);
						
						entidadFinancieraManager.modificarEntidadFinanciera(entidadFinancieraBeanAux);
						entidadFinancieraMod.add(entidadFinancieraBeanAux);
						//La descartamos de la lista para borrar
						entidadFinancieraLista.remove(a);
						//Marcamos que ya ha sido encontrada para no darla de alta
						alta = false;
						break;
					}					
				}
				//En caso de no haber sido encontrada la damos de alta
				if(alta){	
					logger.info("******* NO ENCONTRADA ******");
					logger.info(entidad.get(i).getDescripcion()+STRING_CON_CODIGO+entidad.get(i).getCodigo());
					EntidadFinancieraBean entidadFinancieraAltaBean = new EntidadFinancieraBean();
					
					entidadFinancieraAltaBean.setCodigo(entidad.get(i).getCodigo());
					entidadFinancieraAltaBean.setDescripcion(entidad.get(i).getDescripcion());
					
				   
					if(!listaMediosPago.isEmpty()){
						Medio elemento1 = listaMediosPago.get(0);
						Medio elemento2 = null;
						
						if(listaMediosPago.size() > 1){
							elemento2 = listaMediosPago.get(1);
							if(elemento1.getValue().equals(Constantes.EEFF_TIPO_PAGO_CARGO_CUENTA) && elemento2.getValue().equals(Constantes.EEFF_TIPO_PAGO_TARJETA))
								entidadFinancieraAltaBean.setIdTipoPago(3);
							
						}
						else{
						 if (elemento1.getValue().equals(Constantes.EEFF_TIPO_PAGO_TARJETA))
							 entidadFinancieraAltaBean.setIdTipoPago(1);
							
						 if (elemento1.getValue().equals(Constantes.EEFF_TIPO_PAGO_CARGO_CUENTA)) 
							 entidadFinancieraAltaBean.setIdTipoPago(2);
						 
						}
						
					}
					
					entidadFinancieraAltaBean.setEstado(Character.valueOf(Constantes.ENTIDAD_FINANCIERA_ESTADO_ACTIVO));
					entidadFinancieraAltaBean.setActualizada(Character.valueOf(Constantes.ENTIDAD_FINANCIERA_ACTUALIZADA));
					
					//Guardar Horarios
					actualizarHorarios(entidad, i, entidadFinancieraAltaBean);
					
					entidadFinancieraManager.guardarEntidadFinanciera(entidadFinancieraAltaBean);
					entidadFinancieraAlta.add(entidadFinancieraAltaBean);
				}
			}
			
			// Comprobamos que se ha obtenido algún resultado
			//Recorremos la lista de entidades que no se ha actualizado para ver cuales se deben dar de baja
			if(!entidad.isEmpty() && !entidadFinancieraLista.isEmpty()){
				
					for(int b = 0; b < entidadFinancieraLista.size(); b++){
						EntidadFinancieraBean entidadFinancieraBajaBean = entidadFinancieraLista.get(b);
						if(entidadFinancieraBajaBean.getEstado().equals(Constantes.ENTIDAD_FINANCIERA_ESTADO_ACTIVO)){
							entidadFinancieraBajas.add(entidadFinancieraBajaBean);
						}
					}
				
			}
			
			//En el caso que alguna entidad no haya sido actualizada la deshabilitamos
			if(!entidadFinancieraBajas.isEmpty()){
				for(int b = 0; b < entidadFinancieraBajas.size(); b++){
					logger.info("******* BAJA DE ENTIDAD *******");
					logger.info(entidad.get(b).getDescripcion()+STRING_CON_CODIGO+entidad.get(b).getCodigo());
					
					EntidadFinancieraBean entidadFinancieraBajaBean = entidadFinancieraBajas.get(b);
					entidadFinancieraBajaBean.setEstado(Character.valueOf(Constantes.ENTIDAD_FINANCIERA_ESTADO_INACTIVO));
					entidadFinancieraBajaBean.setActualizada(Character.valueOf(Constantes.ENTIDAD_FINANCIERA_ACTUALIZADA));
					entidadFinancieraManager.modificarEntidadFinanciera(entidadFinancieraBajaBean);
				}				
			}

			guardarRegistroAuditoria(entidadFinancieraAlta.size()+entidadFinancieraMod.size(), false);
			
		} catch (Exception e) {
			getLogger().error("Error: "+e);
			logger.error("Error Batch de ActualizarEntidadFinancieraAutomaticasAction - doExecute:",e);
			
			guardarRegistroAuditoria(entidadFinancieraAlta.size()+entidadFinancieraMod.size(), true);
		}
		getLogger().debug("Batch de ActualizarEntidadFinancieraAutomaticasAction -end");
	}

	private void actualizarHorarios(ArrayList<Entidad> entidad, int i,
			EntidadFinancieraBean entidadFinancieraAltaBean) {
		if (entidad.get(i).getHorarios() != null) {
			if (entidad.get(i).getHorarios().getApertura() != null) {
				String apertura = entidad.get(i).getHorarios().getApertura().toString();
				String[] aperturaList = apertura.split(".000");
				entidadFinancieraAltaBean.setApertura(aperturaList != null && aperturaList[0] != null?aperturaList[0]:"");
			}
			if (entidad.get(i).getHorarios().getCierre() != null) {
				String cierre = entidad.get(i).getHorarios().getCierre().toString();
				String[] cierreList = cierre.split(".000");
				entidadFinancieraAltaBean.setCierre(cierreList != null && cierreList[0] != null?cierreList[0]:"");
			}
		}
	}

	/**
	 * Guarda un registro en BD con el resultado de la ejecucion del Proceso
	 * 
	 * @param ipsAuditoriaProcesoEEFF
	 * @param entidadFinancieraAlta
	 * @param entidadFinancieraMod
	 */
	private void guardarRegistroAuditoria(int numEEFFActualizadas, boolean isError) {
		IpsAuditoriaProcesoEEFF ipsAuditoriaProcesoEEFF = new IpsAuditoriaProcesoEEFF();
		ipsAuditoriaProcesoEEFF.setNumEEFFActualizadas(numEEFFActualizadas);
		ipsAuditoriaProcesoEEFF.setNombreProceso(PROCESO_ACTUALIZACION_EEFF);
		ipsAuditoriaProcesoEEFF.setFechaEjecucion(new Date());
		ipsAuditoriaProcesoEEFF.setEstadoProceso(entidadFinancieraManager.obtenerEstadoProceso((isError)?NO_PROCESADO:PROCESADO));
		
		int id=entidadFinancieraManager.guardarAuditoriaProcesoEEFF(ipsAuditoriaProcesoEEFF);
		logger.info("Se ha guardo el registro de la auditoria IPS_AUDITORIA_PROCESO_EEFF correctamente con idAuditoria: "+id);
	}
	
	
	/**
	 * @return the especialidadManager
	 */
	public EntidadFinancieraManager getEntidadFinancieraManager() {
		return entidadFinancieraManager;
	}

	/**
	 * @param especialidadManager
	 *            the especialidadManager to set
	 */
	public void setEntidadFinancieraManager(
			EntidadFinancieraManager entidadFinancieraManager) {
		this.entidadFinancieraManager = entidadFinancieraManager;
	}

	public static Logger getLogger() {
		return logger;
	}

}
