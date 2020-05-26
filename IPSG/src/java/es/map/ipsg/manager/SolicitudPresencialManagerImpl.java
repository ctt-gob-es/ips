package es.map.ipsg.manager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.SolicitudComunDAO;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.CorreoElectronico;
import es.map.ips.model.Modelo;
import es.map.ips.model.PagoSolicitud;
import es.map.ips.model.RegistroSolicitud;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.TipoSolicitudQuery;
import es.map.ipsg.bean.CorreoElectronicoBean;
import es.map.ipsg.bean.PagoSolicitudBean;
import es.map.ipsg.bean.RegistroSolicitudBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.util.Constantes;

/**
 *  Clase que implementa el SolicitudManager.
 *
 * @author amartinl
 */
public class SolicitudPresencialManagerImpl implements SolicitudPresencialManager {

	/** el solicitud comun DAO. */
	//Variables
	private SolicitudComunDAO solicitudComunDAO;	
	
	/** el arr solicitud. */
	private ArrayList<SolicitudBean> arrSolicitud;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(SolicitudPresencialManagerImpl.class);
	
	/** La constante STRING_SIMPLEDATEFORMAT. */
	private static final String STRING_SIMPLEDATEFORMAT = "yyyy-MM-dd HH:mm:ss";

	/**
	 * Busca todas las solicitudes Presenciales.
	 *
	 * @param solicitudQuery SolicitudQuery
	 * @return arrSolicitud ArrayList
	 */
	public ArrayList<SolicitudBean> buscarSolicitudCombo(SolicitudComunQuery solicitudQuery){
		
		TipoSolicitudQuery tipoSolicitudQuery = new TipoSolicitudQuery();
		tipoSolicitudQuery.setId(Constantes.TIPO_SOLICITUD_PRESENCIAL);
		solicitudQuery.setTipoSolicitud(tipoSolicitudQuery);
		SearchResult<SolicitudComun> Solicitud = buscarSolicitud(solicitudQuery);
		arrSolicitud= new ArrayList<SolicitudBean>();
		
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
	 * @param SolicitudQuery el solicitud query
	 * @return el search result
	 */
	private SearchResult<SolicitudComun> buscarSolicitud(SolicitudComunQuery SolicitudQuery) {
			ApplicationException.assertNotNull(SolicitudQuery, "SolicitudQuery no puede ser null");
		
		return solicitudComunDAO.search(SolicitudQuery);
	}

	
	/**
	 * Buscar solicitud all.
	 *
	 * @param solicitudQuery SolicitudQuery
	 * @return arrsolicitud ArrayList
	 */
	public ArrayList<SolicitudBean> buscarSolicitudAll(SolicitudComunQuery solicitudQuery){
		TipoSolicitudQuery tipoSolicitudQuery = new TipoSolicitudQuery();
		tipoSolicitudQuery.setId(Constantes.TIPO_SOLICITUD_PRESENCIAL);
		solicitudQuery.setTipoSolicitud(tipoSolicitudQuery);
		SearchResult<SolicitudComun> Solicitud = buscarSolicitud(solicitudQuery);
		Integer numTotal = Solicitud.getNumResults();
		int iNumTotal = 0;
		
		if (numTotal != null){
			iNumTotal = numTotal.intValue();
		}
		arrSolicitud = new ArrayList<SolicitudBean>();
		
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
		arrSolicitud= new ArrayList<SolicitudBean>();
		
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
	private SolicitudBean toSolicitudBean(SolicitudComun solicitud, int numTotal) {
	SimpleDateFormat sdf = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
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
		
		if(solicitud.getTipoSolicitud() != null){
			auxSolicitud.setTipo(solicitud.getTipoSolicitud());
			auxSolicitud.setTipoDescripcion(solicitud.getTipoSolicitud().getDescripcion());
		}
		
		auxSolicitud.setId(solicitud.getIdSolicitud());
		
		if (solicitud.getFechaSolicitud() != null){
			auxSolicitud.setFechaSolicitud(sdf.format(solicitud.getFechaSolicitud()));			
		}
		
		if (solicitud.getTipoSolicitud()!=null){			
			auxSolicitud.setTipoDescripcion(solicitud.getTipoSolicitud().getDescripcion());
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
		
		toSolicitudBean2(solicitud,auxSolicitud);
		
		
		return auxSolicitud;
	}
	
	/**
	 * To solicitud bean 2.
	 *
	 * @param solicitud el solicitud
	 * @param auxSolicitud el aux solicitud
	 */
	private void toSolicitudBean2(SolicitudComun solicitud, SolicitudBean auxSolicitud) {
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
		
		if(solicitud.getTituloOficial() != null){
			auxSolicitud.setTituloOficial(solicitud.getTituloOficial());
			
			if(solicitud.getTituloOficial().getDescripcion() != null){	
				auxSolicitud.setDescripcionTituloOficial(solicitud.getTituloOficial().getDescripcion());
			}
		}
		
		auxSolicitud.setComentarios(solicitud.getComentarios());
		
		if(solicitud.getPais()!= null){
			auxSolicitud.setPais(solicitud.getPais());
			if(solicitud.getPais().getDescripcion() != null){
				auxSolicitud.setNacionPaisDomicilio(solicitud.getPais().getDescripcion());
			}
		}
	}
	
	/**
	 * To correo electronico bean.
	 *
	 * @param correoElectronico el correo electronico
	 * @return el correo electronico bean
	 */
	public CorreoElectronicoBean toCorreoElectronicoBean(CorreoElectronico correoElectronico){
		
		CorreoElectronicoBean correoElectronicoBean = new CorreoElectronicoBean();
		
		if(correoElectronico.getId()!=null){
			correoElectronicoBean.setId(correoElectronico.getId());
		}
		
		correoElectronicoBean.setDe(correoElectronico.getDe());
		correoElectronicoBean.setPara(correoElectronico.getPara());
		correoElectronicoBean.setMensaje(correoElectronico.getMensaje());
		correoElectronicoBean.setAsunto(correoElectronico.getAsunto());
		correoElectronicoBean.setFechaEnvio(correoElectronico.getFechaEnvio());
		correoElectronicoBean.setEstado(correoElectronico.getEstado());
		correoElectronicoBean.setSolicituds(correoElectronico.getSolicituds());
		correoElectronicoBean.setAlertas(correoElectronico.getAlertas());
				
		return correoElectronicoBean;
	}
	
	/**
	 * To solicitud combo bean.
	 *
	 * @param solicitud el solicitud
	 * @return el solicitud bean
	 */
	private SolicitudBean toSolicitudComboBean(SolicitudComun solicitud) {
		SimpleDateFormat sdf = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
		SolicitudBean auxSolicitud = new SolicitudBean();
		auxSolicitud.setNumeroSolicitud(solicitud.getNumeroSolicitud());
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
		auxSolicitud.setFechaSolicitud(sdf.format(solicitud.getFechaSolicitud()));
		auxSolicitud.setTipo(solicitud.getTipoSolicitud());
		auxSolicitud.setComentarios(solicitud.getComentarios());
		auxSolicitud.setCodigoPostalDomicilio(solicitud.getCodigoPostalDomicilio());
		
		if(solicitud.getPais()!= null){
			auxSolicitud.setPais(solicitud.getPais());
			if(solicitud.getPais().getDescripcion() != null){
				auxSolicitud.setNacionPaisDomicilio(solicitud.getPais().getDescripcion());
			}
		}
		
		return auxSolicitud;
	}
	
	/**
	 * Modifica una solicitud Registrada .
	 *
	 * @param solicitudBean SolicitudBean
	 */
	public void modificarSolicitudRegistrada (SolicitudBean  solicitudBean)
	{
		SolicitudComun solicitud =  toSolicitud(solicitudBean);
		solicitudComunDAO.update(solicitud);
	}
	
	/**
	 * To solicitud.
	 *
	 * @param solicitudBean el solicitud bean
	 * @return el solicitud comun
	 */
	private SolicitudComun toSolicitud (SolicitudBean  solicitudBean)
	{
		SolicitudComun solicitud = new SolicitudComun();
	
		solicitud.setConvocatoria(solicitudBean.getConvocatoria());
		solicitud.setCalleDomicilio(solicitudBean.getCalleDomicilio());
		solicitud.setCodigoPostalDomicilio(solicitudBean.getCodigoPostalDomicilio());
		solicitud.setComentarios(solicitudBean.getComentarios());
		solicitud.setConvocatoria(solicitudBean.getConvocatoria());	
		solicitud.setDetalleDiscapacidad(solicitudBean.getDetalleDiscapacidad());
		solicitud.setIdConsentimiento(solicitudBean.getIdConsentimiento());
		solicitud.setEdadVerificada(solicitudBean.getEdadVerificada());		
		solicitud.setEmail(solicitudBean.getEmail());
		solicitud.setEspecialidad(solicitudBean.getEspecialidad());
		solicitud.setEstadoSolicitud(solicitudBean.getEstadoSolicitud());
		solicitud.setFechaNacimiento(solicitudBean.getFechaNacimiento());
		
		if(solicitudBean.getFechaNacimientoVerificada() != null){
			solicitud.setFechaNacimientoVerificada(solicitudBean.getFechaNacimientoVerificada());
		}	

		if(solicitudBean.getFechaSolicitud() != null ){
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			try {
				solicitud.setFechaSolicitud(df.parse(solicitudBean.getFechaSolicitud()));
			} catch (ParseException e) {
				logger.error("No se ha podido parsear la fecha de solicitud"+ solicitudBean.getFechaSolicitud(),e);	
			}
		}

		solicitud.setFechaUltActualizacion(solicitudBean.getFechaUtlActualizacion());
		solicitud.setIdSolicitud(solicitudBean.getId());
		solicitud.setLocalidadNacimiento(solicitudBean.getLocalidadNacimiento());
		solicitud.setMunicipioDomicilio(solicitudBean.getMunicipioDomicilio());
		solicitud.setNacionalidad(solicitudBean.getNacionalidad());
		solicitud.setNif(solicitudBean.getNif());
		solicitud.setNombre(solicitudBean.getNombre());
		solicitud.setNumeroSolicitud(solicitudBean.getNumeroSolicitud());	
		solicitud.setCodigoPostalDomicilio(solicitudBean.getCodigoPostalDomicilio());
		solicitud.setPais(solicitudBean.getPais());
		solicitud.setPorcentajeDiscapacidad(solicitudBean.getPorcentajeDiscapacidad().shortValue());
		solicitud.setPrimerApellido(solicitudBean.getPrimerApellido());
		solicitud.setProvinciaByIdProvinciaDomicilio(solicitudBean.getProvinciaByIdProvinciaDomicilio());
		solicitud.setProvinciaByIdProvinciaExamen(solicitudBean.getProvinciaByIdProvinciaExamen());
		solicitud.setProvinciaByIdProvinciaNacimiento(solicitudBean.getProvinciaByIdProvinciaNacimiento());		
		solicitud.setReservaDiscapacidad(solicitudBean.getReservaDiscapacidad());
		solicitud.setSegundoApellido(solicitudBean.getSegundoApellido());
		solicitud.setSexo(solicitudBean.getSexo());
		solicitud.setTelefono(solicitudBean.getTelefono());
		solicitud.setTipoDiscapacidad(solicitudBean.getTipoDiscapacidad());
		solicitud.setTipoSolicitud(solicitudBean.getTipo());
		solicitud.setTituloOficial(solicitudBean.getTituloOficial());
		
		if(solicitudBean.getOtrosTitulos() != null){
			solicitud.setOtrosTitulos(solicitudBean.getOtrosTitulos());
		}
		
		if(solicitudBean.getTituloVerificado() != null){
			solicitud.setTituloVerificado(solicitudBean.getTituloVerificado());
		}	
		
		Modelo modelo = new Modelo();
		if (!StringUtils.isEmpty(solicitudBean.getIdModelo())) {
			modelo.setIdModelo(Integer.parseInt(solicitudBean.getIdModelo()));
			solicitud.setModelo(modelo);
		}
		

		// Desempleo
		if(solicitudBean.getDesempleoVerificado() != null){
			solicitud.setDesempleoVerificado(solicitudBean.getDesempleoVerificado());
		}
		
		// Familia Numerosa
		if(solicitudBean.getFnumerosaVerificado() != null){
			solicitud.setFnumerosaVerificado(solicitudBean.getFnumerosaVerificado());
		}
		
		// Discapacidad
		if(solicitudBean.getDiscapacidadVerificado() != null){
			solicitud.setDiscapacidadVerificado(solicitudBean.getDiscapacidadVerificado() );
		}
		
		// no hay funcionario habilitado, simplemente funcionario usando gestion
		solicitud.setFunHabilitado(false);
				
		solicitud.setIdConsentimientoAEAT(solicitudBean.getIdConsentimientoAeat());
		solicitud.setMotivoOposicion(solicitudBean.getMotivoOposicion());
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
		
		arrSolicitud = new ArrayList<SolicitudBean>();
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
	 * Guarda una Solicitud Presencial.
	 *
	 * @param solicitudBean SolicitudBean
	 * @return IidSolicitud Integer
	 */
	public Integer guardarSolicitudPresencial(SolicitudBean solicitudBean){

		SolicitudComun solicitud =  toSolicitud(solicitudBean);
		Long idSolicitud = solicitudComunDAO.insert(solicitud);
		Integer IidSolicitud = null;
		
		if(idSolicitud != null)	{
			IidSolicitud = new Integer(idSolicitud.toString());
		}
		
		return IidSolicitud;
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

		if(!StringUtils.isEmpty(registroSolicitudBean.getFechaIntento()))
			registroSolicitud.setFechaIntento(registroSolicitudBean.getFechaIntento());
		
		if(!StringUtils.isEmpty(registroSolicitudBean.getFechaRegistro()) )
			registroSolicitud.setFechaRegistro(registroSolicitudBean.getFechaRegistro());
		
		if(!StringUtils.isEmpty(registroSolicitudBean.getId()) )
			registroSolicitud.setId(registroSolicitudBean.getId());
		
		if(!StringUtils.isEmpty(registroSolicitudBean.getLogServicio()))
			registroSolicitud.setLogServicio(registroSolicitudBean.getLogServicio());
		
		if(!StringUtils.isEmpty(registroSolicitudBean.getNumeroRegistro()))
			registroSolicitud.setNumeroRegistro(registroSolicitudBean.getNumeroRegistro());
		
		if(!StringUtils.isEmpty(registroSolicitudBean.getOficinaRegistro()))
			registroSolicitud.setOficinaRegistro(registroSolicitudBean.getOficinaRegistro());
		
		if(!StringUtils.isEmpty(registroSolicitudBean.getResultado()))
			registroSolicitud.setResultado(registroSolicitudBean.getResultado());
		
		if(!StringUtils.isEmpty(registroSolicitudBean.getSolicitante()))
			registroSolicitud.setSolicitante(registroSolicitudBean.getSolicitante());
		
		if(!StringUtils.isEmpty(registroSolicitudBean.getSolicitud()))
			registroSolicitud.setSolicitudComun(registroSolicitudBean.getSolicitud());
		
		return registroSolicitud;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.SolicitudPresencialManager#existeSolicitudPresencial(es.map.ipsg.bean.SolicitudBean)
	 */
	public boolean existeSolicitudPresencial(SolicitudBean solicitudBean){
		boolean existe = false;
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(solicitudBean.getIdConvocatoria());
		
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		solicitudQuery.setNif(solicitudBean.getNif());
		solicitudQuery.setNumeroSolicitud(solicitudBean.getNumeroSolicitud());
		solicitudQuery.setConvocatoria(convocatoriaQuery);
		
		// Filtrar por estado de solicitud registrada al comprobar si ya existe.
		solicitudQuery.addEstadoSolicitudIn(Constantes.ESTADO_SOLICITUD_REGISTRADO);
		
		SearchResult<SolicitudComun> solicitudes = solicitudComunDAO.search(solicitudQuery);
		if(solicitudes!=null && solicitudes.size()>0){
			existe = true;
		}
		return existe;
		
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
	 * Obtiene el arr solicitud.
	 *
	 * @return arrSolicitud ArrayList<SolicitudBean>
	 */
	public ArrayList<SolicitudBean> getArrSolicitud() {
		return arrSolicitud;
	}

	/**
	 * Establece el arr solicitud.
	 *
	 * @param arrSolicitud ArrayList<SolicitudBean>
	 */
	public void setArrSolicitud(ArrayList<SolicitudBean> arrSolicitud) {
		this.arrSolicitud = arrSolicitud;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
}