package es.map.ipsc.spring.solicitudes;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.SolComunViewQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.BuscarSolicitudesBean;
import es.map.ipsc.bean.CiudadanoBean;
import es.map.ipsc.bean.MinisterioBean;
import es.map.ipsc.form.BuscarSolicitudesForm;
import es.map.ipsc.manager.ministerio.MinisterioManager;
import es.map.ipsc.manager.solicitudes.EstadoSolicitudManager;
import es.map.ipsc.manager.solicitudes.PagoSolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.tablaBase.TablaBaseManager;
import es.map.ipsc.spring.AbstractSecureSpring;

/**
 * El Class BuscarSolicitudesCerradasSpring.
 */
public class BuscarSolicitudesCerradasSpring extends AbstractSecureSpring {

	/** el estado solicitud manager. */
	private EstadoSolicitudManager estadoSolicitudManager;
	
	/** el solicitudes manager. */
	private SolicitudManager solicitudesManager;
	
	/** el ministerio manager. */
	private MinisterioManager ministerioManager;
	
	/** el tablas base manager. */
	private TablaBaseManager tablasBaseManager;
	
	/** el pago solicitud managar. */
	private PagoSolicitudManager pagoSolicitudManagar;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarSolicitudesCerradasSpring.class);
	
	/** La constante STRING_BUSCAR. */
	private static final String STRING_BUSCAR = "Buscar";
	
	/** La constante STRING_ERRORPARSEARNUMREGISTROS. */
	private static final String STRING_ERRORPARSEARNUMREGISTROS = "Error parsear num registros";

	/** el properties. */
	private static Properties properties;
	
	//Constantes
	
	/**
	 * Crea una nueva buscar solicitudes cerradas spring.
	 */
	public BuscarSolicitudesCerradasSpring() {
		try {
			setSolicitudesManager((SolicitudManager) getBean("solicitudesManager"));
			setMinisterioManager((MinisterioManager) getBean("ministerioManager"));
			setEstadoSolicitudManager((EstadoSolicitudManager) getBean("estadoSolicitudManager"));
			setTablasBaseManager((TablaBaseManager) getBean("tablasBaseManager"));
			setPagoSolicitudManagar((PagoSolicitudManager) getBean("pagoSolicitudesManager"));
			properties = (Properties) getBean("propertiesBean");
			
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error buscar solicitudes cerradas",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.spring.AbstractSecureSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		//Cargo el formulario
		BuscarSolicitudesForm formulario;
		formulario = (BuscarSolicitudesForm) form;
		
		String busqueda = this.getRequest().getParameter("submit");
		String numReg = this.getRequest().getParameter("numRegistro");
		String cambios = this.getRequest().getParameter("cambios");
		
		SolComunViewQuery solComunViewQuery;
		logger.info("Busqueda: "+busqueda);
		
		String paginaActual = this.getRequest().getParameter("paginaActual");
		String paginaTotal = this.getRequest().getParameter("paginasTotales");
		if(this.getRequest().getParameter("llamada") != null){
			busqueda = this.getRequest().getParameter("llamada");
		}
		if(this.getRequest().getParameter("form") != null){
			formulario.setNumJustificante("");
			formulario.setMinisterio("");
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA_SOLICITUDES);
		}
		
		int numTotal=1;
		int aux=0;
		//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
		if(formulario.getCampo()!= null && !"0".equals(formulario.getCampo())){
			try{
				aux = Integer.parseInt(formulario.getCampo());
				String campo=cargarCampos(formulario.getCampo());
				formulario.setCampo(campo);
			}catch(Exception e){
				logger.error("Error parsear campo"+ formulario.getCampo(),e);
			}			
		}
		cargarCombos();
		
		
		
		if(paginaActual != null && !"".equals(paginaActual) && paginaTotal != null && !"".equals(paginaTotal)){
			int pagActu = 0;
			try{
				pagActu = Integer.parseInt(paginaActual);
			}catch(Exception e){
				logger.error("Error parsear pagina actual"+ pagActu,e);
			}
			int pagTotales = 0;
			try{
				pagTotales = Integer.parseInt(paginaTotal);
			}catch(Exception e){
				logger.error("Error parsear pagina totales"+ pagTotales,e);
			}

			if(pagActu > pagTotales){
				paginaActual = String.valueOf(pagActu-1);
			}
		}
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual) || STRING_BUSCAR.equals(busqueda)){
			paginaActual = Constantes.PAGINA_PRINCIPAL;
			formulario.setPaginaActual(paginaActual);
		}
		
		solComunViewQuery=crearQuerySolicitud(formulario,paginaActual,numReg,busqueda);
		
		List<BuscarSolicitudesBean> solicitudes = null;
		if(STRING_BUSCAR.equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){
			CiudadanoBean usuActual = (CiudadanoBean) this.getRequest().getSession().getAttribute("usuario");
			if(usuActual == null){
				return "errorUsuario";
			}else{
				if(usuActual.getNif() == null){
					return "errorUsuario";
				}
			}
			solComunViewQuery.setNifSolicitud(usuActual.getNif());
			solicitudes = solicitudesManager.buscarSolicitudesCerradasAll(solComunViewQuery);
			logger.info("NumSolicitudesIf: "+solicitudes.size());
			cambios = Constantes.ESTADO_BUSQUEDA;
		}
		
		int pagTotal = 0, numPaginas=0;
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(properties.getProperty("conf.numRegistrosListados"));
		}
		int numRegistros = 0;
		if(numReg != null && !"".equals(numReg)){
			try{
				numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error(STRING_ERRORPARSEARNUMREGISTROS+ numReg,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA_SOLICITUDES);
			}
		}else{
			try{
				numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error(STRING_ERRORPARSEARNUMREGISTROS+ numReg,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA_SOLICITUDES);
			}
		}
		
		if(numRegistros > 50){
			numRegistros = 50;
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}else{
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}
		
		if(solicitudes != null && solicitudes.size()!= 0){
			pagTotal= solicitudes.get(0).getNumTotal();
			numPaginas = pagTotal / numRegistros;
			int resto = pagTotal % numRegistros;
			if(resto > 0){
				numPaginas++;
			}
			if(solicitudes.size()>numRegistros){
				solicitudes.remove(solicitudes.size()-1);
			}
		}
		logger.info("Pagina Actual: "+paginaActual);
		setRequestAttribute("solicitudes", solicitudes);
		setRequestAttribute("paginaActual", paginaActual);
		setRequestAttribute("paginasTotales", numPaginas);
		setRequestAttribute("submit", busqueda);
		setRequestAttribute("cambios", cambios);
		
		return "success";
	}
	
	/**
	 * Crear query solicitud.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param numReg el num reg
	 * @param busqueda el busqueda
	 * @return el sol comun view query
	 */
	private SolComunViewQuery crearQuerySolicitud(BuscarSolicitudesForm formulario,String paginaActual,String numReg,String busqueda) {
		String numeroJustificante = formulario.getNumJustificante();
		String ministerio = formulario.getMinisterio();
		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();
		
		int numRegistros = 0;
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA_SOLICITUDES);
			try{
				numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error(STRING_ERRORPARSEARNUMREGISTROS+ numRegistros,e);
			}
		}
		if(numReg != null && !"".equals(numReg)){
			try{
			numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error(STRING_ERRORPARSEARNUMREGISTROS+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA_SOLICITUDES);
			}
		}else{
			try{
				numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error(STRING_ERRORPARSEARNUMREGISTROS+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA_SOLICITUDES);
			}
		}
		
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual) || STRING_BUSCAR.equals(busqueda)){
			paginaActual = Constantes.PAGINA_PRINCIPAL;
			formulario.setPaginaActual(paginaActual);
		}
		if(numRegistros > 50){
			numRegistros = 50;
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}else{
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}
		int tamanyoPaginacionReal = numRegistros;
		int tamanyoPaginacion = tamanyoPaginacionReal+1;
		int numPagina = Integer.parseInt(paginaActual);
		int primerRegistro = (numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
		
		SolComunViewQuery solComunViewQuery = new SolComunViewQuery();
		if(paginaActual != null){
			solComunViewQuery.setMaxResults(tamanyoPaginacion);
			solComunViewQuery.setFirstResult(primerRegistro);
		}
		solComunViewQuery.setCalculateNumResults(true);
		
		solComunViewQuery.addIdEstadoConvocatoriaIn(2);
		solComunViewQuery.addIdEstadoConvocatoriaIn(3);
		if(numeroJustificante != null && !"".equals(numeroJustificante) && !"0".equals(numeroJustificante)){
			solComunViewQuery.setNumueroSolicitudComparator(Comparator.LIKE_LEFT_RIGHT);
			solComunViewQuery.setNumueroSolicitud(numeroJustificante);
		}
		if(ministerio != null && !"".equals(ministerio) && !"0".equals(ministerio)){
			solComunViewQuery.setIdMinisterio(Integer.parseInt(ministerio));
		}
				
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			if("up".equals(direccion)){
				solComunViewQuery.addOrder(campo,OrderType.ASC);
			}else{
				solComunViewQuery.addOrder(campo,OrderType.DESC);
			}
		}
		
		return solComunViewQuery;
	}
	

	/**
	 * Cargar combos.
	 */
	private void cargarCombos() {
		ArrayList<MinisterioBean> ministerios;
		ministerios = tablasBaseManager.buscarMinisteriosCombo();
		setRequestAttribute("ministerios", ministerios);
		
	}
	
	/**
	 * Cargar campos.
	 *
	 * @param campo el campo
	 * @return el string
	 */
	private String cargarCampos(String campo) {
		String auxCampo=null;
		int codCampo = 0;
		try{
			try{
				codCampo = Integer.parseInt(campo);
			}catch(Exception e){
				logger.error("Error cargarCampos campo"+ codCampo,e);
			}
		}catch(Exception e){
			logger.error("Error cargarCampos campo"+ codCampo,e);
		}
			if (campo != null && !"".equals(campo) && !campo.equals("null")){
				
					switch(codCampo){
					//Los campos de ordenacion del jsp
					case 1:auxCampo = "numueroSolicitud";break;
					case 2:auxCampo = "descripcionMinisterio";break;
					case 3:auxCampo = "desCentroGestor";break;
					case 4:auxCampo = "desFormaAcceso";break;
					case 5:auxCampo = "descripcionCuerpoEscala";break;
					case 6:auxCampo = "desGrupo";break;
					case 7:auxCampo = "fechaSolicitud";break;
					case 8:auxCampo = "desEstadoSolicitud";break;
					default:break;
					}
				
			}
		return auxCampo;
	}

	/**
	 * Obtiene el solicitudes manager.
	 *
	 * @return el solicitudes manager
	 */
	public SolicitudManager getSolicitudesManager() {
		return solicitudesManager;
	}

	/**
	 * Establece el solicitudes manager.
	 *
	 * @param pSolicitudManager el nuevo solicitudes manager
	 */
	public void setSolicitudesManager(SolicitudManager pSolicitudManager) {
		this.solicitudesManager = pSolicitudManager;
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
	 * @param pMinisterioManager el nuevo ministerio manager
	 */
	public void setMinisterioManager(MinisterioManager pMinisterioManager) {
		this.ministerioManager = pMinisterioManager;
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
	 * @param pEstadoSolicitudManager el nuevo estado solicitud manager
	 */
	public void setEstadoSolicitudManager(
			EstadoSolicitudManager pEstadoSolicitudManager) {
		this.estadoSolicitudManager = pEstadoSolicitudManager;
	}

	/**
	 * Obtiene el tablas base manager.
	 *
	 * @return el tablas base manager
	 */
	public TablaBaseManager getTablasBaseManager() {
		return tablasBaseManager;
	}

	/**
	 * Establece el tablas base manager.
	 *
	 * @param tablasBaseManager el nuevo tablas base manager
	 */
	public void setTablasBaseManager(TablaBaseManager tablasBaseManager) {
		this.tablasBaseManager = tablasBaseManager;
	}

	/**
	 * Obtiene el pago solicitud managar.
	 *
	 * @return el pago solicitud managar
	 */
	public PagoSolicitudManager getPagoSolicitudManagar() {
		return pagoSolicitudManagar;
	}

	/**
	 * Establece el pago solicitud managar.
	 *
	 * @param pagoSolicitudManagar el nuevo pago solicitud managar
	 */
	public void setPagoSolicitudManagar(PagoSolicitudManager pagoSolicitudManagar) {
		this.pagoSolicitudManagar = pagoSolicitudManagar;
	}
	
	

}
