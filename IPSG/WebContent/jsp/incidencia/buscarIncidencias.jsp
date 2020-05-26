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
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/Datepicker_calendario.js"></script>
<script type="text/javascript">

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

function irPagina(pagTotales)
{
	var actual = document.getElementById("numeroPaginaIr").value;
	var actualInt = parseInt(actual,10);
	
	if(isNaN(actualInt) || actualInt<=0){
		actualInt = 1;
	}
	
	document.getElementById("pulsaIr").value = true;	
	document.getElementById("submit").value="Paginar";
	document.getElementById("numeroPaginaIr").value = actualInt;
	document.getElementById("paginaActual").value=actualInt;
	document.getElementById("paginasTotales").value=pagTotales;
	document.forms[0].action = "../spring/buscarIncidencias";
}

function ordena(value,numPag){
	
	if(value==1){
		document.getElementById("campo").value = "1";
		document.getElementById("direccion").value="up";
	}
	if(value==2){
		document.getElementById("campo").value ="1";
		document.getElementById("direccion").value="down";
	}
	if(value==3){
		document.getElementById("campo").value ="2";
		document.getElementById("direccion").value="up";
	}
	if(value==4){
		document.getElementById("campo").value ="2";
		document.getElementById("direccion").value="down";
	}
	if(value==5){
		document.getElementById("campo").value ="3";
		document.getElementById("direccion").value="up";
	}
	if(value==6	){
		document.getElementById("campo").value ="3";
		document.getElementById("direccion").value="down";
	}
	if(value==7){
		document.getElementById("campo").value ="4";
		document.getElementById("direccion").value="up";
	}
	if(value==8){
		document.getElementById("campo").value ="4";
		document.getElementById("direccion").value="down";
	}
	if(value==9){
		document.getElementById("campo").value ="5";
		document.getElementById("direccion").value="up";
	}
	if(value==10){
		document.getElementById("campo").value ="5";
		document.getElementById("direccion").value="down";
	}
	if(value==11){
		document.getElementById("campo").value ="6";
		document.getElementById("direccion").value="up";
	}
	if(value==12){
		document.getElementById("campo").value ="6";
		document.getElementById("direccion").value="down";
	}
	
	document.getElementById("paginaActual").value=numPag;
	document.getElementById("submit").value="Ordenar";
}

function comprobarEliminar(){
	return confirm('<spring:message code="field.mensajeEliminar"/>');
}

function busqueda(){
	document.getElementById("submit").value="Buscar";
	document.getElementById("paginaActual").value="1";
	document.getElementById("cambios").value="Correcto";
}


function paginaActual(paginaActual){

	document.getElementById("paginaActual").value=paginaActual;
	document.getElementById("submit").value="Paginar";
}

function paginaSiguiente(pag,paginasTotales){
	var next=pag+1;
	var buscar = document.getElementById("cambios").value;
	if("Correcto" == buscar){
		document.getElementById("submit").value="Paginar";
		document.getElementById("paginaActual").value=next;
		document.getElementById("paginasTotales").value=paginasTotales;
	}else{
		document.getElementById("submit").value="Buscar";
		document.getElementById("paginaActual").value=pag;
		document.getElementById("cambios").value = "Correcto";
	}
}

function paginaAnterior(pag,paginasTotales){
	var next=pag-1;
	var buscar = document.getElementById("cambios").value;
	if("Correcto" == buscar){		
		document.getElementById("submit").value="Paginar";
		document.getElementById("paginaActual").value=next;
		document.getElementById("paginasTotales").value=paginasTotales;
	}else{
		document.getElementById("submit").value="Buscar";
		document.getElementById("paginaActual").value=pag;
		document.getElementById("cambios").value = "Correcto";
	}
}

function ultimaPagina(paginasTotales,pag){
	var buscar = document.getElementById("cambios").value;
	if("Correcto" == buscar){
		document.getElementById("submit").value="Paginar";
		document.getElementById("paginaActual").value=paginasTotales;
		document.getElementById("paginasTotales").value=paginasTotales;
	}else{
		document.getElementById("submit").value="Buscar";
		document.getElementById("paginaActual").value=pag;
		document.getElementById("cambios").value = "Correcto";
	}
}

function primeraPagina(paginasTotales,pag){
	var buscar = document.getElementById("cambios").value;
	if("Correcto" == buscar){
		document.getElementById("submit").value="Paginar";
		document.getElementById("paginaActual").value="1";
		document.getElementById("paginasTotales").value=paginasTotales;
	}else{
		document.getElementById("submit").value="Buscar";
		document.getElementById("paginaActual").value=pag;
		document.getElementById("cambios").value = "Correcto";
	}
}
function llamada(){
	document.forms[0].action="detalleIncidencias";
	document.forms[0].submit();
	
}

function comprobarRegistros(){
	document.getElementById("submit").value = "Paginar";
	document.getElementById("cambios").value = "Incorrecto";
	document.getElementById("paginaActual").value="1";
	numeroPagina = document.getElementById("paginasTotales").value;
	pagina = document.getElementById("paginaActual").value;
	numRegistro = document.getElementById("numRegistro").value;
	window.location.href = "../spring/buscarIncidencias?paginaActual="+pagina +"&paginasTotales="+numeroPagina +"&numRegistro="+numRegistro; 
}

</script>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 
	<title></title>
</head>

<body style="margin-left:0.4em;"> 
<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">

<form:form commandName="buscarIncidenciasForm" action="/IPSG/spring/buscarIncidencias" id="formPadre" method="post" onsubmit="return validarNif()">
	<form:hidden path="paginaActual" id="paginaActual"/>
	<form:hidden path="paginasTotales" id="paginasTotales"/>
	<form:hidden path="submit" id="submit"/>
	<form:hidden path="campo" id="campo"/>
	<form:hidden path="direccion" id="direccion"/>
	<form:hidden path="cambios" id="cambios"/>
	<form:hidden path="pulsaIr" id="pulsaIr" />
	

	
	
	<h1 class="pae-title"><spring:message code="field.incidencias.titulo"/></h1>    
	
	<logic:present name="org.apache.spring.ERROR">
		<div id="error">
			<html:errors/>
		</div>
		<div class="clear"></div>
	</logic:present>
	
	<div data-function="accordion-box" class="pae-box">	
		<div class="pae-box__body">
			<fieldset>
				<%-- Ini NIF --%>
				<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">		
							<spring:message code="field.incidencias.nif"/>
						</span>	
						<form:input type="text" path="nif" id="nif" class="pae-form__input" maxlength="9"/>
					</div>
				</div>
				<%-- FIN NIF --%>			
				<%-- Ini Tipo --%>
				<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">		
							<spring:message code="field.incidencias.tipo"/>
						</span>	
						<form:select path="tipo" id="descripcion" class="pae-form__input">
							<form:option value="U"><spring:message code="field.incidencias.usuario"/></form:option>
							<form:option value="C"><spring:message code="field.incidencias.ciudadano"/></form:option>
						</form:select>
					</div>
				</div>
				<%-- FIN Tipo --%>	
				<%-- Ini Fecha Desde --%>
				<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">		
							<spring:message code="field.incidencias.fechaDesde"/>
						</span>	
						<form:input type="text" path="fechaDesde" data-function="function-datepicker" 
						placeholder="dd/mm/aaaa" class="pae-form__input" style="fechaDesde" maxlength="10"/>
					</div>
				</div>
				<%-- FIN Fecha Desde --%>					
				<%-- Ini Fecha Fin --%>
				<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
					<div class="pae-form__cell">
						<span class="pae-form__label pae-form__span-label">		
							<spring:message code="field.incidencias.fechaHasta"/>
						</span>	
						<form:input type="text" path="fechaHasta" data-function="function-datepicker" 
						placeholder="dd/mm/aaaa" class="pae-form__input" style="fechaHasta" maxlength="10"/>
					</div>
				</div>
				<%-- FIN Fecha Fin --%>					
				
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-80">
						<div class="pae-box-buttons">
							<input type="submit" name="buscarConvocatorias" value="Buscar" onclick="busqueda()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
						</div>
					</div>
				</div>
			</fieldset>
		</div>		
	</div>			
	

	<logic:present name="incidencias" scope="request">	
		<bean:size id="numResultados" name="incidencias" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">		
		<table id="paetable" class="pae-table pae-table--margin">
			<caption class="hiddenAccesible">Tabla de Incidencias</caption>
			<thead class="pae-table__header">
				<tr class="pae-table__row">
					<th class="pae-table__cell-header wauto">
						<table class="tabla_resultadosInterno">
							<tbody>
								<tr>											
									<th>
										<div class="titulo_izq_tabla">									
											<span class="pae-table__txt-title">
												<spring:message code="field.incidencias.id"/>
											</span>	
										</div>
									</th>
									<th>											
										<div class="titulo_der_tabla">
											<input type="image"
														src="../images/order_desc.png" value="accion" alt="Ordenar"
														onclick="ordena(1,<%=numPag %>)"><input type="image"
														src="../images/order_asc.png" value="accion" alt="Ordenar"
														onclick="ordena(2,<%=numPag %>)">
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
												<spring:message code="field.incidencias.nif"/>
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
												<spring:message code="field.incidencias.nombre"/>
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
												<spring:message code="field.incidencias.primerApellido"/>
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
												<spring:message code="field.incidencias.segundoApellido"/>
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
												<spring:message code="field.incidencias.fecha"/>
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
				</tr>
			<logic:iterate id="registro" name="incidencias" >
				<tr class="pae-table__row" name="row">	
					<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
						<a href="${pageContext.request.contextPath}/spring/detalleIncidencias?id=${registro.id}" id="id" name="id">
						<font color="#c33400">	<bean:write name="registro" property="id" /> </font>
						</a>
					</td>
					<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
					<bean:write name="registro" property="nif" /></td>
					<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
					<bean:write name="registro" property="nombre" /></td>
					<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
					<bean:write name="registro" property="primerApellido" /></td>
					<td data-content=""  data-function="fc-collapse-table" class="pae-table__cell-body">
					<bean:write name="registro" property="segundoApellido" /></td>
					<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
					<bean:write name="registro" property="fechaEnvio" /></td>
				</tr>
			</logic:iterate> 
		</table>
		
		
		
			<%
			Object numPat = request.getAttribute("paginasTotales");
			Object pagActual = request.getAttribute("paginaActual");
			int numeroPagina = Integer.parseInt(numPat.toString());
			int paginaActual = Integer.parseInt(pagActual.toString());
			String paginar = "Paginar";%>
			<div class="capaPaginacion" id="cap">
			
				<span class="pae-form__span-label-regxp">
					<spring:message code="field.numRegistros"/>
				</span>
						<form:select path="numRegistro" onchange="comprobarRegistros()" class="pae-form__input wauto">
							<form:option value="10"><spring:message code="field.10Registros"/></form:option>
							<form:option value="20"><spring:message code="field.20Registros"/></form:option>
							<form:option value="50"><spring:message code="field.50Registros"/></form:option>
						</form:select>
					
				<div class="pagination">				
				<%if(paginaActual != 1) { %>
					<a href="buscarIncidencias?paginaActual=<%=paginaActual-1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar">&laquo;</a>
				<% } else{  %>	
			 		<a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
				<% }
			if(numeroPagina <= 6)
			{	
				for(int x = 1 ;x<numeroPagina+1; x++){
					String enlacePagina = "buscarIncidencias?paginaActual="+x +"&paginasTotales="+numeroPagina+ "&llamada=" + paginar;
					if(x==paginaActual){%>
						<strong><a href="<%=enlacePagina %>" class="active" ><%=x %></a></strong>
						&nbsp;
					<%}else{%>
						<a href="<%=enlacePagina %>" style="color: #000000;" ><%=x %></a>
						&nbsp;
					<%}%>
				<%}%>
			<%}
			else
			{
				for(int x = 1 ;x<numeroPagina+1; x++){
					String enlacePagina = "buscarIncidencias?paginaActual="+x +"&paginasTotales="+numeroPagina+ "&llamada=" + paginar;
					if(x==paginaActual && !(x<= 3 || x+3>numeroPagina)){%>
						<div><spring:message code="field.puntosPaginacion"/></div>
						<strong><a href="<%=enlacePagina %>" class="active" ><%=x %></a></strong>
						<div><spring:message code="field.puntosPaginacion"/></div>
					<%}else if (x<= 3 || x+3>numeroPagina){
						if(x==paginaActual){%>	
							<strong><a href="<%=enlacePagina %>" class="active" ><%=x %></a></strong>
							<%if(x == 3 && (paginaActual<= 3 || paginaActual+3>numeroPagina)){	%>
								<div><spring:message code="field.puntosPaginacion"/></div>
							<%}%>						
						<%}
						else
						{%>	
							<a href="<%=enlacePagina %>" style="color: #000000;" ><%=x %></a>
							<%if(x == 3 && (paginaActual<= 3 || paginaActual+3>numeroPagina)){	%>
								<div><spring:message code="field.puntosPaginacion"/></div>
							<%}%>							
						<%}%>	
					<%}%>
				<%}%>
			<%}%>
			<%if(paginaActual != numeroPagina) { %>
				<a href="buscarIncidencias?paginaActual=<%=paginaActual+1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar">&raquo;</a>
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
	
			
</div><div class="clear">			 
		</logic:greaterThan>
		<logic:lessThan name="numResultados" value="1">
				
				<div id="error">
					<div id="rellenarCampos" class="pae-alertbox pae-alertbox--warning">
						<ul><p class="pae-alertbox__text"><spring:message code="field.cuerpoEscala.error"/></p></ul>
					</div>				
				</div>
			</logic:lessThan>
	</logic:present>
</form:form>
	
</div>
</body>
</html>