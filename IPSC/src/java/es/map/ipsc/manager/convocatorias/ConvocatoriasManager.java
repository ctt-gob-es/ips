package es.map.ipsc.manager.convocatorias;


import java.util.ArrayList;
import java.util.List;

import es.map.ips.model.Convocatoria;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.ConvocatoriasAbiertasViewQuery;
import es.map.ips.model.ConvocatoriasSubsanarViewQuery;
import es.map.ips.model.ConvocatoriasViewQuery;
import es.map.ips.model.EspecialidadQuery;
import es.map.ips.model.ProvinciaExamenQuery;
import es.map.ips.model.TituloOficialQuery;
import es.map.ipsc.bean.BuscarConvocatoriasBean;
import es.map.ipsc.bean.ConvocatoriaBean;


/**
 * El Interface ConvocatoriasManager.
 */
public interface ConvocatoriasManager {

	/**
	 * Recuperar convocatoria.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @return el convocatoria bean
	 */
	ConvocatoriaBean recuperarConvocatoria(ConvocatoriaQuery convocatoriaQuery);

	/**
	 * Buscar convocatoria id.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @return el convocatoria bean
	 */
	ConvocatoriaBean buscarConvocatoriaId(ConvocatoriaQuery convocatoriaQuery);

	/**
	 * Buscar convocatorias view.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @return el list
	 */
	List<BuscarConvocatoriasBean> buscarConvocatoriasView(
			ConvocatoriasAbiertasViewQuery convocatoriaQuery);

	/**
	 * Buscar provincias filtrado.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @param provinciaQuery el provincia query
	 * @return el array list
	 */
	ArrayList<Long> buscarProvinciasFiltrado(
			ConvocatoriaQuery convocatoriaQuery, ProvinciaExamenQuery provinciaQuery);

	/**
	 * Buscar especialidad filtrado.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @param especialidadQuery el especialidad query
	 * @return el array list
	 */
	ArrayList<Long> buscarEspecialidadFiltrado(ConvocatoriaQuery convocatoriaQuery,
			EspecialidadQuery especialidadQuery);

	/**
	 * Buscar titulos filtrado.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @param tituloOficialQuery el titulo oficial query
	 * @return el array list
	 */
	ArrayList<Long> buscarTitulosFiltrado(ConvocatoriaQuery convocatoriaQuery,
			TituloOficialQuery tituloOficialQuery);

	/**
	 * Buscar convocatoria id model.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @return el convocatoria
	 */
	Convocatoria buscarConvocatoriaIdModel(ConvocatoriaQuery convocatoriaQuery);

	/**
	 * Recuperar convocatoria.
	 *
	 * @param idConvocatoria el id convocatoria
	 * @return el convocatoria bean
	 */
	ConvocatoriaBean recuperarConvocatoria(long idConvocatoria);

	/**
	 * Buscar convocatorias subsanadas view.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @return el list
	 */
	List<BuscarConvocatoriasBean> buscarConvocatoriasSubsanadasView(ConvocatoriasSubsanarViewQuery convocatoriaQuery);
	
	/**
	 * Recuperar num convocatoria abiertas.
	 *
	 * @param query el query
	 * @return el integer
	 */
	Integer recuperarNumConvocatoriaAbiertas(ConvocatoriasAbiertasViewQuery query);
	
	/**
	 * Recuperar num convocatoria subsanadas.
	 *
	 * @param query el query
	 * @return el integer
	 */
	Integer recuperarNumConvocatoriaSubsanadas(ConvocatoriasSubsanarViewQuery query);
}
