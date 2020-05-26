<script type="text/javascript" src="<%=request.getContextPath()%>/js/solicitudes/solicitudes.js"></script>
	
	<%if(detalleRegistro == null || tipoPersona.equals("FH") && !continuar.equals("true")) { %>
	
<div data-function="accordion-box" class="pae-box">
  <div class="pae-box__header">
    <h3 class="pae-box__header--title"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento"/></h3>
  </div>
  <div class="pae-box__body pae-box__body--small">
    <div class="pae-form__box-text">
      <p class="pae-form__box-description"><strong class="pae-form__box-description pae-form__box-description--bold"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__1"/></strong></p>
      <p class="pae-form__box-description"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__2"/></p>
      <p class="pae-form__box-description"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__3"/></p>
      <p class="pae-form__box-description"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__4"/>
<%--       <a target="_blank" href="<%=request.getContextPath()%>/secure/tablaComunidades"><spring:message code="field.convocatoria.mensajePulsAqui"/></a></p> --%>
    </div>
    <%-- habilitar check consentimiento INI --%>
    <input type="hidden" id="consiente" value="${altaSolicitud.idConsentimiento}"/>
    <input type="hidden" id="consienteAEAT" value="${altaSolicitud.idConsentimientoAEAT}"/>
    
    <script type="text/javascript">
   	 	$(document).ready(function(){
   	 		if ($("#consiente").val() == "false") {
   	 			$("#ck-accept-access-db").click();
   	 		}
   	 	});
   	 $(document).ready(function(){
	 		if ($("#consienteAEAT").val() == "true") {
	 			$("#ck-accept-access-db-AEAT").click();
	 		}
	 	});
    </script>
    <div class="pae-form__text-small">
      <p class="pae-form__text-small pae-form__text pae-form__text--bold"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__7"/></p>
      <c:choose>
	      <c:when test="${altaSolicitud.enlace eq null}">
	      	<p class="pae-form__text-small pae-form__text"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__8.1"/> <spring:message code="field.convocatoria.mensajeFirmaConsentimiento__8.2"/> <spring:message code="field.convocatoria.mensajeFirmaConsentimiento__8.3"/>
	      	<a target="_blank" href="<spring:message code="field.convocatoria.mensajeFirmaConsentimiento__8.4"/>"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__8.4"/></a><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__8.5"/></p>
	      </c:when>
	      <c:otherwise>
	      	<p class="pae-form__text-small pae-form__text"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__8.1"/> <a target="_blank" href="${altaSolicitud.enlace}">${altaSolicitud.enlace}</a><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__8.3"/>
	      	<a target="_blank" href="<spring:message code="field.convocatoria.mensajeFirmaConsentimiento__8.4"/>"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__8.4"/></a><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__8.5"/></p>
	      </c:otherwise>
      </c:choose>
    </div>
    <br>
    <%-- habilitar check consentimiento FIN --%>
    <input type="checkbox" name="ck-accept-access-db" id="ck-accept-access-db" value="" class="pae-form__custom-check" onchange="cambiarConsentimiento(this.checked)">
    <label for="ck-accept-access-db" class="pae-form__custom-check-label pae-form__custom-check-label--verify"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__5"/></label>
    <div class="pae-form__advisor-tooltip pae-hidden" id="access-db-advisor">
      <p class="pae-form__advisor-text"><strong class="pae-form__advisor-text pae-form__advisor-text--bold"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__6"/></strong>
       <spring:message code="field.convocatoria.mensajeFirmaConsentimiento__6.1"/> <strong class="pae-form__advisor-text pae-form__advisor-text--bold"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__6.2"/> </strong> 
       <spring:message code="field.convocatoria.mensajeFirmaConsentimiento__6.3"/>
      </p>
    </div>
    <div class="pae-form__text">
  			<label for="motivoOposicion" id="lblMotivoOposicion" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.motivoOposicion"/></span></label>
			<form:input path="motivoOposicion" type="text" name="motivoOposicion2" id="motivoOposicion2" disabled="true" maxlength="256" class="pae-form__input motivoOposicion" style="text-transform: uppercase;" onPaste="comprobarPasteCaracteresEspeciales(this);" onkeypress="return validar(event)" value="${altaSolicitud.motivoOposicion}"/>
 	</div>
 	<div>
     	 <p class="pae-form__text-small pae-form__text"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__5.1"/></p>
    </div>
    <div class="pae-form__text-small">
     	 <p class="pae-form__text-small pae-form__text"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__5.2"/></p>
    </div>
    <br>
    <input type="checkbox" name="ck-accept-access-db-AEAT" id="ck-accept-access-db-AEAT" value="" class="pae-form__custom-check" onchange="cambiarAutorizar(this.checked)">
    <label for="ck-accept-access-db-AEAT" class="pae-form__custom-check-label pae-form__custom-check-label--verify"><spring:message code="formulario790.autorizar"/></label>
    <div>
     	 <p class="pae-form__text-small pae-form__text"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__5.3"/></p>
    </div>
    <br>    
  </div>
</div>
<%} %>
            <!-- Errores Configurables INFO - INI -->
            <c:if test="${listaErroresInfo.size() > 0}">
            <div id="modal" class="pae-modal" name="modal">
	     		<div class="pae-modal__body pae-modal__body--table">
                    <div class="pae-layout__item pae-u-12/12 pae-u-4/12-lap pae-u-1/1-palm">
                      <div data-opcion="descripciones" class="pae-form__cell pae-modal__footer--padding-bottom">
	   					  <c:forEach var="errorInfo" items="${listaErroresInfo}">
								   <p class="pae-modal__text pae-modal__text--special">- ${errorInfo.descripcion }</p>
						    </c:forEach>
	   					</div>
	 				</div>
	 				<div class="pae-layout__item pae-u-11/12 pae-u-4/12-lap pae-u-1/1-palm">
					     <div class="pae-box-buttons pae-modal__footer--padding-bottom">
					     	<c:set var="tipo"  value="<%=click%>"/>
							<c:choose>
							    <c:when test="${tipo eq 'firmaryPagar()'}">
							        <button type="button" onclick="completarPagar();"; class="pae-buttons pae-buttons--default pae-buttons--medium outline pae-separation pae-button-right" ><spring:message code="field.convocatoria.continuar"/></button>		
							    </c:when>
							    <c:when test="${tipo eq 'firmaryRegistrar()'}">
							        <button type="button" onclick="completarRegistrar();"; class="pae-buttons pae-buttons--default pae-buttons--medium outline pae-separation pae-button-right" ><spring:message code="field.convocatoria.continuar"/></button>		
							    </c:when>     
							    <c:otherwise>
							        <button type="button" onclick="completar(0);"; class="pae-buttons pae-buttons--default pae-buttons--medium outline pae-separation pae-button-right" ><spring:message code="field.convocatoria.continuar"/></button>
							    </c:otherwise>
							</c:choose>					     
    						<button type="button" onclick="$('#modal').dialog('close');" class="pae-buttons pae-buttons--default pae-buttons--medium outline pae-separation pae-button-right" ><spring:message code="field.convocatoria.cancelar"/></button>
    					</div>
	 				</div>
				</div>
			</div>
			</c:if>
           <!-- Errores Configurables INFO - FIN --> 
<div class="pae-box pae-box--transparent">
  <div class="pae-box__body--xsmall">
    <input type="checkbox" name="conforme" id="conforme" value="" class="pae-form__custom-check">
    <label for="conforme" class="pae-form__custom-check-label pae-form__custom-check-label--final-check"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__10"/></label>
    <div class="pae-form__advisor-tooltip pae-hidden" id="conforme-db-advisor">
     <p class="pae-form__advisor-text"><spring:message code="field.informacion.noconformidad"/></strong></p>
    </div>
 </div>
</div>
<div class="pae-box pae-box--transparent pae-box--align-right pae-box--force-align-center pae-box--border-top">
  <div class="pae-box__body--big">
    <div class="pae-box-buttons">
    <% if(subsanarInscripcion != null && subsanarInscripcion  ){ %>
      <button type="button" id="botonFirma" class="pae-buttons pae-buttons--default pae-buttons--medium pae-buttons--text-size-normal" onclick="<%=click%>"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__13"/></button>
   	<% } else { %>
      <button type="button" id="botonFirma" class="pae-buttons pae-buttons--default pae-buttons--medium pae-buttons--text-size-normal" onclick="<%=click%>"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__11"/></button>
   	<% } %>
    </div>
    <div class="pae-box-buttons pae-box-buttons--mg-top display-palm">
      <a href="buscarConvocatorias?form=L" class="pae-buttons pae-buttons--white pae-buttons--medium pae-buttons--text-size-normal"><spring:message code="field.convocatoria.mensajeFirmaConsentimiento__12"/></a>
    </div>
  </div>
</div>