package es.map.ipsg.action.solicitudPresencial;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringMessage;
import es.map.ipsg.bean.DocumentoBean;
import es.map.ipsg.bean.ModeloBean;
import es.map.ipsg.form.Modelo790MasivoForm;
import es.map.ipsg.manager.Modelo790Manager;
import es.map.ipsg.util.Zipeador;

/**
 * El Class GenerarModelo790MasivoSpring.
 */
public class GenerarModelo790MasivoSpring extends AbstractSpring<Modelo790MasivoForm> {

	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(GenerarModelo790MasivoSpring.class);
	
	/** La constante MESSAGE_RESOURCE. */
	private static final String MESSAGE_RESOURCE = "MessageResources";
	
	/** La constante RESOURCE_BUNDLE. */
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(MESSAGE_RESOURCE);
	
	/** el properties. */
	private static Properties properties;
	
	/** el modelo 790 manager. */
	private Modelo790Manager modelo790Manager;
	
	/**
	 * Acción GenerarModelo790MasivolAction.
	 */
	public GenerarModelo790MasivoSpring() {
		this.setModelo790Manager((Modelo790Manager) getBean("modelo790Manager"));	
		properties = (Properties) getBean("propertiesBean");
	}

	/**
	 * Método doExecute de GenerarModelo790MasivolAction.
	 *
	 * @param form SpringForm
	 * @return resultado String si no tiene errores nos devuelve success
	 * @throws Exception Exception
	 */
	protected String doExecute(Modelo790MasivoForm form) throws Exception {
		//*********** METO EL PARAMETRO QUE ME INDICA EN QUE MENU ESTOY **********
		String menu_solPresencial = RESOURCE_BUNDLE.getString("field.menu.solicPresenciales");
		this.getRequest().getSession().setAttribute("pagActiva", menu_solPresencial);
		String subMenu_SolPresen = RESOURCE_BUNDLE.getString("field.menuLateral.solicitudesPresenciales.generarModelo790Masivo");
		this.getRequest().getSession().setAttribute("subMenuActiva",subMenu_SolPresen);
		//******************************************************************
		
		try{
			String sNumModelos = form.getNumModelos();
			Integer numModelos = Integer.parseInt(sNumModelos);
			
			// Recuperamos la lista de modelos de sesión
			ArrayList<ModeloBean> listaModelos;
			listaModelos = (ArrayList<ModeloBean>) this.getSession().getAttribute("listaModelos");
			
			// Recuperamos el modelo comparando con el recibido del formulario
			String modeloAux = form.getModelo();
			// Por defecto
			ModeloBean modeloBean = new ModeloBean();
			
			for (ModeloBean modeloBeanAux : listaModelos) {
				if(String.valueOf(modeloBeanAux.getId()).equals(modeloAux)){
					modeloBean = modeloBeanAux;
				}
			}
			
			logger.info("Se van a generar "+numModelos+" del modelo "+modeloBean.getNumModelo());
			
			//Para crar rutas temporales distintas segun tiempo
			String path = properties.getProperty("gestDocumental.rutaZipTemp");
			Long temporal = System.currentTimeMillis();	
			path = path + temporal.toString();
			String rutaZip = properties.getProperty("gestDocumental.rutaDocsTemp");
			rutaZip = rutaZip + "modelos790.zip";
			
			//Borramos la carpeta Raiz
			File file = new File(path);
			logger.info("ruta absoluta:" + file.getAbsolutePath());
			
			if (file.exists()){
				borraCarpeta(path);
			}
			creaCarpeta(path);
			int contador = 0;
			for(int i = 0; i < numModelos; i++){
				contador++;
				String rutaAux = path;
				rutaAux=rutaAux;
				if(i==0){
					//Creamos la carpeta con el numero de solicitud
					creaCarpeta(rutaAux);
				}
				byte[] pdf = modelo790Manager.generarModelo790Masivo(1, modeloBean);
				
				
				if(pdf != null){
					guardarSalida(rutaAux, pdf, i);
				}
			}
			
			if (contador>0){	
				//Generamos el Zip
				File zip = new File(path); 
				Zipeador zipeador = new Zipeador(zip,rutaZip);
				zipeador.comprimir();

				javax.servlet.ServletOutputStream stream;

				try (FileInputStream f = new FileInputStream(rutaZip);
					 BufferedInputStream bf = new BufferedInputStream(f)
				){
					stream = this.getResponse().getOutputStream();
					String contentDisposition = "attachment; filename=\"" + "modelos790.zip"	+ "\"";
					HttpServletResponse resp = this.getResponse();
					resp.setContentType("application/octet-stream");
					resp.setHeader("Content-Disposition", contentDisposition);			

					byte[] buf = new byte[bf.available()]; 
					bf.read(buf);

					stream.write(buf);
					stream.flush();
					stream.close();

				} catch (IOException e) {
					logger.error("Error DescargarDocumentoZipSpring - generar Zip ",e);
				}
			}
			
			borraCarpeta(path);
			
		}catch(Exception e){
			logger.error("Error GenerarModelo790MasivoSpring  - generarModelo790Masivo ",e);
			this.getRequest().setAttribute("descripcionError", RESOURCE_BUNDLE.getString("field.errorGenerico"));
			return "error";
		}finally{
			
		}
		
		return "success";		
	}
	
	/**
	 * Enviar salida.
	 *
	 * @param salida el salida
	 */
	public void enviarSalida(byte[] salida) {
		
		javax.servlet.ServletOutputStream stream;
		try {
			stream = this.getResponse().getOutputStream();
			String contentDisposition = "attachment; filename=\"modelo790.pdf\"";
			HttpServletResponse resp = this.getResponse();
			resp.setContentType("application/octet-stream");
			resp.setHeader("Content-Disposition", contentDisposition);
			IOUtils.write(salida,stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Error GenerarModelo790MasivoSpring - enviarSalida ",e);
		}
	}
	
	/**
	 * Guardar salida.
	 *
	 * @param ruta el ruta
	 * @param salida el salida
	 * @param num el num
	 */
	public void guardarSalida(String ruta,byte[] salida, Integer num) {
		String rutaArchivo = "";
		if(num != 0){
			rutaArchivo = ruta+"/modelo790 ("+num+").pdf";
		}else{
			rutaArchivo = ruta+"/modelo790.pdf";
		}
		File documento = new File(rutaArchivo);
		try (FileOutputStream fo = new FileOutputStream(documento)
		){			
			fo.write(salida);
			fo.close();
		} catch (FileNotFoundException e1) {
			logger.error(" Error DescargarDocumentoZipSpring - guardarsalida",e1);
		} catch (IOException e) {
			logger.error(" Error DescargarDocumentoZipSpring - guardarsalida",e);
		}		
	}
	
	/**
	 * Borra carpeta.
	 *
	 * @param path el path
	 */
	public static void borraCarpeta(String path){
		File f1 = new File(path);
		File[] files = f1.listFiles();
		for(int i=0;i<files.length;i++){
			if(files[i].isDirectory()){
				try {
					borraCarpeta(files[i].getCanonicalPath());
				} 
				catch (Exception e) {
					logger.error(" Error DescargarDocumentoZipSpring - borrarCarpeta",e);	
				}
				files[i].delete();
			}else{
				files[i].delete();
			}
		}
		f1.delete();
	} 
	
	/**
	 * Crea carpeta.
	 *
	 * @param path el path
	 */
	public static void creaCarpeta(String path){

		File fichero= new File (path);
		// A partir del objeto File creamos el fichero físicamente
		try {
			fichero.mkdirs();

		} catch (Exception e) {
			logger.error(" Error DescargarDocumentoZipSpring - creaCarpeta",e);
		}
	}
	
	
	/**
	 * Obtiene el modelo 790 manager.
	 *
	 * @return el modelo 790 manager
	 */
	public Modelo790Manager getModelo790Manager() {
		return modelo790Manager;
	}

	/**
	 * Establece el modelo 790 manager.
	 *
	 * @param modelo790Manager el nuevo modelo 790 manager
	 */
	public void setModelo790Manager(Modelo790Manager modelo790Manager) {
		this.modelo790Manager = modelo790Manager;
	}
}
