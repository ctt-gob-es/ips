<div data-function="accordion-box" class="pae-box" id="detallesBoxConvocatoria">
              <div class="pae-box__header">
                <h3 class="pae-box__header--title"><spring:message code="field.DatosConvocatoria"/></h3>
              </div>
              <div class="pae-box__body">
			  	<fieldset>
                  <legend class="pae-form__legend-text hiddenAccesible"><spring:message code="field.DatosConvocatoria"/></legend>
                  <div class="pae-layout">
                    <div class="pae-layout__item pae-u-8/12 pae-u-8/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell">
                        <div class="control-group">
                        	<label for="area" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.convocatoria.especialidad"/></span></label>
                        	<span class="pae-form__text-readonly"><bean:write name="solicitud" property="especialidad" scope="request" /></span>
                        </div>
                      </div>
                    </div>
                    <div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell">
                        <div class="control-group">
                          <label for="provinciaExamen" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.convocatoria.provinciaExamen"/></span></label>
                          <span class="pae-form__text-readonly"><bean:write name="solicitud" property="provinciaExamen" scope="request" /></span>
                        </div>
                      </div>
                    </div>
                  </div>
                    <div class="pae-layout">
                      <div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
                        <div class="pae-form__cell"><span class="pae-form__label pae-form__span-label"><spring:message code="field.formulario790.convocatoria.cuerpoEscalaAux"/>
                        	</span><span class="pae-form__text-readonly pae-form__text-readonly--small"><bean:write name="solicitud" property="cuerpo" scope="request"/></span>
                      </div>
                      </div>
                      <div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
                        <div class="pae-form__cell"><span class="pae-form__label pae-form__span-label"><spring:message code="field.formulario790.convocatoria.formaAcceso"/>
                        	</span><span class="pae-form__text-readonly pae-form__text-readonly--small"><bean:write name="solicitud" property="formaAcceso" scope="request"/></span>
                        </div>
                      </div>
                      <div class="pae-layout__item pae-u-4/12 pae-u-3/12-lap pae-u-1/1-palm">
                        <div class="pae-form__cell"><span class="pae-form__label pae-form__span-label"><spring:message code="field.formulario790.convocatoria.ministerioAux" scope="request"/></span>
                        	<span class="pae-form__text-readonly pae-form__text-readonly--small">
                        	<bean:write name="solicitud" property="ministerio" scope="request"/></span>
                        </div>
                      </div>
                      <div class="pae-layout__item pae-u-2/12 pae-u-3/12-lap pae-u-1/1-palm">
                        <div class="pae-form__cell"><span class="pae-form__label pae-form__span-label"><spring:message code="field.formulario790.convocatoria.fechaBOE"/>
                        	</span><span class="pae-form__text-readonly pae-form__text-readonly--small"><bean:write name="solicitud" property="fechaBOE" scope="request"/></span>
                      </div>
                    </div>
                  </div>
                </fieldset>
            </div>
            </div>