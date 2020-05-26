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
function clearForms()
{
    $(':input').not(':button, :submit, :reset, :hidden, :checkbox, :radio').val('');
    $(':checkbox, :radio').prop('checked', false);
}
function comprobarCampos(){
	var idPerfil = document.getElementById("idPerfil").value;
	if(idPerfil=='5'){
		document.getElementById("login").disabled=true;
		document.getElementById("password").disabled=true;
		document.getElementById("confirmacionPassword").disabled=true;
	}
}

function changePerfil(perfil){
	document.getElementById("idCentroGestor").value = "";
	document.getElementById("dsCentroGestor").value = "";
	document.getElementById("idCentroGestor").disabled = false;
	document.getElementById("lupaCentroGestor").disabled = false;

	document.getElementById("publicaConvocatorias").disabled = false;
	document.getElementById("recibeCorreosIncidencias").disabled = false;
	
	if (perfil == '4' || perfil == '2'){//administrador o soporte no se asocian a un centro gestor
		document.getElementById("idCentroGestor").value = "";
		document.getElementById("idCentroGestor").disabled = true;
		document.getElementById("dsCentroGestor").value="";
		//document.getElementById("lupaCentroGestor").disabled = true;
				
		if(perfil == '4'){//administrador
			document.getElementById("publicaConvocatorias").checked = true;
			document.getElementById("publicaConvocatorias").disabled = true;
			
			document.getElementById("recibeCorreosIncidencias").checked = true;
			document.getElementById("recibeCorreosIncidencias").disabled = true;
			
		}else{ //soporte
			document.getElementById("publicaConvocatorias").checked = false;
			document.getElementById("recibeCorreosIncidencias").checked = false;
			document.getElementById("publicaConvocatorias").disabled = true;				
			document.getElementById("recibeCorreosIncidencias").disabled = true;
		}
	}else {
		document.getElementById("idCentroGestor").disabled = false;
		//document.getElementById("lupaCentroGestor").disabled = false;
		document.getElementById("publicaConvocatorias").checked = false;
		document.getElementById("recibeCorreosIncidencias").checked = false;
			
			if (perfil == '1' || perfil == '6' || perfil == '5'){ //consultor, operador y receptor , se desativan los dos checks
					document.getElementById("publicaConvocatorias").disabled = true;				
					document.getElementById("recibeCorreosIncidencias").disabled = true;
				} 
	}	

	//Cambiamos el estilo
	if(document.getElementById("publicaConvocatorias").disabled == true)
		document.getElementById("publiConv").style.opacity = '0.5';
		
	else document.getElementById("publiConv").style.opacity = '1';

	if(document.getElementById("recibeCorreosIncidencias").disabled == true)
		document.getElementById("recibeCorreo").style.opacity = '0.5';
		
	else document.getElementById("recibeCorreo").style.opacity = '1';
}

function openWindowCentroGestor() {
	var perfil = document.getElementById("idPerfil").value;
	
	if(perfil=='2'){
		alert('Los usuarios con perfil Soporte no tienen asociado Centro Gestor');
	}else if(perfil=='4'){
		alert('Los usuarios con perfil Administrador no tienen asociado Centro Gestor');
	}else{
		var param = "idCentroGestor";
		var param2 = "dsCentroGestor";
		var extract = new Object();
		ventanaPopup = window.open("../spring/listarCentroGestor?parametro="+param+"&parametro2="+param2, 'miventana', 'width=550, height=550,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=yes,resizable=no ,modal=yes');
	}
	
	return false;
} 
function nifValidate(nif){ 
	if (!nif) 
		return false;
	
	if(nif.length == 9){ 
		nif=nif.toUpperCase(); 
		if(/[0-9]{8}[ABCDEFGHJKLMNPQRSTVWXYZ]/.test(nif)){  
			var temp = 'TRWAGMYFPDXBNJZSQVHLCKET'; 
			var posicion_letra = nif.substring(0,8) % 23; 
			if(nif.charAt(8) != temp.charAt(posicion_letra)){
				return false;
			}
		}else{
			return false;
		}
	}else{ 
		return false;
	}
	return true;
}

function nieValidate(nie){ 
	if (!nie){
		return false;
	} 
		
	if(nie.length == 9){ 
		nie=nie.toUpperCase(); 
		
		if(/[XYZ][0-9]{7}[ABCDEFGHJKLMNPQRSTVWXYZ]/.test(nie)){ 
			var temp = 'TRWAGMYFPDXBNJZSQVHLCKET'; 
			var posicion_letra = 0;
			
      		if((nie).charAt(0)=='X'){
        		posicion_letra = nie.substring(1,8) % 23; 
     		}else if((nie).charAt(0)=='Y'){
       			nie = nie.replace('Y',1);
        		posicion_letra = nie.substring(0,8) % 23; 
      		}else if((nie).charAt(0)=='Z'){
       			nie = nie.replace('Z',2);
        		posicion_letra = nie.substring(0,8) % 23; 
      		}
			
			if(nie.charAt(8) != temp.charAt(posicion_letra)){
				return false;
			}
			
		}else{
			return false;
		}
	}else{ 
		return false;
	}
	return true;
}



function ValidaNIF(){
	var cadenaceros = "";
	var nif = document.getElementById("nif").value;

	if (nif != ""){
		if (nif.length != 9){
			var numeroceros = 9 - nif.length;			
			for (var i=0; i<numeroceros; i++){
				cadenaceros =  cadenaceros + "0";
			}
		}
		nif = cadenaceros + nif;
		dni = nif.substring(0,8);
		if (!/^\d{8}$/.test(dni)) {
			alert('<spring:message code="usuario.errors.dni1"/>');
		   	return false;
		}else{
			letra =  nif.substring(8,9).toUpperCase();
			var letras = "TRWAGMYFPDXBNJZSQVHLCKE";	
			var numero = dni%23;
			if ( letra == letras.substring(numero,numero+1) ){
				return true;
			}else{
				alert('<spring:message code="usuario.errors.dni2"/>');
				return false;
			}
		}
	}else{
		return true;
	}
}


function validaDatos(){
	$("#accion").val("GUARDAR");
	var identificacion = document.getElementById("nif").value;
	var letranie = identificacion.substring(0,1);
	var result;
	if(identificacion != null && identificacion != ""){
		if(letranie == "X" || letranie == "Y" || letranie == "Z"){
			result = nieValidate(identificacion);
			if(result == false){				
				alert('<spring:message code="usuario.errors.dni1"/>');
				return false;
			}else{
				return true;	
			}
		}else{
			result = nifValidate(identificacion);
			if(result == false){
				alert('<spring:message code="usuario.errors.dni2"/>');
				return false;
			}else{
				return true;	
			}
		}
	}else{
		alert('<spring:message code="usuario.errors.dni3"/>');
		return false;
	}
}

function limpiarCentroGestor(){
	var desCentro = document.getElementById("dsCentroGestor").value;
		if(desCentro != ""){
			document.getElementById("idCentroGestor").value = "";
			document.getElementById("dsCentroGestor").value = "";
		}
	}

<!--INI-TRA042-->
function eliminarFila(index) {
    $("#row" + index).remove();
}

var numberSelected = 0;
$(document).ready(function(){
	 
	$("#centroGestor").click(function(){
		if($("#idCentroGestor").val() != ''){
			if(numberSelected<1){
				$("#cg").append("<tbody id='tablaCg'>")
				$("#cg").append("</tbody>");
				$("#cg").append("</br>");
			}
			$("#tablaCg").append("<tr id='row"+ $("#idCentroGestor").val() +"' class='pae-table__row' name='row'><td data-content='' data-col='col3' data-function='fc-collapse-table' class='pae-table__cell-body'><span class='pae-table__txt-base pae-table__span-head'>"+$("#idCentroGestor").val()+"</span><input type='hidden' id='idCg' name='idCg' value='"+$("#idCentroGestor").val()+"'/></td><td data-content='' data-col='col3' data-function='fc-collapse-table' class='pae-table__cell-body'><span class='pae-table__txt-base pae-table__span-head'>"+$("#dsCentroGestor").val()+"</span></td><td data-content='' data-col='col3' data-function='fc-collapse-table' class='pae-table__cell-body'><a href='javascript:eliminarFila("+$("#idCentroGestor").val()+");' style='color: #c33400;'>"+ 'Eliminar' +"</a></td></tr>");

			numberSelected++;
		} 
	})

});
<!--FIN-TRA042-->

function comprobarCampos(){
	var idPerfil = document.getElementById("idPerfil").value;
		if(idPerfil=='5'){
		document.getElementById("login").disabled=true;
		document.getElementById("password").disabled=true;
		document.getElementById("confirmacionPassword").disabled=true;
	}
	if (idPerfil == '4' || idPerfil == '2'){
		document.getElementById("idCentroGestor").value = "";
		document.getElementById("idCentroGestor").disabled = true;
		document.getElementById("dsCentroGestor").value="";
		//document.getElementById("lupaCentroGestor").disabled = true;

			if(idPerfil == '4'){//administrador
			document.getElementById("publicaConvocatorias").checked = true;
			document.getElementById("publicaConvocatorias").disabled = true;
		
			document.getElementById("recibeCorreosIncidencias").checked = true;
			document.getElementById("recibeCorreosIncidencias").disabled = true;

	}else{ //soporte
		document.getElementById("publicaConvocatorias").checked = false;
		document.getElementById("recibeCorreosIncidencias").checked = false;
		document.getElementById("publicaConvocatorias").disabled = true;
		document.getElementById("recibeCorreosIncidencias").disabled = true;
	}
	}
	}
</script>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script language='javascript' src="../javascript/utils.js"></script> 
	<title></title>
</head>
<body style="margin-left:0.4em;" onload="javascript:comprobarCampos()">

<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->


<form:form modelAttribute="usuarioForm" action="/IPSG/spring/altaUsuario" method="post">
	<form:hidden id="correcto" path="correcto"/>
	<logic:present name="usuario" scope="request">
		<form:hidden name="usuario" path="id"/>
	</logic:present>

<div class="capaAll">
<br>
<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>
	
<form:hidden name="accion" path="accion" id="accion"/>
<%-- MAQUETACION INI --%>
<div class="">
	<h1 class="pae-title"><spring:message code="field.usuario.tituloMantenimientoUsuario"/></h1>
	
	<div data-function="accordion-box" class="pae-box">
		<div class="pae-box__header">
			<h3 class="pae-box__header--title">
				<spring:message code="field.usuario.busquedaExplicacion"/>
			</h3>
		</div>
		<div class="pae-box__body">
			<fieldset>
				<div class="pae-layout">
					<%-- NIF --%>
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.usuario.nif.mayus"/><span class="obligatorio"> *</span>
							</span>	
							<form:input id="nif" path="nif"  name="usuario"  class="pae-form__input" maxlength="9"/>		 						 						
						</div>
					</div>
					<%-- NIF --%>	
					<%-- NOMBRE --%>
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.usuario.nombre.mayus"/><span class="obligatorio"> *</span>
							</span>	
							<form:input path="nombre" class="pae-form__input" maxlength="50"/>	 						 						
						</div>
					</div>
					<%-- NOMBRE --%>
					<%-- APELLIDO --%>
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label pae-ancho">
								<spring:message code="field.usuario.primerApellido.mayus"/><span class="obligatorio"> *</span>
							</span>	
							<form:input path="primerApellido" class="pae-form__input" maxlength="50"/>	 						 						
						</div>
					</div>
					<%-- APELLIDO --%>
					<%-- APELLIDO 2 --%>
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label pae-ancho">
								<spring:message code="field.usuario.segundoApellido.mayus"/><span class="obligatorio"></span>
							</span>	
							<form:input path="segundoApellido" class="pae-form__input" maxlength="50"/>	 						 						
						</div>
					</div>
					<%-- APELLIDO 2 --%>
				</div>
				<div class="pae-layout">
					<%-- EMAIL --%>
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.usuario.email.mayus"/><span class="obligatorio"> *</span>
							</span>	
							<form:input path="email" class="pae-form__input" maxlength="50"/>					 						
						</div>
					</div>
					<%-- EMAIL --%>		
					<%-- TELF --%>
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.usuario.telefono.mayus"/><span class="obligatorio"> *</span>
							</span>	
							<form:input path="telefono" class="pae-form__input" maxlength="9" />				 						
						</div>
					</div>
					<%-- TELF --%>
				<!--INI-TRA042-->	
				</div>
				<div class="pae-layout">
				<%-- Ini Centro Gestor--%>
					<div class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.centroMay" />
							</span>
							<form:input path="idCentroGestor" class="pae-form__input" readonly="true" onchange="comprobarBusqueda();comprobar();comprobarCampos()" onclick="limpiarCentroGestor();"/>
				 			<form:input type="hidden" path="dsCentroGestor" readonly="true" class="input_texto_border0 em100"/>					 						
						</div>
					</div>
					<div class="pae-layout__item pae-u-1/12 pae-u-2/12-lap pae-u-1/1-palm search_btn">
						<input type="button" class="pae-form__btn-search" id="lupaCentroGestor" alt="Buscar Centro Gestor" onclick="return openWindowCentroGestor()"/> 
					</div>
					<div class="pae-layout__item pae-u-1/12 pae-u-2/12-lap pae-u-1/1-palm search_btn">
						<div class="filaBtn pd-left-89">
							<div class="pae-box-buttons">		
								<input type="button" id="centroGestor" value="Añadir" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation">
							</div>
						</div>
					</div>	
					<%-- Fin Centro Gestor--%>	
				</div>
				<table><div id="cg"></div></table>
				<!--FIN-TRA042-->
				<div class="pae-layout">
					<%-- Perfil --%>
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.usuario.perfil.mayus"/>
							</span>
							<form:select path="idPerfil" class="pae-form__input" onchange="changePerfil(this.value);">
								<form:option value=""></form:option>
								<form:options items="${perfiles}" itemValue="id" itemLabel="descripcion" />
							</form:select>
						</div>
					</div>
					<%-- Perfil --%>
					<%-- Convocatoria --%>
					<div id="publiConv" class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-check">
					
           				<c:choose>
						    <c:when test="${usuarioForm.publicaConvocatorias}">
						        <input type="checkbox" name="publicaConvocatorias" id="publicaConvocatorias" 
           						data-function="checkbox-custom-input" class="pae-form__custom-check" checked>
						    </c:when>    
						    <c:otherwise>
								<input type="checkbox" name="publicaConvocatorias" id="publicaConvocatorias" 
           						data-function="checkbox-custom-input" class="pae-form__custom-check" >
						    </c:otherwise>
						</c:choose>
           				<label for="publicaConvocatorias" data-function="checkbox-custom" 
           					class="pae-form__custom-check-label pae-form__custom-check-label--option">
           					<span class="pae-form__label pae-form__span-label pd-top-2">
           						<spring:message code="field.usuario.publicaConvocatorias.mayus"/>
           					</span>
           				</label>
                            				
					</div>
					<%-- Convocatoria --%>	
					<%-- Recibe correo --%>
					<div id="recibeCorreo" class="pae-layout__item pae-u-4/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-check">
						<c:choose>
						    <c:when test="${usuarioForm.recibeCorreosIncidencias}">
						        <input type="checkbox" name="recibeCorreosIncidencias" id="recibeCorreosIncidencias" 
           						data-function="checkbox-custom-input" class="pae-form__custom-check" checked>
						    </c:when>    
						    <c:otherwise>
								<input type="checkbox" name="recibeCorreosIncidencias" id="recibeCorreosIncidencias" 
           						data-function="checkbox-custom-input" class="pae-form__custom-check" >
						    </c:otherwise>
						</c:choose>
           				<label for="recibeCorreosIncidencias" data-function="checkbox-custom" 
           					class="pae-form__custom-check-label pae-form__custom-check-label--option">
           					<span class="pae-form__label pae-form__span-label pd-top-2">
           						<spring:message code="field.usuario.recibeCorreos.mayus"/>
           					</span>
           				</label>
					</div>
					<%-- Recibe correo --%>
				</div>
				<div class="pae-layout">	
					<%-- login --%>
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.usuario.login.mayus"/><span class="obligatorio"> *</span>
							</span>
							<form:input path="login" id="login" class="pae-form__input" maxlength="20"/>						
						</div>
					</div>
					<%-- login --%>
					<%-- pass --%>
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label">
								<spring:message code="field.usuario.password.mayus"/><span class="obligatorio"> *</span>
							</span>
							<form:input type="password" path="password" id="password" class="pae-form__input" maxlength="15"/>					
						</div>
					</div>
					<%-- pass --%>	
					<%-- confirmar pass --%>
					<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm">
						<div class="pae-form__cell">
							<span class="pae-form__label pae-form__span-label pae-ancho2">
								<spring:message code="field.usuario.confirmacionPassword.mayus"/><span class="obligatorio"> *</span>
							</span>
							<form:input type="password" path="confirmacionPassword" id="confirmacionPassword" class="pae-form__input" maxlength="15"/>						
						</div>
					</div>
					<%-- confirmar pass --%>	
				</div>						
				<%-- Boton --%>
				<div class="pae-layout">
					<div class="filaBtn pd-left-89">
						<div class="pae-box-buttons">					
							<input type="submit" value="Guardar" onclick="return validaDatos();" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation"/>		
							<input type="button" onclick="clearForms(usuarioForm)" value="Limpiar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium pae-buttons--download outline pae-separation mg-left-1"/>		
						</div>
					</div>
				</div>
				<%-- Boton --%>																																																													
				</div>
			</fieldset>	
		</div>
	</div>	
</div>		
<%-- MAQUETACION FIN --%>	
		
</form:form>
	

</body>
</html>


