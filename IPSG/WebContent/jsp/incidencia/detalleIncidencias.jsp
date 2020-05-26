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
<%
String numPag = (String)request.getAttribute("paginaActual");%>

<script type="text/javascript">

function llamada(){
	document.forms[1].action="../spring/buscarIncidencias?menu=S";
	document.forms[1].submit();
}




</script>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 
<title></title>
</head>

<body style="margin-left:0.4em;"> 
<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">

<form:form commandName="incidenciasForm" action="/IPSG/spring/detalleIncidencias" id="formPadre" method="post">
	
	<logic:present name="org.apache.spring.ERROR">
		<div id="error">
			<html:errors/>
		</div>
		<div class="clear"></div>
	</logic:present>
	
	
	<h1 class="pae-title"> <spring:message code="field.incidencias.titulo.detalle"/></h1>  
	
	
	<div data-function="accordion-box" class="pae-box">	
		<div class="pae-box__body">
			<fieldset>
				<%-- INI DESC --%>
				<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">		
							<spring:message code="field.incidencias.id"/>
						</span>
						<form:input type="text" path="id" readonly="true"  id="descripcion" class="pae-form__input" />
					</div>
				</div>			
				<%-- FIN DESC --%>
				<%-- INI NIF --%>
				<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">	
							<spring:message code="field.incidencias.nif"/>
						</span>
						<form:input type="text" path="nif" readonly="true" id="descripcion" class="pae-form__input" maxlength="10"/>
					</div>
				</div>
				<%-- FIN NIF --%>			  
				<%-- INI NOMBRE --%>
				<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">	
							<spring:message code="field.incidencias.nombre"/> 
						</span>
						<form:input type="text" path="nombre" readonly="true" class="pae-form__input" maxlength="50"/>
					</div>
				</div>
				<%-- FIN NOMBRE --%>		
				<%-- INI FECHA --%>
				<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">	
							<spring:message code="field.incidencias.fecha"/>
						</span>
					<form:input type="text" path="fecha" data-function="function-datepicker" 
						placeholder="dd/mm/aaaa" class="pae-form__input" style="fecha" maxlength="10"/>
					</div>
				</div>
				<%-- FIN FECHA --%>	
				<%-- INI APELLIDO1 --%>
				<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">	
							<spring:message code="field.incidencias.primerApellido"/> 
						</span>
						<form:input type="text" path="primerApellido" readonly="true" class="pae-form__input" maxlength="50"/>
					</div>
				</div>
				<%-- FIN APELLIDO1 --%>		
				<%-- INI APELLIDO2 --%>
				<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">	
							<spring:message code="field.incidencias.segundoApellido"/>
						</span>
						<form:input type="text" path="segundoApellido" readonly="true" class="pae-form__input" maxlength="50"/>
					</div>
				</div>
				<%-- FIN APELLIDO2 --%>		
				<%-- INI EMAIL --%>
				<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">	
							<spring:message code="field.incidencias.email"/>
						</span>
						<form:input type="text" path="email" readonly="true" class="pae-form__input" maxlength="50"/>
					</div>
				</div>
				<%-- FIN EMAIL --%>		
				<%-- INI TELF --%>
				<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">	
							<spring:message code="field.incidencias.telefono"/>
						</span>
						<form:input type="text" path="telefono" readonly="true" class="pae-form__input" maxlength="10"/>
					</div>
				</div>
				<%-- FIN TELF --%>	
				<%-- INI MENSAJE --%>
				<div class="pae-layout__item pae-u-6/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">	
							<spring:message code="field.incidencias.mensaje"/>
						</span>
						<form:textarea path="mensaje" readonly="true" rows="20" cols="100" class="pae-form__input" style="overflow:hidden"/>
					</div>
				</div>
				<%-- FIN MENSAJE --%>					
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-80">
						<div class="pae-box-buttons">
							<input type="submit" value="Volver" name="accion" onclick="llamada()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
						</div>
					</div>
				</div>
			</fieldset>
		</div>
	</div>
	
		
</form:form>
	
</div>
</body>
</html>