package es.map.ipsc;

/**
 * El Class Constantes.
 */
public class Constantes {
	
	/** La constante USUARIO_ESTADO_ACTIVO. */
	public static final Character USUARIO_ESTADO_ACTIVO = '1';
	
	/** La constante PAGINA_PRINCIPAL. */
	// GENERALES
	public static final String PAGINA_PRINCIPAL = "1";	
	
	/** La constante ESTADO_BUSQUEDA. */
	public static final String ESTADO_BUSQUEDA = "Correcto";
	
	/** La constante NUMERO_REGISTROS_TOTALES. */
	public static final String NUMERO_REGISTROS_TOTALES = "1000";
	
	/** La constante NUMERO_REGISTROS_TOTALES_MOSTRAR. */
	public static final String NUMERO_REGISTROS_TOTALES_MOSTRAR = "5";
	
	/** La constante NUMERO_REGISTROS_PAGINA_SOLICITUDES. */
	public static final String NUMERO_REGISTROS_PAGINA_SOLICITUDES = "50";
	
	/** La constante SI. */
	public static final String SI = "SI";
	
	/** La constante NO. */
	public static final String NO = "NO";
	
	/** La constante ID_PAIS_ESPANIA. */
	public static final Integer ID_PAIS_ESPANIA = 1;
	
	/** La constante RESULTADO_OK. */
	public static final String RESULTADO_OK = "OK";
	
	/** La constante RESULTADO_KO. */
	public static final String RESULTADO_KO = "KO";
	
	/** La constante NUMERO_JUSTIFICANTE_DEFECTO. */
	// Formulario 790
	public static final String NUMERO_JUSTIFICANTE_DEFECTO = "1";	
	
	/** La constante NUM_CARACTERES_FORMULARIO790. */
	public static final int NUM_CARACTERES_FORMULARIO790 = 800;
	
	/** La constante MODELO_ASOCIADO_GENERACION_EJB. */
	public static final String MODELO_ASOCIADO_GENERACION_EJB = "790001";
	
	/** La constante AVISO_ESTADO_ACTIVO. */
	// Estados Aviso
	public static final Integer AVISO_ESTADO_ACTIVO = 1;
	
	/** La constante AVISO_ESTADO_INACTIVO. */
	public static final Integer AVISO_ESTADO_INACTIVO = 2;
	
	/** La constante AVISO_ESTADO_PUBLICADO. */
	public static final Integer AVISO_ESTADO_PUBLICADO = 3;
	
	/** La constante AUTENTICACION_CERTIFICADOE. */
	// Autenticacion CL@VE
	public static final String AUTENTICACION_CERTIFICADOE = "AFIRMA";
	
	/** La constante AUTENTICACION_CLAVE_PIN. */
	public static final String AUTENTICACION_CLAVE_PIN = "PIN24H";
	
	/** La constante AUTENTICACION_CLAVE_PERMANENTE. */
	public static final String AUTENTICACION_CLAVE_PERMANENTE = "SEGSOC";
	
	/** La constante CODIGO_AUTENTICACION_CERTIFICADOE. */
	public static final String CODIGO_AUTENTICACION_CERTIFICADOE = "0";
	
	/** La constante CODIGO_AUTENTICACION_CLAVE_PIN. */
	public static final String CODIGO_AUTENTICACION_CLAVE_PIN = "1";
	
	/** La constante CODIGO_AUTENTICACION_CLAVE_PERMANENTE. */
	public static final String CODIGO_AUTENTICACION_CLAVE_PERMANENTE = "2";

	
	/** La constante FORMA_PAGO_EXENTO. */
	// Formas Pago
	public static final char FORMA_PAGO_EXENTO = 'E';
	
	/** La constante FORMA_PAGO_ADEUDO. */
	public static final char FORMA_PAGO_ADEUDO = 'C';
	
	/** La constante FORMA_PAGO_TARJETA. */
	public static final char FORMA_PAGO_TARJETA = 'T';
	
	/** La constante FORMA_PAGO_EXENTO_S. */
	public static final String FORMA_PAGO_EXENTO_S = "E";
	
	/** La constante FORMA_PAGO_ADEUDO_S. */
	public static final String FORMA_PAGO_ADEUDO_S = "C";
	
	/** La constante FORMA_PAGO_ADEUDO_CUENTA_S. */
	public static final String FORMA_PAGO_ADEUDO_CUENTA_S = "A";
	
	/** La constante FORMA_PAGO_TARJETA_S. */
	public static final String FORMA_PAGO_TARJETA_S = "T";
	
	/** La constante FORMA_PAGO_NINGUNO. */
	public static final char FORMA_PAGO_NINGUNO = 'N';
	
	/** La constante MOTIVO_DISCAPACIDAD. */
	// Motivos Exención/Reducción	
	public static final String MOTIVO_DISCAPACIDAD = "1";
	
	/** La constante MOTIVO_DESEMPLEO. */
	public static final String MOTIVO_DESEMPLEO = "2";
	
	/** La constante MOTIVO_FNUMEROSA. */
	public static final String MOTIVO_FNUMEROSA = "5";
	
	/** La constante MOTIVO_FNUMEROSAESPECIAL. */
	public static final String MOTIVO_FNUMEROSAESPECIAL = "3";
	
	/** La constante MOTIVO_VICTIMATERRORISMO. */
	public static final String MOTIVO_VICTIMATERRORISMO = "6";

	
	/** La constante SOLICITUD_ESTADO_NOPAGADO_DES. */
	//Solicitud
	public static final String SOLICITUD_ESTADO_NOPAGADO_DES = "INSCRITA, NO PAGADA Y NO REGISTRADA";
	
	/** La constante SOLICITUD_ESTADO_NOREGISTRADO_DES. */
	public static final String SOLICITUD_ESTADO_NOREGISTRADO_DES = "INSCRITA, PAGADA Y NO REGISTRADA";
	
	/** La constante SOLICITUD_ESTADO_REGISTRADO_DES. */
	public static final String SOLICITUD_ESTADO_REGISTRADO_DES = "INSCRITA, PAGADA Y REGISTRADA";
	
	/** La constante SOLICITUD_ESTADO_PENDIENTEREGISTRO_DES. */
	public static final String SOLICITUD_ESTADO_PENDIENTEREGISTRO_DES = "INSCRITA, PAGADA Y REGISTRO PENDIENTE";
	
	/** La constante ESTADO_PAGO_SOLICITUD_EXENTO. */
	public static final String ESTADO_PAGO_SOLICITUD_EXENTO = "E";
	
	/** La constante ESTADO_PAGO_SOLICITUD_EXENTO_CHAR. */
	public static final char ESTADO_PAGO_SOLICITUD_EXENTO_CHAR = 'E';
	
	/** La constante RESULTADO_PAGO_SOLICITUD_OK. */
	public static final String RESULTADO_PAGO_SOLICITUD_OK = "OK";
	
	/** La constante RESULTADO_PAGO_SOLICITUD_ER. */
	public static final String RESULTADO_PAGO_SOLICITUD_ER = "ER";
	
	/** La constante SOLICITUD_ESTADO_TELEMATICO. */
	public static final  Character SOLICITUD_ESTADO_TELEMATICO = 'T';
	
	/** La constante SOLICITUD_PARAMETROS_CONFIG. */
	public static final String SOLICITUD_PARAMETROS_CONFIG = "METODO_NUMERO_SOLICITUD";
	
	/** La constante SOLICITUD_VALOR_PARAMETROS_CONFIG. */
	public static final String SOLICITUD_VALOR_PARAMETROS_CONFIG = "EJB";
	
	/** La constante SOLICITUD_ID_NOPAGADO. */
	public static final int SOLICITUD_ID_NOPAGADO = 1;
	
	/** La constante SOLICITUD_ID_NOPAGADO_STRING. */
	public static final String SOLICITUD_ID_NOPAGADO_STRING = "1";
	
	/** La constante SOLICITUD_ID_NOREGISTRADO. */
	public static final int SOLICITUD_ID_NOREGISTRADO = 2;
	
	/** La constante SOLICITUD_ID_NOREGISTRADO_STRING. */
	public static final String SOLICITUD_ID_NOREGISTRADO_STRING = "2";
	
	/** La constante SOLICITUD_ID_REGISTRADO. */
	public static final int SOLICITUD_ID_REGISTRADO = 3;
	
	/** La constante SOLICITUD_ID_REGISTRADO_STRING. */
	public static final String SOLICITUD_ID_REGISTRADO_STRING = "3";
	
	/** La constante SOLICITUD_ID_PENDIENTE_REGISTRO. */
	public static final int SOLICITUD_ID_PENDIENTE_REGISTRO = 5;
	
	/** La constante SOLICITUD_ID_PENDIENTE_REGISTRO_STRING. */
	public static final String SOLICITUD_ID_PENDIENTE_REGISTRO_STRING = "5";
	
	/** La constante SOLICITUD_ID_PROCESO_PAGO. */
	public static final int SOLICITUD_ID_PROCESO_PAGO = 6;
	
	/** La constante SOLICITUD_ID_PROCESO_REGISTRO. */
	public static final int SOLICITUD_ID_PROCESO_REGISTRO = 7;
	
	/** La constante SOLICITUD_ID_ELIMINADO. */
	public static final int SOLICITUD_ID_ELIMINADO = 4;
	
	/** La constante SOLICITUD_ID_ELIMINADO_STRING. */
	public static final String SOLICITUD_ID_ELIMINADO_STRING = "4";
	
	/** La constante SOLICITUD_ID_PROCESO_REGISTRO_STRING. */
	public static final String SOLICITUD_ID_PROCESO_REGISTRO_STRING = "7";
	
	/** La constante SOLICITUD_ID_PROCESO_PAGO_STRING. */
	public static final String SOLICITUD_ID_PROCESO_PAGO_STRING = "6";
	
	/** La constante LOG_SOLICITUD_ALTA. */
	//Log Solicitud
	public static final String LOG_SOLICITUD_ALTA = "A";
	
	/** La constante LOG_SOLICITUD_MOFIFICAR. */
	public static final String LOG_SOLICITUD_MOFIFICAR = "M";
	
	/** La constante LOG_SOLICITUD_CAMBIOESTADO. */
	public static final String LOG_SOLICITUD_CAMBIOESTADO = "E";
	
	/** La constante LOG_SOLICITUD_BAJA. */
	public static final String LOG_SOLICITUD_BAJA = "B";
	
	/** La constante LOG_SOLICITUD_ESTADO_NOPAGADO. */
	public static final String LOG_SOLICITUD_ESTADO_NOPAGADO = "1";
	
	/** La constante LOG_SOLICITUD_ESTADO_NOREGISTRADO. */
	public static final String LOG_SOLICITUD_ESTADO_NOREGISTRADO = "2";
	
	/** La constante LOG_SOLICITUD_ESTADO_REGISTRADO. */
	public static final String LOG_SOLICITUD_ESTADO_REGISTRADO = "3";
	
	/** La constante LOG_SOLICITUD_ESTADO_PENDIENTE_REGISTRAR. */
	public static final String LOG_SOLICITUD_ESTADO_PENDIENTE_REGISTRAR = "5";
	
	/** La constante LOG_SOLICITUD_ESTADO_ELIMINADO. */
	public static final String LOG_SOLICITUD_ESTADO_ELIMINADO = "4";
	
	/** La constante LOG_TIPO_ACTOR. */
	public static final String LOG_TIPO_ACTOR = "C";
	
	/** La constante CUERPOESCALA_ESTADO_ACTIVO. */
	//Cuerpo escala
	public static final Character CUERPOESCALA_ESTADO_ACTIVO = 'A';
	
	/** La constante CENTROGESTOR_ESTADO_ACTIVO. */
	//Centro Gestor
	public static final Character CENTROGESTOR_ESTADO_ACTIVO = 'A';
	
	/** La constante CONVOCATORIA_ESTADO_PUBLICADO. */
	//Convocatoria
	public static final String CONVOCATORIA_ESTADO_PUBLICADO = "PUBLICADO";
	
	/** La constante ESPECIALIDAD_ESTADO_ACTIVO. */
	//Especialidad
	public static final Character ESPECIALIDAD_ESTADO_ACTIVO = 'A';
	
	/** La constante FORMAACCESO_ESTADO_ACTIVO. */
	//Forma Acceso
	public static final Character FORMAACCESO_ESTADO_ACTIVO = 'A';
	
	/** La constante GRUPO_ESTADO_ACTIVO. */
	//Grupo
	public static final Character GRUPO_ESTADO_ACTIVO = 'A';
	
	/** La constante MOTIVOTASA_PORCENTAJE_COMPLETO. */
	//MotivoTasa
	public static final int MOTIVOTASA_PORCENTAJE_COMPLETO = 100;
	
	/** La constante MOTIVOTASA_ESTADO_ACTIVO. */
	public static final Character MOTIVOTASA_ESTADO_ACTIVO = 'A';
	
	/** La constante MOTIVOTASA_DISCAPACIDAD. */
	public static final String MOTIVOTASA_DISCAPACIDAD = "1";
	
	/** La constante PROVINCIA_ESTADO_ACTIVO. */
	//Provincia
	public static final Character PROVINCIA_ESTADO_ACTIVO = 'A';
	
	/** La constante TITULO_ESTADO_ACTIVO. */
	//Titulo
	public static final Character TITULO_ESTADO_ACTIVO = 'A';
	
	/** La constante CORREO_INCIDENCIA_CIUDADANO. */
	//Incidencias
	public static final String CORREO_INCIDENCIA_CIUDADANO = "CORREO_INCIDENCIA_CIUDADANO";
	
	/** La constante ENTIDAD_FINANCIERA_ESTADO_ACTIVO. */
	//Entidad Financiera
	public static final Character ENTIDAD_FINANCIERA_ESTADO_ACTIVO = 'A';
	
	/** La constante CORREO_ELECTRONICO_ESTADO_ENVIADO. */
	//Correo Electronico
	public static final char CORREO_ELECTRONICO_ESTADO_ENVIADO = 'E';
	
	/** La constante CORREO_ELECTRONICO_ESTADO_PENDIENTE. */
	public static final char CORREO_ELECTRONICO_ESTADO_PENDIENTE = 'P';
	
	/** La constante TIPO_DOCUMENTO_ANEXO_CONVOCATORIA. */
	//Documentos
	public static final int TIPO_DOCUMENTO_ANEXO_CONVOCATORIA = 2;
	
	/** La constante TIPO_DOCUMENTO_ANEXO_SOLICITUD. */
	public static final int TIPO_DOCUMENTO_ANEXO_SOLICITUD = 1;
	
	/** La constante TIPO_DOCUMENTO_FIRMA. */
	public static final int TIPO_DOCUMENTO_FIRMA = 8;
	
	/** La constante ENTORNO_SOLICITUDES. */
	public static final String ENTORNO_SOLICITUDES = "Solicitudes";
	
	/** La constante ENTORNO_CONVOCATORIAS. */
	public static final String ENTORNO_CONVOCATORIAS = "Convocatorias";
	
	/** La constante ENTORNO_AYUDA. */
	public static final String ENTORNO_AYUDA = "Ayuda";
	
	/** La constante ENTORNO_REQUISITOS. */
	public static final String ENTORNO_REQUISITOS = "Requisitos";
	
	/** La constante TIPO_DOCUMENTO_ADJUNTO. */
	public static final int TIPO_DOCUMENTO_ADJUNTO = 7;
	
	/** La constante TIPO_DOCUMENTO_JUSTIFICANTE_TASA. */
	public static final int TIPO_DOCUMENTO_JUSTIFICANTE_TASA = 6;
	
	/** La constante TIPO_DOCUMENTO_JUSTIFICANTE_DISCAPACIDAD. */
	public static final int TIPO_DOCUMENTO_JUSTIFICANTE_DISCAPACIDAD = 10;
	
	/** La constante TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF. */
	//Justificante de registro de solicitud
	public static final int TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF = 4;
	
	/** La constante TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_XML. */
	public static final int TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_XML = 5;
	
	/** La constante TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_CATALAN. */
	public static final int TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_CATALAN = 19;
	
	/** La constante TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_XML_CATALAN. */
	public static final int TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_XML_CATALAN = 20;
	
	/** La constante TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_EUSKERA. */
	public static final int TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_EUSKERA = 21;
	
	/** La constante TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_XML_EUSKERA. */
	public static final int TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_XML_EUSKERA = 22;
	
	/** La constante TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_GALLEGO. */
	public static final int TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_GALLEGO = 23;
	
	/** La constante TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_XML_GALLEGO. */
	public static final int TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_XML_GALLEGO = 24;
	
	/** La constante TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_VALENCIANO. */
	public static final int TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_PDF_VALENCIANO = 25;
	
	/** La constante TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_XML_VALENCIANO. */
	public static final int TIPO_DOCUMENTO_JUSTIFICANTE_REGISTRO_XML_VALENCIANO = 26;
	
	/** La constante JUSTIFICANTE_DE_INSCRIPCION_SOLICITUD_PDF. */
	public static final int JUSTIFICANTE_DE_INSCRIPCION_SOLICITUD_PDF = 4;
	
	
	/** La constante TIPO_DOCUMENTO_JUSTIFICANTE_PAGO. */
	//Justificante de pago de solicitud 
	public static final int TIPO_DOCUMENTO_JUSTIFICANTE_PAGO = 3;
	
	/** La constante TIPO_DOCUMENTO_JUSTIFICANTE_PAGO_CATALAN. */
	public static final int TIPO_DOCUMENTO_JUSTIFICANTE_PAGO_CATALAN = 15;
	
	/** La constante TIPO_DOCUMENTO_JUSTIFICANTE_PAGO_EUSKERA. */
	public static final int  TIPO_DOCUMENTO_JUSTIFICANTE_PAGO_EUSKERA = 16;
	
	/** La constante TIPO_DOCUMENTO_JUSTIFICANTE_PAGO_GALLEGO. */
	public static final int TIPO_DOCUMENTO_JUSTIFICANTE_PAGO_GALLEGO = 17;
	
	/** La constante TIPO_DOCUMENTO_JUSTIFICANTE_PAGO_VALENCIANO. */
	public static final int  TIPO_DOCUMENTO_JUSTIFICANTE_PAGO_VALENCIANO = 18;

	/** La constante TIPO_DOCUMENTO_FIRMA_REC. */
	public static final int TIPO_DOCUMENTO_FIRMA_REC = 14;
	
	/** La constante TAMANIO_MAXIMO_DOCUMENTOS. */
	public static final int TAMANIO_MAXIMO_DOCUMENTOS = 5000;
	
	/** La constante RESULTADO_PAGO_OK. */
	//Pago
	public static final String RESULTADO_PAGO_OK = "OK";
	
	/** La constante REDUCCION_PAGO_SI. */
	public static final String REDUCCION_PAGO_SI = "S";
	
	/** La constante REDUCCION_PAGO_NO. */
	public static final String REDUCCION_PAGO_NO = "N";
	
	/** La constante PAGO_TIPO_EXENTO. */
	public static final String PAGO_TIPO_EXENTO = "Exento";
	
	/** La constante PAGO_TIPO_TARJETA. */
	public static final String PAGO_TIPO_TARJETA = "Tarjeta de Credito";
	
	/** La constante PAGO_TIPO_ADEUDO. */
	public static final String PAGO_TIPO_ADEUDO = "Adeudo en cuenta";
	
	/** La constante PAGO_TIPO_NINGUNO. */
	public static final String PAGO_TIPO_NINGUNO = "Ninguno";
	
	/** La constante PAGO_TIPO_EXENTO_CHAR. */
	public static final String PAGO_TIPO_EXENTO_CHAR = "E";
	
	/** La constante PAGO_TIPO_TARJETA_CHAR. */
	public static final String PAGO_TIPO_TARJETA_CHAR = "T";
	
	/** La constante PAGO_TIPO_ADEUDO_CHAR. */
	public static final String PAGO_TIPO_ADEUDO_CHAR = "C";
	
	/** La constante PAGO_TIPO_TARJETA_ABRE. */
	public static final String PAGO_TIPO_TARJETA_ABRE = "Tarjeta";
	
	/** La constante PAGO_TIPO_ADEUDO_ABRE. */
	public static final String PAGO_TIPO_ADEUDO_ABRE = "Adeudo";
	
	/** La constante PAGO_TIPO_ADEUDO_ID. */
	public static final int PAGO_TIPO_ADEUDO_ID = 2;
	
	/** La constante PAGO_TIPO_TARJETA_ID. */
	public static final int PAGO_TIPO_TARJETA_ID = 1;
	
	/** La constante PAGO_TIPO_TODOS_ID. */
	public static final int PAGO_TIPO_TODOS_ID = 3;
	
	/** La constante REGISTRO_RESULTADO_OK. */
	//Registro solicitud
	public static final String REGISTRO_RESULTADO_OK = "OK";
	
	/** La constante REGISTRO_RESULTADO_ERROR. */
	public static final String REGISTRO_RESULTADO_ERROR = "ER";
	
	/** La constante REGISTRO_NUMREGISTRO. */
	public static final String REGISTRO_NUMREGISTRO = "1";
	
	/** La constante REGISTRO_SOLICITANTE. */
	public static final String REGISTRO_SOLICITANTE = "A";
	
	/** La constante REGISTRO_SOLICITANTE_CIUDADANO. */
	public static final String REGISTRO_SOLICITANTE_CIUDADANO = "C";
	
	/** La constante REGISTRO_SOLICITANTE_FUNCIONARIO. */
	public static final String REGISTRO_SOLICITANTE_FUNCIONARIO = "F";
	
	/** La constante REGISTRO_OFICINA. */
	public static final String REGISTRO_OFICINA = "3";
	
	/** La constante LONGITUD_MAX_CAMPO_REC. */
	public static final int LONGITUD_MAX_CAMPO_REC = 30;
	
	/** La constante TIPO_PERSONA_CIUDADANO. */
	// distincion usuarios
	public static final String TIPO_PERSONA_CIUDADANO = "C";
	
	/** La constante TIPO_PERSONA_FUNCIONARIO_HABILITADO. */
	public static final String TIPO_PERSONA_FUNCIONARIO_HABILITADO = "FH";
	
	/** La constante TIPO_ACTOR_FUNCIONARIO_HABILITADO. */
	public static final Character TIPO_ACTOR_FUNCIONARIO_HABILITADO = 'F';
	
	/** La constante TIPO_PERSONA_FUNCIONARIO_NO_HABILITADO. */
	public static final String TIPO_PERSONA_FUNCIONARIO_NO_HABILITADO = "FNH";
	
	/** La constante TIPO_PERSONA_FUNCIONARIO_HABILITADO_NO. */
	public static final Boolean TIPO_PERSONA_FUNCIONARIO_HABILITADO_NO = false;
	
	/** La constante TIPO_PERSONA_FUNCIONARIO_HABILITADO_SI. */
	public static final Boolean TIPO_PERSONA_FUNCIONARIO_HABILITADO_SI = true;
	
	/** La constante PLANTILLA_SI. */
	//Plantilla
	public static final char PLANTILLA_SI = 'S';
	
	/** La constante PLANTILLA_NO. */
	public static final char PLANTILLA_NO = 'N';
	
	/** La constante NOAPLICA. */
	public static final char NOAPLICA = 'R';
	
	/** La constante TIPO_DOCUMENTO_CONVOCATORIA_CUALQUIERA. */
	//Tipo documento permitido
	public static final String TIPO_DOCUMENTO_CONVOCATORIA_CUALQUIERA = "C";
	
	/** La constante TIPO_DOCUMENTO_CONVOCATORIA_NINGUNO. */
	public static final String TIPO_DOCUMENTO_CONVOCATORIA_NINGUNO = "N";
	
	/** La constante TIPO_DOCUMENTO_CONVOCATORIA_REDUCCION. */
	public static final String TIPO_DOCUMENTO_CONVOCATORIA_REDUCCION = "R";
	
	/** La constante PARAMETROS_CORREO_INCIDENCIA_INSCRIPCION. */
	//Parametros Configuracion
	public static final String PARAMETROS_CORREO_INCIDENCIA_INSCRIPCION = "CORREO_INCIDENCIA_INSCRIPCION";
	
	/** La constante PARAMETROS_PORCENTAJE_DISCAPACIDAD. */
	public static final String PARAMETROS_PORCENTAJE_DISCAPACIDAD = "PORCENTAJE_PAGO_EXENTO";
	
	/** La constante PARAMETRO_CONFIGURACION_CAMBIO_AFIRMA. */
	//Parámertro de configuracion CAMBIO_AFIRMA
	public static final int    PARAMETRO_CONFIGURACION_CAMBIO_AFIRMA = 16;
	
	/** La constante PARAMETRO_CONFIGURACION_CAMBIO_EPAGO. */
	//Parámertro de configuracion CAMBIO_AFIRMA
	public static final int    PARAMETRO_CONFIGURACION_CAMBIO_EPAGO = 17;
	
	/** La constante NUM_MAX_DOCUMENTOS. */
	//Registro de documentos
	public static final String NUM_MAX_DOCUMENTOS = "10";
	
	/** La constante DOCUMENTO_TIPO_FORMULARIO. */
	public static final int DOCUMENTO_TIPO_FORMULARIO = 9;
	
	/** La constante INTERESADO_TIPO_DOCUMENTO_NACIONAL. */
	//InteresadoType
	public static final String INTERESADO_TIPO_DOCUMENTO_NACIONAL = "N";
	
	/** La constante INTERESADO_TIPO_DOCUMENTO_EXTRANJERO. */
	public static final String INTERESADO_TIPO_DOCUMENTO_EXTRANJERO = "E";
	
	/** La constante USUARIO_PERFIL_ADMIN_ID. */
	//Usuario
	public static final String USUARIO_PERFIL_ADMIN_ID = "4";
	
	/** La constante LOG_SERVICIO_TIPOSERVICIO_EJB_NUMERADOR. */
	//LOG_servicios
	public static final String LOG_SERVICIO_TIPOSERVICIO_EJB_NUMERADOR = "1";
	
	/** La constante LOG_SERVICIO_TIPOSERVICIO_VALIDAR_CERTIFICADO. */
	public static final String LOG_SERVICIO_TIPOSERVICIO_VALIDAR_CERTIFICADO = "2";
	
	/** La constante LOG_SERVICIO_TIPOSERVICIO_PAGO. */
	public static final String LOG_SERVICIO_TIPOSERVICIO_PAGO = "3";
	
	/** La constante LOG_SERVICIO_TIPOSERVICIO_REGISTRO. */
	public static final String LOG_SERVICIO_TIPOSERVICIO_REGISTRO = "4";
	
	/** La constante LOG_SERVICIO_TIPOSERVICIO_GUARDAR_DOCUMENTOS. */
	public static final String LOG_SERVICIO_TIPOSERVICIO_GUARDAR_DOCUMENTOS = "6";
	
	/** La constante LOG_SERVICIO_RESULTADO_ERROR. */
	public static final String LOG_SERVICIO_RESULTADO_ERROR = "ER";
	
	/** La constante LOG_SERVICIO_RESULTADO_OK. */
	public static final String LOG_SERVICIO_RESULTADO_OK = "OK";
	
	/** La constante LOG_SERVICIO_TIPO_ERROR_FISICO. */
	public static final String LOG_SERVICIO_TIPO_ERROR_FISICO = "F";
	
	/** La constante LOG_SERVICIO_TIPO_ERROR_LOGICO. */
	public static final String LOG_SERVICIO_TIPO_ERROR_LOGICO = "L";
	
	/** La constante LOG_SERVICIO_PRUEBA_SI. */
	public static final String LOG_SERVICIO_PRUEBA_SI = "S";
	
	/** La constante LOG_SERVICIO_PRUEBA_NO. */
	public static final String LOG_SERVICIO_PRUEBA_NO = "N";

	/** La constante MINISTERIO_ESTADO_ACTIVO. */
	public static final Character MINISTERIO_ESTADO_ACTIVO = 'A';
	
	/** La constante JUSTIFICANTE_REGISTRO_LLAMADA. */
	//Justificantes
	public static final String JUSTIFICANTE_REGISTRO_LLAMADA = "R";
	
	/** La constante JUSTIFICANTE_PAGO_LLAMADA. */
	public static final String JUSTIFICANTE_PAGO_LLAMADA = "P";
	
	/** La constante JUSTIFICANTE_PAGO_ID. */
	//Justificante de pago de solicitud
	public static final String JUSTIFICANTE_PAGO_ID = "3";
	
	/** La constante JUSTIFICANTE_PAGO_ID_CATALAN. */
	public static final String  JUSTIFICANTE_PAGO_ID_CATALAN = "15";
	
	/** La constante JUSTIFICANTE_PAGO_ID_EUSKERA. */
	public static final String  JUSTIFICANTE_PAGO_ID_EUSKERA = "16";
	
	/** La constante JUSTIFICANTE_PAGO_ID_GALLEGO. */
	public static final String  JUSTIFICANTE_PAGO_ID_GALLEGO = "17";
	
	/** La constante JUSTIFICANTE_PAGO_ID_VALENCIANO. */
	public static final String  JUSTIFICANTE_PAGO_ID_VALENCIANO = "18";
	
	/** La constante JUSTIFICANTE_REGISTRO_ID. */
	//Justificante de registro de solicitud PDF
	public static final String JUSTIFICANTE_REGISTRO_ID = "4";
	
	/** La constante JUSTIFICANTE_REGISTRO_ID_CATALAN. */
	public static final String JUSTIFICANTE_REGISTRO_ID_CATALAN = "19";
	
	/** La constante JUSTIFICANTE_REGISTRO_ID_EUSKERA. */
	public static final String JUSTIFICANTE_REGISTRO_ID_EUSKERA = "21";
	
	/** La constante JUSTIFICANTE_REGISTRO_ID_GALLEGO. */
	public static final String JUSTIFICANTE_REGISTRO_ID_GALLEGO = "23";
	
	/** La constante JUSTIFICANTE_REGISTRO_ID_VALENCIANO. */
	public static final String JUSTIFICANTE_REGISTRO_ID_VALENCIANO = "25";
	
	/** La constante NOMBRE_JUSTIFICANTE_REGISTRO. */
	public static final String NOMBRE_JUSTIFICANTE_REGISTRO = "JustificanteRegistroPdf.pdf";
	
	/** La constante NOMBRE_JUSTIFICANTE_REGISTRO_CSV. */
	public static final String NOMBRE_JUSTIFICANTE_REGISTRO_CSV = "JustificanteRegistroPdfCsv";
	
	/** La constante NOMBRE_JUSTIFICANTE_REGISTRO_EMAIL. */
	//Justificante de registro de solicitud E-mail
	public static final String NOMBRE_JUSTIFICANTE_REGISTRO_EMAIL = "Justificante de Registro.pdf";
	
	/** La constante ASUNTO_JUSTIFICANTE_REGISTRO_EMAIL. */
	public static final String ASUNTO_JUSTIFICANTE_REGISTRO_EMAIL = "Inscripción en Pruebas Selectivas";
	
	
	/** La constante JUSTIFICANTE_REGISTRO_ID_XML. */
	//Justificante de registro de solicitud XML
	public static final String JUSTIFICANTE_REGISTRO_ID_XML = "5";
	
	/** La constante JUSTIFICANTE_REGISTRO_ID_CATALAN_XML. */
	public static final String JUSTIFICANTE_REGISTRO_ID_CATALAN_XML = "20";
	
	/** La constante JUSTIFICANTE_REGISTRO_ID_EUSKERA_XML. */
	public static final String JUSTIFICANTE_REGISTRO_ID_EUSKERA_XML = "22";
	
	/** La constante JUSTIFICANTE_REGISTRO_ID_GALLEGO_XML. */
	public static final String JUSTIFICANTE_REGISTRO_ID_GALLEGO_XML = "24";
	
	/** La constante JUSTIFICANTE_REGISTRO_ID_VALENCIANO_XML. */
	public static final String JUSTIFICANTE_REGISTRO_ID_VALENCIANO_XML = "26";
	
	/** La constante CONVOCATORIA_CERRADA. */
	//Estados de Convocatoria
	public static final Integer CONVOCATORIA_CERRADA = 2;
	
	/** La constante CONVOCATORIA_FINALIZADA. */
	public static final Integer CONVOCATORIA_FINALIZADA = 3;
	
	/** La constante CONVOCATORIA_CONFIGURACION. */
	public static final Integer CONVOCATORIA_CONFIGURACION = 4;
	
	/** La constante CONVOCATORIA_PUBLICADA. */
	public static final Integer CONVOCATORIA_PUBLICADA = 5;
	
	/** La constante CONVOCATORIA_ELIMINADA. */
	public static final Integer CONVOCATORIA_ELIMINADA = 6;
	
	/** La constante CONVOCATORIA_APROBADA. */
	public static final Integer CONVOCATORIA_APROBADA = 7;
	
	/** La constante CONVOCATORIA_DESACTIVADA. */
	public static final Integer CONVOCATORIA_DESACTIVADA = 8;
	
	/** La constante CONVOCATORIA_SUBSANADA. */
	public static final Integer CONVOCATORIA_SUBSANADA = 9;
	
	// Migración MiniApplet @firma
	
	/** La constante SIGN_FORMAT_XADES_IMPLICIT. */
	// Formato de firma XADES_IMPLICITO
	public static final String SIGN_FORMAT_XADES_IMPLICIT = "XADES IMPLICITO";

	/** La constante SIGN_FORMAT_CADES. */
	// Formato de firma CADES
	public static final String SIGN_FORMAT_CADES = "CADES";
	
	/** La constante SIGN_FORMAT_PDF. */
	// Formato de firma PDF
	public static final String SIGN_FORMAT_PDF = "PDF";
	
	/** La constante SIGN_FORMAT_XADES_ENVELOPING. */
	// Formato de firma XADES_ENVELOPING
	public static final String SIGN_FORMAT_XADES_ENVELOPING = "XADES ENVELOPING";

	/** La constante JASPER_PATH. */
	// Jasper
	public static final String JASPER_PATH = "JASPER_PATH";
	
	/** La constante RECURSOS_JASPER. */
	public static final String RECURSOS_JASPER = "/jasper/";
	
	/** La constante NO_APLICA. */
	public static final String NO_APLICA = "No aplica";
	
	/** La constante SIN_DOCUMENTOS_ANEXOS. */
	public static final String SIN_DOCUMENTOS_ANEXOS = "No existen documentos anexos";
	
	/** La constante EXTENSION_FORMULARIO. */
	public static final String EXTENSION_FORMULARIO = "html";
	
	/** La constante SEXO_HOMBRE. */
	public static final char SEXO_HOMBRE = 'H';
	
	/** La constante SEXO_MUJER. */
	public static final char SEXO_MUJER = 'M';
	
	/** el nombre fichero cancelado. */
	// Identificador de ficheros cancelados en el proceso de anexado de la inscripción.
	public static String NOMBRE_FICHERO_CANCELADO = "-";
	
	/** el tipo persona fisica. */
	// Clasificación @firma de certificados
	public static String TIPO_PERSONA_FISICA = "0";
	
	/** el tipo persona juridica. */
	public static String TIPO_PERSONA_JURIDICA = "1";
	
	/** el tipo empleado publico. */
	public static String TIPO_EMPLEADO_PUBLICO = "5";
	
	/** el emisor certificado dnie. */
	// Emisor DNIe
	public static String EMISOR_CERTIFICADO_DNIE = "DIRECCION GENERAL DE LA POLICIA";
	
	/** La constante CODIGO_DISCAP_INTELEC. */
	public static final char CODIGO_DISCAP_INTELEC = 'I';
	
	/** La constante CODIGO_TASA_JUSTICIA. */
	// Formulario 790007
	public static final String CODIGO_TASA_JUSTICIA = "007";
	
	/** La constante MODELO79007. */
	public static final String MODELO79007 = "790007";
	
	/** La constante MODELO790001. */
	public static final String MODELO790001 = "790001";
	
	/** La constante DES_DESCARGA_FORM_790007_SECRETARIOS. */
	public static final String DES_DESCARGA_FORM_790007_SECRETARIOS = "Modelo Justicia - Letrados de la Administración de Justicia";
	
	/** La constante PARAMETRO_CONFIGURACION_DEFAULT_TEXT_007. */
	public static final int PARAMETRO_CONFIGURACION_DEFAULT_TEXT_007 = 12;
	
	/** La constante PROVINCIA_NO_APLICA. */
	public static final int PROVINCIA_NO_APLICA = 99;
	
	/** el cod secretario judicial. */
	// Código cuerpo escala de secretarios judiciales
	public static String COD_SECRETARIO_JUDICIAL = "4041";
	
	/** el encabezado generico. */
	// Encabezado Justificante Registro Generico-Justicia
	public static String ENCABEZADO_GENERICO = "REGISTRO DE SOLICITUD DE ADMISIÓN A PRUEBAS SELECTIVAS EN LA ADMINISTRACIÓN PÚBLICA";
	
	/** el encabezado generico ca. */
	public static String ENCABEZADO_GENERICO_CA = "REGISTRE DE SOL · LICITUD D'ADMISSIÓ A PROVES SELECTIVES EN L'ADMINISTRACIÓ PÚBLICA";
	
	/** el encabezado generico eu. */
	public static String ENCABEZADO_GENERICO_EU = "MATRIKULAZIOA ANALISI ESKAERA SELEKTIBOA ADMINISTRAZIO PUBLIKOAN";
	
	/** el encabezado generico gl. */
	public static String ENCABEZADO_GENERICO_GL = "REXISTRO APPLICATION TESTING A SELECTIVA EN ADMINISTRACIÓN PÚBLICA";
	
	/** el encabezado generico vl. */
	public static String ENCABEZADO_GENERICO_VL = "REGISTRE DE SOL · LICITUD D'ADMISSIÓ A PROVES SELECTIVES EN L'ADMINISTRACIÓ PÚBLICA";
	
	/** el encabezado justicia. */
	public static String ENCABEZADO_JUSTICIA = "REGISTRO DE SOLICITUD DE ADMISIÓN A PRUEBAS SELECTIVAS EN LA ADMINISTRACIÓN DE JUSTICIA";
	
	/** el encabezado justicia ca. */
	public static String ENCABEZADO_JUSTICIA_CA = "REGISTRE DE SOL · LICITUD D'ADMISSIÓ A PROVES SELECTIVES EN L'ADMINISTRACIÓ DE JUSTÍCIA";
	
	/** el encabezado justicia eu. */
	public static String ENCABEZADO_JUSTICIA_EU = "MATRIKULAZIOA ANALISI ESKAERA SELEKTIBOA ADMINISTRAZIO JUSTIZIA";
	
	/** el encabezado justicia gl. */
	public static String ENCABEZADO_JUSTICIA_GL = "REXISTRO APPLICATION TESTING A SELECTIVA EN ADMINISTRACIÓN DE JUSTICIA";
	
	/** el encabezado justicia vl. */
	public static String ENCABEZADO_JUSTICIA_VL = "REGISTRE DE SOL · LICITUD D'ADMISSIÓ A PROVES SELECTIVES EN L'ADMINISTRACIÓ DE JUSTÍCIA";
	
	/** el justificante registro. */
	public static String JUSTIFICANTE_REGISTRO = "JUSTIFICANTE DE REGISTRO SOLICITUD PDF";
	
	/** el justificante registro gl. */
	public static String JUSTIFICANTE_REGISTRO_GL = "XUSTIFICANTE DE  REXISTRE DE SOLICITUDE PDF";
	
	/** el justificante registro cat. */
	public static String JUSTIFICANTE_REGISTRO_CAT = "JUSTIFICANT SOL · LICITUD D'INSCRIPCIÓ PDF";
	
	/** el justificante registro vl. */
	public static String JUSTIFICANTE_REGISTRO_VL = "JUSTIFICANT SOL · LICITUD D'INSCRIPCIÓ PDF";
	
	/** el justificante registro eu. */
	public static String JUSTIFICANTE_REGISTRO_EU = "EGIAZTAGIRIA APLIKAZIOA ERREGISTROAREN PDF";
	
	/** el espanhol. */
	// Locale
	public static String ESPANHOL = "es";
	
	/** el espanhol espanha. */
	public static String ESPANHOL_ESPANHA = "es_ES";
	
	/** el catalan. */
	public static String CATALAN = "ca";
	
	/** el gallego. */
	public static String GALLEGO = "gl";
	
	/** el valenciano. */
	public static String VALENCIANO = "vl";
	
	/** el euskera. */
	public static String EUSKERA = "eu";
	
	/** el discapacidad. */
	// Comunidades
	public static String DISCAPACIDAD = "D";
	
	/** el fnumerosa. */
	public static String FNUMEROSA = "FN";

	/** el tipo discapacidad general. */
	// Tipos de discapacidad
	public static String TIPO_DISCAPACIDAD_GENERAL = "1";
	
	/** el tipo discapacidad intelectual. */
	public static String TIPO_DISCAPACIDAD_INTELECTUAL = "2";
	
	/** el tipo discapacidad si. */
	public static String TIPO_DISCAPACIDAD_SI = "3";
	
	/** el tipo discapacidad no. */
	public static String TIPO_DISCAPACIDAD_NO = "4";
	
	/** el reserva discapacidad no. */
	public static char RESERVA_DISCAPACIDAD_NO = 'N';
	
	/** el reserva discapacidad si. */
	public static char RESERVA_DISCAPACIDAD_SI = 'S';
	
	/** el codigo acceso libre. */
	public static String CODIGO_ACCESO_LIBRE = "L";
	
	/** el conforme. */
	// No conforme
	public static String CONFORME = "C";
	
	/** el nocomunidades. */
	// No Comunidades Autónomas
	public static String NOCOMUNIDADES = "NC";
	
	//Constantes de Cl@ve
	/** La constante SP_QAA_PETICION_S. */
	//PARAMETROS
    public static final String SP_QAA_PETICION_S = "N";
    
    /** La constante SING_ATTRIBUTE. */
    public static final String SING_ATTRIBUTE = "signedDoc";
	
	/** La constante PERSONAL_TYPE. */
	public static final String PERSONAL_TYPE = "personal";
	
	/** La constante BUSINESS_TYPE. */
	public static final String BUSINESS_TYPE = "business";
	
	/** La constante LEGAL_TYPE. */
	public static final String LEGAL_TYPE = "legal";
	
	/** La constante SP_CONF. */
	public static final String SP_CONF = "SP";
	
	/** el attr eidentifier. */
	//ATRIBUTOS RESPUESTA Cl@ve
    public static String ATTR_EIDENTIFIER = "eIdentifier";
    
    /** el attr givenname. */
    public static String ATTR_GIVENNAME ="givenName";
    
    /** el attr inheritedfamilyname. */
    public static String ATTR_INHERITEDFAMILYNAME ="inheritedFamilyName";
    
    /** el attr surname. */
    public static String ATTR_SURNAME = "surname";
    
    /** el attr afirmaresponse. */
    public static String ATTR_AFIRMARESPONSE = "afirmaResponse";
    
    /** el attr email. */
    public static String ATTR_EMAIL ="eMail";
    
    /** el attr telephone. */
    public static String ATTR_TELEPHONE ="telephone";
    
    /** el attr qaa. */
    public static String ATTR_QAA = "citizenQAALevel";
    
    /** el attr dateofbirth. */
    public static String ATTR_DATEOFBIRTH = "dateOfBirth";
    
    /** el attr nationality. */
    public static String ATTR_NATIONALITY = "nationalityCode";
    
    /** el disponible. */
    public static String DISPONIBLE = "Available";
    
    /** el persona juridica. */
    public static String PERSONA_JURIDICA = "attribute.allowLegalPerson";
	
    /** La constante DES_FAMILIA_NUMEROSA_GENERAL. */
    // Literales Motivos reduccion-exencion
    public static final String DES_FAMILIA_NUMEROSA_GENERAL = "F. NUMEROSA GENERAL";
    
    /** La constante DES_FAMILIA_NUMEROSA_ESPECIAL. */
    public static final String DES_FAMILIA_NUMEROSA_ESPECIAL = "F. NUMEROSA ESPECIAL";
    
    /** La constante DES_DISCAPACIDAD. */
    public static final String DES_DISCAPACIDAD = "DISCAPACIDAD";
    
    /** La constante MODELOENBLANCO. */
    //ModeloenBlanco
    public static final String MODELOENBLANCO = "modelo790enBlanco";
}
