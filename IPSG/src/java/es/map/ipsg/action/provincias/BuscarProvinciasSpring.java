package es.map.ipsg.action.provincias;

import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.ProvinciaQuery;
import es.map.ipsg.bean.ProvinciaBean;
import es.map.ipsg.form.BuscaProvinciasForm;
import es.map.ipsg.manager.ProvinciaManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class BuscarProvinciasSpring.
 */
public class BuscarProvinciasSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarProvinciasSpring.class);
	
	/** el properties. */
	private static Properties properties;
	
	/** La constante STRING_BUSCAR. */
	private static final String STRING_BUSCAR = "Buscar";

	/** el provincicia manager. */
	//Declaracion de manager
	private ProvinciaManager provinciciaManager;

	/**
	 * Crea una nueva buscar provincias spring.
	 */
	public BuscarProvinciasSpring() {
		try {
			setProvinciciaManager((ProvinciaManager) getBean("provinciaManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error BuscarProvinciasSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_MESSAGE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_provincia = RESOURCE_MESSAGE.getString("field.menuLateral.tablasBase.provincia");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_provincia);
		
		getLogger().debug("BuscarProvinciasSpring -start");
		//Cojo el lugar de la llamada
		try{
			//Coger el form del jsp
			BuscaProvinciasForm formulario;
			formulario = (BuscaProvinciasForm) form;
			String busqueda=null;
			
			if(this.getRequest().getParameter("llamada")!=null)
				busqueda=this.getRequest().getParameter("llamada");
			else
				busqueda = formulario.getSubmit();
			
			
			String cambios = this.getRequest().getParameter("cambios");
			String numReg = this.getRequest().getParameter("numRegistro");
			logger.info("NumRegistros: "+numReg);
			
			//Comprobar si viene del menu
			String menu = this.getRequest().getParameter("menu");
			if(menu != null && menu.equals("S")){
				formulario.setDescripcion("");
				busqueda = null;
			}
			
			//Creamos la Query
			ProvinciaQuery provinciaQuery;	
			//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
			if(formulario.getCampo()!= null && !"Paginar".equals(busqueda)){
				try{
					
					String campo=cargarCampos(formulario.getCampo());
					formulario.setCampo(campo);
				}catch(Exception e){
					logger.error("Error BuscarProvinciasSpring - error parsear campo:"+ formulario.getCampo(),e);
				}			
			}
			
			
			//Llama a la funcion para crear la Query pasandole el formulario
			
			String paginaActual = this.getRequest().getParameter("paginaActual");
			String paginaTotal = this.getRequest().getParameter("paginasTotales");
			

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
			
			logger.info("PaginaActual: "+ paginaActual);
			logger.info("PaginaTotal: "+paginaTotal);
			
			
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
	
			provinciaQuery=crearQueryProvincia(formulario,paginaActual,numReg,busqueda);
				
			
			//Coger la lista de convocatorias a mostrar
		
			List<ProvinciaBean> provincias = null;
			if(STRING_BUSCAR.equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){ 
				provincias = provinciciaManager.buscarProvinciaCombo(provinciaQuery);
				//Error sin resultados
				if (provincias != null && provincias.size() == 0) {
					SpringMessages errors = new SpringMessages();
					errors.add("errorSinResultados", new SpringMessage("field.provincia.error"));
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
			if(provincias != null && provincias.size()!= 0){
				pagTotal= provincias.get(0).getNumTotal();
				numPaginas = pagTotal / numRegistros;
				int resto = pagTotal % numRegistros;
				if(resto > 0){
					numPaginas++;
				}
				if(provincias.size()>numRegistros){
					provincias.remove(provincias.size()-1);
				}	
			}
			
			if(paginaActual==null||"".equals(paginaActual))
				paginaActual="1";
			
			setRequestAttribute("provincias", provincias);
			setRequestAttribute("paginaActual", paginaActual);
			setRequestAttribute("paginasTotales", numPaginas);
			setRequestAttribute("submit", busqueda);
			setRequestAttribute("cambios", cambios);
	
			//Pasar la convocatoria y la pagina al jsp
			getLogger().debug("BuscarProvinciasSpring -end");
			return "success";
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error BuscarProvinciasSpring - doExecute:",e);
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
			logger.error("Error BuscarProvinciasSpring - numRegistros:"+ resultado,e);
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
			logger.error("Error BuscarProvinciasSpring - cargarCampos -  error parsear campo:"+ codCampo,e);
		}
			if (campo != null && !"".equals(campo) && !campo.equals("null")){
				
					switch(codCampo){
					//Los campos de ordenacion del jsp
						case 1:auxCampo = "id";break;
						case 2:auxCampo = "codigo";break;
						case 3:auxCampo = "descripcion";break;
						case 4:auxCampo = "visible";break;
						default:break;
					}
				
			}
		return auxCampo;
	}

	/**
	 * Crear query provincia.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param numReg el num reg
	 * @param busqueda el busqueda
	 * @return el provincia query
	 */
	private ProvinciaQuery crearQueryProvincia(BuscaProvinciasForm formulario,String paginaActual,String numReg,String busqueda) {
		//Recoger los datos del formulario
		String idProvincia = formulario.getId();
		String codigo = formulario.getCodigo();
		String descripcion = formulario.getDescripcion();
		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();
		int numRegistros = 0;
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		if(numReg != null && !"".equals(numReg)){
			try{
			numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error BuscarProvinciasSpring - crearQueryProvincia -  error parsear numRegistros:"+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error BuscarProvinciasSpring - crearQueryProvincia -  error parsear numRegistros:"+ numRegistros,e);
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
		int tamanyoPaginacionReal = numRegistros;
		int tamanyoPaginacion = tamanyoPaginacionReal+1;
		int numPagina = Integer.parseInt(paginaActual);
		int primerRegistro = (numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
		
		ProvinciaQuery provinciaQuery = new ProvinciaQuery();
		if(paginaActual != null){
			provinciaQuery.setMaxResults(tamanyoPaginacion);
			provinciaQuery.setFirstResult(primerRegistro);
		}
		provinciaQuery.setCalculateNumResults(true);
		//Comprobar los filtros para realizar la busqueda
			
		if(idProvincia!= null && !"0".equals(idProvincia) && !"".equals(idProvincia)){
			try{
			int codId = Integer.parseInt(idProvincia);
			provinciaQuery.setId(codId);
			}catch(Exception e){
				logger.error("Error BuscarProvinciasSpring - crearQueryProvincia - No se pudo parsear el id :"+ idProvincia,e);
			}
		}
		
		provinciaQuery.setEstado('A');
		
		if(codigo!= null && !"".equals(codigo)){
			try{
				provinciaQuery.setCodigo(codigo);
			}catch(Exception e){
				logger.error("Error BuscarProvinciasSpring - crearQueryProvincia - codigo :"+ codigo,e);
			}
		}
		
		if(descripcion!= null && !"".equals(descripcion)){
			try{
				provinciaQuery.setDescripcionComparator(Comparator.LIKE_LEFT_RIGHT);
				provinciaQuery.setDescripcion(descripcion);
			}catch(Exception e){
				logger.error("Error BuscarProvinciasSpring - crearQueryProvincia - descripcion :"+ descripcion,e);
			}
		}
		
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			OrderType orden = ("up".equals(direccion)==true ? OrderType.ASC:OrderType.DESC);
			provinciaQuery.addOrder(campo, orden);
		}
		
		//Devulelve la convocatoria con los filtros
		return provinciaQuery;
	}


	/**
	 * Obtiene el provincicia manager.
	 *
	 * @return el provincicia manager
	 */
	public ProvinciaManager getProvinciciaManager() {
		return provinciciaManager;
	}

	/**
	 * Establece el provincicia manager.
	 *
	 * @param provinciciaManager el nuevo provincicia manager
	 */
	public void setProvinciciaManager(ProvinciaManager provinciciaManager) {
		this.provinciciaManager = provinciciaManager;
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
