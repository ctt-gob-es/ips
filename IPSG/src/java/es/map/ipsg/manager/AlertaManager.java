package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.AlertaQuery;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ipsg.bean.AlertaBean;
import es.map.ipsg.bean.UsuarioBean;




/**
 * El Interface AlertaManager.
 *
 * @author amartinl
 */
public interface AlertaManager {
	
	/**
	 * Buscar alerta all.
	 *
	 * @param alertaQuery el alerta query
	 * @return el array list
	 */
	public ArrayList<AlertaBean> buscarAlertaAll(AlertaQuery alertaQuery);
	
	/**
	 * Buscar alerta combo.
	 *
	 * @param alertaQuery el alerta query
	 * @return el array list
	 */
	public ArrayList<AlertaBean> buscarAlertaCombo(AlertaQuery alertaQuery);
	
	/**
	 * Guardar alerta.
	 *
	 * @param alertaBean el alerta bean
	 * @return el int
	 */
	int guardarAlerta(AlertaBean alertaBean);
	
	/**
	 * Buscar alerta.
	 *
	 * @param alertaQuery el alerta query
	 * @return el alerta bean
	 */
	AlertaBean buscarAlerta(AlertaQuery alertaQuery);
	
	/**
	 * Obtener alerta.
	 *
	 * @param idAlerta el id alerta
	 * @return el alerta bean
	 */
	public AlertaBean obtenerAlerta(Integer idAlerta);
	
	/**
	 * Modificar alerta.
	 *
	 * @param alertaBean el alerta bean
	 */
	public void modificarAlerta(AlertaBean alertaBean);
	
	/**
	 * Actualizar alerta.
	 *
	 * @param alertaBean el alerta bean
	 */
	void actualizarAlerta(AlertaBean alertaBean);
	
	/**
	 * Comprobar alertas.
	 *
	 * @param usuarioBean el usuario bean
	 * @param mensajes el mensajes
	 */
	public void comprobarAlertas(UsuarioBean usuarioBean,SpringMessages mensajes);
	
	/**
	 * Ninguna inscripcion.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @param alertaBean el alerta bean
	 * @param mensajes el mensajes
	 * @param listadoMensajesEmail el listado mensajes email
	 */
	public void ningunaInscripcion(ConvocatoriaQuery convocatoriaQuery,AlertaBean alertaBean,SpringMessages mensajes,ArrayList<String> listadoMensajesEmail);
	
	/**
	 * Ninguna inscripcion ayer.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @param alertaBean el alerta bean
	 * @param mensajes el mensajes
	 * @param listadoMensajesEmail el listado mensajes email
	 */
	public void ningunaInscripcionAyer(ConvocatoriaQuery convocatoriaQuery,AlertaBean alertaBean,SpringMessages mensajes,ArrayList<String> listadoMensajesEmail);
	
	/**
	 * Inscripcion pagada no registrada.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @param alertaBean el alerta bean
	 * @param mensajes el mensajes
	 * @param listadoMensajesEmail el listado mensajes email
	 */
	public void inscripcionPagadaNoRegistrada(ConvocatoriaQuery convocatoriaQuery,AlertaBean alertaBean,SpringMessages mensajes,ArrayList<String> listadoMensajesEmail);
	
	/**
	 * Cambiar estado tipo 4.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @param alertaBean el alerta bean
	 * @param mensajes el mensajes
	 * @param listadoMensajesEmail el listado mensajes email
	 */
	public void cambiarEstadoTipo4(ConvocatoriaQuery convocatoriaQuery,AlertaBean alertaBean,SpringMessages mensajes,ArrayList<String> listadoMensajesEmail);
	
	/**
	 * Cambiar estado tipo 5.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @param alertaBean el alerta bean
	 * @param mensajes el mensajes
	 * @param listadoMensajesEmail el listado mensajes email
	 */
	public void cambiarEstadoTipo5(ConvocatoriaQuery convocatoriaQuery,AlertaBean alertaBean,SpringMessages mensajes,ArrayList<String> listadoMensajesEmail);
	
	/**
	 * Cambiar estado tipo 6.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @param alertaBean el alerta bean
	 * @param mensajes el mensajes
	 * @param listadoMensajesEmail el listado mensajes email
	 */
	public void cambiarEstadoTipo6(ConvocatoriaQuery convocatoriaQuery,AlertaBean alertaBean,SpringMessages mensajes,ArrayList<String> listadoMensajesEmail);
	
	/**
	 * Cambiar estado tipo 7.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @param alertaBean el alerta bean
	 * @param mensajes el mensajes
	 * @param listadoMensajesEmail el listado mensajes email
	 */
	public void cambiarEstadoTipo7(ConvocatoriaQuery convocatoriaQuery,AlertaBean alertaBean,SpringMessages mensajes,ArrayList<String> listadoMensajesEmail);
	
	/**
	 * Comprobar tipo alerta.
	 *
	 * @param alertaBean el alerta bean
	 * @param mensajes el mensajes
	 */
	public void comprobarTipoAlerta(AlertaBean alertaBean,SpringMessages mensajes);
}
