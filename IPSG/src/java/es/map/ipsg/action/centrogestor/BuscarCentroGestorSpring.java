package es.map.ipsg.action.centrogestor;

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
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.MinisterioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.form.CentroGestorForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class BuscarCentroGestorSpring.
 */
public class BuscarCentroGestorSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** el properties. */
	private static Properties properties;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarCentroGestorSpring.class);
	
	/** La constante STRING_LLAMADA. */
	private static final String STRING_LLAMADA = "llamada";
	
	/** el centro gestor manager. */
	//Declaracion de manager
	private CentroGestorManager centroGestorManager;
		
	
	/**
	 * Crea una nueva buscar centro gestor spring.
	 */
	public BuscarCentroGestorSpring() {
		try {
			
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error BuscarCentroGestorSpring():",e );
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("BuscarCentroGestorSpring -start");
		try{
			//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********	
			String menu_tablabase = RESOURCE_MESSAGE.getString("field.menu.tablasBase");
			this.getRequest().getSession().setAttribute("pagActiva",menu_tablabase);
			String subMenu_centroGestor = RESOURCE_MESSAGE.getString("field.menuLateral.tablasBase.centroGestor");
			this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_centroGestor);
			//****************************************************************** 
			
			//Coger el form del jsp
			CentroGestorForm formulario;
			formulario = (CentroGestorForm) form;
			String busqueda = formulario.getSubmit();
			String numReg = this.getRequest().getParameter("numRegistro");
			logger.info("NumRegistros: "+numReg);
			String cambios = this.getRequest().getParameter("cambios");
			
			//Creamos la Query		
			CentroGestorQuery centroGestorQuery;
			logger.info(busqueda);
			logger.info(this.getRequest().getParameter(STRING_LLAMADA));
			
			// Si viene desde el menu borramos los datos previamente introducidos
			busqueda = busqueda(formulario,busqueda);
			
			
			//Llama a la funcion para crear la Query pasandole el formulario		
			String paginaActual = this.getRequest().getParameter("paginaActual");
			String paginaTotal = this.getRequest().getParameter("paginasTotales");		
			

			paginaActual(formulario,paginaActual,paginaTotal);
			
			
			//Creamos la query para busca la especialidad
			centroGestorQuery=crearQueryCentroGestor(formulario,paginaActual,numReg);
					
			List<CentroGestorBean> centroGestor = null;
			if("Buscar".equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){ 
				centroGestor = centroGestorManager.buscarCentroGestorAll(centroGestorQuery);
				//Error sin resultados
				if (centroGestor != null && centroGestor.size() == 0) {
					SpringMessages errors = new SpringMessages();
					errors.add("errorSinResultados", new SpringMessage("field.cuerpoEscala.error"));
					this.setRequestAttribute("org.apache.spring.ERROR", errors);
				}
				cambios = "Correcto";
			}
			int pagTotal = 0;
			int numPaginas=0;
			if(formulario.getNumRegistro() == null && numReg == null){
				//Para el primer paso para el action, se establece que los registros por pagina son 5
				formulario.setNumRegistro(properties.getProperty("conf.numRegistrosListados"));
			}
			int numRegistros = 0;
			numRegistros = numRegistros(numReg,numRegistros,formulario);
			
			
			//Recorrer la lista, para comprobar los estados de cada convocatoria para activar los enlaces
			if(centroGestor != null && centroGestor.size()!= 0){
				pagTotal= centroGestor.get(0).getNumTotal();
				numPaginas = pagTotal / numRegistros;
				int resto = pagTotal % numRegistros;
				if(resto > 0){
					numPaginas++;
				}
				if(centroGestor.size()>numRegistros){
					centroGestor.remove(centroGestor.size()-1);
				}
				
			}
			
			//Pasar la convocatoria y la pagina al jsp			
			
			
			setRequestAttribute("centroGestor", centroGestor);
			setRequestAttribute("cambios", cambios);			
			setRequestAttribute("paginasTotales", numPaginas);	
			return "success";
			
		}catch(Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error BuscarCentroGestorSpring() - doExecute ",e );
			return "nosuccess";
		}
	}
	
	/**
	 * Busqueda.
	 *
	 * @param formulario el formulario
	 * @param busqueda el busqueda
	 * @return el string
	 */
	public String busqueda(CentroGestorForm formulario, String busqueda) {
		if ("S".equals(this.getRequest().getParameter("menu")))
		{
			formulario.setDescripcion("");	// Si viene del menu borramos todos sus campos
			busqueda = "";
		}			
		
		if(this.getRequest().getParameter(STRING_LLAMADA) != null && !"".equals(this.getRequest().getParameter(STRING_LLAMADA))){
			busqueda = this.getRequest().getParameter(STRING_LLAMADA);
		}
		if(StringUtils.isNotEmpty(formulario.getCampo()) && !"Paginar".equals(busqueda)){
			try{	
				String campo=cargarCampos(formulario.getCampo());
				formulario.setCampo(campo);
			}catch(Exception e){
				logger.error("Error BuscarCentroGestorSpring() - cargarCampos",e );
			}			
		}
		
		return busqueda;
	}
	
	/**
	 * Num registros.
	 *
	 * @param numReg el num reg
	 * @param numRegistros el num registros
	 * @param formulario el formulario
	 * @return el int
	 */
	private int numRegistros(String numReg, int numRegistros, CentroGestorForm formulario) {
		if(StringUtils.isNotEmpty(numReg)){
			try{
				numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error BuscarCentroGestorSpring()- numero de registros " + numRegistros ,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
				numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error BuscarCentroGestorSpring()- numero de registros " + numRegistros ,e );
				
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}
		if(numRegistros > 50){
			numRegistros = 50;
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}else{
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}
		
		return numRegistros;
	}
	
	/**
	 * Pagina actual.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param paginaTotal el pagina total
	 */
	private void paginaActual(CentroGestorForm formulario, String paginaActual, String paginaTotal) {
		if(formulario.isPulsaIr()){
			paginaActual = formulario.getNumeroPaginaIr().toString();
			logger.info("PaginaActualIr: "+ paginaActual);
			formulario.setPulsaIr(false);
			formulario.setNumeroPaginaIr(null);
		}else{
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
		
		if(paginaActual==null || "".equals(paginaActual))
			paginaActual = "1";
		
		setRequestAttribute("paginaActual", paginaActual);
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
						case 2:auxCampo = "ministerio";break;
						case 3:auxCampo = "descripcion";break;
						case 4:auxCampo = "siglas";break;
						case 5:auxCampo = "ejercicio";break;
						case 6:auxCampo = "visible";break;
						default:break;
					}
				
			}
		return auxCampo;
	}

	/**
	 * Crear query centro gestor.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param numReg el num reg
	 * @return el centro gestor query
	 */
	private CentroGestorQuery crearQueryCentroGestor(CentroGestorForm formulario, String paginaActual, String numReg) {
//Recoger los datos del formulario
		
		String descripcion = formulario.getDescripcion();
		if(descripcion!=null){
			descripcion=descripcion.toUpperCase();
		}
		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();
		
		
		if(formulario.getNumRegistro() == null && numReg == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(properties.getProperty("conf.numRegistrosListados"));
		}
		int numRegistros = 0;
		if(numReg != null && !"".equals(numReg)){
			try{
				numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error BuscarCentroGestorSpring() - crearQueryCentroGestor - numRegistros"+ numRegistros,e );
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
				numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error BuscarCentroGestorSpring() - crearQueryCentroGestor - numRegistros"+ numRegistros,e );
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
			paginaActual = properties.getProperty("conf.primerPagina");
			formulario.setPaginaActual(paginaActual);
		}
		//Calcular el numero de lineas de cada paginacion
		int tamanyoPaginacionReal = numRegistros;
		int tamanyoPaginacion = tamanyoPaginacionReal+1;
		int numPagina = Integer.parseInt(paginaActual);
		int primerRegistro = (numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
		
		CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
		crearQueryCentroGestor2(paginaActual,centroGestorQuery,tamanyoPaginacion,primerRegistro,descripcion,campo,direccion);
		
		//Devulelve el centro gestor con los filtros
		return centroGestorQuery;		
	}
	
	/**
	 * Crear query centro gestor 2.
	 *
	 * @param paginaActual el pagina actual
	 * @param centroGestorQuery el centro gestor query
	 * @param tamanyoPaginacion el tamanyo paginacion
	 * @param primerRegistro el primer registro
	 * @param descripcion el descripcion
	 * @param campo el campo
	 * @param direccion el direccion
	 */
	public void crearQueryCentroGestor2(String paginaActual, CentroGestorQuery centroGestorQuery, int tamanyoPaginacion, int primerRegistro, String descripcion, String campo, String direccion) {
		if(paginaActual != null){
			centroGestorQuery.setMaxResults(tamanyoPaginacion);
			centroGestorQuery.setFirstResult(primerRegistro);
		}
		centroGestorQuery.setCalculateNumResults(true);
		//Comprobar los filtros para realizar la busqueda
			
		if(descripcion!= null  && !"".equals(descripcion)){			
			centroGestorQuery.setDescripcion(descripcion);
			centroGestorQuery.setDescripcionComparator(Comparator.LIKE_LEFT_RIGHT);
		}
		String estado= RESOURCE_MESSAGE.getString("field.estado.activo");						
		centroGestorQuery.setEstado(estado.charAt(0));
		
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			OrderType orden =("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);	
			if("ministerio".equals(campo)){
				MinisterioQuery ministerioQuery = new MinisterioQuery();
				ministerioQuery.addOrder("siglas", orden);
				centroGestorQuery.setMinisterio(ministerioQuery);
			}else
				centroGestorQuery.addOrder(campo,orden);						
		}
		centroGestorQuery.setCalculateNumResults(true);
		
		if(paginaActual!=null){
			centroGestorQuery.setMaxResults(tamanyoPaginacion);
			centroGestorQuery.setFirstResult(primerRegistro);
		}
	}
	
	/**
	 * Obtiene el centro gestor manager.
	 *
	 * @return the especialidadManager
	 */
	public CentroGestorManager getCentroGestorManager() {
		return centroGestorManager;
	}

	/**
	 * Establece el centro gestor manager.
	 *
	 * @param centroGestorManager el nuevo centro gestor manager
	 */
	public void setCentroGestorManager(CentroGestorManager centroGestorManager) {
		this.centroGestorManager = centroGestorManager;
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
