<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="es.map.ipsg.util.Constantes"%>

  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-bootstrap.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/selectize/dist/css/selectize.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/font-awesome/css/font-awesome.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/jquery-ui/themes/smoothness/jquery-ui.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-general.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-modules.css"/>
<script type="text/javascript">
	window.onunload = refreshParent;
	function refreshParent() {
	    window.opener.location.reload();
	}
</script>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>

<body style="margin-left:0.4em;" class="background-color-white-ip">

<div class="pae-body2" style="overflow-y:hidden">
	<div class="pae-title2"><spring:message code="field.consultarPagoAeat"/></div>
	<div class="pae-box">
		<div class="pae-box__header2">
		<logic:present name="verificarNRC">		
				<logic:equal name="verificarNRC" property="fueCorrecto" value="true">
					<spring:message code="field.consultarPagoAeat.errores.NRCValido"/>				
				</logic:equal>
				<logic:notEqual name="verificarNRC" property="fueCorrecto" value="true">	
					<spring:message code="field.consultarPagoAeat.errores.NRCInvalido"/>
				</logic:notEqual>
				<logic:empty name="verificarNRC" property="fueCorrecto" >
					<spring:message code="field.consultarPagoAeat.errores.vacio"/><br>
				</logic:empty>
			</logic:present>	
			<logic:notPresent name="verificarNRC">
				<spring:message code="field.consultarPagoAeat.errores.vacio"/><br>
			</logic:notPresent>
			<logic:present name="org.apache.spring.ERROR">
				<div id="error">
					<html:errors/>
				</div>
				<div class="clear"></div>
			</logic:present>
		</div>
	</div>
	
	<div class="pae-layout">
			<div class="filaBtn">
				<div class="pae-box-buttons">	
				<input type="button" value="Cerrar" name="accion" onclick="javascript: window.close()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>
				</div>
			</div>
		</div>
	
</div>

	
	</body>
	</html>