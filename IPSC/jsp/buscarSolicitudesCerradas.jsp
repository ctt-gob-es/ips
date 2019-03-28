<%@ page contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="es.map.ipsc.bean.*" %>

<script type="text/javascript">

function ordena(value){
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
	document.getElementById("submit").value="Ordenar";
}

function comprobarEliminar(){
	return confirm("<spring:message code='field.mensajeEliminar'/>");
}

function busqueda(){
	document.getElementById("submit").value="Buscar";
	document.getElementById("cambios").value="Correcto";
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
function comprobarRegistros(){
	document.getElementById("submit").value = "Paginar";
	document.getElementById("cambios").value = "Incorrecto";
	document.getElementById("paginaActual").value="1";
	numeroPagina = document.getElementById("paginasTotales").value;
	pagina = document.getElementById("paginaActual").value;
	numRegistro = document.getElementById("numRegistro").value;
	window.location.href = "<%=request.getContextPath()%>/secure/buscarSolicitudesCerradas?paginaActual="+pagina +"&paginasTotales="+numeroPagina +"&llamada=Buscar"+"&numRegistro="+numRegistro; 
}

</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body style="margin-left:1em;">

<br>
<div id="content_tituloNivel1">
		<div id="cuerpo_central_tituloNivel1">
     		<spring:message code="field.solicitud.tituloSolicitudesCerradas"/>
     	</div>	
</div>

<div style="width: 99%">

<form:form modelAttribute="buscarSolicitudesForm" action="/IPSC/secure/buscarSolicitudesCerradas" method="post">
<div style="margin-left:1em; margin-right: 1em;float: left;width: 80%;">
	<form:hidden path="paginaActual" id="paginaActual"/>
	<form:hidden path="paginasTotales" id="paginasTotales"/>
	<form:hidden path="campo" id="campo"/>
	<form:hidden path="direccion" id="direccion"/>
	<form:hidden path="submit" id="submit"/>
	<form:hidden path="cambios" id="cambios"/>
	
<div id="capaAll2">
			<div class="capaBusquedaSolicIzq">
				<div class="labelIzq">
					<spring:message code="field.solicitud.numeroJustificante"/>
				</div>
				<div class="contenidoDer"> 
					<form:input path="numJustificante" class="text_2"></form:input>
				</div>
				<div class="clear"></div>
			</div>
			
			<div class="capaDer">
				<logic:present name="ministerios" scope="request">
					<bean:size id="numMinisterios" name="ministerios" scope="request"/>
					<logic:greaterThan name="numMinisterios" value="0">
						<div class="labelIzq">
							<spring:message code="field.ministerio"/>
						</div>
						<div class="contenidoDer">
							<form:select path="ministerio" cass="select_1">
									<option value="0">         </option>
									<form:options items="${ministerios}" itemValue="id" itemLabel="descripcion" style="text-transform: uppercase;" />
							</form:select>
						</div>
						<div class="clear"></div>
					</logic:greaterThan>
				</logic:present>
				<div class="clear"></div>
			<br><br>
			</div>
			<div class="posBotonDer_Busq">
				<button type="submit" class="boton2" name="submit" onclick="busqueda()" titleKey="field.Buscar"><spring:message code="field.Buscar"/></button>
			</div>
			<div class="clear"></div>			
	<div class="clear"></div>
</div>
 	
</div>
<div style="margin-left: 1em; margin-right: 1em;float: right;width: 15%;">
	<div id="destBloqeuDer">
		<div id="caja_destacados">
				<ul>
				<li><span class="titulo"><spring:message code="field.solicitudesCerradas.Destacados"/></span></li>
					<li class="dest_der">
						<a href="/secure/buscarSolicitudes?llamada=Buscar&form=L" titleKey="field.solicitudesAbiertas">
								<spring:message code="field.solicitud.volverSolicitudesAbiertas"/>
						 	</a>
						</li>
				</ul>
			</div>
		 <div class="flotacion"></div>
	</div>					
</div>
<div class="clear"></div>
	<br>
	
<jsp:useBean id="solicitudes" scope="request" class="java.util.ArrayList"/>
		<%int i = 0; %>
		<logic:present name="solicitudes" scope="request">
			<bean:size id="numResultados" name="solicitudes" scope="request"/>
			<logic:greaterThan name="numResultados" value="0">
				<center>
				<table class="tabla_resultados">
					<tr>
					 <th>
						<table class="tabla_resultadosInterno">
							 <tr>
					 			<th>
									<div class="titulo_izq_tabla">
										<spring:message code="field.solicitud.numJustifMay" /> 
									</div>
								</th>
								<th>		
								<div class="titulo_der_tabla">
									<input type="image" src="<%=request.getContextPath()%>/images/paginacion/order_desc.png" value="Submit" alt="<spring:message code="field.Ascendiente" />" title="<spring:message code="field.Ascendiente" />" onclick="ordena(1)"><input type="image" src="<%=request.getContextPath()%>/images/paginacion/order_asc.png" value="Submit" alt="<spring:message code="field.Descendiente" />" title="<spring:message code="field.Descendiente" />" onclick="ordena(2)">
								</div>
								</th>
							</tr>
				 		</table>
					</th>	
					<th>
						<table class="tabla_resultadosInterno">
							 <tr>
					 			<th>
									<div class="titulo_izq_tabla">
										<spring:message code="field.centroMay" />
									</div>
								</th>
								<th>		
								<div class="titulo_der_tabla">
									<input type="image" src="<%=request.getContextPath()%>/images/paginacion/order_desc.png" value="Submit" alt="<spring:message code="field.Ascendiente" />" title="<spring:message code="field.Ascendiente" />" onclick="ordena(5)"><input type="image" src="<%=request.getContextPath()%>/images/paginacion/order_asc.png" value="Submit" alt="<spring:message code="field.Descendiente" />" title="<spring:message code="field.Descendiente" />" onclick="ordena(6)">
								</div>
								</th>
							</tr>
				 		</table>
					</th>	
					
					<th>
						<table class="tabla_resultadosInterno">
							 <tr>
					 			<th>
									<div class="titulo_izq_tabla">
										<spring:message code="field.formaAccesoMay" /> 
									</div>
								</th>
								<th>		
								<div class="titulo_der_tabla">
									<input type="image" src="<%=request.getContextPath()%>/images/paginacion/order_desc.png" value="Submit" alt="<spring:message code="field.Ascendiente" />" title="<spring:message code="field.Ascendiente" />" onclick="ordena(7)"><input type="image" src="<%=request.getContextPath()%>/images/paginacion/order_asc.png" value="Submit" alt="<spring:message code="field.Descendiente" />" title="<spring:message code="field.Descendiente" />" onclick="ordena(8)">
								</div>
								</th>
							</tr>
				 		</table>
					</th>	
					
					<th>
						<table class="tabla_resultadosInterno">
							 <tr>
					 			<th>
									<div class="titulo_izq_tabla">
										<spring:message code="field.cuerpoMay" />
									</div>
								</th>
								<th>		
								<div class="titulo_der_tabla">
									<input type="image" src="<%=request.getContextPath()%>/images/paginacion/order_desc.png" value="Submit" alt="<spring:message code="field.Ascendiente" />" title="<spring:message code="field.Ascendiente" />" onclick="ordena(9)"><input type="image" src="<%=request.getContextPath()%>/images/paginacion/order_asc.png" value="Submit" alt="<spring:message code="field.Descendiente" />" title="<spring:message code="field.Descendiente" />" onclick="ordena(10)">
								</div>
								</th>
							</tr>
				 		</table>
					</th>	
					
					<th>
						<table class="tabla_resultadosInterno">
							 <tr>
					 			<th>
									<div class="titulo_izq_tabla">
										<spring:message code="field.grupoMay" />
									</div>
								</th>
								<th>		
								<div class="titulo_der_tabla">
									<input type="image" src="<%=request.getContextPath()%>/images/paginacion/order_desc.png" value="Submit" alt="<spring:message code="field.Ascendiente" />" title="<spring:message code="field.Ascendiente" />" onclick="ordena(11)"><input type="image" src="<%=request.getContextPath()%>/images/paginacion/order_asc.png" value="Submit" alt="<spring:message code="field.Descendiente" />" title="<spring:message code="field.Descendiente" />" onclick="ordena(12)">
								</div>
								</th>
							</tr>
				 		</table>
					</th>	
					
					<th>
						<table class="tabla_resultadosInterno">
							 <tr>
					 			<th>
									<div class="titulo_izq_tabla">
										<spring:message code="field.fechaMay" />
									</div>
								</th>
								<th>		
								<div class="titulo_der_tabla">
									<input type="image" src="<%=request.getContextPath()%>/images/paginacion/order_desc.png" value="Submit" alt="<spring:message code="field.Ascendiente" />" title="<spring:message code="field.Ascendiente" />" onclick="ordena(13)"><input type="image" src="<%=request.getContextPath()%>/images/paginacion/order_asc.png" value="Submit" alt="<spring:message code="field.Descendiente" />" title="<spring:message code="field.Descendiente" />" onclick="ordena(14)">
								</div>
								</th>
							</tr>
				 		</table>
					</th>
					
					<th>
						<table class="tabla_resultadosInterno">
							 <tr>
					 			<th>
									<div class="titulo_izq_tabla">
										<spring:message code="field.estadoMay" />
									</div>
								</th>
								<th>		
								<div class="titulo_der_tabla">
									<input type="image" src="<%=request.getContextPath()%>/images/paginacion/order_desc.png" value="Submit" alt="<spring:message code="field.Ascendiente" />" title="<spring:message code="field.Ascendiente" />" onclick="ordena(15)"><input type="image" src="<%=request.getContextPath()%>/images/paginacion/order_asc.png" value="Submit" alt="<spring:message code="field.Descendiente" />" title="<spring:message code="field.Descendiente" />" onclick="ordena(16)">
								</div>
								</th>
							</tr>
				 		</table>
					</th>
<!--					<th><spring:message code="field.acciones" />&nbsp;</th>-->
					
					
					</tr>
					<logic:iterate id="registro" name="solicitudes" >
						<tr>
							<td><bean:write name="registro" property="numJustificante" /></td>
<!--						<td><bean:write name="registro" property="ministerio" /></td>-->
							<td><bean:write name="registro" property="centroGestor" /></td>
							<td><bean:write name="registro" property="formaAcceso" /></td>
							<td><bean:write name="registro" property="cuerpoEscala" /></td>
							<td><bean:write name="registro" property="grupo" /></td>
							<td><bean:write name="registro" property="fecha" /></td>
							<td><bean:write name="registro" property="estadoInscripcion" /></td>
		    			</tr>
					</logic:iterate>
				</table>
				</center>
				
				
				<br>

					<%
					Object numPat = request.getAttribute("paginasTotales");
					Object pagActual = request.getAttribute("paginaActual");
					int numeroPagina = Integer.parseInt(numPat.toString());
					int paginaActual = Integer.parseInt(pagActual.toString());
					String paginar = "Paginar";%>
					
			
				<div class="capaPaginacion">
					<span class="pagPorFila" >
						<spring:message code="field.mostrar"/>
								<form:select path="numRegistro" id="numRegistro" onchange="comprobarRegistros()">
									<form:option value="10"><spring:message code="field.10Registros"/></form:option>
									<form:option value="20" ><spring:message code="field.20Registros"/></form:option>
									<form:option value="50"><spring:message code="field.50Registros"/></form:option>
								</form:select>
						<spring:message code="field.filasPorPagina"/>
					</span>
					
					<spring:message code="field.Pagina"/>&nbsp; 
					<%for(int x = 1 ;x<numeroPagina+1; x++){
						String enlacePagina = "../secure/buscarSolicitudesCerradas?paginaActual="+x +"&paginasTotales="+numeroPagina +"&llamada="+paginar;
						if(x==paginaActual){%>
							<strong><a href="<%=enlacePagina %>" style="color: #000000;"><%=x %></a></strong>
							&nbsp;
						<%}else{%>
						<a href="<%=enlacePagina %>"  style="color: #000000;"><%=x %></a>
						&nbsp;
						<%}%>
					<%}%>
					<input type="image" src="<%=request.getContextPath()%>/images/paginacion/anterior.png"  alt="<spring:message code="field.Anterior" />" title="<spring:message code="field.Anterior" />" onclick="paginaAnterior(<%=paginaActual%>,<%=numeroPagina %>)">
					<input type="image" src="<%=request.getContextPath()%>/images/paginacion/siguiente.png"  alt="<spring:message code="field.Siguiente" />" title="<spring:message code="field.Siguiente" />" onclick="paginaSiguiente(<%=paginaActual%>,<%=numeroPagina %>)">				
				</div>
				<div class="clear" />

				
			</logic:greaterThan>
			<logic:present name="submit" scope="request">
				<logic:lessThan name="numResultados" value="1">
					<strong><spring:message code="field.solicitudes.error"/></strong>
					<div class="clear"/>
				</logic:lessThan>
			</logic:present>		
		</logic:present>
	</form:form>
	</div>

<div class="clear"></div>
</body>
</html>