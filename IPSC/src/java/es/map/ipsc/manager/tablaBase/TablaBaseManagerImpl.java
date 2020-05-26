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
import es.map.ipsc.manager.aviso.AvisoManager;
import es.map.ipsc.manager.centroGestor.CentroGestorManager;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.cuerpoEscala.CuerpoEscalaManager;
import es.map.ipsc.manager.especialidades.EspecialidadManager;
import es.map.ipsc.manager.formaAcceso.FormaAccesoManager;
import es.map.ipsc.manager.grupo.GrupoManager;
import es.map.ipsc.manager.ministerio.MinisterioManager;
import es.map.ipsc.manager.pais.PaisManager;
import es.map.ipsc.manager.provincia.ProvinciaExamenManager;
import es.map.ipsc.manager.provincia.ProvinciaManager;
import es.map.ipsc.manager.solicitudes.EstadoSolicitudManager;
import es.map.ipsc.manager.solicitudes.LogSolicitudManager;
import es.map.ipsc.manager.tipoDiscapacidad.TipoDiscapacidadManager;
import es.map.ipsc.manager.tituloOficial.TituloOficialManager;

/**
 * El Class TablaBaseManagerImpl.
 */
public class TablaBaseManagerImpl implements TablaBaseManager {
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el especialidades manager. */
	private EspecialidadManager especialidadesManager;
	
	/** el forma acceso manager. */
	private FormaAccesoManager formaAccesoManager;
	
	/** el grupo manager. */
	private GrupoManager grupoManager;
	
	/** el titulo oficial manager. */
	private TituloOficialManager tituloOficialManager;
	
	/** el provincia manager. */
	private ProvinciaManager provinciaManager;
	
	/** el provincia examen manager. */
	private ProvinciaExamenManager provinciaExamenManager;
	
	/** el estado solicitud manager. */
	private EstadoSolicitudManager estadoSolicitudManager;
	
	/** el ministerio manager. */
	private MinisterioManager ministerioManager;
	
	/** el pais manager. */
	private PaisManager paisManager;
	
	/** el tipo discapacidad manager. */
	private TipoDiscapacidadManager tipoDiscapacidadManager;
	
	/** el cuerpo escala manager. */
	private CuerpoEscalaManager cuerpoEscalaManager;
	
	/** el log solicitud manager. */
	private LogSolicitudManager logSolicitudManager;
	
	/** el aviso manager. */
	private AvisoManager avisoManager;
	
	/** el convocatoria manager. */
	private ConvocatoriasManager convocatoriaManager;
	
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tablaBase.TablaBaseManager#buscarCentroGestoresCombo()
	 */
	public ArrayList<CentroGestorBean> buscarCentroGestoresCombo() {
		return this.centroGestorManager.buscarCentroGestoresCombo();
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tablaBase.TablaBaseManager#buscarEspecialidades()
	 */
	public ArrayList<EspecialidadBean> buscarEspecialidades() {
		return this.especialidadesManager.buscarEspecialidades();
	}
	

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tablaBase.TablaBaseManager#buscarFormaAccesoCombo()
	 */
	public ArrayList<FormaAccesoBean> buscarFormaAccesoCombo() {
		return this.formaAccesoManager.buscarFormaAccesoCombo();
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tablaBase.TablaBaseManager#buscarGruposCombo()
	 */
	public ArrayList<GrupoBean> buscarGruposCombo() {
		return this.grupoManager.buscarGruposCombo();
	}

	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tablaBase.TablaBaseManager#buscarMinisteriosCombo()
	 */
	public ArrayList<MinisterioBean> buscarMinisteriosCombo() {
		return this.ministerioManager.buscarMinisteriosCombo();
	}	

	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tablaBase.TablaBaseManager#buscarProvinciasActivasCombo()
	 */
	public ArrayList<ProvinciaBean> buscarProvinciasActivasCombo() {
		return this.provinciaManager.buscarProvinciasActivasCombo();
	}	
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tablaBase.TablaBaseManager#buscarProvinciasExamenActivasCombo()
	 */
	public ArrayList<ProvinciaExamenBean> buscarProvinciasExamenActivasCombo() {
		return this.provinciaExamenManager.buscarProvinciasExamenActivasCombo();
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tablaBase.TablaBaseManager#buscarTituloOficialesCombo()
	 */
	public ArrayList<TituloOficialBean> buscarTituloOficialesCombo() {
		return this.tituloOficialManager.buscarTituloOficialesCombo();
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tablaBase.TablaBaseManager#buscarEstadoSolicitudCombo()
	 */
	public ArrayList<EstadoSolicitudBean> buscarEstadoSolicitudCombo() {
		return this.estadoSolicitudManager.buscarEstadoSolicitudCombo();
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tablaBase.TablaBaseManager#buscarPaises()
	 */
	public ArrayList<PaisBean> buscarPaises() {
		return this.paisManager.buscarPaises();
	}


	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tablaBase.TablaBaseManager#buscarTiposDiscapacidad()
	 */
	public ArrayList<TipoDiscapacidadBean> buscarTiposDiscapacidad() {
		return this.tipoDiscapacidadManager.buscarTiposDiscapacidad();
	}


	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tablaBase.TablaBaseManager#obtenerProvinciasActivas()
	 */
	public ArrayList<ProvinciaBean> obtenerProvinciasActivas() {
		return this.provinciaManager.buscarProvinciasActivasCombo();
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tablaBase.TablaBaseManager#obtenerProvinciasExamenActivas()
	 */
	public ArrayList<ProvinciaExamenBean> obtenerProvinciasExamenActivas() {
		return this.provinciaExamenManager.buscarProvinciasExamenActivasCombo();
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tablaBase.TablaBaseManager#buscarCuerpoEscala()
	 */
	public ArrayList<CuerpoEscalaBean> buscarCuerpoEscala() {
		return this.cuerpoEscalaManager.buscarCuerpoEscala();
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tablaBase.TablaBaseManager#buscarProvinciasFiltrado(es.map.ips.model.ConvocatoriaQuery, es.map.ips.model.ProvinciaQuery)
	 */
	public ArrayList<Integer> buscarProvinciasFiltrado(
			ConvocatoriaQuery convocatoriaQuery, ProvinciaQuery provinciaQuery) {
		ArrayList<ProvinciaBean> provincias = provinciaManager.buscarProvinciasActivasCombo();
		ArrayList<Integer> idConvocatoria = new ArrayList<Integer>();
		for(int i=0;i<provincias.size();i++){
			idConvocatoria.add(provincias.get(i).getId());
		}
		return idConvocatoria;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tablaBase.TablaBaseManager#insertarLogSolicitud(es.map.ipsc.bean.LogSolicitudBean)
	 */
	public Integer insertarLogSolicitud(LogSolicitudBean logSolicitudBean) {
		Integer id = logSolicitudManager.actualizarLogSolicitud(logSolicitudBean);
		if(id != null){
			return id;
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tablaBase.TablaBaseManager#buscarAvisos(es.map.ips.model.AvisoQuery)
	 */
	public ArrayList<AvisoBean> buscarAvisos(AvisoQuery avisoQuery) {
		ArrayList<AvisoBean> avisoAux = avisoManager.buscarAvisos(avisoQuery);
		if(avisoAux != null){
			return avisoAux;
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tablaBase.TablaBaseManager#buscarCentroGestorCodModel(es.map.ips.model.CentroGestorQuery)
	 */
	public SearchResult<CentroGestor> buscarCentroGestorCodModel(
			CentroGestorQuery centroGestorQuery) {
		SearchResult<CentroGestor> centroGestorAux = centroGestorManager.buscarCentroGestorCodModel(centroGestorQuery);
		if(centroGestorAux != null){
			return centroGestorAux;
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tablaBase.TablaBaseManager#buscarConvocatoriaId(es.map.ips.model.ConvocatoriaQuery)
	 */
	public ConvocatoriaBean buscarConvocatoriaId(
			ConvocatoriaQuery convocatoriaQuery) {
		ConvocatoriaBean convocatoriaBean = convocatoriaManager.buscarConvocatoriaId(convocatoriaQuery);
		if(convocatoriaBean != null){
			return convocatoriaBean;
		}
		return null;
	}
	
	/////////////////////////////////////////////////////////////////////
	//////////////                      GETTERS Y SETTERS
	/////////////////////////////////////////////////////////////////////
	
	/**
	 * Obtiene el centro gestor manager.
	 *
	 * @return el centro gestor manager
	 */
	public CentroGestorManager getCentroGestorManager() {
		return centroGestorManager;
	}

	/**
	 * Establece el centro gestor manager.
	 *
	 * @param centroGestorManager el nuevo centro gestor manager
	 */
	public void setCentroGestorManager(CentroGestorManager centroGestorManager) {
		this.centroGestorManager = centroGestorManager;
	}

	

	/**
	 * Obtiene el especialidades manager.
	 *
	 * @return el especialidades manager
	 */
	public EspecialidadManager getEspecialidadesManager() {
		return especialidadesManager;
	}


	/**
	 * Establece el especialidades manager.
	 *
	 * @param especialidadesManager el nuevo especialidades manager
	 */
	public void setEspecialidadesManager(EspecialidadManager especialidadesManager) {
		this.especialidadesManager = especialidadesManager;
	}


	/**
	 * Obtiene el pais manager.
	 *
	 * @return el pais manager
	 */
	public PaisManager getPaisManager() {
		return paisManager;
	}


	/**
	 * Establece el pais manager.
	 *
	 * @param paisManager el nuevo pais manager
	 */
	public void setPaisManager(PaisManager paisManager) {
		this.paisManager = paisManager;
	}


	/**
	 * Obtiene el tipo discapacidad manager.
	 *
	 * @return el tipo discapacidad manager
	 */
	public TipoDiscapacidadManager getTipoDiscapacidadManager() {
		return tipoDiscapacidadManager;
	}


	/**
	 * Establece el tipo discapacidad manager.
	 *
	 * @param tipoDiscapacidadManager el nuevo tipo discapacidad manager
	 */
	public void setTipoDiscapacidadManager(
			TipoDiscapacidadManager tipoDiscapacidadManager) {
		this.tipoDiscapacidadManager = tipoDiscapacidadManager;
	}


	/**
	 * Obtiene el forma acceso manager.
	 *
	 * @return el forma acceso manager
	 */
	public FormaAccesoManager getFormaAccesoManager() {
		return formaAccesoManager;
	}

	/**
	 * Establece el forma acceso manager.
	 *
	 * @param formaAccesoManager el nuevo forma acceso manager
	 */
	public void setFormaAccesoManager(FormaAccesoManager formaAccesoManager) {
		this.formaAccesoManager = formaAccesoManager;
	}

	/**
	 * Obtiene el grupo manager.
	 *
	 * @return el grupo manager
	 */
	public GrupoManager getGrupoManager() {
		return grupoManager;
	}

	/**
	 * Establece el grupo manager.
	 *
	 * @param grupoManager el nuevo grupo manager
	 */
	public void setGrupoManager(GrupoManager grupoManager) {
		this.grupoManager = grupoManager;
	}

	/**
	 * Obtiene el titulo oficial manager.
	 *
	 * @return el titulo oficial manager
	 */
	public TituloOficialManager getTituloOficialManager() {
		return tituloOficialManager;
	}

	/**
	 * Establece el titulo oficial manager.
	 *
	 * @param tituloOficialManager el nuevo titulo oficial manager
	 */
	public void setTituloOficialManager(TituloOficialManager tituloOficialManager) {
		this.tituloOficialManager = tituloOficialManager;
	}

	/**
	 * Obtiene el provincia manager.
	 *
	 * @return el provincia manager
	 */
	public ProvinciaManager getProvinciaManager() {
		return provinciaManager;
	}

	/**
	 * Establece el provincia manager.
	 *
	 * @param provinciaManager el nuevo provincia manager
	 */
	public void setProvinciaManager(ProvinciaManager provinciaManager) {
		this.provinciaManager = provinciaManager;
	}

	/**
	 * Obtiene el provincia examen manager.
	 *
	 * @return el provincia examen manager
	 */
	public ProvinciaExamenManager getProvinciaExamenManager() {
		return provinciaExamenManager;
	}


	/**
	 * Establece el provincia examen manager.
	 *
	 * @param provinciaExamenManager el nuevo provincia examen manager
	 */
	public void setProvinciaExamenManager(
			ProvinciaExamenManager provinciaExamenManager) {
		this.provinciaExamenManager = provinciaExamenManager;
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
	 * Obtiene el ministerio manager.
	 *
	 * @return el ministerio manager
	 */
	public MinisterioManager getMinisterioManager() {
		return ministerioManager;
	}

	/**
	 * Establece el ministerio manager.
	 *
	 * @param ministerioManager el nuevo ministerio manager
	 */
	public void setMinisterioManager(MinisterioManager ministerioManager) {
		this.ministerioManager = ministerioManager;
	}


	/**
	 * Obtiene el cuerpo escala manager.
	 *
	 * @return el cuerpo escala manager
	 */
	public CuerpoEscalaManager getCuerpoEscalaManager() {
		return cuerpoEscalaManager;
	}


	/**
	 * Establece el cuerpo escala manager.
	 *
	 * @param cuerpoEscalaManager el nuevo cuerpo escala manager
	 */
	public void setCuerpoEscalaManager(CuerpoEscalaManager cuerpoEscalaManager) {
		this.cuerpoEscalaManager = cuerpoEscalaManager;
	}


	/**
	 * Obtiene el log solicitud manager.
	 *
	 * @return el log solicitud manager
	 */
	public LogSolicitudManager getLogSolicitudManager() {
		return logSolicitudManager;
	}


	/**
	 * Establece el log solicitud manager.
	 *
	 * @param logSolicitudManager el nuevo log solicitud manager
	 */
	public void setLogSolicitudManager(LogSolicitudManager logSolicitudManager) {
		this.logSolicitudManager = logSolicitudManager;
	}


	/**
	 * Obtiene el aviso manager.
	 *
	 * @return el aviso manager
	 */
	public AvisoManager getAvisoManager() {
		return avisoManager;
	}


	/**
	 * Establece el aviso manager.
	 *
	 * @param avisoManager el nuevo aviso manager
	 */
	public void setAvisoManager(AvisoManager avisoManager) {
		this.avisoManager = avisoManager;
	}


	


	/**
	 * Obtiene el convocatoria manager.
	 *
	 * @return el convocatoria manager
	 */
	public ConvocatoriasManager getConvocatoriaManager() {
		return convocatoriaManager;
	}


	/**
	 * Establece el convocatoria manager.
	 *
	 * @param convocatoriaManager el nuevo convocatoria manager
	 */
	public void setConvocatoriaManager(ConvocatoriasManager convocatoriaManager) {
		this.convocatoriaManager = convocatoriaManager;
	}


	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.tablaBase.TablaBaseManager#buscarTiposDiscapacidad(es.map.ips.model.TipoDiscapacidadQuery)
	 */
	@Override
	public ArrayList<TipoDiscapacidadBean> buscarTiposDiscapacidad(
			TipoDiscapacidadQuery tipoDiscapacidadQuery) {
		return this.tipoDiscapacidadManager.buscarTiposDiscapacidad(tipoDiscapacidadQuery);
	}





	


	


	


	


	





	

}
