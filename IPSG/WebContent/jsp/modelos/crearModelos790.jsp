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
	function cancelar(){
		window.location.href = "../spring/buscarGestionModelos790";
	}
</script>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
			
		<title></title>
	</head>
	<body style="margin-left:0.4em;">
	
	
		<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">
			<form:form commandName="modeloGestion790Form" action="/IPSG/spring/crearModelos790" id="formPadre" method="post">			
				<div class="">					
					
					
					
					<h1 class="pae-title"><spring:message code="field.gestionModelos790.tituloCrearModelo790"/></h1>	
					
					<logic:present name="org.apache.spring.ERROR">
						<div id="error">
							<html:errors/>
						</div>
						<div class="clear"></div>
					</logic:present>		
					
					<div data-function="accordion-box" class="pae-box">
						<div class="pae-box__body">   
							<fieldset>		
								<!-- Ini Titulo Modelo-->
								<div class="pae-layout">
									<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">	
										<div class="pae-form__cell">
											<span class="pae-form__label pae-form__span-label">						
												<spring:message code="field.gestionModelos790.tituloModelo"/><span class="obligatorio"> *</span>
											</span>
											<form:input type="text" path="numModelo" class="pae-form__input" maxlength="10"/>
										</div>
									</div>
								<!-- Fin Titulo Modelo-->
								<!-- Ini Descripción Modelo-->
									<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">	
										<div class="pae-form__cell">
											<span class="pae-form__label pae-form__span-label">	
												<spring:message code="field.gestionModelos790.descripcion"/><span class="obligatorio"> *</span>
											</span>
											<form:input type="text" path="descripcion" class="pae-form__input" maxlength="100"/>
										</div>
									</div>												
								</div>	
								<!-- Fin descripción Modelo -->
						<div class="filaBtn">
							<div class="pae-box-buttons pd-right-1">
								<input type="submit" name="accion" value="Guardar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>
								<input type="button" name="submit" value="Cancelar" onclick="cancelar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1"/>								
							</div>
						</div>
					</fieldset>
				</div>		
			</form:form>
		</div>
	</body>
</html>


