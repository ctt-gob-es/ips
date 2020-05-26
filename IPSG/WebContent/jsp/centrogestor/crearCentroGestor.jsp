<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean"%>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html"%>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/Datepicker_calendario.js"></script>
<script type="text/javascript">

function changePerfil(perfil){
	//alert("Perfil: "+perfil.value);
	if (perfil.value == '4' || perfil.value=='2'){//administrador o soporte no se asocian a un centro gestor
		document.getElementById("idCentroGestor").value = "";
		document.getElementById("idCentroGestor").disabled = true;
		
		document.getElementById("publicaConvocatorias").disabled=true;
		document.getElementById("recibeCorreosIncidencias").disabled=true;
		
		if(perfil.value == '4'){//administrador
			document.getElementById("publicaConvocatorias").checked = true;			
			document.getElementById("recibeCorreosIncidencias").checked = true;
		}else{
			document.getElementById("publicaConvocatorias").checked = false;
			document.getElementById("recibeCorreosIncidencias").checked = false;
			
		}
	}else {
		document.getElementById("idCentroGestor").disabled = false;
		document.getElementById("publicaConvocatorias").checked = false;
		document.getElementById("recibeCorreosIncidencias").checked = false;
		
		if(perfil.value == '3'){//gestor
			document.getElementById("publicaConvocatorias").disabled=false;
			document.getElementById("recibeCorreosIncidencias").disabled=false;
		}else{
			document.getElementById("publicaConvocatorias").disabled=true;
			document.getElementById("recibeCorreosIncidencias").disabled=true;
		}
	}
}
/*
function limpiar(){
	document.getElementById("idMinisterio").value="";
	document.getElementById("siglas").value="";
	document.getElementById("descripcion").value="";
	document.getElementById("codigo").value="";
	document.getElementById("ejercicio").value="";
	document.getElementById("idCentroGestorSust").selectedIndex = 0;
}*/
function cancelar(){
	window.location.href = "../spring/buscarCentroGestor";
}

function marcar(){
	document.getElementById("visibilidad").checked= true;
}
</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body style="margin-left: 0.4em;" onload="marcar()">

<div class="capaAll">
		
	<h1 class="pae-title"> <spring:message code="field.centroGestor.tituloCrearCentroGestor" /> </h1>
	<logic:present name="org.apache.spring.ERROR">
		<div id="error">
			<html:errors />
		</div>
		<div class="clear"></div>
	</logic:present>
<!-- 	<div style="margin-left: 1em; margin-right: 1em; float: left; width: 99%;"> -->
	<div data-function="accordion-box" class="pae-box">
		<form:form commandName="centroGestorForm" action="/IPSG/spring/crearCentroGestor" id="formPadre" method="post">

			<logic:present name="centroGestor" scope="request">

				<form:hidden path="id" name="centroGestor"  />
			</logic:present>

		<div class="pae-box__body">
			<fieldset>
			<div class="pae-layout">
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
						<div class="pae-form__cell">		
				<!-- Ini Ministerio-->
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.centroGestor.ministerioMay" />
							<span class="obligatorio"> *</span>
						</span>
						<form:select path="idMinisterio" class="pae-form__input" id="idMinisterio">
							<option value=""></option>
							<logic:present name="ministerio" scope="request">
								<bean:size id="numMinisterio" name="ministerio" scope="request" />
								<logic:greaterThan name="ministerio" value="0">
									<form:options items="${ministerio}"  itemLabel="descripcion" itemValue="id"/>	
								</logic:greaterThan>
							</logic:present>
						</form:select>
						</div>
					</div>
				<!-- Fin Ministerio-->
				<!-- Ini Siglas-->
				<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
					<div class="pae-form__cell">	
						<span class="pae-form__label pae-form__span-label">
							<spring:message code="field.centroGestor.siglasMay" />
						<span class="obligatorio"> *</span>
						</span>
						<form:input type="text" path="siglas" class="pae-form__input" maxlength="20" />
					</div>
				</div>
				<!-- Fin Siglas-->
				<!-- Ini Descripción-->
				<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">	
							<spring:message code="field.descripcionMay" />
							<span class="obligatorio"> *</span>
						</span>
						<form:input type="text" path="descripcion" class="pae-form__input"
							maxlength="100" />
					</div>
				</div>
				<!-- Fin Descripción-->		
			<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7"">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">	
						<spring:message code="field.ejercicioMay" />
					<span class="obligatorio"> *</span>
					</span>
					<form:input type="text" path="ejercicio" class="pae-form__input" maxlength="4" />
				</div>
				</div>
				<!-- Fin Descripción-->
			</div>	
			<div class="pae-layout">				
				<!-- Ini Código-->
				<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">	
							<spring:message code="field.codigoMay" />
							<span class="obligatorio"> *</span>
						</span>
						<form:input type="text" path="codigo" class="pae-form__input"  maxlength="4" />
					</div>	
				</div>
				<!-- Fin Código -->
				<!-- Modelo Convocatoria -->
				<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
					<div class="pae-form__cell">
						<logic:present name="modelos" scope="request">
							<bean:size name="modelos" id="numModelo" scope="request" />
							<logic:greaterThan name="numModelo" value="0">
								<span class="pae-form__label pae-form__span-label">	
									<spring:message code="field.modeloMayConv" />
									<span class="obligatorio"> *</span>
								</span>
								<form:select path="idModelo" class="pae-form__input" id="idModelo">
								<form:option value=""></form:option>
								<form:options items="${modelos}"  itemLabel="numModelo" itemValue="id"/>	
								</form:select>
							</logic:greaterThan>
						</logic:present>
					</div>
				</div>
				<!-- Fin Modelo Convocatoria -->	
				<!-- Ini Centro Gestor Sustituido-->
			<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">		
						<spring:message code="field.centroGestor.centroGestorSustMay" />
					</span>
						<form:select path="idCentroGestorSust" class="pae-form__input"
							id="idCentroGestorSust">
							<form:option value=""></form:option>
							<logic:present name="centroGestorLista" scope="request">
								<bean:size id="numCentroGestor" name="centroGestorLista"
									scope="request" />
								<logic:greaterThan name="numCentroGestor" value="0">
									<form:options items="${centroGestorLista}" itemLabel="descripcion" itemValue="id"/>		
								</logic:greaterThan>
							</logic:present>
						</form:select>
					</div>
				</div>
				<!-- Fin Centro Gestor Sustituido-->
				<!-- Ini Fecha Sustitución-->
				<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">		
						<spring:message code="field.fechaSustitucionMay" />
					</span>
						<form:input type="text" path="fechaSustitucion" id="fechaSustitucion"  data-function="function-datepicker" 
							placeholder="dd/mm/aaaa" class="pae-form__input" style="fechaDesde" maxlength="10"/>		
						
						<!-- 			<img type="image" src="../images/calendar/boton-calendario.gif" style="cursor: pointer;" alt="Abrir Calendario"  onclick="javascript: popUpCalendar(this, document.getElementById('fechaSustitucion'), 'dd/mm/yyyy',2); "/> -->
<!-- 						<script type="text/javascript"> -->
<%-- 							fncCalendario('fechaSustitucion', '<%=request.getContextPath()%>'); --%>
<!-- 						</script> -->
					</div>
				</div>
				<!-- Fin fecha Sustitución-->
	</div>
				<!-- Ini Visibilidad-->
	<div class="pae-layout">
		<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-check">
			<div class="pae-form__cell">
				<input type="checkbox" name="visibilidad" property="visibilidad" id="visibilidad" data-function="checkbox-custom-input" class="pae-form__custom-check" checked="yes">
				<label for="visibilidad" data-function="checkbox-custom" class="pae-form__custom-check-label pae-form__custom-check-label--option">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.general.visibilidadTitulo"/> 
					</span>
				</label>
			</div>
		</div>
				<!-- Fin Visibilidad-->
				<!-- Ini Fecha Inicio Inahbil-->
		<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
			<div class="pae-form__cell">
				<span class="pae-form__label pae-form__span-label">		
					<spring:message code="field.fechaInicioInhabil" />
				</span>
						<form:input type="text" path="fechaInicioInhabil" id="fechaInicioInhabil"  data-function="function-datepicker" 
					placeholder="dd/mm/aaaa" class="pae-form__input" style="fechaDesde" maxlength="10"/>	
				
<!-- 						<script type="text/javascript"> -->
<%-- 							fncCalendario('fechaInicioInhabil', '<%=request.getContextPath()%>'); --%>
<!-- 						</script> -->
					
			</div>
		</div>
				<!-- Fin Fecha Inicio Inahbil-->
			
				<!-- Ini Fecha Fin Inahbil-->
		<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
			<div class="pae-form__cell">
				<span class="pae-form__label pae-form__span-label">		
						<spring:message code="field.fechaFinInhabil" />
				</span>
				<form:input type="text" path="fechaFinInhabil" id="fechaFinInhabil"  data-function="function-datepicker" 
					placeholder="dd/mm/aaaa" class="pae-form__input" style="fechaDesde" maxlength="10"/>	
				
<!-- 						<script type="text/javascript"> -->
<%-- 							fncCalendario('fechaFinInhabil', '<%=request.getContextPath()%>'); --%>
<!-- 						</script> -->
			</div>
		</div>
</div>
	
	
 		<div class="filaBtn">
		<div class="pae-box-buttons">
				<input type="submit" name="accion" value="Guardar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>
				<!--<input type="button" name="submit" value="Limpiar" onclick="limpiar()" class="boton"/>-->
				<input type="button" name="submit" value="Cancelar" onclick="cancelar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline mg-left-1"/>	
			</div>
		</div>	

	</fieldset>	
</div>		

</form:form>
</div>
</div>
</body>
</html>


