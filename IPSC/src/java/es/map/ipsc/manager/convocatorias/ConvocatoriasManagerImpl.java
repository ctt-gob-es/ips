package es.map.ipsc.manager.convocatorias;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.IteratorUtils;
import org.apache.log4j.Logger;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.CentroGestorDAO;
import es.map.ips.dao.ConvocatoriaDAO;
import es.map.ips.dao.ConvocatoriaEspecialidadCustomDAO;
import es.map.ips.dao.ConvocatoriaMotivoReduccionCustomDAO;
import es.map.ips.dao.ConvocatoriaProvinciaExamenCustomDAO;
import es.map.ips.dao.ConvocatoriaTituloOficialCustomDAO;
import es.map.ips.dao.ConvocatoriasAbiertasViewDAO;
import es.map.ips.dao.ConvocatoriasSubsanarViewDAO;
import es.map.ips.dao.CuerpoEscalaDAO;
import es.map.ips.dao.DiscapacidadDAO;
import es.map.ips.dao.EspecialidadDAO;
import es.map.ips.dao.FormaAccesoDAO;
import es.map.ips.dao.GrupoDAO;
import es.map.ips.dao.MinisterioDAO;
import es.map.ips.dao.MotivoReduccionTasaDAO;
import es.map.ips.dao.OtrosTitulosDAO;
import es.map.ips.dao.ProvinciaExamenDAO;
import es.map.ips.dao.TituloOficialDAO;
import es.map.ips.model.CamposPropios;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.ConvocatoriasAbiertasView;
import es.map.ips.model.ConvocatoriasAbiertasViewQuery;
import es.map.ips.model.ConvocatoriasSubsanarView;
import es.map.ips.model.ConvocatoriasSubsanarViewQuery;
import es.map.ips.model.DatosPropiosConfiguracion;
import es.map.ips.model.DatosPropiosConfiguracionQuery;
import es.map.ips.model.Discapacidad;
import es.map.ips.model.DiscapacidadQuery;
import es.map.ips.model.Especialidad;
import es.map.ips.model.EspecialidadQuery;
import es.map.ips.model.Modelo;
import es.map.ips.model.MotivoReduccionTasa;
import es.map.ips.model.MotivoReduccionTasaQuery;
import es.map.ips.model.OtrosTitulos;
import es.map.ips.model.OtrosTitulosQuery;
import es.map.ips.model.PlantillaPropios;
import es.map.ips.model.PlantillaPropiosQuery;
import es.map.ips.model.ProvinciaExamen;
import es.map.ips.model.ProvinciaExamenQuery;
import es.map.ips.model.TituloOficial;
import es.map.ips.model.TituloOficialQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.BuscarConvocatoriasBean;
import es.map.ipsc.bean.CamposPropiosBean;
import es.map.ipsc.bean.ConvocatoriaBean;
import es.map.ipsc.bean.ModeloBean;
import es.map.ipsc.bean.PlantillaPropiosBean;
import es.map.ipsc.manager.datosPropiosConfiguracion.DatosPropiosConfiguracionManager;
import es.map.ipsc.manager.plantilla.PlantillaPropiosManager;


/**
 * El Class ConvocatoriasManagerImpl.
 */
public class ConvocatoriasManagerImpl implements ConvocatoriasManager {
	
	/** el convocatoria DAO. */
	private ConvocatoriaDAO convocatoriaDAO;
	
	/** el convocatoria provincia examen DAO. */
	private ConvocatoriaProvinciaExamenCustomDAO convocatoriaProvinciaExamenDAO;
	
	/** el convocatorias abiertas view DAO. */
	private ConvocatoriasAbiertasViewDAO convocatoriasAbiertasViewDAO;
	
	/** el convocatorias subsanar view DAO. */
	private ConvocatoriasSubsanarViewDAO convocatoriasSubsanarViewDAO;
	
	/** el convocatoria especialidad DAO. */
	private ConvocatoriaEspecialidadCustomDAO convocatoriaEspecialidadDAO;
	
	/** el convocatoria titulo oficial DAO. */
	private ConvocatoriaTituloOficialCustomDAO convocatoriaTituloOficialDAO;
	
	/** el convocatoria motivo reduccion DAO. */
	private ConvocatoriaMotivoReduccionCustomDAO convocatoriaMotivoReduccionDAO;
	
	/** el motivo reduccion tasa DAO. */
	private MotivoReduccionTasaDAO motivoReduccionTasaDAO;
	
	/** el provincia examen DAO. */
	private ProvinciaExamenDAO provinciaExamenDAO;
	
	/** el titulo oficial DAO. */
	private TituloOficialDAO tituloOficialDAO; 
	
	/** el especialidad DAO. */
	private EspecialidadDAO especialidadDAO;
	
	/** el ministerio DAO. */
	private MinisterioDAO ministerioDAO;
	
	/** el centro gestor DAO. */
	private CentroGestorDAO centroGestorDAO;
	
	/** el cuerpo escala DAO. */
	private CuerpoEscalaDAO cuerpoEscalaDAO;
	
	/** el otros titulos DAO. */
	private OtrosTitulosDAO otrosTitulosDAO;
	
	/** el discapacidad DAO. */
	private DiscapacidadDAO discapacidadDAO;
	
	/** el forma acceso DAO. */
	private FormaAccesoDAO formaAccesoDAO;		
	
	/** el grupo DAO. */
	private GrupoDAO grupoDAO; 
	
	/** el plantilla propios manager. */
	private PlantillaPropiosManager plantillaPropiosManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ConvocatoriasManagerImpl.class);
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.convocatorias.ConvocatoriasManager#recuperarConvocatoria(es.map.ips.model.ConvocatoriaQuery)
	 */
	public ConvocatoriaBean recuperarConvocatoria(ConvocatoriaQuery convocatoriaQuery) {
		SearchResult<Convocatoria> lista= convocatoriaDAO.search(convocatoriaQuery);
		ArrayList<ConvocatoriaBean> arrConvocatorias = new ArrayList<ConvocatoriaBean>();
		for(int i=0;i<lista.getResults().size();i++){
			ConvocatoriaBean aux;
			aux = toConvocatoriaBean(lista.getResults().get(i));
			if(aux != null){
				arrConvocatorias.add(aux);
			}
		}
		return arrConvocatorias.get(0);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.convocatorias.ConvocatoriasManager#recuperarConvocatoria(long)
	 */
	public ConvocatoriaBean recuperarConvocatoria(long codigoConvocatoria) {
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(codigoConvocatoria);	
		SearchResult<Convocatoria> lista= convocatoriaDAO.search(convocatoriaQuery);
		ArrayList<ConvocatoriaBean> arrConvocatorias = new ArrayList<ConvocatoriaBean>();
		if(lista == null){
			return null;
		}
		for(int i=0;i<lista.getResults().size();i++){
			ConvocatoriaBean aux;
			aux = toConvocatoriaBean(lista.getResults().get(i));
			if(aux != null){
				arrConvocatorias.add(aux);
			}
		}
		if(arrConvocatorias.size() <= 0){
			return null;
		}
		
		PlantillaPropiosQuery plantillaQuery = new PlantillaPropiosQuery();
		
		plantillaQuery.setConvocatoria(convocatoriaQuery);
		plantillaQuery.addOrder(PlantillaPropiosQuery.CAMPOSPROPIOS,OrderType.ASC);
		List<PlantillaPropiosBean> plantilla = plantillaPropiosManager.buscarPlantillaPropios(plantillaQuery);
		
		arrConvocatorias.get(0).setPlantillaPropios(plantilla);
		
		return arrConvocatorias.get(0);
	}
	
	
	/**
	 * To convocatoria bean.
	 *
	 * @param convocatoria el convocatoria
	 * @return el convocatoria bean
	 */
	public ConvocatoriaBean toConvocatoriaBean(Convocatoria convocatoria) {
		ConvocatoriaBean convocatoriaBean = new ConvocatoriaBean();

		convocatoriaBean.setId(convocatoria.getId());
		convocatoriaBean.setEnlace(convocatoria.getEnlace());
		convocatoriaBean.setEjercicio(convocatoria.getEjercicio());
		convocatoriaBean.setFechaBoe(convocatoria.getFechaBoe());
		convocatoriaBean.setFechaInicio(convocatoria.getFechaInicio());
		convocatoriaBean.setFechaFin(convocatoria.getFechaFin());
		convocatoriaBean.setFechaIniSub(convocatoria.getFechaIniSubsanacion());
		convocatoriaBean.setFechaFinSub(convocatoria.getFechaFinSubsanacion());
		convocatoriaBean.setImporte(convocatoria.getImporte());
		convocatoriaBean.setNPlazasTotal(convocatoria.getNPlazasTotal());
		convocatoriaBean.setNPlazasDiscapacitados(convocatoria
				.getNPlazasDiscapacitados());	
		convocatoriaBean.setTipoDocumentoPermitido(convocatoria
				.getTipoDocumentoPermitido());		
		convocatoriaBean.setDirigidoA(convocatoria.getDirigidoA());
		convocatoriaBean.setFechaNacimientoMinima(convocatoria
				.getFechaNacimientoMinima());
		convocatoriaBean.setFechaNacimientoMaxima(convocatoria
				.getFechaNacimientoMaxima());
		convocatoriaBean.setDesCentroGestor(convocatoria.getCuerpoEscala().getCentroGestor().getDescripcion());
		convocatoriaBean.setCodCentroGestor(convocatoria.getCuerpoEscala().getCentroGestor().getCodigo());
		convocatoriaBean.setCodCuerpoEscala(convocatoria.getCuerpoEscala().getCodigo());
		convocatoriaBean.setDesCuerpoEscala(convocatoria.getCuerpoEscala().getDescripcion());
		convocatoriaBean.setDesFormaAcceso(convocatoria.getFormaAcceso().getDescripcion());
		convocatoriaBean.setDesMinisterio(convocatoria.getCuerpoEscala().getCentroGestor().getMinisterio().getDescripcion());
		convocatoriaBean.setOcultarDatosPropios(convocatoria.getOcultarDatosPropios());
		
		//Si no tenemos el ministerio convocante en la base de datos, ponemos por defecto el del centro gestor
		if(convocatoria.getMinisterioConvocante()==null){
		convocatoriaBean.setIdMinisterioConvocante(String.valueOf(convocatoria.getCuerpoEscala().getCentroGestor().getMinisterio().getId()));
		convocatoriaBean.setDesMinisterioConvocante(convocatoria.getCuerpoEscala().getCentroGestor().getMinisterio().getDescripcion());
		}else{
		// Si tenemos el ministerio convocante en base de datos, lo asignamos directamente
		convocatoriaBean.setIdMinisterioConvocante(convocatoria.getMinisterioConvocante().getId().toString());
		convocatoriaBean.setDesMinisterioConvocante(convocatoria.getMinisterioConvocante().getDescripcion());
		}
		
		convocatoriaBean.setIdCentroGestor(String.valueOf(convocatoria.getCuerpoEscala().getCentroGestor().getIdCentroGestor()));
		convocatoriaBean.setSiglasCentroGestor(String.valueOf(convocatoria.getCuerpoEscala().getCentroGestor().getSiglas()));
		convocatoriaBean.setIdCuerpoEscala(String.valueOf(convocatoria.getCuerpoEscala().getId()));
		convocatoriaBean.setIdFormaAcceso(String.valueOf(convocatoria.getFormaAcceso().getId()));
		convocatoriaBean.setIdMinisterio(String.valueOf(convocatoria.getCuerpoEscala().getCentroGestor().getMinisterio().getId()));
		convocatoriaBean.setCuerpoEscala(convocatoria.getCuerpoEscala());
		convocatoriaBean.setPlantilla(convocatoria.getPlantilla());
		convocatoriaBean.setIdPlantilla(convocatoria.getPlantilla().getId());
		convocatoriaBean.setCodFormaAcceso(convocatoria.getFormaAcceso().getCodigo());
		convocatoriaBean.setIdEstado(convocatoria.getEstadoConvocatoria().getId());
		convocatoriaBean.setCodNuevoFormaAcceso(convocatoria.getCodigoFormaAcceso());

		convocatoriaBean.setDocumentos(convocatoria.getDocumentos());
		if(convocatoria.getTituloOficials() != null && convocatoria.getTituloOficials().size()>0){
			// se recuperan los titulos 	
			SearchResult<TituloOficial> resultado = convocatoriaTituloOficialDAO.searchTituloOficialPorConvocatoria(convocatoria.getId());
			convocatoriaBean.setTituloOficials(resultado.getResults());
			convocatoriaBean.getTituloOficials().toArray();
		}
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(convocatoria.getId());
		MotivoReduccionTasaQuery motivoReduccionTasaQuery = new MotivoReduccionTasaQuery();
		SearchResult<MotivoReduccionTasa> motivos = convocatoriaMotivoReduccionDAO.searchMotivoReduccionPorConvocatoria(motivoReduccionTasaQuery, convocatoriaQuery);
		
		if(convocatoria.getMotivoReduccionTasas()!= null && convocatoria.getMotivoReduccionTasas().size()>0){
			convocatoriaBean.setMotivoReduccionTasas(convocatoria.getMotivoReduccionTasas());
			ArrayList<MotivoReduccionTasa> arrayTasas = new ArrayList<MotivoReduccionTasa>();
			ArrayList<MotivoReduccionTasa> arrayTasasIncompleto = new ArrayList<MotivoReduccionTasa>();
			Iterator it = motivos.getResults().iterator();
			while(it.hasNext()){
				Integer motivoAux = ((MotivoReduccionTasa)it.next()).getId();
				try{
					motivoReduccionTasaQuery.setId(motivoAux);
					MotivoReduccionTasa motivoTasaAux = motivoReduccionTasaDAO.searchUnique(motivoReduccionTasaQuery);
					if(motivoTasaAux != null && motivoTasaAux.getPorcentajeDescuento() == Constantes.MOTIVOTASA_PORCENTAJE_COMPLETO){
						arrayTasas.add(motivoTasaAux);
					}else{
						arrayTasasIncompleto.add(motivoTasaAux);
					}
				}catch(Exception e){
					logger.error("Error motivo reduccion tasas:",e );
				}
			}
			convocatoriaBean.setMotivoReduccionTasasCompleto(arrayTasas);
			convocatoriaBean.setMotivoReduccionTasasIncompleto(arrayTasasIncompleto);
				

		}
		
		if(convocatoria.getEspecialidads()!= null && convocatoria.getEspecialidads().size()>0){
			// se recuperan las provincias ordenadas alfabeticamente
			Iterator<Especialidad>  it = convocatoria.getEspecialidads().iterator();
			Integer idEspecialidad = 0;

			EspecialidadQuery espQuery = new EspecialidadQuery();
			while(it.hasNext()){
				Especialidad tituloAux =it.next();
				idEspecialidad = tituloAux.getId();
				espQuery.addIdIn(idEspecialidad);
			}		
			espQuery.addOrder(EspecialidadQuery.DESCRIPCION,OrderType.ASC);
			SearchResult<Especialidad> resultado = especialidadDAO.search(espQuery);
			convocatoriaBean.setEspecialidads(resultado.getResults());
		}
		
		if(convocatoria.getProvinciasExamen() != null && convocatoria.getProvinciasExamen().size()>0){
			// se recuperan las provincias ordenadas alfabeticamente
			Iterator<ProvinciaExamen>  it = convocatoria.getProvinciasExamen().iterator();
			Integer idProncincia = 0;

			ProvinciaExamenQuery provQuery = new ProvinciaExamenQuery();
			while(it.hasNext()){
				ProvinciaExamen tituloAux =it.next();
				idProncincia = tituloAux.getId();
				provQuery.addIdIn(idProncincia);
			}		
			provQuery.addOrder(ProvinciaExamenQuery.DESCRIPCION,OrderType.ASC);
			SearchResult<ProvinciaExamen> resultado = provinciaExamenDAO.search(provQuery);
			convocatoriaBean.setProvinciasExamen(resultado.getResults());
			convocatoriaBean.getProvinciasExamen().toArray();
		}
		
		//Otros Titulos
		if(convocatoria.getOtrosTitulos() != null && convocatoria.getOtrosTitulos().size()>0){
			// se recuperan las otrosTitulos ordenadas alfabeticamente
			Iterator<OtrosTitulos>  it = convocatoria.getOtrosTitulos().iterator();
			Integer idOtroTitulo = 0;

			OtrosTitulosQuery otrosTitQuery = new OtrosTitulosQuery();
			while(it.hasNext()){
				OtrosTitulos tituloAux =it.next();
				idOtroTitulo = tituloAux.getIdOtroTitulo();
				otrosTitQuery.addIdIn(idOtroTitulo);
			}		
			otrosTitQuery.addOrder(OtrosTitulosQuery.DESCRIPCION,OrderType.ASC);
			SearchResult<OtrosTitulos> resultado = otrosTitulosDAO.search(otrosTitQuery);
			convocatoriaBean.setOtrosTitulos(resultado.getResults());
			convocatoriaBean.getOtrosTitulos().toArray();
		}
		
		//Discapacidad
		if(convocatoria.getDiscapacidad() != null && convocatoria.getDiscapacidad().size()>0){
			// se recuperan las discapacidad ordenadas alfabeticamente
			Iterator<Discapacidad>  it = convocatoria.getDiscapacidad().iterator();
			Integer idOtroTitulo = 0;

			DiscapacidadQuery discaQuery = new DiscapacidadQuery();
			while(it.hasNext()){
				Discapacidad tituloAux =it.next();
				idOtroTitulo = tituloAux.getIdDiscapacidad();
				discaQuery.addIdIn(idOtroTitulo);
			}		
			discaQuery.addOrder(DiscapacidadQuery.DESCRIPCION,OrderType.ASC);
			SearchResult<Discapacidad> resultado = discapacidadDAO.search(discaQuery);
			convocatoriaBean.setDiscapacidad(resultado.getResults());
			convocatoriaBean.getDiscapacidad().toArray();
		}
		
		//Datos Propios
		if(convocatoria.getDatosPropiosConfiguracion() != null && convocatoria.getDatosPropiosConfiguracion().size()>0){
			convocatoriaBean.setDatosPropiosConfiguracion(IteratorUtils.toList(convocatoria.getDatosPropiosConfiguracion().iterator()));
		}
		
		convocatoriaBean.setCargaSolicitudExternos(convocatoria
				.getCargaSolicitudExternos());

		if(convocatoria.getModelo()!=null && convocatoria.getModelo().getIdModelo()!=null){
			convocatoriaBean.setModelo(convocatoria.getModelo().getIdModelo().toString());
		}
		if(convocatoria.getModelo()!=null && convocatoria.getModelo().getNumModelo()!=null){
			convocatoriaBean.setNumModelo(convocatoria.getModelo().getNumModelo());
		}
		
		return convocatoriaBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.convocatorias.ConvocatoriasManager#buscarConvocatoriaId(es.map.ips.model.ConvocatoriaQuery)
	 */
	public ConvocatoriaBean buscarConvocatoriaId(
			ConvocatoriaQuery convocatoriaQuery) {
		Convocatoria convocatoriaAux = convocatoriaDAO.searchUnique(convocatoriaQuery);
		ConvocatoriaBean convocatoriaBean = toConvocatoriaBean(convocatoriaAux);
		return convocatoriaBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.convocatorias.ConvocatoriasManager#recuperarNumConvocatoriaAbiertas(es.map.ips.model.ConvocatoriasAbiertasViewQuery)
	 */
	public Integer recuperarNumConvocatoriaAbiertas(ConvocatoriasAbiertasViewQuery convocatoriaQuery) {
		int[] cuerpoEscalaAux = new int[convocatoriaQuery.getIdCuerpoEscalaIn().size()];
		if(convocatoriaQuery.getIdCuerpoEscalaIn().size() > 0 && convocatoriaQuery.getIdCentroGestorIn().size() > 0) {
			for(int i = 0; i < convocatoriaQuery.getIdCuerpoEscalaIn().size(); i++) {
				cuerpoEscalaAux[i] = convocatoriaQuery.getIdCuerpoEscalaIn().get(i);
			}
			convocatoriaQuery.getIdCuerpoEscalaIn().removeAll(convocatoriaQuery.getIdCuerpoEscalaIn());
		}
		SearchResult<ConvocatoriasAbiertasView> convocatorias = convocatoriasAbiertasViewDAO.search(convocatoriaQuery);
		Integer numConvocatoriasAbiertas = convocatoriasAbiertasViewDAO.obtenerNumConvocatoriaAbiertas(convocatoriaQuery);

		return numConvocatoriasAbiertas;
		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.convocatorias.ConvocatoriasManager#recuperarNumConvocatoriaSubsanadas(es.map.ips.model.ConvocatoriasSubsanarViewQuery)
	 */
	public Integer recuperarNumConvocatoriaSubsanadas(ConvocatoriasSubsanarViewQuery convocatoriaQuery) {
		int[] cuerpoEscalaAux = new int[convocatoriaQuery.getIdCuerpoEscalaIn().size()];
		if(convocatoriaQuery.getIdCuerpoEscalaIn().size() > 0 && convocatoriaQuery.getIdCentroGestorIn().size() > 0) {
			for(int i = 0; i < convocatoriaQuery.getIdCuerpoEscalaIn().size(); i++) {
				cuerpoEscalaAux[i] = convocatoriaQuery.getIdCuerpoEscalaIn().get(i);
			}
			convocatoriaQuery.getIdCuerpoEscalaIn().removeAll(convocatoriaQuery.getIdCuerpoEscalaIn());
		}
		SearchResult<ConvocatoriasSubsanarView> convocatorias = convocatoriasSubsanarViewDAO.search(convocatoriaQuery);
		Integer numConvocatoriasAbiertas = convocatoriasSubsanarViewDAO.obtenerNumConvocatoriaSubsanadas(convocatoriaQuery);

		return numConvocatoriasAbiertas;
		
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.convocatorias.ConvocatoriasManager#buscarConvocatoriasView(es.map.ips.model.ConvocatoriasAbiertasViewQuery)
	 */
	public List<BuscarConvocatoriasBean> buscarConvocatoriasView(
			ConvocatoriasAbiertasViewQuery convocatoriaQuery) {
			boolean cuerpoEscala = false;
			int[] cuerpoEscalaAux = new int[convocatoriaQuery.getIdCuerpoEscalaIn().size()];
			if(convocatoriaQuery.getIdCuerpoEscalaIn().size() > 0 && convocatoriaQuery.getIdCentroGestorIn().size() > 0) {
				for(int i = 0; i < convocatoriaQuery.getIdCuerpoEscalaIn().size(); i++) {
					cuerpoEscalaAux[i] = convocatoriaQuery.getIdCuerpoEscalaIn().get(i);
				}
				convocatoriaQuery.getIdCuerpoEscalaIn().removeAll(convocatoriaQuery.getIdCuerpoEscalaIn());
				cuerpoEscala = true;
			}
			SearchResult<ConvocatoriasAbiertasView> convocatorias = 
				convocatoriasAbiertasViewDAO.search(convocatoriaQuery);
			ArrayList<BuscarConvocatoriasBean> arrConvocatoriasView = new ArrayList<BuscarConvocatoriasBean>();
			int numTotal = 0;
			if (convocatorias.getNumResults() != null) {
				numTotal = convocatorias.getNumResults();
			}
			
			if(cuerpoEscala) {
				convocatoriaQuery.getIdCentroGestorIn().removeAll(convocatoriaQuery.getIdCentroGestorIn());
				for(int i = 0; i < cuerpoEscalaAux.length; i++) {
					convocatoriaQuery.getIdCuerpoEscalaIn().add(cuerpoEscalaAux[i]);
				}
				SearchResult<ConvocatoriasAbiertasView> convocatorias2 = 
						convocatoriasAbiertasViewDAO.search(convocatoriaQuery);
				if (convocatorias2.getNumResults() != null) {
					numTotal += convocatorias2.getNumResults();
				}
			
				//arrConvocatorias para centros gestores
				for(int i=0;i<convocatorias.getResults().size();i++){
					BuscarConvocatoriasBean aux;
					aux = toConvocatoriaBean(convocatorias.getResults().get(i),numTotal);
					if(aux != null){
						arrConvocatoriasView.add(aux);
					}
				}
				//Para cuerpos o escalas
				for(int i=0;i<convocatorias2.getResults().size();i++){
					BuscarConvocatoriasBean aux;
					aux = toConvocatoriaBean(convocatorias2.getResults().get(i),numTotal);
					boolean find = !arrConvocatoriasView.contains(aux);
					if(aux != null && !find){
						arrConvocatoriasView.add(aux);
					}else {
						numTotal--;
					}
				}
			}else {
				for(int i=0;i<convocatorias.getResults().size();i++){
					BuscarConvocatoriasBean aux;
					aux = toConvocatoriaBean(convocatorias.getResults().get(i),numTotal);
					if(aux != null){
						arrConvocatoriasView.add(aux);
					}
				}
			}
			
			logger.info("TamañoListaManager: "+numTotal);
			return arrConvocatoriasView;
			
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.convocatorias.ConvocatoriasManager#buscarConvocatoriasSubsanadasView(es.map.ips.model.ConvocatoriasSubsanarViewQuery)
	 */
	@Override
	public List<BuscarConvocatoriasBean> buscarConvocatoriasSubsanadasView(ConvocatoriasSubsanarViewQuery convocatoriaQuery) {
			boolean cuerpoEscala = false;
			int[] cuerpoEscalaAux = new int[convocatoriaQuery.getIdCuerpoEscalaIn().size()];
			if(convocatoriaQuery.getIdCuerpoEscalaIn().size() > 0 && convocatoriaQuery.getIdCentroGestorIn().size() > 0) {
				for(int i = 0; i < convocatoriaQuery.getIdCuerpoEscalaIn().size(); i++) {
					cuerpoEscalaAux[i] = convocatoriaQuery.getIdCuerpoEscalaIn().get(i);
				}
				convocatoriaQuery.getIdCuerpoEscalaIn().removeAll(convocatoriaQuery.getIdCuerpoEscalaIn());
				cuerpoEscala = true;
			}
			SearchResult<ConvocatoriasSubsanarView> convocatorias = convocatoriasSubsanarViewDAO.search(convocatoriaQuery);
			ArrayList<BuscarConvocatoriasBean> arrConvocatoriasView = new ArrayList<BuscarConvocatoriasBean>();
			int numTotal = 0;
			if (convocatorias.getNumResults() != null) {
				numTotal = convocatorias.getNumResults();
			}
			
			if(cuerpoEscala) {
				convocatoriaQuery.getIdCentroGestorIn().removeAll(convocatoriaQuery.getIdCentroGestorIn());
				for(int i = 0; i < cuerpoEscalaAux.length; i++) {
					convocatoriaQuery.getIdCuerpoEscalaIn().add(cuerpoEscalaAux[i]);
				}
				SearchResult<ConvocatoriasSubsanarView> convocatorias2 = convocatoriasSubsanarViewDAO.search(convocatoriaQuery);
				if (convocatorias2.getNumResults() != null) {
					numTotal += convocatorias2.getNumResults();
				}
			
				//arrConvocatorias para centros gestores
				for(int i=0;i<convocatorias.getResults().size();i++){
					BuscarConvocatoriasBean aux;
					aux = toConvocatoriaBean(convocatorias.getResults().get(i),numTotal);
					if(aux != null){
						arrConvocatoriasView.add(aux);
					}
				}
				//Para cuerpos o escalas
				for(int i=0;i<convocatorias2.getResults().size();i++){
					BuscarConvocatoriasBean aux;
					aux = toConvocatoriaBean(convocatorias2.getResults().get(i),numTotal);
					boolean find = !arrConvocatoriasView.contains(aux);
					if(aux != null && !find){
						arrConvocatoriasView.add(aux);
					}else {
						numTotal--;
					}
				}
			}else {
				for(int i=0;i<convocatorias.getResults().size();i++){
					BuscarConvocatoriasBean aux;
					aux = toConvocatoriaBean(convocatorias.getResults().get(i),numTotal);
					if(aux != null){
						arrConvocatoriasView.add(aux);
					}
				}
			}
			
			logger.info("TamañoListaManager: "+numTotal);
			return arrConvocatoriasView;
			
	}	
	
	/**
	 * To plantilla propios bean.
	 *
	 * @param plantillaPropios el plantilla propios
	 * @return el plantilla propios bean
	 */
	public PlantillaPropiosBean toPlantillaPropiosBean(PlantillaPropios plantillaPropios){
		PlantillaPropiosBean plantillaPropiosBean = new PlantillaPropiosBean();
		
		plantillaPropiosBean.setId(Long.toString(plantillaPropios.getIdPlantillaPropios()));
		plantillaPropiosBean.setModeloBean(toModeloBean(plantillaPropios.getModelo()));
		plantillaPropiosBean.setTipoPlantilla(plantillaPropios.getTipoPlantilla());
		if(plantillaPropios.getObligatorio()!=null && "S".equals(plantillaPropios.getObligatorio())){
			plantillaPropiosBean.setObligatorio(true);
		}else{
			plantillaPropiosBean.setObligatorio(false);
		}
		
		plantillaPropiosBean.setCampoPropioBean(toCamposPropiosBean(plantillaPropios.getCamposPropios()));
		plantillaPropiosBean.setTipoPlantilla('G');
			
		return plantillaPropiosBean;
	}
	
	/**
	 * To modelo bean.
	 *
	 * @param modelo el modelo
	 * @return el modelo bean
	 */
	private ModeloBean toModeloBean(Modelo modelo) {		
		
		ModeloBean auxModelo = new ModeloBean();
		auxModelo.setId(modelo.getIdModelo());
		auxModelo.setDescripcion(modelo.getDescripcion());
		auxModelo.setNumModelo(modelo.getNumModelo());
		auxModelo.setCamposPropios(modelo.getCamposPropioses());		
		auxModelo.setCentrosGestores(modelo.getCentroGestors());
		auxModelo.setFechaAlta(modelo.getFechaAlta());
		auxModelo.setSolicitudComun(modelo.getSolicitudComuns());
		
		if(modelo.getCentroGestors() != null){
			auxModelo.setCentrosGestores(modelo.getCentroGestors());
		}

		return auxModelo;
	}

	/**
	 * To campos propios bean.
	 *
	 * @param camposPropios el campos propios
	 * @return el campos propios bean
	 */
	private CamposPropiosBean toCamposPropiosBean(CamposPropios camposPropios) {					
		CamposPropiosBean auxCamposPropios = new CamposPropiosBean();
		auxCamposPropios.setDescripcion(camposPropios.getDescripcion());
		auxCamposPropios.setIdModelo(Integer.toString(camposPropios.getModelo().getIdModelo()));
		auxCamposPropios.setCampo(Integer.toString(camposPropios.getIdCampo()));
		auxCamposPropios.setTituloCampo(camposPropios.getCampo());
		return auxCamposPropios;
	}
	
	
	/**
	 * To convocatoria bean.
	 *
	 * @param convocatoriasAbiertasView el convocatorias abiertas view
	 * @param numTotal el num total
	 * @return el buscar convocatorias bean
	 */
	private BuscarConvocatoriasBean toConvocatoriaBean(
			ConvocatoriasAbiertasView convocatoriasAbiertasView, int numTotal) {
		SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");
		BuscarConvocatoriasBean aux = new BuscarConvocatoriasBean();
		aux.setId(convocatoriasAbiertasView.getIdConvocatoria());
		// Se muestran sólo campos visibles
	
		aux.setMinisterio(convocatoriasAbiertasView.getDesMinisterio());
		aux.setIdMinisterio(convocatoriasAbiertasView.getIdMinisterio());

		aux.setCentro(convocatoriasAbiertasView.getDesCentroGestor());		
		aux.setCuerpo(convocatoriasAbiertasView.getDesCuerpoEscala());
		aux.setSiglasCentroGestor(convocatoriasAbiertasView.getSiglasCentroGestor());
		
		aux.setCodigoCuerpo(convocatoriasAbiertasView.getCodCuerpoEscala());
		
		if(convocatoriasAbiertasView.getFechaTermino() != null){
			aux.setFecha(formatoFecha.format(convocatoriasAbiertasView.getFechaTermino()));
		}
		
	
		aux.setFormaAcceso(convocatoriasAbiertasView.getDesFormaAcceso());	
	
	
		aux.setGrupo(convocatoriasAbiertasView.getDesGrupo());	
		aux.setNumTotal(numTotal);
		aux.setPresencial(convocatoriasAbiertasView.getPresencial());
		
		return aux;
	}
	
	/**
	 * To convocatoria bean.
	 *
	 * @param convocatoriasSubsanarView el convocatorias subsanar view
	 * @param numTotal el num total
	 * @return el buscar convocatorias bean
	 */
	private BuscarConvocatoriasBean toConvocatoriaBean(
			ConvocatoriasSubsanarView convocatoriasSubsanarView, int numTotal) {
		SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");
		BuscarConvocatoriasBean aux = new BuscarConvocatoriasBean();
		aux.setId(convocatoriasSubsanarView.getIdConvocatoria());
		// Se muestran sólo campos visibles
	
		aux.setMinisterio(convocatoriasSubsanarView.getDesMinisterio());
		aux.setIdMinisterio(convocatoriasSubsanarView.getIdMinisterio());

		aux.setCentro(convocatoriasSubsanarView.getDesCentroGestor());		
		aux.setCuerpo(convocatoriasSubsanarView.getDesCuerpoEscala());
		aux.setSiglasCentroGestor(convocatoriasSubsanarView.getSiglasCentroGestor());
		
		aux.setCodigoCuerpo(convocatoriasSubsanarView.getCodCuerpoEscala());
		
		if(convocatoriasSubsanarView.getFechaTermino() != null){
			aux.setFecha(formatoFecha.format(convocatoriasSubsanarView.getFechaTermino()));
		}
		
	
		aux.setFormaAcceso(convocatoriasSubsanarView.getDesFormaAcceso());	
	
	
		aux.setGrupo(convocatoriasSubsanarView.getDesGrupo());	
		aux.setNumTotal(numTotal);
		aux.setPresencial(convocatoriasSubsanarView.getPresencial());
		
		return aux;
	}	
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.convocatorias.ConvocatoriasManager#buscarProvinciasFiltrado(es.map.ips.model.ConvocatoriaQuery, es.map.ips.model.ProvinciaExamenQuery)
	 */
	public ArrayList<Long> buscarProvinciasFiltrado(
			ConvocatoriaQuery convocatoriaQuery, ProvinciaExamenQuery provinciaQuery) {
		
		SearchResult<Convocatoria> provincias = convocatoriaProvinciaExamenDAO.searchConvocatoriaPorProvinciaExamen(provinciaQuery,
				convocatoriaQuery);
		ArrayList<Long> idConvocatoria = new ArrayList<Long>();
		for(int i=0;i<provincias.size();i++){
			idConvocatoria.add(provincias.getResults().get(i).getId());
		}
		return idConvocatoria;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.convocatorias.ConvocatoriasManager#buscarEspecialidadFiltrado(es.map.ips.model.ConvocatoriaQuery, es.map.ips.model.EspecialidadQuery)
	 */
	public ArrayList<Long> buscarEspecialidadFiltrado(
			ConvocatoriaQuery convocatoriaQuery,EspecialidadQuery especialidadQuery) {
		
		SearchResult<Convocatoria> especialidades = convocatoriaEspecialidadDAO.searchConvocatoriaPorEspecialidad(especialidadQuery,
				convocatoriaQuery);
		ArrayList<Long> idConvocatoria = new ArrayList<Long>();
		for(int i=0;i<especialidades.size();i++){
			idConvocatoria.add(especialidades.getResults().get(i).getId());
		}
		return idConvocatoria;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.convocatorias.ConvocatoriasManager#buscarTitulosFiltrado(es.map.ips.model.ConvocatoriaQuery, es.map.ips.model.TituloOficialQuery)
	 */
	public ArrayList<Long> buscarTitulosFiltrado(
			ConvocatoriaQuery convocatoriaQuery,TituloOficialQuery tituloOficialQuery) {

		SearchResult<Convocatoria> titulos = convocatoriaTituloOficialDAO.searchConvocatoriaPorTituloOficial(tituloOficialQuery,
				convocatoriaQuery);
		ArrayList<Long> idConvocatoria = new ArrayList<Long>();
		for(int i=0;i<titulos.size();i++){
			idConvocatoria.add(titulos.getResults().get(i).getId());
		}
		return idConvocatoria;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.convocatorias.ConvocatoriasManager#buscarConvocatoriaIdModel(es.map.ips.model.ConvocatoriaQuery)
	 */
	public Convocatoria buscarConvocatoriaIdModel(
			ConvocatoriaQuery convocatoriaQuery) {
		Convocatoria convocatoria;
		convocatoria = convocatoriaDAO.searchUnique(convocatoriaQuery);
		if(convocatoria == null){
			return null;
		}
		return convocatoria;
	}
	

	/**
	 * Obtiene el convocatoria DAO.
	 *
	 * @return el convocatoria DAO
	 */
	public ConvocatoriaDAO getConvocatoriaDAO() {
		return convocatoriaDAO;
	}

	/**
	 * Establece el convocatoria DAO.
	 *
	 * @param convocatoriaDAO el nuevo convocatoria DAO
	 */
	public void setConvocatoriaDAO(ConvocatoriaDAO convocatoriaDAO) {
		this.convocatoriaDAO = convocatoriaDAO;
	}

	/**
	 * Obtiene el convocatorias abiertas view DAO.
	 *
	 * @return el convocatorias abiertas view DAO
	 */
	public ConvocatoriasAbiertasViewDAO getConvocatoriasAbiertasViewDAO() {
		return convocatoriasAbiertasViewDAO;
	}

	/**
	 * Establece el convocatorias abiertas view DAO.
	 *
	 * @param convocatoriasAbiertasViewDAO el nuevo convocatorias abiertas view DAO
	 */
	public void setConvocatoriasAbiertasViewDAO(
			ConvocatoriasAbiertasViewDAO convocatoriasAbiertasViewDAO) {
		this.convocatoriasAbiertasViewDAO = convocatoriasAbiertasViewDAO;
	}

	/**
	 * Obtiene el convocatorias subsanar view DAO.
	 *
	 * @return el convocatorias subsanar view DAO
	 */
	public ConvocatoriasSubsanarViewDAO getConvocatoriasSubsanarViewDAO() {
		return convocatoriasSubsanarViewDAO;
	}

	/**
	 * Establece el convocatorias subsanar view DAO.
	 *
	 * @param convocatoriasSubsanarViewDAO el nuevo convocatorias subsanar view DAO
	 */
	public void setConvocatoriasSubsanarViewDAO(ConvocatoriasSubsanarViewDAO convocatoriasSubsanarViewDAO) {
		this.convocatoriasSubsanarViewDAO = convocatoriasSubsanarViewDAO;
	}

	/**
	 * Obtiene el convocatoria provincia examen DAO.
	 *
	 * @return el convocatoria provincia examen DAO
	 */
	public ConvocatoriaProvinciaExamenCustomDAO getConvocatoriaProvinciaExamenDAO() {
		return convocatoriaProvinciaExamenDAO;
	}

	/**
	 * Establece el convocatoria provincia examen DAO.
	 *
	 * @param convocatoriaProvinciaExamenDAO el nuevo convocatoria provincia examen DAO
	 */
	public void setConvocatoriaProvinciaExamenDAO(
			ConvocatoriaProvinciaExamenCustomDAO convocatoriaProvinciaExamenDAO) {
		this.convocatoriaProvinciaExamenDAO = convocatoriaProvinciaExamenDAO;
	}

	/**
	 * Obtiene el convocatoria especialidad DAO.
	 *
	 * @return el convocatoria especialidad DAO
	 */
	public ConvocatoriaEspecialidadCustomDAO getConvocatoriaEspecialidadDAO() {
		return convocatoriaEspecialidadDAO;
	}

	/**
	 * Establece el convocatoria especialidad DAO.
	 *
	 * @param convocatoriaEspecialidadDAO el nuevo convocatoria especialidad DAO
	 */
	public void setConvocatoriaEspecialidadDAO(
			ConvocatoriaEspecialidadCustomDAO convocatoriaEspecialidadDAO) {
		this.convocatoriaEspecialidadDAO = convocatoriaEspecialidadDAO;
	}

	/**
	 * Obtiene el convocatoria titulo oficial DAO.
	 *
	 * @return el convocatoria titulo oficial DAO
	 */
	public ConvocatoriaTituloOficialCustomDAO getConvocatoriaTituloOficialDAO() {
		return convocatoriaTituloOficialDAO;
	}

	/**
	 * Establece el convocatoria titulo oficial DAO.
	 *
	 * @param convocatoriaTituloOficialDAO el nuevo convocatoria titulo oficial DAO
	 */
	public void setConvocatoriaTituloOficialDAO(
			ConvocatoriaTituloOficialCustomDAO convocatoriaTituloOficialDAO) {
		this.convocatoriaTituloOficialDAO = convocatoriaTituloOficialDAO;
	}

	/**
	 * Obtiene el motivo reduccion tasa DAO.
	 *
	 * @return el motivo reduccion tasa DAO
	 */
	public MotivoReduccionTasaDAO getMotivoReduccionTasaDAO() {
		return motivoReduccionTasaDAO;
	}

	/**
	 * Establece el motivo reduccion tasa DAO.
	 *
	 * @param motivoReduccionTasaDAO el nuevo motivo reduccion tasa DAO
	 */
	public void setMotivoReduccionTasaDAO(
			MotivoReduccionTasaDAO motivoReduccionTasaDAO) {
		this.motivoReduccionTasaDAO = motivoReduccionTasaDAO;
	}

	/**
	 * Obtiene el convocatoria motivo reduccion DAO.
	 *
	 * @return el convocatoria motivo reduccion DAO
	 */
	public ConvocatoriaMotivoReduccionCustomDAO getConvocatoriaMotivoReduccionDAO() {
		return convocatoriaMotivoReduccionDAO;
	}

	/**
	 * Establece el convocatoria motivo reduccion DAO.
	 *
	 * @param convocatoriaMotivoReduccionDAO el nuevo convocatoria motivo reduccion DAO
	 */
	public void setConvocatoriaMotivoReduccionDAO(
			ConvocatoriaMotivoReduccionCustomDAO convocatoriaMotivoReduccionDAO) {
		this.convocatoriaMotivoReduccionDAO = convocatoriaMotivoReduccionDAO;
	}

	/**
	 * Obtiene el provincia examen DAO.
	 *
	 * @return el provincia examen DAO
	 */
	public ProvinciaExamenDAO getProvinciaExamenDAO() {
		return provinciaExamenDAO;
	}

	/**
	 * Establece el provincia examen DAO.
	 *
	 * @param provinciaExamenDAO el nuevo provincia examen DAO
	 */
	public void setProvinciaExamenDAO(ProvinciaExamenDAO provinciaExamenDAO) {
		this.provinciaExamenDAO = provinciaExamenDAO;
	}

	/**
	 * Obtiene el titulo oficial DAO.
	 *
	 * @return el titulo oficial DAO
	 */
	public TituloOficialDAO getTituloOficialDAO() {
		return tituloOficialDAO;
	}

	/**
	 * Establece el titulo oficial DAO.
	 *
	 * @param tituloOficialDAO el nuevo titulo oficial DAO
	 */
	public void setTituloOficialDAO(TituloOficialDAO tituloOficialDAO) {
		this.tituloOficialDAO = tituloOficialDAO;
	}

	/**
	 * Obtiene el especialidad DAO.
	 *
	 * @return el especialidad DAO
	 */
	public EspecialidadDAO getEspecialidadDAO() {
		return especialidadDAO;
	}

	/**
	 * Establece el especialidad DAO.
	 *
	 * @param especialidadDAO el nuevo especialidad DAO
	 */
	public void setEspecialidadDAO(EspecialidadDAO especialidadDAO) {
		this.especialidadDAO = especialidadDAO;
	}

	/**
	 * Obtiene el ministerio DAO.
	 *
	 * @return el ministerio DAO
	 */
	public MinisterioDAO getMinisterioDAO() {
		return ministerioDAO;
	}

	/**
	 * Establece el ministerio DAO.
	 *
	 * @param ministerioDAO el nuevo ministerio DAO
	 */
	public void setMinisterioDAO(MinisterioDAO ministerioDAO) {
		this.ministerioDAO = ministerioDAO;
	}

	/**
	 * Obtiene el centro gestor DAO.
	 *
	 * @return el centro gestor DAO
	 */
	public CentroGestorDAO getCentroGestorDAO() {
		return centroGestorDAO;
	}

	/**
	 * Establece el centro gestor DAO.
	 *
	 * @param centroGestorDAO el nuevo centro gestor DAO
	 */
	public void setCentroGestorDAO(CentroGestorDAO centroGestorDAO) {
		this.centroGestorDAO = centroGestorDAO;
	}

	/**
	 * Obtiene el forma acceso DAO.
	 *
	 * @return el forma acceso DAO
	 */
	public FormaAccesoDAO getFormaAccesoDAO() {
		return formaAccesoDAO;
	}

	/**
	 * Establece el forma acceso DAO.
	 *
	 * @param formaAccesoDAO el nuevo forma acceso DAO
	 */
	public void setFormaAccesoDAO(FormaAccesoDAO formaAccesoDAO) {
		this.formaAccesoDAO = formaAccesoDAO;
	}

	/**
	 * Obtiene el grupo DAO.
	 *
	 * @return el grupo DAO
	 */
	public GrupoDAO getGrupoDAO() {
		return grupoDAO;
	}

	/**
	 * Establece el grupo DAO.
	 *
	 * @param grupoDAO el nuevo grupo DAO
	 */
	public void setGrupoDAO(GrupoDAO grupoDAO) {
		this.grupoDAO = grupoDAO;
	}

	/**
	 * Obtiene el cuerpo escala DAO.
	 *
	 * @return el cuerpo escala DAO
	 */
	public CuerpoEscalaDAO getCuerpoEscalaDAO() {
		return cuerpoEscalaDAO;
	}

	/**
	 * Establece el cuerpo escala DAO.
	 *
	 * @param cuerpoEscalaDAO el nuevo cuerpo escala DAO
	 */
	public void setCuerpoEscalaDAO(CuerpoEscalaDAO cuerpoEscalaDAO) {
		this.cuerpoEscalaDAO = cuerpoEscalaDAO;
	}

	/**
	 * Obtiene el plantilla propios manager.
	 *
	 * @return el plantilla propios manager
	 */
	public PlantillaPropiosManager getPlantillaPropiosManager() {
		return plantillaPropiosManager;
	}

	/**
	 * Establece el plantilla propios manager.
	 *
	 * @param plantillaPropiosManager el nuevo plantilla propios manager
	 */
	public void setPlantillaPropiosManager(
			PlantillaPropiosManager plantillaPropiosManager) {
		this.plantillaPropiosManager = plantillaPropiosManager;
	}

	/**
	 * Obtiene el otros titulos DAO.
	 *
	 * @return el otros titulos DAO
	 */
	public OtrosTitulosDAO getOtrosTitulosDAO() {
		return otrosTitulosDAO;
	}

	/**
	 * Establece el otros titulos DAO.
	 *
	 * @param otrosTitulosDAO el nuevo otros titulos DAO
	 */
	public void setOtrosTitulosDAO(OtrosTitulosDAO otrosTitulosDAO) {
		this.otrosTitulosDAO = otrosTitulosDAO;
	}

	/**
	 * Obtiene el discapacidad DAO.
	 *
	 * @return el discapacidad DAO
	 */
	public DiscapacidadDAO getDiscapacidadDAO() {
		return discapacidadDAO;
	}

	/**
	 * Establece el discapacidad DAO.
	 *
	 * @param discapacidadDAO el nuevo discapacidad DAO
	 */
	public void setDiscapacidadDAO(DiscapacidadDAO discapacidadDAO) {
		this.discapacidadDAO = discapacidadDAO;
	}

}
