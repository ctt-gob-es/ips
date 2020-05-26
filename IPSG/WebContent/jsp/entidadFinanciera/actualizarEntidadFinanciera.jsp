<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="es.map.ipsg.bean.*" %>

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
  <!-- Fin Archivos JS-->
<% String numPag = (String)request.getAttribute("paginaActual"); %>

<script type="text/javascript">

$(document).ready(function(){

	$('body').css("background-color","white");
	$('#pae-body').append($('#paetable'));
});

function cerrar(){
	window.close();
}



function busqueda(){
	document.getElementById("submit").value="busqueda";
	window.location.href = "../spring/buscarEntidadFinanciera";
}

</script>

<%@page import="es.map.ips.model.EntidadFinanciera"%><html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title></title>
</head>

<body style="margin-left:0.4em;">

<div class="capaAll">


<h1 class="pae-title">	<spring:message code="field.entidadFinanciera.tituloActualizar"/> </h1>


<br>
<!-- <div style="margin-left:1.5em; margin-right: 1em;float: left;width: 98%;"> -->

<form:form commandName="buscarEntidadFinancieraForm" action="/IPSG/spring/buscarEntidadFinanciera" id="formPadre" method="post">
	<div class="clear"></div><br>
		<jsp:useBean id="entidadFinancieraAltas" scope="request" class="java.util.ArrayList"/>
		<div class="sub_titulo">
			<spring:message code="field.entidadFinanciera.mensaje.altas"/>		
		</div>
		<logic:notEmpty name="entidadFinancieraAltas" scope="request">
		<table id="paetable" class="pae-table pae-table--margin">
		<caption class="hiddenAccesible">Tabla actualizar Entidad Financiera - Altas</caption>
			<thead class="pae-table__header">
             <tr class="pae-table__row">
				<th data-col="col1" class="pae-table__cell-header">
					<span class="pae-table__txt-title">
						<spring:message code="field.entidadFinanciera.codigo.mayus"/>
					</span>									
				</th>
				<th data-col="col2" class="pae-table__cell-header">
					<span class="pae-table__txt-title">
						<spring:message code="field.entidadFinanciera.descripcion.mayus"/>	
					</span>									
				</th>
			</tr>
		</thead>					
			<logic:iterate id="registro" name="entidadFinancieraAltas" >
			<tbody class="pae-table__body">
			<tr class="pae-table__row" name="row">
				<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body">
					<span class="pae-table__txt-base pae-table__span-head">
						<bean:write name="registro" property="codigo" />
					</span>
				</td>					
				<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body">
					<span class="pae-table__txt-base pae-table__span-head">
					<bean:write name="registro" property="descripcion" />
					</span>
				</td>
			</tr>	
			</tbody>						    		
			</logic:iterate> 	
		</table>
		</logic:notEmpty>	
		<logic:empty name="entidadFinancieraAltas" scope="request">
			<br>
			<div id="error">
				<spring:message code="field.entidadFinanciera.mensaje.vacio"/>
			</div>
		</logic:empty>	
		
	<div class="clear"></div><br><br><br>
		<jsp:useBean id="entidadFinancieraBajas" scope="request" class="java.util.ArrayList"/>		
		<div class="sub_titulo">
			<spring:message code="field.entidadFinanciera.mensaje.bajas"/>
		</div>
		<logic:notEmpty name="entidadFinancieraBajas" scope="request">
			<table id="paetable" class="pae-table pae-table--margin">
		<caption class="hiddenAccesible">Tabla actualizar Entidad Financiera - Bajas</caption>
			<thead class="pae-table__header">
             <tr class="pae-table__row">
					<th  data-col="col1" class="pae-table__cell-header">
						<span class="pae-table__txt-title">
							<spring:message code="field.entidadFinanciera.codigo.mayus"/>
						</span>								
					</th>
					<th  data-col="col2" class="pae-table__cell-header">
						<span class="pae-table__txt-title">
							<spring:message code="field.entidadFinanciera.descripcion.mayus"/>
						</span>										
					</th>
				</tr>		
				</thead>	
				<logic:iterate id="registro" name="entidadFinancieraBajas" >
				<tbody class="pae-table__body">
					<tr class="pae-table__row" name="row">
					<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body">
						<span class="pae-table__txt-base pae-table__span-head">
							<bean:write name="registro" property="codigo" />
						</span>
					</td>					
					<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body">
						<span class="pae-table__txt-base pae-table__span-head">
							<bean:write name="registro" property="descripcion" />
						</span>
					</td>
				</tr>
				</tbody>			    		
				</logic:iterate> 	
			</table>
		</logic:notEmpty>		
		
		<logic:empty name="entidadFinancieraBajas" scope="request">
			<br>
			<div id="error">
				<spring:message code="field.entidadFinanciera.mensaje.vacio"/>
			</div>
		</logic:empty>	
		
				
	<div class="clear"></div><br><br><br>

<div class="pae-layout">
  <div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm ">
		<div class="pae-box-buttons pd-top-3">
			<input type="submit" value="Cerrar" name="submit" onclick="cerrar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>
		</div>

	</div>
</div>
	 <div class="clear"></div>

		<logic:present name="noExiste" scope="request">
			<div id="error">
				<spring:message code="field.entidadFinanciera.error"/>
			</div>
		</logic:present>
			
			<br>
		<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>
	
</form:form>
	
</div>
</body>
</html>