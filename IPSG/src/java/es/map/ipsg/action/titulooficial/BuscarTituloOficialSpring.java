package es.map.ipsg.action.titulooficial;

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
import es.map.ips.model.TituloOficialQuery;
import es.map.ipsg.bean.TituloOficialBean;
import es.map.ipsg.form.TituloOficialForm;
import es.map.ipsg.manager.TituloOficialManager;
import es.map.ipsg.util.Constantes;


/**
 * Acción Busqueda de Títulos dados de alta en la aplicación.
 *
 * @author amartinl
 */
public class BuscarTituloOficialSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** el properties. */
	private static Properties properties;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarTituloOficialSpring.class);
	
	/** el titulo oficial manager. */
	//Declaracion de manager
	private TituloOficialManager tituloOficialManager;
	
	/**
	 * BuscarTituloOficialAction.
	 */
	public BuscarTituloOficialSpring() {
		try {
			
			setTituloOficialManager((TituloOficialManager) getBean("tituloOficialManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error BuscarTituloOficialSpring - crear constructor",e);
		}
	}

/**
 * Método doExecute de BuscarTituloOficialAction.
 *
 * @param form SpringForm Parámetros del formulario
 * @return acción String success si todo va bien.
 * @throws Exception Exception
 */
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("BuscarTituloOficialSpring -start");
		
		
		//*********** METO EL PARÁMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_tablabase = RESOURCE_MESSAGE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva",menu_tablabase);	
		String subMenu_tituloOficial = RESOURCE_MESSAGE.getString("field.menuLateral.tablasBase.tituloOficial");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_tituloOficial);
		//****************************************************************** 
		try{
			//Cojo el lugar de la llamada
			
			//Coger el form del jsp
			TituloOficialForm formulario;
			formulario = (TituloOficialForm) form;
			String busqueda = formulario.getSubmit();
			if(this.getRequest().getParameter("llamada") != null){
				busqueda = this.getRequest().getParameter("llamada");
			}
			String numReg = this.getRequest().getParameter("numRegistro");
			logger.info("NumRegistros: "+numReg);
			String cambios = this.getRequest().getParameter("cambios");
			//Indicamos si viene desde el enlace del menú para mostrar los resultados de búsqueda o no 
			//y guardar la descripción para el formulario de búsqueda
			String sVieneMenu = this.getRequest().getParameter("menu"); 
			if (sVieneMenu != null && sVieneMenu.equals("S"))
			{
				formulario.setDescripcion("");
				busqueda = "";
			}else //Si viene a null es que viene de las demás páginas que no son del menú principal
			{
				sVieneMenu = "N";
			}
			
			//Creamos la Query
			TituloOficialQuery tituloOficialQuery;
					
			if(formulario.getCampo()!= null && !"Paginar".equals(busqueda)){
				try{
					 
					String campo=cargarCampos(formulario.getCampo());
					formulario.setCampo(campo);
				}catch(Exception e){
					logger.error("Error BuscarTituloOficialSpring - parsear campo"+ formulario.getCampo(),e);
				}			
			}
			
			//Paginación
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
			
			if(paginaActual != null && !"".equals(paginaActual) && paginaTotal != null && !"".equals(paginaTotal)){
				int pagActu = Integer.parseInt(paginaActual);		
				int pagTotales = Integer.parseInt(paginaTotal);
	
				if(pagActu > pagTotales){
					paginaActual = String.valueOf(pagTotales);
				}
			}
			
			if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual) /*|| "Buscar".equals(busqueda)*/){
				paginaActual = Constantes.PAGINA_PRINCIPAL;
				formulario.setPaginaActual(paginaActual);
			}
			
			//Fin Paginación
			//Llama a la funcion para crear la Query pasándole el formulario de todos los títulos
			tituloOficialQuery = crearQueryTituloOficial(formulario,paginaActual,numReg);
			//Se crea la lista que recogerá de la select de buscar		
			List<TituloOficialBean> titulo = null;
			if("Buscar".equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){ 
				titulo = tituloOficialManager.buscarTituloOficialAll(tituloOficialQuery);
				//Error sin resultados
				if (titulo != null && titulo.size() == 0) {
					SpringMessages errors = new SpringMessages();
					errors.add("errorSinResultados", new SpringMessage("field.cuerpoEscala.error"));
					this.setRequestAttribute("org.apache.spring.ERROR", errors);
				}
				cambios = "Correcto";
			}
			int pagTotal = 0, numPaginas=0;
			logger.info("Busqueda: "+busqueda);
			if(formulario.getNumRegistro() == null && numReg == null){
				//Para el primer paso para el action, se establece que los registros por pagina son 5
				formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
			}
			int numRegistros = 0;
			if(numReg != null && !"".equals(numReg)){
				try{
				numRegistros = Integer.parseInt(numReg);
				}catch(Exception e){
					logger.error("Error BuscarTituloOficialSpring - numRegistros"+ numRegistros,e);
					numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
				}
			}else{
				try{
				numRegistros = Integer.parseInt(formulario.getNumRegistro());
				}catch(Exception e){
					logger.error("Error BuscarTituloOficialSpring - numRegistros"+ numRegistros,e);
					numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
				}
			}
			if(numRegistros > 50){
				numRegistros = 50;
				formulario.setNumRegistro(String.valueOf(numRegistros));
			}else{
				formulario.setNumRegistro(String.valueOf(numRegistros));
			}
			//Recorrer la lista, para comprobar los estados de cada convocatoria para activar los enlaces
			if(titulo != null && titulo.size()!= 0){
				pagTotal= titulo.get(0).getNumTotal();
				numPaginas = pagTotal / numRegistros;
				int resto = pagTotal % numRegistros;
				if(resto > 0){
					numPaginas++;
				}
				if(titulo.size()>numRegistros){
					titulo.remove(titulo.size()-1);
				}
			}
			
			//Pasar el titulo y la pagina al jsp
			setRequestAttribute("titulos", titulo);
			//Paginación
			if(paginaActual==null||"".equals(paginaActual))
				paginaActual="1";
			
			setRequestAttribute("paginaActual", paginaActual);
			setRequestAttribute("cambios", cambios);
			setRequestAttribute("paginasTotales", numPaginas);	
			setRequestAttribute("sVieneMenu", sVieneMenu); //S = Viene del menú principal / N = Viene de cualquier otra pantalla.

			return "success";
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error BuscarTituloOficialSpring - doExecute",e);
			return "nosuccess";
		}
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
			logger.error("Error BuscarTituloOficialSpring - cargarCampos - campo "+ codCampo,e);
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
	 * Crear query titulo oficial.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param numReg el num reg
	 * @return el titulo oficial query
	 */
	private TituloOficialQuery crearQueryTituloOficial(TituloOficialForm formulario,String paginaActual,String numReg) {
//Recoger los datos del formulario
		
		String descripcion = formulario.getDescripcion();
		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();
		
		
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = 0;
		numRegistros = crearQueryTituloOficial2(numReg,numRegistros,formulario);
		
		if(numRegistros > 50){
			numRegistros = 50;
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}else{
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}
		//Si la pagina actual no esta definida, se establece a 1
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual)){
			paginaActual = properties.getProperty("conf.primerPagina");
			formulario.setPaginaActual(paginaActual);
		}
		//Calcular el numero de lineas de cada paginacion
		int tamanyoPaginacionReal = numRegistros;
		int tamanyoPaginacion = tamanyoPaginacionReal+1;
		int numPagina = Integer.parseInt(paginaActual);
		int primerRegistro = (numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
		
		TituloOficialQuery tituloOficialQuery = new TituloOficialQuery();
		if(paginaActual != null){
			tituloOficialQuery.setMaxResults(tamanyoPaginacion);
			tituloOficialQuery.setFirstResult(primerRegistro);
		}
		tituloOficialQuery.setCalculateNumResults(true);
		//Comprobar los filtros para realizar la busqueda
		if(descripcion!= null  && !"".equals(descripcion)){
			try{
				tituloOficialQuery.setDescripcionComparator(Comparator.LIKE_LEFT_RIGHT);
				tituloOficialQuery.setDescripcion(descripcion);
			}catch(Exception e){
				logger.warn(e);
				logger.error("Error BuscarTituloOficialSpring -   crearQueryTituloOficial - descripcion",e);
			}
		}
		String estado= RESOURCE_MESSAGE.getString("field.estado.activo");						
		tituloOficialQuery.setEstado(estado.charAt(0));
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			OrderType orden = ("up".equals(direccion)==true ? OrderType.ASC:OrderType.DESC);
			tituloOficialQuery.addOrder(campo, orden);
		}
		//Devuelve la select que queremos conseguir de los títulos con filtro
		return tituloOficialQuery;		
	}
	
	/**
	 * Crear query titulo oficial 2.
	 *
	 * @param numReg el num reg
	 * @param numRegistros el num registros
	 * @param formulario el formulario
	 * @return el int
	 */
	private int crearQueryTituloOficial2(String numReg, int numRegistros, TituloOficialForm formulario) {
		if(numReg != null && !"".equals(numReg)){
			try{
			numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error BuscarTituloOficialSpring - crearQueryTituloOficial - numRegistros"+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error BuscarTituloOficialSpring - crearQueryTituloOficial - numRegistros"+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}
		return numRegistros;
	}
	

	/**
	 * Obtiene el titulo oficial manager.
	 *
	 * @return tituloOficialManager TituloOficialManager
	 */
	public TituloOficialManager getTituloOficialManager() {
		return tituloOficialManager;
	}

	/**
	 * Establece el titulo oficial manager.
	 *
	 * @param tituloOficialManager TituloOficialManager
	 */
	public void setTituloOficialManager(TituloOficialManager tituloOficialManager) {
		this.tituloOficialManager = tituloOficialManager;
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
