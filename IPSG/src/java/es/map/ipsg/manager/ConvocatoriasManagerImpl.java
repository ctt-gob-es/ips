package es.map.ipsg.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.CamposPropiosDAO;
import es.map.ips.dao.CentroGestorDAO;
import es.map.ips.dao.ConvocatoriaDAO;
import es.map.ips.dao.ConvocatoriaMotivoReduccionCustomDAO;
import es.map.ips.dao.CuerpoEscalaDAO;
import es.map.ips.dao.DatosPropiosConfiguracionDAO;
import es.map.ips.dao.DiscapacidadDAO;
import es.map.ips.dao.EspecialidadDAO;
import es.map.ips.dao.EstadoConvocatoriaDAO;
import es.map.ips.dao.FormaAccesoDAO;
import es.map.ips.dao.LogConvocatoriaDAO;
import es.map.ips.dao.MinisterioDAO;
import es.map.ips.dao.MotivoReduccionTasaDAO;
import es.map.ips.dao.OtrosTitulosDAO;
import es.map.ips.dao.PlantillaDAO;
import es.map.ips.dao.PlantillaPropiosDAO;
import es.map.ips.dao.ProvinciaExamenDAO;
import es.map.ips.dao.TarifaDAO;
import es.map.ips.dao.TituloOficialDAO;
import es.map.ips.model.CamposPropios;
import es.map.ips.model.CentroGestor;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.DatosPropiosConfiguracion;
import es.map.ips.model.DatosPropiosConfiguracionQuery;
import es.map.ips.model.Discapacidad;
import es.map.ips.model.Especialidad;
import es.map.ips.model.EstadoConvocatoria;
import es.map.ips.model.EstadoConvocatoriaQuery;
import es.map.ips.model.FormaAcceso;
import es.map.ips.model.Grupo;
import es.map.ips.model.GrupoQuery;
import es.map.ips.model.LogConvocatoria;
import es.map.ips.model.LogConvocatoriaQuery;
import es.map.ips.model.Ministerio;
import es.map.ips.model.Modelo;
import es.map.ips.model.MotivoReduccionTasa;
import es.map.ips.model.MotivoReduccionTasaQuery;
import es.map.ips.model.OtrosTitulos;
import es.map.ips.model.Plantilla;
import es.map.ips.model.PlantillaPropios;
import es.map.ips.model.PlantillaPropiosQuery;
import es.map.ips.model.ProvinciaExamen;
import es.map.ips.model.Tarifa;
import es.map.ips.model.TarifaQuery;
import es.map.ips.model.TipoAcceso;
import es.map.ips.model.TipoAccesoQuery;
import es.map.ips.model.TituloOficial;
import es.map.ipsg.bean.CamposPropiosBean;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.CrearConvocatoriaBean;
import es.map.ipsg.bean.DatosPropiosConfigBean;
import es.map.ipsg.bean.DetalleConvocatoriaBean;
import es.map.ipsg.bean.DiscapacidadBean;
import es.map.ipsg.bean.EspecialidadBean;
import es.map.ipsg.bean.FormaAccesoBean;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.bean.ModificarConvocatoriaBean;
import es.map.ipsg.bean.MotivoReduccionTasaBean;
import es.map.ipsg.bean.OtrosTitulosBean;
import es.map.ipsg.bean.PlantillaPropiosBean;
import es.map.ipsg.bean.ProvinciaExamenBean;
import es.map.ipsg.bean.TituloOficialBean;
import es.map.ipsg.form.CrearConvocatoriaForm;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.StringIgnoreAccentComparator;

/**
 * El Class ConvocatoriasManagerImpl.
 */
public class ConvocatoriasManagerImpl implements ConvocatoriasManager {
	//private static final String BUNDLE_RESOURCE = "MessageResources";
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ConvocatoriasManagerImpl.class);
	
	/** el convocatoria DAO. */
	private ConvocatoriaDAO convocatoriaDAO;
	
	/** el log convocatoria DAO. */
	private LogConvocatoriaDAO logConvocatoriaDAO;
	
	/** el estado convocatoria DAO. */
	private EstadoConvocatoriaDAO estadoConvocatoriaDAO;
	
	/** el cuerpo escala DAO. */
	private CuerpoEscalaDAO cuerpoEscalaDAO;
	
	/** el forma acceso DAO. */
	private FormaAccesoDAO formaAccesoDAO;
	
	/** el ministerio DAO. */
	private MinisterioDAO ministerioDAO;
	
	/** el titulo oficial DAO. */
	private TituloOficialDAO tituloOficialDAO;
	
	/** el centro gestor DAO. */
	private CentroGestorDAO centroGestorDAO;
	
	/** el campo propio DAO. */
	private CamposPropiosDAO campoPropioDAO;
	
	/** el convocatoria motivo reduccion DAO. */
	private ConvocatoriaMotivoReduccionCustomDAO convocatoriaMotivoReduccionDAO;
	
	/** el plantilla DAO. */
	private PlantillaDAO plantillaDAO;
	
	/** el plantilla propios DAO. */
	private PlantillaPropiosDAO plantillaPropiosDAO;
	
	/** el tarifa DAO. */
	private TarifaDAO tarifaDAO;
	
	/** el provincia examen DAO. */
	private ProvinciaExamenDAO provinciaExamenDAO;
	
	/** el motivo reduccion tasa DAO. */
	private MotivoReduccionTasaDAO motivoReduccionTasaDAO;
	
	/** el especialidad DAO. */
	private EspecialidadDAO especialidadDAO;
	
	/** el otros titulos DAO. */
	private OtrosTitulosDAO otrosTitulosDAO;
	
	/** el discapacidad DAO. */
	private DiscapacidadDAO discapacidadDAO;
	
	/** el datos propios configuracion DAO. */
	private DatosPropiosConfiguracionDAO datosPropiosConfiguracionDAO;
	
	/** el plantilla propios manager. */
	private PlantillaPropiosManager plantillaPropiosManager;
	
	/** La constante STRING_SIMPLEDATEFORMAT. */
	private static final String STRING_SIMPLEDATEFORMAT = "dd/MM/yyyy";

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ConvocatoriasManager#buscarConvocatoriasAll(es.map.ips.model.ConvocatoriaQuery)
	 */
	public ArrayList<ConvocatoriasBean> buscarConvocatoriasAll(
			ConvocatoriaQuery convocatoriaQuery) {
		SearchResult<Convocatoria> convocatorias = convocatoriaDAO.search(convocatoriaQuery);
		
		int numTotal = 0;
		
		if(convocatorias.getNumResults() != null){
			numTotal = convocatorias.getNumResults();
		}
		
		ArrayList<ConvocatoriasBean> arrConvocatorias = new ArrayList<ConvocatoriasBean>();
		for (int i = 0; i < convocatorias.getResults().size(); i++) {
			ConvocatoriasBean aux;
			aux = toBuscarConvocatoriaBean(convocatorias.getResults().get(i),numTotal);
			if (aux != null) {
				arrConvocatorias.add(aux);
			}
		}
		return arrConvocatorias;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ConvocatoriasManager#buscarConvocatoriaById(es.map.ips.model.ConvocatoriaQuery)
	 */
	public ConvocatoriasBean buscarConvocatoriaById(
			ConvocatoriaQuery convocatoriaQuery) {
		Convocatoria convocatoria = convocatoriaDAO.searchUnique(convocatoriaQuery);
		if(convocatoria != null){
			return toConvocatoriaBean(convocatoria);
		}
		return null;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ConvocatoriasManager#recuperarConvocatoria(long)
	 */
	public ConvocatoriasBean recuperarConvocatoria(long codigoConvocatoria) {
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(codigoConvocatoria);	
		SearchResult<Convocatoria> lista= convocatoriaDAO.search(convocatoriaQuery);
		ArrayList<ConvocatoriasBean> arrConvocatorias = new ArrayList<ConvocatoriasBean>();
		if(lista == null){
			return null;
		}
		for(int i=0;i<lista.getResults().size();i++){
			ConvocatoriasBean aux;
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
		List<PlantillaPropiosBean> plantilla = plantillaPropiosManager.obtenerPlantillaPropiosById(plantillaQuery);
		
		arrConvocatorias.get(0).setPlantillaPropios(plantilla);
		
		return arrConvocatorias.get(0);
	}
	
	/**
	 * To convocatoria bean.
	 *
	 * @param convocatoria el convocatoria
	 * @return el convocatorias bean
	 */
	public ConvocatoriasBean toConvocatoriaBean(Convocatoria convocatoria) {
		ConvocatoriasBean aux = new ConvocatoriasBean();
		
		if(convocatoria.getId() != null){
			aux.setIdConvocatoria(String.valueOf(convocatoria.getId()));
		}		
		
		toConvocatoriaBean2(convocatoria,aux);
		
		if(convocatoria.getDirigidoA() != null){
			aux.setDirigidoA(convocatoria.getDirigidoA());
		}
		
		if(convocatoria.getEnlace() != null){
			aux.setEnlace(convocatoria.getEnlace());
		}
		
		if(convocatoria.getPlantilla() != null){
			aux.setIdPlantilla(convocatoria.getPlantilla().getId());
		}
		
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(convocatoria.getId());
		MotivoReduccionTasaQuery motivoReduccionTasaQuery = new MotivoReduccionTasaQuery();
		SearchResult<MotivoReduccionTasa> motivos = convocatoriaMotivoReduccionDAO.searchMotivoReduccionPorConvocatoria(motivoReduccionTasaQuery,convocatoriaQuery);
		if(convocatoria.getMotivoReduccionTasas()!= null && convocatoria.getMotivoReduccionTasas().size()>0){
			aux.setMotivoReduccionTasas(convocatoria.getMotivoReduccionTasas());
			ArrayList<MotivoReduccionTasa> arrayTasas = new ArrayList<MotivoReduccionTasa>();
			ArrayList<MotivoReduccionTasa> arrayTasasIncompleto = new ArrayList<MotivoReduccionTasa>();
			Iterator<?> it = motivos.getResults().iterator();
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
					logger.error(" Error ConvocatoriasManagerImpl -  buscarConvocatoriaById- motivo de reduccion tasa",e);
				}
			}
			aux.setMotivoReduccionTasasCompleto(arrayTasas);
			aux.setMotivoReduccionTasasIncompleto(arrayTasasIncompleto);
				

		}
				
		return aux;
	}

	/**
	 * To convocatoria bean 2.
	 *
	 * @param convocatoria el convocatoria
	 * @param aux el aux
	 */
	private void toConvocatoriaBean2(Convocatoria convocatoria, ConvocatoriasBean aux) {		
		
		if(convocatoria.getCuerpoEscala().getCentroGestor() != null){
			aux.setCentroGestor(convocatoria.getCuerpoEscala().getCentroGestor().getDescripcion());
		}
		if(convocatoria.getCuerpoEscala().getCentroGestor() != null){
			
			aux.setCodCentroGestor(convocatoria.getCuerpoEscala().getCentroGestor().getIdCentroGestor());
			aux.setSiglasCentroGestor(convocatoria.getCuerpoEscala().getCentroGestor().getSiglas());
		}
		
		if(convocatoria.getCuerpoEscala() !=  null){
			aux.setCuerpoEscala(convocatoria.getCuerpoEscala().getDescripcion());
		}
		
		if(convocatoria.getEjercicio() != null){
			aux.setEjercicio(convocatoria.getEjercicio());
		}
		if(convocatoria.getEstadoConvocatoria() != null){
			aux.setEstadoConvocatoria(convocatoria.getEstadoConvocatoria().getDescripcion());
		}
		if(convocatoria.getFechaFin() != null){
			aux.setFechaFin(convocatoria.getFechaFin().toString());
		}
		if(convocatoria.getFechaInicio() != null){
			aux.setFechaInicio(convocatoria.getFechaInicio().toString());
		}
		if(convocatoria.getFechaBoe() != null){
			SimpleDateFormat formatoFechaBOE = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
			String fechaBoe = formatoFechaBOE.format(convocatoria.getFechaBoe());
			aux.setFechaBoe(fechaBoe);
		}
		if(convocatoria.getFormaAcceso() != null){
			// Correccion Incidencia
			aux.setFormaAcceso(convocatoria.getFormaAcceso().getCodigo());
		}
		if(convocatoria.getCuerpoEscala().getGrupo() != null){
			aux.setGrupo(convocatoria.getCuerpoEscala().getGrupo().getDescripcion());
		}
		//Ministerio
		if(convocatoria.getCuerpoEscala().getCentroGestor().getMinisterio() != null){
			aux.setMinisterio(convocatoria.getCuerpoEscala().getCentroGestor().getMinisterio().getDescripcion());
		}
		//Ministerio Convocatoria
		if(convocatoria.getMinisterioConvocante() != null){
			aux.setMinisterioConvocatoria(convocatoria.getMinisterioConvocante().getDescripcion());
		}else{
			if(convocatoria.getCuerpoEscala().getCentroGestor().getMinisterio() != null){
				aux.setMinisterioConvocatoria(convocatoria.getCuerpoEscala().getCentroGestor().getMinisterio().getDescripcion());
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ConvocatoriasManager#buscarConvocatoriasAllSinNumTotal(es.map.ips.model.ConvocatoriaQuery)
	 */
	public ArrayList<ConvocatoriasBean> buscarConvocatoriasAllSinNumTotal(
			ConvocatoriaQuery convocatoriaQuery) {

		SearchResult<Convocatoria> convocatorias = buscarConvocatorias(convocatoriaQuery);
		CentroGestorQuery centroGestor = new CentroGestorQuery();
		centroGestor.setId(1);
		CuerpoEscalaQuery cuerpoEscala = new CuerpoEscalaQuery();
		cuerpoEscala.setCentroGestor(centroGestor);
		convocatoriaQuery.setCuerpoEscala(cuerpoEscala);
		
		ArrayList<ConvocatoriasBean> arrConvocatorias = new ArrayList<ConvocatoriasBean>();
		for (int i = 0; i < convocatorias.getResults().size(); i++) {
			ConvocatoriasBean aux;
			aux = toBuscarConvocatoriaBeanSinNumTotal(convocatorias.getResults().get(i));
			if (aux != null) {
				arrConvocatorias.add(aux);
			}
		}

		return arrConvocatorias;
	}

	/**
	 * To buscar convocatoria bean.
	 *
	 * @param convocatoria el convocatoria
	 * @param numTotal el num total
	 * @return el convocatorias bean
	 */
	private ConvocatoriasBean toBuscarConvocatoriaBean(Convocatoria convocatoria, int numTotal) {

		String id = convocatoria.getId().toString();
		logger.info("ID: " + id);

		String ejercicio = convocatoria.getEjercicio();
		logger.info("Ejercicio: " + ejercicio);

		String estado = convocatoria.getEstadoConvocatoria().getDescripcion();
		logger.info("Estado: " + estado);

		String centro;
		String siglasCentroGestor;
		/***** QUE SE PONE EN CODIGO GESTOR PARA SABER QUE NO ESTA VISIBLE? *****/
		int codCentroGestor = convocatoria.getCuerpoEscala().getCentroGestor().getIdCentroGestor();
		if(convocatoria.getCuerpoEscala().getCentroGestor().getVisible() == 'S' || convocatoria.getCuerpoEscala().getCentroGestor().getVisible() == 's')
		{	
			centro = convocatoria.getCuerpoEscala().getCentroGestor()
					.getDescripcion();
			
			siglasCentroGestor = convocatoria.getCuerpoEscala().getCentroGestor().getSiglas();
			logger.info("CentroGestor: " + centro);		
		}
		else
		{
			centro = "";
			siglasCentroGestor = "";
			logger.info("CentroGestor: No visible" );		
		}	
		
		String cuerpo;
		if(convocatoria.getCuerpoEscala().getVisible() == 'S' || convocatoria.getCuerpoEscala().getVisible() == 'N')
		{	
			cuerpo = convocatoria.getCuerpoEscala().getDescripcion();
			logger.info("Cuerpo: " + cuerpo);
		}
		else
		{
			cuerpo = "";
			logger.info("Cuerpo: No visible");
		}	
		
		String grupo;
		if(convocatoria.getCuerpoEscala().getGrupo().getVisible() == 'S' || convocatoria.getCuerpoEscala().getGrupo().getVisible() == 's')
		{	
			grupo = convocatoria.getCuerpoEscala().getGrupo().getDescripcion();
			logger.info("Grupo: " + grupo);
		}
		else
		{
			grupo = "";
			logger.info("Grupo: no visible");
		}	

		String formaAcceso;
		if(convocatoria.getFormaAcceso().getVisible() == 'S' || convocatoria.getFormaAcceso().getVisible() == 's')
		{	
			formaAcceso = convocatoria.getFormaAcceso().getDescripcion();
		}
		else
		{
			formaAcceso = "";
			logger.info("Forma de acceso: no visible");
		}	
	
		SimpleDateFormat formatoFecha = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
		String fechaInicio = null;
		if (convocatoria.getFechaInicio() != null) {
			fechaInicio = formatoFecha.format(convocatoria.getFechaInicio());
			logger.info("Fecha Inicio: " + fechaInicio);
		}
		String fechaFin = null;
		if (convocatoria.getFechaFin() != null) {
			fechaFin = formatoFecha.format(convocatoria.getFechaFin());
			logger.info("Fecha Inicio: " + fechaFin);
		}
	
		ConvocatoriasBean convocatoriaBean = new ConvocatoriasBean();
		convocatoriaBean.setIdConvocatoria(id);
		convocatoriaBean.setEstadoConvocatoria(estado);
		convocatoriaBean.setCentroGestor(centro);
		convocatoriaBean.setCodCentroGestor(codCentroGestor);
		convocatoriaBean.setSiglasCentroGestor(siglasCentroGestor);
		convocatoriaBean.setCuerpoEscala(cuerpo);
		convocatoriaBean.setGrupo(grupo);
		convocatoriaBean.setFormaAcceso(formaAcceso);
		convocatoriaBean.setNumTotal(numTotal);	
		if (fechaInicio != null) {
			convocatoriaBean.setFechaInicio(fechaInicio);
		}
		if (fechaFin != null) {
			convocatoriaBean.setFechaFin(fechaFin);
		}
		
		//Fechas de subsanacion
		if (convocatoria.getFechaIniSubsanacion() != null) {
			convocatoriaBean.setFechaIniSub(formatoFecha.format(convocatoria.getFechaIniSubsanacion()));
			logger.info("Fecha Inicio Subsanacion: " + ((convocatoriaBean.getFechaIniSub() != null)?convocatoriaBean.getFechaIniSub():" nula"));
		}
		if (convocatoria.getFechaFinSubsanacion() != null) {
			convocatoriaBean.setFechaFinSub(formatoFecha.format(convocatoria.getFechaFinSubsanacion()));
			logger.info("Fecha Fin Subsanacion: " + ((convocatoriaBean.getFechaFinSub() != null)?convocatoriaBean.getFechaFinSub():" nula"));
		}			
		convocatoriaBean.setEjercicio(ejercicio);
		convocatoriaBean.setIdPlantilla(convocatoria.getPlantilla().getId());
		
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(convocatoria.getId());
		MotivoReduccionTasaQuery motivoReduccionTasaQuery = new MotivoReduccionTasaQuery();
		SearchResult<MotivoReduccionTasa> motivos = convocatoriaMotivoReduccionDAO.searchMotivoReduccionPorConvocatoria(motivoReduccionTasaQuery,convocatoriaQuery);
		if(convocatoria.getMotivoReduccionTasas()!= null && convocatoria.getMotivoReduccionTasas().size()>0){
			convocatoriaBean.setMotivoReduccionTasas(convocatoria.getMotivoReduccionTasas());
			ArrayList<MotivoReduccionTasa> arrayTasas = new ArrayList<MotivoReduccionTasa>();
			ArrayList<MotivoReduccionTasa> arrayTasasIncompleto = new ArrayList<MotivoReduccionTasa>();
			Iterator<?> it = motivos.getResults().iterator();
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
					logger.error(" Error ConvocatoriasManagerImpl - toBuscarConvocatoriaBean- motivo de reduccion tasa",e);
				}
			}
			convocatoriaBean.setMotivoReduccionTasasCompleto(arrayTasas);
			convocatoriaBean.setMotivoReduccionTasasIncompleto(arrayTasasIncompleto);
				

		}

		return convocatoriaBean;
	}
	
	/**
	 * To buscar convocatoria bean sin num total.
	 *
	 * @param convocatoria el convocatoria
	 * @return el convocatorias bean
	 */
	private ConvocatoriasBean toBuscarConvocatoriaBeanSinNumTotal(Convocatoria convocatoria) {

		String id = convocatoria.getId().toString();
		logger.info("ID: " + id);

		String ejercicio = convocatoria.getEjercicio();
		logger.info("Ejercicio: " + ejercicio);

		String estado = convocatoria.getEstadoConvocatoria().getDescripcion();
		logger.info("Estado: " + estado);

		ConvocatoriasBean convocatoriaBean = new ConvocatoriasBean();
		convocatoriaBean.setIdConvocatoria(id);
		convocatoriaBean.setEstadoConvocatoria(estado);
		convocatoriaBean.setEstadoConvocatoria(estado);
		

		convocatoriaBean.setEjercicio(ejercicio);

		return convocatoriaBean;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ConvocatoriasManager#buscarConvocatoria(es.map.ips.model.ConvocatoriaQuery)
	 */
	public Convocatoria buscarConvocatoria(ConvocatoriaQuery convocatoriaQuery) {
		SearchResult<Convocatoria> convocatorias = buscarConvocatorias(convocatoriaQuery);
		if (convocatorias.size() == 1) {
			return convocatorias.getResults().get(0);
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ConvocatoriasManager#actualizarEstado(es.map.ips.model.ConvocatoriaQuery, es.map.ips.model.EstadoConvocatoriaQuery)
	 */
	public void actualizarEstado(ConvocatoriaQuery convocatoriaQuery,
			EstadoConvocatoriaQuery estadoConvocatoriaQuery) {

		Convocatoria convocatorias;	
		convocatorias = buscarConvocatoria(convocatoriaQuery);
		if (convocatorias != null) {
			EstadoConvocatoria estadoConvocatoria;
			estadoConvocatoria = estadoConvocatoriaDAO
					.searchUnique(estadoConvocatoriaQuery);
			if (estadoConvocatoria != null) {
				convocatoriaDAO = getConvocatoriaDAO();
				convocatorias.setEstadoConvocatoria(estadoConvocatoria);				
				if(convocatorias.getMinisterioConvocante()==null){
					Ministerio ministerioConvocante=convocatorias.getCuerpoEscala().getCentroGestor().getMinisterio();
					convocatorias.setMinisterioConvocante(ministerioConvocante);
				}
				convocatoriaDAO.update(convocatorias);
			}
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ConvocatoriasManager#detalleConvocatoria(es.map.ips.model.ConvocatoriaQuery)
	 */
	public DetalleConvocatoriaBean detalleConvocatoria(
			ConvocatoriaQuery convocatoriaQuery) {
		Convocatoria convocatorias;
		convocatorias = convocatoriaDAO.searchUnique(convocatoriaQuery);
		LogConvocatoriaQuery logConvocatoriaQuery = new LogConvocatoriaQuery();
		logConvocatoriaQuery.setConvocatoria(convocatoriaQuery);
		logConvocatoriaQuery.addOrder("fecha", OrderType.ASC);
		SearchResult<LogConvocatoria> logConvocatorias = logConvocatoriaDAO.search(logConvocatoriaQuery);
		return toBuscarConvocatoriasBean(convocatorias, logConvocatorias);
	}

	/**
	 * To buscar convocatorias bean.
	 *
	 * @param convocatorias el convocatorias
	 * @param logConvocatorias el log convocatorias
	 * @return el detalle convocatoria bean
	 */
	private DetalleConvocatoriaBean toBuscarConvocatoriasBean(
			Convocatoria convocatorias, SearchResult<LogConvocatoria> logConvocatorias) {

		SimpleDateFormat formatoFecha = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
		String ministerioConvocante=null;
		Long idConvocatoria = convocatorias.getId();
		String centroGestor = convocatorias.getCuerpoEscala().getCentroGestor()
				.getDescripcion();	
		String cuerpoEscala = convocatorias.getCuerpoEscala().getDescripcion();
		String enlace = convocatorias.getEnlace();

		if(convocatorias.getMinisterioConvocante()==null){
			ministerioConvocante = convocatorias.getCuerpoEscala().getCentroGestor().getMinisterio().getDescripcion();
		}else{
			ministerioConvocante = convocatorias.getMinisterioConvocante().getDescripcion();
		}		
		String ejercicio = convocatorias.getEjercicio();
		String formaAcceso = convocatorias.getFormaAcceso().getDescripcion();
		float importe = convocatorias.getImporte();
		String fechaBOE = null;
		if (convocatorias.getFechaBoe() != null) {
			fechaBOE = formatoFecha.format(convocatorias.getFechaBoe());
		}
		String fechaInicio = null;
		if (convocatorias.getFechaInicio() != null) {
			fechaInicio = formatoFecha.format(convocatorias.getFechaInicio());
		}
		String fechaFin = null;
		if (convocatorias.getFechaFin() != null) {
			fechaFin = formatoFecha.format(convocatorias.getFechaFin());
		}
		String fechaInicioSub = null;
		if (convocatorias.getFechaIniSubsanacion() != null) {
			fechaInicioSub = formatoFecha.format(convocatorias.getFechaIniSubsanacion());
		}
		String fechaFinSub = null;
		if (convocatorias.getFechaFinSubsanacion() != null) {
			fechaFinSub = formatoFecha.format(convocatorias.getFechaFinSubsanacion());
		}		
		int numPlazasTotales = convocatorias.getNPlazasTotal();
		int numPlazasDiscapacitados = convocatorias.getNPlazasDiscapacitados();
		String estadoConvocatoria = convocatorias.getEstadoConvocatoria()
				.getDescripcion();
		Integer idEstadoConvocatoria = convocatorias.getEstadoConvocatoria().getId();
		String dirigidoA = convocatorias.getDirigidoA();
		String tipoDocumentacionAnexar = convocatorias
				.getTipoDocumentoPermitido().toString();

		String provinciasExamen = "";
		ArrayList<String> provin = new ArrayList<String>();
		for (Iterator<ProvinciaExamen> iter = convocatorias.getProvinciasExamen().iterator(); iter
				.hasNext();) {
			ProvinciaExamen provincia = (ProvinciaExamen) iter.next();
			provinciasExamen = provincia.getDescripcion();
			
			provin.add(provinciasExamen);
			
		}
		
		String titulosExigidos = "";
		ArrayList<String> titulos = new ArrayList<String>();
		for (Iterator<TituloOficial> iter = convocatorias.getTituloOficials().iterator();iter.hasNext();) {
			TituloOficial tituloOficial = (TituloOficial) iter.next();
			titulosExigidos =tituloOficial.getDescripcion();
			titulos.add(titulosExigidos);
		}
		
		String motivosExencion = "";
		ArrayList<String> motivos = new ArrayList<String>();
		for (Iterator<MotivoReduccionTasa> iter = convocatorias.getMotivoReduccionTasas().iterator(); iter
				.hasNext();) {
			MotivoReduccionTasa motivoResduccion = (MotivoReduccionTasa) iter
					.next();
			motivosExencion =motivoResduccion.getDescripcion();
			motivos.add(motivosExencion);
		}
		
		String especialidades = "";
		ArrayList<String> especial = new ArrayList<String>();
		for (Iterator<Especialidad> iter = convocatorias.getEspecialidads().iterator(); iter
				.hasNext();) {
			Especialidad especialidad = (Especialidad) iter.next();
			especialidades =especialidad.getDescripcion();
			especial.add(especialidades);
		}
		
		ArrayList<LogConvocatoria> arrayLogConvocatorias = new ArrayList<LogConvocatoria>();
		for (int i = 0; i<logConvocatorias.size();i++) {
			LogConvocatoria logConvocatoria = logConvocatorias.getResults().get(i);
			arrayLogConvocatorias.add(logConvocatoria);
		}

		String otrosTitulos = "";
		ArrayList<String> otroTitulo = new ArrayList<String>();
		for (Iterator<OtrosTitulos> iter = convocatorias.getOtrosTitulos().iterator(); iter
				.hasNext();) {
			OtrosTitulos otroTit = (OtrosTitulos) iter.next();
			otrosTitulos =otroTit.getDescripcion();
			otroTitulo.add(otrosTitulos);
		}
		
		String discapacidades = "";
		ArrayList<String> discapacidad = new ArrayList<String>();
		for (Iterator<Discapacidad> iter = convocatorias.getDiscapacidad().iterator(); iter
				.hasNext();) {
			Discapacidad discap = (Discapacidad) iter.next();
			discapacidades =discap.getDescripcion();
			discapacidad.add(discapacidades);
		}
		String datosPropios = "";
		ArrayList<String> datosPropiosList = new ArrayList<String>();
		for (Iterator<DatosPropiosConfiguracion> iter = convocatorias.getDatosPropiosConfiguracion().iterator(); iter
				.hasNext();) {
			DatosPropiosConfiguracion datosPropiosConfiguracion = (DatosPropiosConfiguracion) iter.next();
			datosPropios =datosPropiosConfiguracion.getDescripcion();
			datosPropiosList.add(datosPropios);
		}
		
		//Ordenamos alfabeticamente las listas
		StringIgnoreAccentComparator comparator = new StringIgnoreAccentComparator();
		java.util.Collections.sort(provin,comparator);
		java.util.Collections.sort(titulos,comparator);
		java.util.Collections.sort(motivos,comparator);
		java.util.Collections.sort(especial,comparator);
		java.util.Collections.sort(otroTitulo,comparator);
		java.util.Collections.sort(discapacidad,comparator);
		java.util.Collections.sort(datosPropiosList,comparator);

		DetalleConvocatoriaBean detalleConvocatoriaBean = new DetalleConvocatoriaBean();
		detalleConvocatoriaBean.setIdPlantilla(convocatorias.getPlantilla().getId());
		detalleConvocatoriaBean.setIdConvocatoria(idConvocatoria);
		detalleConvocatoriaBean.setCentroGestor(centroGestor);
		detalleConvocatoriaBean.setCuerpoEscala(cuerpoEscala);
		detalleConvocatoriaBean.setMinisterioConvocante(ministerioConvocante);
		detalleConvocatoriaBean.setEjercicio(ejercicio);
		detalleConvocatoriaBean.setFormaAcceso(formaAcceso);
		detalleConvocatoriaBean.setImporte(importe);
		detalleConvocatoriaBean.setFechaBoe(fechaBOE);
		detalleConvocatoriaBean.setFechaInicio(fechaInicio);
		detalleConvocatoriaBean.setFechaFin(fechaFin);
		detalleConvocatoriaBean.setFechaInicioSub(fechaInicioSub);
		detalleConvocatoriaBean.setFechaFinSub(fechaFinSub);
		detalleConvocatoriaBean.setNumPlazasTotales(numPlazasTotales);
		detalleConvocatoriaBean.setNumPlazasDiscapacitados(numPlazasDiscapacitados);
		detalleConvocatoriaBean.setIdEstadoConvocatoria(idEstadoConvocatoria);
		detalleConvocatoriaBean.setEstadoConvocatoria(estadoConvocatoria);
		detalleConvocatoriaBean.setDirigidoA(dirigidoA);
		detalleConvocatoriaBean.setTipoDocumentacionAnexar(tipoDocumentacionAnexar);
		detalleConvocatoriaBean.setProvinciasExamen(provin);
		detalleConvocatoriaBean.setTitulosExigidos(titulos);
		detalleConvocatoriaBean.setMotivosExencion(motivos);
		detalleConvocatoriaBean.setEspecialidades(especial);	
		detalleConvocatoriaBean.setOtrosTitulos(otroTitulo);
		detalleConvocatoriaBean.setDiscapacidad(discapacidad);
		detalleConvocatoriaBean.setDatosPropios(datosPropiosList);
		detalleConvocatoriaBean.setEnlace(enlace);

		if(convocatorias.getPresencial() != null){
			if (convocatorias.getPresencial().equals('S')){
				detalleConvocatoriaBean.setPresencial(true);
			}else{
				detalleConvocatoriaBean.setPresencial(false);
			}
		}else{
			detalleConvocatoriaBean.setPresencial(false);
		}
		detalleConvocatoriaBean.setLogConvocatorias(arrayLogConvocatorias);

		return detalleConvocatoriaBean;
	}

	/**
	 * Buscar convocatorias.
	 *
	 * @param convocatoriaQuery el convocatoria query
	 * @return el search result
	 */
	public SearchResult<Convocatoria> buscarConvocatorias(
			ConvocatoriaQuery convocatoriaQuery) {

		ApplicationException.assertNotNull(convocatoriaQuery,
				"convocatoriaQuery no puede ser null");

		return convocatoriaDAO.search(convocatoriaQuery);
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ConvocatoriasManager#crearConvocatoria(es.map.ipsg.bean.CrearConvocatoriaBean, es.map.ipsg.form.CrearConvocatoriaForm)
	 */
	public long crearConvocatoria(CrearConvocatoriaBean bean, CrearConvocatoriaForm formulario) {
		logger.info("Entrada en CrearConvocatoria ConvocatoriaManager");
		Convocatoria con = new Convocatoria();
		//le seteamos el modelo asociado
		Modelo modelo = new Modelo();
		modelo.setIdModelo(Integer.parseInt(bean.getIdModeloAsociado()));
		con.setModelo(modelo);
		con.setCuerpoEscala(cuerpoEscalaDAO.get(bean.getIdCuerpoEscala()));
		logger.info(cuerpoEscalaDAO.get(bean.getIdCuerpoEscala()).getDescripcion());
		con.setMinisterioConvocante(ministerioDAO.get(bean.getIdMinisterioConvocante()));
		con.setNPlazasTotal(bean.getNumPlazas());
		con.setNPlazasDiscapacitados(bean.getNumPlazasDisc());
		FormaAcceso formaAcc = formaAccesoDAO.get(bean.getIdFormaAcceso());
		con.setFormaAcceso(formaAcc);
		if(!formaAcc.getCodigo().equals(bean.getCodigoFormaAcceso()))
		{	
			con.setCodigoFormaAcceso(bean.getCodigoFormaAcceso());
		}
		else
		{
			con.setCodigoFormaAcceso(null);
		}
		
		con.setImporte(bean.getImporte());
		con.setDirigidoA(bean.getDirigidoA());
		con.setTipoDocumentoPermitido(bean.getTipoDocumentacion());
		con.setTipoCargaSolPresencial(Constantes.TIPO_SOLICITUD_PRESENCIAL_IPS);

		cargarTitulosOficiales(bean, con);

		// Empezamos la introduccion de datos opcionales	
		crearConvocatoriaAux(bean,con);
		
		cargarCamposConIds(bean, con);
		

		con.setEjercicio(this.calcularEjercicio(bean.getIdCentroGestor()));

		// Recuperamos la plantilla del centro gestor
		Plantilla plantilla = centroGestorDAO.get(bean.getIdCentroGestor()).getPlantilla();
		logger.info("ID plantilla CentroGestor: " + plantilla.getId());
		
		// Hacemos una copia y la insertamos en la tabla de plantillas		
		long idPlantilla = plantillaDAO.insert(this.copiaPlantilla(plantilla, formulario));
		logger.info("ID plantilla insertada" + idPlantilla);

		// Recuperamos la plantilla insertada/copiada para incluirla en la
		// Convocatoria
		plantilla = plantillaDAO.get(idPlantilla);

		con.setPlantilla(plantilla);

		con.setEstadoConvocatoria(estadoConvocatoriaDAO.get(4));

		con.setPresencial(bean.getPresencial());
		con.setEnlace(bean.getEnlace());
		
		con.setOcultarDatosPropios(bean.getOcultarDatosPropios());

		return convocatoriaDAO.insert(con);
	}
	
	/**
	 * Crear convocatoria aux.
	 *
	 * @param bean el bean
	 * @param con el con
	 */
	public void crearConvocatoriaAux(CrearConvocatoriaBean bean, Convocatoria con) {
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
		if (StringUtils.isNotEmpty(bean.getFechaBoe())) {

			Date fechaBoe = null;
			try {

				fechaBoe = formatoDelTexto.parse(bean.getFechaBoe());

			} catch (ParseException ex) {
				logger.error(" Error ConvocatoriasManagerImpl - crearConvocatoria-  parsear fechaBoe"+ fechaBoe,ex);
				

			}
			con.setFechaBoe(fechaBoe);
		}
		if (StringUtils.isNotEmpty(bean.getFechaInicio())) {
			Date fechaInicio = null;
			try {

				fechaInicio = formatoDelTexto.parse(bean.getFechaInicio());

			} catch (ParseException ex) {

				logger.error(" Error ConvocatoriasManagerImpl - crearConvocatoria-  parsear fechaInicio"+ fechaInicio,ex);

			}
			con.setFechaInicio(fechaInicio);
		}
		if (StringUtils.isNotEmpty(bean.getFechaTermino())) {
			Date fechaTermino = null;
			try {

				fechaTermino = formatoDelTexto.parse(bean.getFechaTermino());

			} catch (ParseException ex) {

				logger.error(" Error ConvocatoriasManagerImpl - crearConvocatoria-  parsear fechaTermino"+ fechaTermino,ex);

			}
			con.setFechaFin(fechaTermino);
		}
		if (StringUtils.isNotEmpty(bean.getFechaIniSub())) {
			Date fechaIniSub = null;
			try {

				fechaIniSub = formatoDelTexto.parse(bean.getFechaIniSub());

			} catch (ParseException ex) {

				logger.error(" Error ConvocatoriasManagerImpl - crearConvocatoria-  parsear fechaIniSub"+ fechaIniSub,ex);

			}
			con.setFechaIniSubsanacion(fechaIniSub);
		}
		if (StringUtils.isNotEmpty(bean.getFechaFinSub())) {
			Date fechaFinSub = null;
			try {

				fechaFinSub = formatoDelTexto.parse(bean.getFechaFinSub());

			} catch (ParseException ex) {

				logger.error(" Error ConvocatoriasManagerImpl - crearConvocatoria-  parsear fechaFinSub"+ fechaFinSub,ex);

			}
			con.setFechaFinSubsanacion(fechaFinSub);
		}		
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
	 * Copia plantilla.
	 *
	 * @param plantilla el plantilla
	 * @param formulario el formulario
	 * @return el plantilla
	 */
	private Plantilla copiaPlantilla(Plantilla plantilla, CrearConvocatoriaForm formulario) {
		Plantilla nueva = new Plantilla();

		nueva.setCodigoPostal(plantilla.getCodigoPostal());
		nueva.setCorreoElectronico(plantilla.getCorreoElectronico());
		nueva.setCuerpo(plantilla.getCuerpo());
		nueva.setDatosA(plantilla.getDatosA());
		nueva.setDatosB(plantilla.getDatosB());
		nueva.setDatosC(plantilla.getDatosC());
		nueva.setDetalleDiscapacidad((formulario != null && formulario.isDiscapacidadFlag())?'S':plantilla.getReservaDiscapacidad());
		nueva.setEntidadConvocante(plantilla.getEntidadConvocante());
		nueva.setEspecialidad(plantilla.getEspecialidad());
		nueva.setFechaBoe(plantilla.getFechaBoe());
		nueva.setFechaNacimiento(plantilla.getFechaNacimiento());
		nueva.setFormaacceso(plantilla.getFormaacceso());
		nueva.setLocalidadNacimiento(plantilla.getLocalidadNacimiento());
		nueva.setMunicipio(plantilla.getMunicipio());
		nueva.setNacionalidad(plantilla.getNacionalidad());
		nueva.setNif(plantilla.getNif());
		nueva.setNombre(plantilla.getNombre());
		nueva.setOtrosTitulos((formulario != null && formulario.isOtrosTitulosFlag())?'S':plantilla.getOtrosTitulos());
		nueva.setPais(plantilla.getPais());
		nueva.setPorcentajeDiscapacidad(plantilla.getPorcentajeDiscapacidad());
		nueva.setPrimerApellido(plantilla.getPrimerApellido());
		nueva.setProvincia(plantilla.getProvincia());
		nueva.setProvinciaExamen(plantilla.getProvinciaExamen());
		nueva.setProvinciaNacimiento(plantilla.getProvinciaNacimiento());
		nueva.setReservaDiscapacidad(plantilla.getReservaDiscapacidad());
		nueva.setSegundoApellido(plantilla.getSegundoApellido());
		nueva.setSexo(plantilla.getSexo());
		nueva.setTelefono(plantilla.getTelefono());
		nueva.setTipoDiscapacidad(plantilla.getTipoDiscapacidad());
		nueva.setTipoPlantilla(Constantes.TIPO_PLANTILLA_CONVOCATORIA);
		nueva.setTitulosExigidos(plantilla.getTitulosExigidos());
		nueva.setVia(plantilla.getVia());
		nueva.setCodigoCuerpoEscala(plantilla.getCodigoCuerpoEscala());
		nueva.setCodigoEspecialidad(plantilla.getCodigoEspecialidad());
		nueva.setCodigoMinisterio(plantilla.getCodigoMinisterio());
		nueva.setCodigoPaisDomicilio(plantilla.getCodigoPaisDomicilio());
		nueva.setCodigoProvinciaDomicilio(plantilla.getCodigoProvinciaDomicilio());
		nueva.setCodigoProvinciaExamen(plantilla.getCodigoProvinciaExamen());
		nueva.setCodigoTituloOficial(plantilla.getCodigoTituloOficial());
		
		return nueva;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ConvocatoriasManager#buscarImporte(int, int, es.map.ips.model.TarifaQuery)
	 */
	public String buscarImporte(int cuerpoEscalaId, int formaAccesoId,
			TarifaQuery tarifaQuery) {

		Grupo grupo = cuerpoEscalaDAO.get(cuerpoEscalaId).getGrupo();
		TipoAcceso tipo = formaAccesoDAO.get(formaAccesoId).getTipoAcceso();

		GrupoQuery grupoQuery = new GrupoQuery();
		grupoQuery.setId(grupo.getId());

		TipoAccesoQuery tipoAccesoQuery = new TipoAccesoQuery();
		tipoAccesoQuery.setId(tipo.getId());

		tarifaQuery.setGrupo(grupoQuery);
		tarifaQuery.setTipoAcceso(tipoAccesoQuery);
		tarifaQuery.setEstado('A');
		
		List<Tarifa> tarifas = tarifaDAO.search(tarifaQuery).getResults();
		float result;
		
		if(tarifas!=null && tarifas.size()>0){
			 result = tarifas.get(0).getImporte();
		}else{
			throw new BusinessException();
		}

		logger.info("ConvocatoriaManager--> Tarifa: " + result);

		return result + "";
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ConvocatoriasManager#copiarConvocatoria(long)
	 */
	public long copiarConvocatoria(long id){
		Convocatoria origen;
		origen=convocatoriaDAO.get(id);
		Convocatoria destino=new Convocatoria();
		destino.setCuerpoEscala(origen.getCuerpoEscala());
		destino.setDirigidoA(origen.getDirigidoA());
		destino.setModelo(origen.getModelo());
		destino.setPresencial(origen.getPresencial());
		destino.setEnlace(origen.getEnlace());
		destino.setOcultarDatosPropios(origen.getOcultarDatosPropios());
		
		destino.setEjercicio(this.calcularEjercicio(origen.getCuerpoEscala().getCentroGestor().getIdCentroGestor()));
		
		//Calcular Tarifa
		TarifaQuery tarifaQuery = new TarifaQuery();
		tarifaQuery.setEjercicio(destino.getEjercicio());
		destino.setImporte(Float.parseFloat(this.buscarImporte(origen.getCuerpoEscala().getId(), origen.getFormaAcceso().getId(), tarifaQuery)));
		
		Set<Especialidad> especialidadesOrigen= origen.getEspecialidads();
		Set<Especialidad> especialidadesDestino = new HashSet<Especialidad>();
		
		//Especialidad espTemp;
		
		
		Iterator<?> it = especialidadesOrigen.iterator();
		while(it.hasNext()){
			logger.info("iterator Titulacion");
			Especialidad e=(Especialidad)it.next();
			
			especialidadesDestino.add(especialidadDAO.get(e.getId()));
		}
		destino.setEspecialidads(especialidadesDestino);
		
		destino.setEstadoConvocatoria(estadoConvocatoriaDAO.get(4));
		destino.setFechaBoe(origen.getFechaBoe());
		destino.setFechaFin(origen.getFechaFin());
		destino.setFechaInicio(origen.getFechaInicio());
		destino.setFormaAcceso(origen.getFormaAcceso());
		destino.setFechaFinSubsanacion(origen.getFechaFinSubsanacion());
		destino.setFechaIniSubsanacion(origen.getFechaIniSubsanacion());

		
		
		Set<MotivoReduccionTasa> motivosOrigen= origen.getMotivoReduccionTasas();
		Set<MotivoReduccionTasa> motivosDestino = new HashSet<MotivoReduccionTasa>();
		it = motivosOrigen.iterator();
		while(it.hasNext()){
			logger.info("iterator MotivoReduccionTasa");
			MotivoReduccionTasa m=(MotivoReduccionTasa)it.next();
			
			motivosDestino.add(motivoReduccionTasaDAO.get(m.getId()));
		}
		destino.setMotivoReduccionTasas(motivosDestino);
		
	
		
		
		destino.setNPlazasDiscapacitados(origen.getNPlazasDiscapacitados());
		destino.setNPlazasTotal(origen.getNPlazasTotal());
			
		
		
		Plantilla plantilla =origen.getPlantilla();
		long idPlantilla = plantillaDAO.insert(this.copiaPlantilla(plantilla, null));
		plantilla = plantillaDAO.get(idPlantilla);
		destino.setPlantilla(plantilla);
		
		destino.setProvinciasExamen(origen.getProvinciasExamen());
		
		Set<ProvinciaExamen> provinciasOrigen= origen.getProvinciasExamen();
		Set<ProvinciaExamen> provinciaDestino = new HashSet<ProvinciaExamen>();
		it = provinciasOrigen.iterator();
		while(it.hasNext()){
			logger.info("iterator Provincia");
			ProvinciaExamen p=(ProvinciaExamen)it.next();		
			provinciaDestino.add(provinciaExamenDAO.get(p.getId()));
		}
		destino.setProvinciasExamen(provinciaDestino);
		
		
				
		destino.setTipoDocumentoPermitido(origen.getTipoDocumentoPermitido());
		destino.setTipoCargaSolPresencial(origen.getTipoCargaSolPresencial());
		
		Set<TituloOficial> titulosOrigen= origen.getTituloOficials();
		Set<TituloOficial> titulosDestino = new HashSet<TituloOficial>();
		
		
		
		
		Iterator<TituloOficial> it2 = titulosOrigen.iterator();
		while(it2.hasNext()){
			logger.info("iterator TituloOficial");
			TituloOficial t=(TituloOficial)it2.next();
			
			titulosDestino.add(tituloOficialDAO.get(t.getId()));
		}
		destino.setTituloOficials(titulosDestino);
		
		if (origen.getOtrosTitulos() != null && origen.getOtrosTitulos().size() > 0) {
			Set<OtrosTitulos> otrosTitulosDestino = new HashSet<OtrosTitulos>();
			Iterator<OtrosTitulos> it3 = origen.getOtrosTitulos().iterator();
			while(it3.hasNext()){
				logger.info("iterator OtrosTitulos");
				OtrosTitulos t=(OtrosTitulos)it3.next();
				
				otrosTitulosDestino.add(otrosTitulosDAO.get(t.getIdOtroTitulo()));
			}
			destino.setOtrosTitulos(otrosTitulosDestino);
		}
		
		if (origen.getDiscapacidad() != null && origen.getDiscapacidad().size() > 0) {
			Set<Discapacidad> discapacidadDestino = new HashSet<Discapacidad>();
			Iterator<Discapacidad> it4 = origen.getDiscapacidad().iterator();
			while(it4.hasNext()){
				logger.info("iterator Discapacidad");
				Discapacidad t=(Discapacidad)it4.next();
				
				discapacidadDestino.add(discapacidadDAO.get(t.getIdDiscapacidad()));
			}
			destino.setDiscapacidad(discapacidadDestino);
		}
		
		if (origen.getDatosPropiosConfiguracion() != null && origen.getDatosPropiosConfiguracion().size() > 0) {
			Set<DatosPropiosConfiguracion> datosPropiosDestino = new HashSet<DatosPropiosConfiguracion>();
			Iterator<DatosPropiosConfiguracion> it5 = origen.getDatosPropiosConfiguracion().iterator();
			while(it5.hasNext()){
				logger.info("iterator Discapacidad");
				DatosPropiosConfiguracion t=(DatosPropiosConfiguracion)it5.next();
				
				datosPropiosDestino.add(datosPropiosConfiguracionDAO.get(t.getIdDesplegable()));
			}
			destino.setDatosPropiosConfiguracion(datosPropiosDestino);
		}	
		
		Long idNuevo = convocatoriaDAO.insert(destino);
		
		PlantillaPropiosQuery plantPropQuery = new PlantillaPropiosQuery();
		ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
		convocatoriaQuery.setId(origen.getId());
		plantPropQuery.setConvocatoria(convocatoriaQuery);
		plantPropQuery.setTipoPlantilla('C');
		List<PlantillaPropios> propiosOrigen = plantillaPropiosDAO.search(plantPropQuery).getResults();
		
		for(PlantillaPropios propOri : propiosOrigen ){
			PlantillaPropios propDest = new PlantillaPropios();
			propDest.setCamposPropios(propOri.getCamposPropios());
			propDest.setCentroGestor(propOri.getCentroGestor());
			propDest.setModelo(propOri.getModelo());
			propDest.setObligatorio(propOri.getObligatorio());
			propDest.setTipoPlantilla(propOri.getTipoPlantilla());
			Convocatoria c = new Convocatoria();
			c.setId(idNuevo);
			propDest.setConvocatoria(c);
			plantillaPropiosDAO.insert(propDest);
		}
		
		return idNuevo;
		
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ConvocatoriasManager#obtenerModificarConvocatoria(es.map.ips.model.ConvocatoriaQuery)
	 */
	public ModificarConvocatoriaBean obtenerModificarConvocatoria(ConvocatoriaQuery convocatoriaQuery){
			
			Convocatoria convocatorias;
			convocatorias = convocatoriaDAO.searchUnique(convocatoriaQuery);
			return toModificarConvocatoriaBean(convocatorias);
	}
	
	/**
	 * To modificar convocatoria bean.
	 *
	 * @param con el con
	 * @return el modificar convocatoria bean
	 */
	private ModificarConvocatoriaBean toModificarConvocatoriaBean(Convocatoria con){
		ModificarConvocatoriaBean modificarConvocatoriaBean = new ModificarConvocatoriaBean();
		modificarConvocatoriaBean.setDirigidoA(con.getDirigidoA());
		modificarConvocatoriaBean.setEnlace(con.getEnlace());
		modificarConvocatoriaBean.setIdConvocatoria(con.getId());
		modificarConvocatoriaBean.setEjercicio(con.getEjercicio());
		//Se carga el estado de la convocatoria para la subsanacion
		obtenerEstadoConvocatoria(con, modificarConvocatoriaBean);
		
		
		toModificarConvocatoriaBeanAux(con,modificarConvocatoriaBean);
		
		Set<ProvinciaExamen> provinciasOrigen= con.getProvinciasExamen();
		ArrayList<ProvinciaExamenBean> provinciaDestino = new ArrayList<ProvinciaExamenBean>();
		Iterator<?> it = provinciasOrigen.iterator();
		while(it.hasNext()){
			logger.info("iterator Provincia");
			ProvinciaExamen p=(ProvinciaExamen)it.next();
			if(p.getEstado()=='A'){
				ProvinciaExamenBean provinciaExamenBean= new ProvinciaExamenBean();
				provinciaExamenBean.setId(p.getId());
				provinciaExamenBean.setDescripcion(p.getDescripcion());
				
				provinciaDestino.add(provinciaExamenBean);
			}
			
		}
		modificarConvocatoriaBean.setProvincias(provinciaDestino);
		
		
		Set<Especialidad> especialidadOrigen= con.getEspecialidads();
		ArrayList<EspecialidadBean> especialidadDestino = new ArrayList<EspecialidadBean>();
		it = especialidadOrigen.iterator();
		while(it.hasNext()){
			logger.info("iterator Especialidad");
			Especialidad p=(Especialidad)it.next();
			if(p.getEstado()=='A'){
				EspecialidadBean especialidadBean= new EspecialidadBean();
				especialidadBean.setId(p.getId());
				especialidadBean.setDescripcion(p.getDescripcion());
				
				especialidadDestino.add(especialidadBean);
			}
			
		}
		modificarConvocatoriaBean.setEspecialidades(especialidadDestino);
		
		
		Set<MotivoReduccionTasa> motivoOrigen= con.getMotivoReduccionTasas();
		ArrayList<MotivoReduccionTasaBean> motivoDestino = new ArrayList<MotivoReduccionTasaBean>();
		it = motivoOrigen.iterator();
		while(it.hasNext()){
			logger.info("iterator Motivo Reduccion Tasa");
			MotivoReduccionTasa p=(MotivoReduccionTasa)it.next();
			if(p.getEstado()=='A'){
				MotivoReduccionTasaBean motivoBean= new MotivoReduccionTasaBean();
				motivoBean.setId(p.getId());
				motivoBean.setDescripcion(p.getDescripcion());
				
				motivoDestino.add(motivoBean);
			}
			
		}
		modificarConvocatoriaBean.setMotivosExencion(motivoDestino);
		
		
		Set<TituloOficial> tituloOrigen= con.getTituloOficials();
		ArrayList<TituloOficialBean> tituloDestino = new ArrayList<TituloOficialBean>();
		it = tituloOrigen.iterator();
		while(it.hasNext()){
			logger.info("iterator Titulos");
			TituloOficial p=(TituloOficial)it.next();
			if(p.getEstado()=='A'){
				TituloOficialBean tituloBean= new TituloOficialBean();
				tituloBean.setId(p.getId());
				tituloBean.setDescripcion(p.getDescripcion());
				
				tituloDestino.add(tituloBean);
			}
			
		}
		modificarConvocatoriaBean.setTitulos(tituloDestino);
		
		
		Set<OtrosTitulos> otroTituloOrigen = con.getOtrosTitulos();
		ArrayList<OtrosTitulosBean> otroTituloDestino = new ArrayList<OtrosTitulosBean>();
		it = otroTituloOrigen.iterator();
		while(it.hasNext()){
			logger.info("iterator Titulos");
			OtrosTitulos p=(OtrosTitulos)it.next();
			if(p.getVisible() =='S'){
				OtrosTitulosBean tituloBean= new OtrosTitulosBean();
				tituloBean.setId(p.getIdOtroTitulo());
				tituloBean.setDescripcion(p.getDescripcion());
				
				otroTituloDestino.add(tituloBean);
			}
			
		}
		modificarConvocatoriaBean.setOtrosTitulos(otroTituloDestino);
		
		
		Set<Discapacidad> discapacidadOrigen = con.getDiscapacidad();
		ArrayList<DiscapacidadBean> discapacidadDestino = new ArrayList<DiscapacidadBean>();
		it = discapacidadOrigen.iterator();
		while(it.hasNext()){
			logger.info("iterator Titulos");
			Discapacidad p=(Discapacidad)it.next();
			if(p.getVisible() =='S'){
				DiscapacidadBean discapacidadBean= new DiscapacidadBean();
				discapacidadBean.setId(p.getIdDiscapacidad());
				discapacidadBean.setDescripcion(p.getDescripcion());
				
				discapacidadDestino.add(discapacidadBean);
			}
			
		}
		modificarConvocatoriaBean.setDiscapacidades(discapacidadDestino);
		
		Set<DatosPropiosConfiguracion> datosPropiosOrigen = con.getDatosPropiosConfiguracion();
		ArrayList<DatosPropiosConfigBean> datosPropiosDestino = new ArrayList<DatosPropiosConfigBean>();
		it = datosPropiosOrigen.iterator();
		while(it.hasNext()){
			logger.info("iterator Titulos");
			DatosPropiosConfiguracion p=(DatosPropiosConfiguracion)it.next();
			if (p != null && p.getIdDesplegable() != null && p.getIdDesplegable() > 0) {
				DatosPropiosConfiguracionQuery datosPropiosConfiguracionQuery = new DatosPropiosConfiguracionQuery();
				datosPropiosConfiguracionQuery.setIdDesplegable(p.getIdDesplegable());
				datosPropiosConfiguracionQuery.setJoin_campo(true);
				p = datosPropiosConfiguracionDAO.searchUnique(datosPropiosConfiguracionQuery);
			}
			DatosPropiosConfigBean datosPropiosBean= new DatosPropiosConfigBean();
			datosPropiosBean.setIdDesplegable(p.getIdDesplegable().longValue());
			datosPropiosBean.setDescripcion(p.getDescripcion());
			datosPropiosBean.setCampo(p.getCamposPropios());
			
			datosPropiosDestino.add(datosPropiosBean);
			
		}
		modificarConvocatoriaBean.setDatosPropiosConfigBean(datosPropiosDestino);
		
		modificarConvocatoriaBean.setOcultarDatosPropios(con.getOcultarDatosPropios());
		
		toModificarConvocatoriaBeanAux2(con,modificarConvocatoriaBean);
		
		return modificarConvocatoriaBean;
	}

	/**
	 * Obtener estado convocatoria.
	 *
	 * @param con el con
	 * @param modificarConvocatoriaBean el modificar convocatoria bean
	 */
	private void obtenerEstadoConvocatoria(Convocatoria con, ModificarConvocatoriaBean modificarConvocatoriaBean) {
		if (con.getEstadoConvocatoria() != null && !StringUtils.isEmpty(con.getEstadoConvocatoria().getDescripcion())) {
			modificarConvocatoriaBean.setEstado(con.getEstadoConvocatoria().getDescripcion());
		}
	}

	/**
	 * To modificar convocatoria bean aux 2.
	 *
	 * @param con el con
	 * @param modificarConvocatoriaBean el modificar convocatoria bean
	 */
	private void toModificarConvocatoriaBeanAux2(Convocatoria con, ModificarConvocatoriaBean modificarConvocatoriaBean) {
		if(con.getPresencial() != null){
			if(con.getPresencial().equals('S')){
				modificarConvocatoriaBean.setPresencial(true);
			}else{
				modificarConvocatoriaBean.setPresencial(false);
			}
		}else{
			modificarConvocatoriaBean.setPresencial(false);
		}
		
		if(con.getOcultarDatosPropios() != null) {
			if(con.getOcultarDatosPropios().equals('S')) {
				modificarConvocatoriaBean.setOcultarDatosPropios('S');
			} else {
				modificarConvocatoriaBean.setOcultarDatosPropios('N');
			}
		}
	}
	
	/**
	 * To modificar convocatoria bean aux.
	 *
	 * @param con el con
	 * @param modificarConvocatoriaBean el modificar convocatoria bean
	 */
	private void toModificarConvocatoriaBeanAux(Convocatoria con, ModificarConvocatoriaBean modificarConvocatoriaBean) {
		
		SimpleDateFormat formatoFecha = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
		
		if (con.getFechaBoe() != null) {
			modificarConvocatoriaBean.setFechaBoe(formatoFecha.format(con.getFechaBoe()));
		}else{
			modificarConvocatoriaBean.setFechaBoe("");
		}
		
		if (con.getFechaInicio() != null) {
			modificarConvocatoriaBean.setFechaInicio(formatoFecha.format(con.getFechaInicio()));
		}else{
			modificarConvocatoriaBean.setFechaInicio("");
		}
		
		if (con.getFechaFin() != null) {
			modificarConvocatoriaBean.setFechaTermino(formatoFecha.format(con.getFechaFin()));
		}else{
			modificarConvocatoriaBean.setFechaTermino("");
		}
		
		if (con.getFechaIniSubsanacion() != null) {
			modificarConvocatoriaBean.setFechaInicioSub(formatoFecha.format(con.getFechaIniSubsanacion()));
		}else{
			modificarConvocatoriaBean.setFechaInicioSub("");
		}
		
		if (con.getFechaFinSubsanacion() != null) {
			modificarConvocatoriaBean.setFechaFinSub(formatoFecha.format(con.getFechaFinSubsanacion()));
		}else{
			modificarConvocatoriaBean.setFechaFinSub("");
		}		
		
		modificarConvocatoriaBean.setIdCentroGestor(con.getCuerpoEscala().getCentroGestor().getIdCentroGestor());
		modificarConvocatoriaBean.setDsCentroGestor(con.getCuerpoEscala().getCentroGestor().getDescripcion());
		if(con.getMinisterioConvocante()!=null){
		modificarConvocatoriaBean.setIdMinisterioConvocante(con.getMinisterioConvocante().getId().toString());
		}else{
		modificarConvocatoriaBean.setIdMinisterioConvocante(con.getCuerpoEscala().getCentroGestor().getMinisterio().getId().toString());
		}
		modificarConvocatoriaBean.setIdCuerpoEscala(con.getCuerpoEscala().getId());
		modificarConvocatoriaBean.setDsCuerpoEscala(con.getCuerpoEscala().getDescripcion());
		modificarConvocatoriaBean.setImporte(con.getImporte());		
		modificarConvocatoriaBean.setNumPlazas(con.getNPlazasTotal());
		modificarConvocatoriaBean.setNumPlazasDisc(con.getNPlazasDiscapacitados());
		modificarConvocatoriaBean.setTipoDocumentacion(con.getTipoDocumentoPermitido());
		
		FormaAccesoBean formaAccesoBean = new FormaAccesoBean();
		if(con.getFormaAcceso().getEstado()=='A'){
			formaAccesoBean.setId(con.getFormaAcceso().getId());
			formaAccesoBean.setDescripcion(con.getFormaAcceso().getDescripcion());
		}else{
			formaAccesoBean.setId(0);
			formaAccesoBean.setDescripcion("");
		}
		modificarConvocatoriaBean.setFormaAcceso(formaAccesoBean);
		if(con.getCodigoFormaAcceso() != null)
		{	
			if(!con.getCodigoFormaAcceso().equals(con.getFormaAcceso().getCodigo())) {	
				modificarConvocatoriaBean.setCodigoFormaAcceso(con.getCodigoFormaAcceso());
			} else {
				modificarConvocatoriaBean.setCodigoFormaAcceso(null);
			}	
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.ConvocatoriasManager#modificarConvocatoria(es.map.ipsg.bean.CrearConvocatoriaBean, es.map.ipsg.form.CrearConvocatoriaForm)
	 */
	public void modificarConvocatoria(CrearConvocatoriaBean bean, CrearConvocatoriaForm formulario){
		logger.info("Entrada en modificarConvocatoria ConvocatoriaManager");
		Convocatoria con = new Convocatoria();
		con.setId(bean.getIdConvocatoria());
		con.setEjercicio(bean.getEjercicio());
		if(bean.getIdModeloAsociado()!=null){
			Modelo modelo = new Modelo();
			modelo.setIdModelo(Integer.parseInt(bean.getIdModeloAsociado()));
			con.setModelo(modelo);
		}
		con.setCuerpoEscala(cuerpoEscalaDAO.get(bean.getIdCuerpoEscala()));
		logger.info(cuerpoEscalaDAO.get(bean.getIdCuerpoEscala())
				.getDescripcion());
		con.setMinisterioConvocante(ministerioDAO.get(bean.getIdMinisterioConvocante()));
		con.setNPlazasTotal(bean.getNumPlazas());
		con.setNPlazasDiscapacitados(bean.getNumPlazasDisc());
		con.setFormaAcceso(formaAccesoDAO.get(bean.getIdFormaAcceso()));
		
		FormaAcceso fAux= formaAccesoDAO.get(bean.getIdFormaAcceso());
		
		
		if(bean.getCodigoFormaAcceso() != null && !bean.getCodigoFormaAcceso().equals(fAux.getCodigo()))
		{	
			con.setCodigoFormaAcceso(bean.getCodigoFormaAcceso());
		}
		else
		{
			con.setCodigoFormaAcceso(null);
		}	
		con.setImporte(bean.getImporte());
		con.setDirigidoA(bean.getDirigidoA());
		con.setTipoDocumentoPermitido(bean.getTipoDocumentacion());
		con.setEnlace(bean.getEnlace());

		//Carga de los titulos oficiales
		cargarTitulosOficiales(bean, con);

		// Empezamos la introduccion de datos opcionales
		SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
		//Fechas ini y fin convocatoria y fecha boe
		cargarFechasConvocatoria(bean, con, formatoDelTexto);
		
		//Fechas de Subsanacion
		cargarFechasSub(bean, con, formatoDelTexto);	
		
		//Campos de opciones de desplegables
		cargarCamposConIds(bean, con);		
		
		con.setFechaNacimientoMaxima(bean.getFechaNacimientoMaxima());
		con.setFechaNacimientoMinima(bean.getFechaNacimientoMinima());
		

		// Recuperamos la plantilla
		//La plantilla no se actualiza en esta ventana, se le pone la misma que ya tiene;
		Plantilla plantilla;
		plantilla=convocatoriaDAO.get(bean.getIdConvocatoria()).getPlantilla();
		//Se comprueba los flags de los campos de texto libre
		plantilla.setOtrosTitulos((formulario != null && formulario.isOtrosTitulosFlag())?'S':'N');
		plantilla.setDetalleDiscapacidad((formulario != null && formulario.isDiscapacidadFlag())?'S':'N');
		con.setPlantilla(plantilla);

		//El estado no se actualiza solo se pueden modificar convocatorias en estado CONFIGURACION
		con.setEstadoConvocatoria(convocatoriaDAO.get(bean.getIdConvocatoria()).getEstadoConvocatoria());
		if(bean.getPresencial() != null){
			con.setPresencial(bean.getPresencial());
		}else{
			con.setPresencial('N');
		}
		
		con.setEnlace(bean.getEnlace());
		
		//Actualizamos el estado de los datos propios si el check de ocultar esta marcado
		con.setOcultarDatosPropios(bean.getOcultarDatosPropios());

		convocatoriaDAO.update(con);
		
	}

	/**
	 * Cargar titulos oficiales.
	 *
	 * @param bean el bean
	 * @param con el con
	 */
	private void cargarTitulosOficiales(CrearConvocatoriaBean bean, Convocatoria con) {
		HashSet<TituloOficial> titulos = new HashSet<TituloOficial>();

		int[] idTitulos = bean.getIdTitulos();
		for (int i = 0; i < bean.getIdTitulos().length; i++) {
			titulos.add(tituloOficialDAO.get(idTitulos[i]));
		}
		con.setTituloOficials(titulos);
	}

	/**
	 * Cargar fechas convocatoria.
	 *
	 * @param bean el bean
	 * @param con el con
	 * @param formatoDelTexto el formato del texto
	 */
	private void cargarFechasConvocatoria(CrearConvocatoriaBean bean, Convocatoria con, SimpleDateFormat formatoDelTexto) {
	
		if (bean.getFechaBoe() != null) {
			Date fechaBoe = null;
			try {

				fechaBoe = formatoDelTexto.parse(bean.getFechaBoe());

			} catch (ParseException ex) {

				logger.error(" Error ConvocatoriasManagerImpl - modificarConvocatoria -  parsear fechaBoe"+ fechaBoe,ex);

			}
			con.setFechaBoe(fechaBoe);
		}
		if (bean.getFechaInicio() != null) {
			Date fechaInicio = null;
			try {

				fechaInicio = formatoDelTexto.parse(bean.getFechaInicio());

			} catch (ParseException ex) {

				logger.error(" Error ConvocatoriasManagerImpl - modificarConvocatoria -  parsear fechaInicio"+ fechaInicio,ex);

			}
			con.setFechaInicio(fechaInicio);
		}
		if (bean.getFechaTermino() != null) {
			Date fechaTermino = null;
			try {

				fechaTermino = formatoDelTexto.parse(bean.getFechaTermino());

			} catch (ParseException ex) {

				logger.error(" Error ConvocatoriasManagerImpl - modificarConvocatoria -  parsear fechaTermino"+ fechaTermino,ex);

			}
			con.setFechaFin(fechaTermino);
		}
	}

	/**
	 * Cargar fechas sub.
	 *
	 * @param bean el bean
	 * @param con el con
	 * @param formatoDelTexto el formato del texto
	 */
	private void cargarFechasSub(CrearConvocatoriaBean bean, Convocatoria con, SimpleDateFormat formatoDelTexto) {
		if (!StringUtils.isEmpty(bean.getFechaIniSub())) {
			Date fechaInicioSub = null;
			try {

				fechaInicioSub = formatoDelTexto.parse(bean.getFechaIniSub());

			} catch (ParseException ex) {

				logger.error(" Error ConvocatoriasManagerImpl - modificarConvocatoria -  parsear fechaInicio: "+ fechaInicioSub,ex);

			}
			con.setFechaIniSubsanacion(fechaInicioSub);
		}
		if (!StringUtils.isEmpty(bean.getFechaFinSub())) {
			Date fechaFinSub = null;
			try {

				fechaFinSub = formatoDelTexto.parse(bean.getFechaFinSub());

			} catch (ParseException ex) {

				logger.error(" Error ConvocatoriasManagerImpl - modificarConvocatoria -  parsear fechaTermino: "+ fechaFinSub,ex);

			}
			con.setFechaFinSubsanacion(fechaFinSub);
		}
	}

	/**
	 * Cargar campos con ids.
	 *
	 * @param bean el bean
	 * @param con el con
	 */
	private void cargarCamposConIds(CrearConvocatoriaBean bean, Convocatoria con) {
		if (bean.getIdProvincias() != null) {
			HashSet<ProvinciaExamen> provinciasExamen = new HashSet<ProvinciaExamen>();
			int[] idProvinciasExamen = bean.getIdProvincias();
			for (int i = 0; i < bean.getIdProvincias().length; i++) {
				provinciasExamen.add(provinciaExamenDAO.get(idProvinciasExamen[i]));
			}
			con.setProvinciasExamen(provinciasExamen);
		}
		if (bean.getIdMotivosExencion() != null) {
			HashSet<MotivoReduccionTasa> motivos = new HashSet<MotivoReduccionTasa>();

			int[] idMotivos = bean.getIdMotivosExencion();
			for (int i = 0; i < bean.getIdMotivosExencion().length; i++) {
				motivos.add(motivoReduccionTasaDAO.get(idMotivos[i]));
			}
			con.setMotivoReduccionTasas(motivos);
		}
		if (bean.getIdEspecialidades() != null) {
			
			HashSet<Especialidad> especialidades = new HashSet<Especialidad>();

			int[] idEspecialidades = bean.getIdEspecialidades();
			for (int i = 0; i < bean.getIdEspecialidades().length; i++) {
				especialidades.add(especialidadDAO.get(idEspecialidades[i]));
			}
			con.setEspecialidads(especialidades);
		}
		//Otros Titulos
		if (bean.getIdOtrosTitulos() != null) {
		
			HashSet<OtrosTitulos> otrosTitulos = new HashSet<OtrosTitulos>();

			int[] idOtrosTitulos = bean.getIdOtrosTitulos();
			for (int i = 0; i < bean.getIdOtrosTitulos().length; i++) {
				otrosTitulos.add(otrosTitulosDAO.get(idOtrosTitulos[i]));
			}
			con.setOtrosTitulos(otrosTitulos);
		}
		//Discapacidad
		if (bean.getIdDiscapacidad() != null) {

			HashSet<Discapacidad> discapacidad = new HashSet<Discapacidad>();
		
			int[] idDiscapacidad = bean.getIdDiscapacidad();
			for (int i = 0; i < bean.getIdDiscapacidad().length; i++) {
				discapacidad.add(discapacidadDAO.get(idDiscapacidad[i]));
			}
			con.setDiscapacidad(discapacidad);
		}
		//Datos Propios
		if (bean.getIdDatosPropios() != null) {

			HashSet<DatosPropiosConfiguracion> datosPropiosConf = new HashSet<DatosPropiosConfiguracion>();
			DatosPropiosConfiguracionQuery datosPropiosConfiguracionQuery = new DatosPropiosConfiguracionQuery();
			datosPropiosConfiguracionQuery.setJoin_campo(true);
			
			int[] idDatosPropios = bean.getIdDatosPropios();
			for (int i = 0; i < bean.getIdDatosPropios().length; i++) {
				datosPropiosConfiguracionQuery.setIdDesplegable(idDatosPropios[i]);
				datosPropiosConf.add(datosPropiosConfiguracionDAO.searchUnique(datosPropiosConfiguracionQuery));
			}
			con.setDatosPropiosConfiguracion(datosPropiosConf);
		}
	}
	
	/**
	 * Modifica la convocatoria con los campos que se le pase en el entity.
	 *
	 * @param convocatoria Convocatoria
	 */
	public void modificarCamposConvocatoria (Convocatoria convocatoria)
	{
		convocatoriaDAO.update(convocatoria);
	}
	
	/**
	 * Calcular ejercicio.
	 *
	 * @param idCentroGestor el id centro gestor
	 * @return el string
	 */
	private String calcularEjercicio(int idCentroGestor){

		CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
		centroGestorQuery.setId(idCentroGestor);
		
		CentroGestor centroGestor = centroGestorDAO.get(idCentroGestor);
		
		return (centroGestor!=null ? centroGestor.getEjercicio(): null);
	}
	
	
	
	/**
	 * Obtiene el plantilla propios DAO.
	 *
	 * @return el plantilla propios DAO
	 */
	public PlantillaPropiosDAO getPlantillaPropiosDAO() {
		return plantillaPropiosDAO;
	}

	/**
	 * Establece el plantilla propios DAO.
	 *
	 * @param plantillaPropiosDAO el nuevo plantilla propios DAO
	 */
	public void setPlantillaPropiosDAO(PlantillaPropiosDAO plantillaPropiosDAO) {
		this.plantillaPropiosDAO = plantillaPropiosDAO;
	}
	
	/**
	 * Obtiene el campo propio DAO.
	 *
	 * @return el campo propio DAO
	 */
	public CamposPropiosDAO getCampoPropioDAO() {
		return campoPropioDAO;
	}

	/**
	 * Establece el campo propio DAO.
	 *
	 * @param campoPropioDAO el nuevo campo propio DAO
	 */
	public void setCampoPropioDAO(CamposPropiosDAO campoPropioDAO) {
		this.campoPropioDAO = campoPropioDAO;
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
	 * Obtiene el log convocatoria DAO.
	 *
	 * @return el log convocatoria DAO
	 */
	public LogConvocatoriaDAO getLogConvocatoriaDAO() {
		return logConvocatoriaDAO;
	}

	/**
	 * Establece el log convocatoria DAO.
	 *
	 * @param logConvocatoriaDAO el nuevo log convocatoria DAO
	 */
	public void setLogConvocatoriaDAO(LogConvocatoriaDAO logConvocatoriaDAO) {
		this.logConvocatoriaDAO = logConvocatoriaDAO;
	}
	
	/**
	 * Obtiene el estado convocatoria DAO.
	 *
	 * @return el estado convocatoria DAO
	 */
	public EstadoConvocatoriaDAO getEstadoConvocatoriaDAO() {
		return estadoConvocatoriaDAO;
	}

	/**
	 * Establece el estado convocatoria DAO.
	 *
	 * @param estadoConvocatoriaDAO el nuevo estado convocatoria DAO
	 */
	public void setEstadoConvocatoriaDAO(
			EstadoConvocatoriaDAO estadoConvocatoriaDAO) {
		this.estadoConvocatoriaDAO = estadoConvocatoriaDAO;
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
	 * Obtiene el plantilla DAO.
	 *
	 * @return el plantilla DAO
	 */
	public PlantillaDAO getPlantillaDAO() {
		return plantillaDAO;
	}

	/**
	 * Establece el plantilla DAO.
	 *
	 * @param plantillaDAO el nuevo plantilla DAO
	 */
	public void setPlantillaDAO(PlantillaDAO plantillaDAO) {
		this.plantillaDAO = plantillaDAO;
	}

	/**
	 * Obtiene el tarifa DAO.
	 *
	 * @return el tarifa DAO
	 */
	public TarifaDAO getTarifaDAO() {
		return tarifaDAO;
	}

	/**
	 * Establece el tarifa DAO.
	 *
	 * @param tarifaDAO el nuevo tarifa DAO
	 */
	public void setTarifaDAO(TarifaDAO tarifaDAO) {
		this.tarifaDAO = tarifaDAO;
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
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
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

	/**
	 * Obtiene el datos propios configuracion DAO.
	 *
	 * @return el datos propios configuracion DAO
	 */
	public DatosPropiosConfiguracionDAO getDatosPropiosConfiguracionDAO() {
		return datosPropiosConfiguracionDAO;
	}

	/**
	 * Establece el datos propios configuracion DAO.
	 *
	 * @param datosPropiosConfiguracionDAO el nuevo datos propios configuracion DAO
	 */
	public void setDatosPropiosConfiguracionDAO(DatosPropiosConfiguracionDAO datosPropiosConfiguracionDAO) {
		this.datosPropiosConfiguracionDAO = datosPropiosConfiguracionDAO;
	}
	
}
