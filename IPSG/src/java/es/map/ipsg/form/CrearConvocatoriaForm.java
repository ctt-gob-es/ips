package es.map.ipsg.form;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import es.map.ips.common.spring.SpringErrors;
import es.map.ips.common.spring.SpringForm;
import es.map.ips.common.spring.SpringMapping;
import es.map.ips.common.spring.SpringMessage;
import es.map.ipsg.bean.CentroGestorBean;
import es.map.ipsg.util.Constantes;
import es.map.ipsg.util.Validacion;

/**
 * El Class CrearConvocatoriaForm.
 */
public class CrearConvocatoriaForm extends SpringForm {
	
	/** el centro gestor. */
	String centroGestor;
	
	/** el ds centro gestor. */
	String dsCentroGestor;
	
	/** el centro gestor anterior. */
	String centroGestorAnterior;
	
	/** el cuerpo escala. */
	String cuerpoEscala;
	
	/** el ds cuerpo escala. */
	String dsCuerpoEscala;
	
	/** el num plazas. */
	String numPlazas;
	
	/** el num plazas disc. */
	String numPlazasDisc;
	
	/** el forma acceso. */
	String formaAcceso;
	
	/** el codigo forma acceso. */
	String codigoFormaAcceso;
	
	/** el importe. */
	String importe;
	
	/** el dirigido A. */
	String dirigidoA;
	
	/** el tipo documentacion. */
	String tipoDocumentacion;
	
	/** el titulos. */
	String[] titulos;
	
	/** el titulos seleccionados. */
	String[] titulosSeleccionados;
	
	/** el titulos seleccionados input. */
	String titulosSeleccionadosInput;
	
	/** el accion. */
	String accion;
	
	/** el id convocatoria. */
	String idConvocatoria;
	
	/** el ejercicio. */
	String ejercicio;
	
	/** el estado. */
	String estado;
	
	/** el siglas ministerio boe. */
	String siglasMinisterioBoe;
	
	/** el anio boe. */
	String anioBoe;	
	
	/** el fecha boe. */
	String fechaBoe;
	
	/** el fecha inicio. */
	String fechaInicio;
	
	/** el fecha termino. */
	String fechaTermino;
	
	/** el fecha ini sub. */
	String fechaIniSub;
	
	/** el fecha fin sub. */
	String fechaFinSub;
	
	/** el provincia examen. */
	String[] provinciaExamen;
	
	/** el provincia examen seleccionados. */
	String[] provinciaExamenSeleccionados;
	
	/** el motivos exencion. */
	String[] motivosExencion;
	
	/** el motivos exencion seleccionados. */
	String[] motivosExencionSeleccionados;
	
	/** el especialidades. */
	String[] especialidades;
	
	/** el especialidades seleccionados. */
	String[] especialidadesSeleccionados;
	
	/** el otros titulos. */
	String[] otrosTitulos;
	
	/** el otros titulos seleccionados. */
	String[] otrosTitulosSeleccionados;
	
	/** el discapacidad. */
	String[] discapacidad;
	
	/** el discapacidad seleccionados. */
	String[] discapacidadSeleccionados;
	
	/** el datos propios. */
	String[] datosPropios;
	
	/** el datos propios seleccionados. */
	String[] datosPropiosSeleccionados;	
	
	/** el parametro. */
	//Variable para listar
	String parametro;
	
	/** el parametro 2. */
	String parametro2;
	
	/** el campo. */
	//Variables Paginacion
	private String campo;
	
	/** el direccion. */
	private String direccion;
	
	/** el pagina actual. */
	private String paginaActual;
	
	/** el paginas totales. */
	private String paginasTotales;
	
	/** el submit. */
	private String submit;
	
	/** el num registro. */
	private String numRegistro;
	
	/** el mensaje. */
	private String mensaje;
	
	/** el cambios. */
	private String cambios;
	
	/** el modelo asociado. */
	private String modeloAsociado;

	/** el ministerio siglas aux. */
	//Variable con las siglas del ministerio asociado al centro gestor
	String ministerioSiglasAux;
	
	/** el ministerio convocante. */
	String ministerioConvocante;

	/** el fecha inicio inhabil. */
	private String fechaInicioInhabil;
	
	/** el fecha fin inhabil. */
	private String fechaFinInhabil;
	
	/** el modificado fecha inhabil. */
	private String modificadoFechaInhabil;
	
	/** el presencial. */
	private boolean presencial;
	
	/** el otros titulos flag. */
	private boolean otrosTitulosFlag;
	
	/** el discapacidad flag. */
	private boolean discapacidadFlag;
	
	/** el datos propios flag. */
	private boolean datosPropiosFlag;
	
	/** enlace informativo. */
	private String enlace;
	
	/** el ocultar datos propios. */
	private boolean ocultarDatosPropios;
	
	/*INI-TRA042*/
	/** el lista centros gestores. */
	private List<CentroGestorBean> listaCentrosGestores = new ArrayList<CentroGestorBean>();
	/*FIN-TRA042*/
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(CrearConvocatoriaForm.class);
	
	/** La constante STRING_ERRORCUERPOESCALA. */
	private static final String STRING_ERRORCUERPOESCALA = "errorCuerpoEscala";
	
	/** La constante STRING_ERRORFORMAACCESO. */
	private static final String STRING_ERRORFORMAACCESO = "errorFormaAcceso";
	
	/** La constante STRING_ERRORTIPODOCUMENTACION. */
	private static final String STRING_ERRORTIPODOCUMENTACION = "errorTipoDocumentacion";
	
	/** La constante STRING_ERRORCODIGOFORMAACCESO. */
	private static final String STRING_ERRORCODIGOFORMAACCESO = "errorCodigoFormaAcceso";
	
	/** La constante STRING_ERRORCENTROGESTOR. */
	private static final String STRING_ERRORCENTROGESTOR = "errorCentroGestor";
	
	/** La constante STRING_ERRORMINISTERIOCONVOCANTE. */
	private static final String STRING_ERRORMINISTERIOCONVOCANTE = "errorMinisterioConvocante";
	
	/** La constante STRING_ERRORNUMPLAZAS. */
	private static final String STRING_ERRORNUMPLAZAS = "errorNumPlazas";
	
	/** La constante STRING_ERRORNUMPLAZASNUMERICO. */
	private static final String STRING_ERRORNUMPLAZASNUMERICO = "errorNumPlazasNumerico";
	
	/** La constante STRING_ERRORNUMPLAZASDISC. */
	private static final String STRING_ERRORNUMPLAZASDISC = "errorNumPlazasDisc";
	
	/** La constante STRING_ERRORNUMPLAZASDISCNUMERICO. */
	private static final String STRING_ERRORNUMPLAZASDISCNUMERICO = "errorNumPlazasDiscNumerico";
	
	/** La constante STRING_ERRORNUMPLAZASMENORNUMPLAZASDISC. */
	private static final String STRING_ERRORNUMPLAZASMENORNUMPLAZASDISC = "errorNumPlazas<numPlazasDisc";
	
	/** La constante STRING_ERRORIMPORTE. */
	private static final String STRING_ERRORIMPORTE = "errorImporte";
	
	/** La constante STRING_ERRORDIRIGIDOA. */
	private static final String STRING_ERRORDIRIGIDOA = "errorDirigidoA";
	
	/** La constante STRING_ERRORTITULOSSELECCIONADOS. */
	private static final String STRING_ERRORTITULOSSELECCIONADOS = "errorTitulosSeleccionados";
	
	/** La constante STRING_ERROROTROSTITULOSSELECCIONADOS. */
	private static final String STRING_ERROROTROSTITULOSSELECCIONADOS = "errorOtrosTitulosSeleccionados";
	
	/** La constante STRING_ERRORDISCAPACIDADSELECCIONADOS. */
	private static final String STRING_ERRORDISCAPACIDADSELECCIONADOS = "errorOtrosTitulosSeleccionados";
	
	/** La constante STRING_ERRORDATOSPROPIOSSELECCIONADOS. */
	private static final String STRING_ERRORDATOSPROPIOSSELECCIONADOS = "errorDatosPropiosSeleccionados";
	
	
	/**
	 * Obtiene el modelo asociado.
	 *
	 * @return el modelo asociado
	 */
	public String getModeloAsociado() {
		return modeloAsociado;
	}

	/**
	 * Establece el modelo asociado.
	 *
	 * @param modeloAsociado el nuevo modelo asociado
	 */
	public void setModeloAsociado(String modeloAsociado) {
		this.modeloAsociado = modeloAsociado;
	}
	
	/**
	 * Obtiene el centro gestor.
	 *
	 * @return el centro gestor
	 */
	public String getCentroGestor() {
		return centroGestor;
	}

	/**
	 * Establece el centro gestor.
	 *
	 * @param centroGestor el nuevo centro gestor
	 */
	public void setCentroGestor(String centroGestor) {
		this.centroGestor = centroGestor;
	}

	/**
	 * Obtiene el cuerpo escala.
	 *
	 * @return el cuerpo escala
	 */
	public String getCuerpoEscala() {
		return cuerpoEscala;
	}

	/**
	 * Establece el cuerpo escala.
	 *
	 * @param cuerpoEscala el nuevo cuerpo escala
	 */
	public void setCuerpoEscala(String cuerpoEscala) {
		this.cuerpoEscala = cuerpoEscala;
	}

	/**
	 * Obtiene el num plazas.
	 *
	 * @return el num plazas
	 */
	public String getNumPlazas() {
		return numPlazas;
	}

	/**
	 * Establece el num plazas.
	 *
	 * @param numPlazas el nuevo num plazas
	 */
	public void setNumPlazas(String numPlazas) {
		this.numPlazas = numPlazas;
	}

	/**
	 * Obtiene el num plazas disc.
	 *
	 * @return el num plazas disc
	 */
	public String getNumPlazasDisc() {
		return numPlazasDisc;
	}

	/**
	 * Establece el num plazas disc.
	 *
	 * @param numPlazasDisc el nuevo num plazas disc
	 */
	public void setNumPlazasDisc(String numPlazasDisc) {
		this.numPlazasDisc = numPlazasDisc;
	}

	/**
	 * Obtiene el forma acceso.
	 *
	 * @return el forma acceso
	 */
	public String getFormaAcceso() {
		return formaAcceso;
	}

	/**
	 * Establece el forma acceso.
	 *
	 * @param formaAcceso el nuevo forma acceso
	 */
	public void setFormaAcceso(String formaAcceso) {
		this.formaAcceso = formaAcceso;
	}

	/**
	 * Obtiene el importe.
	 *
	 * @return el importe
	 */
	public String getImporte() {
		return importe;
	}

	/**
	 * Establece el importe.
	 *
	 * @param importe el nuevo importe
	 */
	public void setImporte(String importe) {
		this.importe = importe;
	}

	/**
	 * Obtiene el dirigido A.
	 *
	 * @return el dirigido A
	 */
	public String getDirigidoA() {
		return dirigidoA;
	}

	/**
	 * Establece el dirigido A.
	 *
	 * @param dirigidoA el nuevo dirigido A
	 */
	public void setDirigidoA(String dirigidoA) {
		this.dirigidoA = dirigidoA;
	}

	/**
	 * Obtiene el tipo documentacion.
	 *
	 * @return el tipo documentacion
	 */
	public String getTipoDocumentacion() {
		return tipoDocumentacion;
	}

	/**
	 * Establece el tipo documentacion.
	 *
	 * @param tipoDocumentacion el nuevo tipo documentacion
	 */
	public void setTipoDocumentacion(String tipoDocumentacion) {
		this.tipoDocumentacion = tipoDocumentacion;
	}

	/**
	 * Obtiene el titulos.
	 *
	 * @return el titulos
	 */
	public String[] getTitulos() {
		return titulos;
	}

	/**
	 * Establece el titulos.
	 *
	 * @param titulos el nuevo titulos
	 */
	public void setTitulos(String[] titulos) {
		this.titulos = titulos;
	}

	/**
	 * Obtiene el titulos seleccionados.
	 *
	 * @return el titulos seleccionados
	 */
	public String[] getTitulosSeleccionados() {
		return titulosSeleccionados;
	}

	/**
	 * Establece el titulos seleccionados.
	 *
	 * @param titulosSeleccionados el nuevo titulos seleccionados
	 */
	public void setTitulosSeleccionados(String[] titulosSeleccionados) {
		this.titulosSeleccionados = titulosSeleccionados;
	}
	
	/**
	 * Obtiene el titulos seleccionados input.
	 *
	 * @return the titulosSeleccionadosInput
	 */
	public String getTitulosSeleccionadosInput() {
		return titulosSeleccionadosInput;
	}

	/**
	 * Establece el titulos seleccionados input.
	 *
	 * @param titulosSeleccionadosInput the titulosSeleccionadosInput to set
	 */
	public void setTitulosSeleccionadosInput(String titulosSeleccionadosInput) {
		this.titulosSeleccionadosInput = titulosSeleccionadosInput;
	}

	/**
	 * Obtiene el accion.
	 *
	 * @return el accion
	 */
	public String getAccion() {
		return accion;
	}

	/**
	 * Establece el accion.
	 *
	 * @param accion el nuevo accion
	 */
	public void setAccion(String accion) {
		this.accion = accion;
	}

	/**
	 * Obtiene el id convocatoria.
	 *
	 * @return el id convocatoria
	 */
	public String getIdConvocatoria() {
		return idConvocatoria;
	}

	/**
	 * Establece el id convocatoria.
	 *
	 * @param idConvocatoria el nuevo id convocatoria
	 */
	public void setIdConvocatoria(String idConvocatoria) {
		this.idConvocatoria = idConvocatoria;
	}
	

	/**
	 * Obtiene el ejercicio.
	 *
	 * @return el ejercicio
	 */
	public String getEjercicio() {
		return ejercicio;
	}


	/**
	 * Establece el ejercicio.
	 *
	 * @param ejercicio el nuevo ejercicio
	 */
	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
	}

	/**
	 * Obtiene el fecha boe.
	 *
	 * @return el fecha boe
	 */
	public String getFechaBoe() {
		return fechaBoe;
	}

	/**
	 * Establece el fecha boe.
	 *
	 * @param fechaBoe el nuevo fecha boe
	 */
	public void setFechaBoe(String fechaBoe) {
		this.fechaBoe = fechaBoe;
	}

	/**
	 * Obtiene el fecha inicio.
	 *
	 * @return el fecha inicio
	 */
	public String getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Establece el fecha inicio.
	 *
	 * @param fechaInicio el nuevo fecha inicio
	 */
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * Obtiene el fecha termino.
	 *
	 * @return el fecha termino
	 */
	public String getFechaTermino() {
		return fechaTermino;
	}

	/**
	 * Establece el fecha termino.
	 *
	 * @param fechaTermino el nuevo fecha termino
	 */
	public void setFechaTermino(String fechaTermino) {
		this.fechaTermino = fechaTermino;
	}

	/**
	 * Obtiene el provincia examen.
	 *
	 * @return el provincia examen
	 */
	public String[] getProvinciaExamen() {
		return provinciaExamen;
	}

	/**
	 * Establece el provincia examen.
	 *
	 * @param provinciaExamen el nuevo provincia examen
	 */
	public void setProvinciaExamen(String[] provinciaExamen) {
		this.provinciaExamen = provinciaExamen;
	}

	/**
	 * Obtiene el provincia examen seleccionados.
	 *
	 * @return el provincia examen seleccionados
	 */
	public String[] getProvinciaExamenSeleccionados() {
		return provinciaExamenSeleccionados;
	}

	/**
	 * Establece el provincia examen seleccionados.
	 *
	 * @param provinciaExamenSeleccionados el nuevo provincia examen seleccionados
	 */
	public void setProvinciaExamenSeleccionados(
			String[] provinciaExamenSeleccionados) {
		this.provinciaExamenSeleccionados = provinciaExamenSeleccionados;
	}

	/**
	 * Obtiene el motivos exencion.
	 *
	 * @return el motivos exencion
	 */
	public String[] getMotivosExencion() {
		return motivosExencion;
	}

	/**
	 * Establece el motivos exencion.
	 *
	 * @param motivosExencion el nuevo motivos exencion
	 */
	public void setMotivosExencion(String[] motivosExencion) {
		this.motivosExencion = motivosExencion;
	}

	/**
	 * Obtiene el motivos exencion seleccionados.
	 *
	 * @return el motivos exencion seleccionados
	 */
	public String[] getMotivosExencionSeleccionados() {
		return motivosExencionSeleccionados;
	}

	/**
	 * Establece el motivos exencion seleccionados.
	 *
	 * @param motivosExencionSeleccionados el nuevo motivos exencion seleccionados
	 */
	public void setMotivosExencionSeleccionados(
			String[] motivosExencionSeleccionados) {
		this.motivosExencionSeleccionados = motivosExencionSeleccionados;
	}

	/**
	 * Obtiene el especialidades.
	 *
	 * @return el especialidades
	 */
	public String[] getEspecialidades() {
		return especialidades;
	}

	/**
	 * Establece el especialidades.
	 *
	 * @param especialidades el nuevo especialidades
	 */
	public void setEspecialidades(String[] especialidades) {
		this.especialidades = especialidades;
	}

	/**
	 * Obtiene el especialidades seleccionados.
	 *
	 * @return el especialidades seleccionados
	 */
	public String[] getEspecialidadesSeleccionados() {
		return especialidadesSeleccionados;
	}

	/**
	 * Establece el especialidades seleccionados.
	 *
	 * @param especialidadesSeleccionados el nuevo especialidades seleccionados
	 */
	public void setEspecialidadesSeleccionados(
			String[] especialidadesSeleccionados) {
		this.especialidadesSeleccionados = especialidadesSeleccionados;
	}

	/* (non-Javadoc)
	 * @see es.map.ips.common.spring.SpringForm#validate(es.map.ips.common.spring.SpringMapping, javax.servlet.http.HttpServletRequest)
	 */
	public SpringErrors validate(SpringMapping mapping,
			HttpServletRequest request) {
		logger.info("Validando");
		SpringErrors SpringErrors = new SpringErrors();
		Validacion validacion = new Validacion();
		logger.info("Action Form: "+accion);

		if (accion.equalsIgnoreCase("importe")) {
			if (cuerpoEscala == null || cuerpoEscala.equalsIgnoreCase("0")) {
				request.setAttribute(STRING_ERRORCUERPOESCALA, STRING_ERRORCUERPOESCALA);
				SpringErrors.add("dsErrorCuerpoEscala", new SpringMessage(
						"field.convocatoria.errores.cuerpoEscalaRequerido"));
			}
			if (formaAcceso == null || formaAcceso.equalsIgnoreCase("0")) {
				request.setAttribute(STRING_ERRORFORMAACCESO, STRING_ERRORFORMAACCESO);
				SpringErrors.add("dsErrorFormaAcceso", new SpringMessage(
						"field.convocatoria.errores.formaAccesoRequerido"));
			}
		}
		if (accion.equalsIgnoreCase("modificar")) {
			if (tipoDocumentacion == null
					|| tipoDocumentacion.equalsIgnoreCase("")) {
				request.setAttribute(STRING_ERRORTIPODOCUMENTACION,
						STRING_ERRORTIPODOCUMENTACION);
				SpringErrors.add("dsErrorTipoDocumentacion", new SpringMessage(
						"field.convocatoria.errores.tipoDocumentoRequerido"));
			}
			
			if (codigoFormaAcceso == null || codigoFormaAcceso.trim().equalsIgnoreCase("")) {
				request.getSession().setAttribute(STRING_ERRORCODIGOFORMAACCESO, STRING_ERRORCODIGOFORMAACCESO);
				request.setAttribute(STRING_ERRORCODIGOFORMAACCESO, STRING_ERRORCODIGOFORMAACCESO);
				SpringErrors.add("dsErrorCodigoFormaAcceso", new SpringMessage(
						"field.convocatoria.errores.codigoFormaAccesoRequerido"));
			}
			else {
				request.getSession().removeAttribute(STRING_ERRORCODIGOFORMAACCESO);
			}	
		}
		if (accion.equalsIgnoreCase("alta")) {
			
			if (centroGestor == null || centroGestor.equalsIgnoreCase("")) {
				request.getSession().setAttribute(STRING_ERRORCENTROGESTOR, STRING_ERRORCENTROGESTOR);
				request.setAttribute(STRING_ERRORCENTROGESTOR, STRING_ERRORCENTROGESTOR);
				SpringErrors.add("dsErrorCentroGestor", new SpringMessage(
						"field.convocatoria.errores.centroGestorRequerido"));
			}
			else {
				request.getSession().removeAttribute(STRING_ERRORCENTROGESTOR);
			}
			if (cuerpoEscala == null || cuerpoEscala.equalsIgnoreCase("")) {
				request.getSession().setAttribute(STRING_ERRORCUERPOESCALA, STRING_ERRORCUERPOESCALA);
				request.setAttribute(STRING_ERRORCUERPOESCALA, STRING_ERRORCUERPOESCALA);
				SpringErrors.add("dsErrorCuerpoEscala", new SpringMessage(
						"field.convocatoria.errores.cuerpoEscalaRequerido"));
			}
			else {
				request.getSession().removeAttribute(STRING_ERRORCUERPOESCALA);
			}
			
			if (ministerioConvocante == null || ministerioConvocante.equalsIgnoreCase("")) {
				request.getSession().setAttribute(STRING_ERRORMINISTERIOCONVOCANTE, STRING_ERRORMINISTERIOCONVOCANTE);
				request.setAttribute(STRING_ERRORMINISTERIOCONVOCANTE, STRING_ERRORMINISTERIOCONVOCANTE);
				SpringErrors.add("dsErrorMinisterioConvocante", new SpringMessage(
						"field.convocatoria.errores.MinisterioConvocanteRequerido"));
			}
			else {
				request.getSession().removeAttribute(STRING_ERRORMINISTERIOCONVOCANTE);
			}
			
			
			if (numPlazas == null || numPlazas.equalsIgnoreCase("")) {
				request.getSession().setAttribute(STRING_ERRORNUMPLAZAS, STRING_ERRORNUMPLAZAS);
				request.setAttribute(STRING_ERRORNUMPLAZAS, STRING_ERRORNUMPLAZAS);
				SpringErrors.add("dsErrorNumPlazas", new SpringMessage(
						"field.convocatoria.errores.numPlazasRequerido"));
			}
			else {
				request.getSession().removeAttribute(STRING_ERRORNUMPLAZAS);
			}
			if(numPlazas != null && !"".equals(numPlazas)){
				try{
					
					request.getSession().removeAttribute(STRING_ERRORNUMPLAZASNUMERICO);
				}catch(Exception e){
					request.getSession().setAttribute(STRING_ERRORNUMPLAZASNUMERICO, STRING_ERRORNUMPLAZASNUMERICO);
					request.setAttribute(STRING_ERRORNUMPLAZAS, STRING_ERRORNUMPLAZAS);
					SpringErrors.add("dsErrorNumPlazas", new SpringMessage(
							"field.convocatoria.errores.numPlazasNumerico"));
				}
			}
			
			if (numPlazasDisc == null || numPlazasDisc.equalsIgnoreCase("")) {
				request.getSession().setAttribute(STRING_ERRORNUMPLAZASDISC, STRING_ERRORNUMPLAZASDISC);
				request.setAttribute(STRING_ERRORNUMPLAZASDISC,STRING_ERRORNUMPLAZASDISC);
				SpringErrors.add("dsErrorNumPlazasDisc",new SpringMessage(
										"field.convocatoria.errores.NumPlazasDiscapacitadosRequerido"));
			}
			else {
				request.getSession().removeAttribute(STRING_ERRORNUMPLAZASDISC);
			}
			if(numPlazasDisc != null && !"".equals(numPlazasDisc)){
				try{
					
					request.getSession().removeAttribute(STRING_ERRORNUMPLAZASDISCNUMERICO);
				}catch(Exception e){
					request.getSession().setAttribute(STRING_ERRORNUMPLAZASDISCNUMERICO, STRING_ERRORNUMPLAZASDISCNUMERICO);
					request.setAttribute(STRING_ERRORNUMPLAZASDISC, STRING_ERRORNUMPLAZASDISC);
					SpringErrors.add("dsErrorNumPlazasDisc", new SpringMessage(
							"field.convocatoria.errores.numPlazasDiscapacitadosNumerico"));
				}
			}

			if((numPlazas != null && !(numPlazas.equalsIgnoreCase("")))&&
					(numPlazasDisc != null && !(numPlazasDisc.equalsIgnoreCase("")))){
				try{
				if(Integer.parseInt(numPlazas)<Integer.parseInt(numPlazasDisc)){
					request.getSession().setAttribute(STRING_ERRORNUMPLAZASMENORNUMPLAZASDISC, STRING_ERRORNUMPLAZASMENORNUMPLAZASDISC);
					request.setAttribute(STRING_ERRORNUMPLAZASMENORNUMPLAZASDISC,STRING_ERRORNUMPLAZASMENORNUMPLAZASDISC);
					SpringErrors.add("dsErrorNumPlazas<numPlazasDisc",new SpringMessage(
											"field.convocatoria.errores.numPlazas<numPlazasDisc"));
				}
				else {
					request.getSession().removeAttribute(STRING_ERRORNUMPLAZASMENORNUMPLAZASDISC);
				}
				}catch(Exception e){}				
			}			

			if (formaAcceso == null || formaAcceso.equalsIgnoreCase("0")) {
				request.getSession().setAttribute(STRING_ERRORFORMAACCESO, STRING_ERRORFORMAACCESO);
				request.setAttribute(STRING_ERRORFORMAACCESO, STRING_ERRORFORMAACCESO);
				SpringErrors.add("dsErrorFormaAcceso", new SpringMessage(
						"field.convocatoria.errores.formaAccesoRequerido"));
			}
			else {
				request.getSession().removeAttribute(STRING_ERRORFORMAACCESO);
			}
			
			if (codigoFormaAcceso == null || codigoFormaAcceso.trim().equalsIgnoreCase("")) {
				request.getSession().setAttribute(STRING_ERRORCODIGOFORMAACCESO, STRING_ERRORCODIGOFORMAACCESO);
				request.setAttribute(STRING_ERRORCODIGOFORMAACCESO, STRING_ERRORCODIGOFORMAACCESO);
				SpringErrors.add("dsErrorCodigoFormaAcceso", new SpringMessage(
						"field.convocatoria.errores.codigoFormaAccesoRequerido"));
			}
			else {
				request.getSession().removeAttribute(STRING_ERRORCODIGOFORMAACCESO);
			}

			if (importe == null || importe.equalsIgnoreCase("")) {
				request.getSession().setAttribute(STRING_ERRORIMPORTE, STRING_ERRORIMPORTE);
				request.setAttribute(STRING_ERRORIMPORTE, STRING_ERRORIMPORTE);
				SpringErrors.add("dsErrorImporte", new SpringMessage(
						"field.convocatoria.errores.importeRequerido"));
			}
			else {
				request.getSession().removeAttribute(STRING_ERRORIMPORTE);
			}

			if (dirigidoA == null || dirigidoA.equalsIgnoreCase("")) {
				request.getSession().setAttribute(STRING_ERRORDIRIGIDOA, STRING_ERRORDIRIGIDOA);
				request.setAttribute(STRING_ERRORDIRIGIDOA, STRING_ERRORDIRIGIDOA);
				SpringErrors.add("dsErrorDirigidoA", new SpringMessage(
						"field.convocatoria.errores.dirigidoARequerido"));
			}
			else {
				request.getSession().removeAttribute(STRING_ERRORDIRIGIDOA);
			}

			if (tipoDocumentacion == null
					|| tipoDocumentacion.equalsIgnoreCase("")) {
				request.getSession().setAttribute(STRING_ERRORTIPODOCUMENTACION, STRING_ERRORTIPODOCUMENTACION);
				request.setAttribute(STRING_ERRORTIPODOCUMENTACION,
						STRING_ERRORTIPODOCUMENTACION);
				SpringErrors.add("dsErrorTipoDocumentacion", new SpringMessage(
						"field.convocatoria.errores.tipoDocumentoRequerido"));
			}
			else {
				request.getSession().removeAttribute(STRING_ERRORTIPODOCUMENTACION);
			}

			if (titulosSeleccionadosInput == null || "".equals(titulosSeleccionadosInput) ) {
				request.getSession().setAttribute(STRING_ERRORTITULOSSELECCIONADOS, STRING_ERRORTITULOSSELECCIONADOS);
				request.setAttribute(STRING_ERRORTITULOSSELECCIONADOS,
						STRING_ERRORTITULOSSELECCIONADOS);
				SpringErrors
						.add(
								"dsErrorTitulosSeleccionados",
								new SpringMessage(
										"field.convocatoria.errores.titulosExigidosRequerido"));
			}
			else {
				request.getSession().removeAttribute(STRING_ERRORTITULOSSELECCIONADOS);
			}

			//Comprobacion si se marca el check otros titulos
			if (otrosTitulosFlag && (otrosTitulosSeleccionados == null || otrosTitulosSeleccionados.length <= 0) ) {
				request.getSession().setAttribute(STRING_ERROROTROSTITULOSSELECCIONADOS, STRING_ERROROTROSTITULOSSELECCIONADOS);
				request.setAttribute(STRING_ERROROTROSTITULOSSELECCIONADOS, STRING_ERROROTROSTITULOSSELECCIONADOS);
				SpringErrors.add("dsErrorOtrosTitulosSeleccionados",new SpringMessage("field.convocatoria.errores.otrosTitulosExigidosRequerido"));
			}
			else {
				request.getSession().removeAttribute(STRING_ERROROTROSTITULOSSELECCIONADOS);
			}
			//Comprobacion si se marca el check discapacidad
			if (discapacidadFlag && (discapacidadSeleccionados == null || discapacidadSeleccionados.length <= 0) ) {
				request.getSession().setAttribute(STRING_ERRORDISCAPACIDADSELECCIONADOS, STRING_ERRORDISCAPACIDADSELECCIONADOS);
				request.setAttribute(STRING_ERRORDISCAPACIDADSELECCIONADOS, STRING_ERRORDISCAPACIDADSELECCIONADOS);
				SpringErrors.add("dsErrorDiscapacidadSeleccionados",new SpringMessage("field.convocatoria.errores.discapacidadExigidosRequerido"));
			}
			else {
				request.getSession().removeAttribute(STRING_ERRORDISCAPACIDADSELECCIONADOS);
			}
			//Comprobacion si se marca el check Datos propios
			if ((!datosPropiosFlag && !ocultarDatosPropios) || (datosPropiosFlag && !ocultarDatosPropios)) {
				if (datosPropiosFlag && (datosPropiosSeleccionados == null || datosPropiosSeleccionados.length <= 0) ) {
					request.getSession().setAttribute(STRING_ERRORDATOSPROPIOSSELECCIONADOS, STRING_ERRORDATOSPROPIOSSELECCIONADOS);
					request.setAttribute(STRING_ERRORDATOSPROPIOSSELECCIONADOS, STRING_ERRORDATOSPROPIOSSELECCIONADOS);
					SpringErrors.add("dsErrorDatosPropiosSeleccionados",new SpringMessage("field.convocatoria.errores.datosPropiosExigidosRequerido"));
				} else {
					request.getSession().removeAttribute(STRING_ERRORDATOSPROPIOSSELECCIONADOS);
				}
			}

			SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
			boolean errorFormatoFechaBoe = false;
			boolean errorFormatoFechaInicio = false;
			boolean errorFormatoFechaTermino = false;
			boolean errorFormatoFechaInicioSub = false;
			boolean errorFormatoFechaTerminoSub = false;			
			Date dFechaInicio = null;
			Date dFechaBoe = null;
			Date dFechaTermino = null;
			Date dFechaIniSub = null;
			Date dFechaFinSub = null;
			
			if (fechaBoe!=null&&!(fechaBoe.equalsIgnoreCase(""))){				
				try {
					dFechaBoe = formatoDelTexto.parse(fechaBoe);
					errorFormatoFechaBoe = !validacion.isFechaValida(fechaBoe);
					if(errorFormatoFechaBoe) throw new Exception();
				} catch (Exception ex) {
					errorFormatoFechaBoe = true;
					logger.warn("Error parseando la Fecha Inicio: "+fechaBoe);
					request.setAttribute("errorFechaBoeFormato", "errorFechaBoeFormato");
					SpringErrors.add("dsErrorFechaBoeFormato", new SpringMessage(
							"field.convocatoria.errores.fechaBoeNoValido"));
				}
			}
			
			if(fechaInicio!=null&&!(fechaInicio.equalsIgnoreCase(""))){				
				try {
					dFechaInicio = formatoDelTexto.parse(fechaInicio);
					errorFormatoFechaInicio = !validacion.isFechaValida(fechaInicio);
					if(errorFormatoFechaInicio) throw new Exception();
				} catch (Exception ex) {
					errorFormatoFechaInicio = true;
					logger.warn("Error parseando la Fecha Inicio: "+fechaInicio);
					request.setAttribute("errorFechaInicioFormato", "errorFechaInicioFormato");
					SpringErrors.add("dsErrorFechaInicioFormato", new SpringMessage(
							"field.convocatoria.errores.fechaInicioNoValido"));
				}
			}
			
			if (fechaTermino != null && !(fechaTermino.equalsIgnoreCase(""))){
				try {
					dFechaTermino= formatoDelTexto.parse(fechaTermino);
					errorFormatoFechaTermino = !validacion.isFechaValida(fechaTermino);
					if(errorFormatoFechaTermino) throw new Exception();
				} catch (Exception ex) {
					errorFormatoFechaTermino = true;
					logger.warn("Error parseando la Fecha Termino: "+fechaTermino);
					request.setAttribute("errorFechaFinFormato", "errorFechaFinFormato");
					SpringErrors.add("dsErrorFechaFinFormato", new SpringMessage(
							"field.convocatoria.errores.fechaFinNoValido"));
				}
			}
			//Fecha inicio subsanacion
			if(fechaIniSub !=null&&!(fechaIniSub.equalsIgnoreCase(""))){				
				try {
					dFechaIniSub = formatoDelTexto.parse(fechaIniSub);
					errorFormatoFechaInicioSub = !validacion.isFechaValida(fechaIniSub);
					if(errorFormatoFechaInicioSub) throw new Exception();
				} catch (Exception ex) {
					errorFormatoFechaInicioSub = true;
					logger.warn("Error parseando la Fecha Inicio subsanacion: "+fechaIniSub);
					request.setAttribute("errorFechaInicioSubFormato", "errorFechaInicioSubFormato");
					SpringErrors.add("dsErrorFechaInicioSubFormato", new SpringMessage(
							"field.convocatoria.errores.fechaInicioSubNoValido"));
				}
			}
			//Fecha fin subsanacion
			if (fechaFinSub != null && !(fechaFinSub.equalsIgnoreCase(""))){
				try {
					dFechaFinSub= formatoDelTexto.parse(fechaFinSub);
					errorFormatoFechaTerminoSub = !validacion.isFechaValida(fechaFinSub);
					if(errorFormatoFechaTerminoSub) throw new Exception();
				} catch (Exception ex) {
					errorFormatoFechaTerminoSub = true;
					logger.warn("Error parseando la Fecha Termino subsanacion: "+fechaFinSub);
					request.setAttribute("errorFechaFinSubFormato", "errorFechaFinSubFormato");
					SpringErrors.add("dsErrorFechaFinSubFormato", new SpringMessage(
							"field.convocatoria.errores.fechaFinSubNoValido"));
				}
			}
			
			setEstado((String)request.getSession().getAttribute("estado"));
			request.getSession().setAttribute("estado", null);
			
			//Para el caso donde se hace subsanacion
			if (!StringUtils.isEmpty(estado) && estado.equals(Constantes.CONVOCATORIA_ESTADO_FINALIZADA) 
					&& (StringUtils.isEmpty(fechaFinSub) || StringUtils.isEmpty(fechaIniSub))) {
				request.setAttribute("errorFechaSubObligatoria", "errorFechaSubObligatoria");
				SpringErrors.add("dsErrorFechaSubObligatoria", new SpringMessage(
						"field.convocatoria.errores.fechasSubObligatorias"));
			}
			
			if (!errorFormatoFechaInicio && !errorFormatoFechaBoe && dFechaInicio!=null && dFechaBoe!=null && dFechaInicio.getTime()<dFechaBoe.getTime()) {
				request.setAttribute("errorFechaMayorBOE", "errorFechaMayorBOE");
				SpringErrors.add("dsErrorFechaMayorBOE", new SpringMessage("field.convocatoria.errores.fechaMayorBOE"));
			}
			
			if (!errorFormatoFechaInicio && !errorFormatoFechaTerminoSub && dFechaInicio!=null && dFechaFinSub!=null && dFechaInicio.getTime() > dFechaFinSub.getTime()) {
				request.setAttribute("errorFechaMayorInicio", "errorFechaMayorInicio");
				SpringErrors.add("dsErrorFechaMayorInicio", new SpringMessage("field.convocatoria.errores.fechaMayorInicio"));
			}
			
			//Comprobacion fecha inicio mayor que fecha fin subsanacion
			if (!errorFormatoFechaInicioSub && !errorFormatoFechaTermino && dFechaIniSub!=null && dFechaFinSub!=null 
					&& dFechaIniSub.getTime() > dFechaFinSub.getTime()) {			
				request.setAttribute("errorFechaMayorInicioSub", "errorFechaMayorInicioSub");
				SpringErrors.add("dsErrorFechaMayorInicioSub", new SpringMessage(
						"field.convocatoria.errores.fechaMayorInicioSub"));
			}
			
			//Comprobacion fecha fin convocatoria con fecha ini subsanacion
			if (!errorFormatoFechaInicioSub && !errorFormatoFechaTerminoSub && dFechaIniSub != null && dFechaTermino != null
					&& dFechaIniSub.getTime() <= dFechaTermino.getTime()) {		
				request.setAttribute("errorFechaMayorInicioConvSub", "errorFechaMayorInicioConvSub");
				SpringErrors.add("dsErrorFechaMayorInicioConvSub", new SpringMessage(
						"field.convocatoria.errores.fechaMayorInicioConvSub"));
			}
		}
		if (accion.equalsIgnoreCase("") || accion == null) {
			// No se valida nada porque es la seleccion del Centro Gestor
		}

		return SpringErrors;
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
	 * Obtiene el campo.
	 *
	 * @return the campo
	 */
	public String getCampo() {
		return campo;
	}

	/**
	 * Establece el campo.
	 *
	 * @param campo the campo to set
	 */
	public void setCampo(String campo) {
		this.campo = campo;
	}

	/**
	 * Obtiene el direccion.
	 *
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * Establece el direccion.
	 *
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * Obtiene el num registro.
	 *
	 * @return the numRegistro
	 */
	public String getNumRegistro() {
		return numRegistro;
	}

	/**
	 * Establece el num registro.
	 *
	 * @param numRegistro the numRegistro to set
	 */
	public void setNumRegistro(String numRegistro) {
		this.numRegistro = numRegistro;
	}

	/**
	 * Obtiene el submit.
	 *
	 * @return the submit
	 */
	public String getSubmit() {
		return submit;
	}

	/**
	 * Establece el submit.
	 *
	 * @param submit the submit to set
	 */
	public void setSubmit(String submit) {
		this.submit = submit;
	}

	/**
	 * Obtiene el parametro.
	 *
	 * @return the parametro
	 */
	public String getParametro() {
		return parametro;
	}

	/**
	 * Establece el parametro.
	 *
	 * @param parametro the parametro to set
	 */
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	/**
	 * Obtiene el pagina actual.
	 *
	 * @return the paginaActual
	 */
	public String getPaginaActual() {
		return paginaActual;
	}

	/**
	 * Establece el pagina actual.
	 *
	 * @param paginaActual the paginaActual to set
	 */
	public void setPaginaActual(String paginaActual) {
		this.paginaActual = paginaActual;
	}

	/**
	 * Obtiene el paginas totales.
	 *
	 * @return the paginasTotales
	 */
	public String getPaginasTotales() {
		return paginasTotales;
	}

	/**
	 * Establece el paginas totales.
	 *
	 * @param paginasTotales the paginasTotales to set
	 */
	public void setPaginasTotales(String paginasTotales) {
		this.paginasTotales = paginasTotales;
	}

	/**
	 * Obtiene el mensaje.
	 *
	 * @return the mensaje
	 */
	public String getMensaje() {
		return mensaje;
	}

	/**
	 * Establece el mensaje.
	 *
	 * @param mensaje the mensaje to set
	 */
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	/**
	 * Obtiene el cambios.
	 *
	 * @return the cambios
	 */
	public String getCambios() {
		return cambios;
	}

	/**
	 * Establece el cambios.
	 *
	 * @param cambios the cambios to set
	 */
	public void setCambios(String cambios) {
		this.cambios = cambios;
	}

	/**
	 * Obtiene el siglas ministerio boe.
	 *
	 * @return el siglas ministerio boe
	 */
	public String getSiglasMinisterioBoe() {
		return siglasMinisterioBoe;
	}

	/**
	 * Establece el siglas ministerio boe.
	 *
	 * @param siglasMinisterioBoe el nuevo siglas ministerio boe
	 */
	public void setSiglasMinisterioBoe(String siglasMinisterioBoe) {
		this.siglasMinisterioBoe = siglasMinisterioBoe;
	}

	/**
	 * Obtiene el anio boe.
	 *
	 * @return el anio boe
	 */
	public String getAnioBoe() {
		return anioBoe;
	}

	/**
	 * Establece el anio boe.
	 *
	 * @param anioBoe el nuevo anio boe
	 */
	public void setAnioBoe(String anioBoe) {
		this.anioBoe = anioBoe;
	}

	/**
	 * Obtiene el ministerio siglas aux.
	 *
	 * @return el ministerio siglas aux
	 */
	public String getMinisterioSiglasAux() {
		return ministerioSiglasAux;
	}

	/**
	 * Establece el ministerio siglas aux.
	 *
	 * @param ministerioSiglasAux el nuevo ministerio siglas aux
	 */
	public void setMinisterioSiglasAux(String ministerioSiglasAux) {
		this.ministerioSiglasAux = ministerioSiglasAux;
	}

	/**
	 * Obtiene el ds centro gestor.
	 *
	 * @return el ds centro gestor
	 */
	public String getDsCentroGestor() {
		return dsCentroGestor;
	}

	/**
	 * Establece el ds centro gestor.
	 *
	 * @param dsCentroGestor el nuevo ds centro gestor
	 */
	public void setDsCentroGestor(String dsCentroGestor) {
		this.dsCentroGestor = dsCentroGestor;
	}

	/**
	 * Obtiene el ds cuerpo escala.
	 *
	 * @return el ds cuerpo escala
	 */
	public String getDsCuerpoEscala() {
		return dsCuerpoEscala;
	}

	/**
	 * Establece el ds cuerpo escala.
	 *
	 * @param dsCuerpoEscala el nuevo ds cuerpo escala
	 */
	public void setDsCuerpoEscala(String dsCuerpoEscala) {
		this.dsCuerpoEscala = dsCuerpoEscala;
	}

	/**
	 * Obtiene el parametro 2.
	 *
	 * @return el parametro 2
	 */
	public String getParametro2() {
		return parametro2;
	}

	/**
	 * Establece el parametro 2.
	 *
	 * @param parametro2 el nuevo parametro 2
	 */
	public void setParametro2(String parametro2) {
		this.parametro2 = parametro2;
	}

	/**
	 * Obtiene el codigo forma acceso.
	 *
	 * @return el codigo forma acceso
	 */
	public String getCodigoFormaAcceso() {
		return codigoFormaAcceso;
	}

	/**
	 * Establece el codigo forma acceso.
	 *
	 * @param codigoFormaAcceso el nuevo codigo forma acceso
	 */
	public void setCodigoFormaAcceso(String codigoFormaAcceso) {
		this.codigoFormaAcceso = codigoFormaAcceso;
	}

	/**
	 * Obtiene el fecha inicio inhabil.
	 *
	 * @return el fecha inicio inhabil
	 */
	public String getFechaInicioInhabil() {
		return fechaInicioInhabil;
	}

	/**
	 * Establece el fecha inicio inhabil.
	 *
	 * @param fechaInicioInhabil el nuevo fecha inicio inhabil
	 */
	public void setFechaInicioInhabil(String fechaInicioInhabil) {
		this.fechaInicioInhabil = fechaInicioInhabil;
	}

	/**
	 * Obtiene el fecha fin inhabil.
	 *
	 * @return el fecha fin inhabil
	 */
	public String getFechaFinInhabil() {
		return fechaFinInhabil;
	}

	/**
	 * Establece el fecha fin inhabil.
	 *
	 * @param fechaFinInhabil el nuevo fecha fin inhabil
	 */
	public void setFechaFinInhabil(String fechaFinInhabil) {
		this.fechaFinInhabil = fechaFinInhabil;
	}

	/**
	 * Obtiene el modificado fecha inhabil.
	 *
	 * @return el modificado fecha inhabil
	 */
	public String getModificadoFechaInhabil() {
		return modificadoFechaInhabil;
	}

	/**
	 * Establece el modificado fecha inhabil.
	 *
	 * @param modificadoFechaInhabil el nuevo modificado fecha inhabil
	 */
	public void setModificadoFechaInhabil(String modificadoFechaInhabil) {
		this.modificadoFechaInhabil = modificadoFechaInhabil;
	}

	/**
	 * Obtiene el ministerio convocante.
	 *
	 * @return el ministerio convocante
	 */
	public String getMinisterioConvocante() {
		return ministerioConvocante;
	}

	/**
	 * Establece el ministerio convocante.
	 *
	 * @param ministerioConvocante el nuevo ministerio convocante
	 */
	public void setMinisterioConvocante(String ministerioConvocante) {
		this.ministerioConvocante = ministerioConvocante;
	}
	
	/**
	 * Obtiene el presencial.
	 *
	 * @return el presencial
	 */
	public boolean getPresencial() {
		return presencial;
	}

	/**
	 * Establece el presencial.
	 *
	 * @param presencial el nuevo presencial
	 */
	public void setPresencial(boolean presencial) {
		this.presencial = presencial;
	}
	
	/**
	 * Obtiene el fecha ini sub.
	 *
	 * @return el fecha ini sub
	 */
	public String getFechaIniSub() {
		return fechaIniSub;
	}

	/**
	 * Establece el fecha ini sub.
	 *
	 * @param fechaIniSub el nuevo fecha ini sub
	 */
	public void setFechaIniSub(String fechaIniSub) {
		this.fechaIniSub = fechaIniSub;
	}

	/**
	 * Obtiene el fecha fin sub.
	 *
	 * @return el fecha fin sub
	 */
	public String getFechaFinSub() {
		return fechaFinSub;
	}

	/**
	 * Establece el fecha fin sub.
	 *
	 * @param fechaFinSub el nuevo fecha fin sub
	 */
	public void setFechaFinSub(String fechaFinSub) {
		this.fechaFinSub = fechaFinSub;
	}

	/**
	 * Obtiene el estado.
	 *
	 * @return el estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * Establece el estado.
	 *
	 * @param estado el nuevo estado
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * Obtiene el otros titulos.
	 *
	 * @return el otros titulos
	 */
	public String[] getOtrosTitulos() {
		return otrosTitulos;
	}

	/**
	 * Establece el otros titulos.
	 *
	 * @param otrosTitulos el nuevo otros titulos
	 */
	public void setOtrosTitulos(String[] otrosTitulos) {
		this.otrosTitulos = otrosTitulos;
	}

	/**
	 * Obtiene el otros titulos seleccionados.
	 *
	 * @return el otros titulos seleccionados
	 */
	public String[] getOtrosTitulosSeleccionados() {
		return otrosTitulosSeleccionados;
	}

	/**
	 * Establece el otros titulos seleccionados.
	 *
	 * @param otrosTitulosSeleccionados el nuevo otros titulos seleccionados
	 */
	public void setOtrosTitulosSeleccionados(String[] otrosTitulosSeleccionados) {
		this.otrosTitulosSeleccionados = otrosTitulosSeleccionados;
	}

	/**
	 * Obtiene el discapacidad.
	 *
	 * @return el discapacidad
	 */
	public String[] getDiscapacidad() {
		return discapacidad;
	}

	/**
	 * Establece el discapacidad.
	 *
	 * @param discapacidad el nuevo discapacidad
	 */
	public void setDiscapacidad(String[] discapacidad) {
		this.discapacidad = discapacidad;
	}

	/**
	 * Obtiene el discapacidad seleccionados.
	 *
	 * @return el discapacidad seleccionados
	 */
	public String[] getDiscapacidadSeleccionados() {
		return discapacidadSeleccionados;
	}

	/**
	 * Establece el discapacidad seleccionados.
	 *
	 * @param discapacidadSeleccionados el nuevo discapacidad seleccionados
	 */
	public void setDiscapacidadSeleccionados(String[] discapacidadSeleccionados) {
		this.discapacidadSeleccionados = discapacidadSeleccionados;
	}

	/**
	 * Comprueba si es otros titulos flag.
	 *
	 * @return verdadero, si es otros titulos flag
	 */
	public boolean isOtrosTitulosFlag() {
		return otrosTitulosFlag;
	}

	/**
	 * Establece el otros titulos flag.
	 *
	 * @param otrosTitulosFlag el nuevo otros titulos flag
	 */
	public void setOtrosTitulosFlag(boolean otrosTitulosFlag) {
		this.otrosTitulosFlag = otrosTitulosFlag;
	}

	/**
	 * Comprueba si es discapacidad flag.
	 *
	 * @return verdadero, si es discapacidad flag
	 */
	public boolean isDiscapacidadFlag() {
		return discapacidadFlag;
	}

	/**
	 * Establece el discapacidad flag.
	 *
	 * @param discapacidadFlag el nuevo discapacidad flag
	 */
	public void setDiscapacidadFlag(boolean discapacidadFlag) {
		this.discapacidadFlag = discapacidadFlag;
	}

	/**
	 * Obtiene el datos propios.
	 *
	 * @return el datos propios
	 */
	public String[] getDatosPropios() {
		return datosPropios;
	}

	/**
	 * Establece el datos propios.
	 *
	 * @param datosPropios el nuevo datos propios
	 */
	public void setDatosPropios(String[] datosPropios) {
		this.datosPropios = datosPropios;
	}

	/**
	 * Obtiene el datos propios seleccionados.
	 *
	 * @return el datos propios seleccionados
	 */
	public String[] getDatosPropiosSeleccionados() {
		return datosPropiosSeleccionados;
	}

	/**
	 * Establece el datos propios seleccionados.
	 *
	 * @param datosPropiosSeleccionados el nuevo datos propios seleccionados
	 */
	public void setDatosPropiosSeleccionados(String[] datosPropiosSeleccionados) {
		this.datosPropiosSeleccionados = datosPropiosSeleccionados;
	}

	/**
	 * Comprueba si es datos propios flag.
	 *
	 * @return verdadero, si es datos propios flag
	 */
	public boolean isDatosPropiosFlag() {
		return datosPropiosFlag;
	}

	/**
	 * Establece el datos propios flag.
	 *
	 * @param datosPropiosFlag el nuevo datos propios flag
	 */
	public void setDatosPropiosFlag(boolean datosPropiosFlag) {
		this.datosPropiosFlag = datosPropiosFlag;
	}

	/**
	 * Obtiene el centro gestor anterior.
	 *
	 * @return el centro gestor anterior
	 */
	public String getCentroGestorAnterior() {
		return centroGestorAnterior;
	}

	/**
	 * Establece el centro gestor anterior.
	 *
	 * @param centroGestorAnterior el nuevo centro gestor anterior
	 */
	public void setCentroGestorAnterior(String centroGestorAnterior) {
		this.centroGestorAnterior = centroGestorAnterior;
	}
	
	public String getEnlace() {
		return enlace;
	}

	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	
	/**
	 * Comprueba si es ocultar datos propios.
	 *
	 * @return verdadero, si es ocultar datos propios
	 */
	public boolean getOcultarDatosPropios() {
		return ocultarDatosPropios;
	}

	/**
	 * Establece el ocultar datos propios.
	 *
	 * @param ocultarDatosPropios el nuevo ocultar datos propios
	 */
	public void setOcultarDatosPropios(boolean ocultarDatosPropios) {
		this.ocultarDatosPropios = ocultarDatosPropios;
	}

	/*INI-TRA042*/
	/**
	 * @return the listaCentrosGestores
	 */
	public List<CentroGestorBean> getListaCentrosGestores() {
		return listaCentrosGestores;
	}

	/**
	 * @param listaCentrosGestores the listaCentrosGestores to set
	 */
	public void setListaCentrosGestores(List<CentroGestorBean> listaCentrosGestores) {
		this.listaCentrosGestores = listaCentrosGestores;
	}
	/*FIN-TRA042*/
}
