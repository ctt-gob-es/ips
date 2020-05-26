<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/solicitudes/solicitudes.js"></script>
<script type="text/javascript">

function validarDatosPago(error){
	var IBANCorrecto = true;
	var tarjetaCorrecta = true;
	var formPago = document.getElementById("formPago").value;
	var pagoInicialModif = null;
	if($("#pagoInicialModif") != null){
		pagoInicialModif = $("#pagoInicialModif").val();
	}

	if(pagoInicialModif == "S"){
		if(formPago == null || formPago == ""){
			error=1;
		}else{
			var importe = $("#importeHTML")[0].textContent;
			document.getElementById("importe").value = importe;
			if(formPago == "E"){
				//Si requiere insertar el número de título de familia numerosa y el ciudadano no lo ha puesto, salta un mensaje de error
				if(document.getElementById("comunidadesFNExento").className == "animated fadeInLeft pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm"){
					if(document.getElementById("idComunidadFNExento").value == "0" || document.getElementById("idComunidadFNExento").value == ""){
						document.getElementById("idComunidadFNExento").className += " pae-form__input--error";
						if($("#setFocus").val() == "")
							$("#setFocus").val('idComunidadFNExento');
						error = 1;
					}else if(document.getElementById("idComunidadFNExento").value == "7") {
						if(document.getElementById("tituloFNExento").value=="" || document.getElementById("tituloFNExento")==null){
							document.getElementById("tituloFNExento").className += " pae-form__input--error";
							if($("#setFocus").val() == "")
								$("#setFocus").val('tituloFNExento');
							error=1;
						}
						else {
							document.getElementById("tituloFNExento").className = "pae-form__input";
						}
					}else {
						document.getElementById("idComunidadFNExento").className = "pae-form__input";
						document.getElementById("tituloFNExento").className = "pae-form__input";
					}
				}
				if(error == 1) {
					document.getElementById("boxExento").className = "pae-box pae-box--error";
					return false;
				}else {
					document.getElementById("boxExento").className = "pae-box";
					return true;
				}
				
			}else if(formPago == "C"){
				var IBANInput = document.getElementById("IBANInput").value;
				var IBAN = "";
				
				if(document.getElementById("entidadAdeudo").value == "0" || document.getElementById("entidadAdeudo").value == null) {
					document.getElementById("entidadAdeudo").className = "pae-form__input pae-form__input--error large";
					if($("#setFocus").val() == "")
						$("#setFocus").val('entidadAdeudo');
					error = 1;
				}else {
					document.getElementById("entidadAdeudo").className = "pae-form__input large";
				}
				
					if (IBANInput == "" || IBANInput == null || (IBANInput.length != 28 && IBANInput.length != 24)) {
					document.getElementById("IBANInput").className += " pae-form__input--error";
					if($("#setFocus").val() == "")
						$("#setFocus").val('IBANInput');
					error = 1;
				} else { // el input incluye un IBAN con un numero de caracteres correcto,faltando diferenciar si lleva o no guiones
					document.getElementById("IBANInput").className = "pae-form__input";
					if (IBANInput.length == 28) { // el IBAN incluye guiones y hay que suprimirselos hasta quedarse en 24 caracteres
						IBAN = IBANInput.substring(0,4) + IBANInput.substring(5,9) + IBANInput.substring(10,14) + IBANInput.substring(15,17) + IBANInput.substring(18,28);
					} else if (IBANInput.length == 24) { // el IBAN no incluye guiones
						IBAN = IBANInput;
					}
					document.getElementById("IBAN").value = IBAN;
				} 
				
				
				if ($("#entidadAdeudo").val() != "" > 0) {	
					if (comprobarAvisoHoraBanco("entidadAdeudo")) {
						$("#errorHorariosCuenta").show();
						$( "#entidadAdeudo" ).focus();
					error = 1;
				}else {
						$("#errorHorariosCuenta").hide();
					}
				}
	
				if(error!=1 && !validaIBAN(IBAN)){
					error=1;
					IBANCorrecto = false;
				}
			}else if(formPago == "T"){
				
				var entidadTarjeta = document.getElementById("entidadTarjeta").value;
				var banco = document.getElementById("bancoTarjeta").value;
				var numTarjetaHTML = document.getElementById("numeroTarjetaHTML").value;
				var caducidadTarjeta1 = document.getElementById("caducidadTarjeta1").value;
				var caducidadTarjeta2 = document.getElementById("caducidadTarjeta2").value;
	
				if(entidadTarjeta == "0" || document.getElementById("entidadTarjeta").value == null) {
					document.getElementById("entidadTarjeta").className = "pae-form__input pae-form__input--error large";
					if($("#setFocus").val() == "")
						$("#setFocus").val('entidadTarjeta');
					error=1;
				}else {
					document.getElementById("entidadTarjeta").className = "pae-form__input large";
				}
				if(document.getElementById("numeroTarjetaHTML").value == "" || document.getElementById("numeroTarjetaHTML").value == null) {
					document.getElementById("numeroTarjetaHTML").className += " pae-form__input--error";
					error = 1;
				}else {
					document.getElementById("numeroTarjetaHTML").className = "pae-form__input";
							
					document.getElementById("numTarjeta2").value = numTarjetaHTML.substring(0,4);
					document.getElementById("numTarjeta3").value = numTarjetaHTML.substring(5,9);
					document.getElementById("numTarjeta4").value = numTarjetaHTML.substring(10,14);
					document.getElementById("numTarjeta5").value = numTarjetaHTML.substring(15,19);
					document.getElementById("numTarjeta").value = $("#numTarjeta2").val() + $("#numTarjeta3").val() + $("#numTarjeta4").val() + $("#numTarjeta5").val(); 
				}
				
				if(banco == ""){
					error=1;
				}
				
				if($("#numeroTarjetaHTML").val().length == 19) {
					var numTarjeta = $("#numTarjeta").val();
					if(!validarNumerico(numTarjeta)) {
						error=1;
						document.getElementById("numeroTarjetaHTML").className = "pae-form__input pae-form__input--error";
						if($("#setFocus").val() == "")
							$("#setFocus").val('numeroTarjetaHTML');
						tarjetaCorrecta = false;
					}else {
						document.getElementById("numeroTarjetaHTML").className = "pae-form__input";
					}
				}else {
					error = 1;
					if($("#setFocus").val() == "")
						$("#setFocus").val('numeroTarjetaHTML');
				}
				
				if(caducidadTarjeta1 == null || caducidadTarjeta1 == "" || caducidadTarjeta1.length!=2 ){
						error=1;	

						var aux = document.getElementById("mesTarjeta");
						if(aux != null){
						aux.className += (" pae-form__input--error");
						}
						$("#mesTarjetaSpan").remove();
						$('<span id="mesTarjetaSpan" class="pae-form__text-error"><spring:message code="field.pagoSolicitud.fechaTarjetaError" /></span>').insertAfter($("#caducidadTarjeta1"));
						document.getElementById("caducidadTarjeta1").className += " pae-form__input--error";
												
						if($("#setFocus").val() == "")
							$("#setFocus").val('mesTarjeta');
					}else {
						var aux = document.getElementById("mesTarjeta");
						if(aux != null){
						aux.className = ("selectize-input items not-full has-options");						
						}
					}

				if(caducidadTarjeta2 == null || caducidadTarjeta2 == "" || caducidadTarjeta2.length!=4){
					error=1;				
					var aux2 = document.getElementById("anoTarjeta");
					if(aux2 != null){
						aux2.className += (" pae-form__input--error");
					}
					
					$("#anoTarjetaSpan").remove();
					$('<span id="anoTarjetaSpan" class="pae-form__text-error"><spring:message code="field.pagoSolicitud.fechaTarjetaError" /></span>').insertAfter($("caducidadTarjeta2"));
					document.getElementById("caducidadTarjeta2").className += " pae-form__input--error";
					
					if($("#setFocus").val() == ""){
						$("#setFocus").val('anoTarjeta');
					}
					
				}else {
					var aux2 = document.getElementById("anoTarjeta");
					if(aux2 != null){
						aux2.className = ("selectize-input items not-full has-options");						
					}		
				}				
				
				if ($("#entidadTarjeta").val() != "") {
					if (comprobarAvisoHoraBanco("entidadTarjeta")) {
						$("#errorHorariosTarjeta").show();
						$( "#entidadTarjeta" ).focus();
						error = 1;
					} else {
						$("#errorHorariosTarjeta").hide();
					}
				}
			}
			// El nif del titular se debe validar siempre
			var errorNif = validarNumeroDocumento();
			if(document.getElementById("nifTitular").value == "" || document.getElementById("nifTitular").value == null || errorNif == true) {
				document.getElementById("nifTitular").className = "pae-form__input pae-form__input--error large";
				if($("#setFocus").val() == "")
					$("#setFocus").val('nifTitular');
				error = 1;
			}else {
				document.getElementById("nifTitular").className = "pae-form__input large";
			}
			// El nombre del titular se debe validar siempre
			if(document.getElementById("nombreTitular").value == "" || document.getElementById("nombreTitular").value == null) {
				document.getElementById("nombreTitular").className = "pae-form__input pae-form__input--error large";
				if($("#setFocus").val() == "")
					$("#setFocus").val('nombreTitular');
				error = 1;
			}else {
				document.getElementById("nombreTitular").className = "pae-form__input large";
			}
			if(document.getElementById("apellido1Titular").value == "" || document.getElementById("apellido1Titular").value == null) {
				document.getElementById("apellido1Titular").className = "pae-form__input pae-form__input--error large";
				if($("#setFocus").val() == "")
					$("#setFocus").val('apellido1Titular');
				error = 1;
			}else {
				document.getElementById("apellido1Titular").className = "pae-form__input large";
			}
			if(document.getElementById("apellido2Titular").value == "" || document.getElementById("apellido2Titular").value == null) {
				document.getElementById("apellido2Titular").className = "pae-form__input pae-form__input--error large";
				if($("#setFocus").val() == "")
					$("#setFocus").val('apellido2Titular');
				error = 1;
			}else {
				document.getElementById("apellido2Titular").className = "pae-form__input large";
			}
		}
	}
		if(error == 1 && (document.getElementById("IBANInput") != null || document.getElementById("IBANInput") != undefined)) {
			document.getElementById("boxPago").className = "pae-box pae-box--error";
			if(formPago == "C") {
				if(IBANCorrecto == false) {
					$("#IBANSpan").remove();
					$('<span id="IBANSpan" class="pae-form__text-error"><spring:message code="field.pagoSolicitud.IBANError" /></span>').insertAfter($("#IBANInput"));
					document.getElementById("IBANInput").className += " pae-form__input--error";
					if($("#setFocus").val() == "")
						$("#setFocus").val('IBANInput');
				}else if($("#IBANInput").val().replace(/-/g,"").length < 24) {
					$("#IBANSpan").remove();
					$('<span id="IBANSpan" class="pae-form__text-error"><spring:message code="field.pagoSolicitud.numCuentaExacto" /></span>').insertAfter($("#IBANInput"));
					document.getElementById("IBANInput").className += " pae-form__input--error";
					if($("#setFocus").val() == "")
						$("#setFocus").val('IBANInput');
				}else {
					document.getElementById("IBANInput").className = "pae-form__input";
				}
				if ($("#setFocus").val() != "") {
					document.getElementById($("#setFocus").val()).focus();
				}
				$("#setFocus").val("");
				return false;
			}else if(formPago == "T") {
				if(tarjetaCorrecta == false) {
					$("#tarjetaSpan").remove();
					$('<span id="tarjetaSpan" class="pae-form__text-error"><spring:message code="field.pagoSolicitud.tarjetaError" /></span>').insertAfter($("#numeroTarjetaHTML"));
					document.getElementById("numeroTarjetaHTML").className += " pae-form__input--error";
				}else if($("#numeroTarjetaHTML").val().length < 19 || $("#numeroTarjetaHTML").val().length > 19) {
					$("#tarjetaSpan").remove();
					$('<span id="tarjetaSpan" class="pae-form__text-error"><spring:message code="field.pagoSolicitud.numTarjetaExacto" /></span>').insertAfter($("#numeroTarjetaHTML"));
					document.getElementById("numeroTarjetaHTML").className += " pae-form__input--error";
					if($("#setFocus").val() == "")
						$("#setFocus").val('numeroTarjetaHTML');
				}else {
					document.getElementById("numeroTarjetaHTML").className = "pae-form__input";
				}
				if ($("#setFocus").val() != "") {
					document.getElementById($("#setFocus").val()).focus();
				}
				$("#setFocus").val("");
				return false;
			}
		}else {
			document.getElementById("boxPago").className = "pae-box";
			return true;
		}
}

var dni1 = "El NIE introducido no es válido";
var dni2 = "El NIF/NIE introducido no es válido";
var dni3 = "El NIF es obligatorio";
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
			if (/[KML][0-9]{7}[ABCDEFGHJKLMNPQRSTVWXYZ]/.test(nif)){
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
	var error = false;
	var identificacion = document.getElementById("nifTitular").value;
	var letranie = identificacion.substring(0, 1);
	var result;
	if (identificacion != null && identificacion != "") {
		if (letranie == "X" || letranie == "Y" || letranie == "Z") {
			result = nieValidate(identificacion);
			if (result == false) {
				error = true;
			}
		} else {
			result = nifValidate(identificacion);
			if (result == false) {
				error = true;
			}
		}
	} else {
		error = true;
	}
	return error;
}
</script>
<input type="hidden" name="pagoInicialModif" id="pagoInicialModifPago" value="${altaSolicitud.pagoInicialModif }" />

<div data-function="accordion-box" class="pae-box" id="boxPago">
	<div class="pae-box__header">
		<h3 class="pae-box__header--title">
			<spring:message code="field.pagoSolicitud.datosPago" />
		</h3>
	</div>
	<div class="pae-box__body">

		<fieldset id="importeAPagar" style="display: none;">
			<legend class="pae-form__legend-text hiddenAccesible">
				<spring:message code="field.pagoSolicitud.datosPago" />
			</legend>
			<div class="pae-layout">
				<div
					class="pae-layout__item pae-u-4/12 pae-u-5/12-lap pae-u-1/1-palm">
					<div class="pae-form__cell">
						<label class="pae-form__label--text hiddenAccesible">
							<spring:message	code="field.pagoSolicitud.importeAPagar" />
						</label>
						<div class="pae-form__dark-cell">
							<span class="pae-form__dark-cell-text">
								<spring:message	code="field.pagoSolicitud.importeAPagar" /></span> 
								<span id=""	class="pae-form__dark-cell-text pae-form__dark-cell-text--big">
								<strong	class="pae-form__dark-cell-text pae-form__dark-cell-text--big pae-form__dark-cell-text--bold pae-form__dark-cell-text--spacing"
								id="importeHTML" name="importeHTML">
									<%=importe%>
								</strong> &euro;
							</span>
						</div>
					</div>
				</div>
			</div>

			<!-- Campos entidad bancaria y NRC para registro por F.H -->
			<logic:equal name="ciudadanoBean" property="tipoPersona" value="FH">
				<div class="pae-layout">
					<div
						class="pae-layout__item pae-u-4/12 pae-u-5/12-lap pae-u-1/1-palm"
						style="margin-top: -1px">
						<div class="pae-form__cell">
							<label for="nrcCiudadano" class="pae-form__label"><span
								class="pae-form__label pae-form__label--text"><spring:message
										code="field.pagoSolicitud.NRC" /></span></label> <input type="text" size="22"
								maxlength="22" id="nrcCiudadano" name="nrcCiudadano"
								style="text-transform: uppercase;" class="pae-form__input">
							<span class="pae-form__text-error"><spring:message
									code="field.solicitud.NrcObligatorio" /></span>
						</div>
					</div>
				</div>
			</logic:equal>


			<div id="formasDePago">
				<div class="pae-layout">
					<div
						class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message	code="field.pagoSolicitud.metodoPago" />*
							</span>
							<div class="pae-form__custom-rb-box">
								<input type="radio" name="checkFormPago" id="bank-account"
									value="C" checked="checked" data-function="fc-advisor-open"
									data-object="advisor-bank-account" class="pae-form__custom-rb"
									onchange="formPagoValue()" /> 
									<label for="bank-account" class="pae-form__custom-rb-label medium">
										<span class="pae-form__custom-rb-label--text">
											<spring:message code="field.pagoSolicitud.cuentaBancaria" />
										</span>
									</label> 
								<input type="radio" name="checkFormPago" id="credict-card" value="T"
									data-function="fc-advisor-open" data-object="advisor-bank-account" 
									class="pae-form__custom-rb"	onchange="formPagoValue()" /> 
								<label for="credict-card" class="pae-form__custom-rb-label medium">
									<span class="pae-form__custom-rb-label--text">
										<spring:message	code="field.pagoSolicitud.tarjetaCredito" />
									</span>
								</label>
							</div>
						</div>
					</div>
				</div>
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-4/12 pae-u-8/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<label class="pae-form__label"> 
								<span class="pae-form__label--text"> 
									<spring:message	code="field.pagoSolicitud.nifTitular" />*
								</span>
							</label> 
							<input type="text" id="nifTitular" name="nifTitular"
								class="pae-form__input" style="text-transform: uppercase;"
								onpaste="comprobarPasteCaracteresEspeciales(this);"
								maxlength="9"/> 
								<span class="pae-form__text-error">
									<spring:message code="field.solicitud.nifTitular" />
								</span>
						</div>
					</div>
					<div class="pae-layout__item pae-u-4/12 pae-u-8/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<label class="pae-form__label"> 
								<span class="pae-form__label--text"> 
									<spring:message	code="field.pagoSolicitud.nombreTitular" />*
								</span>
							</label> 
							<input type="text" id="nombreTitular" name="nombreTitular"
								class="pae-form__input" style="text-transform: uppercase;"
								onpaste="comprobarPasteCaracteresEspeciales(this);"
								onkeypress="soloLetras(event)" maxlength="38"> 
								<span class="pae-form__text-error"> 
									<spring:message	code="field.solicitud.campoObligatorio" />
								</span>
						</div>
					</div>
					<div class="pae-layout__item pae-u-4/12 pae-u-8/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<label class="pae-form__label"> 
								<span class="pae-form__label--text"> 
									<spring:message	code="field.pagoSolicitud.primerApellido" />*
								</span>
							</label> 
							<input type="text" id="apellido1Titular" name="apellido1Titular"
								class="pae-form__input" style="text-transform: uppercase;"
								maxlength="44" onkeypress="soloLetras(event)"
								onpaste="comprobarPasteCaracteresEspeciales(this);"> 
								<span class="pae-form__text-error" /> 
									<spring:message	code="field.solicitud.campoObligatorio" />
								</span>
						</div>
					</div>
					<div class="pae-layout__item pae-u-4/12 pae-u-8/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<label class="pae-form__label"> 
								<spanclass="pae-form__label--text"> 
									<spring:message	code="field.pagoSolicitud.segundoApellido" />*
								</span>
							</label> 
							<input type="text" id="apellido2Titular" name="apellido2Titular"
								class="pae-form__input" style="text-transform: uppercase;"
								maxlength="46" onkeypress="soloLetras(event)"
								onpaste="comprobarPasteCaracteresEspeciales(this);" /> 
									<span class="pae-form__text-error"> 
										<spring:message	code="field.solicitud.campoObligatorio" />
									</span>
						</div>
					</div>
				</div>
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-1/1 pae-u-1/1-lap pae-u-1/1-palm">
						<div id="advisor-bank-account" class="pae-form__cell pae-hidden">
							<div class="pae-form__advisor-tooltip pae-form__advisor-tooltip--no-arrow pae-form__advisor-tooltip--no-shadow pae-form__advisor-tooltip--big">
								<p class="pae-form__advisor-text">
									<spring:message	code="field.pagoSolicitud.infoCuentaBancariaOnline" />
									<strong	class="pae-form__advisor-text pae-form__advisor-text--bold">
										<spring:message	code="field.pagoSolicitud.infoCuentaBancaria4" /> 
									</strong>
									<spring:message code="field.pagoSolicitud.infoCuentaBancaria5" />
								</p>
								<button name="advisorclose" id="advisorclose"
									data-function="fc-advisor-close" class="pae-form__advisor-close">
								</button>
							</div>
						</div>
					</div>
					<div class="pae-layout__item pae-u-1/1 pae-u-1/1-lap pae-u-1/1-palm">
						<div id="avisoHora" class="pae-form__cell pae-hidden">
							<div class="pae-form__advisor-tooltip pae-form__advisor-tooltip--no-arrow pae-form__advisor-tooltip--no-shadow pae-form__advisor-tooltip--big">
								<p class="pae-form__advisor-text" style="padding-top: 1%;">
									<spring:message code="field.pagoSolicitud.infoBancoCerrado" />
								</p>
								<button name="advisorclose" id="advisorclose"
									data-function="fc-advisor-close" class="pae-form__advisor-close">
								</button>
							</div>
						</div>
					</div>
				</div>
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-4/12 pae-u-8/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__span-button"> <label
								for="entidadAdeudo" class="pae-form__label"><span
									class="pae-form__label pae-form__label--text"><spring:message
											code="field.pagoSolicitud.entidad" />*</span></label>
								<button type="button" id="bank-help" name="bank-help"
									data-function="fc-help-bank"
									class="pae-form__span-button--button-help"></button></span>
							<div id="divTarjeta">
								<form:select path="entidadTarjeta" id="entidadTarjeta"
									onchange="actualizarEntidadTarjeta(this.value);comprobarAvisoHoraBanco('entidadTarjeta');"
									class="pae-form__input large">
									<option value="0" selected="selected" class="pae-form__option"><spring:message
											code="field.solicitud.selecciona" /></option>
									<c:forEach var="entidad" items="${entidadesTarjetas}">
										<c:choose>
											<c:when
												test="${not empty entidad.apertura and not empty entidad.cierre}">
												<form:option value="${entidad.codigo}">
													<c:out
														value="${entidad.descripcion} (${entidad.apertura} - ${entidad.cierre})" />
												</form:option>
											</c:when>
											<c:otherwise>
												<form:option value="${entidad.codigo}">
													<c:out value="${entidad.descripcion}" />
												</form:option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</form:select>
								<span class="pae-form__text-error">
									<spring:message	code="field.solicitud.campoObligatorio" />
								</span> 
								<span id="errorHorariosTarjeta" class="pae-form__text-error">
									<spring:message	code="field.pagoSolicitud.infoBancoCerrado" />
								</span>
							</div>

							<div id="divAdeudo" class="pae-hidden">
								<form:select path="entidadAdeudo" id="entidadAdeudo"
									onchange="actualizarEntidadAdeudo(this.value);comprobarAvisoHoraBanco('entidadAdeudo');"
									class="pae-form__input large">
									<option value="0" selected="selected" class="pae-form__option"><spring:message
											code="field.solicitud.selecciona" /></option>
									<c:forEach var="entidad" items="${entidades}">
										<c:choose>
											<c:when
												test="${not empty entidad.apertura and not empty entidad.cierre}">
												<form:option value="${entidad.codigo}">
													<c:out
														value="${entidad.descripcion} (${entidad.apertura} - ${entidad.cierre})" />
												</form:option>
											</c:when>
											<c:otherwise>
												<form:option value="${entidad.codigo}">
													<c:out value="${entidad.descripcion}" />
												</form:option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</form:select>
								<span class="pae-form__text-error"><spring:message
										code="field.solicitud.campoObligatorio" /></span> <span
									id="errorHorariosCuenta" class="pae-form__text-error"><spring:message
										code="field.pagoSolicitud.infoBancoCerrado" /></span>
							</div>

							<div id="help-bank-tooltip" class="pae-form__help-tooltip">
								<p class="pae-form__help-tooltip-text">
									<spring:message code="field.pagoSolicitud.infoEntidad" />
									<strong class="pae-form__help-tooltip-text--bold"><spring:message
											code="field.pagoSolicitud.infoEntidad2" /></strong>
								</p>
							</div>
						</div>
					</div>
					<div
						class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-1/1-palm">
						<div data-opcion="tarjeta" class="pae-form__cell">
							<label for="numeroTarjetaHTML" class="pae-form__label"><span
								class="pae-form__label pae-form__label--text"><spring:message
										code="field.pagoSolicitud.numTarjetaAux" />*</span></label> <input
								type="text" id="numeroTarjetaHTML" name="numeroTarjetaHTML"
								placeholder="0000-0000-0000-0000" value=""
								class="pae-form__input" maxlength="19"
								onkeypress="return soloNumeros(event);"
								onkeyup="escribirGuionTarjeta(this.value, event);"><span
								id="tarjetaSpan" class="pae-form__text-error"><spring:message
									code="field.pagoSolicitud.numTarjetaExacto" /></span>
						</div>
						<div data-opcion="cuenta" class="pae-form__cell pae-hidden">
							<label for="IBANInput" class="pae-form__label"><span
								class="pae-form__label pae-form__label--text"><spring:message
										code="field.pagoSolicitud.numCuenta" />*</span></label> <input type="text"
								id="IBANInput" name="IBANInput"
								placeholder="ES00-0000-0000-00-000000000" maxlength="28"
								value="ES" class="pae-form__input"
								onkeypress="return soloNumeros(event);"
								onkeyup="escribirGuionCuenta(this.value, event);"><span
								id="IBANSpan" class="pae-form__text-error"><spring:message
									code="field.pagoSolicitud.numCuentaExacto" /></span>
						</div>
					</div>
					<div class="pae-layout__item pae-u-3/12 pae-u-4/12-lap pae-u-1/1-palm">
						<div data-opcion="tarjeta" class="pae-form__cell">
							<label for="FechaCaducidad" class="pae-form__label">
								<span class="pae-form__label pae-form__label--text">
									<spring:message	code="field.pagoSolicitud.fechaCaducidadTarjetaAux" />*
								</span>
							</label> 
							<select	id="caducidadTarjeta1" name="caducidadTarjeta1"
								data-function="function-selectize" data-placeholder="mm"
								class="pae-form__select pae-form__select--small large">
								<option value="" class="pae-form__option">mm</option>
								<option value="01" class="pae-form__option">01</option>
								<option value="02" class="pae-form__option">02</option>
								<option value="03" class="pae-form__option">03</option>
								<option value="04" class="pae-form__option">04</option>
								<option value="05" class="pae-form__option">05</option>
								<option value="06" class="pae-form__option">06</option>
								<option value="07" class="pae-form__option">07</option>
								<option value="08" class="pae-form__option">08</option>
								<option value="09" class="pae-form__option">09</option>
								<option value="10" class="pae-form__option">10</option>
								<option value="11" class="pae-form__option">11</option>
								<option value="12" class="pae-form__option">12</option>
							</select>
							<span id="mesTarjetaSpan" class="pae-form__text-error">
								<spring:message	code="field.pagoSolicitud.fechaTarjetaError" />
							</span> 
							<select	id="caducidadTarjeta2" name="caducidadTarjeta2"
								data-function="function-selectize" data-placeholder="yyyy"
								class="pae-form__select pae-form__select--small large">
									<option value="" class="pae-form__option">yyyy</option>
									<option value="2018" class="pae-form__option">2018</option>
									<option value="2019" class="pae-form__option">2019</option>
									<option value="2020" class="pae-form__option">2020</option>
									<option value="2021" class="pae-form__option">2021</option>
									<option value="2022" class="pae-form__option">2022</option>
									<option value="2023" class="pae-form__option">2023</option>
									<option value="2024" class="pae-form__option">2024</option>
									<option value="2025" class="pae-form__option">2025</option>
									<option value="2026" class="pae-form__option">2026</option>
									<option value="2027" class="pae-form__option">2027</option>
									<option value="2028" class="pae-form__option">2028</option>
							</select>
							<span id="anoTarjetaSpan" class="pae-form__text-error">
								<spring:message code="field.pagoSolicitud.fechaTarjetaError" />
							</span>
						</div>
					</div>
				</div>
			</div>
		</fieldset>

		<c:if test="${mensajePago ne '' }">
			<div id="mensajePago">${mensajePago}</div>
		</c:if>
		<div id="mensajePagoModificado" style="display: none;">La
			inscripción se pagó aplicando el descuento correspondiente al motivo
			que solicitó. En caso de haberlo solicitado por error y le
			corresponda un motivo con mayor descuento deberá acudir a un centro
			gestor para realizar su subsanación.</div>

		<c:if test="${mensajePago eq '' }">
			<c:if test="${importePagado ne '0.0' }">
				<fieldset id="importePagado2">
					<legend class="pae-form__legend-text hiddenAccesible">
						<spring:message code="field.pagoSolicitud.datosPago" />
					</legend>
					<div class="pae-layout">
						<div
							class="pae-layout__item pae-u-4/12 pae-u-5/12-lap pae-u-1/1-palm">
							<div class="pae-form__cell">
								<label class="pae-form__label--text hiddenAccesible"><spring:message
										code="field.pagoSolicitud.importePagado" /></label>
								<div class="pae-form__dark-cell">
									<span class="pae-form__dark-cell-text"><spring:message
											code="field.pagoSolicitud.importePagado" /></span> <span id=""
										class="pae-form__dark-cell-text pae-form__dark-cell-text--big">
										<strong
										class="pae-form__dark-cell-text pae-form__dark-cell-text--big pae-form__dark-cell-text--bold pae-form__dark-cell-text--spacing">${altaSolicitud.importePagado}</strong>
										&euro;
									</span>
								</div>
							</div>
						</div>
					</div>
				</fieldset>
			</c:if>
		</c:if>

	</div>
</div>

<script type="text/javascript">
function escribirGuionTarjeta(valor, evento) {
		var x = valor.split('.');
	    var x1 = x[0];
	    var rgx = /(\d+)(\d{4})/;
		while (rgx.test(x1)) {
        	   x1 = x1.split('').reverse().join('').replace(rgx, '$1' + '-' + '$2');
    	       x1 = x1.split('').reverse().join('');
    	       num = x1;
    	}
		$("#numeroTarjetaHTML").val(x1);
}

function escribirGuionCuenta(valor, evento) {
		if(valor.length <= 2 && valor.substring(0,1) != "ES") {
			$("#IBANInput").val("ES");
			setTimeout (function () {
				document.getElementById("IBANInput").charAt(2).focus();
				}, 100);
		}
		else if(valor.length < 20) {
			var inicio = "";
			var longitud = "";
			if(valor.length >= 3 && valor.length < 7){
				inicio = 2;
				longitud = 2;
			}else if(valor.length >= 6 && valor.length < 17) {
				inicio = 5;
				longitud = 4;
			}else if(valor.length >= 17 && valor.length < 20) {
				inicio = 15;
				longitud = 2;
			}
			var num = valor.substring(inicio).replace(/\W/g,'');
			var x = num.split('.');
		    var x1 = x[0];
		    var rgx;
			if(longitud == 2) {
			    rgx = /(\d+)(\d{2})/;
			}else if(longitud == 4) {
			    	rgx = /(\d+)(\d{4})/;
			}
			while (rgx.test(x1)) {
	        	   x1 = x1.split('').reverse().join('').replace(rgx, '$1' + '-' + '$2');
	    	       x1 = x1.split('').reverse().join('');
	    	       num = x1;
	    	}
			$("#IBANInput").val(valor.substring(0,inicio)+num);
		}
}
function formPagoValue() {
	document.getElementById("boxPago").className = "pae-box";
	if(document.getElementById("credict-card").checked) {
		//Pago con tarjeta
		document.getElementById("formPago").value = "T";
		document.getElementById("divTarjeta").className = "";
		document.getElementById("divAdeudo").className = "pae-hidden";
		document.getElementById("IBANInput").value = "ES";
		document.getElementById("IBANInput").className = "pae-form__input";
		document.getElementById("entidadAdeudo").className = "pae-form__input large";
		document.getElementById("entidadAdeudo").value = "0";
		$("#infoEntidad1").removeClass("hidden");
		$("#infoEntidad2").addClass("hidden");
	}
	else {
		//Pago por cuenta bancaria
		document.getElementById("formPago").value = "C";
		document.getElementById("divTarjeta").className = "pae-hidden";
		document.getElementById("divAdeudo").className = "";
		document.getElementById("numeroTarjetaHTML").value = "";
		document.getElementById("entidadTarjeta").value = "0";
		document.getElementById("caducidadTarjeta1").value = null;
		document.getElementById("caducidadTarjeta2").value = "";
		$("#infoEntidad1").addClass("hidden");
		$("#infoEntidad2").removeClass("hidden");
	};
}
$(document).ready(function() {
    document.getElementById("importeHTML").textContent = parseFloat((document.getElementById("importe").value - document.getElementById("importePagado").value)).toFixed(2);
	
	formPagoValue();
	cambioMetodoPago();
	eleccionEntidad();
});

function cambioMetodoPago() {
	var a;
	if(document.getElementById("bank-account").checked) {
		a = $("#bank-account");
	} else {
		a = $("#credict-card");
	}
	
    var b = $("#" + $(a).attr("data-object"));
    "bank-account" === $(a).attr("id") ? (b.removeClass("pae-hidden"),
    $("[data-opcion='tarjeta']").addClass("pae-hidden"),
    $("[data-opcion='cuenta']").removeClass("pae-hidden")) : (b.hasClass("pae-hidden") || b.addClass("pae-hidden"),
    $("[data-opcion='cuenta']").addClass("pae-hidden"),
    $("[data-opcion='tarjeta']").removeClass("pae-hidden"))
}
</script>