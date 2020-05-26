<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="es.map.ipsg.bean.DocumentoBean" %>

  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-bootstrap.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/selectize/dist/css/selectize.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/font-awesome/css/font-awesome.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/jquery-ui/themes/smoothness/jquery-ui.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-general.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-modules.css"/>

<%
String idSolicitud =  request.getAttribute("idSolicitud").toString();
%>

<script type="text/javascript">

function creaJustificante()
{
	window.location.href="../spring/gestionJustificanteSolicitud";
}

function guarda()
{
	//document.forms[0].action="../spring/guardarJustificanteGenerado";
	window.location.href="../spring/gestionJustificanteSolicitud";
	window.close();
}

function cierra()
{
	window.close();
}

</script>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="expires" content="Wed, 01 Jan 1997 00:00:00 GMT">
<base target="_self">
</head>
<body style="margin-left:0.4em;background:white" onload="creaJustificante();">
	<div style="margin-left:1em; margin-right: 1em;float: left;width: 95%;">
	<div class="pae-title"><spring:message code="field.documento.documentosAnexados"/></div>

	<logic:present name="justificanteSinFirmaSolicitud" scope="request"> 
		<logic:equal name="justificanteSinFirmaSolicitud" value="true">
			<br> </br>
	 		<div id="error">
				<spring:message code="field.documento.AvisoNoFirmaSolicitud"/>
			</div>	
		</logic:equal>	
	</logic:present>
	
	<logic:present name="justificanteSinFirmaJustificante" scope="request"> 
		<logic:equal name="justificanteSinFirmaJustificante" value="true">
			<br> </br>
	 		<div id="error">
				<spring:message code="field.documento.AvisoNoFirmaJustificante"/>
			</div>	
		</logic:equal>	
	</logic:present>
	
	<br> </br>
	<div style="margin-left:10px; padding-left: 20px;">
		<spring:message code="field.documento.mensajeGuardarDocumento"/>
	</div>
	<div class="clear"></div><br>
		<form:form modelAttribute="crearDocumentoForm" action="/IPSG/spring/documentosJustificanteSolicitud" id="formPadre">		
			<div class="pae-layout">
				<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-40">
					<div class="pae-box-buttons">	
						<input type="submit" name="accion" value="Aceptar" onclick="javascript: guarda()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>			
						<input type="submit" name="submit" value="Cancelar" onclick="javascript:cierra()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>	
						<form:hidden path="id" value="${idSolicitud}"/>
					</div>
				</div>	
			</div>
		</form:form>
	</div>
</body>
</html>