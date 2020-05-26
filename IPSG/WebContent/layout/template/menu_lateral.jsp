<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@page import="java.util.ResourceBundle"%>
<%
	final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("MessageResources");
	
	String usuarios = RESOURCE_BUNDLE.getString("field.menu.usuarios");
	String consultas = RESOURCE_BUNDLE.getString("field.menu.consultas");
	String solicitudes = RESOURCE_BUNDLE.getString("field.menu.solicitudes");
	String tablasBase = RESOURCE_BUNDLE.getString("field.menu.tablasBase");
	String convocatorias = RESOURCE_BUNDLE.getString("field.menu.convocatorias");
	String alertas = RESOURCE_BUNDLE.getString("field.menu.alertas");
	String avisos = RESOURCE_BUNDLE.getString("field.menu.avisos");
	String solicPresenciales = RESOURCE_BUNDLE.getString("field.menu.solicPresenciales");
	String documentos = RESOURCE_BUNDLE.getString("field.menu.documentos");
	String adminServExt = RESOURCE_BUNDLE.getString("field.menu.adminServExt");
	String formBaseCentroGestor = RESOURCE_BUNDLE.getString("field.menu.formBaseCentroGestor");
	
	String pagActiva = (String)request.getSession().getAttribute("pagActiva");
%>

	<%	
		if(usuarios.equals(pagActiva)){
	%>
		<%@ include file="../menuLateral/menLatUsuarios.jsp"%>
	<%	
		}
	%>
	
	<%	
		if(consultas.equals(pagActiva)){
	%>
		<%@ include file="../menuLateral/menLatConsultas.jsp"%>
	<%	
		}
	%>

	<%	
		if(solicitudes.equals(pagActiva)){
	%>
		<%@ include file="../menuLateral/menLatSolicitudes.jsp"%>
	<%	
		}
	%>
	
	<%	
		if(tablasBase.equals(pagActiva)){
	%>
		<%@ include file="../menuLateral/menLatTablasBase.jsp"%>
	<%	
		}
	%>
	
	<%	
		if(convocatorias.equals(pagActiva)){
	%>
		<%@ include file="../menuLateral/menLatConvocatoria.jsp"%>
	<%	
		}
	%>
	
	<%	
		if(alertas.equals(pagActiva)){
	%>
		<%@ include file="../menuLateral/menLatAlerta.jsp"%>
	<%	
		}
	%>
	
	<%	
		if(avisos.equals(pagActiva)){
	%>
		<%@ include file="../menuLateral/menLatAviso.jsp"%>
	<%	
		}
	%>
	
	<%	
		if(solicPresenciales.equals(pagActiva)){
	%>
		<%@ include file="../menuLateral/menLatSolicitudPresencial.jsp"%>
	<%	
		}
	%>
	
	<%	
		if(documentos.equals(pagActiva)){
	%>
		<%@ include file="../menuLateral/menLatDocumentos.jsp"%>
	<%	
		}
	%>
	
	<%	
		if(adminServExt.equals(pagActiva)){
	%>
		<%@ include file="../menuLateral/menLatServiciosExternos.jsp"%>
	<%	
		}
	%>
	
	<%	
		if(formBaseCentroGestor.equals(pagActiva)){
	%>
		<%@ include file="../menuLateral/menLatFormularioCG.jsp"%>
	<%	
		}
	%>
