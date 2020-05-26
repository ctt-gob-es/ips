<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">

function guardar(){
	document.getElementById("submit").value = "Guardar";
}

function validarCodPostal(){
	if(document.getElementById("codigoPostal").checked == true){
		document.getElementById("provincia").checked = true;
	}
}

function validarProvincia(){
	if(document.getElementById("provincia").checked == false){
		document.getElementById("codigoPostal").checked = false;
	}
	
}

function validarTipoDiscapacidad(){
	if(document.getElementById("tipoDiscapacidad").checked == true){
		document.getElementById("porcentajeDiscapacidad").checked = true;
		document.getElementById("reservaDiscapacidad").checked = true;
		document.getElementById("detalleDiscapacidad").checked = true;
	}else{
		document.getElementById("porcentajeDiscapacidad").checked = false;
		document.getElementById("reservaDiscapacidad").checked = false;
		document.getElementById("detalleDiscapacidad").checked = false;
	}
}

function validarPorcentajeDiscapacidad(){
	if(document.getElementById("porcentajeDiscapacidad").checked == true){
		//document.getElementById("tipoDiscapacidad").checked = true;
		document.getElementById("reservaDiscapacidad").checked = true;
		document.getElementById("detalleDiscapacidad").checked = true;
	}else{
		//document.getElementById("tipoDiscapacidad").checked = false;
		document.getElementById("reservaDiscapacidad").checked = false;
		document.getElementById("detalleDiscapacidad").checked = false;
	}
}

function validarReservaDiscapacitados(){
	if(document.getElementById("reservaDiscapacidad").checked == true){
		document.getElementById("porcentajeDiscapacidad").checked = true;
		//document.getElementById("tipoDiscapacidad").checked = true;
	}else{
		if(document.getElementById("detalleDiscapacidad").checked == false){
				document.getElementById("porcentajeDiscapacidad").checked = false;
				//document.getElementById("tipoDiscapacidad").checked = false;
		}
	}
}

function validarDetalleDiscapacidad(){
	if(document.getElementById("detalleDiscapacidad").checked == true){
		document.getElementById("porcentajeDiscapacidad").checked = true;
		//document.getElementById("tipoDiscapacidad").checked = true;
	}else{
		if(document.getElementById("reservaDiscapacidad").checked == false){
				document.getElementById("porcentajeDiscapacidad").checked = false;
				//document.getElementById("tipoDiscapacidad").checked = false;
		}
	}
}

function volver(){
	var id = document.getElementById("idConvocatoria").value;
	window.location.href = "../spring/detalleConvocatoria?registro="+id; 
}

</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<link rel="stylesheet"
	href='<%=request.getContextPath()%>/css/modelo790.css' type="text/css">
<body style="margin-left:0.4em;">

<form:form modelAttribute="plantillaForm" action="/IPSG/spring/actualizarPlantillaConvocatoria" id="formHijo" method="post">
	<form:hidden path="submit" id="submit"/>
	<form:hidden path="id" id="id"/>
	<form:hidden path="tipoPlantilla" value="C"/>
	<form:hidden path="idConvocatoria" id="idConvocatoria"/>
	
	<logic:present name="mensajeConfirmacion" scope="request">
			<br><br>
			<strong><FONT color="#00FF00"><bean:write name="mensajeConfirmacion" scope="request"/></FONT></strong>
			<br><br>
	</logic:present>
	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>
	
	<h1 class="pae-title"><spring:message code="field.plantilla.tituloConvocatoria"/></h1>
	<br>
	
	<table border="2" width="97%" id="tablePlantilla">
		<tr>
			<td>
				<table border ="1" width="100%">
					<tr>
						<td width="10%" height=0px display="none"></td>
						<td width="10%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="14%" height=0px display="none"></td>
					</tr>
					<tr>
						<td align="center" colspan="1" class="TD790">
							<img src="../images/escudo.gif" width="65" height="65">
						</td>
						
						<td align="center" colspan="6" class="TD790">
							<div class="TXTTITULO"><spring:message code="field.plantilla.tituloPlantilla"/></div>
						</td>
						<td align="center" colspan="2" class="TD790">
							<DIV class="TXTTITULO">
								<spring:message code="field.plantilla.modelo"/>
							</DIV>
							<DIV class="TXTTITULO">
								<spring:message code="field.plantilla.790"/>
							</DIV>
						</td>
					</tr>
				</table>			
			</td>
		</tr>
		<tr>
			<td>
				<table border = "1" width="100%">
					<tr>
						<td width="12%" height=0px display="none"></td>
						<td width="10%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="14%" height=0px display="none"></td>
					</tr>
					<tr>
						<td colspan="8" class="TD7901CABECERA1">
						<FONT color="#ffffff"><spring:message code="field.plantilla.datosPersonales"/></FONT>
						</td>
					</tr>
					<tr>
						<td colspan="1" class="TD7901">
							<form:checkbox path="nif" disabled="true"/>
							<spring:message code="field.plantilla.nif"/>
						</td>
						<td colspan="2" class="TD7901"> 
							<form:checkbox path="primerApellido" disabled="true"/>						
							<spring:message code="field.plantilla.primerApellido"/>
						</td>
						<td colspan="3" class="TD7901">
							<form:checkbox path="segundoApellido"/>
							<spring:message code="field.plantilla.segundoApellido"/>
						</td>
						<td colspan="2" class="TD7901">
							<form:checkbox path="nombre" disabled="true"/>	
							<spring:message code="field.plantilla.nombre"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="TD7901">
							<form:checkbox path="fechaNacimiento"/>
							<spring:message code="field.plantilla.fechaNacimiento"/>
						</td>
						<td colspan="1" class="TD7901">
							<form:checkbox path="sexo"/>
							<spring:message code="field.plantilla.sexo"/>
						</td>
						<td colspan="2" class="TD7901">
							<form:checkbox path="nacionalidad"/>
							<spring:message code="field.plantilla.nacionalidad"/>
						</td>
						<td colspan="3" class="TD7901">
							<form:checkbox path="correoElectronico" disabled="true"/>
							<spring:message code="field.plantilla.correoElectronico"/>
						</td>
					</tr>
					<tr>
						<td colspan="3" class="TD7901">
							<form:checkbox path="telefono"/>
							<spring:message code="field.plantilla.telefono"/>
						</td>
						<td colspan="3" class="TD7901">
							<form:checkbox path="via" id="via"></form:checkbox>
							<spring:message code="field.plantilla.domicilio"/>
						</td>
						<td colspan="2" class="TD7901">
							<form:checkbox path="codigoPostal" id="codigoPostal" onclick="validarCodPostal()"></form:checkbox>
							<spring:message code="field.plantilla.CodPostal"/>
						</td>
					</tr>
					<tr>
						<td colspan="3" class="TD7901">
							<form:checkbox path="municipio" id="municipio"></form:checkbox>
							<spring:message code="field.plantilla.municipio"/>
						</td>
						<td colspan="2" class="TD7901">
							<form:checkbox path="provincia" id="provincia" onclick ="validarProvincia()"></form:checkbox>
							<spring:message code="field.plantilla.provincia"/>
						</td>
						<td colspan="3" class="TD7901">
							<form:checkbox path="pais" id="pais"></form:checkbox>
							<spring:message code="field.plantilla.pais"/>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table border = "1" width="100%">
					<tr>
						<td width="12%" height=0px display="none"></td>
						<td width="10%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="14%" height=0px display="none"></td>
					</tr>
					<tr>
						<td colspan= "8" class="TD7901CABECERA1">
							<FONT color="#ffffff"><spring:message code="field.plantilla.convocatoria"/></FONT>
						</td>
					</tr>
					<tr>
						<td colspan="4" class="TD7901">
							<form:checkbox path="cuerpo" id="cuerpo" disabled="true"></form:checkbox>
							<spring:message code="field.plantilla.cuerpoEscala"/>
						</td>
						<td colspan="3" class="TD7901">
							<form:checkbox path="especialidad" id="especialidad"></form:checkbox>
							<spring:message code="field.plantilla.especialidad"/>
						</td>
						<td colspan="1" class="TD7901">
							<form:checkbox path="formaacceso" id="formaacceso" disabled="true"></form:checkbox>
							<spring:message code="field.plantilla.formaAcceso"/>
						</td>
					</tr>
					<tr>
						<td colspan="4" class="TD7901">
							<form:checkbox path="entidadConvocante" id="entidadConvocante" disabled="true"></form:checkbox>
							<spring:message code="field.plantilla.ministerio"/>
						</td>
						<td colspan="2" class="TD7901">
							<form:checkbox path="fechaBoe" id="fechaBoe" disabled="true"></form:checkbox>
							<spring:message code="field.plantilla.fechaBOE"/>
						</td>
						<td colspan="2" class="TD7901">
							<form:checkbox path="provinciaExamen" id="provinciaExamen"></form:checkbox>							
							<spring:message code="field.plantilla.provinciaExamen"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="TD7901">
							<form:checkbox path="porcentajeDiscapacidad" id="porcentajeDiscapacidad" onclick="validarPorcentajeDiscapacidad()"></form:checkbox>
							<spring:message code="field.plantilla.minusvalia"/>
						</td>
						
						<td colspan="3" class="TD7901">
							<form:checkbox path="reservaDiscapacidad" id="reservaDiscapacidad" onclick="validarReservaDiscapacitados()"></form:checkbox>
							<spring:message code="field.plantilla.reserva"/>
						</td>
						<td colspan="3" class="TD7901">
							<form:checkbox path="detalleDiscapacidad" id="detalleDiscapacidad" onclick="validarDetalleDiscapacidad()"></form:checkbox>
							<spring:message code="field.plantilla.motivo"/>
						</td>
					</tr>
					<tr>
						<td colspan= "8" class="TD7901CABECERA1">
							<FONT color="#ffffff"><spring:message code="field.plantilla.titulosAcademicos"/></FONT>
						</td>
					</tr>
					<tr>
						<td colspan="8" class="TD7901">
							<form:checkbox path="titulosExigidos" id="titulosExigidos"></form:checkbox>							
							<spring:message code="field.plantilla.titulosExigidos"/>
						</td>					
					</tr>
					<tr>
						<td colspan="8" class="TD7901">
							<form:checkbox path="otrosTitulos" id="otrosTitulos"></form:checkbox>
							<spring:message code="field.plantilla.otrosTitulos"/>
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td>
				<table border = "1" width="100%">
					<tr>
						<td width="12%" height=0px display="none"></td>
						<td width="10%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="14%" height=0px display="none"></td>
					</tr>
					<tr>
						<td colspan= "8" class="TD7901CABECERA1">
							<FONT color="#ffffff"><spring:message code="field.plantilla.datosConsignarConvocatoria"/></FONT>
						</td>
					</tr>
					<tr>
						<logic:present name="listaPlantillaPropiosBean" scope="request">
							<logic:iterate id="registro" name="listaPlantillaPropiosBean" >
								<tr>
									<td colspan="8" class="TD7901">
										<c:if test="${registro.obligatorio}">
											<input type="checkbox" name="listacheckbox" checked="checked" value="${registro.modeloBean.id}_${registro.campoPropioBean.id}"></input>
										</c:if>
										<c:if test="${!registro.obligatorio}">
											<input type="checkbox" name="listacheckbox" value="${registro.modeloBean.id}_${registro.campoPropioBean.id}"></input>
										</c:if>
				 							<bean:write name="registro" property="campoPropioBean.tituloCampo" />
									</td>
								</tr>	
							</logic:iterate>
						</logic:present>
					</tr>
				</table>
			</td>
		</tr>
		
		<tr>
			<td>
				<table border = "1" width="100%">
					<tr>
						<td width="12%" height=0px display="none"></td>
						<td width="10%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="12%" height=0px display="none"></td>
						<td width="14%" height=0px display="none"></td>
					</tr>
					<tr>
						<td colspan="8" class="TD7901CABECERA1">
							<FONT color="#ffffff"><spring:message code="field.plantilla.codigosPrecargables"/></FONT>
						</td>
					</tr>
					<tr>
						<td colspan="4" class="TD7901">
							<form:checkbox path="codigoMinisterio" id="codigoMinisterio"></form:checkbox>
							<spring:message code="field.plantilla.codMinisterio"/>
						</td>
						
						<td colspan="4" class="TD7901">
							<form:checkbox path="codigoPaisDomicilio" id="codigoPaisDomicilio"></form:checkbox>
							<spring:message code="field.plantilla.codPaisDomicilio"/>
						</td>
					</tr>
					<tr>	
						<td colspan="4" class="TD7901">
							<form:checkbox path="codigoCuerpoEscala" id="codigoCuerpoEscala"></form:checkbox>
							<spring:message code="field.plantilla.codCuerpoEscala"/>
						</td>
						<td colspan="4" class="TD7901">
							<form:checkbox path="codigoProvinciaDomicilio" id="codigoProvinciaDomicilio" ></form:checkbox>
							<spring:message code="field.plantilla.codProvinciaDomicilio"/>
						</td>
					</tr>
					<tr>	
						<td colspan="4" class="TD7901">
							<form:checkbox path="codigoEspecialidad" id="codigoEspecialidad" ></form:checkbox>
							<spring:message code="field.plantilla.codEspecialidad"/>
						</td>
						<td colspan="4" class="TD7901">
							<form:checkbox path="codigoProvinciaExamen" id="codigoProvinciaExamen" ></form:checkbox>							
							<spring:message code="field.plantilla.codProvinciaExamen"/>
						</td>	
					</tr>
					<tr>		
						<td colspan="4" class="TD7901">
							<form:checkbox path="codigoTituloOficial" id="codigoTituloOficial" ></form:checkbox>
							<spring:message code="field.plantilla.codTituloOficial"/>
						</td>
					</tr>	
				</table>		
			</td>
		</tr>				
	</table>
	<div id="divButton">
	<center>
		<table>
			<br>
			<div class="filaBtn">
					<div class="pae-box-buttons" align="center">
							<input type="submit" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline" value="Aceptar" onclick="guardar()"/>&nbsp;
							<input type="button" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1" value="Volver" name="submit" onclick="volver()"/>				
					</div>
			</div>			
		</table>
	</center>
	</div>
</form:form>
</body>
</html>


