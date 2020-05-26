<script type="text/javascript">
$( document ).ready(function() {
    if($("#importeHTML").val() == "0.0" || $("#importeHTML").val() == "0"){
    	$("#formasDePago").hide();
    	$("#formaPago").val("N");
    }else{
    	$("#formasDePago").show();
    }
});
</script>
			<div data-function="accordion-box" class="pae-box" id="boxPago">
              <div class="pae-box__header">
                <h3 class="pae-box__header--title"><spring:message code="field.pagoSolicitud.datosPago" /></h3>
              </div>
              <div class="pae-box__body">
                <fieldset>
                  <legend class="pae-form__legend-text hiddenAccesible"><spring:message code="field.pagoSolicitud.datosPago" /></legend>
                  <div class="pae-layout">
                    <div class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell">
                        <label class="pae-form__label--text hiddenAccesible"><spring:message code="field.pagoSolicitud.importeAPagar" /></label>
                        <div class="pae-form__dark-cell"><span class="pae-form__dark-cell-text"><spring:message code="field.pagoSolicitud.importeAPagar" /></span>
                        		<input type="text" name="importeHTML" value="${importe}" id="importeHTML" class="pae-form__input--disabled__4" >&euro;</input>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div id="formasDePago" class="">
<!--                   <div class="pae-layout"> -->
<!--                     <div class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm"> -->
<!--                       <div class="pae-form__cell"><span class="pae-form__label pae-form__span-label"><spring:message code="field.pagoSolicitud.metodoPago" />*</span> -->
<!--                         <div class="pae-form__custom-rb-box"> -->
<!--                           <input type="radio" id="pagoEfectivo" value="E" checked="checked" class="pae-form__custom-rb" onchange="cambioFormaPago(this)"/> -->
<!--                           <label for="pagoEfectivo" class="pae-form__custom-rb-label medium"><span class="pae-form__custom-rb-label--text"><spring:message code="field.formulario790.efectivo"/></span></label> -->
<!--                           <input type="radio" id="bank-account" value="A" data-function="fc-advisor-open" data-object="advisor-bank-account" class="pae-form__custom-rb" onchange="cambioFormaPago(this)"/> -->
<!--                           <label for="bank-account" class="pae-form__custom-rb-label medium"><span class="pae-form__custom-rb-label--text"><spring:message code="field.pagoSolicitud.cuentaBancaria" /></span></label> -->
<!--                         </div> -->
<!--                       </div> -->
<!--                     </div> -->
<!--                   </div> -->
                  <!-- Informaci?n cuenta bancaria -->
                  <div class="pae-layout" id="infoAdeudo">
                    <div class="pae-layout__item pae-u-1/1 pae-u-1/1-lap pae-u-1/1-palm">
                      <div id="advisor-bank-account" class="pae-form__cell pae-hidden">
                        <div class="pae-form__advisor-tooltip pae-form__advisor-tooltip--no-arrow pae-form__advisor-tooltip--no-shadow pae-form__advisor-tooltip--big">
                        <p class="pae-form__advisor-text"><spring:message code="field.pagoSolicitud.infoCuentaBancaria" /><strong class="pae-form__advisor-text pae-form__advisor-text--bold">
                          	<spring:message code="field.pagoSolicitud.infoCuentaBancaria2" /></strong><spring:message code="field.pagoSolicitud.infoCuentaBancaria3" /> 
                          	<strong class="pae-form__advisor-text pae-form__advisor-text--bold"><spring:message code="field.pagoSolicitud.infoCuentaBancaria4" />
                          	</strong><spring:message code="field.pagoSolicitud.infoCuentaBancaria5" /></p>
                          <button name="advisorclose" id="advisorclose" data-function="fc-advisor-close" class="pae-form__advisor-close"></button>
                        </div>
                      </div>
                    </div>
                  </div>
                  <!-- Divs tarjetas y adeudo -->
                  <div class="pae-layout pae-hidden" id="divAdeudo">
                    <!-- Numero de cuenta o tarjeta -->
                    <div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-1/1-palm">
                      <div data-opcion="cuenta" class="pae-form__cell pae-hidden">
                        <label for="IBANInput" class="pae-form__label"><span class="pae-form__label pae-form__label--text"><spring:message code="field.pagoSolicitud.numCuenta" />*</span></label>
                        <input type="text" id="IBANInput" name="IBANInput" placeholder="ES00-0000-0000-00-000000000"  maxlength="28" value="ES" onchange="comprobarAdeudo(this.value)"
                        	class="pae-form__input" onkeypress="return soloNumeros(event);" onkeyup="escribirGuionCuenta(this.value, event);"><span id="IBANSpan" class="pae-form__text-error"><spring:message code="field.pagoSolicitud.numCuentaExacto" /></span>
                      <span class="pae-form__text-error"><spring:message
										code="field.solicitud.campoObligatorio" /></span>
                      </div>
                    </div>
                  </div>
                  </div>
                </fieldset>
              </div>
            </div>
            
<script type="text/javascript">
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

function validarPago790(){
	
	var errorPago = 0;
	var IBAN = "";
	
	if(document.getElementById("formaPago").value == "A") {
		if(document.getElementById("IBANInput").value == "" || document.getElementById("IBANInput").value == "ES" || (document.getElementById("IBANInput").value.length != 28 && document.getElementById("IBANInput").value.length != 24)) {
			errorPago = 1;
			$("#IBANSpan").remove();
			$('<span id="IBANSpan" class="pae-form__text-error"><spring:message code="field.pagoSolicitud.numCuentaExacto" /></span>').insertAfter($("#IBANInput"));
			document.getElementById("IBANInput").className += " pae-form__input--error";
			if($("#setFocus").val() == "")
				$("#setFocus").val('IBANInput');		
		} else {	// el input incluye un IBAN con un numero de caracteres correcto,faltando diferenciar si lleva o no guiones
			var IBANInput = document.getElementById("IBANInput").value;
			document.getElementById("IBANInput").className = "pae-form__input";
			if (IBANInput.length == 28) { // el IBAN incluye guiones y hay que suprimirselos hasta quedarse en 24 caracteres
				IBAN = IBANInput.substring(0,4) + IBANInput.substring(5,9) + IBANInput.substring(10,14) + IBANInput.substring(15,17) + IBANInput.substring(18,28);
			} else if (IBANInput.length == 24) { // el IBAN no incluye guiones
				IBAN = IBANInput;
			}
			
		} 
	
/* 	if(document.getElementById("formaPago").value == "A") {
		if(document.getElementById("IBANInput").value == "" || document.getElementById("IBANInput").value == "ES" || document.getElementById("IBANInput").value.length != 28) {
			errorPago = 1;
			$("#IBANSpan").remove();
			$('<span id="IBANSpan" class="pae-form__text-error"><spring:message code="field.pagoSolicitud.numCuentaExacto" /></span>').insertAfter($("#IBANInput"));
			document.getElementById("IBANInput").className += " pae-form__input--error";
			if($("#setFocus").val() == "")
				$("#setFocus").val('IBANInput');		
		}else {			
		var IBANInput = document.getElementById("IBANInput").value;
		document.getElementById("IBANInput").className = "pae-form__input";
		IBAN = IBANInput.substring(0,4) +IBANInput.substring(5,9) + IBANInput.substring(10,14) +IBANInput.substring(15,17) + IBANInput.substring(18,28);
		}
	 */
		if(errorPago!=1 && !validaIBAN(IBAN)){
			errorPago = 1;
			$("#IBANSpan").remove();
			$('<span id="IBANSpan" class="pae-form__text-error"><spring:message code="field.pagoSolicitud.IBANError" /></span>').insertAfter($("#IBANInput"));
			document.getElementById("IBANInput").className += " pae-form__input--error";
			if($("#setFocus").val() == "")
				$("#setFocus").val('IBANInput');
		}
	}
	
	if(errorPago == 1) {
		document.getElementById("boxPago").className = "pae-box pae-box--error";
	}else {
		document.getElementById("boxPago").className = "pae-box";
	}
	
	return errorPago;
	
}
</script>