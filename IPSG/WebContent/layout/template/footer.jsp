<%-- <%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %> --%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<div class="pae-footer">
      <div class="pae-layout">
        <div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
			<figure class="pae-footer__images pae-footer__images--left">	
			<a href="http://administracion.gob.es" target="_blank" title="Punto de Acceso General (administracion.gob.es)"><img src="<%=request.getContextPath()%>/images/logo_admon.png" alt="Punto de Acceso General" class="pae-footer__images-img"/></a>
			</figure>
		</div>
				<div class="pae-layout__item pae-u-6/12 pae-u-6/12-lap pae-u-1/1-palm" align="center">
          		<nav class="pae-footer__nav">
				<ul class="pae-footer__nav__list-menu">				
			     <li class="pae-footer__nav__item-list"><a href="http://administracion.gob.es/pag_Home/avisoLegal.html"  class="pae-footer__nav__link-item outline" target="_new">
			     <spring:message code="field.pie.avisoLegal" />
			     </a> 
			     </li>
				 <li class="pae-footer__nav__item-list"><a href="http://administracion.gob.es/pag_Home/politicaDePrivacidad.html"  class="pae-footer__nav__link-item outline" target="_new">
				 <spring:message code="field.pie.politicaPrivacidad" />
				 </a> 
				 </li>
				 <li class="pae-footer__nav__item-list"><a href="http://administracion.gob.es/pag_Home/accesibilidad.html" class="pae-footer__nav__link-item outline" target="_new">
				 <spring:message code="field.pie.accesibilidad" />
				 </a> 
				 </li>
				 <li class="pae-footer__nav__item-list"> 
				 <img src="<%=request.getContextPath()%>/images/xml.PNG" alt="xml_inside" width="46" height="20"/></li>
				 
				 <li class="pae-footer__nav__item-list"> |
				 <img src="<%=request.getContextPath()%>/images/w3c.PNG" alt="w3c" width="57" height="20"/></li>
				</ul>
				</nav>
				</div>
				<div class="pae-layout__item pae-u-3/12 pae-u-3/12-lap pae-u-1/1-palm">
				<figure class="pae-footer__images pae-footer__images--right pae-head">
				<img src="<%=request.getContextPath()%>/img/content/ue.png" alt="Proyecto Financiado Union Europea" class="pae-footer__images-img"/></figure>
				</div>
</div>
</div>


