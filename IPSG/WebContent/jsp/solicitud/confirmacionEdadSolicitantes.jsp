<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="es.map.ipsg.bean.*" %>

<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/javascript/jquery-1.10.2.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/javascript/jquery-ui-1.10.4.custom.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/javascript/Datepicker_calendario.js"></script>
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/javascript/utilsFechas.js"></script>

  <!-- Archivos CSS-->
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-bootstrap.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/selectize/dist/css/selectize.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/font-awesome/css/font-awesome.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/jquery-ui/themes/smoothness/jquery-ui.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-general.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-modules.css"/>
  <!-- Fin archivos CSS-->
  <!-- Archivos JS-->
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery/jquery.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/core.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/widget.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/mouse.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/position.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/draggable.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/resizable.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/menu.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/selectmenu.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/tabs.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/button.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/datepicker.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/dialog.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/progressbar.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/accordion.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/tooltip.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/tablesorter/jquery.tablesorter.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/bootstrap-sass/assets/javascripts/bootstrap.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-ui/ui/autocomplete.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/selectize/dist/js/standalone/selectize.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/bower_components/jquery-placeholder/jquery.placeholder.js"></script>
  <script type="text/javascript" src="<%=request.getContextPath()%>/js/scripts.min.js"></script>
  <!-- Fin rchivos JS-->


<jsp:useBean id="convocatorias" class="java.util.ArrayList" scope="request"></jsp:useBean> 

<script type="text/javascript">


function recargarPadre(){
	window.opener.location.href = "../spring/buscarSolicitudesRegistradas";
}

function volver(){
	window.close(); 		
}
</script>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 
<title></title>
</head>

<body style="margin-left:1em;" onload="recargarPadre();" class="background-color-white">

<br>

<h1 class="pae-title"><spring:message code="field.verificarEdad.titulo"/></h1>	
<br><br>

<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">

<form:form modelAttribute="crearConvocatoriaForm" action="/IPSG/spring/buscarSolicitudesRegistradas" id="formPadre" >
	
	
<div class="capaAll" align="center">
<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
</logic:present>
	
	<spring:message code="field.verificarEdad.confirmacion"/><br><br>
	<ul>
		<li><spring:message code="field.verificarEdad.confirmacion1"/></li>
		<li><spring:message code="field.verificarEdad.confirmacion2"/></li>
		<li><spring:message code="field.verificarEdad.confirmacion3"/></li>
	</ul>

	<div class="clear"></div>
	<br><br>
	
	<div class="pae-box-buttons pd-right-1">		
		<input type="button" value="Cerrar" name="accion" onclick="volver()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>	
	</div>
</div>

</form:form>
	
</div>
</body>
</html>