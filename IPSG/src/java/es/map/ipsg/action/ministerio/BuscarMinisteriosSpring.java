package es.map.ipsg.action.ministerio;

import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.MinisterioQuery;
import es.map.ipsg.bean.MinisterioBean;
import es.map.ipsg.form.MinisterioForm;
import es.map.ipsg.manager.MinisterioManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class BuscarMinisteriosSpring.
 */
public class BuscarMinisteriosSpring extends AbstractSpring {
	
	/** el ministerio manager. */
	private MinisterioManager ministerioManager;
	
	/** La constante MESSAGE_BUNDLE_RESOURCE. */
	private static final String MESSAGE_BUNDLE_RESOURCE = "MessageResources";
	
	/** La constante MESSAGE_RESOURCE_BUNDLE. */
	private static final ResourceBundle MESSAGE_RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_BUNDLE_RESOURCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarMinisteriosSpring.class);
	
	/** el properties. */
	private static Properties properties;
	
	/** La constante STRING_BUSCAR. */
	private static final String STRING_BUSCAR = "Buscar";
	
	/**
	 * Crea una nueva buscar ministerios spring.
	 */
	public BuscarMinisteriosSpring() {
		try {
			setMinisterioManager((MinisterioManager) getBean("ministeriosManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error BuscarMinisteriosSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("BuscarMinisteriosSpring -start");
		
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********	
		String menu_tablasBase = MESSAGE_RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva",menu_tablasBase);
		String subMenu_ministerio = MESSAGE_RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.ministerio");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_ministerio);
		//****************************************************************** 
		
		
		MinisterioForm formulario;
		formulario = (MinisterioForm) form;
		
		try{
			String paginaActual = this.getRequest().getParameter("paginaActual");
			String paginaTotal = this.getRequest().getParameter("paginasTotales");
			String cambios = this.getRequest().getParameter("cambios");
			
			String numReg = this.getRequest().getParameter("numRegistro");
			logger.info("NumRegistros: "+numReg);
			
			String busqueda = formulario.getSubmit();
			if(this.getRequest().getParameter("llamada") != null){
				busqueda = this.getRequest().getParameter("llamada");
			}
			
			// Si viene desde el menu borramos los datos previamente introducidos
			if ("S".equals(this.getRequest().getParameter("menu")))
			{
				formulario.setDescripcion("");	// Si viene del menu borramos todos sus campos
				busqueda = null;
			}	
			
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
			
			if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual) || STRING_BUSCAR.equals(busqueda)){
				paginaActual = Constantes.PAGINA_PRINCIPAL;
				formulario.setPaginaActual(paginaActual);
			}
			
					
			
			MinisterioQuery ministerioQuery;
			//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
			if(formulario.getCampo()!= null && !"Paginar".equals(busqueda)){
				try{
					
					String campo=cargarCampos(formulario.getCampo());
					formulario.setCampo(campo);
				}catch(Exception e){
					logger.error("Error BuscarMinisteriosSpring - campo :"+ formulario.getCampo(),e);
				}			
			}
			ministerioQuery=crearMinisterioQuery(formulario,paginaActual,numReg,busqueda);
			
			List<MinisterioBean> ministerios = null;
			if(STRING_BUSCAR.equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){ 
				ministerios = ministerioManager.buscarMinisteriosAll(ministerioQuery);
				//Error sin resultados
				if (ministerios != null && ministerios.size() == 0) {
					SpringMessages errors = new SpringMessages();
					errors.add("errorSinResultados", new SpringMessage("field.ministerio.error"));
					this.setRequestAttribute("org.apache.spring.ERROR", errors);
				}
			}
			int pagTotal = 0, numPaginas=0;
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
			
			//Recorrer la lista, para comprobar los estados de cada convocatoria para activar los enlaces
			if(ministerios != null && ministerios.size()!= 0){
				pagTotal= ministerios.get(0).getNumTotal();
				numPaginas = pagTotal / numRegistros;
				int resto = pagTotal % numRegistros;
				if(resto > 0){
					numPaginas++;
				}
				if(ministerios.size()>numRegistros){
					ministerios.remove(ministerios.size()-1);
				}			
			}
			
			if(paginaActual==null || "".equals(paginaActual))
				paginaActual="1";
			
			setRequestAttribute("ministerios", ministerios);
			setRequestAttribute("paginaActual", paginaActual);
			setRequestAttribute("paginasTotales", numPaginas);	
			setRequestAttribute("submit", busqueda);
			setRequestAttribute("cambios", cambios);
	
			getLogger().debug("BuscarMinisteriosSpring -end");
			return "success";
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error BuscarMinisteriosSpring - doExecute :",e);
			return "nosuccess";
		}
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
			logger.error("Error comprobarEntero() - numRegistros :"+ resultado,e);
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
			logger.error("Error BuscarMinisteriosSpring - cargarCampos- campo :"+ codCampo,e);
		}
			if (campo != null && !"".equals(campo) && !campo.equals("null")){
				
					switch(codCampo){
					//Los campos de ordenacion del jsp
						case 1:auxCampo = "id";break;
						case 2:auxCampo = "descripcion";break;
						case 3:auxCampo = "siglas";break;
						case 4:auxCampo = "visible";break;
						default:break;
					}
				
			}
		return auxCampo;
	}
	
	/**
	 * Crear ministerio query.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param numReg el num reg
	 * @param busqueda el busqueda
	 * @return el ministerio query
	 */
	private MinisterioQuery crearMinisterioQuery(MinisterioForm formulario,String paginaActual,String numReg,String busqueda) {
		
		String descripcion = formulario.getDescripcion();
		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();

		int numRegistros = 0;
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
		}		
		if(StringUtils.isNotEmpty(numReg)){
			try{
			numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error BuscarMinisteriosSpring -  crearMinisterioQuery - numRegistros :"+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error BuscarMinisteriosSpring -  crearMinisterioQuery - numRegistros :"+ numRegistros,e);
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
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual) || STRING_BUSCAR.equals(busqueda)){
			paginaActual = properties.getProperty("conf.primerPagina");
			formulario.setPaginaActual(paginaActual);
		}
		
		//Calcular el numero de lineas de cada paginacion
		MinisterioQuery ministerioQueryAux = new MinisterioQuery();
		calcularNumeroLineas(numRegistros,paginaActual,ministerioQueryAux,descripcion,campo,direccion,formulario);
		
		return ministerioQueryAux;
	}
	
	/**
	 * Calcular numero lineas.
	 *
	 * @param numRegistros el num registros
	 * @param paginaActual el pagina actual
	 * @param ministerioQueryAux el ministerio query aux
	 * @param descripcion el descripcion
	 * @param campo el campo
	 * @param direccion el direccion
	 * @param formulario el formulario
	 */
	private void calcularNumeroLineas(int numRegistros, String paginaActual, MinisterioQuery ministerioQueryAux, String descripcion, String campo, String direccion,MinisterioForm formulario) {
		
		int tamanyoPaginacionReal = numRegistros;
		int tamanyoPaginacion = tamanyoPaginacionReal+1;
		int numPagina = Integer.parseInt(paginaActual);
		int primerRegistro = (numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
		
		
		ministerioQueryAux.setEstado('A');
		if(descripcion != null && !"".equals(descripcion)){
			ministerioQueryAux.setDescripcionComparator(Comparator.LIKE_LEFT_RIGHT);
			ministerioQueryAux.setDescripcion(formulario.getDescripcion());
		}
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			OrderType orden=("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);			
			ministerioQueryAux.addOrder(campo,orden);
		}
		ministerioQueryAux.setCalculateNumResults(true);
		
		if(paginaActual != null){
			ministerioQueryAux.setMaxResults(tamanyoPaginacion);
			ministerioQueryAux.setFirstResult(primerRegistro);
		}
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
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
	
}
