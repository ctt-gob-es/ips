package es.map.ipsc.manager.formulario790;


import org.apache.log4j.Logger;

import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.ComunidadesQuery;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.EspecialidadQuery;
import es.map.ips.model.FormaAccesoQuery;
import es.map.ips.model.MinisterioQuery;
import es.map.ips.model.PaisQuery;
import es.map.ips.model.PlantillaQuery;
import es.map.ips.model.ProvinciaExamenQuery;
import es.map.ips.model.ProvinciaQuery;
import es.map.ips.model.TipoDiscapacidadQuery;
import es.map.ips.model.TituloOficialQuery;
import es.map.ipsc.bean.CentroGestorBean;
import es.map.ipsc.bean.ComunidadesBean;
import es.map.ipsc.bean.ConvocatoriaBean;
import es.map.ipsc.bean.CuerpoEscalaBean;
import es.map.ipsc.bean.EspecialidadBean;
import es.map.ipsc.bean.FormaAccesoBean;
import es.map.ipsc.bean.MinisterioBean;
import es.map.ipsc.bean.PaisBean;
import es.map.ipsc.bean.PlantillaBean;
import es.map.ipsc.bean.ProvinciaBean;
import es.map.ipsc.bean.ProvinciaExamenBean;
import es.map.ipsc.bean.TipoDiscapacidadBean;
import es.map.ipsc.bean.TituloOficialBean;
import es.map.ipsc.manager.centroGestor.CentroGestorManager;
import es.map.ipsc.manager.comunidades.ComunidadesManager;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.manager.cuerpoEscala.CuerpoEscalaManager;
import es.map.ipsc.manager.especialidades.EspecialidadManager;
import es.map.ipsc.manager.formaAcceso.FormaAccesoManager;
import es.map.ipsc.manager.ministerio.MinisterioManager;
import es.map.ipsc.manager.pais.PaisManager;
import es.map.ipsc.manager.plantilla.PlantillaManager;
import es.map.ipsc.manager.provincia.ProvinciaExamenManager;
import es.map.ipsc.manager.provincia.ProvinciaManager;
import es.map.ipsc.manager.tipoDiscapacidad.TipoDiscapacidadManager;
import es.map.ipsc.manager.tituloOficial.TituloOficialManager;

/**
 * El Class Formulario790ManagerImpl.
 */
public class Formulario790ManagerImpl implements Formulario790Manager {
	
	/** el pais manager. */
	private PaisManager paisManager;
	
	/** el provincia manager. */
	private ProvinciaManager provinciaManager;
	
	/** el provincia examen manager. */
	private ProvinciaExamenManager provinciaExamenManager;
	
	/** el cuerpo escala manager. */
	private CuerpoEscalaManager cuerpoEscalaManager;
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** el especialidades manager. */
	private EspecialidadManager especialidadesManager;
	
	/** el forma acceso manager. */
	private FormaAccesoManager formaAccesoManager;
	
	/** el ministerio manager. */
	private MinisterioManager ministerioManager;
	
	/** el titulo oficial manager. */
	private TituloOficialManager tituloOficialManager;
	
	/** el tipo discapacidad manager. */
	private TipoDiscapacidadManager tipoDiscapacidadManager;
	
	/** el convocatoria manager. */
	private ConvocatoriasManager convocatoriaManager;
	
	/** el plantilla manager. */
	private PlantillaManager plantillaManager;	
	
	/** el comunidades manager. */
	private ComunidadesManager comunidadesManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(Formulario790ManagerImpl.class);

	
	

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.formulario790.Formulario790Manager#getPaisPorId(java.lang.Long)
	 */
	public PaisBean getPaisPorId(Long id){
		PaisQuery paisQuery = new PaisQuery();
		String auxPais = String.valueOf(id);
		PaisBean result = new PaisBean();
		try{
			paisQuery.setId(Integer.parseInt(auxPais));
			result = paisManager.buscarPaisId(paisQuery);
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error Formulario790ManagerImpl - parsear Pais",e);
		}
		if(result == null){
			return null;
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.formulario790.Formulario790Manager#getComunidadPorId(java.lang.Long)
	 */
	@Override
	public ComunidadesBean getComunidadPorId(Long id) {
		ComunidadesQuery comunidadQuery = new ComunidadesQuery();
		String auxComunidad = String.valueOf(id);
		ComunidadesBean result = new ComunidadesBean();
		try{
			comunidadQuery.setIdComunidad(Integer.parseInt(auxComunidad));		
			result = comunidadesManager.buscarComunidadesId(comunidadQuery);
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error Formulario790ManagerImpl - Parsear Comunidad",e);
		}
		if(result == null){
			return null;
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.formulario790.Formulario790Manager#getProvinciaPorId(java.lang.Long)
	 */
	public ProvinciaBean getProvinciaPorId(Long id){
		ProvinciaQuery provinciaQuery = new ProvinciaQuery();
		String auxProvincia = String.valueOf(id);
		ProvinciaBean result = new ProvinciaBean();
		try{
			provinciaQuery.setId(Integer.parseInt(auxProvincia));		
			result = provinciaManager.buscarProviciaId(provinciaQuery);
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error Formulario790ManagerImpl - Parsear Provincia",e);
		}
		if(result == null){
			return null;
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.formulario790.Formulario790Manager#getProvinciaExamenPorId(java.lang.Long)
	 */
	public ProvinciaExamenBean getProvinciaExamenPorId(Long id){
		ProvinciaExamenQuery provinciaExamenQuery = new ProvinciaExamenQuery();
		String auxProvincia = String.valueOf(id);
		ProvinciaExamenBean result = new ProvinciaExamenBean();
		try{
			provinciaExamenQuery.setId(Integer.parseInt(auxProvincia));		
			result = provinciaExamenManager.buscarProviciaExamenId(provinciaExamenQuery);
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error Formulario790ManagerImpl - parsear Provincia examen",e);
		}
		if(result == null){
			return null;
		}
		return result;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.formulario790.Formulario790Manager#getCuerpoEscalaPorId(java.lang.Long)
	 */
	public CuerpoEscalaBean getCuerpoEscalaPorId(Long id){
		CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
		String auxCuerpoEscala = String.valueOf(id);
		CuerpoEscalaBean result = new CuerpoEscalaBean();
		try{
			cuerpoEscalaQuery.setId(Integer.parseInt(auxCuerpoEscala));		
			result = cuerpoEscalaManager.buscarCuerpoEscalaId2(cuerpoEscalaQuery);
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error Formulario790ManagerImpl - Parsear cuerpo Escala",e);
		}
		if(result == null){
			return null;
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.formulario790.Formulario790Manager#getCentroGestorPorId(java.lang.Long)
	 */
	public CentroGestorBean getCentroGestorPorId(Long id) {
		CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
		String auxCentroGestor = String.valueOf(id);
		CentroGestorBean result = new CentroGestorBean();
		try{
			centroGestorQuery.setId(Integer.parseInt(auxCentroGestor));		
			result = centroGestorManager.buscarCentroGestorId(centroGestorQuery);
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error Formulario790ManagerImpl - Parsear centro gestor",e);
		}
		if(result == null){
			return null;
		}
		
		return result;
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.formulario790.Formulario790Manager#getEspecialidadPorId(java.lang.Long)
	 */
	public EspecialidadBean getEspecialidadPorId(Long id){
		EspecialidadQuery especialidadQuery = new EspecialidadQuery();
		String auxEspecialidad = String.valueOf(id);
		EspecialidadBean result = new EspecialidadBean();
		try{
			especialidadQuery.setId(Integer.parseInt(auxEspecialidad));
			result = especialidadesManager.buscarEspecialidadId(especialidadQuery);
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error Formulario790ManagerImpl - Parsear Especialidad",e);
		}
		if(result == null){
			return null;
		}
		
		return result;
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.formulario790.Formulario790Manager#getFormaAccesoPorId(java.lang.Long)
	 */
	public FormaAccesoBean getFormaAccesoPorId(Long id){
		FormaAccesoQuery formaAccesoQuery = new FormaAccesoQuery();
		String auxFormaAcceso = String.valueOf(id);
		FormaAccesoBean result = new FormaAccesoBean();
		try{
			formaAccesoQuery.setId(Integer.parseInt(auxFormaAcceso));		
			result = formaAccesoManager.buscarFormaAccesoId(formaAccesoQuery);
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error Formulario790ManagerImpl - Parsear forma de acceso",e);
		}
		if(result == null){
			return null;
		}

		return result;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.formulario790.Formulario790Manager#getMinisterioPorId(java.lang.Long)
	 */
	public MinisterioBean getMinisterioPorId(Long id){
		MinisterioQuery ministerioQuery = new MinisterioQuery();
		String auxMinisterio = String.valueOf(id);
		MinisterioBean result = new MinisterioBean();
		try{
			ministerioQuery.setId(Integer.parseInt(auxMinisterio));
			result = ministerioManager.buscarMinisterioId(ministerioQuery);
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error Formulario790ManagerImpl - Parsear ministerio",e);
		}
		if(result == null){
			return null;
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.formulario790.Formulario790Manager#getTituloOficialPorId(java.lang.Long)
	 */
	public TituloOficialBean getTituloOficialPorId(Long id){
		TituloOficialQuery tituloOficialQuery = new TituloOficialQuery();
		String auxTitulo = String.valueOf(id);
		TituloOficialBean result = new TituloOficialBean();
		try{
			tituloOficialQuery.setId(Integer.parseInt(auxTitulo));	
			result = tituloOficialManager.buscarTituloOficialId(tituloOficialQuery);
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error Formulario790ManagerImpl - Parsear titulo oficial",e);
		}
		if(result == null){
			return null;
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.formulario790.Formulario790Manager#getTipoDiscapacidadPorId(java.lang.Long)
	 */
	public TipoDiscapacidadBean getTipoDiscapacidadPorId(Long id){
		TipoDiscapacidadQuery tipoDiscapacidadQuery = new TipoDiscapacidadQuery();
		String auxTipoDiscapacidad = String.valueOf(id);
		TipoDiscapacidadBean result = new TipoDiscapacidadBean();
		try{
			tipoDiscapacidadQuery.setId(Integer.parseInt(auxTipoDiscapacidad));		
			result = tipoDiscapacidadManager.buscarTipoDiscapacidadId(tipoDiscapacidadQuery);
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error Formulario790ManagerImpl - Parsear tipo discapacidad",e);
		}
		if(result == null){
			return null;
		}
		
		return result;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.formulario790.Formulario790Manager#getConvocatoriaPorId(java.lang.Long)
	 */
	public ConvocatoriaBean getConvocatoriaPorId(Long id){
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(id);
		ConvocatoriaBean result = convocatoriaManager.buscarConvocatoriaId(convocatoriaQuery);		
		if(result == null){
			return null;
		}
		return result;
	}

/* (non-Javadoc)
 * @see es.map.ipsc.manager.formulario790.Formulario790Manager#getCodigosPrecargablesPlantillaConvocatoria(java.lang.Long, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean)
 */
public void getCodigosPrecargablesPlantillaConvocatoria(Long idPlantilla, Boolean codMinisterio ,Boolean codCuerpoEscala ,Boolean codEspecialidad ,Boolean codTituloOficial ,Boolean codPaisDomicilio ,Boolean codProvinciaDomicilio ,Boolean codProvinciaExamen){
		
		try{
			PlantillaBean plantilla;
			
			// se busca la plantilla general
			PlantillaQuery plantillaQuery = new PlantillaQuery();
			plantillaQuery.setId(idPlantilla);
			plantilla = plantillaManager.buscarPlantillaById(plantillaQuery);
			
			// se obtienen la visibilidad de los codigos precargables
			if(plantilla.getCodigoCuerpoEscala().equals('S'))
			{
				codCuerpoEscala = true;
			}
			else
			{
				codCuerpoEscala = false;
			}	
			
			if(plantilla.getCodigoEspecialidad().equals('S'))
			{
				codEspecialidad = true;
			}
			else
			{
				codEspecialidad = false;
			}	
			
			if(plantilla.getCodigoMinisterio().equals('S'))
			{
				codMinisterio = true;
			}
			else
			{
				codMinisterio = false;
			}	
			
			if(plantilla.getCodigoPaisDomicilio().equals('S'))
			{
				codPaisDomicilio = true;
			}
			else
			{
				codPaisDomicilio = false;
			}	
			
			if(plantilla.getCodigoProvinciaDomicilio().equals('S'))
			{
				codProvinciaDomicilio = true;
			}
			else
			{
				codProvinciaDomicilio = false;
			}	
			
			if(plantilla.getCodigoProvinciaExamen().equals('S'))
			{
				codProvinciaExamen = true;
			}
			else
			{
				codProvinciaExamen = false;
			}	
			
			if(plantilla.getCodigoTituloOficial().equals('S'))
			{
				codTituloOficial = true;
			}
			else
			{
				codTituloOficial = false;
			}	
			
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error Formulario790ManagerImpl - Codigos Precargables Plantilla Convocatoria",e);
		}
	}


	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.formulario790.Formulario790Manager#getCodigosPrecargablesPlantillaGeneral(java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean, java.lang.Boolean)
	 */
	public void getCodigosPrecargablesPlantillaGeneral(Boolean codMinisterio ,Boolean codCuerpoEscala ,Boolean codEspecialidad ,Boolean codTituloOficial ,Boolean codPaisDomicilio ,Boolean codProvinciaDomicilio ,Boolean codProvinciaExamen){
		
		try{
			PlantillaBean plantilla;
			
			// se busca la plantilla general
			PlantillaQuery plantillaQuery = new PlantillaQuery();
			plantillaQuery.setTipoPlantilla('A');
			plantilla = plantillaManager.buscarPlantilla(plantillaQuery);
			
			// se obtienen la visibilidad de los codigos precargables
			if(plantilla.getCodigoCuerpoEscala().equals('S'))
			{
				codCuerpoEscala = true;
			}
			else
			{
				codCuerpoEscala = false;
			}	
			
			if(plantilla.getCodigoEspecialidad().equals('S'))
			{
				codEspecialidad = true;
			}
			else
			{
				codEspecialidad = false;
			}	
			
			if(plantilla.getCodigoMinisterio().equals('S'))
			{
				codMinisterio = true;
			}
			else
			{
				codMinisterio = false;
			}	
			
			if(plantilla.getCodigoPaisDomicilio().equals('S'))
			{
				codPaisDomicilio = true;
			}
			else
			{
				codPaisDomicilio = false;
			}	
			
			if(plantilla.getCodigoProvinciaDomicilio().equals('S'))
			{
				codProvinciaDomicilio = true;
			}
			else
			{
				codProvinciaDomicilio = false;
			}	
			
			if(plantilla.getCodigoProvinciaExamen().equals('S'))
			{
				codProvinciaExamen = true;
			}
			else
			{
				codProvinciaExamen = false;
			}	
			
			if(plantilla.getCodigoTituloOficial().equals('S'))
			{
				codTituloOficial = true;
			}
			else
			{
				codTituloOficial = false;
			}	
			
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error Formulario790ManagerImpl - Codigos Precargables Plantilla General",e);
		}
		
		
		
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

	/**
	 * Obtiene el plantilla manager.
	 *
	 * @return el plantilla manager
	 */
	public PlantillaManager getPlantillaManager() {
		return plantillaManager;
	}

	/**
	 * Establece el plantilla manager.
	 *
	 * @param plantillaManager el nuevo plantilla manager
	 */
	public void setPlantillaManager(PlantillaManager plantillaManager) {
		this.plantillaManager = plantillaManager;
	}

	/**
	 * Obtiene el comunidades manager.
	 *
	 * @return el comunidades manager
	 */
	public ComunidadesManager getComunidadesManager() {
		return comunidadesManager;
	}

	/**
	 * Establece el comunidades manager.
	 *
	 * @param comunidadesManager el nuevo comunidades manager
	 */
	public void setComunidadesManager(ComunidadesManager comunidadesManager) {
		this.comunidadesManager = comunidadesManager;
	}



	

	
}
