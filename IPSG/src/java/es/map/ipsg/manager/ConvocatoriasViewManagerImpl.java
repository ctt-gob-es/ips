package es.map.ipsg.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.ConvocatoriasViewDAO;
import es.map.ips.model.ConvocatoriasView;
import es.map.ips.model.ConvocatoriasViewQuery;
import es.map.ipsg.bean.ConvocatoriasViewBean;


/**
 *  Clase que implementa el ConvocatoriasViewManager.
 *
 * @author amartinl
 */
public class ConvocatoriasViewManagerImpl implements ConvocatoriasViewManager {

	/** el convocatorias view DAO. */
	//Variables
	private ConvocatoriasViewDAO convocatoriasViewDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ConvocatoriasViewManagerImpl.class);
	
	/** La constante STRING_SIMPLEDATEFORMAT. */
	private static final String STRING_SIMPLEDATEFORMAT = "dd/MM/yyyy";
	
	/**
	 * Buscar convocatorias view combo.
	 *
	 * @param convocatoriasViewQuery ConvocatoriasViewQuery
	 * @return arrConvocatoriasView ArrayList
	 */
	public ArrayList<ConvocatoriasViewBean> buscarConvocatoriasViewCombo(ConvocatoriasViewQuery convocatoriasViewQuery){
		SearchResult<ConvocatoriasView> convocatoriasView = buscarConvocatoriasView(convocatoriasViewQuery);
		ArrayList<ConvocatoriasViewBean> arrConvocatoriasView= new ArrayList<ConvocatoriasViewBean>();
		for(int i=0;i<convocatoriasView.getResults().size();i++){
			ConvocatoriasViewBean aux;
			aux = toConvocatoriasViewComboBean(convocatoriasView.getResults().get(i));
			if(aux != null){
				arrConvocatoriasView.add(aux);
			}
		}	
		return arrConvocatoriasView;		
	}

	/**
	 * Buscar convocatorias view.
	 *
	 * @param convocatoriasViewQuery el convocatorias view query
	 * @return el search result
	 */
	private SearchResult<ConvocatoriasView> buscarConvocatoriasView(ConvocatoriasViewQuery convocatoriasViewQuery) {
			ApplicationException.assertNotNull(convocatoriasViewQuery, "convocatoriasViewQuery no puede ser null");
		
		return convocatoriasViewDAO.search(convocatoriasViewQuery);
	}

	
	/**
	 * Buscar convocatorias view all.
	 *
	 * @param convocatoriasViewQuery ConvocatoriasViewQuery
	 * @return arrConvocatoriasView ArrayList
	 */
	public ArrayList<ConvocatoriasViewBean> buscarConvocatoriasViewAll(ConvocatoriasViewQuery convocatoriasViewQuery){		
		SearchResult<ConvocatoriasView> convocatoriasView = buscarConvocatoriasView(convocatoriasViewQuery);
		Integer numTotal = convocatoriasView.getNumResults();
		int iNumTotal = 0;
		if (numTotal != null){
			iNumTotal = numTotal.intValue();
		}
		ArrayList<ConvocatoriasViewBean> arrConvocatoriasView = new ArrayList<ConvocatoriasViewBean>();
		for(int i=0;i<convocatoriasView.getResults().size();i++){
			ConvocatoriasViewBean aux;
			aux = toConvocatoriasViewBean(convocatoriasView.getResults().get(i), iNumTotal);
			if(aux != null){
				arrConvocatoriasView.add(aux);
			}
		}	
		return arrConvocatoriasView;		
	}
	
	/**
	 * To convocatorias view combo bean.
	 *
	 * @param convocatoriasView el convocatorias view
	 * @return el convocatorias view bean
	 */
	private ConvocatoriasViewBean toConvocatoriasViewComboBean(ConvocatoriasView convocatoriasView) {
		
		ConvocatoriasViewBean auxConvocatoriasViewBean = new ConvocatoriasViewBean();
		auxConvocatoriasViewBean.setDesCentroGestor(convocatoriasView.getDesCentroGestor());
		auxConvocatoriasViewBean.setDesCuerpoEscala(convocatoriasView.getDesCuerpoEscala());
		auxConvocatoriasViewBean.setDesEstadoConvocatoria(convocatoriasView.getDesEstadoConvocatoria());
		auxConvocatoriasViewBean.setDesFormaAcceso(convocatoriasView.getDesFormaAcceso());
		auxConvocatoriasViewBean.setDesGrupo(convocatoriasView.getDesGrupo());
		auxConvocatoriasViewBean.setDesMinisterio(convocatoriasView.getDesMinisterio());
		auxConvocatoriasViewBean.setEjercicio(convocatoriasView.getEjercicio());
		
		SimpleDateFormat formatoFecha = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
		String fechaBoe = "";
		if (convocatoriasView.getFechaBoe() != null) {
			fechaBoe = formatoFecha.format(convocatoriasView.getFechaBoe());
			
		}
		auxConvocatoriasViewBean.setFechaBoe(fechaBoe);
		auxConvocatoriasViewBean.setIdCentroGestor(convocatoriasView.getIdCentroGestor());
		auxConvocatoriasViewBean.setIdConvocatoria(convocatoriasView.getIdConvocatoria());
		auxConvocatoriasViewBean.setIdCuerpoEscala(convocatoriasView.getIdCuerpoEscala());
		auxConvocatoriasViewBean.setIdEstadoConvocatoria(convocatoriasView.getIdEstadoConvocatoria());
		auxConvocatoriasViewBean.setIdFormaAcceso(convocatoriasView.getIdFormaAcceso());
		auxConvocatoriasViewBean.setIdGrupo(convocatoriasView.getIdGrupo());
		auxConvocatoriasViewBean.setIdMinisterio(convocatoriasView.getIdMinisterio());		
		auxConvocatoriasViewBean.setPlazasDiscapacitados(convocatoriasView.getPlazasDiscapacitados());
		auxConvocatoriasViewBean.setPlazasTotal(convocatoriasView.getPlazasTotal());
		
		return auxConvocatoriasViewBean;
	}
	
	
	/**
	 * To convocatorias view bean.
	 *
	 * @param convocatoriasView el convocatorias view
	 * @param numTotal el num total
	 * @return el convocatorias view bean
	 */
	private ConvocatoriasViewBean toConvocatoriasViewBean(ConvocatoriasView convocatoriasView, int numTotal) {
	
		
		ConvocatoriasViewBean auxConvocatoriasViewBean = new ConvocatoriasViewBean();
		auxConvocatoriasViewBean.setDesCentroGestor(convocatoriasView.getDesCentroGestor());
		auxConvocatoriasViewBean.setDesCuerpoEscala(convocatoriasView.getDesCuerpoEscala());
		auxConvocatoriasViewBean.setDesEstadoConvocatoria(convocatoriasView.getDesEstadoConvocatoria());
		auxConvocatoriasViewBean.setDesFormaAcceso(convocatoriasView.getDesFormaAcceso());
		auxConvocatoriasViewBean.setDesGrupo(convocatoriasView.getDesGrupo());
		auxConvocatoriasViewBean.setDesMinisterio(convocatoriasView.getDesMinisterio());
		auxConvocatoriasViewBean.setEjercicio(convocatoriasView.getEjercicio());
		SimpleDateFormat formatoFecha = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
		String fechaBoe = "";
		if (convocatoriasView.getFechaBoe() != null) {
			fechaBoe = formatoFecha.format(convocatoriasView.getFechaBoe());
			
		}
		auxConvocatoriasViewBean.setFechaBoe(fechaBoe);
		auxConvocatoriasViewBean.setIdCentroGestor(convocatoriasView.getIdCentroGestor());
		auxConvocatoriasViewBean.setIdConvocatoria(convocatoriasView.getIdConvocatoria());
		auxConvocatoriasViewBean.setIdCuerpoEscala(convocatoriasView.getIdCuerpoEscala());
		auxConvocatoriasViewBean.setIdEstadoConvocatoria(convocatoriasView.getIdEstadoConvocatoria());
		auxConvocatoriasViewBean.setIdFormaAcceso(convocatoriasView.getIdFormaAcceso());
		auxConvocatoriasViewBean.setIdGrupo(convocatoriasView.getIdGrupo());
		auxConvocatoriasViewBean.setIdMinisterio(convocatoriasView.getIdMinisterio());		
		auxConvocatoriasViewBean.setPlazasDiscapacitados(convocatoriasView.getPlazasDiscapacitados());
		auxConvocatoriasViewBean.setPlazasTotal(convocatoriasView.getPlazasTotal());
		
		return auxConvocatoriasViewBean;
	}
	
	/**
	 * Pasa de  ConvocatoriasViewBean a  ConvocatoriasView.
	 *
	 * @param convocatoriasViewBean ConvocatoriasViewBean
	 * @return auxConvocatoriasView ConvocatoriasView
	 */
	private ConvocatoriasView toConvocatoriasView(ConvocatoriasViewBean convocatoriasViewBean) {
		
		
		ConvocatoriasView auxConvocatoriasView  = new ConvocatoriasView();
		auxConvocatoriasView.setDesCentroGestor(convocatoriasViewBean.getDesCentroGestor());
		auxConvocatoriasView.setDesCuerpoEscala(convocatoriasViewBean.getDesCuerpoEscala());
		auxConvocatoriasView.setDesEstadoConvocatoria(convocatoriasViewBean.getDesEstadoConvocatoria());
		auxConvocatoriasView.setDesFormaAcceso(convocatoriasViewBean.getDesFormaAcceso());
		auxConvocatoriasView.setDesGrupo(convocatoriasViewBean.getDesGrupo());
		auxConvocatoriasView.setDesMinisterio(convocatoriasViewBean.getDesMinisterio());
		auxConvocatoriasView.setEjercicio(convocatoriasViewBean.getEjercicio());
		
		try
		{
			SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
			Date dFechaBoe = formatoDelTexto.parse(convocatoriasViewBean.getFechaBoe());
			auxConvocatoriasView.setFechaBoe(dFechaBoe);
		}catch (ParseException ex) {
			logger.error(" Error ConvocatoriasViewManagerImpl - toConvocatoriasView - parsear fecha",ex);
		}
		
		auxConvocatoriasView.setIdCentroGestor(convocatoriasViewBean.getIdCentroGestor());
		auxConvocatoriasView.setIdConvocatoria(convocatoriasViewBean.getIdConvocatoria());
		auxConvocatoriasView.setIdCuerpoEscala(convocatoriasViewBean.getIdCuerpoEscala());
		auxConvocatoriasView.setIdEstadoConvocatoria(convocatoriasViewBean.getIdEstadoConvocatoria());
		auxConvocatoriasView.setIdFormaAcceso(convocatoriasViewBean.getIdFormaAcceso());
		auxConvocatoriasView.setIdGrupo(convocatoriasViewBean.getIdGrupo());
		auxConvocatoriasView.setIdMinisterio(convocatoriasViewBean.getIdMinisterio());		
		auxConvocatoriasView.setPlazasDiscapacitados(convocatoriasViewBean.getPlazasDiscapacitados());
		auxConvocatoriasView.setPlazasTotal(convocatoriasViewBean.getPlazasTotal());
		
		return auxConvocatoriasView;
	}


	/**
	 * Obtiene el convocatorias view DAO.
	 *
	 * @return convocatoriasViewDAO ConvocatoriasViewDAO
	 */
	public ConvocatoriasViewDAO getConvocatoriasViewDAO() {
		return convocatoriasViewDAO;
	}

	/**
	 * Establece el convocatorias view DAO.
	 *
	 * @param convocatoriasViewDAO ConvocatoriasViewDAO
	 */
	public void setConvocatoriasViewDAO(ConvocatoriasViewDAO convocatoriasViewDAO) {
		this.convocatoriasViewDAO = convocatoriasViewDAO;
	}


	/**
	 * Obtiene el logger.
	 *
	 * @return logger Logger
	 */
	public static Logger getLogger() {
		return logger;
	}

}