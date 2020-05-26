<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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

<body style="margin-left:0.4em;" onload="recargarPadre()" class="background-color-white-ip">

<div class="pae-body2" style="overflow-y:hidden">
<div class="pae-title2"><spring:message code="svdi.titulo.verificarFechaNacimiento"/></div>

<div class="pae-box">
 	<div class="pae-box__header2">	
		<logic:present name="org.apache.spring.ERROR">
			<div id="error">
				<html:errors/>
			</div>
			<div class="clear"></div>
		</logic:present>
		
		<logic:present name="identidad" scope="request">			
			<logic:equal name="identidad" property="resultadoVerificacion" value="true">
				<spring:message code="svdi.field.mensajeCoincide1"/><br>
				<spring:message code="svdi.field.mensajeCoincide2"/><br>
			</logic:equal>
			<logic:notEqual name="identidad" property="resultadoVerificacion" value="true">
				<logic:notEmpty name="solicitud" property="fechaNacimiento" >
					<bean:define id="fechaNacSolicitud" name="solicitud" property="fechaNacimiento" type="java.util.Date"/>
					<fmt:formatDate value="${fechaNacSolicitud}" pattern="dd/MM/yyyy" var="s_fechaNacSolicitud"/>
				</logic:notEmpty>
				<logic:empty name="solicitud" property="fechaNacimiento" >
					<bean:define id="s_fechaNacSolicitud" value="null">
					</bean:define>
				</logic:empty>
				
				<bean:define id="fechaNacServicio" name="identidad" property="fechaNacimiento" type="java.util.Date"/>
				<fmt:formatDate value="${fechaNacServicio}" pattern="dd/MM/yyyy" var="s_fechaNacServicio"/>				
<%-- 				<html:message code="svdi.field.mensajeNoCoincide1" arg0="${s_fechaNacSolicitud}" arg1="${s_fechaNacServicio}"/><br> --%>
				<c:if test="${s_fechaNacSolicitud} != ${s_fechaNacServicio}">
					<spring:message code="svdi.field.mensajeNoCoincide1a"/>${s_fechaNacSolicitud}
					<spring:message code="svdi.field.mensajeNoCoincide1b"/>${s_fechaNacServicio}
					<spring:message code="svdi.field.mensajeNoCoincide1c"/><br>
				</c:if>
				<spring:message code="svdi.field.mensajeNoCoincide2"/><br>
			</logic:notEqual>
		</logic:present>
		<logic:notPresent name="identidad" scope="request">
			<spring:message code="svdi.error.identidad.noRecuperada"/>
		</logic:notPresent>
	

	
		<div class="pae-layout__item">
			<div class="filaBtn">
				<div class="pae-box-buttons">		
					<input type="button" value="Cerrar" name="accion" onclick="javascript: window.close()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>
				</div>
			</div>
		</div>	
	</div>
</div>
</div>
</body>
</html>