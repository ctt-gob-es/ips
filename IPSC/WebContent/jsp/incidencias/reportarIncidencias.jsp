<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String envio = (String)request.getAttribute("envio");
System.out.println("Envio: "+envio);
String resultEnvio ="";
if(envio != null){
	resultEnvio = envio;
}

%>
<script type="text/javascript">

$( document ).ready(function() {
	$("form").append("<input style='display: none' id='buttonSubmit' type='submit'/>");
});

var confirmacion = "<%=resultEnvio%>";

function validar(){
	if(document.getElementById("texto").value == ""){
		alert("<spring:message code='field.incidencias.jsp.errorTexto'/>");
		return false;
	}else{
		var textoArea = document.getElementById("texto").value;
		if(textoArea.length>1800){
			alert("<spring:message code='field.incidencias.jsp.maxCaracteres'/>");
			return false;
		}
	}
	//Añadido verificacion de Asunto
	if(document.getElementById("asunto").value=="") { 
		alert("<spring:message code='field.incidencias.jsp.errorAsunto'/>");
		return false;
	}else{
		var textoAsunto = document.getElementById("asunto").value;
		if(textoAsunto.length>100){
			alert("<spring:message code='field.incidencias.jsp.maxCaracteres'/>");
			return false;
		}
	}
	
	if(document.getElementById("email").value == ""){ 
		alert("<spring:message code='field.incidencias.jsp.errorEmail'/>");
		return false;
	}else{
		var email = document.getElementById("email").value;
		var op = validarCorreo(email);
		if(!op){		
			return false;
		}
	}
	if(document.getElementById("telefono").value == ""){
		alert("<spring:message code='field.incidencias.jsp.errorTelefono'/>");
		return false;
	}else{
		var telf = document.getElementById("telefono").value;
		var op2 = comprueba(telf);
		if(!op2){
			return false;
		}
	}
	document.getElementById("accion").value = "Enviar";
	return true;
}

function comprueba(obj){ 
	if (isNaN(obj)){
		alert("<spring:message code='field.incidencias.telefonoError'/>"); 
		return false; 
	} else { 
	return true; 
	} 
} 
//Funcion para volver a la pagina principal
function home(url){
	document.getElementById("ConvocatoriasEnlaceReportar").action = url;
	$("#buttonSubmit")[0].formAction = url;
	$("#buttonSubmit").click();
}

function validarCorreo(ctrl) { 
    var mailres = true;        
    var cadena = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ1234567890@._-";
    var arroba = 0;
    var punto = 0; 
    var texto = ctrl;
    if (texto!=""){ 
	    //comprobacion de si existe mas de una @
	    arroba = texto.indexOf("@",0); 
	    if ((texto.lastIndexOf("@")) != arroba){
	    	arroba = -1;
	    } 
	
	    //comprobacion de que existen caracteres despues del ultimo punto
	    punto = texto.lastIndexOf("."); 
	    //Comprobacion de que los caracteres de la direccion son correctos
	    for (var i = 0 ; i < texto.length ; i++){
	    	if (cadena.indexOf(texto.substr(i,1),0) == -1){ 		    	
	        	mailres = false; 
	            break;
	      	} 
	    } 
	
	    if ((arroba <= 0) || (arroba + 1 >= punto) || (punto + 1 == (texto.length)) || (mailres == false) || (texto.indexOf("..",0) != -1)){ 
	    	alert("<spring:message code='field.incidencias.jsp.errorEmailFormato'/>");
	    	return false;
	    }
    }    	
    return true;
}
</script>

<html>
<head>
  <!-- Título página-->
  <title>Inscripci&oacute;n online</title>
  <!-- Etiquetas META-->
  <meta charset="UTF-8"/>
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
</head>
<body>
  <div class="pae-body pae-body--without-border pae-body--without-padding-mobile">
  	<form:form modelAttribute="incidenciasForm" action="/IPSC/secure/reportarIncidencias" method="post">
  	<logic:present name="validacion" scope="request">
	<div id="error">
		<form:errors/>
		<br>
	</div>
	</logic:present>
	
	<logic:present name="mensajeConfirmacion" scope="request">
	<div class="pae-wrapper">
  		<div class="pae-layout">
   			<div class="pae-layout__item pae-u-12/12 pae-u-12/12-lap pae-u-1/1-palm">
  				<div class="pae-box-details pae-alertbox pae-alertbox__2 pae-alertbox--success">
  				<div class="pae-alertbox__body pae-alertbox__body__2 pae-alertbox__body__2--mobile">
            		<h2 class="pae-alertbox__text"><bean:write name="mensajeConfirmacion" scope="request"/></h2>
            		</div>
    			</div>
    		</div>
  		</div>
   </div>
	</logic:present>
	<logic:present name="mensajeError" scope="request">
	<div class="pae-wrapper">
  		<div class="pae-layout">
   			<div class="pae-layout__item pae-u-12/12 pae-u-12/12-lap pae-u-1/1-palm">
   			 <div class="pae-alertbox pae-alertbox__2 pae-alertbox--warning">
            <div class="pae-alertbox__body pae-alertbox__body__2 pae-alertbox__body__2--mobile">
              <p class="pae-alertbox__text"><bean:write name="mensajeError" scope="request"/></p>
            </div>
          </div>
    		</div>
  		</div>
   </div>
	</logic:present>
	<div class="clear"></div>
    <div class="pae-wrapper">
      <div class="pae-layout">
        <div class="pae-layout__item pae-u-12/12 pae-u-12/12-lap pae-u-1/1-palm">
          <div class="pae-contact">
            <h2 class="pae-title">Contacto</h2>
            <p class="pae-sub-title">Para cualquier duda o incidencia, completa el siguiente formulario</p>
            <form action="#" method="post" class="pae-form">
              <div class="pae-box">
                <div class="pae-box__body">
                  <fieldset>
                    <legend class="pae-form__legend-text hiddenAccesible">Formulario de contacto</legend>
                    <div class="pae-layout">
                      <div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
                        <div class="pae-form__cell">
                          <label for="nif" class="pae-form__label"><span class="pae-form__label--text">NIF</span></label>
                          <!--  <input type="text" id="nif" name="nif" value="" class="pae-form__input">-->
                          <div class="pae-form__input">
						  	<bean:write name="incidencia" property="nif" scope="request" />
						  </div>
                        </div>
                      </div>
                      <div class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm">
                        <div class="pae-form__cell">
                          <label for="nombre" class="pae-form__label"><span class="pae-form__label--text">Nombre y apellidos</span></label>
                          <!-- <input type="text" id="nombre" name="name" value="" class="pae-form__input"> -->
                          <div class="pae-form__input">
						  	<bean:write name="incidencia" property="nombre" scope="request" />		
						  </div>
                        </div>
                      </div>
                    </div>
                    <div class="pae-layout">
                      <div class="pae-layout__item pae-u-4/9 pae-u-4/9-lap pae-u-1/1-palm">
                        <div class="pae-form__cell">
                          <label for="email" class="pae-form__label"><span class="pae-form__label--text">Email</span></label>
                          <input type="text" id="email" name="email" value="" class="pae-form__input" maxlength="50">
                        </div>
                      </div>
                      <div class="pae-layout__item pae-u-2/9 pae-u-2/9-lap pae-u-1/1-palm">
                        <div class="pae-form__cell">
                          <label for="telefono" class="pae-form__label"><span class="pae-form__label--text">Tel&eacute;fono</span></label>
                          <input type="text" id="telefono" name="telefono" value="" maxlength="9" class="pae-form__input">
                        </div>
                      </div>
                    </div>
                    <div class="pae-layout">
                      <div class="pae-layout__item pae-u-6/9 pae-u-6/9-lap pae-u-1/1-palm">
                        <div class="pae-form__cell">
                          <label for="asunto" class="pae-form__label"><span class="pae-form__label--text">Asunto del mensaje</span></label>
                          <input type="text" id="asunto" name="asunto" value="" maxlength="50" class="pae-form__input">
                        </div>
                      </div>
                    </div>
                    <div class="pae-layout">
                      <div class="pae-layout__item pae-u-6/9 pae-u-6/9-lap pae-u-1/1-palm">
                        <div class="pae-form__cell">
                          <label for="texto" class="pae-form__label"><span class="pae-form__label--text">Mensaje</span></label>
                          <textarea id="texto" name="texto" class="pae-form__textarea" maxlength="1000"></textarea>
                        </div>
                      </div>
                    </div>
                  </fieldset>
                </div>
              </div>
              <div class="pae-box-buttons">
              	<button type="button" class="pae-buttons pae-buttons--default pae-buttons--medium outline pae-buttons--half pae-separation" value="Volver" 
              		onclick="javascript: home('<%=request.getContextPath()%>/secure/buscarConvocatorias')">Volver</button>
             	<button type="submit" name="accion" value="Enviar" class="pae-buttons pae-buttons--default pae-buttons--medium outline pae-buttons--half pae-separation" 
               		onclick="return validar();">Enviar mensaje</button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
    </form:form>
  </div>
</body></html>
</html>
<!-- Requerido para ir a la pagina principal cuando se pulsa el botón de volver -->
<form  name="ConvocatoriasEnlace" id="ConvocatoriasEnlace" action="<%=request.getContextPath()%>/secure/buscarConvocatorias?form=L"  method="POST"> </form>