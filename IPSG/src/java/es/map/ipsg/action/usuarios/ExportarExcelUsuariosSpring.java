package es.map.ipsg.action.usuarios;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;

import es.map.ips.common.model.OrderType;
import es.map.ips.common.spring.AbstractSpring;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.model.UsuarioQuery;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.bean.UsuarioBean;
import es.map.ipsg.form.BuscarUsuariosForm;
import es.map.ipsg.manager.UsuarioCentrogestorManager;
import es.map.ipsg.manager.UsuarioManager;


/**
 * El Class ExportarExcelUsuariosSpring.
 */
public class ExportarExcelUsuariosSpring extends AbstractSpring {

	/** La constante MESSAGE_RESOUCE. */
	//Declarar los resource
	private static final String MESSAGE_RESOUCE = "MessageResources";
	
	/** La constante RESOURCE_MESSAGE. */
	private static final ResourceBundle RESOURCE_MESSAGE = ResourceBundle.getBundle(MESSAGE_RESOUCE);
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(ExportarExcelUsuariosSpring.class);
	
	/** el properties. */
	private static Properties properties;

	//Declaracion de Manager
	
	/** el usuario manager. */
	private UsuarioManager usuarioManager;

	/** el usuario centro gestor manager. */
	private UsuarioCentrogestorManager usuarioCentrogestorManager;
	
	/**
	 * Crea una nueva exportar excel usuarios spring.
	 */
	public ExportarExcelUsuariosSpring() {
		try {
			setUsuarioManager((UsuarioManager) getBean("usuarioManager"));
			setUsuarioCentrogestorManager((UsuarioCentrogestorManager) getBean("usuarioCentrogestorManager"));
			properties = (Properties) getBean("propertiesBean");
		} catch (Exception e) {
			logger.error("Error ExportarExcelUsuariosSpring:",e);
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
		String menu_usuarios = RESOURCE_MESSAGE.getString("field.menu.usuarios");
		this.getRequest().getSession().setAttribute("pagActiva",menu_usuarios);
		//******************************************************************
		try{
			//Cogemos el form del jsp
			BuscarUsuariosForm formulario;
			formulario = (BuscarUsuariosForm) form;
			String campoOrden = null;
			
			List<UsuarioBean> arrayUsuarios;
			UsuarioQuery usuarioQuery = new UsuarioQuery();

			if(formulario.getCampo()!= null){	
				try{
					campoOrden = cargarCamposUsuarios(formulario.getCampo());
				}catch(Exception e){	
					logger.error("Error en cargarCamposUsuarios", e);
				}
			}
			
			logger.info("Se va a crear la query"); 
			
			usuarioQuery = crearQueryUsuarios(formulario, campoOrden);

			arrayUsuarios = usuarioManager.buscarUsuarios(usuarioQuery);
			/*INI-TRA042*/
			cargarCentrosgestores(arrayUsuarios);
			/*FIN-TRA042*/
			generarExcel(arrayUsuarios, formulario);
			
		}catch(Exception eGenerico){
			logger.error("Error ExportarExcelUsuariosSpring - doExecute:",eGenerico);
			this.getRequest().setAttribute("descripcionError", RESOURCE_MESSAGE.getString("field.errorGenerico"));
			return "errorGenerico";
		}	
		//**** BORRAR ESTE RETURN DESPUES ****/
		return null;
		//**** BORRAR ESTE RETURN DESPUES ****/
	}
	
	/**
	 * Crear query usuarios.
	 *
	 * @param formulario el formulario
	 * @param campo el campo
	 * @return el usuario query
	 */
	private UsuarioQuery crearQueryUsuarios (BuscarUsuariosForm formulario, String campo)	{

		UsuarioQuery usuarioQuery = new UsuarioQuery();

		String estadoFormulario = formulario.getEstado();
		String idPerfilFormulario = formulario.getIdPerfil();
		String idCentroGestorFormulario = formulario.getIdCentroGestor();
		String nifFormulario = formulario.getNif();
		String loginFormulario = formulario.getLogin();
		String emailFormulario = formulario.getEmail();
		String campoFormulario = campo;
		String direccionFormulario = formulario.getDireccion();

		//Comprobar los filtros para realizar la busqueda

		//Estado
		if (estadoFormulario != null && !estadoFormulario.equals("") ){
			usuarioQuery.setEstado(estadoFormulario.charAt(0)); 
		}
		
		//ID Perfil
		if (idPerfilFormulario != null && !idPerfilFormulario.equals("") ){
			usuarioQuery.addPerfilIn(Integer.valueOf(idPerfilFormulario));
			usuarioQuery.setJoin_perfil(true);
		}
		
		//ID Centro Gestor
		if (idCentroGestorFormulario != null && !idCentroGestorFormulario.equals("") ){
			usuarioQuery.addCentroGestorIn(Integer.valueOf(idCentroGestorFormulario));
			usuarioQuery.setJoin_centroGestor(true);
		}
		
		//Nif
		if (nifFormulario != null && !nifFormulario.equals("") ){
			usuarioQuery.setNif(nifFormulario);
		}
		
		//Login
		if (loginFormulario != null && !loginFormulario.equals("") ){
			usuarioQuery.setLogin(loginFormulario);
		}
		
		//Email
		if (emailFormulario != null && !emailFormulario.equals("") ){
			usuarioQuery.setEmail(emailFormulario);
		}
		
		usuarioQuery.setNifProcesoAutomatico(properties.getProperty("log.usuario.automatico.nif"));
		
		//Ordenación
		if(campoFormulario != null && !"0".equals(campoFormulario) && !"".equals(campoFormulario)){
			// Obtenemos si vamos a ordenar ascendentemente o descendentemente
			OrderType orden = ("up".equals(direccionFormulario) == true ? OrderType.ASC : OrderType.DESC);
			usuarioQuery.addOrder(campoFormulario, orden);
		}
		
		return usuarioQuery;
	}
	
	/**
	 * Generar excel.
	 *
	 * @param arrayUsuarios el array usuarios
	 * @param formulario el formulario
	 * @throws Exception el exception
	 */
	public void generarExcel(List<UsuarioBean> arrayUsuarios,BuscarUsuariosForm formulario) throws Exception{

		int iFila = 1;
		int iCol = 0;
		// Proceso la información y genero el xls
		HSSFWorkbook objLibro = new HSSFWorkbook();
		//Establecer el tipo de fuente 
		HSSFFont fuente = objLibro.createFont();
		fuente.setColor(IndexedColors.BLUE.getIndex());
		fuente.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);

		// Luego creamos el objeto que se encargará de aplicar el estilo a la celda
		HSSFCellStyle estiloCelda = objLibro.createCellStyle();
		estiloCelda.setAlignment(HSSFCellStyle. ALIGN_JUSTIFY);
		estiloCelda.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		estiloCelda.setFont(fuente);
		estiloCelda.setWrapText(false);

		HSSFDataFormat format = objLibro.createDataFormat();
		HSSFCellStyle cellStyle = objLibro.createCellStyle();
		String numberFormat = "###.00";
		cellStyle.setDataFormat(format.getFormat(numberFormat));

		// Se crea la hoja del Libro de la Excel
		HSSFSheet hoja1 = objLibro.createSheet("Hoja Usuarios");
		//PRIMERA FILA QUE CONTENDRÁ LOS NOMBRES DE LOS CAMPOS
		// Proceso la información y genero el xls. Número de la Fila
		HSSFRow fila = hoja1.createRow((short)iFila);

		//Se usa para ajustar el ancho de las columnas
		Sheet sheet = objLibro.getSheetAt(0);

		// Creamos la celda, aplicamos el estilo y definimos el tipo de dato que contendrá la celda
		//NIF //0
		HSSFCell celda = fila.createCell(iCol);	//Número de la columna comenzando por 0
		celda.setCellType(HSSFCell.CELL_TYPE_STRING); //Estilo de la Celda
		celda.setCellStyle(estiloCelda);
		celda.setCellValue("NIF"); //Valor a incluir
		sheet.autoSizeColumn((short)iCol); //ajusta el ancho de la primera columna
		iCol++;
		//NOMBRE //1
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue("NOMBRE");
		sheet.autoSizeColumn((short)iCol); //ajusta el ancho de la segunda columna
		iCol++;
		//PRIMER APELLIDO //2
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue("PRIMER APELLIDO");
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//SEGUNDO APELLIDO //3
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue("SEGUNDO APELLIDO");
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//TELEFONO //4
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue("TELEFONO");
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//EMAIL //5
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue("EMAIL");
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//CENTRO GESTOR //6
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue("CENTRO GESTOR");
		iCol++;
		//PERFIL //7
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue("PERFIL");
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//LOGIN //8
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue("LOGIN");
		sheet.autoSizeColumn((short)iCol);
		iCol++;
		//ESTADO //9
		celda = fila.createCell(iCol);
		celda.setCellType(HSSFCell.CELL_TYPE_STRING);
		celda.setCellStyle(estiloCelda);
		celda.setCellValue("ESTADO");
		sheet.autoSizeColumn((short)iCol);
		iCol++;

		Iterator<UsuarioBean> it = arrayUsuarios.iterator();
		//Recogemos cada solicitud completa para insertar cada fila de la excel 

		while (it.hasNext())
		{
			//Creamos nueva Fila
			iFila++;
			fila = hoja1.createRow((short)iFila);

			UsuarioBean usuarioBean = (UsuarioBean) it.next();
			
			//NIF
			celda = fila.createCell(0);	//Número de la columna comenzando por 0
			celda.setCellType(HSSFCell.CELL_TYPE_STRING); //Tipo de la celda
			if(usuarioBean.getNif() != null)
				celda.setCellValue(usuarioBean.getNif()); //Valor a incluir
			//NOMBRE
			celda = fila.createCell(1);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(usuarioBean.getNombre() != null)
				celda.setCellValue(usuarioBean.getNombre());
			//PRIMER APELLIDO
			celda = fila.createCell(2);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(usuarioBean.getPrimerApellido() != null)
				celda.setCellValue(usuarioBean.getPrimerApellido());
			//SEGUNDO APELLIDO
			celda = fila.createCell(3);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(usuarioBean.getSegundoApellido() != null)
				celda.setCellValue(usuarioBean.getSegundoApellido());
			//TELEFONO
			celda = fila.createCell(4);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(usuarioBean.getTelefono() != null)
				celda.setCellValue(usuarioBean.getTelefono());
			//EMAIL
			celda = fila.createCell(5);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(usuarioBean.getEmail() != null)
				celda.setCellValue(usuarioBean.getEmail());
			//CENTRO GESTOR
			celda = fila.createCell(6);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(usuarioBean.getListaCGconcat() != null)
				celda.setCellValue(usuarioBean.getListaCGconcat());
			//PERFIL
			celda = fila.createCell(7);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(usuarioBean.getDesPerfil() != null)
				celda.setCellValue(usuarioBean.getDesPerfil());
			//LOGIN
			celda = fila.createCell(8);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(usuarioBean.getLogin() != null)
				celda.setCellValue(usuarioBean.getLogin());
			//ESTADO
			celda = fila.createCell(9);
			celda.setCellType(HSSFCell.CELL_TYPE_STRING);
			if(usuarioBean.getEstado() != null) {
				if (usuarioBean.getEstado() == '1')
					celda.setCellValue("Activo");
				else
					celda.setCellValue("Inactivo");
			}	
		}
			
		try{
			ajustaColumnas(hoja1);
			
			// Volcamos la informacion a un archivo.
			String sNombreFichero= "";
			sNombreFichero = "Usuarios";

			String sExtension = ".xls";

			javax.servlet.ServletOutputStream stream;
			stream = this.getResponse().getOutputStream();

			String contentDisposition = "attachment; filename=\"" + sNombreFichero + sExtension + "\"";

			HttpServletResponse resp = this.getResponse();

			resp.setContentType("application/vnd.ms-excel");

			resp.setHeader("Content-Disposition", contentDisposition);
			
			objLibro.write(stream);

			stream.flush();
			stream.close();
	
		}catch (FileNotFoundException ex)
		{
			logger.error("Error ExportarExcelSolicitudesSpring - no se ha encontrado el fichero :",ex);
			
		}catch (IOException ex)
		{
			logger.error("Error ExportarExcelSolicitudesSpring - no se ha encontrado el fichero :",ex);
		}
	}

	/*INI-TRA042*/
	private void ajustaColumnas(HSSFSheet hoja1) {
		final short numeroColumnas = hoja1.getRow(1).getLastCellNum();
		for (int i = 0; i < numeroColumnas; i++) {
			hoja1.autoSizeColumn(i);
		}
	}
	
	private void cargarCentrosgestores(List<UsuarioBean> usuarios) {
		for(UsuarioBean usuario: usuarios) {
			List<CentroGestorBean> listaCentrosGestores = usuarioCentrogestorManager.buscarCentrosGestoresByIdUsuario(usuario.getId());
			String listaCGconcat="";
			for(int i=0; i<listaCentrosGestores.size();i++) {
				if(i<listaCentrosGestores.size()-1) {
					listaCGconcat+=listaCentrosGestores.get(i).getDescripcion()+"\n";
				} else {
					listaCGconcat+=listaCentrosGestores.get(i).getDescripcion();
				}
			}
			usuario.setListaCGconcat(listaCGconcat);
		}
	}
	/*FIN-TRA042*/
	
	/**
	 * Obtiene el usuario manager.
	 *
	 * @return convocatoriaManager ConvocatoriasManager
	 */
	public UsuarioManager getUsuarioManager() {
		return usuarioManager;
	}

	/**
	 * Establece el usuario manager.
	 *
	 * @param usuarioManager el nuevo usuario manager
	 */
	public void setUsuarioManager(UsuarioManager usuarioManager) {
		this.usuarioManager = usuarioManager;
	}
	
	/**
	 * Cargar campos usuarios.
	 *
	 * @param campo el campo
	 * @return el string
	 */
	private String cargarCamposUsuarios(String campo) {
		String auxCampo=null;
		int codCampo = 0;
		try{
		codCampo = Integer.parseInt(campo);
		switch(codCampo){
		//Los campos de ordenacion del jsp
			case 1:auxCampo = UsuarioQuery.ID;break;
			case 2:auxCampo = UsuarioQuery.NIF;break;
			case 3:auxCampo = UsuarioQuery.LOGIN;break;
			case 4:auxCampo = UsuarioQuery.NOMBRE;break;
			case 5:auxCampo = UsuarioQuery.PRIMERAPELLIDO;break;
			case 6:auxCampo = UsuarioQuery.SEGUNDOAPELLIDO;break;
			case 7:auxCampo = UsuarioQuery.CENTROGESTOR;break;
			case 8:auxCampo = UsuarioQuery.PERFIL;break;
			case 9:auxCampo = UsuarioQuery.ESTADO;break;
			default:break;
		}	
		}catch(Exception e){
			logger.error("Error ExportarExcelUsuariosSpring - cargarCampos - parsear campo:"+ codCampo ,e);
		}

		return auxCampo;
	}

	/*INI-TRA042*/
	/**
	 * @return the usuarioCentrogestorManager
	 */
	public UsuarioCentrogestorManager getUsuarioCentrogestorManager() {
		return usuarioCentrogestorManager;
	}

	/**
	 * @param usuarioCentrogestorManager the usuarioCentrogestorManager to set
	 */
	public void setUsuarioCentrogestorManager(UsuarioCentrogestorManager usuarioCentrogestorManager) {
		this.usuarioCentrogestorManager = usuarioCentrogestorManager;
	}
	/*FIN-TRA042*/
	
}