package es.map.ipsc.spring.modelo790;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.spring.Globals;
import org.springframework.util.StringUtils;

import com.itextpdf.text.pdf.PdfCopyFields;
import com.itextpdf.text.pdf.PdfReader;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMessage;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.ParametrosConfiguracionQuery;
import es.map.ips.model.SolicitudComunAuxiliarQuery;
import es.map.ips.model.SolicitudComunQuery;
import es.map.ipsc.Constantes;
import es.map.ipsc.bean.CamposPropiosBean;
import es.map.ipsc.bean.CentroGestorBean;
import es.map.ipsc.bean.ComunidadesBean;
import es.map.ipsc.bean.ConvocatoriaBean;
import es.map.ipsc.bean.CuerpoEscalaBean;
import es.map.ipsc.bean.DescargaModeloBean;
import es.map.ipsc.bean.DetalleSolicitudBean;
import es.map.ipsc.bean.EspecialidadBean;
import es.map.ipsc.bean.FormaAccesoBean;
import es.map.ipsc.bean.Formulario790Bean;
import es.map.ipsc.bean.MinisterioBean;
import es.map.ipsc.bean.PaisBean;
import es.map.ipsc.bean.ParametrosConfiguracionBean;
import es.map.ipsc.bean.ProvinciaBean;
import es.map.ipsc.bean.ProvinciaExamenBean;
import es.map.ipsc.bean.SolicitudBean;
import es.map.ipsc.bean.TipoDiscapacidadBean;
import es.map.ipsc.bean.TituloOficialBean;
import es.map.ipsc.form.Formulario790Form;
import es.map.ipsc.manager.descargaDocumento.DescargaDocumentoManager;
import es.map.ipsc.manager.formulario790.Formulario790Manager;
import es.map.ipsc.manager.solicitudes.SolicitudCcaaAuxiliarManager;
import es.map.ipsc.manager.solicitudes.SolicitudComunAuxiliarManager;
import es.map.ipsc.manager.solicitudes.SolicitudManager;
import es.map.ipsc.manager.solicitudes.SolicitudPropioAuxiliarManager;
import es.map.ipsc.manager.tablaBase.ParametroConfiguracionManager;
import es.map.ipsc.res.ResourceLocator;
import es.map.ipsc.utils.Barcode;
import es.map.ipsc.utils.Jasper;
import es.map.ipsc.utils.Util;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * El Class GenerarFormulario790Spring.
 */
public class GenerarFormulario790Spring extends AbstractSpring {

	/** el descarga documento manager. */
	private DescargaDocumentoManager descargaDocumentoManager;
	
	/** el formulario 790 manager. */
	private Formulario790Manager formulario790Manager;
	
	/** el parametro configuracion manager. */
	private ParametroConfiguracionManager parametroConfiguracionManager;
	
	/** el solicitud manager. */
	private SolicitudManager solicitudManager;
	
	/** el solicitud propio auxiliar manager. */
	private SolicitudPropioAuxiliarManager solicitudPropioAuxiliarManager;
	
	/** el solicitud comun auxiliar manager. */
	private SolicitudComunAuxiliarManager solicitudComunAuxiliarManager;
	
	/** el solicitud ccaa auxiliar manager. */
	private SolicitudCcaaAuxiliarManager solicitudCcaaAuxiliarManager;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(GenerarFormulario790Spring.class);
	
	/** el properties. */
	private static Properties properties;
	
	/** La constante STRING_ERRORDESCRIPCION. */
	private static final String STRING_ERRORDESCRIPCION = "errorDescripcion";
	
	/** La constante STRING_GENERARFORMULARIO790END. */
	private static final String STRING_GENERARFORMULARIO790END = "GenerarFormulario790Spring - end";
	
	/** La constante STRING_NOSUCCESS. */
	private static final String STRING_NOSUCCESS = "nosuccess";
	
	/** La constante STRING_USER_NOSUCCESS. */
	private static final String STRING_USER_NOSUCCESS = "errorUsuario";
	
	/** La constante PATRON_CARAC_EXTRAÑOS. */
	private static final String PATRON_CARAC_EXTRAÑOS = "[-+.^:,?¿!¡*_]";
	
	/**
	 * Crea una nueva generar formulario 790 spring.
	 */
	public GenerarFormulario790Spring() {
		try {
			this.setFormulario790Manager((Formulario790Manager) getBean("formulario790Manager"));
			this.setDescargaDocumentoManager((DescargaDocumentoManager) getBean("descargaDocumentoManager"));
			this.setParametroConfiguracionManager((ParametroConfiguracionManager) getBean("parametroConfiguracionManager"));
			this.setSolicitudManager((SolicitudManager) getBean("solicitudesManager"));
			this.setSolicitudPropioAuxiliarManager((SolicitudPropioAuxiliarManager) getBean("solicitudPropioAuxiliarManager"));
			this.setSolicitudComunAuxiliarManager((SolicitudComunAuxiliarManager) getBean("solicitudComunAuxiliarManager"));
			this.setSolicitudCcaaAuxiliarManager((SolicitudCcaaAuxiliarManager) getBean("solicitudCcaaAuxiliarManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.warn(e);
			logger.error("Error generar Formulario 790",e);
		}
	}
	
	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.AbstractSpring#doExecute(es.map.ips.common.spring.SpringForm)
	 */
	@Override
	protected String doExecute(SpringForm form) throws Exception {

		logger.info("GenerarFormulario790Spring - start");
		
		//Obtenemos el idioma en session	
		Locale locale = (Locale) this.getRequest().getSession().getAttribute(Globals.LOCALE_KEY);
		String localeName = locale.toString();	
		logger.info("Locale: "+localeName);
		
		
		//Comprobar datos de la convocatoria para caso subsanar o inscripcion
		
		
		// Multi-idioma
		String MESSAGE_RESOUCE = "MessageResources";
		if(localeName.equals("ca")){
			MESSAGE_RESOUCE = "MessageResources_ca";
		}else if(localeName.equals("gl")){
			MESSAGE_RESOUCE = "MessageResources_gl";
		}else if(localeName.equals("vl")){
			MESSAGE_RESOUCE = "MessageResources_vl";
		}else if(localeName.equals("eu")){
			MESSAGE_RESOUCE = "MessageResources_eu";
		}
		
		ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
		
		
		HttpServletResponse response = null;
		HttpServletRequest request = null;
		Formulario790Form formulario = null;
		Formulario790Bean formulario790Bean = null;
		ArrayList<Formulario790Bean> aList = new ArrayList<Formulario790Bean>();
		File sourceFile = null;
		ServletOutputStream ouputStream = null;
		Jasper jasperCiudadano = new Jasper();
		Jasper jasperAdministracion = new Jasper();

		try {
			
			response = getResponse();
			request = getRequest();
			formulario = (Formulario790Form) form;
		
			//Quitar caracteres especiales
			quitarCaracteresEspeciales(formulario);
		
			//Marcamos el check de discpacidad en el pdf si tiene igual o mas del minimo 
			if(formulario.getPorcentajeDiscapacidad() != null
					&& ((Long.valueOf(formulario.getPorcentajeMinDiscapacidad()).longValue() <= formulario.getPorcentajeDiscapacidad()) && formulario.getMotivoRedEx() == null)){						
					formulario.setMotivoRedEx("1");
				
			}
			
			//Comprobar que se ha pasado la convocatoria
			if (StringUtils.isEmpty(formulario.getIdConvocatoria())) {
				logger.error("No se ha pasado la convocatoria");
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION, RESOURCE_MESSAGE.getString("solicitud.error.convocatoria"));	
				logger.info(STRING_GENERARFORMULARIO790END);
				return STRING_NOSUCCESS;
			} else {
				if (!comprobarSolicitudesConvocatoriaUsuario(formulario)) {
					logger.error("Usuario no valido");
					this.getRequest().setAttribute(STRING_ERRORDESCRIPCION, RESOURCE_MESSAGE.getString("solicitud.error.generarSolicitud.usuario"));	
					logger.info(STRING_GENERARFORMULARIO790END);
					return STRING_USER_NOSUCCESS;
				}
			}
			
			
			// Declaro todos los objetos que voy a utilizar en la generacion del pdf
			String rutaReporte = properties.getProperty("jasper.rutaReport");
			
			// Comprueba si el directorio existe, en caso contrario lo crea.
			Util.verificarDirectorio(rutaReporte);

			String imagenReporte = properties.getProperty("jasper.imagen");
			String imagerNumeroJustificante = properties.getProperty("jasper.imagenNumeroJustificante");
			String extension = properties.getProperty("jasper.extension");
			
			// Cargo el formulario Bean con los datos tratados que me llegan desde la jsp
			String nombreBarcodeAux = String.valueOf(System.currentTimeMillis());
			String nombreBarcode = imagenReporte + "" + nombreBarcodeAux + "" + extension;
			String nombreBarcodeNJustificante = imagerNumeroJustificante + "" + nombreBarcodeAux + "" + extension;
			
			formulario790Bean = cargarBean(formulario, localeName, MESSAGE_RESOUCE);
			
			Long id = solicitudComunAuxiliarManager.guardarSolicitudFormulario790(formulario790Bean);
			logger.info("Solicitud generada con id: "+id);
			
			if(null!=id && id!=0){
				logger.info("Guardando la comunidad autónoma de la solicitud");
				solicitudCcaaAuxiliarManager.guardarSolicitudCcaaAuxiliarFormulario790(formulario790Bean,id);
			}else{
				logger.error("La solicitud generada de comunidades autónomas no pudo ser registrada en BBDD");
			}
			
			if(null == formulario790Bean){
				return STRING_NOSUCCESS;
			}
			
			formulario790Bean.setRutaFicheros(rutaReporte);
			formulario790Bean.setNombreBarcode(nombreBarcode);
			formulario790Bean.setNombreBarcodeNJustificante(nombreBarcodeNJustificante);
			String rutaLogo = properties.getProperty("jasper.rutaLogo");
			formulario790Bean.setRutaLogo(rutaLogo);
			
			//CAMPOS PROPIOS
			// Recuperamos de sesión los literales de los campos específicos
			if(null!=getSessionAttribute("camposPropios")){

				ArrayList<CamposPropiosBean> camposPropios = (ArrayList<CamposPropiosBean>) getSessionAttribute("camposPropios");
				
				// Valores de los campos específicos del modelo

				
				for (CamposPropiosBean campo: camposPropios) {
					campo.setValorVista(this.getRequest().getParameter("plantilla"+campo.getId()));
				}
				
				// TODO BILINGUE - Recuperar idioma en caso de modelo 790007 bilingue
				if(formulario.getCodigoTasa()!=null && null!=locale && locale.getLanguage()!="es"){
					evaluaModJusticiaBilingue(locale, camposPropios, formulario.getCodigoTasa());
				}
				
				formulario790Bean.setListaCamposPropios(camposPropios);
				
				if(null!=id && id!=0){
					logger.info("Guardando campos propios de la solicitud");
					solicitudPropioAuxiliarManager.guardarSolicitudPropioAuxiliar(formulario790Bean.getListaCamposPropios(),id);
				}else{
					logger.error("La solicitud generada no pudo ser registrada en BBDD");
				}
				
				
				
			}else{
				logger.error("La sesion ha caducado");
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION, RESOURCE_MESSAGE.getString("solicitud.error.sesion"));	
				logger.info(STRING_GENERARFORMULARIO790END);
				return STRING_NOSUCCESS;
			}
			
			String cadenaAux;
			
			// MINISTERIO
			if(formulario.getCodigoMinisterio() != null && !formulario.getCodigoMinisterio().equals("")){
				formulario790Bean.setCodigoMinisterio(formulario.getCodigoMinisterio());
				cadenaAux = this.completarCeros(formulario.getCodigoMinisterio(), 5);
				formulario790Bean.setCodigoMinisterio1(cadenaAux.charAt(0));
				formulario790Bean.setCodigoMinisterio2(cadenaAux.charAt(1));
				formulario790Bean.setCodigoMinisterio3(cadenaAux.charAt(2));
				formulario790Bean.setCodigoMinisterio4(cadenaAux.charAt(3));
				formulario790Bean.setCodigoMinisterio5(cadenaAux.charAt(4));
			}else{
				formulario790Bean.setCodigoMinisterio1(' ');
				formulario790Bean.setCodigoMinisterio2(' ');
				formulario790Bean.setCodigoMinisterio3(' ');
				formulario790Bean.setCodigoMinisterio4(' ');
				formulario790Bean.setCodigoMinisterio5(' ');
			}
			
			// CODIGO CUERPO ESCALA
			if(formulario.getCodigoCuerpoEscala() != null && !formulario.getCodigoCuerpoEscala().equals("")){
				formulario790Bean.setCodigoCuerpoEscala(formulario.getCodigoCuerpoEscala());
				cadenaAux = this.completarCeros(formulario.getCodigoCuerpoEscala(), 4);
				formulario790Bean.setCodigoCuerpoEscala1(cadenaAux.charAt(0));
				formulario790Bean.setCodigoCuerpoEscala2(cadenaAux.charAt(1));
				formulario790Bean.setCodigoCuerpoEscala3(cadenaAux.charAt(2));
				formulario790Bean.setCodigoCuerpoEscala4(cadenaAux.charAt(3));
			}else{
				formulario790Bean.setCodigoCuerpoEscala1(' ');
				formulario790Bean.setCodigoCuerpoEscala2(' ');
				formulario790Bean.setCodigoCuerpoEscala3(' ');
				formulario790Bean.setCodigoCuerpoEscala4(' ');
			}
			
			// ESPECIALIDAD
			if(formulario.getCodigoEspecialidad() != null && !formulario.getCodigoEspecialidad().equals("")){
				formulario790Bean.setCodigoEspecialidad(formulario.getCodigoEspecialidad());
				
				cadenaAux = this.completarCeros(formulario.getCodigoEspecialidad(), 4);
				formulario790Bean.setCodigoEspecialidad1(cadenaAux.charAt(0));
				formulario790Bean.setCodigoEspecialidad2(cadenaAux.charAt(1));
				formulario790Bean.setCodigoEspecialidad3(cadenaAux.charAt(2));
				formulario790Bean.setCodigoEspecialidad4(cadenaAux.charAt(3));
			}else{
				formulario790Bean.setCodigoEspecialidad1(' ');
				formulario790Bean.setCodigoEspecialidad2(' ');
				formulario790Bean.setCodigoEspecialidad3(' ');
				formulario790Bean.setCodigoEspecialidad4(' ');
			}
			
			// TITULO OFICIAL
			if(formulario.getCodigoTituloExigido() != null && !formulario.getCodigoTituloExigido().equals("")){
				formulario790Bean.setCodTitutoOficial(formulario.getCodigoTituloExigido());
				
				cadenaAux = this.completarCeros(formulario.getCodigoTituloExigido(), 5);
				formulario790Bean.setCodTitulo1(cadenaAux.charAt(0));
				formulario790Bean.setCodTitulo2(cadenaAux.charAt(1));
				formulario790Bean.setCodTitulo3(cadenaAux.charAt(2));
				formulario790Bean.setCodTitulo4(cadenaAux.charAt(3));
				formulario790Bean.setCodTitulo5(cadenaAux.charAt(4));
			}else{
				formulario790Bean.setCodTitulo1(' ');
				formulario790Bean.setCodTitulo2(' ');
				formulario790Bean.setCodTitulo3(' ');
				formulario790Bean.setCodTitulo4(' ');
				formulario790Bean.setCodTitulo5(' ');
			}
			
			// PAIS DOMICILIO
			if(formulario.getCodigoPaisDomicilio() != null && !formulario.getCodigoPaisDomicilio().equals("")){
				formulario790Bean.setCodigoPais(formulario.getCodigoPaisDomicilio());
				
				cadenaAux = this.completarCeros(formulario.getCodigoPaisDomicilio(), 3);
				formulario790Bean.setCodPais1(String.valueOf(cadenaAux.charAt(0)));
				formulario790Bean.setCodPais2(String.valueOf(cadenaAux.charAt(1)));
				formulario790Bean.setCodPais3(String.valueOf(cadenaAux.charAt(2)));
			}else{
				formulario790Bean.setCodPais1("");
				formulario790Bean.setCodPais2("");
				formulario790Bean.setCodPais3("");
			}
			
			// PROVINCIA DOMICILIO
			if(formulario.getCodigoProvinciaDomicilio() != null && !formulario.getCodigoProvinciaDomicilio().equals("")){
				
				cadenaAux = this.completarCeros(formulario.getCodigoProvinciaDomicilio(), 2);
				formulario790Bean.setCodProvinciaDomicilio1(String.valueOf(cadenaAux.charAt(0)));
				formulario790Bean.setCodProvinciaDomicilio2(String.valueOf(cadenaAux.charAt(1)));
			}else{
				formulario790Bean.setCodProvinciaDomicilio1(" ");
				formulario790Bean.setCodProvinciaDomicilio2(" ");
			}
			
			// PROVINCIA EXAMEN
			if(formulario.getCodigoProvinciaExamen() != null && !formulario.getCodigoProvinciaExamen().equals("")){
				
				cadenaAux = this.completarCeros(formulario.getCodigoProvinciaExamen(), 2);
				formulario790Bean.setCodProvinciaExamen1(String.valueOf(cadenaAux.charAt(0)));
				formulario790Bean.setCodProvinciaExamen2(String.valueOf(cadenaAux.charAt(1)));
			}else{
				formulario790Bean.setCodProvinciaExamen1(" ");
				formulario790Bean.setCodProvinciaExamen2(" ");
			}
			
			// NACIONALIDAD
			if(formulario.getCodigoNacionalidad() != null && !formulario.getCodigoNacionalidad().equals("")){
				formulario790Bean.setCodigoNacionalidad(formulario.getCodigoNacionalidad());
				
				cadenaAux = this.completarCeros(formulario.getCodigoNacionalidad(), 2);
				formulario790Bean.setCodigoNacionalidad1(String.valueOf(cadenaAux.charAt(0)));
				formulario790Bean.setCodigoNacionalidad2(String.valueOf(cadenaAux.charAt(1)));
			}else{
				formulario790Bean.setCodigoNacionalidad1("");
				formulario790Bean.setCodigoNacionalidad2("");
			}
			
			// MUNICIPIO
			if(formulario.getCodigoMunicipio() != null && !formulario.getCodigoMunicipio().equals("")){
				formulario790Bean.setCodigoMunicipioDomicilio(formulario.getCodigoMunicipio());
				
				cadenaAux = this.completarCeros(formulario.getCodigoMunicipio(), 3);
				formulario790Bean.setCodigoMunicipioDomicilio1(String.valueOf(cadenaAux.charAt(0)));
				formulario790Bean.setCodigoMunicipioDomicilio2(String.valueOf(cadenaAux.charAt(1)));
				formulario790Bean.setCodigoMunicipioDomicilio3(String.valueOf(cadenaAux.charAt(2)));
			}else{
				formulario790Bean.setCodigoMunicipioDomicilio1("");
				formulario790Bean.setCodigoMunicipioDomicilio2("");
				formulario790Bean.setCodigoMunicipioDomicilio3("");
			}
			
			// OTROS TITULOS
			if(formulario.getCodigoTituloOtros() != null && !formulario.getCodigoTituloOtros().equals("")){
				formulario790Bean.setCodigoOtrosTitulos(formulario.getCodigoTituloOtros());
				
				cadenaAux = this.completarCeros(formulario.getCodigoTituloOtros(), 5);
				formulario790Bean.setCodigoOtrosTitulos1(String.valueOf(cadenaAux.charAt(0)));
				formulario790Bean.setCodigoOtrosTitulos2(String.valueOf(cadenaAux.charAt(1)));
				formulario790Bean.setCodigoOtrosTitulos3(String.valueOf(cadenaAux.charAt(2)));
				formulario790Bean.setCodigoOtrosTitulos4(String.valueOf(cadenaAux.charAt(3)));
				formulario790Bean.setCodigoOtrosTitulos5(String.valueOf(cadenaAux.charAt(4)));
			}else{
				formulario790Bean.setCodigoOtrosTitulos1("");
				formulario790Bean.setCodigoOtrosTitulos2("");
				formulario790Bean.setCodigoOtrosTitulos3("");
				formulario790Bean.setCodigoOtrosTitulos4("");
				formulario790Bean.setCodigoOtrosTitulos5("");
			}
									
					
			// Compongo la cadena que se va a codificar y genero el barcode
			String datosCodificar = componerDatosCodigo(formulario790Bean,request);
			
			logger.debug("Generando Codigo de Puntos: " + rutaReporte + "/"	+ imagenReporte);
			StringBuffer ruta = new StringBuffer(); 
			ruta.append(rutaReporte).append("/").append(nombreBarcode);
			StringBuffer rutaImagenNumeroJustificante = new StringBuffer();
			rutaImagenNumeroJustificante.append(rutaReporte).append("/").append(nombreBarcodeNJustificante);
			
			try	{
				Barcode.generarBarcodeDatosSolicitud(ruta.toString(),datosCodificar);
				Barcode.generarBarcodeNumeroJustificante(rutaImagenNumeroJustificante.toString(), formulario790Bean.getNumeroJustificante());		
			}catch (Exception e) {
				logger.error("El texto del formulario es muy largo",e);
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION, RESOURCE_MESSAGE.getString("field.formulario790.errorLongitud"));	
				logger.info(STRING_GENERARFORMULARIO790END);
				return STRING_NOSUCCESS;
			}
			

			
			// TODO 790007 Bilingue
			// Viniendo de impresora formulario.getCodigoTasa()
			boolean isModJusticia = false;
			String idMJUST = properties.getProperty("jasper.justicia.id");
			String[] idMJUSTAux = idMJUST.split(";"); 
			List<String> listaMinisterio = Arrays.asList(idMJUSTAux);
			
			if(null!=formulario.getIdMinisterio()){
				for(int i=0; i<listaMinisterio.size(); i++){
					if (formulario.getIdMinisterio().equals(listaMinisterio.get(i))){
						isModJusticia = true;
						break;
					}else {
						isModJusticia = false;
					}
				}				
			}
			
			// Logo específico para Justicia
			if(isModJusticia){
				rutaLogo = properties.getProperty("jasper.rutaLogo.justicia");
				formulario790Bean.setRutaLogo(rutaLogo);
			}
			
			// Preparo el Bean para pasarlo al generador de formularios del report.
			logger.info("Centro Gestor:"+ formulario790Bean.getCentroGestor());

			
			aList.add(formulario790Bean);

			JRBeanCollectionDataSource jrBeanCollectionDataSource = null;
			jrBeanCollectionDataSource = new JRBeanCollectionDataSource((Collection<Formulario790Bean>) aList);
			
			// Hoja Ciudadano
			String ficheroReporteC = "";
			// Hoja Administración
			String ficheroReporteA = "";
			// Hoja Entidad
			String ficheroReporteE = "";
			// Hoja Instrucciones
			//String ficheroReporteI = "";
			
			if (localeName.equals("ca")) {
					ficheroReporteC = properties.getProperty("jasper.modelo790CCAT");
					ficheroReporteA = properties.getProperty("jasper.modelo790ACAT");
					ficheroReporteE = properties.getProperty("jasper.modelo790ECAT");							
			} else if (localeName.equals("gl")) {
					ficheroReporteC = properties.getProperty("jasper.modelo790CGAL");
					ficheroReporteA = properties.getProperty("jasper.modelo790AGAL");
					ficheroReporteE = properties.getProperty("jasper.modelo790EGAL");
			} else if (localeName.equals("eu")) {
					ficheroReporteC = properties.getProperty("jasper.modelo790CEU");
					ficheroReporteA = properties.getProperty("jasper.modelo790AEU");
					ficheroReporteE = properties.getProperty("jasper.modelo790EEU");
			} else if (localeName.equals("vl")) {				
					ficheroReporteC = properties.getProperty("jasper.modelo790CVL");
					ficheroReporteA = properties.getProperty("jasper.modelo790AVL");
					ficheroReporteE = properties.getProperty("jasper.modelo790EVL");
			} else{				
					ficheroReporteC = properties.getProperty("jasper.modelo790C");
					ficheroReporteA = properties.getProperty("jasper.modelo790A");
					ficheroReporteE = properties.getProperty("jasper.modelo790E");
			}
			
			// Obtengo los ficheros implicados en la generacion del pdf
			URL url = ResourceLocator.getInstance().getJasperTemplate(ficheroReporteC);			
			String rutaFicheroJasper="";
			if(url!=null){
				rutaFicheroJasper = url.getFile();
			}
	
			sourceFile = new File(rutaFicheroJasper);
			byte[] pdfasbytes = null;			
			pdfasbytes = jasperCiudadano.generarPDF(jrBeanCollectionDataSource,	sourceFile, "");

			// Genero el segundo pdf

			// Recargo la coleccion de datos que se le pasa al informe
			jrBeanCollectionDataSource = new JRBeanCollectionDataSource((Collection<Formulario790Bean>) aList);
			// Obtengo los ficheros implicados en la generacion del pdf
			url = ResourceLocator.getInstance().getJasperTemplate(ficheroReporteA);
			rutaFicheroJasper="";
			if(url!=null){
				rutaFicheroJasper = url.getFile();
			}
	
			sourceFile = new File(rutaFicheroJasper);

			// Cargo el informe compilado en el objeto jasperreport
			byte[] pdfasbytes2 = null;
			pdfasbytes2 = jasperAdministracion.generarPDF(jrBeanCollectionDataSource, sourceFile, "");

			// Genero el tercer pdf
			// Recargo la coleccion de datos que se le pasa al informe
			jrBeanCollectionDataSource = new JRBeanCollectionDataSource((Collection<Formulario790Bean>) aList);
			// Obtengo los ficheros implicados en la generacion del pdf
			
			url = ResourceLocator.getInstance().getJasperTemplate(ficheroReporteE);			
			rutaFicheroJasper="";
			if(url!=null){
				rutaFicheroJasper = url.getFile();
			}
	
			sourceFile = new File(rutaFicheroJasper);

			// Cargo el informe compilado en el objeto jasperreport
			byte[] pdfasbytes3 = null;
			pdfasbytes3 = jasperAdministracion.generarPDF(jrBeanCollectionDataSource, sourceFile, "");

			ByteArrayOutputStream pdfCompuesto = new ByteArrayOutputStream();
			PdfReader reader1 = new PdfReader(pdfasbytes);
			PdfReader reader2 = new PdfReader(pdfasbytes2);
			PdfReader reader3 = new PdfReader(pdfasbytes3);
			PdfCopyFields copy = new PdfCopyFields(pdfCompuesto);
			copy.addDocument(reader2);
			copy.addDocument(reader1);
			copy.addDocument(reader3);
			copy.close();

			byte[] pdfSalida = pdfCompuesto.toByteArray();
			response.setContentType("application/pdf");

			// Asigno el nombre del pdf
			response.setHeader("Content-Disposition",("attachment;filename=\"modelo790.pdf\""));
			int longitud = pdfSalida.length;

			// especifico el tamaño
			response.setContentLength(longitud);

			// Volcando a fichero
			ouputStream = response.getOutputStream();
			ouputStream.write(pdfSalida);
			ouputStream.flush();
			ouputStream.close();
			
			// Borramos el png
			 File fPng=new File(ruta.toString());
			 fPng.delete();
			 
			 File fPng2=new File(rutaImagenNumeroJustificante.toString());
			 fPng2.delete();

		} catch (Exception e) {
			logger.error("Error generar el objeto para el Jasper Report",e);
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION, RESOURCE_MESSAGE.getString("field.formulario790.error"));	
			logger.info(STRING_GENERARFORMULARIO790END);
			return STRING_NOSUCCESS;
		}
		try {
			DescargaModeloBean descargaModeloBean = new DescargaModeloBean();
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			Date fecha = new Date();
			String fechaString = formatoFecha.format(fecha);
			descargaModeloBean.setFecha(fechaString);
			if (formulario790Bean.getIdConvocatoria() != null) {
				descargaModeloBean.setIdConvocatoria(String
						.valueOf(formulario790Bean.getIdConvocatoria()));
			} else {
				descargaModeloBean.setIdConvocatoria(null);
			}
			descargaDocumentoManager.insertarDescargaModelo(descargaModeloBean);
		} catch (Exception e) {
			logger.error("Error al guardar los datos en la tabla DescargaModelo790",e);
			this.getRequest().setAttribute(STRING_ERRORDESCRIPCION, RESOURCE_MESSAGE.getString("field.formulario790.error"));	
			logger.info(STRING_GENERARFORMULARIO790END);
			return STRING_NOSUCCESS;
		}
		logger.info(STRING_GENERARFORMULARIO790END);
		return "success";
	}

	/**
	 * Se comprueba que el usuario dispone de una solicitud en las tablas comunes y comunes_aux
	 * para la convocatoria seleccionada.
	 *
	 * @param formulario el formulario
	 * @return verdadero, si tiene exito
	 */
	private boolean comprobarSolicitudesConvocatoriaUsuario(Formulario790Form formulario) {
		ConvocatoriaBean convocatoriaBean;
		// Obtengo la convocatoria seleccionada
		convocatoriaBean = formulario790Manager.getConvocatoriaPorId(formulario.getIdConvocatoria());
		boolean existeUsuario = false;
		//Logica para la descarga de la subsanacion
		if (convocatoriaBean != null && convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_SUBSANADA) && formulario.isSubsanar()) {
			//subsanar convocatoria
			ConvocatoriaQuery convocatoriaQuery = new ConvocatoriaQuery();
			SolicitudComunQuery solicitudComunQuery = new SolicitudComunQuery();
			convocatoriaQuery.setId(convocatoriaBean.getId());
			solicitudComunQuery.setConvocatoria(convocatoriaQuery);
			solicitudComunQuery.setNif(formulario.getNif());
			SolicitudBean solicitud = solicitudManager.buscarSolicitudById(solicitudComunQuery);
				
			
			//Comprobamos si existe usuario en la tabla de solicitudes comunes
			if (solicitud != null && !StringUtils.isEmpty(solicitud.getNombre()) 
					&& !StringUtils.isEmpty(solicitud.getPrimerApellido()) 
					&& solicitud.getNombre().equals(formulario.getNombre()) 
					&& solicitud.getPrimerApellido().equals(formulario.getPrimerApellido())) {
				existeUsuario = true;
			} else {
				//Comprobamos si existe usuario en la tabla de solicitudes comunes auxiliares
				SolicitudComunAuxiliarQuery solicitudComunAuxiliarQuery = new SolicitudComunAuxiliarQuery();
				solicitudComunAuxiliarQuery.addConvocatoriaIn(convocatoriaBean.getId());
				solicitudComunAuxiliarQuery.setNif(formulario.getNif());
				DetalleSolicitudBean detalleSolicitudBean = solicitudComunAuxiliarManager.buscarSolicitudBean(solicitudComunAuxiliarQuery);
				
				if (detalleSolicitudBean != null && !StringUtils.isEmpty(detalleSolicitudBean.getNombre()) 
						&& !StringUtils.isEmpty(detalleSolicitudBean.getPrimerApellido()) 
						&& detalleSolicitudBean.getNombre().equals(formulario.getNombre()) 
						&& detalleSolicitudBean.getPrimerApellido().equals(formulario.getPrimerApellido())) {
					existeUsuario = true;
				}
			}
		//Logica para la descarga de la inscripcion	
		} else if (convocatoriaBean != null && !convocatoriaBean.getIdEstado().equals(Constantes.CONVOCATORIA_SUBSANADA) && !formulario.isSubsanar()) {
			//inscripcion de convocatoria no requiere de validacion
			existeUsuario = true;
		}
		return existeUsuario;
	}

	
	/**
	 * Método que construye los campos propios del modelo de justicia bilingue.
	 *
	 * @param locale el locale
	 * @param camposPropios el campos propios
	 * @param codigoTasa el codigo tasa
	 */
	private void evaluaModJusticiaBilingue(Locale locale, ArrayList<CamposPropiosBean> camposPropios, String codigoTasa) {
		
		for(int i=0; i<camposPropios.size(); i++){
			String campoPropio = camposPropios.get(i).getTituloCampo();
			if(locale.getLanguage().equals("ca")){	
				if(codigoTasa.equals(Constantes.CODIGO_TASA_JUSTICIA)){
					camposPropios.get(i).setCampo(campoPropio + " / " + camposPropios.get(i).getCampoCa());
				}else{
					camposPropios.get(i).setCampo(camposPropios.get(i).getCampoCa());
				}	
			}else if(locale.getLanguage().equals("gl")){
				if(codigoTasa.equals(Constantes.CODIGO_TASA_JUSTICIA)){
					camposPropios.get(i).setCampo(campoPropio + " / " + camposPropios.get(i).getCampoGl());
				}else{
					camposPropios.get(i).setCampo(camposPropios.get(i).getCampoGl());
				}
			}else if(locale.getLanguage().equals("vl")){
				if(codigoTasa.equals(Constantes.CODIGO_TASA_JUSTICIA)){
					camposPropios.get(i).setCampo(campoPropio + " / " + camposPropios.get(i).getCampoVl());
				}else{
					camposPropios.get(i).setCampo(camposPropios.get(i).getCampoVl());
				}
			}else if(locale.getLanguage().equals("eu")){
				if(codigoTasa.equals(Constantes.CODIGO_TASA_JUSTICIA)){
					camposPropios.get(i).setCampo(campoPropio + " / " + camposPropios.get(i).getCampoEu());
				}else{
					camposPropios.get(i).setCampo(camposPropios.get(i).getCampoEu());
				}
			}
		}
		
	}

	/**
	 * Procedimiento encargado de tratar los datos recogidos en el fomulario del
	 * modelo 790 para ser cargados en el pdf que se genera para el ciudadano.
	 *
	 * @param form el form
	 * @param locale el locale
	 * @param MESSAGE_RESOUCE el message resouce
	 * @return Formulario790Bean
	 */
	public Formulario790Bean cargarBean(Formulario790Form form, String locale, String MESSAGE_RESOUCE) {
		
		long idConvocatoria = form.getIdConvocatoria();
		Formulario790Bean formulario = new Formulario790Bean();
		String cadenaAux;

		// Evaluar si es modelo 790007, para rellenar campos configurados
		// en tabla PARAMETROS_CONFIGURACION con valor por defecto.
		boolean modJusticia = false;
		
		// TODO Corrección incidencia
		if(form.getCodigoTasa()!=null && form.getCodigoTasa().equals(Constantes.CODIGO_TASA_JUSTICIA)){
			modJusticia = true;
		}
		
		/* ParametrosConfiguracionBean paramBean = new ParametrosConfiguracionBean();
		if(form.getCodigoTasa() != null && modJusticia){
			ParametrosConfiguracionQuery paramQuery = new ParametrosConfiguracionQuery();
			paramQuery.setId(Constantes.PARAMETRO_CONFIGURACION_DEFAULT_TEXT_007);
			paramBean = parametroConfiguracionManager.buscarParametroConfiguracionUnico(paramQuery);
		}
		String valorDefectoJusticia = "";
		if(paramBean != null && paramBean.getValorCampo() != null){
			valorDefectoJusticia = paramBean.getValorCampo();
		} */
		
		// Ministerio
		// codigo de tasa
		if(form.getCodigoTasa() != null && !"".equals(form.getCodigoTasa())){
			formulario.setCodigoTasa(form.getCodigoTasa());
		}else{
			formulario.setCodigoTasa(" ");
		}

			// Nº de justificante
		
			// Solo se generara el numero cuando se haya pulsado el boton de 'Generar Solicitud'
			// del formulario y no con anterioridad. Asi se evita la reutilizacion del mismo
			
			boolean is007= false;
			if(form.getCodigoTasa().equals(Constantes.CODIGO_TASA_JUSTICIA)){
				is007 = true;
			}
			
			try {
				String numeroJustificante = solicitudManager.obtenerNumeroSolicitud(is007, form.getNumModelo());
				// Comprobamos si ya se ha generado un numero de justificante igual
				while (solicitudManager.existeNumeroSolicitud(numeroJustificante)) {
					numeroJustificante = solicitudManager.obtenerNumeroSolicitud(is007, form.getNumModelo());
				}
				formulario.setNumeroJustificante(numeroJustificante);
			} catch (Exception e) {
				logger.error("Error nuemro solicitud",e);
				this.getRequest().setAttribute(STRING_ERRORDESCRIPCION, ResourceBundle.getBundle(MESSAGE_RESOUCE).getString("field.numeroSolicitud.error"));
				return null;
			}
			
				

		// Año de la convocatoria
		if(form.getAnioConvocatoria() != null && !"".equals(form.getAnioConvocatoria())){
			cadenaAux = form.getAnioConvocatoria().toString();
		}else{
			cadenaAux = "    ";
		}
		formulario.setAnioConvocatoria1(cadenaAux.charAt(0));
		formulario.setAnioConvocatoria2(cadenaAux.charAt(1));
		formulario.setAnioConvocatoria3(cadenaAux.charAt(2));
		formulario.setAnioConvocatoria4(cadenaAux.charAt(3));

		// ministerio
		if (form.getMinisterio() != null && !"0".equals(form.getMinisterio())
				&& !"-1".equals(form.getMinisterio())) {
			formulario.setMinisterio(form.getMinisterio());
		} else {
			if(form.getMinisterioManual() != null && !"".equals(form.getMinisterioManual())){
				formulario.setMinisterio(form.getMinisterioManual());			
			}else{
				formulario.setMinisterio(" ");
			}
		}

		// ministerio convocatoria
		if(form.getMinisterioAutomatico() != null && !"".equals(form.getMinisterioAutomatico())){
			formulario.setMinisterioConvocatoria(form.getMinisterioAutomatico());
		}else{
			if(form.getDesMinisterioConvocante()!=null && !"".equals(form.getDesMinisterioConvocante())){
				formulario.setMinisterioConvocatoria(form.getDesMinisterioConvocante());
			}else{
				formulario.setMinisterioConvocatoria(" ");
			}
		}
		
		// centroGestor
		if (form.getCentroGestor() != null
				&& !"0".equals(form.getCentroGestor())
				&& !"-1".equals(form.getCentroGestor())) {
			formulario.setCentroGestor(form.getCentroGestor());
			formulario.setCodCentroGestor(form.getCodCentroGestor());
		} else {
			if(form.getCentroGestorManual() != null && !"".equals(form.getCentroGestorManual())){
				formulario.setCentroGestor(form.getCentroGestorManual());
			}else{
				formulario.setCentroGestor(" ");
			}
			formulario.setCodCentroGestor(" ");
		}
		
		// TODO Resolución incidencia
		boolean secretarioJud = false;
		if(this.getSession()!=null && this.getSession().getAttribute("secretarioJud")!=null){
			secretarioJud = (Boolean)this.getSession().getAttribute("secretarioJud");
		}
		
	
		// Petición Secretarios Judiciales para que vuelva a aparecer el cuerpo escala
		
		CuerpoEscalaBean cuerpoEscalaBean1 = formulario790Manager.getCuerpoEscalaPorId(new Long(form
				.getIdCuerpoEscala()));
		form.setDesCuerpoEscala(cuerpoEscalaBean1.getDescripcion());
		
		if(form.getDesCuerpoEscala() != null && !"".equals(form.getDesCuerpoEscala())){
			formulario.setCuerpoEscala(form.getDesCuerpoEscala());
		}else{
			formulario.setCuerpoEscala("");
		}

		// nif
		if(form.getNif() != null && !"".equals(form.getNif())){
			formulario.setNif(form.getNif());
		}else{
			formulario.setNif(" ");
		}

		// nombre
		if(form.getNombre() != null && !"".equals(form.getNombre())){
			formulario.setNombre(form.getNombre().toString().replaceAll(PATRON_CARAC_EXTRAÑOS,""));
		}else{
			formulario.setNombre(" ");
		}

		// primer apellido
		if(form.getPrimerApellido()!= null && !"".equals(form.getPrimerApellido())){
			formulario.setPrimerApellido(form.getPrimerApellido().toString().replaceAll(PATRON_CARAC_EXTRAÑOS,""));
		}else{
			formulario.setPrimerApellido(" ");
		}

		// segundo apellido
		if(form.getSegundoApellido() != null && !"".equals(form.getSegundoApellido())){
			formulario.setSegundoApellido(form.getSegundoApellido().toString().replaceAll(PATRON_CARAC_EXTRAÑOS,""));
		}else{
			formulario.setSegundoApellido(" ");
		}

		// fecha de nacimiento
		if(form.getDiaNacimiento()!=null && !"".equals(form.getDiaNacimiento())
				&& form.getAnioNacimiento()!=null && !"".equals(form.getAnioNacimiento())
				&& form.getMesNacimiento()!=null && !"".equals(form.getMesNacimiento())){
			StringBuffer sFechaNacimiento = new StringBuffer();
			sFechaNacimiento.append(form.getDiaNacimiento()).append("/").append(form.getMesNacimiento()).append("/").append(form.getAnioNacimiento());
			formulario.setFechaNacimiento(sFechaNacimiento.toString());
		}
		
		cadenaAux = completarBlancos(form.getDiaNacimiento(), 2);
		formulario.setDiaNacimiento1(cadenaAux.charAt(0));
		formulario.setDiaNacimiento2(cadenaAux.charAt(1));
		cadenaAux = completarBlancos(form.getMesNacimiento(), 2);
		formulario.setMesNacimiento1(cadenaAux.charAt(0));
		formulario.setMesNacimiento2(cadenaAux.charAt(1));
		cadenaAux = completarBlancos(form.getAnioNacimiento(), 4);
		formulario.setAnioNacimiento1(cadenaAux.charAt(2));
		formulario.setAnioNacimiento2(cadenaAux.charAt(3));

		// sexo
		if (form.getSexo() != null) {
			if (("V").compareTo(form.getSexo()) == 0) {
				formulario.setSexoHombre("X");
				formulario.setSexoMujer(" ");
			} else {
				formulario.setSexoHombre(" ");
				formulario.setSexoMujer("X");
			}
		} else {
			formulario.setSexoHombre(" ");
			formulario.setSexoMujer(" ");
		}
		// nacionalidad
		if(form.getNacionalidad() != null && !"".equals(form.getNacionalidad())){
			formulario.setNacionalidad(form.getNacionalidad().toString().replaceAll(PATRON_CARAC_EXTRAÑOS,""));
		}else{
			formulario.setNacionalidad(" ");
		}

		// correo electronico
		if(form.getCorreoElectronicos() != null && !"".equals(form.getCorreoElectronicos())){
			formulario.setCorreoElectronicos(form.getCorreoElectronicos());
		}else{
			formulario.setCorreoElectronicos(" ");
		}

		// telefono
		if(form.getTelefono() != null && !"".equals(form.getTelefono())){
			formulario.setTelefono(form.getTelefono());
		}else{
			formulario.setTelefono(" ");
		}
		if(form.getTelefonoAux() != null && !"".equals(form.getTelefonoAux())){
			formulario.setTelefonoAux(form.getTelefonoAux());
		}else{
			formulario.setTelefonoAux("");
		}

		// direccion del domicilio
		if(form.getCalleDomicilio() != null && !"".equals(form.getCalleDomicilio())){
			formulario.setCalleDomicilio(form.getCalleDomicilio().replace('\\', '/'));
		}else{
			formulario.setCalleDomicilio(" ");
		}

		// codigo postal del domicilio
		if(form.getCodigoPostalDomicilio() != null && !"".equals(form.getCodigoPostalDomicilio())){
			formulario.setCodigoPostalDomicilio(form.getCodigoPostalDomicilio());
		}else{
			formulario.setCodigoPostalDomicilio(" ");
		}

		// municipio del domicilio
		if(form.getMunicipioDomicilio() != null && !"".equals(form.getMunicipioDomicilio())){
			formulario.setMunicipioDomicilio(form.getMunicipioDomicilio().toString().replaceAll(PATRON_CARAC_EXTRAÑOS,""));
		}else{
			formulario.setMunicipioDomicilio(" ");
		}

		// provincia del domicilio
		if (null !=form.getProvinciaDomicilio() && form.getProvinciaDomicilio() > 0) {
			ProvinciaBean provinciaBean = formulario790Manager
					.getProvinciaPorId(form.getProvinciaDomicilio());
			formulario.setProvinciaDomicilio(provinciaBean.getDescripcion());
			formulario.setCodigoprovinciaDomicilio(provinciaBean.getCodigo());
			String codProvinciaDomicilio = completarCeros(provinciaBean.getCodigo(),2);
			formulario.setCodProvinciaDomicilio1(String.valueOf(codProvinciaDomicilio.charAt(0)));
			try {
				formulario.setCodProvinciaDomicilio2(String
						.valueOf(codProvinciaDomicilio.charAt(1)));
			} catch (Exception e) {
				logger.error("Error provincia domicilio",e);
				formulario.setCodProvinciaDomicilio2(" ");
			}
			formulario.setIdProvinciaDomicilio(form.getProvinciaDomicilio());	
		} else {
			formulario.setProvinciaDomicilio(" ");
			formulario.setCodigoprovinciaDomicilio(" ");
			formulario.setCodProvinciaDomicilio1(" ");
			formulario.setCodProvinciaDomicilio2(" ");
		}
		
		// pais del domicilio
		if (null != form.getPais() && form.getPais() > 0) {
			PaisBean paisBean = formulario790Manager.getPaisPorId(form
					.getPais());
			formulario.setPais(paisBean.getDescripcion());
			String aux = paisBean.getCodigo();
							
			formulario.setCodigoPais(aux);
			formulario.setCodPais1(String.valueOf(aux.charAt(0)));
			formulario.setCodPais2(String.valueOf(aux.charAt(1)));
			formulario.setCodPais3(String.valueOf(aux.charAt(2)));
			
		} else {
			formulario.setPais(" ");
			formulario.setCodigoPais(" ");
			formulario.setCodPais1(" ");
			formulario.setCodPais2(" ");
			formulario.setCodPais3(" ");
		}
		if(form.getPais() != null && !"".equals(form.getPais())){
			formulario.setIdPais(form.getPais());
		}else{
			formulario.setIdPais(0L);
		}

		
		// Petición Secretarios Judiciales para que vuelva a aparecer la especialidad
		if (form.getEspecialidad() != null && form.getEspecialidad() > 0) {
			EspecialidadBean especialidadBean = formulario790Manager
					.getEspecialidadPorId(form.getEspecialidad());
			cadenaAux = this.completarCeros(especialidadBean.getCodigo(), 4);
			formulario.setEspecialidad(especialidadBean.getDescripcion());

			formulario.setCodigoEspecialidad(especialidadBean.getCodigo());
		} else {
			if(form.getEspecialidadManual() != null && !"".equals(form.getEspecialidadManual())){
				formulario.setEspecialidad(form.getEspecialidadManual());
			}else{
				formulario.setEspecialidad(" ");
			}		
			cadenaAux = this.completarBlancos("", 4);
			formulario.setCodigoEspecialidad(" ");
		}
		formulario.setCodigoEspecialidad1(cadenaAux.charAt(0));
		formulario.setCodigoEspecialidad2(cadenaAux.charAt(1));
		formulario.setCodigoEspecialidad3(cadenaAux.charAt(2));
		formulario.setCodigoEspecialidad4(cadenaAux.charAt(3));
		formulario.setIdEspecialidad(form.getEspecialidad());

		// fecha del BOE
		if(form.getDiaFechaBoe()!=null && form.getAnioFechaBoe()!=null && form.getMesFechaBoe()!=null){
			StringBuffer sFechaBoe = new StringBuffer();
			sFechaBoe.append(form.getDiaFechaBoe()).append("/").append(form.getMesFechaBoe()).append("/").append(form.getAnioFechaBoe());
			formulario.setFechaBoe(sFechaBoe.toString());
		}
		
		cadenaAux = completarBlancos(form.getDiaFechaBoe(), 2);
		formulario.setDiaFechaBoe1(cadenaAux.charAt(0));
		formulario.setDiaFechaBoe2(cadenaAux.charAt(1));
		cadenaAux = completarBlancos(form.getMesFechaBoe(), 2);
		formulario.setMesFechaBoe1(cadenaAux.charAt(0));
		formulario.setMesFechaBoe2(cadenaAux.charAt(1));
		cadenaAux = completarBlancos(form.getAnioFechaBoe(), 4);
		formulario.setAnioFechaBoe1(cadenaAux.charAt(2));
		formulario.setAnioFechaBoe2(cadenaAux.charAt(3));


		// Petición Secretarios Judiciales para que vuelva a aparecer la Provincia de Examen.
		
		if (null!=form.getProvinciaExamen() && form.getProvinciaExamen() > 0) {
			ProvinciaExamenBean provinciaExamenBean = formulario790Manager
					.getProvinciaExamenPorId(form.getProvinciaExamen());
			formulario.setProvinciaExamen(provinciaExamenBean.getDescripcion());
			formulario.setCodigoProvinciaExamen(provinciaExamenBean.getCodigo());
			String auxProvinciaExamen = this.completarCeros(provinciaExamenBean.getCodigo(),2);
			formulario.setCodProvinciaExamen1(String.valueOf(auxProvinciaExamen.charAt(0)));
			formulario.setIdProvinciaExamen(form.getProvinciaExamen());
			try {
				formulario.setCodProvinciaExamen2(String.valueOf(auxProvinciaExamen.charAt(1)));
			} catch (Exception e) {
				logger.error("Error codigo provincia examen",e);
				formulario.setCodProvinciaExamen2(" ");
			}
		} else {
			formulario.setProvinciaExamen(" ");
			formulario.setCodigoProvinciaExamen(" ");
			formulario.setCodProvinciaExamen1(" ");
			formulario.setCodProvinciaExamen2(" ");
			formulario.setIdProvinciaExamen(form.getProvinciaExamen());
		}
		
		if (null!=form.getComunidadFN() && form.getComunidadFN() > 0) {
			ComunidadesBean comunidadesBean = formulario790Manager
					.getComunidadPorId(form.getComunidadFN());
			formulario.setCcaa(comunidadesBean.getDescripcion());	
			formulario.setIdCcaa(comunidadesBean.getIdComunidad().longValue());
			
		}else if (null!=form.getComunidadDD() && form.getComunidadDD() > 0) {
			ComunidadesBean comunidadesBean = formulario790Manager
					.getComunidadPorId(form.getComunidadDD());
			formulario.setCcaa(comunidadesBean.getDescripcion());
			formulario.setIdCcaa(comunidadesBean.getIdComunidad().longValue());
		}else{
			formulario.setCcaa(" ");
			
		}
		
		if(null!=form.getNumeroTituloFN() && !("").equals(form.getNumeroTituloFN())){
			formulario.setNumeroTituloFN(form.getNumeroTituloFN());
		}else{
			formulario.setNumeroTituloFN(" ");
		}
		
		if(null!=form.getMotivoOposicion() && !("").equals(form.getMotivoOposicion())){
			formulario.setMotivoOposicion(form.getMotivoOposicion());
		}else{
			formulario.setMotivoOposicion(" ");
		}
		
		if(form.isAutorizar()){
			formulario.setAutorizar("X");
		}else{
			formulario.setAutorizar("");
		}
		// porcentaje de discapacidad
		
		if (form.getPorcentajeDiscapacidad() == null){
			formulario.setPorcentajeDiscapacidad(" ");
		}else if (form.getPorcentajeDiscapacidad() != 0L) {
			formulario.setPorcentajeDiscapacidad(String.valueOf(form
					.getPorcentajeDiscapacidad()));
		} else {
			formulario.setPorcentajeDiscapacidad(" ");
		}
		// reserva de discapacidad
		if (form.getReservaDiscapacidad() != null) {
			formulario.setReservaDiscapacidad("X");
		} else {
			formulario.setReservaDiscapacidad(" ");
		}

		// destalle de discapacidad
		if (form.getDetalleDiscapacidad() != null && !form.getDetalleDiscapacidad().equals("")) {
			formulario.setDetalleDiscapacidad(form.getDetalleDiscapacidad());
		} else {
			formulario.setDetalleDiscapacidad(" ");
		}

		// tipo de discapacidad
		if (form.getTipoDiscapacidad() == null) {
			form.setTipoDiscapacidad(new Long(0));
		}

		if (form.getTipoDiscapacidad() > 0) {
			TipoDiscapacidadBean tipoDiscapacidadBean = formulario790Manager
					.getTipoDiscapacidadPorId(form.getTipoDiscapacidad());
			formulario.setTipoDiscapacidad(String.valueOf(tipoDiscapacidadBean
					.getCodigo()));
			String tipoAux = String.valueOf(tipoDiscapacidadBean.getId());
			formulario.setIdTipoDiscapacidad(Long.parseLong(tipoAux));
			
		} else {
			formulario.setTipoDiscapacidad(" ");
			formulario.setCodigoTipoDiscapacidad(' ');
			formulario.setIdTipoDiscapacidad(0L);
		}

		// titulo oficial requerido
		if (form.getTituloOficial() > 0) {
			TituloOficialBean tituloOficialBean = formulario790Manager
					.getTituloOficialPorId(form.getTituloOficial());
			formulario.setTituloOficial(tituloOficialBean.getDescripcion());
			String auxTitulo = String.valueOf(tituloOficialBean.getId());
			formulario.setIdTituloOficial(Long.valueOf(auxTitulo));
			if(tituloOficialBean.getCodigo() != null && !"".equals(tituloOficialBean.getCodigo())){
				formulario.setCodTitutoOficial(tituloOficialBean.getCodigo());
				String codTitulo = this.completarCeros(tituloOficialBean.getCodigo(), 5);
				formulario.setCodTitulo1(codTitulo.charAt(0));
				formulario.setCodTitulo2(codTitulo.charAt(1));
				formulario.setCodTitulo3(codTitulo.charAt(2));
				formulario.setCodTitulo4(codTitulo.charAt(3));
				formulario.setCodTitulo5(codTitulo.charAt(4));
			}else{			
				formulario.setCodTitutoOficial(" ");
				formulario.setCodTitulo1(' ');
				formulario.setCodTitulo2(' ');
				formulario.setCodTitulo3(' ');
				formulario.setCodTitulo4(' ');
				formulario.setCodTitulo5(' ');
			}
			
			
		} else {
			if(form.getTituloManual() != null && !"".equals(form.getTituloManual())){
				formulario.setTituloOficial(form.getTituloManual());
			}else{
				formulario.setTituloOficial(" ");
			}
			
			formulario.setIdTituloOficial(new Long(0));
			formulario.setCodTitutoOficial(" ");
			formulario.setCodTitulo1(' ');
			formulario.setCodTitulo2(' ');
			formulario.setCodTitulo3(' ');
			formulario.setCodTitulo4(' ');
			formulario.setCodTitulo5(' ');
		}

		// otros titulos
		formulario.setOtrosTitulos(form.getOtrosTitulos());

		// consentimiento divulgación datos
		if (form.getConsentimiento() != null) {
			if (form.getConsentimiento() == true) {
				formulario.setConsentimiento("X");
			} else {
				formulario.setConsentimiento("");
			}
		} else {
			formulario.setConsentimiento("");
		}
		
		//Motivo oposicion
		if (form.getMotivoOposicion() != null && !form.getMotivoOposicion().equals("")) {
			formulario.setMotivoOposicion(form.getMotivoOposicion());
		} else {
			formulario.setMotivoOposicion(" ");
		}
		
		// persona firmante
		formulario.setPersonaFirmante(form.getPersonaFirmante());

		// datos del lugar y la fecha de la firma
		StringBuffer declarante = new StringBuffer("");
		declarante.append("En ");
		if (form.getLugarFirma() != null && !"".equals(form.getLugarFirma()) && !" ".equals(form.getLugarFirma())) {
			declarante.append(form.getLugarFirma().toString().replaceAll(PATRON_CARAC_EXTRAÑOS,""));
		} else {
			declarante.append("                             ");
		}
		declarante.append(" ");
		declarante.append(form.getFechaFirma());
		formulario.setDeclarante(declarante.toString());

		// datos del importe
		// parte entera
		formulario.setImporteSolicitud(form.getImporteSolicitud());
		// parte decimal
		formulario.setImporteSolicitudDecimal(form.getImporteSolicitudDecimal());

		// forma de pago
		if (form.getFormaPago() != null) {
			if ((Constantes.FORMA_PAGO_EXENTO) == (form.getFormaPago()
					.charAt(0))) {
				formulario.setFormaPagoEfectivo("X");
				formulario.setFormaPagoAdeudo(" ");
				formulario.setFormaPagoNinguno(" ");
			} else if ((Constantes.FORMA_PAGO_ADEUDO) == (form.getFormaPago()
					.charAt(0))) {
				formulario.setFormaPagoEfectivo(" ");
				formulario.setFormaPagoAdeudo("X");
				formulario.setFormaPagoNinguno(" ");
			} else if ((Constantes.FORMA_PAGO_NINGUNO) == (form.getFormaPago()
					.charAt(0))) {
				formulario.setFormaPagoEfectivo(" ");
				formulario.setFormaPagoAdeudo(" ");
				formulario.setFormaPagoNinguno("X");				
			}
		} else {
			formulario.setFormaPagoEfectivo(" ");
			formulario.setFormaPagoAdeudo(" ");
			formulario.setFormaPagoNinguno(" ");
		}
		
		String mensajeFN = ResourceBundle.getBundle(MESSAGE_RESOUCE).getString("field.formulario790.fnumerosaGeneral");;
		formulario.setFnumerosa(mensajeFN);
		
		// Motivos exención/reducción
		if (form.getMotivoRedEx() != null) {
			if ((Constantes.MOTIVO_DISCAPACIDAD).equals(form.getMotivoRedEx())) {
				formulario.setDiscapacidad("X");
				formulario.setDemandanteEmpleo(" ");
				formulario.setFamiliaNumerosa(" ");
				formulario.setFamiliaNumerosaGeneral(" ");
				formulario.setFamiliaNumerosaEspecial(" ");
				formulario.setVictimaTerrorismo(" ");
			} else if ((Constantes.MOTIVO_DESEMPLEO).equals(form.getMotivoRedEx())) {
				formulario.setDiscapacidad(" ");
				formulario.setDemandanteEmpleo("X");
				formulario.setFamiliaNumerosa(" ");
				formulario.setFamiliaNumerosaGeneral(" ");
				formulario.setFamiliaNumerosaEspecial(" ");
				formulario.setVictimaTerrorismo(" ");
			} else if ((Constantes.MOTIVO_FNUMEROSA).equals(form.getMotivoRedEx())) {
				formulario.setDiscapacidad(" ");
				formulario.setDemandanteEmpleo(" ");
				formulario.setFamiliaNumerosa("X");
				formulario.setFamiliaNumerosaGeneral("X");
				formulario.setFamiliaNumerosaEspecial(" ");
				formulario.setVictimaTerrorismo(" ");	
			} else if ((Constantes.MOTIVO_FNUMEROSAESPECIAL).equals(form.getMotivoRedEx())) {
				formulario.setDiscapacidad(" ");
				formulario.setDemandanteEmpleo(" ");
				formulario.setFamiliaNumerosa("X");
				formulario.setFamiliaNumerosaEspecial("X");
				formulario.setFamiliaNumerosaGeneral(" ");
				String mensajeFNespecial = ResourceBundle.getBundle(MESSAGE_RESOUCE).getString("field.formulario790.fnumerosaEspecial");;
				formulario.setFnumerosa(mensajeFNespecial);
				formulario.setVictimaTerrorismo(" ");
			} else if ((Constantes.MOTIVO_VICTIMATERRORISMO).equals(form.getMotivoRedEx())) {
				formulario.setDiscapacidad(" ");
				formulario.setDemandanteEmpleo(" ");
				formulario.setFamiliaNumerosa(" ");
				formulario.setFamiliaNumerosaGeneral(" ");
				formulario.setFamiliaNumerosaEspecial(" ");
				formulario.setVictimaTerrorismo("X");		
			} else {
				formulario.setDiscapacidad(" ");
				formulario.setDemandanteEmpleo(" ");
				formulario.setFamiliaNumerosa(" ");
				formulario.setFamiliaNumerosaGeneral(" ");
				formulario.setFamiliaNumerosaEspecial(" ");
				formulario.setVictimaTerrorismo(" ");
			}
		}else{
			formulario.setDiscapacidad(" ");
			formulario.setDemandanteEmpleo(" ");
			formulario.setFamiliaNumerosa(" ");
			formulario.setVictimaTerrorismo(" ");
		}
		
		// en el caso de que la forma de pago elegida sea efectivo, en los
		// campos de datos de la cuenta bancaria vendran nulos
		// los cargo en blanco para que se puedan cargar todos los campos
		if (form.getEntidad() == null) {
			form.setIban(" ");
			form.setEntidad(" ");
			form.setOficina(" ");
			form.setDigitoConttrol(" ");
			form.setNumeroCuenta(" ");
		}

		// iban
		formulario.setIban(form.getIban());
		cadenaAux = this.completarEquis(form.getIban(), 4);
		formulario.setCodigoIban1(cadenaAux.charAt(0));
		formulario.setCodigoIban2(cadenaAux.charAt(1));
		formulario.setCodigoIban3(cadenaAux.charAt(2));
		formulario.setCodigoIban4(cadenaAux.charAt(3));
		
		// entidad
		formulario.setEntidad(form.getEntidad());
		cadenaAux = this.completarEquis(form.getEntidad(), 4);
		formulario.setCodigoEntidad1(cadenaAux.charAt(0));
		formulario.setCodigoEntidad2(cadenaAux.charAt(1));
		formulario.setCodigoEntidad3(cadenaAux.charAt(2));
		formulario.setCodigoEntidad4(cadenaAux.charAt(3));

		// oficina
		formulario.setOficina(form.getOficina());
		cadenaAux = this.completarEquis(form.getOficina(), 4);
		formulario.setCodigoOficina1(cadenaAux.charAt(0));
		formulario.setCodigoOficina2(cadenaAux.charAt(1));
		formulario.setCodigoOficina3(cadenaAux.charAt(2));
		formulario.setCodigoOficina4(cadenaAux.charAt(3));

		// digito de control
		formulario.setDigitoConttrol(form.getDigitoConttrol());
		cadenaAux = this.completarEquis(form.getDigitoConttrol(), 2);
		formulario.setCodigoDigitoControl1(cadenaAux.charAt(0));
		formulario.setCodigoDigitoControl2(cadenaAux.charAt(1));

		// numero de cuenta
		formulario.setNumeroCuenta(form.getNumeroCuenta());
		cadenaAux = this.completarEquis(form.getNumeroCuenta(), 10);
		formulario.setCodigoNumeroCuenta1(cadenaAux.charAt(0));
		formulario.setCodigoNumeroCuenta2(cadenaAux.charAt(1));
		formulario.setCodigoNumeroCuenta3(cadenaAux.charAt(2));
		formulario.setCodigoNumeroCuenta4(cadenaAux.charAt(3));
		formulario.setCodigoNumeroCuenta5(cadenaAux.charAt(4));
		formulario.setCodigoNumeroCuenta6(cadenaAux.charAt(5));
		formulario.setCodigoNumeroCuenta7(cadenaAux.charAt(6));
		formulario.setCodigoNumeroCuenta8(cadenaAux.charAt(7));
		formulario.setCodigoNumeroCuenta9(cadenaAux.charAt(8));
		formulario.setCodigoNumeroCuenta10(cadenaAux.charAt(9));

		if (form.getId() != null && form.getId() > 0) {
			// Obtengo los datos de la convocatoria
			ConvocatoriaBean convocatoriaBean = formulario790Manager
					.getConvocatoriaPorId(form.getId());

			// Completo los datos del cuerpo de escala
			if(!modJusticia){
				cadenaAux = this.completarCeros(convocatoriaBean
						.getCuerpoEscala().getCodigo(), 4);
				formulario.setCuerpoEscala(convocatoriaBean.getCuerpoEscala()
						.getDescripcion());
				formulario.setCodigoCuerpoEscala(convocatoriaBean.getCuerpoEscala()
						.getCodigo());
				formulario.setCodigoCuerpoEscala1(cadenaAux.charAt(0));
				formulario.setCodigoCuerpoEscala2(cadenaAux.charAt(1));
				formulario.setCodigoCuerpoEscala3(cadenaAux.charAt(2));
				formulario.setCodigoCuerpoEscala4(cadenaAux.charAt(3));
				String auxCuerpoEscala = String.valueOf(convocatoriaBean
						.getCuerpoEscala().getId());
				formulario.setIdCuerpoEscala(Long.parseLong(auxCuerpoEscala));
				formulario.setCodigoCuerpoEscala(convocatoriaBean.getCuerpoEscala()
						.getCodigo());
			}
			
			// Centro Gestor
			if (!"".equals(convocatoriaBean.getIdCentroGestor())
					&& convocatoriaBean.getIdCentroGestor() != null
					&& new Long(convocatoriaBean.getIdCentroGestor()) > 0) {
				CentroGestorBean centroGestorBean = formulario790Manager
						.getCentroGestorPorId(new Long(convocatoriaBean
								.getIdCentroGestor()));
				cadenaAux = this.completarBlancos(centroGestorBean.getCodigo(),
						4);
				formulario.setCentroGestor(centroGestorBean.getDescripcion());
				formulario.setIdCentroGestor(centroGestorBean.getId()
						.toString());
				formulario.setCodCentroGestor(centroGestorBean.getCodigo());
				
			} else {
				formulario.setCentroGestor(" ");
				formulario.setIdCentroGestor(" ");
				formulario.setCodCentroGestor(" ");
			}

			// Convocatoria
			String auxIdConvocatoria;
			auxIdConvocatoria = String.valueOf(convocatoriaBean.getId());
			if (!"null".equals(auxIdConvocatoria)
					&& !"".equals(auxIdConvocatoria)
					&& auxIdConvocatoria != null) {
				formulario.setIdConvocatoria(Long.parseLong(auxIdConvocatoria));
			} else {
				formulario.setIdConvocatoria(null);
			}

			// Completo los datos de la forma de acceso
			logger.info("FormaAcceso: " + convocatoriaBean.getDesFormaAcceso());
			formulario.setFormaAcceso(convocatoriaBean.getDesFormaAcceso());
			String auxFormaAcceso = String.valueOf(convocatoriaBean
					.getIdFormaAcceso());
			formulario.setIdFormaAcceso(Long.parseLong(auxFormaAcceso));
			formulario.setCodFormaAcceso(convocatoriaBean.getCodFormaAcceso());


			
		} else {
			// cuerpo de escala
			if(!modJusticia){
				if (form.getCuerpoEscala() != null
						&& !"".equals(form.getCuerpoEscala())
						&& new Long(form.getCuerpoEscala()) > 0) {
					CuerpoEscalaBean cuerpoEscalaBean = formulario790Manager
							.getCuerpoEscalaPorId(new Long(form.getCuerpoEscala()));
					cadenaAux = this.completarCeros(cuerpoEscalaBean.getCodigo(),
							4);
					formulario.setCuerpoEscala(cuerpoEscalaBean.getDescripcion());
					formulario.setCodigoCuerpoEscala(cuerpoEscalaBean.getCodigo());
					formulario.setIdCuerpoEscala(cuerpoEscalaBean.getId());
				} else {
					if (form.getIdCuerpoEscala() != null
							&& !"".equals(form.getIdCuerpoEscala())
							&& new Long(form.getIdCuerpoEscala()) > 0) {
						CuerpoEscalaBean cuerpoEscalaBean = formulario790Manager
								.getCuerpoEscalaPorId(new Long(form
										.getIdCuerpoEscala()));
						cadenaAux = this.completarCeros(cuerpoEscalaBean
								.getCodigo(), 4);
						formulario.setCuerpoEscala(cuerpoEscalaBean
								.getDescripcion());
						formulario.setCodigoCuerpoEscala(cuerpoEscalaBean
								.getCodigo());
						formulario.setIdCuerpoEscala(new Long(form
								.getIdCuerpoEscala()));
					} else {
						if(form.getCuerpoEscalaManual() != null && !"".equals(form.getCuerpoEscalaManual())){
							formulario.setCuerpoEscala(form.getCuerpoEscalaManual());
						}else{
							formulario.setCuerpoEscala(" ");
						}		
						
						formulario.setCodigoCuerpoEscala(" ");
						cadenaAux = this.completarBlancos("", 4);
						formulario.setIdCuerpoEscala(0L);
					}
				}
			}
			
			formulario.setCodigoCuerpoEscala1(cadenaAux.charAt(0));
			formulario.setCodigoCuerpoEscala2(cadenaAux.charAt(1));
			formulario.setCodigoCuerpoEscala3(cadenaAux.charAt(2));
			formulario.setCodigoCuerpoEscala4(cadenaAux.charAt(3));

			// Convocatoria
			String auxIdConvocatoria;
			auxIdConvocatoria = String.valueOf(form.getIdConvocatoria());
			if (!"null".equals(auxIdConvocatoria)
					&& !"".equals(auxIdConvocatoria)
					&& auxIdConvocatoria != null) {
				formulario.setIdConvocatoria(Long.parseLong(auxIdConvocatoria));
			} else {
				formulario.setIdConvocatoria(null);
			}

			// Centro gestor
			if (!"".equals(form.getCentroGestor())
					&& form.getCentroGestor() != null
					&& new Long(form.getCentroGestor()) > 0) {
				CentroGestorBean centroGestorBean = formulario790Manager.getCentroGestorPorId(new Long(form.getCentroGestor()));
				if(centroGestorBean.getCodigo()!=null){
					cadenaAux = this.completarBlancos(centroGestorBean.getCodigo(),4);
				}else{
					cadenaAux = this.completarBlancos("",4);
				}
				formulario.setCentroGestor(centroGestorBean.getDescripcion());
				formulario.setIdCentroGestor(centroGestorBean.getId()
						.toString());
				formulario.setCodCentroGestor(centroGestorBean.getCodigo());
			} else {
				if (!"".equals(form.getIdCentroGestor())
						&& form.getIdCentroGestor() != null
						&& new Long(form.getIdCentroGestor()) > 0) {
					CentroGestorBean centroGestorBean = formulario790Manager
							.getCentroGestorPorId(new Long(form
									.getIdCentroGestor()));
					cadenaAux = this.completarBlancos(centroGestorBean
							.getCodigo(), 4);
					formulario.setCentroGestor(centroGestorBean
							.getDescripcion());
					formulario.setIdCentroGestor(centroGestorBean.getId()
							.toString());
					formulario.setCodCentroGestor(centroGestorBean.getCodigo());
				} else {
					if (!"".equals(form.getCentroGestorManual())
							&& form.getCentroGestorManual() != null) {
						formulario
								.setCentroGestor(form.getCentroGestorManual());
						formulario.setIdCentroGestor(" ");
						formulario.setCodCentroGestor(" ");

					} else {
						formulario.setCentroGestor(" ");
						formulario.setIdCentroGestor(" ");
						formulario.setCodCentroGestor(" ");
					}
				}
			}

			// forma de acceso
			if (!"".equals(form.getIdFormaAcceso())
					&& new Long(form.getIdFormaAcceso()) > 0) {
				formulario.setFormaAcceso(form.getCodFormaAcceso());
				formulario.setIdFormaAcceso(new Long(form.getIdFormaAcceso()));
			} else {
				if (!"".equals(form.getFormaAcceso())
						&& new Long(form.getFormaAcceso()) > 0) {
					FormaAccesoBean formaAccesoBean = formulario790Manager
							.getFormaAccesoPorId(new Long(form.getFormaAcceso()));
					formulario.setFormaAcceso(formaAccesoBean.getCodigo());
					String auxFormaAcceso = String.valueOf(formaAccesoBean
							.getId());
					formulario.setIdFormaAcceso(Long.parseLong(auxFormaAcceso));
					formulario.setCodFormaAcceso(formaAccesoBean.getCodigo());
				} else if(form.getFormaAccesoManual() != null && !"".equals(form.getFormaAccesoManual())){
					formulario.setFormaAcceso(form.getFormaAccesoManual());
				} else{
					formulario.setFormaAcceso(" ");
					formulario.setIdFormaAcceso(0L);
					formulario.setCodFormaAcceso(" ");
				}
			}

			// ministerio
			if (!"".equals(form.getMinisterio())
					&& form.getMinisterio() != null
					&& !"0".equals(form.getMinisterio())
					&& !"-1".equals(form.getMinisterio())) {
				MinisterioBean ministerioBean = null;
				try {
					Long idMinisterio = Long.parseLong(form.getMinisterio());
					ministerioBean = formulario790Manager
							.getMinisterioPorId(idMinisterio);
				} catch (Exception e) {
					logger.error("Error parsear ministerio",e);
				}
				if (ministerioBean != null) {
					// TODO Eliminar Bloque no utilizado
					
					formulario.setMinisterio(ministerioBean.getDescripcion());					
					String idMinisterioAux = String.valueOf(ministerioBean
							.getId());
					formulario.setIdMinisterio(Long.parseLong(idMinisterioAux));
				} else {
					cadenaAux = this.completarBlancos("", 5);
					formulario.setMinisterio(form.getMinisterio());
					// TODO Eliminar Bloque no utilizado
					formulario.setIdMinisterio(0L);
				}
			} else {
				if (!"".equals(form.getMinisterioManual())
						&& form.getMinisterioManual() != null) {
					cadenaAux = this.completarBlancos("", 5);
					formulario.setMinisterio(form.getMinisterioManual());
					// TODO Eliminar Bloque no utilizado
					formulario.setIdMinisterio(0L);
				} else {
					if (!"".equals(form.getIdMinisterio())
							&& form.getIdMinisterio() != null) {
						MinisterioBean ministerioBean = formulario790Manager
								.getMinisterioPorId(Long.parseLong(form
										.getIdMinisterio()));
						if (ministerioBean != null) {
							// TODO Eliminar Bloque no utilizado							
							
							formulario.setMinisterio(ministerioBean
									.getDescripcion());
							
							formulario.setIdMinisterio(new Long(form
									.getIdMinisterio()));
						} else {
							// TODO Eliminar Bloque no utilizado	
							formulario.setMinisterio(" ");							
							formulario.setIdMinisterio(0L);
						}
					} else {
						cadenaAux = this.completarBlancos("", 5);
						formulario.setMinisterio(" ");
						// TODO Eliminar Bloque no utilizado
						formulario.setIdMinisterio(0L);
					}
				}
			}

		}

		// Convocatoria
		
		//	TipoPago
		if(form.getFormaPago() != null && !"".equals(form.getFormaPago())){
			if("A".equals(form.getFormaPago())){
				formulario.setFormaPagoAdeudo("X");
				formulario.setFormaPagoEfectivo(" ");
			} else if("E".equals(form.getFormaPago())) {
				formulario.setFormaPagoAdeudo(" ");
				formulario.setFormaPagoEfectivo("X");				
			}else if ("N".equals(form.getFormaPago())){
				formulario.setFormaPagoAdeudo(" ");
				formulario.setFormaPagoEfectivo(" ");
			}
		}
		
		// TODO BILINGUE - Incluir locale
		ResourceBundle RESOURCE_MESSAGE_ES = ResourceBundle.getBundle("MessageResources");
		ResourceBundle RESOURCE_MESSAGE_BI = ResourceBundle.getBundle(MESSAGE_RESOUCE);
		
		StringBuffer comunidadAutonoma = new StringBuffer();
		
		comunidadAutonoma.append("");
		formulario.setComunidadAutonoma(comunidadAutonoma.toString());
		
		StringBuffer numeroTituloTexto = new StringBuffer();
		
		numeroTituloTexto.append(RESOURCE_MESSAGE_ES.getString("formulario790.numerotituloFN"));
		formulario.setNumeroTitulo(numeroTituloTexto.toString());
		
		//Numero titulo en otros idiomas
		if (!StringUtils.isEmpty(RESOURCE_MESSAGE_BI.getString("formulario790.numerotituloFN"))) {
			formulario.setNumeroTitulo(RESOURCE_MESSAGE_BI.getString("formulario790.numerotituloFN"));
		}
		
		StringBuffer numeroTituloTexto2 = new StringBuffer();
		
		numeroTituloTexto2.append(RESOURCE_MESSAGE_ES.getString("formulario790.numerotituloFN2"));
		formulario.setNumeroTitulo2(numeroTituloTexto2.toString());
		
		//Numero titulo en otros idiomas
		if (!StringUtils.isEmpty(RESOURCE_MESSAGE_BI.getString("formulario790.numerotituloFN2"))) {
			formulario.setNumeroTitulo2(RESOURCE_MESSAGE_BI.getString("formulario790.numerotituloFN2"));
		}
		
		ConvocatoriaBean convocatoria = formulario790Manager.getConvocatoriaPorId(idConvocatoria);
		String enlace = convocatoria.getEnlace();
		if(enlace == null || enlace.isEmpty()) {
			enlace = RESOURCE_MESSAGE_ES.getString("formulario790.enlace.noIndicado");
		}
		
		StringBuffer cumplimiento = new StringBuffer();
		
		cumplimiento.append(RESOURCE_MESSAGE_ES.getString("formulario790.cumplimiento"));
		String error = RESOURCE_MESSAGE_BI.getString("formulario790.cumplimiento")+enlace+RESOURCE_MESSAGE_BI.getString("formulario790.cumplimiento2");
		formulario.setCumplimiento(error);
		
		//cumplimiento en otros idiomas
		if (!StringUtils.isEmpty(RESOURCE_MESSAGE_BI.getString("formulario790.cumplimiento"))) {
			formulario.setCumplimiento(error);
		}
		
		StringBuffer textoCCAA = new StringBuffer();
		
		textoCCAA.append(RESOURCE_MESSAGE_ES.getString("formulario790.ccaa"));
		formulario.setCcaaFN(textoCCAA.toString());
		
		//CCAA en otros idiomas
		if (!StringUtils.isEmpty(RESOURCE_MESSAGE_BI.getString("formulario790.ccaa"))) {
			formulario.setCcaaFN(RESOURCE_MESSAGE_BI.getString("formulario790.ccaa"));
		}
		
		StringBuffer textoCCAA2 = new StringBuffer();
		
		textoCCAA2.append(RESOURCE_MESSAGE_ES.getString("formulario790.ccaa2"));
		formulario.setCcaaFN2(textoCCAA2.toString());
		
		//CCAA en otros idiomas
		if (!StringUtils.isEmpty(RESOURCE_MESSAGE_BI.getString("formulario790.ccaa2"))) {
			formulario.setCcaaFN2(RESOURCE_MESSAGE_BI.getString("formulario790.ccaa2"));
		}
		
		StringBuffer textoEjerce = new StringBuffer();
		
		textoEjerce.append(RESOURCE_MESSAGE_ES.getString("formulario790.ejerce"));
		formulario.setEjerce(textoEjerce.toString());
		
		//Ejerce en otros idiomas
		if (!StringUtils.isEmpty(RESOURCE_MESSAGE_BI.getString("formulario790.ejerce"))) {
			formulario.setEjerce(RESOURCE_MESSAGE_BI.getString("formulario790.ejerce"));
		}
		
		StringBuffer textoEjerce2 = new StringBuffer();
		
		textoEjerce2.append(RESOURCE_MESSAGE_ES.getString("formulario790.ejerce2"));
		formulario.setEjerce2(textoEjerce2.toString());
		
		//Ejerce en otros idiomas
		if (!StringUtils.isEmpty(RESOURCE_MESSAGE_BI.getString("formulario790.ejerce2"))) {
			formulario.setEjerce2(RESOURCE_MESSAGE_BI.getString("formulario790.ejerce2"));
		}
		
		StringBuffer textoNoAutorizar = new StringBuffer();
		
		textoNoAutorizar.append(RESOURCE_MESSAGE_ES.getString("formulario790.noAutorizar"));
		formulario.setNoAutorizar(textoNoAutorizar.toString());
		
		//Ejerce en otros no autorizar
		if (!StringUtils.isEmpty(RESOURCE_MESSAGE_BI.getString("formulario790.noAutorizar"))) {
			formulario.setNoAutorizar(RESOURCE_MESSAGE_BI.getString("formulario790.noAutorizar"));
		}
		
		StringBuffer textoAutorizar = new StringBuffer();
		
		textoAutorizar.append(RESOURCE_MESSAGE_ES.getString("formulario790.autorizar"));
		formulario.setAutoriza(textoAutorizar.toString());
		
		//Ejerce en otros no autorizar
		if (!StringUtils.isEmpty(RESOURCE_MESSAGE_BI.getString("formulario790.autorizar"))) {
			formulario.setAutoriza(RESOURCE_MESSAGE_BI.getString("formulario790.autorizar"));
		}

		// Declaración según modelo y casilla de consentimiento
		if(form.getCodigoTasa() != null && form.getCodigoTasa().equals(Constantes.CODIGO_TASA_JUSTICIA)){
			StringBuffer declaracion = new StringBuffer();
			declaracion.append(RESOURCE_MESSAGE_ES.getString("formulario790.solicita.modelo"));
			declaracion.append("\n");
			declaracion.append(RESOURCE_MESSAGE_ES.getString("formulario790.declara.modelo"));
			StringBuffer declaracionBi = new StringBuffer();
			declaracionBi.append(RESOURCE_MESSAGE_BI.getString("formulario790.solicita.modelo"));
			declaracionBi.append("\n");
			declaracionBi.append(RESOURCE_MESSAGE_BI.getString("formulario790.declara.modelo"));
		
			// Para los secretarios judiciales (código cuerpo escala 4041),
			// se añade el texto específico de juramento.
			if(secretarioJud || (null!=form.getCodigoCuerpoEscala() && form.getCodigoCuerpoEscala().equals(Constantes.COD_SECRETARIO_JUDICIAL))){
				declaracion.append(RESOURCE_MESSAGE_ES.getString("formulario790.secretarioJ.modelo"));
				declaracionBi.append(RESOURCE_MESSAGE_BI.getString("formulario790.secretarioJ.modelo"));
			}
			
			declaracion.append("\n");
			declaracion.append(RESOURCE_MESSAGE_ES.getString("formulario790.manifiesta.modelo"));
//			declaracion.append("\n");
//			declaracion.append(RESOURCE_MESSAGE_ES.getString("formulario790.consiente.modelo"));
			formulario.setDeclaracion(declaracion.toString());
			declaracionBi.append("\n");
			declaracionBi.append(RESOURCE_MESSAGE_BI.getString("formulario790.manifiesta.modelo"));
//			declaracionBi.append("\n");
//			declaracionBi.append(RESOURCE_MESSAGE_BI.getString("formulario790.consiente.modelo"));
			if(!StringUtils.isEmpty(declaracionBi.toString())) {
				formulario.setDeclaracion(declaracionBi.toString());
			}
			StringBuffer noConsiente = new StringBuffer();
			noConsiente.append(RESOURCE_MESSAGE_ES.getString("formulario790.consiente2.modelo"));
			formulario.setNoConsentimiento(noConsiente.toString());
			StringBuffer noConsienteBi = new StringBuffer();
			noConsienteBi.append(RESOURCE_MESSAGE_BI.getString("formulario790.consiente2.modelo"));
			formulario.setNoConsentimientoBilingue(noConsienteBi.toString());
			if(!StringUtils.isEmpty(noConsienteBi.toString())) {
				formulario.setNoConsentimiento(noConsienteBi.toString());
			}
			
			// En caso de formulario 790007 Español, solo figurará una declaración
			if(RESOURCE_MESSAGE_ES.getLocale().equals(RESOURCE_MESSAGE_BI.getLocale())){
				declaracionBi = new StringBuffer();
				noConsienteBi = new StringBuffer();
			}
			
		}else{
			StringBuffer declaracion = new StringBuffer();
			declaracion.append(RESOURCE_MESSAGE_BI.getString("formulario790.solicita"));
			declaracion.append("\n");
			declaracion.append(RESOURCE_MESSAGE_BI.getString("formulario790.declara"));
			declaracion.append("\n");
			declaracion.append(RESOURCE_MESSAGE_BI.getString("formulario790.manifiesta").replaceAll("<br>",""));
//			declaracion.append("\n");
//			declaracion.append(RESOURCE_MESSAGE_BI.getString("formulario790.opone").replaceAll("<br>",""));
//			declaracion.append("\n");
//			declaracion.append(RESOURCE_MESSAGE_BI.getString("formulario790.autoriza"));
			formulario.setDeclaracion(declaracion.toString());
			StringBuffer noConsiente = new StringBuffer();
			noConsiente.append(RESOURCE_MESSAGE_BI.getString("formulario790.presencial.consiente2"));
			
			formulario.setNoConsentimiento(noConsiente.toString());
		}
			
		return formulario;
	}

	/**
	 * Completar blancos.
	 *
	 * @param valor el valor
	 * @param posiciones el posiciones
	 * @return el string
	 */
	public String completarBlancos(String valor, int posiciones) {
		if(valor==null){
			valor = "";
		}
		StringBuffer cadenaAux = new StringBuffer();
		cadenaAux.append(valor.trim());
		while (cadenaAux.length() < posiciones) {
			cadenaAux.append(" ");
		}		
		return cadenaAux.toString();
	}
	
	/**
	 * Completar equis.
	 *
	 * @param valor el valor
	 * @param posiciones el posiciones
	 * @return el string
	 */
	public String completarEquis(String valor, int posiciones) {
		if(valor==null){
			valor = "";
		}
		StringBuffer cadenaAux = new StringBuffer();
		if(valor != ""){
			cadenaAux.append("X");
		}else{
			cadenaAux.append(valor.trim());
		}
		while (cadenaAux.length() < posiciones) {
			if(valor != ""){
				cadenaAux.append("X");
			}else{
				cadenaAux.append(" ");
			}
		}		
		return cadenaAux.toString();
	}
	
	/**
	 * Completar ceros.
	 *
	 * @param valor el valor
	 * @param posiciones el posiciones
	 * @return el string
	 */
	public String completarCeros(String valor, int posiciones) {
		StringBuffer aux = new StringBuffer();
		if(valor != null && !"".equals(valor)){
			StringBuffer cadenaAux = new StringBuffer();
			cadenaAux.append(valor.trim());
			for(int i= cadenaAux.length();i<posiciones;i++){
				aux.append("0");
			}
			aux.append(cadenaAux);
		}else{
			aux.append(this.completarBlancos("", posiciones));
		}
		return aux.toString();
	}

	/**
	 * Quitar caracteres especiales.
	 *
	 * @param formulario el formulario
	 */
	private void quitarCaracteresEspeciales(Formulario790Form formulario) {
			
		if(formulario.getNacionalidad() != null){
			String nacionalidad = this.eliminarCaracteresNoPermitidos(formulario.getNacionalidad());
			formulario.setNacionalidad(nacionalidad);
		}
		
		if(formulario.getNif() != null){
			String nif = this.eliminarCaracteresNoPermitidos(formulario.getNif());
			formulario.setNif(nif);
		}
		
		if(formulario.getNombre() != null){
			String nombre = this.eliminarCaracteresNoPermitidos(formulario.getNombre());
			formulario.setNombre(nombre);
		}
		
		if(formulario.getPrimerApellido() != null){
			String primerApellido = this.eliminarCaracteresNoPermitidos(formulario.getPrimerApellido());
			formulario.setPrimerApellido(primerApellido);
		}

		if(formulario.getSegundoApellido() != null){
			String segundoApellido = this.eliminarCaracteresNoPermitidos(formulario.getSegundoApellido());
			formulario.setSegundoApellido(segundoApellido);
		}
		
		if(formulario.getCorreoElectronicos() != null){
			String correoElectronico = this.eliminarCaracteresNoPermitidos(formulario.getCorreoElectronicos());
			formulario.setCorreoElectronicos(correoElectronico);
		}
		
		if(formulario.getCalleDomicilio() != null){
			String calleDomicilio = this.eliminarCaracteresNoPermitidos(formulario.getCalleDomicilio());
			formulario.setCalleDomicilio(calleDomicilio);
		}
		
		if(formulario.getMunicipioDomicilio() != null){
			String municipioDomicilio = this.eliminarCaracteresNoPermitidos(formulario.getMunicipioDomicilio());
			formulario.setMunicipioDomicilio(municipioDomicilio);
		}
		
		if(formulario.getOtrosTitulos() != null){
			String otrosTitulos = this.eliminarCaracteresNoPermitidos(formulario.getOtrosTitulos());
			formulario.setOtrosTitulos(otrosTitulos);
		}
		
		if(formulario.getCampoPropio0()!=null){
			String campoPropio0 = this.eliminarCaracteresNoPermitidos(formulario.getCampoPropio0());
			formulario.setCampoPropio0(campoPropio0);
		}
		
		if(formulario.getCampoPropio1()!=null){
			String campoPropio1 = this.eliminarCaracteresNoPermitidos(formulario.getCampoPropio1());
			formulario.setCampoPropio1(campoPropio1);
		}
		
		if(formulario.getCampoPropio2()!=null){
			String campoPropio2 = this.eliminarCaracteresNoPermitidos(formulario.getCampoPropio2());
			formulario.setCampoPropio2(campoPropio2);
		}
		
		if(formulario.getCampoPropio3()!=null){
			String campoPropio3 = this.eliminarCaracteresNoPermitidos(formulario.getCampoPropio3());
			formulario.setCampoPropio3(campoPropio3);
		}
		
		if(formulario.getPersonaFirmante() != null){
			String personaFirmante = this.eliminarCaracteresNoPermitidos(formulario.getPersonaFirmante());
			formulario.setPersonaFirmante(personaFirmante);
		}
		
		if(formulario.getLugarFirma() != null){
			String fechaFirma = this.eliminarCaracteresNoPermitidos(formulario.getLugarFirma());
			formulario.setLugarFirma(fechaFirma);
		}
		
		if(formulario.getMinisterioManual() != null){
			String ministerioManual = this.eliminarCaracteresNoPermitidos(formulario.getMinisterioManual());
			formulario.setMinisterioManual(ministerioManual);
		}
		
		if(formulario.getMinisterioAutomatico() != null){
			String ministerioConvocatoria = this.eliminarCaracteresNoPermitidos(formulario.getMinisterioAutomatico());
			formulario.setMinisterioAutomatico(ministerioConvocatoria);
		}
		
		if(formulario.getCentroGestorManual() != null){
			String centroGestorManual = this.eliminarCaracteresNoPermitidos(formulario.getCentroGestorManual());
			formulario.setCentroGestorManual(centroGestorManual);
		}
		
		if(formulario.getCuerpoEscalaManual() != null){
			String cuerpoEscalaManual = this.eliminarCaracteresNoPermitidos(formulario.getCuerpoEscalaManual());
			formulario.setCuerpoEscalaManual(cuerpoEscalaManual);
		}
		
		if(formulario.getEspecialidadManual() != null){
			String especialidadManual = this.eliminarCaracteresNoPermitidos(formulario.getEspecialidadManual());
			formulario.setEspecialidadManual(especialidadManual);
		}
		
		if(formulario.getFormaAccesoManual() != null){
			String formaAccesoManual = this.eliminarCaracteresNoPermitidos(formulario.getFormaAccesoManual());
			formulario.setFormaAccesoManual(formaAccesoManual);
		}
		
		if(formulario.getCodigoPostalDomicilio() != null){
			String codPostal = this.eliminarCaracteresNoPermitidos(formulario.getCodigoPostalDomicilio());
			formulario.setCodigoPostalDomicilio(codPostal);	
		}
		
		if(formulario.getTelefono() != null){
			String telefono = this.eliminarCaracteresNoPermitidos(formulario.getTelefono());
			formulario.setTelefono(telefono);
		}
		
		if(formulario.getTelefonoAux() != null){
			String telefonoAux = this.eliminarCaracteresNoPermitidos(formulario.getTelefonoAux());
			formulario.setTelefonoAux(telefonoAux);
		}
		
		if(formulario.getDetalleDiscapacidad() != null){
			String detalleDiscapacidad = this.eliminarCaracteresNoPermitidos(formulario.getDetalleDiscapacidad());
			formulario.setDetalleDiscapacidad(detalleDiscapacidad);
		}else{
			formulario.setDetalleDiscapacidad(" ");
		}
		
		if(formulario.getMotivoOposicion() != null){
			String motivoOposicion = this.eliminarCaracteresNoPermitidos(formulario.getMotivoOposicion());
			formulario.setMotivoOposicion(motivoOposicion);
		}else{
			formulario.setMotivoOposicion(" ");
		}
	}
	
	
	/**
	 * Eliminar caracteres no permitidos.
	 *
	 * @param original el original
	 * @return el string
	 */
	public String eliminarCaracteresNoPermitidos(String original){
		String result = "";
		if(original != null){
			result = original;
			result = result.replace("*", "");
		}
		
		return result;
	}
	
	/**
	 * Componer datos codigo.
	 *
	 * @param formulario el formulario
	 * @param request el request
	 * @return el string
	 * @throws ParseException el parse exception
	 */
	public String componerDatosCodigo(Formulario790Bean formulario,HttpServletRequest request) throws ParseException {
		
		StringBuffer anioConvocatoria = new StringBuffer();
		anioConvocatoria.append(formulario.getAnioConvocatoria1()).append("").append(formulario.getAnioConvocatoria2())
			.append("").append(formulario.getAnioConvocatoria3()).append("").append(formulario.getAnioConvocatoria4());

		String fechaNacimiento = formulario.getFechaNacimiento();
		String fechaBoe = formulario.getFechaBoe();

		StringBuffer codProvinciaDomicilio = new StringBuffer(); 
		codProvinciaDomicilio.append(formulario.getCodProvinciaDomicilio1()).append("").append(formulario.getCodProvinciaDomicilio2());
		
		String codPaisDomicilio = formulario.getCodigoPais();
		
		if(formulario.getOtrosTitulos()== null || "".equals(formulario.getOtrosTitulos())){
			formulario.setOtrosTitulos(" ");
		}
		
		String telefono;
		if(formulario.getTelefonoAux() != null && !"".equals(formulario.getTelefonoAux())){
			telefono = formulario.getTelefono() + "/"
					+ formulario.getTelefonoAux();
		}else{
			telefono = formulario.getTelefono();
		}
		
		StringBuffer codMinisterioAux = new StringBuffer();
		if (formulario.getCodigoMinisterio() == null
				|| "".equals(formulario.getCodigoMinisterio())) {
			codMinisterioAux.append(formulario.getCodigoMinisterio1()).append("").append(formulario.getCodigoMinisterio2())
				.append("").append(formulario.getCodigoMinisterio3()).append("").append(formulario.getCodigoMinisterio4())
				.append("").append(formulario.getCodigoMinisterio5());
		} else {
			codMinisterioAux.append(formulario.getCodigoMinisterio());
		}
		
		if(formulario.getSegundoApellido() == null || "".equals(formulario.getSegundoApellido())){
			formulario.setSegundoApellido(" ");
		}
		
		// composicion de campos propios
		List<CamposPropiosBean> listaCamposPropios = formulario.getListaCamposPropios();
		String[] listaCamposPropiosAux = {" "," "," ", " "};				
		for (int i = 0; i < listaCamposPropios.size(); i++) {
			if(listaCamposPropios.get(i).getValorVista() != null || !("".equals(listaCamposPropios.get(i).getValorVista()))){
				listaCamposPropiosAux[i] = listaCamposPropios.get(i).getValorVista();
			}
		}
		String composicionCamposPropios =
				listaCamposPropiosAux[0] + " *" + listaCamposPropiosAux[1] + " *" + listaCamposPropiosAux[2] + " *";


		String importe =" ";
		if(formulario.getImporteSolicitud() != null && !"".equals(formulario.getImporteSolicitud())){
			importe = formulario.getImporteSolicitud();
			if(formulario.getImporteSolicitudDecimal() != null && !"".equals(formulario.getImporteSolicitudDecimal())){
				importe = importe + "," + formulario.getImporteSolicitudDecimal();
			}
		}		

		SimpleDateFormat sdf0 = new SimpleDateFormat("dd/MM/yyyy");
		String fechaSolicitud = sdf0.format(new Date());
		StringBuilder cadena = new StringBuilder();
		
		cadena.append(formulario.getIdMinisterio()).append("*").append(codMinisterioAux).append("*")
			.append(formulario.getIdCentroGestor()).append("*").append(formulario.getCodCentroGestor()).append("*")
			.append(formulario.getNumeroJustificante()).append("*").append(anioConvocatoria).append("*")
			.append(formulario.getNif()).append("*").append(formulario.getPrimerApellido()).append("*")
			.append(formulario.getSegundoApellido()).append("*").append(formulario.getNombre()).append("*")
			.append(fechaNacimiento).append("*").append(formulario.getSexoHombre()).append("*")
			.append(formulario.getSexoMujer()).append("*").append(formulario.getNacionalidad()).append("*")
			.append(formulario.getCorreoElectronicos()).append("*").append(telefono).append("*")
			.append(formulario.getCalleDomicilio()).append("*").append(formulario.getCodigoPostalDomicilio()).append("*")
			.append(formulario.getMunicipioDomicilio()).append("*").append(formulario.getIdProvinciaDomicilio()).append("*")
			.append(codProvinciaDomicilio).append("*").append(codPaisDomicilio).append("*").append(formulario.getIdCuerpoEscala()).append("*")
			.append(formulario.getCodigoCuerpoEscala()).append("*").append(formulario.getIdEspecialidad()).append("*")
			.append(formulario.getCodigoEspecialidad()).append("*").append(formulario.getIdFormaAcceso()).append("*")
			.append(formulario.getCodFormaAcceso()).append("*").append(formulario.getIdMinisterioConvocatoria()).append("*")
			.append(formulario.getCodigoMinisterio()).append("*").append(fechaBoe).append("*")
			.append(formulario.getIdProvinciaExamen()).append("*").append(formulario.getCodigoProvinciaExamen()).append("*")
			.append(formulario.getPorcentajeDiscapacidad()).append("*").append(formulario.getIdTipoDiscapacidad()).append("*")
			.append(((Character) formulario.getCodigoTipoDiscapacidad()).toString()).append(" *").append(formulario.getDetalleDiscapacidad()).append("*")
			.append(formulario.getIdTituloOficial()).append("*").append(formulario.getCodTitutoOficial()).append("*")
			.append(formulario.getOtrosTitulos()).append("*").append(composicionCamposPropios).append(fechaSolicitud).append("*").append(importe).append("*")
			.append(formulario.getConsentimiento()).append("*").append(formulario.getMotivoOposicion()).append("*").append(formulario.getFormaPagoEfectivo()).append("*").append(formulario.getFormaPagoAdeudo()).append("*")
			.append(formulario.getDiscapacidad()).append("*").append(formulario.getDemandanteEmpleo()).append("*").append(formulario.getFamiliaNumerosaGeneral()).append("*").append(formulario.getFamiliaNumerosaEspecial()).append("*")
			.append(formulario.getVictimaTerrorismo()).append("*").append(formulario.getIdCcaa()).append("*").append(formulario.getNumeroTituloFN()).append("* ");
		
		//Sustituir las ñ por $ en la cadena de texto antes de formar la nube de puntos
		String cadenaFinal = cadena.toString();
		cadenaFinal = cadenaFinal.replace("Ñ", "$");
		cadenaFinal = cadenaFinal.replace("º", "o");
		cadenaFinal = cadenaFinal.replace("ª", "a");
		

		logger.info("Cadena: "+cadenaFinal);	
		logger.info("Codigo de barras: " + cadenaFinal);
		
		return cadenaFinal;
	}
	
	/**
	 * Obtiene el formulario 790 manager.
	 *
	 * @return el formulario 790 manager
	 */
	public Formulario790Manager getFormulario790Manager() {
		return formulario790Manager;
	}

	/**
	 * Establece el formulario 790 manager.
	 *
	 * @param formulario790Manager el nuevo formulario 790 manager
	 */
	public void setFormulario790Manager(
			Formulario790Manager formulario790Manager) {
		this.formulario790Manager = formulario790Manager;
	}

	/**
	 * Obtiene el descarga documento manager.
	 *
	 * @return el descarga documento manager
	 */
	public DescargaDocumentoManager getDescargaDocumentoManager() {
		return descargaDocumentoManager;
	}

	/**
	 * Establece el descarga documento manager.
	 *
	 * @param descargaDocumentoManager el nuevo descarga documento manager
	 */
	public void setDescargaDocumentoManager(
			DescargaDocumentoManager descargaDocumentoManager) {
		this.descargaDocumentoManager = descargaDocumentoManager;
	}

	/**
	 * Obtiene el parametro configuracion manager.
	 *
	 * @return el parametro configuracion manager
	 */
	public ParametroConfiguracionManager getParametroConfiguracionManager() {
		return parametroConfiguracionManager;
	}

	/**
	 * Establece el parametro configuracion manager.
	 *
	 * @param parametroConfiguracionManager el nuevo parametro configuracion manager
	 */
	public void setParametroConfiguracionManager(
			ParametroConfiguracionManager parametroConfiguracionManager) {
		this.parametroConfiguracionManager = parametroConfiguracionManager;
	}

	/**
	 * Obtiene el solicitud manager.
	 *
	 * @return el solicitud manager
	 */
	public SolicitudManager getSolicitudManager() {
		return solicitudManager;
	}

	/**
	 * Establece el solicitud manager.
	 *
	 * @param solicitudManager el nuevo solicitud manager
	 */
	public void setSolicitudManager(SolicitudManager solicitudManager) {
		this.solicitudManager = solicitudManager;
	}

	/**
	 * Obtiene el solicitud propio auxiliar manager.
	 *
	 * @return el solicitud propio auxiliar manager
	 */
	public SolicitudPropioAuxiliarManager getSolicitudPropioAuxiliarManager() {
		return solicitudPropioAuxiliarManager;
	}

	/**
	 * Establece el solicitud propio auxiliar manager.
	 *
	 * @param solicitudPropioAuxiliarManager el nuevo solicitud propio auxiliar manager
	 */
	public void setSolicitudPropioAuxiliarManager(
			SolicitudPropioAuxiliarManager solicitudPropioAuxiliarManager) {
		this.solicitudPropioAuxiliarManager = solicitudPropioAuxiliarManager;
	}

	/**
	 * Obtiene el solicitud comun auxiliar manager.
	 *
	 * @return el solicitud comun auxiliar manager
	 */
	public SolicitudComunAuxiliarManager getSolicitudComunAuxiliarManager() {
		return solicitudComunAuxiliarManager;
	}

	/**
	 * Establece el solicitud comun auxiliar manager.
	 *
	 * @param solicitudComunAuxiliarManager el nuevo solicitud comun auxiliar manager
	 */
	public void setSolicitudComunAuxiliarManager(
			SolicitudComunAuxiliarManager solicitudComunAuxiliarManager) {
		this.solicitudComunAuxiliarManager = solicitudComunAuxiliarManager;
	}

	/**
	 * Obtiene el solicitud ccaa auxiliar manager.
	 *
	 * @return el solicitud ccaa auxiliar manager
	 */
	public SolicitudCcaaAuxiliarManager getSolicitudCcaaAuxiliarManager() {
		return solicitudCcaaAuxiliarManager;
	}

	/**
	 * Establece el solicitud ccaa auxiliar manager.
	 *
	 * @param solicitudCcaaAuxiliarManager el nuevo solicitud ccaa auxiliar manager
	 */
	public void setSolicitudCcaaAuxiliarManager(
			SolicitudCcaaAuxiliarManager solicitudCcaaAuxiliarManager) {
		this.solicitudCcaaAuxiliarManager = solicitudCcaaAuxiliarManager;
	}

}
