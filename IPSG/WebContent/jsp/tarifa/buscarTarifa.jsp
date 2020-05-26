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
String sVieneMenu = (String)request.getAttribute("sVieneMenu"); 
String numPag = (String)request.getAttribute("paginaActual");
%>

<script type="text/javascript">
function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
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
	document.getElementById("accion").value="Paginar";
	document.getElementById("numeroPaginaIr").value = actualInt;
	document.getElementById("paginaActual").value=actualInt;
	document.getElementById("paginasTotales").value=pagTotales;
	document.forms[0].action = "../spring/buscarTarifa";
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
	if(value==13){
		document.getElementById("campo").value ="7";
		document.getElementById("direccion").value="up";
	}
	if(value==14){
		document.getElementById("campo").value ="7";
		document.getElementById("direccion").value="down";
	}
	if(value==15){
		document.getElementById("campo").value ="8";
		document.getElementById("direccion").value="up";
	}
	if(value==16){
		document.getElementById("campo").value ="8";
		document.getElementById("direccion").value="down";
	}	
	if(value==17){
		document.getElementById("campo").value ="9";
		document.getElementById("direccion").value="up";
	}
	if(value==18){
		document.getElementById("campo").value ="9";
		document.getElementById("direccion").value="down";
	}
	document.getElementById("paginaActual").value=numPag;
	document.getElementById("submit").value="Ordenar";
	document.getElementById("accion").value="Ordenar";
}

function comprobarEliminar(){
	return confirm('<spring:message code="field.mensajeEliminar"/>');
}

function busqueda(){
	document.getElementById("paginaActual").value="1";
	document.getElementById("accion").value="Buscar";
	document.getElementById("submit").value="Buscar";
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

function comprobarBusqueda(){
	document.getElementById("cambios").value="Incorrecto";
}
///
//Al cambiar por el número de Registros por página  
function comprobarRegistros(){
	document.getElementById("submit").value = "Paginar";
	document.getElementById("accion").value = "Paginar";
	document.getElementById("cambios").value = "Incorrecto";
	document.getElementById("paginaActual").value="1";
	numeroPagina = document.getElementById("paginasTotales").value;
	pagina = document.getElementById("paginaActual").value;
	numRegistro = document.getElementById("numRegistro").value;
	window.location.href = "../spring/buscarTarifa?paginaActual="+pagina +"&paginasTotales="+numeroPagina +"&llamada=Buscar"+"&numRegistro="+numRegistro; 
}
function cambiarCombo ()
{
	document.getElementById("paginaActual").value="1";
}

function llamada(){
	document.getElementById("submit").value="Alta";
	document.forms[1].action="../spring/verCrearTarifa";
}
</script>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 
<title></title>
</head>

<body style="margin-left:0.4em;">
<div class="capaAll">


 <h1 class="pae-title"> <spring:message code="field.tarifa.solicitudBuscar"/> </h1>
 <logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
</logic:present>
	
 <div data-function="accordion-box" class="pae-box">   
	<div class="pae-box__header">
		<h3 class="pae-box__header--title">
			<spring:message code="field.textosIntroduccion.tarifa"/>
		</h3>
 	</div>

<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->


<form:form modelAttribute="tarifaForm" action="/IPSG/spring/buscarTarifa" id="formPadre" method="post">

	<form:hidden path="paginaActual" id="paginaActual"/>
	<form:hidden path="paginasTotales" id="paginasTotales"/>
	<form:hidden path="submit" id="submit"/>
	<form:hidden path="campo" id="campo"/>
	<form:hidden path="direccion" id="direccion"/>
	<input type="hidden" name="menu" value="N" id="menu">
	<form:hidden path="accion" id="accion"/>
	<form:hidden path="cambios" id="cambios"/>
	<form:hidden path="pulsaIr" id="pulsaIr" />

<div class="pae-box__body">
<fieldset id="formulario">
<div class="pae-layout">
	<div class="pae-layout__item pae-u-5/12 pae-u-2/12-lap pae-u-1/1-palm">
		<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
			<spring:message code="field.descripcionMay"/>
			</span>
			<form:input path="descripcion" id="descripcion" class="pae-form__input" maxlength="50"/>
		</div>
	</div>
	<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
		<div class="pae-form__cell">
			<span class="pae-form__label pae-form__span-label">
				<spring:message code="field.ejercicioMay"/>
			</span>
			<logic:present name="ejercicio" scope="request">
			</logic:present>
			<form:input path="ejercicio" class="pae-form__input" onkeypress="return isNumber(event)" maxlength="4"/>
		</div>
	</div>
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
			<div class="pae-form__cell">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.grupoMay"/>
				</span>
		
			<form:select path="idGrupo" class="pae-form__input" id="idGrupo" >
					<option value="0">         </option>
					<logic:present name="grupo" scope="request">				
						<bean:size id="numgrupo" name="grupo" scope="request"/>
							<logic:greaterThan name="grupo" value="0">
								<form:options items="${grupo}" itemValue="id" itemLabel="descripcion" />
							</logic:greaterThan>
					</logic:present>
			</form:select>
			</div>
		</div>
		
		<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
			<div class="pae-form__cell">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.tipoAccesoMay"/>
				</span>
		
				<form:select path="idTipoAcceso" class="pae-form__input"  id="idTipoAcceso" >
						<option value="">         </option>
						<logic:present name="tipoAcceso" scope="request">
							<bean:size id="numtipoAcceso" name="tipoAcceso" scope="request"/>
								<logic:greaterThan name="tipoAcceso" value="0">
									<form:options items="${tipoAcceso}" itemValue="id" itemLabel="codigo" />
								</logic:greaterThan>
						</logic:present>
				</form:select>
			</div>
		</div>			
 </div> <!-- cierre class="pae-layout" -->



 	<div class="filaBtn">
		<div class="pae-box-buttons">
			<input type="submit" value="Buscar" onclick="busqueda()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline">
			<input type="submit" value="Alta" onclick="llamada()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline mg-left-1">
		</div>
	</div>

	
</fieldset>
 </div>	  <!--class="pae-box__body" -->
</div>  <!-- cierra class="pae-box" -->

<%	
	
	
%>
	<logic:present name="tarifa" scope="request">	
		<bean:size id="numResultados" name="tarifa" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
		<table id="paetable" class="pae-table pae-table--margin">
			<caption class="hiddenAccesible">Tabla de tarifas</caption>
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
													src="../images/order_desc.png" value="submit" alt="Ascendente" 
													onclick="ordena(1,<%=numPag %>)"><input type="image" 
													src="../images/order_asc.png" value="Submit" alt="Descendente" onclick="ordena(2,<%=numPag %>)">
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
													<spring:message code="field.grupoMay"/>
												</span>	
											</div>
										</th>
										<th>											
											<div class="titulo_der_tabla">
												<input type="image" 
													src="../images/order_desc.png" value="submit" alt="Ascendente" 
													onclick="ordena(3,<%=numPag %>)"><input type="image" 
													src="../images/order_asc.png" value="Submit" alt="Descendente" onclick="ordena(4,<%=numPag %>)">
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
													<spring:message code="field.tipoAccesoMay"/>
												</span>	
											</div>
										</th>
										<th>											
											<div class="titulo_der_tabla">
												<input type="image" 
													src="../images/order_desc.png" value="submit" alt="Ascendente" 
													onclick="ordena(5,<%=numPag %>)"><input type="image" 
													src="../images/order_asc.png" value="Submit" alt="Descendente" onclick="ordena(6,<%=numPag %>)">
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
													<spring:message code="field.descripcionMay"/>
												</span>	
											</div>
										</th>
										<th>											
											<div class="titulo_der_tabla">
												<input type="image" 
													src="../images/order_desc.png" value="submit" alt="Ascendente" 
													onclick="ordena(7,<%=numPag %>)"><input type="image" 
													src="../images/order_asc.png" value="Submit" alt="Descendente" onclick="ordena(8,<%=numPag %>)">
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
													<spring:message code="field.importeMay"/>
												</span>	
											</div>
										</th>
										<th>											
											<div class="titulo_der_tabla">
												<input type="image" 
													src="../images/order_desc.png" value="submit" alt="Ascendente" 
													onclick="ordena(9,<%=numPag %>)"><input type="image" 
													src="../images/order_asc.png" value="Submit" alt="Descendente" onclick="ordena(10,<%=numPag %>)">
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
													<spring:message code="field.ejercicioMay"/>
												</span>	
											</div>
										</th>
										<th>											
											<div class="titulo_der_tabla">
												<input type="image" 
													src="../images/order_desc.png" value="submit" alt="Ascendente" 
													onclick="ordena(11,<%=numPag %>)"><input type="image" 
													src="../images/order_asc.png" value="Submit" alt="Descendente" onclick="ordena(12,<%=numPag %>)">
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
			<logic:iterate id="registro" name="tarifa" >
				<tr class="pae-table__row" name="row">
					<td data-content="" data-col="col5" data-function="fc-collapse-table" class="pae-table__cell-body">						
						<span class="pae-table__txt-base pae-table__span-head">
							<a href="${pageContext.request.contextPath}/spring/verModificarTarifa?id=${registro.id}" style="color: #c33400;">
							<bean:write name="registro" property="id" />
						</span>
					</a></td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body w35">
						<span class="pae-table__txt-base pae-table__span-head">
						<bean:write name="registro" property="desGrupo" />
						</span>
					</td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
					<span class="pae-table__txt-base pae-table__span-head"> 
					<bean:write name="registro" property="desTipoAcceso" />
					</span>
					</td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
					<span class="pae-table__txt-base pae-table__span-head"> 
					<bean:write name="registro" property="descripcion" />
					</span>
					</td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
					<span class="pae-table__txt-base pae-table__span-head"> 
					<bean:write name="registro" property="importe" />
					</span>
					</td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">
					<span class="pae-table__txt-base pae-table__span-head"> 
					<bean:write name="registro" property="ejercicio" />
					</span>
					</td>
					<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body">					
						<span class="pae-table__txt-base pae-table__span-head"> 
							<a href="${pageContext.request.contextPath}/spring/eliminarTarifa?id=${registro.id}" style="color: #c33400;" onclick="return comprobarEliminar();">
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
				<a href="buscarTarifa?paginaActual=<%=paginaActual-1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar" >&laquo;</a>
			<% } else{  %>	
			 	<a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
			<% } 
			if(numeroPagina <= 6)
			{	
				for(int x = 1 ;x<numeroPagina+1; x++){
					String enlacePagina = "buscarTarifa?paginaActual="
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
					String enlacePagina = "buscarTarifa?paginaActual="
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
				<a href="buscarTarifa?paginaActual=<%=paginaActual+1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar" >&raquo;</a>
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
			 
		<!--  Selección Paginación de Registros -->
			<div class="clear"></div>	
		</logic:greaterThan>		
		<logic:present name="accion" scope="request">
				<logic:lessThan name="numResultados" value="1">
						<div id="error">
							<spring:message code="field.tarifa.error"/>
						</div>
					</logic:lessThan>
		 </logic:present>
		 </logic:present>
		 
		 </div> <!--  cierre class="capaAll" -->
</form:form>
</body>
</html>