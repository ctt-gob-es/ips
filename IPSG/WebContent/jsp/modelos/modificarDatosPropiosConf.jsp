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

function volver(idCampo){
	window.location.href = "../spring/verModificarCampoPropio?id="+idCampo; 
}

function addOption(idCampo){
	if ($("#id").val() == "" || $("#numModelo").val() == "" ||  $("#descripcion").val() == "" ) {
		$("#error").html("<div id='rellenarCampos' class='pae-alertbox pae-alertbox--warning'><p class='pae-alertbox__text fw-bold'>Errores:</p><ul><li><p class='pae-alertbox__text'>No se puede añadir si existen campos vacíos</p></li></ul></div>");
	} else {
		window.location.href = "../spring/anadirDatosCamposPropios?id="+idCampo; 
	}	
}

function comprobarPasteCaracteresEspeciales(texto) {

	setTimeout(function() {
		// get the value of the input text
		var data = $(texto).val();
		// replace the special characters to ''
		var dataFull = data.replace(/[^\w\sÑÁÉÍÓÚ'Ü]/gi, '');
		
		$(texto).val(dataFull);
				
	});
}

function soloLetrasyNumeros(evt) {

	if(evt.key.match(/[a-z0-9ñçáéíóú\s]/i)===null) {
		evt.preventDefault();
		return false;
	}else{
		return true;
	}
}


</script>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	
<title></title>
</head>

<body style="margin-left:0.4em;" >



<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">

<form:form commandName="datosPropiosConfForm" action="/IPSG/spring/modificarDatosCamposPropios" id="formPadre" method="post">


<div class="">

	<logic:present name="org.apache.spring.ERROR">
		<div id="error">
			<html:errors/>
		</div>
		<div class="clear"></div>
	</logic:present>
	
	
		<h1 class="pae-title"><spring:message code="field.camposPropios.modConfigCampo"/></h1>	
	
		<div data-function="accordion-box" class="pae-box">
			<div class="pae-box__body">   
					<fieldset>
						<!-- Ini ID -->
						<div class="pae-layout">
							<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">	
								<div class="pae-form__cell">
									<span class="pae-form__label pae-form__span-label">	
										<spring:message code="field.camposPropios.id"/>
									</span>			
									<form:input type="text" path="idDesplegable" name="camposPropiosBean" class="pae-form__input pae-box--transparent" readonly="true"/>
								</div>
							</div>
							<!-- Fin ID -->						
							<!-- Ini ID CAMPO -->
							<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">	
								<div class="pae-form__cell">
									<span class="pae-form__label pae-form__span-label">	
										<spring:message code="field.camposPropios.idCampo"/>
									</span>			
									<form:input type="text" path="id" name="camposPropiosBean" class="pae-form__input pae-box--transparent" readonly="true"/>
								</div>
							</div>
							<!-- Fin ID CAMPO -->					
							<!-- Ini Descripcion -->						
							<div class="pae-layout__item pae-u-5/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">	
								<div class="pae-form__cell">
									<span class="pae-form__label pae-form__span-label">
										<spring:message code="field.gestionModelos790.descripcionCampo"/><span class="obligatorio"> *</span>
									</span>
									<form:input type="text" path="descripcion" name="camposPropiosBean" class="pae-form__input" maxlength="100" onPaste="comprobarPasteCaracteresEspeciales(this);" onkeypress="return soloLetrasyNumeros(event);"/>
								</div>
							</div>						
						</div>					
						<!-- Fin Descripcion -->				
				<div class="filaBtn">
					<div class="pae-box-buttons pd-right-1">
							<input type="submit" name="accion" value="Modificar Campo" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>
							<input type="button" value="Volver" onclick="volver(${datosPropiosConfForm.id})" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1" />																	
					</div>
				</div>
				</fieldset>		
			</div>
		</div>
					
		</form:form>
	</div>
	</body>
</html>


