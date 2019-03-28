<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">

function pagarSolicitud(){
	var idSol = document.getElementById("idSolicitud").value;
	window.location.href = "<%=request.getContextPath()%>/secure/verPagarSolicitud?id="+idSol;	
}


function continuarInscripcion(){
	var idSol = document.getElementById("idSolicitud").value;
	window.location.href = "<%=request.getContextPath()%>/secure/verRegistroSolicitud?id="+idSol;	
}
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
<div style="margin-left:1px; margin-right: 1px;float: left;width: 99%;">
	
	<form:form modelAttribute="pagoSolicitudForm" action="/IPSC/secure/ConsultaPago" method="post" Id="form">
 	<form:hidden path="idConvocatoria" id="idConvocatoria"/>
 	<form:hidden path="numeroSolicitud" id="numeroSolicitud"/>
 	<form:hidden path="nrc" id="nrc"/>
 	<form:hidden path="accion" id="accion"/>
 	<form:hidden path="idSolicitud" id="idSolicitud"/>
 	<form:hidden path="formPago" id="formPago"/>
 	<form:hidden path="nombreParcial" id="nombreParcial"/>
 	<form:hidden path="nombre" id="nombre"/>
 	<form:hidden path="apellido1" id="apellido1"/>
 	<form:hidden path="apellido2" id="apellido2"/>
 	<form:hidden path="detalleMotivo" id="detalleMotivo"/>
 	<form:hidden path="signature" id="signature"/>
 	<form:hidden path="signerCert" id="signerCert"/>
 	<form:hidden path="aceptar" id="aceptar"/>
 	<form:hidden path="origenFirma" id="origenFirma"/>
	
	<br>
	<div id="content_tituloNivel1">
		<div id="cuerpo_central_tituloNivel1">
    		 <spring:message code="field.consultaPago.resultadoTitulo"/>
  		</div>
	</div>
		<div id="capaAll">
			<logic:equal name="defaultDatosPagoOut" property="fueCorrecto" value="false">
			<div style="width:98%">	
				<spring:message code="field.consultaPago.resultadoError"/></div>
				<div class="clear"></div>
				<br>
				<div class="capaIzq">
					<div class="labelIzqDet_Rojo">
						<spring:message code="field.pagoSolicitud.codigoError"/>
					</div>
					<div class="labelDrc">
						<bean:write name="defaultDatosPagoOut" property="errorCode"/>
					</div>
				</div>
				<div class="clear"></div>
				
				<div class="capaIzq">
					<div class="labelIzqDet_Rojo" >
						<spring:message code="field.pagoSolicitud.descripcionError"/>
					</div>
					<div class="labelDrc" >
						<bean:write name="defaultDatosPagoOut" property="errorCodeTexto"/>
					</div>
				</div>
				<div class="clear"></div>
			<div>
			</logic:equal>
			<logic:notEqual name="defaultDatosPagoOut" property="fueCorrecto" value="false">
			<div style="width:90%">	
				<div id="content_tituloNivel1">
					<div id="cuerpo_central_tituloNivel1"><spring:message code="field.consultaPago.resultadoExito"/></div>
				</div>
				<div class="clear"></div>
				<br>
				<div class="capaIzq">
					<div class="labelIzqDet_Rojo" >
						<spring:message code="field.pagoSolicitud.nifMay"/>
					</div>
					<div class="labelDrc" >
						<bean:write name="detallePasarela" property="nif"/>
					</div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
				<br>
				<div class="capaIzq">
					<div class="labelIzqDet_Rojo" >
						<spring:message code="field.pagoSolicitud.numJustificante"/>
					</div>
					<div class="labelDrc" >
						<bean:write name="detallePasarela" property="numeroSolicitud"/>
					</div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
				<br>
				<div class="capaIzq">
					<div class="labelIzqDet_Rojo" >
						<spring:message code="field.pagoSolicitud.importe"/>
					</div>
					<div class="labelDrc">
						<bean:write name="detallePasarela" property="importe"/>
					</div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
				<br>
				<div class="capaIzq">
					<div class="labelIzqDet_Rojo" >
						<spring:message code="field.pagoSolicitud.NRC"/>
					</div>
					<div class="labelDrc" >
						<bean:write name="defaultDatosPagoOut" property="NRC"/>
					</div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
				<br>
				<div class="capaIzq">
					<div class="labelIzqDet_Rojo" >
						<spring:message code="field.pagoSolicitud.fechaOperacion"/>
					</div>
					<div class="labelDrc" >
						<bean:write name="detallePasarela" property="fechaOperacion"/>
					</div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
				<br>
			</div>
			</logic:notEqual>			
			<div class="clear"></div>
			<br>
		</div>
		<div class="clear"></div><br>
		<div align="center">
			<logic:notEqual name="defaultDatosPagoOut" property="fueCorrecto" value="false">
	       		<input type="button" name="accion" titleKey="field.consultarPago.continuarInscripcion" class="botonContinuar" onclick="continuarInscripcion()"/>
	       			<spring:message code="field.consultarPago.continuarInscripcion"/>
			</logic:notEqual>
			<logic:equal  name="defaultDatosPagoOut" property="fueCorrecto" value="false">
		     	<input type="button" name="accion" titleKey="field.consultarPago.volverPago" class="botonGrande" onclick="pagarSolicitud();"/>
		       		<spring:message code="field.consultarPago.volverPago"/>
	       	</logic:equal>
       	</div>		
	<br>
	</form:form>
<div>
</body>
</html>