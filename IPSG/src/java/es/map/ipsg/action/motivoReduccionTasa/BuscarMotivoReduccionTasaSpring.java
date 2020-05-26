package es.map.ipsg.action.motivoReduccionTasa;

import java.util.List;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.MotivoReduccionTasaQuery;
import es.map.ipsg.bean.MotivoReduccionTasaBean;
import es.map.ipsg.form.MotivoReduccionTasaForm;
import es.map.ipsg.manager.MotivoReduccionTasaManager;
import es.map.ipsg.util.Constantes;

/**
 * El Class BuscarMotivoReduccionTasaSpring.
 */
public class BuscarMotivoReduccionTasaSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarMotivoReduccionTasaSpring.class);
	
	/** el motivo manager. */
	//Declaracion de manager
	private MotivoReduccionTasaManager motivoManager;

	

	
	/**
	 * Crea una nueva buscar motivo reduccion tasa spring.
	 */
	public BuscarMotivoReduccionTasaSpring() {
		try {
			setMotivoReduccionTasaManager((MotivoReduccionTasaManager)getBean("motivoReduccionTasaManager"));
			
		} catch (Exception e) {
			logger.error("Error BuscarMotivoReduccionTasaSpring:" ,e);
		}
		
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
	
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********	
		String menu_cuerposEscala = RESOURCE_MESSAGE.getString("field.menu.tablasBase");
		this.getRequest().getSession().setAttribute("pagActiva",menu_cuerposEscala);
		String subMenu_motivos = RESOURCE_MESSAGE.getString("field.menuLateral.tablasBase.motivoReduccion");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_motivos);
		//****************************************************************** 

		//Cojo el lugar de la llamada
	try{
		//Coger el form del jsp
		MotivoReduccionTasaForm formulario;
		formulario = (MotivoReduccionTasaForm) form;
		String busqueda = formulario.getSubmit();
		
		// Si viene desde el menu borramos los datos previamente introducidos
		if ("S".equals(this.getRequest().getParameter("menu")))
		{
			formulario.setDescripcion("");	// Si viene del menu borramos todos sus campos
			busqueda = "";
		}
		

		//Creamos la Query
		MotivoReduccionTasaQuery motivoQuery;	
		//Si ha seleccionado un campo de ordenacion se le asigna el atributo de ordenacion
		int aux = 0;
		if(formulario.getCampo()!= null){
			try{
				aux = Integer.parseInt(formulario.getCampo());
				String campo=cargarCampos(formulario.getCampo());
				formulario.setCampo(campo);
			}catch(Exception e){
				logger.error("Error BuscarMotivoReduccionTasaSpring - campo :"+ formulario.getCampo() ,e);
			}			
		}
		//Llama a la funcion para crear la Query pasandole el formulario
		
		String paginaActual = this.getRequest().getParameter("paginaActual");
		String paginaTotal = this.getRequest().getParameter("paginasTotales");
		String numReg = this.getRequest().getParameter("numRegistro");
		
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

		motivoQuery=crearQueryMotivo(formulario,paginaActual,numReg);
			
		
		//Coger la lista de convocatorias a mostrar
	
		List<MotivoReduccionTasaBean> motivo = null;
		if("Buscar".equals(busqueda) || "Ordenar".equals(busqueda) || "Paginar".equals(busqueda)){ 
			motivo = motivoManager.buscarMotivoReduccionTasaAll(motivoQuery);
			//Error sin resultados
			if (motivo != null && motivo.size() == 0) {
				setRequestAttribute("sinResultados", "si");	
			}
		}
		int pagTotal = 0, numPaginas=0;
		if(formulario.getNumRegistro() == null){
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
		if(motivo != null && motivo.size()!= 0){
			pagTotal= motivo.get(0).getNumTotal();
			numPaginas = pagTotal / numRegistros;
			int resto = pagTotal % numRegistros;
			if(resto > 0){
				numPaginas++;
			}
			if(motivo.size()>numRegistros){
				motivo.remove(motivo.size()-1);
			}
			
		}
		
		//Pasar la convocatoria y la pagina al jsp
		if(paginaActual==null || "".equals(paginaActual))
			paginaActual="1";
		
		setRequestAttribute("motivo", motivo);		
		setRequestAttribute("paginaActual", paginaActual);
		setRequestAttribute("paginasTotales", numPaginas);
		formulario.setCampo(String.valueOf(aux));
		
	}catch(Exception eGenerico){
		logger.error("Error BuscarMotivoReduccionTasaSpring - doExecute:" ,eGenerico);
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
			logger.error("Error BuscarMotivoReduccionTasaSpring - comprobarEntero() - numRegistros :"+ resultado ,e);
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
			logger.error("Error BuscarMotivoReduccionTasaSpring - cargarCampos- Error parsear  campo :"+ codCampo ,e);
		}
		
		if (campo != null && !"".equals(campo) && !campo.equals("null")){
			
				switch(codCampo){
				//Los campos de ordenacion del jsp
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
						auxCampo = "textoExplicativo";
						break;
					case 5:
						auxCampo = "porcentajeDescuento";
						break;
					case 6:
						auxCampo = "visible";
						break;
					default:
						break;
				}
			
		}
	
		return auxCampo;
	}
			
	

	/**
	 * Crear query motivo.
	 *
	 * @param formulario el formulario
	 * @param paginaActual el pagina actual
	 * @param numReg el num reg
	 * @return el motivo reduccion tasa query
	 */
	private MotivoReduccionTasaQuery crearQueryMotivo(MotivoReduccionTasaForm formulario,String paginaActual,String numReg) {
		//Recoger los datos del formulario
		
		String descripcion = formulario.getDescripcion();
		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();
		
		
		if(formulario.getNumRegistro() == null){
			//Para el primer paso para el action, se establece que los registros por pagina son 5
			formulario.setNumRegistro(Constantes.NUMERO_REGISTROS_PAGINA);
		}
		int numRegistros = 0;
		if(numReg != null && !"".equals(numReg)){
			try{
			numRegistros = Integer.parseInt(numReg);
			}catch(Exception e){
				logger.error("Error BuscarMotivoReduccionTasaSpring - crearQueryMotivo- numRegistros:"+ numRegistros ,e);
				numRegistros = Integer.parseInt(Constantes.NUMERO_REGISTROS_PAGINA);
			}
		}else{
			try{
			numRegistros = Integer.parseInt(formulario.getNumRegistro());
			}catch(Exception e){
				logger.error("Error BuscarMotivoReduccionTasaSpring - crearQueryMotivo- numRegistros:"+ numRegistros ,e);
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
		MotivoReduccionTasaQuery motivoQuery = new MotivoReduccionTasaQuery();
		calcularNumeroLineas(numRegistros,descripcion,paginaActual,motivoQuery,campo,direccion);
		
		
		
		//Devulelve la convocatoria con los filtros
		return motivoQuery;
		
	}
	
	/**
	 * Calcular numero lineas.
	 *
	 * @param numRegistros el num registros
	 * @param descripcion el descripcion
	 * @param paginaActual el pagina actual
	 * @param motivoQuery el motivo query
	 * @param campo el campo
	 * @param direccion el direccion
	 */
	private void calcularNumeroLineas(int numRegistros, String descripcion, String paginaActual, MotivoReduccionTasaQuery motivoQuery, String campo, String direccion) {
		
		int tamanyoPaginacionReal = numRegistros;
		int tamanyoPaginacion = tamanyoPaginacionReal+1;
		int numPagina = Integer.parseInt(paginaActual);
		int primerRegistro = (numPagina*tamanyoPaginacionReal)-tamanyoPaginacionReal;
		
		
		if(paginaActual != null){
			motivoQuery.setMaxResults(tamanyoPaginacion);
			motivoQuery.setFirstResult(primerRegistro);
		}
		motivoQuery.setCalculateNumResults(true);
		//Comprobar los filtros para realizar la busqueda
			
		if(descripcion!= null  && !"".equals(descripcion)){
			try{
						
			motivoQuery.setDescripcion(descripcion);
			motivoQuery.setDescripcionComparator(Comparator.LIKE_LEFT_RIGHT);
			
			}catch(Exception e){
				logger.error("Error BuscarMotivoReduccionTasaSpring - crearQueryMotivo- descripcion:"+ descripcion ,e);
			}
		}
		String estado= RESOURCE_MESSAGE.getString("field.estado.activo");						
		motivoQuery.setEstado(estado.charAt(0));
		
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			OrderType orden = ("up".equals(direccion)==true ? OrderType.ASC:OrderType.DESC);
			motivoQuery.addOrder(campo,orden);
		}
	}

	/**
	 * Obtiene el motivo manager.
	 *
	 * @return the motivoManager
	 */
	public MotivoReduccionTasaManager getMotivoManager() {
		return motivoManager;
	}

	/**
	 * Establece el motivo reduccion tasa manager.
	 *
	 * @param motivoManager the motivoManager to set
	 */
	public void setMotivoReduccionTasaManager(MotivoReduccionTasaManager motivoManager) {
		this.motivoManager = motivoManager;
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

	


