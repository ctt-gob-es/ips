<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
function validar(){
	if(document.getElementById("texto").value == ""){
		alert("Debe introducir un mensaje");
	}
  	
}
function limitarTextAreaTexto(){
	var texotArea = document.getElementById("texto").value;
	
	if(texotArea != null && texotArea.length>2000){
		alert('El tamaño máximo del campo es de 2000 caracteres');
		document.getElementById("texto").value = texotArea.substring(2000);
		document.getElementById("contadorTexto").value="0";
	}
	else{
		document.getElementById("contadorTexto").value = 2000-texotArea.length;
	}
}

function cerrar(){
	window.close(); 
}
</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body style="margin-left:00.4em;">
<br>
<div id="content_tituloNivel1">
	<div id="cuerpo_central_tituloNivel1">
     <spring:message code="field.usuario.tituloReportarIncidencias"/>
     </div>
</div>

<br>



<form:form commandName="incidenciasForm" action="/IPSG/spring/reportarIncidencias" id="formPadre" method="post">
<div style="margin-left:1em; margin-right: 1em;float: left;width: 75%;">
<logic:present name="incidencia" scope="request">

</logic:present>

	<logic:present name="mensajeConfirmacion" scope="request">
		<strong><bean:write name="mensajeConfirmacion" scope="request"/></strong>
	</logic:present>
	<logic:present name="mensajeError" scope="request">
		<strong style="color: #c33400;"><bean:write name="mensajeError" scope="request"/></strong>
	</logic:present>
	<br><br>
	<div>
		<div class="labelIzq_larg">
			<spring:message code="field.usuario.nif.mayus"/>
		</div>
		<div class="labelDrc_larg">
			<bean:write name="incidencia" property="nif" scope="request" />
		</div>
	</div>
		<div class="clear"></div>
	<br><br>
	<div>
		<div class="labelIzq_larg">
			<spring:message code="field.usuario.nombre.mayus"/>
		</div>
		<div class="labelDrc_larg">
			<bean:write name="incidencia" property="nombre" scope="request" />		
		</div>
	</div>
	<div class="clear"></div>
	<br><br>
	<div>
		<div class="labelIzq_larg">
			<spring:message code="field.usuario.email.mayus"/>
		</div>
		<div class="labelDrc_larg">
			<bean:write name="incidencia" property="email" scope="request" />
		</div>
	</div>
	<br><br><br>
		<div class="clear"></div>
	<div>
		<div class="labelIzq_larg">
			<spring:message code="field.usuario.telefono.mayus"/>
		</div>
		<div class="labelDrc_larg">
			<bean:write name="incidencia" property="telefono" scope="request" />
		</div>
	</div>
	<br><br><br>
	<div class="clear"></div>
	<div>
		<div class="labelIzq_larg">
		<spring:message code="field.incidencias.texto"/>	
		</div>	
	</div>
	<div>
		<div class="labelDrc_larg">
			<input type="text" name="contadorTexto2" id="contadorTexto2" value="2000" size="4" disabled="disabled" style="border:0em;" align="bottom"/>
			/
			<input type="text" name="contadorTexto" id="contadorTexto" readonly="readonly" value="2000" size="4" disabled="disabled" style="border:0em;" align="bottom"/>
	  		
		</div>
	</div>
	<br><br><br>
	<div class="clear"></div>
	<div>
		<div class="labelIzq_larg">
			<form:textarea path="texto" id="texto" cols="90" rows="7" style="font: normal 100% Arial;font-size=12px" onkeyup="limitarTextAreaTexto();"/>
		</div>
	</div>
	<br><br>
	</div>	
	<div class="clear"></div><br>
	<div class="posBotonDer">
		<logic:notPresent name="mensajeConfirmacion" scope="request">
			<input type="submit" value="Enviar" class="boton" name="accion" onclick="validar()"/>
		</logic:notPresent>
		<logic:present name="mensajeConfirmacion" scope="request">
			<input type="button" value="Cerrar" class="boton" name="accion" onclick="cerrar()"/>
		</logic:present>
	</div>
	<div class="clear"></div>
	
</form:form>



</body>
<script>
if(document.getElementById("texto")!=null && document.getElementById("texto").value!=""){
	document.getElementById("contadorTexto").value = 2000 - document.getElementById("texto").value.length;
}
</script>
</html>


