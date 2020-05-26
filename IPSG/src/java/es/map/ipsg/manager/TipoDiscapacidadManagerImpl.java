package es.map.ipsg.manager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.TipoDiscapacidadDAO;
import es.map.ips.model.PagoSolicitud;
import es.map.ips.model.RegistroSolicitud;
import es.map.ips.model.SolicitudComun;
import es.map.ips.model.TipoDiscapacidad;
import es.map.ips.model.TipoDiscapacidadQuery;
import es.map.ipsg.bean.PagoSolicitudBean;
import es.map.ipsg.bean.RegistroSolicitudBean;
import es.map.ipsg.bean.SolicitudBean;
import es.map.ipsg.bean.TipoDiscapacidadBean;
import es.map.ipsg.util.UtilesIPSG;

/**
 * Implementa el Tipo Discapacidad Manager.
 *
 * @author amartinl
 */
public class TipoDiscapacidadManagerImpl implements TipoDiscapacidadManager {

	/** el tipo discapacidad DAO. */
	//Variables
	private TipoDiscapacidadDAO tipoDiscapacidadDAO;
	
	/** el arr tipo discapacidad bean. */
	private ArrayList<TipoDiscapacidadBean> arrTipoDiscapacidadBean;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(TipoDiscapacidadManagerImpl.class);
	
/**
 * Busca los tipoDiscapacidades que se mostraran en Combos.
 *
 * @param tipoDiscapacidadQuery TipoDiscapacidadQuery
 * @return arrTipoDiscapacidad ArrayList<TipoDiscapacidadBean>
 */
	public ArrayList<TipoDiscapacidadBean> buscarTipoDiscapacidadCombo(TipoDiscapacidadQuery tipoDiscapacidadQuery){
		SearchResult<TipoDiscapacidad> tipoDiscapacidad = buscarTipoDiscapacidad(tipoDiscapacidadQuery);
		arrTipoDiscapacidadBean= new ArrayList<TipoDiscapacidadBean>();
		for(int i=0;i<tipoDiscapacidad.getResults().size();i++){
			TipoDiscapacidadBean aux;
			aux = toTipoDiscapacidadBean(tipoDiscapacidad.getResults().get(i));
			if(aux != null){
				arrTipoDiscapacidadBean.add(aux);
			}
		}	
		return arrTipoDiscapacidadBean;		
	}
	
	/**
	 * Busca un sólo país.
	 *
	 * @param tipoDiscapacidadQuery TipoDiscapacidadQuery
	 * @return tipoDiscapacidadBean  TipoDiscapacidadBean
	 */
	public TipoDiscapacidadBean buscarTipoDiscapacidadUnique(TipoDiscapacidadQuery tipoDiscapacidadQuery) {
		TipoDiscapacidad tipoDiscapacidadAux;
		tipoDiscapacidadAux = tipoDiscapacidadDAO.searchUnique(tipoDiscapacidadQuery);
		TipoDiscapacidadBean tipoDiscapacidadBean = new TipoDiscapacidadBean();
		try{
			tipoDiscapacidadBean = toTipoDiscapacidadBean(tipoDiscapacidadAux,0);
		}catch(Exception e){
			logger.error("No se ha podido buscar tipo de discapacidad unico",e);	
		}
		return tipoDiscapacidadBean;
	}
	
	
	/**
	 * To tipo discapacidad bean.
	 *
	 * @param u el u
	 * @param i el i
	 * @return el tipo discapacidad bean
	 */
	private TipoDiscapacidadBean toTipoDiscapacidadBean(TipoDiscapacidad u, int i) {
		TipoDiscapacidadBean aux = new TipoDiscapacidadBean();
		aux.setId(u.getId());
		aux.setDescripcion(u.getDescripcion());
		aux.setCodigo(u.getCodigo().toString());

		return aux;
	}

/**
 * Inserta en la tabla un País.
 *
 * @param tipoDiscapacidadBean TipoDiscapacidadBean
 * @return idTipoDiscapacidad Integer
 */
	public Integer guardarTipoDiscapacidad(TipoDiscapacidadBean tipoDiscapacidadBean){
		TipoDiscapacidad tipoDiscapacidad = toTipoDiscapacidad(tipoDiscapacidadBean);
		Integer idTipoDiscapacidad = tipoDiscapacidadDAO.insert(tipoDiscapacidad);
		return idTipoDiscapacidad;
	}
	
	/**
	 * Modifica los datos de un país.
	 *
	 * @param tipoDiscapacidadBean TipoDiscapacidadBean
	 */
	public void modificarTipoDiscapacidad(TipoDiscapacidadBean tipoDiscapacidadBean){
		TipoDiscapacidad tipoDiscapacidad = toTipoDiscapacidad(tipoDiscapacidadBean);
		tipoDiscapacidadDAO.update(tipoDiscapacidad);
	}
	
	/**
	 * To tipo discapacidad.
	 *
	 * @param tipoDiscapacidadBean el tipo discapacidad bean
	 * @return el tipo discapacidad
	 */
	private TipoDiscapacidad toTipoDiscapacidad(TipoDiscapacidadBean tipoDiscapacidadBean) {
		
		UtilesIPSG  utilesIPSG= new UtilesIPSG();
		TipoDiscapacidad auxTipoDiscapacidad = new TipoDiscapacidad();
		auxTipoDiscapacidad.setId(tipoDiscapacidadBean.getId());
		auxTipoDiscapacidad.setDescripcion(tipoDiscapacidadBean.getDescripcion());
		auxTipoDiscapacidad.setCodigo(utilesIPSG.stringToCharPos(tipoDiscapacidadBean.getCodigo(),0));
		
		if(tipoDiscapacidadBean.getSolicitudes() != null)
		{
			Set<SolicitudBean> setSolicitudBean = new HashSet<SolicitudBean> (tipoDiscapacidadBean.getSolicitudes());
			Iterator<SolicitudBean> it = setSolicitudBean.iterator();
			Set<SolicitudComun> setPagoSolicitud = new HashSet<SolicitudComun>();
			while (it.hasNext())
			{
				SolicitudBean solicitudBean = (SolicitudBean) it.next();
				SolicitudComun solicitud = this.toSolicitud(solicitudBean);
				setPagoSolicitud.add(solicitud);
			}
			auxTipoDiscapacidad.setSolicituds(setPagoSolicitud);
		}
		
		return auxTipoDiscapacidad;
	}
	
	/**
	 * To tipo discapacidad bean.
	 *
	 * @param tipoDiscapacidad el tipo discapacidad
	 * @return el tipo discapacidad bean
	 */
	private TipoDiscapacidadBean toTipoDiscapacidadBean(TipoDiscapacidad tipoDiscapacidad) {
		int id = tipoDiscapacidad.getId();
		String descripcion = tipoDiscapacidad.getDescripcion();
		String codigo = tipoDiscapacidad.getCodigo().toString();
		
		
		TipoDiscapacidadBean auxTipoDiscapacidadBean = new TipoDiscapacidadBean();
		auxTipoDiscapacidadBean.setId(id);
		auxTipoDiscapacidadBean.setDescripcion(descripcion);
		auxTipoDiscapacidadBean.setCodigo(codigo);
		
		
		if(tipoDiscapacidad.getSolicituds() != null)
		{
			ArrayList<SolicitudComun>  arrSolicitud = new ArrayList<SolicitudComun>(tipoDiscapacidad.getSolicituds());
			Iterator<SolicitudComun> it = arrSolicitud.iterator();
			ArrayList<SolicitudBean> arrSolicitudBean = new ArrayList<SolicitudBean>();
			while (it.hasNext())
			{
				SolicitudComun solicitud = (SolicitudComun) it.next();
				SolicitudBean solicitudBean = this.toSolicitudComboBean(solicitud);
				arrSolicitudBean.add(solicitudBean);
			}
			auxTipoDiscapacidadBean.setSolicitudes(arrSolicitudBean);
		}
		
		
		return auxTipoDiscapacidadBean;
	}

	/**
	 * Buscar tipo discapacidad.
	 *
	 * @param tipoDiscapacidadQuery el tipo discapacidad query
	 * @return el search result
	 */
	private SearchResult<TipoDiscapacidad> buscarTipoDiscapacidad(TipoDiscapacidadQuery tipoDiscapacidadQuery) {
			ApplicationException.assertNotNull(tipoDiscapacidadQuery, "tipoDiscapacidadQuery no puede ser null");
		
		return tipoDiscapacidadDAO.search(tipoDiscapacidadQuery);
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
		
		// TODO SOLICITUD_PROPIOS
		
		
		solicitud.setDetalleDiscapacidad(solicitudBean.getDetalleDiscapacidad());
		solicitud.setEdadVerificada(solicitudBean.getEdadVerificada());
		
		solicitud.setEmail(solicitudBean.getEmail());
		solicitud.setEspecialidad(solicitudBean.getEspecialidad());
		solicitud.setEstadoSolicitud(solicitudBean.getEstadoSolicitud());
		solicitud.setFechaNacimiento(solicitudBean.getFechaNacimiento());
		if(solicitudBean.getFechaNacimientoVerificada() != null)
		{
			solicitud.setFechaNacimientoVerificada(solicitudBean.getFechaNacimientoVerificada());
		}	
	
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			solicitud.setFechaSolicitud(df.parse(solicitudBean.getFechaSolicitud()));
		} catch (ParseException e) {
			logger.error("No se ha podido parsear la fecha de la solicitud"+solicitudBean.getFechaSolicitud(),e );
		}
		
		solicitud.setFechaUltActualizacion(solicitudBean.getFechaUtlActualizacion());
		solicitud.setIdSolicitud(solicitudBean.getId());
		solicitud.setLocalidadNacimiento(solicitudBean.getLocalidadNacimiento());
		
		// TODO SOLICITUD_PROPIOS
		
		solicitud.setMunicipioDomicilio(solicitudBean.getMunicipioDomicilio());
		solicitud.setNacionalidad(solicitudBean.getNacionalidad());
		solicitud.setNif(solicitudBean.getNif());
		solicitud.setNombre(solicitudBean.getNombre());
		solicitud.setNumeroSolicitud(solicitudBean.getNumeroSolicitud());
		
		// TODO SOLICITUD_PROPIOS
		
		solicitud.setTipoDiscapacidad(solicitudBean.getTipoDiscapacidad());
		solicitud.setPorcentajeDiscapacidad(solicitudBean.getPorcentajeDiscapacidad().shortValue());
		solicitud.setPrimerApellido(solicitudBean.getPrimerApellido());
		solicitud.setProvinciaByIdProvinciaDomicilio(solicitudBean.getProvinciaByIdProvinciaDomicilio());
		solicitud.setProvinciaByIdProvinciaExamen(solicitudBean.getProvinciaByIdProvinciaExamen());
		solicitud.setProvinciaByIdProvinciaNacimiento(solicitudBean.getProvinciaByIdProvinciaNacimiento());
		
		// TODO SOLICITUD_PROPIOS
	
	
		solicitud.setReservaDiscapacidad(solicitudBean.getReservaDiscapacidad());
		solicitud.setSegundoApellido(solicitudBean.getSegundoApellido());
		solicitud.setSexo(solicitudBean.getSexo());
		solicitud.setTelefono(solicitudBean.getTelefono());
		solicitud.setTipoDiscapacidad(solicitudBean.getTipoDiscapacidad());
		solicitud.setTipoSolicitud(solicitudBean.getTipo());
		solicitud.setTituloOficial(solicitudBean.getTituloOficial());
		if(solicitudBean.getTituloVerificado() != null)
		{
			solicitud.setTituloVerificado(solicitudBean.getTituloVerificado());
		}	
	
		
		
		return solicitud;
	}
	
	
	
/**
 * To solicitud combo bean.
 *
 * @param solicitud el solicitud
 * @return el solicitud bean
 */
private SolicitudBean toSolicitudComboBean(SolicitudComun solicitud) {
		
		SolicitudBean auxSolicitud = new SolicitudBean();
		auxSolicitud.setNumeroSolicitud(solicitud.getNumeroSolicitud());
		auxSolicitud.setNif(solicitud.getNif());
		auxSolicitud.setNombre(solicitud.getNombre());
		auxSolicitud.setSegundoApellido(solicitud.getSegundoApellido());
		auxSolicitud.setPrimerApellido(solicitud.getPrimerApellido());
		if(solicitud.getTituloVerificado() != null)
		{
			auxSolicitud.setTituloVerificado(solicitud.getTituloVerificado());
		}	
	
		if(solicitud.getEdadVerificada() != null)
		{
			auxSolicitud.setEdadVerificada(solicitud.getEdadVerificada());
		}
		if(solicitud.getFechaNacimientoVerificada() != null)
		{
			auxSolicitud.setFechaNacimientoVerificada(solicitud.getFechaNacimientoVerificada());
		}	
	
		if(solicitud.getEstadoSolicitud() != null)
		{	
			auxSolicitud.setEstadoSolicitud(solicitud.getEstadoSolicitud());
		}
		auxSolicitud.setId(solicitud.getIdSolicitud());
		if(solicitud.getFechaSolicitud()!=null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			auxSolicitud.setFechaSolicitud(sdf.format(solicitud.getFechaSolicitud()));
		}
		auxSolicitud.setTipo(solicitud.getTipoSolicitud());
		auxSolicitud.setComentarios(solicitud.getComentarios());
		if(solicitud.getPais()!= null)
		{
			auxSolicitud.setPais(solicitud.getPais());
			if(solicitud.getPais().getDescripcion() != null)
			{
				auxSolicitud.setNacionPaisDomicilio(solicitud.getPais().getDescripcion());
			}
		}
		
		
		return auxSolicitud;
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
	 * Obtiene el logger.
	 *
	 * @return logger Logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Obtiene el tipo discapacidad DAO.
	 *
	 * @return  tipoDiscapacidadDAO TipoDiscapacidadDAO
	 */
	public TipoDiscapacidadDAO getTipoDiscapacidadDAO() {
		return tipoDiscapacidadDAO;
	}

	/**
	 * Establece el tipo discapacidad DAO.
	 *
	 * @param tipoDiscapacidadDAO TipoDiscapacidadDAO
	 */
	public void setTipoDiscapacidadDAO(TipoDiscapacidadDAO tipoDiscapacidadDAO) {
		this.tipoDiscapacidadDAO = tipoDiscapacidadDAO;
	}

	/**
	 * Obtiene el arr tipo discapacidad.
	 *
	 * @return arrTipoDiscapacidad ArrayList<TipoDiscapacidadBean>
	 */
	public ArrayList<TipoDiscapacidadBean> getArrTipoDiscapacidad() {
		return arrTipoDiscapacidadBean;
	}

	/**
	 * Establece el arr tipo discapacidad.
	 *
	 * @param arrTipoDiscapacidad ArrayList<TipoDiscapacidadBean>
	 */
	public void setArrTipoDiscapacidad(ArrayList<TipoDiscapacidadBean> arrTipoDiscapacidad) {
		this.arrTipoDiscapacidadBean = arrTipoDiscapacidad;
	}


	

}