package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.model.Convocatoria;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.EstadoConvocatoriaQuery;
import es.map.ips.model.TarifaQuery;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.CrearConvocatoriaBean;
import es.map.ipsg.bean.DetalleConvocatoriaBean;
import es.map.ipsg.bean.ModificarConvocatoriaBean;
import es.map.ipsg.form.CrearConvocatoriaForm;


/**
 * El Interface ConvocatoriasManager.
 */
public interface ConvocatoriasManager {

	/**
	 * Buscar convocatorias all.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @return el array list
	 */
	public ArrayList<ConvocatoriasBean> buscarConvocatoriasAll(ConvocatoriaQuery convocatoriaQuery);
	
	/**
	 * Buscar convocatoria.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @return el convocatoria
	 */
	public Convocatoria buscarConvocatoria(ConvocatoriaQuery convocatoriaQuery);
	
	/**
	 * Actualizar estado.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @param estadoConvocatoriaQuery el estado convocatoria query
	 */
	public void actualizarEstado(ConvocatoriaQuery convocatoriaQuery,EstadoConvocatoriaQuery estadoConvocatoriaQuery);
	
	/**
	 * Detalle convocatoria.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @return el detalle convocatoria bean
	 */
	public DetalleConvocatoriaBean detalleConvocatoria(ConvocatoriaQuery convocatoriaQuery);
	
	/**
	 * Crear convocatoria.
	 *
	 * @param bean el bean
	 * @param formulario el formulario
	 * @return el long
	 */
	public long crearConvocatoria(CrearConvocatoriaBean bean, CrearConvocatoriaForm formulario);
	
	/**
	 * Buscar importe.
	 *
	 * @param cuerpoEscalaId el cuerpo escala id
	 * @param formaAccesoId el forma acceso id
	 * @param tarifaQuery el tarifa query
	 * @return el string
	 */
	public String buscarImporte(int cuerpoEscalaId, int formaAccesoId,TarifaQuery tarifaQuery);
	
	/**
	 * Copiar convocatoria.
	 *
	 * @param id el id
	 * @return el long
	 */
	public long copiarConvocatoria(long id);
	
	/**
	 * Obtener modificar convocatoria.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @return el modificar convocatoria bean
	 */
	public ModificarConvocatoriaBean obtenerModificarConvocatoria(ConvocatoriaQuery convocatoriaQuery);
	
	/**
	 * Modificar convocatoria.
	 *
	 * @param bean el bean
	 * @param formulario el formulario
	 */
	public void modificarConvocatoria(CrearConvocatoriaBean bean, CrearConvocatoriaForm formulario);
	
	/**
	 * Buscar convocatorias all sin num total.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @return el array list
	 */
	public ArrayList<ConvocatoriasBean> buscarConvocatoriasAllSinNumTotal(ConvocatoriaQuery convocatoriaQuery);	
	
	/**
	 * Modificar campos convocatoria.
	 *
	 * @param convocatoria el convocatoria
	 */
	public void modificarCamposConvocatoria (Convocatoria convocatoria);
	
	/**
	 * Buscar convocatoria by id.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @return el convocatorias bean
	 */
	public ConvocatoriasBean buscarConvocatoriaById(ConvocatoriaQuery convocatoriaQuery);
	
	/**
	 * Recuperar convocatoria.
	 *
	 * @param codigoConvocatoria el codigo convocatoria
	 * @return el convocatorias bean
	 */
	public ConvocatoriasBean recuperarConvocatoria(long codigoConvocatoria);
	public ConvocatoriasBean toConvocatoriaBean(Convocatoria convocatoria);
}
