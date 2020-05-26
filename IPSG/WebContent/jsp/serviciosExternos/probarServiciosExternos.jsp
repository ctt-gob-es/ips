<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="es.map.ipsg.bean.*" %>
<%@page import="es.map.ipsg.util.Constantes"%>
<%
String servicio = (String)request.getAttribute("servicio"); 
%>

<script type="text/javascript">
	
	//cargarMiniAppletFirma2(null);

function volver(){
	document.getElementById('servicio').value = '0';
	document.forms[0].action ="probarServiciosExternos.do";
	document.forms[0].submit();
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

function soloNumeros(evt){
	if(window.event){
		keynum = evt.keyCode;
	}else{
		keynum = evt.which;
	}

	if((keynum>47 && keynum<58) || keynum == 8){
		return true;
	}else{
		return false;
	}
}

function isImporte(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if ((charCode > 47 && charCode < 58) || charCode == 44 || charCode == 46) {
        return true;
    }
    return false;
}

function cargaCamposServicio(){
	document.getElementById("servicio").value="<%=servicio%>";
	var serv = document.getElementById("servicio").value; 
	if(serv==1){
		pago();
	}
}
function cambiaServicio(){
	
	// WS Firma
	if(document.getElementById('servicio').value==4){
		cargarMiniAppletFirma2('WSFirma', null);
	}else{
		$('#formPadre').trigger('submit');
	}	 
}

function pago(){
	
	var tipoPago="";
	
	if(document.getElementById("tipoPago")){
		tipoPago = document.getElementById("tipoPago").value;
	}
	
	if(tipoPago==2){
		document.getElementById("divPagoTarjeta").style.display="block";
		document.getElementById("divPagoCuenta").style.display="none";
	}
	if(tipoPago==1){
		document.getElementById("divPagoTarjeta").style.display="none";
		document.getElementById("divPagoCuenta").style.display="block";
	}
}

function actualizarCodigoBanco(banco){
	document.getElementById("bancoAdeudo").value = banco;
}

function firmarFormulario(){
	if(!($('#servicio').val() == 0) && !validarDatos()){
		return false;
	} else {
		$('#formPadre').trigger('submit');
	}

	//cargarMiniAppletFirma3();	 	
}

function validarNumerico(campo){
	if(/^[0-9]+$/.test(campo)){
		return true;
	}
	return false;
}

function validarDatos(){
		var nifNie = document.getElementById("nifNie").value;
		var numeroSolicitud = document.getElementById("numeroSolicitud").value;
		var nombre = document.getElementById("nombre").value;
		var primerApellido = document.getElementById("primerApellido").value;
		var segundoApellido = document.getElementById("segundoApellido").value;
		var importe = document.getElementById("importe").value;
		var formPago = document.getElementById("tipoPago").value;
	
	if(nifNie == null || nifNie == ""){
		alert("Debe introducir el NIF del titular de la cuenta o tarjeta de credito");
		return false;
	}
	if(numeroSolicitud == null || numeroSolicitud == ""){
		alert("El campo Numero de Solicitud debe estar informado");
		return false;
	}
	if(nombre == null || nombre == ""){
		alert("Debe introducir el Nombre del titular de la cuenta o tarjeta de credito..");
		return false;
	}
	if(primerApellido == null || primerApellido == ""){
		alert("Debe introducir el Primer Apellido del titular de la cuenta o tarjeta de credito.");
		return false;
	}
	if(segundoApellido == null || segundoApellido == ""){
		alert("Debe introducir el Segundo Apellido del titular de la cuenta o tarjeta de credito.");
		return false;
	}
	if(importe == null || importe == ""){
		alert("Debe introducir el importe del pago.");
		return false;
	}

	if(formPago == null || formPago == ""){
		alert("No se ha seleccionado ninguna forma de pago");
		return false;
	}else{
		if(formPago == "1"){
			var entidadAdeudo = document.getElementById("bancoAdeudo").value;
			var oficina = document.getElementById("oficina").value;
			var dc = document.getElementById("dc").value;
			var cuenta = document.getElementById("cuenta").value;
			
			if(entidadAdeudo == "0"){
				alert("Debe seleccionar una entidad financiera para realizar el pago.");
				return false;
			}
			if(oficina == null || oficina == "" || oficina.length!=4 || 
				dc == null || dc == "" || dc.length!=2	||
				cuenta == null || cuenta == "" || cuenta.length!=10){
				alert("Debe informar los 20 digitos de la cuenta.");
				return false;
			}
			if(!validarNumerico(oficina) || !validarNumerico(dc) || !validarNumerico(cuenta)){
				alert("El codigo de cuenta cliente debe tener solo dígitos numéricos.");
				return false;
			}
			
		}else if(formPago == "2"){
			var entidadTarjeta = document.getElementById("entidadFinanciera").value;
			var numTarjeta2 = document.getElementById("numTarjeta2").value;
			var numTarjeta3 = document.getElementById("numTarjeta3").value;
			var numTarjeta4 = document.getElementById("numTarjeta4").value;
			var numTarjeta5 = document.getElementById("numTarjeta5").value;
			var caducidadTarjeta1 = document.getElementById("caducidadTarjeta1").value;
			var caducidadTarjeta2 = document.getElementById("caducidadTarjeta2").value;

			if(entidadTarjeta == "0"){
				alert("Debe seleccionar una entidad financiera para realizar el pago.");
				return false;
			}
			if(entidadTarjeta == ""){
				alert("Debe seleccionar una entidad financiera para realizar el pago.");
				return false;
			}
			if(numTarjeta2 == null || numTarjeta2 == "" || numTarjeta2.length!=4 ||
				numTarjeta3 == null || numTarjeta3 == "" || numTarjeta3.length!=4 ||
				numTarjeta4 == null || numTarjeta4 == "" || numTarjeta4.length!=4 ||
				numTarjeta5 == null || numTarjeta5 == "" || numTarjeta5.length!=4){
				alert("Debe informar los 20 dígitos de la tarjeta.");
				return false;
			}
			if(caducidadTarjeta1 == null || caducidadTarjeta1 == "" || caducidadTarjeta1.length!=2 ||
				caducidadTarjeta2 == null || caducidadTarjeta2 == "" || caducidadTarjeta2.length!=4){
				alert("Debe informar la fecha de caducidad de la tarjeta.");
				return false;
			}
			if(!validarNumerico(numTarjeta2) || !validarNumerico(numTarjeta4) || !validarNumerico(numTarjeta4) || !validarNumerico(numTarjeta5)){
				alert("El campo tarjeta de crédito debe contener sólo dígitos numéricos.");
				return false;
			}
			if(!validarNumerico(caducidadTarjeta1) || !validarNumerico(caducidadTarjeta2)){
				alert("La fecha de caducidad de la tarjeta debe contener sólo dígitos numéricos.");
				return false;
			}
		}
	}
	return true;
}

//This code was written by Tyler Akins and has been placed in the
//public domain.  It would be nice if you left this header intact.
//Base64 code from Tyler Akins -- http://rumkin.com

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
}

function origenFirma(){
	var formPago = document.getElementById("tipoPago").value;
	var s_importe = document.getElementById("importe").value;
	var f_importe = Number(s_importe);
	
	s_importe = f_importe.toFixed(2);
	s_importe = s_importe.toString().replace(".",",");
		
	var str_comun = "JUSTIFICANTE:           " + document.getElementById("numeroSolicitud").value + "\n" +
					"NIF/CIF:                " + document.getElementById("nifNie").value + "\n" +
					"IMPORTE DEL INGRESO:    " + s_importe;
						
	if(formPago == "1"){
		/*var ccc = document.getElementById("bancoAdeudo").value + "-" +
					document.getElementById("oficina").value + "-" +
					document.getElementById("dc").value +"-" +
					document.getElementById("cuenta").value;

		var str_firma =          str_comun + "\n"+
					"CODIGO DE LA CUENTA:    " + ccc + "\n";*/
					
					
		var ccc = 	document.getElementById("iban").value +
		document.getElementById("bancoAdeudo").value +
		document.getElementById("oficina").value +
		document.getElementById("dc").value +
		document.getElementById("cuenta").value + 
		"          ";
		var str_firma = str_comun + "\n" + "IBAN:                   " + ccc + "\n";
		
		document.getElementById("origenFirma").value = str_firma;
		return str_firma;				
		
	}else{ //if(formPago == "2"){
		var numero_tarjeta = document.getElementById("numTarjeta2").value + "-" + 
							document.getElementById("numTarjeta3").value + "-" +
							document.getElementById("numTarjeta4").value + "-" +
							document.getElementById("numTarjeta5").value;

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
</script>


<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>

<body style="margin-left:0.4em;" onload="cargaCamposServicio();">
			
		<form:form modelAttribute="probarServiciosExternosForm" action="/IPSG/spring/probarServiciosExternos" id="formPadre" method="post" enctype="multipart/form-data">
			<form:hidden path="signature" id="signature"/>
		 	<form:hidden path="signerCert" id="signerCert"/>
		 	<form:hidden path="origenFirma" id="origenFirma"/>
		 				
			
			<form:hidden path="resultado" id="resultado"/>
	
			<div class="">
			
			<h1 class="pae-title"><spring:message code="field.menu.adminServExt" /></h1>
			<html:errors/>
			<div data-function="accordion-box" class="pae-box">
		 		<div class="pae-box__header">
		 			<h3 class="pae-box__header--title">
						<spring:message code="field.textosIntroduccion.ServiciosExternos"/>
					</h3>
				</div> 
				<div class="pae-box__body">
					<fieldset>
						<!-- Ini Servicio-->
						<div class="pae-layout">
							<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
								<div class="pae-form__cell">
									<span class="pae-form__label pae-form__span-label">										
										<spring:message code="field.ServiciosExternos.Servicio"/>
									</span>	
									<form:select path="servicio" name="servicio" class="pae-form__input" onchange="cambiaServicio()">
										<option value="0"></option>
										<option value="5"><spring:message code="field.ServiciosExternos.FileSystem"/></option>
										<option value="3"><spring:message code="field.ServiciosExternos.EJB"/></option>
										<option value="1"><spring:message code="field.ServiciosExternos.Pasarela"/></option>
										<option value="2"><spring:message code="field.ServiciosExternos.REC"/></option>
										<option value="4"><spring:message code="field.ServiciosExternos.Firma"/></option>								
									</form:select>				
								</div>
							</div>
						</div>
						<!-- Fin Tipo Servicio-->
					
								
					
					<div class="clear"></div>
								
			
			
			<logic:present name="respuestaWS" scope="request">
				<logic:equal name= "respuestaWS" value="N">
					<div class="capaAll">
					<logic:present name="servicio" scope="request">
						<logic:equal name="servicio" value="1">
						<!--	Inicio Pasaserla de pago	-->
						
						
						<spring:message code="field.ServiciosExternos.pasarelaIntroduccion" />
						<br><br>
												
						<div class="pae-layout">
							<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
								<div class="pae-form__cell">
									<span class="pae-form__label pae-form__span-label">		
										<spring:message code="field.ServiciosExternos.tipoPago" />
									</span>
									<form:select path="tipoPago" class="pae-form__input" onchange="pago();">
										<form:option value="1"><spring:message code="field.ServiciosExternos.tipoPago.adeudo"/></form:option>
										<form:option value="2"><spring:message code="field.ServiciosExternos.tipoPago.tarjeta"/></form:option>
									</form:select>
								</div>
							</div>
						</div>
						
						<div class="pae-layout">
							<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
								<div class="pae-form__cell">
									<span class="pae-form__label pae-form__span-label">
											<spring:message code="field.ServiciosExternos.numeroSolicitud" />
									</span>
									<form:input type="text" path="numeroSolicitud" id="numeroSolicitud" class="pae-form__input" readonly="true"/>
								</div>
							</div>
						</div>
						
						<div class="pae-layout">
							<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
								<div class="pae-form__cell">
									<span class="pae-form__label pae-form__span-label">
										<spring:message code="field.ServiciosExternos.nifNie" />
									</span>
									<form:input type="text" path="nifNie" class="pae-form__input" maxlength="9" />
								</div>
							</div>
						</div>
						
						
						<div class="pae-layout">
							<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
								<div class="pae-form__cell">
									<span class="pae-form__label pae-form__span-label">
										<spring:message code="field.ServiciosExternos.nombre" />											
									</span>
									<form:input type="text" path="nombre" class="pae-form__input" maxlength="50" onkeypress="return soloLetras(event);"/>
								</div>
							</div>
						</div>
						
						<div class="pae-layout">
							<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
								<div class="pae-form__cell">
									<span class="pae-form__label pae-form__span-label">
										<spring:message code="field.ServiciosExternos.primerApellido" />
									</span>
									<form:input type="text" path="primerApellido" class="pae-form__input" maxlength="50" onkeypress="return soloLetras(event);"/>
								</div>
							</div>
						</div>
											
						<div class="pae-layout">
							<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
								<div class="pae-form__cell">
									<span class="pae-form__label pae-form__span-label">
										<spring:message code="field.ServiciosExternos.segundoApellido" />
									</span>
									<form:input type="text" path="segundoApellido" id="segundoApellido" class="pae-form__input" maxlength="50" onkeypress="return soloLetras(event);"/>
								</div>
							</div>
						</div>
						
						<div class="pae-layout">
							<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
								<div class="pae-form__cell">
									<span class="pae-form__label pae-form__span-label">
										<spring:message code="field.ServiciosExternos.importe" />
									</span>
									<form:input type="text" path="importe" id="importe" class="pae-form__input" maxlength="10" onkeypress="return isImporte(event)"/>
								</div>
							</div>
						</div>
						
						<div id="divPagoCuenta">
							<div class="pae-layout">
								<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
									<div class="pae-form__cell">
										<span class="pae-form__label pae-form__span-label">
											<spring:message code="field.ServiciosExternos.entidadFinanciera" />
										</span>
										<form:select path="entidadFinanciera" class="pae-form__input" onchange="javascript: actualizarCodigoBanco(this.value);">
											<form:option value=""></form:option>
											<form:options items="${entidadesAdeudo}" itemLabel="descripcion" itemValue="codigo" style="text-transform: uppercase;"/>
										</form:select>
									</div>
								</div>
							</div>		
						
							<div class="pae-layout">
								<div class="pae-layout__item pae-u-12/12 pae-u-2/12-lap pae-u-1/1-palm">	
									<div class="pae-form__cell">
										<span class="pae-form__label pae-form__span-label">													
											<spring:message code="field.ServiciosExternos.numeroCuenta" />
										</span>						
										<form:input type="pae-form__input" path="iban" id="iban" size="1" maxlength="4" style="font-size: 11px;font-family: Arial;" class="pae-form__input wauto"/>
										&nbsp;
										<form:input type="text" path="bancoAdeudo" id="bancoAdeudo" size="1" maxlength="4" style="font-size: 11px;font-family: Arial;" readonly="true" class="pae-form__input wauto"/>
										&nbsp;
										<form:input type="text" path="oficina" id="oficina" size="1" maxlength="4" style="font-size: 11px;font-family: Arial;" onkeypress="return soloNumeros(event);" class="pae-form__input wauto"/>
										&nbsp;
										<form:input type="text" path="dc" id="dc" size="1" maxlength="2" style="font-size: 11px;font-family: Arial;" onkeypress="return soloNumeros(event);" class="pae-form__input wauto"/>
										&nbsp;
										<form:input type="text" path="cuenta" id="cuenta" size="8" maxlength="10" style="font-size: 11px;font-family: Arial;" onkeypress="return soloNumeros(event);" class="pae-form__input wauto"/>
											
									</div>
								</div>
							</div>
						</div>							
						
						<div id="divPagoTarjeta">
							<div class="pae-layout">
								<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
									<div class="pae-form__cell">
										<span class="pae-form__label pae-form__span-label">										
											<spring:message code="field.ServiciosExternos.entidadFinanciera" />
										</span>
										<form:select path="entidadFinanciera" class="pae-form__input">
											<form:option value=""></form:option>
											<form:options items="${entidadesTarjetas}" itemLabel="descripcion" itemValue="codigo" style="text-transform: uppercase;"/>
										</form:select>
									</div>
								</div>
							</div>
							
							<div class="pae-layout">
								<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
									<div class="pae-form__cell">
										<span class="pae-form__label pae-form__span-label">
											<spring:message code="field.ServiciosExternos.numeroTarjeta" />
										</span>
										<form:input type="text" path="numTarjeta2" id="numTarjeta2" size="3" maxlength="4" style="font-size: 11px;font-family: Arial;" class="pae-form__input wauto" onkeypress="return soloNumeros(event);"/>
										&nbsp;
										<form:input type="text" path="numTarjeta3" id="numTarjeta3" size="3" maxlength="4" style="font-size: 11px;font-family: Arial;" class="pae-form__input wauto" onkeypress="return soloNumeros(event);"/>
										&nbsp;
										<form:input type="text" path="numTarjeta4" id="numTarjeta4" size="3" maxlength="4" style="font-size: 11px;font-family: Arial;" class="pae-form__input wauto" onkeypress="return soloNumeros(event);"/>
										&nbsp;
										<form:input type="text" path="numTarjeta5" id="numTarjeta5" size="3" maxlength="4" style="font-size: 11px;font-family: Arial;" class="pae-form__input wauto" onkeypress="return soloNumeros(event);"/>
									</div>
								</div>
							</div>							
							
							<div class="pae-layout">
								<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
									<div class="pae-form__cell">
										<span class="pae-form__label pae-form__span-label">
											<spring:message code="field.ServiciosExternos.fechaCaducidad" />
										</span>							
										<form:input type="text" path="caducidadTarjeta1" class="pae-form__input wauto" id="caducidadTarjeta1" size="2" maxlength="2" style="font-size: 11px;font-family: Arial;" onkeypress="return soloNumeros(event);"/>
										&nbsp;&nbsp;
										<form:input type="text" path="caducidadTarjeta2" class="pae-form__input wauto" id="caducidadTarjeta2" size="3" maxlength="4" style="font-size: 11px;font-family: Arial;" onkeypress="return soloNumeros(event);"/>
										&nbsp;
									</div>
								</div>
							</div>
						</div>
						
						
						</logic:equal>
						
						<!--	REC	-->
						<logic:equal name="servicio" value="2">						
						<div class="pae-layout">
							<div class="pae-layout__item">	
								<div class="pae-form__cell">
									<br><br>
									<spring:message code="field.ServiciosExternos.recIntroduccion" />
									<br><br>
									<span class="pae-form__label pae-form__span-label">											
											<spring:message code="field.ServiciosExternos.numRegistro"/>
									</span>
									<form:input type="text" path="nombreDocumentoREC" id="nombreDocumentoREC" class="pae-form__input pae-u-2/12-lap pae-u-1/1-palm w35" maxlength="100"/>
								</div>
							</div>
						</div>						
						</logic:equal>
						<!-- Fin REC	-->
						
						<!--	EJB	-->
						<logic:equal name="servicio" value="3">
							<div class="capaAll" id="ejbNumeroSolicitud">
								<spring:message code="field.ServiciosExternos.ejbIntroduccion" />
												
								<div class="clear"></div>
								<br>
								<br>
								<br>
							</div>
						</logic:equal>
						<!-- Fin EJB	-->
						
						<!--	@Firma	-->						
						<logic:equal name="servicio" value="4">						
							<div class="capaAll" id="certificado">
								<b><spring:message code="field.ServiciosExternos.afirmaIntroduccion" /></b>
												
								<div class="clear"></div>
								<br>
								<br>
								<div class="labelIzq">
									<spring:message code="field.ServiciosExternos.certificadoDigital"/>
								</div>
							</div>
						</logic:equal>
						<!-- Fin @Firma	-->
						
						<!--	Alfresco	-->
						<logic:equal name="servicio" value="5">
						<logic:notPresent name="docSubidoOK" scope="request">
								<br><br>							
								<spring:message code="field.ServiciosExternos.introduccion" />
								<br><br>
									<div class="pae-layout">
										<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
											<div class="pae-form__cell">
												<span class="pae-form__label pae-form__span-label">
													<spring:message code="field.ServiciosExternos.nombreDocumento"/>
												</span>
												<form:input type="text" path="nombreDocumento" id="nombreDocumento" class="pae-form__input" maxlength="100"/>
											</div>
										</div>
									</div>
									<div class="pae-layout">
										<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
											<div class="pae-form__cell">
												<span class="pae-form__label pae-form__span-label">
													<spring:message code="field.documento.fichero"/>
												</span>
												<input type="file" name="fichero" size="50" class="input_texto_border0 em100" id="fichero">
											</div>
										</div>
									</div>
							
						</logic:notPresent>		
						<div class="clear"></div>
						<br>						
									
						</logic:equal>
						
					</logic:present>
					</div>	
					
					<div class="pae-layout">
						<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-80">
							<div class="pae-box-buttons">
								<input type="submit" name="accion" value="Aceptar" onclick="return firmarFormulario();" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>								
							</div>
						</div>
					</div>				
					
				</logic:equal>
				
				<logic:equal name= "respuestaWS" value="S">
					<div class="capaAll">
					<b><spring:message code="field.ServiciosExternos.respuesta"/></b>
					<br><br>
					
					<spring:message code="field.ServiciosExternos.respuesta.tiempoRespuesta"/>
					<bean:write name="logServicioBean" property="tiempoRespuesta"/><br>
															
					<logic:equal value="ER" name="logServicioBean" property="resultado">
						<spring:message code="field.ServiciosExternos.respuesta.resultado"/>
						<spring:message code="field.ServiciosExternos.respuesta.ER"/><br>
						
						<spring:message code="field.ServiciosExternos.respuesta.codigoError"/>
						<bean:write name="logServicioBean" property="codigoError"/><br>
						
						<spring:message code="field.ServiciosExternos.respuesta.descripcionError"/>
						<bean:write name="logServicioBean" property="desTipoError"/><br>
					</logic:equal>
					<logic:notEqual value="ER" name="logServicioBean" property="resultado">
						<spring:message code="field.ServiciosExternos.respuesta.resultado"/>
						<spring:message code="field.ServiciosExternos.respuesta.OK"/><br>
					</logic:notEqual>
					
					<br>
					<br>
					<logic:notEqual value="ER" name="logServicioBean" property="resultado">
						<spring:message code="field.ServiciosExternos.respuestaObtenida"/>
						<br>
					</logic:notEqual>
					<logic:equal name="servicio" value="1">
						<logic:present name="defaultDatosPagoOut" scope="request">
							<spring:message code="field.ServiciosExternos.respuesta.NRC"/>
							<bean:write name="defaultDatosPagoOut" property="NRC"/><br>
						</logic:present>
					</logic:equal>
					<logic:equal name="servicio" value="2">
						<logic:present name="registro" scope="request">
							<spring:message code="field.ServiciosExternos.numRegistro"/>
							<bean:write name="registro" property="numRegistro" /><br>
						</logic:present>
					</logic:equal>
					<logic:equal name="servicio" value="3">
						<div class="capaIzq">
							<div class="labelIzq">
								<spring:message code="field.ServiciosExternos.numeroJustificante"/>
							</div>
							<div class="contenidoDer">
								<bean:write name="probarServiciosExternosForm" property="numeroJustificante"/>
							</div>	
						</div>			
					</logic:equal>
					<logic:equal name="servicio" value="4">
						<div class="capaIzq">
							<form:input type="text" path="certificadoDigital" readonly="true"  style="border: 0px; width: 200px;"/>
						</div>			
					</logic:equal>
					<logic:equal name="servicio" value="5">
						<logic:present name="docSubidoOK" scope="request">	
								<div class="capaIzq">
									<spring:message code="field.ServiciosExternos.docSubidoOK"/>:<bean:write name="probarServiciosExternosForm" property="rutaFichero"/>	
								</div>
						</logic:present>							
					</logic:equal>
					
					</div>
						<div class="pae-layout">
							<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-80">
								<div class="pae-box-buttons">								
									<input type="submit" value="Volver" onclick="volver()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>								
								</div>
							</div>
					</div>
				</logic:equal>										
			</logic:present>
					
			</fieldset>	
		</div>
		</div>
		</div>
		</form:form>
</body>
</html>