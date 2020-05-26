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
String parametro = (String)request.getAttribute("parametro");
String parametro2 = (String)request.getAttribute("parametro2");
String numRegistro = (String)request.getAttribute("numRegistro");
request.setAttribute("parametro" ,parametro);
request.setAttribute("parametro2" ,parametro2);
%>

<script type="text/javascript">

function ordena(value){
	document.getElementById("submit").value="Ordenar";
	
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
	}
	if(value==5){
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
	}
}

function busqueda(){
	document.getElementById("submit").value="Buscar";
	document.getElementById("cambios").value="Correcto";
}
function cerrar(centro,cuerpo,ejercicio){


	var desc= cuerpo+" - "+ejercicio;
	window.opener.document.forms[1].elements["<%=parametro%>"].value = centro;
	window.opener.document.forms[1].elements["<%=parametro2%>"].value = desc;
	window.opener.document.forms[1].elements["<%=parametro%>"].focus();
	
  	window.close();
}


function paginaActual(paginaActual){
	document.getElementById("paginaActual").value=paginaActual;
	document.getElementById("submit").value="Paginar";
}


function comprobarRegistros(origen){
	document.getElementById("submit").value = "Paginar";
	var param = document.getElementById("parametro").value;
	var param2 = document.getElementById("parametro2").value;
	if (origen==1){
		document.getElementById("cambios").value = "Incorrecto";
		document.getElementById("paginaActual").value="1";
	}	else{
		document.getElementById("cambios").value = "Correcto";
	}
	numeroPagina = document.getElementById("paginasTotales").value;
	pagina = document.getElementById("paginaActual").value;
	numRegistro = document.getElementById("numRegistro").value;
	window.location.href = "../spring/listarConvocatoria?parametro="+param+"&parametro2="+param2+"&paginaActual="+pagina +"&paginasTotales="+numeroPagina +"&numRegistro="+numRegistro+"&llamada=Paginar"; 
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
	comprobarRegistros(0);
	
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
	comprobarRegistros(0);
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
	comprobarRegistros(0);
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
	comprobarRegistros(0);
}

function comprobarBusqueda(){
	document.getElementById("cambios").value="Incorrecto";
	
}


</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body style="margin-left:0.4em;" class="background-color-white-ip">
<div class="pae-body2" style="overflow-y:hidden">
	<form:form modelAttribute="crearConvocatoriaForm" action="/IPSG/spring/listarConvocatoria" id="formHijo" method="post">
	 <div class="pae-layout__item">
	 	<form:hidden path="submit" id="submit"/> 
		<form:hidden path="parametro" id="parametro"/>
		<form:hidden path="parametro2" id="parametro2"/>
		<form:hidden path="paginaActual" id="paginaActual"/>
		<form:hidden path="paginasTotales" id="paginasTotales"/>
		<form:hidden path="campo" id="campo"/>
		<form:hidden path="direccion" id="direccion"/>
		<form:hidden path="cambios" id="cambios" value="Correcto"/>
		
		
		<div class="pae-title2"><spring:message code="field.convocatorias.listarConvocatorias"/></div>
		
		<div class="pae-box">	
			<div class="pae-box__header2">	
				<fieldset>		
					<div class="pae-layout__item">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.convocatoria.idConvocatoria"/>
							</span>
							<form:input type="text" path="idConvocatoria" class="pae-form__input w100" onchange="comprobarBusqueda()" maxlength="22"/>
						</div>
					</div>
					<div class="pae-layout__item">
						<div class="pae-box-buttons">
							<input type="submit" name="submit" value="Buscar" onclick="busqueda()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>
						</div>	
					</div>	
				</fieldset>
			</div>
	   </div>	
		
	
	<div class="clear"></div>
	<br><br><br>	
	<jsp:useBean id="convocatorias" scope="request" class="java.util.ArrayList"/>
		<%int i=0; %>
		<logic:present name="convocatorias" scope="request">
			<bean:size id="numResultados" name="convocatorias" scope="request"/>
			<logic:greaterThan name="numResultados" value="0">
				<table class="pae-table pae-table--margin">
					<thead class="pae-table__header">
	             		<tr class="pae-table__row">
	             			<th class="pae-table__cell-header">
							<table class="tabla_resultadosInterno">
								<tbody>
									<tr>											
										<th>
											<div class="titulo_izq_tabla">									
												<span class="pae-table__txt-title">
													<spring:message code="field.convocatoria.idMay"/>
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
													<spring:message code="field.convocatoria.ejercicioMay"/>
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
													<spring:message code="field.convocatoria.cuerpoEscalaMay"/>
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
													<spring:message code="field.convocatoria.formaAccesoMay"/>
												</span>	
											</div>
										</th>
										<th>											
											<div class="titulo_der_tabla">
												<input type="image" 
													src="../images/order_desc.png" value="submit" alt="Submit" 
													onclick="ordena(7,<%=numPag %>)"><input type="image" 
													src="../images/order_asc.png" value="Submit" alt="Submit" onclick="ordena(8,<%=numPag %>)">
											</div>
										</th>
									</tr>
								</tbody>
							</table>
						</th>	             		
					</tr>
					</thead>
					<tbody class="pae-table__body">
					<logic:iterate id="registro" name="convocatorias" >					
	               		<tr class="pae-table__row" name="row">
	                		<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body_centroGest">
	              					<span class="pae-table__txt-base pae-table__span-head">
										<a href="" style="color: #c33400;" onclick="javascript:cerrar('<bean:write name="registro" property="idConvocatoria" />','<bean:write name="registro" property="cuerpoEscala" />','<bean:write name="registro" property="ejercicio" />')">  <font color="#c33400"><bean:write name="registro" property="idConvocatoria" /></font> <a/>
										</a>
									</span>	
							</td>
	                		<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body_centroGest">
	              					<span class="pae-table__txt-base pae-table__span-head">
	              						<bean:write name="registro" property="ejercicio" />
	              					</span>
	              			</td>
	                		<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body_centroGest">
	              					<span class="pae-table__txt-base pae-table__span-head">
	              						<bean:write name="registro" property="cuerpoEscala" />
	              					</span>	
	              			</td>
	                		<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body_centroGest">
	              					<span class="pae-table__txt-base pae-table__span-head">
	              						<bean:write name="registro" property="formaAcceso" />
	              					</span>	
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
						<a href="listarConvocatoria?paginaActual=<%=paginaActual-1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar&parametro=${parametro}&parametro2=${parametro2}&centro=${centro}"">&laquo;</a>
					<% } else{  %>	
			 			<a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
					<% }
			if(numeroPagina <= 6)
			{	
				for(int x = 1 ;x<numeroPagina+1; x++){
					String enlacePagina = "listarConvocatoria?paginaActual="
						+ x
						+ "&paginasTotales="
						+ numeroPagina
						+ "&llamada=" + paginar+ "&parametro="+parametro + "&parametro2="+parametro2+"&numRegistro="+numRegistro;
					if(x==paginaActual){%>
						<a href="<%= enlacePagina %>" class="active" ><%=x %></a></strong>
						 
					<%}else{%>
						<a href="<%= enlacePagina %>" ><%=x %></a>
						 
					<%}%>
				<%}%>
			<%}
			else
			{
				for(int x = 1 ;x<numeroPagina+1; x++){
					String enlacePagina = "listarConvocatoria?paginaActual="
						+ x
						+ "&paginasTotales="
						+ numeroPagina
						+ "&llamada=" + paginar+ "&parametro="+parametro + "&parametro2="+parametro2+"&numRegistro="+numRegistro;
					if(x==paginaActual && !(x<= 3 || x+3>numeroPagina)){%>
						<div><spring:message code="field.puntosPaginacion"/></div>
						<a href="<%= enlacePagina %>" class="active" ><%=x %></a>
						<div><spring:message code="field.puntosPaginacion"/></div>
					<%}else if (x<= 3 || x+3>numeroPagina){
						if(x==paginaActual){%>	
							<a href="<%= enlacePagina %>" class="active" ><%=x %></a></strong>
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
				<a href="listarConvocatoria?paginaActual=<%=paginaActual+1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar&parametro=${parametro}&parametro2=${parametro2}&centro=${centro}"">&raquo;</a>
			<% } else{  %>	
			 <a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
			<% }  %> 			
			</div>
			
			</div>


			</logic:greaterThan>
			<logic:present name="submit" scope="request">
				<logic:lessThan name="numResultados" value="1">
					<div id="error">
						<spring:message code="field.convocatoria.error"/>
					</div>
				</logic:lessThan>
			</logic:present>
		</logic:present>
		</div>
	</form:form>
</div>
</body>
</html>