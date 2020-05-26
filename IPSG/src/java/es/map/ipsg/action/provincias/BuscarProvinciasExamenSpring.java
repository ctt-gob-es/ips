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
import es.map.ips.model.ProvinciaExamenQuery;
import es.map.ipsg.bean.ProvinciaExamenBean;
import es.map.ipsg.form.BuscaProvinciasExamenForm;
import es.map.ipsg.manager.ProvinciaExamenManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class BuscarProvinciasExamenSpring.
 */
public class BuscarProvinciasExamenSpring extends AbstractSpring{
	
	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** el properties. */
	private static Properties properties;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarProvinciasExamenSpring.class);
	
	/** La constante STRING_BUSCAR. */
	private static final String STRING_BUSCAR = "Buscar";

	/** el provincicia examen manager. */
	//Declaracion de manager
	private ProvinciaExamenManager provinciciaExamenManager;

	/**
	 * Crea una nueva buscar provincias examen spring.
	 */
	public BuscarProvinciasExamenSpring() {
		try {
			setProvinciciaExamenManager((ProvinciaExamenManager) getBean("provinciaExamenManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error BuscarProvinciasExamenSpring:",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		String menu_tablaBase = RESOURCE_MESSAGE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_provinciaExamen = RESOURCE_MESSAGE.getString("field.menuLateral.tablasBase.provinciaExamen");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_provinciaExamen);
		
		getLogger().debug("BuscarProvinciasExamenSpring -start");
		//Cojo el lugar de la llamada
		try{
			//Coger el form del jsp
			BuscaProvinciasExamenForm formulario;
			formulario = (BuscaProvinciasExamenForm) form;
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
			ProvinciaExamenQuery provinciaExamenQuery;	
			//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
			if(formulario.getCampo()!= null && !"Paginar".equals(busqueda)){
				try{
					
					String campo=cargarCampos(formulario.getCampo());
					formulario.setCampo(campo);
				}catch(Exception e){
					logger.error("Error BuscarProvinciasExamenSpring - campo :"+ formulario.getCampo(),e);
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
	
			provinciaExamenQuery=crearQueryProvinciaExamen(formulario,paginaActual,numReg,busqueda);
			
			//Coger la lista de convocatorias a mostrar
		
			List<ProvinciaExamenBean> provinciasExamen = null;
			if(STRING_BUSCAR.equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){ 
				provinciasExamen = provinciciaExamenManager.buscarProvinciaExamenCombo(provinciaExamenQuery);
				//Error sin resultados
				if (provinciasExamen != null && provinciasExamen.size() == 0) {
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
			if(provinciasExamen != null && provinciasExamen.size()!= 0){
				pagTotal= provinciasExamen.get(0).getNumTotal();
				numPaginas = pagTotal / numRegistros;
				int resto = pagTotal % numRegistros;
				if(resto > 0){
					numPaginas++;
				}
				if(provinciasExamen.size()>numRegistros){
					provinciasExamen.remove(provinciasExamen.size()-1);
				}	
			}
			
			if(paginaActual==null||"".equals(paginaActual))
				paginaActual="1";
			
			setRequestAttribute("provinciasExamen", provinciasExamen);
			setRequestAttribute("paginaActual", paginaActual);
			setRequestAttribute("paginasTotales", numPaginas);
			setRequestAttribute("submit", busqueda);
			setRequestAttribute("cambios", cambios);
	
			//Pasar la convocatoria y la pagina al jsp
			getLogger().debug("BuscarProvinciasExamenSpring -end");
			return "success";
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error BuscarProvinciasExamenSpring - doExecute :",e);
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
			logger.error("Error BuscarProvinciasExamenSpring - comprobarEntero() - numRegistros : "+ resultado,e);
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
			logger.error("Error BuscarProvinciasExamenSpring - cargarcampos - campo :"+ codCampo,e);
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
	 * Crear query provincia examen.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param numReg el num reg
	 * @param busqueda el busqueda
	 * @return el provincia examen query
	 */
	private ProvinciaExamenQuery crearQueryProvinciaExamen(BuscaProvinciasExamenForm formulario,String paginaActual,String numReg,String busqueda) {
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
				logger.error("Error BuscarProvinciasExamenSpring -crearQueryProvinciaExamen - numRegistros :"+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error BuscarProvinciasExamenSpring -crearQueryProvinciaExamen - numRegistros :"+ numRegistros,e);
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
		
		ProvinciaExamenQuery provinciaExamenQuery = new ProvinciaExamenQuery();
		if(paginaActual != null){
			provinciaExamenQuery.setMaxResults(tamanyoPaginacion);
			provinciaExamenQuery.setFirstResult(primerRegistro);
		}
		provinciaExamenQuery.setCalculateNumResults(true);
		//Comprobar los filtros para realizar la busqueda
			
		if(idProvincia!= null && !"0".equals(idProvincia) && !"".equals(idProvincia)){
			try{
			int codId = Integer.parseInt(idProvincia);
			provinciaExamenQuery.setId(codId);
			}catch(Exception e){
				logger.error("Error BuscarProvinciasExamenSpring -crearQueryProvinciaExamen - error parsear Id :"+ idProvincia,e);
			}
		}
		
		provinciaExamenQuery.setEstado('A');
		
		if(codigo!= null && !"".equals(codigo)){
			try{
				provinciaExamenQuery.setCodigo(codigo);
			}catch(Exception e){
				logger.error("Error BuscarProvinciasExamenSpring -crearQueryProvinciaExamen - error codigo :"+ codigo,e);
			}
		}
		
		if(descripcion!= null && !"".equals(descripcion)){
			try{
				provinciaExamenQuery.setDescripcionComparator(Comparator.LIKE_LEFT_RIGHT);
				provinciaExamenQuery.setDescripcion(descripcion);
			}catch(Exception e){
				logger.error("Error BuscarProvinciasExamenSpring -crearQueryProvinciaExamen - error descripcion :"+ descripcion,e);
			}
		}
		
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			OrderType orden = ("up".equals(direccion)==true ? OrderType.ASC:OrderType.DESC);
			provinciaExamenQuery.addOrder(campo, orden);
		}
		
		//Devulelve la convocatoria con los filtros
		return provinciaExamenQuery;
	}


	/**
	 * Obtiene el provincicia examen manager.
	 *
	 * @return el provincicia examen manager
	 */
	public ProvinciaExamenManager getProvinciciaExamenManager() {
		return provinciciaExamenManager;
	}

	/**
	 * Establece el provincicia examen manager.
	 *
	 * @param provinciciaExamenManager el nuevo provincicia examen manager
	 */
	public void setProvinciciaExamenManager(ProvinciaExamenManager provinciciaExamenManager) {
		this.provinciciaExamenManager = provinciciaExamenManager;
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
