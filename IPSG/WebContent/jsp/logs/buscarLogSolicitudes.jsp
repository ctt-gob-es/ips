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
String numRegistro=(String)request.getAttribute("numRegistro");
%>
<script type="text/javascript" src="${pageContext.request.contextPath}/javascript/Datepicker_calendario.js"></script>
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
	document.getElementById("numeroPaginaIr").value = actualInt;
	document.getElementById("paginaActual").value=actualInt;
	document.getElementById("paginasTotales").value=pagTotales;
	document.forms[0].action = "../spring/buscarLogSolicitudes";
}

function limpiarConvocatoria(){
	var desConvocatoria = document.getElementById("dsConvocatoria").value;
	if(desConvocatoria != ""){
		document.getElementById("idConvocatoria").value = "";
		document.getElementById("dsConvocatoria").value = "";
	}	
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
	document.getElementById("submit").value="Ordenar";
}

function comprobarEliminar(){
	return confirm('<spring:message code="field.mensajeEliminar"/>');
}

function busqueda(){		
	document.getElementById("submit").value="Buscar";
	document.getElementById("paginaActual").value="1";
}

function validarNif(){
	var identificacion = document.getElementById("nif").value;
	if(identificacion != ""){
		result = nifValidate(identificacion);
		if (result == false) {		
			alert(unescape("El NIF es incorrecto."));
			return false;
		}
	}
}


function nifValidate(nif) {
	if (!nif)
		return false;

	if (nif.length == 9) {
		nif = nif.toUpperCase();
		if (/[0-9]{8}[ABCDEFGHJKLMNPQRSTVWXYZ]/.test(nif)) {
			var temp = 'TRWAGMYFPDXBNJZSQVHLCKET';
			var posicion_letra = nif.substring(0, 8) % 23;
			if (nif.charAt(8) != temp.charAt(posicion_letra)) {
				return false;
			}
		} else {
			if (/[KML][0-9]{7}[ABCDEFGHJKLMNPQRSTVWXYZ]/.test(nif)){
				var temp = 'TRWAGMYFPDXBNJZSQVHLCKET';
				var posicion_letra = nif.substring(1, 8) % 23;
				if (nif.charAt(8) != temp.charAt(posicion_letra)) {
					return false;
				}
			} else {
				return false;
			}
		}
	} else {
		return false;
	}
	return true;
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
	window.location.href = "../spring/buscarLogSolicitudes?menu=N&paginaActual="+pagina +"&paginasTotales="+numeroPagina +"&llamada=Buscar"+"&numRegistro="+numRegistro; 
}
//Fin Paginación

function openWindowConvocatoria() {
	var param = "idConvocatoria";
	var param2 = "dsConvocatoria";
	var extract = new Object();
		ventanaPopup = window.open("../spring/listarConvocatoria?parametro="+param+"&parametro2="+param2, 'miventana', 'width=600, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
	return false;
}

function openDetalleLogSolicitud(numeroJustificante,idConvocatoria) {
	document.getElementById("submit").value="Detalle";
	ventanaPopup = window.open("../spring/detalleLogSolicitudes?numeroJustificante="+numeroJustificante+"&idConvocatoria="+idConvocatoria, 'miventana', 'width=600, height=470,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');	    
		
	ventanaPopup.focus();
	 
}
</script>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	 
	<title></title>
</head>

<body style="margin-left:0.4em;"> 

<!--<div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;">-->
<form:form commandName="logSolicitudesForm" action="/IPSG/spring/buscarLogSolicitudes?menu=N" id="formPadre" method="post" onsubmit="return validarNif()">
	<form:hidden path="paginaActual" id="paginaActual"/>
	<form:hidden path="paginasTotales" id="paginasTotales"/>
	<form:hidden path="submit" id="submit"/>
	<form:hidden path="campo" id="campo"/>
	<form:hidden path="direccion" id="direccion"/>
	<form:hidden path="cambios" id="cambios"/>
	<form:hidden path="pulsaIr" id="pulsaIr" />
	
<div class="">
<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>
	
	<h1 class="pae-title"><spring:message code="field.logSolicitudes.titulo"/></h1>    
	
	<div data-function="accordion-box" class="pae-box">
		<div class="pae-box__body">
			<fieldset>
				<!-- Ini Convocatoria-->
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">		
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">							
										<spring:message code="field.logSolicitudes.convocatoriaMay"/>									
							</span>		
							<form:input type="text" path="idConvocatoria" class="pae-form__input" id="idConvocatoria" maxlength="10" readonly="true" onclick="limpiarConvocatoria();"/>									
							<form:input type="text" path="dsConvocatoria" id="dsConvocatoria" class="input_texto_border0 em100" readonly="true"/>
						</div>							
					</div>
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-3 search_btn">
							<input type="button"
									class="pae-form__btn-search" alt="Buscar Convocatoria"
									onclick="return openWindowConvocatoria()"> 	
					</div>
					<!-- Fin Convocatoria-->	
				</div>		
					<!-- Ini NIF-->
				<div class="pae-layout">
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">		
							<div class="pae-form__cell">
									<span class="pae-form__label pae-form__span-label">							
											<spring:message code="field.logSolicitudes.nifMay"/>							
									</span>		
								<form:input type="text" path="nif" id="nif" class="pae-form__input" maxlength="10"/>
							</div>		
					</div>
				
				<!-- Fin NIF-->
				<!-- Ini Núm. Solicitud-->	
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">	
						<div class="pae-form__cell">							
								<span class="pae-form__label pae-form__span-label">
									<spring:message code="field.logSolicitudes.numSolicitudMay"/>
								</span>
							<form:input type="text" path="numeroJustificante" class="pae-form__input" id="numeroJustificante" maxlength="20" onkeypress="return isNumber(event)"/>
						</div>
					</div>
					
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">	
						<div class="pae-form__cell">						
								<span class="pae-form__label pae-form__span-label">		
									<spring:message code="field.logSolicitudes.fechaDesde"/>
								</span>
					
										<form:input type="text" path="fechaDesde" data-function="function-datepicker" 
									placeholder="dd/mm/aaaa" class="pae-form__input" style="fechaDesde" maxlength="10"/>
						</div>				
					</div>				
					<!-- Fin Fecha Desde-->
					<!-- Ini Fecha Hasta-->
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">	
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.logSolicitudes.fechaHasta"/>
							</span>
						
						 
							<form:input type="text" path="fechaHasta"  data-function="function-datepicker" 
									placeholder="dd/mm/aaaa" class="pae-form__input" style="fechaHasta" maxlength="10"/>
				<!-- 			<img type="image" src="../images/calendar/boton-calendario.gif"  alt="Abrir Calendario" style="cursor: pointer;" onclick="popUpCalendar(this, document.getElementById('fechaHasta'), 'dd/mm/yyyy',2);"> -->
<!-- 				<script type="text/javascript"> -->
<%-- 											fncCalendario('fechaHasta', '<%=request.getContextPath()%>'); --%>
<!-- 								</script> -->
						</div>								
					</div>	
				</div>					
				<!-- Fin Núm. Solicitud-->
			

					<!-- Fin Fecha Hasta-->
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
	
	<logic:present name="solicitudes" scope="request">	
		<bean:size id="numResultados" name="solicitudes" scope="request"/>
		<logic:greaterThan name="numResultados" value="0">
		<table id="paetable" class="pae-table pae-table--margin">
			<caption class="hiddenAccesible">Tabla de log de solicitudes</caption>
				<thead class="pae-table__header">
					<tr class="pae-table__row">
						<th class="pae-table__cell-header wauto">
							<table class="tabla_resultadosInterno">
								<tbody>
									<tr>											
										<th>
											<div class="titulo_izq_tabla">									
												<span class="pae-table__txt-title">
													<spring:message code="field.logSolicitudes.numJustificante" /> 
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
													<spring:message code="field.logSolicitudes.nif" /> 
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
						<th class="pae-table__cell-header wauto">
							<table class="tabla_resultadosInterno">
								<tbody>
									<tr>											
										<th>
											<div class="titulo_izq_tabla">									
												<span class="pae-table__txt-title">
													<spring:message code="field.logSolicitudes.id" /> 
												</span>	
											</div>
										</th>
										<th>											
											<div class="titulo_der_tabla">
												<input type="image" 
													src="../images/order_desc.png" value="accion" alt="Ordenar" 
													onclick="ordena(5,<%=numPag%>)"><input type="image" 
													src="../images/order_asc.png" value="accion" alt="Ordenar" onclick="ordena(6,<%=numPag%>)">
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
													<spring:message code="field.logSolicitudes.cuerpoEscala" /> 
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
						<th class="pae-table__cell-header wauto">
							<table class="tabla_resultadosInterno">
								<tbody>
									<tr>											
										<th>
											<div class="titulo_izq_tabla">									
												<span class="pae-table__txt-title">
													<spring:message code="field.logSolicitudes.centroGestor" /> 
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
				</tr>
			</thead>
			<tbody class="pae-table__body">
					<c:forEach var="registro" items="${solicitudes}">
						<tr class="pae-table__row" name="row">
							<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body w12">
               					<span class="pae-table__txt-base pae-table__span-head"><a href="#" onclick="openDetalleLogSolicitud(${registro.numeroJustificante},${registro.idConvocatoria})">
								<font color="#c33400">	<bean:write name="registro" property="numeroJustificante" /> </font>
								</a></span>
               				</td>
               				<td data-content="" data-col="col2" data-function="fc-collapse-table" class="pae-table__cell-body w12">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="dniCiudadano" /></span>
               				</td>
							<td data-content="" data-col="col3" data-function="fc-collapse-table" class="pae-table__cell-body w12">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="idConvocatoria" /></span>
               				</td>
							<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="desCuerpoEscala" /></span>
               				</td>
							<td data-content="" data-col="col1" data-function="fc-collapse-table" class="pae-table__cell-body">
               					<span class="pae-table__txt-base pae-table__span-head"><bean:write name="registro" property="desCentroGestor" /></span>
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
						<form:select path="numRegistro" id="numRegistro" onchange="comprobarRegistros()" class="pae-form__input wauto">
							<form:option value="10"><spring:message code="field.10Registros"/></form:option>
							<form:option value="20"><spring:message code="field.20Registros"/></form:option>
							<form:option value="50"><spring:message code="field.50Registros"/></form:option>
						</form:select>
					</span>
					
					<div class="pagination">
					<%if(paginaActual != 1) { %>
						<a href="buscarLogSolicitudes?paginaActual=<%=paginaActual-1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar" >&laquo;</a>
					<% } else{  %>	
			 			<a style="text-decoration: none;cursor: default;background-color:#FFFFFF"></a> 
					<% }   
				
			if(numeroPagina <= 6)
			{	
				for(int x = 1 ;x<numeroPagina+1; x++){
					String enlacePagina = "buscarLogSolicitudes?paginaActual="+x +"&paginasTotales="+numeroPagina +"&llamada=Paginar";
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
					String enlacePagina = "buscarLogSolicitudes?paginaActual="+x +"&paginasTotales="+numeroPagina +"&llamada=Paginar";
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
				<a href="buscarLogSolicitudes?paginaActual=<%=paginaActual+1%>&paginasTotales=<%=numeroPagina %>&llamada=Paginar" >&raquo;</a>
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
		</logic:greaterThan>
		<logic:lessThan name="numResultados" value="1">
				<div id="error">
					<div id="rellenarCampos" class="pae-alertbox pae-alertbox--warning">
						<ul><p class="pae-alertbox__text"><spring:message code="field.logSolicitudes.error"/></p></ul>
					</div>				
				</div>				
			</logic:lessThan>
	</logic:present>
	
</div>
</form:form>
	
<!--</div>-->
</body>
</html>