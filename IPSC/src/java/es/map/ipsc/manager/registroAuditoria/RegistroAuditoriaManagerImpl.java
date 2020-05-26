package es.map.ipsc.manager.registroAuditoria;

import org.apache.log4j.Logger;
import es.map.ips.dao.RegistroAuditoriaDAO;
import es.map.ips.model.RegistroAuditoria;
import es.map.ips.model.SolicitudComun;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.AuditoriaBean;

/**
 * El Class RegistroAuditoriaManagerImpl.
 */
public class RegistroAuditoriaManagerImpl implements RegistroAuditoriaManager {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(RegistroAuditoriaManagerImpl.class);
	
	/** el registro auditoria DAO. */
	private RegistroAuditoriaDAO registroAuditoriaDAO;

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.registroAuditoria.RegistroAuditoriaManager#guardarRegistroAuditoria(es.map.ipsc.bean.AuditoriaBean)
	 */
	@Override
	public void guardarRegistroAuditoria(AuditoriaBean auditoriaBean) {
		
		RegistroAuditoria registroAuditoria = toRegistroAuditoria(auditoriaBean);
		try{
			registroAuditoriaDAO.insert(registroAuditoria);
		}catch (Exception e){
			logger.error("Error al insertar datos de auditoria:",e);
		
		}
	}

	/**
	 * To registro auditoria.
	 *
	 * @param auditoriaBean el auditoria bean
	 * @return el registro auditoria
	 */
	private RegistroAuditoria toRegistroAuditoria(AuditoriaBean auditoriaBean) {
		
		RegistroAuditoria registroAuditoria = new RegistroAuditoria();
		// Guardamos la solicitud comun
		if(auditoriaBean.getIdSolicitud()!=null){		
			SolicitudComun solicitudComun = new SolicitudComun();
			solicitudComun.setIdSolicitud(auditoriaBean.getIdSolicitud());
			registroAuditoria.setSolicitudComun(solicitudComun);
		}
		// Guardamos la fecha de autenticación
		if(auditoriaBean.getFechaAutenticacion()!=null){
			registroAuditoria.setFechaAutenticacion(auditoriaBean.getFechaAutenticacion());
		}
		// Guardamos el NIF
		if(auditoriaBean.getNif()!=null){
			registroAuditoria.setNif(auditoriaBean.getNif());
		}
		
		// Guardamos el NOMBRE
		if(auditoriaBean.getNombre()!=null){
			registroAuditoria.setNombre(auditoriaBean.getNombre());
		}
		
		// Guardamos el Primer Apellido
		if(auditoriaBean.getPrimerApellido()!=null){
			registroAuditoria.setPrimerApellido(auditoriaBean.getPrimerApellido());
		}		
		// Guardamos el Segundo Apellido
		if(auditoriaBean.getSegundoApellido()!=null){
			registroAuditoria.setSegundoApellido(auditoriaBean.getSegundoApellido());
		}		
		// Guardamos la IP del ciudadano
		if(auditoriaBean.getIpSolicitante()!=null){
			registroAuditoria.setIpSolicitante(auditoriaBean.getIpSolicitante());
		}
		
		// Guardamos los datos del navegador del ciudadano
		if(auditoriaBean.getDatosNavegador()!=null){
			registroAuditoria.setDatosNavegador(auditoriaBean.getDatosNavegador());
		}
		
		// Guardamos el proveedor de identidad
		if(auditoriaBean.getProveedorIdentidad()!=null){
			registroAuditoria.setPvi(auditoriaBean.getProveedorIdentidad());
		}
			
		// Guardamos el emisor del certificado
		if(auditoriaBean.getOrganizacionEmisora()!=null){
			registroAuditoria.setPsc(auditoriaBean.getOrganizacionEmisora());
		}
		
		// Guardamos el número de serie del certificado
		if(auditoriaBean.getNumeroSerie()!=null){
			registroAuditoria.setNumeroSerie(auditoriaBean.getNumeroSerie());
		}
		
		// Guardamos la respuesta de Cl@ve
		if(auditoriaBean.getRespuestaClave()!=null){
			registroAuditoria.setRespuestaClave(auditoriaBean.getRespuestaClave());
		}
						
		// Guardamos el tipo de persona
		if(auditoriaBean.getTipoPersona()!=null){
			registroAuditoria.setTipoPersona(auditoriaBean.getTipoPersona());
		} else {
			// no puede ser null por tanto se inserta por defecto 'C'
			registroAuditoria.setTipoPersona(Constantes.TIPO_PERSONA_CIUDADANO);
		}
						
		return registroAuditoria;
	}

	/**
	 * Obtiene el registro auditoria DAO.
	 *
	 * @return el registro auditoria DAO
	 */
	public RegistroAuditoriaDAO getRegistroAuditoriaDAO() {
		return registroAuditoriaDAO;
	}

	/**
	 * Establece el registro auditoria DAO.
	 *
	 * @param registroAuditoriaDAO el nuevo registro auditoria DAO
	 */
	public void setRegistroAuditoriaDAO(RegistroAuditoriaDAO registroAuditoriaDAO) {
		this.registroAuditoriaDAO = registroAuditoriaDAO;
	}
}
