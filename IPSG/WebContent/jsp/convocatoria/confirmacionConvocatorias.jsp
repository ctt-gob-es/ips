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
     <spring:message code="field.confirmarConvocatoria.titulo"/>
    </div>
</div>

<br><br>

<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">
		<b><spring:message code="field.confirmacionConvocatoria.convocatoriasAprobadas"/><br></b>	
		<logic:present name="listaConvocatoriasValidas" scope="request">
			<logic:notEmpty name="listaConvocatoriasValidas" scope="request">		
				<logic:iterate id="registro" name="listaConvocatoriasValidas" scope="request">
					<li><bean:write name="registro" property="idConvocatoria"/></li><br>
				</logic:iterate>
			</logic:notEmpty>
			<logic:empty name="listaConvocatoriasValidas" scope="request">
				No hay ninguna convocatoria aprobada.
			</logic:empty>				
		</logic:present>
		<logic:notPresent name="listaConvocatoriasValidas" scope="request">
			No hay ninguna convocatoria aprobada.
		</logic:notPresent>
	<br><br><br>
	
	<b><spring:message code="field.confirmacionConvocatoria.convocatoriasNoAprobadas"/><br></b>
	<logic:present name="listaConvocatoriasNoValidas" scope="request">
		<logic:notEmpty name="listaConvocatoriasNoValidas" scope="request">
			<center>
			<table class="tabla_resultados">
				<tr>
					<th>
					<spring:message code="field.confirmacionConvocatoria.id"/>
					</th>
					<th>
					<spring:message code="field.confirmacionConvocatoria.motivo"/>
					</th>
				</tr>
				<logic:iterate id="registro" name="listaConvocatoriasNoValidas" scope="request">
					<tr>
						<td>
							<bean:write name="registro" property="key"/>
						</td>
						<td>
							<bean:write name="registro" property="value"/>
						</td>
					</tr>
				</logic:iterate>			
			</table>
		</logic:notEmpty>
		<logic:empty name="listaConvocatoriasNoValidas" scope="request">
			No hay ninguna convocatoria no aprobada.
		</logic:empty>
	</logic:present>
	<logic:notPresent name="listaConvocatoriasNoValidas" scope="request">
		No hay ninguna convocatoria no aprobada.
	</logic:notPresent>
	<br><br>
</div>

<br><br>


</body>
</html>