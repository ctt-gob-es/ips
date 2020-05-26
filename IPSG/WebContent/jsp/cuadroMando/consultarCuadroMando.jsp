<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.map.ipsg.bean.*" %>
<%@ page import="es.map.ipsg.util.Constantes" %>

<%
String sVieneMenu = (String)request.getAttribute("sVieneMenu");
String irAnclaje = (String)request.getAttribute("irAnclaje");
String gestor = Constantes.PERFIL_GESTOR_INT+"";
%>
<%@page import="es.map.ipsg.util.Constantes"%>
<script type="text/javascript">

function openWindowCentroGestor() {
	var param = "idCentroGestor";
	var param2 = "dsCentroGestor";
		ventanaPopup = window.open("../spring/listarCentroGestor?parametro="+param+"&parametro2="+param2, 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
	return false;
}
function comprobarBusqueda(){
	document.getElementById("cambios").value="Incorrecto";
}
function comprobarCheckMarcado()
{
	var bSeleccionado = false;
	var formulario = document.forms[1];
	for (i=0; i < formulario.length; i++)
    {
		if (formulario.elements[i].type=='checkbox')
		{	
			if (formulario.elements[i].checked)
			{
				bSeleccionado = true;
			}
		}
	}
	return bSeleccionado;
}
function busqueda(){
	var bCorrecto = true;
	if(comprobarCheckMarcado())
	{
		document.getElementById("accion").value="BUSCAR";
		document.getElementById("submit").value="Buscar";
		document.forms[1].action = "../spring/buscarCuadroMando";
	}else
	{
		alert('<spring:message code="field.cuadroMando.elegirCamposBusqueda"/>');
		bCorrecto = false;
	}
	return bCorrecto;
}
function deshabilitar(irAnclaje)
{
	
	if(irAnclaje=='S'){
		irA("ancla");
	}

}


function exportarExcel(opcion)
{
	if(opcion == 'Exportar')
	{
		document.getElementById("accion").value="EXPORTAR";
		document.getElementById("submit").value="Exportar";
		document.forms[1].action = "../spring/exportarExcelCuadroMando";
	}
	
}

function irA(elemID) {
	var offsetTrail = document.getElementById(elemID);
	var offsetLeft = 0;
	var offsetTop = 0;
	while (offsetTrail) {
	offsetLeft += offsetTrail.offsetLeft;
	offsetTop += offsetTrail.offsetTop;
	offsetTrail = offsetTrail.offsetParent;
	}
	if (navigator.userAgent.indexOf("Mac") != -1 && typeof document.body.leftMargin != "undefined" && navigator.appName=="Microsoft Internet Explorer" ) {
	offsetLeft += parseInt(document.body.leftMargin);
	offsetTop += parseInt(document.body.topMargin);
	}
	window.scrollTo(offsetLeft,offsetTop)
	}
	
function limpiarCentroGestor(){
var desCentro = document.getElementById("dsCentroGestor").value;
	if(desCentro != ""){
		document.getElementById("idCentroGestor").value = "";
		document.getElementById("dsCentroGestor").value = "";
	}
}

</script>
<style>
#espacio {
	border-bottom-color:  white;
}
.otro {
	padding-bottom: 15px;
}
</style>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> 
<title></title>
</head>

<body style="margin-left:0.4em;" onload="deshabilitar('${irAnclaje}');">
<!--<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">-->

<form:form modelAttribute="consultarCuadroMandoForm" action="/IPSG/spring/verConsultarCuadroMando" id="formPadre" method="post">
	<form:hidden path="submit" id="submit"/>
	<form:hidden path="menu" id="menu" value="N"/>
	<form:hidden path="accion" id="accion"/>
	<form:hidden path="cambios" id="cambios"/>
	<form:hidden path="perfilUsuario" id="perfilUsuario"/>
	

<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
</logic:present>
	
	
	
<%-- MAQUETACION INI --%>	
<div class="">
	<h1 class="pae-title"><spring:message code="field.cuadroMando.consultarCuadro"/> </h1>
	
	<div data-function="accordion-box" class="pae-box">
		<div class="pae-box__header">
			<h3 class="pae-box__header--title">
				<spring:message code="field.cuadroMando.filtrar"/>
			</h3>
		</div>
		<div class="pae-box__body">
			<fieldset>
				<!-- Ini Centro Gestor -->
				<div class="pae-layout">
				<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">	
					<div class="pae-form__cell">
				  		<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.centroMay" />
						</span>		
				<logic:present name="rol" scope="request">
					<logic:equal name="rol" value="<%=gestor%>">			
							<!--INI-TRA042-->
							<logic:present name="listaCentrosGestores" scope="request">
								<div class="pae-form__cell">
									<form:select path="idCentroGestor" class="pae-form__input">
										<option value=""></option>
										<form:options items="${listaCentrosGestores}" itemValue="id" itemLabel="descripcion" />
									</form:select>
								</div>
							</logic:present>
							<!--FIN-TRA042-->
						</div>
				</div>											
				</logic:equal>
				<logic:notEqual name="rol" value="<%=gestor%>">	
							
				<form:input path="idCentroGestor" class="pae-form__input" id="idCentroGestor" readonly="true" onchange="comprobarBusqueda();comprobar()" onclick="limpiarCentroGestor();"></form:input> 
				<form:input path="dsCentroGestor" id="dsCentroGestor" readonly="true" class="input_texto_border0"></form:input>	
				</div>
				</div>
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm search_btn pd-left-2">
						<input type="button"
						class="pae-form__btn-search" alt="Buscar Centro Gestor"
						onclick="return openWindowCentroGestor()" style="margin-left:0em;">
					</div> 
					</logic:notEqual>
				</logic:present>	
				</div>			
 				<!-- Fin Centro Gestor -->
				<!-- Ini Estado-->
				<div class="pae-layout">
				<logic:present name="estadosConvocatoria" scope="request">
					<bean:size id="numEstadosConvocatoria" name="estadosConvocatoria" scope="request"/>
					<logic:greaterThan name="numEstadosConvocatoria" value="0">				
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">	
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.cuadroMando.estado.mayus"/>
							</span>
							<form:select path="idEstado" id="idEstado" class="pae-form__input">
	  							<form:option value=""></form:option>	
								<form:options items="${estadosConvocatoria}" itemLabel="descripcion" itemValue="id"/>	
							</form:select>
						</div>
					</div>
				</logic:greaterThan>
				</logic:present>
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-2/1-palm">	
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.cuadroMando.fechaDesde"/>
						</span> 
						<form:input type="text" path="fechaDesde" id="fechaDesde" data-function="function-datepicker"  placeholder="dd/mm/aaaa" class="pae-form__input" maxlength="10"/>
						<script type="text/javascript">fncCalendario('fechaDesde', '<%=request.getContextPath()%>');</script>
					</div>
					<!-- Fin Fecha Desde -->
					<!-- Ini Fecha Hasta -->
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-2/1-palm">	
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.cuadroMando.fechaHasta"/>
						</span>
							<form:input type="text" path="fechaHasta"  id="fechaHasta"  data-function="function-datepicker"  placeholder="dd/mm/aaaa" class="pae-form__input" maxlength="10"/>
							<script type="text/javascript">fncCalendario('fechaHasta', '<%=request.getContextPath()%>');</script>
					</div>	
				</div>	
				<!-- Fin Estado-->	
				<!-- Fin Fecha Desde -->
														 				
			</fieldset>
		</div>
	</div>
<%-- MAQUETACION FIN --%>	


<%-- MAQUETACION FILTROS INI --%>	
	<div data-function="accordion-box" class="pae-box">
		<div class="pae-box__header">
			<h3 class="pae-box__header--title">
				<spring:message code="field.cuadroMando.resultado"/>
			</h3>
		</div>
		<div class="pae-box__body">
			<fieldset>
				<%-- MAQUETACION CONVOCATORIA INI --%>	
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-6/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">	
						<div class="pae-form__cell">
							<h3 class="pae-box__header--title">
								<spring:message code="field.cuadroMando.datosConvocatoria"/>
							</h3>						
						<div class="pae-layout__item pae-u-5/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
	 						<ul class="pae-form__list pae-mt">                     
	                          <li class="pae-form__list-item">
	                          	<c:choose>
								    <c:when test="${consultarCuadroMandoForm.ckCentroGestor}">
								        <input type="checkbox"  id="ckCentroGestor" name="ckCentroGestor" data-function="checkbox-custom-input" class="pae-form__custom-check" checked>
								    </c:when>    
								    <c:otherwise>
										<input type="checkbox"  id="ckCentroGestor" name="ckCentroGestor" data-function="checkbox-custom-input" class="pae-form__custom-check">
								    </c:otherwise>
								</c:choose>
	                            <label for="ckCentroGestor" id="Centro Gestor" data-function="checkbox-custom" title="Al seleccionar, se mostrará información adicional sobre 
	                            Centro Gestor" class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
	                            	<spring:message code="field.cuadroMando.centroGestor.mayus"/>
	                            </label>
	                          </li> 
	                          <li class="pae-form__list-item">
	                          	<c:choose>
								    <c:when test="${consultarCuadroMandoForm.ckEjercicio}">
								        <input type="checkbox" name="ckEjercicio" id="ckEjercicio" data-function="checkbox-custom-input" class="pae-form__custom-check" checked>
								    </c:when>    
								    <c:otherwise>
										<input type="checkbox" name="ckEjercicio" id="ckEjercicio" data-function="checkbox-custom-input" class="pae-form__custom-check">
								    </c:otherwise>
								</c:choose>	                          
	                            <label for="ckEjercicio" id="EJERCICIO" data-function="checkbox-custom" title="Al seleccionar, se mostrará información adicional sobre 
	                            EJERCICIO " class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
	                            	<spring:message code="field.cuadroMando.ejercicio.mayus"/> 
	                            </label>
	                          </li>   
	                          <li class="pae-form__list-item">
	                          	<c:choose>
								    <c:when test="${consultarCuadroMandoForm.ckFormaAcceso}">
								         <input type="checkbox" name="ckFormaAcceso" id="ckFormaAcceso" data-function="checkbox-custom-input" class="pae-form__custom-check" checked>
								    </c:when>    
								    <c:otherwise>
										 <input type="checkbox" name="ckFormaAcceso" id="ckFormaAcceso" data-function="checkbox-custom-input" class="pae-form__custom-check">
								    </c:otherwise>
								</c:choose>	      	                          
	                            <label for="ckFormaAcceso" id="F. NUMEROSA ESPECIAL" data-function="checkbox-custom" title="Al seleccionar, se mostrará información adicional sobre 
	                            	F.ACCESO " class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
	                            	<spring:message code="field.cuadroMando.formaAcceso.mayus"/>
	                            </label>
	                          </li>
	                          <li class="pae-form__list-item">
	                          	<c:choose>
								    <c:when test="${consultarCuadroMandoForm.ckFechaBOE}">
								         <input type="checkbox" name="ckFechaBOE" id="ckFechaBOE" data-function="checkbox-custom-input" class="pae-form__custom-check" checked>
								    </c:when>    
								    <c:otherwise>
										 <input type="checkbox" name="ckFechaBOE" id="ckFechaBOE" data-function="checkbox-custom-input" class="pae-form__custom-check">
								    </c:otherwise>
								</c:choose>	 	                                    
	                            <label for="ckFechaBOE" id="F. NUMEROSA GENERAL" data-function="checkbox-custom" title="Al seleccionar, se mostrará información adicional sobre 
	                           		F.BOE" class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
	                            	<spring:message code="field.cuadroMando.fechaBOE.mayus"/>
	                            </label>
	                          </li>
	                          <li class="pae-form__list-item">
	                          	<c:choose>
								    <c:when test="${consultarCuadroMandoForm.ckNumPlazasTotales}">
								         <input type="checkbox" name="ckNumPlazasTotales" id="ckNumPlazasTotales" data-function="checkbox-custom-input" class="pae-form__custom-check" checked>
								    </c:when>    
								    <c:otherwise>
										 <input type="checkbox" name="ckNumPlazasTotales" id="ckNumPlazasTotales" data-function="checkbox-custom-input" class="pae-form__custom-check">
								    </c:otherwise>
								</c:choose>	 	                          
	                            <label for="ckNumPlazasTotales" id="F. NUMEROSA GENERAL" data-function="checkbox-custom" title="Al seleccionar, se mostrará información adicional sobre 
	                           		PLAZAS TOTALES" class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
	                            	<spring:message code="field.cuadroMando.numPlazasTotalesTabla"/>
	                            </label>
	                          </li>
	                         </ul>
                          </div>
                          <div class="pae-layout__item pae-u-6/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">	
	                          <ul class="pae-form__list pae-mt">   
		                          <li class="pae-form__list-item">
		                          	<c:choose>
									    <c:when test="${consultarCuadroMandoForm.ckCuerpoEscala}">
									         <input type="checkbox" name="ckCuerpoEscala" id="ckCuerpoEscala" data-function="checkbox-custom-input" class="pae-form__custom-check" checked>
									    </c:when>    
									    <c:otherwise>
											 <input type="checkbox" name="ckCuerpoEscala" id="ckCuerpoEscala" data-function="checkbox-custom-input" class="pae-form__custom-check">
									    </c:otherwise>
									</c:choose>	 		                                        
		                            <label for="ckCuerpoEscala" id="F. NUMEROSA GENERAL" data-function="checkbox-custom" title="Al seleccionar, se mostrará información adicional sobre 
		                           		CUERPO ESCALA" class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
		                            	<spring:message code="field.cuadroMando.cuerpoEscala.mayus"/>
		                            </label>
		                          </li>  
		                          <li class="pae-form__list-item">
		                          	<c:choose>
									    <c:when test="${consultarCuadroMandoForm.ckEstado}">
									         <input type="checkbox" name="ckEstado" id="ckEstado" data-function="checkbox-custom-input" class="pae-form__custom-check" checked>
									    </c:when>    
									    <c:otherwise>
											<input type="checkbox" name="ckEstado" id="ckEstado" data-function="checkbox-custom-input" class="pae-form__custom-check">
									    </c:otherwise>
									</c:choose>	 		                          
		                            <label for="ckEstado" id="ESTADO" data-function="checkbox-custom" title="Al seleccionar, se mostrará información adicional sobre 
		                           		ESTADO" class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
		                            	<spring:message code="field.cuadroMando.estadoTabla"/>
		                            </label>
		                          </li>       
		                          <li class="pae-form__list-item">
		                          	<c:choose>
									    <c:when test="${consultarCuadroMandoForm.ckNumPlazasDiscap}">
									         <input type="checkbox" name="ckNumPlazasDiscap" id="ckNumPlazasDiscap" data-function="checkbox-custom-input" class="pae-form__custom-check" checked>
									    </c:when>    
									    <c:otherwise>
											 <input type="checkbox" name="ckNumPlazasDiscap" id="ckNumPlazasDiscap" data-function="checkbox-custom-input" class="pae-form__custom-check">
									    </c:otherwise>
									</c:choose>	 		                          
		                            <label for="ckNumPlazasDiscap" id="PLAZAS DISCAPACIDAD" data-function="checkbox-custom" title="Al seleccionar, se mostrará información adicional sobre 
		                           		PLAZAS DISCAPACIDAD" class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
		                            	<spring:message code="field.cuadroMando.numPlazasDiscapTabla"/>	
		                            </label>
		                          </li>                                                                                                          
		                        </ul>
	                       </div>
						</div>
					</div>
					<%-- MAQUETACION CONVOCATORIA FIN --%>	
					<%-- MAQUETACION INSCRIPCION INI --%>	
					<div class="pae-layout__item pae-u-6/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">	
						<div class="pae-form__cell">
							<h3 class="pae-box__header--title">
								<spring:message code="field.cuadroMando.datosInscripciones"/>
							</h3>						
						<div class="pae-layout__item pae-u-5/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
	 						<ul class="pae-form__list pae-mt">                     
	                          <li class="pae-form__list-item">
	                          	<c:choose>
								    <c:when test="${consultarCuadroMandoForm.ckNumSolTelematicas}">
								        <input type="checkbox"  id="ckNumSolTelematicas" name="ckNumSolTelematicas" data-function="checkbox-custom-input" class="pae-form__custom-check" checked>
								    </c:when>    
								    <c:otherwise>
										<input type="checkbox"  id="ckNumSolTelematicas" name="ckNumSolTelematicas" data-function="checkbox-custom-input" class="pae-form__custom-check">
								    </c:otherwise>
								</c:choose>
	                            <label for="ckNumSolTelematicas" id="numSolTelematicas" data-function="checkbox-custom" title="Al seleccionar, se mostrará información adicional sobre 
	                            Nº Solicitudes telematicas" class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
	                            	<spring:message code="field.cuadroMando.numSolTelematicas.mayus"/>
	                            </label>
	                          </li> 
	                          <li class="pae-form__list-item">
	                          	<c:choose>
								    <c:when test="${consultarCuadroMandoForm.ckNumSolPresenciales}">
								        <input type="checkbox" name="ckNumSolPresenciales" id="ckNumSolPresenciales" data-function="checkbox-custom-input" class="pae-form__custom-check" checked>
								    </c:when>    
								    <c:otherwise>
										<input type="checkbox" name="ckNumSolPresenciales" id="ckNumSolPresenciales" data-function="checkbox-custom-input" class="pae-form__custom-check">
								    </c:otherwise>
								</c:choose>	                          
	                            <label for="ckNumSolPresenciales" id="numSolPresenciales" data-function="checkbox-custom" title="Al seleccionar, se mostrará información adicional sobre 
	                            Nº Solicitudes Presenciales " class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
	                            	<spring:message code="field.cuadroMando.numSolPresenciales.mayus"/>
	                            </label>
	                          </li>   
	                          <sec:authorize ifNotGranted="ROLE_ADMIN">
		                          <li class="pae-form__list-item">
		                          	<c:choose>
									    <c:when test="${consultarCuadroMandoForm.ckNumDescargasManuales}">
									         <input type="checkbox" name="ckNumDescargasManuales" id="ckNumDescargasManuales" data-function="checkbox-custom-input" class="pae-form__custom-check" checked>
									    </c:when>    
									    <c:otherwise>
											 <input type="checkbox" name="ckNumDescargasManuales" id="ckNumDescargasManuales" data-function="checkbox-custom-input" class="pae-form__custom-check">
									    </c:otherwise>
									</c:choose>	      	                          
		                            <label for="ckNumDescargasManuales" id="numDescargasManuales" data-function="checkbox-custom" title="Al seleccionar, se mostrará información adicional sobre 
		                            	Nº Inscripcion Sin Pago" class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
		                            	<spring:message code="field.cuadroMando.numDescargasManuales.mayus"/>
		                            </label>
		                          </li>
	                          </sec:authorize>
	                          <li class="pae-form__list-item">
	                          	<c:choose>
								    <c:when test="${consultarCuadroMandoForm.ckNumInscripcionSinPago}">
								         <input type="checkbox" name="ckNumInscripcionSinPago" id="ckNumInscripcionSinPago" data-function="checkbox-custom-input" class="pae-form__custom-check" checked>
								    </c:when>    
								    <c:otherwise>
										 <input type="checkbox" name="ckNumInscripcionSinPago" id="ckNumInscripcionSinPago" data-function="checkbox-custom-input" class="pae-form__custom-check">
								    </c:otherwise>
								</c:choose>	 	                                    
	                            <label for="ckNumInscripcionSinPago" id="numInscripcionSinPago" data-function="checkbox-custom" title="Al seleccionar, se mostrará información adicional sobre 
	                           		Nº Solicitud Sin Inscripcion Pago" class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
	                            	<spring:message code="field.cuadroMando.numInscripcionSinPago.mayus"/>
	                            </label>
	                          </li>	
	                         </ul>
                          </div>
                          <div class="pae-layout__item pae-u-5/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">	
	                          <ul class="pae-form__list pae-mt">  
		                          <li class="pae-form__list-item">
		                          	<c:choose>
									    <c:when test="${consultarCuadroMandoForm.ckNumSolSinIntentoPago}">
									         <input type="checkbox" name="ckNumSolSinIntentoPago" id="ckNumSolSinIntentoPago" data-function="checkbox-custom-input" class="pae-form__custom-check" checked>
									    </c:when>    
									    <c:otherwise>
											 <input type="checkbox" name="ckNumSolSinIntentoPago" id="ckNumSolSinIntentoPago" data-function="checkbox-custom-input" class="pae-form__custom-check">
									    </c:otherwise>
									</c:choose>	 	                                    
		                            <label for="ckNumSolSinIntentoPago" id="numSolSinIntentoPago" data-function="checkbox-custom" title="Al seleccionar, se mostrará información adicional sobre 
		                           		Nº Solicitud Sin Intento Pago" class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
		                            	<spring:message code="field.cuadroMando.numSolSinIntentoPago.mayus"/>
		                            </label>
		                          </li>	                           
		                          <li class="pae-form__list-item">
		                          	<c:choose>
									    <c:when test="${consultarCuadroMandoForm.ckNumPagosSinRegistro}">
									         <input type="checkbox" name="ckNumPagosSinRegistro" id="ckNumPagosSinRegistro" data-function="checkbox-custom-input" class="pae-form__custom-check">
									    </c:when>    
									    <c:otherwise>
											 <input type="checkbox" name="ckNumPagosSinRegistro" id="ckNumPagosSinRegistro" data-function="checkbox-custom-input" class="pae-form__custom-check">
									    </c:otherwise>
									</c:choose>	 		                                        
		                            <label for="ckNumPagosSinRegistro" id="numPagosSinRegistro" data-function="checkbox-custom" title="Al seleccionar, se mostrará información adicional sobre 
		                           		Nº Pagos Sin Registro" class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
		                            	<spring:message code="field.cuadroMando.numPagosSinRegistro.mayus"/>
		                            </label>
		                          </li>  
		                          <li class="pae-form__list-item">
		                          	<c:choose>
									    <c:when test="${consultarCuadroMandoForm.ckNumPagosSinIntentoRegistro}">
									         <input type="checkbox" name="ckNumPagosSinIntentoRegistro" id="ckNumPagosSinIntentoRegistro" data-function="checkbox-custom-input" class="pae-form__custom-check" checked>
									    </c:when>    
									    <c:otherwise>
											<input type="checkbox" name="ckNumPagosSinIntentoRegistro" id="ckNumPagosSinIntentoRegistro" data-function="checkbox-custom-input" class="pae-form__custom-check">
									    </c:otherwise>
									</c:choose>	 		                          
		                            <label for="ckNumPagosSinIntentoRegistro" id="numSolSinIntentoRegistro" data-function="checkbox-custom" title="Al seleccionar, se mostrará información adicional sobre 
		                           Nº Solicitudes Sin Intento Registro" class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
		                            	 <spring:message code="field.cuadroMando.numSolSinIntentoRegistro.mayus"/>
		                            </label>
		                          </li>                                                                                                      
		                        </ul>
	                       </div>							
                      </div>  					
				    </div>
					<%-- MAQUETACION INSCRIPCION FIN --%>	
					<sec:authorize ifAnyGranted="ROLE_ADMIN">
					<%-- MAQUETACION INCIDENCIAS INI --%>	
					<div class="pae-layout__item pae-u-6/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
						<div class="pae-form__cell">
							<h3 class="pae-box__header--title">
								<spring:message code="field.cuadroMando.datosIncidencias"/>
							</h3>						
						<div class="pae-layout__item pae-u-12/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
	 						<ul class="pae-form__list pae-mt">                     
	                          <li class="pae-form__list-item">
	                          	<c:choose>
								    <c:when test="${consultarCuadroMandoForm.ckNumSolIncPagoResuelta}">
								        <input type="checkbox"  id="ckNumSolIncPagoResuelta" name="ckNumSolIncPagoResuelta" data-function="checkbox-custom-input" class="pae-form__custom-check" checked>
								    </c:when>    
								    <c:otherwise>
										<input type="checkbox"  id="ckNumSolIncPagoResuelta" name="ckNumSolIncPagoResuelta" data-function="checkbox-custom-input" class="pae-form__custom-check">
								    </c:otherwise>
								</c:choose>
	                            <label for="ckNumSolIncPagoResuelta" id="numSolIncPagoResuelta" data-function="checkbox-custom" title="Al seleccionar, se mostrará información adicional sobre 
	                            Nº Solicitudes Incidencias Pago Resuelta" class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
	                            	<spring:message code="field.cuadroMando.numSolIncPagoResuelta.mayus"/>
	                            </label>
	                          </li> 
	                          <li class="pae-form__list-item">
	                          	<c:choose>
								    <c:when test="${consultarCuadroMandoForm.ckNumSolIncPagoSinResolver}">
								        <input type="checkbox" name="ckNumSolIncPagoSinResolver" id="ckNumSolIncPagoSinResolver" data-function="checkbox-custom-input" class="pae-form__custom-check" checked>
								    </c:when>    
								    <c:otherwise>
										<input type="checkbox" name="ckNumSolIncPagoSinResolver" id="ckNumSolIncPagoSinResolver" data-function="checkbox-custom-input" class="pae-form__custom-check">
								    </c:otherwise>
								</c:choose>	                          
	                            <label for="ckNumSolIncPagoSinResolver" id="numSolIncPagoSinResolver" data-function="checkbox-custom" title="Al seleccionar, se mostrará información adicional sobre 
	                            Nº Solicitudes Incidencias Pago Sin Resolver" class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
	                            	<spring:message code="field.cuadroMando.numSolIncPagoSinResolver.mayus"/>
	                            </label>
	                          </li>   
	                          <li class="pae-form__list-item">
	                          	<c:choose>
								    <c:when test="${consultarCuadroMandoForm.ckNumSolIncRegistroResuelta}">
								         <input type="checkbox" name="ckNumSolIncRegistroResuelta" id="ckNumSolIncRegistroResuelta" data-function="checkbox-custom-input" class="pae-form__custom-check" checked>
								    </c:when>    
								    <c:otherwise>
										 <input type="checkbox" name="ckNumSolIncRegistroResuelta" id="ckNumSolIncRegistroResuelta" data-function="checkbox-custom-input" class="pae-form__custom-check">
								    </c:otherwise>
								</c:choose>	      	                          
	                            <label for="ckNumSolIncRegistroResuelta" id="numSolIncRegistroResuelta" data-function="checkbox-custom" title="Al seleccionar, se mostrará información adicional sobre 
	                            	Nº Solicitudes Incidencias Registro Resuelta" class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
	                            	<spring:message code="field.cuadroMando.numSolIncRegistroResuelta.mayus"/>
	                            </label>
	                          </li>
	                          <li class="pae-form__list-item">
	                          	<c:choose>
								    <c:when test="${consultarCuadroMandoForm.ckNumSolIncRegistroSinResolver}">
								         <input type="checkbox" name="ckNumSolIncRegistroSinResolver" id="ckNumSolIncRegistroSinResolver" data-function="checkbox-custom-input" class="pae-form__custom-check" checked>
								    </c:when>    
								    <c:otherwise>
										 <input type="checkbox" name="ckNumSolIncRegistroSinResolver" id="ckNumSolIncRegistroSinResolver" data-function="checkbox-custom-input" class="pae-form__custom-check">
								    </c:otherwise>
								</c:choose>	 	                                    
	                            <label for="ckNumSolIncRegistroSinResolver" id="numSolIncRegistroSinResolver" data-function="checkbox-custom" title="Al seleccionar, se mostrará información adicional sobre 
	                           		Nº Solicitud Incidencia Registro Sin Resolver" class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
	                            	<spring:message code="field.cuadroMando.numSolIncRegistroSinResolver.mayus"/>
	                            </label>
	                          </li>	
	                         </ul>
                          </div>						
                      </div>  			
				    </div>
					<%-- MAQUETACION INSCRIPCION FIN --%>	
					<%-- MAQUETACION MODELO790 INI --%>	
					<div class="pae-layout__item pae-u-6/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
						<div class="pae-form__cell">
							<h3 class="pae-box__header--title">
								<spring:message code="field.cuadroMando.datosModelo790"/>
							</h3>						
						<div class="pae-layout__item pae-u-12/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
	 						<ul class="pae-form__list pae-mt">                     
	                          <li class="pae-form__list-item">
	                          	<c:choose>
								    <c:when test="${consultarCuadroMandoForm.ckNumDescargasManuales}">
								        <input type="checkbox"  id="ckNumDescargasManuales" name="ckNumDescargasManuales" data-function="checkbox-custom-input" class="pae-form__custom-check" checked>
								    </c:when>    
								    <c:otherwise>
										<input type="checkbox"  id="ckNumDescargasManuales" name="ckNumDescargasManuales" data-function="checkbox-custom-input" class="pae-form__custom-check">
								    </c:otherwise>
								</c:choose>
	                            <label for="ckNumDescargasManuales" id="numSolIncPagoResuelta" data-function="checkbox-custom" title="Al seleccionar, se mostrará información adicional sobre 
	                            Nº Solicitudes Incidencias Pago Resuelta" class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
	                            	<spring:message code="field.cuadroMando.numDescargasManuales.mayus"/>
	                            </label>
	                          </li> 
	                          <li class="pae-form__list-item">
	                          	<c:choose>
								    <c:when test="${consultarCuadroMandoForm.ckNumDescargasManualesTotal}">
								        <input type="checkbox" name="ckNumDescargasManualesTotal" id="ckNumDescargasManualesTotal" data-function="checkbox-custom-input" class="pae-form__custom-check" checked>
								    </c:when>    
								    <c:otherwise>
										<input type="checkbox" name="ckNumDescargasManualesTotal" id="ckNumDescargasManualesTotal" data-function="checkbox-custom-input" class="pae-form__custom-check">
								    </c:otherwise>
								</c:choose>	                          
	                            <label for="ckNumDescargasManualesTotal" id="numSolIncPagoSinResolver" data-function="checkbox-custom" title="Al seleccionar, se mostrará información adicional sobre 
	                            Nº Solicitudes Incidencias Pago Sin Resolver" class="pae-form__custom-check-label pae-form__custom-check-label--option unchecked">
	                            	<spring:message code="field.cuadroMando.numDescargasManualesTotal.mayus"/>
	                            </label>
	                          </li>   
	                         </ul>
                          </div>						
                      </div>  			
				    </div>					
					<%-- MAQUETACION MODELO790 FIN --%>	
					</sec:authorize>
					
					<div class="pae-layout">
						<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-80">
							<div class="pae-box-buttons">
								<input type="submit" name="buscar" value="Buscar" onclick="return busqueda()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>							
							</div>
						</div>
					</div>
				</div>
			</fieldset>
		</div>
 	</div>
<%-- MAQUETACION FILTROS FIN --%>	

	

<!-- 	<a name="ancla" id="ancla"/>  -->
	
	  
	<c:if test="${sVieneMenu == 'N'}">
	<%	  Boolean mostrarCentroGestor = (Boolean) request.getAttribute("mostrarCentroGestor");
		  Boolean mostrarEjercicio = (Boolean) request.getAttribute("mostrarEjercicio");
		  Boolean mostrarCuerpoEscala = (Boolean) request.getAttribute("mostrarCuerpoEscala");
		  Boolean mostrarEstado = (Boolean) request.getAttribute("mostrarEstado");
		  Boolean mostrarFechaBOE = (Boolean) request.getAttribute("mostrarFechaBOE");
		  Boolean mostrarFormaAcceso = (Boolean) request.getAttribute("mostrarFormaAcceso");
		  Boolean mostrarNumDescargasManuales = (Boolean) request.getAttribute("mostrarNumDescargasManuales");
		  Boolean mostrarNumInscripcionSinPago = (Boolean) request.getAttribute("mostrarNumInscripcionSinPago");
		  Boolean mostrarNumDescargasManualesTotal = (Boolean) request.getAttribute("mostrarNumDescargasManualesTotal");
		  		  
		  Boolean mostrarNumPagosSinRegistro = (Boolean) request.getAttribute("mostrarNumPagosSinRegistro");
		  Boolean mostrarNumPlazasDiscap = (Boolean) request.getAttribute("mostrarNumPlazasDiscap");
		  Boolean mostrarNumPlazasTotales = (Boolean) request.getAttribute("mostrarNumPlazasTotales");
		  Boolean mostrarNumSolIncPagoResuelta = (Boolean) request.getAttribute("mostrarNumSolIncPagoResuelta");
		  Boolean mostrarNumSolIncPagoSinResolver = (Boolean) request.getAttribute("mostrarNumSolIncPagoSinResolver");
		  Boolean mostrarNumSolIncRegistroResuelta = (Boolean) request.getAttribute("mostrarNumSolIncRegistroResuelta");
		  Boolean mostrarNumSolIncRegistroSinResolver = (Boolean) request.getAttribute("mostrarNumSolIncRegistroSinResolver");
		  Boolean mostrarNumSolPresenciales = (Boolean) request.getAttribute("mostrarNumSolPresenciales");
		  Boolean mostrarNumSolTelematicas = (Boolean) request.getAttribute("mostrarNumSolTelematicas");
		  Boolean mostrarNumSolSinIntentoPago =(Boolean) request.getAttribute("mostrarNumSolSinIntentoPago");
		  Boolean mostrarNumPagosSinIntentoRegistro =(Boolean) request.getAttribute("mostrarNumPagosSinIntentoRegistro");
%>
	<logic:present name="lConvocatoriasViewBean" scope="request">	
		<bean:size id="numResultados" name="lConvocatoriasViewBean" scope="request"/>
		
		<logic:greaterThan name="numResultados" value="0">
		<bean:define id="idMinisterioAux" value="0"></bean:define>
    	<bean:define id="idMinisterioTotalAux" value="0"></bean:define>
    	

		 
		<div class="scrollmenu">
		<table id="paetable" class="pae-table pae-table--margin">
		<logic:iterate id="registro" name="lConvocatoriasViewBean"> <!-- Bean que te trae los resultados de lConvocatoriasViewBean-->
		
		<!-- Asignación de la variable Auxiliar -->	
    		
    	<logic:notEqual name="registro" property="idMinisterio" value="${idMinisterioAux}" > 	
				<!-- INI Nombre del ministerio -->
				<thead class="pae-table__header"> 
				<tr background="white" class="pae-pv1"><td colspan="8" style="background-color :white">
				<span>
				<b><bean:write name="registro" property="desMinisterio"/></b>
				</span>
				</td>
				</tr>
				<bean:define id="idMinisterioTotalAux" value="${idMinisterioAux}"></bean:define>
				<bean:define id="idMinisterioAux" value="${registro.idMinisterio}"></bean:define>
		
				<!-- FIN Poner nombre del ministerio -->
		<!-- Se crea una tabla por cada Ministerio -->	
		<logic:notEqual name="registro" property="desCentroGestor" value="TOTAL" >
			 <!-- Cabecera de la tabla -->
		 		<caption class="hiddenAccesible">Tabla <bean:write name="registro" property="desMinisterio"/></caption>				 			 
				<tr class="pae-table__row">
					<c:if test="${mostrarCentroGestor == true}">
						<th rowspan="2" data-col="col3" class="pae-table__cell-header ">	
							<span class="pae-table__txt-title">
								<spring:message code="field.cuadroMando.centroGestor.mayus"/>
							</span>
						</th>
					</c:if>
					<c:if test="${mostrarEjercicio == true}">
						<th rowspan="2" data-col="col3" class="pae-table__cell-header ">	
							<span class="pae-table__txt-title">
								<spring:message code="field.cuadroMando.ejercicio.mayus"/>
							</span>
						</th>
					</c:if>
					<c:if test="${mostrarCuerpoEscala == true}">
						<th rowspan="2" data-col="col3" class="pae-table__cell-header ">	
							<span class="pae-table__txt-title">
								<spring:message code="field.cuadroMando.cuerpoEscala.mayus"/>
							</span>
						</th>
					</c:if>
					<c:if test="${mostrarFormaAcceso == true}">
						<th rowspan="2" data-col="col3" class="pae-table__cell-header ">	
							<span class="pae-table__txt-title">
								<spring:message code="field.cuadroMando.formaAcceso.mayus"/>
							</span>
						</th>
					</c:if>
					
					<c:if test="${mostrarFechaBOE == true}">
						<th rowspan="2" data-col="col3" class="pae-table__cell-header ">	
							<span class="pae-table__txt-title">
								<spring:message code="field.cuadroMando.fechaBOE.mayus"/>
							</span>
						</th>
					</c:if>			
					<c:if test="${mostrarEstado == true}">
						<th rowspan="2" data-col="col3" class="pae-table__cell-header ">
							<span class="pae-table__txt-title">	
								<spring:message code="field.cuadroMando.estadoTabla"/>
							</span>
						</th>
					</c:if>
					<c:if test="${mostrarNumPlazasTotales == true}">
						<th rowspan="2" data-col="col3" class="pae-table__cell-header ">	
							<span class="pae-table__txt-title">
								<spring:message code="field.cuadroMando.numPlazasTotalesTabla"/>
							</span>
						</th>
					</c:if>
					<c:if test="${mostrarNumPlazasDiscap == true}">
						<th rowspan="2" data-col="col3" class="pae-table__cell-header ">
							<span class="pae-table__txt-title">	
								<spring:message code="field.cuadroMando.numPlazasDiscapTabla"/>		
							</span>
						</th>
					</c:if>					
					<c:if test="${mostrarNumDescargasManuales == true || mostrarNumDescargasManualesTotal == true}">
						<th rowspan="2" data-col="col3" class="pae-table__cell-header ">	
							<span class="pae-table__txt-title">
								<spring:message code="field.cuadroMando.descargaManual.mayus"/>
							</span>
						</th>
					</c:if>
					
					<c:choose>
					<c:when test="${mostrarNumSolTelematicas == true && mostrarNumSolPresenciales == true}">
						<th colspan="2" data-col="col3" class="pae-table__cell-header ">
							<span class="pae-table__txt-title">	
								<spring:message code="field.cuadroMando.titulos.solicitudes"/>
							</span>
						</th>
						
					</c:when>
					<c:otherwise>
						
							<c:if test="${mostrarNumSolTelematicas == true}">
								<th rowspan="2" data-col="col3" class="pae-table__cell-header ">	
									<span class="pae-table__txt-title">
										<spring:message code="field.cuadroMando.solTelematicas.mayus"/>
									</span>
								</th>
							</c:if>
							<c:if test="${mostrarNumSolPresenciales == true}">
								<th rowspan="2" data-col="col3" class="pae-table__cell-header ">
									<span class="pae-table__txt-title">	
										<spring:message code="field.cuadroMando.solPresenciales.mayus"/>
									</span>
								</th>	
							</c:if>
						
					</c:otherwise>
					</c:choose>

					<c:choose>
						<c:when test="${mostrarNumInscripcionSinPago == true && mostrarNumSolSinIntentoPago == true}">
							<th colspan="2" data-col="col3" class="pae-table__cell-header ">	
								<span class="pae-table__txt-title">
									<span class="pae-table__txt-title">
										<spring:message code="field.cuadroMando.titulos.inscripcionesSinPagar"/>
									</span>
								<span class="pae-table__txt-title">
							</th>
						</c:when>
						<c:otherwise>
							<c:if test="${mostrarNumInscripcionSinPago == true}">
								<th rowspan="2" data-col="col3" class="pae-table__cell-header ">	
									<span class="pae-table__txt-title">
										<spring:message code="field.cuadroMando.inscripcionesSinPago.mayus"/>
									</span>
								</th>
							</c:if>		
							<c:if test="${mostrarNumSolSinIntentoPago == true}">
								<th rowspan="2" data-col="col3" class="pae-table__cell-header ">	
									<span class="pae-table__txt-title">
										<spring:message code="field.cuadroMando.inscripcionesSinIntentoPago.mayus"/>
									</span>
								</th>
							</c:if>
						</c:otherwise>
					</c:choose>
					
					<c:choose>
						<c:when test="${mostrarNumPagosSinRegistro == true && mostrarNumPagosSinIntentoRegistro == true}">
							<th colspan="2" data-col="col3" class="pae-table__cell-header ">	
								<span class="pae-table__txt-title">
									<spring:message code="field.cuadroMando.titulos.pagosSinRegistrar"/>
								</span>
							</th>
						</c:when>
						<c:otherwise>
							<c:if test="${mostrarNumPagosSinRegistro == true}">
								<th rowspan="2" data-col="col3" class="pae-table__cell-header ">
									<span class="pae-table__txt-title">	
										<spring:message code="field.cuadroMando.pagosSinRegistro.mayus"/>
									</span>
								</th>
							</c:if>	
							<c:if test="${mostrarNumPagosSinIntentoRegistro == true}">
								<th rowspan="2" data-col="col3" class="pae-table__cell-header ">	
									<span class="pae-table__txt-title">
										<spring:message code="field.cuadroMando.pagosSinIntentoRegistro.mayus"/>
									</span>
								</th>
							</c:if>
						</c:otherwise>
					</c:choose>
										
					<c:choose>
					<c:when test="${mostrarNumSolIncPagoResuelta == true && mostrarNumSolIncPagoSinResolver == true}">
						<th colspan="2" data-col="col3" class="pae-table__cell-header ">
							<span class="pae-table__txt-title">	
								<spring:message code="field.cuadroMando.titulos.incidenciasPago"/>
							</span>
						</th>
					</c:when>
					<c:otherwise>
						<sec:authorize ifAnyGranted="ROLE_ADMIN">
							<c:if test="${mostrarNumSolIncPagoResuelta == true}">
								<th rowspan="2"  data-col="col3" class="pae-table__cell-header ">	
									<span class="pae-table__txt-title">
										<spring:message code="field.cuadroMando.incPagoResuelta.mayus"/>
									</span>
								</th>
							</c:if>
						</sec:authorize>		
							<c:if test="${mostrarNumSolIncPagoSinResolver == true}">
								<th rowspan="2" data-col="col3" class="pae-table__cell-header ">	
									<span class="pae-table__txt-title">
										<spring:message code="field.cuadroMando.incPagoSinResolver.mayus"/>
									</span>
								</th>	
							</c:if>
						
					</c:otherwise>
					</c:choose>
					<c:choose>
					<c:when test="${mostrarNumSolIncRegistroResuelta == true && mostrarNumSolIncRegistroSinResolver == true}">
						<th colspan="2" data-col="col3" class="pae-table__cell-header ">	
							<span class="pae-table__txt-title">
								<spring:message code="field.cuadroMando.titulos.incidenciasRegistro"/>
							</span>
						</th>
					</c:when>
					<c:otherwise>
						<sec:authorize ifAnyGranted="ROLE_ADMIN">
							<c:if test="${mostrarNumSolIncRegistroResuelta == true}">
								<th rowspan="2" data-col="col3" class="pae-table__cell-header ">	
									<span class="pae-table__txt-title">
										<spring:message code="field.cuadroMando.incRegistroResuelta.mayus"/>
									</span>
								</th>
							</c:if>
						</sec:authorize>	
							<c:if test="${mostrarNumSolIncRegistroSinResolver == true}">
								<th rowspan="2" data-col="col3" class="pae-table__cell-header ">
									<span class="pae-table__txt-title">	
										<spring:message code="field.cuadroMando.incRegistroSinResolver.mayus"/>
									</span>
								</th>	
							</c:if>
						
					</c:otherwise>
					</c:choose>
								
				</tr>
				<tr class="pae-table__row">
					<c:if test="${mostrarNumSolTelematicas == true && mostrarNumSolPresenciales == true}">
						<th data-col="col3" class="pae-table__cell-header ">
							<span class="pae-table__txt-title">	
								<spring:message code="field.cuadroMando.subtitulo.telem"/>
							</span>
						</th>
						<th data-col="col3" class="pae-table__cell-header ">
							<span class="pae-table__txt-title">	
								<spring:message code="field.cuadroMando.subtitulo.presen"/>
							</span>
						</th>
					</c:if>
					<c:if test="${mostrarNumInscripcionSinPago == true && mostrarNumSolSinIntentoPago == true}">
						<th data-col="col3" class="pae-table__cell-header ">	
							<span class="pae-table__txt-title">
								<spring:message code="field.cuadroMando.subtitulo.sinPago"/>
							</span>
						</th>
						<th data-col="col3" class="pae-table__cell-header ">	
							<span class="pae-table__txt-title">
								<spring:message code="field.cuadroMando.subtitulo.sinIntPago"/>
							</span>
						</th>
					</c:if>
					<c:if test="${mostrarNumPagosSinRegistro == true && mostrarNumPagosSinIntentoRegistro == true}">
						<th data-col="col3" class="pae-table__cell-header ">	
							<span class="pae-table__txt-title">
								<spring:message code="field.cuadroMando.subtitulo.sinRegistro"/>
							</span>
						</th>
						<th data-col="col3" class="pae-table__cell-header ">
							<span class="pae-table__txt-title">	
								<spring:message code="field.cuadroMando.subtitulo.sinIntRegistro"/>
							</span>	
						</th>
					</c:if>
					<c:if test="${mostrarNumSolIncPagoResuelta == true && mostrarNumSolIncPagoSinResolver == true}">
						<th data-col="col3" class="pae-table__cell-header ">	
							<span class="pae-table__txt-title">
								<spring:message code="field.cuadroMando.subtitulo.resuelto"/>
							</span>
						</th>
						<th data-col="col3" class="pae-table__cell-header ">	
							<span class="pae-table__txt-title">
								<spring:message code="field.cuadroMando.subtitulo.sinResolver"/>
							</span>
						</th>
					</c:if>
					<c:if test="${mostrarNumSolIncRegistroResuelta == true && mostrarNumSolIncRegistroSinResolver == true}">
						<th data-col="col3" class="pae-table__cell-header ">	
							<span class="pae-table__txt-title">
								<spring:message code="field.cuadroMando.subtitulo.resuelto"/>
							</span>
						</th>
						<th data-col="col3" class="pae-table__cell-header ">	
							<span class="pae-table__txt-title">
								<spring:message code="field.cuadroMando.subtitulo.sinResolver"/>
							</span>
						</th>
						</c:if>

				</tr>
				</logic:notEqual> <!-- Fin desCentroGestor != 'TOTAL' -->
				</logic:notEqual> <!-- Fin  Registro.idMinisterio != idMinisterioAux -->
				<!-- Fin de la cabecera -->
			
				<!-- Resultados de la consulta -->
				<logic:notEqual name="registro" property="desCentroGestor" value="TOTAL" >
					<tr class="pae-table__row" name="row">
						<c:if test="${mostrarCentroGestor == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="desCentroGestor" /></span>
							</td>
						</c:if>
						<c:if test="${mostrarEjercicio == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="ejercicio" /></span></td>
						</c:if>
						<c:if test="${mostrarCuerpoEscala == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="desCuerpoEscala" /></span></td>
						</c:if>
						<c:if test="${mostrarFormaAcceso == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="desFormaAcceso" /></span></td>
						</c:if>
						<c:if test="${mostrarFechaBOE == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="fechaBoe" /></span></td>
						</c:if>						
						<c:if test="${mostrarEstado == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="desEstadoConvocatoria" /></span></td>
						</c:if>
						<c:if test="${mostrarNumPlazasTotales == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="plazasTotal" /></span></td>
						</c:if>						
						<c:if test="${mostrarNumPlazasDiscap == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="plazasDiscapacitados" /></span></td>
						</c:if>						
						<c:if test="${mostrarNumDescargasManuales == true || mostrarNumDescargasManualesTotal == true }">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numDescargasManuales"/></span></td>
						</c:if>
						<c:if test="${mostrarNumSolTelematicas == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numSolTelematica"/></span></td>
						</c:if>
						<c:if test="${mostrarNumSolPresenciales == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numSolPresencial"/></span></td>
						</c:if>
						<c:if test="${mostrarNumInscripcionSinPago == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numInscripcionSinPago"/></span></td>
						</c:if>
						<c:if test="${mostrarNumSolSinIntentoPago == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numSolSinIntentoPago"/></span></td>
						</c:if>
						<c:if test="${mostrarNumPagosSinRegistro == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numPagosSinRegistro"/></span></td>
						</c:if>
						<c:if test="${mostrarNumPagosSinIntentoRegistro == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numPagosSinIntentoRegistro"/></span></td>
						</c:if>

						<sec:authorize ifAnyGranted="ROLE_ADMIN">
						<c:if test="${mostrarNumSolIncPagoResuelta == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numSolIncPagoResuelta"/></span></td>
							</c:if>

						</sec:authorize>
						
						<c:if test="${mostrarNumSolIncPagoSinResolver == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numSolIncPagoSinResolver"/></span></td>
						</c:if>
						
						<sec:authorize ifAnyGranted="ROLE_ADMIN">	
							<c:if test="${mostrarNumSolIncRegistroResuelta == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numSolIncRegistroResuelta"/></span></td>
							</c:if>
						</sec:authorize>
						
						<c:if test="${mostrarNumSolIncRegistroSinResolver == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numSolIncRegistroSinResolver"/></span></td>
						</c:if>
						

	    			</tr>
	    			<!-- Fin Resultados de la consulta -->
    			</logic:notEqual>
    			<!--Cálculo de los Totales por Ministerios -->
    			<logic:greaterThan name="numResultados" value="1"> <!-- Se mostrará si viene más de un registro. Uno es para el registro Final del Total -->
					<logic:equal name="registro" property="desCentroGestor" value="TOTAL" >
					<!-- INI Nombre del ministerio -->
					</thread>
					<thead class="pae-table__header"> 
					<tr background="white" bordercolor="#FFFFFF">
						<td colspan="8" style="background-color :white; border: #FFFFFF">
						<span>
							<b><spring:message code="field.cuadroMando.totalMinisterio"/></b>
						</span>
						</td>
					</tr>
					<!-- FIN Poner nombre del ministerio -->
					<thead class="pae-table__header">  	
					<tr class="pae-table__row" name="row">
		    			<c:if test="${mostrarCentroGestor == true}">
							<td id="espacio" data-content="" data-col="col3" data-function="fc-collapse-table" style="
    							border-bottom-color:  white;" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head">&nbsp;</span></td>
						</c:if>
						<c:if test="${mostrarEjercicio == true}">
							<td id="espacio" data-content="" data-col="col3" data-function="fc-collapse-table"  class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head">&nbsp;</span></td>
						</c:if>
						<c:if test="${mostrarCuerpoEscala == true}">
							<td id="espacio" data-content="" data-col="col3" data-function="fc-collapse-table" style="
    							border-bottom-color:  white;"  class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head">&nbsp;</span></td>
						</c:if>
						<c:if test="${mostrarFormaAcceso == true}">
							<td id="espacio" data-content="" data-col="col3" data-function="fc-collapse-table" style="
    							border-bottom-color:  white;" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head">&nbsp;</span></td>
						</c:if>
						<c:if test="${mostrarFechaBOE == true}">
							<td id="espacio" data-content="" data-col="col3" data-function="fc-collapse-table" style="
    							border-bottom-color:  white;"  class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head">&nbsp;</span></td>
						</c:if>						
						<c:if test="${mostrarEstado == true}">
							<td id="espacio" data-content="" data-col="col3" data-function="fc-collapse-table" style="
    							border-bottom-color:  white;" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head">&nbsp;</span></td>
						</c:if>
						<c:if test="${mostrarNumPlazasTotales == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numTotalPlazasTotales"/></span></td>
						</c:if>
						<c:if test="${mostrarNumPlazasDiscap == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numTotalPlazasDiscapacitados"/></span></td>
						</c:if>						
						<c:if test="${mostrarNumDescargasManuales == true || mostrarNumDescargasManualesTotal == true }">						
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numTotalDescargasManuales"/></span></td>
						</c:if>
						<c:if test="${mostrarNumSolTelematicas == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numTotalSolTelematica"/></span></td>
						</c:if>
						<c:if test="${mostrarNumSolPresenciales == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numTotalSolPresencial"/></span></td>
						</c:if>
						<c:if test="${mostrarNumInscripcionSinPago == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numTotalInscripcionSinPago"/></span></td>
						</c:if>
						<c:if test="${mostrarNumSolSinIntentoPago == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numTotalInscripcionSinIntentoPago"/></span></td>
						</c:if>
						<c:if test="${mostrarNumPagosSinRegistro == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numTotalPagosSinRegistro"/></span></td>
						</c:if>
						<c:if test="${mostrarNumPagosSinIntentoRegistro == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numTotalPagosSinIntentoRegistro"/></span></td>
						</c:if>	
						<sec:authorize ifAnyGranted="ROLE_ADMIN">
							<c:if test="${mostrarNumSolIncPagoResuelta == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numTotalSolIncPagoResuelta"/></span></td>
							</c:if>
						</sec:authorize>	
							<c:if test="${mostrarNumSolIncPagoSinResolver == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numTotalSolIncPagoSinResolver"/></span></td>
						</c:if>	
						<sec:authorize ifAnyGranted="ROLE_ADMIN">
							<c:if test="${mostrarNumSolIncRegistroResuelta == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numTotalSolIncRegistroResuelta"/></span></td>
							</c:if>
						</sec:authorize>
						<c:if test="${mostrarNumSolIncRegistroSinResolver == true}">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body ">
								<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numTotalSolIncRegistroSinResolver"/></span></td>
						</c:if>
    				</tr>
    				</thead>
    				<!-- INI Mensajes Fecha Sustitución-->
    				<!-- Sustituido a Otro -->
    				<logic:equal name="registro" property="sustituidoAOtro" value="S" >
					<tr background="white">
						<td colspan="8" style="background-color :white;">
								<spring:message code="field.cuadroMando.sustituidoAOtro1"/>
								<bean:write name="registro" property="fechaSustitucion"/>
								<spring:message code="field.cuadroMando.sustituidoAOtro2"/>
								<bean:write name="registro" property="desMinisterioSust"/>
								<spring:message code="field.cuadroMando.sustituidoAOtro3"/>
						</td>
					</tr>
					</logic:equal>
					<!-- sustituidoPorOtro -->
					<logic:equal name="registro" property="sustituidoPorOtro" value="S" >
					<tr background="white">
						<td colspan="8" class="otro" style="background-color :white; ">
								<spring:message code="field.cuadroMando.sustituidoPorOtro1"/>
								<bean:write name="registro" property="fechaSustitucion2"/>
								<spring:message code="field.cuadroMando.sustituidoPorOtro2"/>
								<bean:write name="registro" property="desMinisterioSustitutivo"/>
								<spring:message code="field.cuadroMando.sustituidoPorOtro3"/>
						</td>
					</tr>
					
					</logic:equal>
					<!-- FIN Mensajes Fecha Sustitución -->
	    			</logic:equal>
	    		</logic:greaterThan>
	    		
	    	<!-- Fin Resultados del Registro Total -->
		</logic:iterate> <!-- Fin del Iterador que trae los resultados de lConvocatoriasViewBean-->
		
	
		<!-- TOTAL DE TODOS LOS MINISTERIOS -->
		<logic:present name="totales">
		<bean:define id="totales" name="totales" type="es.map.ipsg.bean.ConvocatoriasViewBean"></bean:define>
		<tr background="white" bordercolor="#FFFFFF">
			<td style="background-color :white" colspan="8" height="40">
    		</td>
		</tr>
			<!-- INI Nombre del ministerio -->
		<tr bordercolor="#FFFFFF">					
					<c:if test="${mostrarNumDescargasManuales == true || mostrarNumDescargasManualesTotal == true }">						
						<th style="background-color: red"><spring:message code="field.cuadroMando.totalDescargasManuales"/></th>
					</c:if>
					<c:if test="${mostrarNumSolTelematicas == true}">
						<th style="background-color: red"><spring:message code="field.cuadroMando.totalsolicitudesTelematicas"/></th>
					</c:if>
					<c:if test="${mostrarNumSolPresenciales == true}">
						<th style="background-color: red"><spring:message code="field.cuadroMando.totalsolicitudesPresenciales"/></th>
					</c:if>
					<c:if test="${mostrarNumInscripcionSinPago == true}">
						<th style="background-color: red"><spring:message code="field.cuadroMando.totalsolicitudesSinPago"/></th>
					</c:if>
					<c:if test="${mostrarNumSolSinIntentoPago == true}">
						<th style="background-color: red"><spring:message code="field.cuadroMando.totalsolicitudesSinIntentoPago"/></th>
					</c:if>
					<c:if test="${mostrarNumPagosSinRegistro == true}">
						<th style="background-color: red"><spring:message code="field.cuadroMando.totalsolicitudesSinRegistro"/></th>
					</c:if>
					<c:if test="${mostrarNumPagosSinIntentoRegistro == true}">
						<th style="background-color: red"><spring:message code="field.cuadroMando.totalsolicitudesSinIntentoRegistro"/></th>
					</c:if>		
					<sec:authorize ifAnyGranted="ROLE_ADMIN">
						<c:if test="${mostrarNumSolIncPagoResuelta == true}">
							<th style="background-color: red"><spring:message code="field.cuadroMando.totalsolicitudesIncPagoResuelta"/></th>
						</c:if>
					</sec:authorize>	
						<c:if test="${mostrarNumSolIncPagoSinResolver == true}">
							<th style="background-color: red"><spring:message code="field.cuadroMando.totalsolicitudesIncPagoSinResolver"/></th>
					</c:if>	
					<sec:authorize ifAnyGranted="ROLE_ADMIN">
						<c:if test="${mostrarNumSolIncRegistroResuelta == true}">
							<th style="background-color: red"><spring:message code="field.cuadroMando.totalsolicitudesIncRegistroResuelta"/></th>
						</c:if>
					</sec:authorize>
					<c:if test="${mostrarNumSolIncRegistroSinResolver == true}">
						<th style="background-color: red"><spring:message code="field.cuadroMando.totalsolicitudesIncRegistroSinResolver"/></th>
					</c:if> 
   				</tr>		
				<!-- FIN Poner nombre del ministerio -->
   				<tr>
					<c:if test="${mostrarNumDescargasManuales == true || mostrarNumDescargasManualesTotal == true }">						
						<td style="background-color: rgb(255,210,210);"><bean:write name="totales" property="numTotalDescargasManuales"/></td>
					</c:if>
					<c:if test="${mostrarNumSolTelematicas == true}">
						
						<td style="background-color: rgb(255,210,210);"><bean:write name="totales" property="numTotalSolTelematica"/></td>
					</c:if>
					<c:if test="${mostrarNumSolPresenciales == true}">
						
						<td style="background-color: rgb(255,210,210);"><bean:write name="totales" property="numTotalSolPresencial"/></td>
					</c:if>
					<c:if test="${mostrarNumInscripcionSinPago == true}">
						
						<td style="background-color: rgb(255,210,210);"><bean:write name="totales" property="numTotalInscripcionSinPago"/></td>
					</c:if>
					<c:if test="${mostrarNumSolSinIntentoPago == true}">
						
						<td style="background-color: rgb(255,210,210);"><bean:write name="totales" property="numTotalInscripcionSinIntentoPago"/></td>
					</c:if>
					<c:if test="${mostrarNumPagosSinRegistro == true}">
						
						<td style="background-color: rgb(255,210,210);"><bean:write name="totales" property="numTotalPagosSinRegistro"/></td>
					</c:if>
					<c:if test="${mostrarNumPagosSinIntentoRegistro == true}">
						
						<td style="background-color: rgb(255,210,210);"><bean:write name="totales" property="numTotalPagosSinIntentoRegistro"/></td>
					</c:if>	
					<sec:authorize ifAnyGranted="ROLE_ADMIN">
						<c:if test="${mostrarNumSolIncPagoResuelta == true}">
							
						<td style="background-color: rgb(255,210,210);"><bean:write name="totales" property="numTotalSolIncPagoResuelta"/></td>
						</c:if>
					</sec:authorize>	
						<c:if test="${mostrarNumSolIncPagoSinResolver == true}">
						
						<td style="background-color: rgb(255,210,210);"><bean:write name="totales" property="numTotalSolIncPagoSinResolver"/></td>
					</c:if>	
					<sec:authorize ifAnyGranted="ROLE_ADMIN">
						<c:if test="${mostrarNumSolIncRegistroResuelta == true}">
							
						<td style="background-color: rgb(255,210,210);"><bean:write name="totales" property="numTotalSolIncRegistroResuelta"/></td>
						</c:if>
					</sec:authorize>
					<c:if test="${mostrarNumSolIncRegistroSinResolver == true}">
						
						<td style="background-color: rgb(255,210,210);"><bean:write name="totales" property="numTotalSolIncRegistroSinResolver"/></td>
					</c:if>
   				</tr>		
    		
    		</logic:present>	
    		</table>	
			<br></br><br></br>
			<c:if test="${mostrarNumDescargasManualesTotal == true}">
			 <table class="tabla_resultados">
				<th colspan="17"><!-- Descarga Manual -->
					<spring:message code="field.cuadroMando.descargaManualBlanco.mayus"/>
				</th>
				<tr>
						<td colspan="17">		
							<logic:present name="numeroDescargasModeloBlanco" scope="request">	
								
									<bean:write name="numeroDescargasModeloBlanco" scope="request"/>
								
							</logic:present>
						</td>	
				</tr>
			</table>
			</c:if>
		
		<br>
		<logic:greaterThan name="numResultados" value="1"> <!-- Se mostrará si viene más de un registro. Uno es para el registro Final del Total -->
			<br><br>
			<div class="pae-box-buttons" align="center">
			<input type="submit" id="exportar" value="Exportar A Excel" onclick="exportarExcel('Exportar');" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline" />
			</div>
		</logic:greaterThan>
		
		</logic:greaterThan> <!-- Fin: numResultados >  0 -->		
		<logic:lessThan name="numResultados" value="2"> <!-- Un Resultado es el total que viene al final -->
				<div id="error">
					<spring:message code="field.cuadroMando.error"/>
				</div>
		</logic:lessThan>
	</logic:present>
	<logic:notPresent name="lConvocatoriasViewBean" scope="request">
		<div id="error">
			<spring:message code="field.cuadroMando.error"/>
		</div>
	</logic:notPresent>
</c:if>
<!--</div>-->
</form:form>
	
<!--</div>-->
</body>
</html>