<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="es.map.ipsg.bean.*" %>
<% String numPag = (String)request.getAttribute("paginaActual"); %>

<script type="text/javascript">

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
	document.forms[0].action = "../spring/buscarConfiguracionErrores";
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
	document.getElementById("paginaActual").value=numPag;
	document.getElementById("accion").value="Ordenar";
}

function comprobarEliminar(visibilidad){
	if (visibilidad == 'true') {
		return confirm('<spring:message code="field.configuracionErrores.mensaje.desactivar"/>');
	}
	else {
		return confirm('<spring:message code="field.configuracionErrores.mensaje.activar"/>');
	}	
		
}

function busqueda(){
	document.getElementById("submit").value="Buscar";
	document.getElementById("accion").value="Buscar";
	document.getElementById("paginaActual").value="1";
	document.getElementById("cambios").value="Correcto";
}

function paginaActual(paginaActual,paginasTotales){
	var buscar = document.getElementById("cambios").value;
	if("Correcto" == buscar){
		document.getElementById("accion").value="Paginar";
		document.getElementById("paginaActual").value=paginaActual;
		document.getElementById("paginasTotales").value=paginasTotales;
	}else{
		document.getElementById("accion").value="Buscar";
		document.getElementById("paginaActual").value=paginaActual;
	}
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
		document.getElementById("cambios").value = "Correcto";
	}
	$("#buttonSubmit").click();
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
		document.getElementById("cambios").value = "Correcto";
	}
	$("#buttonSubmit").click();
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
		document.getElementById("cambios").value = "Correcto";
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
		document.getElementById("cambios").value = "Correcto";
	}
}

function alta(){
	
	document.getElementById("submit").value="Alta";
	window.location.href = "../spring/verCrearConfiguracionErrores";
}

function actualizarConfiguracionErrores(){
	document.getElementById("submit").value="ActualizarConfiguracionErrores";
	window.location.href = "../spring/actualizarConfiguracionErrores";
}
function comprobarRegistros(){
// 	document.getElementById("accion").value = "Paginar";
	document.getElementById("cambios").value = "Incorrecto";
	document.getElementById("paginaActual").value="1";
	numeroPagina = document.getElementById("paginasTotales").value;
	pagina = document.getElementById("paginaActual").value;
	numRegistro = document.getElementById("numRegistro").value;
	window.location.href = "../spring/buscarConfiguracionErrores?paginaActual="+pagina +"&paginasTotales="+numeroPagina +"&llamada=Buscar"+"&numRegistro="+numRegistro; 
	
}
function openWindowActualiza() {
	var extract = new Object();
	ventanaPopup = window.open("../spring/actualizarConfiguracionErroes", 'ActualizarConfiguracionErrores', 'width=650, height=600,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');	    
	ventanaPopup.focus();
}

</script>

<%@page import="es.map.ips.model.ConfiguracionErrores"%>
<%@page import="es.map.ipsg.bean.ConfiguracionErroresBean"%>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title></title>
</head>

<body style="margin-left:0.4em;">

<div class="capaAll">

	<h1 class="pae-title"> <spring:message code="field.configuracionErrores.tituloBuscar"/> </h1>
 	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present> 

<div data-function="accordion-box" class="pae-box">   
	<div class="pae-box__header">
		<h3 class="pae-box__header--title">
			<spring:message code="field.textosIntroduccion.configuracionErrores"/>
		</h3>
	</div>


<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->


<form:form commandName="buscarConfiguracionErroresForm" action="/IPSG/spring/buscarConfiguracionErrores" id="formPadre" method="post">
	<form:hidden path="paginaActual" id="paginaActual"/>
	<form:hidden path="paginasTotales" id="paginasTotales"/>
	<form:hidden path="submit" id="submit"/>
	<form:hidden path="campo" id="campo"/>
	<form:hidden path="direccion" id="direccion"/>
	<form:hidden path="cambios" id="cambios"/>
	<form:hidden path="pulsaIr" id="pulsaIr" />
	<form:hidden path="accion" id="accion" />

<div class="pae-box__body">
	<fieldset id="formulario">
		<div class="pae-layout">
			<div class="pae-layout__item pae-u-8/12 pae-u-2/12-lap pae-u-1/1-palm">
			<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.configuracionErrores.descripcion.mayus"/>
			</span>
			<logic:present name="descripcion" scope="request"></logic:present>
			<form:input type="text" path="descripcion" class="pae-form__input" id="descripcion" maxlength="200"/>
			</div>
			</div>
	</div>
	
<div class="filaBtn">
		<div class="pae-box-buttons ">
		
			<input type="submit" value="Buscar" onclick="busqueda()"  class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>
			<input type="button" value="Alta" onclick="alta()"  class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation mg-left-1"/>
		</div>
	</div>
</div>
</fieldset>
</div>  <!-- cierre class="pae-box__body" -->
</div> <!--  cierre class="pae-box"	 -->
	
<!-- 	<div class="clear"></div><br><br><br> -->
	<jsp:useBean id="configuracionErrores" scope="request" class="java.util.ArrayList"/>		
	<logic:present name="configuracionErrores" scope="request">
		<bean:size id="numResultados" name="configuracionErrores" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
				<table id="paetable" class="pae-table pae-table--margin">
		<caption class="hiddenAccesible">Tabla de Configuración de mensajes</caption>
		<thead class="pae-table__header">
			<tr class="pae-table__row">
			<th class="pae-table__cell-header">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.configuracionErrores.id.mayus"/>
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
											<spring:message code="field.configuracionErrores.descripcion.mayus"/>
										</span>	
									</div>
								</th>
								<th>											
									<div class="titulo_der_tabla">
										<input type="image" 
											src="../images/order_desc.png" value="submit" alt="Submit" 
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
											<spring:message code="field.configuracionErrores.visibilidad.mayus"/>
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
													<spring:message code="field.configuracionErrores.eliminar.mayus"/>	
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
				<% int i = 0; %>
				<logic:iterate id="registro" name="configuracionErrores" >
					<tr class="pae-table__row" name="row">
						<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
							<%
								ConfiguracionErroresBean configuracionErroresBean = (ConfiguracionErroresBean)configuracionErrores.get(i);
								i++;
								String enlace = "../spring/verModificarConfiguracionErrores?id="+ configuracionErroresBean.getId();
							%>
							<span class="pae-table__txt-base pae-table__span-head">
								<a href="<%=enlace %>" paramId="id" paramName="registro" paramProperty="id" style="color: #c33400;">
								<bean:write name="registro" property="id" />
							</span>
							</a>
						</td>				
						<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
							<span class="pae-table__txt-base pae-table__span-head">
								<bean:write name="registro" property="descripcion" />
							</span>
						</td>
						<bean:define id="campoVisibilidad" name="registro" property ="visibilidad" type="Boolean"/>
					<c:choose>
					    <c:when test="${campoVisibilidad eq true }">
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
								<span class="pae-table__txt-base pae-table__span-head">
									<spring:message code="field.general.visible"/>
								</span>
							</td>	
					    </c:when> 
					    <c:otherwise>
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
								<span class="pae-table__txt-base pae-table__span-head">
									<spring:message code="field.general.noVisible"/>
								</span>
							</td>
					    </c:otherwise> 
				    </c:choose>
						<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
							<logic:equal name="registro" property="visibilidad" value="true">								
									<span class="pae-table__txt-base pae-table__span-head">
										<a href="${pageContext.request.contextPath}/spring/eliminarConfiguracionErrores?id=${registro.id}" onclick="return comprobarEliminar(&quot;${registro.visibilidad}&quot;)" paramId="id" paramName="registro" paramProperty="id" style="color: #c33400;">
										<spring:message code="field.configuracionErrores.desactivar"/>	
									</span>	
								</a>
							</logic:equal>
							<logic:notEqual name="registro" property="visibilidad" value="true">								
									<span class="pae-table__txt-base pae-table__span-head">
										<a href="${pageContext.request.contextPath}/spring/eliminarConfiguracionErrores?id=${registro.id}" onclick="return comprobarEliminar(&quot;${registro.visibilidad}&quot;)" paramId="id" paramName="registro" paramProperty="id" style="color: #c33400;">
										<spring:message code="field.configuracionErrores.activar"/>
									</span>		
								</a>
							</logic:notEqual>
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
					
					
<%-- 					<spring:message code="field.Pagina"/>&nbsp; --%>

			<div class="pagination">
			<%if(paginaActual != 1) { %>
				<a href="buscarConfiguracionErrores?paginaActual=<%=paginaActual-1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar">&laquo;</a>
			<% } else{  %>	
			 	<a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
			<% }
			if(numeroPagina <= 6)
			{	
				for(int x = 1 ;x<numeroPagina+1; x++){
					String enlacePagina = "buscarConfiguracionErrores?paginaActual="
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
					String enlacePagina = "buscarConfiguracionErrores?paginaActual="
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
				<a href=buscarConfiguracionErrores?paginaActual=<%=paginaActual+1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar">&raquo;</a>
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
		<logic:present name="accion" scope="request">
				<logic:lessThan name="numResultados" value="1">
						<div id="error">
							<spring:message code="field.categoria.error"/>
						</div>
					</logic:lessThan>
	 </logic:present>
	</logic:present>
	
	</div>  <!-- cierre  capaAll -->
</form:form>
	

</body>
</html>