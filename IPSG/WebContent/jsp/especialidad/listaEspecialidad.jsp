<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean"%>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html"%>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-general.css"/>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/pae-modules.css"/>
<%@ page import="es.map.ipsg.bean.*"%>

<%
	String numPag = (String) request.getAttribute("paginaActual");
	String parametro = (String) request.getAttribute("parametro");
	String parametro2 = (String) request.getAttribute("parametro2");
	String centro = (String)request.getAttribute("centro");
	request.setAttribute("parametro" ,parametro);
	request.setAttribute("parametro2" ,parametro2);
	request.setAttribute("centro" ,centro);
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
}

function busqueda(){
	document.getElementById("submit").value="Buscar";
	document.getElementById("cambios").value="Correcto";
}

function cerrar(especialidad, desc){
	window.opener.document.forms[1].elements["<%=parametro%>"].value = especialidad;
	window.opener.document.forms[1].elements["<%=parametro2%>"].value = desc;
	window.opener.document.forms[1].elements["<%=parametro%>"].focus();
  	window.close();
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

function comprobarBusqueda(){
	document.getElementById("cambios").value="Incorrecto";
}

function comprobarRegistros(){
	var param = document.getElementById("parametro").value;
	var param2 = document.getElementById("parametro2").value;
	document.getElementById("submit").value = "Paginar";
	//document.getElementById("cambios").value = "Incorrecto";
	document.getElementById("paginaActual").value="1";
	numeroPagina = document.getElementById("paginasTotales").value;
	pagina = document.getElementById("paginaActual").value;
	centroGestor = document.getElementById("centro").value;
	numRegistro = document.getElementById("numRegistro").value;
	window.location.href = "../spring/listarEspecialidad?paginaActual="+pagina +"&paginasTotales="+numeroPagina +"&llamada=Buscar&centro="+centroGestor +"&numRegistro="+numRegistro+ "&parametro="+param+ "&parametro2="+param2;
}

</script>

<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title></title>
	</head>

	<body style="margin-left: 0.4em;" class="background-color-white-ip">
		<div class="pae-body2" style="overflow-y:hidden">				
			
			<form:form commandName="especialidadForm" action="/IPSG/spring/listarEspecialidad" id="formPadre" method="post">	
				<div class="pae-layout__item">
					<form:hidden path="submit" id="submit" />
					<form:hidden path="centro" id="centro" />
					<form:hidden path="parametro" id="parametro" />
					<form:hidden path="parametro2" id="parametro2" />
					<form:hidden path="paginaActual" id="paginaActual" />
					<form:hidden path="paginasTotales" id="paginasTotales" />
					<form:hidden path="campo" id="campo" />
					<form:hidden path="direccion" id="direccion" />
					<form:hidden path="cambios" id="cambios" />
					<form:hidden path="listarTodos" id="listarTodos" />
					
					<h1 class="pae-title2"> <spring:message code="field.especialidad.listarEspecialidad" /> </h1>	
					
					<div class="pae-box">	
						<div class="pae-box__header2">
							<fieldset>
								<div class="pae-layout">
									<div class="pae-layout__item">
										<div class="pae-form__cell">
											<span class="pae-form__label pae-form__span-label">
												<spring:message code="field.especialidad.id.mayus" />
											</span>
											<form:input type="text" path="id" class="pae-form__input w100" onchange="comprobarBusqueda()"/>
										</div>
									</div>
									<div class="pae-layout__item">
										<div class="pae-form__cell">
											<span class="pae-form__label pae-form__span-label">
												<spring:message code="field.especialidad.descripcion.mayus" />
											</span>
											<form:input type="text" path="descripcion" class="pae-form__input w100" onchange="comprobarBusqueda()"/>
										</div>
									</div>
									<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-3/1-palm">
										<div class="pae-box-buttons">
											<input type="submit"  value="Buscar" onclick="busqueda()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>
										</div>
									</div>		
								</div>	
							</fieldset>
						</div>
					</div>
				<jsp:useBean id="especialidades" scope="request"
					class="java.util.ArrayList" />
				<%
					int i = 0;
				%>
				<logic:present name="especialidades" scope="request">
					<bean:size id="numResultados" name="especialidades" scope="request" />
					<logic:greaterThan name="numResultados" value="0">
						<table id="paetable" class="pae-table pae-table--margin">
							<caption class="hiddenAccesible">Tabla de Lista Especialidades</caption>
							<thead class="pae-table__header">
			             		<tr class="pae-table__row">
			             			<th class="pae-table__cell-header">
										<table class="tabla_resultadosInterno">
											<tbody>
												<tr>											
													<th>
														<div class="titulo_izq_tabla">									
															<span class="pae-table__txt-title">
																<spring:message code="field.especialidad.id.mayus" />
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
																<spring:message code="field.especialidad.descripcion.mayus" /> 
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
							</tr>
							</thead>
							<tbody class="pae-table__body">
							<logic:iterate id="registro" name="especialidades">
								<tr class="pae-table__row" name="row">
									<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body">
									<a href="" style="color: #c33400;"
									onclick="javascript:cerrar('<bean:write name="registro" property="id" />','<bean:write name="registro" property="descripcion" />')" />
									<font color="#c33400">
										<bean:write name="registro" property="id" />
									</font>
									</a>
									</td>
									<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body">
									<span class="pae-table__txt-base pae-table__span-head">
										<bean:write name="registro" property="descripcion" />
									</span>
									</td>
								</tr>
							</logic:iterate>
							</tbody>
						</table>
						
							<%
								Object numPat = request.getAttribute("paginasTotales");
											Object pagActual = request.getAttribute("paginaActual");
											Object numRegistro = request.getAttribute("numRegistro");
											int numeroPagina = Integer.parseInt(numPat.toString());
											int paginaActual = Integer.parseInt(pagActual.toString());
											String paginar = "Paginar";
							%>
							
				<div class="capaPaginacion" id="cap">
							
							<span class="pae-form__span-label-regxp">
								<spring:message code="field.numRegistros"/>
							</span>
									<form:select path="numRegistro" id="numRegistro" onchange="comprobarRegistros()" class="pae-form__input wauto">
										<form:option value="10"><spring:message code="field.10Registros"/></form:option>
										<form:option value="20"><spring:message code="field.20Registros"/></form:option>
										<form:option value="50"><spring:message code="field.50Registros"/></form:option>
									</form:select>
								
								
			<!-- 				<spring:message code="field.Pagina"/>&nbsp; -->
						
						<div class="pagination">
							<%if(paginaActual != 1) { %>
									<a href="listarEspecialidad?paginaActual=<%=paginaActual-1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar&parametro=${parametro}&parametro2=${parametro2}&centro=${centro}">&laquo;</a>
								<% } else{  %>	
			 						<a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
								<% } 
							if(numeroPagina <= 6)
							{	
								for(int x = 1 ;x<numeroPagina+1; x++){
									String enlacePagina = "listarEspecialidad?paginaActual="
										+ x
										+ "&paginasTotales="
										+ numeroPagina
										+ "&llamada=" + paginar+ "&parametro="+parametro + "&parametro2="+parametro2+"&numRegistro="+numRegistro+"&centro="+centro;
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
									String enlacePagina = "listarEspecialidad?paginaActual="
										+ x
										+ "&paginasTotales="
										+ numeroPagina
										+ "&llamada=" + paginar+ "&parametro="+parametro + "&parametro2="+parametro2+"&numRegistro="+numRegistro+"&centro="+centro;
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
								<a href="listarEspecialidad?paginaActual=<%=paginaActual+1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar&parametro=${parametro}&parametro2=${parametro2}&centro=${centro}"">&raquo;</a>
							<% } else{  %>	
			 					<a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
							<% }  %>							
							</div>
							
							</div>
						
					</logic:greaterThan>
					<logic:present name="submit" scope="request">
						<logic:lessThan name="numResultados" value="1">
							<div id="error">
								<div id="rellenarCampos" class="pae-alertbox pae-alertbox--warning">
									<p class="pae-alertbox__text fw-bold">Errores:</p>
									<ul>
										<li>
											<p class="pae-alertbox__text"><spring:message
											code="field.especialidad.error" /></p>
										</li>
									</ul>
								</div>
							</div>
						</logic:lessThan>
					</logic:present>
				</logic:present>
				
				</div>
			</form:form>
		</div>
	</body>
</html>