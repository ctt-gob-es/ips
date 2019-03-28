<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">

function cerrar(){
	window.close(); 
}

</script>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title></title>
</head>
<body style="margin-left:0.4em;">
<br>
<div id="capaAll">
	<!-- Errores -->
	<!-- ---------------------------- -->
	<div id="error">
	<logic:present name="avisoPago" scope="request">
		<logic:equal name="avisoPago" value="true"> 
			<spring:message code="field.solicitud.avisoNoEnPago" />
		</logic:equal>	
	</logic:present>	
		<logic:present name="avisoRegistro" scope="request">
		<logic:equal name="avisoRegistro" value="true"> 
			<spring:message code="field.solicitud.avisoNoEnRegistro" />
		</logic:equal>	
	</logic:present>
		
		<br><br/><br/><br/>
	</div>
	
	<div align="center">
		<input type="button" name="aceptar" value="<spring:message code="field.extractoDocumento.Aceptar" />" class="boton2" name="accion" onclick="cerrar()"/>
	</div>
</div>

</body>
</html>