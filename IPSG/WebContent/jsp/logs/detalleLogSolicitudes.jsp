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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-general.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-modules.css"/>
<%
String numPag = (String)request.getAttribute("paginaActual");
String numeroJustificante= (String) request.getAttribute("numeroJustificante");
String idConvocatoria= (String) request.getAttribute("idConvocatoria");
%>
<script type="text/javascript">

function ordena(value,numPag){
	
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
	
}

function comprobarEliminar(){
	return confirm('<spring:message code="field.mensajeEliminar"/>');
}

function busqueda(){
	document.getElementById("submit").value="Buscar";
	document.getElementById("paginaActual").value="1";
}


//Paginación
function paginaActual(paginaActual){

	document.getElementById("paginaActual").value=paginaActual;
	document.getElementById("submit").value="Paginar";
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
	numeroJustificante = document.getElementById("numeroJustificante").value;
	idConvocatoria = document.getElementById("idConvocatoria").value;
	window.location.href = "../spring/detalleLogSolicitudes?menu=N&numeroJustificante="+numeroJustificante+"&idConvocatoria="+idConvocatoria+"&paginaActual="+pagina +"&paginasTotales="+numeroPagina +"&llamada=Buscar"+"&numRegistro="+numRegistro; 
}
//Fin Paginación




</script>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 
<title></title>
</head>

<body style="margin-left:0.4em; background-color:white; overflow-x: hidden;" > 

<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">

<form:form commandName="logSolicitudesForm" action="/IPSG/spring/detalleLogSolicitudes" id="formPadre" method="post">
	<form:hidden path="paginaActual" id="paginaActual"/>
	<form:hidden path="paginasTotales" id="paginasTotales"/>
	<form:hidden path="submit" id="submit"/>
	<form:hidden path="campo" id="campo"/>
	<form:hidden path="direccion" id="direccion"/>
	<form:hidden path="cambios" id="cambios"/>
	<form:hidden path="numeroJustificante" id="numeroJustificante"/>
	<form:hidden path="idConvocatoria" id="idConvocatoria"/>
	
<div class="pae-body2">
	<logic:present name="org.apache.spring.ERROR">
		<div id="error">
			<html:errors/>
		</div>
		<div class="clear"></div>
	</logic:present>
	
	
	<h1 class="pae-title"><spring:message code="field.logSolicitudes.tituloDetalle"/></h1>	
	
	<logic:present name="detalle" scope="request">	
		<bean:size id="numResultados" name="detalle" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
		
		<table id="paetable" class="pae-table pae-table--margin">
			<caption class="hiddenAccesible">Tabla de detalle de log solicitudes</caption>
				<thead class="pae-table__header">
					<tr class="pae-table__row">
						<th class="pae-table__cell-header">
							<table class="tabla_resultadosInterno">
								<tbody>
									<tr>											
										<th>
											<div class="titulo_izq_tabla">									
												<span class="pae-table__txt-title">
													<spring:message code="field.logSolicitudes.estadoActual" />  
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
													<spring:message code="field.logSolicitudes.estadoAnterior" />  
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
													<spring:message code="field.logSolicitudes.fecha" />  
												</span>	
											</div>
										</th>
										<th>											
											<div class="titulo_der_tabla">
												<input type="image" 
													src="../images/order_desc.png" value="submit" alt="Submit" 
													onclick="ordena(5,<%=numPag%>)"><input type="image" 
													src="../images/order_asc.png" value="Submit" alt="Submit" onclick="ordena(6,<%=numPag%>)">
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
													<spring:message code="field.logSolicitudes.responsable" />  
												</span>	
											</div>
										</th>
										<th>											
											<div class="titulo_der_tabla">
												<input type="image" 
													src="../images/order_desc.png" value="accion" alt="Ordenar" 
													onclick="ordena(7,<%=numPag%>)"><input type="image" 
													src="../images/order_asc.png" value="accion" alt="Ordenar" onclick="ordena(8,<%=numPag%>)">
											</div>
										</th>
									</tr>
								</tbody>
							</table>
						</th>						
					</tr>
				</thead>
				<tbody class="pae-table__body">
				<c:forEach var="registro" items="${detalle}">
					<tr class="pae-table__row" name="row">
						<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body">
	               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="estadoActual" /></span>
	             		</td>
	             		<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
	               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="estadoAnterior" /></span>
	             		</td>
	             		<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
	               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="fecha" format="dd/MM/yyyy HH:mm"/></span>
	             		</td>
	             		<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
	               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="actor" /></span>
	             		</td>				
				</tr>
				</c:forEach>		
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
						<a href="detalleLogSolicitudes?paginaActual=<%=paginaActual-1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar&numeroJustificante=<%=numeroJustificante%>&idConvocatoria=<%=idConvocatoria%>">&laquo;</a>
					<% } else{  %>	
				 		<a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
					<% }
				
			if(numeroPagina <= 6)
			{	
				for(int x = 1 ;x<numeroPagina+1; x++){
					String enlacePagina = "detalleLogSolicitudes?menu=N&numeroJustificante="+numeroJustificante+"&idConvocatoria="+idConvocatoria+"&paginaActual="+x +"&paginasTotales="+numeroPagina + "&llamada=" + paginar;
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
					String enlacePagina = "detalleLogSolicitudes?menu=N&numeroJustificante="+numeroJustificante+"&idConvocatoria="+idConvocatoria+"&paginaActual="+x +"&paginasTotales="+numeroPagina + "&llamada=" + paginar;
					if(x==paginaActual && !(x<= 3 || x+3>numeroPagina)){%>
						&nbsp;<spring:message code="field.puntosPaginacion"/>
						<strong><a href="<%=enlacePagina %>" style="color: #000000;" class="active" ><%=x %></a></strong>
						<spring:message code="field.puntosPaginacion"/>&nbsp;
					<%}else if (x<= 3 || x+3>numeroPagina){
						if(x==paginaActual){%>	
							<strong><a href="<%=enlacePagina %>" style="color: #000000;" class="active"><%=x %></a></strong>
							<%if(x == 3 && (paginaActual<= 3 || paginaActual+3>numeroPagina)){	%>
								&nbsp;<spring:message code="field.puntosPaginacion"/>
							<%}%>
						&nbsp;
						<%}
						else
						{%>	
							<a href="<%=enlacePagina %>" style="color: #000000;" ><%=x %></a>
							<%if(x == 3 && (paginaActual<= 3 || paginaActual+3>numeroPagina)){	%>
								&nbsp;<spring:message code="field.puntosPaginacion"/>
							<%}%>
							&nbsp;
						<%}%>	
					<%}%>
				<%}%>
			<%}%>
			<%if(paginaActual != numeroPagina) { %>			
				
			<a href="detalleLogSolicitudes?paginaActual=<%=paginaActual+1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar&numeroJustificante=<%=numeroJustificante%>&idConvocatoria=<%=idConvocatoria%>">&raquo;</a>
			<% } else{  %>	
			 <a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
			<% }  %> 			
			</div>
			<br>		
			<div class="pae-box-buttons">
				<input type="submit" value="Cerrar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"  onclick="javascript: window.close()">	
	</div>		

		</table>
		</center>
					 
		</logic:greaterThan>
		<logic:lessThan name="numResultados" value="1">
				<div id="error">
					<spring:message code="field.logSolicitudes.error"/>
				</div>
			</logic:lessThan>
	</logic:present>
	<br><br><br>
	</div>
	
</form:form>
	
</div>
</body>
</html>