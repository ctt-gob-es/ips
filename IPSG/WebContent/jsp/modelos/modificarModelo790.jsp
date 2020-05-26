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
	window.location.href = "../spring/buscarGestionModelos790"; 
}

function anadir(idModelo){
	if ($("#id").val() == "" || $("#numModelo").val() == "" ||  $("#descripcion").val() == "" ) {
		$("#error").html("<div id='rellenarCampos' class='pae-alertbox pae-alertbox--warning'><p class='pae-alertbox__text fw-bold'>Errores:</p><ul><li><p class='pae-alertbox__text'>No se puede añadir si existen campos vacíos</p></li></ul></div>");
	} else {
		window.location.href = "../spring/anadirCampoPropioModelo?id="+idModelo; 
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

<form:form commandName="modeloGestion790Form" action="/IPSG/spring/modificarModelo790" id="formPadre" method="post">

<div class="">
	<div id="error">
	<logic:present name="org.apache.spring.ERROR">
		<html:errors/>
	</logic:present>
	</div>
	
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
								<form:input type="text" path="numModelo" name="modeloBean" class="pae-form__input" maxlength="13"/>
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
								<form:input type="text" path="descripcion" name="modeloBean" class="pae-form__input" maxlength="100"/>
							</logic:present>
							<logic:notPresent name="modeloBean" scope="request">
								<form:input type="text" path="id" class="pae-form__input" readonly="true"/>
							</logic:notPresent>
						</div>			
					</div>
				</div>
				<!-- Fin Descripcion -->
				<div class="filaBtn">
					<div class="pae-box-buttons pd-right-1">
						<div class="pae-box__body"> 
							<input type="submit" name="accion" value="Modificar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>
							<input type="button" value="Volver" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1" onclick="volver()"/>
						</div>												
					</div>
				</div>
			</fieldset>		
		</div>		
	</div>
			<logic:present name="camposPropiosBean" scope="request">			
					<h1 class="pae-title"><spring:message code="field.camposPropios.modificarCampo" /></h1>  
					
				<logic:present name="modeloBean" scope="request">
					
						<div class="pae-box__body"> 						
								<div class="filaBtn">
									<div class="pae-box__body"> 
										<div class="pae-box-buttons pd-right-1">							
												<input type="button" name="accion" value="Añadir" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline" onclick="anadir(${modeloGestion790Form.id})"/>												
										</div>
									</div>
								</div>							
						</div>
									
				</logic:present>
			
				<bean:size id="numResultados" name="camposPropiosBean" scope="request"/>
				<logic:greaterThan name="numResultados" value="0">
					<table id="paetable" class="pae-table pae-table--margin">
						<caption class="hiddenAccesible">Tabla campos propios de modelos</caption>
						<thead class="pae-table__header">	
							<tr class="pae-table__row">
								<th data-col="col1" class="pae-table__cell-header">
									<span class="pae-table__txt-title">
										<spring:message code="field.camposPropios.id"/>
									</span>									
								</th>
								<th data-col="col2" class="pae-table__cell-header">
									<span class="pae-table__txt-title">
										<spring:message code="field.camposPropios.titulo"/>
									</span>									
								</th>
								<th data-col="col3" class="pae-table__cell-header w10">
									<span class="pae-table__txt-title">
										<spring:message code="field.camposPropios.descripcion"/>
									</span>									
								</th>	
							
							
							</tr>
						</thead>
						<tbody class="pae-table__body">
								<c:forEach var="registro" items="${camposPropiosBean}">
									<tr class="pae-table__row" name="row">
										<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body">
		               						<span class="pae-table__txt-base pae-table__span-head"><a href="${pageContext.request.contextPath}/spring/verModificarCampoPropio?id=${registro.id}" id="id" style="color: #c33400;">
											<bean:write name="registro" property="id" />
										</a></span>
		               					</td>
										<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body">
		               						<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="tituloCampo" /></span>
		               					</td>								
										<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body w10">
		               						<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="descripcion" /></span>
		               					</td>								
									</tr>
								</c:forEach>
					</table>
				</logic:greaterThan>								
			</logic:present>
			</form:form>
		</div>
	</body>
</html>


