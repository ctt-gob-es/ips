package es.map.ipsg.action.categoria;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.common.spring.SpringMessages;
import es.map.ips.model.CategoriaQuery;
import es.map.ipsg.bean.CategoriaBean;
import es.map.ipsg.form.CategoriaForm;
import es.map.ipsg.manager.CategoriaManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class BuscarCategoriaSpring.
 */
public class BuscarCategoriaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarCategoriaSpring.class);
	
	/** el categoria manager. */
	//Declaracion de manager
	private CategoriaManager categoriaManager;
	
	/**
	 * Crea una nueva buscar categoria spring.
	 */
	public BuscarCategoriaSpring() {
		try {			
			setCategoriaManager((CategoriaManager) getBean("categoriaManager"));
		} catch (Exception e) {
			logger.warn(e);	
			logger.error("Error BuscarCategoriaSpring():",e );
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("BuscarCategoriaSpring -start");
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********	
		String menu_tablabase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva",menu_tablabase);
		String subMenu_Categoria = RESOURCE_BUNDLE.getString("field.menuLateral.tablasBase.categoria");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_Categoria);
		//****************************************************************** 
	try{
		//Cojo el lugar de la llamada
		
		//Coger el form del jsp
		CategoriaForm formulario;
		formulario = (CategoriaForm) form;
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
		CategoriaQuery categoriaQuery;
		
		if(StringUtils.isNotEmpty(formulario.getCampo()) && !"Paginar".equals(busqueda)){
			cargarCampos2(formulario);		
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
		
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual)/* || "Buscar".equals(busqueda)*/){
			paginaActual = Constantes.PAGINA_PRINCIPAL;
			formulario.setPaginaActual(paginaActual);
		}
		
		//Creamos la query para busca la categoria
		categoriaQuery=crearQueryCategoria(formulario,paginaActual,numReg);
				
		List<CategoriaBean> categoria = null;
		if("Buscar".equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){
			categoria = categoriaManager.buscarCategoriaAll(categoriaQuery);
			//Error sin resultados
			if (categoria != null && categoria.size() == 0) {
				SpringMessages errors = new SpringMessages();
				errors.add("errorSinResultados", new SpringMessage("field.cuerpoEscala.error"));
				this.setRequestAttribute("org.apache.spring.ERROR", errors);
			}
		}
		int pagTotal = 0;
		int numPaginas=0;
		int numRegistros=0;
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
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
		if(categoria != null && categoria.size()!= 0){
			pagTotal= categoria.get(0).getNumTotal();
			numPaginas = pagTotal / numRegistros;
			int resto = pagTotal % numRegistros;
			if(resto > 0){
				numPaginas++;
			}
			if(categoria.size()>numRegistros){
				categoria.remove(categoria.size() - 1);
			}			
		}
		
		//Pasar la pagina al jsp
		setRequestAttribute("categoria", categoria);
		
		if(paginaActual==null || paginaActual.equals(""))
			paginaActual="1";
		
		setRequestAttribute("paginaActual", paginaActual);
		setRequestAttribute("paginasTotales", numPaginas);
		setRequestAttribute("cambios", cambios);
		
		getLogger().debug("BuscarCuerposEscalaSpring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error BuscarcategoriaSpring -  doExecute: "+ eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
		return "errorGenerico";
	}
		return "success";
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
			logger.error("Error cargarCampos():",e );
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
	 * Cargar campos 2.
	 *
	 * @param formulario el formulario
	 */
	private void cargarCampos2(CategoriaForm formulario) {
		try{	
			String campo=cargarCampos(formulario.getCampo());
			formulario.setCampo(campo);
		}catch(Exception e){
			logger.error("Error BuscarCategoriaSpring():", e );
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
			logger.error(e);
			e.printStackTrace();
			resultado = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		return resultado;
	}

	/**
	 * Crear query categoria.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param numReg el num reg
	 * @return el categoria query
	 */
	private CategoriaQuery crearQueryCategoria(CategoriaForm formulario, String paginaActual,String numReg) {
		//Recoger los datos del formulario
		
		String descripcion = formulario.getDescripcion();
		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();
		
		int numRegistros=0;
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		if(numReg != null && !"".equals(numReg)){
			try{
			numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error(e);
				e.printStackTrace();
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error(e);
				e.printStackTrace();
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
		int primerRegistro = (numPagina*tamanyoPaginacionReal) - tamanyoPaginacionReal;
		
		CategoriaQuery categoriaQuery = new CategoriaQuery();
		
		crearQueryCategoria2(paginaActual,categoriaQuery,tamanyoPaginacion,primerRegistro,descripcion,campo,direccion);
	
		
		//Devulelve el centro gestor con los filtros
		return categoriaQuery;		
	}
	
	/**
	 * Crear query categoria 2.
	 *
	 * @param paginaActual el pagina actual
	 * @param categoriaQuery el categoria query
	 * @param tamanyoPaginacion el tamanyo paginacion
	 * @param primerRegistro el primer registro
	 * @param descripcion el descripcion
	 * @param campo el campo
	 * @param direccion el direccion
	 */
	private void crearQueryCategoria2(String paginaActual, CategoriaQuery categoriaQuery, int tamanyoPaginacion, int primerRegistro, String descripcion, String campo, String direccion) {
		
		if(paginaActual != null){
			categoriaQuery.setMaxResults(tamanyoPaginacion);
			categoriaQuery.setFirstResult(primerRegistro);
		}
		categoriaQuery.setCalculateNumResults(true);
		//Comprobar los filtros para realizar la busqueda
			
		if(descripcion!= null  && !"".equals(descripcion)){
			categoriaQuery.setDescripcionComparator(Comparator.LIKE_LEFT_RIGHT);
			categoriaQuery.setDescripcion(descripcion);	
		}
		
		String estado = RESOURCE_BUNDLE.getString("field.estado.activo");						
		categoriaQuery.setEstado(estado.charAt(0));		
		
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			if("up".equals(direccion)){
				categoriaQuery.addOrder(campo,OrderType.ASC);
			}else{
				categoriaQuery.addOrder(campo,OrderType.DESC);
			}
		}
	}

	/**
	 * Obtiene el categoria manager.
	 *
	 * @return the categoriaManager
	 */
	public CategoriaManager getCategoriaManager() {
		return categoriaManager;
	}

	/**
	 * Establece el categoria manager.
	 *
	 * @param categoriaManager the categoriaManager to set
	 */
	public void setCategoriaManager(CategoriaManager categoriaManager) {
		this.categoriaManager = categoriaManager;
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
