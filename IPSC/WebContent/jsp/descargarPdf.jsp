<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean"%>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html"%>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="es.map.ipsc.Constantes"%>
<%@ page import="es.map.ipsc.bean.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet"
	href='<%=request.getContextPath()%>/css/detalles.css' type="text/css">
<link rel="stylesheet"
	href='<%=request.getContextPath()%>/css/default.css' type="text/css">
<link rel="stylesheet"
	href='<%=request.getContextPath()%>/css/estilo_060.css' type="text/css">
<title></title>
</head>
<body style="margin-left: 1em;">
	<br />
	<div id="content_tituloNivel1">
		<div id="cuerpo_central_tituloNivel1">
			<spring:message code="field.documento.Modelo790007" />
		</div>
	</div>
	<br />
	<form:form modelAttribute="springForm" action="IPSC/secure/descargarPdf" method="post">
		<div class="errorIdioma">
			<logic:present name="errorIdioma">
				<spring:message code="error.descargarDocumento.validarIdioma" />
			</logic:present>
		</div>
		<div class="clear"></div>
		<div class="capaDescargar">
			<div class="labelIzqDet_Rojo">
				<spring:message code="field.idiomas" />
			</div>
			<div class="labelDrc">
				<form:select path="idiomas">
					<form:option value="">
						<spring:message code="field.documento.seleccioneIdioma" />
					</form:option>
					<form:option value="es">
						<spring:message code="field.documento.idiomaCastellano" />
					</form:option>
					<form:option value="ca">
						<spring:message code="field.documento.idiomaCatalan" />
					</form:option>
					<form:option value="eu">
						<spring:message code="field.documento.idiomaEuskera" />
					</form:option>
					<form:option value="gl">
						<spring:message code="field.documento.idiomaGallego" />
					</form:option>
					<form:option value="vl">
						<spring:message code="field.documento.idiomaValenciano" />
					</form:option>
				</form:select>
			</div>
		</div>
		<div class="posBotonDercargar">
			<input type="submit"
				value="<spring:message code="field.documento.descargar"/>"
				title="<spring:message code="field.documento.descargar"/>"
				class="boton2"> <input type="button"
				value="<spring:message code="field.Cerrar"/>"
				title="<spring:message code="field.Cerrar"/>"
				onclick="javascript:window.close();" class="boton2">
		</div>
	</form:form>
</body>
</html>
