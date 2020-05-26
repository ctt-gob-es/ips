package es.map.ipsg.manager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.SolComunClotesViewDAO;
import es.map.ips.dao.SolComunIncidenciasViewDAO;
import es.map.ips.dao.SolComunPresencialesViewDAO;
import es.map.ips.dao.SolComunRegistradasViewDAO;
import es.map.ips.dao.SolicitudComunDAO;
import es.map.ips.model.CentroGestor;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.CuerpoEscala;
import es.map.ips.model.Especialidad;
import es.map.ips.model.Ministerio;
import es.map.ips.model.Modelo;
import es.map.ips.model.PagoSolicitud;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.RegistroSolicitud;
import es.map.ips.model.SolComunClotesView;
import es.map.ips.model.SolComunClotesViewQuery;
import es.map.ips.model.SolComunIncidenciasView;
import es.map.ips.model.SolComunIncidenciasViewQuery;
import es.map.ips.model.SolComunPresencialesView;
import es.map.ips.model.SolComunPresencialesViewQuery;
import es.map.ips.model.SolComunRegistradasView;
import es.map.ips.model.SolComunRegistradasViewQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsg.bean.PagoSolicitudBean;
import es.map.ipsg.bean.RegistroSolicitudBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudCcaaBean;
import es.map.ipsg.bean.SolicitudXmlSigpBean;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.UtilesIPSG;
import es.map.ipsg.util.Validacion;


/**
 *  Clase que implementa el SolicitudManager.
 *
 * @author amartinl
 */
public class SolicitudesRegistradasManagerImpl implements SolicitudesRegistradasManager {

	/** el Solicitud comun DAO. */
	//Variables
	private SolicitudComunDAO SolicitudComunDAO;
	
	/** el correo electronico manager. */
	private CorreoElectronicoManager correoElectronicoManager;
	
	/** el sol comun incidencias view DAO. */
	private SolComunIncidenciasViewDAO solComunIncidenciasViewDAO;
	
	/** el sol comun registradas view DAO. */
	private SolComunRegistradasViewDAO solComunRegistradasViewDAO;
	
	/** el sol comun presenciales view DAO. */
	private SolComunPresencialesViewDAO solComunPresencialesViewDAO;
	
	/** el sol comun clotes view DAO. */
	private SolComunClotesViewDAO solComunClotesViewDAO;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SolicitudesRegistradasManagerImpl.class);
	
	/** La constante STRING_SIMPLEDATEFORMAT. */
	private static final String STRING_SIMPLEDATEFORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/** La constante STRING_FECHASTRING. */
	private static final String STRING_FECHASTRING = "FechaString: ";
	
	/** La constante STRING_ERRORPARSEARFECHASOLICITUD. */
	private static final String STRING_ERRORPARSEARFECHASOLICITUD = "No se ha podido parsear la fecha de solicitud";

	/**
	 * Buscar solicitud combo.
	 *
	 * @param SolicitudQuery SolicitudQuery
	 * @return arrTituloOficial ArrayList
	 */
	public ArrayList<SolicitudBean> buscarSolicitudCombo(SolicitudComunQuery SolicitudQuery){
		
		SearchResult<SolicitudComun> Solicitud = buscarSolicitud(SolicitudQuery);
		ArrayList<SolicitudBean> arrSolicitud = new ArrayList<SolicitudBean>();
		for(int i=0; i < Solicitud.getResults().size(); i++)
		{
			SolicitudBean aux; 
			aux = toSolicitudComboBean(Solicitud.getResults().get(i));
			if(aux != null){
				arrSolicitud.add(aux);
			}
		}
		
		return arrSolicitud;		
	}
	
	/**
	 * Buscar solicitud.
	 *
	 * @param SolicitudQuery el solicitud query
	 * @return el search result
	 */
	private SearchResult<SolicitudComun> buscarSolicitud(SolicitudComunQuery SolicitudQuery) {
			ApplicationException.assertNotNull(SolicitudQuery, "SolicitudQuery no puede ser null");
		
		return SolicitudComunDAO.search(SolicitudQuery);
	}

	
	/**
	 * Buscar solicitud xml sigp all.
	 *
	 * @param SolicitudQuery SolicitudQuery
	 * @return arrsolicitud ArrayList
	 */
	public ArrayList<SolicitudXmlSigpBean> buscarSolicitudXmlSigpAll(SolicitudComunQuery SolicitudQuery){
		SearchResult<SolicitudComun> Solicitud = buscarSolicitud(SolicitudQuery);
		ArrayList<SolicitudXmlSigpBean> arrSolicitudXmlSigp = new ArrayList<SolicitudXmlSigpBean>();
		
		for(int i=0; i < Solicitud.getResults().size(); i++){
			SolicitudXmlSigpBean aux;
			aux = toSolicitudComboBeanXmlSigp(Solicitud.getResults().get(i));
			
			if(aux != null){
				arrSolicitudXmlSigp.add(aux);
			}
		}
		
		return arrSolicitudXmlSigp;	
	}
	
	/*
	 * 
	 * (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudesRegistradasManager#buscarSolicitudAll(es.map.ips.model.SolicitudQuery)
	 */
	public ArrayList<SolicitudXmlSigpBean> buscarSolicitudXmlSigpVista(SolComunRegistradasViewQuery solicitudQuery){
		SearchResult<SolComunRegistradasView> solicitud = solComunRegistradasViewDAO.search(solicitudQuery);
		ArrayList<SolicitudXmlSigpBean> arrSolicitudXmlSigp = new ArrayList<SolicitudXmlSigpBean>();
		for(int i=0; i < solicitud.getResults().size(); i++)
		{
			SolicitudXmlSigpBean aux;
			aux = toSolicitudComboBeanXmlSigpVista(solicitud.getResults().get(i));
			if(aux != null){
				arrSolicitudXmlSigp.add(aux);
			}
		}	
		
		//Datos del pago
		PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
		SolicitudComunQuery solQuery = new SolicitudComunQuery();
		    Iterator<SolicitudXmlSigpBean> it = arrSolicitudXmlSigp.iterator();
		    ArrayList<PagoSolicitudBean> aPagoSolicitud;
			while (it.hasNext())
			{
				SolicitudXmlSigpBean solicitudBean = (SolicitudXmlSigpBean) it.next();
				solQuery.setIdSolicitud(solicitudBean.getIdSolicitud());
				//Completamos los datos de PagoSolicitud
				pagoSolicitudQuery.setSolicitudComun(solQuery);
				pagoSolicitudQuery.setResultado("OK");
				aPagoSolicitud = pagoSolicitudManager.buscarPagoSolicitudAll(pagoSolicitudQuery);
				Iterator<PagoSolicitudBean> itPago = aPagoSolicitud.iterator();
				while (itPago.hasNext())
				{
			    	PagoSolicitudBean pagSol=itPago.next();
			    	
			    	if (pagSol.getResultado()!=null && pagSol.getResultado().equals(Constantes.RESULTADO_OK)){
			    		solicitudBean.setTasas(pagSol.getImporte());
			    		if(pagSol.getMotivoReduccionTasa()!=null){
			    			solicitudBean.setReducTasas(pagSol.getMotivoReduccionTasa().getCodigo());
			    		}
			    		break;
			    	}
			    }
		    }
		return arrSolicitudXmlSigp;	
		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudesRegistradasManager#buscarSolicitudAll(es.map.ips.model.SolicitudComunQuery)
	 */
	public ArrayList<SolicitudBean> buscarSolicitudAll(SolicitudComunQuery SolicitudQuery){		
		SearchResult<SolicitudComun> Solicitud = buscarSolicitud(SolicitudQuery);
		Integer numTotal = Solicitud.size();
		int iNumTotal = 0;
		if (numTotal != null){
			iNumTotal = numTotal.intValue();
		}
		ArrayList<SolicitudBean> arrSolicitud = new ArrayList<SolicitudBean>();
		for(int i=0;i<Solicitud.getResults().size();i++){
			SolicitudBean aux;
			aux = toSolicitudBean(Solicitud.getResults().get(i), iNumTotal);
			if(aux != null){
				arrSolicitud.add(aux);
			}
		}	
		return arrSolicitud;		
	}

	/**
	 * Obtiene el ID y la descripción de un  de Solicitud pasándole el ID.
	 * @param idSolicitud  Integer El ID del título que se desea obtener
	 * @return SolicitudBean Solicitud
	 */
	public SolicitudBean obtenerSolicitud (Long idSolicitud) {
		SolicitudComun Solicitud = SolicitudComunDAO.get(idSolicitud);
		SolicitudBean SolicitudBean = this.toSolicitudBean(Solicitud,0);
		
		return SolicitudBean;
	}
	
	/**
	 * Buscar solicitudes filtro.
	 *
	 * @param solicitudQuery SolicitudQuery
	 * @return arrSolicitud ArrayList
	 */
	public ArrayList<SolicitudBean> buscarSolicitudesFiltro (SolicitudComunQuery solicitudQuery){		
		SearchResult<SolicitudComun> solicitud = buscarSolicitud(solicitudQuery);
		int numTotal = 0;
		if(solicitud.getNumResults() != null)
		{
			numTotal = solicitud.getNumResults();
		}
		ArrayList<SolicitudBean> arrSolicitud = new ArrayList<SolicitudBean>();
		for(int i = 0; i < solicitud.getResults().size(); i++){
			SolicitudBean aux;
			aux = toSolicitudBean (solicitud.getResults().get(i),numTotal);
			if(aux != null){
				arrSolicitud.add(aux);
			}
		}	
		return arrSolicitud;		
	}

	/**
	 * Buscar solicitudes incidencias vista.
	 *
	 * @param solicitudQuery SolicitudesSinIntRegisViewQuery
	 * @return arrSolicitud ArrayList
	 */
	public ArrayList<SolicitudBean> buscarSolicitudesIncidenciasVista (SolComunIncidenciasViewQuery solicitudQuery){		
		
		if(solicitudQuery != null){	
			logger.info("Antes de realizar la busqueda");
			SearchResult<SolComunIncidenciasView> solicitud = solComunIncidenciasViewDAO.search(solicitudQuery);
			logger.info("Después de realizar la busqueda");
			int numTotal = 0;
			if(solicitud.getNumResults() != null){
				numTotal = solicitud.getNumResults();
			}
			
			ArrayList<SolicitudBean> arrSolicitud = new ArrayList<SolicitudBean>();
			
			for(int i = 0; i < solicitud.getResults().size(); i++){
				SolicitudBean aux;
				aux = SolicitudVistatoSolicitudBean (solicitud.getResults().get(i),numTotal);
				if(aux != null){
					arrSolicitud.add(aux);
				}
			}	
			return arrSolicitud;		
		}else{
			return new ArrayList<SolicitudBean>();
		}	
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudesRegistradasManager#buscarSolicitudesRegistradasVista(es.map.ips.model.SolComunRegistradasViewQuery)
	 */
	public ArrayList<SolicitudBean> buscarSolicitudesRegistradasVista (SolComunRegistradasViewQuery solicitudQuery){		
		
		if(solicitudQuery != null){	
			SearchResult<SolComunRegistradasView> solicitud = solComunRegistradasViewDAO.search(solicitudQuery);
			
			int numTotal = 0;
			if(solicitud.getNumResults() != null){
				numTotal = solicitud.getNumResults();
			}
			ArrayList<SolicitudBean> arrSolicitud = new ArrayList<SolicitudBean>();
			for(int i = 0; i < solicitud.getResults().size(); i++){
				SolicitudBean aux;
				aux = SolicitudRegistradaVistatoSolicitudBean (solicitud.getResults().get(i),numTotal);
				if(aux != null){
					arrSolicitud.add(aux);
				}
			}	
			return arrSolicitud;		
		}else{
			return new ArrayList<SolicitudBean>();
		}	
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudesRegistradasManager#buscarSolicitudesPresencialesVista(es.map.ips.model.SolComunPresencialesViewQuery)
	 */
	public ArrayList<SolicitudBean> buscarSolicitudesPresencialesVista (SolComunPresencialesViewQuery solicitudQuery){		
		
		if(solicitudQuery != null){	
			SearchResult<SolComunPresencialesView> solicitud = solComunPresencialesViewDAO.search(solicitudQuery);
			
			int numTotal = 0;
			if(solicitud.getNumResults() != null){
				numTotal = solicitud.getNumResults();
			}
			ArrayList<SolicitudBean> arrSolicitud = new ArrayList<SolicitudBean>();
			for(int i = 0; i < solicitud.getResults().size(); i++){
				SolicitudBean aux;
				aux = SolicitudPresencialVistatoSolicitudBean (solicitud.getResults().get(i),numTotal);
				if(aux != null){
					arrSolicitud.add(aux);
				}
			}	
			return arrSolicitud;		
		}else{
			return new ArrayList<SolicitudBean>();
		}	
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudesRegistradasManager#buscarSolicitudesClotesVista(es.map.ips.model.SolComunClotesViewQuery)
	 */
	public ArrayList<SolicitudBean> buscarSolicitudesClotesVista (SolComunClotesViewQuery solicitudQuery){		
		
		if(solicitudQuery != null){	
			SearchResult<SolComunClotesView> solicitud = solComunClotesViewDAO.search(solicitudQuery);
			
			int numTotal = 0;
			if(solicitud.getNumResults() != null){
				numTotal = solicitud.getNumResults();
			}
			ArrayList<SolicitudBean> arrSolicitud = new ArrayList<SolicitudBean>();
			for(int i = 0; i < solicitud.getResults().size(); i++){
				SolicitudBean aux;
				aux = SolicitudClotesVistatoSolicitudBean (solicitud.getResults().get(i),numTotal);
				if(aux != null){
					arrSolicitud.add(aux);
				}
			}	
			return arrSolicitud;		
		}else{
			return new ArrayList<SolicitudBean>();
		}	
	}
	
	/**
	 * Pasa de Solicitud a SolicitudBean.
	 *
	 * @param solicitud el solicitud
	 * @param numTotal int
	 * @return auxSolicitud SolicitudBean
	 */
	private SolicitudBean toSolicitudBean(SolicitudComun solicitud, int numTotal) {
		SimpleDateFormat sdf = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
		UtilesIPSG utilesIPSG = new UtilesIPSG();

		SolicitudBean auxSolicitud = new SolicitudBean();
		if(solicitud.getIdSolicitud() != null){
			auxSolicitud.setId(solicitud.getIdSolicitud());
		}
		
		auxSolicitud.setNumeroSolicitud(solicitud.getNumeroSolicitud());
		auxSolicitud.setNif(solicitud.getNif());
		auxSolicitud.setNombre(solicitud.getNombre());
		auxSolicitud.setSegundoApellido(solicitud.getSegundoApellido());
		auxSolicitud.setPrimerApellido(solicitud.getPrimerApellido());
		auxSolicitud.setFechaNacimiento(solicitud.getFechaNacimiento());
		
		if(solicitud.getTituloVerificado() != null){
			auxSolicitud.setTituloVerificado(solicitud.getTituloVerificado());
		}	

		if(solicitud.getEdadVerificada() != null){
			auxSolicitud.setEdadVerificada(solicitud.getEdadVerificada());
		}
		
		if(solicitud.getFechaNacimientoVerificada() != null){
			auxSolicitud.setFechaNacimientoVerificada(solicitud.getFechaNacimientoVerificada());
		}	
		if(solicitud.getDesempleoVerificado() != null){
			auxSolicitud.setDesempleoVerificado(solicitud.getDesempleoVerificado());
		}
		if(solicitud.getFnumerosaVerificado() != null){
			auxSolicitud.setFnumerosaVerificado(solicitud.getFnumerosaVerificado());
		}
		
		if(solicitud.getDiscapacidadVerificado() != null){
			auxSolicitud.setDiscapacidadVerificado(solicitud.getDiscapacidadVerificado() );
		}
				
		if(solicitud.getTipoSolicitud() != null){
			auxSolicitud.setTipo(solicitud.getTipoSolicitud());
			auxSolicitud.setTipoDescripcion(solicitud.getTipoSolicitud().getDescripcion());
		}
		auxSolicitud.setId(solicitud.getIdSolicitud());
		
		if (solicitud.getFechaSolicitud() != null) {
			try{
				logger.info(STRING_FECHASTRING+ sdf.format(solicitud.getFechaSolicitud()));
				auxSolicitud.setFechaSolicitud(sdf.format(solicitud.getFechaSolicitud()));
			}catch(Exception e){
				logger.error(STRING_ERRORPARSEARFECHASOLICITUD+ solicitud.getFechaSolicitud() ,e);			
			}
		}

		if (solicitud.getTipoSolicitud()!=null){			
			auxSolicitud.setTipoDescripcion(solicitud.getTipoSolicitud().getDescripcion());
		}
		auxSolicitud.setNumTotal(numTotal);
		
		//Para el resultado de la búsqueda de solicitudes Registradas
		if(solicitud.getConvocatoria() != null){
			auxSolicitud.setEjercicio(solicitud.getConvocatoria().getEjercicio());
			auxSolicitud.setSiglasCentroGestor(solicitud.getConvocatoria().getCuerpoEscala().getCentroGestor().getSiglas());
			auxSolicitud.setCentroGestor(solicitud.getConvocatoria().getCuerpoEscala().getCentroGestor().getDescripcion());
			auxSolicitud.setDesCuerpoEscala(solicitud.getConvocatoria().getCuerpoEscala().getDescripcion());
			auxSolicitud.setIdConvocatoria(solicitud.getConvocatoria().getId());
			auxSolicitud.setConvocatoria(solicitud.getConvocatoria());
			if(null != solicitud.getModelo() && null != solicitud.getModelo().getIdModelo()){
				auxSolicitud.setIdModelo(solicitud.getModelo().getIdModelo().toString());
			}
		}
		
		if(solicitud.getEstadoSolicitud() != null){
			auxSolicitud.setEstadoSolicitud(solicitud.getEstadoSolicitud());
			auxSolicitud.setIdEstadoSolicitud(solicitud.getEstadoSolicitud().getId());
			auxSolicitud.setDescEstadoSolicitud((solicitud.getEstadoSolicitud().getDescripcion()));
		}
		
		auxSolicitud.setEmail(solicitud.getEmail());
		auxSolicitud.setTelefono(solicitud.getTelefono());
		
		//Exportar Excel
		if(solicitud.getEspecialidad() != null){
			auxSolicitud.setEspecialidad(solicitud.getEspecialidad());
			
			if(solicitud.getEspecialidad().getDescripcion() != null){
				auxSolicitud.setDescripcionEspecialidad(solicitud.getEspecialidad().getDescripcion());
			}
		}
		
		auxSolicitud.setSexo(solicitud.getSexo());
		
		if(solicitud.getProvinciaByIdProvinciaNacimiento() != null){
			auxSolicitud.setProvinciaByIdProvinciaNacimiento(solicitud.getProvinciaByIdProvinciaNacimiento());
			if(solicitud.getProvinciaByIdProvinciaNacimiento().getDescripcion() != null){
				auxSolicitud.setDescripcionIdProvinciaNacimiento(solicitud.getProvinciaByIdProvinciaNacimiento().getDescripcion());
			}
		}
		
		auxSolicitud.setLocalidadNacimiento(solicitud.getLocalidadNacimiento());
		auxSolicitud.setNacionalidad(solicitud.getNacionalidad());
		auxSolicitud.setCalleDomicilio(solicitud.getCalleDomicilio());
		auxSolicitud.setCodigoPostalDomicilio(solicitud.getCodigoPostalDomicilio());
		auxSolicitud.setMunicipioDomicilio(solicitud.getMunicipioDomicilio());
		
		if(solicitud.getPais() != null){
			auxSolicitud.setPais(solicitud.getPais());
		}
		if(solicitud.getProvinciaByIdProvinciaDomicilio() != null){
			auxSolicitud.setProvinciaByIdProvinciaDomicilio(solicitud.getProvinciaByIdProvinciaDomicilio());
			
			if(solicitud.getProvinciaByIdProvinciaDomicilio().getDescripcion() != null){
				auxSolicitud.setDescripcionIdProvinciaDomicilio(solicitud.getProvinciaByIdProvinciaDomicilio().getDescripcion());
			}
		}
		
		auxSolicitud.setLocalidadNacimiento(solicitud.getLocalidadNacimiento());
		
		if(solicitud.getProvinciaByIdProvinciaExamen() != null){
			auxSolicitud.setProvinciaByIdProvinciaExamen(solicitud.getProvinciaByIdProvinciaExamen());
			
			if(solicitud.getProvinciaByIdProvinciaExamen().getDescripcion() != null){
				auxSolicitud.setDescripcionIdProvinciaExamen(solicitud.getProvinciaByIdProvinciaExamen().getDescripcion());
			}
		}
		
		if(solicitud.getTipoDiscapacidad() != null){
			auxSolicitud.setTipoDiscapacidad(solicitud.getTipoDiscapacidad());
			
			if(solicitud.getTipoDiscapacidad().getDescripcion() != null){
				auxSolicitud.setDescripcionTipoDiscapacidad(solicitud.getTipoDiscapacidad().getDescripcion());
			}
		}
		
		auxSolicitud.setPorcentajeDiscapacidad((int)solicitud.getPorcentajeDiscapacidad());
		auxSolicitud.setDetalleDiscapacidad(solicitud.getDetalleDiscapacidad());
		auxSolicitud.setReservaDiscapacidad(solicitud.getReservaDiscapacidad());
		
		if(solicitud.getTituloOficial() != null){
			auxSolicitud.setTituloOficial(solicitud.getTituloOficial());
			if(solicitud.getTituloOficial().getDescripcion() != null){	
				auxSolicitud.setDescripcionTituloOficial(solicitud.getTituloOficial().getDescripcion());
			}
		}
		
		auxSolicitud.setOtrosTitulos(solicitud.getOtrosTitulos());
		auxSolicitud.setIdConsentimiento(solicitud.getIdConsentimiento());
		
		if(null != solicitud.getModelo() && null != solicitud.getModelo().getNumModelo()){
			auxSolicitud.setNumModelo(solicitud.getModelo().getNumModelo());
		}
		
		Date fechaBoe = solicitud.getConvocatoria().getFechaBoe();
		if(null != fechaBoe){
		auxSolicitud.setFechaBoe(utilesIPSG.dateToString(fechaBoe));
		}
		
		if(null != solicitud.getFunHabilitado()){
			auxSolicitud.setFunHabilitado(solicitud.getFunHabilitado());
		}
		return auxSolicitud;
	}
	
	
	/**
	 * To solicitudes bean.
	 *
	 * @param solicitudes el solicitudes
	 * @return el list
	 */
	public List<SolicitudBean> toSolicitudesBean(List<SolicitudComun> solicitudes){
		
		ArrayList<SolicitudBean> solicitudesBean = new ArrayList<SolicitudBean>();
		
		for (SolicitudComun solicitudComun : solicitudes) {
			
			SolicitudBean solicitud = toSolicitudBean(solicitudComun, solicitudes.size());
			solicitudesBean.add(solicitud);
			
		}
		
		return solicitudesBean;
	}
	
	/**
	 * Pasa de Solicitud a SolicitudBean.
	 *
	 * @param solicitud el solicitud
	 * @param numTotal int
	 * @return auxSolicitud SolicitudBean
	 */
	private SolicitudBean SolicitudVistatoSolicitudBean(SolComunIncidenciasView solicitud, int numTotal) {
		SimpleDateFormat sdf = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT); 

		SolicitudBean auxSolicitud = new SolicitudBean();
		
		if(solicitud.getIdSolicitud() != null){
			auxSolicitud.setId(solicitud.getIdSolicitud());
		}
		
		if(solicitud.getNumeroSolicitud() != null){	
			auxSolicitud.setNumeroSolicitud(solicitud.getNumeroSolicitud());
		}
		
		if(solicitud.getNifSolicitud() != null){	
			auxSolicitud.setNif(solicitud.getNifSolicitud());
		}
		
		if(solicitud.getNombreSolicitud() != null){	
			auxSolicitud.setNombre(solicitud.getNombreSolicitud());
		}
		
		if(solicitud.getSegundoApellidoSolicitud() != null){	
			auxSolicitud.setSegundoApellido(solicitud.getSegundoApellidoSolicitud());
		}
		
		if(solicitud.getPrimerApellidoSolicitud() != null){	
			auxSolicitud.setPrimerApellido(solicitud.getPrimerApellidoSolicitud());
		}
		
		if(solicitud.getEmailSolicitud() != null){		
			auxSolicitud.setEmail(solicitud.getEmailSolicitud());
		}
		
		if(solicitud.getTelefonoSolicitud() != null){	
			auxSolicitud.setTelefono(solicitud.getTelefonoSolicitud());
		}
		
		if(solicitud.getEjercicioConvocatoria() != null){	
			auxSolicitud.setEjercicio(solicitud.getEjercicioConvocatoria());
		}
		
		if(solicitud.getIdConvocatoria() != null){	
			auxSolicitud.setIdConvocatoria(solicitud.getIdConvocatoria());
		}
		
		if(solicitud.getSiglasCentroGestor() != null){	
			auxSolicitud.setSiglasCentroGestor(solicitud.getSiglasCentroGestor());
		}	
		
		if (solicitud.getFechaSolicitud() != null) {
			try{
				logger.info(STRING_FECHASTRING+ sdf.format(solicitud.getFechaSolicitud()));
				auxSolicitud.setFechaSolicitud(sdf.format(solicitud.getFechaSolicitud()));
			}catch(Exception e){
				logger.error(STRING_ERRORPARSEARFECHASOLICITUD+ solicitud.getFechaSolicitud() ,e);		
			}
		}
		
		if(solicitud.getIdEstadoSolicitud() != null){
			auxSolicitud.setIdEstadoSolicitud((int)solicitud.getIdEstadoSolicitud());
		}

		if(solicitud.getTipoSolicitudDesc() != null){
			auxSolicitud.setTipoDescripcion(solicitud.getTipoSolicitudDesc());
		}
		
		if(solicitud.getEspecialidadDesc() != null){
			auxSolicitud.setDescripcionEspecialidad(solicitud.getEspecialidadDesc());
		}
		
		if(solicitud.getCuerpoEscalaDesc() != null){
			auxSolicitud.setDesCuerpoEscala(solicitud.getCuerpoEscalaDesc());
		}
		
		if(solicitud.getFechaNacimiento() != null){
			auxSolicitud.setFechaNacimiento(solicitud.getFechaNacimiento());
		}
		
		if(solicitud.getSexo() != null){
			auxSolicitud.setSexo(solicitud.getSexo());
		}
		
		if(solicitud.getNacionalidad() != null){
			auxSolicitud.setNacionalidad(solicitud.getNacionalidad());
		}
		
		if(solicitud.getCalleDomicilio() != null){
			auxSolicitud.setCalleDomicilio(solicitud.getCalleDomicilio());
		}
		
		if(solicitud.getCodigoPostal() != null){
			auxSolicitud.setCodigoPostalDomicilio(solicitud.getCodigoPostal());
		}
		
		if(solicitud.getMunicipioDomicilio() != null){
			auxSolicitud.setMunicipioDomicilio(solicitud.getMunicipioDomicilio());
		}
		
		if(solicitud.getProvinciaDomicilio() != null){
			auxSolicitud.setDescripcionIdProvinciaDomicilio(solicitud.getProvinciaDomicilio());
		}
		
		if(solicitud.getPaisDomicilio() != null){
			auxSolicitud.setNacionPaisDomicilio(solicitud.getPaisDomicilio());
		}
		
		if(solicitud.getProvinciaExamen() != null){
			auxSolicitud.setDescripcionIdProvinciaExamen(solicitud.getProvinciaExamen());
		}
		
		if(solicitud.getTipoDiscapacidadDesc() != null){
			auxSolicitud.setDescripcionTipoDiscapacidad(solicitud.getTipoDiscapacidadDesc());
		}
		
		if(solicitud.getPorcentajeDiscapacidad() != null){
			auxSolicitud.setPorcentajeDiscapacidad((int)solicitud.getPorcentajeDiscapacidad());
		}
		
		if(solicitud.getAdaptacionDiscapacidad() != null){
			auxSolicitud.setDetalleDiscapacidad(solicitud.getAdaptacionDiscapacidad());
		}
		
		if(solicitud.getReservaDiscapacidad() != null){
			auxSolicitud.setReservaDiscapacidad(solicitud.getReservaDiscapacidad());
		}
		
		if(solicitud.getObservaciones() != null){
			auxSolicitud.setComentarios(solicitud.getObservaciones());
		}
		
		if(solicitud.getTituloOficialDesc() != null){
			auxSolicitud.setDescripcionTituloOficial(solicitud.getTituloOficialDesc());
		}
		
		if(solicitud.getOtrosTitulos() != null){
			auxSolicitud.setOtrosTitulos(solicitud.getOtrosTitulos());
		}
		
		if(solicitud.getDatosA() != null){
			auxSolicitud.setDatosA(solicitud.getDatosA());
		}
		
		if(solicitud.getDatosB() != null){
			auxSolicitud.setDatosB(solicitud.getDatosB());
		}
		
		if(solicitud.getDatosC() != null){
			auxSolicitud.setDatosC(solicitud.getDatosC());
		}
		
		if(solicitud.getCentroGestorDesc() != null){
			auxSolicitud.setCentroGestor(solicitud.getCentroGestorDesc());
		}
		
		if(solicitud.isIdConsentimiento()){
			auxSolicitud.setIdConsentimiento(true);
		}else{
			auxSolicitud.setIdConsentimiento(false);
		}
		
		if(solicitud.getEdadVerificada() != null){
			auxSolicitud.setEdadVerificada(solicitud.getEdadVerificada());
		}
		//FECHA DE NACIMIENTO VERIFICADA
		if(solicitud.getFechaNacimientoVerificada() != null){
			auxSolicitud.setFechaNacimientoVerificada(solicitud.getFechaNacimientoVerificada());
		}
		//TITULO OFICIAL VERIFICADO
		if(solicitud.getTituloVerificado() != null){
			auxSolicitud.setTituloVerificado(solicitud.getTituloVerificado());
		}
		//DESEMPLEO VERIFICADO
		if(solicitud.getDesempleoVerificado() != null){
			auxSolicitud.setDesempleoVerificado(solicitud.getDesempleoVerificado());
		}
		//FAMILIA NUMEROSA VERIFICADO
		if(solicitud.getFnumerosaVerificado() != null){
			auxSolicitud.setFnumerosaVerificado(solicitud.getFnumerosaVerificado());
		}
		//DISCAPACIDAD VERIFICADO
		if(solicitud.getDiscapacidadVerificado() != null){
			auxSolicitud.setDiscapacidadVerificado(solicitud.getDiscapacidadVerificado());
		}
		
		//VICTIMAS VERIFICADO
		if(solicitud.getVictimasVerificado() != null){
			auxSolicitud.setVictimasVerificado(solicitud.getVictimasVerificado());
		}
		
		// ID_CONSENTIMIENTO
		if(solicitud.isIdConsentimientoAeat() != null){
			auxSolicitud.setIdConsentimientoAeat(solicitud.isIdConsentimientoAeat());
		}else{
			// SI NO SE HA ESPECIFICADO VALOR PARA EL CONSENTIMIENTO, POR DEFECTO SE CONSIDERA TRUE
			auxSolicitud.setIdConsentimientoAeat(true);
		}	
		
		//Motivo oposicion
		if(solicitud.getMotivoOposicion() != null){	
			auxSolicitud.setMotivoOposicion(solicitud.getMotivoOposicion());
		}
		
		auxSolicitud.setNumTotal(numTotal);
		
		return auxSolicitud;
	}
	
	
	/**
	 * Pasa de Solicitud a SolicitudBean.
	 *
	 * @param solicitud el solicitud
	 * @param numTotal int
	 * @return auxSolicitud SolicitudBean
	 */
	private SolicitudBean SolicitudRegistradaVistatoSolicitudBean(SolComunRegistradasView solicitud, int numTotal) {
		SimpleDateFormat sdf = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT); 
		SolicitudBean auxSolicitud = new SolicitudBean();
		
		if(solicitud.getIdModelo()!=null && !"".equals(solicitud.getIdModelo())){
			auxSolicitud.setIdModelo(solicitud.getIdModelo().toString());
		}
		
		if(solicitud.isIdConsentimiento() != null){
			auxSolicitud.setIdConsentimiento(solicitud.isIdConsentimiento());
		}
		
		if(solicitud.getIdSolicitud() != null){
			auxSolicitud.setId(solicitud.getIdSolicitud());
		}
		
		if(solicitud.getNumeroSolicitud() != null){	
			auxSolicitud.setNumeroSolicitud(solicitud.getNumeroSolicitud());
		}
		
		if(solicitud.getNif() != null){	
			auxSolicitud.setNif(solicitud.getNif());
		}
		
		if(solicitud.getNombre() != null){	
			auxSolicitud.setNombre(solicitud.getNombre());
		}
		
		if(solicitud.getSegundoApellido() != null){	
			auxSolicitud.setSegundoApellido(solicitud.getSegundoApellido());
		}
		
		if(solicitud.getPrimerApellido() != null){	
			auxSolicitud.setPrimerApellido(solicitud.getPrimerApellido());
		}
		
		if(solicitud.getEmail() != null){		
			auxSolicitud.setEmail(solicitud.getEmail());
		}
		
		if(solicitud.getTelefonoSolicitud() != null){	
			auxSolicitud.setTelefono(solicitud.getTelefonoSolicitud());
		}
		
		if(solicitud.getEjercicioConvocatoria() != null){	
			auxSolicitud.setEjercicio(solicitud.getEjercicioConvocatoria());
		}
		
		if(solicitud.getIdConvocatoria() != null){	
			auxSolicitud.setIdConvocatoria(solicitud.getIdConvocatoria());
		}
		
		if(solicitud.getSiglasCentroGestor() != null){	
			auxSolicitud.setSiglasCentroGestor(solicitud.getSiglasCentroGestor());
		}
		
		// Para la exportacion a excel
		if(solicitud.getDesEspecialidad() != null){	
			auxSolicitud.setDescripcionEspecialidad(solicitud.getDesEspecialidad());
		}
		
		if(solicitud.getDesCuerpoEscala() != null){	
			auxSolicitud.setDesCuerpoEscala(solicitud.getDesCuerpoEscala());
		}
		
		if(solicitud.getFechaNacimiento() != null){	
			auxSolicitud.setFechaNacimiento(solicitud.getFechaNacimiento());
		}
		
		if(solicitud.getSexo()!= null){	
			auxSolicitud.setSexo(solicitud.getSexo());
		}
		
		if(solicitud.getNacionalidad() != null){	
			auxSolicitud.setNacionalidad(solicitud.getNacionalidad());
		}
		
		if(solicitud.getCalleDomicilio() != null){	
			auxSolicitud.setCalleDomicilio(solicitud.getCalleDomicilio());
		}
		
		if(solicitud.getCodigoPostal() != null){	
			auxSolicitud.setCodigoPostalDomicilio(solicitud.getCodigoPostal());
		}
		
		if(solicitud.getMunicipioDomicilio() != null){	
			auxSolicitud.setMunicipioDomicilio(solicitud.getMunicipioDomicilio());
		}
		
		if(solicitud.getProvinciaDomicilio() != null){	
			auxSolicitud.setDescripcionIdProvinciaDomicilio(solicitud.getProvinciaDomicilio());
		}
		
		if(solicitud.getNacionDomicilio() != null){	
			auxSolicitud.setNacionPaisDomicilio(solicitud.getNacionDomicilio());
		}
		
		if(solicitud.getProvinciaExamen() != null){	
			auxSolicitud.setDescripcionIdProvinciaExamen(solicitud.getProvinciaExamen());
		}
		
		if(solicitud.getDesTipoDiscapacidad() != null){	
			auxSolicitud.setDescripcionTipoDiscapacidad(solicitud.getDesTipoDiscapacidad());
		}
		
		if(solicitud.getPorcentajeDiscapacidad() != null){	
			auxSolicitud.setPorcentajeDiscapacidad((int)solicitud.getPorcentajeDiscapacidad());
		}
		
		if(solicitud.getAdaptacionDiscapacidad() != null){	
			auxSolicitud.setDetalleDiscapacidad(solicitud.getAdaptacionDiscapacidad());
		}
		
		if(solicitud.getReservaDiscapacidad() != null){	
			auxSolicitud.setReservaDiscapacidad(solicitud.getReservaDiscapacidad());
		}
		
		if(solicitud.getObservaciones() != null){	
			auxSolicitud.setComentarios(solicitud.getObservaciones());
		}
		
		if(solicitud.getTitulo() != null){	
			auxSolicitud.setDescripcionTituloOficial(solicitud.getTitulo());
		}
		
		if(solicitud.getOtrosTitulos() != null){	
			auxSolicitud.setOtrosTitulos(solicitud.getOtrosTitulos());
		}
		
		if(solicitud.getDatosA() != null){	
			auxSolicitud.setDatosA(solicitud.getDatosA());
		}
		
		if(solicitud.getDatosB() != null){	
			auxSolicitud.setDatosB(solicitud.getDatosB());
		}
		
		if(solicitud.getDatosC() != null){	
			auxSolicitud.setDatosC(solicitud.getDatosC());
		}
		
		if(solicitud.getDesCentroGestor() != null){	
			auxSolicitud.setCentroGestor(solicitud.getDesCentroGestor());
		}
		
		if (solicitud.getFechaRegistro() != null) {
			try{
				logger.info(STRING_FECHASTRING+ sdf.format(solicitud.getFechaRegistro()));
				auxSolicitud.setFechaSolicitud(sdf.format(solicitud.getFechaRegistro()));
			}catch(Exception e){
				logger.error(STRING_ERRORPARSEARFECHASOLICITUD+ solicitud.getFechaRegistro(),e);			
			}
		}
		
		if(solicitud.getEdadVerificada() != null){
			auxSolicitud.setEdadVerificada(solicitud.getEdadVerificada());
		}	
		
		if(solicitud.getDescTipoSolicitud() != null){
			auxSolicitud.setTipoDescripcion(solicitud.getDescTipoSolicitud());
		}
		
		// ID_CONSENTIMIENTO
		if(solicitud.isIdConsentimiento() != null){
			auxSolicitud.setIdConsentimiento(solicitud.isIdConsentimiento());
		}else{
			// SI NO SE HA ESPECIFICADO VALOR PARA EL CONSENTIMIENTO, POR DEFECTO SE CONSIDERA TRUE
			auxSolicitud.setIdConsentimiento(true);
		}	
		
		auxSolicitud.setFechaNacimientoSvdi(solicitud.getFechaNacimientoSvdi());

		//FECHA DE NACIMIENTO VERIFICADA
		if(solicitud.getFechaNacimientoVerificada() != null){
			auxSolicitud.setFechaNacimientoVerificada(solicitud.getFechaNacimientoVerificada());
		}
		
		//TITULO OFICIAL VERIFICADO
		if(solicitud.getTituloVerificado() != null){
			auxSolicitud.setTituloVerificado(solicitud.getTituloVerificado());
		}
		//DESEMPLEO VERIFICADO
		if(solicitud.getDesempleoVerificado() != null){
			auxSolicitud.setDesempleoVerificado(solicitud.getDesempleoVerificado());
		}
		//FAMILIA NUMEROSA VERIFICADO
		if(solicitud.getFnumerosaVerificado() != null){
			auxSolicitud.setFnumerosaVerificado(solicitud.getFnumerosaVerificado());
		}
		//DISCAPACIDAD VERIFICADO
		if(solicitud.getDiscapacidadVerificado() != null){
			auxSolicitud.setDiscapacidadVerificado(solicitud.getDiscapacidadVerificado());
		}
		// ID_CONSENTIMIENTO
		if(solicitud.isIdConsentimientoAeat() != null){
			auxSolicitud.setIdConsentimientoAeat(solicitud.isIdConsentimientoAeat());
		}else{
			// SI NO SE HA ESPECIFICADO VALOR PARA EL CONSENTIMIENTO, POR DEFECTO SE CONSIDERA TRUE
			auxSolicitud.setIdConsentimientoAeat(true);
		}	
		//Motivo oposicion
		if(solicitud.getMotivoOposicion() != null){	
			auxSolicitud.setMotivoOposicion(solicitud.getMotivoOposicion());
		}
		//VICTIMAS VERIFICADO
		if(solicitud.getVictimasVerificado() != null){
			auxSolicitud.setVictimasVerificado(solicitud.getVictimasVerificado());
		}
		
		auxSolicitud.setNumTotal(numTotal);
		
		return auxSolicitud;
	}
	
	/**
	 * Pasa de Solicitud a SolicitudBean.
	 *
	 * @param solicitud el solicitud
	 * @param numTotal int
	 * @return auxSolicitud SolicitudBean
	 */
	private SolicitudBean SolicitudPresencialVistatoSolicitudBean(SolComunPresencialesView solicitud, int numTotal) {
		SimpleDateFormat sdf = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT); 
		SolicitudBean auxSolicitud = new SolicitudBean();
		
		if(solicitud.getIdModelo()!=null && !"".equals(solicitud.getIdModelo())){
			auxSolicitud.setIdModelo(solicitud.getIdModelo().toString());
		}
		
		if(solicitud.isIdConsentimiento() != null){
			auxSolicitud.setIdConsentimiento(solicitud.isIdConsentimiento());
		}
		
		if(solicitud.getIdSolicitud() != null){
			auxSolicitud.setId(solicitud.getIdSolicitud());
		}
		
		if(solicitud.getNumeroSolicitud() != null){	
			auxSolicitud.setNumeroSolicitud(solicitud.getNumeroSolicitud());
		}
		
		if(solicitud.getNif() != null){	
			auxSolicitud.setNif(solicitud.getNif());
		}
		
		if(solicitud.getNombre() != null){	
			auxSolicitud.setNombre(solicitud.getNombre());
		}
		
		if(solicitud.getSegundoApellido() != null){	
			auxSolicitud.setSegundoApellido(solicitud.getSegundoApellido());
		}
		
		if(solicitud.getPrimerApellido() != null){	
			auxSolicitud.setPrimerApellido(solicitud.getPrimerApellido());
		}
		
		if(solicitud.getEmail() != null){		
			auxSolicitud.setEmail(solicitud.getEmail());
		}
		
		if(solicitud.getTelefonoSolicitud() != null){	
			auxSolicitud.setTelefono(solicitud.getTelefonoSolicitud());
		}
		
		if(solicitud.getEjercicio() != null){	
			auxSolicitud.setEjercicio(solicitud.getEjercicio());
		}
		
		if(solicitud.getIdConvocatoria() != null){	
			auxSolicitud.setIdConvocatoria(solicitud.getIdConvocatoria());
		}
		
		if(solicitud.getSiglasCentroGestor() != null){	
			auxSolicitud.setSiglasCentroGestor(solicitud.getSiglasCentroGestor());
		}
		
		// Para la exportacion a excel
		if(solicitud.getDesEspecialidad() != null){	
			auxSolicitud.setDescripcionEspecialidad(solicitud.getDesEspecialidad());
		}
		
		if(solicitud.getCodigoCuerpoEscala() != null){
			auxSolicitud.setCodigoCuerpoEscala(solicitud.getCodigoCuerpoEscala());
		}
		
		if(solicitud.getDesCuerpoEscala() != null){	
			auxSolicitud.setDesCuerpoEscala(solicitud.getDesCuerpoEscala());
		}
		
		if(solicitud.getFechaNacimiento() != null){	
			auxSolicitud.setFechaNacimiento(solicitud.getFechaNacimiento());
		}
		
		if(solicitud.getSexo()!= null){	
			auxSolicitud.setSexo(solicitud.getSexo());
		}
		
		if(solicitud.getNacionalidad() != null){	
			auxSolicitud.setNacionalidad(solicitud.getNacionalidad());
		}
		
		if(solicitud.getCalleDomicilio() != null){	
			auxSolicitud.setCalleDomicilio(solicitud.getCalleDomicilio());
		}
		
		if(solicitud.getCodigoPostal() != null){	
			auxSolicitud.setCodigoPostalDomicilio(solicitud.getCodigoPostal());
		}
		
		if(solicitud.getMunicipioDomicilio() != null){	
			auxSolicitud.setMunicipioDomicilio(solicitud.getMunicipioDomicilio());
		}
		
		if(solicitud.getProvinciaDomicilio() != null){	
			auxSolicitud.setDescripcionIdProvinciaDomicilio(solicitud.getProvinciaDomicilio());
		}
		
		if(solicitud.getNacionDomicilio() != null){	
			auxSolicitud.setNacionPaisDomicilio(solicitud.getNacionDomicilio());
		}
		
		if(solicitud.getProvinciaExamen() != null){	
			auxSolicitud.setDescripcionIdProvinciaExamen(solicitud.getProvinciaExamen());
		}
		
		if(solicitud.getDesTipoDiscapacidad() != null){	
			auxSolicitud.setDescripcionTipoDiscapacidad(solicitud.getDesTipoDiscapacidad());
		}
		
		if(solicitud.getPorcentajeDiscapacidad() != null){	
			auxSolicitud.setPorcentajeDiscapacidad((int)solicitud.getPorcentajeDiscapacidad());
		}
		
		if(solicitud.getAdaptacionDiscapacidad() != null){	
			auxSolicitud.setDetalleDiscapacidad(solicitud.getAdaptacionDiscapacidad());
		}
		
		if(solicitud.getReservaDiscapacidad() != null){	
			auxSolicitud.setReservaDiscapacidad(solicitud.getReservaDiscapacidad());
		}
		
		if(solicitud.getObservaciones() != null){	
			auxSolicitud.setComentarios(solicitud.getObservaciones());
		}
		
		if(solicitud.getTitulo() != null){	
			auxSolicitud.setDescripcionTituloOficial(solicitud.getTitulo());
		}
		
		if(solicitud.getOtrosTitulos() != null){	
			auxSolicitud.setOtrosTitulos(solicitud.getOtrosTitulos());
		}
		
		if(solicitud.getDatosA() != null){	
			auxSolicitud.setDatosA(solicitud.getDatosA());
		}
		
		if(solicitud.getDatosB() != null){	
			auxSolicitud.setDatosB(solicitud.getDatosB());
		}
		
		if(solicitud.getDatosC() != null){	
			auxSolicitud.setDatosC(solicitud.getDatosC());
		}
		
		if(solicitud.getDesCentroGestor() != null){	
			auxSolicitud.setCentroGestor(solicitud.getDesCentroGestor());
		}
		
		if (solicitud.getFechaRegistro() != null) {
			try{
				logger.info(STRING_FECHASTRING+ sdf.format(solicitud.getFechaRegistro()));
				auxSolicitud.setFechaSolicitud(sdf.format(solicitud.getFechaRegistro()));
			}catch(Exception e){
				logger.error(STRING_ERRORPARSEARFECHASOLICITUD+ solicitud.getFechaRegistro(),e);			
			}
		}
		
		if(solicitud.getEdadVerificada() != null){
			auxSolicitud.setEdadVerificada(solicitud.getEdadVerificada());
		}	
		
		if(solicitud.getDescTipoSolicitud() != null){
			auxSolicitud.setTipoDescripcion(solicitud.getDescTipoSolicitud());
		}
		
		// ID_CONSENTIMIENTO
		if(solicitud.isIdConsentimiento() != null){
			auxSolicitud.setIdConsentimiento(solicitud.isIdConsentimiento());
		}else{
			// SI NO SE HA ESPECIFICADO VALOR PARA EL CONSENTIMIENTO, POR DEFECTO SE CONSIDERA TRUE
			auxSolicitud.setIdConsentimiento(true);
		}	
		
		auxSolicitud.setFechaNacimientoSvdi(solicitud.getFechaNacimientoSvdi());

		//FECHA DE NACIMIENTO VERIFICADA
		if(solicitud.getFechaNacimientoVerificada() != null){
			auxSolicitud.setFechaNacimientoVerificada(solicitud.getFechaNacimientoVerificada());
		}
		
		//TITULO OFICIAL VERIFICADO
		if(solicitud.getTituloVerificado() != null){
			auxSolicitud.setTituloVerificado(solicitud.getTituloVerificado());
		}
		//DESEMPLEO VERIFICADO
		if(solicitud.getDesempleoVerificado() != null){
			auxSolicitud.setDesempleoVerificado(solicitud.getDesempleoVerificado());
		}
		//FAMILIA NUMEROSA VERIFICADO
		if(solicitud.getFnumerosaVerificado() != null){
			auxSolicitud.setFnumerosaVerificado(solicitud.getFnumerosaVerificado());
		}
		//DISCAPACIDAD VERIFICADO
		if(solicitud.getDiscapacidadVerificado() != null){
			auxSolicitud.setDiscapacidadVerificado(solicitud.getDiscapacidadVerificado());
		}
		
		auxSolicitud.setNumTotal(numTotal);
		
		return auxSolicitud;
	}
	
	/**
	 * Pasa de Solicitud a SolicitudBean.
	 *
	 * @param solicitud el solicitud
	 * @param numTotal int
	 * @return auxSolicitud SolicitudBean
	 */
	private SolicitudBean SolicitudClotesVistatoSolicitudBean(SolComunClotesView solicitud, int numTotal) {
		SimpleDateFormat sdf = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT); 
		SolicitudBean auxSolicitud = new SolicitudBean();
		
		if(solicitud.getIdModelo()!=null && !"".equals(solicitud.getIdModelo())){
			auxSolicitud.setIdModelo(solicitud.getIdModelo().toString());
		}
		
		if(solicitud.isIdConsentimiento() != null){
			auxSolicitud.setIdConsentimiento(solicitud.isIdConsentimiento());
		}
		
		if(solicitud.getIdSolicitud() != null){
			auxSolicitud.setId(solicitud.getIdSolicitud());
		}
		
		if(solicitud.getNumeroSolicitud() != null){	
			auxSolicitud.setNumeroSolicitud(solicitud.getNumeroSolicitud());
		}
		
		if(solicitud.getNif() != null){	
			auxSolicitud.setNif(solicitud.getNif());
		}
		
		if(solicitud.getNombre() != null){	
			auxSolicitud.setNombre(solicitud.getNombre());
		}
		
		if(solicitud.getSegundoApellido() != null){	
			auxSolicitud.setSegundoApellido(solicitud.getSegundoApellido());
		}
		
		if(solicitud.getPrimerApellido() != null){	
			auxSolicitud.setPrimerApellido(solicitud.getPrimerApellido());
		}
		
		if(solicitud.getEmail() != null){		
			auxSolicitud.setEmail(solicitud.getEmail());
		}
		
		if(solicitud.getTelefonoSolicitud() != null){	
			auxSolicitud.setTelefono(solicitud.getTelefonoSolicitud());
		}
		
		if(solicitud.getEjercicioConvocatoria() != null){	
			auxSolicitud.setEjercicio(solicitud.getEjercicioConvocatoria());
		}
		
		if(solicitud.getIdConvocatoria() != null){	
			auxSolicitud.setIdConvocatoria(solicitud.getIdConvocatoria());
		}
		
		if(solicitud.getSiglasCentroGestor() != null){	
			auxSolicitud.setSiglasCentroGestor(solicitud.getSiglasCentroGestor());
		}
		
		// Para la exportacion a excel
		if(solicitud.getDesEspecialidad() != null){	
			auxSolicitud.setDescripcionEspecialidad(solicitud.getDesEspecialidad());
		}
		
		if(solicitud.getDesCuerpoEscala() != null){	
			auxSolicitud.setDesCuerpoEscala(solicitud.getDesCuerpoEscala());
		}
		
		if(solicitud.getFechaNacimiento() != null){	
			auxSolicitud.setFechaNacimiento(solicitud.getFechaNacimiento());
		}
		
		if(solicitud.getSexo()!= null){	
			auxSolicitud.setSexo(solicitud.getSexo());
		}
		
		if(solicitud.getNacionalidad() != null){	
			auxSolicitud.setNacionalidad(solicitud.getNacionalidad());
		}
		
		if(solicitud.getCalleDomicilio() != null){	
			auxSolicitud.setCalleDomicilio(solicitud.getCalleDomicilio());
		}
		
		if(solicitud.getCodigoPostal() != null){	
			auxSolicitud.setCodigoPostalDomicilio(solicitud.getCodigoPostal());
		}
		
		if(solicitud.getMunicipioDomicilio() != null){	
			auxSolicitud.setMunicipioDomicilio(solicitud.getMunicipioDomicilio());
		}
		
		if(solicitud.getProvinciaDomicilio() != null){	
			auxSolicitud.setDescripcionIdProvinciaDomicilio(solicitud.getProvinciaDomicilio());
		}
		
		if(solicitud.getNacionDomicilio() != null){	
			auxSolicitud.setNacionPaisDomicilio(solicitud.getNacionDomicilio());
		}
		
		if(solicitud.getProvinciaExamen() != null){	
			auxSolicitud.setDescripcionIdProvinciaExamen(solicitud.getProvinciaExamen());
		}
		
		if(solicitud.getDesTipoDiscapacidad() != null){	
			auxSolicitud.setDescripcionTipoDiscapacidad(solicitud.getDesTipoDiscapacidad());
		}
		
		if(solicitud.getPorcentajeDiscapacidad() != null){	
			auxSolicitud.setPorcentajeDiscapacidad((int)solicitud.getPorcentajeDiscapacidad());
		}
		
		if(solicitud.getAdaptacionDiscapacidad() != null){	
			auxSolicitud.setDetalleDiscapacidad(solicitud.getAdaptacionDiscapacidad());
		}
		
		if(solicitud.getReservaDiscapacidad() != null){	
			auxSolicitud.setReservaDiscapacidad(solicitud.getReservaDiscapacidad());
		}
		
		if(solicitud.getObservaciones() != null){	
			auxSolicitud.setComentarios(solicitud.getObservaciones());
		}
		
		if(solicitud.getTitulo() != null){	
			auxSolicitud.setDescripcionTituloOficial(solicitud.getTitulo());
		}
		
		if(solicitud.getOtrosTitulos() != null){	
			auxSolicitud.setOtrosTitulos(solicitud.getOtrosTitulos());
		}
		
		if(solicitud.getDatosA() != null){	
			auxSolicitud.setDatosA(solicitud.getDatosA());
		}
		
		if(solicitud.getDatosB() != null){	
			auxSolicitud.setDatosB(solicitud.getDatosB());
		}
		
		if(solicitud.getDatosC() != null){	
			auxSolicitud.setDatosC(solicitud.getDatosC());
		}
		
		if(solicitud.getDesCentroGestor() != null){	
			auxSolicitud.setCentroGestor(solicitud.getDesCentroGestor());
		}
		
		if (solicitud.getFechaRegistro() != null) {
			try{
				logger.info(STRING_FECHASTRING+ sdf.format(solicitud.getFechaRegistro()));
				auxSolicitud.setFechaSolicitud(sdf.format(solicitud.getFechaRegistro()));
			}catch(Exception e){
				logger.error(STRING_ERRORPARSEARFECHASOLICITUD+ solicitud.getFechaRegistro(),e);			
			}
		}
		
		if(solicitud.getEdadVerificada() != null){
			auxSolicitud.setEdadVerificada(solicitud.getEdadVerificada());
		}	
		
		if(solicitud.getDescTipoSolicitud() != null){
			auxSolicitud.setTipoDescripcion(solicitud.getDescTipoSolicitud());
		}
		
		// ID_CONSENTIMIENTO
		if(solicitud.isIdConsentimiento() != null){
			auxSolicitud.setIdConsentimiento(solicitud.isIdConsentimiento());
		}else{
			// SI NO SE HA ESPECIFICADO VALOR PARA EL CONSENTIMIENTO, POR DEFECTO SE CONSIDERA TRUE
			auxSolicitud.setIdConsentimiento(true);
		}	
		
		auxSolicitud.setFechaNacimientoSvdi(solicitud.getFechaNacimientoSvdi());

		//FECHA DE NACIMIENTO VERIFICADA
		if(solicitud.getFechaNacimientoVerificada() != null){
			auxSolicitud.setFechaNacimientoVerificada(solicitud.getFechaNacimientoVerificada());
		}
		
		//TITULO OFICIAL VERIFICADO
		if(solicitud.getTituloVerificado() != null){
			auxSolicitud.setTituloVerificado(solicitud.getTituloVerificado());
		}
		//DESEMPLEO VERIFICADO
		if(solicitud.getDesempleoVerificado() != null){
			auxSolicitud.setDesempleoVerificado(solicitud.getDesempleoVerificado());
		}
		//FAMILIA NUMEROSA VERIFICADO
		if(solicitud.getFnumerosaVerificado() != null){
			auxSolicitud.setFnumerosaVerificado(solicitud.getFnumerosaVerificado());
		}
		//DISCAPACIDAD VERIFICADO
		if(solicitud.getDiscapacidadVerificado() != null){
			auxSolicitud.setDiscapacidadVerificado(solicitud.getDiscapacidadVerificado());
		}
		
		auxSolicitud.setNumTotal(numTotal);
		
		return auxSolicitud;
	}
	
	/**
	 * To solicitud combo bean.
	 *
	 * @param solicitud el solicitud
	 * @return el solicitud bean
	 */
	private SolicitudBean toSolicitudComboBean(SolicitudComun solicitud) {
		UtilesIPSG utilesIPSG = new UtilesIPSG();
		SimpleDateFormat sdf = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
		SolicitudBean auxSolicitud = new SolicitudBean();
		auxSolicitud.setNumeroSolicitud(solicitud.getNumeroSolicitud());
		auxSolicitud.setNif(solicitud.getNif());
		auxSolicitud.setNombre(solicitud.getNombre());
		auxSolicitud.setSegundoApellido(solicitud.getSegundoApellido());
		auxSolicitud.setPrimerApellido(solicitud.getPrimerApellido());
		auxSolicitud.setFechaNacimiento(solicitud.getFechaNacimiento());
		auxSolicitud.setLocalidadNacimiento(solicitud.getLocalidadNacimiento());
		auxSolicitud.setNacionalidad(solicitud.getNacionalidad());
		auxSolicitud.setProvinciaByIdProvinciaNacimiento(solicitud.getProvinciaByIdProvinciaNacimiento());
		auxSolicitud.setSexo(solicitud.getSexo());
		auxSolicitud.setEmail(solicitud.getEmail());
		auxSolicitud.setConvocatoria(solicitud.getConvocatoria());
		auxSolicitud.setCalleDomicilio(solicitud.getCalleDomicilio());
		auxSolicitud.setMunicipioDomicilio(solicitud.getMunicipioDomicilio());
		auxSolicitud.setPais(solicitud.getPais());
		auxSolicitud.setProvinciaByIdProvinciaDomicilio(solicitud.getProvinciaByIdProvinciaDomicilio());
		auxSolicitud.setTelefono(solicitud.getTelefono());
		auxSolicitud.setEspecialidad(solicitud.getEspecialidad());
		auxSolicitud.setProvinciaByIdProvinciaExamen(solicitud.getProvinciaByIdProvinciaExamen());
		auxSolicitud.setTipoDiscapacidad(solicitud.getTipoDiscapacidad());
		auxSolicitud.setPorcentajeDiscapacidad((int)solicitud.getPorcentajeDiscapacidad());
		auxSolicitud.setReservaDiscapacidad(solicitud.getReservaDiscapacidad());
		auxSolicitud.setDetalleDiscapacidad(solicitud.getDetalleDiscapacidad());
		auxSolicitud.setTituloOficial(solicitud.getTituloOficial());
		
		if(solicitud.getTituloVerificado() != null){	
			auxSolicitud.setTituloVerificado(solicitud.getTituloVerificado());
		}
		
		if(solicitud.getEdadVerificada() != null){
			auxSolicitud.setEdadVerificada(solicitud.getEdadVerificada());
		}
		
		if(solicitud.getFechaNacimientoVerificada() != null){
			auxSolicitud.setFechaNacimientoVerificada(solicitud.getFechaNacimientoVerificada());
		}	
		// DESEMPLEO
		if(solicitud.getDesempleoVerificado() != null){
			auxSolicitud.setDesempleoVerificado(solicitud.getDesempleoVerificado());
		}
		// FAMILIA NUMEROSA
		if(solicitud.getFnumerosaVerificado() != null){
			auxSolicitud.setFnumerosaVerificado(solicitud.getFnumerosaVerificado());
		}
		// DISCAPACIDAD
		if(solicitud.getDiscapacidadVerificado() != null){
			auxSolicitud.setDiscapacidadVerificado(solicitud.getDiscapacidadVerificado());
		}
		if(solicitud.getEstadoSolicitud() != null){	
			auxSolicitud.setEstadoSolicitud(solicitud.getEstadoSolicitud());
		}
		
		auxSolicitud.setId(solicitud.getIdSolicitud());

		if (solicitud.getFechaSolicitud()!=null){
			auxSolicitud.setFechaSolicitud(sdf.format(solicitud.getFechaSolicitud()));
		}
		
		auxSolicitud.setTipo(solicitud.getTipoSolicitud());
		auxSolicitud.setComentarios(solicitud.getComentarios());
		
		if(solicitud.getPais()!= null){
			auxSolicitud.setPais(solicitud.getPais());
			if(solicitud.getPais().getDescripcion() != null){
				auxSolicitud.setNacionPaisDomicilio(solicitud.getPais().getDescripcion());
			}
		}
		
		Date fechaBoe = solicitud.getConvocatoria().getFechaBoe();
		if(null != fechaBoe){
		auxSolicitud.setFechaBoe(utilesIPSG.dateToString(fechaBoe));
		}
		
		return auxSolicitud;
	}
	
	/**
	 * To solicitud combo bean xml sigp.
	 *
	 * @param solicitud el solicitud
	 * @return el solicitud xml sigp bean
	 */
	private SolicitudXmlSigpBean toSolicitudComboBeanXmlSigp(SolicitudComun solicitud) {
		
	    SolicitudXmlSigpBean SolicitudXmlSigpBeanAux= new SolicitudXmlSigpBean();
	    Validacion validacion = new Validacion();
	    
	    //Recuperamos los datos necesarios de la solicitud
	    SolicitudXmlSigpBeanAux.setIdSolicitud(solicitud.getIdSolicitud());
	    
	    Convocatoria convocatoria=null;
	    if(solicitud.getConvocatoria()!=null){
	    	convocatoria=solicitud.getConvocatoria();
	    }
	    
	    CuerpoEscala cuerpoEscala=null;
	    if(convocatoria.getCuerpoEscala()!=null){
	    	cuerpoEscala=convocatoria.getCuerpoEscala();
	    }
	    
	    CentroGestor centroGestor=null;
	    if(cuerpoEscala.getCentroGestor()!=null){
	    	centroGestor=cuerpoEscala.getCentroGestor();
	    }
	    
	    Ministerio ministerio=null;
	    if(centroGestor.getMinisterio()!=null){
	    	ministerio=centroGestor.getMinisterio();
	    }
	    
	    Especialidad especialidad=null;
	    if(solicitud.getEspecialidad()!=null){
	    	especialidad=solicitud.getEspecialidad();
	    }
		
	    //Proceso_Selectivo
	    SolicitudXmlSigpBeanAux.setMinisterio(ministerio.getCodigo());
	    SolicitudXmlSigpBeanAux.setCentroGestor(centroGestor.getCodigo());
	    SolicitudXmlSigpBeanAux.setAnioConvocatoria(convocatoria.getEjercicio());
	    
	    if(cuerpoEscala!=null && cuerpoEscala.getCodigo()!=null &&!"".equals(cuerpoEscala.getCodigo())){
		   
		    	SolicitudXmlSigpBeanAux.setCuerpoEscala(cuerpoEscala.getCodigo());	
		    
	    }
	    if (especialidad!=null && especialidad.getCodigo()!=null &&!"".equals(especialidad.getCodigo())){
	    	
	 	    	SolicitudXmlSigpBeanAux.setEspecialidad(especialidad.getCodigo());
	 	    
	    }
	   
	    if(convocatoria!=null && convocatoria.getFechaBoe()!=null && !"".equals(convocatoria.getFechaBoe())){
	    	
		    	SolicitudXmlSigpBeanAux.setFecBoe(convocatoria.getFechaBoe());	
		    
	    }
	    
	    if(centroGestor!=null && centroGestor.getCodigo()!=null &&!"".equals(centroGestor.getCodigo())){
	    	 
	 	    	SolicitudXmlSigpBeanAux.setEntidadConvocante(centroGestor.getCodigo());
	 	    
	    }
	   
		//Empleado
	    if (solicitud.getNombre()!=null && !"".equals(solicitud.getNombre())){
	    	SolicitudXmlSigpBeanAux.setNombre(solicitud.getNombre());	
	    }
	    if (solicitud.getPrimerApellido()!=null && !"".equals(solicitud.getPrimerApellido())){
	    	 SolicitudXmlSigpBeanAux.setApellido1(solicitud.getPrimerApellido());
	    }
	   
	    if (solicitud.getSegundoApellido()!=null && !"".equals(solicitud.getSegundoApellido())){
	    	 SolicitudXmlSigpBeanAux.setApellido2(solicitud.getSegundoApellido());
	    }
	    if (solicitud.getSexo()!=null && !"".equals(solicitud.getSexo())){
	    	SolicitudXmlSigpBeanAux.setSexo(solicitud.getSexo());
	    }
	    if (solicitud.getFechaNacimiento()!=null && !"".equals(solicitud.getFechaNacimiento())){
	    	SolicitudXmlSigpBeanAux.setFechaNacimiento(solicitud.getFechaNacimiento());
	    }
	    if (solicitud.getNif()!=null && !"".equals(solicitud.getNif())){
	    	if (validacion.nieValidate(solicitud.getNif())){
		    	//Si es NIE
		    	SolicitudXmlSigpBeanAux.setTipoDocumento(Constantes.SIGP_TIPO_DOCUMENTO_NIE);
		    }else{
		    	 //si es DNI
				SolicitudXmlSigpBeanAux.setTipoDocumento(Constantes.SIGP_TIPO_DOCUMENTO_NIF);
		    }
	    	SolicitudXmlSigpBeanAux.setNumeroDocumento(solicitud.getNif());
	    }
	    
	    if(solicitud.getEmail()!=null){
	    	if(solicitud.getEmail().length()>Constantes.SIGP_TAM_MAX_EMAIL){
	    		SolicitudXmlSigpBeanAux.setEmail(solicitud.getEmail().substring(Constantes.CTE_INT_0, Constantes.SIGP_TAM_MAX_EMAIL));
	    	}else{
	    		SolicitudXmlSigpBeanAux.setEmail(solicitud.getEmail());
	    	}
	    	
		}
	    
	    if(solicitud.getTelefono()!=null){
	    	if(solicitud.getTelefono().length()>Constantes.SIGP_TAM_MAX_TELEFONOFAX){
	    		SolicitudXmlSigpBeanAux.setNumeroTelefonoFax(solicitud.getTelefono().substring(Constantes.CTE_INT_0, Constantes.SIGP_TAM_MAX_TELEFONOFAX));
	    	}else{
	    		SolicitudXmlSigpBeanAux.setNumeroTelefonoFax(solicitud.getTelefono());
	    	}
	    	
		}
	    
	  //Direccion
	    if(solicitud.getCalleDomicilio()!=null){
	    	if(solicitud.getCalleDomicilio().length()>Constantes.SIGP_TAM_MAX_VALOR_DIRECCION){
	    		SolicitudXmlSigpBeanAux.setValorDireccion(solicitud.getCalleDomicilio().substring(Constantes.CTE_INT_0, Constantes.SIGP_TAM_MAX_VALOR_DIRECCION));
	    	}else{
	    		SolicitudXmlSigpBeanAux.setValorDireccion(solicitud.getCalleDomicilio());
	    	}
	    	
		}
	    
	    if(solicitud.getPais()!=null){
	    	SolicitudXmlSigpBeanAux.setPais(solicitud.getPais().getCodigo());
	    
		    if(Constantes.COD_ESPANA.equals(solicitud.getPais().getCodigo())){
		    	if (solicitud.getProvinciaByIdProvinciaDomicilio()!=null){
		    		SolicitudXmlSigpBeanAux.setProvincia(solicitud.getProvinciaByIdProvinciaDomicilio().getCodigo());
		    	}
		    	SolicitudXmlSigpBeanAux.setLocalidad(Constantes.CTE_000);
		    }else{
		    	SolicitudXmlSigpBeanAux.setProvincia(Constantes.CTE_060);
		    	SolicitudXmlSigpBeanAux.setLocalidad(solicitud.getPais().getCodigo());
		    }
	    }
		
		//Aspirante
	    if(solicitud.getNumeroSolicitud()!=null){
	    
		    int tamString=Constantes.CTE_INT_0;
		    if(solicitud.getNumeroSolicitud().length()>Constantes.SIGP_TAM_MAX_NUM_SOL){
		    	tamString=Constantes.SIGP_TAM_MAX_NUM_SOL;
		    }else{
		    	tamString=solicitud.getNumeroSolicitud().length();
		    }
		    SolicitudXmlSigpBeanAux.setNumSolicitud(solicitud.getNumeroSolicitud().substring(Constantes.CTE_INT_0, tamString));
	    }
	    
		//Convocatoria
	    
	    if(solicitud.getTipoSolicitud()!=null){
	    	SolicitudXmlSigpBeanAux.setTipoSolicitud(solicitud.getTipoSolicitud().getCodigo());
	    }
	    
	    SolicitudXmlSigpBeanAux.setNumJustificante(solicitud.getNumeroSolicitud());
	    if(solicitud.getProvinciaByIdProvinciaExamen()!=null){
	    	SolicitudXmlSigpBeanAux.setProvExamen(solicitud.getProvinciaByIdProvinciaExamen().getCodigo());
	    }
	    SolicitudXmlSigpBeanAux.setCuerpoAsp(cuerpoEscala.getCodigo());
	    if(cuerpoEscala.getCategoria()!=null){
	    	SolicitudXmlSigpBeanAux.setCategAsp(cuerpoEscala.getCategoria().getCodigo());
	    }
	    if(cuerpoEscala.getGrupo()!=null){
	    	SolicitudXmlSigpBeanAux.setGrupProfAsp(cuerpoEscala.getGrupo().getCodigo());
	    }
	    
		//Datos Aspirante
	    if(solicitud.getTipoDiscapacidad()!=null){
	    	if(solicitud.getTipoDiscapacidad().getId()!=null){
	    		SolicitudXmlSigpBeanAux.setDiscapacitado(Constantes.CTE_1);
	    	}
	    	if(solicitud.getTipoDiscapacidad().getCodigo()!=null && solicitud.getTipoDiscapacidad().getCodigo().equals(Constantes.TITULO_DISCAP_INTELEC)){
	    		 SolicitudXmlSigpBeanAux.setDiscapIntel(Constantes.CTE_1);
	    	}else{
	    		SolicitudXmlSigpBeanAux.setDiscapIntel(Constantes.CTE_0);
	    	}
	    }else{
	    	SolicitudXmlSigpBeanAux.setDiscapIntel(Constantes.CTE_0);
	    }	
	    SolicitudXmlSigpBeanAux.setGradoDisc((int)solicitud.getPorcentajeDiscapacidad());
	    SolicitudXmlSigpBeanAux.setAdaptacionDisc(solicitud.getDetalleDiscapacidad());
	    
	    if(solicitud.getReservaDiscapacidad()!=null && solicitud.getReservaDiscapacidad().equals(Constantes.RESERVA_DISCAPACIDAD_S)){
	    	SolicitudXmlSigpBeanAux.setReserDisc(Constantes.CTE_1);
	    }else{
	    	SolicitudXmlSigpBeanAux.setReserDisc(Constantes.CTE_0);
	    }
	    
	    if(solicitud.getTituloOficial()!=null){
	    	SolicitudXmlSigpBeanAux.setTituloExigido(solicitud.getTituloOficial().getCodigo());
	    }
		
		//Admision
	    if(convocatoria.getFormaAcceso()!=null){
	    	SolicitudXmlSigpBeanAux.setFormaAcceso(convocatoria.getFormaAcceso().getCodigo());
	    }
	    
		return SolicitudXmlSigpBeanAux;
	}

	/**
	 * To solicitud combo bean xml sigp vista.
	 *
	 * @param solicitud el solicitud
	 * @return el solicitud xml sigp bean
	 */
	private SolicitudXmlSigpBean toSolicitudComboBeanXmlSigpVista(SolComunRegistradasView solicitud) {
	
    SolicitudXmlSigpBean SolicitudXmlSigpBeanAux= new SolicitudXmlSigpBean();
    Validacion validacion = new Validacion();
    //Recuperamos los datos necesarios de la solicitud
    SolicitudXmlSigpBeanAux.setIdSolicitud(solicitud.getIdSolicitud());
    
    //Proceso_Selectivo
    procesoSelectivo(solicitud,SolicitudXmlSigpBeanAux);
   
    
	//Empleado
    empleado(solicitud,SolicitudXmlSigpBeanAux,validacion);
    empleado2(solicitud,SolicitudXmlSigpBeanAux,validacion);
    
    //Direccion
    direccion(solicitud,SolicitudXmlSigpBeanAux);
   
	
	//Aspirante
    if(solicitud.getNumeroSolicitud()!=null){
    
	    int tamString=Constantes.CTE_INT_0;
	    
	    if(solicitud.getNumeroSolicitud().length()>Constantes.SIGP_TAM_MAX_NUM_SOL){
	    	tamString=Constantes.SIGP_TAM_MAX_NUM_SOL;
	    }else{
	    	tamString=solicitud.getNumeroSolicitud().length();
	    }
	    SolicitudXmlSigpBeanAux.setNumSolicitud(solicitud.getNumeroSolicitud().substring(Constantes.CTE_INT_0, tamString));
    }
   
    // Fecha de registro
    SolicitudXmlSigpBeanAux.setFechaSol(solicitud.getFechaRegistro());
    
	//Convocatoria
    if(solicitud.getCodigoTipoSolicitud()!=null){
    	SolicitudXmlSigpBeanAux.setTipoSolicitud(solicitud.getCodigoTipoSolicitud());
    }
    
    SolicitudXmlSigpBeanAux.setNumJustificante(solicitud.getNumeroSolicitud());
    if(solicitud.getCodigoProvinciaExamen()!=null){
    	SolicitudXmlSigpBeanAux.setProvExamen(solicitud.getCodigoProvinciaExamen());
    }
    SolicitudXmlSigpBeanAux.setCuerpoAsp(solicitud.getCodigoCuerpoEscala());
    if(solicitud.getCodigoCategoria()!=null){
    	SolicitudXmlSigpBeanAux.setCategAsp(solicitud.getCodigoCategoria());
    }
    if(solicitud.getCodigoGrupo()!=null){
    	SolicitudXmlSigpBeanAux.setGrupProfAsp(solicitud.getCodigoGrupo());
    }
    SolicitudXmlSigpBeanAux.setDatoA(solicitud.getDatosA());
    SolicitudXmlSigpBeanAux.setDatoB(solicitud.getDatosB());
    SolicitudXmlSigpBeanAux.setDatoC(solicitud.getDatosC());
 
    
	//Datos Aspirante
    datosAspirantes(solicitud,SolicitudXmlSigpBeanAux);
	
	//Admision
    
    if(solicitud.getCodigoFormaAcceso()!=null){
    	SolicitudXmlSigpBeanAux.setFormaAcceso(solicitud.getCodigoFormaAcceso());
    }
	
	return SolicitudXmlSigpBeanAux;
}
	
	/**
	 * Direccion.
	 *
	 * @param solicitud el solicitud
	 * @param SolicitudXmlSigpBeanAux el solicitud xml sigp bean aux
	 */
	private void direccion(SolComunRegistradasView solicitud, SolicitudXmlSigpBean SolicitudXmlSigpBeanAux) {
		 if(solicitud.getCalleDomicilio()!=null){
		    	if(solicitud.getCalleDomicilio().length()>Constantes.SIGP_TAM_MAX_VALOR_DIRECCION){
		    		SolicitudXmlSigpBeanAux.setValorDireccion(solicitud.getCalleDomicilio().substring(Constantes.CTE_INT_0, Constantes.SIGP_TAM_MAX_VALOR_DIRECCION));
		    	}else{
		    		SolicitudXmlSigpBeanAux.setValorDireccion(solicitud.getCalleDomicilio());
		    	}
			}
		    
		    if(solicitud.getNacionDomicilio()!=null){
		    	SolicitudXmlSigpBeanAux.setPais(solicitud.getCodigoNacionDomicilio());
		    
			    if(Constantes.COD_ESPANA.equals(solicitud.getCodigoNacionDomicilio())){
			    	if (solicitud.getCodigoProvinciaDomicilio()!=null){
			    		SolicitudXmlSigpBeanAux.setProvincia(solicitud.getCodigoProvinciaDomicilio());
			    	}
			    	SolicitudXmlSigpBeanAux.setLocalidad(Constantes.CTE_000);
			    }else{
			    	SolicitudXmlSigpBeanAux.setProvincia(Constantes.CTE_060);
			    	SolicitudXmlSigpBeanAux.setLocalidad(solicitud.getCodigoNacionDomicilio());
			    }
		    }
	}

	/**
	 * Datos aspirantes.
	 *
	 * @param solicitud el solicitud
	 * @param SolicitudXmlSigpBeanAux el solicitud xml sigp bean aux
	 */
	private void datosAspirantes(SolComunRegistradasView solicitud, SolicitudXmlSigpBean SolicitudXmlSigpBeanAux) {

		  if(solicitud.getIdTipoDiscapacidad()!=null){
		    	SolicitudXmlSigpBeanAux.setDiscapacitado(Constantes.CTE_1);
		    }
		    if(solicitud.getCodigoTipoDescapacidad()!=null && solicitud.getCodigoTipoDescapacidad().equals(Constantes.TITULO_DISCAP_INTELEC)){
		    	SolicitudXmlSigpBeanAux.setDiscapIntel(Constantes.CTE_1);
		    }else{
		    	SolicitudXmlSigpBeanAux.setDiscapIntel(Constantes.CTE_0);
		    }
		    	
		    SolicitudXmlSigpBeanAux.setGradoDisc((int)solicitud.getPorcentajeDiscapacidad());
		    SolicitudXmlSigpBeanAux.setAdaptacionDisc(solicitud.getDetalleDiscapacidad());
		    
		    if(solicitud.getReservaDiscapacidad()!=null && solicitud.getReservaDiscapacidad().equals(Constantes.RESERVA_DISCAPACIDAD_S)){
		    	SolicitudXmlSigpBeanAux.setReserDisc(Constantes.CTE_1);
		    }else{
		    	SolicitudXmlSigpBeanAux.setReserDisc(Constantes.CTE_0);
		    }
		    
		    if(solicitud.getCodigoTituloOficial()!=null){
		    	SolicitudXmlSigpBeanAux.setTituloExigido(solicitud.getCodigoTituloOficial());
		    }
		    SolicitudXmlSigpBeanAux.setTitulosAcademicos(solicitud.getOtrosTitulos());
	}
	
	/**
	 * Empleado 2.
	 *
	 * @param solicitud el solicitud
	 * @param SolicitudXmlSigpBeanAux el solicitud xml sigp bean aux
	 * @param validacion el validacion
	 */
	private void empleado2(SolComunRegistradasView solicitud, SolicitudXmlSigpBean SolicitudXmlSigpBeanAux, Validacion validacion) {
		 if (StringUtils.isNotEmpty(solicitud.getNif())){
		    	if (validacion.nieValidate(solicitud.getNif())){
			    	//Si es NIE
			    	SolicitudXmlSigpBeanAux.setTipoDocumento(Constantes.SIGP_TIPO_DOCUMENTO_NIE);
			    }else{
			    	 //si es DNI
					SolicitudXmlSigpBeanAux.setTipoDocumento(Constantes.SIGP_TIPO_DOCUMENTO_NIF);
			    }
		    	SolicitudXmlSigpBeanAux.setNumeroDocumento(solicitud.getNif());
		    }
		     
		    if(solicitud.getEmail()!=null){
		    	if(solicitud.getEmail().length()>Constantes.SIGP_TAM_MAX_EMAIL){
		    		SolicitudXmlSigpBeanAux.setEmail(solicitud.getEmail().substring(Constantes.CTE_INT_0, Constantes.SIGP_TAM_MAX_EMAIL));
		    	}else{
		    		SolicitudXmlSigpBeanAux.setEmail(solicitud.getEmail());
		    	}
			}
		    
		    if(solicitud.getTelefonoSolicitud()!=null){
		    	if(solicitud.getTelefonoSolicitud().length()>Constantes.SIGP_TAM_MAX_TELEFONOFAX){
		    		SolicitudXmlSigpBeanAux.setNumeroTelefonoFax(solicitud.getTelefonoSolicitud().substring(Constantes.CTE_INT_0, Constantes.SIGP_TAM_MAX_TELEFONOFAX));
		    	}else{
		    		SolicitudXmlSigpBeanAux.setNumeroTelefonoFax(solicitud.getTelefonoSolicitud());
		    	}
			}
	}
	
	/**
	 * Empleado.
	 *
	 * @param solicitud el solicitud
	 * @param SolicitudXmlSigpBeanAux el solicitud xml sigp bean aux
	 * @param validacion el validacion
	 */
	private void empleado(SolComunRegistradasView solicitud, SolicitudXmlSigpBean SolicitudXmlSigpBeanAux, Validacion validacion) {
		if (StringUtils.isNotEmpty(solicitud.getNombre())){
	    	SolicitudXmlSigpBeanAux.setNombre(solicitud.getNombre());	
	    }
	    
	    if (StringUtils.isNotEmpty(solicitud.getPrimerApellido())){
	    	 SolicitudXmlSigpBeanAux.setApellido1(solicitud.getPrimerApellido());
	    }
	   
	    if (StringUtils.isNotEmpty(solicitud.getSegundoApellido())){
	    	 SolicitudXmlSigpBeanAux.setApellido2(solicitud.getSegundoApellido());
	    }
	    
	    if (solicitud.getSexo()!=null && !"".equals(solicitud.getSexo())){
	    	SolicitudXmlSigpBeanAux.setSexo(solicitud.getSexo());
	    }
	   
	    if (solicitud.getFechaNacimiento()!=null && !"".equals(solicitud.getFechaNacimiento())){
	    	SolicitudXmlSigpBeanAux.setFechaNacimiento(solicitud.getFechaNacimiento());
	    }
	    
	   
	}
	
	/**
	 * Proceso selectivo.
	 *
	 * @param solicitud el solicitud
	 * @param SolicitudXmlSigpBeanAux el solicitud xml sigp bean aux
	 */
	private void procesoSelectivo(SolComunRegistradasView solicitud, SolicitudXmlSigpBean SolicitudXmlSigpBeanAux) {
		 if( solicitud.getCodigoMinisterio() != null){	
		    	SolicitudXmlSigpBeanAux.setMinisterio(solicitud.getCodigoMinisterio());
		    }	
			
		    if( solicitud.getCodigoCentroGestor() != null){	
		        SolicitudXmlSigpBeanAux.setCentroGestor(solicitud.getCodigoCentroGestor());
		    }    
		    
			if( solicitud.getEjercicioConvocatoria() != null){	
		    	SolicitudXmlSigpBeanAux.setAnioConvocatoria(solicitud.getEjercicioConvocatoria());
		    }
		    
		    if( solicitud.getCodigoCuerpoEscala() != null)    {	
		    	SolicitudXmlSigpBeanAux.setCuerpoEscala(solicitud.getCodigoCuerpoEscala());
		    }
		    
		    if( solicitud.getCodigoEspecialidad() != null){	
		    	SolicitudXmlSigpBeanAux.setEspecialidad(solicitud.getCodigoEspecialidad());
		    }
		    
		    if( solicitud.getFechaBoe() != null){	
		    	SolicitudXmlSigpBeanAux.setFecBoe(solicitud.getFechaBoe());
		    }	
		   
		    if( solicitud.getCodigoCentroGestor() != null){	
		    	SolicitudXmlSigpBeanAux.setEntidadConvocante(solicitud.getCodigoCentroGestor());
		    }
	}
	
	/**
	 * Modifica una solicitud Registrada .
	 *
	 * @param solicitudBean SolicitudBean
	 */
	public void modificarSolicitudRegistrada (SolicitudBean  solicitudBean)
	{
		SolicitudComun solicitud =  toSolicitud(solicitudBean);
		SolicitudComunDAO.update(solicitud);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudesRegistradasManager#toSolicitud(es.map.ipsg.bean.SolicitudBean)
	 */
	public SolicitudComun toSolicitud (SolicitudBean  solicitudBean)
	{
		DateFormat sdf = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
		SolicitudComun solicitud = new SolicitudComun();
		solicitud.setFunHabilitado(solicitudBean.getFunHabilitado());
		solicitud.setConvocatoria(solicitudBean.getConvocatoria());
		solicitud.setIdConsentimiento(solicitudBean.getIdConsentimiento());
		solicitud.setCalleDomicilio(solicitudBean.getCalleDomicilio());
		solicitud.setCodigoPostalDomicilio(solicitudBean.getCodigoPostalDomicilio());
		solicitud.setComentarios(solicitudBean.getComentarios());
		solicitud.setConvocatoria(solicitudBean.getConvocatoria());
		
		if(solicitudBean.getIdModelo()!=null && !"".equals(solicitudBean.getIdModelo())){
			Modelo modelo = new Modelo();
			modelo.setIdModelo(Integer.parseInt(solicitudBean.getIdModelo()));
			solicitud.setModelo(modelo);
		}
		
		solicitud.setDetalleDiscapacidad(solicitudBean.getDetalleDiscapacidad());
		solicitud.setEdadVerificada(solicitudBean.getEdadVerificada());
		solicitud.setEmail(solicitudBean.getEmail());
		solicitud.setEspecialidad(solicitudBean.getEspecialidad());
		solicitud.setEstadoSolicitud(solicitudBean.getEstadoSolicitud());
		solicitud.setFechaNacimiento(solicitudBean.getFechaNacimiento());
		
		if(solicitudBean.getFechaNacimientoVerificada() != null){
			solicitud.setFechaNacimientoVerificada(solicitudBean.getFechaNacimientoVerificada());
		}
		//DESEMPLEO
		if(solicitudBean.getDesempleoVerificado() != null){
			solicitud.setDesempleoVerificado(solicitudBean.getDesempleoVerificado());
		}
		// FAMILIA NUMEROSA
		if(solicitudBean.getFnumerosaVerificado() != null){
			solicitud.setFnumerosaVerificado(solicitudBean.getFnumerosaVerificado());
		}
		// DISCAPACIDAD
		if(solicitudBean.getDiscapacidadVerificado() != null){
			solicitud.setDiscapacidadVerificado(solicitudBean.getDiscapacidadVerificado());
		}

		try {
			solicitud.setFechaSolicitud(sdf.parse(solicitudBean.getFechaSolicitud()));
		} catch (Exception e) {
			logger.error(STRING_ERRORPARSEARFECHASOLICITUD+ solicitud.getFechaSolicitud() ,e);
			
		}
		
		solicitud.setFechaUltActualizacion(solicitudBean.getFechaUtlActualizacion());
		solicitud.setIdSolicitud(solicitudBean.getId());
		solicitud.setLocalidadNacimiento(solicitudBean.getLocalidadNacimiento());		
		solicitud.setMunicipioDomicilio(solicitudBean.getMunicipioDomicilio());
		solicitud.setNacionalidad(solicitudBean.getNacionalidad());
		solicitud.setNif(solicitudBean.getNif());
		solicitud.setNombre(solicitudBean.getNombre());
		solicitud.setNumeroSolicitud(solicitudBean.getNumeroSolicitud());

		if(solicitudBean.getPagoSolicitudes() != null){
			Set<PagoSolicitudBean> setPagoSolicitudBean = new HashSet<PagoSolicitudBean> (solicitudBean.getPagoSolicitudes());
			Iterator<PagoSolicitudBean> it = setPagoSolicitudBean.iterator();
			Set<PagoSolicitud> setPagoSolicitud = new HashSet<PagoSolicitud>();
			while (it.hasNext()){
				PagoSolicitudBean pagoSolicitudBean = (PagoSolicitudBean) it.next();
				PagoSolicitud pagoSolicitud = this.toPagoSolicitud(pagoSolicitudBean);
				setPagoSolicitud.add(pagoSolicitud);
			}
		}
		
		solicitud.setPais(solicitudBean.getPais());
		solicitud.setPorcentajeDiscapacidad(solicitudBean.getPorcentajeDiscapacidad().shortValue());
		solicitud.setPrimerApellido(solicitudBean.getPrimerApellido());
		solicitud.setProvinciaByIdProvinciaDomicilio(solicitudBean.getProvinciaByIdProvinciaDomicilio());
		solicitud.setProvinciaByIdProvinciaExamen(solicitudBean.getProvinciaByIdProvinciaExamen());
		solicitud.setProvinciaByIdProvinciaNacimiento(solicitudBean.getProvinciaByIdProvinciaNacimiento());
		
		if(solicitudBean.getRegistroSolicitudes() != null){
			Set<RegistroSolicitudBean> setRegistroSolicitudBean = new HashSet<RegistroSolicitudBean> (solicitudBean.getRegistroSolicitudes());
			Iterator<RegistroSolicitudBean> it = setRegistroSolicitudBean.iterator();
			Set<RegistroSolicitud> setRegistroSolicitud = new HashSet<RegistroSolicitud>();
			while (it.hasNext())
			{
				RegistroSolicitudBean registroSolicitudBean = (RegistroSolicitudBean) it.next();
				RegistroSolicitud registroSolicitud = this.toRegistroSolicitud(registroSolicitudBean);
				setRegistroSolicitud.add(registroSolicitud);
			}
		}
		
		solicitud.setReservaDiscapacidad(solicitudBean.getReservaDiscapacidad());
		solicitud.setSegundoApellido(solicitudBean.getSegundoApellido());
		solicitud.setSexo(solicitudBean.getSexo());
		solicitud.setTelefono(solicitudBean.getTelefono());
		solicitud.setTipoDiscapacidad(solicitudBean.getTipoDiscapacidad());
		solicitud.setTipoSolicitud(solicitudBean.getTipo());
		solicitud.setTituloOficial(solicitudBean.getTituloOficial());
		
		if(solicitudBean.getTituloVerificado() != null){
			solicitud.setTituloVerificado(solicitudBean.getTituloVerificado());
		}	
		
		if(solicitudBean.getOtrosTitulos() != null){
			solicitud.setOtrosTitulos(solicitudBean.getOtrosTitulos());
		}
		//Si no se informa no modifica (Comprobar si este campo es necesario)
		if(solicitudBean.getFunHabilitado() != null){
			solicitud.setFunHabilitado(solicitudBean.getFunHabilitado());
		}else {
			solicitud.setFunHabilitado(false);
		}
			
		return solicitud;
	}
	
	/**
	 * Buscar solicitud.
	 *
	 * @param idConvocatoria  Long
	 * @return arrSolicitud ArrayList<SolicitudBean>
	 */
	public ArrayList<SolicitudBean> buscarSolicitud (Long idConvocatoria){
		
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(idConvocatoria);
		
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.setConvocatoria(convocatoriaQuery);
		
		SearchResult<SolicitudComun> Solicitud = buscarSolicitud(solicitudQuery);
		
		ArrayList<SolicitudBean> arrSolicitud = new ArrayList<SolicitudBean>();
		for(int i=0;i<Solicitud.getResults().size();i++){
			SolicitudBean aux;
			aux = toSolicitudComboBean(Solicitud.getResults().get(i));
			if(aux != null){
				arrSolicitud.add(aux);
			}
		}	
		return arrSolicitud;
	}
	
	/**
	 * Pasa de PagoSolicitudBean a PagoSolicitud.
	 *
	 * @param pagoSolicitudBean el pago solicitud bean
	 * @return pagoSolicitudBean PagoSolicitudBean
	 */
	private PagoSolicitud toPagoSolicitud (PagoSolicitudBean pagoSolicitudBean)
	{
		PagoSolicitud pagoSolicitud = new PagoSolicitud();
		pagoSolicitud.setEntidadFinanciera(pagoSolicitudBean.getEntidadFinanciera());
		pagoSolicitud.setFechaIntento(pagoSolicitudBean.getFechaIntento());
		pagoSolicitud.setId(pagoSolicitudBean.getId());
		pagoSolicitud.setLogServicio(pagoSolicitudBean.getLogservicio());
		pagoSolicitud.setMotivoReduccionTasa(pagoSolicitudBean.getMotivoReduccionTasa());
		pagoSolicitud.setSolicitudComun(pagoSolicitudBean.getSolicitud());
		pagoSolicitud.setImporte(pagoSolicitudBean.getImporte());
		pagoSolicitud.setNrc(pagoSolicitudBean.getNrc());
		pagoSolicitud.setResultado(pagoSolicitudBean.getResultado());
		pagoSolicitud.setSolicitaReduccion(pagoSolicitudBean.getSolicitaReduccion());
		pagoSolicitud.setTipo(pagoSolicitudBean.getTipo());
		
		return pagoSolicitud;
	}
	
	/**
	 * Pasa de RegistroSolicitudBean a RegistroSolicitud.
	 *
	 * @param registroSolicitudBean  RegistroSolicitudBean
	 * @return registroSolicitud RegistroSolicitud
	 */
	private RegistroSolicitud toRegistroSolicitud (RegistroSolicitudBean registroSolicitudBean)
	{
		RegistroSolicitud registroSolicitud = new RegistroSolicitud();
		
		registroSolicitud.setFechaIntento(registroSolicitudBean.getFechaIntento());
		registroSolicitud.setFechaRegistro(registroSolicitudBean.getFechaRegistro());
		registroSolicitud.setId(registroSolicitudBean.getId());
		registroSolicitud.setLogServicio(registroSolicitudBean.getLogServicio());
		registroSolicitud.setNumeroRegistro(registroSolicitudBean.getNumeroRegistro());
		registroSolicitud.setOficinaRegistro(registroSolicitudBean.getOficinaRegistro());
		registroSolicitud.setResultado(registroSolicitudBean.getResultado());
		registroSolicitud.setSolicitante(registroSolicitudBean.getSolicitante());
		registroSolicitud.setSolicitudComun(registroSolicitudBean.getSolicitud());
		
		return registroSolicitud;
	}

	
	/**
	 * Obtiene el solicitud comun DAO.
	 *
	 * @return el solicitud comun DAO
	 */
	public SolicitudComunDAO getSolicitudComunDAO() {
		return SolicitudComunDAO;
	}

	/**
	 * Establece el solicitud comun DAO.
	 *
	 * @param solicitudComunDAO el nuevo solicitud comun DAO
	 */
	public void setSolicitudComunDAO(SolicitudComunDAO solicitudComunDAO) {
		SolicitudComunDAO = solicitudComunDAO;
	}

	/**
	 * Obtiene el correo electronico manager.
	 *
	 * @return el correo electronico manager
	 */
	public CorreoElectronicoManager getCorreoElectronicoManager() {
		return correoElectronicoManager;
	}

	/**
	 * Establece el correo electronico manager.
	 *
	 * @param correoElectronicoManager el nuevo correo electronico manager
	 */
	public void setCorreoElectronicoManager(
			CorreoElectronicoManager correoElectronicoManager) {
		this.correoElectronicoManager = correoElectronicoManager;
	}

	/**
	 * Obtiene el sol comun incidencias view DAO.
	 *
	 * @return el sol comun incidencias view DAO
	 */
	public SolComunIncidenciasViewDAO getSolComunIncidenciasViewDAO() {
		return solComunIncidenciasViewDAO;
	}

	/**
	 * Establece el sol comun incidencias view DAO.
	 *
	 * @param solComunIncidenciasViewDAO el nuevo sol comun incidencias view DAO
	 */
	public void setSolComunIncidenciasViewDAO(
			SolComunIncidenciasViewDAO solComunIncidenciasViewDAO) {
		this.solComunIncidenciasViewDAO = solComunIncidenciasViewDAO;
	}

	/**
	 * Obtiene el sol comun registradas view DAO.
	 *
	 * @return el sol comun registradas view DAO
	 */
	public SolComunRegistradasViewDAO getSolComunRegistradasViewDAO() {
		return solComunRegistradasViewDAO;
	}

	/**
	 * Establece el sol comun registradas view DAO.
	 *
	 * @param solComunRegistradasViewDAO el nuevo sol comun registradas view DAO
	 */
	public void setSolComunRegistradasViewDAO(
			SolComunRegistradasViewDAO solComunRegistradasViewDAO) {
		this.solComunRegistradasViewDAO = solComunRegistradasViewDAO;
	}

	/**
	 * Obtiene el sol comun presenciales view DAO.
	 *
	 * @return el sol comun presenciales view DAO
	 */
	public SolComunPresencialesViewDAO getSolComunPresencialesViewDAO() {
		return solComunPresencialesViewDAO;
	}

	/**
	 * Establece el sol comun presenciales view DAO.
	 *
	 * @param solComunPresencialesViewDAO el nuevo sol comun presenciales view DAO
	 */
	public void setSolComunPresencialesViewDAO(
			SolComunPresencialesViewDAO solComunPresencialesViewDAO) {
		this.solComunPresencialesViewDAO = solComunPresencialesViewDAO;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudesRegistradasManager#eliminarSolicitud(java.lang.Long)
	 */
	public void eliminarSolicitud(Long id) {
		
		getSolicitudComunDAO().delete(id);
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Obtiene el pago solicitud manager.
	 *
	 * @return el pago solicitud manager
	 */
	public PagoSolicitudManager getPagoSolicitudManager() {
		return pagoSolicitudManager;
	}

	/**
	 * Establece el pago solicitud manager.
	 *
	 * @param pagoSolicitudManager el nuevo pago solicitud manager
	 */
	public void setPagoSolicitudManager(PagoSolicitudManager pagoSolicitudManager) {
		this.pagoSolicitudManager = pagoSolicitudManager;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudesRegistradasManager#buscarSolicitudes(java.util.List)
	 */
	@Override
	public List<SolicitudBean> buscarSolicitudes(
			List<SolicitudCcaaBean> solicitudes) {
    
		SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
		ArrayList<SolicitudBean> arrSolicitudes;
		
		for (SolicitudCcaaBean solicitudCcaaBean : solicitudes) {
			if(null != solicitudCcaaBean.getIdSolicitud()){
				solicitudComunQuery.addIdSolicitudIn(solicitudCcaaBean.getIdSolicitud());			
			}			
		}
		
		solicitudComunQuery.addOrder(SolicitudComunQuery.IDSOLICITUD,OrderType.ASC);
		arrSolicitudes= buscarSolicitudCombo(solicitudComunQuery);
		
		return arrSolicitudes;
	}

	/**
	 * Obtiene el sol comun clotes view DAO.
	 *
	 * @return el sol comun clotes view DAO
	 */
	public SolComunClotesViewDAO getSolComunClotesViewDAO() {
		return solComunClotesViewDAO;
	}

	/**
	 * Establece el sol comun clotes view DAO.
	 *
	 * @param solComunClotesViewDAO el nuevo sol comun clotes view DAO
	 */
	public void setSolComunClotesViewDAO(SolComunClotesViewDAO solComunClotesViewDAO) {
		this.solComunClotesViewDAO = solComunClotesViewDAO;
	}
	
	
	
}