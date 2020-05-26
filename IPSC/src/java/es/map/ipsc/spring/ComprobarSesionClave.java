package es.map.ipsc.spring;

import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.ConvocatoriasAbiertasViewQuery;
import es.map.ips.model.ConvocatoriasSubsanarViewQuery;
import es.map.ipsc.form.BuscaConvocatoriasForm;
import es.map.ipsc.manager.convocatorias.ConvocatoriasManager;
import es.map.ipsc.spring.convocatorias.BuscarConvocatoriasSpring;

/**
 * El Class ComprobarSesionClave.
 */
public class ComprobarSesionClave extends AbstractSecureSpring {

	/** La constante MESSAGE_RESOUCE. */
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante STRING_USUARIOCLAVE. */
	private static final String STRING_USUARIOCLAVE = "usuarioClave";
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(BuscarConvocatoriasSpring.class);
	
	/** el convocatorias manager. */
	private ConvocatoriasManager convocatoriasManager;
	
	/**
	 * Crea una nueva comprobar sesion clave.
	 */
	public ComprobarSesionClave(){ //Metodo vacio
		try {
			setConvocatoriasManager((ConvocatoriasManager) getBean("convocatoriaManager"));
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error buscar convocatorias",e);
		}
	}

	/**
	 * Do execute.
	 *
	 * @param form el form
	 * @return el string
	 * @throws Exception el exception
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_solicitudes = RESOURCE_MESSAGE.getString("field.menu.listaSolic");
		this.getRequest().getSession().setAttribute("pagActiva", menu_solicitudes);
		//****************************************************************** 
		
		String llamadaRequest = this.getRequest().getParameter("llamada");
		String convocatoriaReq = this.getRequest().getParameter("convocatoria");
		
		String retn = "";
		BuscaConvocatoriasForm formulario = null;
		formulario = (BuscaConvocatoriasForm) form;

		
		//Comprobamos si el usuario ya se ha autenticado en cl@ve a través de la sesión
		 if(llamadaRequest.equals("Contactar")){
			if (getSessionAttribute(STRING_USUARIOCLAVE) != null) {
				retn="successContactar";
			}else{
				retn= "noSesionCont";
			}
		 }
		 
		 if(llamadaRequest.equals("Buscar")){
			if (getSessionAttribute(STRING_USUARIOCLAVE) != null) {
				retn="successBuscar";
			}else{
				retn= "noSesionBuscar";
			}
		 }
		 
		 if(llamadaRequest.equals("Inscribirse")){
			if (getSessionAttribute(STRING_USUARIOCLAVE) != null) {
				retn="successInscribirse";
			}else{
				retn= "noSesionIns";
			}
		 }
		 
		 if(llamadaRequest.equals("Subsanar")){
			if (getSessionAttribute(STRING_USUARIOCLAVE) != null) {
				retn="successSubsanar";
			}else{
				retn= "noSesionSub";
			}
		 }
		 
		 setRequestAttribute("llamadaReq", llamadaRequest);
		 setRequestAttribute("convocatoriaReq", convocatoriaReq);
		
		 numConvocatorias(formulario);
		 
		 return retn;
		}

	/**
	 * Num convocatorias.
	 *
	 * @param formulario el formulario
	 */
	private void numConvocatorias(BuscaConvocatoriasForm formulario) {
		 ConvocatoriasSubsanarViewQuery convocatoriaQuerySub = new ConvocatoriasSubsanarViewQuery();
		 ConvocatoriasAbiertasViewQuery convocatoriaQueryMain = new ConvocatoriasAbiertasViewQuery();
		 
		 boolean verTodoSub = formulario.isVerTodoSub();
		 boolean verTodoMain = formulario.isVerTodo();
		 
		 Integer convocatoriasMain = 0;
		 Integer convocatoriasSub = 0;
		 
		 convocatoriasSub = convocatoriasManager.recuperarNumConvocatoriaSubsanadas(convocatoriaQuerySub);
		 convocatoriasMain = convocatoriasManager.recuperarNumConvocatoriaAbiertas(convocatoriaQueryMain);
		 
		 if (verTodoMain && convocatoriasMain != null && convocatoriasMain>0){
				this.setRequestAttribute("verTodo", 0);
				this.setRequestAttribute("numTotalConvocatorias", convocatoriasMain);
			}else{
				this.setRequestAttribute("verTodo", 1);
				this.setRequestAttribute("numTotalConvocatorias", convocatoriasMain);
			}
		 if (verTodoSub && convocatoriasSub != null && convocatoriasSub>0){
				this.setRequestAttribute("verTodoSub", 0);
				this.setRequestAttribute("numTotalConvocatoriasSub", convocatoriasSub);
			}else{
				this.setRequestAttribute("verTodoSub", 1);
				this.setRequestAttribute("numTotalConvocatoriasSub", convocatoriasSub);
			}
	}
	
	/**
	 * Establece el convocatorias manager.
	 *
	 * @param convocatoriasManager el nuevo convocatorias manager
	 */
	public void setConvocatoriasManager(ConvocatoriasManager convocatoriasManager) {
		this.convocatoriasManager = convocatoriasManager;
	}
}