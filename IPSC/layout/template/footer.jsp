<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

  <div class="pae-footer" id ="pie">
  	<script type="text/javascript">
				var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
				document.write(unescape("%3Cscript src='" + gaJsHost +"google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
	</script>
	<script type="text/javascript">
			try {
				var pageTracker = _gat._getTracker("UA-4632269-6");
				pageTracker._trackPageview();
			} catch(err) {}
			
	</script>
    <div class="pae-wrapper">
      <div class="pae-layout">
        <div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
          <figure class="pae-footer__images pae-footer__images--left"><a href="http://administracion.gob.es" target="_blank" title="Punto de Acceso General (administracion.gob.es)"><img src="<%=request.getContextPath()%>/img/content/administracion-gob.png" alt="administración.gob.es" class="pae-footer__images-img"/></a></figure>
        </div>
        <div class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm">
          <nav class="pae-footer__nav">
            <ul class="pae-footer__nav__list-menu">
              <li class="pae-footer__nav__item-list"><a href="http://administracion.gob.es/pag_Home/avisoLegal.html" title="Aviso Legal" class="pae-footer__nav__link-item outline" target="_new"><spring:message code="field.pie.avisoLegal" /></a></li>
              <li class="pae-footer__nav__item-list"><a href="http://administracion.gob.es/pag_Home/politicaDePrivacidad.html" title="Política de Privacidad" class="pae-footer__nav__link-item outline" target="_new"><spring:message code="field.pie.politicaPrivacidad" /></a></li>
              <li class="pae-footer__nav__item-list"><a href="http://administracion.gob.es/pag_Home/accesibilidad.html" title="Accesibilidad" class="pae-footer__nav__link-item outline" target="_new"><spring:message code="field.pie.accesibilidad" /></a></li>
            </ul>
          </nav>
        </div>
        <div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
          <figure class="pae-footer__images pae-footer__images--right"><img src="<%=request.getContextPath()%>/img/content/ue.png" alt="UNIÓN EUROPEA" class="pae-footer__images-img"/></figure>
        </div>
      </div>
    </div>
  </div>

