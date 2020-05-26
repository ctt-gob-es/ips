     	<c:choose>
		    <c:when test="${formulario790Form.subsanar}">
		    	<h1 class="pae-title"><spring:message code="field.solicitud.subsanacionPresencial"/></h1>
		    </c:when>    
		    <c:otherwise>
<h1 class="pae-title"><spring:message code="field.solicitud.inscripcionPresencial"/></h1>
		    </c:otherwise>
		</c:choose> 
          <div id="rellenarCampos" class="pae-alertbox pae-alertbox--warning pae-hidden">
              <p class="pae-alertbox__text"><spring:message code="field.solicitud.errorCampos" /></p>
          </div>
          <div class="pae-box-details">
	     	<c:choose>
			    <c:when test="${formulario790Form.subsanar}">
			    	<h2 class="pae-box-details__title"><spring:message code="field.incidencias.realizandoSubsanacion"/></h2>
			    </c:when>    
			    <c:otherwise>
            <h2 class="pae-box-details__title"><spring:message code="field.solicitud.realizandoInscripcion"/></h2>
			    </c:otherwise>
			</c:choose>          
            <div class="pae-box-list">
              <ul class="pae-list-details">
                <li class="pae-list-details__item-head"><span class="pae-list-details__item-text pae-list-details__item-text--highlighted"><spring:message code="field.cuerpoMin"/></span></li>
                <li class="pae-list-details__item-body--last"><span class="pae-list-details__item-text"><bean:write name="convocatoria" property="desCuerpoEscala"/></span></li>
              </ul>
              <ul class="pae-list-details">
                <li class="pae-list-details__item-head"><span class="pae-list-details__item-text pae-list-details__item-text--highlighted"><spring:message code="field.centroMin"/></span></li>
                <li class="pae-list-details__item-body"><span class="pae-list-details__item-text"><bean:write name="convocatoria" property="desMinisterio"/></span></li>
                <li class="pae-list-details__item-body--last"><span class="pae-list-details__item-text"><bean:write name="convocatoria" property="desCentroGestor"/></span></li>
              </ul>
              <ul class="pae-list-details">
                <li class="pae-list-details__item-head"><span class="pae-list-details__item-text pae-list-details__item-text--highlighted"><spring:message code="field.nivelAcceso"/></span></li>
                <li class="pae-list-details__item-body"><span class="pae-list-details__item-text">${siglasGrupoConvocatoria}</span></li>
                <li class="pae-list-details__item-body--last"><span class="pae-list-details__item-text"><bean:write name="convocatoria" property="desFormaAcceso"/></span></li>
              </ul>
            </div>
          </div>
          <form:input path="anioConvocatoria" id="anioConvocatoria" class="pae-hidden"	maxlength="4" size="4" />