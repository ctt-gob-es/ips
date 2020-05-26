package es.map.ipsg.action.centrogestor;


import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CentroGestorQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.form.CentroGestorForm;
import es.map.ipsg.manager.CentroGestorManager;
import es.map.ipsg.util.Constantes;


/**
 * El Class ListarCentroGestorSpring.
 */
public class ListarCentroGestorSpring extends AbstractSpring {
	
	/** el centro gestor manager. */
	private CentroGestorManager centroGestorManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ListarCentroGestorSpring.class);
	
	/** La constante STRING_LLAMADA. */
	private static final String STRING_LLAMADA = "llamada";
	
	/** La constante STRING_PARAMETRO. */
	private static final String STRING_PARAMETRO = "parametro";
	
	/** La constante STRING_PARAMETRO2. */
	private static final String STRING_PARAMETRO2 = "parametro2";
	
	/**
	 * Crea una nueva listar centro gestor spring.
	 */
	public ListarCentroGestorSpring() {
		try{
			setCentroGestorManager((CentroGestorManager) getBean("centrosGestoresManager"));
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error ListarCentroGestorSpring()",e );
		}
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {

		getLogger().debug("ListarCentroGestorSpring -start");
		CentroGestorForm formulario;
		formulario = (CentroGestorForm) form;
		
		try{
			logger.info("Entra en el action");
			String busqueda = this.getRequest().getParameter("submit");
		
			if(busqueda == null || "".equals(busqueda)){
				busqueda = this.getRequest().getParameter(STRING_LLAMADA);
			}
			logger.info(busqueda);
			String parametro = this.getRequest().getParameter(STRING_PARAMETRO);
			logger.info("parametro: "+ this.getRequest().getParameter(STRING_PARAMETRO));
			logger.info("parametroGuARDADO: "+ this.getRequest().getAttribute(STRING_PARAMETRO));
			String parametro2 = this.getRequest().getParameter(STRING_PARAMETRO2);
			logger.info("parametro2: "+ this.getRequest().getParameter(STRING_PARAMETRO2));
			logger.info("parametroGuARDADO2: "+ this.getRequest().getAttribute(STRING_PARAMETRO2));
			if(parametro != null && !"".equals(parametro)){
				setRequestAttribute(STRING_PARAMETRO, parametro);
				formulario.setParametro(parametro);
			}else{
				if(formulario.getParametro() != null && !"".equals(formulario.getParametro())){
					setRequestAttribute(STRING_PARAMETRO, formulario.getParametro());				
				}else{
					String paramAux = (String) this.getRequest().getAttribute(STRING_PARAMETRO);
					if(paramAux != null && !"".equals(paramAux)){
						setRequestAttribute(STRING_PARAMETRO, parametro);
						formulario.setParametro(parametro);
					}
				}
			}
			
			if(parametro2 != null && !"".equals(parametro2)){
				setRequestAttribute(STRING_PARAMETRO2, parametro2);
				formulario.setParametro2(parametro2);
			}else{
				if(formulario.getParametro2() != null && !"".equals(formulario.getParametro2())){
					setRequestAttribute(STRING_PARAMETRO2, formulario.getParametro2());				
				}else{
					String paramAux = (String) this.getRequest().getAttribute(STRING_PARAMETRO2);
					if(paramAux != null && !"".equals(paramAux)){
						setRequestAttribute(STRING_PARAMETRO2, parametro2);
						formulario.setParametro2(parametro2);
					}
				}
			}
			
			String numReg = this.getRequest().getParameter("numRegistro");
			if(numReg == null || "".equals(numReg)){
				numReg = formulario.getNumRegistro();
			}
			
			logger.info("NumRegistros: "+numReg);
			String cambios = this.getRequest().getParameter("cambios");
			String paginaActual = this.getRequest().getParameter("paginaActual");
			String paginaTotal = this.getRequest().getParameter("paginasTotales");
			String listarTodos = this.getRequest().getParameter("listarTodos");
			
			if(listarTodos!=null){
				formulario.setListarTodos(listarTodos);
			}
			
			if(this.getRequest().getParameter(STRING_LLAMADA) != null && busqueda == null){
				busqueda = this.getRequest().getParameter(STRING_LLAMADA);
			}
			
			if(paginaActual != null && !"".equals(paginaActual) && paginaTotal != null && !"".equals(paginaTotal)){
				int pagActu = Integer.parseInt(paginaActual);		
				int pagTotales = Integer.parseInt(paginaTotal);
	
				if(pagActu > pagTotales){
					paginaActual = String.valueOf(pagActu-1);
				}
			}
			
			CentroGestorQuery centroGestorQuery = new CentroGestorQuery();
			if(formulario.getCampo()!= null && !"Paginar".equals(busqueda)){
				String campo=cargarCampos(formulario.getCampo());
				formulario.setCampo(campo);			
			}
			
			//Dar valor a la Query
			String campo = formulario.getCampo();
			String direccion = formulario.getDireccion();
			
			if(formulario.getNumRegistro() == null && numReg == null){
				//Para el primer paso para el action, se establece que los registros por pagina son 5
				formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
			}
			
			int numRegistros = 0;
			if(numReg != null){ 
				numRegistros = Integer.parseInt(numReg);
			}else{
				numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}
			if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual) || "Buscar".equals(busqueda)){
				paginaActual = "1";			
			}
			int tamanyoPaginacionReal = numRegistros;
			int tamanyoPaginacion = tamanyoPaginacionReal+1;
			int numPagina = Integer.parseInt(paginaActual);
			int primerRegistro = (numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
			
			centroGestorQuery.setEstado('A');
			
			// Se mostrarán sólo los registros visibles si se indica que no se muestren todos
			if(formulario.getListarTodos()!=null && formulario.getListarTodos().equals("N")){
				centroGestorQuery.setVisible('S');
			}
			
			if(campo != null && !"0".equals(campo) && !"".equals(campo)){
				if("up".equals(direccion)){
					centroGestorQuery.addOrder(campo,OrderType.ASC);
				}else{
					centroGestorQuery.addOrder(campo,OrderType.DESC);
				}
			}
			if(!"".equals(formulario.getId()) && formulario.getId() != null){
				int id = comprobarEntero(formulario.getId());
				centroGestorQuery.setId(id);
			}
			
			if(!"".equals(formulario.getDescripcion()) && formulario.getDescripcion() != null){
				centroGestorQuery.setDescripcionComparator(Comparator.LIKE_LEFT_RIGHT);
				centroGestorQuery.setDescripcion(formulario.getDescripcion());
			}
			
			centroGestorQuery.setCalculateNumResults(true);
			if(paginaActual != null){
				centroGestorQuery.setMaxResults(tamanyoPaginacion);
				centroGestorQuery.setFirstResult(primerRegistro);
			}
			
			List<CentroGestorBean> centrosGestores = null;
			if("Buscar".equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){
				centrosGestores = centroGestorManager.buscarCentroGestorAll(centroGestorQuery);
				cambios = "Correcto";
				formulario.setCambios("Correcto");
			}
			int pagTotal = 0;
			int numPaginas=0;
			if(centrosGestores != null && centrosGestores.size()!= 0){
				pagTotal= centrosGestores.get(0).getNumTotal();
				numPaginas = pagTotal / numRegistros;
				int resto = pagTotal % numRegistros;
				if(resto > 0){
					numPaginas++;
				}
				if(centrosGestores.size()>numRegistros){
					centrosGestores.remove(centrosGestores.size()-1);
				}			
			}
			
					
			setRequestAttribute("centrosGestores", centrosGestores);
			setRequestAttribute("paginaActual", paginaActual);
			setRequestAttribute("paginasTotales", numPaginas);	
			setRequestAttribute("submit", busqueda);
			setRequestAttribute("cambios", cambios);
			setRequestAttribute("numRegistros", numRegistros);
			
			getLogger().debug("ListarCentroGestorSpring -end");
			return "success";
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error ListarCentroGestorSpring() - doExecute",e );
			this.getRequest().setAttribute("descripcionError", e.getMessage());
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
			logger.error("Imposible parsear el id de Centro Gestor");
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
			logger.error("Error cargarCampos():",e );
		}
			if (campo != null && !"".equals(campo) && !campo.equals("null")){
				
					switch(codCampo){
					//Los campos de ordenacion del jsp
						case 1:auxCampo = "id";break;
						case 2:auxCampo = "descripcion";break;
						default:break;
					}
				
			}
		return auxCampo;
	}

	/**
	 * Obtiene el centro gestor manager.
	 *
	 * @return el centro gestor manager
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
