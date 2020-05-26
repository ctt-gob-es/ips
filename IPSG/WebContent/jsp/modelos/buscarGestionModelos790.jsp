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

function irPagina(pagTotales){
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
	document.forms[0].action = "../spring/buscarGestionModelos790";
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

function comprobarCerrar(){
	return confirm('<spring:message code="field.mensajeCerrarEjercicio"/>');
}
function comprobarReabrir(){
	return confirm('<spring:message code="field.mensajeReabrirEjercicio"/>');
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

function alta(){
	document.getElementById("submit").value="Alta";
	window.location.href = "../spring/verCrearGestionModelos790"; 
}

function comprobarRegistros(){
	document.getElementById("submit").value = "Paginar";
	document.getElementById("cambios").value = "Incorrecto";
	document.getElementById("paginaActual").value="1";
	numeroPagina = document.getElementById("paginasTotales").value;
	pagina = document.getElementById("paginaActual").value;
	numRegistro = document.getElementById("numRegistro").value;
	window.location.href = "../spring/buscarGestionModelos790?paginaActual="+pagina +"&paginasTotales="+numeroPagina+ "&llamada=Buscar" +"&numRegistro="+numRegistro;
}
</script>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title></title>
	</head>
	
	<body style="margin-left:0.4em;">
		
		
		<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->
		
		<form:form modelAttribute="modelo790Form" action="/IPSG/spring/buscarGestionModelos790" id="formPadre" method="post">
			<form:hidden path="paginaActual" id="paginaActual"/>
			<form:hidden path="paginasTotales" id="paginasTotales"/>
			<form:hidden path="submit" id="submit"/>
			<form:hidden path="campo" id="campo"/>
			<form:hidden path="direccion" id="direccion"/>
			<form:hidden path="cambios" id="cambios"/>
			<form:hidden path="pulsaIr" id="pulsaIr" />

		<div class="">
				
		<h1 class="pae-title"><spring:message code="field.gestionModelos790.tituloBuscar"/></h1>	
		
		<logic:present name="org.apache.spring.ERROR" scope="request">
			<div id="error">
				<html:errors/>
			</div>
			<div class="clear"></div>
		</logic:present> 	

		<div data-function="accordion-box" class="pae-box">
			<div class="pae-box__header">
				<h3 class="pae-box__header--title">
					<spring:message code="field.gestionModelos790.modelosAlta" />
				</h3>
			</div>
			<div class="pae-box__body">
				<fieldset>
					<div class="pae-layout">
						<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.centroGestor.numModelo"/>
							</span>
							<form:input type="text" path="numModelo" class="pae-form__input" id="numModelo" maxlength="100"/>
						</div>
					</div>
			
				<div class="filaBtn">
					<div class="pae-box-buttons pd-right-1">
							<input type="submit" value="Buscar" onclick="busqueda()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>
							<input type="button" value="Alta" onclick="alta()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1"/>							
					</div>
				</div>
			</fieldset>		
		</div>
	</div>
		
			
			<logic:present name="modelo790" scope="request">	
				<bean:size id="numResultados" name="modelo790" scope="request"/>
				<logic:greaterThan name="numResultados" value="0">
				<table id="paetable" class="pae-table pae-table--margin">
					<caption class="hiddenAccesible">Tabla de modelos 790</caption>
					<thead class="pae-table__header">
						<tr class="pae-table__row">
							<th class="pae-table__cell-header">
								<table class="tabla_resultadosInterno">
									<tbody>
										<tr>											
											<th>
												<div class="titulo_izq_tabla">									
													<span class="pae-table__txt-title">
														<spring:message code="field.gestionModelos790.id"/>
													</span>	
												</div>
											</th>
											<th>											
												<div class="titulo_der_tabla">
													<input type="image" 
														src="../images/order_desc.png" value="submit" alt="Submit" 
														onclick="ordena(1,<%=numPag %>)"><input type="image" 
														src="../images/order_asc.png" value="submit" alt="Submit" onclick="ordena(2,<%=numPag %>)">
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
														<spring:message code="field.gestionModelos790.descripcion"/>
													</span>	
												</div>
											</th>
											<th>											
												<div class="titulo_der_tabla">
													<input type="image" 
														src="../images/order_desc.png" value="submit" alt="Submit" 
														onclick="ordena(3,<%=numPag %>)"><input type="image" 
														src="../images/order_asc.png" value="submit" alt="Submit" onclick="ordena(4,<%=numPag %>)">
												</div>
											</th>
										</tr>
									</tbody>
								</table>
							</th>									
							<th data-col="col3" class="pae-table__cell-header w10">
								<span class="pae-table__txt-title">
									<spring:message code="field.gestionModelos790.numModelo"/>
								</span>						
							</th>														
						</tr>
					</thead>
					<tbody class="pae-table__body">
						<c:forEach var="registro" items="${modelo790}">				
							<tr class="pae-table__row" name="row">
								<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body">
               						<span class="pae-table__txt-base pae-table__span-head"><a href="${pageContext.request.contextPath}/spring/verModificarModelosGestion790?id=${registro.id}" id="id" style="color: #c33400;">
										<bean:write name="registro" property="id" />
									</a></span>
               					</td>
               					<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body">
               						<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="descripcion" /></span>
               					</td>	
               					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body w10">
               						<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="numModelo" /></span>
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
						</span>
					
					<div class="pagination">
					<%if(paginaActual != 1) { %>
						<a href="buscarGestionModelos790?paginaActual=<%=paginaActual+11%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar">&laquo;</a>
					<% } else{  %>	
			 			<a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
					<% }
	
					if(numeroPagina <= 6){	
						for(int x = 1 ;x<numeroPagina+1; x++){
							String enlacePagina = "buscarGestionModelos790?paginaActual="
									+ x
									+ "&paginasTotales="
									+ numeroPagina
									+ "&llamada=" + paginar;	
							if(x==paginaActual){%>
								<strong><a href="<%=enlacePagina %>" class="active" ><%=x %></a></strong>
								&nbsp;
							<%}else{%>
								<a href="<%=enlacePagina %>" style="color: #000000;" ><%=x %></a>
								&nbsp;
							<%}%>
						<%}%>
					<%}
					else{
						for(int x = 1 ;x<numeroPagina+1; x++){
							String enlacePagina = "buscarGestionModelos790?paginaActual="
									+ x
									+ "&paginasTotales="
									+ numeroPagina
									+ "&llamada=" + paginar;							
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
								else{%>	
									<a href="<%=enlacePagina %>" style="color: #000000;" ><%=x %></a>
									<%if(x == 3 && (paginaActual<= 3 || paginaActual+3>numeroPagina)){	%>
										<div><spring:message code="field.puntosPaginacion"/></div>
									<%}%>									
								<%}%>	
							<%}%>
						<%}%>
					<%}%>
					<%if(paginaActual != numeroPagina) { %>
						<a href="buscarGestionModelos790?paginaActual=<%=paginaActual+1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar">&raquo;</a>
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
								 
				</logic:greaterThan>
				<logic:present name="accion" scope="request">
					<logic:lessThan name="numResultados" value="1">
							<div id="error">
								<spring:message code="field.gestionModelos790.error"/>
							</div>
					</logic:lessThan>
				 </logic:present>
			</logic:present>
		</form:form>
	</body>
</html>