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
String numPag = (String)request.getAttribute("paginaActual");
String perfilUsuario =  request.getAttribute("perfilUsuario").toString();
%>
<%@page import="es.map.ipsg.util.Constantes"%>


<script type="text/javascript">

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
	document.forms[0].action = "../spring/buscarAlerta";
}

function comprobarEliminar(){
	return confirm('<spring:message code="field.mensajeEliminar"/>');
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
	}
	document.getElementById("paginaActual").value=numPag;
	document.getElementById("submit").value="Ordenar";
}

function busqueda(){
	document.getElementById("submit").value="Buscar";
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
	}
}

function comprobarEliminar(){
	return confirm('<spring:message code="field.mensajeEliminar"/>');
}

function comprobarBusqueda(){
	document.getElementById("cambios").value="Incorrecto";
}

function openWindowCentroGestor() {
	var param = "idCentroGestorBusqueda";
	var param2 = "dsCentroGestor";
	var extract = new Object();
		ventanaPopup = window.open("../spring/listarCentroGestor?parametro="+param+"&parametro2="+param2, 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
	return false;
} 
function comprobarRegistros(){
	document.getElementById("submit").value = "Paginar";
	document.getElementById("cambios").value = "Incorrecto";
	document.getElementById("paginaActual").value="1";
	numeroPagina = document.getElementById("paginasTotales").value;
	pagina = document.getElementById("paginaActual").value;
	numRegistro = document.getElementById("numRegistro").value;
	window.location.href = "../spring/buscarAlerta?paginaActual="+pagina +"&paginasTotales="+numeroPagina +"&llamada=Buscar"+"&numRegistro="+numRegistro; 
}

function limpiarCentroGestor(){
var desCentro = document.getElementById("dsCentroGestor").value;
	if(desCentro != ""){
		document.getElementById("idCentroGestorBusqueda").value = "";
		document.getElementById("dsCentroGestor").value = "";
	}
}


</script>


<%-- <%@page import="org.apache.commons.validator.*"%><html> --%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title></title>
</head>

<body style="margin-left:0.4em;">

<div class="">

 	<h1 class="pae-title"> <spring:message code="field.alerta.tituloBusqueda"/> </h1>


	<div data-function="accordion-box" class="pae-box">
	<div class="pae-box__header">
		<h3 class="pae-box__header--title">
			<spring:message code="field.textosIntroduccion.alertas"/>
		</h3>
	</div>


<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->
<!--<div class="elements_sub_titulo">-->

<form:form modelAttribute="alertaForm" action="/IPSG/spring/buscarAlerta" id="formPadre" method="post">
	<form:hidden path="paginaActual" id="paginaActual"/>
	<form:hidden path="paginasTotales" id="paginasTotales"/>
	<form:hidden path="submit" id="submit"/>
	<form:hidden path="campo" id="campo"/>
	<form:hidden path="direccion" id="direccion"/>
	<form:hidden path="cambios" id="cambios"/>
	<form:hidden path="pulsaIr" id="pulsaIr" />
	

	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
		<div class="clear"></div>
	</div>
	</logic:present>
	<br>
	<!-- Ini Centro Gestor-->
	<div class="pae-box__body">
		<fieldset>
			<div class="pae-layout">
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.alerta.centroGestorMay"/>
					</span>
		    <%if( perfilUsuario.equals(Constantes.PERFIL_GESTOR)) { %>
				<!--INI-TRA042-->
				<logic:present name="listaCentrosGestores" scope="request">
					<div class="pae-form__cell">
						<form:select path="idCentroGestorBusqueda" class="pae-form__input">
							<option value=""></option>
							<form:options items="${listaCentrosGestores}" itemValue="id" itemLabel="descripcion" />
						</form:select>
					</div>
				</logic:present>
				<!--FIN-TRA042-->
			<%} else {%>
			<form:input path="idCentroGestorBusqueda" id="idCentroGestorBusqueda" onchange="comprobarBusqueda()" class="pae-form__input"  maxlength="5" readonly="true" onclick="limpiarCentroGestor()"></form:input>
<!-- 			<input type="image" src="../images/lupa.png" alt="Buscar Centro Gestor" onclick="return openWindowCentroGestor()"> -->
			<%} %>
			<form:input path="dsCentroGestor" id="dsCentroGestor" class="input_texto_border0 em100" readonly="true"></form:input>
			</div>
			</div>
		    <%if(!perfilUsuario.equals(Constantes.PERFIL_GESTOR)) { %>	
				<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm search_btn">
				<input type="button"
				class="pae-form__btn-search" alt="Buscar Centro Gestor"
				onclick="return openWindowCentroGestor()"> 
				</div>
			<%} %>	
		<logic:present name="tiposAlertas" scope="request">
			<bean:size id="numAlertas" name="tiposAlertas" scope="request" />
			<logic:greaterThan name="numAlertas" value="0">
				<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.alerta.tipoAlertaMay" />
					</span>
					<form:select path="tipoAlerta" class="pae-form__input">
						<option value=""></option>
						<form:options items="${tiposAlertas}" itemValue="id" itemLabel="descripcion" />
					</form:select>
				</div>
				</div>
			</logic:greaterThan>
		</logic:present>
	</div>
	<!-- Fin Alerta-->
	
	<div class="clear"></div>
	<div class="pae-layout">
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-80">
			<div class="pae-box-buttons">
				<input type="submit" name="submit" value="Buscar" onclick="busqueda()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
			</div>
		</div>
	</div>
	
	</fieldset>	
	</div>

	</div>	
	</div>
	

	<jsp:useBean id="alertas" scope="request" class="java.util.ArrayList"/>
	<%int i=0; %>
	<logic:present name="alertas" scope="request">
		<bean:size id="numResultados" name="alertas" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
	<table id="paetable" class="pae-table pae-table--margin">
		<caption class="hiddenAccesible">Tabla Alertas</caption>
			<thead class="pae-table__header">
             	<tr class="pae-table__row">
             		<th class="pae-table__cell-header">
						<table class="tabla_resultadosInterno">
							<tbody>
								<tr>											
									<th>
										<div class="titulo_izq_tabla">									
											<span class="pae-table__txt-title">
												<spring:message code="field.idMay"/>
											</span>	
										</div>
									</th>
									<th>											
										<div class="titulo_der_tabla">
											<input type="image" 
												src="../images/order_desc.png" value="submit" alt="Submit" 
												onclick="ordena(1,<%=numPag %>)"><input type="image" 
												src="../images/order_asc.png" value="Submit" alt="Submit" onclick="ordena(2,<%=numPag %>)">
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
												<spring:message code="field.alerta.centroGestor"/>
											</span>	
										</div>
									</th>
									<th>											
										<div class="titulo_der_tabla">
											<input type="image" 
												src="../images/order_desc.png" value="Submit" alt="Submit" 
												onclick="ordena(3,<%=numPag %>)"><input type="image" 
												src="../images/order_asc.png" value="Submit" alt="Submit" onclick="ordena(4,<%=numPag %>)">
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
												<spring:message code="field.alerta.tipoAlerta"/>
											</span>	
										</div>
									</th>
									<th>											
										<div class="titulo_der_tabla">
											<input type="image" 
												src="../images/order_desc.png" value="Submit" alt="Submit" 
												onclick="ordena(5,<%=numPag %>)"><input type="image" 
												src="../images/order_asc.png" value="Submit" alt="Submit" onclick="ordena(6,<%=numPag %>)">
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
												<spring:message code="field.alerta.modoAlerta"/>
											</span>	
										</div>
									</th>
									<th>											
										<div class="titulo_der_tabla">
											<input type="image" 
												src="../images/order_desc.png" value="Submit" alt="Submit" 
												onclick="ordena(7,<%=numPag %>)"><input type="image" 
												src="../images/order_asc.png" value="Submit" alt="Submit" onclick="ordena(8,<%=numPag %>)">
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
												<spring:message code="field.alerta.perfil"/>
											</span>	
										</div>
									</th>
									<th>											
										<div class="titulo_der_tabla">
											<input type="image" 
												src="../images/order_desc.png" value="Submit" alt="Submit" 
												onclick="ordena(9,<%=numPag %>)"><input type="image" 
												src="../images/order_asc.png" value="Submit" alt="Submit" onclick="ordena(10,<%=numPag %>)">
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
												<spring:message code="field.alerta.eliminarMay"/>
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
			<logic:iterate id="registro" name="alertas" >
				<tr class="pae-table__row" name="row">
					<td data-content="" data-col="col5" data-function="fc-collapse-table" class="pae-table__cell-body w10">
					<%
						AlertaBean alerta = (AlertaBean)alertas.get(i);
						i++;
						String enlaceModificarAlerta = "verActualizarAlerta?menu=N&id="+alerta.getId();					
					%>
					
						<span class="pae-table__txt-base pae-table__span-head">
							<a href="<%= enlaceModificarAlerta%>" style="color: #c33400;" >
							<bean:write name="registro" property="id" />
						</span>
					</a>				
					</td>					
					<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body">
						<span class="pae-table__txt-base pae-table__span-head">
							<bean:write name="registro" property="desCentroGestor" />
						</span>
					</td>
					<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body">
						<span class="pae-table__txt-base pae-table__span-head">
							<bean:write name="registro" property="desTipoAlerta" />
						</span>
					</td>
					<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body w12">
						<span class="pae-table__txt-base pae-table__span-head">
							<bean:write name="registro" property="desModoAlerta" />
						</span>
					</td>
					<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body">
						<span class="pae-table__txt-base pae-table__span-head">
							<bean:write name="registro" property="desTipoUsuario" />
						</span>
					</td>
					<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body w12">
					<%
						String enlaceEliminarAlerta = "eliminarAlerta?id="+alerta.getId();
						
					%>
					
					
						<span class="pae-table__txt-base pae-table__span-head">
							<a href="<%= enlaceEliminarAlerta%>" style="color: #c33400;" onclick="return comprobarEliminar()">
							<spring:message code="field.alerta.eliminar"/>
						</span>
					</a>	
					</td>			
    			</tr>
			</logic:iterate>
			</tbody> 
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
				
				<form:select path="numRegistro" id="numRegistro" onchange="comprobarRegistros()" class="pae-form__input wauto">
					<form:option value="10"><spring:message code="field.10Registros"/></form:option>
					<form:option value="20"><spring:message code="field.20Registros"/></form:option>
					<form:option value="50"><spring:message code="field.50Registros"/></form:option>
				</form:select>
				
					
			<div class="pagination">
				<%if(paginaActual != 1) { %>
					<a href="buscarAlerta?paginaActual=<%=paginaActual-1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar">&laquo;</a>
				<% } else{  %>	
			 		<a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
				<% }	
		
			if(numeroPagina <= 6)
			{	
				for(int x = 1 ;x<numeroPagina+1; x++){
					String enlacePagina = "buscarAlerta?paginaActual="
						+ x
						+ "&paginasTotales="
						+ numeroPagina
						+ "&llamada=" + paginar;
					if(x==paginaActual){%>
						<strong><a href="<%= enlacePagina %>" class="active" ><%=x %></a></strong>
						 
					<%}else{%>
						<a href="<%= enlacePagina %>" ><%=x %></a>
						 
					<%}%>
				<%}%>
			<%}
			else
			{
				for(int x = 1 ;x<numeroPagina+1; x++){
					String enlacePagina = "buscarAlerta?paginaActual="
						+ x
						+ "&paginasTotales="
						+ numeroPagina
						+ "&llamada=" + paginar;
					if(x==paginaActual && !(x<= 3 || x+3>numeroPagina)){%>
						<div><spring:message code="field.puntosPaginacion"/></div>
						<a href="<%= enlacePagina %>" class="active" ><%=x %></a>
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
				<a href="buscarAlerta?paginaActual=<%=paginaActual+1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar">&raquo;</a>
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
		
			
	<div class="clear">	</div>
		</logic:greaterThan>
		<logic:present name="submit" scope="request">
			<logic:lessThan name="numResultados" value="1">
					<div id="error">
						<spring:message code="field.alerta.error"/>
					</div>
			</logic:lessThan>
		</logic:present>
	</logic:present>
</form:form>
	

</body>
</html>