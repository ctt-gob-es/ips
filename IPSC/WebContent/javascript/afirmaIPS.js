var idRegistro;
var varContexto;
var varVariable;

/**
 * Metodo que comprueba la carga correcta del miniapplet y realiza x reintentos en caso negativo.
 */
function comprobarCargaLimitada(index) {

	if (index == undefined) {
		index = 0;
	}

	// Se haran 6 intentos
	if (index < 6) {	
		try {
			if (MiniApplet.echo() == "Cliente JavaScript") {
				
				if(index==2){
					if(document.getElementById("espera")){
		    			document.getElementById("espera").style.display = "none";
		    		}
				}
				
				throw "No cargado";
			}

			firmarAcceso();
			
			return;
		}
		catch(e) {
			setTimeout(comprobarCargaLimitada, 4000, index + 1);	// Comprobacion cada 4 segundos
		}
	}
	else {
		if(document.getElementById("espera")){
			document.getElementById("espera").style.display = "none";
		}
		alert("Se ha sobrepasado el tiempo maximo de carga. Por favor, recargue la página.");	
	}
}


/**
 * Metodo que genera un token aleatorio y lo firma con Xades para extraer el certificado en servidor.
 * Variable: id de solicitud seleccionada para la inscripcion.
 */
function firmarAcceso(){

	try {
		// Generacion de token aleatorio
		var text = "" +Math.random()*11;
		
		if(clienteFirma == null){
			clienteFirma = document.getElementById("miniApplet");
		}
		
		var error = "";
		var signature="";

		try {
			if(document.getElementById("signerCertReg")){
				var certificadoRegistro=document.getElementById("signerCertReg").value;
				if(certificadoRegistro!="" && certificadoRegistro.length>25){
					signature = certificadoRegistro;
				}
			}
			
			if(signature==""){
				var dataB64 = MiniApplet.getBase64FromText (text, null);
				signature = MiniApplet.sign(dataB64, signatureAlgorithm, signatureFormat, null);				
			}
			
			document.getElementById("certificado").value = signature;
			//document.getElementById("id").value = variable;
			document.getElementById("formulario").submit();
			//idRegistro = "";
			
		} catch (e) {
			if (MiniApplet.getErrorType() == 'es.gob.afirma.core.AOCancelledOperationException') {
				error = "operationCancelled";			
			} else if (MiniApplet.getErrorType() == 'es.gob.afirma.keystores.common.AOCertificatesNotFoundException') {
				error = "No se ha encontrado certificado en el almacen";
			} else {
				alert("Error en la ejecución del applet");
				error = MiniApplet.getErrorMessage();
			}
		}
	}
	catch (e) {
		alert("Error en la ejecución del applet de firma.");
	}
}

/**
 * Funcion que realiza la carga del miniapplet de @firma para el pago y el registro
 */
function cargarMiniAppletFirma3(){
	if(document.getElementById('miniApplet')==null){
		MiniApplet.cargarMiniApplet(base);
		MiniApplet.setStickySignatory(true);
	}
	setTimeout(function(){comprobarCargaLimitada3(null);}, 1000);
}

/**
 * Metodo que comprueba la carga correcta del miniapplet y realiza x reintentos en caso negativo.
 */
function comprobarCargaLimitada3(index) {

	if (index == undefined) {
		index = 0;
	}

	// Se haran 10 intentos
	if (index < 10) {	
		try {
			if (MiniApplet.echo() == "Cliente JavaScript") {
				throw "No cargado";
			}

			return;
		}
		catch(e) {
			setTimeout(comprobarCargaLimitada, 4000, index + 1);	// Comprobacion cada 4 segundos
		}
	}
	else {
		if(document.getElementById("espera")){
			document.getElementById("espera").style.display = "none";
		}
		alert("Se ha sobrepasado el tiempo maximo de carga. Por favor, recargue la página.");	
	}
}

/**
 * Determina con un boolean si el navegador es Microsoft Edge
 */
function isEdge() {
	return !!navigator.userAgent.match(/Edge\/\d+/);
}

/** Indica si el navegador detecta Java. Este valor no es completamente fiable, ya que
 * Internet Explorer siempre indica que si esta activado. */
function isJavaEnabled() {
	return navigator.javaEnabled();
}