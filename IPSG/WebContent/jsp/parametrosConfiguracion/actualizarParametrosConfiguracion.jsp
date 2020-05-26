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
<%@page import="es.map.ipsg.util.Constantes"%>

<%String contador = (String) request.getAttribute("contador"); %>
<script type="text/javascript">

function comprobarGuardar(){
	return confirm('<spring:message code="field.mensajeActualizarParametros"/>');
}

function habilitarContador(contador){	
	if(document.getElementById("valor")){
		var valor = document.getElementById("valor").value;
	}
	var comparacion = "LOCAL";
	
	if(comparacion==valor){
		document.getElementById("contadorIn").disabled=false;
		document.getElementById("contadorIn").value = contador;

	}else{
		document.getElementById("contadorIn").disabled=true;
		document.getElementById("contadorIn").value = "";
	}
}

function validate(){
	var valor = document.getElementById("valor").value;
	var contador = document.getElementById("contadorIn").value;

	if(valor=="LOCAL"){
		if(contador==null || contador==""){
			alert("Debe de introducir un Número de Contador");
			return false;
		}else{
			return true;
		}
	}else{
		return true;
	}
}

function isValidateField(evt, id, value) {
	if (id == <%=(new Integer(Constantes.PARAMETRO_CONFIGURACION_ID_PORCENTAJE_EXENTO)).toString()%> || id == <%=(new Integer(Constantes.PARAMETRO_CONFIGURACION_ID_SALARIO_MINIMO)).toString()%>) {
		if (value.length > 9) {
			return false;
		} else {
			return isImporte(evt);
		}
	} else if (id == <%=(new Integer(Constantes.PARAMETRO_CONFIGURACION_ID_TIEMPO_ESTANCIA_SOLICITUDES_AUXILIAR)).toString()%> || id == <%=(new Integer(Constantes.PARAMETRO_CONFIGURACION_ID_ULTIMO_ID_MIGRADO)).toString()%>) {
		if (value.length > 9) {
			return false;
		} else {
			return isNumber(evt);
		}
	} else if (id == <%=(new Integer(Constantes.PARAMETRO_CONFIGURACION_ID_CAMBIO_AFIRMA)).toString()%> || id == <%=(new Integer(Constantes.PARAMETRO_CONFIGURACION_ID_CAMBIO_EPAGO)).toString()%>) {
		if (value.length > 0) {
			return false;
		} else {
			return isBoolean(evt);
		}
	} else {
		return true;
	}
	
}

function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
        return false;
    }
    return true;
}

function isImporte(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if ((charCode > 47 && charCode < 58) || charCode == 44 || charCode == 46) {
        return true;
    }
    return false;
}

function isBoolean(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode == 48 || charCode == 49) {
        return true;
    }
    return false;
}
</script>



<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title></title>
</head>

<body style="margin-left:0.4em;" onload="habilitarContador(<%= contador%>);">

<div class="">

 <h1 class="pae-title"><spring:message code="field.parametroConfiguracion.titulo"/></h1>
    

<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->

<form:form commandName="parametrosConfiguracionForm" action="/IPSG/spring/modificarParametrosConfiguracion" id="formPadre" method="post">
	<form:hidden path="submit" id="submit"/>	
	<form:hidden path="accion" id="accion"/>
	
	

	
<br>
		<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>

	
	<logic:present name="parametros" scope="request">
		<bean:size id="numResultados" name="parametros" scope="request"/>
			<logic:greaterThan name="numResultados" value="0">
				<table id="paetable" class="pae-table pae-table--margin">
				<caption class="hiddenAccesible">Tabla de Parametros Configuracion</caption>
				<thead class="pae-table__header">
             		<tr class="pae-table__row">
									
						<th data-col="colauto" class="pae-table__cell-header" ><!-- Valor-->
							<span class="pae-table__txt-title">
								<spring:message code="field.parametroConfiguracion.valorCampo"/>
							</span>
						</th>
						<th data-col="colauto" class="pae-table__cell-header"><!-- Descripcion-->
							<span class="pae-table__txt-title">
								<spring:message code="field.parametroConfiguracion.descripcion"/>
							<span class="pae-table__txt-title">
						</th>		
									
					</tr> <!-- Resultados de la consulta -->
					</thead>
					<tbody class="pae-table__body">
					<logic:iterate id="registro" name="parametros" >
						<tr class="pae-table__row" name="row">
							
							<!--  Valor del parametro de configuracion -->
						<logic:equal name="registro" property="visible" value="true">
							<logic:equal name="registro" property="id" value="<%=(new Integer(Constantes.PARAMETRO_CONFIGURACION_ID_METODO_SOLICITUD)).toString()%>">
								<td data-content="" data-col="colauto" data-function="fc-collapse-table" class="pae-table__cell-body">
									<input id="valor" type="text" class="text_2" maxlength="10" name="valorCampos" onkeyup="habilitarContador(<%=contador %>)" value="<bean:write name="registro" property="valorCampo"/>" />
									<input id="contadorIn" type="text" class="text_2" maxlength="6" name="contador"/>
								</td>
							</logic:equal>
							<logic:notEqual name="registro" property="id"  value="<%=(new Integer(Constantes.PARAMETRO_CONFIGURACION_ID_METODO_SOLICITUD)).toString()%>">									
								<td data-content="" data-col="colauto" data-function="fc-collapse-table" class="pae-table__cell-body">
									<input type="text"  maxlength="100" class="text_5" name="valorCampos" value="<bean:write name="registro" property="valorCampo"/>" onkeypress="return isValidateField(event,${registro.id},this.value)"/>							    							
								</td>
							</logic:notEqual>	
						</logic:equal>
							<!-- Descripcion del parametro de configuracion  -->

						<logic:equal name="registro" property="visible" value="true">	
							<td data-content="" data-col="colauto" data-function="fc-collapse-table" class="pae-table__cell-body">
								<bean:write name="registro" property="descripcion" />
							</td>		
						</logic:equal>	
						
							<input type="hidden" size="10" name="id" value="<bean:write name="registro" property="id"/>"/>						

							<input type="hidden" size="35" name="nombreCampos" value="<bean:write name="registro" property="nombreCampo"/>"/>																							
			   			</tr>
					</logic:iterate> 
				</table>
			</logic:greaterThan>	
			
			<br><br>
			<div class="pae-layout">
					<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-left-80">
						<div class="pae-box-buttons">
							<input type="submit" value="Guardar" onclick="return validate(); return comprobarGuardar();" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline"/>
						</div>
					</div>
			</div>
			<div class="clear"></div>
					
	</logic:present>
	
</div>
</form:form>
	
</div>
</body>
</html>