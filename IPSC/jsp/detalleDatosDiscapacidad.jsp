<div data-function="accordion-box" class="pae-box" id="detalleBoxDiscapacidad">
              <div class="pae-box__header">
                <h3 class="pae-box__header--title"><spring:message code="field.convocatoria.preguntaDiscapacidad"/><span class="pae-box__span-text hidden-palm"><spring:message code="field.convocatoria.discapacidadOpcional"/></span></h3>
              </div>
              <div class="pae-box__body">
                <fieldset>
                <!-- Cambiar por Datos discapacidad -->
                  <legend class="pae-form__legend-text hiddenAccesible"><spring:message code="field.convocatoria.preguntaDiscapacidad"/></legend>
                  <div class="pae-layout">
                    <div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/3-palm">
                      <div class="pae-form__cell">
                        <label for="porcentajeDiscapacidad" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.convocatoria.gradoDiscapacidadAux" />%</span></label>
                        <span class="pae-form__text-readonly"><bean:write name="solicitud" property="adaptacionDiscapacidad" scope="request" /></span>
                      </div>
                    </div>
                  </div>
                  <div class="pae-layout">
                    <div class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell"><span class="pae-form__label pae-form__span-label"><spring:message code="field.formulario790.convocatoria.reservaDiscapacidadAux"/></span>
                        <span class="pae-form__text-readonly"><bean:write name="solicitud" property="reservaDiscapacitados" scope="request" /></span>
                      </div>
                    </div>
                    <div class="pae-layout__item pae-u-6/12 pae-u-8/12-lap pae-u-1/1-palm">
                      <div class="pae-form__cell">
                        <label for="detalleDiscapacidad" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.convocatoria.motivoDiscapacidadAux" /></span></label>
                        <span class="pae-form__text-readonly" id="detalleDiscapacidad"><bean:write name="solicitud" property="motivoDiscapacidad" scope="request" /></span>
                      </div>
                    </div>
                  </div>
                </fieldset>
              </div>
            </div>