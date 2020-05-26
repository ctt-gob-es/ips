package es.map.ipsc.manager.registroAuditoria;

import java.util.ArrayList;

import es.map.ips.model.ProvinciaExamenQuery;
import es.map.ipsc.bean.AuditoriaBean;
import es.map.ipsc.bean.ProvinciaExamenBean;

/**
 * El Interface RegistroAuditoriaManager.
 */
public interface RegistroAuditoriaManager {

	 /**
 	 * Guardar registro auditoria.
 	 *
 	 * @param auditoriaBean el auditoria bean
 	 */
 	void guardarRegistroAuditoria(AuditoriaBean auditoriaBean);



}