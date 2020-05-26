package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.UsuarioAplicacionDAO;
import es.map.ips.dao.UsuarioDAO;
import es.map.ips.model.EntidadFinanciera;
import es.map.ips.model.TipoPago;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioAplicacion;
import es.map.ips.model.UsuarioAplicacionQuery;
import es.map.ipsg.bean.EntidadFinancieraBean;
import es.map.ipsg.bean.UsuarioAplicacionBean;
import es.map.ipsg.bean.UsuarioBean;

/**
 * El Class UsuarioAplicacionManagerImpl.
 */
public class UsuarioAplicacionManagerImpl implements UsuarioAplicacionManager{
	
	/** el usuario aplicacion DAO. */
	private UsuarioAplicacionDAO usuarioAplicacionDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(UsuarioAplicacionManagerImpl.class);
	
	/** La constante STRING_ARROW. */
	private static final String STRING_ARROW = " --> ";
	
	/**
	 * Establece el usuario aplicacion DAO.
	 *
	 * @param usuarioAplicacionDAO el nuevo usuario aplicacion DAO
	 */
	public void setUsuarioAplicacionDAO(UsuarioAplicacionDAO usuarioAplicacionDAO) {
		this.usuarioAplicacionDAO = usuarioAplicacionDAO;
	}
	
	/**
	 * Obtiene el usuario DAO.
	 *
	 * @return el usuario DAO
	 */
	public UsuarioAplicacionDAO getUsuarioDAO() {
		return usuarioAplicacionDAO;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.UsuarioAplicacionManager#buscarUsuariosAplicacion(es.map.ips.model.UsuarioAplicacionQuery)
	 */
	@Override
	public List<UsuarioAplicacionBean> buscarUsuariosAplicacion(UsuarioAplicacionQuery usuarioAplicacionQuery) {
		List<UsuarioAplicacionBean> list = new ArrayList<UsuarioAplicacionBean>();
		SearchResult<UsuarioAplicacion> usuariosAplicacion = null;
		try{
			usuariosAplicacion = buscarUsuarioAplicacion(usuarioAplicacionQuery);
		}catch(Exception e){
			logger.error("No se ha podido buscar usuarios",e );
		}
		int numTotal = 0;

		if(usuariosAplicacion != null){
			try{
// TODO Corrección incidencia buscador usuarios				

				if (usuariosAplicacion.getNumResults() != null){
					numTotal = usuariosAplicacion.getNumResults();
				}else{
					numTotal=0;
				}
			}catch (Exception e) {
				logger.error("No se ha podido buscar usuarios -numero de usuarios"+ numTotal,e );
				numTotal = 0;
			}
			
		}

		for(UsuarioAplicacion u:usuariosAplicacion.getResults()){
			list.add(toUsuarioAplicacionBean(u,numTotal));
		}
		
		return list;
	}

	/**
	 * To usuario aplicacion bean.
	 *
	 * @param usuarioAplicacion el usuario aplicacion
	 * @param numTotal el num total
	 * @return el usuario aplicacion bean
	 */
	private UsuarioAplicacionBean toUsuarioAplicacionBean(UsuarioAplicacion usuarioAplicacion, int numTotal) {
		int id = usuarioAplicacion.getIdUsuario();
		String usuario = usuarioAplicacion.getUsuarioAplicacion();
		String nombre = usuarioAplicacion.getNombreAplicacion();
		String descripcion = usuarioAplicacion.getDescripcionAplicacion();
		Character estado = usuarioAplicacion.getEstado();
		
		UsuarioAplicacionBean auxUsuarioAplicacion = new UsuarioAplicacionBean();
		auxUsuarioAplicacion.setId(id);
		auxUsuarioAplicacion.setUsuario(usuario);
		auxUsuarioAplicacion.setNombre(nombre);
		auxUsuarioAplicacion.setDescripcion(descripcion);
		auxUsuarioAplicacion.setEstado(estado);
		
		auxUsuarioAplicacion.setNumTotal(numTotal);
		
		return auxUsuarioAplicacion;
	}

	/**
	 * Buscar usuario aplicacion.
	 *
	 * @param usuarioAplicacionQuery el usuario aplicacion query
	 * @return el search result
	 */
	private SearchResult<UsuarioAplicacion> buscarUsuarioAplicacion(UsuarioAplicacionQuery usuarioAplicacionQuery) {
			ApplicationException.assertNotNull(usuarioAplicacionQuery, "usuarioAplicacionQuery no puede ser null");
		
		return usuarioAplicacionDAO.search(usuarioAplicacionQuery);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.UsuarioAplicacionManager#modificarUsuario(es.map.ipsg.bean.UsuarioAplicacionBean)
	 */
	@Override
	public void modificarUsuario(UsuarioAplicacionBean usuarioAplicacionBean) {
		UsuarioAplicacion usuarioAplicacion = toUsuarioAplicacion(usuarioAplicacionBean);
		usuarioAplicacionDAO.update(usuarioAplicacion);
		
	}

	/**
	 * To usuario aplicacion.
	 *
	 * @param usuarioAplicacionBean el usuario aplicacion bean
	 * @return el usuario aplicacion
	 */
	private UsuarioAplicacion toUsuarioAplicacion(UsuarioAplicacionBean usuarioAplicacionBean) {
		UsuarioAplicacion usuarioAplicacion = new UsuarioAplicacion();
		
		usuarioAplicacion.setIdUsuario(usuarioAplicacionBean.getId());
		usuarioAplicacion.setUsuarioAplicacion(usuarioAplicacionBean.getUsuario());
		usuarioAplicacion.setNombreAplicacion(usuarioAplicacionBean.getNombre());
		usuarioAplicacion.setDescripcionAplicacion(usuarioAplicacionBean.getDescripcion());
		usuarioAplicacion.setEstado(usuarioAplicacionBean.getEstado());
		usuarioAplicacion.setUltimaActAplicacion(usuarioAplicacionBean.getFecha());
				
		return usuarioAplicacion;
	}
		
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.UsuarioAplicacionManager#buscarUsuariosAplicacionBean(es.map.ips.model.UsuarioAplicacionQuery)
	 */
	@Override
	public UsuarioAplicacionBean buscarUsuariosAplicacionBean(UsuarioAplicacionQuery usuarioAplicacionQuery) {
		UsuarioAplicacion usuarioAplicacion = usuarioAplicacionDAO.searchUnique(usuarioAplicacionQuery);
		return (usuarioAplicacion==null ? null:toUsuarioAplicacionBean(usuarioAplicacion));
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.UsuarioAplicacionManager#toUsuarioAplicacionBean(es.map.ips.model.UsuarioAplicacion)
	 */
	public UsuarioAplicacionBean toUsuarioAplicacionBean(UsuarioAplicacion usuarioAplicacion) {
		UsuarioAplicacionBean usuarioAplicacionBean = new UsuarioAplicacionBean();
		
		usuarioAplicacionBean.setId(usuarioAplicacion.getIdUsuario());
		usuarioAplicacionBean.setUsuario(usuarioAplicacion.getUsuarioAplicacion());
		usuarioAplicacionBean.setNombre(usuarioAplicacion.getNombreAplicacion());
		usuarioAplicacionBean.setDescripcion(usuarioAplicacion.getDescripcionAplicacion());
		usuarioAplicacionBean.setEstado(usuarioAplicacion.getEstado());
		
		return usuarioAplicacionBean;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.UsuarioAplicacionManager#obtenerUsuarioAplicacion(java.lang.Integer)
	 */
	@Override
	public UsuarioAplicacionBean obtenerUsuarioAplicacion(Integer idUsuarioAplicacion) {
		UsuarioAplicacion usuarioAplicacion = usuarioAplicacionDAO.get(idUsuarioAplicacion);
		return toUsuarioAplicacionBean(usuarioAplicacion);
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.UsuarioAplicacionManager#buscarAplicacion(es.map.ips.model.UsuarioAplicacionQuery)
	 */
	@Override
	public UsuarioAplicacionBean buscarAplicacion(UsuarioAplicacionQuery usuarioAplicacionQuery) {
		UsuarioAplicacion usuarioAplicacion = usuarioAplicacionDAO.searchUnique(usuarioAplicacionQuery);
		return (usuarioAplicacion==null ? null:toUsuarioAplicacionBean(usuarioAplicacion));
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.UsuarioAplicacionManager#toAplicacion(es.map.ipsg.bean.UsuarioAplicacionBean)
	 */
	@Override
	public UsuarioAplicacion toAplicacion(UsuarioAplicacionBean usuarioAplicacionBean) {
		UsuarioAplicacion usuarioAplicacion = new UsuarioAplicacion();
		
		if(usuarioAplicacionBean.getId()!=null && usuarioAplicacionBean.getId()>0)
			usuarioAplicacion = usuarioAplicacionDAO.get(usuarioAplicacionBean.getId());
		else
			usuarioAplicacion = new UsuarioAplicacion();	
		
		usuarioAplicacion.setIdUsuario(usuarioAplicacionBean.getId());
		usuarioAplicacion.setUsuarioAplicacion(usuarioAplicacionBean.getUsuario());
		usuarioAplicacion.setNombreAplicacion(usuarioAplicacionBean.getNombre());
		usuarioAplicacion.setDescripcionAplicacion(usuarioAplicacionBean.getDescripcion());
		usuarioAplicacion.setEstado(usuarioAplicacionBean.getEstado());
		usuarioAplicacion.setUltimaActAplicacion(usuarioAplicacionBean.getFecha());
		
		return usuarioAplicacion;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.UsuarioAplicacionManager#comapararUsuariosAplicacion(es.map.ipsg.bean.UsuarioAplicacionBean, es.map.ipsg.bean.UsuarioAplicacionBean)
	 */
	@Override
	public String comapararUsuariosAplicacion(UsuarioAplicacionBean usuarioAplicacionBeanAnteriror,
			UsuarioAplicacionBean usuarioAplicacionBean) {
		StringBuffer cambios = new StringBuffer("");
		boolean cambiado = false;
		
		String usuarioAnterior = "";
		String nombreAnterior = "";
		String descripcionAnterior = "";
		String estadoAnterior = "";
		
		usuarioAnterior = usuarioAplicacionBeanAnteriror.getUsuario();
		nombreAnterior = usuarioAplicacionBeanAnteriror.getNombre();
		descripcionAnterior = usuarioAplicacionBeanAnteriror.getDescripcion();
		estadoAnterior = usuarioAplicacionBeanAnteriror.getEstado().toString();
		
		
		String usuarioActual = "";
		String nombreActual = "";
		String descripcionActual = "";
		String estadoActual = "";
		
		usuarioActual = usuarioAplicacionBean.getUsuario();
		nombreActual = usuarioAplicacionBean.getNombre();
		descripcionActual = usuarioAplicacionBean.getDescripcion();
		estadoActual = usuarioAplicacionBean.getEstado().toString();
		
		if (usuarioAnterior.compareTo(usuarioActual)!=0){
			if (cambiado){
				cambios.append(", ");
			}
			cambios.append("USUARIO: ");
			cambios.append(usuarioAnterior);
			cambios.append(STRING_ARROW);
			cambios.append(usuarioActual);
			cambiado = true;
		}
		
		if (nombreAnterior.compareTo(nombreActual)!=0){
			if (cambiado){
				cambios.append(", ");
			}
			cambios.append("NOMBRE: ");
			cambios.append(nombreAnterior);
			cambios.append(STRING_ARROW);
			cambios.append(nombreActual);
			cambiado = true;
		}
		if (descripcionAnterior.compareTo(descripcionActual)!=0){
			if (cambiado){
				cambios.append(", ");
			}
			cambios.append("DESCRIPCION: ");
			cambios.append(descripcionAnterior);
			cambios.append(STRING_ARROW);
			cambios.append(descripcionActual);
			cambiado = true;
		}
		if (estadoAnterior.compareTo(estadoActual)!=0){
			if (cambiado){
				cambios.append(", ");
			}
			cambios.append("ESTADO: ");
			cambios.append(estadoAnterior);
			cambios.append(STRING_ARROW);
			cambios.append(estadoActual);
			cambiado = true;
		}
		
		
		if (cambiado){
			if(cambios.length()>500)
				cambios.substring(0, 500);
			return cambios.toString();
		}else{
			return "";
		}
		
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.UsuarioAplicacionManager#guardarUsuarioAplicacion(es.map.ipsg.bean.UsuarioAplicacionBean)
	 */
	@Override
	public int guardarUsuarioAplicacion(UsuarioAplicacionBean usuarioAplicacionBean) throws BusinessException{
		UsuarioAplicacion usuarioAplicacion = toAplicacion(usuarioAplicacionBean);
		Integer idUsuarioAplicacion = usuarioAplicacionDAO.insert(usuarioAplicacion);
		return idUsuarioAplicacion;
	}
}
