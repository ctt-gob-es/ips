package es.map.ipsg.action.entidadFinanciera;

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
import es.map.ips.model.EntidadFinancieraQuery;
import es.map.ips.model.TipoPagoQuery;
import es.map.ipsg.bean.EntidadFinancieraBean;
import es.map.ipsg.form.BuscarEntidadFinancieraForm;
import es.map.ipsg.manager.EntidadFinancieraManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class BuscarEntidadFinancieraSpring.
 */
public class BuscarEntidadFinancieraSpring extends AbstractSpring {

	// Declarar los resource
	
	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarEntidadFinancieraSpring.class);

	/** el entidad financiera manager. */
	// Declaracion de manager
	private EntidadFinancieraManager entidadFinancieraManager;

	/**
	 * Crea una nueva buscar entidad financiera spring.
	 */
	public BuscarEntidadFinancieraSpring() {
		try {
			setEntidadFinancieraManager((EntidadFinancieraManager) getBean("entidadFinancieraManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error BuscarEntidadFinancieraSpring :",e);
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		getLogger().debug("BuscarEntidadFinancieraSpring -start");
		// *********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY
		// **********
		
		String menu_tablaBase = RESOURCE_MESSAGE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva", menu_tablaBase);
		String subMenu_EntidadFinanciera = RESOURCE_MESSAGE.getString("field.menuLateral.tablasBase.entidadFinanciera");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_EntidadFinanciera);
		// ******************************************************************
	try{
		// Coger el form del jsp
		BuscarEntidadFinancieraForm formulario;
		formulario = (BuscarEntidadFinancieraForm) form;
		String busqueda = formulario.getSubmit();

		// Creamos la Query
		EntidadFinancieraQuery entidadFinancieraQuery;
		
		// Llama a la funcion para crear la Query pasandole el formulario
		String paginaActual = this.getRequest().getParameter("paginaActual");
		String paginaTotal = this.getRequest().getParameter("paginasTotales");
		String numReg = this.getRequest().getParameter("numRegistro");
		logger.info("NumRegistros: " + numReg);
		String cambios = this.getRequest().getParameter("cambios");

		//Comprobar si viene del menu
		String menu = this.getRequest().getParameter("menu");
		if(menu != null && menu.equals("S")){
			formulario.setDescripcion("");
			formulario.setEstado("0");
			busqueda = "";
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
		
		if (paginaActual != null && !"".equals(paginaActual) && paginaTotal != null && !"".equals(paginaTotal)) {
			int pagActu = Integer.parseInt(paginaActual);
			int pagTotales = Integer.parseInt(paginaTotal);

			if (pagActu > pagTotales) {
				paginaActual = String.valueOf(pagTotales);
			}
		}
		
		if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual)/* || "Buscar".equals(busqueda)*/){
			paginaActual = Constantes.PAGINA_PRINCIPAL;	
			formulario.setPaginaActual(paginaActual);
		}

		// Creamos la query para busca la especialidad
		entidadFinancieraQuery = crearEntidadFinancieraQuery(formulario,numReg,paginaActual);

		List<EntidadFinancieraBean> entidadFinanciera = null;
		if ("Buscar".equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)) {
			entidadFinanciera = entidadFinancieraManager.buscarEntidadFinancieraAll(entidadFinancieraQuery);
			cambios = "Correcto";
		}
		int pagTotal = 0;
		int numPaginas = 0;
		if (formulario.getNumRegistro() == null && numReg == null) {
			// Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = 0;
		if(StringUtils.isNotEmpty(numReg)){
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

		// Recorrer la lista, para comprobar los estados de cada entidad
		// financiera para activar los enlaces
		if (entidadFinanciera != null && entidadFinanciera.size() != 0) {
			pagTotal = entidadFinanciera.get(0).getNumTotal();
			numPaginas = pagTotal / numRegistros;
			int resto = pagTotal % numRegistros;
			if (resto > 0) {
				numPaginas++;
			}
			if (entidadFinanciera.size() > numRegistros) {
				entidadFinanciera.remove(entidadFinanciera.size() - 1);
			}
		}
		
		// Si no hemos encontrado ningun registro mostramos mensaje de error
		if (pagTotal == 0 && "Correcto".equals(cambios)) {
			logger.info("No existe ningun registro ");
			SpringMessages errors = new SpringMessages();
			errors.add("errorSinResultados", new SpringMessage("field.entidadFinanciera.error"));
			this.setRequestAttribute("org.apache.spring.ERROR", errors);
		}

		// Pasar la convocatoria y la pagina al jsp
		if(paginaActual==null||"".equals(paginaActual))
			paginaActual = "1";
		
		setRequestAttribute("entidadFinanciera", entidadFinanciera);
		setRequestAttribute("paginaActual", paginaActual);
		setRequestAttribute("paginasTotales", numPaginas);
		setRequestAttribute("cambios", cambios);
		getLogger().debug("BuscarEntidadFinancieraSpring -end");
		
	}catch(Exception eGenerico){
		logger.error("Error BuscarEntidadFinancieraSpring - doExecute:",eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
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
			logger.error(e);
			e.printStackTrace();
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
		String auxCampo = null;
		int codCampo = 0;
		try {
			codCampo = Integer.parseInt(campo);
			switch (codCampo) {
			// Los campos de ordenacion del jsp
			case 1:
				auxCampo = "id";
				break;
			case 2:
				auxCampo = "codigo";
				break;
			case 3:
				auxCampo = "descripcion";
				break;
			case 4:
				auxCampo = "tipoPago";
				break;
			case 5:
				auxCampo = "estado";
				break;
			case 6:
				auxCampo = "actualizada";
				break;
			default:
				break;
			}
		} catch (Exception e) {
			logger.error("Error BuscarEntidadFinancieraSpring - cargarCampos:"+ codCampo,e);
		}

		return auxCampo;
	}

	/**
	 * Crear entidad financiera query.
	 *
	 * @param formulario el formulario
	 * @param numReg el num reg
	 * @param paginaActual el pagina actual
	 * @return el entidad financiera query
	 */
	private EntidadFinancieraQuery crearEntidadFinancieraQuery(BuscarEntidadFinancieraForm formulario, String numReg, String paginaActual) {

		// Recoger los datos del formulario
		String descripcion = formulario.getDescripcion();
		String estado = formulario.getEstado();
		String campo = cargarCampos(formulario.getCampo()); 
		String direccion = formulario.getDireccion();

		if (formulario.getNumRegistro() == null && numReg == null) {
			// Para el primer paso para el action, se establece que los
			// registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = 0;
		if(StringUtils.isNotEmpty(numReg)){
			try{
			numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error BuscarEntidadFinancieraSpring - numRegistros:"+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error BuscarEntidadFinancieraSpring - numRegistros:"+ numRegistros,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}
		if(numRegistros > 50){
			numRegistros = 50;
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}else{
			formulario.setNumRegistro(String.valueOf(numRegistros));
		}
		// Si la pagina actual no esta definida, se establece a 1
		if ("0".equals(paginaActual) || paginaActual == null
				|| "".equals(paginaActual)) {
			paginaActual = "1";
			formulario.setPaginaActual(paginaActual);
		}
		// Calcular el numero de lineas de cada paginacion
		EntidadFinancieraQuery entidadFinancieraQuery = new EntidadFinancieraQuery();
		calcularNumeroLineas(numRegistros,paginaActual,descripcion,entidadFinancieraQuery,estado,campo,direccion);
		
		// Devulelve la entidad financiera con los filtros
		return entidadFinancieraQuery;
	}
	
	/**
	 * Calcular numero lineas.
	 *
	 * @param numRegistros el num registros
	 * @param paginaActual el pagina actual
	 * @param descripcion el descripcion
	 * @param entidadFinancieraQuery el entidad financiera query
	 * @param estado el estado
	 * @param campo el campo
	 * @param direccion el direccion
	 */
	private void calcularNumeroLineas(int numRegistros, String paginaActual, String descripcion, EntidadFinancieraQuery entidadFinancieraQuery, String estado, String campo, String direccion) {
		int tamanyoPaginacionReal = numRegistros;
		int tamanyoPaginacion = tamanyoPaginacionReal + 1;
		int numPagina = Integer.parseInt(paginaActual);
		int primerRegistro = (numPagina * tamanyoPaginacionReal) - tamanyoPaginacionReal;

		
		if (paginaActual != null) {
			entidadFinancieraQuery.setMaxResults(tamanyoPaginacion);
			entidadFinancieraQuery.setFirstResult(primerRegistro);
		}
		entidadFinancieraQuery.setCalculateNumResults(true);
		// Comprobar los filtros para realizar la busqueda

		if (StringUtils.isNotEmpty(descripcion)) {
			entidadFinancieraQuery.setDescripcion(descripcion);
			entidadFinancieraQuery.setDescripcionComparator(Comparator.LIKE_LEFT_RIGHT);
		}
		
		if(StringUtils.isNotEmpty(estado)){
			try{
				entidadFinancieraQuery.setEstado(estado.charAt(0));
			} catch (Exception e) {
				logger.error("Error BuscarEntidadFinancieraSpring - seteando estado en BuscarEntidadFinanciera:",e);
				
			}
		}
		
		if (campo != null && !"0".equals(campo) && !"".equals(campo)) {
			OrderType orden =("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);	
			if(campo.equals("tipoPago")){
				TipoPagoQuery tipoPagoQuery = new TipoPagoQuery();
				tipoPagoQuery.addOrder("descripcion",orden);
				entidadFinancieraQuery.setTipoPago(tipoPagoQuery);
				entidadFinancieraQuery.addOrder("id", OrderType.ASC);
			} 
			entidadFinancieraQuery.addOrder(campo, orden);
			if (campo.equals("estado")) {
				entidadFinancieraQuery.addOrder("id", OrderType.ASC);
			}
		} else {
			entidadFinancieraQuery.addOrder("id", OrderType.ASC);
		}
		
	}

	/**
	 * Obtiene el entidad financiera manager.
	 *
	 * @return the especialidadManager
	 */
	public EntidadFinancieraManager getEntidadFinancieraManager() {
		return entidadFinancieraManager;
	}

	/**
	 * Establece el entidad financiera manager.
	 *
	 * @param entidadFinancieraManager el nuevo entidad financiera manager
	 */
	public void setEntidadFinancieraManager(
			EntidadFinancieraManager entidadFinancieraManager) {
		this.entidadFinancieraManager = entidadFinancieraManager;
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
