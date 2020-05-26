package es.map.ipsg.manager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.PaisDAO;
import es.map.ips.model.PagoSolicitud;
import es.map.ips.model.Pais;
import es.map.ips.model.PaisQuery;
import es.map.ips.model.RegistroSolicitud;
import es.map.ips.model.SolicitudComun;
import es.map.ipsg.bean.PagoSolicitudBean;
import es.map.ipsg.bean.PaisBean;
import es.map.ipsg.bean.RegistroSolicitudBean;
import es.map.ipsg.bean.SolicitudBean;

/**
 * Implementa el Pais Manager.
 *
 * @author amartinl
 */
public class PaisManagerImpl implements PaisManager {

	/** el pais DAO. */
	//Variables
	private PaisDAO paisDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(PaisManagerImpl.class);
	
/**
 * Busca los paises que se mostraran en Combos.
 *
 * @param paisQuery PaisQuery
 * @return arrPais ArrayList<PaisBean>
 */
	public ArrayList<PaisBean> buscarPaisCombo(PaisQuery paisQuery){
		SearchResult<Pais> pais = buscarPais(paisQuery);
		ArrayList<PaisBean> arrPaisBean= new ArrayList<PaisBean>();
		for(int i=0;i<pais.getResults().size();i++){
			PaisBean aux;
			aux = toPaisBean(pais.getResults().get(i));
			if(aux != null){
				arrPaisBean.add(aux);
			}
		}	
		return arrPaisBean;		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.PaisManager#buscarPaisCombo2(es.map.ips.model.PaisQuery)
	 */
	public ArrayList<PaisBean> buscarPaisCombo2(PaisQuery paisQuery){
		SearchResult<Pais> pais = buscarPais(paisQuery);
		ArrayList<PaisBean> arrPaisBean= new ArrayList<PaisBean>();
		for(int i=0;i<pais.getResults().size();i++){
			Pais p = pais.getResults().get(i);
			PaisBean aux = new PaisBean();
			aux.setId(p.getId());
			aux.setDescripcion(p.getDescripcion());
			aux.setCodigo(p.getCodigo());
			if(aux != null){
				arrPaisBean.add(aux);
			}
		}	
		return arrPaisBean;		
	}
	
	/**
	 * Busca un sólo país.
	 *
	 * @param paisQuery PaisQuery
	 * @return paisBean  PaisBean
	 */
	public PaisBean buscarPaisUnique(PaisQuery paisQuery) {
		Pais paisAux;
		paisAux = paisDAO.searchUnique(paisQuery);
		PaisBean paisBean = new PaisBean();
		try{
			paisBean = toPaisBean(paisAux,0);
		}catch(Exception e){
			logger.error("Error PaisManagerImpl - buscarPaisUnique.",e);
		}
		return paisBean;
	}
	
	
	/**
	 * To pais bean.
	 *
	 * @param u el u
	 * @param i el i
	 * @return el pais bean
	 */
	private PaisBean toPaisBean(Pais u, int i) {
		PaisBean aux = new PaisBean();
		aux.setId(u.getId());
		aux.setDescripcion(u.getDescripcion());
		aux.setCodigo(u.getCodigo());
		
		return aux;
	}

/**
 * Inserta en la tabla un País.
 *
 * @param paisBean PaisBean
 * @return idPais Integer
 */
	public Integer guardarPais(PaisBean paisBean){
		Pais pais = toPais(paisBean);
		Integer idPais = paisDAO.insert(pais);
		return idPais;
	}
	
	/**
	 * Modifica los datos de un país.
	 *
	 * @param paisBean PaisBean
	 */
	public void modificarPais(PaisBean paisBean){
		Pais pais = toPais(paisBean);
		paisDAO.update(pais);
	}
	
	/**
	 * To pais.
	 *
	 * @param paisBean el pais bean
	 * @return el pais
	 */
	private Pais toPais(PaisBean paisBean) {
		
		Pais auxPais = new Pais();
		auxPais.setId(paisBean.getId());
		auxPais.setDescripcion(paisBean.getDescripcion());
		auxPais.setCodigo(paisBean.getCodigo());
		
		return auxPais;
	}
	
	/**
	 * To pais bean.
	 *
	 * @param pais el pais
	 * @return el pais bean
	 */
	private PaisBean toPaisBean(Pais pais) {
		int id = pais.getId();
		String descripcion = pais.getDescripcion();
		String codigo = pais.getCodigo();
		
		
		PaisBean auxPaisBean = new PaisBean();
		auxPaisBean.setId(id);
		auxPaisBean.setDescripcion(descripcion);
		auxPaisBean.setCodigo(codigo);
		
		
		if(pais.getSolicituds() != null)
		{
			ArrayList<SolicitudComun>  arrSolicitud = new ArrayList<SolicitudComun>(pais.getSolicituds());
			Iterator<SolicitudComun> it = arrSolicitud.iterator();
			ArrayList<SolicitudBean> arrSolicitudBean = new ArrayList<SolicitudBean>();
			while (it.hasNext())
			{
				SolicitudComun solicitud = (SolicitudComun) it.next();
				SolicitudBean solicitudBean = this.toSolicitudComboBean(solicitud);
				arrSolicitudBean.add(solicitudBean);
			}
			auxPaisBean.setSolicitudes(arrSolicitudBean);
		}
		
		
		return auxPaisBean;
	}

	/**
	 * Buscar pais.
	 *
	 * @param paisQuery el pais query
	 * @return el search result
	 */
	private SearchResult<Pais> buscarPais(PaisQuery paisQuery) {
			ApplicationException.assertNotNull(paisQuery, "paisQuery no puede ser null");
		
		return paisDAO.search(paisQuery);
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
		solicitud.setFechaNacimientoVerificada(solicitudBean.getFechaNacimientoVerificada());
		
		
			
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			solicitud.setFechaSolicitud(df.parse(solicitudBean.getFechaSolicitud()));
		} catch (ParseException e) {
			logger.error(" Error PaisManagerImpl- No se ha podido parsear la fecha de la solicitud,e");
			
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
		solicitud.setTituloVerificado(solicitudBean.getTituloVerificado());
		
		
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
		auxSolicitud.setTituloVerificado(solicitud.getTituloVerificado());
	
		if(solicitud.getEdadVerificada() != null )
		{
			auxSolicitud.setEdadVerificada(solicitud.getEdadVerificada());
		}
		auxSolicitud.setFechaNacimientoVerificada(solicitud.getFechaNacimientoVerificada());
	
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
	 * Obtiene el pais DAO.
	 *
	 * @return  paisDAO PaisDAO
	 */
	public PaisDAO getPaisDAO() {
		return paisDAO;
	}

	/**
	 * Establece el pais DAO.
	 *
	 * @param paisDAO PaisDAO
	 */
	public void setPaisDAO(PaisDAO paisDAO) {
		this.paisDAO = paisDAO;
	}

}