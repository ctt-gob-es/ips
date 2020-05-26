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

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pagina principal pruebas</title>
</head>
<body>
		<p>
 		<a href="${request.getContextPath()}/secure/buscarMinisterioStart">
 		Buscar Ministerio
 		</a>
		<p>
		<a href="${request.getContextPath()}/secure/buscarGrupoStart">
		Buscar Grupo
 		</a>
		<p>
		<a href="${request.getContextPath()}/secure/buscarConvocatorias" >
		Lista Convocatorias
 		</a>
 		<p>
 		<a href="${request.getContextPath()}/secure/buscarSolicitudes">
		Lista Solicitudes
 		</a>
 		<p>
 		<a href="${request.getContextPath()}/secure/iniciarSolicitud" >
		Alta Solicitud
 		</a>
 		<p>
 		<a href="${request.getContextPath()}/secure/descargarFormulario790" >
		Mostrar Formulario 790
 		</a>

</body>
</html>