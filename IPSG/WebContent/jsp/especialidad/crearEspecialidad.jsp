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

function changePerfil(perfil){
	//alert("Perfil: "+perfil.value);
	if (perfil.value == '4' || perfil.value=='2'){//administrador o soporte no se asocian a un centro gestor
		document.getElementById("idCentroGestor").value = "";
		document.getElementById("idCentroGestor").disabled = true;
		
		document.getElementById("publicaConvocatorias").disabled=true;
		document.getElementById("recibeCorreosIncidencias").disabled=true;
		
		if(perfil.value == '4'){//administrador
			document.getElementById("publicaConvocatorias").checked = true;			
			document.getElementById("recibeCorreosIncidencias").checked = true;
		}else{
			document.getElementById("publicaConvocatorias").checked = false;
			document.getElementById("recibeCorreosIncidencias").checked = false;
			
		}
	}else {
		document.getElementById("idCentroGestor").disabled = false;
		document.getElementById("publicaConvocatorias").checked = false;
		document.getElementById("recibeCorreosIncidencias").checked = false;
		
		if(perfil.value == '3'){//gestor
			document.getElementById("publicaConvocatorias").disabled=false;
			document.getElementById("recibeCorreosIncidencias").disabled=false;
		}else{
			document.getElementById("publicaConvocatorias").disabled=true;
			document.getElementById("recibeCorreosIncidencias").disabled=true;
		}
	}
	
}
function cancelar(){
	window.location.href = "../spring/buscarEspecialidad";
}

function marcar(){
	document.getElementById("visibilidad").checked= true;
}
</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body style="margin-left:0.4em;" onload="marcar()">

<div class="capaAll">

	
  <h1 class="pae-title"> <spring:message code="field.especialidad.tituloCrearEspecialidad"/> </h1>
     

<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->

<form:form commandName="especialidadForm" action="/IPSG/spring/crearEspecialidad" id="formPadre" method="post">

<logic:present name="especialidad" scope="request">

<form:hidden path="id" name="especialidad"/></logic:present>


	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>
	

	<!-- Ini Código -->
<div data-function="accordion-box" class="pae-box">   
<div class="pae-box__body">
	<fieldset id="formulario">
		<div class="pae-layout">
			<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
			<div class="pae-form__cell">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.especialidad.codigo.mayus"/>
				</span>
				<form:input type="text" path="codigo" class="pae-form__input" maxlength="10"/>
			</div>
			</div>
			<!-- Fin Código -->
			<!-- Ini Descrpción -->
			<div class="pae-layout__item pae-u-6/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
			<div class="pae-form__cell">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.especialidad.descripcion.mayus"/><span class="obligatorio"> *</span>
				</span>
				<form:input type="text" path="descripcion" class="pae-form__input" maxlength="500"/>
			</div>
			</div>
		</div>
	<!-- Fin Cuerpo y Escala -->
	
	<!-- Ini Visibilidad-->
	<div class="pae-layout">
			<div class="pae-layout__item pae-u-3/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-check">
			<div class="pae-form__cell">
				<input type="checkbox" name="visibilidad" property="visibilidad" id="visibilidad" data-function="checkbox-custom-input" class="pae-form__custom-check" checked="yes">
				<label for="visibilidad" data-function="checkbox-custom" class="pae-form__custom-check-label pae-form__custom-check-label--option">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.general.visibilidadTitulo"/> 
					</span>
				</label>		
			</div>
			</div>
			<div class="pae-layout__item pae-u-6/12 pae-u-2/12-lap pae-u-1/1-palm">
			<div class="pae-form__cell">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.cuerpoMay"/><span class="obligatorio"> *</span>
				</span>
				<form:select path="idCuerpoEscala" class="pae-form__input" styleId="idCuerpoEscala" >
					<form:option value=""></form:option>
						<logic:present name="cuerpo" scope="request">
							<bean:size id="numCuerpo" name="cuerpo" scope="request"/>
								<logic:greaterThan name="numCuerpo" value="0">
									<form:options items="${cuerpo}"  itemLabel="descripcion" itemValue="id"/>
								</logic:greaterThan>
						</logic:present>
				</form:select>
			</div>
			</div>			
	</div>
	<!-- Fin Visibilidad-->
	


 	<div class="filaBtn">
		<div class="pae-box-buttons pd-right-1">
		   	<input type="submit" name="accion" value="Guardar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium"/>
			<input type="button" name="submit" value="Cancelar" onclick="cancelar()" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1"/>
		</div>
	</div>


</fieldset>	
</div>
</div>	
</form:form>
	
 </div>  <!--cierre capaAll -->
</body>
</html>


