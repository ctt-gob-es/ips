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

String.prototype.trim = function() { return this.replace(/^\s+|\s+$/g, ""); };

function valida() {
	document.getElementById("submit").value = "Guardar";
	document.forms[1].action="../spring/ActualizarAviso?menu=N";
}   


function limitarTextAreaTexto(){
	var texotArea = document.getElementById("texto").value;
	
	if(texotArea != null && texotArea.length>1000){
		alert('El tamaño máximo del campo es de 1000 caracteres');
		document.getElementById("texto").value = texotArea.substring(0,1000);
		document.getElementById("contadorTexto").value="0";
	}
	else{
		document.getElementById("contadorTexto").value = 1000-texotArea.length;
	}
}

function volver(){
	window.location.href = "<%=request.getContextPath()%>/spring/buscarAviso";	
	
}

</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title></title>
</head>
<body style="margin-left:0.4em;">

<br>

  <h1 class="pae-title"> <spring:message code="field.aviso.tituloModificar"/> </h1>
   

<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->


<form:form modelAttribute="avisoForm" action="/IPSG/spring/verActualizarAviso" method="post">
<form:hidden path="submit" id="submit" />
<input type="hidden" name="contadorTexto"  id="contadorTexto">	
<form:hidden path="idAviso" id="idAviso"/>

	
		<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>
		
	<div id="divPrincipal" class="">
	<div data-function="accordion-box" class="pae-box">
	<div class="pae-box__body">
	<fieldset>
	
	<div class="pae-layout">
		<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-2/1-palm">	
			<span class="pae-form__label pae-form__span-label">		
				<spring:message code="field.aviso.fechaInicioMay" />
			</span>
			<form:input path="fechaInicio" id="fechaInicio" data-function="function-datepicker"  placeholder="dd/mm/aaaa" class="pae-form__input" maxlength="10"></form:input>
<!-- 		<img type="image" src="../images/calendar/boton-calendario.gif" style="cursor: pointer;" alt="Abrir Calendario"  onclick="popUpCalendar(this, document.getElementById('fechaInicio'), 'dd/mm/yyyy',2);"> -->
			<script type="text/javascript">
					fncCalendario('fechaInicio', '<%=request.getContextPath()%>');
			</script>
		</div>
		<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-2/1-palm">	
			<span class="pae-form__label pae-form__span-label">	
					<spring:message code="field.aviso.fechaFinMay" />
			</span>
			<form:input path="fechaFin" id="fechaFin" data-function="function-datepicker"  placeholder="dd/mm/aaaa" class="pae-form__input" maxlength="10"></form:input>
<!-- 				<img type="image" src="../images/calendar/boton-calendario.gif" style="cursor: pointer;" alt="Abrir Calendario"  onclick="popUpCalendar(this, document.getElementById('fechaFin'), 'dd/mm/yyyy',2);"> -->
			<script type="text/javascript">
				fncCalendario('fechaFin', '<%=request.getContextPath()%>');
			</script>
		</div>
			
	</div>
	<div class="pae-layout">
		<div class="pae-layout__item pae-u-8/12 pae-u-2/12-lap pae-u-2/1-palm">	
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.aviso.textoAvisoMay" />
			</span>
			<form:textarea path="texto" id="texto" class="pae-form__input" cols="100" rows="10" style="overflow:hidden" onkeyup="limitarTextAreaTexto();"></form:textarea>					
		</div>
	</div>
	<div class="pae-layout">
		<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-2/1-palm">	
			<span class="pae-form__label pae-form__span-label">	
				<spring:message code="field.aviso.estadoAvisoMay" />
			</span>
			<form:select path="idEstadoAviso"	id="idEstadoAviso" class="pae-form__input">
				<form:options items="${estados}" itemLabel="descripcion" itemValue="idEstadoAviso"/>	
			</form:select>
		</div>
	</div>
	
<div class="filaBtn">
	<div class="pae-box-buttons pd-right-1">
		<input type="submit" name="submit" value="Modificar" onclick="valida()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium">		
		<input type="button" value="Cancelar" name="submit" onclick="volver()"  class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1"/>
	 </div>
</div>
	</fieldset>
	</div>
	</div>
	</div>
	
</form:form>

</body>
</html>



