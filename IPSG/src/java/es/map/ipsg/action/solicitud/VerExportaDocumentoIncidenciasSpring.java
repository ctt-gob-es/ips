package es.map.ipsg.action.solicitud;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ipsg.form.BuscarSolicitudesForm;
import es.map.ipsg.form.SolicitudesIncidenciasForm;
import es.map.ipsg.manager.IncidenciasManager;

/**
 * El Class VerExportaDocumentoIncidenciasSpring.
 */
public class VerExportaDocumentoIncidenciasSpring extends AbstractSpring {
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(VerExportaDocumentoIncidenciasSpring.class);

	/** el incidencias manager. */
	private IncidenciasManager incidenciasManager;

		
	/**
	 * Crea una nueva ver exporta documento incidencias spring.
	 */
	public VerExportaDocumentoIncidenciasSpring() {
		try {
			setIncidenciasManager((IncidenciasManager) getBean("incidenciasManager"));
			
		} catch (Exception e) {
			logger.error(" Error VerExportaDocumentoIncidenciasSpring:" ,e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		//Cogemos el form del jsp
		SolicitudesIncidenciasForm formulario;
		formulario = (SolicitudesIncidenciasForm) form;
		
		formulario.setAnexo(false);
		formulario.setStrAnexo("N");
		formulario.setJustificaPago(false);
		formulario.setStrJustificaPago("N");
		formulario.setRegistroPDF(false);
		formulario.setStrRegistroPDF("N");
		formulario.setRegistroXML(false);
		formulario.setStrRegistroXML("N");
		
		if (this.getRequest().getParameterValues("sol") != null) {
			formulario.setSolicitudesSel(this.getRequest().getParameterValues("sol") );
		}
		
		if (this.getRequest().getParameter("m") != null) {
			formulario.setMarcarTodo(this.getRequest().getParameter("m"));
		}
		
		return "success";
	}

	/**
	 * Obtiene el incidencias manager.
	 *
	 * @return el incidencias manager
	 */
	public IncidenciasManager getIncidenciasManager() {
		return incidenciasManager;
	}

	/**
	 * Establece el incidencias manager.
	 *
	 * @param incidenciasManager el nuevo incidencias manager
	 */
	public void setIncidenciasManager(IncidenciasManager incidenciasManager) {
		this.incidenciasManager = incidenciasManager;
	}

	


}
