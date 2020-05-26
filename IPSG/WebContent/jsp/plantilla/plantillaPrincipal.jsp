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

</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<link rel="stylesheet"
	href='<%=request.getContextPath()%>/css/modelo790.css' type="text/css">
<body style="margin-left:0.4em;">

<form:form modelAttribute="plantillaForm" action="/IPSG/spring/actualizarPlantilla" id="formHijo" method="post">
	<form:hidden path="submit" id="submit"/>
	<form:hidden path="id" id="id"/>
	<form:hidden path="tipoPlantilla" value="A"/>
	
	<logic:present name="mensajeConfirmacion" scope="request">
			<br><br>
			<strong><FONT color="#00FF00"><bean:write name="mensajeConfirmacion" scope="request"/></FONT></strong>
			<br><br>
	</logic:present><br>
	<logic:present name="org.apache.spring.ERROR">
	
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>

	<h1 class="pae-title"> <spring:message code="field.plantilla.tituloPrincipal"/> </h1>
	<br>	
	<table border="2" width="100%">
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
							<form:checkbox path="nif" id="nif" disabled="true"></form:checkbox>
							<spring:message code="field.plantilla.nif"/>
						</td>
						<td colspan="2" class="TD7901"> 
							<form:checkbox path="primerApellido" styleId="primerApellido" disabled="true"></form:checkbox>							
							<spring:message code="field.plantilla.primerApellido"/>
						</td>
						<td colspan="3" class="TD7901">
							<form:checkbox path="segundoApellido" styleId="segundoApellido"></form:checkbox>
							<spring:message code="field.plantilla.segundoApellido"/>
						</td>
						<td colspan="2" class="TD7901">
							<form:checkbox path="nombre" styleId="nombre" disabled="true"></form:checkbox>
							<spring:message code="field.plantilla.nombre"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="TD7901">
							<form:checkbox path="fechaNacimiento" styleId="fechaNacimiento"></form:checkbox>
							<spring:message code="field.plantilla.fechaNacimiento"/>
						</td>
						<td colspan="1" class="TD7901">
							<form:checkbox path="sexo" styleId="sexo"></form:checkbox>
							<spring:message code="field.plantilla.sexo"/>
						</td>
						<td colspan="2" class="TD7901">
							<form:checkbox path="nacionalidad" styleId="nacionalidad"></form:checkbox>
							<spring:message code="field.plantilla.nacionalidad"/>
						</td>
						<td colspan="3" class="TD7901">
							<form:checkbox path="correoElectronico" styleId="correoElectronico" disabled="true"></form:checkbox>
							<spring:message code="field.plantilla.correoElectronico"/>
						</td>
					</tr>
					<tr>
						<td colspan="3" class="TD7901">
							<form:checkbox path="telefono" styleId="telefono"></form:checkbox>
							<spring:message code="field.plantilla.telefono"/>
						</td>
						<td colspan="3" class="TD7901">
							<form:checkbox path="via" styleId="via"></form:checkbox>
							<spring:message code="field.plantilla.domicilio"/>
						</td>
						<td colspan="2" class="TD7901">
							<form:checkbox path="codigoPostal" styleId="codigoPostal" onclick="validarCodPostal()"></form:checkbox>
							<spring:message code="field.plantilla.CodPostal"/>
						</td>
					</tr>
					<tr>
						<td colspan="3" class="TD7901">
							<form:checkbox path="municipio" styleId="municipio"></form:checkbox>
							<spring:message code="field.plantilla.municipio"/>
						</td>
						<td colspan="2" class="TD7901">
							<form:checkbox path="provincia" styleId="provincia" onclick ="validarProvincia()"></form:checkbox>
							<spring:message code="field.plantilla.provincia"/>
						</td>
						<td colspan="3" class="TD7901">
							<form:checkbox path="pais" styleId="pais"></form:checkbox>
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
							<form:checkbox path="cuerpo" styleId="cuerpo" disabled="true"></form:checkbox>
							<spring:message code="field.plantilla.cuerpoEscala"/>
						</td>
						<td colspan="3" class="TD7901">
							<form:checkbox path="especialidad" styleId="especialidad"></form:checkbox>
							<spring:message code="field.plantilla.especialidad"/>
						</td>
						<td colspan="1" class="TD7901">
							<form:checkbox path="formaacceso" styleId="formaacceso" disabled="true"></form:checkbox>
							<spring:message code="field.plantilla.formaAcceso"/>
						</td>
					</tr>
					<tr>
						<td colspan="4" class="TD7901">
							<form:checkbox path="entidadConvocante" styleId="entidadConvocante" disabled="true"></form:checkbox>
							<spring:message code="field.plantilla.ministerio"/>
						</td>
						<td colspan="2" class="TD7901">
							<form:checkbox path="fechaBoe" styleId="fechaBoe" disabled="true"></form:checkbox>
							<spring:message code="field.plantilla.fechaBOE"/>
						</td>
						<td colspan="2" class="TD7901">
							<form:checkbox path="provinciaExamen" styleId="provinciaExamen"></form:checkbox>							
							<spring:message code="field.plantilla.provinciaExamen"/>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="TD7901">
							<form:checkbox path="porcentajeDiscapacidad" styleId="porcentajeDiscapacidad" onclick="validarPorcentajeDiscapacidad()"></form:checkbox>
							<spring:message code="field.plantilla.minusvalia"/>
						</td>

						<td colspan="3" class="TD7901">
							<form:checkbox path="reservaDiscapacidad" styleId="reservaDiscapacidad" onclick="validarReservaDiscapacitados()"></form:checkbox>
							<spring:message code="field.plantilla.reserva"/>
						</td>
						<td colspan="3" class="TD7901">
							<form:checkbox path="detalleDiscapacidad" styleId="detalleDiscapacidad" onclick="validarDetalleDiscapacidad()"></form:checkbox>
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
							<form:checkbox path="titulosExigidos" styleId="titulosExigidos"></form:checkbox>							
							<spring:message code="field.plantilla.titulosExigidos"/>
						</td>					
					</tr>
					<tr>
						<td colspan="8" class="TD7901">
							<form:checkbox path="otrosTitulos" styleId="otrosTitulos"></form:checkbox>
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

						<logic:iterate id="listaPlantillasPropiosBean" property="listaPlantillasPropiosBean" name="plantillaForm" indexId="count">
								<c:if test="${listaPlantillasPropiosBean.modeloBean.id != modeloUltimo }">
									<tr>
										<td colspan= "8" class="TD7901CABECERA1">
											<FONT color="#ffffff"><spring:message code="field.plantilla.datosConsignar"/> <bean:write name="listaPlantillasPropiosBean" property="modeloBean.numModelo" /></FONT>
										</td>
									</tr>
									<c:set var="modeloUltimo" value="${listaPlantillasPropiosBean.modeloBean.id}"></c:set>
								</c:if>
									<tr>
										<td colspan="8" class="TD7901">
											<c:if test="${listaPlantillasPropiosBean.obligatorio}">
													<input type="checkbox" name="listacheckbox" checked="checked" value="${modeloUltimo}_${listaPlantillasPropiosBean.campoPropioBean.id}"></input>
											</c:if>
											<c:if test="${!listaPlantillasPropiosBean.obligatorio}">
												<input type="checkbox" name="listacheckbox" value="${modeloUltimo}_${listaPlantillasPropiosBean.campoPropioBean.id}"></input>
											</c:if>
											<bean:write name="listaPlantillasPropiosBean" property="campoPropioBean.tituloCampo" />
 										</td>
 									</tr>
							</logic:iterate>
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
	<center>
	<table>	
	<br><br>
	<div class="filaBtn">
		<div class="pae-box-buttons" align="center">
			<input type="submit" value="Aceptar" onclick="guardar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation">
		</div>
	</div>
	</table>
	</center>
</form:form>
</body>
</html>


