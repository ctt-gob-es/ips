<%@ page contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page import="es.map.ipsg.bean.*" %>
<%@ page import="es.map.ipsg.util.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.apache.commons.lang.time.DateUtils" %>


<script type="text/javascript">
$(document).ready(function(){
	$('body').css("background-color","white");
	$('#divTable').append($('#table'));
	$('#divPrincipal').append($('#boxMotivoTasa'));
	$('#divPrincipal').append($('#boxTitulosExigidos'));
	$('#divPrincipal').append($('#boxEspecialidades'));
	$('#divPrincipal').append($('#boxHistorico'));
});
function comprobarPublicar(){
	return confirm('<spring:message code="field.comprobarPublicar"/>');
}
function comprobarReconfigurar(){
	return confirm('<spring:message code="field.comprobarReconfigurar"/>');
}
function comprobarDesactivar(){
	return confirm('<spring:message code="field.comprobarDesactivar"/>');
}
function comprobarAprobar(){
	return confirm('<spring:message code="field.comprobarAprobar"/>');
}

function volver(){
	window.location.href = "../spring/buscarConvocatorias?accion=Buscar"; 
}

function copiar(){
	var id = document.getElementById("idConvocatoria").value;
	window.location.href = "../spring/copiarConvocatoria?id="+id; 
}

function formulario(){
	var id = document.getElementById("idConvocatoria").value;
	window.location.href = "../spring/plantillaConvocatoria?idConvocatoria="+id; 
}

function modificar(){
	var id = document.getElementById("idConvocatoria").value;
	window.location.href = "../spring/verModificarConvocatoria?menu=S&registro="+id; 
}

function publicarSubsanar(){
	var id = document.getElementById("idConvocatoria").value;
	window.location.href = "../spring/publicarSubsanar?menu=S&registro="+id; 
}

function openWindowDocumentos() {
	var navegador = navigator.appName;
	var extract = new Object();
	var id = document.getElementById("idConvocatoria").value;
	
	ventanaPopup = window.open("documentosConvocatoria?id="+id, 'miventana', 'width=550, height=470,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');	    
	ventanaPopup.focus();
	 
}
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<link rel="stylesheet"
	href='<%=request.getContextPath()%>/css/estilosDetalle.css' type="text/css">
<body style="margin-left:0.4em;">

<form:form modelAttribute="convocatoria" action="/IPSG/spring/ActualizarEstadoConvocatoria" id="mainForm">
	<form:hidden path="idConvocatoria" id="idConvocatoria" name="convocatoria"/>
	<form:hidden path="fechaInicio" id="fechaInicio" name="convocatoria"/>
	<form:hidden path="fechaFin" id="fechaFin" name="convocatoria"/>
	<form:hidden path="fechaBoe" id="fechaBoe" name="convocatoria"/>
	<jsp:useBean id="convocatoria" scope="request" class="es.map.ipsg.bean.DetalleConvocatoriaBean" />	

	<%String publica = (String)request.getAttribute("publica");
	String perfil = (String)request.getAttribute("perfil"); %>
	
	<%
	if(convocatoria.isActualizar() == false){%>
	<p><center>
 			<strong><bean:write name="convocatoria" property="detalleError" scope="request" /></strong>
 		</center></p>
	<%}%>
	
	<h1 class="pae-title"><spring:message code="field.convocatoria.detalleMay"/></h1>
	<br>
	<logic:present name="org.apache.spring.ERROR" scope="request">
			<logic:present name="org.apache.spring.ERROR">
			<div id="error">
				<html:errors/>
			</div>
			<div class="clear"></div>
			</logic:present>
	</logic:present>
	<logic:present name="mensajeConfirmacion" scope="request">
		<strong style="margin-left: 2em; font-weight: bold"><bean:write name="mensajeConfirmacion" scope="request"/></strong>
		<br><br>
	</logic:present>
	<div id="divPrincipal">
	<div data-function="accordion-box" class="pae-box">
		<div class="pae-box__header">
			<h3 class="pae-box__header--title">
				<spring:message code="field.convocatoria"/>
			</h3>
		</div>
		<div class="pae-box__body">
			<fieldset>
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.idConvocatoria"/>
							</span>
							<bean:write name="convocatoria" property="idConvocatoria" scope="request" />
						</div>
					</div>
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.ejercicio"/>
							</span>
							<bean:write name="convocatoria" property="ejercicio" scope="request" />	
						</div>
					</div>
				</div>
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.centroGestor"/>
							</span>
							<bean:write name="convocatoria" property="centroGestor" scope="request" />
						</div>
					</div>
				</div>
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.cuerpo"/>
							</span>
							<bean:write name="convocatoria" property="cuerpoEscala" scope="request" />
						</div>
					</div>
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.ministerioConvocanteDetalle"/>
							</span>
							<bean:write name="convocatoria" property="ministerioConvocante" scope="request" />
						</div>
					</div>
				</div>
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.formaAcceso"/>
							</span>
							<bean:write name="convocatoria" property="formaAcceso" scope="request" />
						</div>
					</div>
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.importe"/>
							</span>
							<bean:write name="convocatoria" property="importe" scope="request" />
						</div>
					</div>
				</div>
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.dirigidoA"/>
							</span>
							<bean:write name="convocatoria" property="dirigidoA" scope="request" />
						</div>
					</div>				
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.fechaBoe"/>
							</span>
							<bean:write name="convocatoria" property="fechaBoe" scope="request" />
						</div>
					</div>
				</div>
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.fechaInicio"/>
							</span>
							<bean:write name="convocatoria" property="fechaInicio" scope="request" />
						</div>
					</div>
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.fechaFin"/>
							</span>
							<bean:write name="convocatoria" property="fechaFin" scope="request" />
						</div>
					</div>
				</div>
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.fechaInicioSub"/>
							</span>
							<bean:write name="convocatoria" property="fechaInicioSub" scope="request" />
						</div>
					</div>
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.fechaFinalSub"/>
							</span>
							<bean:write name="convocatoria" property="fechaFinSub" scope="request" />
						</div>
					</div>
				</div>
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.numPlazasTotales"/>
							</span>
							<bean:write name="convocatoria" property="numPlazasTotales" scope="request" />
						</div>
					</div>
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.numPlazasDiscapacitados"/>
							</span>
							<bean:write name="convocatoria" property="numPlazasDiscapacitados" scope="request" />
						</div>
					</div>
				</div>			
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.estadoConvocatoria"/>
							</span>
							<bean:write name="convocatoria" property="estadoConvocatoria" scope="request" />
						</div>
					</div>
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.tipoDocumentacionAnexar"/>
							</span>
							<bean:define id="tipoDocumentacionAnexar" name="convocatoria" property="tipoDocumentacionAnexar" scope="request"></bean:define>
									<c:if test="${tipoDocumentacionAnexar == 'R'}">
										<spring:message code="field.tipoDocumento.Exencion2" />
									</c:if>
									<c:if test="${tipoDocumentacionAnexar == 'C'}">
										<spring:message code="field.tipoDocumento.Cualquiera"/>
									</c:if>
									<c:if test="${tipoDocumentacionAnexar == 'N'}">
										<spring:message code="field.tipoDocumento.Ninguna"/>
									</c:if>
						</div>
					</div>

				</div>
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.presencial"/>
							</span>
							<logic:equal name="convocatoria" property="presencial" scope="request" value="true">
								Si
							</logic:equal>
							<logic:equal name="convocatoria" property="presencial" scope="request" value="false">
								No
							</logic:equal>
						</div>
					</div>
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.enlaceInformativoMay"/>
							</span>
							<bean:write name="convocatoria" property="enlace" scope="request" />
						</div>
					</div>
				</div>	
			</fieldset>
			<fieldset>
			<div class="pae-layout">
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm ">
						<div class="pae-box-buttons pd-top-3">
							<input type="button" name="accion" value="Formulario" onclick="formulario()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
						</div>
					</div>
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm ">
						<div class="pae-box-buttons pd-top-3">
							<input type="button" name="accion" value="Copiar" onclick="copiar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
						</div>
					</div>
					<%if(Constantes.CONVOCATORIA_ESTADO_APROBADO.equals(convocatoria.getEstadoConvocatoria())){
						if (Constantes.PERFIL_ADMINISTRADOR.equals(perfil) || Constantes.PUBLICA_CONVOCATORIAS.equals(publica) ){%>
							<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm ">
								<div class="pae-box-buttons pd-top-3">
									<input type="submit" name="accion" value="Publicar" onclick="return comprobarPublicar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
								</div>
							</div>
						<%}%>
						<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm ">
							<div class="pae-box-buttons pd-top-3">
								<input type="submit" name="accion" value="Reconfigurar" onclick="return comprobarReconfigurar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
							</div>
						</div>
					<%}else{
					if(Constantes.CONVOCATORIA_ESTADO_PUBLICADO.equals(convocatoria.getEstadoConvocatoria()) || Constantes.CONVOCATORIA_ESTADO_SUBSANACION.equals(convocatoria.getEstadoConvocatoria())){
						if (Constantes.PERFIL_ADMINISTRADOR.equals(perfil) || Constantes.PERFIL_GESTOR.equals(perfil)){%>
							<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm ">
								<div class="pae-box-buttons pd-top-3">
									<input type="submit" name="accion" value="Desactivar" onclick="return comprobarDesactivar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
								</div>
							</div>
					<%		}
					}else{
						if(Constantes.CONVOCATORIA_ESTADO_CONFIGURACION.equals(convocatoria.getEstadoConvocatoria())){%>
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm ">
						<div class="pae-box-buttons pd-top-3">
							<input type="button" name="accion" value="Modificar" onclick="modificar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
						</div>
					</div>
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm ">
						<div class="pae-box-buttons pd-top-3">
							<input type="submit" name="accion" value="Aprobar" onclick="return comprobarAprobar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
						</div>
					</div>
						<%} else if(Constantes.CONVOCATORIA_ESTADO_FINALIZADA.equals(convocatoria.getEstadoConvocatoria())){%>
							<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm ">
								<div class="pae-box-buttons pd-top-3">
									<input type="button" name="accion" value="Subsanar" onclick="modificar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
								</div>
							</div>
							<% if(convocatoria.getFechaInicioSub() != null && convocatoria.getFechaFinSub() != null){
								SimpleDateFormat formatoFecha= new SimpleDateFormat("dd/MM/yyyy");
								Date fechaini = formatoFecha.parse(convocatoria.getFechaInicioSub());
								Date fechafin = formatoFecha.parse(convocatoria.getFechaFinSub());
								Date today = new Date();	
								if ((DateUtils.isSameDay(today, fechafin) ||  today.before(fechafin)) && (DateUtils.isSameDay(today, fechaini) || today.after(fechaini))) {
								%>
								<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm ">
									<div class="pae-box-buttons pd-top-3">
										<input type="button" name="accion" value="Publicar Subsanar" onclick="publicarSubsanar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
									</div>
								</div>
							<%} } %>
						<%}else{
						if(Constantes.CONVOCATORIA_ESTADO_DESACTIVADO.equals(convocatoria.getEstadoConvocatoria())){%>
											
						<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm ">
							<div class="pae-box-buttons pd-top-3">
								<input type="button" name="accion" value="Modificar" onclick="modificar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
							</div>
						</div>
						<%if (Constantes.PERFIL_ADMINISTRADOR.equals(perfil) || Constantes.PUBLICA_CONVOCATORIAS.equals(publica)){%>						
							<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm ">
								<div class="pae-box-buttons pd-top-3">
									<input type="submit" name="accion" value="Publicar" onclick="return comprobarPublicar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
								</div>
							</div>
					<%
						}
												}
											}
										}							
									}
								  
								%>
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm ">
						<div class="pae-box-buttons pd-top-3">
							<input type="submit" name="accion" value="Documentacion" onclick="openWindowDocumentos()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
						</div>
					</div>
					<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm ">
						<div class="pae-box-buttons pd-top-3">
							<input type="button" name="accion" value="Volver" onclick="volver()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
						</div>
					</div>
					
				</div>
			</fieldset>
		</div>
	</div>
	
	<div data-function="accordion-box" class="pae-box" id="boxProvinciaExamen">
		<div class="pae-box__header">
			<h3 class="pae-box__header--title">
				<spring:message code="field.convocatoria.provinciaExamen"/>
			</h3>
		</div>
		<div class="pae-box__body">
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-12/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell" id="divTable">						
							<table width="100%" id="table">
								<logic:iterate indexId="index" id="registro" name="convocatoria" property="provinciasExamen" >
									<bean:size name="convocatoria" property="provinciasExamen" id="count"/>
									<logic:equal name="count" value="0"><tr></tr></logic:equal>
									<c:choose>
										<c:when test="${(index+1)% 2 == 0}">            								 	
										 	<td width="50%" colspan="2">- <bean:write name="registro"/></td>	
			     								 	</tr>
			     						</c:when>
			         					<c:otherwise>
			         					<c:choose>
			        						<c:when test="${(index+1) == count}">            								 	
			        							<tr>
			        								 <td width="50%" colspan="2">- <bean:write name="registro"/></td>	
			        							</tr>
			        						</c:when>
			         					<c:otherwise>        								 
			         						<tr>
			         						<td width="50%" colspan="2">- <bean:write name="registro" /></td>																					
										</c:otherwise>
										</c:choose>																			
										</c:otherwise>
									</c:choose>
								</logic:iterate>
							</table>
						</div>
					</div>
				</div>
		</div>
	</div>
	
	<div data-function="accordion-box" class="pae-box" id="boxMotivoTasa">
		<div class="pae-box__header">
			<h3 class="pae-box__header--title">
				<spring:message code="field.convocatoria.motivoTasa"/>
			</h3>
		</div>
		<div class="pae-box__body">
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-12/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell" id="divTable">						
							<table width="100%" id="table">
								<logic:iterate indexId="index" id="registro" name="convocatoria" property="motivosExencion" >
									<bean:size name="convocatoria" property="motivosExencion" id="count"/>
									<logic:equal name="count" value="0"><tr></tr></logic:equal>
									<c:choose>
										<c:when test="${(index+1)% 2 == 0}">            								 	
										 	<td width="50%" colspan="2">- <bean:write name="registro"/></td>	
			     								 	</tr>
			     						</c:when>
			         					<c:otherwise>
			         					<c:choose>
			        						<c:when test="${(index+1) == count}">            								 	
			        							<tr>
			        								 <td width="50%" colspan="2">- <bean:write name="registro"/></td>	
			        							</tr>
			        						</c:when>
			         					<c:otherwise>        								 
			         						<tr>
			         						<td width="50%" colspan="2">- <bean:write name="registro" /></td>																					
										</c:otherwise>
										</c:choose>																			
										</c:otherwise>
									</c:choose>
								</logic:iterate>
							</table>
						</div>
					</div>
				</div>
		</div>
	</div>
	<div data-function="accordion-box" class="pae-box" id="boxTitulosExigidos">
		<div class="pae-box__header">
			<h3 class="pae-box__header--title">
				<spring:message code="field.convocatoria.titulosExigidos"/>
			</h3>
		</div>
		<div class="pae-box__body">
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-12/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell" id="divTable">						
							<table width="100%">
									<logic:iterate indexId="index" id="registro" name="convocatoria" property="titulosExigidos" length="longitud">
										<bean:size name="convocatoria" property="titulosExigidos" id="count"/>
										<c:choose>
            								 <c:when test="${(index+1)% 2 == 0}">            								 	
            								 	<td width="50%" colspan="2">- <bean:write name="registro"/></td>	
            								 	</tr>
            								 </c:when>
             								 <c:otherwise>
             								 	<c:choose>
            								 		<c:when test="${(index+1) == count}">            								 	
            								 			<tr>
            								 			<td width="50%" colspan="2">- <bean:write name="registro"/></td>	
            								 			</tr>
            								 		</c:when>
             										<c:otherwise>        								 
             								 			<tr>
             								 			<td width="50%" colspan="2">- <bean:write name="registro" /></td>																					
													</c:otherwise>
 												</c:choose>																			
											</c:otherwise>
 										</c:choose>
									</logic:iterate>
								</table>
						</div>
					</div>
				</div>
		</div>
	</div>
	<div data-function="accordion-box" class="pae-box" id="boxEspecialidades">
		<div class="pae-box__header">
			<h3 class="pae-box__header--title">
				<spring:message code="field.convocatoria.especialidades"/>
			</h3>
		</div>
		<div class="pae-box__body">
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-12/12 pae-u-3/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell" id="divTable">						
							<table width="100%">
									<logic:iterate indexId="index" id="registro" name="convocatoria" property="especialidades" length="longitud">
										<bean:size name="convocatoria" property="especialidades" id="count"/>
										<c:choose>
            								 <c:when test="${(index+1)% 2 == 0}">            								 	
            								 	<td width="50%" colspan="2">- <bean:write name="registro"/></td>	
            								 	</tr>
            								 </c:when>
             								 <c:otherwise>
             								 	<c:choose>
            								 		<c:when test="${(index+1) == count}">            								 	
            								 			<tr>
            								 			<td width="50%" colspan="2">- <bean:write name="registro"/></td>	
            								 			</tr>
            								 		</c:when>
             										<c:otherwise>        								 
             								 			<tr>
             								 			<td width="50%" colspan="2">- <bean:write name="registro" /></td>																					
													</c:otherwise>
 												</c:choose>																			
											</c:otherwise>
 										</c:choose>
									</logic:iterate>
							</table>
						</div>
					</div>
				</div>
		</div>
	</div>
	<c:if test="${convocatoria.otrosTitulos != null && convocatoria.otrosTitulos.size() > 0}">
	<!-- INI - Otros Titulos -->
		<div data-function="accordion-box" class="pae-box" id="boxOtrosTitulos">
			<div class="pae-box__header">
				<h3 class="pae-box__header--title">
					<spring:message code="field.convocatoria.otrosTitulos"/>
				</h3>
			</div>
			<div class="pae-box__body">
					<div class="pae-layout">
						<div class="pae-layout__item pae-u-12/12 pae-u-3/12-lap pae-u-1/1-palm">
							<div class="pae-form__cell" id="divTable">						
								<table width="100%">
										<logic:iterate indexId="index" id="registro" name="convocatoria" property="otrosTitulos" length="longitud">
											<bean:size name="convocatoria" property="otrosTitulos" id="count"/>
											<c:choose>
	            								 <c:when test="${(index+1)% 2 == 0}">            								 	
	            								 	<td width="50%" colspan="2">- <bean:write name="registro"/></td>	
	            								 	</tr>
	            								 </c:when>
	             								 <c:otherwise>
	             								 	<c:choose>
	            								 		<c:when test="${(index+1) == count}">            								 	
	            								 			<tr>
	            								 			<td width="50%" colspan="2">- <bean:write name="registro"/></td>	
	            								 			</tr>
	            								 		</c:when>
	             										<c:otherwise>        								 
	             								 			<tr>
	             								 			<td width="50%" colspan="2">- <bean:write name="registro" /></td>																					
														</c:otherwise>
	 												</c:choose>																			
												</c:otherwise>
	 										</c:choose>
										</logic:iterate>
									</table>
							</div>
						</div>
					</div>
			</div>
		</div>	
	<!-- FIN - Otros Titulos -->	
	</c:if>
	
	<c:if test="${convocatoria.discapacidad != null && convocatoria.discapacidad.size() > 0}">
	<!-- INI - Discapacidad -->
		<div data-function="accordion-box" class="pae-box" id="boxDiscapacidad">
			<div class="pae-box__header">
				<h3 class="pae-box__header--title">
					<spring:message code="field.convocatoria.discapacidad"/>
				</h3>
			</div>
			<div class="pae-box__body">
					<div class="pae-layout">
						<div class="pae-layout__item pae-u-12/12 pae-u-3/12-lap pae-u-1/1-palm">
							<div class="pae-form__cell" id="divTable">						
								<table width="100%">
										<logic:iterate indexId="index" id="registro" name="convocatoria" property="discapacidad" length="longitud">
											<bean:size name="convocatoria" property="discapacidad" id="count"/>
											<c:choose>
	            								 <c:when test="${(index+1)% 2 == 0}">            								 	
	            								 	<td width="50%" colspan="2">- <bean:write name="registro"/></td>	
	            								 	</tr>
	            								 </c:when>
	             								 <c:otherwise>
	             								 	<c:choose>
	            								 		<c:when test="${(index+1) == count}">            								 	
	            								 			<tr>
	            								 			<td width="50%" colspan="2">- <bean:write name="registro"/></td>	
	            								 			</tr>
	            								 		</c:when>
	             										<c:otherwise>        								 
	             								 			<tr>
	             								 			<td width="50%" colspan="2">- <bean:write name="registro" /></td>																					
														</c:otherwise>
	 												</c:choose>																			
												</c:otherwise>
	 										</c:choose>
										</logic:iterate>
									</table>
							</div>
						</div>
					</div>
			</div>
		</div>	
	<!-- FIN - Discapacidad -->	
	</c:if>
	<c:if test="${convocatoria.datosPropios != null && convocatoria.datosPropios.size() > 0}">
	<!-- INI - Datos Propios -->
		<div data-function="accordion-box" class="pae-box" id="boxDiscapacidad">
			<div class="pae-box__header">
				<h3 class="pae-box__header--title">
					<spring:message code="field.convocatoria.datosPropios"/>
				</h3>
			</div>
			<div class="pae-box__body">
					<div class="pae-layout">
						<div class="pae-layout__item pae-u-12/12 pae-u-3/12-lap pae-u-1/1-palm">
							<div class="pae-form__cell" id="divTable">						
								<table width="100%">
										<logic:iterate indexId="index" id="registro" name="convocatoria" property="datosPropios" length="longitud">
											<bean:size name="convocatoria" property="datosPropios" id="count"/>
											<c:choose>
	            								 <c:when test="${(index+1)% 2 == 0}">            								 	
	            								 	<td width="50%" colspan="2">- <bean:write name="registro"/></td>	
	            								 	</tr>
	            								 </c:when>
	             								 <c:otherwise>
	             								 	<c:choose>
	            								 		<c:when test="${(index+1) == count}">            								 	
	            								 			<tr>
	            								 			<td width="50%" colspan="2">- <bean:write name="registro"/></td>	
	            								 			</tr>
	            								 		</c:when>
	             										<c:otherwise>        								 
	             								 			<tr>
	             								 			<td width="50%" colspan="2">- <bean:write name="registro" /></td>																					
														</c:otherwise>
	 												</c:choose>																			
												</c:otherwise>
	 										</c:choose>
										</logic:iterate>
									</table>
							</div>
						</div>
					</div>
			</div>
		</div>	
	<!-- FIN - Datos Propios -->	
	</c:if>	
	</div>
	<div data-function="accordion-box" class="pae-box" id="boxHistorico">
		<div class="pae-box__header">
			<h3 class="pae-box__header--title">
				<spring:message code="field.convocatoria.historico"/>
			</h3>
		</div>
		<div>
			<table id="paetable">
				<tbody>
					<thead class="pae-table__header">
						<tr class="pae-table__row">											
							<th class="pae-table__cell-header">
								<table class="tabla_resultadosInterno">
									<tbody>
										<tr>											
											<th>
												<div class="titulo_izq_tabla">									
													<span class="pae-table__txt-title">
														<spring:message code="field.convocatoria.detalle.logConvocatoria.nombre"/>
													</span>	
												</div>
											</th>
										</tr>
									</tbody>
								</table>
							</th>
							<th class="pae-table__cell-header">
								<table class="tabla_resultadosInterno">
									<tbody>
										<tr>											
											<th>
												<div class="titulo_izq_tabla">									
													<span class="pae-table__txt-title">
														<spring:message code="field.convocatoria.detalle.logConvocatoria.primerApellido"/>
													</span>	
												</div>
											</th>
										</tr>
									</tbody>
								</table>
							</th>
							<th class="pae-table__cell-header">
								<table class="tabla_resultadosInterno">
									<tbody>
										<tr>											
											<th>
												<div class="titulo_izq_tabla">									
													<span class="pae-table__txt-title">
														<spring:message code="field.convocatoria.detalle.logConvocatoria.segundoApellido"/>
													</span>	
												</div>
											</th>
										</tr>
									</tbody>
								</table>
							</th>
							<th class="pae-table__cell-header">
								<table class="tabla_resultadosInterno">
									<tbody>
										<tr>											
											<th>
												<div class="titulo_izq_tabla">									
													<span class="pae-table__txt-title">
														<spring:message code="field.convocatoria.detalle.logConvocatoria.correoElectronico"/>
													</span>	
												</div>
											</th>
										</tr>
									</tbody>
								</table>
							</th>
							<th class="pae-table__cell-header">
								<table class="tabla_resultadosInterno">
									<tbody>
										<tr>											
											<th>
												<div class="titulo_izq_tabla">									
													<span class="pae-table__txt-title">
														<spring:message code="field.convocatoria.detalle.logConvocatoria.telefono"/>
													</span>	
												</div>
											</th>
										</tr>
									</tbody>
								</table>
							</th>
							<th class="pae-table__cell-header">
								<table class="tabla_resultadosInterno">
									<tbody>
										<tr>											
											<th>
												<div class="titulo_izq_tabla">									
													<span class="pae-table__txt-title">
														<spring:message code="field.convocatoria.detalle.logConvocatoria.fecha"/>
													</span>	
												</div>
											</th>
										</tr>
									</tbody>
								</table>
							</th>
							<th class="pae-table__cell-header">
								<table class="tabla_resultadosInterno">
									<tbody>
										<tr>											
											<th>
												<div class="titulo_izq_tabla">									
													<span class="pae-table__txt-title">
														<spring:message code="field.convocatoria.detalle.logConvocatoria.estadoAnterior"/>
													</span>	
												</div>
											</th>
										</tr>
									</tbody>
								</table>
							</th>
							<th class="pae-table__cell-header">
								<table class="tabla_resultadosInterno">
									<tbody>
										<tr>											
											<th>
												<div class="titulo_izq_tabla">									
													<span class="pae-table__txt-title">
														<spring:message code="field.convocatoria.detalle.logConvocatoria.estadoFinal"/>
													</span>	
												</div>
											</th>
										</tr>
									</tbody>
								</table>
							</th>		
						</tr>
					</thead>
					<tbody class="pae-table__body">
						<logic:iterate indexId="index" id="registro" name="convocatoria" property="logConvocatorias" length="longitud">
							<bean:size name="convocatoria" property="logConvocatorias" id="count"/>
							<tr class="pae-table__row" name="row">
								<td data-content="" data-col="col5" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
									<bean:write name="registro" property="usuario.nombre"/>
								</td>
								<td data-content="" data-col="col5" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
									<bean:write name="registro" property="usuario.primerApellido"/>
								</td>
								<td data-content="" data-col="col5" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
									<bean:write name="registro" property="usuario.segundoApellido"/>
								</td>
								<td data-content="" data-col="col5" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
									<bean:write name="registro" property="usuario.email"/>
								</td>
								<td data-content="" data-col="col5" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
									<bean:write name="registro" property="usuario.telefono"/>
								</td>
								<td data-content="" data-col="col5" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
									<bean:write name="registro" property="fecha" format="dd/MM/yyyy"/>
								</td>
								<td data-content="" data-col="col5" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
								${registro.estadoConvocatoriaByIdEstadoAnterior.descripcion}
								</td>
								<td data-content="" data-col="col5" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
								<c:choose>
									<c:when test="${registro.tipoOperacion eq 'A'.charAt(0)}">
										ALTA
									</c:when>
									<c:when test="${registro.tipoOperacion eq 'M'.charAt(0)}">
										MODIFICACIÓN
									</c:when>
									<c:otherwise>
										${registro.estadoConvocatoriaByIdEstadoActual.descripcion}
									</c:otherwise>
								</c:choose>
								</td>
							</tr>
						</logic:iterate>
					</tbody>
			</table>
		</div>
	
	</div>	
	
				
		
				
		
</form:form>
</body>
</html>