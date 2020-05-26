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

  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-bootstrap.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/selectize/dist/css/selectize.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/font-awesome/css/font-awesome.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/bower_components/jquery-ui/themes/smoothness/jquery-ui.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-general.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-modules.css"/>

<%
String numPag = (String)request.getAttribute("paginaActual");
String scroll = (String)request.getAttribute("scroll");
String idSolicitud = (String)request.getAttribute("idSolicitud");
%>

<script type="text/javascript">

var idSolicitudAux = "<%=idSolicitud%>";

function comprobarEstado(idEstadoSolicitud)
{	

	if (idEstadoSolicitud == 1 || idEstadoSolicitud == 6 || idEstadoSolicitud == 7 || idEstadoSolicitud == 2 || idEstadoSolicitud == 5 ){
		elemento = document.getElementById("REC");
		elemento.style.visibility = "hidden";
	}
	if(idEstadoSolicitud == 1 ||  idEstadoSolicitud == 6)
	{
		elemento = document.getElementById("AEAT");
		elemento.style.visibility = "hidden";
	}
}

function verificarRec(){
	var idSolicitud = document.getElementById("idSolicitud").value;
	window.location.href = "../spring/verificarRegistroRec?solicitud="+idSolicitud; 
	
}

function verificarPago(){
	var idSolicitud = document.getElementById("idSolicitud").value;
	window.location.href = "../spring/verificarPagoRec?solicitud="+idSolicitud; 
	
}

function volver(){
	var idSolicitud = idSolicitudAux;
	window.location = "../spring/verActualizarEstadoSolicitud?solicitud="+idSolicitud+"&scroll="+document.body.scrollTop;
}

</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body class="background-color-white-ip">
<body class="pae-body2" style="overflow-y:hidden" onload="comprobarEstado(${solicitud.idEstadoSolicitud})">
<div class="pae-title2"><spring:message code="field.estadoSolicitud.actualizar"/></div>

	<logic:present name="org.apache.spring.ERROR">
		<logic:notPresent name="errors">
			<div id="error">
				<html:errors/>
				<br>
			</div>
			<div class="clear"></div>
		</logic:notPresent>
	</logic:present>

<logic:notPresent name="errors">
<logic:notPresent name="actualizado">	
	<form:form modelAttribute="actualizarEstadoSolicitudForm" action="/IPSG/spring/actualizarEstadoSolicitud" id="formHijo" method="post">
		<form:hidden path="submit" id="submit" value="<%=scroll%>"/>		
		<form:hidden path="idSolicitud" value="${solicitud.id}" id="idSolicitud"/>
	
	
	<%-- MAQUETACION INI --%>
	<div class="pae-box">
		<div class="pae-box__header2">	
			<fieldset style="border: none;">
				<div class="pae-layout">
						<%-- Estado Act --%>
						<div class="pae-layout__item">
							<div class="pae-form__cell">
								<span class="pae-form__label pae-form__span-label">
									<spring:message code="field.estadoSolicitud.estadoActual.may"/>
								</span>
								<logic:present name="solicitud">
									<logic:equal name="solicitud" property="idEstadoSolicitud" value="1">
										<form:input path="estadoActual" id="estadoActual" class="pae-form__input pae-box--transparent" readonly="true" value="NO PAGADA"/>
									</logic:equal>
									<logic:equal name="solicitud" property="idEstadoSolicitud" value="2">
										<form:input path="estadoActual" id="estadoActual" class="pae-form__input pae-box--transparent" readonly="true" value="NO REGISTRADA"/>
									</logic:equal>
									<logic:equal name="solicitud" property="idEstadoSolicitud" value="5">
										<form:input path="estadoActual" id="estadoActual" class="pae-form__input pae-box--transparent" readonly="true" value="PENDIENTE REGISTRO"/>
									</logic:equal>
									<logic:equal name="solicitud" property="idEstadoSolicitud" value="6">
										<form:input path="estadoActual" id="estadoActual" class="pae-form__input pae-box--transparent" readonly="true" value="PROCESO DE PAGO"/>
									</logic:equal>
									<logic:equal name="solicitud" property="idEstadoSolicitud" value="7">
										<form:input path="estadoActual" id="estadoActual" class="pae-form__input pae-box--transparent" readonly="true" value="PROCESO DE REGISTRO"/>
									</logic:equal>
								</logic:present>				
							</div>
						</div>
						<%-- Estado Act --%>
					</div>
					<div class="pae-layout">	
						<%-- Estado Nuevo --%>
						<div class="pae-layout__item">
							<div class="pae-form__cell">
								<span class="pae-form__label pae-form__span-label">
									<spring:message code="field.estadoSolicitud.nuevoEstado.may"/>
								</span>
								<!-- si el estado actual es No Pagado -->
								<logic:equal name="solicitud" property="idEstadoSolicitud" value="1">
									<form:select path="idNuevoEstado" class="pae-form__input" id="idNuevoEstado" >						
										<option value="2">No Registrado</option>
									</form:select>
								</logic:equal> 
								
								<!-- si el estado actual es Proceso de Pago -->
								<logic:equal name="solicitud" property="idEstadoSolicitud" value="6">
									<form:select path="idNuevoEstado" class="pae-form__input" id="idNuevoEstado" >						
										<option value="1">No Pagado</option>
										<option value="2">No Registrado</option>
									</form:select>
								</logic:equal> 
								
								<!-- si el estado actual es No Registrada -->
								<logic:equal name="solicitud" property="idEstadoSolicitud" value="2">
									<form:select path="idNuevoEstado" class="pae-form__input" id="idNuevoEstado" >						
										<option value="3">Registrado</option>
									</form:select>
								</logic:equal>
								
								<!-- si el estado actual es Pendiente Registro -->
								<logic:equal name="solicitud" property="idEstadoSolicitud" value="5">
									<form:select path="idNuevoEstado" class="pae-form__input" id="idNuevoEstado" >
										<option value="2">No Registrado</option>
										<option value="3">Registrado</option>
									</form:select>
								</logic:equal>  
								
								<!-- si el estado actual es Proceso Registro -->
								<logic:equal name="solicitud" property="idEstadoSolicitud" value="7">
									<form:select path="idNuevoEstado" class="pae-form__input" id="idNuevoEstado" >						
										<option value="2">No Registrado</option>
										<option value="3">Registrado</option>
									</form:select>
								</logic:equal> 			
							</div>
						</div>
						<%-- Estado Nuevo --%>	
						</div>	
						<div class="pae-layout">
							<div class="pae-layout__item">	
							</div>
						</div>					
						<%-- Boton --%>
						<div class="pae-layout">
							<div class="pae-layout__item">
								<div class="pae-box-buttons">
									<input type="submit" name="submit" value="Actualizar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline  pae-separation"/>
									<input type="button" value="Verificar Pago AEAT" id="AEAT" name="submit" onclick="verificarPago()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>
									<input type="button" value="Verificar Registro REC" name="submit" onclick="verificarRec()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"" id="REC"/>		
								</div>
							</div>
						</div>								
					</div>
				</fieldset>
			</div>	
		</div>
	</div>
	<%-- MAQUETACION FIN --%>		
	
	
	
	
	
	</form:form>
	</logic:notPresent>
	<logic:present name="actualizado">
		<center>
		<logic:present name="errors">
			<font color="#c33400"><bean:write name="errors"/></font>
		</logic:present>
		<logic:present name="actualizarEstadoBean" scope="request">
			<div>
				<div>
					<div class="labelIzq">
						<spring:message code="field.consultarRegistrosRec.nJustificante.mayus"/>
					</div>
					<div class="contenidoDer"> 
						<logic:present name="actualizarEstadoBean" scope="request">
							<bean:write name="actualizarEstadoBean" property="numJustificante"/>
						</logic:present>
					</div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
				<div>
					<div class="labelIzq">
						<spring:message code="field.consultarRegistrosRec.numRegistro.mayus"/>
					</div>
					<div class="contenidoDer"> 
						<logic:present name="actualizarEstadoBean" scope="request">
							<bean:write name="actualizarEstadoBean" property="numRegistro"/>
						</logic:present>
					</div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
				<br>
				<div>
					<div class="labelIzq">
						<spring:message code="field.consultarRegistrosRec.fechaRegistro.mayus"/>
					</div>
					<div class="contenidoDer"> 
						<logic:present name="actualizarEstadoBean" scope="request">
							<bean:write name="actualizarEstadoBean" property="fecha"/>
						</logic:present>
					</div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
				<br>
				<div>
					<br><br><br>
					<bean:write name="actualizarEstadoBean" property="mensaje"/>
				</div>
			</div>
		</logic:present>
		<logic:present name="actualizarEstadoPagoBean" scope="request">
			<div>
				<div>
					<div class="labelIzq">
						<spring:message code="field.consultarRegistrosRec.nJustificante.mayus"/>
					</div>
					<div class="contenidoDer"> 
						<logic:present name="actualizarEstadoPagoBean" scope="request">
							<bean:write name="actualizarEstadoPagoBean" property="numJustificante"/>
						</logic:present>
					</div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>
				<div>
					<div class="labelIzq">
						<spring:message code="field.consultarRegistrosRec.nrc.mayus"/>
					</div>
					<div class="contenidoDer"> 
						<logic:present name="actualizarEstadoPagoBean" scope="request">
							<bean:write name="actualizarEstadoPagoBean" property="nrc"/>
						</logic:present>
					</div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>				
				<br>
				<div>
					<div class="labelIzq">
						<spring:message code="field.consultarRegistrosRec.fechaRegistro.mayus"/>
					</div>
					<div class="contenidoDer"> 
						<logic:present name="actualizarEstadoPagoBean" scope="request">
							<bean:write name="actualizarEstadoPagoBean" property="fecha"/>
						</logic:present>
					</div>
					<div class="clear"></div>
				</div>
				<div class="clear"></div>				
				<br>
				<div>
					<br><br><br>
					<bean:write name="actualizarEstadoPagoBean" property="mensaje"/>
				</div>
			</div>
		</logic:present>
		</center>
	</logic:present>
</logic:notPresent>
<logic:present name="errors">
	<font color="#c33400"><bean:write name="errors"/></font>
	<br><br><br>	
	<div class="clear"></div>
	<div align="center">		
		<input type="button" value="Volver" name="submit" onclick="volver()"  class="boton" id="submit" />		
		<input type="button" value="Cerrar" name="accion" onclick="javascript: window.close()" class="boton" />		
	</div>			
</logic:present>
</body>
</html>