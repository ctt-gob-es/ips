package es.map.ipsg.action.especialidad;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.EspecialidadQuery;
import es.map.ipsg.bean.EspecialidadBean;
import es.map.ipsg.form.EspecialidadForm;
import es.map.ipsg.manager.EspecialidadManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class ListarEspecialidadSpring.
 */
public class ListarEspecialidadSpring extends AbstractSpring {
	
	/** el especialidad manager. */
	private EspecialidadManager especialidadManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ListarEspecialidadSpring.class);
	
	/** La constante STRING_PARAMETRO2. */
	private static final String STRING_PARAMETRO2 = "parametro2";
	
	/** La constante STRING_CENTRO. */
	private static final String STRING_CENTRO = "centro";
	
	/** La constante STRING_CAMBIOS. */
	private static final String STRING_CAMBIOS = "cambios";
	
	/** La constante STRING_LLAMADA. */
	private static final String STRING_LLAMADA = "llamada";
	
	/** La constante STRING_PARAMETRO. */
	private static final String STRING_PARAMETRO = "parametro";	


	/**
	 * Crea una nueva listar especialidad spring.
	 */
	public ListarEspecialidadSpring() {
		try{
			setEspecialidadManager((EspecialidadManager) getBean("especialidadManager"));
		}catch(Exception e){
			logger.warn(e);
			logger.error("Error ListarEspecialidadSpring():",e);
		}
	}


	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		
		getLogger().debug("ListarEspecialidadSpring -start");
		EspecialidadForm formulario;
		formulario = (EspecialidadForm) form;
		try{
			logger.info("Entra en el action");
			String busqueda = formulario.getSubmit();
		
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
			String centro = this.getRequest().getParameter(STRING_CENTRO);
			logger.info("centro: "+ this.getRequest().getParameter(STRING_CENTRO));
			logger.info("centroGuARDADO2: "+ this.getRequest().getAttribute(STRING_CENTRO));
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
			if(centro != null && !"".equals(centro)){
				setRequestAttribute(STRING_CENTRO, centro);
				formulario.setCentro(centro);
			}else{
				if(formulario.getCentro() != null && !"".equals(formulario.getCentro())){
					setRequestAttribute(STRING_CENTRO, formulario.getCentro());				
				}else{
					String paramAux = (String) this.getRequest().getAttribute(STRING_CENTRO);
					if(paramAux != null && !"".equals(paramAux)){
						setRequestAttribute(STRING_CENTRO, centro);
						formulario.setCentro(centro);
					}
				}
			}
			
			
			String cambios = this.getRequest().getParameter(STRING_CAMBIOS);
			logger.info(cambios);
			String numReg = this.getRequest().getParameter("numRegistro");
			logger.info("NumRegistros: "+numReg);
			String paginaActual = this.getRequest().getParameter("paginaActual");
			String paginaTotal = this.getRequest().getParameter("paginasTotales");
			String listarTodos = this.getRequest().getParameter("listarTodos");
			
			if(listarTodos!=null){
				formulario.setListarTodos(listarTodos);
			}
			if(this.getRequest().getParameter(STRING_LLAMADA) != null && (busqueda == null || "".equals(busqueda))){
				busqueda = this.getRequest().getParameter(STRING_LLAMADA);
			}
			logger.info(busqueda);
			if(paginaActual != null && !"".equals(paginaActual) && paginaTotal != null && !"".equals(paginaTotal)){
				int pagActu = Integer.parseInt(paginaActual);		
				int pagTotales = Integer.parseInt(paginaTotal);
	
				if(pagActu > pagTotales){
					paginaActual = String.valueOf(pagActu-1);
				}
			}
			EspecialidadQuery especialidadquery = new EspecialidadQuery();
			if(formulario.getCampo()!= null && !"Paginar".equals(busqueda)){
				try{
					String campo=cargarCampos(formulario.getCampo());
					formulario.setCampo(campo);
				}catch(Exception e){
					logger.error("Error ListarEspecialidadSpring() - cargarCampos - campo:"+formulario.getCampo(),e);
				}			
			}
			
			
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
			if("0".equals(paginaActual) || paginaActual == null || "".equals(paginaActual) /*|| "Buscar".equals(busqueda)*/){
				paginaActual = "1";
			}
			int tamanyoPaginacionReal = numRegistros;
			int tamanyoPaginacion = tamanyoPaginacionReal+1;
			int numPagina = Integer.parseInt(paginaActual);
			int primerRegistro = (numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
			
			especialidadquery.setEstado('A');
			// S�lo se mostrar�n los registros visibles si se indica que no se muestren todos
			if(formulario.getListarTodos()!=null && formulario.getListarTodos().equals("N")){
				especialidadquery.setVisible('S');
			}
			
			if(campo != null && !"0".equals(campo) && !"".equals(campo)){
				if("up".equals(direccion)){
					especialidadquery.addOrder(campo,OrderType.ASC);
				}else{
					especialidadquery.addOrder(campo,OrderType.DESC);
				}
			}
			if(!"".equals(centro) && centro != null){
				CuerpoEscalaQuery cuerpoEscalaQuery = new CuerpoEscalaQuery();
				int codCentro = Integer.parseInt(centro);
				cuerpoEscalaQuery.setId(codCentro);
				especialidadquery.setCuerpoEscala(cuerpoEscalaQuery);
			}
			int codCuerpo;
			if(formulario.getId() != null && formulario.getId() != "0" && !formulario.getId().equals("")){
				codCuerpo = comprobarEntero(formulario.getId());
				especialidadquery.setId(codCuerpo);
			}
			if(!"".equals(formulario.getDescripcion()) && formulario.getDescripcion() != null){
				especialidadquery.setDescripcionComparator(Comparator.LIKE_LEFT_RIGHT);
				especialidadquery.setDescripcion(formulario.getDescripcion());
			}
			
			especialidadquery.setCalculateNumResults(true);
			if(paginaActual != null){
				especialidadquery.setMaxResults(tamanyoPaginacion);
				especialidadquery.setFirstResult(primerRegistro);
			}
			
			List<EspecialidadBean> especialidadesBean = null;
			if("Buscar".equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){
				especialidadesBean = especialidadManager.buscarEspecialidadAll(especialidadquery);
				cambios = "Correcto";
				formulario.setCambios("Correcto");
			}
			int pagTotal = 0;
			int numPaginas=0;
			if(especialidadesBean != null && especialidadesBean.size()!= 0){
				pagTotal= especialidadesBean.get(0).getNumTotal();
				numPaginas = pagTotal / numRegistros;
				int resto = pagTotal % numRegistros;
				if(resto > 0){
					numPaginas++;
				}
				if(especialidadesBean.size()>numRegistros){
					especialidadesBean.remove(especialidadesBean.size()-1);
				}			
			}
			setRequestAttribute(STRING_CAMBIOS, cambios);
			
			setRequestAttribute("submit", busqueda);		
			setRequestAttribute("especialidades", especialidadesBean);
			setRequestAttribute("paginaActual", paginaActual);
			setRequestAttribute("paginasTotales", numPaginas);	
			setRequestAttribute(STRING_CAMBIOS, cambios);
			setRequestAttribute("numRegistro", numRegistros);
			
			getLogger().debug("ListarEspecialidadSpring -end");
			return "success";
			
		}catch (Exception e){
			logger.warn(e);
			this.getRequest().setAttribute("descripcionError", e.getMessage());
			logger.error("Error ListarEspecialidadSpring() -  doExecute :",e);
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
			logger.error("Error comprobarEntero() - Imposible parsear el id Especialidad");
		}
		return resultado;
	}
	
	
	
	
	

	/**
	 * Obtiene el especialidad manager.
	 *
	 * @return el especialidad manager
	 */
	public EspecialidadManager getEspecialidadManager() {
		return especialidadManager;
	}


	/**
	 * Establece el especialidad manager.
	 *
	 * @param especialidadManager el nuevo especialidad manager
	 */
	public void setEspecialidadManager(EspecialidadManager especialidadManager) {
		this.especialidadManager = especialidadManager;
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
		logger.error("Error ListarEspecialidadSpring() - cargarCampos - campo :"+ codCampo,e);
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
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}

}
