<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="es.map.ipsg.bean.*" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/Datepicker_calendario.js"></script>
<script type="text/javascript">

function volver(){
	window.location.href = "../spring/buscarCentroGestor"; 
}
</script>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>

<body style="margin-left:0.4em;" >
<div class="capaAll">


  <h1 class="pae-title">  <spring:message code="field.centroGestor.tituloMantenimientoCentroGestor"/> </h1>
    

<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->
	<div data-function="accordion-box" class="pae-box">
<form:form commandName="centroGestorForm" action="/IPSG/spring/modificarCentroGestor" id="formPadre" method="post">
<input name="id" type="hidden" value="${centroGestor.id}"/>
<input name="ejercicio" type="hidden" value="${centroGestor.ejercicio}"/>
	<logic:present name="org.apache.spring.ERROR">
		<div id="error">
			<html:errors/>
		</div>
		<div class="clear"></div>
	</logic:present>	

	<logic:present name="centroGestor" scope="request">
		<form:hidden path="idPlantilla" name="centroGestor" />
	</logic:present>
	<logic:notPresent name="centroGestor" scope="request">
		<form:hidden path="idPlantilla"/>
	</logic:notPresent>
	<!-- Ini ID -->
<div class="pae-box__body">
	<fieldset>
		<div class="pae-layout">
			<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">	
						<spring:message code="field.centroGestor.idMay"/>
					</span>
					<logic:present name="centroGestor" scope="request">
						<form:input type="text" path="id" name="centroGestor" class="pae-form__input" readonly="true" disabled="true"/>
					</logic:present>
					<logic:notPresent name="centroGestor" scope="request">
					<form:input type="text" path="id" disabled="true"
					 class="pae-form__input" readonly="true"/>
					</logic:notPresent>
				</div>
		
			</div>
		<!-- Fin ID -->
		<!-- Ini Ministerio -->
		<div class="pae-layout__item pae-u-5/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
			<div class="pae-form__cell">
				<logic:present name="ministerio" scope="request">
				<bean:size name="ministerio" id="numMinisterio" scope="request"/>
				<logic:greaterThan name="numMinisterio" value="0">
				<span class="pae-form__label pae-form__span-label">	
					<spring:message code="field.centroGestor.ministerioMay"/><span class="obligatorio"> *</span>
				</span>
				
				<form:select path="idMinisterio" class="pae-form__input" id="idMinisterio" >
					<form:option value=""></form:option>
					<form:options items="${ministerio}"  itemLabel="descripcion" itemValue="id"/>					
					</form:select>
			
				</logic:greaterThan>
				</logic:present>
			</div>
			</div>
			<!-- Fin Ministerio -->
			<!-- Ini Siglas -->
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">	
						<spring:message code="field.centroGestor.siglasMay"/><span class="obligatorio"> *</span>
					</span>
					<logic:present name="centroGestor" scope="request">
						<form:input type="text" path="siglas" name="centroGestor" id="siglas" class="pae-form__input" maxlength="20"/>
					</logic:present>
					<logic:notPresent name="centroGestor" scope="request">
						<form:input type="text" path="siglas" id="siglas" class="pae-form__input" maxlength="20"/>
					</logic:notPresent>
				</div>
			</div>
			<!-- fin Siglas -->
	</div>	
	<div class="pae-layout">	
	
	<!-- Ini Ejercicio-->
	<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
		<div class="pae-form__cell">
		<span class="pae-form__label pae-form__span-label">	
			<spring:message code="field.ejercicioMay"/><span class="obligatorio"> *</span>
		</span>
			<logic:present name="centroGestor" scope="request">
				<form:input type="text" path="ejercicio" name="centroGestor" id="ejercicio" class="pae-form__input" maxlength="4" readonly="true" disabled="true" />
			</logic:present>
			<logic:notPresent name="centroGestor" scope="request">
				<form:input type="text" path="ejercicio" id="ejercicio" class="pae-form__input" maxlength="4" readonly="true" />
			</logic:notPresent>
		</div>
	</div>
	<!-- Fin Ejercicio-->			
	<!-- Ini Descripción -->
	<div class="pae-layout__item pae-u-5/12 pae-u-2/12-lap pae-u-1/1-palm">
		<div class="pae-form__cell">
		<span class="pae-form__label pae-form__span-label">	
			<spring:message code="field.descripcionMay"/><span class="obligatorio"> *</span>
		</span>
		<logic:present name="centroGestor" scope="request">
			<form:input type="text" path="descripcion" name="centroGestor" id="descripcion" class="pae-form__input" maxlength="100"/>
		</logic:present>
			<logic:notPresent name="centroGestor" scope="request">
				<form:input type="text" path="descripcion" id="descripcion" class="pae-form__input" maxlength="100"/>
			</logic:notPresent>
		</div>
				
	</div>
	<!-- Fin Descripción-->	

	<!-- Ini Código -->
	<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
		<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">	
				<spring:message code="field.codigoMay"/><span class="obligatorio"> *</span>
			</span>
			<logic:present name="centroGestor" scope="request">
				<form:input type="text" path="codigo" name="centroGestor" id="codigo" class="pae-form__input"  maxlength="10"/>
			</logic:present>
			<logic:notPresent name="centroGestor" scope="request">
				<form:input type="text" path="codigo" id="codigo" class="pae-form__input" maxlength="10"/>
			</logic:notPresent>
		</div>		
	</div>	
	<!-- Fin Código -->

<!-- Modelo Convocatoria -->
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
		<div class="pae-form__cell">
				
		<logic:present name="modelos" scope="request">
			<bean:size name="modelos" id="numModelo" scope="request"/>
			<span class="pae-form__label pae-form__span-label pae-ancho3">
				<spring:message code="field.modeloMayConv"/><span class="obligatorio"> *</span>
			</span>
				<bean:size id="numModelo" name="modelos" scope="request"/>
				<logic:greaterThan name="numModelo" value="0">
					<form:select path="idModelo" class="pae-form__input" id="idModelo" >
						<form:option value=""></form:option>
						<form:options items="${modelos}"  itemLabel="numModelo" itemValue="id"/>					
					</form:select>
					<div class="clear"></div>
				</logic:greaterThan>
			</div>
		</logic:present>	
	</div>
	<!-- Fin Modelo Convocatoria -->
	</div>
	<div class="pae-layout">


	<!-- Ini 	-->
	<!-- Ini Centro Gestor Sustituido-->
	<div class="pae-layout__item pae-u-7/12 pae-u-2/12-lap pae-u-1/1-palm">
		<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.centroGestor.centroGestorSustMay"/>		
			</span>
		
		<logic:present name="centroGestor" scope="request">
			<form:select path="idCentroGestorSust" name="centroGestor" class="pae-form__input"  id="idCentroGestorSust" >
				<option value="">         </option>
				<logic:present name="centroGestorLista" scope="request">
					<bean:size id="numCentroGestor" name="centroGestorLista" scope="request"/>
						<logic:greaterThan name="numCentroGestor" value="0">
							<form:options items="${centroGestorLista}"  itemLabel="descripcion" itemValue="id"/>
						</logic:greaterThan>
				</logic:present>
			</form:select>
		</logic:present>
		<logic:notPresent name="centroGestor" scope="request">
			<form:select path="idCentroGestorSust" class="pae-form__input"  id="idCentroGestorSust" >
				<form:option value=""></form:option>
				<logic:present name="centroGestorLista" scope="request">
					<bean:size id="numCentroGestor" name="centroGestorLista" scope="request"/>
						<logic:greaterThan name="numCentroGestor" value="0">
							<form:options items="${centroGestorLista}"  itemLabel="descripcion" itemValue="id"/>
						</logic:greaterThan>
				</logic:present>
			</form:select>
		</logic:notPresent>
		</div>
	</div>	
	<!-- Fin Centro Gestor Sustituido -->
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-check">
			<div class="pae-form__cell">	
			<logic:present name="centroGestor" scope="request">		
				<c:choose>
				    <c:when test="${centroGestorForm.visibilidad == true }">
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
			</logic:present>		
			</div>
		</div>	

</div>
	<div class="pae-layout">
			<!-- Ini Fecha Sustitución -->
<div class="pae-layout__item pae-u-3/12 pae-u-4/12-lap pae-u-1/1-palm">
	<div class="pae-form__cell">
		<span class="pae-form__label pae-form__span-label pae-ancho3">
			<spring:message code="field.fechaSustitucionMay"/>
		</span>
				<form:input type="text" path="fechaSustitucion" id="fechaSustitucion"  data-function="function-datepicker" 
					placeholder="dd/mm/aaaa" class="pae-form__input" style="fechaDesde" maxlength="10"/>		
			
<!-- 			<script type="text/javascript"> -->
<%-- 					fncCalendario('fechaSustitucion', '<%=request.getContextPath()%>'); --%>
<!-- 			</script> -->
	</div>	
</div>
	
	<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
		<div class="pae-form__cell">	
			<span class="pae-form__label pae-form__span-label pae-ancho3">
				<spring:message code="field.fechaInicioInhabil" />
			</span>
			<form:input type="text" path="fechaInicioInhabil" id="fechaInicioInhabil"  data-function="function-datepicker" 
					placeholder="dd/mm/aaaa" class="pae-form__input"  maxlength="10"/>
<%-- 			<form:input type="text" path="fechaInicioInhabil" id="fechaInicioInhabil" maxlength="10"  class="pae-form__input" style="width: 50%;"/> --%>
			<script type="text/javascript">
				fncCalendario('fechaInicioInhabil', '<%=request.getContextPath()%>');
			</script>
		</div>
	</div>
	<!-- Fin Fecha Inicio Inahbil-->
	<div class="pae-layout__item pae-u-1/12 pae-u-4/12-lap pae-u-1/1-palm"></div>
	<!-- Ini Fecha Fin Inahbil-->
	<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
		<div class="pae-form__cell">
		<span class="pae-form__label pae-form__span-label pae-ancho3">
			<spring:message code="field.fechaFinInhabil" />
		</span>
			<form:input type="text" path="fechaFinInhabil" id="fechaFinInhabil"  data-function="function-datepicker" 
					placeholder="dd/mm/aaaa" class="pae-form__input"  maxlength="10"/>
<%-- 			<form:input type="text" path="fechaFinInhabil" id="fechaFinInhabil" maxlength="10" class="pae-form__input" /> --%>
<!-- 			<script type="text/javascript"> -->
<%-- 				fncCalendario('fechaFinInhabil', '<%=request.getContextPath()%>'); --%>
<!-- 			</script> -->
		</div>
	</div>
	<!-- Fin Fecha Fin Inahbil-->
	</div>

 	<div class="filaBtn">
		<div class="pae-box-buttons">
				<input type="submit" value="Modificar" name="accion" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>
				<input type="button" value="Volver" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline mg-left-1" onclick="volver()"/>
			</div>
		</div>

</fieldset>	
</div>		

</form:form>
</div>	
</div>
</body>
</html>


