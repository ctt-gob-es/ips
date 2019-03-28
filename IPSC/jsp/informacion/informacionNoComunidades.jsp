<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>

<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
<base target="_self">
<title></title>
</head>
<body style="margin-left:1em;">
<div style="margin-left:1em; margin-right: 1em;float: left;width: 95%;">
<br>

<div id="content_tituloNivel1">
		<div id="cuerpo_central_tituloNivel1">
     <spring:message code="field.nocomunidades"/></div>
</div>
<br>
<div class="clear"/>
<div style="width: 98%;">
	<spring:message code="field.informacion.nocomunidades"/>
</div>

<div class="posBotonDer">
<br><br>
	<input type="button" value="<spring:message code="field.Cerrar"/>" title="<spring:message code="field.Cerrar"/>" class="boton2" onclick="javascript:window.close();" >	
</div>
</div>
</div>
</body>
</html>