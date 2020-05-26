		<div data-function="accordion-box" class="pae-box" id="boxConvocatoria">
              <div class="pae-box__header">
                <h3 class="pae-box__header--title"><spring:message code="field.DatosConvocatoria"/></h3>
              </div>
              <div class="pae-box__body">
			  	<fieldset>
                  <legend class="pae-form__legend-text hiddenAccesible">
                  	<spring:message code="field.DatosConvocatoria"/>
                  </legend>
                  <div class="pae-layout">
                    <div class="pae-layout__item pae-u-8/12 pae-u-8/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell">
                        <div class="control-group">
                        	<label for="area" class="pae-form__label">
                        		<span class="pae-form__label--text">
                        			<spring:message code="field.formulario790.convocatoria.especialidad"/>
                        			<logic:equal name="plantilla" property="especialidad" value="S">
                        				<input type="hidden" id="obl_especialidad" value="true">
                        				<span class="obligatorio">*</span>
                        			</logic:equal>
                        		</span>
                        	</label>
                        	<logic:equal name="altaSolicitud" property="modelo79007" value="1">
								<form:select path="especialidad" id="especialidad"  class="pae-form__input" disabled="true" >
									<option value="0" selected="selected" class="pae-form__option">
										<spring:message code="field.solicitud.selecciona"/>
									</option>
									<form:options items="${especialidades}" itemLabel="descripcion" itemValue="id" style="text-transform: uppercase;"/>	
								</form:select>
							</logic:equal>
							<logic:notEqual name="altaSolicitud" property="modelo79007" value="1">			
								<form:select path="especialidad" id="especialidad" onchange="cambiarMayusculas(this)" class="pae-form__input" >
									<option value="0" selected="selected" class="pae-form__option">
										<spring:message code="field.solicitud.selecciona"/>
									</option>
									<form:options items="${especialidades}" itemLabel="descripcion" itemValue="id" style="text-transform: uppercase;"/>
								</form:select>
								<span class="pae-form__text-error"><spring:message code="field.solicitud.campoObligatorio"/></span>
							</logic:notEqual>
                        </div>
                      </div>
                    </div>
                    <div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell">
                        <div class="control-group">
                          <label for="provinciaExamen" class="pae-form__label">
                          	<span class="pae-form__label--text">
                          		<spring:message code="field.formulario790.convocatoria.provinciaExamen"/>
                          			<logic:equal name="plantilla" property="provinciaExamen" value="S">
										<input type="hidden" id="obl_provinciaExamen" value="true">
										<span class="obligatorio">*</span>
									</logic:equal>
                          	</span>
                          </label>
                          
						  <form:select path="provinciaExamen" value="${altaSolicitud.provinciaExamen}" onchange="cambiarMayusculas(this)" class="pae-form__input">	
						  	<option value="0"><spring:message code="field.solicitud.selecciona"/></option>
							<form:options items="${provinciasExamen}" itemValue="id" itemLabel="descripcion"  style="text-transform: uppercase;"/>
						</form:select><span class="pae-form__text-error"><spring:message code="field.solicitud.campoObligatorio" />
                        </div>
                      </div>
                    </div>
                  </div>
                  <div data-funtion="accordion-simple" class="pae-form__accordion-simple">
                    <div class="pae-layout">
                      <div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
                        <div class="pae-form__cell">
                        	<span class="pae-form__label pae-form__span-label">
                        		<spring:message code="field.formulario790.convocatoria.cuerpoEscalaAux"/>
                        	</span>
                        	<span class="pae-form__text-readonly pae-form__text-readonly--small">
                        		<bean:write name="altaSolicitud" property="desCuerpoEscala" />
                        	</span>
                        </div>
                      </div>
                      <div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
                        <div class="pae-form__cell">
	                        <span class="pae-form__label pae-form__span-label">
	                        	<spring:message code="field.formulario790.convocatoria.formaAcceso"/>
	                        </span>
	                        <span class="pae-form__text-readonly pae-form__text-readonly--small">
	                        	<bean:write name="altaSolicitud" property="desFormaAcceso" />
	                        </span>
                        </div>
                      </div>
                      <div class="pae-layout__item pae-u-4/12 pae-u-3/12-lap pae-u-1/1-palm">
                        <div class="pae-form__cell">
	                        <span class="pae-form__label pae-form__span-label">
	                        	<spring:message code="field.formulario790.convocatoria.ministerioAux"/>
	                        </span>
	                        <span class="pae-form__text-readonly pae-form__text-readonly--small">
	                        	<bean:write name="altaSolicitud" property="desMinisterio" />
	                        </span>
                        </div>
                      </div>
                      <div class="pae-layout__item pae-u-2/12 pae-u-3/12-lap pae-u-1/1-palm">
                        <div class="pae-form__cell">
                        	<span class="pae-form__label pae-form__span-label">
                        		<spring:message code="field.formulario790.convocatoria.fechaBOE"/>
                        	</span>
                        	<span class="pae-form__text-readonly pae-form__text-readonly--small">
                        		<bean:write name="altaSolicitud" property="fechaBOE" />
                        	</span>
                        </div>
                      </div>
                    </div>
                  </div>
                  <div class="pae-layout">
                    <div class="pae-layout__item pae-u-1/1 pae-u-1/1-lap pae-u-1/1-palm">
                      <div class="pae-form__cell">
                      	<a data-funtion="fc-accordion-simple-bar" href="#" title="Desplegar datos de la convocatoria" data-nolink="fc-nolink" class="pae-form__accordion-simple-text pae-form__accordion-simple-text--down">
                      		<spring:message code="field.solicitud.datosConvocatoria"/>
                      	</a>
                      </div>
                    </div>
                  </div>
                </fieldset>
              </div>
            </div>