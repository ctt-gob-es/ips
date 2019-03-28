<script type="text/javascript">
$( document ).ready(function() {
	if($("#porcentajeDiscapacidad").val() > 0 && $("#porcentajeDiscapacidad").val() <33) {
		$('#datosDiscapacidad').removeClass('pae-hidden');
		$("#porcentajeDiscapacidad").attr('disabled','disabled');
		$("#detalleDiscapacidad").attr('disabled','disabled');
	}
});

function desmarcarChecks() {
	var grado = $("#porcentajeDiscapacidad").val();
	var adaptacion = $("#detalleDiscapacidad").val();
	if ($("#porcentajeDiscapacidad").val() <33) {
		document.getElementById("DISCAPACIDAD").previousSibling.previousSibling.checked=false;
		actualizarImporte(document.getElementById("DISCAPACIDAD").previousSibling.previousSibling.value);
		$('#datosDiscapacidad').removeClass('pae-hidden');
	} else if ($("#porcentajeDiscapacidad").val() >=33) {
		document.getElementById("DISCAPACIDAD").previousSibling.previousSibling.checked=true;
		var grado = $("#porcentajeDiscapacidad").val();
		actualizarImporte(document.getElementById("DISCAPACIDAD").previousSibling.previousSibling.value);
	}
	$("#porcentajeDiscapacidad").val(grado);
	$("#detalleDiscapacidad").val(adaptacion);
}
</script>

		<c:if test="${altaSolicitud.idMotivoReduccion eq 1}">
		<div class="pae-layout__item pae-u-12/12 pae-u-12/12-lap pae-u-1/1-palm pae-margin-top"
		id="datosDiscapacidad">
		</c:if>
		<c:if test="${altaSolicitud.idMotivoReduccion ne 1}">
		<div class="pae-layout__item pae-u-12/12 pae-u-12/12-lap pae-u-1/1-palm pae-margin-top pae-hidden"
		id="datosDiscapacidad">
		</c:if>
              <legend class="pae-form__legend-text">
				<spring:message code="field.convocatoria.preguntaDiscapacidad" />
			  </legend>
                <fieldset>
                  <legend class="pae-form__legend-text hiddenAccesible">
                  	<spring:message code="field.convocatoria.preguntaDiscapacidad"/>
                  </legend>
                  <div class="pae-layout">
                    <div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/3-palm">
                      <div class="pae-form__cell">
                        <label for="porcentajeDiscapacidad" class="pae-form__label">
	                        <span class="pae-form__label--text">
	                        	<spring:message code="field.formulario790.convocatoria.gradoDiscapacidadAux"/>
	                        	<logic:equal name="plantilla" property="porcentajeDiscapacidad" value="S">
									<input type="hidden" id="obl_porcentajeDiscapacidad" value="true">
									<span class="obligatorio">*</span>
								</logic:equal>
	                        </span>
                        </label>
                        <input type="text" name="porcentajeDiscapacidad" id="porcentajeDiscapacidad" value="${altaSolicitud.porcentajeDiscapacidad }" onchange="calcularImporte2();desmarcarChecks();"  maxlength="3" value="" onkeypress="return soloNumeros(event);" onpaste="comprobarPasteCaracteresNumericos(this);"
                        	title="<spring:message code="field.formulario790.convocatoria.tituloGradoDiscapacidad"/>" class="pae-form__input pae-form__input--medium"> %
                       </div>
                    </div>
                    <div class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell" >
                        <label for="state" id="labelComunidadDDExento" class="pae-form__label">
	                        <span class="${altaSolicitud.porcentajeDiscapacidad lt 33 ? "pae-form__label--disabled" : "pae-form__label--text"}">
	                        	<spring:message code="field.formulario790.convocatoria.ccaaDiscapacidad"/>
								<span class="obligatorio" id="oblComunidadDDExento" style="${altaSolicitud.porcentajeDiscapacidad lt 33 ? "display: none;" : ""}">*</span>
	                        </span>
                        </label>
                        <form:select path="idComunidadDDExento" class="pae-form__input pae-form__select--disabled" value="${altaSolicitud.idComunidadDDExento }" disabled="${altaSolicitud.porcentajeDiscapacidad lt 33}">
                            <option value="0" class="pae-form__option">
                            	<spring:message code="field.solicitud.selecciona"/>
                            </option>
                        	<!-- <form:options items="${listcomunidadesDiscapacidad}" itemLabel="descripcion" itemValue="idComunidad" style="text-transform: uppercase;"/> -->
                        	<c:forEach items="${listcomunidadesDiscapacidad}" var="comunidad">
						        <c:choose>
						            <c:when test="${comunidad.idComunidad eq altaSolicitud.idComunidadDDExento}">
						                <option value="${comunidad.idComunidad}" selected="true">${comunidad.descripcion}</option>
						            </c:when>
						            <c:otherwise>
						                <option value="${comunidad.idComunidad}">${comunidad.descripcion}</option>
						            </c:otherwise>
						        </c:choose> 
						    </c:forEach>
                        </form:select>
                        <span class="pae-form__text-error">
                        	<spring:message code="field.solicitud.campoObligatorio"/>
                        </span>
                      </div>
                    </div>
                  </div>
                  <div class="pae-layout">
                    <div class="pae-layout__item pae-u-1/1 pae-u-1/1-lap pae-u-1/1-palm">
                      <div id="advisor-disabilities" class="pae-form__cell pae-hidden">
                        <div class="pae-form__advisor-tooltip pae-form__advisor-tooltip--no-arrow pae-form__advisor-tooltip--no-shadow pae-form__advisor-tooltip--big">
                          <p class="pae-form__advisor-text">
                          	<spring:message code="field.solicitud.recordatorioDocumentos"/>
                          	<strong class="pae-form__advisor-text pae-form__advisor-text--bold"><spring:message code="field.convocatoria.mensajeDiscapacidad__1"/></strong> y 
                          	<strong class="pae-form__advisor-text pae-form__advisor-text--bold"><spring:message code="field.convocatoria.mensajeDiscapacidad__2"/>
                          	</strong> <spring:message code="field.convocatoria.mensajeDiscapacidad__3"/>
                          	<strong class="pae-form__advisor-text pae-form__advisor-text--bold"><spring:message code="field.convocatoria.mensajeDiscapacidad__4"/></strong>
                          </p>
                          	<button name="advisor-disabilities-close" id="advisor-disabilities-close" data-function="fc-advisor-close" class="pae-form__advisor-close"></button>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="pae-layout">
                    <div class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell">
                      	<span class="pae-form__label pae-form__span-label">
                      		<spring:message code="field.formulario790.convocatoria.reservaDiscapacidadAux"/>
                      		<logic:equal name="plantilla" property="reservaDiscapacidad" value="S">
							<input type="hidden" id="obl_reservaDiscapacidad" value="true">
								<span class="obligatorio">*</span>
							</logic:equal>
                      	</span>
                        <div class="pae-form__custom-rb-box">
	                        <c:if test="${altaSolicitud.tipoDiscapacidad eq null}">
	                          <input type="radio" name="tipoDiscapacidad" path="tipoDiscapacidad" id="reservation-no" value="0" checked="checked" class="pae-form__custom-rb" onchange="cambioTipoDiscapacidad();"/>
	                        </c:if>
	                        <c:if test="${altaSolicitud.tipoDiscapacidad ne null}">
	                          <input type="radio" name="tipoDiscapacidad" path="tipoDiscapacidad" id="reservation-no" value="0" class="pae-form__custom-rb" onchange="cambioTipoDiscapacidad();"/>
	                        </c:if>
                          <label for="reservation-no" class="pae-form__custom-rb-label small">
                          	<span class="pae-form__custom-rb-label--text">
                          		<spring:message code="field.convocatoria.reservaDiscapacidad.sinDiscapacidad" />
                          	</span>
                          </label>
                          <c:if test="${altaSolicitud.tipoDiscapacidad eq 1}">
                         	 <input type="radio" name="tipoDiscapacidad" path="tipoDiscapacidad" id="reservation-general" value="1" checked="checked" class="pae-form__custom-rb" onchange="cambioTipoDiscapacidad();" />
                          </c:if>
                          <c:if test="${altaSolicitud.tipoDiscapacidad ne 1}">
                         	 <input type="radio" name="tipoDiscapacidad" path="tipoDiscapacidad" id="reservation-general" value="1" class="pae-form__custom-rb" onchange="cambioTipoDiscapacidad();" />
                          </c:if>
                          <label for="reservation-general" class="pae-form__custom-rb-label small">
                          	<span class="pae-form__custom-rb-label--text">
                          		<spring:message code="field.convocatoria.reservaDiscapacidad.general"/>
                          	</span>
                          </label>
                          <c:if test="${altaSolicitud.tipoDiscapacidad eq 2}">
                         	 <input type="radio" name="tipoDiscapacidad" path="tipoDiscapacidad" id="reservation-intellectual" value="2" checked="checked" class="pae-form__custom-rb" onchange="cambioTipoDiscapacidad();"/>
                          </c:if>
                          <c:if test="${altaSolicitud.tipoDiscapacidad ne 2}">
                         	 <input type="radio" name="tipoDiscapacidad" path="tipoDiscapacidad" id="reservation-intellectual" value="2" class="pae-form__custom-rb" onchange="cambioTipoDiscapacidad();"/>
                          </c:if>
                          <label for="reservation-intellectual" class="pae-form__custom-rb-label small">
                          	<span class="pae-form__custom-rb-label--text">
                          		<spring:message code="field.convocatoria.reservaDiscapacidad.intelectual"/>
                          	</span>
                          </label>
                        </div>
                      </div>
                    </div>
                    <div class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell">
                        <label for="detalleDiscapacidad" class="pae-form__label">
                        	<span class="pae-form__label--text">
                        		<spring:message code="field.formulario790.convocatoria.motivoDiscapacidadAux" />
                        		<logic:equal name="plantilla" property="detalleDiscapacidad" value="S">
									<input type="hidden" id="obl_detalleDiscapacidad" value="true">
									<span class="obligatorio">*</span>
								</logic:equal>
                        	</span>
                        </label>
	                    <!-- Discapacidad - INI -->
	                    <c:choose>
						    <c:when test="${solicitudForm.discapacidadList != null && solicitudForm.discapacidadList.size() > 0}">
					       		<select name="detalleDiscapacidad" id="detalleDiscapacidad" class="pae-form__input" style="text-transform: uppercase;" disabled>
									<option value="0"><spring:message code="field.solicitud.selecciona" /></option>
									<c:forEach var="discapacidad" items="${solicitudForm.discapacidadList}">
										<c:choose>
									        <c:when test="${altaSolicitud.detalleDiscapacidad eq discapacidad.descripcion}">
									        	<option value="${discapacidad.descripcion}" selected>${discapacidad.descripcion}</option>
									        </c:when>
									         <c:otherwise>
									         	<option value="${discapacidad.descripcion}">${discapacidad.descripcion}</option>
									         </c:otherwise>
									    </c:choose>     
									</c:forEach>
								</select>
						    </c:when>    
						 	<c:otherwise>
							<c:choose>
    							<c:when test="${altaSolicitud.porcentajeDiscapacidad > 0 && altaSolicitud.porcentajeDiscapacidad <= 100}">
        							<input type="text" name="detalleDiscapacidad"
										id="detalleDiscapacidad" value="${altaSolicitud.detalleDiscapacidad}" class="pae-form__input"
										onchange="return cambiarMayusculasText(this);"
										style="text-transform: uppercase;"  maxlength="200">
    							</c:when>
    						<c:otherwise>
        							<input type="text" name="detalleDiscapacidad"
										id="detalleDiscapacidad" value="${altaSolicitud.detalleDiscapacidad}" class="pae-form__input"
										disabled="true" onchange="return cambiarMayusculasText(this);"
										style="text-transform: uppercase;"  maxlength="200">
    						</c:otherwise>
							</c:choose>									    						 
						    </c:otherwise>
						</c:choose>	
	                  	<!-- Discapacidad - FIN -->		                  		                        
                        <span class="pae-form__text-error">
                        	<spring:message code="field.solicitud.campoObligatorio"/>
                        </span>
                      </div>
                    </div>
                  </div>
                </fieldset>
            </div>
 

<script type="text/javascript">         
            
function cambioTipoDiscapacidad() {
	if(document.getElementById("porcentajeDiscapacidad").value < 33) {
		document.getElementById("reservation-no").checked = true;
		document.getElementById("reservation-general").checked = false;
		document.getElementById("reservation-intellectual").checked = false;
	}
}
</script>