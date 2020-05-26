<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="es.map.ipsc.bean.*"%>

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

<body style="margin-left:1em; margin-right:1em;" class="background-color-white-ip">

<div class="pae-title2"><spring:message code="svdsepe.titulo.verificarVictimas"/></div>
<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">

<form:form modelAttribute="consultarVictimasTerrorismoForm" action="/IPSG/spring/verificarVictimasTerrorismo?auxiliar=false" id="Form" >
	<form:hidden path="idSolicitud" />
		
	<logic:present name="org.apache.spring.ERROR">
		<div id="error">
			<html:errors/>
		</div>
		<div class="clear"></div>
	</logic:present>
	
	
<%-- MAQUETACION INI --%>
<div class="">
	<div data-function="accordion-box" class="pae-box">
		<div class="pae-box__body">	
	
	<c:if test="${empty victimaTerrorismo}">
		<spring:message code="svdsepe.error.victimas.noRecuperada"/>
	</c:if>
		
	<div class="clear"></div><br>
		<c:if test="${not empty victimaTerrorismo}">			
				${victimaTerrorismo.descripcion}
		</c:if>	
	<div class="clear"></div><br><br>
	<div class="filaBtn">
 		<div class="class="pae-box-buttons">
			<div class="pae-box-buttons">	
				<input type="submit" name="accion" value="Aprobar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>		
				<input type="submit" name="accion" value="Rechazar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>			
				<input type="button" value="Cerrar" name="accion" onclick="javascript: window.close()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>	
			</div>
		</div>	
	</div>
	</div>
</div>
</div>	
<%-- MAQUETACION FIN --%>
</form:form>	
</body>
</html>

