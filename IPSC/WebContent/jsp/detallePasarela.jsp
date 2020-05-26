<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*" %>
<%@ page import="es.map.ipsc.bean.*" %>



<!-- ****************************************************************************** -->
<!-- Formulario                                                                     -->
<!-- ****************************************************************************** -->
<!--log:dump scope="page"/-->
<script type="text/javascript">

function pagarSolicitud(){
	var idSol = document.getElementById("idSolicitud").value;
	window.location.href = "<%=request.getContextPath()%>/secure/verPagarSolicitud?id="+idSol;	
}

</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
<div style="margin-left:1px; margin-right: 1px;float: left;width: 99%;">
	<br>

	<div id="content_tituloNivel1">
		<div id="cuerpo_central_tituloNivel1">
    		 <spring:message code="field.solicitud.detallePasarela"/>
  		</div>
	</div>

<center>

<div id="enviar" 
	style="display:none;
		   position:absolute;
		   background-color:#ffffff;
	       filter:Alpha(Opacity=70);
	       top:0px;
	       left:0px;
	       width:100%;
	       height:150em">
	<div style="margin-top:0%; margin-left:15%;">
		<img alt="Esperar..." src="../../images/waiting.gif">
	</div>
	<div style="margin-top:0%; margin-left:15%; font-size:1.5em;">
		<spring:message code="field.message.pasarela"/>
	</div>
	<div style="margin-bottom:2%; margin-left:15%; font-size:1.5em">
	</div>
</div>


<form:form modelAttribute="pagoSolicitudForm" action="/IPSC/secure/ConsultaPago" method="post" id="form">
 	<form:hidden path="idSolicitud" id="idSolicitud" name="detallePasarela"/>
 	<form:hidden path="numeroSolicitud" id="numeroSolicitud" name="detallePasarela"/>
 	<form:hidden path="codigoTasa" id="codigoTasa" name="detallePasarela"/>
 	<form:hidden path="importe" id="importe" name="detallePasarela"/>
 	<form:hidden path="tipoDocumento" id="tipoDocumento" name="detallePasarela"/>
 	<form:hidden path="nif" id="nif" name="detallePasarela"/>
 	<form:hidden path="nombre" id="nombre" name="detallePasarela"/>
 	<form:hidden path="apellido1" id="apellido1" name="detallePasarela"/>
 	<form:hidden path="apellido2" id="apellido2" name="detallePasarela"/>
 	<form:hidden path="bancoAdeudo" id="bancoAdeudo" name="detallePasarela"/>
 	<form:hidden path="bancoTarjeta" id="bancoTarjeta" name="detallePasarela"/>
 	<form:hidden path="numTarjeta" id="numTarjeta" name="detallePasarela"/>
 	<form:hidden path="numTarjeta2" id="numTarjeta2" name="detallePasarela"/>
 	<form:hidden path="numTarjeta3" id="numTarjeta3" name="detallePasarela"/>
 	<form:hidden path="numTarjeta4" id="numTarjeta4" name="detallePasarela"/>
 	<form:hidden path="caducidadTarjeta1" id="caducidadTarjeta1" name="detallePasarela"/>
 	<form:hidden path="caducidadTarjeta2" id="caducidadTarjeta2" name="detallePasarela"/>
 	<form:hidden path="oficina" id="oficina" name="detallePasarela"/>
 	<form:hidden path="dc" id="dc" name="detallePasarela"/>
 	<form:hidden path="cuenta" id="cuenta" name="detallePasarela"/>
 	<form:hidden path="tipoCargo" id="tipoCargo" name="detallePasarela"/>
 	<form:hidden path="codBanco" id="codBanco" name="detallePasarela"/>
 	<form:hidden path="origenFirma" id="origenFirma" name="detallePasarela"/>
 	<form:hidden path="signature" id="signature" name="detallePasarela"/>
 	<form:hidden path="signerCert" id="signerCert" name="detallePasarela"/>
 	<form:hidden path="formaPago" id="formaPago" name="detallePasarela"/>
 	
 	<br>
 	<div style="width:90%">
 		<div id="error">
			<spring:message code="error.titulo"/>&nbsp;<bean:write name="errorPasarela"/>
			<br>
		</div>
 		
	<!--  	<div id="barra_Titular" align="left" style="color:#CF0547;font-size: 15px">
			<div id="capaAll" align="center">	  -->			
			<div class="clear"></div>
			<br>

		<div class="clear"></div>
		<br>
	
	<div>
		<spring:message code="field.pagoSolicitud.detallePasarela"/>
		<br><br>
	</div>	
	<div>
		<button type="submit" id="action" titleKey="field.consultarPago" class="botonSmall103" >
       		<spring:message code="field.consultarPago"/>
       	</button>
       	<input type="button" name="action" id="action" titleKey="field.errorPasarela.volverPago" class="botonSmall103" onclick="pagarSolicitud();"/>
       		<spring:message code="field.errorPasarela.volverPago"/>
	</div>

</div>        
</form:form>
</center>
</div>
</body>
</html>
