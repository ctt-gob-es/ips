var NUMPROVINCIAS = 52;
if(document.getElementById("importeSolicitud") != null || document.getElementById("importeSolicitud") != undefined){
var importeSol = document.getElementById("importeSolicitud").value;
}
if (document.getElementById("importe")) {
	var importeOriginal = document.getElementById("importe").value;
}
if(document.getElementById("importeSolicitudDecimal") != null || document.getElementById("importeSolicitudDecimal") != undefined){
var importedecimal = document.getElementById("importeSolicitudDecimal").value;
}
// Funciones que validan los datos que se introducen por el ciudadano mientras
// esta cumplimentando el formulario 790

function cambiarMayusculas(ctrl) {
	ctrl.value = ctrl.value.toUpperCase();
}

function igualarMinisterios(ctrl) {
	document.getElementById('ministerioAutomatico').value = ctrl.value;
}

function cambiarMayusculasText(ctrl) {
	ctrl.value = ctrl.value.toUpperCase();
	var registro = ctrl.value;
	var resultado = quitarDolar(registro);
	if (resultado == "error") {
		ctrl.value = "";
		return false;
	}
	return true;
}

function quitarDolar(registro) {
	for (var i = 0; i < registro.length; i++) {
		if (registro.charAt(i) == '$') {
			alert(dolar);
			return "error";
		}
	}
	return "correcto";
}
function quitarDolarCorreo(ctrl) {
	var registro = ctrl.value;
	for (var i = 0; i < registro.length; i++) {
		if (registro.charAt(i) == '$') {
			alert(dolar);
			ctrl.value = "";
			return false;
		}
	}
	return true;
}

function soloNumeros(evt) {
	if (window.event) {
		keynum = evt.keyCode;
	} else {
		keynum = evt.which;
	}

	if (keynum > 47 && keynum < 58 || keynum == 0 || keynum == 8) {
		return true;
	} else {
		evt.preventDefault();
		return false;
	}
}

function soloLetras(evt) {
	if (window.event) {
		keynum = evt.keyCode;
	} else {
		keynum = evt.which;
	}

	if (keynum > 47 && keynum < 58) {
		evt.preventDefault();
		return false;
	} else {
		return true;
	}
}

function validarTelefono(ctrl) {

	var tel = new String(ctrl.value);

	if (tel.length != 11 && tel.length != 9 && tel.length != 0) {
		ctrl.focus();
		return false;
	}
	return true;
}

function validarCodigoPostal(ctrl) {

	var codigo = new String(ctrl.value);

	if (codigo.length != 5 && codigo.length != 0) {
		return false;
	}
	return true;

}

function validarCP() {
	var codigoprovincia = document.getElementById("codigoProvinciaDomicilio").value;
	var cpostal = document.getElementById("codigoPostalDomicilio").value;
	var cp = cpostal.substring(0, 2);
	if (codigoprovincia > NUMPROVINCIAS || cp != codigoprovincia)
		return false;
	else
		return true;
}
function isInt(x) {
	var y = parseInt(x);
	if (isNaN(y))
		return false;
	return x == y && x.toString() == y.toString();
}

var Boe = "Boe";
var Nac = "Nac";

function validarCorreo() {

	var mailres = true;
	var cadena = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ1234567890@._-";
	var arroba = 0;
	var punto = 0;
	var texto = document.getElementById("correoElectronicos").value;

	if (texto != "") {
		// comprobacion de si existe mas de una @
		arroba = texto.indexOf("@", 0);
		if ((texto.lastIndexOf("@")) != arroba) {
			arroba = -1;
		}

		// comprobacion de que existen caracteres despues del ultimo punto
		punto = texto.lastIndexOf(".");

		// Comprobacion de que los caracteres de la direccion son correctos
		for (var i = 0; i < texto.length; i++) {
			if (cadena.indexOf(texto.substr(i, 1), 0) == -1) {
				mailres = false;
				break;
			}
		}

		if ((arroba <= 0) || (arroba + 1 >= punto)
				|| (punto + 1 == (texto.length)) || (mailres == false)
				|| (texto.indexOf("..", 0) != -1)) {
			if ($("#setFocus").val() == "") {
				$("#setFocus").val('correoElectronicos');
			}
			return false;
		}
	}
	return true;
}

function comprobarDiscapacidad(ctrl) {
	// Si el valor es válido dejar el check
	if (ctrl.value > 0 && ctrl.value < 101) {
		var limpiar = true;
		var porcentajeMinDiscapacidad = document
				.getElementById("porcentajeMinDiscapacidad").value;
		var numPorcentajeMinDiscapacidad = parseInt(porcentajeMinDiscapacidad,
				10);
		if (ctrl.value != "") {
			document.getElementById("detalleDiscapacidad").disabled = false;
			var valor = parseInt(ctrl.value);

			// Si >= 33 y < 100
			if (valor >= numPorcentajeMinDiscapacidad && valor < 101) {
				limpiar = false;
				discapacidad = true;
				// Se activa el desplegable de la comunidad
				document.getElementById("idComunidadDD").disabled = false;
				$("[for='state'] span")
						.removeClass("pae-form__label--disabled").addClass(
								"pae-form__label--text");
				$("#importeHTML").val("0");
				$("#importeSolicitud").val("0");
				$("#importeSolicitudDecimal").val("0");
				document.getElementById("formasDePago").className += " pae-hidden";

				motivoReduccion(document.getElementById("checkbox-exemption-1"));
				// Se desactivan el resto de checkboxes
				for (var i = 2; i < 5; i++) {
					document.getElementById("checkbox-exemption-" + i).disabled = true;
				}
				// Inhabilitamos la posibilidad de indicar algun tipo de pago
				// document.getElementById("pagoEfectivo").checked=false;
				// document.getElementById("bank-account").checked=false;

				// Se establece "CCAA en la que se reconoce su discapacidad"
				// obligatorio
				$("#oblComunidadDDExento").removeAttr("style");

			}
			// Si < 33
			else {
				// Se desactiva el campo comunidad
				$("[for='state'] span").addClass("pae-form__label--disabled")
						.removeClass("pae-form__label--text");
				// document.getElementById("checkbox-exemption-1").click();
				// document.getElementById("checkbox-exemption-1").checked ==
				// false;
				document.getElementById("idComunidadDD").disabled = true;
				document.getElementById("spanComunidadDDExento").className = "pae-form__label--disabled";
				// document.getElementById("detalleDiscapacidad").disabled =
				// true;
				document.getElementById("idComunidadDD").value = "0";
				// $(".pae-form__advisor-tooltip--dynamic").hide();
				document.getElementById("formasDePago").className = "";
				$("#oblComunidadDDExento").attr("style", "display:none;");
			}
		}
			
		if (!limpiar) {
			actualizarImporte("1");
			if (discapacidad) { // Comprobamos que exista grado mayor de 33 y
				// ponemos el importe a 0 y quitamos la opciones
				// de pago
				$("#importeHTML").val("0");
				importeSolicitud.value = 0;
				importeSolicitudDecimal.value = 0;
				document.getElementById("formasDePago").className += " pae-hidden";
			}
		}
		// Si < 33
		else {
			// Se activan el resto de checkboxes
			document.getElementById("checkbox-exemption-2").disabled = false;
			document.getElementById("checkbox-exemption-3").disabled = false;
			document.getElementById("checkbox-exemption-4").disabled = false;

			// Habilitamos la posibilidad de indicar algun tipo de pago
			// document.getElementById("pagoEfectivo").disabled=false;
			// document.getElementById("bank-account").disabled=false;
			motivoReduccion(ctrl);
			document.getElementById("importeSolicitud").value = importeSol;
			document.getElementById("importeSolicitudDecimal").value = importedecimal;
			$("#importe").val(
					$("#importeSolicitud").val()
							+ $("#importeSolicitudDecimal").val() / 100);
			document.getElementById("reservation-no").checked = true;
		}

		return true;
	}
	// Si no hay nada o el valor no es válido
	else {
		if (ctrl.value != '' && (ctrl.value <= 0 || ctrl.value > 100)) {
			alert('No se puede introducir un valor superior al 100 %');
		}
		$("[for='state'] span").addClass("pae-form__label--disabled")
				.removeClass("pae-form__label--text");
		document.getElementById("formasDePago").className = "";
		actualizarImporte(ctrl.value);

		document.getElementById("detalleDiscapacidad").disabled = true;
		document.getElementById("detalleDiscapacidad").value = null;
		document.getElementById("idComunidadDD").disabled = true;
		document.getElementById("idComunidadDD").value = "0";
		document.getElementById("reservation-no").checked = true;

		// Si el valor no es válido se activan el resto de checkbox
		document.getElementById("checkbox-exemption-2").disabled = false;
		document.getElementById("checkbox-exemption-3").disabled = false;
		document.getElementById("checkbox-exemption-4").disabled = false;

		// Si el check estaba marcado y tiene un valor no válido se desmarca
		if (document.getElementById("checkbox-exemption-1").checked == true
				&& ctrl.value != '') {
			document.getElementById("checkbox-exemption-1").click();
		}
		ctrl.value = null;
		$("#oblComunidadDDExento").attr("style", "display:none;");
		return false;
	}
}

function comprobarDiscapacidad2(ctrl) {
	// Si el valor es válido
	if (ctrl.value != '' && ctrl.value > 0 && ctrl.value <= 100) {
		var limpiar = true;
		var porcentajeMinDiscapacidad = document
				.getElementById("porcentajeMinDiscapacidad").value;
		var numPorcentajeMinDiscapacidad = parseInt(porcentajeMinDiscapacidad, 10);

		// Se activa el campo detalle
		document.getElementById("detalleDiscapacidad").disabled = false;
		var valor = parseInt(ctrl.value);

		// Si >= 33 y < 100
		if (valor >= numPorcentajeMinDiscapacidad && valor <= 100) {
			limpiar = false;
			discapacidad = true;
			// Se activa el desplegable de la comunidad
			document.getElementById("idComunidadDD").disabled = false;
			$("[for='state'] span").removeClass("pae-form__label--disabled")
					.addClass("pae-form__label--text");

			$("#importeHTML").val("0");
			$("#importeSolicitud").val("0");
			$("#importeSolicitudDecimal").val("0");
			document.getElementById("formasDePago").className += " pae-hidden";
			//$("#oblComunidadDDExento").html("*");
			
			// Se establece "CCAA en la que se reconoce su discapacidad"
			// obligatorio
			$("#oblComunidadDDExento").removeAttr("style");
		}
		// Si < 33
		else {
			// Se desactiva el campo comunidad
			$("[for='state'] span").addClass("pae-form__label--disabled")
					.removeClass("pae-form__label--text");
			document.getElementById("idComunidadDD").disabled = true;
			document.getElementById("spanComunidadDDExento").className = "pae-form__label--disabled";
			//$("#oblComunidadDDExento").html("");
			document.getElementById("idComunidadDD").value = "0";
			// $(".pae-form__advisor-tooltip--dynamic").hide();
			document.getElementById("formasDePago").className = "";
			$("#oblComunidadDDExento").attr("style", "display:none;");
			
			document.getElementById("reservation-no").checked = true;
			document.getElementById("reservation-general").checked = false;
			document.getElementById("reservation-intellectual").checked = false;
		}
	}
	// Si está vacío o el valor no es válido
	else {
		// Se desactivan las formas de pago
		$("[for='state'] span").addClass("pae-form__label--disabled")
				.removeClass("pae-form__label--text");
		document.getElementById("formasDePago").className = "";
		actualizarImporte(ctrl.value);

		// Se desactivan los campos detalle y comunidad
		document.getElementById("detalleDiscapacidad").disabled = true;
		document.getElementById("detalleDiscapacidad").value = null;
		document.getElementById("idComunidadDD").disabled = true;
		document.getElementById("idComunidadDD").value = "0";
		document.getElementById("reservation-no").checked = true;
		document.getElementById("reservation-general").checked = false;
		document.getElementById("reservation-intellectual").checked = false;
	
		// Si es <= 0 o >= 100
		if (ctrl.value != '') {
			alert('No se puede introducir un valor igual o inferior al 0% ni superior al 100%');
			// Si el check estaba marcado y tiene un valor no válido se desmarca
			if (document.getElementById("checkbox-exemption-1").checked == true) {
				document.getElementById("checkbox-exemption-1").click();
			}
		}				
		
		ctrl.value = null;
		$("#oblComunidadDDExento").attr("style", "display:none;");
		return false;

	}
}

function limpiarCerosIzquierda(ctrl) {

	cadena = ctrl.value;
	hayCeros = true;
	while ((cadena.length > 1) && (hayCeros)) {
		if (cadena.charAt(0) == "0") {
			cadena = eliminaCaracter(cadena, 0);
		} else {
			hayCeros = false;
		}
	}
	ctrl.value = cadena;

	return true;
}

function rellenaCerosCentimos(ctrl) {

	var strCentimos = ctrl.value;

	if (strCentimos.length == 1) {
		strCentimos += "0";
	}

	ctrl.value = strCentimos;
}

function eliminaCaracter(cadena, posicion) {

	var cadResult;

	cadResult = cadena.substr(0, posicion)
			+ cadena.substr((posicion + 1), (cadena.length - 1));

	return cadResult;
}

function cambioFormaPago(ctrl) {

	document.getElementById("boxPago").className = "pae-box";
	if (ctrl.value != "E") {
		$("#formaPago").val("A");
		document.getElementById("divAdeudo").className = "pae-layout";
		document.getElementById("infoAdeudo").className = "pae-layout";
		document.getElementById("bank-account").checked = true;
		document.getElementById("pagoEfectivo").checked = false;

	} else {
		$("#formaPago").val("E");
		document.getElementById("divAdeudo").className = "pae-layout pae-hidden";
		document.getElementById("infoAdeudo").className = "pae-layout pae-hidden"
		document.getElementById("IBANInput").value = "ES";
		document.getElementById("IBANInput").className = "pae-form__input";
		document.getElementById("bank-account").checked = false;
		document.getElementById("pagoEfectivo").checked = true;
	}

}

function comprobarAdeudo(numeroCuentaCompleto) {
	if (numeroCuentaCompleto.length == 28) {
		document.getElementById("IBANInput").className = "pae-form__input";
		$("#iban").val(numeroCuentaCompleto.substring(0, 4));
		$("#entidad").val(numeroCuentaCompleto.substring(5, 9));
		$("#oficina").val(numeroCuentaCompleto.substring(10, 14));
		$("#digitoControl").val(numeroCuentaCompleto.substring(15, 17));
		$("#numeroCuenta").val(numeroCuentaCompleto.substring(18));
	} else if (numeroCuentaCompleto.length == 24) {
		document.getElementById("IBANInput").className = "pae-form__input";
		$("#iban").val(numeroCuentaCompleto.substring(0, 4));
		$("#entidad").val(numeroCuentaCompleto.substring(4, 8));
		$("#oficina").val(numeroCuentaCompleto.substring(8, 12));
		$("#digitoControl").val(numeroCuentaCompleto.substring(12, 14));
		$("#numeroCuenta").val(numeroCuentaCompleto.substring(14));
	} else {
		document.getElementById("IBANInput").className = "pae-form__input pae-form__input--error";
	}
}

function motivoReduccion(ctrl) {

	// Se inicializan los campos de los formularios que están ocultos
	document.getElementById("comunidadesFN").className = "pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm pae-hidden";
	document.getElementById("idComunidadFN").value = "";
	document.getElementById("idNumeroTitulo").className = "pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm pae-hidden";
	document.getElementById("numeroTituloFN").value = "";
	document.getElementById("datosDiscapacidad").className = "pae-layout__item pae-u-12/12 pae-u-12/12-lap pae-u-1/1-palm pae-margin-top pae-hidden";
	$('#porcentajeDiscapacidad').val('');
	$('#detalleDiscapacidad').val('');
	$('#idComunidadDD').val('0');
	cambioTipoDiscapacidad();
	
	var iteracion = document.getElementsByName("motivoRedEx");

	// Limpiamos el resto de checkbox
	for (var k = 1; k <= iteracion.length; k++) {
		if (!(document.getElementById("checkbox-exemption-" + k)
				&& document.getElementById("checkbox-exemption-" + k).checked == true && document
				.getElementById("checkbox-exemption-" + k).value == ctrl.value)) {
			document.getElementById("checkbox-exemption-" + k).checked = false;
			d = $("[for='" + "checkbox-exemption-" + k + "']").attr("id");
			document.getElementById(d).className = "pae-form__custom-check-label pae-form__custom-check-label--option checked";
		} else {
			document.getElementById("checkbox-exemption-" + k).checked = true;
			d = $("[for='" + "checkbox-exemption-" + k + "']").attr("id");
			document.getElementById(d).className = "pae-form__custom-check-label pae-form__custom-check-label--option unchecked";
		}
	}

	if (ctrl.value == "1") {
		ctrl.click();
		if (ctrl.checked == true) {
			document.getElementById("idComunidadDD").disabled = false;
			document.getElementById("spanComunidadDDExento").className = "pae-form__label--text";
			document.getElementById("detalleDiscapacidad").disabled = false;
			document.getElementById("formasDePago").className += " pae-hidden";
			/*$("[for='state'] span").addClass("pae-form__label--disabled").removeClass("pae-form__label--text");
			$("#oblComunidadDDExento").attr("style", "display:none;");
			$("#oblComunidadDDExento").className = "pae-form__label--disabled";
			//Borra el asterisco
			$("#oblComunidadDDExento").html("");*/

			$('#datosDiscapacidad').removeClass('pae-hidden');

		} else {
			document.getElementById("idComunidadDD").disabled = true;
			document.getElementById("spanComunidadDDExento").className = "pae-form__label--disabled";
			document.getElementById("detalleDiscapacidad").disabled = true;
			document.getElementById("idComunidadDD").value = "0";
			ctrl.checked == false;
			// document.getElementById("porcentajeDiscapacidad").value = "";
			document.getElementById("formasDePago").className = "";
		}
		// Habilitamos la posibilidad de indicar algun tipo de pago en caso de
		// equivocacion

		document.getElementById("pagoEfectivo").checked = true;
		document.getElementById("bank-account").checked = false;
		actualizarImporte(ctrl.value);

	} else if (ctrl.value == "2") {
		if (ctrl.checked == true) {
			document.getElementById("formasDePago").className += " pae-hidden";
		} else {
			document.getElementById("formasDePago").className = "";
		}
		document.getElementById("idComunidadDD").disabled = true;
		document.getElementById("pagoEfectivo").checked = true;
		document.getElementById("bank-account").checked = false;
		actualizarImporte(ctrl.value);
	} else if (ctrl.value == "3") {
		if (ctrl.checked == true) {
			document.getElementById("comunidadesFN").className = "pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm pae-margin-top";
			document.getElementById("formasDePago").className += " pae-hidden";
		} else {
			document.getElementById("formasDePago").className = "";
		}
		document.getElementById("idComunidadDD").disabled = true;
		document.getElementById("pagoEfectivo").checked = true;
		document.getElementById("bank-account").checked = false;
		actualizarImporte(ctrl.value);

	} else if (ctrl.value == "4") {
		if (ctrl.checked == true) {
			document.getElementById("formasDePago").className += " pae-hidden";
		} else {
			document.getElementById("formasDePago").className = "";
		}
		document.getElementById("idComunidadDD").disabled = true;
		document.getElementById("pagoEfectivo").checked = true;
		document.getElementById("bank-account").checked = false;
		actualizarImporte(ctrl.value);

		// Habilitamos la posibilidad de indicar algun tipo de pago
	} else if (ctrl.value == "5") {
		if (ctrl.checked == true) {
			document.getElementById("comunidadesFN").className = "pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm pae-margin-top";
		}
		document.getElementById("idComunidadDD").disabled = true;
		document.getElementById("formasDePago").className = "";
		document.getElementById("pagoEfectivo").checked = true;
		document.getElementById("bank-account").checked = false;
		actualizarImporte(ctrl.value);
	} else if (ctrl.value == "6") {
		if (ctrl.checked == true) {
			ctrl.disabled = false;
			document.getElementById("formasDePago").className += " pae-hidden";
		} else {
			document.getElementById("formasDePago").className = "";
		}
		document.getElementById("idComunidadDD").disabled = true;
		document.getElementById("pagoEfectivo").checked = true;
		document.getElementById("bank-account").checked = false;
		actualizarImporte(ctrl.value);
	} else {
		// Habilitamos la posibilidad de indicar algun tipo de pago
		if (document.getElementById("checkbox-exemption-1").checked == true) {
			document.getElementById("checkbox-exemption-1").click();
		}
		document.getElementById("idComunidadDD").disabled = true;
		document.getElementById("pagoEfectivo").checked = true;
		document.getElementById("bank-account").checked = false;
		actualizarImporte(ctrl.value);
	}
	// cambioFormaPago(ctrl);
	$("#formaPago").val("E");

	var idSeleccionado = ctrl.value;
	var check = ctrl.checked;
	// Si se marca discapacidad, se establece "CCAA en la que se reconoce su
	// discapacidad" como obligatorio
	if (idSeleccionado == 1) {
		// Si esta checkeado
		if (check) {
			$("#oblComunidadDDExento").removeAttr("style");
		} else {
			$("#oblComunidadDDExento").attr("style", "display:none;");
		}
	} else {
		$("#oblComunidadDDExento").attr("style", "display:none;");
	}
}

function comunidadFNChecked() {

	document.getElementById("idNumeroTitulo").className = "pae-hidden";
	document.getElementById("numeroTituloFN").value = "";
	numTituloRequerido();
}

function numTituloRequerido() {
	var listaComunidadesReqTitulo = document
			.getElementById("comunidadesReqTitulo").value;
	var encontrado = false;
	var i = 0;
	// Comprobamos si la comunidad autonoma seleccionada cuando es exento
	if (document.getElementById("comunidadesFN").className == "pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm pae-margin-top") {
		var comunidadSelect = document.getElementById("idComunidadFN").value;
		var comunidadesRequierenTitulo = listaComunidadesReqTitulo.split('##');

		while (i <= comunidadesRequierenTitulo.length && encontrado == false) {

			if (comunidadesRequierenTitulo[i] != "") {
				// Comprobamos si la comunidad autonoma seleccionada requiere
				// que se inserte en numero de titulo de FN
				if (comunidadSelect == comunidadesRequierenTitulo[i]) {
					document.getElementById("idNumeroTitulo").className = 'pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm pae-margin-top';
					encontrado = true;
				} else {
					i++;
				}
			} else {
				i++;
			}
		}
	} else {
		document.getElementById("idNumeroTitulo").className == "pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm pae-hidden";
	}
	return encontrado;
}

function actualizarImporte(idSeleccionado) {
	var find = false;
	var iteracion = document.getElementsByName("motivoRedEx");
	for (var i = 1; i <= iteracion.length; i++) {
		if (document.getElementById("checkbox-exemption-" + i).checked == true)
			find = true
	}
	if (find) {
		var listaOrgIniSel = document.getElementById("motivosTipificado").value;
		listaOrgIniSel = listaOrgIniSel.replace("[", "");
		listaOrgIniSel = listaOrgIniSel.replace("]", "");
		var orgsSelect = listaOrgIniSel.split('##');
		var id = 0;
		var porcentaje = 0;
		var importeNuevo = 0;
		var descuento = 0;

		if (document.getElementById("importe") != null) {
			for (var i = 1; i <= orgsSelect.length; i++) {
				if (orgsSelect[i] != "" && orgsSelect[i] != null) {
					id = orgsSelect[i];
					i++;
				}
				if (orgsSelect[i] != "" && orgsSelect[i] != null) {
					porcentaje = orgsSelect[i];
				}
				if (id == idSeleccionado) {
					descuento = (importeOriginal * porcentaje / 100);
					importeNuevo = Math
							.round((importeOriginal - descuento) * 100) / 100;
					var imp = importeNuevo.toString().split('.');
					document.getElementById("importeSolicitud").value = imp[0];
					if (imp.length == 1) {
						document.getElementById("importeSolicitudDecimal").value = "00";
					} else {
						document.getElementById("importeSolicitudDecimal").value = imp[1];
					}
				}
			}
		} else {
			document.getElementById("importeSolicitud").value = "0";
			document.getElementById("importeSolicitudDecimal").value = "00";
		}
		$("#importeHTML").val(importeNuevo);
	} else {
		$("#importeHTML").val(importeOriginal);
		var imp = importeOriginal.toString().split('.');
		document.getElementById("importeSolicitud").value = imp[0];
		if (imp.length == 1) {
			document.getElementById("importeSolicitudDecimal").value = "00";
		} else {
			document.getElementById("importeSolicitudDecimal").value = imp[1];
		}
	}
}

// El tipico trim que inexplicamente JavaScript no trae implementado
function trim(texto) {
	return texto.replace(/^\s+/g, '').replace(/\s+$/g, '');
}

// Funcion que comprueba el IBAN
function validaIBAN(IBAN) {
	var resto = 0;
	IBAN = IBAN.toUpperCase();
	IBAN = trim(IBAN); // Quita espacios al principio y al final
	IBAN = IBAN.replace(/\s/g, ""); // Quita espacios del medio
	var num1, num2;
	var isbanaux;
	if (IBAN.length != 24) { // En España el IBAN son 24 caracteres
		return false;
	} else {
		num1 = getNumIBAN(IBAN.substring(0, 1));
		num2 = getNumIBAN(IBAN.substring(1, 2));
		isbanaux = IBAN.substr(4) + String(num1) + String(num2)
				+ IBAN.substr(2, 2);
		resto = mod(isbanaux, 97);
		if (resto == 1)
			return true;
		else
			return false;
	}
}

// Funcion que devuelve los numeros correspondientes a cada letra
function getNumIBAN(letra) {
	var letras = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
	return letras.search(letra) + 10;
}

// Funcion que calcula el modulo sin hacer ninguna division
function mod(dividendo, divisor) {
	var cDividendo = '';
	var cResto = '';

	for (var i = 0; i < dividendo.length; i++) {
		var cChar = dividendo[i];
		var cOperador = cResto + '' + cDividendo + '' + cChar;

		if (cOperador < parseInt(divisor)) {
			cDividendo += '' + cChar;
		} else {
			cResto = cOperador % divisor;
			if (cResto == 0) {
				cResto = '';
			}
			cDividendo = '';
		}
	}
	cResto += '' + cDividendo;
	if (cResto == '') {
		cResto = 0;
	}
	return cResto;
}

function alEnviar() {
	var errores;
	errores = validarFormulario();
	if (validarDatos() == 1) {
		document.getElementById("rellenarCampos").className = "pae-alertbox pae-alertbox--warning";
		if ($("#setFocus").val() != "") {
			document.getElementById($("#setFocus").val()).focus();
		}
		$("#setFocus").val("");
		return false;
	} else {
		document.getElementById("rellenarCampos").className = "pae-alertbox pae-alertbox--warning pae-hidden";
	}

	if (errores == "" || errores == null) {
		return true;
	} else {
		alert(unescape(errores));
		return false;
	}
}

function validarFormulario() {

	var errores = "";

	errores = validarParteEspecifica();

	errores = errores + validarParteComun();

	return errores;
}

function validarParteEspecifica() {
	return "";
}

function validarParteComun() {

	var result = "";
	var comprobacion = true;

	// validacion de que no existen caracteres raros en los campos del
	// formulario
	if (!validarCaracteresEspeciales()) {
		result = result + escape(caractEspeciales) + "\n";
	}

	// validacion formato del codigo postal
	if (document.getElementById("pais").value == 1) { // Solo obligatorio y se
		// valida cuando el pais
		// es españa
		if (document.getElementById("codigoPostalDomicilio").value != "") {
			if (!validarCodigoPostal(document
					.getElementById("codigoPostalDomicilio"))) {
				result = result + escape(CPFormat) + "\n";
			} else if (document.getElementById("codigoPostalDomicilio").value != ""
					&& document.getElementById("codigoProvinciaDomicilio").value != "") {// validacion
				// codigo
				// postal
				// y
				// provinciaDomicilio
				if (!validarCP())
					result = result + escape(codPostal) + "\n";
			}
		}
	}

	// validacion correo Electronico
	if (document.getElementById("correoElectronicos").value != "") {
		if (!validarCorreo()) {
			result = result + escape(email) + "\n";
		}
	}

	// validacion de que el a�o de la convocatoria esta introducido
	// correctamente
	if (document.getElementById("anioConvocatoria").value != "") {
		if (document.getElementById("anioConvocatoria").value.length < 4) {
			result = result + escape(ejercicio) + "\n";
		}
	}

	// validacion porcentajeDiscapacidad
	if (document.getElementById("porcentajeDiscapacidad").value != "") {
		var valor = parseInt(document.getElementById("porcentajeDiscapacidad").value);
		if (valor > 100) {
			document.getElementById("porcentajeDiscapacidad").value = "";
			document.getElementById("detalleDiscapacidad").value = "";
			result = result + escape(porcentajeDiscapacidad) + "\n";
		}
	}

	// validacion de que el documento identificativo esta introduccido
	// correctamente
	if (document.getElementById("nif").value != "") {
		var resultValidaNIF = validarNumeroDocumento();
		if (resultValidaNIF != "") {
			result = result + resultValidaNIF + "\n";
		}
	}

	// validacion del importe introducido
	var parteEntera = document.getElementById("importeSolicitud").value;
	var parteDecimal = document.getElementById("importeSolicitudDecimal").value;
	if (parteEntera == "" && parteDecimal != "") {
		if (!esEntero(parteDecimal)) {
			result = result + escape() + "\n";
		} else {
			document.getElementById("importeSolicitud").value = 0;
		}
	} else if (parteEntera != "" && parteDecimal == "") {
		if (!esEntero(parteEntera)) {
			result = result + escape(importeLiq) + "\n";
		} else {
			document.getElementById("importeSolicitudDecimal").value = "00";
		}
	} else if (parteEntera != "" && parteDecimal != "") {
		if (!esEntero(parteEntera) || !esEntero(parteDecimal)) {
			result = result + escape(importeLiq) + "\n";
		} else {
			if (parseInt(parteEntera, 10) < 0 || parseInt(parteDecimal, 10) < 0) {
				result = result + escape(importe) + "\n";
			}
		}
	}
	// validacion del numero de telefono introducido
	if (document.getElementById("telefono1").value != null) {
		if (document.getElementById("telefono1").value != "") {
			comprobacion = validarTelefono(document.getElementById("telefono1"));
			if (!comprobacion) {
				result = result + escape(telefono) + "\n";
			}
		}
	}
	if (document.getElementById("idComunidadFN")) {
		if (numTituloRequerido() == true) {
			if (document.getElementById("numeroTituloFN").value == ""
					|| document.getElementById("numeroTituloFN") == null) {
				result = result + escape(numeroTituloFNErr) + "\n";
			}
		}
	}

	// en el caso de que las casillas del numero de cuenta estan activas,
	// comprobamos que los datos introducidos
	// sean correctos
	if (document.getElementById("bank-account").checked == true) {
		var cuenta = document.getElementById("IBANInput").value.split('-');
		var iban = cuenta[0];
		var entidad = cuenta[1];
		var oficina = cuenta[2];
		var digitoControl = cuenta[3];
		var numeroCuenta = cuenta[4];
		;

		if (iban != "" && entidad != "" && oficina != "" && digitoControl != ""
				&& digitoControl != "" && numeroCuenta != "") {

			if (iban.length < 4) {
				result = result + escape(ibanErr) + "\n";
			}

			if (entidad.length < 4 || !esEntero(entidad)) {
				result = result + escape(entidadErr) + "\n";
			}

			if (oficina.length < 4 || !esEntero(oficina)) {
				result = result + escape(oficinaErr) + "\n";
			}

			if (digitoControl.length < 2 || !esEntero(digitoControl)) {
				result = result + escape(dcErr) + "\n";
			}

			if (numeroCuenta.length < 10 || !esEntero(numeroCuenta)) {
				result = result + escape(cuentaErr) + "\n";
			}

			IBAN = iban + entidad + oficina + digitoControl + numeroCuenta;
			var resultadoIban = validaIBAN(IBAN);
			if (resultadoIban == false) {
				result = result + escape(IBANErr) + "\n";
			}
		} else {
			result = result + escape(numCuentaErr) + "\n";
		}
	}

	// validacion de la fecha de nacimiento
	var diaNacimiento = document.getElementById("diaNacimiento").value;
	var mesNacimiento = document.getElementById("mesNacimiento").value;
	var anioNacimiento = document.getElementById("anioNacimiento").value;

	if (diaNacimiento != "" || mesNacimiento != "" || anioNacimiento != "") {

		// Validacion fecha de nacimiento.
		var fechaForm = mesNacimiento + "/" + diaNacimiento + "/"
				+ anioNacimiento;
		var fecha = new Date(fechaForm).getTime();
		var hoy = new Date().getTime();
		var edad = hoy - fecha;
		var miliseg16 = 504921600000;
		var miliseg70 = 2209032000000;

		if (edad < miliseg16 || edad > (miliseg70)) {
			result = result + escape(edadErr) + "\n";
		}
		if ((mesNacimiento == 4 || mesNacimiento == 6 || mesNacimiento == 9 || mesNacimiento == 11)
				&& diaNacimiento > 30) {
			result = result + escape(fechaNacimiento) + "\n";
		}
		if (mesNacimiento == 2
				&& (diaNacimiento > 29 || (diaNacimiento == 29 && ((anioNacimiento % 400 != 0) && ((anioNacimiento % 4 != 0) || (anioNacimiento % 100 == 0)))))) {
			result = result + escape(fechaNacimiento) + "\n";
		}
		if (diaNacimiento == "") {
			result = result + escape(diaErr) + "\n";
		}

		if (mesNacimiento == "") {
			result = result + escape(mesErr) + "\n";
		}
		if (anioNacimiento == "") {
			result = result + escape(anioErr) + "\n";
		}
	}

	// validacion de la fecha del BOE
	var diaBoe = document.getElementById("diaFechaBoe").value;
	var mesBoe = document.getElementById("mesFechaBoe").value;
	var anioBoe = document.getElementById("anioFechaBoe").value;

	if (diaBoe != "" || mesBoe != "" || anioBoe != "") {
		if (diaBoe == "") {
			result = result + escape(diaBOE) + "\n";
		}
		if (mesBoe == "") {
			result = result + escape(mesBOE) + "\n";
		}
		if (anioBoe == "") {
			result = result + escape(anioBOE) + "\n";
		}
	}

	// Validacion numero titulo familia numerosa
	var titulo = document.getElementById("numeroTituloFN").value;
	titulo = titulo.split('_').join("");
	titulo = titulo.split('/').join("");
	// Se valida solamente cuando el campo esta visible
	if ($("#idNumeroTitulo").is(":visible")) {
		if ($("#numeroTituloFN").val() == null
				|| $("#numeroTituloFN").val() == "" || titulo.length < 8) {
			result = result + "Titulo de familia numerosa obligatorio" + "\n";
		}
	}

	return result;
}

function validarCaracteresEspeciales() {

	var seguir = true;
	var i = 0;
	// var numElem = document.getElementsByTagName("*").length;

	// while (seguir && i < numElem){
	// if(document.getElementsByTagName("*")(i).type=='text'){
	// seguir = textoEsValido (document.getElementsByTagName("*")(i).value);
	// }
	// i++;
	// }
	return seguir;
}

function nifValidate(nif) {
	if (!nif)
		return false;

	if (nif.length == 9) {
		nif = nif.toUpperCase();
		if (/[0-9]{8}[ABCDEFGHJKLMNPQRSTVWXYZ]/.test(nif)) {
			var temp = 'TRWAGMYFPDXBNJZSQVHLCKET';
			var posicion_letra = nif.substring(0, 8) % 23;
			if (nif.charAt(8) != temp.charAt(posicion_letra)) {
				return false;
			}
		} else {
			if (/[KML][0-9]{7}[ABCDEFGHJKLMNPQRSTVWXYZ]/.test(nif)) {
				var temp = 'TRWAGMYFPDXBNJZSQVHLCKET';
				var posicion_letra = nif.substring(1, 8) % 23;
				if (nif.charAt(8) != temp.charAt(posicion_letra)) {
					return false;
				}
			} else {
				return false;
			}
		}
	} else {
		return false;
	}
	return true;
}

function nieValidate(nie) {
	if (!nie) {
		return false;
	}

	if (nie.length == 9) {
		nie = nie.toUpperCase();

		if (/[XYZ][0-9]{7}[ABCDEFGHJKLMNPQRSTVWXYZ]/.test(nie)) {
			var temp = 'TRWAGMYFPDXBNJZSQVHLCKET';
			var posicion_letra = 0;

			if ((nie).charAt(0) == 'X') {
				posicion_letra = nie.substring(1, 8) % 23;
			} else if ((nie).charAt(0) == 'Y') {
				nie = nie.replace('Y', 1);
				posicion_letra = nie.substring(0, 8) % 23;
			} else if ((nie).charAt(0) == 'Z') {
				nie = nie.replace('Z', 2);
				posicion_letra = nie.substring(0, 8) % 23;
			}

			if (nie.charAt(8) != temp.charAt(posicion_letra)) {
				return false;
			}

		} else {
			return false;
		}
	} else {
		return false;
	}
	return true;
}

function validarNumeroDocumento() {
	var error = "";
	var identificacion = document.getElementById("nif").value;
	var letranie = identificacion.substring(0, 1);
	var result;
	if (identificacion != null && identificacion != "") {
		if (letranie == "X" || letranie == "Y" || letranie == "Z") {
			result = nieValidate(identificacion);
			if (result == false) {
				error = dni1;
			}
		} else {
			result = nifValidate(identificacion);
			if (result == false) {
				error = dni2;
			}
		}
	} else {
		error = dni3;
	}
	return error;
}

function calcularLetraDNI(dni) {
	var juegoCaracteres = "TRWAGMYFPDXBNJZSQVHLCKET";
	var posicion = dni % 23;
	var letra = juegoCaracteres.charAt(posicion);
	return letra;
}

function comprobarPasteCaracteresNumericos(numero) {
	var self = numero;
	setTimeout(function(e) {
		var val = $(self).val();
		if (val != '0') {
			var regx = new RegExp(/^[0-9]+$/);
			if (!regx.test(val)) {
				$(numero).val("");
			}
			$(this).val(val);
		}
	}, 0);
}

function comprobarPasteCaracteresEspeciales(texto) {

	setTimeout(function() {
		// get the value of the input text
		var data = $(texto).val();
		// replace the special characters to ''
		var dataFull = data.replace(/[^\w\sÑÁÉÍÓÚ'Ü]/gi, '');
		
		$(texto).val(dataFull);
				
	});
}

function comprobarPasteNumeros(texto){
	setTimeout(function() {
		// get the value of the input text
		var data = $(texto).val();
		// replace the special characters to ''
		var dataFull = data.replace(/[0-9]/g, "");
		// set the new value of the input text without special characters
		$(texto).val(dataFull);
	});
}

function comprobarNorma2(numDocumento) {

	var v1 = new Array(0, 2, 4, 6, 8, 1, 3, 5, 7, 9);
	var temp = 0;
	var temp1;

	for (i = 2; i <= 6; i += 2) {
		temp = temp + v1[parseInt(numDocumento.substr(i - 1, 1))];
		temp = temp + parseInt(numDocumento.substr(i, 1));
	}
	;

	temp = temp + v1[parseInt(numDocumento.substr(7, 1))];
	temp = (10 - (temp % 10));
	return temp;
}

function comprobarNorma3(numDocumento) {
	
	var JuegoCaracteres = 'TRWAGMYFPDXBNJZSQVHLCKET';
	var parteNumerica;
	var letra0 = numDocumento.charAt(0);
	if (letra0 == 'X') {
		parteNumerica = numDocumento.substring(1, 9);
	} else {
		parteNumerica = numDocumento.substring(1, 8);
	}

	var pos = (parteNumerica % 23) + 1;
	var letra = JuegoCaracteres.charAt(pos - 1);
	return letra;
}

function textoEsValido(texto) {
	if (texto.indexOf('<', 0) != -1) {
		return false;
	}
	if (texto.indexOf('>', 0) != -1) {
		return false;
	}
	if (texto.indexOf('"', 0) != -1) {
		return false;
	}
	if (texto.indexOf('*', 0) != -1) {
		return false;
	}
	return true;
}

function esEntero(valor) {
	var cadena = valor.toString();
	var longitud = cadena.length;
	if (longitud == 0) {
		return false;
	}
	var ascii = null;
	for (var i = 0; i < longitud; i++) {
		ascii = cadena.charCodeAt(i);
		if (ascii < 48 || ascii > 57) {
			return false;
		}
	}
	return true;
}

function comprobarMinisterio() {
	var ministerio = document.getElementById("ministerio").value;
	if (ministerio == -1) {
		// document.getElementById("ministerio").value="";
		document.getElementById("ministerioAutomatico").value = "";
		document.getElementById("ministerioManual").value = "";
		document.getElementById("ministerioCombo").style.display = "none";
		document.getElementById("ministerioManualBlock").style.display = "block";
	} else {
		var w = document.getElementById("ministerio").selectedIndex;
		var selected_text = document.getElementById("ministerio").options[w].text;
		document.getElementById("ministerioAutomatico").value = selected_text;
	}
}

function comprobarCentroGestor() {
	var centroGestor = document.getElementById("centroGestor").value;
	if (centroGestor == -1) {
		// document.getElementById("centroGestor").value="";
		document.getElementById("centroGestorManual").value = "";
		document.getElementById("centroGestorCombo").style.display = "none";
		document.getElementById("centroGestorManualBlock").style.display = "block";
	}
}

function comprobarCuerpoEscala() {
	var cuerpoEscala = document.getElementById("cuerpoEscala").value;
	if (cuerpoEscala == -1) {
		// document.getElementById("centroGestor").value="";
		document.getElementById("cuerpoEscalaManual").value = "";
		document.getElementById("cuerpoEscalaCombo").style.display = "none";
		document.getElementById("cuerpoEscalaManualBlock").style.display = "block";
	}
}

function comprobarEspecialidad() {
	var especialidad = document.getElementById("especialidad").value;
	if (especialidad == -1) {
		// document.getElementById("centroGestor").value="";
		document.getElementById("especialidadManual").value = "";
		document.getElementById("especialidadCombo").style.display = "none";
		document.getElementById("especialidadManualBlock").style.display = "block";
	}
}

function comprobarFormaAcceso() {
	var formaAcceso = document.getElementById("formaAcceso").value;
	if (formaAcceso == -1) {

		document.getElementById("formaAccesoManual").value = "";
		document.getElementById("formaAccesoCombo").style.display = "none";
		document.getElementById("formaAccesoManualBlock").style.display = "block";
	}
}

function comprobarTitulo() {
	var titulo = document.getElementById("tituloOficial").value;
	if (titulo == -1) {
		document.getElementById("tituloManual").value = "";
		document.getElementById("tituloCombo").style.display = "none";
		document.getElementById("tituloManualBlock").style.display = "block";
	}
}

function asignarMinisterio(pru) {
	var ministerio = document.getElementById("ministerioManual").value;
	document.getElementById("ministerioAutomatico").value = ministerio;
}

// Validacion de la obligatoriedad de los datos
function validarDatos() {

	var error = 0;
	var errorPago = 0;

	if (document.getElementById("obl_inputNif") != null
			&& document.getElementById("obl_inputNif").value == "true") {
		if (document.getElementById("nif").value == null
				|| document.getElementById("nif").value == ""
				|| document.getElementById("nif").value.length < 9) {
			error = 1;
			$("#nif").addClass("pae-form__input--error");
			$("#nif790Error").removeClass("hiddenAccesible");
			if ($("#setFocus").val() == "")
				$("#setFocus").val('nif');
		} else {
			$("#nif").removeClass("pae-form__input--error");
			$("#nif790Error").addClass("hiddenAccesible");
		}
	}

	if (document.getElementById("obl_inputPrimerApellido") != null
			&& document.getElementById("obl_inputPrimerApellido").value == "true") {
		if (document.getElementById("primerApellido").value == null
				|| document.getElementById("primerApellido").value == "") {
			error = 1;
			$("#primerApellido").addClass("pae-form__input--error");
			$("#primerApellido790Error").removeClass("hiddenAccesible");
			if ($("#setFocus").val() == "")
				$("#setFocus").val('primerApellido');
		} else {
			$("#primerApellido").removeClass("pae-form__input--error");
			$("#primerApellido790Error").addClass("hiddenAccesible");
		}
	}

	if (document.getElementById("obl_inputSegundoApellido") != null
			&& document.getElementById("obl_inputSegundoApellido").value == "true") {
		if (document.getElementById("segundoApellido").value == null
				|| document.getElementById("segundoApellido").value == "") {
			error = 1;
			$("#segundoApellido").addClass("pae-form__input--error");
			$("#segundoApellido790Error").removeClass("hiddenAccesible");
			if ($("#setFocus").val() == "")
				$("#setFocus").val('segundoApellido');
		} else {
			$("#segundoApellido").removeClass("pae-form__input--error");
			$("#segundoApellido790Error").addClass("hiddenAccesible");
		}
	}

	if (document.getElementById("obl_inputNombre") != null
			&& document.getElementById("obl_inputNombre").value == "true") {
		if (document.getElementById("nombre").value == null
				|| document.getElementById("nombre").value == "") {
			error = 1;
			$("#nombre").addClass("pae-form__input--error");
			$("#nombre790Error").removeClass("hiddenAccesible");
			if ($("#setFocus").val() == "")
				$("#setFocus").val('nombre');
		} else {
			$("#nombre").removeClass("pae-form__input--error");
			$("#nombre790Error").addClass("hiddenAccesible");
		}
	}

	if ($("#fechaNacimiento").val() != null
			|| $("#fechaNacimiento").val() != "") {
		var fechaNacimiento = $("#fechaNacimiento").val();
		document.getElementById("diaNacimiento").value = fechaNacimiento
				.substring(0, 2);
		document.getElementById("mesNacimiento").value = fechaNacimiento
				.substring(3, 5);
		document.getElementById("anioNacimiento").value = fechaNacimiento
				.substring(6);
	}

	if (document.getElementById("obl_fechaNacimiento") != null
			&& document.getElementById("obl_fechaNacimiento").value == "true") {
		if ($("#fechaNacimiento").val() == null
				|| $("#fechaNacimiento").val() == "") {
			error = 1;
			$("#fechaNacimiento").addClass("pae-form__input--error");
			$("#fechaNacimiento790Error").removeClass("hiddenAccesible");
			if ($("#setFocus").val() == "")
				$("#setFocus").val('fechaNacimiento');
		} else {
			$("#fechaNacimiento").removeClass("pae-form__input--error");
			$("#fechaNacimiento790Error").addClass("hiddenAccesible");
		}
	}

	if (document.getElementById("obl_inputNacionalidad") != null
			&& document.getElementById("obl_inputNacionalidad").value == "true") {
		if ($("#nacionalidad").val() == null || $("#nacionalidad").val() == "") {
			error = 1;
			$("#nacionalidad").addClass("pae-form__input--error");
			$("#nacionalidad790Error").removeClass("hiddenAccesible");
			if ($("#setFocus").val() == "")
				$("#setFocus").val('nacionalidad');
		} else {
			$("#nacionalidad").removeClass("pae-form__input--error");
			$("#nacionalidad790Error").addClass("hiddenAccesible");
		}
	}

	if (document.getElementById("obl_inputEmail") != null
			&& document.getElementById("obl_inputEmail").value == "true") {
		if ($("#correoElectronicos").val() == null
				|| $("#correoElectronicos").val() == "") {
			error = 1;
			$("#correoElectronicos").addClass("pae-form__input--error");
			if ($("#setFocus").val() == "")
				$("#setFocus").val('correoElectronicos');
		} else {
			$("#correoElectronicos").removeClass("pae-form__input--error");
			expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			if (!expr.test(document.getElementById("correoElectronicos").value)) {
				error = 1;
				$("#correoElectronicos").addClass("pae-form__input--error");
			}
		}
	} else {
		$("#email").removeClass("pae-form__input--error");
		expr = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
		if (!expr.test(document.getElementById("email").value)) {
			error = 1;
			$("#email").addClass("pae-form__input--error");
			if ($("#setFocus").val() == "")
				$("#setFocus").val('correoElectronicos');
		}
	}

	if (document.getElementById("obl_inputTelefono") != null
			&& document.getElementById("obl_inputTelefono").value == "true") {
		if (document.getElementById("telefono1").value == null
				|| document.getElementById("telefono1").value == "") {
			error = 1;
			document.getElementById("telefono1").className += " pae-form__input--error";
			if ($("#setFocus").val() == "")
				$("#setFocus").val('telefono1');
		} else {
			document.getElementById("telefono1").className = "pae-form__input";
			// Validacion formato tfno
			if (!validarTelefono(document.getElementById("telefono1"))) {
				error = 1;
				document.getElementById("telefono1").className += " pae-form__input--error";
				if ($("#setFocus").val() == "")
					$("#setFocus").val('telefono1');
			}
		}
	} else {
		document.getElementById("telefono1").className = "pae-form__input";
		// Validacion formato tfno
		if (!validarTelefono(document.getElementById("telefono1"))) {
			error = 1;
			document.getElementById("telefono1").className += " pae-form__input--error";
			if ($("#setFocus").val() == "")
				$("#setFocus").val('telefono1');
		}

	}

	if (document.getElementById("telefono2").value != ""
			|| document.getElementById("telefono2").value != null) {
		if (!validarTelefono(document.getElementById("telefono2"))) {
			document.getElementById("telefono2").className += " pae-form__input--error";
			error = 1;
		} else if (isNaN(document.getElementById("telefono2").value)) {
			error = 1;
			document.getElementById("telefono2").className += " pae-form__input--error";
		} else {
			document.getElementById("telefono2").className = "pae-form__input";
		}
	}

	if ($("#obl_inputCalleDomicilio") != null
			&& $("#obl_inputCalleDomicilio").val() == "true") {

		var r = new RegExp(
				"[^a-zA-Z0-9áéíóúÁÉÍÓàèìòùÀÈÌÒÙâêîôûÂÊÎÔÛ/ÚÑñüÜç¿!¡'Ç\"%·´>_.,;:\\?\\-\\(\\)\\ª\\º\\s\\+\\*]");
		var a1 = $("#calleDomicilio").val().match(r) // Find matches.

		if (($("#calleDomicilio").val() == null || $("#calleDomicilio").val() == "")
				|| (a1 != null)) {
			error = 1;
			$("#calleDomicilio").addClass("pae-form__input--error");
			if ($("#setFocus").val() == "")
				$("#setFocus").val('calleDomicilio');
		} else {
			$("#calleDomicilio").removeClass("pae-form__input--error");
		}
	}

	if ($("#obl_inputMunicipioDomicilio") != null
			&& $("#obl_inputMunicipioDomicilio").val() == "true") {
		if ($("#municipioDomicilio").val() == null
				|| $("#municipioDomicilio").val() == "") {
			error = 1;
			$("#municipioDomicilio").addClass("pae-form__input--error");
			if ($("#setFocus").val() == "")
				$("#setFocus").val('municipioDomicilio');
		} else {
			$("#municipioDomicilio").removeClass("pae-form__input--error");
		}
	}

	if (($("#obl_inputProvinciaDomicilio") != null && $(
			"#obl_inputProvinciaDomicilio").val() == "true")) { // 1 == ESPAÑA
		if (($("#provinciaDomicilio").val() == null
				|| $("#provinciaDomicilio").value == "" || document
				.getElementById("provinciaDomicilio").selectedIndex == 0)
				&& (document.getElementById("pais").value == 1)) {
			error = 1;
			$("#provinciaDomicilio").addClass("pae-form__input--error");
			if ($("#setFocus").val() == "")
				$("#setFocus").val('provinciaDomicilio');
		} else {
			$("#provinciaDomicilio").removeClass("pae-form__input--error");
		}
	}

	if (document.getElementById("obl_pais") != null
			&& document.getElementById("obl_pais").value == "true") {
		if (document.getElementById("pais").value == null
				|| document.getElementById("pais").value == ""
				|| document.getElementById("pais").selectedIndex == 0) {
			error = 1;
			document.getElementById("pais").className += " pae-form__input--error";
			if ($("#setFocus").val() == "")
				$("#setFocus").val('pais');
		} else {
			document.getElementById("pais").className = "pae-form__input";
		}
	}

	errorCP = error;
	if (document.getElementById("pais").value == 1) { // Solo obligatorio y se
		// valida cuando el pais
		// es españa
		if (document.getElementById("codigoPostalDomicilio")
				&& document.getElementById("pais").value == 1) {
			if (document.getElementById("obl_inputCodigoPostalDomicilio") != null
					&& document
							.getElementById("obl_inputCodigoPostalDomicilio").value == "true") {
				if (document.getElementById("codigoPostalDomicilio").value == null
						|| document.getElementById("codigoPostalDomicilio").value == ""
						|| isNaN(document
								.getElementById("codigoPostalDomicilio").value)
						|| document.getElementById("codigoPostalDomicilio").value.length != 5) {
					error = 1;
					document.getElementById("codigoPostalDomicilio").className += " pae-form__input--error";
					if ($("#setFocus").val() == "")
						$("#setFocus").val('codigoPostalDomicilio');
				} else {
					// validacion formato del codigo postal
					if (!validarCodigoPostal(document
							.getElementById("codigoPostalDomicilio"))) {
						error = 1;
						document.getElementById("codigoPostalDomicilio").className += " pae-form__input--error";
						if ($("#setFocus").val() == "")
							$("#setFocus").val('codigoPostalDomicilio');
					} else {
						document.getElementById("codigoPostalDomicilio").className = "pae-form__input";
					}
				}
			} else {
				// validacion formato del codigo postal
				if (!validarCodigoPostal(document
						.getElementById("codigoPostalDomicilio"))) {
					error = 1;
					document.getElementById("codigoPostalDomicilio").className += " pae-form__input--error";
					if ($("#setFocus").val() == "")
						$("#setFocus").val('codigoPostalDomicilio');
				} else {
					document.getElementById("codigoPostalDomicilio").className = "pae-form__input";
				}
			}
		} else {
			error = 1;
			document.getElementById("codigoPostalDomicilio").className += " pae-form__input--error";
			if ($("#setFocus").val() == "")
				$("#setFocus").val('codigoPostalDomicilio');
		}
	} else {
		document.getElementById("codigoPostalDomicilio").className = "pae-form__input";
	}

	if (error == 1) {
		document.getElementById("boxSolicitante").className = "pae-box pae-box--error";
	} else {
		document.getElementById("boxSolicitante").className = "pae-box";
	}

	var errorConvocatoria = 0;

	if (document.getElementById("obl_especialidad") != null
			&& document.getElementById("obl_especialidad").value == "true") {
		if (document.getElementById("especialidad").value == null
				|| (document.getElementById("especialidad").value == "0" && document
						.getElementById("especialidad").children.length > 1)
				|| (document.getElementById("especialidad").selectedIndex == 0 && document
						.getElementById("especialidad").children.length > 1)) {
			error = 1;
			errorConvocatoria = 1;
			document.getElementById("especialidad").className += " pae-form__input--error";
			if ($("#setFocus").val() == "")
				$("#setFocus").val('especialidad');
		} else {
			document.getElementById("especialidad").className = "pae-form__input";
		}
	}

	if (document.getElementById("obl_provinciaExamen") != null
			&& document.getElementById("obl_provinciaExamen").value == "true") {
		if (document.getElementById("provinciaExamen").value == null
				|| document.getElementById("provinciaExamen").value == "74"
				|| document.getElementById("provinciaExamen").value == "-"
				|| document.getElementById("provinciaExamen").selectedIndex == 0) {
			error = 1;
			errorConvocatoria = 1;
			document.getElementById("provinciaExamen").className += " pae-form__input--error";
			if ($("#setFocus").val() == "")
				$("#setFocus").val('provinciaExamen');
		} else {
			document.getElementById("provinciaExamen").className = "pae-form__input";
		}
	}

	var discapacidadChecked = false;
	var iteracion = document.getElementsByName("motivoRedEx");
	for(var i=1;i<=iteracion.length;i++) {
		if(document.getElementById("checkbox-exemption-"+i).value == "1" && 
				document.getElementById("checkbox-exemption-"+i).checked==true){//DISCAPACIDAD
			discapacidadChecked = true;
			break;
		}
	}
	
	if (discapacidadChecked == true) {
		if (document.getElementById("obl_porcentajeDiscapacidad") != null
				&& document.getElementById("obl_porcentajeDiscapacidad").value == "true") {
			if ($("#porcentajeDiscapacidad").val() == null
					|| $("#porcentajeDiscapacidad").val() == "") {
				error = 1;
				$("#porcentajeDiscapacidad").addClass("pae-form__input--error");
				if ($("#setFocus").val() == "")
					$("#setFocus").val('porcentajeDiscapacidad');
			} else {
				$("#porcentajeDiscapacidad").removeClass("pae-form__input--error");
			}
		}

		// if(document.getElementById("porcentajeDiscapacidad").value >= 33) {
		// if(document.getElementById("idComunidadDD").value == null ||
		// document.getElementById("idComunidadDD").value=="-" ||
		// document.getElementById("idComunidadDD").selectedIndex==0){
		// error=1;
		// document.getElementById("idComunidadDD").className += "
		// pae-form__input--error";
		// if($("#setFocus").val() == "")
		// $("#setFocus").val('idComunidadDD');
		// document.getElementById("advisor-disabilities").className =
		// "pae-form__cell";
		// } else {
		// $("#idComunidadDD").removeClass("pae-form__input--error");
		// }
		// }

		// Si esta habilitado el combo CCAA de discapacidad (21) se marca como
		// obligatorio
		/*if (($("#idComunidadDD").prop("disabled") == false)
				&& ($("#idComunidadDD").val() == null
						|| $("#idComunidadDD").value == "" || document
						.getElementById("idComunidadDD").selectedIndex != 0) && !document.getElementsByName("motivoRedEx").item(1).checked
						&& !document.getElementsByName("motivoRedEx").item(2).checked) {*/
		if (($("#idComunidadDD").prop("disabled") == false)
				&& ($("#idComunidadDD").val() == null
						|| $("#idComunidadDD").value == "" || document
						.getElementById("idComunidadDD").selectedIndex == 0)) {
			error = 1;
			$("#idComunidadDD").addClass("pae-form__input--error");
			if ($("#setFocus").val() == "")
				$("#setFocus").val('idComunidadDD');
		} else {
			$("#idComunidadDD").removeClass("pae-form__input--error");
		}

		if (document.getElementById("obl_detalleDiscapacidad") != null
				&& document.getElementById("obl_detalleDiscapacidad").value == "true") {
			if ($("#detalleDiscapacidad").val() == null
					|| $("#detalleDiscapacidad").val() == "") {
				error = 1;
				$("#detalleDiscapacidad").addClass("pae-form__input--error");
				if ($("#setFocus").val() == "")
					$("#setFocus").val('detalleDiscapacidad');
			} else {
				$("#detalleDiscapacidad").removeClass("pae-form__input--error");
			}
		}
	} else {
		$("#idComunidadDD").removeClass("pae-form__input--error");
		$("#porcentajeDiscapacidad").removeClass("pae-form__input--error");
		$("#detalleDiscapacidad").removeClass("pae-form__input--error");
	}	

	if (errorConvocatoria == 1) {
		document.getElementById("boxConvocatoria").className = "pae-box pae-box--error";
	} else {
		document.getElementById("boxConvocatoria").className = "pae-box";
	}

	var errorInformacion = 0;

	if (document.getElementById("obl_titulo") != null
			&& document.getElementById("obl_titulo").value == "true") {
		if (document.getElementById("tituloOficial").selectedIndex == 0
				|| document.getElementById("tituloOficial").value == ""
				|| document.getElementById("tituloOficial").value == null
				|| document.getElementById("tituloOficial").value == "-") {
			error = 1;
			errorInformacion = 1;
			document.getElementById("tituloOficial").className += " pae-form__input--error";
			if ($("#setFocus").val() == "")
				$("#setFocus").val('tituloOficial');
		} else {
			document.getElementById("tituloOficial").className = "pae-form__input";
		}
	}

	if (document.getElementById("obl_otrosTitulos") != null
			&& document.getElementById("obl_otrosTitulos").value == "true") {
		if (document.getElementById("otrosTitulos").value == null
				|| document.getElementById("otrosTitulos").value == "") {
			$("#anyadeOtroTitulo").click();
			$("#papelera").hide();
			error = 1;
			document.getElementById("otrosTitulos").className += " pae-form__input--error";
			if ($("#setFocus").val() == "")
				$("#setFocus").val('otrosTitulos');
		} else {
			$("#otrosTitulos").removeClass("pae-form__input--error");
			$("#papelera").show();
		}
	}

	$('[id^=campoDinamico]')
			.each(
					function() {
						var nombreId = $(this).attr("id");
						var idCampoDinamico = nombreId.split("-")[1];

						if ($("#obl_campoPropio" + idCampoDinamico) != null
								&& $("#obl_campoPropio" + idCampoDinamico)
										.val() == "true") {
							if ($("#plantilla" + idCampoDinamico).val() == null
									|| $("#plantilla" + idCampoDinamico).val() == "") {
								error = 1;
								$("#plantilla" + idCampoDinamico).addClass(
										"pae-form__input--error");
								if ($("#setFocus").val() == "")
									$("#setFocus").val(
											'plantilla' + idCampoDinamico);
							} else {
								$("#plantilla" + idCampoDinamico).removeClass(
										"pae-form__input--error");
							}
						}
					});

	if (errorInformacion == 1) {
		document.getElementById("boxInformacionAdicional").className = "pae-box pae-box--error";
	} else {
		document.getElementById("boxInformacionAdicional").className = "pae-box";
	}

	errorPago = validarPago790();

	if (errorPago == 1) {
		error = errorPago;
	}

	if (document.getElementById("validarCampos")) {
		var validarCampos = document.getElementById("validarCampos").value;
		if (document.getElementById("validarCampos")
				&& document.getElementById("validarCampos").value != "") {
			var array = validarCampos.split("##");
			var id = "";
			for (var i = 0; i < array.length; i++) {
				if (array[i] != "" && array[i] != " ") {
					id = trim(array[i]);
					if (document.getElementById(id).value == "") {
						error = 1;
						document.getElementById(id).style.borderColor = "red";
					} else
						document.getElementById(id).style.borderColor = "";
				}

			}
		}
	}
	return error;
}
