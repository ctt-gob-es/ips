package es.map.ipsg.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.ContadorNumSolicitudCustomDAO;
import es.map.ips.dao.ParametrosConfiguracionDAO;
import es.map.ips.dao.SolicitudComunDAO;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.EstadoSolicitud;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ips.model.Modelo;
import es.map.ips.model.PagoSolicitud;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.ParametrosConfiguracion;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ips.model.RegistroSolicitud;
import es.map.ips.model.RegistroSolicitudQuery;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.TipoSolicitud;
import es.map.ipsg.bean.DetalleRegistroPagoBean;
import es.map.ipsg.bean.DetalleSolicitudBean;
import es.map.ipsg.bean.EstadoSolicitudBean;
import es.map.ipsg.bean.PagoSolicitudBean;
import es.map.ipsg.bean.RegistroSolicitudBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.SolicitudComunAuxiliarBean;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.NumeroSolicitud;


/**
 *  Clase que implementa el SolicitudManager.
 *
 * @author amartinl
 */
public class SolicitudesManagerImpl implements SolicitudesManager {

	/** el solicitud comun DAO. */
	//Variables
	private SolicitudComunDAO solicitudComunDAO;
	
	/** el contador num solicitud custom DAO. */
	private ContadorNumSolicitudCustomDAO contadorNumSolicitudCustomDAO;
	
	/** el parametros configuracion DAO. */
	private ParametrosConfiguracionDAO parametrosConfiguracionDAO;
	
	/** el estado solicitud manager. */
	private EstadoSolicitudManager estadoSolicitudManager;
	
	/** el pago solicitud manager. */
	private PagoSolicitudManager pagoSolicitudManager;
	
	/** el registro solicitud manager. */
	private RegistroSolicitudManager registroSolicitudManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SolicitudesManagerImpl.class);
	
	/**
	 * Buscar solicitud combo.
	 *
	 * @param SolicitudQuery SolicitudQuery
	 * @return arrTituloOficial ArrayList
	 */
	public ArrayList<SolicitudBean> buscarSolicitudCombo(SolicitudComunQuery SolicitudQuery){
		
		SearchResult<SolicitudComun> Solicitud = buscarSolicitud(SolicitudQuery);
		ArrayList<SolicitudBean> arrSolicitud= new ArrayList<SolicitudBean>();
		
		for(int i=0; i < Solicitud.getResults().size(); i++){
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
	 * @param solicitudQuery el solicitud query
	 * @return el search result
	 */
	private SearchResult<SolicitudComun> buscarSolicitud(SolicitudComunQuery solicitudQuery) {
			ApplicationException.assertNotNull(solicitudQuery, "SolicitudQuery no puede ser null");
		
		return solicitudComunDAO.search(solicitudQuery);
	}

	/**
	 * Buscar solicitud all.
	 *
	 * @param solicitudQuery SolicitudQuery
	 * @return arrsolicitud ArrayList
	 */
	public ArrayList<SolicitudBean> buscarSolicitudAll(SolicitudComunQuery solicitudQuery){		
		SearchResult<SolicitudComun> Solicitud = buscarSolicitud(solicitudQuery);
		Integer numTotal = Solicitud.getNumResults();
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
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudesManager#buscarSolicitudById(es.map.ips.model.SolicitudComunQuery)
	 */
	public SolicitudBean buscarSolicitudById(SolicitudComunQuery solicitudQuery) {
		SolicitudComun solicitud = solicitudComunDAO.searchUnique(solicitudQuery);
		if(solicitud != null){
			return toSolicitudBean(solicitud,0);
		}
		return null;
	}
	

	/**
	 * Obtiene el ID y la descripción de un  de Solicitud pasándole el ID.
	 * @param idSolicitud  Integer El ID del título que se desea obtener
	 * @return SolicitudBean Solicitud
	 */
	public SolicitudBean obtenerSolicitud (Long idSolicitud) {
		SolicitudComun Solicitud = solicitudComunDAO.get(idSolicitud);
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
		
		if(solicitud.getNumResults() != null){
			numTotal = solicitud.getNumResults();
		}
		ArrayList<SolicitudBean> arrSolicitud= new ArrayList<SolicitudBean>();
		
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
	 * Pasa de Solicitud a SolicitudBean.
	 *
	 * @param solicitud el solicitud
	 * @param numTotal int
	 * @return auxSolicitud SolicitudBean
	 */
	public SolicitudBean toSolicitudBean(SolicitudComun solicitud, int numTotal) {
	
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SolicitudBean auxSolicitud = new SolicitudBean();
		
		if(solicitud.getIdSolicitud() != null){
			auxSolicitud.setId(solicitud.getIdSolicitud());
		}
		
		auxSolicitud.setNumeroSolicitud(solicitud.getNumeroSolicitud());
		auxSolicitud.setNif(solicitud.getNif());
		auxSolicitud.setNombre(solicitud.getNombre());
		auxSolicitud.setSegundoApellido(solicitud.getSegundoApellido());
		auxSolicitud.setPrimerApellido(solicitud.getPrimerApellido());
		
		if(solicitud.getIdConsentimiento() != null){	
			auxSolicitud.setIdConsentimiento(solicitud.getIdConsentimiento());
		}else{
			auxSolicitud.setIdConsentimiento(true);
		}	
		
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
		
		if (solicitud.getFnumerosaVerificado() != null) {
			auxSolicitud.setFnumerosaVerificado(solicitud.getFnumerosaVerificado());
		}
		
		if (solicitud.getDiscapacidadVerificado() != null) {
			auxSolicitud.setDiscapacidadVerificado(solicitud.getDiscapacidadVerificado());
		}
		
		if (solicitud.getDesempleoVerificado() != null) {
			auxSolicitud.setDesempleoVerificado(solicitud.getDesempleoVerificado());
		}
		
		if(solicitud.getTipoSolicitud() != null){
			auxSolicitud.setTipo(solicitud.getTipoSolicitud());
			auxSolicitud.setTipoDescripcion(solicitud.getTipoSolicitud().getDescripcion());
		}
		
		auxSolicitud.setId(solicitud.getIdSolicitud());
		
		if (solicitud.getFechaSolicitud() != null) {
			auxSolicitud.setFechaSolicitud(sdf.format(solicitud.getFechaSolicitud()));
		}
		
		auxSolicitud.setNumTotal(numTotal);
		
		//Para el resultado de la búsqueda de solicitudes Registradas
		if(solicitud.getConvocatoria() != null){
			auxSolicitud.setEjercicio(solicitud.getConvocatoria().getEjercicio());
			auxSolicitud.setCentroGestor(solicitud.getConvocatoria().getCuerpoEscala().getCentroGestor().getDescripcion());
			auxSolicitud.setIdConvocatoria(solicitud.getConvocatoria().getId());
			auxSolicitud.setConvocatoria(solicitud.getConvocatoria());
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
		
		if(solicitud.getProvinciaByIdProvinciaDomicilio() != null){
			auxSolicitud.setDesProvinciaDomicilio(solicitud.getProvinciaByIdProvinciaDomicilio().getDescripcion());
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
		
		// Resolución Incidencia - valores no recuperados
		// Corresponde con el campo 22. Reserva discapacidad
		if(null != solicitud.getDetalleDiscapacidad()){
			auxSolicitud.setDetalleDiscapacidad(solicitud.getDetalleDiscapacidad());
			logger.info("Detalle discapacidad: "+solicitud.getDetalleDiscapacidad());
		}
		
		// Corresponde con campo 23. Adaptacion que solicita
		if(null != solicitud.getTipoDiscapacidad() && null != solicitud.getTipoDiscapacidad().getDescripcion()){
			auxSolicitud.setDescripcionTipoDiscapacidad(solicitud.getTipoDiscapacidad().getDescripcion());
			logger.info("Descripcion Tipo Discapacidad: "+solicitud.getTipoDiscapacidad().getDescripcion());
		}
		//
		
		if(solicitud.getTituloOficial() != null){
			auxSolicitud.setTituloOficial(solicitud.getTituloOficial());
			
			if(solicitud.getTituloOficial().getDescripcion() != null){	
				auxSolicitud.setDescripcionTituloOficial(solicitud.getTituloOficial().getDescripcion());
			}
		}
		
		auxSolicitud.setOtrosTitulos(solicitud.getOtrosTitulos());		
		auxSolicitud.setComentarios(solicitud.getComentarios());
		
		if(solicitud.getPais()!= null){
			auxSolicitud.setPais(solicitud.getPais());
			
			if(solicitud.getPais().getDescripcion() != null){
				auxSolicitud.setNacionPaisDomicilio(solicitud.getPais().getDescripcion());
			}
		}
		
		auxSolicitud.setFechaNacimientoSvdi(solicitud.getFechaNacimientoSvdi());
		
		if(null != solicitud.getModelo() && null != solicitud.getModelo().getNumModelo()){
			auxSolicitud.setNumModelo(solicitud.getModelo().getNumModelo());
		}
		
		if(solicitud.getIdConsentimientoAEAT() != null){
			auxSolicitud.setIdConsentimientoAeat(solicitud.getIdConsentimientoAEAT());
		}
		
		if(solicitud.getMotivoOposicion() != null){
			auxSolicitud.setMotivoOposicion(solicitud.getMotivoOposicion());
		}
		
		return auxSolicitud;
	}
	
	/**
	 * To solicitud combo bean.
	 *
	 * @param solicitud el solicitud
	 * @return el solicitud bean
	 */
	private SolicitudBean toSolicitudComboBean(SolicitudComun solicitud) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SolicitudBean auxSolicitud = new SolicitudBean();
		auxSolicitud.setNumeroSolicitud(solicitud.getNumeroSolicitud());
		auxSolicitud.setIdConsentimiento(solicitud.getIdConsentimiento());
		auxSolicitud.setIdConsentimiento(solicitud.getIdConsentimiento());
		auxSolicitud.setNif(solicitud.getNif());
		auxSolicitud.setNombre(solicitud.getNombre());
		auxSolicitud.setSegundoApellido(solicitud.getSegundoApellido());
		auxSolicitud.setPrimerApellido(solicitud.getPrimerApellido());
		
		if(solicitud.getTituloVerificado() != null){
			auxSolicitud.setTituloVerificado(solicitud.getTituloVerificado());
		}	
		
		if(solicitud.getEdadVerificada() != null){
			auxSolicitud.setEdadVerificada(solicitud.getEdadVerificada());
		}
		
		if(solicitud.getFechaNacimientoVerificada() != null){
			auxSolicitud.setFechaNacimientoVerificada(solicitud.getFechaNacimientoVerificada());
		}	
		
		if(solicitud.getEstadoSolicitud() != null){	
			auxSolicitud.setEstadoSolicitud(solicitud.getEstadoSolicitud());
		}
		
		auxSolicitud.setId(solicitud.getIdSolicitud());
		
		if(solicitud.getFechaSolicitud() != null){
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
		
		// TODO Incidencia no mapea Modelo en la solicitud al reintentar el registro
		auxSolicitud.setIdModelo(solicitud.getConvocatoria().getModelo().getIdModelo().toString());
		
		return auxSolicitud;
	}
	
	/**
	 * Inserta una solicitud_comun volcando datos desde una solicitud_comun auxiliar  .
	 *
	 * @param solicitudComunAuxiliarBean el solicitud comun auxiliar bean
	 * @param estadoSolicitud el estado solicitud
	 * @param tipoSolicitud el tipo solicitud
	 * @param dateRegistroSolicitud el date registro solicitud
	 * @param modelo el modelo
	 * @param isMod el is mod
	 * @return el solicitud comun
	 */
	public SolicitudComun insertarSolicitudComunAuxiliar(SolicitudComunAuxiliarBean solicitudComunAuxiliarBean,
											   EstadoSolicitud estadoSolicitud,
											   TipoSolicitud tipoSolicitud,
											   Date dateRegistroSolicitud,
											   Modelo modelo, boolean isMod) {
		
		SolicitudComun solicitud = new SolicitudComun();
		
		if (isMod) {
			SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
			solicitudComunQuery.setNumeroSolicitud(solicitudComunAuxiliarBean.getNumeroSolicitud());
			solicitudComunQuery.addOrder(SolicitudComunQuery.IDSOLICITUD,OrderType.DESC);
			SearchResult<SolicitudComun> listaSolicitudes = solicitudComunDAO.search(solicitudComunQuery);
			if (listaSolicitudes != null && listaSolicitudes.getResults() != null &&  listaSolicitudes.getResults().size() > 0) {
				solicitud=listaSolicitudes.getResults().get(0);
			}
		}
		
		solicitud.setNumeroSolicitud(solicitudComunAuxiliarBean.getNumeroSolicitud());
		solicitud.setFechaNacimiento(solicitudComunAuxiliarBean.getFechaNacimiento());
		solicitud.setNif(solicitudComunAuxiliarBean.getNif());
		solicitud.setSexo(solicitudComunAuxiliarBean.getSexo());
		solicitud.setNacionalidad(solicitudComunAuxiliarBean.getNacionalidad());
		solicitud.setTelefono(solicitudComunAuxiliarBean.getTelefono());
		solicitud.setEmail(solicitudComunAuxiliarBean.getEmail());
		solicitud.setCalleDomicilio(solicitudComunAuxiliarBean.getCalleDomicilio());
		solicitud.setCodigoPostalDomicilio(solicitudComunAuxiliarBean.getCodigoPostalDomicilio());
		solicitud.setMunicipioDomicilio(solicitudComunAuxiliarBean.getMunicipioDomicilio());
		solicitud.setProvinciaByIdProvinciaDomicilio(solicitudComunAuxiliarBean.getProvincia());
		solicitud.setPais(solicitudComunAuxiliarBean.getPais());
		solicitud.setConvocatoria(solicitudComunAuxiliarBean.getConvocatoria());
		solicitud.setEspecialidad(solicitudComunAuxiliarBean.getEspecialidad());
		solicitud.setTipoDiscapacidad(solicitudComunAuxiliarBean.getTipoDiscapacidad());
		solicitud.setPorcentajeDiscapacidad(solicitudComunAuxiliarBean.getPorcentajeDiscapacidad());
		solicitud.setReservaDiscapacidad(solicitudComunAuxiliarBean.getReservaDiscapacidad());
		solicitud.setDetalleDiscapacidad(solicitudComunAuxiliarBean.getDetalleDiscapacidad());
		solicitud.setTituloOficial(solicitudComunAuxiliarBean.getTituloOficial());
		solicitud.setEstadoSolicitud(estadoSolicitud);
		solicitud.setFechaUltActualizacion(dateRegistroSolicitud);
		solicitud.setTipoSolicitud(tipoSolicitud);
		solicitud.setFechaNacimientoVerificada(Constantes.FECHA_NACIMIENTO_NO_COMPROBADA);
		solicitud.setTituloVerificado(Constantes.TITULO_NO_COMPROBADO);
		solicitud.setEdadVerificada(Constantes.EDAD_NO_COMPROBADA);
		solicitud.setFechaSolicitud(dateRegistroSolicitud);
		solicitud.setNombre(solicitudComunAuxiliarBean.getNombre());
		solicitud.setPrimerApellido(solicitudComunAuxiliarBean.getPrimerApellido());
		solicitud.setSegundoApellido(solicitudComunAuxiliarBean.getSegundoApellido());
		solicitud.setIdConsentimiento(solicitudComunAuxiliarBean.getIdConsentimiento());
		solicitud.setModelo(modelo);
		solicitud.setProvinciaByIdProvinciaExamen(solicitudComunAuxiliarBean.getProvinciaExamen());
		solicitud.setOtrosTitulos(solicitudComunAuxiliarBean.getOtrosTitulos());
		solicitud.setDesempleoVerificado(Constantes.DESEMPLEO_NO_COMPROBADO);
		solicitud.setFnumerosaVerificado(Constantes.FNUMEROSA_NO_COMPROBADO);
		solicitud.setDiscapacidadVerificado(Constantes.DISCAPACIDAD_NO_COMPROBADO);
		solicitud.setFunHabilitado(false);

		
		if (isMod) {
			solicitudComunDAO.update(solicitud);
		} else {
			solicitudComunDAO.insert(solicitud);
		}
		
		return solicitud;

	}
	
	
	/**
	 * Modifica una solicitud  .
	 *
	 * @param solicitudBean SolicitudBean
	 */
	public void modificarSolicitud (SolicitudBean  solicitudBean){
		
		SolicitudComun solicitud =  toSolicitud(solicitudBean);
		solicitudComunDAO.update(solicitud);
	}
	
	/**
	 * To solicitud.
	 *
	 * @param solicitudBean el solicitud bean
	 * @return el solicitud comun
	 */
	public SolicitudComun toSolicitud (SolicitudBean  solicitudBean){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		SolicitudComun solicitud = new SolicitudComun();
		
		solicitud.setConvocatoria(solicitudBean.getConvocatoria());
		solicitud.setIdConsentimiento(solicitudBean.getIdConsentimiento());
		solicitud.setCalleDomicilio(solicitudBean.getCalleDomicilio());
		solicitud.setCodigoPostalDomicilio(solicitudBean.getCodigoPostalDomicilio());
		solicitud.setComentarios(solicitudBean.getComentarios());
		solicitud.setConvocatoria(solicitudBean.getConvocatoria());	
		solicitud.setDetalleDiscapacidad(solicitudBean.getDetalleDiscapacidad());
		solicitud.setEdadVerificada(solicitudBean.getEdadVerificada());
		solicitud.setEmail(solicitudBean.getEmail());
		solicitud.setEspecialidad(solicitudBean.getEspecialidad());
		solicitud.setEstadoSolicitud(solicitudBean.getEstadoSolicitud());
		solicitud.setFechaNacimiento(solicitudBean.getFechaNacimiento());
		if(solicitudBean.getFechaNacimientoVerificada() != null){
			solicitud.setFechaNacimientoVerificada(solicitudBean.getFechaNacimientoVerificada());
		}
		if (solicitudBean.getFnumerosaVerificado() != null) {
			solicitud.setFnumerosaVerificado(solicitudBean.getFnumerosaVerificado());
		}
		if (solicitudBean.getDiscapacidadVerificado() != null) {
			solicitud.setDiscapacidadVerificado(solicitudBean.getDiscapacidadVerificado());
		}
		if (solicitudBean.getDesempleoVerificado() != null) {
			solicitud.setDesempleoVerificado(solicitudBean.getDesempleoVerificado());
		}
		if (solicitudBean.getVictimasVerificado() != null) {
			solicitud.setVictimasVerificado(solicitudBean.getVictimasVerificado());
		}
		

		try {
			solicitud.setFechaSolicitud(sdf.parse(solicitudBean.getFechaSolicitud()));
		} catch (ParseException e) {
			logger.error("No se ha podido parserar la fecha de solicitud",e);		
			
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
			
			while (it.hasNext()){
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
		
		if(solicitudBean.getFunHabilitado() != null) {
			solicitud.setFunHabilitado(solicitudBean.getFunHabilitado());
		} else {
			solicitud.setFunHabilitado(false);
		}
		
		// TODO Incidencia no figura Modelo en Solicitud
		Modelo modelo = new Modelo();
		modelo.setIdModelo(solicitudBean.getConvocatoria().getModelo().getIdModelo());
		solicitud.setModelo(modelo);
		
		return solicitud;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudesManager#actualizarEstadoSolicitud(es.map.ipsg.bean.SolicitudBean, int)
	 */
	public void actualizarEstadoSolicitud(SolicitudBean solicitudBeanAux, int i) {
		
		EstadoSolicitudQuery estadoSolicitudQuery = new EstadoSolicitudQuery();
		estadoSolicitudQuery.setId(i);
		EstadoSolicitudBean estadoSolicitudBean = estadoSolicitudManager.buscarEstadoSolicitudById(estadoSolicitudQuery);
		EstadoSolicitud estado = toEstado(estadoSolicitudBean);
		solicitudBeanAux.setEstadoSolicitud(estado);
		SolicitudComun solicitud = toSolicitud(solicitudBeanAux);
		
		solicitudComunDAO.update(solicitud);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudesManager#actualizarEstadoSolicitudRegistro(es.map.ips.model.SolicitudComunQuery, es.map.ips.model.EstadoSolicitudQuery)
	 */
	public Long actualizarEstadoSolicitudRegistro(SolicitudComunQuery solicitudQuery,EstadoSolicitudQuery estadoSolicitudQuery) {
		//Busca la solicitud
		SolicitudComun auxSolicitud = solicitudComunDAO.searchUnique(solicitudQuery);
		if(auxSolicitud == null){
			return 0L;
		}	
		Long result;
		Date fechaActualizacion = new Date();
		//Busca el estado por el id de estado
		EstadoSolicitud estadoSolicitud = estadoSolicitudManager.buscarEstadoSolicitudByIdRegistro(estadoSolicitudQuery); 
		if(estadoSolicitud != null){
			//Si encuentra el estado se lo asigna a la query
			logger.info("1.SolicitudManagerImpl-Estado: "+estadoSolicitud.getDescripcion());
			auxSolicitud.setEstadoSolicitud(estadoSolicitud);
			auxSolicitud.setFechaUltActualizacion(fechaActualizacion);
		}else{
			result = 0L;
			logger.info("2.SolicitudManagerImpl-Estado-ERROR");
		}	
		try{
			//Actualiza la solicitud con el estado nuevo
			solicitudComunDAO.update(auxSolicitud);
			result = 1L;
		}catch(Exception e){
			logger.warn(e);
			result = 0L;
			logger.error("Error actualizar estado solicitud",e);
		}

		return result;
	}

	
	/**
	 * To estado.
	 *
	 * @param estadoSolicitudBean el estado solicitud bean
	 * @return el estado solicitud
	 */
	private EstadoSolicitud toEstado(EstadoSolicitudBean estadoSolicitudBean) {
		EstadoSolicitud estado = new EstadoSolicitud();
		estado.setId(estadoSolicitudBean.getId());
		estado.setDescripcion(estadoSolicitudBean.getDescripcion());
		
		return estado;
	}
	
	/**
	 * Buscar solicitud.
	 *
	 * @param idConvocatoria Long
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
	private PagoSolicitud toPagoSolicitud (PagoSolicitudBean pagoSolicitudBean){
		
		PagoSolicitud pagoSolicitud = new PagoSolicitud();
		pagoSolicitud.setEntidadFinanciera(pagoSolicitudBean.getEntidadFinanciera());
		pagoSolicitud.setFechaIntento(pagoSolicitudBean.getFechaIntento());
		pagoSolicitud.setId(pagoSolicitudBean.getIdSolicitud());
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
	private RegistroSolicitud toRegistroSolicitud (RegistroSolicitudBean registroSolicitudBean){
		
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
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudesManager#obtenerNumeroSolicitud()
	 */
	public String obtenerNumeroSolicitud(){
		String nSolicitud;
		NumeroSolicitud numeroSolicitud = new NumeroSolicitud();
		
		ParametrosConfiguracionQuery parametrosConfiguracionQuery = new ParametrosConfiguracionQuery();
		parametrosConfiguracionQuery.setNombreCampo(Constantes.SOLICITUD_PARAMETROS_CONFIG);
		ParametrosConfiguracion parametrosConfiguracion = parametrosConfiguracionDAO.searchUnique(parametrosConfiguracionQuery);
		
		if(parametrosConfiguracion != null && parametrosConfiguracion.getValorCampo().equals(Constantes.SOLICITUD_VALOR_PARAMETROS_CONFIG)){
			nSolicitud = numeroSolicitud.obtenerNumeroJustificanteEJB();
		}else{
			Integer contador = contadorNumSolicitudCustomDAO.obtenerNumContador(Constantes.ID_TASA_GENERICA, Constantes.ID_MODELO_GENERICO);
			nSolicitud = numeroSolicitud.obtenerNumeroJustificanteLocal(contador, Constantes.ID_TASA_GENERICA, Constantes.ID_MODELO_GENERICO);
		}
		
		logger.info("numero de solicitud generado: "+nSolicitud);
		return nSolicitud;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudesManager#buscarLogPorNumeroSolicitudYNif(java.lang.String, java.lang.String)
	 */
	public ArrayList<Integer> buscarLogPorNumeroSolicitudYNif (String nSolicitud, String nif){
		
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		if(nSolicitud!=null && !nSolicitud.equals("")){
			solicitudQuery.setNumeroSolicitudComparator(Comparator.LIKE_LEFT_RIGHT);
			solicitudQuery.setNumeroSolicitud(nSolicitud.trim());
		}
		
		if(nif!=null && !nif.equals("")){
			solicitudQuery.setNifComparator(Comparator.LIKE_LEFT_RIGHT);
			solicitudQuery.setNif(nif.trim());
		}
		
		ArrayList<SolicitudBean> resultadoSolicitud= buscarSolicitudAll(solicitudQuery);
		
		if(resultadoSolicitud != null && resultadoSolicitud.size()>0){
			return buscarIdLogs(resultadoSolicitud);
		}else{
			return null;
		}	
	}

	/**
	 * Buscar id logs.
	 *
	 * @param resultadoSolicitud el resultado solicitud
	 * @return el array list
	 */
	private ArrayList<Integer> buscarIdLogs(ArrayList<SolicitudBean> resultadoSolicitud){
		ArrayList<Long> arrIds = new ArrayList<Long>();
		ArrayList<Integer> arrIdsLog = new ArrayList<Integer>();
		
		PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
		RegistroSolicitudQuery registroSolicitudQuery = new RegistroSolicitudQuery();
		
		// se recupera la lista de identificadores asociados al numero de solicitud del filtro
		for(SolicitudBean solAux : resultadoSolicitud){
			arrIds.add(solAux.getId());	
			pagoSolicitudQuery.addSolicitudIn(solAux.getId());
			registroSolicitudQuery.addSolicitudIn(solAux.getId());
		}

		// buscamos los id_log_servicio de las tablas pago_solicitud y registro_solicitud
		ArrayList<PagoSolicitudBean> pagos = pagoSolicitudManager.buscarPagoSolicitudAll(pagoSolicitudQuery);
		ArrayList<RegistroSolicitudBean> registros =registroSolicitudManager.buscarRegistroSolicitudAll(registroSolicitudQuery);
		
		for(PagoSolicitudBean pagoAux : pagos){
			if(pagoAux.getLogservicio() != null){	
				arrIdsLog.add(pagoAux.getLogservicio().getId());
			}	
		}	
		
		for(RegistroSolicitudBean registroAux : registros){
			if(registroAux.getLogServicio() != null){	
				arrIdsLog.add(registroAux.getLogServicio().getId());
			}	
		}
		
		return arrIdsLog;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudesManager#obtenerDetalleRegistroPago(java.lang.Long)
	 */
	public DetalleRegistroPagoBean obtenerDetalleRegistroPago(Long idSolicitud){
		DetalleRegistroPagoBean detalle = new DetalleRegistroPagoBean();
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.setIdSolicitud(idSolicitud);
		
		PagoSolicitudQuery pagoSolicitudQuery = new PagoSolicitudQuery();
		pagoSolicitudQuery.setSolicitudComun(solicitudQuery);
		
		List<PagoSolicitudBean> pagos = pagoSolicitudManager.buscarPagoSolicitudAll(pagoSolicitudQuery);
		
		RegistroSolicitudQuery registroSolicitudQuery = new RegistroSolicitudQuery();
		registroSolicitudQuery.setSolicitudComun(solicitudQuery);

		List<RegistroSolicitudBean> registros = registroSolicitudManager.buscarRegistroSolicitudAll(registroSolicitudQuery);
		
		detalle.setPagos(pagos);
		detalle.setRegistros(registros);
		
		return detalle;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudesManager#detalleSolicitud(es.map.ips.model.SolicitudComunQuery)
	 */
	public DetalleSolicitudBean detalleSolicitud(SolicitudComunQuery solicitudQuery) {
		SolicitudComun solicitud = solicitudComunDAO.searchUnique(solicitudQuery);
		if(solicitud != null){
			DetalleSolicitudBean detalleSolicitudBean = toDetalleSolicitudBean(solicitud);
			if(detalleSolicitudBean == null){
				return null;
			}else{
				return detalleSolicitudBean;
			}
		}else{
			return null;
		}		
	}
	
	/**
	 * 	
	 *
	 * @param solicitud el solicitud
	 * @return el detalle solicitud bean
	 */
	private DetalleSolicitudBean toDetalleSolicitudBean(SolicitudComun solicitud) {
		//Formato de fechas
		SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");
		//Datos de la convocatoria
		String centroGestor = "";
		
		if(solicitud.getConvocatoria() != null){
			centroGestor = solicitud.getConvocatoria().getCuerpoEscala().getCentroGestor().getDescripcion().toUpperCase();
		}
		
		String numJustificante = solicitud.getNumeroSolicitud();
		String ejercicioSolicitud = solicitud.getConvocatoria().getEjercicio();
		String registro = String.valueOf(solicitud.getIdSolicitud());
		String nacionalidad = "";
		
		if(solicitud.getNacionalidad() != null){
			nacionalidad = solicitud.getNacionalidad().toUpperCase();
		}
		
		String correoElectronico = "";
		
		if(solicitud.getEmail() != null){
			correoElectronico = solicitud.getEmail();
		}
		
		String estadoSolicitud = "";
		Integer idEstadoSolicitud = 0;
		
		if(solicitud.getEstadoSolicitud() != null){
			estadoSolicitud = solicitud.getEstadoSolicitud().getDescripcion().toUpperCase();
		}

		//Datos del usuario
		String nombre = "";
		String primerApellido ="";
		String segundoApellido = "";

		if(solicitud.getNombre() != null){
			nombre = solicitud.getNombre().toUpperCase();
		}
		
		if(solicitud.getPrimerApellido() != null){
			primerApellido = solicitud.getPrimerApellido().toUpperCase();
		}
		
		if(solicitud.getSegundoApellido() != null){
			segundoApellido = solicitud.getSegundoApellido().toUpperCase();
		}

		String fechaNacimiento = "";
		
		if(solicitud.getFechaNacimiento() != null){
			fechaNacimiento = formatoFecha.format(solicitud.getFechaNacimiento());
		}
		
		String nif = solicitud.getNif();
		char sexo = ' ';
		
		if(solicitud.getSexo() != null && !"".equals(solicitud.getSexo())){
			sexo = solicitud.getSexo();
		}

		String provinciaNacimiento = "";
		
		if(solicitud.getProvinciaByIdProvinciaNacimiento() != null && 
				!"".equals(solicitud.getProvinciaByIdProvinciaNacimiento().getDescripcion())){
			provinciaNacimiento = solicitud.getProvinciaByIdProvinciaNacimiento().getDescripcion().toUpperCase();
		}

		String localidadNacimiento = "";
		
		if(solicitud.getLocalidadNacimiento() != null &&
				!"".equals(solicitud.getLocalidadNacimiento())){
			localidadNacimiento = solicitud.getLocalidadNacimiento().toUpperCase();
		}
		
		//Datos del domicilio
		String calleDomicilio = "";
		
		if(solicitud.getCalleDomicilio() != null && !"".equals(solicitud.getCalleDomicilio())){
			calleDomicilio= solicitud.getCalleDomicilio().toUpperCase();
		}

		String codPostalDomicilio = "";
		
		if(solicitud.getCodigoPostalDomicilio() != null && !"".equals(solicitud.getCodigoPostalDomicilio())){
			codPostalDomicilio= solicitud.getCodigoPostalDomicilio();
		}

		String municipioDomicilio = "";
		
		if(solicitud.getMunicipioDomicilio() != null && !"".equals(solicitud.getMunicipioDomicilio())){
			municipioDomicilio= solicitud.getMunicipioDomicilio().toUpperCase();
		}

		String provinciaDomicilio = "";
		
		if(solicitud.getProvinciaByIdProvinciaDomicilio() != null && !"".equals(solicitud.getProvinciaByIdProvinciaDomicilio())){
			provinciaDomicilio = solicitud.getProvinciaByIdProvinciaDomicilio().getDescripcion().toUpperCase();
		}

		String nacionDomicilio = "";
		
		if(solicitud.getPais() != null && !"".equals(solicitud.getPais().getDescripcion())){
			nacionDomicilio= solicitud.getPais().getDescripcion().toUpperCase();
		}

		String telefono = "";
		
		if(solicitud.getTelefono() != null && !"".equals(solicitud.getTelefono())){
			telefono= solicitud.getTelefono();
		}

		//Datos de la convocatoria
		String cuerpo= "";
		
		if(solicitud.getConvocatoria().getCuerpoEscala() != null && !"".equals(solicitud.getConvocatoria().getCuerpoEscala())){
			cuerpo= solicitud.getConvocatoria().getCuerpoEscala().getDescripcion().toUpperCase();
		}
		
		String grupo = "";
		
		if(solicitud.getConvocatoria().getCuerpoEscala().getGrupo() != null){
			grupo = String.valueOf(solicitud.getConvocatoria().getCuerpoEscala().getGrupo().getDescripcion().toUpperCase());
		}
		
		String especialidad = "";
		
		if(solicitud.getEspecialidad() != null && !"".equals(solicitud.getEspecialidad())){
			especialidad = solicitud.getEspecialidad().getDescripcion().toUpperCase();
		}
		
		String formaAcceso = "";
		
		if(solicitud.getConvocatoria().getFormaAcceso() != null && !"".equals(solicitud.getConvocatoria().getFormaAcceso())){
			formaAcceso = solicitud.getConvocatoria().getFormaAcceso().getDescripcion().toUpperCase();
		}
		
		String ministerio = "";
		
		if(solicitud.getConvocatoria().getCuerpoEscala().getCentroGestor().getMinisterio() != null 
				&& !"".equals(solicitud.getConvocatoria().getCuerpoEscala().getCentroGestor().getMinisterio())){
			ministerio = solicitud.getConvocatoria().getCuerpoEscala().getCentroGestor().getMinisterio().getDescripcion().toUpperCase();
		}
		
		String fechaBOE = "";
		
		if(solicitud.getConvocatoria().getFechaBoe() != null && !"".equals(solicitud.getConvocatoria().getFechaBoe())){
			fechaBOE = formatoFecha.format(solicitud.getConvocatoria().getFechaBoe());
		}
		
		String provinciaExamen = "";
		
		if(solicitud.getProvinciaByIdProvinciaExamen() != null && !"".equals(solicitud.getProvinciaByIdProvinciaExamen())){
			provinciaExamen = solicitud.getProvinciaByIdProvinciaExamen().getDescripcion().toUpperCase();
		}
		
		String discapacidad = "";
		
		if(solicitud.getTipoDiscapacidad() != null && !"".equals(solicitud.getTipoDiscapacidad())){
			discapacidad = solicitud.getTipoDiscapacidad().getDescripcion().toUpperCase();
		}
		
		char reservaDiscapacitados = ' ';
		
		if(solicitud.getReservaDiscapacidad() != null && !"".equals(solicitud.getReservaDiscapacidad())){
			reservaDiscapacitados = solicitud.getReservaDiscapacidad();
		}
		
		String auxAdaptacion = "0";
		
		if(solicitud.getPorcentajeDiscapacidad() != null){
			auxAdaptacion = String.valueOf(solicitud.getPorcentajeDiscapacidad());
		}
		
		Float importe = 0f;
		
		if(solicitud.getConvocatoria().getImporte() != null){
			importe = solicitud.getConvocatoria().getImporte();
		}
		
		String dirigidoA = "";
		
		if(solicitud.getConvocatoria().getDirigidoA() != null){
			dirigidoA = solicitud.getConvocatoria().getDirigidoA().toUpperCase();
		}

		Long adaptacionDiscapacidad = 0L;
		
		if(auxAdaptacion != null){
			adaptacionDiscapacidad = Long.parseLong(auxAdaptacion);
		}

		String motivoDiscapacidad = "";
		
		if(solicitud.getDetalleDiscapacidad() != null && !"".equals(solicitud.getDetalleDiscapacidad())){
			motivoDiscapacidad= solicitud.getDetalleDiscapacidad().toUpperCase();
		}

		//Titulos academicos oficiales
		String titulos = "";
		
		if(solicitud.getTituloOficial() != null){
			titulos= solicitud.getTituloOficial().getDescripcion().toUpperCase();
		}

		DetalleSolicitudBean solicitudBean = new DetalleSolicitudBean();
		logger.info("Despues de crear el bean");

		//Datos de la convocatoria
		solicitudBean.setCentroGestor(centroGestor);
		solicitudBean.setNumJustificante(numJustificante);
		solicitudBean.setEjercicioSolicitud(ejercicioSolicitud);
		solicitudBean.setRegistro(registro);
		solicitudBean.setNacionalidad(nacionalidad);
		solicitudBean.setCorreoElectronico(correoElectronico);
		solicitudBean.setDirigidoA(dirigidoA);
		
		//Datos del usuario
		solicitudBean.setNombre(nombre);
		solicitudBean.setPrimerApellido(primerApellido);
		solicitudBean.setSegundoApellido(segundoApellido);
		solicitudBean.setFechaNacimiento(fechaNacimiento);
		String nombreCompleto = nombre + " " + primerApellido +  " " + segundoApellido;
		solicitudBean.setNombreCompleto(nombreCompleto);
		solicitudBean.setNif(nif);
		solicitudBean.setSexo(sexo);
		solicitudBean.setProvinciaNacimiento(provinciaNacimiento);
		solicitudBean.setLocalidadNacimiento(localidadNacimiento);
		
		if(solicitud.getIdConsentimiento()!= null){
			if(solicitud.getIdConsentimiento() == true){
				solicitudBean.setConsentimiento(Constantes.SI);
			}else if(solicitud.getIdConsentimiento() == false){
				solicitudBean.setConsentimiento(Constantes.NO);
			}
		}else{
			solicitudBean.setConsentimiento("");
		}	

		//Datos del domicilio
		solicitudBean.setCalleDomicilio(calleDomicilio);
		solicitudBean.setCodPostalDomicilio(codPostalDomicilio);
		solicitudBean.setMunicipioDomicilio(municipioDomicilio);
		solicitudBean.setProvinciaDomicilio(provinciaDomicilio);
		solicitudBean.setNacionDomicilio(nacionDomicilio);
		solicitudBean.setTelefono(telefono);
		
		//Datos de la convocatoria
		solicitudBean.setCuerpo(cuerpo);
		solicitudBean.setGrupo(grupo);
		solicitudBean.setEspecialidad(especialidad);
		solicitudBean.setFormaAcceso(formaAcceso);
		solicitudBean.setMinisterio(ministerio);
		solicitudBean.setFechaBOE(fechaBOE);
		solicitudBean.setProvinciaExamen(provinciaExamen);
		solicitudBean.setDiscapacidad(discapacidad);		
		solicitudBean.setReservaDiscapacitados(reservaDiscapacitados);
		solicitudBean.setAdaptacionDiscapacidad(adaptacionDiscapacidad);
		solicitudBean.setMotivoDiscapacidad(motivoDiscapacidad);
		solicitudBean.setImporte(importe);
		
		//Titulos academicos oficiales
		solicitudBean.setTitulos(titulos);

		//Otros datos
		solicitudBean.setEstadoSolicitud(estadoSolicitud);
		solicitudBean.setIdEstadoSolicitud(idEstadoSolicitud);
		
		return solicitudBean;
	}

	/**
	 * Obtiene el solicitud comun DAO.
	 *
	 * @return el solicitud comun DAO
	 */
	public SolicitudComunDAO getSolicitudComunDAO() {
		return solicitudComunDAO;
	}

	/**
	 * Establece el solicitud comun DAO.
	 *
	 * @param solicitudComunDAO el nuevo solicitud comun DAO
	 */
	public void setSolicitudComunDAO(SolicitudComunDAO solicitudComunDAO) {
		this.solicitudComunDAO = solicitudComunDAO;
	}

	/**
	 * Obtiene el estado solicitud manager.
	 *
	 * @return el estado solicitud manager
	 */
	public EstadoSolicitudManager getEstadoSolicitudManager() {
		return estadoSolicitudManager;
	}

	/**
	 * Establece el estado solicitud manager.
	 *
	 * @param estadoSolicitudManager el nuevo estado solicitud manager
	 */
	public void setEstadoSolicitudManager(
			EstadoSolicitudManager estadoSolicitudManager) {
		this.estadoSolicitudManager = estadoSolicitudManager;
	}
	
	/**
	 * Obtiene el contador num solicitud custom DAO.
	 *
	 * @return el contador num solicitud custom DAO
	 */
	public ContadorNumSolicitudCustomDAO getContadorNumSolicitudCustomDAO() {
		return contadorNumSolicitudCustomDAO;
	}

	/**
	 * Establece el contador num solicitud custom DAO.
	 *
	 * @param contadorNumSolicitudCustomDAO el nuevo contador num solicitud custom DAO
	 */
	public void setContadorNumSolicitudCustomDAO(
			ContadorNumSolicitudCustomDAO contadorNumSolicitudCustomDAO) {
		this.contadorNumSolicitudCustomDAO = contadorNumSolicitudCustomDAO;
	}
	
	/**
	 * Obtiene el parametros configuracion DAO.
	 *
	 * @return el parametros configuracion DAO
	 */
	public ParametrosConfiguracionDAO getParametrosConfiguracionDAO() {
		return parametrosConfiguracionDAO;
	}

	/**
	 * Establece el parametros configuracion DAO.
	 *
	 * @param parametrosConfiguracionDAO el nuevo parametros configuracion DAO
	 */
	public void setParametrosConfiguracionDAO(
			ParametrosConfiguracionDAO parametrosConfiguracionDAO) {
		this.parametrosConfiguracionDAO = parametrosConfiguracionDAO;
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

	/**
	 * Obtiene el registro solicitud manager.
	 *
	 * @return el registro solicitud manager
	 */
	public RegistroSolicitudManager getRegistroSolicitudManager() {
		return registroSolicitudManager;
	}

	/**
	 * Establece el registro solicitud manager.
	 *
	 * @param registroSolicitudManager el nuevo registro solicitud manager
	 */
	public void setRegistroSolicitudManager(
			RegistroSolicitudManager registroSolicitudManager) {
		this.registroSolicitudManager = registroSolicitudManager;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudesManager#obtenerNumeroSolicitud(java.lang.String)
	 */
	public String obtenerNumeroSolicitud(String modelo){
		String nSolicitud;
		NumeroSolicitud numeroSolicitud = new NumeroSolicitud();
		
		ParametrosConfiguracionQuery parametrosConfiguracionQuery = new ParametrosConfiguracionQuery();
		parametrosConfiguracionQuery.setNombreCampo(Constantes.SOLICITUD_PARAMETROS_CONFIG);
		ParametrosConfiguracion parametrosConfiguracion = parametrosConfiguracionDAO.searchUnique(parametrosConfiguracionQuery);
		
		if(!modelo.equals(Constantes.MODELO_ASOCIADO_GENERACION_EJB) && parametrosConfiguracion != null 
				&& parametrosConfiguracion.getValorCampo().equals(Constantes.SOLICITUD_VALOR_PARAMETROS_CONFIG)){
			nSolicitud = numeroSolicitud.obtenerNumeroJustificanteEJB();
		}else{
			// Para obtener el contador de bbdd siempre se usa la misma tasa: 001
			Integer contador = contadorNumSolicitudCustomDAO.obtenerNumContador(Constantes.ID_TASA_GENERICA, Constantes.ID_MODELO_GENERICO);
		
			if(modelo.equalsIgnoreCase(Constantes.MODELO79007)){						
				// Para componer el nº de solicitud se utiliza la tasa del modelo seleccionado.
				nSolicitud = numeroSolicitud.obtenerNumeroJustificanteLocal(contador, Constantes.CODIGO_TASA_JUSTICIA, Constantes.ID_MODELO_GENERICO);
			}else{								
			// Para componer el nº de solicitud se utiliza la tasa del modelo seleccionado.
			nSolicitud = numeroSolicitud.obtenerNumeroSolicitud(contador, modelo);
			}
		}
		
		logger.info("numero de solicitud generado: "+nSolicitud);
		return nSolicitud;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudesManager#borrarSolicitudComun(java.lang.Long)
	 */
	@Override
	public void borrarSolicitudComun(Long idSolicitud) {
		try{
			if(null!=idSolicitud && !idSolicitud.equals("")){
				solicitudComunDAO.delete(idSolicitud);
			}
		}catch(Exception e){
			logger.error("No se puede eliminar de solicitud_comun con id_solicitud=" + idSolicitud);
			logger.error("Error en borrarSolicitudComun", e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudesManager#buscarSolicitudComunByFechaSolicitud(es.map.ips.model.SolicitudComunQuery)
	 */
	@Override
	public ArrayList<Long> buscarSolicitudComunByFechaSolicitud(SolicitudComunQuery solicitudComunQuery) {
		
		ArrayList<Long> idSolicitudes=new ArrayList<Long>();
		
		SearchResult<SolicitudComun> solicitud= solicitudComunDAO.search(solicitudComunQuery);
		
		if(solicitud != null || solicitud.getResults().size()>0){
			for (int i = 0; i < solicitud.getResults().size(); i++) {
				idSolicitudes.add(solicitud.getResults().get(i).getIdSolicitud());
			}
			
			return idSolicitudes;
		}
				
		return null;
	}
}
