<div data-function="accordion-box" class="pae-box">
  <div class="pae-box__header">
    <h3 class="pae-box__header--title"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento"/></h3>
  </div>
  <div class="pae-box__body pae-box__body--small">
    <div class="pae-form__box-text">
      <p class="pae-form__box-description"><strong class="pae-form__box-description pae-form__box-description--bold"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__1"/></strong></p>
      <p class="pae-form__box-description"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__2"/></p>
      <p class="pae-form__box-description"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__3"/></p>
      <p class="pae-form__box-description"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__4"/></p>
     <%--  <p class="pae-form__box-description"><a target="_blank" href="<%=request.getContextPath()%>/secure/tablaComunidades"><spring:message code="field.convocatoria.mensajePulsAqui"/></a></p> --%>
    </div>
     <div class="pae-form__text-small">
      <p class="pae-form__text-small pae-form__text pae-form__text--bold"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__7"/></p>
            <c:choose>
	      <c:when test="${formulario790Form.enlace eq null}">
	      	<p class="pae-form__text-small pae-form__text"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__8.1"/> <spring:message code="field.convocatoria.mensajeFirmaConsentimiento__8.2"/> <spring:message code="field.convocatoria.mensajeFirmaConsentimiento__8.3"/>
	      	<a target="_blank" href="<spring:message code="field.convocatoria.mensajeFirmaConsentimiento__8.4"/>"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__8.4"/></a><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__8.5"/></p>
	      </c:when>
	      <c:otherwise>
	      	<p class="pae-form__text-small pae-form__text"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__8.1"/> <a target="_blank" href="${formulario790Form.enlace}">${formulario790Form.enlace}</a><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__8.3"/>
	      	<a target="_blank" href="<spring:message code="field.convocatoria.mensajeFirmaConsentimiento__8.4"/>"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__8.4"/></a><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__8.5"/></p>
	      </c:otherwise>
      </c:choose>
    </div> 
    <br>
    <input type="checkbox" name="ck-accept-access-db" id="ck-accept-access-db" value="" class="pae-form__custom-check" onchange="cambiarConsentimiento(this.checked)">
    <label for="ck-accept-access-db" class="pae-form__custom-check-label pae-form__custom-check-label--verify"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__5"/></label>
    <div class="pae-form__advisor-tooltip pae-hidden" id="access-db-advisor">
      <p class="pae-form__advisor-text"><strong class="pae-form__advisor-text pae-form__advisor-text--bold"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__6"/></strong>
       <spring:message code="field.convocatoria.mensajeFirmaConsentimiento__6.1"/> <strong class="pae-form__advisor-text pae-form__advisor-text--bold"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__6.2"/> </strong> 
       <spring:message code="field.convocatoria.mensajeFirmaConsentimiento__6.3"/>
      </p>
    </div>
    <div class="pae-form__text">
  			<label for="motivoOposicion" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.motivoOposicion"/></span></label>
			<input type="text" name="motivoOposicion" id="motivoOposicion" disabled="" maxlength="256" class="pae-form__input" style="text-transform: uppercase;" onPaste="comprobarPasteCaracteresEspeciales(this);"/>
 	</div>
    <div>
     	 <p class="pae-form__text-small pae-form__text"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__5.1"/></p>
    </div>
    <div class="pae-form__text-small">
     	 <p class="pae-form__text-small pae-form__text"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__5.2"/></p>
    </div>
    <br>
    <input type="checkbox" name="autorizar" id="autorizar" value="" class="pae-form__custom-check" onchange="cambiarAutorizar(this.checked)">
    <label for="autorizar" class="pae-form__custom-check-label pae-form__custom-check-label--verify"><spring:message code="formulario790.autorizar"/></label>
    <div>
     	 <p class="pae-form__text-small pae-form__text"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__5.3"/></p>
    </div>
    <br> 
	<div class="pae-layout">
  		<div class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm">
  			<div class="pae-form__cell">
  				<label for="personaFirmante" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.sr/sra"/></span></label>
				<form:input path="personaFirmante" readonly="true" maxlength="70" style="text-transform: uppercase;" class="pae-form__input pae-form__input--disabled__2"/>
 			</div>
  		</div>
  		<div class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm">
  			<div class="pae-form__cell">
  				<label for="lugarFirma" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.en" /></span></label>
				<input type="text" name="lugarFirma" id="lugarFirma" maxlength="50" class="pae-form__input" style="text-transform: uppercase;" onPaste="comprobarPasteCaracteresEspeciales(this);"/>
				<form:input path="fechaFirma" readonly="true" class="pae-form__input pae-form__input--disabled__3"/>
 			</div>
  		</div>
  	</div>
  	</div>
</div>
<div class="pae-box pae-box--transparent pae-box--align-right pae-box--force-align-center pae-box--border-top">
  <div class="pae-box__body--big">
    <div class="pae-box-buttons">
      <button type="submit" id="botonFirma" class="pae-buttons pae-buttons--default pae-buttons--medium pae-buttons--text-size-normal" ><spring:message code="field.formulario790.enviarSolicitud"/></button>
    </div>
    <div class="pae-box-buttons pae-box-buttons--mg-top display-palm">
     <a href="buscarConvocatorias?form=L" class="pae-buttons pae-buttons--white pae-buttons--medium pae-buttons--text-size-normal"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__12"/></a>
    </div>
  </div>
</div>
