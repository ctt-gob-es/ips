var idRegistro;
var varContexto;
var varVariable;

/*
 * Funcíon que realiza la carga del miniapplet de @firma
 */
function cargarMiniAppletFirma(){
	if(document.getElementById('miniApplet')==null){
		MiniApplet.cargarMiniApplet(base);
	}

	// Identificador de la convocatoria seleccionada.
	setTimeout(function(){comprobarCargaLimitada(null);}, 1000);
}

/*
 * Método que comprueba la carga correcta del miniapplet y realiza x reintentos en caso negativo.
 */
function comprobarCargaLimitada(index) {

	if (index == undefined) {
		index = 0;
	}

	// Se haran 6 intentos
	if (index < 6) {	
		try {
			if (MiniApplet.echo() == "Cliente JavaScript") {
				throw "No cargado";
			}

			firmarAcceso(varContexto);
			
			return;
		}
		catch(e) {
			setTimeout(comprobarCargaLimitada, 4000, index + 1);	// Comprobacion cada 4 segundos
		}
	}
	else {
		alert("Se ha sobrepasado el tiempo maximo de carga. Por favor, recargue la página.");	
	}
}

/*
 * Método que genera un token aleatorio y lo firma con Xades para extraer el certificado en servidor.
 */
function firmarAcceso(){

	try {
		// Generación de token aleatorio
		var text = "" +Math.random()*11;
		clienteFirma = document.getElementById("miniApplet");
		var error = "";

		try {
			var dataB64 = MiniApplet.getBase64FromText (text, null);
			var signature = MiniApplet.sign(dataB64, signatureAlgorithm, signatureFormat, null);
			document.getElementById("nuevoCertif").value = signature;
			document.getElementById("formularioCert").submit();
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

/*
 * Función que realiza la carga del miniapplet para la comproboción de servicios ePAGO y @Firma
 */
function cargarMiniAppletFirma2(variable, contexto){
	if(document.getElementById('miniApplet')==null){
		MiniApplet.cargarMiniApplet(base);
	}

	// Indicador de enlace pulsado: WSFirma o WSPago.
	varVariable = variable;
	varContexto = contexto;

	setTimeout(function(){comprobarCargaLimitada2(null);}, 1000);
}

/*
 * Método que comprueba la carga correcta del miniapplet y realiza x reintentos en caso negativo.
 */
function comprobarCargaLimitada2(index) {

	if (index == undefined) {
		index = 0;
	}

	// Se haran 10 intentos
	if (index < 10) {	
		try {
			if (MiniApplet.echo() == "Cliente JavaScript") {
				throw "No cargado";
			}

			firmarAcceso2(varVariable, varContexto);

			return;
		}
		catch(e) {
			setTimeout(comprobarCargaLimitada2, 4000, index + 1);	// Comprobacion cada 4 segundos
		}
	}
	else {
		alert("Se ha sobrepasado el tiempo maximo de carga. Por favor, recargue la página.");	
	}
}

/*
 * Método que genera un token aleatorio y lo firma con Xades para extraer el certificado en servidor.
 */
function firmarAcceso2(variable, contexto){

	try {
		// Generación de token aleatorio
		var text = "" +Math.random()*11;
		clienteFirma = document.getElementById("miniApplet");
		var error = "";

		try {
			var dataB64 = MiniApplet.getBase64FromText (text, null);
			var config = "mode=explicit\npolicy=FirmaAGE";
			
			if(variable=="WSFirma"){
				signature = MiniApplet.sign(dataB64, signatureAlgorithm, signatureFormat, config);
				document.getElementById("signature").value = signature;
				document.getElementById("probarServiciosExterno").submit();
			}
			
			variable = "";
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

/*
 * Funcíon que realiza la carga del miniapplet de @firma para la prueba de pasarela
 */
function cargarMiniAppletFirma3(){
	if(document.getElementById('miniApplet')==null){
		MiniApplet.cargarMiniApplet(base);
	}

	setTimeout(function(){comprobarCargaLimitada3(null);}, 1000);
}

/*
 * Método que comprueba la carga correcta del miniapplet y realiza x reintentos en caso negativo.
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
			firmarAcceso3(varVariable, varContexto);
			return;
		}
		catch(e) {
			setTimeout(comprobarCargaLimitada3, 4000, index + 1);	// Comprobacion cada 4 segundos
		}
	}
	else {
		alert("Se ha sobrepasado el tiempo maximo de carga. Por favor, recargue la página.");	
	}
}

function firmarAcceso3(varVariable, varContexto){
	
	try {
		if(MiniApplet.echo() != "Cliente JavaScript"){
			// Datos a firmar
			var oFirma = MiniApplet.getBase64FromText(origenFirma(),null);
			var config = "mode=explicit\npolicy=FirmaAGE";
			ok = MiniApplet.sign(oFirma, signatureAlgorithm, signatureFormatPago, config);
			
			if(ok!=null){
				document.getElementById("signature").value = ok;
				document.forms[0].submit();
				//return true;
			}
		}
	}catch (e) {
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


