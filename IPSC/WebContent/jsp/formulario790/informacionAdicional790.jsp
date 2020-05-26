<script type="text/javascript">

function validarDatosInformacionAdicional(error){


	if(document.getElementById("titulo").value == null  || document.getElementById("titulo").value=="-" || document.getElementById("titulo").selectedIndex==0){
		error=1;
		document.getElementById("titulo").className += " pae-form__input--error";
		if($("#setFocus").val() == "")
			$("#setFocus").val('titulo');
	}


	
	if(error == 1) {
		document.getElementById("boxInformacionAdicional").className = "pae-box pae-box--error";
		return false;
	}else {
		document.getElementById("boxInformacionAdicional").className = "pae-box";
		document.getElementById("titulo").className = "pae-form__input";
		return true;
	}
}

</script>
		<div data-function="accordion-box" class="pae-box" id="boxInformacionAdicional">
              <div class="pae-box__header">
                <h3 class="pae-box__header--title"><spring:message code="field.formulario790.informacionAdicional" /></h3>
              </div>
              <div class="pae-box__body">
                <fieldset>
                  <legend class="pae-form__legend-text">
                  	<spring:message code="field.formulario790.titulos.tituloAux" />
                  </legend>
                  <div class="pae-layout">
                    <div class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell">
                        <label for="titulo" class="pae-form__label">
	                        <span class="pae-form__label--text">
	                        	<spring:message code="field.formulario790.titulos.exigiidosAux" />
	                        	<logic:equal name="plantilla" property="titulosExigidos" value="S">
									<input type="hidden" id="obl_titulo" value="true">
									<span class="obligatorio">*</span>
								</logic:equal>
	                        </span>
                        </label>
                        <logic:present name="titulosOficiales" scope="request">
							<bean:size id="numTitulosOficiales" name="titulosOficiales" scope="request"/>
								<logic:greaterThan name="numTitulosOficiales" value="0">
									<form:select path="tituloOficial" id="tituloOficial" class="pae-form__input" onchange="comprobarTitulo();buscaTituloOficial();" style="text-transform: uppercase;">
										<option value="0"><spring:message code="field.solicitud.selecciona" /></option>
										<option value="-1"><spring:message code="field.formulario790.convocatoria.introduzcaValor.titulos"/></option>
										<form:options items="${titulosOficiales}" itemValue="id" itemLabel="descripcion" />
									</form:select>
									<span class="pae-form__text-error">
										<spring:message code="field.solicitud.campoObligatorio" />
									</span>	
								</logic:greaterThan>
							</logic:present>
						<logic:present name="convocatoria" scope="request">
							<form:select path="tituloOficial" class="pae-form__input" id="tituloOficial" onchange="buscaTituloOficialConvocatoria();return cambiarMayusculasText(this);" style="text-transform: uppercase;">
								<option value="0"><spring:message code="field.solicitud.selecciona" /></option>
								<form:options items="${convocatoria.tituloOficials}" itemValue="id" itemLabel="descripcion" />
							</form:select>
							<span class="pae-form__text-error">
								<spring:message code="field.solicitud.campoObligatorio" />
							</span>
						</logic:present>
						<logic:notPresent name="convocatoria" scope="request">
				            <input type="text" class="pae-form__input" name="tituloManual" id="tituloManual" maxlength="80" style="text-transform: uppercase;"/>
				            <span class="pae-form__text-error">
				            	<spring:message code="field.solicitud.campoObligatorio" />
				            </span>
			            </logic:notPresent>
			            <form:input path="codigoTituloExigido" id="codigoTituloExigido" tabindex="-1" size="4" maxlength="5" class="pae-hidden" readonly="true" /></br>
                      </div>
                    </div>
                    <div class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell pae-form__cell--margin-bottom">
                      	<a href="#" id="anyadeOtroTitulo" title="Añadir otro título" data-function="function-click-add-title" class="pae-form__icon-link--add">
                      		<span class="pae-form__icon-link-text">
                      			<spring:message code="field.formulario790.titulos.agregarTitulo" />
                      		</span>
                      	</a>
                      </div>
                      <div class="pae-form__cell pae-form__cell--hide">
                        <label for="otrosTitulos" class="pae-form__label">
                        	<span class="pae-form__label--text">
                        		<spring:message code="field.formulario790.titulos.exigiidosAux2" />
                        		<logic:equal name="plantilla" property="otrosTitulos" value="S">
									<input type="hidden" id="obl_otrosTitulos" value="true">
									<span class="obligatorio">*</span>
								</logic:equal>
                        	</span>
                        </label>
                        <!-- Otros Titulos - INI -->
                        <c:choose>
						    <c:when test="${formulario790Form.otrosTitulosList != null && formulario790Form.otrosTitulosList.size() > 0}">
					       		<form:select path="otrosTitulos" id="otrosTitulos" class="pae-form__input pae-form__input--s-large" style="text-transform: uppercase;">
									<option value=""><spring:message code="field.solicitud.selecciona" /></option>
									<form:options items="${formulario790Form.otrosTitulosList}" itemValue="descripcion" itemLabel="descripcion" />
								</form:select>
						    </c:when>    
						    <c:otherwise>
                        <input type="text" name="otrosTitulos" property="otrosTitulos" id="otrosTitulos" value="" 
                        	class="pae-form__input pae-form__input--s-large" maxlength="200" style="text-transform: uppercase;">
						    </c:otherwise>
						</c:choose>	
                        <!-- Otros Titulos - FIN -->	
                        <span class="pae-form__text-error">
							<spring:message code="field.solicitud.campoObligatorio" />
						</span>
                        <div id="papelera" class="pae-form__icon-contain">
	                        <a href="#" title="Eliminar Campo" data-function="function-click-remove-title" class="pae-form__icon-link--remove">
		                        <span class="pae-form__icon-link-text">
		                        	<spring:message code="field.solicitud.botonEliminar"/>
		                        </span>
	                        </a>
                        </div>
                      </div>
                    </div>
                  </div>
                </fieldset>
                <c:choose>
	                <c:when test="${ocultarDatosPropios.toString() != 'S'}">
		                <fieldset>
		                  <legend class="pae-form__legend-text">
		                  	<spring:message code="field.formulario790.datosConsignarMin" />
		                  </legend>
		                  <div class="pae-layout" >
		                    <logic:present name="plantillaPropios" scope="request">
								<logic:iterate id="campo" name="plantillaPropios" >
									<div id="campoDinamico-${campo.campoPropioBean.id}" class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm">
		                   				<div class="pae-form__cell">
		                       				<label for="plantilla${campo.campoPropioBean.id}" class="pae-form__label">
		                       					<span class="pae-form__label--text">
		                       						<bean:write name="campo"  property="campoPropioBean.tituloCampo"/>
		                       						<logic:equal name="campo" property="obligatorio" value="true">
														<input type="hidden" id="obl_campoPropio${campo.campoPropioBean.id}" value="true">
														<span class="obligatorio">*</span>
													</logic:equal>
		                       					</span>
		                       				</label>
		                       				<!-- DATOS PROPIOS CONF INI -->
		                       				<c:choose>
			           						    <c:when test="${campo.campoPropioBean.datosPropiosConfiguracion != null && campo.campoPropioBean.datosPropiosConfiguracion.size() > 0}">
											    	<select id="plantilla${campo.campoPropioBean.id}" name="plantilla${campo.campoPropioBean.id}" class="pae-form__input" >
														<option value=""><spring:message code="field.solicitud.selecciona" /></option>
														 <c:forEach items="${campo.campoPropioBean.datosPropiosConfiguracion}" var="datoConf">
														 	<option value="${datoConf.descripcion}">${datoConf.descripcion}</option>
														 </c:forEach>
													</select>
											    </c:when>    
											    <c:otherwise>
		                       				<input type="text" id="plantilla${campo.campoPropioBean.id}" name="plantilla${campo.campoPropioBean.id}" class="pae-form__input" 
		                       					style="text-transform: uppercase;" onPaste="comprobarPasteCaracteresEspeciales(this);" maxlength="200">
											    </c:otherwise>
										    </c:choose>
										    <!-- DATOS PROPIOS CONF FIN -->
		                       				<span class="pae-form__text-error">
												<spring:message code="field.solicitud.campoObligatorio" />
											</span>
		                       			</div>	
		                   			</div>
		                   		</logic:iterate>
							</logic:present>
		                  </div>
		                  <input type="hidden" id="validarCampos"/>
		                </fieldset>
		             </c:when>
		          </c:choose>
              </div>
            </div>