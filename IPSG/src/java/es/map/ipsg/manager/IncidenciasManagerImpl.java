package es.map.ipsg.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.CorreoElectronicoDAO;
import es.map.ips.dao.IncidenciaDAO;
import es.map.ips.model.CorreoElectronico;
import es.map.ips.model.Incidencia;
import es.map.ips.model.IncidenciaQuery;
import es.map.ips.model.Usuario;
import es.map.ipsg.bean.CorreoElectronicoBean;
import es.map.ipsg.bean.IncidenciasBean;
import es.map.ipsg.bean.UsuarioBean;


/**
 * El Class IncidenciasManagerImpl.
 */
public class IncidenciasManagerImpl implements IncidenciasManager {
	
	/** el incidencia DAO. */
	IncidenciaDAO incidenciaDAO;
	
	/** el correo electronico DAO. */
	CorreoElectronicoDAO correoElectronicoDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(IncidenciasManagerImpl.class);

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.IncidenciasManager#guardarIncidencias(es.map.ipsg.bean.UsuarioBean, es.map.ipsg.bean.CorreoElectronicoBean)
	 */
	public Long guardarIncidencias(UsuarioBean auxUsuario,
		CorreoElectronicoBean correoElectronicoBean) {
		CorreoElectronico correoElectronico;
		correoElectronico = toCorreoElectronico(correoElectronicoBean);
		Long idCorreo = correoElectronicoDAO.insert(correoElectronico);
		Usuario usuario = new Usuario();
		usuario.setId(auxUsuario.getId());
		Incidencia incidencia = new Incidencia();
		incidencia.setCorreoElectronico(correoElectronico);
		incidencia.setUsuario(usuario);
		Long idIncidencia = incidenciaDAO.insert(incidencia);

		return idIncidencia;
	}

	/**
	 * To correo electronico.
	 *
	 * @param correoElectronicoBean el correo electronico bean
	 * @return el correo electronico
	 */
	private CorreoElectronico toCorreoElectronico(CorreoElectronicoBean correoElectronicoBean) {
		
		CorreoElectronico correoElectronico = new CorreoElectronico();
		correoElectronico.setDe(correoElectronicoBean.getDe());
		correoElectronico.setPara(correoElectronicoBean.getPara());
		correoElectronico.setAsunto(correoElectronicoBean.getAsunto());
		correoElectronico.setMensaje(correoElectronicoBean.getMensaje());
		Date fechaActual = new Date();
		correoElectronico.setFechaEnvio(fechaActual);
		correoElectronico.setEstado(correoElectronicoBean.getEstado());
		
		
		return correoElectronico;
	}

	/**
	 * To usuario.
	 *
	 * @param usuarioBean el usuario bean
	 * @return el usuario
	 */
	public Usuario toUsuario(UsuarioBean usuarioBean){
		Usuario usuario = new Usuario();
		
		usuario.setNif(usuarioBean.getNif());
		usuario.setNombre(usuarioBean.getNombre());
		usuario.setPrimerApellido(usuarioBean.getPrimerApellido());
		usuario.setSegundoApellido(usuarioBean.getSegundoApellido());
		usuario.setEmail(usuarioBean.getEmail());
		usuario.setTelefono(usuarioBean.getTelefono());		
				
		return usuario;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.IncidenciasManager#buscarIncidenciasFiltro(es.map.ips.model.IncidenciaQuery)
	 */
	public ArrayList<IncidenciasBean> buscarIncidenciasFiltro(IncidenciaQuery incidenciaQuery){		
		SearchResult<Incidencia> incidencia = buscarIncidencia(incidenciaQuery);
		int numTotal = incidencia.getNumResults();
		ArrayList<IncidenciasBean> arrIncidencias= new ArrayList<IncidenciasBean>();
		for(int i=0;i<incidencia.getResults().size();i++){
			IncidenciasBean aux;
			aux = toIncidenciaBean(incidencia.getResults().get(i),numTotal);
			if(aux != null){
				arrIncidencias.add(aux);
			}
		}	
		return arrIncidencias;		
	}
	
	/**
	 * Buscar incidencia.
	 *
	 * @param incidenciaQuery el incidencia query
	 * @return el search result
	 */
	private SearchResult<Incidencia> buscarIncidencia(IncidenciaQuery incidenciaQuery) {
		
		ApplicationException.assertNotNull(incidenciaQuery, "incidenciaQuery no puede ser null");
	
	return incidenciaDAO.search(incidenciaQuery);
}
	
/**
 * To incidencia bean.
 *
 * @param incidencias el incidencias
 * @param numTotal el num total
 * @return el incidencias bean
 */
public IncidenciasBean toIncidenciaBean(Incidencia incidencias, int numTotal) {
		
		long id = incidencias.getId();
		String nif="";
		String nombre="";
		String primerApellido="";
		String segundoApellido="";
		if (incidencias.getUsuario()!=null){
			nif= incidencias.getUsuario().getNif();
			nombre=incidencias.getUsuario().getNombre();
			primerApellido=incidencias.getUsuario().getPrimerApellido();
			segundoApellido=incidencias.getUsuario().getSegundoApellido();
		}
		if (incidencias.getCiudadano()!=null){
			nif= incidencias.getCiudadano().getNif();
			nombre= incidencias.getCiudadano().getNombre();
			primerApellido=incidencias.getCiudadano().getPrimerApellido();
			segundoApellido=incidencias.getCiudadano().getSegundoApellido();
			}
		Date fecha=incidencias.getCorreoElectronico().getFechaEnvio();
			
		
		
		IncidenciasBean incidenciaBean= new IncidenciasBean();
		incidenciaBean.setId(id);
		incidenciaBean.setNif(nif);
		incidenciaBean.setNombre(nombre);
		incidenciaBean.setPrimerApellido(primerApellido);
		incidenciaBean.setSegundoApellido(segundoApellido);
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String sFecha=formatoDelTexto.format(fecha);
		incidenciaBean.setFechaEnvio(sFecha);
		incidenciaBean.setNumTotal(numTotal);
	    
	    return incidenciaBean;
	}

/* (non-Javadoc)
 * @see es.map.ipsg.manager.IncidenciasManager#obtenerIncidencia(java.lang.Long)
 */
public IncidenciasBean obtenerIncidencia(Long idIncidencia){
	Incidencia incidencia = incidenciaDAO.get(idIncidencia);
	return toIncidenciasBean(incidencia);
}

/**
 * To incidencias bean.
 *
 * @param incidencias el incidencias
 * @return el incidencias bean
 */
public IncidenciasBean toIncidenciasBean(Incidencia incidencias){
	
	IncidenciasBean incidenciasBean= new IncidenciasBean();
	
	incidenciasBean.setId(incidencias.getId());
	
	//Comprobamos si la incidencia es de un Ciudadano o Usuario
	if  (incidencias.getCiudadano()!=null){
		incidenciasBean.setNombre(incidencias.getCiudadano().getNombre());
		incidenciasBean.setPrimerApellido(incidencias.getCiudadano().getPrimerApellido());
		incidenciasBean.setSegundoApellido(incidencias.getCiudadano().getSegundoApellido());
		incidenciasBean.setEmail(incidencias.getCiudadano().getEmail());
		incidenciasBean.setTelefono(incidencias.getCiudadano().getTelefono());
		incidenciasBean.setNif(incidencias.getCiudadano().getNif());
		
		}
	//En �ste caso es Usuario
	else{
		incidenciasBean.setNombre(incidencias.getUsuario().getNombre());
		incidenciasBean.setPrimerApellido(incidencias.getUsuario().getPrimerApellido());
		incidenciasBean.setSegundoApellido(incidencias.getUsuario().getSegundoApellido());
		incidenciasBean.setEmail(incidencias.getUsuario().getEmail());
		incidenciasBean.setTelefono(incidencias.getUsuario().getTelefono());
		incidenciasBean.setNif(incidencias.getUsuario().getNif());
		
		
	}
	
	SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
	String dFecha=formatoDelTexto.format(incidencias.getCorreoElectronico().getFechaEnvio());
	incidenciasBean.setFechaEnvio(dFecha);
	incidenciasBean.setMensaje(incidencias.getCorreoElectronico().getMensaje());
			
	return incidenciasBean;
}

	/**
	 * Obtiene el incidencia DAO.
	 *
	 * @return el incidencia DAO
	 */
	public IncidenciaDAO getIncidenciaDAO() {
		return incidenciaDAO;
	}

	/**
	 * Establece el incidencia DAO.
	 *
	 * @param incidenciaDAO el nuevo incidencia DAO
	 */
	public void setIncidenciaDAO(IncidenciaDAO incidenciaDAO) {
		this.incidenciaDAO = incidenciaDAO;
	}

	/**
	 * Obtiene el correo electronico DAO.
	 *
	 * @return el correo electronico DAO
	 */
	public CorreoElectronicoDAO getCorreoElectronicoDAO() {
		return correoElectronicoDAO;
	}

	/**
	 * Establece el correo electronico DAO.
	 *
	 * @param correoElectronicoDAO el nuevo correo electronico DAO
	 */
	public void setCorreoElectronicoDAO(CorreoElectronicoDAO correoElectronicoDAO) {
		this.correoElectronicoDAO = correoElectronicoDAO;
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
