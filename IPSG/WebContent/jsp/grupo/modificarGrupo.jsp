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
function limpiar(){
	document.getElementById("descripcion").value = "";
	document.getElementById("codigo").value = "";
	document.getElementById("siglas").value = "";	
}

String.prototype.trim = function() { return this.replace(/^\s+|\s+$/g, ""); };
function valida() {
    if (document.getElementById("codigo").value.trim().length == 0) {
    	document.getElementById("codigo").value= "";
    }
    if (document.getElementById("descripcion").value.trim().length == 0) {
    	document.getElementById("descripcion").value= "";
    }
    if (document.getElementById("siglas").value.trim().length == 0) {
    	document.getElementById("siglas").value= "";
    }    
   	document.getElementById("submit").value= "Modificar";
    
}


function pulsar(e) { 
    tecla = (document.all) ? e.keyCode : e.which; 
    if (tecla==8) return true; 
    patron =/s/; 
    te = String.fromCharCode(tecla); 
    return !patron.test(te); 
} 


</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language='javascript' src="../javascript/utils.js"></script> 
<title></title>
</head>
<body style="margin-left:0.4em;">
<div class="capaAll">
<h1 class="pae-title">  <spring:message code="field.grupo.mantenimiento.titulo"/> </h1>


<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->
<div data-function="accordion-box" class="pae-box">

<form:form modelAttribute="grupo" action="/IPSG/spring/actualizarGrupo" method="post">
<input type="hidden" name="submit"  id="submit">
	

	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>
	<br>
	<!-- Ini ID -->
	<div class="pae-box__body">
		<fieldset>
			<div class="pae-layout">
			<div class="pae-layout__item pae-u-1/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.grupo.id"/>
					</span>
					<logic:present name="grupo" scope="request">
					<form:input path="id" name="grupo" id="id"
					 class="pae-form__input pae-box--transparent" readonly="true"/>
					</logic:present>
					<logic:notPresent name="grupo" scope="request">
					<form:input path="id" id="id" 
					 class="pae-form__input pae-box--transparent" readonly="true"/>
					</logic:notPresent>
				</div>
			</div>
			<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.grupo.siglasMay"/><span class="obligatorio"> *</span>
					</span>
					<logic:present name ="grupo" scope="request">
					<form:input path="siglas" name="grupo" id="siglas" class="pae-form__input" maxlength="5"/>
					</logic:present>
					<logic:notPresent name ="grupo" scope="request">
					<form:input path="siglas" id="siglas" class="pae-form__input" maxlength="5"/>
					</logic:notPresent>
				</div>
			</div>
			<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.grupo.codigo"/><span class="obligatorio"> *</span>
					</span>
					<logic:present name="grupo" scope="request">
					<form:input path="codigo" name="grupo" id="codigo" class="pae-form__input" maxlength="10"/>
					</logic:present>
					<logic:notPresent name="grupo" scope="request">
					<form:input path="codigo" id="codigo" class="pae-form__input" maxlength="10"/>
					</logic:notPresent>
				</div>
			</div>
			<div class="pae-layout__item pae-u-5/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.grupo.descripcion"/><span class="obligatorio"> *</span>
					</span>
					<logic:present name="grupo" scope="request">		
					<form:input path="descripcion" name="grupo" id="descripcion" class="pae-form__input" maxlength="100"/>
					</logic:present>
					<logic:notPresent name="grupo" scope="request">
					<form:input path="descripcion" id="descripcion" class="pae-form__input" maxlength="100"/>
					</logic:notPresent>
				</div>
			</div>
			
	<!-- Fin Descrpción -->
	<!-- Ini Visibilidad-->
			<logic:present name="grupo" scope="request">
				<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-3_7">
	 			<div class="pae-form__cell">
				<c:choose>
				    <c:when test="${grupo.visibilidad == true }">
				        <input type="checkbox" name="visibilidad" property="visibilidad" id="visibilidad" data-function="checkbox-custom-input" class="pae-form__custom-check" checked="checked">
				    </c:when>    
				    <c:otherwise>
						<input type="checkbox" name="visibilidad" property="visibilidad" id="visibilidad" data-function="checkbox-custom-input" class="pae-form__custom-check">
				    </c:otherwise>
				</c:choose>	
					<label for="visibilidad" data-function="checkbox-custom" class="pae-form__custom-check-label pae-form__custom-check-label--option">
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.general.visibilidadTitulo"/> 
						</span>
					</label>					 			
				</div>
				</div>
			</logic:present>		
	</div>
</div>


<div class="filaBtn">
		<div class="pae-box-buttons">
		<input type="submit" name="submit" value="Modificar" onclick="valida()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
		<input type="button" value="Cancelar" name="submit" onclick="volver('../spring/buscarGrupo')"  class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline mg-left-1"/>
	</div>
</div>

	<br><br><br>
	
</form:form>
</fieldset>	
</div>
</div>	

</body>
</html>


