<%-- <%@ page language="java" contentType="text/html; charset=ISO-8859-1" --%>
<%--     pageEncoding="ISO-8859-1"%> --%>
<%-- <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> --%>
<%-- <%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %> --%>
<%-- <%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %> --%>
<%-- <%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %> --%>
<%-- <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> --%>
<%-- <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> --%>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> --%>

<%--   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-bootstrap.css"/> --%>
<%--   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/selectize/dist/css/selectize.css"/> --%>
<%--   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/font-awesome/css/font-awesome.css"/> --%>
<%--   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/jquery-ui/themes/smoothness/jquery-ui.css"/> --%>
<%--   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-general.css"/> --%>
<%--   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-modules.css"/> --%>
<%--<script type="text/javascript">
	window.onunload = refreshParent;
	function refreshParent() {
	    window.opener.location.reload();
	}
</script> --%>
<%-- <html> --%>

<!-- <head> -->
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<!-- <title></title> -->
<!-- </head> -->

<!-- <body style="margin-left:0.4em;"> -->

<%-- <div class="pae-title"><spring:message code="svto.titulo.verificarTitulos"/></div> --%>
<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->

<%-- <form:form modelAttribute="verificarTitulosForm" action="/IPSG/spring/verificarDatosTitulos" > --%>
<%-- 	<form:hidden path="idSolicitud" /> --%>
	
<!-- 	<br> -->
<%-- 	<logic:present name="org.apache.spring.ERROR"> --%>
<!-- 		<div id="error"> -->
<%-- 			<html:errors/> --%>
<!-- 		</div> -->
<!-- 		<div class="clear"></div> -->
<%-- 	</logic:present> --%>
<!-- 	<br> -->
	
<!-- 	<div> -->
<%-- 		<b><spring:message code="svto.titulos.solicitud"/></b> --%>
<!-- 	</div> -->
<!-- 	<br> -->
	
<%-- 	<logic:present name="tituloSolicitud"> --%>
<%-- 		- <bean:write name="tituloSolicitud" /> --%>
<%-- 	</logic:present> --%>
<%-- 	<logic:present name="otrosTituloSolicitud"> --%>
<%-- 		- <bean:write name="otrosTituloSolicitud" /> --%>
<%-- 	</logic:present> --%>
<%-- 	<logic:notPresent name="tituloSolicitud"> --%>
<%-- 		<logic:notPresent name="otrosTituloSolicitud"> --%>
<%-- 			<spring:message code="svto.titulos.solicitud.noExistenTitulos"/> --%>
<%-- 		</logic:notPresent>	 --%>
<%-- 	</logic:notPresent> --%>
	
<!-- 	<div class="clear"></div><br><br> -->
	
<!-- 	<div> -->
<%-- 		<b><spring:message code="svto.titulos.univ.encontrados"/></b> --%>
<!-- 	</div>	 -->
<!-- 	<br> -->
	
<%-- 	<logic:present name="titulosUniversitarios" scope="request"> --%>
<%-- 		<bean:size id="numResultados" name="titulosUniversitarios" scope="request"/> --%>
<%-- 		<logic:greaterThan name="numResultados" value="0"> --%>
<!-- 			<table id="paetable" class="pae-table pae-table--margin"> -->
<%-- 				<caption class="hiddenAccesible">Tabla de titulos universitarios</caption> --%>
<!-- 				<thead class="pae-table__header">	 -->
<!-- 				<tr class="pae-table__row">Nombres de cabecera de la tabla de resultados	 -->
<!-- 					<th data-col="col1" class="pae-table__cell-header">Universidad -->
<!-- 						<span class="pae-table__txt-title"> -->
<%-- 							<spring:message code="svto.titulos.universidad"/> --%>
<!-- 						</span> -->
<!-- 					</th> -->
<!-- 					<th data-col="col2" class="pae-table__cell-header">Nombre titulo -->
<!-- 						<span class="pae-table__txt-title"> -->
<%-- 							<spring:message code="svto.titulos.nombreCarrera"/> --%>
<!-- 						</span> -->
<!-- 					</th>						 -->
<!-- 				</tr> Resultados de la consulta -->
<!-- 				</thead> -->
<%-- 				<logic:iterate id="registro" name="titulosUniversitarios" > --%>
<!-- 					<tr> -->
<!-- 						<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body"> -->
<%-- 							<bean:write name="registro" property="universidad"/> --%>
<!-- 						</td> -->
<!-- 						<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body"> -->
<%-- 							<bean:write name="registro" property="nombreCarrera" /> --%>
<!-- 						</td>												 -->
<!-- 		   			</tr> -->
<%-- 				</logic:iterate> --%>
<!-- 			</table> -->
<%-- 		</logic:greaterThan>	 --%>
<%-- 		<logic:equal name="numResultados" value="0"> --%>
<%-- 				<spring:message code="svto.titulos.noExistenTitulos"/> --%>
<%-- 		</logic:equal> --%>
<%-- 	</logic:present> --%>
	
<!-- 	<div class="clear"></div><br><br> -->
	
<!-- 	<div> -->
<%-- 		<b><spring:message code="svto.titulos.nouniv.encontrados"/></b> --%>
<!-- 	</div>	 -->
<!-- 	<br> -->
	
<%-- 	<logic:present name="titulosNoUniversitarios" scope="request"> --%>
<%-- 		<bean:size id="numResultados" name="titulosNoUniversitarios" scope="request"/> --%>
<%-- 		<logic:greaterThan name="numResultados" value="0"> --%>
<!-- 			<table id="paetable" class="pae-table pae-table--margin"> -->
<%-- 				<caption class="hiddenAccesible">Tabla de titulos no universitarios</caption> --%>
<!-- 				<thead class="pae-table__header">	 -->
<!-- 				<tr class="pae-table__row">Nombres de cabecera de la tabla de resultados -->
<!-- 					<th data-col="col1" class="pae-table__cell-header">Universidad -->
<!-- 						<span class="pae-table__txt-title"> -->
<%-- 							<spring:message code="svto.titulos.universidad"/> --%>
<!-- 						</span> -->
<!-- 					</th> -->
<!-- 					<th data-col="col2" class="pae-table__cell-header">Nombre titulo -->
<!-- 						<span class="pae-table__txt-title"> -->
<%-- 							<spring:message code="svto.titulos.nombreCarrera"/> --%>
<!-- 						</span> -->
<!-- 					</th>				 -->
<!-- 				</tr> Resultados de la consulta -->
<!-- 				</thead> -->
<%-- 				<logic:iterate id="registro" name="titulosNoUniversitarios" > --%>
<!-- 					<tr> -->
<!-- 						<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body"> -->
<%-- 							<bean:write name="registro" property="universidad"/> --%>
<!-- 						</td> -->
<!-- 						<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body"> -->
<%-- 							<bean:write name="registro" property="nombreCarrera" /> --%>
<!-- 						</td>											 -->
<!-- 		   			</tr> -->
<%-- 				</logic:iterate> --%>
<!-- 			</table> -->
<%-- 		</logic:greaterThan>	 --%>
<%-- 		<logic:equal name="numResultados" value="0"> --%>
<%-- 				<spring:message code="svto.titulos.noExistenTitulos"/> --%>
<%-- 		</logic:equal> --%>
<%-- 	</logic:present> --%>

<!-- 	<div class="clear"></div><br><br> -->
<%-- 	<%-- Boton --%> --%>
<!-- 	<div class="pae-layout"> -->
<!-- 		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-40"> -->
<!-- 			<div class="pae-box-buttons"> -->
<!-- 				<input type="submit" name="accion" value="Aprobar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/> -->
<!-- 				<input type="submit" name="accion" value="Rechazar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>	 -->
<!-- 				<input type="button" value="Cerrar" name="accion" onclick="javascript: window.close()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>		 -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div>	 -->
	
<%-- </form:form> --%>
	
<!-- </body> -->
<%-- </html> --%>