<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<body>	
<div class="pae-body">
	<div class="pae-wrapper">
      <div class="pae-layout">
        <div class="pae-layout__item pae-u-12/12 pae-u-1/1-lap pae-u-1/1-palm">
			<section id="inscripciones" class="pae-tabs__content">
              <div class="pae-login">
                <h2 class="pae-title"><spring:message code="field.infoClave5"/></h2>
                <div class="pae-login__contain">
                  <p class="pae-login__text"><spring:message code="field.infoClave1"/><span class="pae-login__text pae-login__text--highlighted"><spring:message code="field.infoClave2"/></span></p>
                  <ul class="unordered_list">
                  	<li><spring:message code="field.tipoAutenticacionClave1" /></li>
                  	<li><spring:message code="field.tipoAutenticacionClave2" /></li>
                  	<li><spring:message code="field.tipoAutenticacionClave3" /></li>
                  </ul>
                  <p class="pae-login__text"><spring:message code="field.infoClave3"/><a href="http://www.clave.gob.es/clave_Home/clave.html" target="_blank" title="Enlace a Cl@ve" class="pae-login__link"><span class="pae-login__link pae-login__link--text"><spring:message code="field.infoClave4"/></span></a></p>
                </div>
                <div class="pae-login__button-container"><a href="#" onclick="javascript:aceptar('C');return false;" title="Acceder a Cl@ve ciudadano" class="pae-login__button"><span class="pae-login__button--link">Ciudadano</span></a></div>
              </div>
            </section>
		  </div>
		</div>
	  </div>
	</div>
</div>  
		<form name="formulario" id="formulario" action="" method="POST">
			<input type="hidden" name="llamada" value="${llamadaReq}" />
			<input type="hidden" name="convocatoria" value="${convocatoriaReq}" />
			<input type="hidden" name="tipoPersona" value="${tipoPersona}" />
		</form>
		
	</body>

	<script type="text/javascript">
	
		function aceptar(tipoPersona){
			//Va a clave
			document.formulario[2].value = tipoPersona;
			document.formulario.action = "<%=request.getContextPath()%>/secure/clave";
			document.getElementById("formulario").submit();
		}
	</script>

