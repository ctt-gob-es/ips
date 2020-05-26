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

function cancelar(idModelo){
	window.location.href = "../spring/verModificarCampoPropio?id="+idModelo; 
}

function comprobarPasteCaracteresEspeciales(texto) {

	setTimeout(function() {
		// get the value of the input text
		var data = $(texto).val();
		// replace the special characters to ''
		var dataFull = data.replace(/[^\w\s—¡…Õ”⁄'‹]/gi, '');
		
		$(texto).val(dataFull);
				
	});
}

function soloLetrasyNumeros(evt) {

	if(evt.key.match(/[a-z0-9ÒÁ·ÈÌÛ˙\s]/i)===null) {
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
	
	<logic:present name="error">
		<div id="error">
			<html:errors/>
		</div>
		<div class="clear"></div>
	</logic:present>

		<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">

		<form:form commandName="datosPropiosConfForm" action="/IPSG/spring/anadirDatosCamposPropios" id="formPadre" method="post">	
<%-- 			<form:hidden path="modeloBean" value="modeloBean"/> --%>
			<div class="">				
				<logic:present name="org.apache.spring.ERROR">
					<div id="error">
						<html:errors/>
					</div>
					<div class="clear"></div>
				</logic:present>
				
				<h1 class="pae-title"><spring:message code="field.camposPropios.configCampo"/></h1>
				
				<div data-function="accordion-box" class="pae-box">
					<div class="pae-box__body">   
						<fieldset>				
							<!-- Ini ID -->
							<div class="pae-layout">
								<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">	
									<div class="pae-form__cell">
										<span class="pae-form__label pae-form__span-label">	
											<spring:message code="field.gestionModelos790.id"/>
										</span>																	
										<logic:present name="modeloBean" scope="request">
											<form:input type="text" path="id" name="modeloBean" class="pae-form__input pae-box--transparent" readonly="true"/>
										</logic:present>
										<logic:notPresent name="modeloBean" scope="request">
											<form:input type="text" path="id" class="pae-form__input pae-box--transparent" readonly="true"/>
										</logic:notPresent>
									</div>								
								</div>
								<!-- Fin ID -->
			
								<!-- Ini Descripcion -->
								<div class="pae-layout__item pae-u-6/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">	
									<div class="pae-form__cell">
										<span class="pae-form__label pae-form__span-label">	
											<spring:message code="field.gestionModelos790.descripcion"/><span class="obligatorio"> *</span>
										</span>
										<form:input type="text" path="descripcion" class="pae-form__input" maxlength="100" onPaste="comprobarPasteCaracteresEspeciales(this);" onkeypress="return soloLetrasyNumeros(event);"/>
									</div>
								</div>
						</div>					
					<!-- Fin Descripcion -->
				<br>
				<div class="pae-box__header">
					<h3 class="pae-box__header--title">
						    <spring:message code="field.camposPropios.anadirCamposPropios"/>
					</h3>
				</div>				
					
				<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">				
						<div class="filaBtn">
							<div class="pae-box-buttons pd-right-1">
								<input type="submit" name="accion" value="Guardar Campo" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
								<input type="button" value="Volver" onclick="cancelar(${datosPropiosConfForm.id})" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1"/>							
							</div>
						</div>											
					</div>					
				</div>
				
				</div>		
			</form:form>
		</div>
	</body>
</html>


