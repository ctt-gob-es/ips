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
	document.forms[0].action = "../spring/buscarEntidadFinanciera";
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
	document.getElementById("accion").value="Ordenar";
}

function comprobarEliminar(estado){
	if (estado == 'A') {
		return confirm('<spring:message code="field.entidadFinanciera.mensaje.desactivar"/>');
	}
	else {
		return confirm('<spring:message code="field.entidadFinanciera.mensaje.activar"/>');
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
	window.location.href = "../spring/verCrearEntidadFinanciera";
}

function actualizarEntidades(){
	document.getElementById("submit").value="ActualizarEntidades";
	window.location.href = "../spring/actualizarEntidadFinanciera";
}
function comprobarRegistros(){
// 	document.getElementById("accion").value = "Paginar";
	document.getElementById("cambios").value = "Incorrecto";
	document.getElementById("paginaActual").value="1";
	numeroPagina = document.getElementById("paginasTotales").value;
	pagina = document.getElementById("paginaActual").value;
	numRegistro = document.getElementById("numRegistro").value;
	window.location.href = "../spring/buscarEntidadFinanciera?paginaActual="+pagina +"&paginasTotales="+numeroPagina +"&llamada=Buscar"+"&numRegistro="+numRegistro; 
	
}
function openWindowActualiza() {
	var extract = new Object();
	ventanaPopup = window.open("../spring/actualizarEntidadFinanciera", 'ActualizarEntidades', 'width=650, height=600,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');	    
	ventanaPopup.focus();
}

</script>

<%@page import="es.map.ips.model.EntidadFinanciera"%>
<%@page import="es.map.ipsg.bean.EntidadFinancieraBean"%>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title></title>
</head>

<body style="margin-left:0.4em;">

<div class="capaAll">

	<h1 class="pae-title"> <spring:message code="field.entidadFinanciera.tituloBuscar"/> </h1>
 	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present> 

<div data-function="accordion-box" class="pae-box">   
	<div class="pae-box__header">
		<h3 class="pae-box__header--title">
			<spring:message code="field.textosIntroduccion.entidadesFinancieras"/>
		</h3>
	</div>


<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->


<form:form commandName="buscarEntidadFinancieraForm" action="/IPSG/spring/buscarEntidadFinanciera" id="formPadre" method="post">
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
			<div class="pae-layout__item pae-u-5/12 pae-u-2/12-lap pae-u-1/1-palm">
			<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.entidadFinanciera.descripcion.mayus"/>
			</span>
			<logic:present name="descripcion" scope="request"></logic:present>
			<form:input type="text" path="descripcion" class="pae-form__input" id="descripcion" maxlength="100"/>
			</div>
			</div>
			<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
			<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.estadoMay"/>
			</span>
			<form:select path="estado" id="estado" class="pae-form__input" >
		    	 <form:option value=""> </form:option>
		         <form:option value="A"><spring:message code="field.estado.activo.mayus"/></form:option>
		         <form:option value="D"><spring:message code="field.estado.desactivado.mayus"/></form:option>
			</form:select>
<!--				<bean:write name="buscarEntidadFinancieraForm" property="estado"/>-->
			</div>
			</div>
	</div>
	
<div class="filaBtn">
		<div class="pae-box-buttons ">
		
			<input type="submit" value="Buscar" onclick="busqueda()"  class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>
			<input type="button" value="Alta" onclick="alta()"  class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation mg-left-1"/>
			<input type="button" value="Actualizar Entidades" onclick="openWindowActualiza()"  class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation mg-left-1"/>	
		</div>
	</div>
</div>
</fieldset>
</div>  <!-- cierre class="pae-box__body" -->
</div> <!--  cierre class="pae-box"	 -->
	
<!-- 	<div class="clear"></div><br><br><br> -->
	<jsp:useBean id="entidadFinanciera" scope="request" class="java.util.ArrayList"/>		
	<logic:present name="entidadFinanciera" scope="request">
		<bean:size id="numResultados" name="entidadFinanciera" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
				<table id="paetable" class="pae-table pae-table--margin">
		<caption class="hiddenAccesible">Tabla de Entidad Finaciera</caption>
		<thead class="pae-table__header">
			<tr class="pae-table__row">
				<th class="pae-table__cell-header">
					<table class="tabla_resultadosInterno">
						<tbody>
							<tr>											
								<th>
									<div class="titulo_izq_tabla">									
										<span class="pae-table__txt-title">
											<spring:message code="field.entidadFinanciera.id.mayus"/>
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
											<spring:message code="field.entidadFinanciera.codigo.mayus"/>
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
											<spring:message code="field.entidadFinanciera.descripcion.mayus"/>
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
											<spring:message code="field.entidadFinanciera.tipoPago.mayus"/>
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
													<spring:message code="field.entidadFinanciera.estado.mayus"/>
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
													<spring:message code="field.entidadFinanciera.actualizada.mayus"/>
												</span>	
											</div>
										</th>
										<th>											
											<div class="titulo_der_tabla">
												<input type="image" 
													src="../images/order_desc.png" value="Submit" alt="Submit" 
													onclick="ordena(11,<%=numPag %>)"><input type="image" 
													src="../images/order_asc.png" value="Submit" alt="Submit" onclick="ordena(12,<%=numPag %>)">
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
													<spring:message code="field.entidadFinanciera.eliminar.mayus"/>	
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
				<logic:iterate id="registro" name="entidadFinanciera" >
					<tr class="pae-table__row" name="row">
						<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
							<%
								EntidadFinancieraBean entidadFinancieraBean = (EntidadFinancieraBean)entidadFinanciera.get(i);
								i++;
								String enlace = "../spring/verModificarEntidadFinanciera?id="+ entidadFinancieraBean.getId();
							%>
							<span class="pae-table__txt-base pae-table__span-head">
								<a href="<%=enlace %>" paramId="id" paramName="registro" paramProperty="id" style="color: #c33400;">
								<bean:write name="registro" property="id" />
							</span>
							</a>
						</td>
						<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
							<span class="pae-table__txt-base pae-table__span-head">
								<bean:write name="registro" property="codigo" />
							</span>
						</td>					
						<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
							<span class="pae-table__txt-base pae-table__span-head">
								<bean:write name="registro" property="descripcion" />
							</span>
						</td>
						<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
							<span class="pae-table__txt-base pae-table__span-head">
								<bean:write name="registro" property="desTipoPago" />
							</span>
						</td>
						<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
							<span class="pae-table__txt-base pae-table__span-head">
								<bean:write name="registro" property="desEstado" />
							</span>
						</td>
						<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
							<span class="pae-table__txt-base pae-table__span-head">
								<bean:write name="registro" property="desActualizada" />
							</span>
						</td>
						<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
							<logic:equal name="registro" property="estado" value="A">								
									<span class="pae-table__txt-base pae-table__span-head">
										<a href="${pageContext.request.contextPath}/spring/eliminarEntidadFinanciera?id=${registro.id}" onclick="return comprobarEliminar(&quot;${registro.estado}&quot;)" paramId="id" paramName="registro" paramProperty="id" style="color: #c33400;">
										<spring:message code="field.entidadFinanciera.desactivar"/>	
									</span>	
								</a>
							</logic:equal>
							<logic:notEqual name="registro" property="estado" value="A">								
									<span class="pae-table__txt-base pae-table__span-head">
										<a href="${pageContext.request.contextPath}/spring/eliminarEntidadFinanciera?id=${registro.id}" onclick="return comprobarEliminar(&quot;${registro.estado}&quot;)" paramId="id" paramName="registro" paramProperty="id" style="color: #c33400;">
										<spring:message code="field.entidadFinanciera.activar"/>
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
				<a href="buscarEntidadFinanciera?paginaActual=<%=paginaActual-1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar">&laquo;</a>
			<% } else{  %>	
			 	<a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
			<% }
			if(numeroPagina <= 6)
			{	
				for(int x = 1 ;x<numeroPagina+1; x++){
					String enlacePagina = "buscarEntidadFinanciera?paginaActual="
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
					String enlacePagina = "buscarEntidadFinanciera?paginaActual="
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
				<a href=buscarEntidadFinanciera?paginaActual=<%=paginaActual+1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar">&raquo;</a>
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