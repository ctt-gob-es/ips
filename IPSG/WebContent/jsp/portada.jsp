<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ page import="es.map.ipsg.bean.*" %>
<% 
	String mostrar = (String)request.getAttribute("mostrarAlertas");	
%>
<script type="text/javascript">

function alertaspopUp(parametro){
	if(parametro == "mostrarAlertas"){
		ventanaPopup = window.open("../spring/verAlertas", 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
	}
}

</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body style="margin-left:1em;" onload="alertaspopUp('<%=mostrar %>')">

<div id="cuerpo">
 	<h2 align="center">
 		
		<bean:message key="field.portada.cabecera"/>
		<br><br>
		<bean:message key="field.portada.perfil"/>
		<bean:write name="usuario" property="desPerfil"/>
		<br><br>
		<bean:message key="field.portada.usuario"/>		
		<bean:write name="usuario" property="nombre"/>
		<bean:write name="usuario" property="primerApellido"/>
		<bean:write name="usuario" property="segundoApellido"/>
	</h2>

</div>

</body>
</html>


