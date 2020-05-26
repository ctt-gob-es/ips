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

<body style="margin-left:1em; margin-right:1em;">

<div class="pae-title"><spring:message code="svdccaadd.titulo.verificarDiscapacidad"/></div>
<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">

<form:form modelAttribute="verificarDiscapacidadForm" action="/IPSG/spring/verificarDiscapacidad?auxiliar=false">
	<form:hidden path="idSolicitud" />
	
	<br>
	<logic:present name="org.apache.spring.ERROR">
		<div id="error">
			<html:errors/>
		</div>
		<div class="clear"></div>
	</logic:present>
	<br>
			
	<div class="clear"></div><br><br>
					
			<logic:equal name="formulario" property="resultadoVerificacion" value="true">
				<div class="verificacionOK">
					<spring:message code="svdccaadd.titulo.discapacidad"/>
				</div>	
				<br>
				<bean:define id="gradoDiscapacidad" name="formulario" property="gradoDiscapacidad" type="java.lang.Integer"/>
				<spring:message code="svdccaadd.field.mensajeGradoDiscapacidadValido" arguments="${gradoDiscapacidad},<%=Constantes.GRADO_DISCAPACIDAD_EXENCION%>"/><br>
			</logic:equal>
			<logic:notEqual name="formulario" property="resultadoVerificacion" value="true">	
				<div class="verificacionKO">
					<spring:message code="svdccaadd.titulo.discapacidad"/>
				</div>	
				<br>	
				<logic:notEmpty name="formulario" property="gradoDiscapacidad" >
					<bean:define id="gradoDiscapacidad" name="formulario" property="gradoDiscapacidad" type="java.lang.Integer"/>
					<spring:message code="svdccaadd.field.mensajeGradoDiscapacidadInvalido" arguments="${gradoDiscapacidad},<%=Constantes.GRADO_DISCAPACIDAD_EXENCION%>"/><br>
				</logic:notEmpty>
				<logic:empty name="formulario" property="gradoDiscapacidad" >
					<spring:message code="svdccaadd.field.mensajeError"/><br>
				</logic:empty>	
			</logic:notEqual>

	
	<div class="clear"></div><br><br>
	
	<div class="pae-layout">
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-40">
			<div class="pae-box-buttons">	
				<input type="submit" name="accion" value="Aprobar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>		
				<input type="submit" name="accion" value="Rechazar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>		
				<input type="button" value="Cerrar" name="accion" onclick="javascript: window.close()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>
			</div>
		</div>
	</div>	
	
</form:form>	
