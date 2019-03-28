
<script type="text/javascript">

	var objectExtracto = new Object(); 
	
	// Función que devuelve los números correspondientes a cada letra
	function getNumIBAN(letra){
	   var letras = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';           
	   return letras.search(letra) + 10;
	}
	
	// Función que calcula el módulo sin hacer ninguna división
	function mod(dividendo, divisor){
	   var cDividendo = '';
	   var cResto = '';
	
	   for (var i=0; i < dividendo.length; i++){
	      var cChar = dividendo.charAt(i);
	      var cOperador = cResto + '' + cDividendo + '' + cChar;
	 
	      if (cOperador < parseInt(divisor)){
	         cDividendo += '' + cChar;
	      }else{
	         cResto = cOperador % divisor;
	         if (cResto == 0){
	            cResto = '';
	         }
	         cDividendo = '';
	      }
	   }
	   cResto += '' + cDividendo;
	   if (cResto == ''){
	      cResto = 0;
	   }
	   return cResto;
	}
	
	// El típico trim que inexplicamente JavaScript no trae implementado
	function trim(texto){
	   return texto.replace(/^\s+/g,'').replace(/\s+$/g,'');
	}
	
	// Función que comprueba el IBAN
	function validaIBAN(IBAN){
	   var resto = 0;	
	   IBAN = IBAN.toUpperCase();
	   IBAN = trim(IBAN); // Quita espacios al principio y al final
	   IBAN = IBAN.replace(/\s/g, ""); // Quita espacios del medio
	   var num1,num2;
	   var isbanaux;
	   if (IBAN.length != 24){ // En España el IBAN son 24 caracteres
	      return false;
	   }else{
	      num1 = getNumIBAN(IBAN.substring(0, 1)); 
	      num2 = getNumIBAN(IBAN.substring(1, 2));
	      isbanaux = IBAN.substr(4) + String(num1) + String(num2) + IBAN.substr(2,2);
	      resto = mod(isbanaux,97);
	      if(resto==1)
	          return true;
	      else
	          return false;
	   }
	}
	
	function volver(){
		window.location.href = "<%=request.getContextPath()%>/secure/buscarSolicitudes";	
	}
	
	function guardar(){
		document.getElementById("accion").value = "Guardar";
	}
	
	function actualizarImporte(idSeleccionado){
		$('#mensajePagoModificado').hide();
		var iteracion = document.getElementsByName("checkbox-exemption");
		$("#importePagado2").css("display", "none");
		//if ($("#importePagado").val() != $("#importeOriginal").val()) {
			$("#importeAPagar").css("display", "block");
		//}	
		
        //Limpiamos el resto de checkbox
		for(var k=1; k<=iteracion.length;k++) {			
			if(!(document.getElementById("checkbox-exemption-"+k) && document.getElementById("checkbox-exemption-"+k).checked == true && document.getElementById("checkbox-exemption-"+k).value == idSeleccionado)) {
				document.getElementById("checkbox-exemption-"+k).checked = false;
				d = $("[for='" + "checkbox-exemption-"+k + "']").attr("id");
				document.getElementById(d).className = "pae-form__custom-check-label pae-form__custom-check-label--option checked";
			}else{
				document.getElementById("checkbox-exemption-"+k).checked = true;
				d = $("[for='" + "checkbox-exemption-"+k + "']").attr("id");
				document.getElementById(d).className = "pae-form__custom-check-label pae-form__custom-check-label--option unchecked";
			}
		}
        
		var listaOrgIniSel = $('#motivosTipificado').val();
		listaOrgIniSel = listaOrgIniSel.replace("[", "");
		listaOrgIniSel = listaOrgIniSel.replace("]", "");
		var orgsSelect = listaOrgIniSel.split('##');
		var id=0;
		var porcentaje=0;
		var importeNuevo = 0;
		var descuento=0;
		var cam=0;
	 	
		if(idSeleccionado>0){
			var tooltip = $(".pae-form__advisor-tooltip--dynamic");
			var check = false;
			//Mostramos o quitamos el combo de comunidades para exencion de familia numerosa
			for(var j=1; j<=iteracion.length;j++) {
				if(document.getElementById("checkbox-exemption-"+j).checked) {
					check = true;
					if(idSeleccionado == 3 || idSeleccionado == 5) {
						if(idSeleccionado == 5) {
							if(document.getElementById("bank-account").checked == true) {
								document.getElementById("motivoAdeudo").value = idSeleccionado;
								document.getElementById("motivoTarjeta").value = 0;
								document.getElementById("motivo").value = 0;
							}else if(document.getElementById("credict-card").checked == true) {
								document.getElementById("motivoTarjeta").value = idSeleccionado;
								document.getElementById("motivoAdeudo").value = 0;
								document.getElementById("motivo").value = 0;
							}
						} else {
							document.getElementById("motivo").value = idSeleccionado; //FN Especial
							document.getElementById("motivoAdeudo").value = 0;
							document.getElementById("motivoTarjeta").value = 0;
						}
						document.getElementById("comunidadesFNExento").className = "pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm pae-margin-top";
						document.getElementById("tituloFN").className = "pae-hidden";
					} else {
						document.getElementById("motivo").value = idSeleccionado; //Resto de motivos
						document.getElementById("motivoAdeudo").value = 0;
						document.getElementById("motivoTarjeta").value = 0;
						document.getElementById("comunidadesFNExento").className = "pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm pae-hidden pae-margin-top";
						document.getElementById("tituloFN").className = "pae-hidden";
						document.getElementById("idComunidadFNExento").value = "";
						document.getElementById("tituloFNExento").value = "";
					}
	                    tooltip.children().remove();
	                    tooltip.show();
	                    //Mostramos tooltip de discapacidad si se ha metido grado de discapacidad >33
	                    if (idSeleccionado== 1 ){
	                   		 tooltip.removeClass().addClass("pae-form__advisor-tooltip pae-form__advisor-tooltip--dynamic pae-form__advisor-tooltip--checkbox-one");
	                    	tooltip.append($(".pae-form__advisor-checkbox--1").html());
	                    	if(document.getElementById("idComunidadDDExento") != null){
	                    		document.getElementById("idComunidadDDExento").disabled = false;
	                    	}
	                    	$("[for='state'] span").removeClass("pae-form__label--disabled").addClass("pae-form__label--text");
	                	}else{
	                		if(document.getElementById("idComunidadDDExento") != null){
	                			document.getElementById("idComunidadDDExento").disabled = true;
	                		}
	                		$("[for='state'] span").addClass("pae-form__label--disabled").removeClass("pae-form__label--text");
	                	}
	                    					
	                //Calculamos descuento
					for(var i=1;i<=orgsSelect.length;i++){
						if(orgsSelect[i]!="" && orgsSelect[i]!=null){	
							id = orgsSelect[i];
							i++;
						}
						if(orgsSelect[i]!="" && orgsSelect[i]!=null){	
							porcentaje = orgsSelect[i];
						}
						if(id==idSeleccionado){
							// Cálculo tasas reducidas para acceso Promoción Interna
							if(document.getElementById("inputIdFormaAcceso").value != 1){
								descuento = ($('#importeOriginal').val() * porcentaje / 100 );
								importeNuevo = Math.round(($('#importeOriginal').val() - descuento)*100)/100;
								// Compruebo si hay un importe pagado, de ser asi se resta al importe nuevo
								if($('#importePagado').val() != null){
									importeNuevo = importeNuevo - $('#importePagado').val();
									importeNuevo = Math.round(importeNuevo * 100) / 100;
									if(importeNuevo < 0){
										//if ($("#importePagado").val() != $("#importeOriginal").val()) {
											$('#mensajePagoModificado').show();
										//}
										$("#importeAPagar").hide();
										$("#pagoInicialModif").val("N");
										$("#pagoInicialModifPago").val("N");
									}else{
										$("#pagoInicialModif").val("S");
										$("#pagoInicialModifPago").val("S");
										}
								}
							}else{
								descuento = ($('#importeOriginal').val() * porcentaje / 100);
								importeNuevo = Math.round(($('#importeOriginal').val() - descuento)*100)/100;
								// Compruebo si hay un importe pagado, de ser asi se resta al importe nuevo
								if($('#importePagado').val() != null){
									importeNuevo = importeNuevo - $('#importePagado').val();
									importeNuevo = Math.round(importeNuevo * 100) / 100;
									if(importeNuevo < 0){
										//if ($("#importePagado").val() != $("#importeOriginal").val()) {
											$('#mensajePagoModificado').show();
										//}
										$("#importeAPagar").hide();
										$("#pagoInicialModif").val("N");
										$("#pagoInicialModifPago").val("N");
									}
								}
							}
							document.getElementById("importe").value = importeNuevo;
							document.getElementById("importeHTML").innerText = document.getElementById("importe").value;
							if(document.getElementById("importe_2"))
								document.getElementById("importe_2").value = importeNuevo;
							cam=1;
						}
					}
	                if(document.getElementById("importe").value == 0.00) {
	                	document.getElementById("formasDePago").className = "pae-hidden";
	                	document.getElementById("formPago").value = "E";
	                }
				}
				else if(cam==0) {
					if(idSeleccionado == 3 || idSeleccionado == 5) {						
						document.getElementById("comunidadesFNExento").className = "pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm pae-hidden pae-margin-top";
						document.getElementById("idComunidadFNExento").value = "";
						document.getElementById("tituloFNExento").value = "";
					}
					if(!check) {
						document.getElementById("motivo").value = 0;
						document.getElementById("motivoAdeudo").value = 0;
						document.getElementById("motivoTarjeta").value = 0;
					}
					var importeNuevo2 = $('#importeOriginal').val();
					// Compruebo si hay un importe pagado, de ser asi se resta al importe nuevo
					if($('#importePagado').val() != null){
						importeNuevo2 = importeNuevo2 - $('#importePagado').val();
							$('#mensajePagoModificado').hide();
							$("#importeHTML")[0].innerText = importeNuevo2;
							$("#importe").val(importeNuevo2);
							$("#pagoInicialModif").val("S");
							$("#pagoInicialModifPago").val("S");
					}else{
						document.getElementById("importeHTML").innerText = document.getElementById("importe").value;
					}
					
					document.getElementById("importe").value = $('#importeOriginal').val();
					
					if(document.getElementById("importe_2"))
						document.getElementById("importe_2").value = $('#importeOriginal').val();
					for(var i=1;i<=iteracion.length;i++) {
						document.getElementById("checkbox-exemption-"+i).disabled = false;
					}
					tooltip.hide();
					document.getElementById("formasDePago").className = "";
					if(document.getElementById("credict-card").checked) {
						document.getElementById("formPago").value = "T";
					}
					else if(document.getElementById("bank-account").checked) {
						document.getElementById("formPago").value = "C";
					}
				}
			}
			
			// Se inicializa el formulario de discapacidad y se muestra u oculta según corresponda
			$('#porcentajeDiscapacidad').val('');
			$('#detalleDiscapacidad').val('');
			$('#idComunidadDDExento').val('0');
			cambioTipoDiscapacidad();
			
			// Si se marca discapacidad, se establece "CCAA en la que se reconoce su discapacidad" como obligatorio
			if(idSeleccionado == 1){
				// Si esta checkeado
				if(check){
					$("#oblComunidadDDExento").removeAttr("style");
					$('#datosDiscapacidad').removeClass('pae-hidden');
				}else{
					document.getElementById("idComunidadDDExento").disabled = true;
					$("#oblComunidadDDExento").attr("style","display:none;");
					$('#datosDiscapacidad').addClass('pae-hidden');
				}
			}else{
				$("#oblComunidadDDExento").attr("style","display:none;");
				$('#datosDiscapacidad').addClass('pae-hidden');
			}
		}
	}
	
	function actualizarEntidadTarjeta(banco){
		document.getElementById("bancoTarjeta").value = banco; 
	}
	
	function actualizarEntidadAdeudo(banco){
		document.getElementById("bancoAdeudo").value = banco;
	}
	
	function validarNumerico(campo){
		if(/^[0-9]+$/.test(campo)){
			return true;
		}
		return false;
	}
	
	function firmar(){
		var formPago = document.getElementById("formPago").value;
		
		if(formPago == null || formPago == ""){
			return false;
		}else{
			if(formPago == "E"){
				document.getElementById("accion").value = "Guardar";
				$("#formularioPago").submit();
				return true;
			}
			guardar();
			$("#formularioPago").submit();
			return true;
		}
	}
	
	function origenFirma(){
		var formPago = document.getElementById("formPago").value;
		var s_importe = document.getElementById("importe").value;
		var f_importe = Number(s_importe);
		s_importe = f_importe.toFixed(2);
		s_importe = s_importe.toString().replace(".",",");
			
		var str_comun = "JUSTIFICANTE:           " + document.getElementById("numeroSolicitud").value + "\n" +
						"NIF/CIF:                " + document.getElementById("nif").outerText + "\n" +
						"IMPORTE DEL INGRESO:    " + s_importe;
							
		if(formPago == "C"){
			
			var ccc = 	document.getElementById("IBAN").value + "          ";
			var str_firma = str_comun + "\n" + "IBAN:                   " + ccc + "\n";
			
			document.getElementById("origenFirma").value = str_firma;
			return str_firma;
			
		}else if(formPago == "T"){
	
			var numero_tarjeta = document.getElementById("numTarjeta").value;
	
			var anyo = document.getElementById("caducidadTarjeta2").value;
			var caducidad = document.getElementById("caducidadTarjeta1").value +"/" + anyo.substr(2,2);
			var stre_firma_tarjeta = 		str_comun + "\n"+
				   "EMISOR DE TARJETA:      " + document.getElementById("bancoTarjeta").value + "\n" +
				   "NUMERO DE TARJETA:      " + numero_tarjeta + "\n" +
				   "CADUCA (MM/AA):         " + caducidad + "\n";
				   
			document.getElementById("origenFirma").value = stre_firma_tarjeta;
			return stre_firma_tarjeta;
		}
	}
	

	
	//This code was written by Tyler Akins and has been placed in the
	// public domain.  It would be nice if you left this header intact.
	// Base64 code from Tyler Akins -- http://rumkin.com
	
	/* OBSOLETO
	
	var keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
	
	function encode64(input) {
	   var output = "";
	   var chr1, chr2, chr3;
	   var enc1, enc2, enc3, enc4;
	   var i = 0;
	
	   do {
	      chr1 = input.charCodeAt(i++);
	      chr2 = input.charCodeAt(i++);
	      chr3 = input.charCodeAt(i++);
	      enc1 = chr1 >> 2;
	      enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
	      enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
	      enc4 = chr3 & 63;
	
	      if (isNaN(chr2)) {
	         enc3 = enc4 = 64;
	      } else if (isNaN(chr3)) {
	         enc4 = 64;
	      }
	
	      output = output + keyStr.charAt(enc1) + keyStr.charAt(enc2) + 
	         keyStr.charAt(enc3) + keyStr.charAt(enc4);
	   } while (i < input.length);
	   
	   return output;
	}*/
	
</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
	
		<form:form modelAttribute="pagoSolicitudForm" action="/IPSC/secure/pagoSolicitud" id="formularioPago" name="pagoSolicitudForm" method="post" accept-charset="ISO-8859-1">
			 <% if(!tipoPersona.equals("FH")) { %>
		       	<form:hidden path="nif" name="pagoSolicitud" value= "${pagoSolicitud.nif}"/>
				<form:hidden path="nombre" name="pagoSolicitud" value= "${pagoSolicitud.nombre}"/>
				<form:hidden path="apellido1" name="pagoSolicitud" value= "${pagoSolicitud.apellido1}"/>
				<form:hidden path="apellido2" name="pagoSolicitud" value= "${pagoSolicitud.apellido2}"/>
			<form:hidden path="numeroSolicitud" id="numeroSolicitud" name="pagoSolicitud" value= "${pagoSolicitud.numeroSolicitud}"/>
      		<% } else if (tipoPersona.equals("FH") && continuar.equals("true")) { %>
      			<form:hidden path="nif" name="pagoSolicitud" value= "${solicitud.nif}"   />
				<form:hidden path="nombre" name="pagoSolicitud" value= "${solicitud.nombre}"   />
				<form:hidden path="apellido1" name="pagoSolicitud" value= "${solicitud.primerApellido}"   />
				<form:hidden path="apellido2" name="pagoSolicitud" value= "${solicitud.segundoApellido}"   />
      			<form:hidden path="numeroSolicitud" id="numeroSolicitud" name="pagoSolicitud" value= "${solicitud.numJustificante}"    />
      		<% } %>
      		<form:hidden path="idSolicitudInicial" value="${altaSolicitud.idSolicitudInicial}"/>
      		<form:hidden path="nrc" id="nrc" name="pagoSolicitud" />
			<form:hidden path="accion" id="accion" />
			<form:hidden path="idSolicitud" id="idSolicitud"/>
			<form:hidden path="numSolicitud" id="numSolicitud" value="${altaSolicitud.numSolicitud}"/>
			<form:hidden path="formPago" id="formPago" value="T" />
			<form:hidden path="nombreParcial" id="nombreParcial" name="pagoSolicitud" />
			<form:hidden path="detalleMotivo" id="detalleMotivo" name="pagoSolicitud" />
			<form:hidden path="signature" id="signature" />
			<form:hidden path="signerCert" id="signerCert" />
			<form:hidden path="documentoHTML" id="documentoHTML" />
			<form:hidden path="aceptar" id="aceptar" />
			<form:hidden path="firmaPago" id="firmaPago" />
			<form:hidden path="bancoTarjeta" id="bancoTarjeta" />
			<form:hidden path="estaEnPago" id="estaEnPago" />
			<form:hidden name="altaSolicitud" id="inputIdFormaAcceso" path="idFormaAcceso"/>
			
			<input type="hidden" id="comunidadesReqTitulo" value="${comunidadesReqTitulo}" >
			<input type="hidden" id="htmlFormulario" />
			<input type="hidden" name="motivoTarjeta" id="motivoTarjeta" value="0" />
      		<input type="hidden" name="motivoAdeudo" id="motivoAdeudo" value="0" />
      		<input type="hidden" name="motivo" id="motivo" value="0" />
      		<input type="hidden" name="importe" id="importe" value="${registroSolicitud.importe}" />
      		<input type="hidden" name="bancoAdeudo" id="bancoAdeudo" value="" />
      		<input type="hidden" name="bancoTarjeta" id="bancoTarjeta" value="" />
      		<input type="hidden" name="IBAN" id="IBAN" value="" />
      		<input type="hidden" name="numTarjeta" id="numTarjeta" value="" />
      		<input type="hidden" name="numTarjeta2" id="numTarjeta2" value="" />
      		<input type="hidden" name="numTarjeta3" id="numTarjeta3" value="" />
      		<input type="hidden" name="numTarjeta4" id="numTarjeta4" value="" />
      		<input type="hidden" name="numTarjeta5" id="numTarjeta5" value="" />
			
			<%@ include file="/jsp/modificarDatosExencion.jsp" %>
			<%@ include file="/jsp/modificarDatosPago.jsp" %>
			
			<!-- Input añadido para el envío correcto del formulario en IE11 -->
			<input type="hidden" name="ajax" />
			
			</form:form>