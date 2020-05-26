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
<%@page import= "java.util.List"%>

<script>
</script>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>

<body style="margin-left:0.4em;" onload="opener.document.forms[0].submit();">

<br>
<div id="content_tituloNivel1">
	<div id="cuerpo_central_tituloNivel1">
     <spring:message code="field.desactivarConvocatoria.titulo"/>
    </div>
</div>

<br><br>

<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">
		<b><spring:message code="field.desactivarConvocatoria.convocatoriasDesactivadas"/></b><br>	
		<logic:present name="listaConvocatoriasDesactivadas" scope="request">		
			<logic:iterate id="registro" name="listaConvocatoriasDesactivadas" scope="request">
				<li><bean:write name="registro" property="idConvocatoria"/></li><br>
			</logic:iterate>				
		</logic:present>
		<logic:notPresent name="listaConvocatoriasDesactivadas" scope="request">
			No hay ninguna convocatoria desactivada.
		</logic:notPresent>
	<br><br>
</div>
<br><br>


</body>
</html>