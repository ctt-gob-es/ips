<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="bean" uri="http://www.mpr.es/ipsg/bean-tags" %>
<%@ taglib prefix="logic" uri="http://www.mpr.es/ipsg/logic-tags" %>
<%@ taglib prefix="html" uri="http://www.mpr.es/ipsg/html-tags" %>

<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@page import="es.map.ipsg.util.Constantes"%>


<%String sPerfilUsuario =  request.getAttribute("sPerfilUsuario").toString();
String numPag = (String) request.getAttribute("paginaActual");
%>

<script type="text/javascript">


function exportarVerificar(opcion)
{
	if(opcion == 'Exportar')
	{
		document.getElementById("accion").value="Exportar";
		document.forms[1].action = "../spring/exportarExcelUsuarios";
	}
}

function irPagina(pagTotales)
{
	var actual = document.getElementById("numeroPaginaIr").value;
	var actualInt = parseInt(actual,10);
	
	if(isNaN(actualInt) || actualInt<=0){
		actualInt = 1;
	}
	
	document.getElementById("pulsaIr").value = true;	
	document.getElementById("accion").value="Paginar";
	document.getElementById("numeroPaginaIr").value = actualInt;
	document.getElementById("paginaActual").value=actualInt;
	document.getElementById("paginasTotales").value=pagTotales;
	document.forms[0].action = "../spring/buscarUsuarios";
	document.forms[1].action = "../spring/buscarUsuarios";
}

function openWindowCentroGestor() {
	var param = "idCentroGestor";
	var param2 = "dsCentroGestor";
	var extract = new Object();
		ventanaPopup = window.open("../spring/listarCentroGestor?parametro="+param+"&parametro2="+param2, 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
	return false;
} 


function ordena(value,numPag){
	if(value==1){
		document.getElementById("campo").value = "1";
		document.getElementById("direccion").value="up";
	}else if(value==2){
		document.getElementById("campo").value = "1";
		document.getElementById("direccion").value="down";
	}else if(value==3){
		document.getElementById("campo").value = "2";
		document.getElementById("direccion").value="up";
	}else if(value==4){
		document.getElementById("campo").value = "2";
		document.getElementById("direccion").value="down";
	}else if(value==5){
		document.getElementById("campo").value = "3";
		document.getElementById("direccion").value="up";
	}else if(value==6){
		document.getElementById("campo").value = "3";
		document.getElementById("direccion").value="down";
	}else if(value==7){
		document.getElementById("campo").value = "4";
		document.getElementById("direccion").value="up";
	}else if(value==8){
		document.getElementById("campo").value = "4";
		document.getElementById("direccion").value="down";
	}else if(value==9){
		document.getElementById("campo").value = "5";
		document.getElementById("direccion").value="up";
	}else if(value==10){
		document.getElementById("campo").value = "5";
		document.getElementById("direccion").value="down";
	}else if(value==11){
		document.getElementById("campo").value = "6";
		document.getElementById("direccion").value="up";
	}else if(value==12){
		document.getElementById("campo").value = "6";
		document.getElementById("direccion").value="down";
	}else if(value==13){
		document.getElementById("campo").value = "7";
		document.getElementById("direccion").value="up";
	}else if(value==14){
		document.getElementById("campo").value = "7";
		document.getElementById("direccion").value="down";
	}else if(value==15){
		document.getElementById("campo").value = "8";
		document.getElementById("direccion").value="up";
	}else if(value==16){
		document.getElementById("campo").value = "8";
		document.getElementById("direccion").value="down";
	}else if(value==17){//Para el estado se cambia el orden porque en la bd 0=Inactivo y 1=Activo
		document.getElementById("campo").value = "9";
		document.getElementById("direccion").value="down";
	}else if(value==18){//Para el estado se cambia el orden porque en la bd 0=Inactivo y 1=Activo
		document.getElementById("campo").value = "9";
		document.getElementById("direccion").value="up";
	}
	document.getElementById("paginaActual").value=numPag;
	document.getElementById("accion").value="Ordenar";
	document.forms[1].action = "../spring/buscarUsuarios";
}

function busqueda(){
	document.getElementById("accion").value="Buscar";
	document.getElementById("cambios").value="Correcto";
	document.forms[1].action = "../spring/buscarUsuarios";
}

function validarNif(){
	var identificacion = document.getElementById("nif").value;
	if(identificacion != ""){
		result = nifValidate(identificacion);
		if (result == false) {		
			alert(unescape("El NIF es incorrecto."));
			return false;
		}
	}	
}
function nifValidate(nif) {
	if (!nif)
		return false;

	if (nif.length == 9) {
		nif = nif.toUpperCase();
		if (/[0-9]{8}[ABCDEFGHJKLMNPQRSTVWXYZ]/.test(nif)) {
			var temp = 'TRWAGMYFPDXBNJZSQVHLCKET';
			var posicion_letra = nif.substring(0, 8) % 23;
			if (nif.charAt(8) != temp.charAt(posicion_letra)) {
				return false;
			}
		} else {
			if (/[KML][0-9]{7}[ABCDEFGHJKLMNPQRSTVWXYZ]/.test(nif)){
				var temp = 'TRWAGMYFPDXBNJZSQVHLCKET';
				var posicion_letra = nif.substring(1, 8) % 23;
				if (nif.charAt(8) != temp.charAt(posicion_letra)) {
					return false;
				}
			} else {
				return false;
			}
		}
	} else {
		return false;
	}
	return true;
}

function paginaActual(paginaActual){
	document.getElementById("paginaActual").value=paginaActual;
	document.getElementById("accion").value="Paginar";
}

function paginaSiguiente(pag,paginasTotales){
	var next=pag+1;
	var buscar = document.getElementById("cambios").value;
	if("Correcto" == buscar){
		document.getElementById("accion").value="Paginar";
		document.getElementById("paginaActual").value=next;
		document.getElementById("paginasTotales").value=paginasTotales;
	}else{
		document.getElementById("accion").value="Buscar";
		document.getElementById("paginaActual").value=pag;
	}
}

function paginaAnterior(pag,paginasTotales){
	var next=pag-1;
	var buscar = document.getElementById("cambios").value;
	if("Correcto" == buscar){		
		document.getElementById("accion").value="Paginar";
		document.getElementById("paginaActual").value=next;
		document.getElementById("paginasTotales").value=paginasTotales;
	}else{
		document.getElementById("accion").value="Buscar";
		document.getElementById("paginaActual").value=pag;
	}
}

function ultimaPagina(paginasTotales,pag){
	var buscar = document.getElementById("cambios").value;
	if("Correcto" == buscar){
		document.getElementById("accion").value="Paginar";
		document.getElementById("paginaActual").value=paginasTotales;
		document.getElementById("paginasTotales").value=paginasTotales;
	}else{
		document.getElementById("accion").value="Buscar";
		document.getElementById("paginaActual").value=pag;
	}
}

function primeraPagina(paginasTotales,pag){
	var buscar = document.getElementById("cambios").value;
	if("Correcto" == buscar){
		document.getElementById("accion").value="Paginar";
		document.getElementById("paginaActual").value="1";
		document.getElementById("paginasTotales").value=paginasTotales;
	}else{
		document.getElementById("accion").value="Buscar";
		document.getElementById("paginaActual").value=pag;
	}
}
function comprobarEliminar(){
	return confirm('<spring:message code="field.mensajeEliminar"/>');
}
function load(){
	document.getElementById("accion").focus();
}

function comprobarBusqueda(){
	document.getElementById("cambios").value="Incorrecto";
}

function comprobarRegistros(){
	document.forms[0].action = "../spring/buscarUsuarios";
	document.getElementById("accion").value = "Paginar";
	document.getElementById("cambios").value = "Incorrecto";
	document.getElementById("paginaActual").value="1";
	numeroPagina = document.getElementById("paginasTotales").value;
	pagina = document.getElementById("paginaActual").value;
	numRegistro = document.getElementById("numRegistro").value;
	window.location.href = "../spring/buscarUsuarios?paginaActual="+pagina +"&paginasTotales="+numeroPagina +"&numRegistro="+numRegistro+"&submit=Buscar"; 
}

function limpiarCentroGestor(){
	var desCentro = document.getElementById("dsCentroGestor").value;
		if(desCentro != ""){
			document.getElementById("idCentroGestor").value = "";
			document.getElementById("dsCentroGestor").value = "";
		}
	}


</script>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script language='javascript' src="../javascript/utils.js"></script> 
	<title></title>
</head>

<body style="margin-left:0.4em;" >

<form:form modelAttribute="buscarUsuariosForm" action="/IPSG/spring/buscarUsuarios" method="post" onsubmit="return validarNif()">
	<form:hidden path="paginaActual" id="paginaActual"/>
	<form:hidden path="paginasTotales" id="paginasTotales"/>
	<form:hidden path="accion" id="accion"/>
	<form:hidden path="campo" id="campo"/>
	<form:hidden path="direccion" id="direccion"/>
	<form:hidden path="cambios" id="cambios"/>
	<form:hidden path="pulsaIr" id="pulsaIr" />
	
<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
</logic:present>

	<%-- MAQUETACION INI --%>
	<div class="">
		<h1 class="pae-title"><spring:message code="field.usuario.tituloBusquedaUsuarios"/></h1>
		
		<div data-function="accordion-box" class="pae-box">
			<div class="pae-box__header">
				<h3 class="pae-box__header--title">
					<spring:message code="field.textosIntroduccion.usuarios"/>
				</h3>
			</div>
			<div class="pae-box__body">
				<fieldset>
					<div class="pae-layout">
						<%-- Ini Centro Gestor--%>
						<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
							<div class="pae-form__cell">
								<span class="pae-form__label pae-form__span-label">
									<spring:message code="field.centroMay" />
								</span>
								<form:input path="idCentroGestor" class="pae-form__input" readonly="true" onchange="comprobarBusqueda();comprobar()" onclick="limpiarCentroGestor();"/>
					 			<form:input path="dsCentroGestor" readonly="true" class="input_texto_border0 em100"/>					 						
							</div>
						</div>
						<div class="pae-layout__item pae-u-1/12 pae-u-2/12-lap pae-u-1/1-palm search_btn">
							<input type="button" class="pae-form__btn-search" alt="Buscar Centro Gestor" onclick="return openWindowCentroGestor()"/> 
						</div>
						<%-- Fin Centro Gestor--%>
						
						<%-- Perfil --%>
						<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
							<div class="pae-form__cell">
								<span class="pae-form__label pae-form__span-label">
									<spring:message code="field.usuario.perfil.mayus"/>
								</span>
								<form:select path="idPerfil" class="pae-form__input" onchange="comprobarBusqueda()">
									<form:option value=""></form:option>
									<form:options items="${perfiles}" itemValue="id" itemLabel="descripcion" />
								</form:select>
							</div>
						</div>
						<%-- Perfil --%>
						<%-- Estado --%>
						<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
							<div class="pae-form__cell">
								<span class="pae-form__label pae-form__span-label">
									<spring:message code="field.estadoMay"/>
								</span>
								<form:select path="estado" class="pae-form__input" onchange="comprobarBusqueda()">
									<form:option value=""></form:option>
									<form:option value="1"><spring:message code="field.estado.activo.mayus"/></form:option>
									<form:option value="0"><spring:message code="field.estado.inactivo.mayus"/></form:option>
								</form:select>
							</div>
						</div>
						<%-- Estado --%>
					</div>
					<div class="pae-layout">
						
					
						
						<%-- NIF --%>
						<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
							<div class="pae-form__cell">
								<span class="pae-form__label pae-form__span-label">
									<spring:message code="field.usuario.nif.mayus"/>
								</span>
								<form:input path="nif" id="nif" class="pae-form__input" maxlength="9"/>						
							</div>
						</div>
						<%-- NIF --%>
						<%-- EMAIL --%>
						<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
							<div class="pae-form__cell">
								<span class="pae-form__label pae-form__span-label">
									<spring:message code="field.usuario.email.mayus"/>
								</span>
								<form:input path="email" class="pae-form__input" maxlength="50"/>					
							</div>
						</div>
						<%-- EMAIL --%>	
						<%-- LOGIN --%>
						<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
							<div class="pae-form__cell">
								<span class="pae-form__label pae-form__span-label">
									<spring:message code="field.usuario.login.mayus"/>
								</span>
								<form:input id="login" path="login" class="pae-form__input" maxlength="20"/>				
							</div>
						</div>
						<%-- LOGIN --%>	
					</div>
						
						<%-- Boton --%>
					<div class="pae-layout">
						<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-89">
							<div class="pae-box-buttons">
								<input type="submit" value="Buscar" name="buscarUsuarios" onclick="busqueda()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>	
							</div>
						</div>
					</div>
					<%-- Boton --%>																								
					</div>
			</div>
		</div>
	<%-- MAQUETACION FIN --%>
	
	<logic:present name="usuarios" scope="request">
		<bean:size id="numResultados" name="usuarios" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
		
		
			<%
				//Boton de'Exportar a Excel Usuarios'
				if(sPerfilUsuario.equals(Constantes.ROL_ADMINISTRADOR))
				{%>
				
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-85">
						<div class="pae-box-buttons">
							<input type="submit" name="exportar" value="Exportar Excel" onclick="return exportarVerificar('Exportar');" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation">
						</div>
					</div>
				
				
				<%} 
			%> 
		<div class="scrollmenu">
		<table id="paetable" class="pae-table pae-table--margin">
			<caption class="hiddenAccesible">Tabla de solicitudes registradas</caption>
			<thead class="pae-table__header">
			<tr class="pae-table__row">
				<th class="pae-table__cell-header wauto">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.usuario.id.mayus"/>
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ordenar" 
											onclick="ordena(1,<%=numPag %>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Ordenar" onclick="ordena(2,<%=numPag %>)">
									</div>
								</th>
							</tr>
						</tbody>
					</table>
				</th>			
				<th class="pae-table__cell-header wauto">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.usuario.nif.mayus"/>
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image"
													src="../images/order_desc.png" value="accion" alt="Ordenar"
													onclick="ordena(3,<%=numPag %>)"><input type="image"
													src="../images/order_asc.png" value="accion" alt="Ordenar"
													onclick="ordena(4,<%=numPag %>)">
									</div>
								</th>
							</tr>
						</tbody>
					</table>
				</th>			
				<th class="pae-table__cell-header wauto">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.usuario.login.mayus"/>
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image"
													src="../images/order_desc.png" value="accion" alt="Ordenar"
													onclick="ordena(5,<%=numPag %>)"><input type="image"
													src="../images/order_asc.png" value="accion" alt="Ordenar"
													onclick="ordena(6,<%=numPag %>)">
									</div>
								</th>
							</tr>
						</tbody>
					</table>
				</th>
				<th class="pae-table__cell-header wauto">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.usuario.nombre.mayus"/>
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image"
													src="../images/order_desc.png" value="accion" alt="Ordenar"
													onclick="ordena(7,<%=numPag %>)"><input type="image"
													src="../images/order_asc.png" value="accion" alt="Ordenar"
													onclick="ordena(8,<%=numPag %>)">
									</div>
								</th>
							</tr>
						</tbody>
					</table>
				</th>
				<th class="pae-table__cell-header wauto">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.usuario.primerApellido.mayus"/>
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image"
													src="../images/order_desc.png" value="accion" alt="Ordenar"
													onclick="ordena(9,<%=numPag %>)"><input type="image"
													src="../images/order_asc.png" value="accion" alt="Ordenar"
													onclick="ordena(10,<%=numPag %>)">
									</div>
								</th>
							</tr>
						</tbody>
					</table>
				</th>
				<th class="pae-table__cell-header wauto">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.usuario.segundoApellido.mayus"/>
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image"
													src="../images/order_desc.png" value="accion" alt="Ordenar"
													onclick="ordena(11,<%=numPag %>)"><input type="image"
													src="../images/order_asc.png" value="accion" alt="Ordenar"
													onclick="ordena(12,<%=numPag %>)">
									</div>
								</th>
							</tr>
						</tbody>
					</table>
				</th>							
				<th class="pae-table__cell-header wauto">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.centroMay"/>
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image"
													src="../images/order_desc.png" value="accion" alt="Ordenar"
													onclick="ordena(13,<%=numPag %>)"><input type="image"
													src="../images/order_asc.png" value="accion" alt="Ordenar"
													onclick="ordena(14,<%=numPag %>)">
									</div>
								</th>
							</tr>
						</tbody>
					</table>
				</th>			
				<th class="pae-table__cell-header wauto">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.usuario.perfil.mayus"/>
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image"
													src="../images/order_desc.png" value="accion" alt="Ordenar"
													onclick="ordena(15,<%=numPag %>)"><input type="image"
													src="../images/order_asc.png" value="accion" alt="Ordenar"
													onclick="ordena(16,<%=numPag %>)">
									</div>
								</th>
							</tr>
						</tbody>
					</table>
				</th>
				<th class="pae-table__cell-header wauto">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.estadoMay"/>
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image"
													src="../images/order_desc.png" value="accion" alt="Ordenar"
													onclick="ordena(17,<%=numPag %>)"><input type="image"
													src="../images/order_asc.png" value="accion" alt="Ordenar"
													onclick="ordena(18,<%=numPag %>)">
									</div>
								</th>
							</tr>
						</tbody>
					</table>
				</th>
				<%if(!sPerfilUsuario.equals(Constantes.ROL_SOPORTE)){%>
					<th data-col="col3" class="pae-table__cell-header">
						<span class="pae-table__txt-title">
							<spring:message code="field.acciones"/>
						</span>
					</th>
				<%}%>
				
			</tr>
			<logic:iterate id="registro" name="usuarios" >
				<tr class="pae-table__row" name="row">
					<td data-content="" data-col="col5" data-function="fc-collapse-table" class="pae-table__cell-body wauto"><bean:write name="registro" property="id" /></td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
						<a href="${pageContext.request.contextPath}/spring/verModificarUsuario?id=${registro.id}">
							<font color="#c33400"><bean:write name="registro" property="nif" /></font>
						</a>
					</td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body wauto"><bean:write name="registro" property="login" /></td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body wauto"><bean:write name="registro" property="nombre" /></td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body wauto"><bean:write name="registro" property="primerApellido" /></td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body wauto"><bean:write name="registro" property="segundoApellido" /></td>
					<!--INI-TRA042-->
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body wauto"><bean:write name="registro" property="listaCGconcat" /></td>
					<!--FIN-TRA042-->
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body wauto"><bean:write name="registro" property="desPerfil" /></td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
						<logic:equal name="registro" property="estado" value="1"><spring:message code="field.estado.activo"/></logic:equal>
						<logic:equal name="registro" property="estado" value="0"><spring:message code="field.estado.inactivo"/></logic:equal>
					</td>
					<%if(!sPerfilUsuario.equals(Constantes.ROL_SOPORTE)){%>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
						<logic:equal name="registro" property="estado" value="1">
							
							<a href="${pageContext.request.contextPath}/spring/eliminarUsuario?id=${registro.id}" onclick="return comprobarEliminar()">
						    	<font color="#c33400"><spring:message code="field.eliminar"/></font>
							</a>
						</logic:equal>					
					</td>
					<%}%>
    			</tr>
			</logic:iterate> 
		</table>
		</div>
		
		<%
		Object numPat = request.getAttribute("paginasTotales");
		Object pagActual = request.getAttribute("paginaActual");
		int numeroPagina = Integer.parseInt(numPat.toString());
		int paginaActual = Integer.parseInt(pagActual.toString());
		String paginar = "Paginar";%>
		<div class="capaPaginacion" id="cap">
			<span class="pae-form__span-label-regxp">
				<spring:message code="field.numRegistros"/>
					<form:select path="numRegistro" id="numRegistro" onchange="comprobarRegistros()" class="pae-form__input wauto">
						<form:option value="10"><spring:message code="field.10Registros"/></form:option>
						<form:option value="20"><spring:message code="field.20Registros"/></form:option>
						<form:option value="50"><spring:message code="field.50Registros"/></form:option>
					</form:select>
			</span>
							
				<div class="pagination">
				<%if(paginaActual != 1) { %>
					<a href="buscarUsuarios?paginaActual=<%=paginaActual-1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar">&laquo;</a>
				<% } else{  %>	
			 		<a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
				<% }   
			if(numeroPagina <= 6)
			{	
				for(int x = 1 ;x<numeroPagina+1; x++){
					String enlacePagina = "buscarUsuarios?paginaActual="
						+ x
						+ "&paginasTotales="
						+ numeroPagina
						+ "&llamada=" + paginar;
					if(x==paginaActual){%>
						<strong><a href="<%=enlacePagina %>" class="active" ><%=x %></a></strong>
						 
					<%}else{%>
						<a href="<%=enlacePagina %>" ><%=x %></a>
						 
					<%}%>
				<%}%>
			<%}
			else
			{
				for(int x = 1 ;x<numeroPagina+1; x++){
					String enlacePagina = "buscarUsuarios?paginaActual="
						+ x
						+ "&paginasTotales="
						+ numeroPagina
						+ "&llamada=" + paginar;
					if(x==paginaActual && !(x<= 3 || x+3>numeroPagina)){%>
						<div><spring:message code="field.puntosPaginacion"/></div>
						<strong><a href="<%= enlacePagina %>" class="active" ><%=x %></a></strong>
						<div><spring:message code="field.puntosPaginacion"/></div>
					<%}else if (x<= 3 || x+3>numeroPagina){
						if(x==paginaActual){%>	
							<strong><a href="<%= enlacePagina %>" class="active" ><%=x %></a></strong>
							<%if(x == 3 && (paginaActual<= 3 || paginaActual+3>numeroPagina)){	%>
								 <a href="#"><div><spring:message code="field.puntosPaginacion"/></div></a>
							<%}%>
						 
						<%}
						else
						{%>	
							<a href="<%= enlacePagina %>"  ><%=x %></a>
							<%if(x == 3 && (paginaActual<= 3 || paginaActual+3>numeroPagina)){	%>
								 <div><spring:message code="field.puntosPaginacion"/></div>
							<%}%>
							 
						<%}%>	
					<%}%>
				<%}%>
			<%}%>
			<%if(paginaActual != numeroPagina) { %>
				<a href="buscarUsuarios?paginaActual=<%=paginaActual+1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar">&raquo;</a>
			<% } else{  %>	
			 <a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
			<% }  %> 			
			</div>
			<span class="pae-form__span-label-regxp">
				<spring:message code="field.general.irAPagina"/>
			</span>
			<form:input path="numeroPaginaIr" maxlength="4" class="pae-form__input w6"/>
			<div class="pae-box-buttons w10 margin-button-ir">
				<input type="submit" value="Ir" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"  onclick="irPagina(<%=numeroPagina %>)">	
			</div>
		</div>
		<!--  Selección Paginación de Registros -->
		
		</logic:greaterThan>		
		<logic:lessThan name="numResultados" value="1">
				<div id="error">
					<spring:message code="field.usuario.error"/>
				</div>
			</logic:lessThan>
		</logic:present>	
		
</form:form>
	
</body>
</html>