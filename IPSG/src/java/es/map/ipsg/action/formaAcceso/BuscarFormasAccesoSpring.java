package es.map.ipsg.action.formaAcceso;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.FormaAccesoQuery;
import es.map.ipsg.bean.FormaAccesoBean;
import es.map.ipsg.form.FormasAccesoForm;
import es.map.ipsg.manager.FormaAccesoManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class BuscarFormasAccesoSpring.
 */
public class BuscarFormasAccesoSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarFormasAccesoSpring.class);
	
	/** el forma acceso manager. */
	//Declaracion de manager
	private FormaAccesoManager formaAccesoManager;	
	
	/**
	 * Crea una nueva buscar formas acceso spring.
	 */
	public BuscarFormasAccesoSpring() {
		try {			
			setFormaAccesoManager((FormaAccesoManager) getBean("formasAccesoManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error BuscarFormasAccesoSpring():",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("BuscarFormasAccesoSpring -start");
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_tablaBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_formaAcceso = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.formaAcceso");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_formaAcceso);
		//****************************************************************** 

		//Cojo el lugar de la llamada
	try{
		//Coger el form del jsp
		FormasAccesoForm formulario;
		formulario = (FormasAccesoForm) form;
		String busqueda = formulario.getSubmit();
		if(this.getRequest().getParameter("llamada") != null){
			busqueda = this.getRequest().getParameter("llamada");
		}
		
		
		// Si viene desde el menu borramos los datos previamente introducidos
		if ("S".equals(this.getRequest().getParameter("menu")))
		{
			formulario.setDescripcion("");	// Si viene del menu borramos todos sus campos
			busqueda = "";
		}		

		String numReg = this.getRequest().getParameter("numRegistro");
		logger.info("NumRegistros: "+numReg);
		String cambios = this.getRequest().getParameter("cambios");
		//Creamos la Query		
		FormaAccesoQuery formasAccesoQuery;		
		if(formulario.getCampo()!= null && !"Paginar".equals(busqueda)){
			try{	
				
				String campo=cargarCampos(formulario.getCampo());
				formulario.setCampo(campo);
			}catch(Exception e){
				logger.error("Error BuscarFormasAccesoSpring() -cargarCampos - campo:"+ formulario.getCampo(),e);
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
			
		//Creamos la query para busca la especialidad
		formasAccesoQuery=crearQueryFormasAcceso(formulario,paginaActual,numReg);

				
		List<FormaAccesoBean> formasAcceso = null;
		if("Buscar".equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){
			//nyADIR METODO buscarFormasAccesoAll(formasAccesoQuery)
			formasAcceso = formaAccesoManager.buscarFormaAccesoAll(formasAccesoQuery);
			//Error sin resultados
			if (formasAcceso != null && formasAcceso.size() == 0) {
				setRequestAttribute("sinResultados", "ok");
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
		//Recorrer la lista, para comprobar los estados de cada convocatoria para activar los enlaces
		if(formasAcceso != null && formasAcceso.size()!= 0){
			pagTotal= formasAcceso.get(0).getNumTotal();
			numPaginas = pagTotal / numRegistros;
			int resto = pagTotal % numRegistros;
			if(resto > 0){
				numPaginas++;
			}
			if(formasAcceso.size()>numRegistros){
				formasAcceso.remove(formasAcceso.size()-1);
			}			
		}
		
		//Pasar la convocatoria y la pagina al jsp
		if(paginaActual==null || "".equals(paginaActual))
			paginaActual="1";		
		
		setRequestAttribute("formasAcceso", formasAcceso);		
		setRequestAttribute("paginaActual", paginaActual);
		setRequestAttribute("paginasTotales", numPaginas);
		setRequestAttribute("cambios", cambios);
		
		getLogger().debug("BuscarCuerposEscalaSpring -end");
	
	}catch(Exception eGenerico){
		logger.error("Error BuscarFormasAccesoSpring() - doExecute :",eGenerico);
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
			logger.error("Error comprobarEntero()  numero registros :"+resultado,e);
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
			logger.error("Error BuscarFormasAccesoSpring() - cargarCampos :"+codCampo,e);
		}
			if (campo != null && !"".equals(campo) && !campo.equals("null")){
				
					switch(codCampo){
					//Los campos de ordenacion del jsp
						case 1:auxCampo = "id";break;
						case 2:auxCampo = "codigo";break;
						case 3:auxCampo = "descripcion";break;
						case 4:auxCampo = "tipoAcceso";break;	
						case 5:auxCampo = "visible";break;
						default:break;
					}
				
			}
		return auxCampo;
	}

	/**
	 * Crear query formas acceso.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param numReg el num reg
	 * @return el forma acceso query
	 */
	private FormaAccesoQuery crearQueryFormasAcceso(FormasAccesoForm formulario,String paginaActual,String numReg) {
//Recoger los datos del formulario
		
		String descripcion = formulario.getDescripcion();
		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();
		
		
		if(formulario.getNumRegistro() == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros=0;
		if(numReg != null && !"".equals(numReg)){
			try{
			numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error BuscarFormasAccesoSpring() - crearQueryFormasAcceso - numero registros :"+numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error BuscarFormasAccesoSpring() - crearQueryFormasAcceso - numero registros :"+numRegistros,e);
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
		FormaAccesoQuery formaAccesoQuery = new FormaAccesoQuery();
		calcularNumeroLineas(numRegistros,paginaActual,formaAccesoQuery,campo,direccion,descripcion);
		
	
		
		//Devulelve el centro gestor con los filtros
		return formaAccesoQuery;		
	}
	
	/**
	 * Calcular numero lineas.
	 *
	 * @param numRegistros el num registros
	 * @param paginaActual el pagina actual
	 * @param formaAccesoQuery el forma acceso query
	 * @param campo el campo
	 * @param direccion el direccion
	 * @param descripcion el descripcion
	 */
	private void calcularNumeroLineas(int numRegistros, String paginaActual, FormaAccesoQuery formaAccesoQuery, String campo, String direccion, String descripcion) {
		int tamanyoPaginacionReal = numRegistros;
		int tamanyoPaginacion = tamanyoPaginacionReal+1;
		int numPagina = Integer.parseInt(paginaActual);
		int primerRegistro = (numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
		
		
		if(paginaActual != null){
			formaAccesoQuery.setMaxResults(tamanyoPaginacion);
			formaAccesoQuery.setFirstResult(primerRegistro);
		}
		formaAccesoQuery.setCalculateNumResults(true);
		//Comprobar los filtros para realizar la busqueda
			
		if(descripcion!= null  && !"".equals(descripcion)){
			try{
			
				formaAccesoQuery.setDescripcion(descripcion);
				formaAccesoQuery.setDescripcionComparator(Comparator.LIKE_LEFT_RIGHT);
			
			}catch(Exception e){
				logger.error("Error BuscarFormasAccesoSpring() - crearQueryFormasAcceso :",e);
			}
		}
		String estado= RESOURCE_BUNDLE.getString("field.estado.activo");						
		formaAccesoQuery.setEstado(estado.charAt(0));
		
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			OrderType orden = ("up".equals(direccion)==true ? OrderType.ASC:OrderType.DESC);
			formaAccesoQuery.addOrder(campo, orden);
		}
	}

	/**
	 * Obtiene el forma acceso manager.
	 *
	 * @return the formaAccesoManager
	 */
	public FormaAccesoManager getFormaAccesoManager() {
		return formaAccesoManager;
	}

	/**
	 * Establece el forma acceso manager.
	 *
	 * @param formaAccesoManager the formaAccesoManager to set
	 */
	public void setFormaAccesoManager(FormaAccesoManager formaAccesoManager) {
		this.formaAccesoManager = formaAccesoManager;
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
