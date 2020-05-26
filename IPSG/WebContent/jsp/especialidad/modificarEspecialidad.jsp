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
	if (perfil == '4' || perfil=='2'){//administrador o soporte no se asocian a un centro gestor
		document.getElementById("idCentroGestor").value = "";
		document.getElementById("idCentroGestor").disabled = true;
		
		document.getElementById("publicaConvocatorias").disabled=true;
		document.getElementById("recibeCorreosIncidencias").disabled=true;
		
		if(perfil == '4'){//administrador
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
		
		if(perfil == '3'){//gestor
			document.getElementById("publicaConvocatorias").disabled=false;
			document.getElementById("recibeCorreosIncidencias").disabled=false;
		}else{
			document.getElementById("publicaConvocatorias").disabled=true;
			document.getElementById("recibeCorreosIncidencias").disabled=true;
		}
	}
}

function verificarPerfil(){
	var idPerfil = document.getElementById("idPerfil").value;
	changePerfil(idPerfil);
}
</script>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script language='javascript' src="../javascript/utils.js"></script> 
<title></title>
</head>

<body style="margin-left:0.4em;" onload="verificarPerfil()">
 
 <div class="capaAll">
 
 <h1 class="pae-title"> <spring:message code="field.especialidad.tituloMantenimientoEspecialidad"/> </h1>
     

<!-- <div style="margin-left:1em; margin-right: 1em;float: left;width: 99%;"> -->

<form:form commandName="especialidadForm" action="/IPSG/spring/modificarEspecialidad" id="formPadre" method="post">	

	<logic:present name="org.apache.spring.ERROR">
	<div id="error">
		<html:errors/>
	</div>
	<div class="clear"></div>
	</logic:present>
	
	<!-- Ini ID -->
<div data-function="accordion-box" class="pae-box">   
<div class="pae-box__body">
	<fieldset id="formulario">
		<div class="pae-layout">
			<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
			<div class="pae-form__cell">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.especialidad.id.mayus"/>
				</span>
				<logic:present name="especialidad" scope="request">
					<form:input type="text" path="id" name="especialidad" class="pae-form__input pae-box--transparent" maxlength="3" readonly="true"/>
				</logic:present>
				<logic:notPresent name="especialidad" scope="request">
				<form:input type="text" path="id" class="pae-form__input pae-box--transparent" maxlength="3"  readonly="true"/>
				</logic:notPresent>
			</div>	
			</div>	
	<!-- Fin ID -->
	<!-- Ini Código -->
			<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
			<div class="pae-form__cell">
				<span class="pae-form__label pae-form__span-label">
					<spring:message code="field.especialidad.codigo.mayus"/>
				</span>
				<logic:present name="especialidad" scope="request">
				<form:input type="text" path="codigo" name="especialidad" class="pae-form__input" property="codigo" maxlength="10"/>
				</logic:present>
				<logic:notPresent name="especialidad" scope="request">
				<form:input type="text" path="codigo" class="pae-form__input" property="codigo" maxlength="10"/>
				</logic:notPresent>

			</div>
			</div>
	<!-- Ini Visibilidad-->
	<div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-3_7">
	<div class="pae-form__cell">
			<logic:present name="especialidad" scope="request">
				<c:choose>
				    <c:when test="${especialidadForm.visibilidad == true }">
				        <input type="checkbox" name="visibilidad" property="visibilidad" id="visibilidad" data-function="checkbox-custom-input" class="pae-form__custom-check" checked="checked">
				    </c:when>    
				    <c:otherwise>
						<input type="checkbox" name="visibilidad" property="visibilidad" id="visibilidad" data-function="checkbox-custom-input" class="pae-form__custom-check">
				    </c:otherwise>
				</c:choose>				
				<label for="visibilidad" data-function="checkbox-custom" class="pae-form__custom-check-label pae-form__custom-check-label--option">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.general.visibilidadTitulo"/> 
					</span>
				</label>			
			</logic:present>		
	</div>
	</div>
	<!-- Fin Visibilidad-->				
	<div class="pae-layout__item pae-u-5/12 pae-u-2/12-lap pae-u-1/1-palm pd-top-1_7">
	<div class="pae-form__cell">
		<span class="pae-form__label pae-form__span-label">
			<spring:message code="field.especialidad.descripcion.mayus"/><span class="obligatorio"> *</span>
		</span>	
		<logic:present name="especialidad" scope="request">
		<form:input type="text" path="descripcion" name="especialidad"  class="pae-form__input" maxlength="100"/>
		</logic:present>
		<logic:notPresent name="especialidad" scope="request">
		<form:input type="text" path="descripcion" class="pae-form__input" maxlength="100"/>
		</logic:notPresent>
	</div>
	</div>

	<!--  Fin Descripción -->
</div>	
<div  class="pae-layout">	
	<!-- ini Cuerpo y Escala -->
	<div class="pae-layout__item pae-u-6/12 pae-u-2/12-lap pae-u-1/1-palm">
	<div class="pae-form__cell">
		
		<logic:present name="cuerpo" scope="request">
			<bean:size id="numCuerpo" name="cuerpo" scope="request"/>
				<logic:greaterThan name="numCuerpo" value="0">
					<span class="pae-form__label pae-form__span-label">
						<spring:message code="field.cuerpoMay"/><span class="obligatorio"> *</span>
					</span>
					<form:select path="idCuerpoEscala" class="pae-form__input" styleId="idCuerpoEscala" >
						<form:option value=""></form:option>																	
						<form:options items="${cuerpo}"  itemLabel="descripcion" itemValue="id"/>										
					</form:select>
				</logic:greaterThan>
		</logic:present>				
	</div>
	</div>
</div>


	

 <div class="filaBtn">
		<div class="pae-box-buttons pd-right-1">
		<input type="submit" name="accion" value="Modificar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium"/>
		<input type="button" name="accion" value="Cancelar" class="pae-buttons pae-buttons--default pae-buttons--default-modal pae-buttons--medium mg-left-1"  onclick="volver('../spring/buscarEspecialidad')"/>
	</div>
	</div>


</fieldset>	
</div>
</div>
	
</form:form>
	
</div>
</body>
</html>


