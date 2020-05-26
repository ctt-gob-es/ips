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
<%@page import="es.map.ipsg.util.Constantes"%>
<%
String numPag = (String)request.getAttribute("paginaActual");%>

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
	document.forms[0].action = "../spring/buscarProvincias";
}

function alta(){
	document.getElementById("submit").value="Alta";
	window.location.href = "../spring/crearProvincia?accion=<%=Constantes.OPERACION_ALTA %>"; 
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
	if(value==8	){
		document.getElementById("campo").value ="4";
		document.getElementById("direccion").value="down";
	}
	document.getElementById("paginaActual").value=numPag;
	document.getElementById("submit").value="Ordenar";
}

function comprobarEliminar(){
	return confirm('<spring:message code="field.mensajeEliminar"/>');
}

function busqueda(){
	
	document.getElementById("paginaActual").value="1";
	document.getElementById("cambios").value="Correcto";
	document.getElementById("submit").value="Buscar";
}

function paginaActual(paginaActual,paginasTotales){
	var buscar = document.getElementById("cambios").value;
	if("Correcto" == buscar){
		document.getElementById("submit").value="Paginar";
		document.getElementById("paginaActual").value=paginaActual;
		document.getElementById("paginasTotales").value=paginasTotales;
	}else{
		document.getElementById("submit").value="Buscar";
		document.getElementById("paginaActual").value=paginaActual;
	}
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

function comprobarBusqueda(){
	document.getElementById("cambios").value="Incorrecto";
}

function comprobarRegistros(){
	document.getElementById("submit").value = "Paginar";
	document.getElementById("cambios").value = "Incorrecto";
	document.getElementById("paginaActual").value="1";
	numeroPagina = document.getElementById("paginasTotales").value;
	pagina = document.getElementById("paginaActual").value;
	numRegistro = document.getElementById("numRegistro").value;
	window.location.href = "../spring/buscarProvincias?paginaActual="+pagina +"&paginasTotales="+numeroPagina +"&llamada=Buscar"+"&numRegistro="+numRegistro; 
}

</script>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>

<body style="margin-left:0.4em;">
<div class="">

     <h1 class="pae-title">  <spring:message code="field.provincia.titulo"/> </h1>
   
   	<logic:present name="org.apache.spring.ERROR">
		<div id="error">
			<html:errors/>
		</div>
	</logic:present>
   
<div data-function="accordion-box" class="pae-box">
	<div class="pae-box__header">
		<h3 class="pae-box__header--title">
			<spring:message code="field.textosIntroduccion.provincias"/>
		</h3>
	</div>

<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->

<form:form commandName="buscaProvinciasForm" action="/IPSG/spring/buscarProvincias" id="formPadre" method="post">
	<form:hidden path="paginaActual" id="paginaActual"/>
	<form:hidden path="paginasTotales" id="paginasTotales"/>
	<form:hidden path="campo" id="campo"/>
	<form:hidden path="direccion" id="direccion"/>
	<form:hidden path="submit" id="submit"/>
	<form:hidden path="cambios" id="cambios"/>
	<form:hidden path="pulsaIr" id="pulsaIr" />
	
	<div class="pae-box__body">
		<fieldset>
			<div class="pae-layout">
			<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
				<div class="pae-form__cell">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.descripcionProv"/>
					</span>
				<logic:present name="descripcion" scope="request"> </logic:present>
				<form:input type="text" path="descripcion" class="pae-form__input" id="descripcion" onchange="comprobarBusqueda()" maxlength="250"/>
				</div>
			</div>
		</div>

	<div class="clear"></div>
	<div class="filaBtn">
		<div class="pae-box-buttons">	
			<input type="submit" value="Buscar" onclick="busqueda()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
			<input type="button" value="Alta" onclick="alta()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline mg-left-1">
		</div>
	</div>
	
	</fieldset>	
	</div>
	
	</div>	

	<jsp:useBean id="provincias" scope="request" class="java.util.ArrayList"/>
	<%int i=0; %>
	<logic:present name="provincias" scope="request">	
		<bean:size id="numResultados" name="provincias" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
		<table id="paetable" class="pae-table pae-table--margin">
				<caption class="hiddenAccesible">Tabla de Provincias</caption>
				<thead class="pae-table__header">
             		<tr class="pae-table__row">
             			<th class="pae-table__cell-header">
							<table class="tabla_resultadosInterno">
								<tbody>
									<tr>											
										<th>
											<div class="titulo_izq_tabla">									
												<span class="pae-table__txt-title">
													<spring:message code="field.idProv"/>
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
													<spring:message code="field.codigoProv"/>
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
													<spring:message code="field.descripcionProv"/>
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
													<spring:message code="field.general.visibilidadTitulo"/>
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
													<spring:message code="field.eliminarMay"/>
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
			<logic:iterate id="registro" name="provincias" >
				<tr class="pae-table__row" name="row">
					<td data-content="" data-col="colauto" data-function="fc-collapse-table" class="pae-table__cell-body">						
							<span class="pae-table__txt-base pae-table__span-head">
								<a href="${pageContext.request.contextPath}/spring/verModificarProvincia?registro=${registro.id}" id="id" style="color: #c33400;">
								<bean:write name="registro" property="id" />
							</span>
						</a>
					</td>
					<td data-content="" data-col="colauto" data-function="fc-collapse-table" class="pae-table__cell-body">
						<span class="pae-table__txt-base pae-table__span-head">
							<bean:write name="registro" property="codigo" />
						</span>
					</td>
					<td data-content="" data-col="colauto" data-function="fc-collapse-table" class="pae-table__cell-body">
						<span class="pae-table__txt-base pae-table__span-head">
							<bean:write name="registro" property="descripcion" />
						</span>
					</td>
					<bean:define id="campoVisibilidad" name="registro" property ="visibilidad" type="Boolean"/>
					<c:choose>
						<c:when test="${campoVisibilidad eq true}">		
							<td data-content="" data-col="colauto" data-function="fc-collapse-table" class="pae-table__cell-body">
								<span class="pae-table__txt-base pae-table__span-head">
									<spring:message code="field.general.visible"/>
								</span>
							</td>	
						</c:when>
						<c:otherwise>
							<td data-content="" data-col="colauto" data-function="fc-collapse-table" class="pae-table__cell-body w20">
								<span class="pae-table__txt-base pae-table__span-head">
									<spring:message code="field.general.noVisible"/>
								</span>
							</td>
						</c:otherwise>
					</c:choose>
					<td data-content="" data-col="colauto" data-function="fc-collapse-table" class="pae-table__cell-body w12">						
							<span class="pae-table__txt-base pae-table__span-head">
								<a href="${pageContext.request.contextPath}/spring/eliminarProvincia?registro=${registro.id}" id="id"  style="color: #c33400;" onclick="return comprobarEliminar()" >
								<spring:message code="field.eliminar"/>
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
				
				
		<div class="capaPaginacion">
		<span class="pagPorFila">
			<spring:message code="field.numRegistros"/>
		</span>
			<form:select path="numRegistro" id="numRegistro" onchange="comprobarRegistros()" class="pae-form__input wauto">
				<form:option value="10"><spring:message code="field.10Registros"/></form:option>
				<form:option value="20"><spring:message code="field.20Registros"/></form:option>
				<form:option value="50"><spring:message code="field.50Registros"/></form:option>
			</form:select>
		
		<div class="pagination">
			<%if(paginaActual != 1) { %>
				<a href="buscarProvincias?paginaActual=<%=paginaActual-1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar">&laquo;</a>
			<% } else{  %>	
				 <a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
			<% }  
		
		if(numeroPagina <= 6)
		{	
			for(int x = 1 ;x<numeroPagina+1; x++){
				String enlacePagina = "buscarProvincias?paginaActual="
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
				String enlacePagina = "buscarProvincias?paginaActual="
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
				<a href="buscarProvincias?paginaActual=<%=paginaActual+1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar">&raquo;</a>
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
	</logic:present>
</form:form>
	

</body>
</html>