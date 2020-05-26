package es.map.ipsc.spring.solicitudes;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.SolicitudComunAuxiliarQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.DetalleSolicitudBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.form.CargaJustificanteAjaxForm;
import es.map.ipsc.manager.solicitudes.SolicitudComunAuxiliarManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.spring.AbstractSecureSpring;

/**
 * El Class CargaJustificanteAjaxSpring.
 */
public class CargaJustificanteAjaxSpring extends AbstractSecureSpring {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CargaJustificanteAjaxSpring.class);
	
	/** La constante STRING_ERRORCONVERSIONJSON. */
	private static final String STRING_ERRORCONVERSIONJSON = "Error conversion de un objeto a String JSON";
	
	/** el solicitudes manager. */
	private SolicitudManager solicitudesManager;
	
	/** el solicitud comun auxiliar manager. */
	private SolicitudComunAuxiliarManager solicitudComunAuxiliarManager;
	
	/**
	 * Crea una nueva carga justificante ajax spring.
	 */
	public CargaJustificanteAjaxSpring() {
		try{
			setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
			setSolicitudComunAuxiliarManager((SolicitudComunAuxiliarManager) getBean("solicitudComunAuxiliarManager"));
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error cargar justificante Ajax",e);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
	
		CargaJustificanteAjaxForm formulario = (CargaJustificanteAjaxForm) form;
		
		String numJustPresencial = formulario.getNumJustificantePresencial();
		String idConvocatoria = formulario.getIdConvocatoria();
				
		// 1º VALIDACION
		// busqueda en bb.dd si existe una solicitud presencial registrada con ese numero de justificante para dicha convocatoria
		SolicitudComunAuxiliarQuery solicitudComunAuxiliarQuery = new SolicitudComunAuxiliarQuery();
		solicitudComunAuxiliarQuery.setNumeroSolicitud(numJustPresencial);
		solicitudComunAuxiliarQuery.addConvocatoriaIn(Long.parseLong(idConvocatoria));
		solicitudComunAuxiliarQuery.setJoin_convocatoria(true);
		List<DetalleSolicitudBean> detallesSolicitudesAuxiliares = solicitudComunAuxiliarManager.detalleSolicitudesBean(solicitudComunAuxiliarQuery);
	
		// 2º VALIDACION
		// busqueda en bb.dd si esa persona ya esta registrada en esa convocatoria oficialmente
		SolicitudComunQuery solicitudQueryComprobar = new SolicitudComunQuery();
											
		solicitudQueryComprobar.setNumeroSolicitud(numJustPresencial);
		solicitudQueryComprobar.addConvocatoriaIn(Long.parseLong(idConvocatoria));
		solicitudQueryComprobar.setJoin_convocatoria(true);
		solicitudQueryComprobar.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_NOPAGADO);
		solicitudQueryComprobar.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_NOREGISTRADO);
		solicitudQueryComprobar.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_REGISTRADO);
		solicitudQueryComprobar.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_PENDIENTE_REGISTRO);
		solicitudQueryComprobar.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_PROCESO_PAGO);
		solicitudQueryComprobar.addEstadoSolicitudIn(Constantes.SOLICITUD_ID_PROCESO_REGISTRO);
													
		SolicitudBean solicitud = solicitudesManager.buscarRegistroSolicitud(solicitudQueryComprobar);
						
		String jsonInString = "";
		// si existen solicitudes auxiliares
		if (detallesSolicitudesAuxiliares!=null && !detallesSolicitudesAuxiliares.isEmpty()) {
			// se valida el numero de justificante
			ObjectMapper mapper = new ObjectMapper();
			try {
				// Conversion de cada objeto detalleSolicitudBean a un String JSON con pretty print
				jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(detallesSolicitudesAuxiliares);
			} catch (JsonGenerationException e) {
				logger.error(STRING_ERRORCONVERSIONJSON,e);
			} catch (JsonMappingException e) {
				logger.error(STRING_ERRORCONVERSIONJSON,e);
			} catch (IOException e) {
				logger.error(STRING_ERRORCONVERSIONJSON,e);
			}
		} else {
			// no se valida el numero de justificante al no exisitir solicitud auxiliar
			jsonInString = "[{ \"error\" : \"No existe solicitud presencial registrada de este nº de justificante para esta convocatoria.\"}]";
		}
		
		if (solicitud!=null && solicitud.getId() > 0) {
			// no se valida porque existe ya una inscripcion de esta solicitud
			jsonInString = "[{ \"error\" : \"Este nº de justificante ya está registrado en esta esta convocatoria. Compruebe su estado en \'Mis inscripciones\'\"}]";
		} 
				
		// respuesta javascript 
		PrintWriter out = this.getResponse().getWriter();
		out.write(jsonInString);
		out.flush();
		logger.info(jsonInString);	
		
		return null;
	}

	/**
	 * Obtiene el solicitudes manager.
	 *
	 * @return el solicitudes manager
	 */
	public SolicitudManager getSolicitudesManager() {
		return solicitudesManager;
	}

	/**
	 * Establece el solicitudes manager.
	 *
	 * @param solicitudesManager el nuevo solicitudes manager
	 */
	public void setSolicitudesManager(SolicitudManager solicitudesManager) {
		this.solicitudesManager = solicitudesManager;
	}

	/**
	 * Obtiene el solicitud comun auxiliar manager.
	 *
	 * @return el solicitud comun auxiliar manager
	 */
	public SolicitudComunAuxiliarManager getSolicitudComunAuxiliarManager() {
		return solicitudComunAuxiliarManager;
	}

	/**
	 * Establece el solicitud comun auxiliar manager.
	 *
	 * @param solicitudComunAuxiliarManager el nuevo solicitud comun auxiliar manager
	 */
	public void setSolicitudComunAuxiliarManager(SolicitudComunAuxiliarManager solicitudComunAuxiliarManager) {
		this.solicitudComunAuxiliarManager = solicitudComunAuxiliarManager;
	}
}

