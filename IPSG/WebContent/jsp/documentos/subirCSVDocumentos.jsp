<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="es.map.ipsg.bean.*" %>
<%@ page import="es.map.ipsg.util.Constantes"%>
<%
String sVieneMenu = (String) request.getAttribute("sVieneMenu"); 
String sPerfilUsuario =  request.getAttribute("sPerfilUsuario").toString();
%>
<script type="text/javascript">

	function cargaNombreArchivo() {
		var file = $("#file")[0].files[0]; 
		var extension = file.name.split(".").pop();

		if (!file || extension !== "csv" ) {
	        alert("Por favor seleccione un archivo CSV");
	        $("#rutaArchivo").val("");
	    } else {
	    	$("#rutaArchivo").val(file.name);
		}
	}

	function examinarArchivo() {
		if (document.getElementById('error') != null) {
			document.getElementById('error').style.display = 'none';
		} 
		document.getElementById('file').click();
	}
	
	

</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body style="margin-left:0.4em;">
 <div class="">
	
	<h1 class="pae-title"> <spring:message code="field.documentos.subirDocumentos"/> </h1>
	
	   

	<form:form commandName="crearCSVDocumentoForm" action="/IPSG/spring/subirCSVDocumento" id="formPadre" method="post" enctype="multipart/form-data">
		
	
		<logic:present name="org.apache.spring.ERROR" scope="request">
				<html:errors/>
<!-- 				<div class="clear"></div> -->
		</logic:present>
		<logic:present name="mensajeCsvCorrecto" scope="request">
				<strong><FONT color="#00FF00"><bean:write name="mensajeCsvCorrecto" scope="request"/></FONT></strong>
		</logic:present>
		
		<br/>
	<div data-function="accordion-box" class="pae-box">
	<div class="pae-box__body">
	<fieldset>	
	<div class="pae-layout">
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
			<div class="pae-form__cell">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.subirDocumentoCSV.ubicacion" />
				</span>
			<input type="text" id="rutaArchivo" readonly="true" onfocus="this.blur()" class="pae-form__input" >
			<input value="Examinar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline" type="button" onclick="examinarArchivo()">
			<input type="file" name="file" class="hidden" id="file" onchange="cargaNombreArchivo();"/>
		
<!-- 		<div class="clear"></div> -->
		
		</div>
	</div>
	</div>
<div class="pae-layout">
	<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-80">
		<div class="pae-box-buttons">
			<input type="submit" value="Subir documento CSV" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>	
		</div>
	</div>
</div>
		<div class="clear"></div>
		
		</fieldset>
	</div>
	</div>
	</form:form>
	
</div>
</body>
</html>