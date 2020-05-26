<div id="detalleBoxSolicitante" data-function="accordion-box" class="pae-box">
	   		<div class="pae-box__header">
            	<h3 class="pae-box__header--title"><spring:message code="field.DatosSolicitante"/></h3>
            </div>
            <div class="pae-box__body">
            	<fieldset>
                	<legend class="pae-form__legend-text"><spring:message code="field.DatosPersonales"/></legend>
                  	<div class="pae-layout">
                    <div class="pae-layout__item pae-u-2/12 pae-u-2/12-lap pae-u-1/1-palm">
                     	<div class="pae-form__cell">
                     		<span class="pae-form__label pae-form__span-label">
                     			<spring:message code="field.formulario790.nif"/>
                     		</span>
                     		<span id="nif" class="pae-form__text-readonly">
                     			<bean:write name="solicitud" property="nif" scope="request" />
                     		</span>
                     	</div>
                    </div>
                    <div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
                     	<div class="pae-form__cell">
                     		<span class="pae-form__label pae-form__span-label">
                     			<spring:message code="field.formulario790.primerApellido"/>
                     		</span>
                     		<span id="primerApellido" class="pae-form__text-readonly">
                     			<bean:write name="solicitud" property="primerApellido" scope="request" />
                     		</span>
                     	</div>
                    </div>
                    <div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
                      	<div class="pae-form__cell">
                      		<span class="pae-form__label pae-form__span-label">
                      			<spring:message code="field.formulario790.segundoApellido"/>
                      		</span>
                      		<span id="segundoApellido" class="pae-form__text-readonly">
                      			<bean:write name="solicitud" property="segundoApellido" scope="request" />
                      		</span>
                      	</div>
                    </div>
                    <div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
                      	<div class="pae-form__cell">
                      		<span class="pae-form__label pae-form__span-label">
                      			<spring:message code="field.formulario790.nombre"/>
                      		</span>
                      		<span id="nombre" class="pae-form__text-readonly">
                      			<bean:write name="solicitud" property="nombre" scope="request" />
                      		</span>
                      	</div>
                    </div>
                  		</div>
                  	<div class="pae-layout">
                    	<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-1/1-palm">
                      		<div class="pae-form__cell">
                        		<label for="fechaNacimiento" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.fechaNacimiento"/></span></label>
                          		<span id="fechaNacimiento" class="pae-form__text-readonly"><bean:write name="solicitud" property="fechaNacimiento" scope="request" /></span>
                      		</div>
                    	</div>
                    	<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
                     		<div class="pae-form__cell"><span class="pae-form__label pae-form__span-label"><spring:message code="field.formulario790.sexo"/></span>
                        		<span id="sexo" class="pae-form__text-readonly"><bean:write name="solicitud" property="sexo" scope="request" /></span>
                      		</div>
                    	</div>
                    	<div class="pae-layout__item pae-u-5/12 pae-u-5/12-lap pae-u-1/1-palm">
                      		<div class="pae-form__cell">
                        		<div class="control-group">
                          			<label for="nacionalidad" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.nacionalidad"/></span></label>
                          			<span class="pae-form__text-readonly"><bean:write name="solicitud" property="nacionalidad" scope="request" /></span>
                        		</div>
                      		</div>
                    	</div>
                  	</div>
                </fieldset>
                <fieldset>
                  	<legend class="pae-form__legend-text"><spring:message code="field.DatosDomicilio"/></legend>
                  	<div class="pae-layout">
                    	<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-1/1-palm">
                      		<div class="pae-form__cell">
                        		<label for="email" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.correosElectronicos"/></span></label>
                        		<span class="pae-form__text-readonly"><bean:write name="solicitud" property="correoElectronico" scope="request" /></span>
                      		</div>
                    	</div>
                    	<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
                      		<div class="pae-form__cell">
                        		<label for="telefono1" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.telefonoAux"/></span></label>
                        		<span class="pae-form__text-readonly"><bean:write name="solicitud" property="telefono1" scope="request" /></span>
                      		</div>
                    	</div>
                    	<div class="pae-layout__item pae-u-5/12 pae-u-5/12-lap pae-u-1/1-palm">
                      		<div class="pae-form__cell">
                        		<label for="telefono2" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.telefonoAux2"/></span></label>
								<span class="pae-form__text-readonly"><bean:write name="solicitud" property="telefonoAux" scope="request" /></span></span>
                    		</div>
                  		</div>
                  		</div>
                  		<div class="pae-layout">
                    	<div class="pae-layout__item pae-u-7/12 pae-u-7/12-lap pae-u-1/1-palm">
                      		<div class="pae-form__cell">
                        		<label for="calleDomicilio" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.domicilioAux"/></span></label>
                        		<span class="pae-form__text-readonly"><bean:write name="solicitud" property="calleDomicilio" scope="request" /></span>
                      		</div>
                    	</div>
                    	<div class="pae-layout__item pae-u-5/12 pae-u-5/12-lap pae-u-1/3-palm">
                      		<div class="pae-form__cell">
                        		<label for="codigoPostalDomicilio" class="pae-form__label"><span class="pae-form__label--text pae-form__label--mobile"><spring:message code="field.solicitud.CP" /></span>
                        			<span class="pae-form__label--text pae-form__label--desktop"><spring:message code="field.formulario790.codigoPostal"/></span></label>
                        		<span class="pae-form__text-readonly"><bean:write name="solicitud" property="codPostalDomicilio" scope="request" /></span>
                      		</div>
                    	</div>
                    	<div class="pae-layout__item pae-u-4/12 pae-u-4/12-lap pae-u-1/1-palm">
                      		<div class="pae-form__cell">
                        		<label for="municipioDomicilio" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.domicilioMunicipioAux"/></span></label>
                        		<span class="pae-form__text-readonly"><bean:write name="solicitud" property="municipioDomicilio" scope="request" /></span>
                      		</div>
                    	</div>
                    	<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
                      		<div class="pae-form__cell">
                        		<div class="control-group">
                          			<label for="provinciaDomicilio" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.domicilioProvinciaAux"/></span></label>
                          			<span class="pae-form__text-readonly"><bean:write name="solicitud" property="provinciaDomicilio" scope="request" /></span>
                        		</div>
                      		</div>
                    	</div>
                    	<div class="pae-layout__item pae-u-5/12 pae-u-5/12-lap pae-u-1/1-palm">
                     		<div class="pae-form__cell">
                        		<div class="control-group">
                          			<label for="pais" class="pae-form__label"><span class="pae-form__label--text"><spring:message code="field.formulario790.domicilioPaisAux"/></span></label>
                          			<span class="pae-form__text-readonly"><bean:write name="solicitud" property="nacionDomicilio" scope="request" /></span>
                        		</div>
                      		</div>
                    	</div>
                  	</div>
                </fieldset>
              </div>
            </div>