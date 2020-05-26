package es.map.ipsg.action.solicitud;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import es.map.ips.common.exception.BusinessException;
import es.map.ips.common.model.Comparator;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.CentroGestorQuery;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.CuerpoEscalaQuery;
import es.map.ips.model.EstadoSolicitudQuery;
import es.map.ips.model.MinisterioQuery;
import es.map.ips.model.PagoSolicitudQuery;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ips.model.SolComunRegistradasViewQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ips.model.TipoSolicitudQuery;
import es.map.ips.model.Usuario;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CorreoElectronicoBean;
import es.map.ipsg.bean.ParametrosConfiguracionBean;
import es.map.ipsg.bean.SolicitudXmlSigpBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.BuscarSolicitudesForm;
import es.map.ipsg.manager.ConvocatoriasManager;
import es.map.ipsg.manager.CorreoElectronicoManager;
import es.map.ipsg.manager.ParametroConfiguracionManager;
import es.map.ipsg.manager.SolicitudesRegistradasManager;
import es.map.ipsg.manager.UsuarioManager;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.EnvioMails;
import es.map.ipsg.util.Util;
import es.map.ipsg.util.UtilesIPSG;
import es.map.ipsg.util.Zipeador;


/**
 * El Class ExportarSigpSolicitudesSpring.
 *
 * @author amartinl
 */
public class ExportarSigpSolicitudesSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante STRING_EXPORTARSIGPSOLICITUDESERROR. */
	private static final String STRING_EXPORTARSIGPSOLICITUDESERROR = "ExportarSigpSolicitudesAction -Error";
	
	/** La constante STRING_MENSAJECONFIRMACION. */
	private static final String STRING_MENSAJECONFIRMACION = "mensajeConfirmacion";
	
	/** La constante STRING_ERROR. */
	private static final String STRING_ERROR = "error";
	
	/** La constante STRING_GESTDOCUMENTAL_RUTADOCS_TEMP. */
	private static final String STRING_GESTDOCUMENTAL_RUTADOCS_TEMP = "gestDocumental.rutaDocsTemp";
	
	/** La constante STRING_SIMPLEDATEFORMAT. */
	private static final String STRING_SIMPLEDATEFORMAT = "dd/MM/yyyy";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ExportarSigpSolicitudesSpring.class);
	
	/** La constante ENCODING_VALUE. */
	private static final String ENCODING_VALUE = "ISO-8859-1";
	
	/** el properties. */
	private static Properties properties;
	
	/** el convocatoria manager. */
	//Declaracion de Manager
	private ConvocatoriasManager convocatoriaManager;
	
	/** el solicitudes registradas manager. */
	private SolicitudesRegistradasManager solicitudesRegistradasManager;
	
	/** el parametro configuracion manager. */
	private ParametroConfiguracionManager parametroConfiguracionManager;
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;
	
	/** el correo electronico manager. */
	private CorreoElectronicoManager correoElectronicoManager;
	
	/** el utiles IPSG. */
	private UtilesIPSG utilesIPSG=new UtilesIPSG();
	
	/** La constante STRING_ERROR_EXPORTARSIGPSOLICITUDES. */
	private static final String STRING_ERROR_EXPORTARSIGPSOLICITUDES = "Error ExportarSigpSolicitudesSpring:";
	

	/**
	 * Crea una nueva exportar sigp solicitudes spring.
	 */
	public ExportarSigpSolicitudesSpring() {
		try {
				setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));
				setConvocatoriaManager((ConvocatoriasManager) getBean("convocatoriaManager"));
				setSolicitudesRegistradasManager((SolicitudesRegistradasManager) getBean("solicitudesRegistradasManager"));
				setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
				setCorreoElectronicoManager((CorreoElectronicoManager)getBean("correoElectronicoManager"));
				properties = (Properties) getBean("propertiesBean");
			} catch (Exception e) {
				logger.error(STRING_ERROR_EXPORTARSIGPSOLICITUDES,e);
		}
	}



	/**
	 * Do execute.
	 *
	 * @param form  SpringForm
	 * @return resultado
	 * @throws Exception Exception
	 */
	protected String doExecute(SpringForm form) throws Exception {
	
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		final ResourceBundle RESOURCE_BUNDLE_2 = ResourceBundle.getBundle(MESSAGE_RESOUCE);	
		String menu_solicitudes = RESOURCE_BUNDLE_2.getString("field.menu.solicitudes");
		this.getRequest().getSession().setAttribute("pagActiva",menu_solicitudes);
		//******************************************************************
		getLogger().debug("ExportarSigpSolicitudesSpring -start");
	try{	
		//Cogemos el form del jsp
		BuscarSolicitudesForm formulario = new BuscarSolicitudesForm();
		formulario = (BuscarSolicitudesForm) form;
		String[] aSolicitudesSel=null;
		ArrayList<SolicitudXmlSigpBean> aSolicitudXmlSigpBean = new ArrayList<SolicitudXmlSigpBean>();
		SolicitudComunQuery solicitudQuery = new SolicitudComunQuery();
		SolComunRegistradasViewQuery solicitudRegistradaQuery = new SolComunRegistradasViewQuery();
		Iterator<SolicitudXmlSigpBean> it;
		ArrayList<Document> fichero=new ArrayList<Document>();
		String zipGenerado=null;
		String checkBox = formulario.getMarcarTodo();
		
		/*
		 * En caso de marcar todo, se exportará toda la búsqueda,
		 * sino sólo las solicitudes seleccionadas
		 * 
		 */
		
		if (formulario.getAccion().equals("Correo")|| formulario.getAccion().equals("Fichero")){
			
			if ("TodoOk".equals(checkBox)) {
				//Creamos la query para mostrar todas las solicitudes
				solicitudRegistradaQuery = crearQuerySolicitudRegistrada(formulario);
				//Recuperamos todos los datos de las solicitudes seleccionadas
				try{
				aSolicitudXmlSigpBean = solicitudesRegistradasManager.buscarSolicitudXmlSigpVista(solicitudRegistradaQuery);
				}catch(Exception e){
					getLogger().debug(STRING_EXPORTARSIGPSOLICITUDESERROR+formulario.getAccion());
					logger.error(STRING_ERROR_EXPORTARSIGPSOLICITUDES+ formulario.getAccion() ,e);
					setRequestAttribute(STRING_MENSAJECONFIRMACION , RESOURCE_MESSAGE.getString("field.exportarXmlSigp.Error")+formulario.getAccion().toString());
					return STRING_ERROR;
				}

			}else {
				//Recuperamos las solicitudes que ha marcado
				aSolicitudesSel = formulario.getSolicitudesSel();
						
				for(int i=0; i < aSolicitudesSel.length; i++)
				{
					Long idSolicitudSel = Long.parseLong(aSolicitudesSel[i]);
					solicitudQuery.addIdSolicitudIn(idSolicitudSel);
				}
				
				//Recuperamos todos los datos de las solicitudes seleccionadas
				try{
				aSolicitudXmlSigpBean = solicitudesRegistradasManager.buscarSolicitudXmlSigpAll(solicitudQuery);
				}catch(Exception e){
					getLogger().debug(STRING_EXPORTARSIGPSOLICITUDESERROR+formulario.getAccion());
					logger.error(STRING_ERROR_EXPORTARSIGPSOLICITUDES+ formulario.getAccion() ,e);
					setRequestAttribute(STRING_MENSAJECONFIRMACION , RESOURCE_MESSAGE.getString("field.exportarXmlSigp.Error")+formulario.getAccion().toString());
					return STRING_ERROR;
				}
			}
			
			
			
		
	
			//Creación de un XML por cada una  las solicitudes recuperadas
			it=aSolicitudXmlSigpBean.iterator();
			try{
			while(it.hasNext()){
				fichero.add(generarXmlSigp(it.next(), formulario));
			}
			}catch(Exception e){
				getLogger().debug(STRING_EXPORTARSIGPSOLICITUDESERROR);
				logger.error(STRING_ERROR_EXPORTARSIGPSOLICITUDES ,e);
				setRequestAttribute(STRING_MENSAJECONFIRMACION , RESOURCE_MESSAGE.getString("field.exportarXmlSigp.ErrorFichero"));
			}
			
			zipGenerado=	generarFichero(fichero);
		}
		
		if(formulario.getAccion().equals("Correo")){
			//Rellenamos el Bean del correo
			CorreoElectronicoBean correoElectronicoBean=new CorreoElectronicoBean();
			//Correo del usuario logeado en la apliaccion
			
			//Tomamos el usuario que se ha logueado
			String sUsernameLogin = recuperarUsuario();
			
			if (sUsernameLogin.equals(STRING_ERROR)) {
				return sUsernameLogin;
			}

			//Obtenemos el Usuario que está logueado en la aplicación para insertarlo en el log
			UsuarioQuery usuarioQuery = new UsuarioQuery();
			usuarioQuery.setLogin(sUsernameLogin); //Le pasamos el login del que está logueado

			UsuarioBean usuarioBean = usuarioManager.buscarUsuario(usuarioQuery);
			Usuario usuario = usuarioManager.toUsuario(usuarioBean);
			
			correoElectronicoBean.setDe(properties.getProperty("mail.de"));
			//Correo indicado en la tabla de parametros.
			ParametrosConfiguracionQuery parametrosConfiguracionQuery = new ParametrosConfiguracionQuery();
			parametrosConfiguracionQuery.setNombreCampo(RESOURCE_MESSAGE.getString("field.parametroConfiguracion.nombreCampo.correoXmlIpsg"));
			ParametrosConfiguracionBean parametroConfig = parametroConfiguracionManager.buscarParametroConfiguracionUnico(parametrosConfiguracionQuery);
			correoElectronicoBean.setPara(parametroConfig.getValorCampo());
			//Asunto y texto
			correoElectronicoBean.setAsunto(RESOURCE_MESSAGE.getString("field.exportarXmlSigp.correoElectronico.asunto"));
			
			String br = RESOURCE_MESSAGE.getString("texto.br");
			String mensaje = RESOURCE_MESSAGE.getString("field.exportarXmlSigp.correoElectronico.mensaje1") + br + 
							RESOURCE_MESSAGE.getString("field.exportarXmlSigp.correoElectronico.mensaje2") + br +
							RESOURCE_MESSAGE.getString("field.exportarXmlSigp.correoElectronico.nombre") + 
									usuario.getNombre() + " " + usuario.getPrimerApellido() + " " +usuario.getSegundoApellido() + br +
							RESOURCE_MESSAGE.getString("field.exportarXmlSigp.correoElectronico.email") + " " +usuario.getEmail() + br +
							RESOURCE_MESSAGE.getString("field.exportarXmlSigp.correoElectronico.nif") + " " +usuario.getNif();
			correoElectronicoBean.setMensaje(mensaje);
		
			//Se adjunta el fichero zip con las solicitudes sigp
			String sZip = properties.getProperty(STRING_GESTDOCUMENTAL_RUTADOCS_TEMP)+zipGenerado+".zip";
			try(FileInputStream f = new FileInputStream(sZip)
			 ){
				
				BufferedInputStream bf = new BufferedInputStream(f);
				 
				 byte[] buf = new byte[bf.available()]; 
				 bf.read(buf);
				 ArrayList<byte[]> arrAjuntos=new ArrayList<byte[]>();
				 arrAjuntos.add(buf);
				 //prueba
				 File file=new File(sZip);
				 ArrayList<File> arrAjuntoFile=new ArrayList<File>();
				 arrAjuntoFile.add(file);
				 correoElectronicoBean.setAdjuntosFile(arrAjuntoFile);
				 //fin prueba
				 //Una vez enviado el correo se debe insertar un registro en la tabla Solicitud_Correo
				//Si el correo ha sido enviado, lo almacenamos en la base de datos
				 if(EnvioMails.envioMail(correoElectronicoBean)){				 
					 	correoElectronicoBean.setEstado(Constantes.CORREO_ELECTRONICO_ESTADO_ENVIADO);
					 	Calendar c = Calendar.getInstance();
						c.add(Calendar.DATE,0);
						Date fechaEnvio = c.getTime();
						
						correoElectronicoBean.setFechaEnvio(fechaEnvio);
						
							
							correoElectronicoManager.guardarCorreoElectronico(correoElectronicoBean);
							setRequestAttribute(STRING_MENSAJECONFIRMACION ,RESOURCE_MESSAGE.getString("field.exportarXmlSigp.mail"));
					bf.close();
					f.close();
					 //borramos el zip
					 File fZip=new File(sZip);
					 fZip.delete();
					
							
					
				}else{
					setRequestAttribute(STRING_MENSAJECONFIRMACION , RESOURCE_MESSAGE.getString("field.exportarXmlSigp.Errormail"));
				}
			}catch(Exception e){
				getLogger().debug(STRING_EXPORTARSIGPSOLICITUDESERROR);
				logger.error(STRING_ERROR_EXPORTARSIGPSOLICITUDES ,e);
				setRequestAttribute(STRING_MENSAJECONFIRMACION , RESOURCE_MESSAGE.getString("field.exportarXmlSigp.Errormail"));
				
			}
			
		}else if (formulario.getAccion().equals("Fichero")) {
			String sZip = properties.getProperty(STRING_GESTDOCUMENTAL_RUTADOCS_TEMP)+zipGenerado+".zip";
			try (FileInputStream f = new FileInputStream(sZip)
				){
				javax.servlet.ServletOutputStream stream;
				stream = this.getResponse().getOutputStream();
				
				String contentDisposition = "attachment; filename=\"" + zipGenerado+".zip"+ "\"";


				HttpServletResponse resp = this.getResponse();
			
				resp.setContentType("application/octet-stream");
				resp.setHeader("Content-Disposition", contentDisposition);
				
				BufferedInputStream bf = new BufferedInputStream(f);
				 
				 byte[] buf = new byte[bf.available()]; 
				 bf.read(buf);
							
				 stream.write(buf);
				 stream.flush();
				 stream.close();
				 bf.close();				
				 //borramos el zip
				 File fZip=new File(sZip);
				 fZip.delete();
				
			} catch (IOException e) {
				logger.error(STRING_ERROR_EXPORTARSIGPSOLICITUDES ,e);
			}
		}
		
		if (formulario.getAccion().equals("BUSCAR")) {
			formulario.setSolicitudesSel(this.getRequest().getParameterValues("sol"));
		}
		
		formulario.setAccion("BUSCAR"); //Vuelve para poder buscar de nuevo.
		getLogger().debug("ExportarSigpSolicitudesSpring -end");
		
	}catch(Exception eGenerico){
		eGenerico.printStackTrace();
		logger.error("Error: "+ eGenerico);
		this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
		return "errorGenerico";
	}	
		
		return "success";
	}
	
	/**
	 * Recuperar usuario.
	 *
	 * @return el string
	 */
	private String recuperarUsuario()  {
		String sUsernameLogin = "";
		try{
			UsuarioBean usuarioBean = (UsuarioBean) getRequest().getSession().getAttribute("usuario");
			sUsernameLogin = usuarioBean.getLogin();
			return sUsernameLogin;
		}catch(Exception e){
			logger.error("Error ExportarSigpSolicitudesSpring - recuperarUsuarioSesion:"+ sUsernameLogin ,e);
			new BusinessException("exception.recuperarUsuarioSesion");
			return STRING_ERROR;
		}
	}

	/**
	 * Generación del xml.
	 *
	 * @param solicitudXmlSigpBean el solicitud xml sigp bean
	 * @param formulario el formulario
	 * @return el document
	 */
	public Document generarXmlSigp(SolicitudXmlSigpBean solicitudXmlSigpBean,BuscarSolicitudesForm formulario){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document document=null;
		Element eCap7Carga=null;
		
		try {
			builder = factory.newDocumentBuilder();
		    document = builder.newDocument();
		    eCap7Carga = (Element) document.createElement(Constantes.XMLTAG_CAP_7_CARGA_ASPIRANTES);
		    Element elemento=crearNodoProcesoSelectivo(solicitudXmlSigpBean,document);
		    eCap7Carga.appendChild(elemento);
		
		} catch (ParserConfigurationException e1) {
			logger.error("Error ExportarSigpSolicitudesSpring -  generarXmlSigp:",e1);
			getLogger().debug("ErrorExportarSigpSolicitudesSpring -end ");
			getLogger().debug("ErrorExportarSigpSolicitudesAction :"+e1.getMessage());
			getLogger().info("ErrorExportarSigpSolicitudesAction :"+e1.getMessage());
		}
		document.appendChild(eCap7Carga);
		return document;
	}
	
	/**
	 * Crear nodo proceso selectivo.
	 *
	 * @param solicitudXmlSigpBean el solicitud xml sigp bean
	 * @param document el document
	 * @return el element
	 */
	private Element crearNodoProcesoSelectivo(SolicitudXmlSigpBean solicitudXmlSigpBean, Document document) {
        Element eProcesoSelectivo = (Element) document.createElement(Constantes.XMLTAG_PROCESO_SELECTIVO);

        Element eMinisterio = (Element) document.createElement(Constantes.XMLTAG_MINISTERIO);
        eMinisterio.appendChild(document.createTextNode(solicitudXmlSigpBean.getMinisterio()));
        
        Element eCentroGestor = (Element) document.createElement(Constantes.XMLTAG_CENTRO_GESTOR);
        eCentroGestor.appendChild(document.createTextNode(solicitudXmlSigpBean.getCentroGestor()));
        
        Element eAnioConvocatoria = (Element) document.createElement(Constantes.XMLTAG_ANIO_CONVOCATORIA);
        eAnioConvocatoria.appendChild(document.createTextNode(solicitudXmlSigpBean.getAnioConvocatoria()));
        
        Element eCuerpoEscala = (Element) document.createElement(Constantes.XMLTAG_CUERPO_ESCALA);
        eCuerpoEscala.appendChild(document.createTextNode(solicitudXmlSigpBean.getCuerpoEscala()));
        
        Element eEspecialidad = (Element) document.createElement(Constantes.XMLTAG_ESPECIALIDAD);
        eEspecialidad.appendChild(document.createTextNode(solicitudXmlSigpBean.getEspecialidad()));
        
        Element eFecBoe = (Element) document.createElement(Constantes.XMLTAG_FEC_BOE);
        eFecBoe.appendChild(document.createTextNode(utilesIPSG.dateToString(solicitudXmlSigpBean.getFecBoe())));
        
        Element eEntidadConvocante = (Element) document.createElement(Constantes.XMLTAG_ENTIDAD_CONVOCANTE);
        eEntidadConvocante.appendChild(document.createTextNode(solicitudXmlSigpBean.getEntidadConvocante()));
        //Recuperamos el elemento Empleado para añadirlo.
        Element eEmpleado=crearNodoEmpleado(solicitudXmlSigpBean,document);
        
        //Añadimos todos los elementos al elemento principal Proceso Selectivo
        eProcesoSelectivo.appendChild(eMinisterio);
        eProcesoSelectivo.appendChild(eCentroGestor);
        eProcesoSelectivo.appendChild(eAnioConvocatoria);
        eProcesoSelectivo.appendChild(eCuerpoEscala);
        eProcesoSelectivo.appendChild(eEspecialidad);
        eProcesoSelectivo.appendChild(eFecBoe);
        eProcesoSelectivo.appendChild(eEntidadConvocante);
        eProcesoSelectivo.appendChild(eEmpleado);
        
        return eProcesoSelectivo;
    }
	
	/**
	 * Crear nodo empleado.
	 *
	 * @param solicitudXmlSigpBean el solicitud xml sigp bean
	 * @param document el document
	 * @return el element
	 */
	private Element crearNodoEmpleado(SolicitudXmlSigpBean solicitudXmlSigpBean, Document document) {
        Element eEmpleado = (Element) document.createElement(Constantes.XMLTAG_EMPLEADO);

        Element eNombre = (Element) document.createElement(Constantes.XMLTAG_NOMBRE);
        eNombre.appendChild(document.createTextNode(solicitudXmlSigpBean.getNombre()));
        
        Element eApellido1 = (Element) document.createElement(Constantes.XMLTAG_APELLIDO1);
        eApellido1.appendChild(document.createTextNode(solicitudXmlSigpBean.getApellido1()));
        
        Element eApellido2 = (Element) document.createElement(Constantes.XMLTAG_APELLIDO2);
        eApellido2.appendChild(document.createTextNode(solicitudXmlSigpBean.getApellido2()));
        
        Element eSexo = (Element) document.createElement(Constantes.XMLTAG_SEXO);
        eSexo.appendChild(document.createTextNode(String.valueOf(solicitudXmlSigpBean.getSexo())));
        
        Element eFechaNacimiento = (Element) document.createElement(Constantes.XMLTAG_FECHANACIMIENTO);
        eFechaNacimiento.appendChild(document.createTextNode(utilesIPSG.dateToString(solicitudXmlSigpBean.getFechaNacimiento())));
        
        Element eTipoDocumento = (Element) document.createElement(Constantes.XMLTAG_TIPODOCUMENTO);
        if (solicitudXmlSigpBean.getTipoDocumento() != null) {
        	eTipoDocumento.appendChild(document.createTextNode(solicitudXmlSigpBean.getTipoDocumento().toString()));
        }
        
        Element eNumeroDocumento = (Element) document.createElement(Constantes.XMLTAG_NUMERODOCUMENTO);
        eNumeroDocumento.appendChild(document.createTextNode(solicitudXmlSigpBean.getNumeroDocumento()));
        
        //Recuperamos el elemento Direccion para añadirlo.
        Element eDireccion=crearNodoDireccion(solicitudXmlSigpBean,document);
        
        Element eEmail = (Element) document.createElement(Constantes.XMLTAG_EMAIL);       
        Element eValor = (Element) document.createElement(Constantes.XMLTAG_VALOR);
        eValor.appendChild(document.createTextNode(solicitudXmlSigpBean.getEmail()));
        eEmail.appendChild(eValor);
        
        Element eNumeroTelefonoFax = (Element) document.createElement(Constantes.XMLTAG_NUMEROTELEFONOFAX);
        Element eValor2 = (Element) document.createElement(Constantes.XMLTAG_VALOR);
        eValor2.appendChild(document.createTextNode(solicitudXmlSigpBean.getNumeroTelefonoFax()));
        eNumeroTelefonoFax.appendChild(eValor2);
        
        //Recuperamos el elemento Aspirante para añadirlo.
        Element eAspirante=crearNodoAspirante(solicitudXmlSigpBean,document);
        
        //Añadimos todos los elementos al elemento principal Empleado
        eEmpleado.appendChild(eNombre);
        eEmpleado.appendChild(eApellido1);
        eEmpleado.appendChild(eApellido2);
        eEmpleado.appendChild(eSexo);
        eEmpleado.appendChild(eFechaNacimiento);
        eEmpleado.appendChild(eTipoDocumento);
        eEmpleado.appendChild(eNumeroDocumento);
        eEmpleado.appendChild(eDireccion);
        eEmpleado.appendChild(eEmail);
        eEmpleado.appendChild(eNumeroTelefonoFax);
        eEmpleado.appendChild(eAspirante);
        
        return eEmpleado;
    }
	
	/**
	 * Crear nodo direccion.
	 *
	 * @param solicitudXmlSigpBean el solicitud xml sigp bean
	 * @param document el document
	 * @return el element
	 */
	private Element crearNodoDireccion(SolicitudXmlSigpBean solicitudXmlSigpBean, Document document) {
        Element eDireccion = (Element) document.createElement(Constantes.XMLTAG_DIRECCION);

        Element eValorDireccion = (Element) document.createElement(Constantes.XMLTAG_VALORDIRECCION);
        eValorDireccion.appendChild(document.createTextNode(solicitudXmlSigpBean.getValorDireccion()));
        
        Element ePais = (Element) document.createElement(Constantes.XMLTAG_PAIS);
        ePais.appendChild(document.createTextNode(solicitudXmlSigpBean.getPais()));
        
        Element eProvincia = (Element) document.createElement(Constantes.XMLTAG_PROVINCIA);
        eProvincia.appendChild(document.createTextNode(solicitudXmlSigpBean.getProvincia()));
        
        Element eLocalidad = (Element) document.createElement(Constantes.XMLTAG_LOCALIDAD);
        eLocalidad.appendChild(document.createTextNode(solicitudXmlSigpBean.getLocalidad()));
        
        
        //Añadimos todos los elementos al elemento principal Empleado
        eDireccion.appendChild(eValorDireccion);
        eDireccion.appendChild(ePais);
        eDireccion.appendChild(eProvincia);
        eDireccion.appendChild(eLocalidad);
        
        return eDireccion;
    }
	
	/**
	 * Crear nodo aspirante.
	 *
	 * @param solicitudXmlSigpBean el solicitud xml sigp bean
	 * @param document el document
	 * @return el element
	 */
	private Element crearNodoAspirante(SolicitudXmlSigpBean solicitudXmlSigpBean, Document document) {
        Element eAspirante = (Element) document.createElement(Constantes.XMLTAG_ASPIRANTE);

        Element eNumSolicitud = (Element) document.createElement(Constantes.XMLTAG_NUM_SOLICITUD);
        eNumSolicitud.appendChild(document.createTextNode(solicitudXmlSigpBean.getNumSolicitud()));
        
        Element eFechaSol = (Element) document.createElement(Constantes.XMLTAG_FECHA_SOL);
        eFechaSol.appendChild(document.createTextNode(utilesIPSG.dateToString(solicitudXmlSigpBean.getFechaSol())));
        
        //Recuperamos el elemento Convocatoria para añadirlo.
        Element eConvocatoria=crearNodoConvocatoria(solicitudXmlSigpBean,document);
        
        //Recuperamos el elemento Datos Aspirante para añadirlo.
        Element eDatosAspirante=crearNodoDatosAspirante(solicitudXmlSigpBean,document);
        
        Element eAdmision = (Element) document.createElement(Constantes.XMLTAG_ADMISION);
        Element eFormaAcceso=(Element) document.createElement(Constantes.XMLTAG_FORMA_ACCESO);
        eFormaAcceso.appendChild(document.createTextNode(solicitudXmlSigpBean.getFormaAcceso()));
        eAdmision.appendChild(eFormaAcceso);
        
        //Añadimos todos los elementos al elemento principal Empleado
        eAspirante.appendChild(eNumSolicitud);
        eAspirante.appendChild(eFechaSol);
        eAspirante.appendChild(eConvocatoria);
        eAspirante.appendChild(eDatosAspirante);
        eAspirante.appendChild(eAdmision);
        return eAspirante;
    }
	
	/**
	 * Crear nodo convocatoria.
	 *
	 * @param solicitudXmlSigpBean el solicitud xml sigp bean
	 * @param document el document
	 * @return el element
	 */
	private Element crearNodoConvocatoria(SolicitudXmlSigpBean solicitudXmlSigpBean, Document document) {

        Element eConvocatoria = (Element) document.createElement(Constantes.XMLTAG_CONVOCATORIA);

        Element eTipoSolicitud = (Element) document.createElement(Constantes.XMLTAG_TIPO_SOLICITUD);
        eTipoSolicitud.appendChild(document.createTextNode(String.valueOf(solicitudXmlSigpBean.getTipoSolicitud())));
        
        
        Element eNumJustificante = (Element) document.createElement(Constantes.XMLTAG_NUM_JUSTIFICANTE);
        eNumJustificante.appendChild(document.createTextNode(solicitudXmlSigpBean.getNumJustificante()));
        
        Element eProvExamen = (Element) document.createElement(Constantes.XMLTAG_PROV_EXAMEN);
        eProvExamen.appendChild(document.createTextNode(solicitudXmlSigpBean.getProvExamen()));
        
        Element eCuerpoAsp = (Element) document.createElement(Constantes.XMLTAG_CUERPO_ASP);
        eCuerpoAsp.appendChild(document.createTextNode(solicitudXmlSigpBean.getCuerpoAsp()));
        
        Element eCategAsp = (Element) document.createElement(Constantes.XMLTAG_CATEG_ASP);
        eCategAsp.appendChild(document.createTextNode(solicitudXmlSigpBean.getCategAsp()));
        
        Element eGrupProfAsp = (Element) document.createElement(Constantes.XMLTAG_GRUP_PROF_ASP);
        eGrupProfAsp.appendChild(document.createTextNode(solicitudXmlSigpBean.getGrupProfAsp()));
        
        Element eDatoA = (Element) document.createElement(Constantes.XMLTAG_DATO_A);
        eDatoA.appendChild(document.createTextNode(solicitudXmlSigpBean.getDatoA()));
        
        Element eDatoB = (Element) document.createElement(Constantes.XMLTAG_DATO_B);
        eDatoB.appendChild(document.createTextNode(solicitudXmlSigpBean.getDatoB()));
        
        Element eDatoC = (Element) document.createElement(Constantes.XMLTAG_DATO_C);
        eDatoC.appendChild(document.createTextNode(solicitudXmlSigpBean.getDatoC()));
        
        Element eTasas = (Element) document.createElement(Constantes.XMLTAG_TASAS);
        if(solicitudXmlSigpBean.getTasas()!=null){
        	eTasas.appendChild(document.createTextNode(solicitudXmlSigpBean.getTasas().toString()));
        }else{
        	eTasas.appendChild(document.createTextNode(""));
        }
        
        
        Element eReducTasas = (Element) document.createElement(Constantes.XMLTAG_REDUC_TASAS);
        Element eIdCausaRed=(Element) document.createElement(Constantes.XMLTAG_ID_CAUSA_RED);
        eIdCausaRed.appendChild(document.createTextNode(solicitudXmlSigpBean.getReducTasas()));
        eReducTasas.appendChild(eIdCausaRed);
        
        //Añadimos todos los elementos al elemento principal Empleado
        eConvocatoria.appendChild(eTipoSolicitud);
        eConvocatoria.appendChild(eNumJustificante);
        eConvocatoria.appendChild(eProvExamen);
        eConvocatoria.appendChild(eCuerpoAsp);
        eConvocatoria.appendChild(eCategAsp);
        eConvocatoria.appendChild(eGrupProfAsp);
        eConvocatoria.appendChild(eDatoA);
        eConvocatoria.appendChild(eDatoB);
        eConvocatoria.appendChild(eDatoC);
        eConvocatoria.appendChild(eTasas);
        eConvocatoria.appendChild(eReducTasas);
        return eConvocatoria;
    }
	
	/**
	 * Crear nodo datos aspirante.
	 *
	 * @param solicitudXmlSigpBean el solicitud xml sigp bean
	 * @param document el document
	 * @return el element
	 */
	private Element crearNodoDatosAspirante(SolicitudXmlSigpBean solicitudXmlSigpBean, Document document) {
        Element eDatosAspirante = (Element) document.createElement(Constantes.XMLTAG_DATOS_ASPIRANTE);

        
        Element eDiscapacitado = (Element) document.createElement(Constantes.XMLTAG_DISCAPACITADO);
        eDiscapacitado.appendChild(document.createTextNode(solicitudXmlSigpBean.getDiscapacitado()));
        
        Element eGradoDisc = (Element) document.createElement(Constantes.XMLTAG_GRADO_DISC);
        if(solicitudXmlSigpBean.getGradoDisc()!=null){
        	eGradoDisc.appendChild(document.createTextNode(solicitudXmlSigpBean.getGradoDisc().toString()));
        }else{
        	eGradoDisc.appendChild(document.createTextNode(null));
        }
        
        Element eAdaptacionDiscap = (Element) document.createElement(Constantes.XMLTAG_ADAPTACION_DISCAP);
        eAdaptacionDiscap.appendChild(document.createTextNode(solicitudXmlSigpBean.getAdaptacionDisc()));
        
        
        Element eReserDisc = (Element) document.createElement(Constantes.XMLTAG_RESER_DISC);
        eReserDisc.appendChild(document.createTextNode(String.valueOf(solicitudXmlSigpBean.getReserDisc())));
        
        Element eDiscapIntel = (Element) document.createElement(Constantes.XMLTAG_DISCAP_INTEL);
        eDiscapIntel.appendChild(document.createTextNode(String.valueOf(solicitudXmlSigpBean.getDiscapIntel())));
        
        Element eTituloExigido = (Element) document.createElement(Constantes.XMLTAG_TITULO_EXIGIDO);
        eTituloExigido.appendChild(document.createTextNode(solicitudXmlSigpBean.getTituloExigido()));
       
        
        Element eTitulosAcademicos = (Element) document.createElement(Constantes.XMLTAG_TITULOS_ACADEMICOS);
        eTitulosAcademicos.appendChild(document.createTextNode(solicitudXmlSigpBean.getTitulosAcademicos()));
        
     
        //Añadimos todos los elementos al elemento principal Empleado
        eDatosAspirante.appendChild(eDiscapacitado);
        eDatosAspirante.appendChild(eGradoDisc);
        eDatosAspirante.appendChild(eAdaptacionDiscap);
        eDatosAspirante.appendChild(eReserDisc);
        eDatosAspirante.appendChild(eDiscapIntel);
        eDatosAspirante.appendChild(eTituloExigido);
        eDatosAspirante.appendChild(eTitulosAcademicos);
        return eDatosAspirante;
    }
	
	/**
	 * Generar fichero.
	 *
	 * @param aXml el a xml
	 * @return el string
	 */
	public String generarFichero(ArrayList<Document> aXml){
		 
	        Iterator<Document> itAxml=aXml.iterator();
	        Integer numFichero=0;

	        Long temporalDir = System.currentTimeMillis();
	        String sRuta = properties.getProperty(STRING_GESTDOCUMENTAL_RUTADOCS_TEMP);
	        Util.verificarDirectorio(sRuta);
	        sRuta += temporalDir;
	        String sFileIn = "";
      
	    	File directorio = new File(sRuta);
	    	directorio.mkdir();
	       
	    	while (itAxml.hasNext()){
	        	numFichero++;
		        // Fichero destino 
	        	
	        	Long temporal = System.currentTimeMillis();
	        	while(existeFichero(sRuta,temporal+"."+Constantes.XML_EXTENSION)){
	        		temporal++;
	        	}
		        Document documento=itAxml.next();
		        sFileIn = sRuta + "/" + temporal+"."+Constantes.XML_EXTENSION;
		        OutputFormat formato  = new OutputFormat(documento, ENCODING_VALUE, true);
		        StringWriter s = new StringWriter();
		        XMLSerializer ser = new XMLSerializer(s, formato);
		        try {
					ser.serialize(documento);
					 FileWriter f = new FileWriter(sFileIn);
				        ser = new XMLSerializer(f, formato);
				        ser.serialize(documento);
				        f.flush();
				        f.close();
				} catch (IOException e1) {
					logger.error("Error ExportarSigpSolicitudesSpring - generarFichero:",e1);
				}

	        }
	        //Creamos el zip
	        String rutaZip = properties.getProperty(STRING_GESTDOCUMENTAL_RUTADOCS_TEMP);
		    if  (numFichero>0){
		    	
				rutaZip = rutaZip + temporalDir.toString()+".zip";
		    	File zip = new File(sRuta); 
		    	Zipeador zipeador = new Zipeador(zip,rutaZip);
		    	try {
					zipeador.comprimir();
					//Borramos la carpeta y dejamos el zip
					UtilesIPSG.borraCarpeta(sRuta);
				} catch (Exception e) {
					logger.error("Error ExportarSigpSolicitudesSpring - generarFichero:",e);
				}
		    }
		return temporalDir.toString();
       
	}
	



	/**
	 * Existe fichero.
	 *
	 * @param path el path
	 * @param fich el fich
	 * @return verdadero, si tiene exito
	 */
	public boolean  existeFichero(String path,String fich){
		File f1 = new File(path);
		File[] files = f1.listFiles();
		for(int i=0;i<files.length;i++){
			if(files[i].getName().equals(fich)){
				return true;
			}
		}
		return false;
		} 	
	
	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}
	
/**
 * Crear query solicitud registrada.
 *
 * @param formulario el formulario
 * @return el sol comun registradas view query
 */
private SolComunRegistradasViewQuery crearQuerySolicitudRegistrada (BuscarSolicitudesForm formulario)	{
		
	SolComunRegistradasViewQuery solicitudQuery = new SolComunRegistradasViewQuery();
		
		String sNifFormulario = formulario.getNif();
		String sNumSolicitud = formulario.getNumSolicitud();
		Integer iIdMinisterio = formulario.getIdMinisterio();
		String sIdCentroGestor = formulario.getIdCentroGestor();
		String sFechaDesde = formulario.getFechaDesde();
		String sFechaHasta = formulario.getFechaHasta();
		String sIdCuerpoEscala = formulario.getCuerpoEscala();
		Integer iIdTipo = formulario.getIdTipo();
		String sIdEspecialidad = formulario.getIdEspecialidad();
		
		String campo = formulario.getCampo();
		String direccion = formulario.getDireccion();

		
		//Comprobar los filtros para realizar la busqueda
		
		//Nif
		if (sNifFormulario != null && !sNifFormulario.equals("") ){
			solicitudQuery.setNif(sNifFormulario);
		}
		//Número de solicitud
		if (sNumSolicitud != null && !sNumSolicitud.equals("")){
			solicitudQuery.setNumeroSolicitudComparator(Comparator.LIKE_LEFT_RIGHT);
			solicitudQuery.setNumeroSolicitud(sNumSolicitud);
		}
		//Ministerio
		if (iIdMinisterio != null && iIdMinisterio.intValue() != 0){
			solicitudQuery.setIdMinisterio(iIdMinisterio.shortValue());
		}
		
		//Centro Gestor
		if (sIdCentroGestor != null && !sIdCentroGestor.equals(""))
		{
			solicitudQuery.setIdCentroGestor(Integer.valueOf(sIdCentroGestor));
		}
		//Cuerpo y Escala
		if (sIdCuerpoEscala != null && !sIdCuerpoEscala.equals(""))
		{
			solicitudQuery.setIdCuerpoEscala(Integer.valueOf(sIdCuerpoEscala));
		}
		
		//Especialidad
				if (sIdEspecialidad != null && !sIdEspecialidad.equals(""))
				{
					solicitudQuery.setIdEspecialidad(Short.valueOf(sIdEspecialidad));
				}
		
		//Tipo Solicitud HASTA AQUI!!
		if(iIdTipo != null && iIdTipo.intValue() != 0)
		{
			solicitudQuery.setIdTipoSolicitud(iIdTipo);
			
		}
		//Fecha Desde
		if (sFechaDesde != null && !sFechaDesde.equals("")){
			try
			{
				//Fecha Desde
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
				Date dFechaDesde=formatoDelTexto.parse(formulario.getFechaDesde());
				solicitudQuery.setFechaRegistroMin(dFechaDesde);
				
			} catch (java.text.ParseException e) {
				logger.error("Error ExportarSigpSolicitudesSpring -  crearQuerySolicitudRegistrada - parsear fechaDesde:"+sFechaDesde,e);
			}
		
		}
		//Fecha Hasta
		if (sFechaHasta != null && !sFechaHasta.equals("")){
			try
			{
				SimpleDateFormat formatoDelTexto = new SimpleDateFormat(STRING_SIMPLEDATEFORMAT);
				Date dFechaHasta=formatoDelTexto.parse(formulario.getFechaHasta());
				solicitudQuery.setFechaRegistroMax(dFechaHasta);
				
			} catch (java.text.ParseException e) {
				logger.error("Error ExportarSigpSolicitudesSpring -  crearQuerySolicitudRegistrada - parsear fechaHasta:"+sFechaHasta,e);
			}
		}
		//Ordenación
		if(campo != null && !"0".equals(campo) && !"".equals(campo)){
			// Obtenemos si vamos a ordenar ascendentemente o descendentemente
			OrderType orden = ("up".equals(direccion) == true ? OrderType.ASC : OrderType.DESC);
			if (SolComunRegistradasViewQuery.EJERCICIOCONVOCATORIA.equals(campo)) {
				solicitudQuery.addOrder(SolComunRegistradasViewQuery.EJERCICIOCONVOCATORIA, orden);
			}
			else if (SolComunRegistradasViewQuery.DESCENTROGESTOR.equals(campo)) {
				solicitudQuery.addOrder(SolComunRegistradasViewQuery.DESCENTROGESTOR, orden);
			}
			else {
				solicitudQuery.addOrder(campo, orden);
			}
		}
		return solicitudQuery;
	}

	/**
	 * Obtiene el convocatoria manager.
	 *
	 * @return convocatoriaManager ConvocatoriasManager
	 */
	public ConvocatoriasManager getConvocatoriaManager() {
		return convocatoriaManager;
	}

	/**
	 * Establece el convocatoria manager.
	 *
	 * @param convocatoriaManager ConvocatoriasManager
	 */
	public void setConvocatoriaManager(ConvocatoriasManager convocatoriaManager) {
		this.convocatoriaManager = convocatoriaManager;
	}

	/**
	 * Obtiene el solicitudes registradas manager.
	 *
	 * @return solicitudesRegistradasManager SolicitudesRegistradasManager
	 */
	public SolicitudesRegistradasManager getSolicitudesRegistradasManager() {
		return solicitudesRegistradasManager;
	}

	/**
	 * Establece el solicitudes registradas manager.
	 *
	 * @param solicitudesRegistradasManager SolicitudesRegistradasManager
	 */
	public void setSolicitudesRegistradasManager(
			SolicitudesRegistradasManager solicitudesRegistradasManager) {
		this.solicitudesRegistradasManager = solicitudesRegistradasManager;
	}

	/**
	 * Establece el parametro configuracion manager.
	 *
	 * @param parametroConfiguracionManager el nuevo parametro configuracion manager
	 */
	private void setParametroConfiguracionManager(ParametroConfiguracionManager parametroConfiguracionManager) {
		this.parametroConfiguracionManager = parametroConfiguracionManager;
	}

	/**
	 * Obtiene el usuario manager.
	 *
	 * @return the usuarioManager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager the usuarioManager to set
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}

	/**
	 * Obtiene el correo electronico manager.
	 *
	 * @return the correoElectronicoManager
	 */
	public CorreoElectronicoManager getCorreoElectronicoManager() {
		return correoElectronicoManager;
	}

	/**
	 * Establece el correo electronico manager.
	 *
	 * @param correoElectronicoManager the correoElectronicoManager to set
	 */
	public void setCorreoElectronicoManager(
			CorreoElectronicoManager correoElectronicoManager) {
		this.correoElectronicoManager = correoElectronicoManager;
	}



	/**
	 * Obtiene el parametro configuracion manager.
	 *
	 * @return the parametroConfiguracionManager
	 */
	public ParametroConfiguracionManager getParametroConfiguracionManager() {
		return parametroConfiguracionManager;
	}

	
}
