<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String envio = (String)request.getAttribute("envio");
System.out.println("Envio: "+envio);
String resultEnvio ="";
if(envio != null){
	resultEnvio = envio;
}

%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body style="margin-left:1em;">

<br>
<div id="content_tituloNivel1">
		<div id="cuerpo_central_tituloNivel1"><spring:message code="field.incidencia.tituloReportarIncidencias"/></div>
</div>
<br>
<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">
	<logic:present name="errorNifNoExiste">
		<div id="error">
			<bean:write name="errorNifNoExiste" scope="request"/>
		</div>
	</logic:present>
	<div class="clear"></div>
	<br>
</div>
</body>
</html>


