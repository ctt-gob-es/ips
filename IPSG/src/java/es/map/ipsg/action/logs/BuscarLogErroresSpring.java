package es.map.ipsg.action.logs;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.LogServicioQuery;
import es.map.ips.model.TipoServicioQuery;

import es.map.ipsg.bean.LogServicioBean;
import es.map.ipsg.bean.TipoServicioBean;
import es.map.ipsg.form.LogErroresForm;
import es.map.ipsg.manager.LogServicioManager;
import es.map.ipsg.manager.SolicitudesManager;
import es.map.ipsg.manager.TipoServicioManager;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.UtilesIPSG;


/**
 * El Class BuscarLogErroresSpring.
 */
public class BuscarLogErroresSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	//Declaracion de manager
	/**
	 * Para recuperar los Errores se utiliza la tabla y ls manager LogServicio pero con el Resultado de error (ER).
	 */
	private LogServicioManager logErroresManager;
	
	/** el tipo servicio manager. */
	private TipoServicioManager tipoServicioManager;
	
	/** el solicitud manager. */
	private SolicitudesManager solicitudManager;

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(LogServicioManager.class);
	
	/** La constante STRING_BUSCAR. */
	private static final String STRING_BUSCAR = "Buscar";
	
		
	/**
	 * Crea una nueva buscar log errores spring.
	 */
	public BuscarLogErroresSpring() {
		try {
			setLogErroresManager((LogServicioManager) getBean("logServicioManager"));
			setTipoServicioManager((TipoServicioManager) getBean("tipoServicioManager"));
			setSolicitudManager((SolicitudesManager) getBean("solicitudesManager"));
		} catch (Exception e) {
			logger.error("Error VerBuscarIncidenciasSpring",e);
		}
	}

	




	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_consultas = RESOURCE_BUNDLE.getString("field.menu.consultas");
		this.getRequest().getSession().setAttribute("pagActiva",menu_consultas);
		String subMenu_log = RESOURCE_BUNDLE.getString("field.menuLateral.consultas.logErrores");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_log);
		//****************************************************************** 
		getLogger().debug("BuscarLogErroresSpring -start");
		LogErroresForm formulario;
		formulario = (LogErroresForm) form;
	try{
		String paginaActual = this.getRequest().getParameter("paginaActual");
		String paginaTotal = this.getRequest().getParameter("paginasTotales");
		String cambios = this.getRequest().getParameter("cambios");
		String llamada = this.getRequest().getParameter("llamada");
		String campo="";
		String direccion="";
		
		if(formulario.isPulsaIr() == true)
		{
			paginaActual = formulario.getNumeroPaginaIr().toString();
			logger.info("PaginaActualIr: "+ paginaActual);
			formulario.setPulsaIr(false);
			formulario.setNumeroPaginaIr(null);
		}
		else
		{
			formulario.setNumeroPaginaIr(null);
			formulario.setPulsaIr(false);
		}
		
		if(paginaActual != null && !"".equals(paginaActual) && paginaTotal != null && !"".equals(paginaTotal)){
			int pagActu = Integer.parseInt(paginaActual);		
			int pagTotales = Integer.parseInt(paginaTotal);

			if(pagActu > pagTotales){
				paginaActual = String.valueOf(pagTotales);
			}
		}
		
		
		String numReg = this.getRequest().getParameter("numRegistro");
		String busqueda="";
		logger.info("NumRegistros: "+numReg);
		 
		if (formulario.getSubmit()!=null&& !"".equals(formulario.getSubmit())){
			busqueda=formulario.getSubmit();
		}else{
			busqueda=STRING_BUSCAR;
			if(llamada == null || llamada.equals(""))
			{	
				paginaActual = "1";
			}	
		}
		
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual) /*|| STRING_BUSCAR.equals(busqueda)*/){
			paginaActual = Constantes.PAGINA_PRINCIPAL;
			formulario.setPaginaActual(paginaActual);
		}
		
		LogServicioQuery logServicioQuery;
		
		//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
		if(formulario.getCampo()!= null){
			try{
				if (formulario.getCampo()==null ||formulario.getCampo().equals("")){
					campo=cargarCampos("1");
					direccion="up";
				}else{
					campo=cargarCampos(formulario.getCampo());
					direccion=formulario.getDireccion();
				}
				
			}catch(Exception e){
				logger.error("Error VerBuscarIncidenciasSpring - cargarCampos - campo:" + campo,e);
			}			
		}
		if(STRING_BUSCAR.equals(busqueda) && (llamada == null || llamada.equals("")))
		{
				
			paginaActual = "1";
			
		}	
		List<LogServicioBean> errores = new ArrayList<LogServicioBean>();
		logServicioQuery=crearQueryErrores(formulario,paginaActual,numReg,busqueda,campo,direccion);
		if(logServicioQuery == null)
		{
			// no se ha encontrado ninguna solicitud con el numero especificado
			setRequestAttribute("errores", errores);
		}	
		else
		{	
			if(STRING_BUSCAR.equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){ 
				errores = logErroresManager.buscarLogErroresAll(logServicioQuery);
			}
		}	
		
		int pagTotal = 0;
		int numPaginas=0;
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = 0;
		if(numReg != null && !"".equals(numReg)){
			numRegistros = comprobarEntero(numReg);
		}else{
			numRegistros = comprobarEntero(formulario.getNumRegistro());
		}
		if(numRegistros > 50){
			numRegistros = 50;
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}else{
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}
		//Recorrer la lista, para activar los enlaces
		if(errores != null && errores.size()!= 0){
			pagTotal= errores.get(0).getNumTotal();
			numPaginas = pagTotal / numRegistros;
			int resto = pagTotal % numRegistros;
			if(resto > 0){
				numPaginas++;
			}
			if(errores.size()>numRegistros){
				errores.remove(errores.size()-1);
			}			
		}
		
		List <TipoServicioBean> tiposServicios=null;
		TipoServicioQuery tipoServicioQuery=new TipoServicioQuery();
		tipoServicioQuery.addOrder(TipoServicioQuery.DESCRIPCION, OrderType.ASC);
		tipoServicioQuery.setCalculateNumResults(true);
		tiposServicios=tipoServicioManager.buscarTipoServicioAll(tipoServicioQuery);
		
		if(paginaActual==null || "".equals(paginaActual))
			paginaActual="1";
		
		setRequestAttribute("errores", errores);
		setRequestAttribute("tiposServicios", tiposServicios);
		setRequestAttribute("paginaActual", paginaActual);
		setRequestAttribute("paginasTotales", numPaginas);	
		setRequestAttribute("submit", busqueda);
		setRequestAttribute("cambios", cambios);

		getLogger().debug("BuscarLogErroresSpring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error VerBuscarIncidenciasSpring - doExecute" ,eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return "success";
	}

	/**
	 * Comprobar entero.
	 *
	 * @param numero el numero
	 * @return el int
	 */
	private int comprobarEntero(String numero) {
		int resultado = 0;
		try{
			resultado = Integer.parseInt(numero);
		}catch(Exception e){
			logger.error("Error comprobarEntero - numRegistros:" + resultado,e);
			resultado = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		return resultado;
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
			codCampo = Integer.parseInt(campo);
		}catch(Exception e){
			logger.error("Error VerBuscarIncidenciasSpring  - CargarCampos- codCampo:" + codCampo,e);
		}
			if (campo != null && !"".equals(campo) && !campo.equals("null")){
				
					switch(codCampo){
					//Los campos de ordenacion del jsp
						case 1:auxCampo = "id";break;
						case 2:auxCampo = "fecha";break;
						case 3:auxCampo = "tipoServicio";break;
						case 4:auxCampo = "tipoError";break;
						case 5:auxCampo = "descripcionError";break;	
						default:break;
						}
					
				}
			return auxCampo;
			}
			
	

	/**
	 * Crear query errores.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param numReg el num reg
	 * @param busqueda el busqueda
	 * @param campo el campo
	 * @param direccion el direccion
	 * @return el log servicio query
	 */
	private LogServicioQuery crearQueryErrores(LogErroresForm formulario,String paginaActual,String numReg,String busqueda,String campo,String direccion) {
		//Recoger los datos del formulario
		
		UtilesIPSG utilesIPSG=new UtilesIPSG();
		
		
		
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = 0;
		if(numReg != null && !"".equals(numReg)){
			try{
			numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error VerBuscarIncidenciasSpring - creaQueryErrores- numRegistros:" + numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error VerBuscarIncidenciasSpring -  creaQueryErrores-numRegistros:" + numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}
		if(numRegistros > 50){
			numRegistros = 50;
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}else{
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}
		//Si la pagina actual no esta definida, se establece a 1
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual)){
			paginaActual = "1";
			formulario.setPaginaActual(paginaActual);
		}
		//Calcular el numero de lineas de cada paginacion
		int tamanyoPaginacionReal = numRegistros;
		int tamanyoPaginacion = tamanyoPaginacionReal+1;
		int numPagina = Integer.parseInt(paginaActual);
		int primerRegistro = (numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
		
		LogServicioQuery logErroresQuery = new LogServicioQuery();
		if(paginaActual != null){
			logErroresQuery.setMaxResults(tamanyoPaginacion);
			logErroresQuery.setFirstResult(primerRegistro);
		}
		
		logErroresQuery.setCalculateNumResults(true);
		if(formulario.getFechaDesde()!=null && !"".equals(formulario.getFechaDesde())){
			logErroresQuery.setFechaMin(utilesIPSG.stringToDate(formulario.getFechaDesde()));
		}
		if(formulario.getFechaHasta()!=null && !"".equals(formulario.getFechaHasta())){
			logErroresQuery.setFechaMax(utilesIPSG.stringToDate(formulario.getFechaHasta()));
		}
		
		if(!StringUtils.isEmpty(formulario.getIdTipoServicio())){
			TipoServicioQuery tipoServicioQuery=new TipoServicioQuery();
			tipoServicioQuery.setId(Integer.valueOf(formulario.getIdTipoServicio()));
			logErroresQuery.setTipoServicio(tipoServicioQuery);
		}
		
		if(!StringUtils.isEmpty(formulario.getIdTipoError())){
			logErroresQuery.setTipoError(formulario.getIdTipoError().toCharArray()[0]);
		}
		
		if((formulario.getNumeroSolicitud() != null && !formulario.getNumeroSolicitud().equals("")) ||
				(formulario.getNif()!=null && !formulario.getNif().equals("")))
		{
			// filtro de busqueda por el numero de solicitud
			ArrayList<Integer> arrIdsLog;
			arrIdsLog = solicitudManager.buscarLogPorNumeroSolicitudYNif(formulario.getNumeroSolicitud(), formulario.getNif());
			if(arrIdsLog != null && arrIdsLog.size()>0)
			{	
				for(Integer idAux : arrIdsLog)
				{	
					logErroresQuery.addIdIn(idAux);
				}	
			}
			else
			{
				// no hay ninguna solicitud con ese numero, devuelve error
				return null;
			}	
			
		}
		
		//Anyadimos el order
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			if("up".equals(direccion)){
				logErroresQuery.addOrder(campo,OrderType.ASC);
			}else{
				logErroresQuery.addOrder(campo,OrderType.DESC);
			}
		}
		
		//Devulelve los errores con los filtros
		return logErroresQuery;
		
	}

	/**
	 * Obtiene el log errores manager.
	 *
	 * @return the logErroresManager
	 */
	public LogServicioManager getLogErroresManager() {
		return logErroresManager;
	}

	/**
	 * Establece el log errores manager.
	 *
	 * @param logErroresManager the logErroresManager to set
	 */
	public void setLogErroresManager(LogServicioManager logErroresManager) {
		this.logErroresManager = logErroresManager;
	}



	/**
	 * Obtiene el logger.
	 *
	 * @return the logger
	 */
	public static Logger getLogger() {
		return logger;
	}






	/**
	 * Obtiene el tipo servicio manager.
	 *
	 * @return the tipoServicioManager
	 */
	public TipoServicioManager getTipoServicioManager() {
		return tipoServicioManager;
	}






	/**
	 * Establece el tipo servicio manager.
	 *
	 * @param tipoServicioManager the tipoServicioManager to set
	 */
	public void setTipoServicioManager(TipoServicioManager tipoServicioManager) {
		this.tipoServicioManager = tipoServicioManager;
	}






	/**
	 * Obtiene el solicitud manager.
	 *
	 * @return el solicitud manager
	 */
	public SolicitudesManager getSolicitudManager() {
		return solicitudManager;
	}






	/**
	 * Establece el solicitud manager.
	 *
	 * @param solicitudManager el nuevo solicitud manager
	 */
	public void setSolicitudManager(SolicitudesManager solicitudManager) {
		this.solicitudManager = solicitudManager;
	}

}
