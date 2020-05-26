package es.map.ipsc.manager.tablaBase;

import java.util.ArrayList;

import es.map.ips.common.model.SearchResult;
import es.map.ips.model.AvisoQuery;
import es.map.ips.model.CentroGestor;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.ProvinciaQuery;
import es.map.ips.model.TipoDiscapacidadQuery;
import es.map.ipsc.bean.AvisoBean;
import es.map.ipsc.bean.CentroGestorBean;
import es.map.ipsc.bean.ConvocatoriaBean;
import es.map.ipsc.bean.CuerpoEscalaBean;
import es.map.ipsc.bean.EspecialidadBean;
import es.map.ipsc.bean.EstadoSolicitudBean;
import es.map.ipsc.bean.FormaAccesoBean;
import es.map.ipsc.bean.GrupoBean;
import es.map.ipsc.bean.LogSolicitudBean;
import es.map.ipsc.bean.MinisterioBean;
import es.map.ipsc.bean.PaisBean;
import es.map.ipsc.bean.ProvinciaBean;
import es.map.ipsc.bean.ProvinciaExamenBean;
import es.map.ipsc.bean.TipoDiscapacidadBean;
import es.map.ipsc.bean.TituloOficialBean;

/**
 * El Interface TablaBaseManager.
 */
public interface TablaBaseManager {

	/**
	 * Buscar ministerios combo.
	 *
	 * @return el array list
	 */
	ArrayList<MinisterioBean> buscarMinisteriosCombo();
	
	/**
	 * Buscar grupos combo.
	 *
	 * @return el array list
	 */
	ArrayList<GrupoBean> buscarGruposCombo();
	
	/**
	 * Buscar forma acceso combo.
	 *
	 * @return el array list
	 */
	ArrayList<FormaAccesoBean> buscarFormaAccesoCombo();
	
	/**
	 * Buscar centro gestores combo.
	 *
	 * @return el array list
	 */
	ArrayList<CentroGestorBean> buscarCentroGestoresCombo();
	
	/**
	 * Buscar especialidades.
	 *
	 * @return el array list
	 */
	ArrayList<EspecialidadBean> buscarEspecialidades();
	
	/**
	 * Buscar provincias activas combo.
	 *
	 * @return el array list
	 */
	ArrayList<ProvinciaBean> buscarProvinciasActivasCombo();
	
	/**
	 * Buscar provincias examen activas combo.
	 *
	 * @return el array list
	 */
	ArrayList<ProvinciaExamenBean> buscarProvinciasExamenActivasCombo();
	
	/**
	 * Buscar titulo oficiales combo.
	 *
	 * @return el array list
	 */
	ArrayList<TituloOficialBean> buscarTituloOficialesCombo();
	
	/**
	 * Buscar estado solicitud combo.
	 *
	 * @return el array list
	 */
	ArrayList<EstadoSolicitudBean> buscarEstadoSolicitudCombo();
	
	/**
	 * Obtener provincias activas.
	 *
	 * @return el array list
	 */
	ArrayList<ProvinciaBean> obtenerProvinciasActivas();
	
	/**
	 * Obtener provincias examen activas.
	 *
	 * @return el array list
	 */
	ArrayList<ProvinciaExamenBean> obtenerProvinciasExamenActivas();
	
	/**
	 * Buscar paises.
	 *
	 * @return el array list
	 */
	ArrayList<PaisBean> buscarPaises();
	
	/**
	 * Buscar tipos discapacidad.
	 *
	 * @return el array list
	 */
	ArrayList<TipoDiscapacidadBean> buscarTiposDiscapacidad();
	
	/**
	 * Buscar tipos discapacidad.
	 *
	 * @param tipoDiscapacidadQuery el tipo discapacidad query
	 * @return el array list
	 */
	ArrayList<TipoDiscapacidadBean> buscarTiposDiscapacidad(TipoDiscapacidadQuery tipoDiscapacidadQuery);
	
	/**
	 * Buscar cuerpo escala.
	 *
	 * @return el array list
	 */
	ArrayList<CuerpoEscalaBean> buscarCuerpoEscala();
	
	/**
	 * Buscar provincias filtrado.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @param provinciaQuery el provincia query
	 * @return el array list
	 */
	ArrayList<Integer> buscarProvinciasFiltrado(ConvocatoriaQuery convocatoriaQuery,
			ProvinciaQuery provinciaQuery);
	
	/**
	 * Insertar log solicitud.
	 *
	 * @param logSolicitudBean el log solicitud bean
	 * @return el integer
	 */
	Integer insertarLogSolicitud(LogSolicitudBean logSolicitudBean);
	
	/**
	 * Buscar avisos.
	 *
	 * @param avisoQuery el aviso query
	 * @return el array list
	 */
	ArrayList<AvisoBean> buscarAvisos(AvisoQuery avisoQuery);
	
	/**
	 * Buscar centro gestor cod model.
	 *
	 * @param centroGestorQuery el centro gestor query
	 * @return el search result
	 */
	SearchResult<CentroGestor> buscarCentroGestorCodModel(CentroGestorQuery centroGestorQuery);
	
	/**
	 * Buscar convocatoria id.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @return el convocatoria bean
	 */
	ConvocatoriaBean buscarConvocatoriaId(ConvocatoriaQuery convocatoriaQuery);
}
