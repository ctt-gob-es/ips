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
%>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/Datepicker_calendario.js"></script>
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
	document.forms[0].action = "../action/buscarLogOperaciones.do";
}

function ordenaDefecto(){
	document.getElementById("campo").value = "1";
	document.getElementById("direccion").value="up";
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
	if(value==6){
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
		document.getElementById("campo").value ="4";
		document.getElementById("direccion").value="up";
	}
	if(value==10){
		document.getElementById("campo").value ="4";
		document.getElementById("direccion").value="down";
	}
	if(value==11){
		document.getElementById("campo").value ="5";
		document.getElementById("direccion").value="up";
	}
	if(value==12){
		document.getElementById("campo").value ="5";
		document.getElementById("direccion").value="down";
	}
	if(value==13){
		document.getElementById("campo").value ="6";
		document.getElementById("direccion").value="up";
	}
	if(value==14){
		document.getElementById("campo").value ="6";
		document.getElementById("direccion").value="down";
	}
	if(value==15){
		document.getElementById("campo").value ="7";
		document.getElementById("direccion").value="up";
	}
	if(value==16){
		document.getElementById("campo").value ="7";
		document.getElementById("direccion").value="down";
	}
	if(value==17){
		document.getElementById("campo").value ="8";
		document.getElementById("direccion").value="up";
	}
	if(value==18){
		document.getElementById("campo").value ="8";
		document.getElementById("direccion").value="down";
	}
	if(value==19){
		document.getElementById("campo").value ="9";
		document.getElementById("direccion").value="up";
	}
	if(value==20){
		document.getElementById("campo").value ="9";
		document.getElementById("direccion").value="down";
	}
	if(value==21){
		document.getElementById("campo").value ="10";
		document.getElementById("direccion").value="up";
	}
	if(value==22){
		document.getElementById("campo").value ="10";
		document.getElementById("direccion").value="down";
	}
	
	document.getElementById("paginaActual").value=numPag;
	document.getElementById("submit").value="Ordenar";
}


function busqueda(){
	document.getElementById("submit").value="Buscar";
	document.getElementById("paginaActual").value="1";
}


function paginaActual(paginaActual){

	document.getElementBy("paginaActual").value=paginaActual;
	document.getElementBy("submit").value="Paginar";
}

function paginaSiguiente(pag,paginasTotales){
	var next=pag+1;
	document.getElementById("submit").value="Paginar";
	document.getElementById("paginaActual").value=next;
	document.getElementById("paginasTotales").value=paginasTotales;
}

function paginaAnterior(pag,paginasTotales){
	var next=pag-1;	
	document.getElementById("submit").value="Paginar";
	document.getElementById("paginaActual").value=next;
	document.getElementById("paginasTotales").value=paginasTotales;		
}


function ultimaPagina(paginasTotales){
	document.getElementById("submit").value="Paginar";
	document.getElementById("paginaActual").value=paginasTotales;
	document.getElementById("paginasTotales").value=paginasTotales;
}

function primeraPagina(paginasTotales){
	document.getElementById("submit").value="Paginar";
	document.getElementById("paginaActual").value="1";
	document.getElementById("paginasTotales").value=paginasTotales;
}


function comprobarRegistros(){
	document.getElementById("submit").value = "Paginar";
	document.getElementById("cambios").value = "Incorrecto";
	document.getElementById("paginaActual").value="1";
	numeroPagina = document.getElementById("paginasTotales").value;
	pagina = document.getElementById("paginaActual").value;
	numRegistro = document.getElementById("numRegistro").value;	
	window.location.href = "../spring/buscarLogOperaciones?paginaActual="+pagina +"&paginasTotales="+numeroPagina +"&numRegistro="+numRegistro;
	 
}


</script>
<html>


<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 
	<title></title>
</head>

<body style="margin-left:0.4em;">

<!--<div style="margin-left:1em; margin-right: 1em;float: left;wth: 99%;">-->

<form:form commandName="logOperacionesForm" action="/IPSG/spring/buscarLogOperaciones" id="formPadre" method="post">
	<form:hidden path="paginaActual" style="paginaActual"/>
	<form:hidden path="paginasTotales" style="paginasTotales"/>
	<form:hidden path="campo" style="campo"/>
	<form:hidden path="direccion" style="direccion"/>
	<form:hidden path="submit" style="submit"/>
	<form:hidden path="cambios" style="cambios"/>
	<form:hidden path="pulsaIr" style="pulsaIr" />
		
	<div class="">
	<logic:present name="org.apache.spring.ERROR">
	<div ="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>
		
     <h1 class="pae-title"><spring:message code="field.logOperaciones.tituloLogOperaciones"/></h1>    
	
	<div data-function="accordion-box" class="pae-box">	
		<div class="pae-box__body">
			<fieldset>
			<!-- Ini Operación-->
				<div class="pae-layout">
						<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
							<div class="pae-form__cell">
								<span class="pae-form__label pae-form__span-label">							
									<spring:message code="field.logOperaciones.operacion"/>									
								</span>					
								<form:select path="tipoOperacion" class="pae-form__input" style="descripcion" onchange="ordenaDefecto();" >
									<form:option value="A"><spring:message code="field.logOperaciones.operacion.accesoAplicacion"/></form:option>
									<form:option value="E"><spring:message code="field.logOperaciones.operacion.cambioEstadosConv"/></form:option>
									<form:option value="C"><spring:message code="field.logOperaciones.operacion.cierreReapertura"/></form:option>				
								</form:select>
						 	</div>					
						</div>
				<!-- Fin Operación-->
				<!-- Ini Usuario-->				
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
						<div class="pae-form__cell">				
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.logOperaciones.usuario"/>
							</span>
							<form:input type="text" path="usuario" class="pae-form__input"  />
						
						</div>					
					</div>
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
						<div class="pae-form__cell">				
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.logOperaciones.fechaDesde"/>
							</span>
					
							
							<form:input type="text" path="fechaDesde" data-function="function-datepicker" 
									placeholder="dd/mm/aaaa" class="pae-form__input" style="fechaDesde" maxlength="10"/>
			<!-- 			<img type="image" src="../images/calendar/boton-calendario.gif" style="cursor: pointer;" alt="Abrir Calendario"  onclick="popUpCalendar(this, document.getElementBy('fechaDesde'), 'dd/mm/yyyy',2);"> -->
<!-- 							<script type="text/javascript"> -->
<%-- 										fncCalendario('fechaDesde', '<%=request.getContextPath()%>'); --%>
<!-- 							</script>					 -->
						</div>
					</div>					
				<!-- Fin Fecha Desde -->
				<!-- Ini Fecha Hasta-->
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">	
						<div class="pae-form__cell">					
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.logOperaciones.fechaHasta"/>
							</span>
							<form:input type="text" path="fechaHasta"  data-function="function-datepicker" 
									placeholder="dd/mm/aaaa" class="pae-form__input" style="fechaHasta" maxlength="10"/>
				<!-- 			<img type="image" src="../images/calendar/boton-calendario.gif" style="cursor: pointer;" alt="Abrir Calendario"  onclick="popUpCalendar(this, document.getElementBy('fechaHasta'), 'dd/mm/yyyy',2);"> -->
							<script type="text/javascript">
											fncCalendario('fechaHasta', '<%=request.getContextPath()%>');
							</script>					
						</div>
					</div>
				</div>
				<!-- Fin Usuario-->		
			
				
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-80">
						<div class="pae-box-buttons">
							<input type="submit" name="buscarConvocatorias" value="Buscar" onclick="busqueda()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
						</div>
					</div>
				</div>
				<div class="clear"></div>				
			</fieldset>
		</div>	
	</div>
	
	
	<logic:present name="logAcceso" scope="request">	
		<bean:size id="numResultados" name="logAcceso" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
		<table width="100%" id="paetable" class="pae-table pae-table--margin" >
			<caption class="hiddenAccesible">Tabla de log de operaciones de acceso</caption>
			<thead class="pae-table__header">
				<tr class="pae-table__row">
					<th class="pae-table__cell-header">
						<table class="tabla_resultadosInterno">
							<tbody>
								<tr>											
									<th>
										<div class="titulo_izq_tabla">									
											<span class="pae-table__txt-title">
												<spring:message code="field.logOperaciones.id" /> 
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
					<th class="pae-table__cell-header">
						<table class="tabla_resultadosInterno">
							<tbody>
								<tr>											
									<th>
										<div class="titulo_izq_tabla">									
											<span class="pae-table__txt-title">
												<spring:message code="field.logOperaciones.fecha" /> 
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
					<th class="pae-table__cell-header">
						<table class="tabla_resultadosInterno">
							<tbody>
								<tr>											
									<th>
										<div class="titulo_izq_tabla">									
											<span class="pae-table__txt-title">
												<spring:message code="field.logOperaciones.usuario" /> 
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
					<th class="pae-table__cell-header">
						<table class="tabla_resultadosInterno">
							<tbody>
								<tr>											
									<th>
										<div class="titulo_izq_tabla">									
											<span class="pae-table__txt-title">
												<spring:message code="field.logOperaciones.resultadoAcceso" /> 
											</span>	
										</div>
									</th>
									<th>											
										<div class="titulo_der_tabla">
											<input type="image"
												src="../images/order_desc.png" value="submit" alt="Submit"
												onclick="ordena(19,<%=numPag%>)"><input type="image"
												src="../images/order_asc.png" value="Submit" alt="Submit"
												onclick="ordena(20,<%=numPag%>)">
										</div>
									</th>
								</tr>
							</tbody>
						</table>
					</th>										
				</tr>
			</thead>
			<tbody class="pae-table__body">
				<c:forEach var="registro" items="${logAcceso}">					
					<tr class="pae-table__row" name="row">				
						<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="id" /></span>
               			</td>	
						<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="fecha" format="dd/MM/yyyy HH:mm" /></span>
               			</td>
               			<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="loginUsuario" /></span>
               			</td>
               			<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="resultadoAcceso" /></span>
               			</td>														
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
		
			
			<%
			Object numPat = request.getAttribute("paginasTotales");
			Object pagActual = request.getAttribute("paginaActual");
			int numeroPagina = Integer.parseInt(numPat.toString());
			int paginaActual = Integer.parseInt(pagActual.toString());%>
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
					<a href="buscarLogOperaciones?paginaActual=<%=paginaActual-1%>&paginasTotales=<%=numeroPagina %>" >&laquo;</a>
				<% } else{  %>	
			 		<a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
				<% }   			
			if(numeroPagina <= 6)
			{	
				for(int x = 1 ;x<numeroPagina+1; x++){
					String enlacePagina = "buscarLogOperaciones?paginaActual="+x +"&paginasTotales="+numeroPagina;
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
					String enlacePagina = "buscarLogOperaciones?paginaActual="+x +"&paginasTotales="+numeroPagina;
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
				<a href="buscarLogOperaciones?paginaActual=<%=paginaActual+1%>&paginasTotales=<%=numeroPagina %>" >&raquo;</a>
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
	<div class="clear">			 
		</logic:greaterThan>
		<logic:lessThan name="numResultados" value="1">
				<div id="error">
					<div id="rellenarCampos" class="pae-alertbox pae-alertbox--warning">
						<ul><p class="pae-alertbox__text"><spring:message code="field.cuerpoEscala.error"/></p></ul>
					</div>				
				</div>
			</logic:lessThan>
	</logic:present>
	
	
	
	<logic:present name="logGenerico" scope="request">			
		<bean:size id="numResultados" name="logGenerico" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
		<table id="paetable" class="pae-table pae-table--margin">
			<caption class="hiddenAccesible">Tabla Generica abierta</caption>			
			<tr class="pae-table__row">
				<th class="pae-table__cell-header">
						<table class="tabla_resultadosInterno">
							<tbody>
								<tr>											
									<th>
										<div class="titulo_izq_tabla">									
											<span class="pae-table__txt-title">
												<spring:message code="field.logOperaciones.id" />
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
					<th class="pae-table__cell-header">
						<table class="tabla_resultadosInterno">
							<tbody>
								<tr>											
									<th>
										<div class="titulo_izq_tabla">									
											<span class="pae-table__txt-title">
												<spring:message code="field.logOperaciones.fecha" /> 
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
					
					<th class="pae-table__cell-header">
						<table class="tabla_resultadosInterno">
							<tbody>
								<tr>											
									<th>
										<div class="titulo_izq_tabla">									
											<span class="pae-table__txt-title">
												<spring:message code="field.logOperaciones.operacion" />
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
												<spring:message code="field.logOperaciones.centroGestor" />
											</span>	
										</div>
									</th>
									<th>											
										<div class="titulo_der_tabla">
											<input type="image"
														src="../images/order_desc.png" value="accion" alt="Ordenar"
														onclick="ordena(21,<%=numPag %>)"><input type="image"
														src="../images/order_asc.png" value="accion" alt="Ordenar"
														onclick="ordena(22,<%=numPag %>)">
										</div>
									</th>
								</tr>
							</tbody>
						</table>
					</th>
			</tr>					
				<c:forEach var="registro" items="${logGenerico}">
					<tr  name="row">
						<td data-content=""  data-function="fc-collapse-table" class="pae-table__cell-body">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="id" /></span>
               			</td>
               			<td  data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="fecha" format="dd/MM/yyyy HH:mm" /></span>
               			</td>
						<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="descripcionTipoOperacion" /></span>
               			</td>
						<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="centroGestor" /></span>
               			</td>
					</tr>
				</c:forEach> 
			</tbody>
		</table>			
			<%
			Object numPat = request.getAttribute("paginasTotales");
			Object pagActual = request.getAttribute("paginaActual");
			int numeroPagina = Integer.parseInt(numPat.toString());
			int paginaActual = Integer.parseInt(pagActual.toString());%>
			
			<div class="capaPaginacion" id="cap">				
					<span class="pae-form__span-label-regxp">
						<spring:message code="field.numRegistros"/>
					</span>
						<form:select path="numRegistro" class="pae-form__input wauto" onchange="comprobarRegistros()">
							<form:option value="10"><spring:message code="field.10Registros"/></form:option>
							<form:option value="20"><spring:message code="field.20Registros"/></form:option>
							<form:option value="50"><spring:message code="field.50Registros"/></form:option>
						</form:select>
					
					
			<div class="pagination">
				<%if(paginaActual != 1) { %>
					<a href="buscarLogOperaciones?paginaActual=<%=paginaActual-1%>&paginasTotales=<%=numeroPagina %>" >&laquo;</a>
				<% } else{  %>	
			 		<a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
				<% }
			if(numeroPagina <= 6)
			{	
				for(int x = 1 ;x<numeroPagina+1; x++){
					String enlacePagina = "buscarLogOperaciones?paginaActual="+x +"&paginasTotales="+numeroPagina +"&llamada=Paginar";
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
					String enlacePagina = "buscarLogOperaciones?paginaActual="+x +"&paginasTotales="+numeroPagina +"&llamada=Paginar";
					if(x==paginaActual && !(x<= 3 || x+3>numeroPagina)){%>
						<div><spring:message code="field.puntosPaginacion"/></div>
						<strong><a href="<%=enlacePagina %>" class="active" ><%=x %></a></strong>
						<spring:message code="field.puntosPaginacion"/>&nbsp;
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
				<a href="buscarLogOperaciones?paginaActual=<%=paginaActual+1%>&paginasTotales=<%=numeroPagina %>" >&raquo;</a>
			<% } else{  %>	
			 <a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
			<% }  %> 
			</div>			
			<span class="pae-form__span-label-regxp">
			<spring:message code="field.general.irAPagina"/>
			</span>
			<form:input path="numeroPaginaIr" maxlength="4" class="pae-form__input w6"/>
			<div class="pae-box-buttons w10 margin-button-ir">
				<input type="submit" value="Ir"class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"  onclick="irPagina(<%=numeroPagina %>)">
			</div>
			</div>
			<div class="clear"></div>
			
			</logic:greaterThan>
			<logic:lessThan name="numResultados" value="1">
					<div ="error">
						<spring:message code="field.cuerpoEscala.error"/>
						<br>
					</div>
				</logic:lessThan>				
		</logic:present>
	
	
	
	
	<logic:present name="logConvocatoria" scope="request">	
		<bean:size id="numResultados" name="logConvocatoria" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
		<table id="paetable" class="pae-table pae-table--margin">
		<caption class="hiddenAccesible">Tabla Log Convocatoria abierta</caption>
		<thead class="pae-table__header">
			<tr class="pae-table__row">
				<th class="pae-table__cell-header wauto">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.logOperaciones.id" />
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ordenar" 
											onclick="ordena(1,<%=numPag%>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Ordenar" onclick="ordena(2,<%=numPag%>)">
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
											<spring:message code="field.logOperaciones.fecha" /> 
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ordenar" 
											onclick="ordena(3,<%=numPag%>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Ordenar" onclick="ordena(4,<%=numPag%>)">
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
											<spring:message code="field.logOperaciones.usuario" />
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ordenar" 
											onclick="ordena(9,<%=numPag%>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Ordenar" onclick="ordena(10,<%=numPag%>)">
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
											<spring:message code="field.logOperaciones.operacion" /> 
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ordenar" 
											onclick="ordena(11,<%=numPag%>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Ordenar" onclick="ordena(12,<%=numPag%>)">
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
											<spring:message code="field.logOperaciones.convocatoria" /> 
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ordenar" 
											onclick="ordena(13,<%=numPag%>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Ordenar" onclick="ordena(14,<%=numPag%>)">
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
											<spring:message code="field.logOperaciones.estadoAnterior" />
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ordenar" 
											onclick="ordena(15,<%=numPag%>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Ordenar" onclick="ordena(16,<%=numPag%>)">
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
											<spring:message code="field.logOperaciones.estadoActual" /> 
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="accion" alt="Ordenar" 
											onclick="ordena(17,<%=numPag%>)"><input type="image" 
											src="../images/order_asc.png" value="accion" alt="Ordenar" onclick="ordena(18,<%=numPag%>)">
									</div>
								</th>
							</tr>
						</tbody>
					</table>
				</th>							
			</tr>
			</thead>
			<tbody class="pae-table__body">
			<c:forEach var="registro" items="${logConvocatoria}">
				<tr class="pae-table__row" name="row">				
					<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
              			<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="idLogConvocatoria" /></span>
            		</td>
            		<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body wauto">
              			<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="fecha" format="dd/MM/yyyy HH:mm" /></span>
            		</td>						
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
              			<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="login" /></span>
            		</td>						
					<td data-content="" data-col="col4" data-function="fc-collapse-table" class="pae-table__cell-body">
						<logic:equal name="registro" property="tipoOperacion" value="A">
              				<span class="pae-table__txt-base pae-table__span-head"><spring:message code="field.tipoOperacion.Alta"/></span>
             				</logic:equal>
           				<logic:equal name="registro" property="tipoOperacion" value="M">
							<span class="pae-table__txt-base pae-table__span-head"><spring:message code="field.tipoOperacion.Modificacion"/></span>
						</logic:equal>
						<logic:equal name="registro" property="tipoOperacion" value="C">
							<span class="pae-table__txt-base pae-table__span-head"><spring:message code="field.tipoOperacion.CambioEstado"/></span>
						</logic:equal>
            		</td>
					<td data-content="" data-col="col5" data-function="fc-collapse-table" class="pae-table__cell-body">
              			<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="nombreConvocatoria" /></span>
            		</td>
        			<td data-content="" data-col="col6" data-function="fc-collapse-table" class="pae-table__cell-body">
              			<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="estadoConvocatoriaAnterior" /></span>
            		</td>		
            		<td data-content="" data-col="col7" data-function="fc-collapse-table" class="pae-table__cell-body">
              			<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="estadoConvocatoriaActual" /></span>
            		</td>
							
				</tr>
			</c:forEach>
			</tbody>			
		</table>
			
			<%
			Object numPat = request.getAttribute("paginasTotales");
			Object pagActual = request.getAttribute("paginaActual");
			int numeroPagina = Integer.parseInt(numPat.toString());
			int paginaActual = Integer.parseInt(pagActual.toString());%>
			
			<div class="capaPaginacion" id="cap">				
				<span class="pae-form__span-label-regxp">
					<spring:message code="field.numRegistros"/>
				</span>
					<form:select path="numRegistro" class="pae-form__input wauto" onchange="comprobarRegistros()">
						<form:option value="10"><spring:message code="field.10Registros"/></form:option>
						<form:option value="20"><spring:message code="field.20Registros"/></form:option>
						<form:option value="50"><spring:message code="field.50Registros"/></form:option>
					</form:select>
					
					
					<div class="pagination">
					<%if(paginaActual != 1) { %>
						<a href="buscarLogOperaciones?paginaActual=<%=paginaActual-1%>&paginasTotales=<%=numeroPagina %>" >&laquo;</a>
					<% } else{  %>	
			 			<a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
					<% }
			if(numeroPagina <= 6)
			{	
				for(int x = 1 ;x<numeroPagina+1; x++){
					String enlacePagina = "buscarLogOperaciones?paginaActual="+x +"&paginasTotales="+numeroPagina;
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
					String enlacePagina = "buscarLogOperaciones?paginaActual="+x +"&paginasTotales="+numeroPagina;
					if(x==paginaActual && !(x<= 3 || x+3>numeroPagina)){%>
						<div><spring:message code="field.puntosPaginacion"/></div>
						<strong><a href="<%=enlacePagina %>" class="active" ><%=x %></a></strong>
						<spring:message code="field.puntosPaginacion"/>&nbsp;
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
				<a href="buscarLogOperaciones?paginaActual=<%=paginaActual+1%>&paginasTotales=<%=numeroPagina %>" >&raquo;</a>
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
			
			<div class="clear"></div>
			</logic:greaterThan>
				<logic:lessThan name="numResultados" value="1">
					<div ="error">
						<spring:message code="field.cuerpoEscala.error"/>
						<br>&nbsp;						
					</div>
				</logic:lessThan>
			</logic:present>

</div>
</form:form>
	
<!--</div>-->
</body>
</html>