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
	document.getElementById("accion").value="Paginar";
	document.getElementById("numeroPaginaIr").value = actualInt;
	document.getElementById("paginaActual").value=actualInt;
	document.getElementById("paginasTotales").value=pagTotales;
	document.forms[0].action = "../spring/consultarResultadosServiciosExternos";
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
		document.getElementById("campo").value = "4";
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

function busqueda(){
	document.getElementById("accion").value="Buscar";
	document.getElementById("cambios").value="Correcto";
}

function paginaActual(paginaActual){
	document.getElementById("paginaActual").value=paginaActual;
	document.getElementById("accion").value="Paginar";
}

function paginaSiguiente(pag,paginasTotales){
	var next=pag+1;
	var buscar = document.getElementById("cambios").value;
	if("Correcto" == buscar){
		document.getElementById("accion").value="Paginar";
		document.getElementById("paginaActual").value=next;
		document.getElementById("paginasTotales").value=paginasTotales;
		document.forms[1].submit();
	}else{
		document.getElementById("accion").value="Buscar";
		document.getElementById("paginaActual").value=pag;
		document.forms[1].submit();
	}
}

function paginaAnterior(pag,paginasTotales){
	var next=pag-1;
	var buscar = document.getElementById("cambios").value;
	if("Correcto" == buscar){		
		document.getElementById("accion").value="Paginar";
		document.getElementById("paginaActual").value=next;
		document.getElementById("paginasTotales").value=paginasTotales;
		document.forms[1].submit();
	}else{
		document.getElementById("accion").value="Buscar";
		document.getElementById("paginaActual").value=pag;
		document.forms[1].submit();
	}
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
	}
}

function irAPagina(paginaActual, paginasTotales){
	document.getElementById("accion").value = "Paginar";
	document.getElementById("paginaActual").value = paginaActual;
	document.getElementById("paginasTotales").value = paginasTotales;
	document.forms[1].submit();
}

function comprobarBusqueda(){
	document.getElementById("cambios").value="Incorrecto";
}

function comprobarRegistros(){
	document.getElementById("accion").value = "Paginar";
	document.getElementById("cambios").value = "Incorrecto";
	document.getElementById("paginaActual").value="1";
	numeroPagina = document.getElementById("paginasTotales").value;
	pagina = document.getElementById("paginaActual").value;
	numRegistro = document.getElementById("numRegistro").value;
	window.location.href = "../spring/consultarResultadosServiciosExternos?paginaActual="+pagina +"&paginasTotales="+numeroPagina +"&llamada=Buscar"+"&numRegistro="+numRegistro; 
}

</script>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 
<title></title>
</head>

<body style="margin-left:0.4em;">

<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->

<form:form commandName="consultaServiciosExternosForm" action="/IPSG/spring/consultarResultadosServiciosExternos?menu=N" id="formPadre" method="post">
	<form:hidden path="paginaActual" id="paginaActual"/>
	<form:hidden path="paginasTotales" id="paginasTotales"/>
	<form:hidden path="campo" id="campo"/>
	<form:hidden path="direccion" id="direccion"/>
	<form:hidden path="accion" id="accion"/>
	<form:hidden path="cambios" id="cambios"/>
	<form:hidden path="pulsaIr" id="pulsaIr" />
	
	<bean:define id="pagActual" name="consultaServiciosExternosForm" property="paginaActual"/>
	<bean:define id="pagTotal" name="consultaServiciosExternosForm" property="paginasTotales"/>
	
	<div class="">
	
		<logic:present name="org.apache.spring.ERROR">
			<div id="error">
				<html:errors/>
			</div>
			<div class="clear"></div>
		</logic:present>
		
		<h1 class="pae-title"><spring:message code="field.ServiciosExternos.estadisticas.titulo"/></h1>
	
	 	<div data-function="accordion-box" class="pae-box">
	 		<div class="pae-box__header">
	 			<h3 class="pae-box__header--title">
					<spring:message code="field.ServiciosExternos.estadisticas.textoIntroduccion"/>
				</h3> 
			</div>
    	<div class="pae-box__body">   	
			<fieldset>
				<!-- Inicio Servicio -->
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.ServiciosExternos.estadisticas.servicio"/>
							</span>
							<form:select name="consultaServiciosExternosForm" path="idServicio"  id="idServicio" class="pae-form__input" >
								<form:option value="">	</form:option>
								<form:option value="5"><spring:message code="field.ServiciosExternos.FileSystem"/></form:option>
								<form:option value="3"><spring:message code="field.ServiciosExternos.EJB"/></form:option>
								<form:option value="1"><spring:message code="field.ServiciosExternos.Pasarela"/></form:option>
								<form:option value="2"><spring:message code="field.ServiciosExternos.REC"/></form:option>					
								<form:option value="4"><spring:message code="field.ServiciosExternos.Firma"/></form:option>					
							</form:select>
						</div>
					</div>
				<!-- Fin Servicio -->
				<!-- Inicio Resultado -->			
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.ServiciosExternos.estadisticas.resultado"/>
							</span>
							<form:select name="consultaServiciosExternosForm" path="resultado" id="resultado" class="pae-form__input" >
								<form:option value=""></form:option>
								<form:option value="OK"><spring:message code="field.ServiciosExternos.respuesta.OK"/></form:option>
								<form:option value="ER"><spring:message code="field.ServiciosExternos.respuesta.ER"/></form:option>
							</form:select> 
						</div>
					</div>
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.ServiciosExternos.estadisticas.fechaDesde"/>
							</span>
							<form:input type="text" path="fechaDesde" id="fechaDesde" name="consultaServiciosExternosForm" data-function="function-datepicker" 
									placeholder="dd/mm/aaaa" class="pae-form__input" maxlength="10"/>							
<!-- 			<img type="image" src="../images/calendar/boton-calendario.gif" style="cursor: pointer;" alt="Abrir Calendario"  onclick="popUpCalendar(this, document.getElementById('fechaDesde'), 'dd/mm/yyyy',2);"> -->
<!-- 							<script type="text/javascript"> -->
<%-- 								fncCalendario('fechaDesde', '<%=request.getContextPath()%>'); --%>
<!-- 							</script> -->
						</div>
					</div>
				<!-- Fin Fecha desde -->
				<!-- Inicio Fecha hasta -->					
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.ServiciosExternos.estadisticas.fechaHasta"/>
							</span>
							<form:input type="text" path="fechaHasta" id="fechaHasta" data-function="function-datepicker" 
										placeholder="dd/mm/aaaa" class="pae-form__input" maxlength="10" name="consultaServiciosExternosForm"/>
					
	<!-- 			<img type="image" src="../images/calendar/boton-calendario.gif" style="cursor: pointer;" alt="Abrir Calendario"  onclick="popUpCalendar(this, document.getElementById('fechaHasta'), 'dd/mm/yyyy',2);"> -->
	<!-- 					<script type="text/javascript"> -->
	<%-- 							fncCalendario('fechaHasta', '<%=request.getContextPath()%>'); --%>
	<!-- 					</script> -->
						</div>
					</div>
				</div>
			
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-89">
						<div class="pae-box-buttons">
							<input type="submit" value="Buscar" onclick="busqueda()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>						
						</div>
					</div>
				</div>
			
			<div class="clear"></div>				
		</fieldset>	
	</div>
	</div>
	

	<logic:present name="resultados" scope="request">	
		<bean:size id="numResultados" name="resultados" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
			<table id="paetable" class="pae-table pae-table--margin">
				<caption class="hiddenAccesible">Tabla de estadisticas</caption> 
				<thead class="pae-table__header">
					<tr class="pae-table__row">
						<th class="pae-table__cell-header">
							<table class="tabla_resultadosInterno">
								<tbody>
									<tr>											
										<th>
											<div class="titulo_izq_tabla">									
												<span class="pae-table__txt-title">
													<spring:message code="field.serviciosExternos.estadisticas.idMay"/> 
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
													<spring:message code="field.serviciosExternos.estadisticas.fechaMay"/> 
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
													<spring:message code="field.serviciosExternos.estadisticas.resultadoMay"/> 
												</span>	
											</div>
										</th>
										<th>											
											<div class="titulo_der_tabla">
												<input type="image" 
													src="../images/order_desc.png" value="submit" alt="Submit" 
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
													<spring:message code="field.serviciosExternos.estadisticas.tiempoRespuestaMay"/> 
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
						<th class="pae-table__cell-header">
							<table class="tabla_resultadosInterno">
								<tbody>
									<tr>											
										<th>
											<div class="titulo_izq_tabla">									
												<span class="pae-table__txt-title">
													<spring:message code="field.serviciosExternos.estadisticas.tipoErrorMay"/> 
												</span>	
											</div>
										</th>
										<th>											
											<div class="titulo_der_tabla">
												<input type="image" 
													src="../images/order_desc.png" value="submit" alt="Submit" 
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
													<spring:message code="field.serviciosExternos.estadisticas.descripcionErrorMay"/> 
												</span>	
											</div>
										</th>
										<th>											
											<div class="titulo_der_tabla">
												<input type="image" 
													src="../images/order_desc.png" value="submit" alt="Submit" 
													onclick="ordena(11,<%=numPag %>)"><input type="image" 
													src="../images/order_asc.png" value="Submit" alt="Submit" onclick="ordena(12,<%=numPag %>)">
											</div>
										</th>
									</tr>
								</tbody>
							</table>
						</th>										
				</tr>
			</thead>
			<tbody class="pae-table__body">
				<c:forEach var="registro" items="${resultados}">
					<tr class="pae-table__row" name="row">
						<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="id" /></span>
               			</td>
               			<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="fecha" format="dd/MM/yyyy HH:mm" /></span>
               			</td>
               			<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="resultado" /></span>
               			</td>
               			<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="tiempoRespuesta" /></span>
               			</td>
               			<td data-content="" data-function="fc-collapse-table" class="pae-table__cell-body">
               					<span class="pae-table__txt-base pae-table__span-head">
               						<logic:equal name="registro" property="idTipoError" value="L">
										<spring:message code="field.serviciosExternos.estadisticas.errorLogicoMay" />
									</logic:equal>
									<logic:equal name="registro" property="idTipoError" value="F">
										<spring:message code="field.serviciosExternos.estadisticas.errorFisicoMay" />
									</logic:equal>
               					</span>
               			</td>
               			<td data-content="" data-col="col6" data-function="fc-collapse-table" class="pae-table__cell-body">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="desTipoError" /></span>
               			</td>
            		</tr>
           		</c:forEach>
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
						<a href="javascript: paginaAnterior(<%=paginaActual%>,<%=numeroPagina %>)" >&laquo;</a>
					<% } else{  %>	
			 			<a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
					<% }
					
			if(numeroPagina <= 6)
			{	
				for(int x = 1 ;x<numeroPagina+1; x++){
					String enlacePagina = "buscarEntidadFinanciera?paginaActual="+x +"&paginasTotales="+numeroPagina;
					if(x==paginaActual){%>
						<strong><a href="javascript: irAPagina(<%=x%>,<%=numeroPagina%>)" class="active" ><%=x %></a></strong>
						&nbsp;
					<%}else{%>
						<a href="javascript: irAPagina(<%=x%>,<%=numeroPagina%>)" style="color: #000000;" ><%=x %></a>
						&nbsp;
					<%}%>
				<%}%>
			<%}
			else
			{
				for(int x = 1 ;x<numeroPagina+1; x++){
					String enlacePagina = "consultarResultadosServiciosExternos?paginaActual="+x +"&paginasTotales="+numeroPagina;
					if(x==paginaActual && !(x<= 3 || x+3>numeroPagina)){%>
						<div><spring:message code="field.puntosPaginacion"/></div>
						<strong><a href="javascript: irAPagina(<%=x%>,<%=numeroPagina%>)" class="active" ><%=x %></a></strong>
						<div><spring:message code="field.puntosPaginacion"/></div>
					<%}else if (x<= 3 || x+3>numeroPagina){
						if(x==paginaActual){%>	
							<strong><a href="javascript: irAPagina(<%=x%>,<%=numeroPagina%>)" class="active" ><%=x %></a></strong>
							<%if(x == 3 && (paginaActual<= 3 || paginaActual+3>numeroPagina)){	%>
							<div><spring:message code="field.puntosPaginacion"/></div>
							<%}%>
						&nbsp;
						<%}
						else
						{%>	
							<a href="javascript: irAPagina(<%=x%>,<%=numeroPagina%>)" style="color: #000000;" ><%=x %></a>
							<%if(x == 3 && (paginaActual<= 3 || paginaActual+3>numeroPagina)){	%>
								<div><spring:message code="field.puntosPaginacion"/></div>
							<%}%>
							&nbsp;
						<%}%>	
					<%}%>
				<%}%>
			<%}%>
					<%if(paginaActual != numeroPagina) { %>
						<a href="javascript: paginaSiguiente(<%=paginaActual%>,<%=numeroPagina %>)">&raquo;</a>
					<% } else{  %>	
			 			<a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
					<% }  %>

					</div>			
					<span class="pae-form__span-label-regxp">
						<spring:message code="field.general.irAPagina"/>
					</span>
					<form:input path="numeroPaginaIr" maxlength="4" class="pae-form__input w6"/>
					<div class="pae-box-buttons">
						<input type="submit" value="Ir" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"  onclick="irPagina(<%=numeroPagina %>)">	
					</div>
					
			
			</div>	
			</logic:greaterThan>
			<logic:present name="accion" scope="request">
				<logic:lessThan name="numResultados" value="1">
					<div id="error">
						<spring:message code="field.serviciosExternos.estadisticas.error"/>
					</div>
				</logic:lessThan>
			</logic:present>		
		</logic:present>
	</div>
</form:form>
	

</body>
</html>