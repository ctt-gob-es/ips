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

function volver(){
	window.location.href = "../spring/verModificarModelosGestion790"; 
}
function cancelar(idModelo){
	window.location.href = "../spring/verModificarModelosGestion790?id="+idModelo; 
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

		<form:form commandName="modeloGestion790Form" action="/IPSG/spring/anadirCampoPropioModelo" id="formPadre" method="post">	
<%-- 			<form:hidden path="modeloBean" value="modeloBean"/> --%>
			<div class="">				
				<logic:present name="org.apache.spring.ERROR">
					<div id="error">
						<html:errors/>
					</div>
					<div class="clear"></div>
				</logic:present>
				
				<h1 class="pae-title"><spring:message code="field.gestionModelos790.modificarModelo"/></h1>
				
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
								<!-- 	Titulo -->
								<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">	
									<div class="pae-form__cell">
										<span class="pae-form__label pae-form__span-label">	
											<spring:message code="field.gestionModelos790.tituloModelo"/><span class="obligatorio"> *</span>
										</span>						
										<logic:present name="modeloBean" scope="request">
											<form:input type="text" path="numModelo" name="modeloBean" class="pae-form__input" readonly="true"/>			
										</logic:present>
									</div>
								</div>
							</div>
								<!-- Fin Titulo -->
			
					<!-- Ini Descripcion -->
					<div class="pae-layout">
								<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
									<div class="pae-form__cell">
										<span class="pae-form__label pae-form__span-label">	
											<spring:message code="field.gestionModelos790.descripcion"/><span class="obligatorio"> *</span>
										</span>
										<logic:present name="modeloBean" scope="request">
											<form:input type="text" path="descripcion" name="modeloBean" class="pae-form__input" readonly="true"/>
										</logic:present>
										<logic:notPresent name="modeloBean" scope="request">
											<form:input type="text" path="id" class="pae-form__input" readonly="true"/>
										</logic:notPresent>
									</div>
								</div>
					</div>					
					<!-- Fin Descripcion -->
				<br>
				<div class="pae-box__header">
					<h3 class="pae-box__header--title">
						    <spring:message code="field.gestionModelos790.anadirCamposPropios"/>
					</h3>
				</div>				
					
				<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">				
						<!-- Ini Titulo Campo -->
						<br>
						<div class="pae-layout">
								<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
									<div class="pae-form__cell">
										<span class="pae-form__label pae-form__span-label">	
											<spring:message code="field.gestionModelos790.tituloCampo"/><span class="obligatorio"> *</span>
										</span>
										<input type="text" name="tituloCampo" class="pae-form__input"/>
									</div>
								</div>
						<!-- Fin Titulo Campo -->
						<!-- Ini Descripcion campo -->
								<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
									<div class="pae-form__cell">
										<span class="pae-form__label pae-form__span-label">	
											<spring:message code="field.gestionModelos790.descripcionCampo"/><span class="obligatorio"> *</span>
										</span>							
										<input type="text" name="descripcionCampo" class="pae-form__input"/>
									</div>
								</div>
						</div>
						<!-- Fin Descripcion Campo -->
						<!-- Ini Titulo Catalan -->
						<div class="pae-layout">
							<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
								<div class="pae-form__cell">
									<span class="pae-form__label pae-form__span-label">
										<spring:message code="field.gestionModelos790.tituloCampoCa"/><span class="obligatorio"> *</span>
									</span>
									<input type="text" name="tituloCampo_ca" class="pae-form__input"/>
								</div>
							</div>
						</div>
						<!-- Fin Titulo Catalan -->
						<!-- Ini Titulo Gallego -->
						<div class="pae-layout">
								<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
									<div class="pae-form__cell">
										<span class="pae-form__label pae-form__span-label">						
											<spring:message code="field.gestionModelos790.tituloCampoGl"/><span class="obligatorio"> *</span>
										</span>
										<input type="text" name="tituloCampo_gl" class="pae-form__input"/>
									</div>
								</div>		
						</div>
						<!-- Fin Titulo Gallego -->
						<!-- Ini Titulo Euskera -->
						<div class="pae-layout">
							<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
								<div class="pae-form__cell">
									<span class="pae-form__label pae-form__span-label">		
										<spring:message code="field.gestionModelos790.tituloCampoEu"/><span class="obligatorio"> *</span>
									</span>
									<input type="text" name="tituloCampo_eu" class="pae-form__input"/>
								</div>
							</div>
						</div>
						<!-- FinTitulo Euskera -->
						<!-- Ini Titulo Valenciano -->
						<div class="pae-layout">
							<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
								<div class="pae-form__cell">
									<span class="pae-form__label pae-form__span-label">
										<spring:message code="field.gestionModelos790.tituloCampoVl"/><span class="obligatorio"> *</span>
									</span>
									<input type="text" name="tituloCampo_vl" class="pae-form__input"/>
								</div>
							</div>
						</div>			
						<!-- Fin Titulo Valenciano -->
						<div class="filaBtn">
							<div class="pae-box-buttons pd-right-1">
								<input type="submit" name="accion" value="Guardar Campo" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>
								<logic:present name="modeloBean" scope="request">
									<input type="button" value="Volver" onclick="cancelar(${modeloBean.id})" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1"/>
								</logic:present>							
							</div>
						</div>											
					</div>					
				</div>
				
				</div>		
			</form:form>
		</div>
	</body>
</html>