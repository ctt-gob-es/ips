<link rel="shortcut icon" type="image/ico" href="../../images/ipsFavicon.ico" type="image/x-icon"/>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean" %>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html" %>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.*" %>
<%@ page import="es.map.ipsc.bean.ProvinciaBean" %>
<%@ page import="es.map.ipsc.bean.ProvinciaExamenBean" %>
<%@ page import="es.map.ipsc.bean.PaisBean" %>
<%@ page import="es.map.ipsc.bean.CuerpoEscalaBean" %>
<%@ page import="es.map.ipsc.bean.EspecialidadBean" %>
<%@ page import="es.map.ipsc.bean.MinisterioBean" %>
<%@ page import="es.map.ipsc.bean.TituloOficialBean" %>
<%@ page import="es.map.ipsc.bean.ConvocatoriaBean" %>
<%@ page import="es.map.ips.model.Especialidad" %>
<%@ page import="es.map.ips.model.Provincia" %>
<%@ page import="es.map.ips.model.ProvinciaExamen" %>
<%@ page import="es.map.ips.model.TituloOficial" %>
<%@ page import="es.map.ipsc.Constantes" %>
<%@ page import="es.map.ipsc.bean.*" %>
<%@ page import="es.map.ipsc.action.*" %>



<script type="text/javascript">

//Funciones que validan los datos que se introducen por el ciudadano mientras 
//esta cumplimentando el formulario



function buscaCodigoProvincia(){

	var idProvincia = document.getElementById("provinciaDomicilio").value;
	<% 
		Boolean precargar = (Boolean)request.getAttribute("codProvinciaDomicilio");
		List objProvincias = (List)request.getAttribute("provincias");
		Integer elemAux =new Integer(0);
		String codAux ="";
		Object objAux= null;
		ProvinciaBean prov;
		if( objProvincias != null)
		{
			for(int i=0;i<objProvincias.size();i++)
			{
				objAux= objProvincias.get(i);
				prov = (ProvinciaBean)objAux;
				elemAux= (Integer)prov.getId();
				codAux=  (String)prov.getCodigo();
	%>
	if("<%=elemAux%>" == idProvincia)
	{
		if("<%=precargar%>" == "true")
		{	
			if("<%=codAux%>" != "null")
			{	
				document.getElementById("codigoProvinciaDomicilio").value = "<%=codAux%>" ;		
			}
			else
			{
				document.getElementById("codigoProvinciaDomicilio").value = "" ;
			}		

		}	
	}		
	
		
	<%}}%>	
			
}

function buscaCodigoPais(){

	var idPais = document.getElementById("pais").value;
	<% 
		Boolean precargarPaisDomicilio = (Boolean)request.getAttribute("codPaisDomicilio");
		List listPaisDomicilio = (List)request.getAttribute("paises");
		Integer paisAux =new Integer(0);
		String codPaisAux ="";
		PaisBean pais = new PaisBean();
		if( listPaisDomicilio != null)
		{
			for(int j=0;j<listPaisDomicilio.size();j++)
			{
				pais = (PaisBean)listPaisDomicilio.get(j);
				paisAux= (Integer)pais.getId();
				codPaisAux=  (String)pais.getCodigo();
	%>
	if("<%=paisAux%>" == idPais)
	{
		<%
		if(precargarPaisDomicilio)
		{	
			if(codPaisAux != null)
			{
				//if(codPaisAux.length()>2){
		%>
					<%--document.getElementById("codigoPaisDomicilio").value = "<%=codPaisAux.substring(0,3)%>" ;--%>
		<%
				//}else{*/
		%>
					document.getElementById("codigoPaisDomicilio").value = "<%=codPaisAux%>" ;
		<%
				//}	
			}	
			else
			{
		
		%>
				document.getElementById("codigoPaisDomicilio").value = "" ;
		<%
			}		
		}	
		%>
	}		
	
		
	<%}}%>	
			
}

function buscaCodigoCuerpoEscala(){

	var idCuerpoEscala = document.getElementById("cuerpoEscala").value;
	<% 
		Boolean precargarCuerpoEscala = (Boolean)request.getAttribute("codCuerpoEscala");
		List listCuerposEscala = (List)request.getAttribute("cuerposEscala");
		Long ceAux =new Long(0L);
		String codCeAux ="";
		CuerpoEscalaBean ccuerpoEscala = new CuerpoEscalaBean();
		if( listCuerposEscala != null)
		{
			for(int j=0;j<listCuerposEscala.size();j++)
			{
				ccuerpoEscala = (CuerpoEscalaBean)listCuerposEscala.get(j);
				ceAux= (Long)ccuerpoEscala.getId();
				codCeAux=  (String)ccuerpoEscala.getCodigo();
	%>
	
	if("<%=ceAux%>" == idCuerpoEscala)
	{
		if("<%=precargarCuerpoEscala%>" == "true")
		{	
			if("<%=codCeAux%>" != "null")
			{	
				document.getElementById("codigoCuerpoEscala").value = "<%=codCeAux%>" ;	
			}
			else
			{
				document.getElementById("codigoCuerpoEscala").value = "" ;
			}			
		}	
	}		
	<%}}%>				
}

function buscaCodigoEspecialidad(){
	var idEspecialidad = document.getElementById("especialidad").value;
	<% 
		Boolean precargarEspecialidad = (Boolean)request.getAttribute("codEspecialidad");
		List listEspecialidades = (List)request.getAttribute("especialidades");
		Integer especialidadAux =new Integer(0);
		String codEspecialidadAux ="";
		EspecialidadBean especialidadBean = new EspecialidadBean();
		if( listEspecialidades != null)
		{
			for(int j=0;j<listEspecialidades.size();j++)
			{
				especialidadBean = (EspecialidadBean)listEspecialidades.get(j);
				especialidadAux= (Integer)especialidadBean.getId();
				codEspecialidadAux=  (String)especialidadBean.getCodigo();
	%>
	if("<%=especialidadAux%>" == idEspecialidad)
	{
		<%
		if(precargarEspecialidad)
		{	
			if(codEspecialidadAux != null)
			{
		%>
				document.getElementById("codigoEspecialidad").value = "<%= (codEspecialidadAux!=null && codEspecialidadAux.length() > 4) ? codEspecialidadAux.substring(0,3) : codEspecialidadAux%>" ;
				alert("valor code "+codEspecialidadAux);
		<%
			}
			else
			{
		%>
				document.getElementById("codigoEspecialidad").value = "" ;
		<%
			}			
		}
		%>
	}		
	
		
	<%}}%>	
			
}


function buscaCodigoEspecialidadConvocatoria(){
	var idEspecialidadC = document.getElementById("especialidad").value;
	<% 
		Boolean precargarEspecialidadC = (Boolean)request.getAttribute("codEspecialidad");
		ConvocatoriaBean conv = (ConvocatoriaBean)request.getAttribute("convocatoria");
		if(conv != null)
		{	
			List listEspecialidadesC = (List)conv.getEspecialidads();
			Integer especialidadAuxC =new Integer(0);
			String codEspecialidadAuxC ="";
			Especialidad especialidadBeanC = new Especialidad();
			if( listEspecialidadesC != null)
			{
				for(int j=0;j<listEspecialidadesC.size();j++)
				{
					especialidadBeanC = (Especialidad)listEspecialidadesC.get(j);
					especialidadAuxC= (Integer)especialidadBeanC.getId();
					codEspecialidadAuxC=  (String)especialidadBeanC.getCodigo();
	%>
	if("<%=especialidadAuxC%>" == idEspecialidadC)
	{
		<%
		if(precargarEspecialidadC)
		{	
			if(codEspecialidadAuxC != null)
			{
				if(codEspecialidadAuxC.length() > 4){
		%>
					document.getElementById("codigoEspecialidad").value = "<%=codEspecialidadAuxC.substring(0,3)%>" ;
		<%
				}else{
		%>
					document.getElementById("codigoEspecialidad").value = "<%=codEspecialidadAuxC%>" ;
		<%
				}
			}
			else
			{
		%>
				document.getElementById("codigoEspecialidad").value = "" ;
		<%
			}			
		}
		%>
	}		
	
		
	<%}}}%>	
			
}
function buscaCodigoMinisterio(){

	var idMinisterio = document.getElementById("ministerio").value;
	<% 
		Boolean precargarMinisterio = (Boolean)request.getAttribute("codMinisterio");
		List listMinisterio = (List)request.getAttribute("ministerios");
		Integer ministerioAux =new Integer(0);
		String codMinisterioAux ="";
		MinisterioBean ministerio = new MinisterioBean();
		if( listMinisterio != null)
		{
			for(int j=0;j<listMinisterio.size();j++)
			{
				ministerio = (MinisterioBean)listMinisterio.get(j);
				ministerioAux= (Integer)ministerio.getId();
				codMinisterioAux=  (String)ministerio.getCodigo();
	%>
	if("<%=ministerioAux%>" == idMinisterio)
	{
		if("<%=precargarMinisterio%>" == "true")
		{	
			if("<%=codMinisterioAux%>" != "null")
			{
				document.getElementById("codigoMinisterio").value = "<%=codMinisterioAux%>" ;		
			}
			else
			{
				document.getElementById("codigoMinisterio").value = "" ;
			}		
		}	
	}		
	
		
	<%}}%>	
			
}

function buscaProvinciaExamen(){

	var idProvExamen = document.getElementById("provinciaExamen").value;
	<% 
		Boolean precargarProvinciasExamen = (Boolean)request.getAttribute("codProvinciaExamen");
		List listProvinciasExamen = (List)request.getAttribute("provinciasExamen");
		Integer provinciaExamenAux =new Integer(0);
		String codProvinciaExamenAux ="";
		ProvinciaExamenBean provinciaExamenBean = new ProvinciaExamenBean();
		if( listProvinciasExamen != null)
		{
			for(int j=0;j<listProvinciasExamen.size();j++)
			{
				provinciaExamenBean = (ProvinciaExamenBean)listProvinciasExamen.get(j);
				provinciaExamenAux= (Integer)provinciaExamenBean.getId();
				codProvinciaExamenAux=  (String)provinciaExamenBean.getCodigo();
	%>
	if("<%=provinciaExamenAux%>" == idProvExamen)
	{
		
		if("<%=precargarProvinciasExamen%>" == "true")
		{	
			if("<%=codProvinciaExamenAux%>" != "null")
			{
				document.getElementById("codigoProvinciaExamen").value = "<%=codProvinciaExamenAux%>" ;	
			}
			else
			{
				document.getElementById("codigoProvinciaExamen").value = "" ;	
			}			
		}	
	}		
	
		
	<%}}%>	
			
}

function buscaProvinciaExamenConvocatoria(){

	var idProvExamenC = document.getElementById("provinciaExamen").value;
	<% 
		Boolean precargarProvinciasExamenC = (Boolean)request.getAttribute("codProvinciaExamen");
		ConvocatoriaBean convExamen = (ConvocatoriaBean)request.getAttribute("convocatoria");
		if(convExamen != null)
		{	
			List listProvinciasExamenC = (List)convExamen.getProvinciasExamen();
			Integer provinciaExamenAuxC =new Integer(0);
			String codProvinciaExamenAuxC ="";
			ProvinciaExamen provinciaExamenBeanC = new ProvinciaExamen();
			if( listProvinciasExamenC != null)
			{
				for(int j=0;j<listProvinciasExamenC.size();j++)
				{
					provinciaExamenBeanC = (ProvinciaExamen)listProvinciasExamenC.get(j);
					provinciaExamenAuxC= (Integer)provinciaExamenBeanC.getId();
					codProvinciaExamenAuxC=  (String)provinciaExamenBeanC.getCodigo();
	%>
	if("<%=provinciaExamenAuxC%>" == idProvExamenC)
	{
		
		if("<%=precargarProvinciasExamenC%>" == "true")
		{	
			if("<%=codProvinciaExamenAuxC%>" != "null")
			{
				document.getElementById("codigoProvinciaExamen").value = "<%=codProvinciaExamenAuxC%>" ;	
			}
			else
			{
				document.getElementById("codigoProvinciaExamen").value = "" ;	
			}			
		}	
	}				
	<%}}}%>	
			
}

function buscaTituloOficial(){

	var idTitulo = document.getElementById("tituloOficial").value;
	<% 
		Boolean precargarTitulo = (Boolean)request.getAttribute("codTituloOficial");
		List listTitulos = (List)request.getAttribute("titulosOficiales");
		Integer tituloAux =new Integer(0);
		String codTituloAux ="";
		TituloOficialBean tituloBean = new TituloOficialBean();
		if( listTitulos != null)
		{
			for(int j=0;j<listTitulos.size();j++)
			{
				tituloBean = (TituloOficialBean)listTitulos.get(j);
				tituloAux= (Integer)tituloBean.getId();
				codTituloAux=  (String)tituloBean.getCodigo();
	%>
	if("<%=tituloAux%>" == idTitulo)
	{
	
		if("<%=precargarTitulo%>" == "true")
		{	
			if("<%=codTituloAux%>" != "null")
			{	
				document.getElementById("codigoTituloExigido").value = "<%=codTituloAux%>" ;	
			}	
			else
			{
				document.getElementById("codigoTituloExigido").value = "" ;
			}		
		}	
	}		
	
		
	<%}}%>	
			
}

function buscaTituloOficialConvocatoria(){
	var idTituloC = document.getElementById("tituloOficial").value;
	<% 
		Boolean precargarTituloC = (Boolean)request.getAttribute("codTituloOficial");
		ConvocatoriaBean convTitulo = (ConvocatoriaBean)request.getAttribute("convocatoria");	
		if(convTitulo != null)
		{	
			List listTitulosC = (List)convTitulo.getTituloOficials();
			Integer tituloAuxC =new Integer(0);
			String codTituloAuxC ="";
			TituloOficial tituloBeanC = new TituloOficial();
			if( listTitulosC != null)
			{
				for(int j=0;j<listTitulosC.size();j++)
				{
					tituloBeanC = (TituloOficial)listTitulosC.get(j);
					tituloAuxC= (Integer)tituloBeanC.getId();
					codTituloAuxC=  (String)tituloBeanC.getCodigo();
	%>
	if("<%=tituloAuxC%>" == idTituloC)
	{
		if("<%=precargarTituloC%>" == "true")
		{	
			if("<%=codTituloAuxC%>" != "null")
			{	
				document.getElementById("codigoTituloExigido").value = "<%=codTituloAuxC%>" ;	
			}	
			else
			{
				document.getElementById("codigoTituloExigido").value = "" ;
			}		
		}	
	}		
	
		
	<%}}}%>	
			
}

function ventanaInformacion(){
	op= "<%=Constantes.NOCOMUNIDADES%>";
	ventanaPopup = window.open("<%=request.getContextPath()%>/secure/informacionAdicional?llamada="+op, 'miventana', 'width=650, height=280,toolbar=no,directories=no,status=no,linemenubar=no,scrollbars=no,resizable=yes ,modal=yes');
	return false;
}

</script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Compatibilidad con Windows 8 -->
<meta http-equiv="X-UA-Compatible" content="requiresActiveX=true"/>
<title><spring:message code="field.formulario790.titulo"/></title>
<link rel="stylesheet"
	href='<%=request.getContextPath()%>/css/estilo_060.css' type="text/css">
</head>
<body>
<form:form modelAttribute="formulario790Form" action="/IPSC/secure/generarFormulario790" onsubmit="return alEnviar()" method="post">
	
	<form:hidden path="idCuerpoEscala" id="idCuerpoEscala"/>
	<form:hidden path="idMinisterio" id="idMinisterio"/>
	<form:hidden path="idFormaAcceso" id="idFormaAcceso"/>
	<form:hidden path="idCentroGestor" id="idCentroGestor"/>
	<form:hidden path="idConvocatoria" id="idConvocatoria"/>
	<form:hidden path="codCentroGestor" id="codCentroGestor"/>
	<form:hidden path="porcentajeMinDiscapacidad" id="porcentajeMinDiscapacidad"/>
	<input type="hidden" id="motivosTipificado" value="${motivoTipificado}">
	<input type="hidden" id="borrarMotivo" value="">
	<input type="hidden" id="comunidadesReqTitulo" value="${comunidadesReqTitulo}">
	<input type="hidden" id="importe" value="${importe}">
	<logic:present name="convocatoria">			                  		
		<input type="hidden" id="esConvocatoria">
	</logic:present>	
	<table border="1" width="100%;">
    	<tr>
			<td>
				<table border="0" style="width:100%;">
					<tr>
					    <td colspan="2" style="text-align:center" width="254">
				            <img height="54" width="142" border="0" src="<%=request.getContextPath()%>/images/logo_izq.PNG"/>
			         	</td>
			         	<td style="text-align:center;font-family:arial;font-weight:bold;">
			         		<logic:notEqual name="formulario790Form" property="codigoTasa" value="007">
				            	<span style="font-size:12pt">
				            		<spring:message code="field.formulario790.subTitulo"/>
				            	</span>
				            </logic:notEqual>
			            	<logic:equal name="formulario790Form" property="codigoTasa" value="007">
				            	<span style="font-size:12pt">
				            		<spring:message code="field.formulario790007.subTitulo"/>
				            	</span>
				            </logic:equal>
			         	</td>
			         	<td style="text-align:center;font-family:c;font-weight:bold;border:thin solid grey;">
			            	<div style="font-size:12pt">
			            		<spring:message code="field.formulario790.modelo"/>
			            	</div><br/>
			            	<div style="font-size:20pt">
			            		<spring:message code="field.formulario790.790"/>
			            	</div><br/>
			         	</td>
			      	</tr>
			      	<tr>
			      		<td/>
			      		<td/>
			      		<!-- <td  style="text-align:center;font-family:arial;font-weight:bold;color:#854316">
			      		 	<SPAN style="font-size:10pt">
			      		 		<spring:message code="field.formulario790.nota"/>
			    	        </SPAN>
			      		</td> -->
			      		<td/>
			      	</tr>
			      	<tr>
			        	<td style="border:1px solid grey;" colspan="4">
			          			<table width="100%">
			                		<tr>
			                    		<td class="text_etiqueta_normal" colspan="2">
			                    			<spring:message code="field.formulario790.ministerioMay"/>
			                     		</td>
			                  		</tr>
			                  		<tr>
			                     		<td style="font-size:10px;"/>
			                     		<td style="font-size:10px;"/>
			                  		</tr>
			                  		<tr>
			                     		<td style="width:100%;display: block" id="ministerioCombo" >
			                     				<logic:present name="ministerios" scope="request">
													<bean:size id="numMinisterios" name="ministerios" scope="request"/>
													<logic:greaterThan name="numMinisterios" value="0">
														<form:select path="ministerio" id="ministario" style="width:100%;" onchange="cambiarMayusculas(this);comprobarMinisterio();buscaCodigoMinisterio();">
															<option value="0">         </option>
															<form:option value="-1">OTRO (Introduzca un valor)</form:option>
															<form:options items="${ministerios}" path="id" labelProperty="descripcion" />
														</form:select>
													</logic:greaterThan>
												</logic:present>
											<logic:present name="convocatoria" scope="request">
												<form:input path="desMinisterio" name="convocatoria" class="lineablanca" style="width:100&" readonly="true" maxlength="80"/></br>
											</logic:present>
										</td>
										<td style="width:100%;display: none" id="ministerioManualBlock">
			                     			<logic:notPresent name="convocatoria" scope="request">
				                     		<form:input path="ministerioManual" id="ministerioManual" size="200" maxlength="80"
			                        					style="font-size:14px;text-align:left;" onkeyup="igualarMinisterios(this);" onchange="cambiarMayusculasText(this); cambiarMayusculasText(document.getElementById('ministerioAutomatico'));"/>
			                        		</logic:notPresent>		                     						         
			                        	</td>
			                  		</tr>
			               		</table>
			            	</td>
			      	</tr>
			   	</table>
			   
			  	<table style="width:100%;">
			      	<tr>
			         	<td style="text-align:center;border:2px solid grey;width:45%;">
			            	<div class="text_etiqueta_normal">
			              		<spring:message code="field.formulario790.centroGestorMay"/><br/><br/>
			            	</div>
			            	<div style="text-align:left;vertical-align:middle;height:3em;" id="centroGestorCombo"> 
			               		<logic:present name="centrosGestor" scope="request">
									<bean:size id="numCentros" name="centrosGestor" scope="request"/>
										<logic:greaterThan name="numCentros" value="0">
											<form:select path="centroGestor" id="centroGestor" style="width:100%;" class="lineablanca" onchange="cambiarMayusculas(this);comprobarCentroGestor();">
												<option value="0">         </option>
												<form:option value="-1"><spring:message code="field.formulario790.otroIntroduzcaValor"/></form:option>
												<form:options items="${centrosGestor}" itemValue="id" itemLabel="descripcion"/>
											</form:select>
										</logic:greaterThan>
								</logic:present>
								<logic:present name="convocatoria" scope="request">
									<form:input path="desCentroGestor" value="${convocatoria.desCentroGestor}" class="lineablanca" style="width:90%;text-align:left;" maxlength="100" onchange="cambiarMayusculas(this)" readonly="true"/>
								</logic:present>								
			            	</div>
			            	
			                        	
				            	<div style="text-align:left;width:100%;display: none" id="centroGestorManualBlock">
					            	<logic:notPresent name="convocatoria" scope="request">
						                  <form:input path="centroGestorManual" id="centroGestorManual" style="width:98%;text-align:left;font-size:14px;" maxlength="80" onchange="cambiarMayusculasText(this);"/>
					                </logic:notPresent>
				                </div>
				              
			         	</td>
			         	<td style="width:15%;border:2px solid grey;padding:0px 0px 0px 0px;">
			            	<table style="width:100%;margin:0px 0px 0px 0px;">
			               		<tr>
			                  		<td class="text_etiqueta_tasa">
			                     		<spring:message code="field.formulario790.tasaMay"/>
			                  		</td>
			               		</tr>
			               		<tr>
			                  		<td class="text_etiqueta_tasa"><br/>
			                     		<spring:message code="field.formulario790.codigoMay"/>
			                     		<span style="text-align:center;">
			                        		<form:input path="codigoTasa" readonly="true" style="font-size:10pt;" size="2" maxlength="3" class="filaclaracentro"/></br>  
			                     		</span><br/><br/>
			                  		</td>
			               		</tr>
			            	</table>
			         	</td>
			         	<td style="width:20%;border:2px solid grey;padding:0px 0px 0px 0px;">
			            	<table style="width:100%;margin:0px 0px 0px 0px;">
			               		<tr>
			                  		<td style="text-align:center;border-bottom:1px solid grey;">
			                     		<span class="text_etiqueta_normal_centrada">
			                     			<spring:message code="field.formulario790.numJustificanteMay"/>
			                     		</span>
			                     		<span style="text-align:center;">
			                        		<form:input path="numeroJustificante" readonly="true" size="16" 
			                        					style="background-color:#D0D0D0;" />
			                     		</span>
			                  		</td>
			               		</tr>
			               		<tr>
			                  		<td class="text_etiqueta_normal_centrada">
			                     		<spring:message code="field.formulario790.añoConvocatoria"/>
			                     		<form:input path="anioConvocatoria" id="anioConvocatoria"
			                     					style="font-size:11px;text-align:center;" class="textonegrita"
			                     					maxlength="4" size="4" onkeypress="return soloNumeros(event)"/>
			                  		</td>
			               		</tr>
			            	</table>
			         	</td>
			      	</tr>
			   	</table>
			   
			   	<table style="font-size:10px;font-family:Arial;" border="0" width="100%">
			    	<colgroup width="10%" span="10"/>
			        	<tr>
			            	<td width="*" height="0px" display="none"/>
			            	<td width="*" height="0px" display="none"/>
			            	<td width="*" height="0px" display="none"/>
			            	<td width="*" height="0px" display="none"/>
			            	<td width="*" height="0px" display="none"/>
			            	<td width="*" height="0px" display="none"/>
			            	<td width="*" height="0px" display="none"/>
			            	<td width="*" height="0px" display="none"/>
			            	<td width="*" height="0px" display="none"/>
			            	<td width="*" height="0px" display="none"/>
			         	</tr>
			         	<tr>
			            	<td colspan="10" class="text_etiqueta_titulo"><FONT color="#ffffff"><spring:message code="field.formulario790.datosPersonalesMay"/></FONT></td>
			         	</tr>
			         	<tr>
			            	<td colspan="1" class="text_etiqueta_pequenia">
			            		<spring:message code="field.formulario790.nifMay"/><logic:equal name="plantilla" property="nif" value="S">
			            		<input type="hidden" id="obl_inputNif" value="true"><span class="obligatorio">*</span></logic:equal>
			            		<logic:notEqual name="plantilla" property="nif" value="S"><span class="obligatorio" style="visibility:hidden;">*</span></logic:notEqual>
			               		<br/>
			               		<form:input path="nif" style="width:100%;" maxlength="9" id="nif" 
			               					class="lineablanca" onchange="javascript: return cambiarMayusculasText(this)" onkeyup=""/>
			            	</td>
			            	<td colspan="3" class="text_etiqueta_pequenia">
			            		<spring:message code="field.formulario790.primerApellido"/><logic:equal name="plantilla" property="primerApellido" value="S">
			            		<input type="hidden" id="obl_inputPrimerApellido" value="true"><span class="obligatorio">*</span></logic:equal>
			            		<logic:notEqual name="plantilla" property="primerApellido" value="S"><span class="obligatorio" style="visibility:hidden;">*</span></logic:notEqual>			            		
			               		<br/>
			            		<form:input path="primerApellido" style="width:100%;" maxlength="44" id="primerApellido" onkeypress="return soloLetras(event)" class="lineablanca" onchange="javascript: return cambiarMayusculasText(this)"/>
			            	</td>
			            	<td colspan="3" class="text_etiqueta_pequenia">
			            		<spring:message code="field.formulario790.segundoApellido"/><logic:equal name="plantilla" property="segundoApellido" value="S">
			            		<input type="hidden" id="obl_inputSegundoApellido" value="true"><span class="obligatorio">*</span></logic:equal>
			            		<logic:notEqual name="plantilla" property="segundoApellido" value="S"><span class="obligatorio" style="visibility:hidden;">*</span></logic:notEqual>
			               		<br/>
			               		<form:input path="segundoApellido" style="width:100%;" maxlength="46" id="segundoApellido" onkeypress="return soloLetras(event)" class="lineablanca" onchange="javascript: return cambiarMayusculasText(this)"/>
			            	</td>
			            	<td colspan="3" class="text_etiqueta_pequenia">
			            		<spring:message code="field.formulario790.nombre"/><logic:equal name="plantilla" property="nombre" value="S">
			            		<input type="hidden" id="obl_inputNombre" value="true"><span class="obligatorio">*</span></logic:equal>
			            		<logic:notEqual name="plantilla" property="nombre" value="S"><span class="obligatorio" style="visibility:hidden;">*</span></logic:notEqual>
			               		<br/>
			               		<form:input path="nombre" style="width:100%;" maxlength="38" id="nombre" class="lineablanca" onkeypress="return soloLetras(event)" onchange="javascript: return cambiarMayusculasText(this)"/>
			            	</td>
			         	</tr>
						<tr>
			        		<td colspan="2" style="border:1px solid grey;">
				            	<table width="100%" id = "fechaNacimiento" style="margin-rigth:15px;">
				            		<tr>
				                		<td class="text_etiqueta_pequenia" colspan="3">
				                			<spring:message code="field.formulario790.fechaNacimiento"/><logic:equal name="plantilla" property="fechaNacimiento" value="S">
				                			<input type="hidden" id="obl_fechaNacimiento" value="true"><span class="obligatorio">*</span></logic:equal>
				                			<logic:notEqual name="plantilla" property="fechaNacimiento" value="S"><span class="obligatorio" style="visibility:hidden;">*</span></logic:notEqual>
				                  		</td>
				               		</tr>
				               		<tr>
				                  		<td class="text_etiqueta_pequenia_centrada">
				                  			<spring:message code="field.formulario790.dia"/>
				                  		</td>
				                  		<td class="text_etiqueta_pequenia_centrada">
				                  			<spring:message code="field.formulario790.mes"/>
				                  		</td>
				                  		<td class="text_etiqueta_pequenia_centrada">
				                  			<spring:message code="field.formulario790.anio"/>
				                  		</td>
				               		</tr>
				               		<tr>
				               			<td style="text-align:center;font-size:10px;" class="lineablanca">
				                     		<form:select path="diaNacimiento" size="1" style="font-size:10px;" id="diaNacimiento">
				                     		    <option value="">  </option>     
												<option value="01">01</option>
												<option value="02">02</option>
												<option value="03">03</option>
												<option value="04">04</option>
												<option value="05">05</option>
												<option value="06">06</option>
												<option value="07">07</option>
												<option value="08">08</option>
												<option value="09">09</option>
												<option value="10">10</option>
												<option value="11">11</option>
												<option value="12">12</option>
												<option value="13">13</option>
												<option value="14">14</option>
												<option value="15">15</option>
												<option value="16">16</option>
												<option value="17">17</option>
												<option value="18">18</option>
												<option value="19">19</option>
												<option value="20">20</option>
												<option value="21">21</option>
												<option value="22">22</option>
												<option value="23">23</option>
												<option value="24">24</option>
												<option value="25">25</option>
												<option value="26">26</option>
												<option value="27">27</option>
												<option value="28">28</option>
												<option value="29">29</option>
												<option value="30">30</option>
												<option value="31">31</option>
											</form:select>    
				                  		</td>
				                  		<td style="text-align:center;font-size:10px;" class="lineablanca">
				                    		<form:select path="mesNacimiento" size="1" style="font-size:10px;" id="mesNacimiento">
				                    			<option value="">  </option> 
												<option value="01">ENERO</option>
												<option value="02">FEBRERO</option>
												<option value="03">MARZO</option>
												<option value="04">ABRIL</option>
												<option value="05">MAYO</option>
												<option value="06">JUNIO</option>
												<option value="07">JULIO</option>
												<option value="08">AGOSTO</option>
												<option value="09">SEPTIEMBRE</option>
												<option value="10">OCTUBRE</option>
												<option value="11">NOVIEMBRE</option>
												<option value="12">DICIEMBRE</option>
											</form:select>
				                  		</td>
				                  		<td style="text-align:center;font-size:10px;" class="lineablanca">
				                     		<form:input path="anioNacimiento" style="font-size:10px;" size="4" maxlength="4" 
				                     					onkeypress="return soloNumeros(event)" id="anioNacimiento"/>
				                  		</td>
				               		</tr>
				          		</table>
			            	</td>
			            	<td colspan="1" class="text_etiqueta_pequenia" style="text-align:center;">
			            		<spring:message code="field.formulario790.sexo"/><logic:equal name="plantilla" property="sexo" value="S">
			            		<input type="hidden" id="obl_sexo" value="true"><span class="obligatorio">*</span></logic:equal>
			            		<logic:notEqual name="plantilla" property="sexo" value="S"><span class="obligatorio" style="visibility:hidden;">*</span></logic:notEqual>
			            		<br/>
			               		<table width="0%" class="tablaconborde" id = "sexo" style="margin: auto">
			                		<tr>
			                			<td><form:radiobutton path="sex" id="radioHombre" value="V"/></td>
			                    		<td class="text_etiqueta_pequenia"><spring:message code="field.formulario790.varon"/></td>
			                  		</tr>
			                  		<tr>
			                  			<td><form:radiobutton path="sexo" id="radioMujer" value="M"/></td>
			                     		<td class="text_etiqueta_pequenia"><spring:message code="field.formulario790.mujer"/></td>                   		
			                  		</tr>
			               		</table>
			            	</td>
			            	<td class="text_etiqueta_pequenia" colspan="3">
			            		<spring:message code="field.formulario790.nacionalidad"/><logic:equal name="plantilla" property="nacionalidad" value="S">
			            		<input type="hidden" id="obl_inputNacionalidad" value="true"><span class="obligatorio">*</span></logic:equal>
			            		<logic:notEqual name="plantilla" property="nacionalidad" value="S"><span class="obligatorio" style="visibility:hidden;">*</span></logic:notEqual>
			            		</br>
			              		<form:input path="nacionalidad" style="width:100%" maxlength="26" id="nacionalidad"
			               					class="lineablanca" onkeypress="return soloLetras(event)" onchange="javascript: return cambiarMayusculasText(this)"/>
			            	</td>
			            	<td class="text_etiqueta_pequenia">
            					<spring:message code="field.formulario790.codigoNacionalidad"/><br/>
               					<form:input path="codigoNacionalidad" id="codigoNacionalidad" size="1" disabled="true"  maxlength="2" 
               					class="lineablanca" style="BACKGROUND-COLOR: #D0D0D0" tabindex="-1" readonly="true" />
			               	</td>
			            	<td class="text_etiqueta_pequenia" colspan="3">
			            		<spring:message code="field.formulario790.correosElectronicos"/><logic:equal name="plantilla" property="correoElectronico" value="S">
			            		<input type="hidden" id="obl_inputEmail" value="true"><span class="obligatorio">*</span></logic:equal>
								<logic:notEqual name="plantilla" property="correoElectronico" value="S"><span class="obligatorio" style="visibility:hidden;">*</span></logic:notEqual>
			            		<br/>
			               		<form:input path="correoElectronicos" style="width:100%" maxlength="50" id="correoElectronicos"
			               		class="lineablanca"  onchange="return quitarDolarCorreo(this)"/>
			            	</td>
			         	</tr>
			         	<tr>
			            	<td colspan="2" class="text_etiqueta_pequenia">
			            		<spring:message code="field.formulario790.telefono"/><logic:equal name="plantilla" property="telefono" value="S">
			            		<input type="hidden" id="obl_inputTelefono" value="true"><span class="obligatorio">*</span></logic:equal>
			            		<logic:notEqual name="plantilla" property="telefono" value="S"><span class="obligatorio" style="visibility:hidden;">*</span></logic:notEqual>
			               		<br/>
			               		<form:input path="telefono" id="telefono" style="text-align:left;width:40%;height:21" 
			               					maxlength="11" class="lineablanca" onkeypress="return soloNumeros(event)"/>
			               		<form:input path="telefonoAux" id="telefonoAux" style="text-align:left;width:40%;height:21" 
			               					maxlength="11" class="lineablanca" onkeypress="return soloNumeros(event)"/>
			            	</td>
			            	<td colspan="7" class="text_etiqueta_pequenia">
			            		<spring:message code="field.formulario790.domicilio"/><logic:equal name="plantilla" property="via" value="S">
			            		<input type="hidden" id="obl_inputDomicilio" value="true"><span class="obligatorio">*</span></logic:equal>
			            		<logic:notEqual name="plantilla" property="via" value="S"><span class="obligatorio" style="visibility:hidden;">*</span></logic:notEqual>
			               		<br/>
			               		<form:input path="calleDomicilio" style="width:100%;" maxlength="50" id="calleDomicilio"
			               					class="lineablanca" onchange="javascript: return cambiarMayusculasText(this)"/>
			            	</td>
			            	<td colspan="1" class="text_etiqueta_pequenia">
			            		<spring:message code="field.formulario790.codigoPostal"/><logic:equal name="plantilla" property="codigoPostal" value="S">
			            		<input type="hidden" id="obl_inputCodigoPostal" value="true"><span class="obligatorio">*</span></logic:equal>
			            		<logic:notEqual name="plantilla" property="codigoPostal" value="S"><span class="obligatorio" style="visibility:hidden;">*</span></logic:notEqual>
			               		<br/>
			               		<form:input path="codigoPostalDomicilio"  style="text-align:left;" maxlength="5"  
			               					size="10" class="lineablanca" onkeypress="return soloNumeros(event)" id="codigoPostalDomicilio"/>
			            	</td>
			         	</tr>
			         	<tr>
			         		<td colspan="3" class="text_etiqueta_pequenia">
			         			<spring:message code="field.formulario790.domicilioMunicipio"/><logic:equal name="plantilla" property="municipio" value="S">
			         			<input type="hidden" id="obl_inputMunicipioDomicilio" value="true"><span class="obligatorio">*</span></logic:equal>
			         			<logic:notEqual name="plantilla" property="municipio" value="S"><span class="obligatorio" style="visibility:hidden;">*</span></logic:notEqual>
			         			<br/>
			               			<form:input path="municipioDomicilio" id="municipioDomicilio" style="width:100%" class="lineablanca" 
			               						onkeypress="return soloLetras(event)" onchange="cambiarMayusculas(this)" maxlength="30"/>
			          		</td>
			          		<td class="text_etiqueta_pequenia" colspan="1">
            					<spring:message code="field.formulario790.domicilioCodigoMunicipio"/><br/>
               					<form:input path="codigoMunicipio" id="codigoMunicipio"  disabled="true" style="BACKGROUND-COLOR: #D0D0D0" tabindex="-1" size="3" maxlength="3" 
               					class="lineablanca" readonly="true" />
			               	</td>
			            	<td colspan="2">
			            		<table width="100%">
			            			<tr> 
			            				<td class="text_etiqueta_pequenia" colspan="2">
			            					<spring:message code="field.formulario790.domicilioProvincia"/><logic:equal name="plantilla" property="provincia" value="S">
			            					<input type="hidden" id="obl_provinciaDomicilio" value="true"><span class="obligatorio">*</span></logic:equal>
			            					<logic:notEqual name="plantilla" property="provincia" value="S"><span class="obligatorio" style="visibility:hidden;">*</span></logic:notEqual>
			                     		</td>
			                  		</tr>
			                  		<tr>
			                     		<td style="width:100%;">
			                     			<logic:present name="provincias" scope="request">
			               
												<bean:size id="numProvincias" name="provincias" scope="request"/>
												<logic:greaterThan name="numProvincias" value="0">
												
													<form:select path="provinciaDomicilio" id="provinciaDomicilio" size="1" onchange="cambiarMayusculas(this); buscaCodigoProvincia();">
														<option value="0">         </option>
														<form:options items="${provincias}" itemValue="id" itemLabel="descripcion" />
										
													</form:select>
												</logic:greaterThan>
											</logic:present>
			                   			</td>
			                   		</tr>
			                   	</table>
							</td>
							<td class="text_etiqueta_pequenia">
			            					<spring:message code="field.formulario790.domicilioCodProvincia"/><br/>
			               					<form:input path="codigoProvinciaDomicilio" id="codigoProvinciaDomicilio" onfocus="this.blur()" style="BACKGROUND-COLOR: #D0D0D0" tabindex="-1" size="2" maxlength="2" 
			               					class="lineablanca" readonly="true" />
			               	</td>
			            	<td colspan="2">
			            		<table width="100%">
			            			<tr> 
			            				<td class="text_etiqueta_pequenia" colspan="3">
			                        		<spring:message code="field.formulario790.domicilioPais"/><logic:equal name="plantilla" property="pais" value="S">
			                        		<input type="hidden" id="obl_pais" value="true"><span class="obligatorio">*</span></logic:equal>
			                        		<logic:notEqual name="plantilla" property="pais" value="S"><span class="obligatorio" style="visibility:hidden;">*</span></logic:notEqual>
			                     		</td>
			                  		</tr>
			                  		<tr>
			                     		<td style="width:100%;">
			                     			<logic:present name="paises" scope="request">
												<bean:size id="numPaises" name="paises" scope="request"/>
												<logic:greaterThan name="numPaises" value="0">
													<form:select path="pais" size="1" id="pais" onchange="cambiarMayusculas(this);buscaCodigoPais();">
														<option value="0">         </option>
														<form:options items="${paises}" itemValue="id" itemLabel="descripcion"/>
													</form:select>
												</logic:greaterThan>
											</logic:present>
			                   			</td>
			                   		</tr>
			                   	</table>
			            	</td>
			            	<td class="text_etiqueta_pequenia" colspan="1">
            					<spring:message code="field.formulario790.domicilioCodPais"/><br/>
               					<form:input path="codigoPaisDomicilio" id="codigoPaisDomicilio"  onfocus="this.blur()" style="BACKGROUND-COLOR: #D0D0D0" tabindex="-1" size="3" maxlength="3" 
               					class="lineablanca" readonly="true" />
			               	</td>
			      		</tr>
			       		<tr>
			            	<td colspan="10" class="text_etiqueta_titulo"><FONT color="#ffffff"><spring:message code="field.formulario790.convocatoria"/></FONT></td>
			        	</tr>
			        	<tr>
			         		<td style="border:1px solid grey;" colspan="5">
			            		<table width="100%">
			                		<tr>
			                    		<td class="text_etiqueta_pequenia" colspan="1" width="85%">
			                    			<spring:message code="field.formulario790.convocatoria.cuerpoEscala"/>			                    	
			                     		</td>
			                     		<td class="text_etiqueta_pequenia" colspan="1" width="15%">
			                    			<spring:message code="field.formulario790.convocatoria.codigoCuerpoEscala"/>
			                     		</td>
			                  		</tr>
			                  		<tr>
			                     		<td style="font-size:10px;"/>
			                     		<td style="font-size:10px;"/>
			                  		</tr>
			                  		<tr>
				                     	<td style="width:85%;display: block"  colspan="1" id="cuerpoEscalaCombo">
				                        	<logic:present name="cuerposEscala" scope="request">
				                        		<%--<logic:notEqual name="formulario790Form" property="codigoTasa" value="007"> --%>
													<bean:size id="numCuerposEscala" name="cuerposEscala" scope="request"/>
													<logic:greaterThan name="numCuerposEscala" value="0">
														<form:select path="cuerpoEscala" id="cuerpoEscala" style="width:100%;" class="lineablanca" onchange="cambiarMayusculas(this);comprobarCuerpoEscala();buscaCodigoCuerpoEscala();">
															<option value="0">         </option>
															<form:option value="-1"><spring:message code="field.formulario790.convocatoria.introduzcaValorCuerpoEscala"/></form:option>
															<form:options items="${cuerposEscala}" itemValue="id" itemLabel="descripcion" />
														</form:select>
													</logic:greaterThan>
												<%--</logic:notEqual>--%>
												<%--<logic:equal name="formulario790Form" property="codigoTasa" value="007">
				                   					<html:select property="cuerpoEscala" styleId="cuerpoEscala" style="width:100%;" styleClass="lineablanca" disabled="true">
														<html:options collection="cuerposEscala" property="id" labelProperty="descripcion" />
													</html:select>	
			                   					</logic:equal>--%>
											</logic:present>
											<logic:present name="convocatoria" scope="request">
												<form:input path="desCuerpoEscala" value="${convocatoria.desCuerpoEscala}" class="lineablanca" style="width:90%;text-align:left;" maxlength="80" onchange="cambiarMayusculas(this)" readonly="true"/></br>  
											</logic:present>
										</td>
										<td style="width:85%;display: none"  colspan="1" id="cuerpoEscalaManualBlock">
											<logic:notPresent name="convocatoria" scope="request">
				                        		<form:input path="cuerpoEscalaManual" id="cuerpoEscalaManual" maxlength="80"
				                        				style="font-size:14px;text-align:left;width:100%;" onchange="return cambiarMayusculasText(this);"/>
				                        	</logic:notPresent>	
				                   		</td>
			                   			
			                   			<td class="text_etiqueta_pequenia" width="15%" colspan="2">
               								<form:input path="codigoCuerpoEscala" id="codigoCuerpoEscala" onfocus="this.blur()" style="BACKGROUND-COLOR: #D0D0D0" tabindex="-1" size="3" maxlength="4" 
               									class="lineablanca" readonly="true" />
               							</td>	
			                  		</tr>
			              		</table>
			            	</td>
			            	
			            	<td style="border:1px solid grey;" colspan="4">
			            		<table width="100%">
			                		<tr>
			                    		<td class="text_etiqueta_pequenia" colspan="1" width="85%">
			                    			<spring:message code="field.formulario790.convocatoria.especialidad"/><logic:equal name="plantilla" property="especialidad" value="S">
			                    			<input type="hidden" id="obl_especialidad" value="true"><span class="obligatorio">*</span></logic:equal>
			                    			<logic:notEqual name="plantilla" property="especialidad" value="S"><span class="obligatorio" style="visibility:hidden;">*</span></logic:notEqual>
			                     		</td>
			                     		<td class="text_etiqueta_pequenia" colspan="1" width="15%">
			                    			<spring:message code="field.formulario790.convocatoria.codigoEspecialidad"/>			                    	
			                     		</td>
			                  		</tr>
			                  		<tr>
			                     		<td style="font-size:10px;"/>
			                     		<td style="font-size:10px;"/>
			                  		</tr>
				                	<tr>
										
										<td style="width: 85%; display: block" colspan="1" id="especialidadCombo">
											<logic:present	name="especialidades" scope="request">
												<%--<logic:notEqual name="formulario790Form" property="codigoTasa" value="007"> --%>
													<bean:size id="numEspecialidades" name="especialidades"	scope="request" />
													<logic:greaterThan name="numEspecialidades" value="0">
														<form:select path="especialidad" id="especialidad" style="width:100%;" class="lineablanca" onchange="cambiarMayusculas(this);comprobarEspecialidad();buscaCodigoEspecialidad();">
															<option value="0"></option>
															<form:option value="-1"
																<spring:message code="field.formulario790.convocatoria.introduzcaValorEspecialidad" />
															</form:option>
															<form:options items="${especialidades}" itemValue="id"	itemLabel="descripcion" />
														</form:select>
													</logic:greaterThan>
												<%--</logic:notEqual> --%>
												<%--<logic:equal name="formulario790Form" property="codigoTasa"	value="007">
													<html:select property="especialidad" styleId="especialidad" style="width:100%;" styleClass="lineablanca" disabled="true">
														<html:options collection="especialidades" property="id" labelProperty="descripcion" />
													</html:select>
												</logic:equal> --%>
											</logic:present> 
												
											<logic:present name="convocatoria" scope="request">
												<form:select path="especialidad" id="especialidad"	style="width:100%;" class="lineablanca" onchange="cambiarMayusculas(this);buscaCodigoEspecialidadConvocatoria();">
													<option value="0"></option>
													<form:options items="${convocatoria.especialidades}" itemValue="id" itemLabel="descripcion" />
												</form:select>
											</logic:present></td>
											<td style="width: 85%; display: none" colspan="1" id="especialidadManualBlock">
												<logic:notPresent name="convocatoria" scope="request">
													<form:input path="especialidadManual" id="especialidadManual" maxlength="80" style="font-size:14px;text-align:left;width:100%;"	onchange="return cambiarMayusculasText(this);" />
												</logic:notPresent>
											</td>
										

										<td class="text_etiqueta_pequenia" width="15%" colspan="1">
               								<form:input path="codigoEspecialidad" id="codigoEspecialidad" onfocus="this.blur()" style="BACKGROUND-COLOR: #D0D0D0" tabindex="-1" size="3" maxlength="4" 
               								class="lineablanca" readonly="true" />
			               				</td>
			            			</tr>
			        			</table>
			            	</td>

			            	<td style="vertical-align:bottom;text-align:center;" colspan="1">
			            		<table width="100%">
			            			<tr>
			            				<td class="text_etiqueta_pequenia">
			            					<spring:message code="field.formulario790.convocatoria.formaAcceso"/>
			            				</td>
			            			</tr>
			            			<tr>
			            				<td style="width:100%;display: block" id="formaAccesoCombo">
						           			<logic:present name="formasAcceso" scope="request">
												<bean:size id="numFormasAcceso" name="formasAcceso" scope="request"/>
												<logic:greaterThan name="numFormasAcceso" value="0">
													<form:select path="formaAcceso" id="formaAcceso" style="width:100%;" class="lineablanca" onchange="cambiarMayusculas(this);comprobarFormaAcceso();">
														<option value="0">         </option>
														<form:option value="-1"><spring:message code="field.formulario790.convocatoria.introduzcaValorAcceso"/></form:option>
														<form:options items="${formasAcceso}" itemValue="id" itemLabel="codigo" />
													</form:select>
												</logic:greaterThan>
											</logic:present>
											<logic:present name="convocatoria" scope="request">
												<form:input path="codFormaAcceso" id="codFormaAcceso" onchange="cambiarMayusculas(this)" readonly="true"/></br>
											</logic:present>
										</td>
										<td style="width:100%;display: none" id="formaAccesoManualBlock">
											<logic:notPresent name="convocatoria" scope="request">
			                        		<form:input path="formaAccesoManual" id="formaAccesoManual" maxlength="4"
			                        					style="font-size:14px;text-align:left;width:100%;" onchange="return cambiarMayusculasText(this);"/>
			                        		</logic:notPresent>	
			                   			</td>
									</tr>
								</table>
			            	</td>
			     		</tr>
			     	
			     		<tr>
			       			<td style="border:1px solid grey;" colspan="5">
			          			<table width="100%">
			                		<tr>
			                    		<td class="text_etiqueta_pequenia" colspan="1" style="width:85%">
			                    			<spring:message code="field.formulario790.convocatoria.ministerio"/>
			                     		</td>
			                     		<td class="text_etiqueta_pequenia" colspan="1" style="width:15%;">
			                    			<spring:message code="field.formulario790.convocatoria.codigoMinisterio"/>
			                     		</td>
			                  		</tr>
			                 		<tr>
			                     		<td style="font-size:10px;"/>
			                     		<td style="font-size:10px;"/>
			                  		</tr>
			                  		<tr>
			                     		<td colspan="1" style="width:85%;" >
				                     		<logic:notPresent name="convocatoria" scope="request">
			                        		<form:input path="ministerioAutomatico" id="ministerioAutomatico"
			                        					style="font-size:14px;text-align:left;width:100%;" maxlength="80" onchange="javascript: return cambiarMayusculasText(this);"/>
			                        		</logic:notPresent>	
											<logic:present name="convocatoria" scope="request">
												<form:input path="desMinisterioConvocante" value="${convocatoria.desMinisterioConvocante}" style="width:100%" class="lineablanca" maxlength="80" readonly="true" onchange="cambiarMayusculas(this);"/></br>
											</logic:present>
			                        	</td>
			                        	<td colspan="1" style="width:15%;" >
	               							<form:input path="codigoMinisterio" id="codigoMinisterio" onfocus="this.blur()" tabindex="-1" style="BACKGROUND-COLOR: #D0D0D0" size="4" maxlength="5" 
	               							class="lineablanca" readonly="true" />
               							</td>
			                  		</tr>	
			               		</table>
			            	</td>

			            	<td style="border:1px solid grey;" colspan="2">
			            		<table width="100%">
			                		<tr>
			                    		<td class="text_etiqueta_pequenia_centrada" colspan="3">
			                    			<spring:message code="field.formulario790.convocatoria.fechaBOE"/>
			                     		</td>
			                  		</tr>
			                  		<tr>
			                     		<td class="text_etiqueta_pequenia_centrada">
			                     			<spring:message code="field.formulario790.dia"/>
			                     		</td>
			                     		<td class="text_etiqueta_pequenia_centrada">
			                     			<spring:message code="field.formulario790.mes"/>
			                     		</td>
			                     		<td class="text_etiqueta_pequenia_centrada">
			                     			<spring:message code="field.formulario790.anio"/>
			                     		</td>
			                  		</tr>
			                  		<tr>
			                     		<td style="text-align:center;font-size:10px;" class="lineablanca">
			                        		<logic:notPresent name="convocatoria" scope="request">
												<form:select path="diaFechaBoe" style="font-size:10px;" size="1" id="diaFechaBoe">  
													<option value="">  </option> 
													<option value="01">01</option>
													<option value="02">02</option>
													<option value="03">03</option>
													<option value="04">04</option>
													<option value="05">05</option>
													<option value="06">06</option>
													<option value="07">07</option>
													<option value="08">08</option>
													<option value="09">09</option>
													<option value="10">10</option>
													<option value="11">11</option>
													<option value="12">12</option>
													<option value="13">13</option>
													<option value="14">14</option>
													<option value="15">15</option>
													<option value="16">16</option>
													<option value="17">17</option>
													<option value="18">18</option>
													<option value="19">19</option>
													<option value="20">20</option>
													<option value="21">21</option>
													<option value="22">22</option>
													<option value="23">23</option>
													<option value="24">24</option>
													<option value="25">25</option>
													<option value="26">26</option>
													<option value="27">27</option>
													<option value="28">28</option>
													<option value="29">29</option>
													<option value="30">30</option>
													<option value="31">31</option>
												</form:select>
											</logic:notPresent>
											<logic:present name="convocatoria" scope="request">
												<form:input path="diaFechaBoe" style="font-size:10px;" size="2" readonly="true" id="diaFechaBoe"/>
											</logic:present>
				    	                </td>
			                     		<td style="text-align:center;font-size:10px;" class="lineablanca">
				                     		<logic:notPresent name="convocatoria" scope="request">
					                        	<form:select path="mesFechaBoe" style="font-size:10px;" size="1" id="mesFechaBoe">
					                        		<option value="">  </option> 
													<option value="01">ENERO</option>
													<option value="02">FEBRERO</option>
													<option value="03">MARZO</option>
													<option value="04">ABRIL</option>
													<option value="05">MAYO</option>
													<option value="06">JUNIO</option>
													<option value="07">JULIO</option>
													<option value="08">AGOSTO</option>
													<option value="09">SEPTIEMBRE</option>
													<option value="10">OCTUBRE</option>
													<option value="11">NOVIEMBRE</option>
													<option value="12">DICIEMBRE</option>
												</form:select>
											</logic:notPresent>
											<logic:present name="convocatoria" scope="request">
												<form:input path="mesFechaBoe" style="font-size:10px;" size="12" readonly="true" id="mesFechaBoe"/>
											</logic:present>
				                     	</td>
			                     		<td style="text-align:center;font-size:10px;" class="lineablanca">
			                     			<logic:notPresent name="convocatoria" scope="request">
			                     				<form:input path="anioFechaBoe" style="font-size:10px;" size="4" maxlength="4" 
			                     							onkeypress="return soloNumeros(event)" id="anioFechaBoe"/>
											</logic:notPresent>
											<logic:present name="convocatoria" scope="request">
												<form:input path="anioFechaBoe" style="font-size:10px;" size="4" maxlength="4" readonly="true" id="anioFechaBoe"/>
											</logic:present>
			                        	</td>
			                  		</tr>
			               		</table>
			            	</td>
			            	<td style="vertical-align:bottom;" colspan="2">
			            		<table width="100%">
			            			<tr> 
			            				<td class="text_etiqueta_pequenia" colspan="2">
			            					<spring:message code="field.formulario790.convocatoria.provinciaExamen"/><logic:equal name="plantilla" property="provinciaExamen" value="S">
			            					<input type="hidden" id="obl_provinciaExamen" value="true"><span class="obligatorio">*</span></logic:equal>
			            					<logic:notEqual name="plantilla" property="provinciaExamen" value="S"><span class="obligatorio" style="visibility:hidden;">*</span></logic:notEqual>
			            					<br/>
			                     		</td>
			                     		<td class="text_etiqueta_pequenia" colspan="1">
            								<spring:message code="field.formulario790.convocatoria.codigoProvinciaExamen"/>
            								<br/>
            							</td>	
			                  		</tr>
			                  		<tr>
			                     		<td colspan="2">
			                     			
				                     		<logic:present name="provinciasExamen" scope="request">
				                     			<%-- <logic:notEqual name="formulario790Form" property="codigoTasa" value="007"> --%>
													<bean:size id="numProvincias" name="provinciasExamen" scope="request"/>
													<logic:greaterThan name="numProvincias" value="0">
														<form:select path="provinciaExamen" id="provinciaExamen" size="1" style="width:100%" onchange="cambiarMayusculas(this);buscaProvinciaExamen();">
															<option value="0">         </option>
															<form:options items="${provinciasExamen}" itemValue="id" itemLabel="descripcion" />
														</form:select>	
													</logic:greaterThan>
												<%-- </logic:notEqual> --%>
												<%-- <logic:equal name="formulario790Form" property="codigoTasa" value="007">
													<html:select property="provinciaExamen" styleId="provinciaExamen" size="1" style="width:100%" disabled="true">
														<html:options collection="provinciasExamen" property="id" labelProperty="descripcion" />
													</html:select>	
												</logic:equal> --%>
											</logic:present>
											
											<logic:present name="convocatoria" scope="request">
												<form:select path="provinciaExamen" size="1" id="provinciaExamen" style="width:100%" onchange="cambiarMayusculas(this);buscaProvinciaExamenConvocatoria();">
													<option value="0">         </option>
													<form:options items="${convocatoria.provinciasExamen}" itemValue="id" itemLabel="descripcion"/>
												</form:select>
											</logic:present>
											
			                   			</td>
			                   			<td colspan="1">
	               							<form:input path="codigoProvinciaExamen" id="codigoProvinciaExamen" onfocus="this.blur()" style="BACKGROUND-COLOR: #D0D0D0" tabindex="-1" size="1" maxlength="2" 
	               							class="lineablanca" readonly="true" />
               							</td> 
			                   		</tr>
			                   	</table>
			            	</td>
			            	
			            	<td style="vertical-align:bottom;text-align:center;" colspan="1">
			            		<table width="100%">
			            			<tr>
			            				<td class="text_etiqueta_pequenia" colspan="1">
			       							<spring:message code="field.formulario790.convocatoria.gradoDiscapacidad"/>
			       							<br/>
			    							<form:input path="porcentajeDiscapacidad" maxlength="3" size="3" 
			    								id="porcentajeDiscapacidad" style="text-align:center;" class="lineablanca" 
			    								onkeypress="return soloNumeros(event)"
			    								onblur="comprobarDiscapacidad(this)"/>            
			         					</td>
			     					</tr>
			     				</table>	
			     			</td>
			     		</tr>
			     			     	
			     		<tr>		   			
			            	<td style="text-align:center;" colspan="3">
			            		<table width="100%">
			            			<tr> 
			            				<td class="text_etiqueta_pequenia" colspan="3">
			            					<spring:message code="field.formulario790.convocatoria.reservaDiscapacidad"/>
			                     		</td>
			                  		</tr>
			                  		<tr>
			                     		<td style="width:100%;">
			                     			<logic:present name="tiposDiscapacidad" scope="request">
												<bean:size id="numTiposDiscapacidad" name="tiposDiscapacidad" scope="request"/>
												<logic:greaterThan name="numTiposDiscapacidad" value="0">
													<form:select id="tipoDiscapacidad" path="tipoDiscapacidad" disabled="true" size="1" onchange="cambiarMayusculas(this);">
														<option value="0">         </option>
														<form:options items="${tiposDiscapacidad}" itemValue="id" itemLabel="descripcion" />
													</form:select>		
												</logic:greaterThan>
											</logic:present>
			                   			</td>
			                   		</tr>
			                   	</table>
			            	</td>
			            	<td class="text_etiqueta_pequenia" colspan="7">
			            		<spring:message code="field.formulario790.convocatoria.motivoDiscapacidad"/>
			            		<br/>
			            		<form:input id="detalleDiscapacidad" path="detalleDiscapacidad" style="width:99%;" maxlength="70" class="lineablanca" onblur="javascript: return cambiarMayusculasText(this)" disabled="true"/></br>               
			            	</td>
			    		</tr>
			    		<tr>
			            	<td colspan="10" class="text_etiqueta_titulo"><FONT color="#ffffff"><spring:message code="field.formulario790.titulos.titulo"/></FONT></td>
			        	</tr>
			        	<tr>
			       			<td colspan="9">
			       				<table width="100%">
			            			<tr> 
			            				<td class="text_etiqueta_pequenia" colspan="8">
			            					<spring:message code="field.formulario790.titulos.exigidos"/><logic:equal name="plantilla" property="titulosExigidos" value="S">
			            					<input type="hidden" id="obl_inputTitulosExigidos" value="true"><span class="obligatorio">*</span></logic:equal>
			            					<logic:notEqual name="plantilla" property="titulosExigidos" value="S"><span class="obligatorio" style="visibility:hidden;">*</span></logic:notEqual>
			                     		</td>
			                  		</tr>
			                  		<tr>
			                     		<td style="width:100%;display: block" id="tituloCombo">
			                     			<logic:present name="titulosOficiales" scope="request">
												<bean:size id="numTitulosOficiales" name="titulosOficiales" scope="request"/>
												<logic:greaterThan name="numTitulosOficiales" value="0">
													<form:select path="tituloOficial" id="tituloOficial" style="width:100%;" onchange="cambiarMayusculas(this);comprobarTitulo();buscaTituloOficial();">
														<option value="0">         </option>
														<option value="-1"><spring:message code="field.formulario790.convocatoria.introduzcaValor.titulos"/></option>
														<form:options items="${titulosOficiales}" itemValue="id" itemLabel="descripcion" />
													</form:select>	
												</logic:greaterThan>
											</logic:present>
											<logic:present name="convocatoria" scope="request">
												<form:select path="tituloOficial" style="width:100%;" id="tituloOficial" onchange="cambiarMayusculas(this);buscaTituloOficialConvocatoria();">
													<option value="0">         </option>
													<form:options items="${convocatoria.tituloOficials}" itemValue="id" itemLabel="descripcion" />
												</form:select>
											</logic:present>
			                   			</td>
			                   			<td style="width:100%;display: none" id="tituloManualBlock">
											<logic:notPresent name="convocatoria" scope="request">
			                        		<form:input path="tituloManual" id="tituloManual" maxlength="80"
			                        					style="font-size:14px;text-align:left;width:100%;" onchange="return cambiarMayusculasText(this);"/>
			                        		</logic:notPresent>	
			                   			</td>
			                   		</tr>
			                   	</table>
							</td>
							<td class="text_etiqueta_pequenia" colspan="1">
			            		<spring:message code="field.formulario790.codigoTitulo"/><br/>
								<form:input path="codigoTituloExigido" id="codigoTituloExigido" onfocus="this.blur()" style="BACKGROUND-COLOR: #D0D0D0" tabindex="-1" size="4" maxlength="5" class="lineablanca" readonly="true" /></br>               
			            	</td>
			        	</tr>

						<tr>
							<td colspan="9" class="text_etiqueta_pequenia">
							        <spring:message code="field.formulario790.titulos.otros" /><logic:equal name="plantilla" property="otrosTitulos" value="S">
									<input type="hidden" id="obl_otrosTitulos" value="true"><span class="obligatorio">*</span></logic:equal>
									<logic:notEqual name="plantilla" property="otrosTitulos" value="S"><span class="obligatorio" style="visibility:hidden;">*</span></logic:notEqual>
									<br />
									<form:input id="otrosTitulos" path="otrosTitulos"
									style="width:100%;" maxlength="100" class="lineablanca"
									onkeypress="return soloLetras(event)" onchange="javascript: return cambiarMayusculasText(this)" /><br>
							</td>
							<td class="text_etiqueta_pequenia" colspan="1"><spring:message
									code="field.formulario790.codigoTitulo" /><br />
									<form:input path="codigoTituloOtros" id="codigoTituloOtros"
									disabled="true" style="BACKGROUND-COLOR: #D0D0D0" tabindex="-1" size="4" maxlength="5"
								    class="lineablanca"
									readonly="true" /><br>
							</td>
						</tr>
						<tr>
			            	<td colspan="10" class="text_etiqueta_titulo">
			            		<FONT color="#ffffff"><spring:message code="field.formulario790.datosConsignar"/></FONT>
			            	</td>
			    		</tr>
			    		<tr>
			       			<td colspan="10">
			            		<table style="width:100%;">
			                 		<tr>
               						<logic:iterate name= "plantillaPropios"  id="plantillaPropios" indexId="indice">
			                 					<td class="text_etiqueta_pequenia">								
				                     				<bean:write name="plantillaPropios" property="campoPropioBean.tituloCampo"/>:				                     					
				                        			<logic:equal value="true" name="plantillaPropios" property="obligatorio">	
													<span class="obligatorio">*</span>
													<c:set var="idsObligatorios" value="${idsObligatorios} ## ${plantillaPropios.campoPropioBean.tituloCampo}"/>
													</logic:equal>	
													<logic:notEqual value="true" name="plantillaPropios" property="obligatorio">	
													<span class="obligatorio" style="visibility:hidden;">*</span>	
													</logic:notEqual>													
													<logic:equal name="formulario790Form" property="secretarioJud" value="false">
					                        				<% String campo = "campoPropio"+indice; %>
					                        				<form:input  path="<%=campo%>" id="${plantillaPropios.campoPropioBean.tituloCampo}" style="width:100%;" maxlength="100" onchange="javascript: return cambiarMayusculasText(this)"/></br>
					                        		</logic:equal>
					                        		<logic:equal name="formulario790Form" property="secretarioJud" value="true">
					                        				<% String campo = "campoPropio"+indice; %>
					                        				<form:text  path="<%=campo%>" id="${plantillaPropios.campoPropioBean.tituloCampo}" value="${plantillaPropios.campoPropioBean.valorVista}" style="width:100%;" maxlength="100" /></br>
					                        		</logic:equal>			                        																	
				                     			</td>
			                 			</logic:iterate>
			                 			</tr>
			                 			<input type="hidden" id="validarCampos" value="${idsObligatorios}"/>
			               		</table>
			            	</td>
			    		</tr>
			    		<tr>
			      			<td colspan="8"></td>
			     		</tr>
			     		<tr>
			     		<!--  <logic:notEqual name="formulario790Form" property="codigoTasa" value=" <%-- <%=Constantes.CODIGO_TASA_JUSTICIA%>"> --%> --> 	
				            	<td style="text-align:justify;font-style: italic;" colspan="10" class="lineablanca">
				           			<span style="width:3em;"> </span>
				           			<br>
								  	<spring:message code="formulario790.presencial.solicita"/>
								  	<br>
								  	<br>							  	
								  	<spring:message code="formulario790.presencial.declara"/>
								  	<br>
								  	<br>
								  	<spring:message code="formulario790.presencial.consiente"/>
								  	<br>
								  <table>							  
								  	<tr>
								  	<td id="comunidadesDefecto" style ="display:block;" colspan="2">
								  		<spring:message code="formulario790.presencial.consiente4"/>
								 	 	<a style="text-decoration: underline;color:blue"  target="_blank" href="<%=request.getContextPath()%>/secure/tablaComunidades">http://administracion.gob.es/PAG/PID</a>	
								  		<spring:message code="formulario790.comunidadAutonoma"/>	
										<form:select path="comunidadDefecto" class="select_1" style="width:15%;" onclick="ventanaInformacion()" >	     											  	
										<option value="">         </option>
										</form:select>
									</td>	
									<td id="comunidadesFN" style="display: none;" colspan="2">	
										<spring:message code="formulario790.presencial.consiente4"/>
								  		<a style="text-decoration: underline;color:blue" target="_blank" href="<%=request.getContextPath()%>/secure/tablaComunidades">http://administracion.gob.es/PAG/PID</a>	
								  		<spring:message code="formulario790.comunidadAutonoma"/>
										<form:select path="comunidadFN" class="select_1" id="idComunidadFN" style="width:15%;" onchange="comunidadFNChecked()">
										<option value="">         </option>
										<form:options items="${listcomunidadesFN}" itemValue="idComunidad" itemLabel="descripcion" />
										</form:select>		
									</td>
									<td id="comunidadesDD" style="display: none;" colspan="2">		   
										<spring:message code="formulario790.presencial.consiente4"/>
								  		<a style="text-decoration: underline;color:blue" target="_blank" href="<%=request.getContextPath()%>/secure/tablaComunidades">http://administracion.gob.es/PAG/PID</a>	
								  		<spring:message code="formulario790.comunidadAutonoma"/>	
										<form:select path="comunidadDD" class="select_1" id="idComunidadDD" style="width:15%;">
										<option value="">         </option>
										<form:options items="${listcomunidadesDiscapacidad}" itemValue="idComunidad" itemLabel="descripcion" />
										</form:select>
				                        <span class="pae-form__text-error">
				                        	<spring:message code="field.solicitud.campoObligatorio"/>
				                        </span>											
									</td> 	
  			 			   			 												  	
				            		</tr>
				            	</table>
				            	</td>
				          <!--   </logic:notEqual> --> 
				          <!--   <logic:equal name="formulario790Form" property="codigoTasa" value="007">
				            	<td style="text-align:justify;font-style: italic;" colspan="10" class="lineablanca">
				           			<span style="width:3em;"> </span>
				           			<br>
								  	<spring:message code="formulario790.solicita.modelo"/>
								  	<br>	
								  	<br>						  	
								  	<spring:message code="formulario790.declara.modelo"/>
								  	<logic:equal name="formulario790Form" property="codigoCuerpoEscalaAux" value="<%-- <%=Constantes.COD_SECRETARIO_JUDICIAL%> --%>">
								  		<spring:message code="formulario790.secretarioJ.modelo"/>
								  	</logic:equal>
								  	<br>
								  	<br>
								  	<spring:message code="formulario790.manifiesta.modelo"/>
								  	<br>
								  	<br>
								  	<spring:message code="formulario790.consiente.modelo"/>
								  	<a style="text-decoration: underline;color:blue"  target="_blank" href="<%-- <%=request.getContextPath()%> --%> /secure/tablaComunidades">http://administracion.gob.es/PAG/PID.</a>	
								  	<br>
								  	<br>
								  	<input type="checkbox" name="consentimiento" align="bottom"/>
								  	<spring:message code="formulario790.consiente2.modelo"/>
								  	<br>
								  	<br>
				            	</td>
				            </logic:equal>  --> 
			     		</tr>
			     		<!-- <logic:notEqual name="formulario790Form" property="codigoTasa" value="<%-- <%=Constantes.CODIGO_TASA_JUSTICIA%> --%>"> -->
			            <tr id="numeroTitulo" style="display: none;">
			            	<td colspan="5">	
			            		<spring:message code="formulario790.numerotituloFN"/>							
								<form:input path="numeroTituloFN" id="idNumeroTitulo" maxlength="20"/>							
			           		</td>
			            </tr>
			            <tr>
			          		  <td colspan="10">
			            			<input type="checkbox" name="consentimiento" align="bottom"/>
								  	<spring:message code="formulario790.presencial.consiente2"/>
								  	<br>
								  	<br>
							 </td>
			            </tr>
			           <!--  </logic:notEqual> -->  
			     		<tr>
			            	<td colspan="10" class="lineablanca">
			            		<span class="text_etiqueta_tasa" ><spring:message code="field.formulario790.sr/sra"/></span>
			            		<form:input path="personaFirmante" readonly="true" style="width:92%;"  maxlength="70" onchange="javascript: return cambiarMayusculasText(this)"/>
			            	</td>
			         	</tr>
				</table>        
			
			
				<table width="100%" style="border-collapse:collapse;" >
			 		<tr>
			        	<td style="border:1px solid #AAAAAA;" valign="top" colspan="4" width="40%">
			            	<table border="0" width="100%">
			   					<tr>
			     					<td class="text_etiqueta_titulo"><FONT color="#ffffff"><spring:message code="field.formulario790.declarante"/></FONT></td>
			   					</tr>
			   					<tr>
			     					<td style="width:100%;text-align:center;">
			     						<span style="font-family:Arial;font-size:11px;"><spring:message code="field.formulario790.en" /></span>
			     						<form:input path="lugarFirma" maxlength="50" style="width:90%;text-align:left;" class="lineablanca" onchange="javascript: return cambiarMayusculasText(this)"/><br/>
			     						<form:input path="fechaFirma" style="border-style:solid;border-color:#FFFFFF;width:100%;text-align:center;font-size:11px;" readonly="true"/>
			     					</td>
			    				</tr>
							</table>
			         	</td>
			         	<td style="border:1px solid #AAAAAA;" valign="top" colspan="4" width="60%">
			            	<table border="0" width="100%">
			                	<tr>
			                     	<td colspan="3" class="text_etiqueta_titulo"><FONT color="#ffffff"><spring:message code="field.formulario790.ingreso"/></FONT></td>
			                  	</tr>
			                  	<tr>
			                     	<td colspan="3">
			                     		<spring:message code="field.formulario790.ingresoTexto"/>			    
			                     	</td>
			                  	</tr>				                  			                  	
			                  	<tr>		                  			                  	
			                     	<td>
			                     		<table border="0" width="100%">
			                     		<tr>
			                     		<td style="text-align:center;font-size:11px;font-family:Arial;" class="textonegrita">
			                        		<spring:message code="field.formulario790.importeEuros"/>	
			                        		<span style="border:0px solid black;">
			                        		<input style="text-align=center;font-size=16px;" 
			                        				value="I" size="1" readonly="true" onfocus="this.blur()" type="text" 
			                        				name="33" Id="I" tabindex="-1"/>
			                        		</span>
											<span style="border:0px solid black;">
			                           		<form:input path="importeSolicitud" id="importeSolicitud" 
			                           				style="text-align:center;font-size=16px;"  
			                           				maxlength="4" size="4" 
			                           				onblur="limpiarCerosIzquierda(this)"  
			                           				onkeypress="return soloNumeros(event)"/>
			                           		,
			                           		<form:input path="importeSolicitudDecimal" 
			                           				style="text-align:center;font-size=16px;" 
			                           				maxlength="2" size="2" id="importeSolicitudDecimal"
			                           				onblur="rellenaCerosCentimos(this)" 
			                           				onkeypress="return soloNumeros(event)"/>
			                        	</span>
			                        	</td>
			                        	</tr>
	
			                  	<tr height="20"></tr>
			                  	<tr style="text-align:center;">
			                     	<td cospan"3">
			                     		<spring:message code="field.formulario790.formaPago"/>
			                     		&nbsp;
			                     		<spring:message code="field.formulario790.efectivo"/>
			                        	<form:radiobutton path="formaPago" id="pagoEfectivo" value="E" onclick="cambioFormaPago(this)"/>
			                        	&nbsp;&nbsp;			                     
			                     		<spring:message code="field.formulario790.adeudo"/>
			                        	<form:radiobutton path="formaPago" id="pagoCuenta" value="A" onclick="cambioFormaPago(this)"/>
			                        	
			                     	</td>
			                  	</tr>
			                  	</table>
			                  	</td>
			                  	<td>
			                     	<table border="0" width="100%">
			                     	<tr height="20"></tr>
			                     		<tr>
			                  				<td style="text-align:left;">
			                     				<spring:message code="field.formulario790.motivoTexto"/>			    
			                     			</td>
			                     		</tr>
			                     		<tr height="10"></tr>
			                  			<tr>
		                     				<td>
			                     				<form:radiobutton path="motivoRedEx" id="discapacidad" value="1" onclick="motivoReduccion(this)"/>
			                     				<spring:message code="field.formulario790.discapacidad"/>			                     		
			                     			</td>
						          		</tr>
						          		<tr>
			                     			<td>
			                     				<form:radiobutton path="motivoRedEx" id="desempleo" value="2" onclick="motivoReduccion(this)"/>
			                     				<spring:message code="field.formulario790.demandanteEmpleo"/>			                     		
			                     			</td>
			                     		</tr>
			                     		<tr>
		                     				<td>
			                     				<form:radiobutton path="motivoRedEx" id="fnumerosaGeneral" value="5" onclick="motivoReduccion(this)"/>
			                     				<spring:message code="field.formulario790.fnumerosaGeneral"/>			                     		
			                     			</td>
			                     		</tr>
			                     		<tr>
		                     				<td>
		                     					<form:radiobutton path="motivoRedEx" id="fnumerosaEspecial" value="3" onclick="motivoReduccion(this)"/>
			                     				<spring:message code="field.formulario790.fnumerosaEspecial"/>			                     		
			                     			</td>
			                     		</tr>
			                     		<tr>
		                     				<td >
		                     					<form:radioburron path="motivoRedEx" id="vterrorismo" value="6" onclick="motivoReduccion(this)"/>
			                     				<spring:message code="field.formulario790.victimaTerrorismo"/>			                     		
			                     			</td>
			                     		</tr>
			                     		</table>			      
			                  	</td>
			                  	</tr>
			                  	<tr height="10"></tr>
			                  	<tr>
			                     	<td colspan="3" style="text-align:center;" >
			                     		<spring:message code="field.formulario790.codCuenta"/>
			                     	</td>
			                  	</tr>
			                  	<tr height="5"></tr>
			                  	<tr>
			                     	<td class="normal" style="text-align:center;" colspan="1">
			                        	<table border="0" style="width:100%;margin-left:130px">
			                           		<tr>
			                           			<td class="text_etiqueta_normal_centrada">
			                            			<spring:message code="field.formulario790.iban"/><br/>
													<form:input path="iban" 
																style="text-align:left;font-size=13px;" 
																maxlength="4" size="4" id="iban" value="ES" disabled="true"
																onkeyup="if (this.value.length==4) entidad.focus()" />
			                              		</td>
			                            		<td class="text_etiqueta_normal_centrada">
			                            			<spring:message code="field.formulario790.entidad"/><br/>
													<form:input path="entidad" 
																style="text-align:left;font-size=13px;" 
																maxlength="4" size="4" id="entidad" disabled="true" 
																onkeypress="return soloNumeros(event)"
																onkeyup="if (this.value.length==4) oficina.focus()" />
			                              		</td>
			                              		<td class="text_etiqueta_normal_centrada">
			                              			<spring:message code="field.formulario790.oficina"/><br/>
			                                 		<form:input path="oficina" 
			                                 					style="text-align:left;font-size=13px;" 
			                                 					maxlength="4" size="4" id="oficina" disabled="true"
			                                 					onkeypress="return soloNumeros(event)"
			                                 					onkeyup="if (this.value.length==4) digitoConttrol.focus()" />
			                              		</td>
			                              		<td class="text_etiqueta_normal_centrada">
			                              			<spring:message code="field.formulario790.dc"/><br/>
			                                 		<form:input path="digitoConttrol" 
			                                 					style="text-align:left;font-size=13px;" 
			                                 					maxlength="2" size="2" id="digitoControl" disabled="true"
			                                 					onkeypress="return soloNumeros(event)"
			                                 					onkeyup="if (this.value.length==2) numeroCuenta.focus()" />
			                              		</td>
			                              		<td class="text_etiqueta_normal_centrada">
			                              			<spring:message code="field.formulario790.numCuenta"/><br/>
			                                 		<form:input path="numeroCuenta" 
			                                 					style="text-align:left;font-size=13px;" 
			                                 					maxlength="10" size="12" id="numeroCuenta" disabled="true"
			                                 					onkeypress="return soloNumeros(event)"/>
			                              		</td>
			                           		</tr>
			                        	</table>
			                     	</td>
			                  	</tr>
			                  	<tr height="5"></tr>
			                  	<tr>                 
			                     	<td colspan="3" class="normal">
			                         	<div align="center">
			                          		<button type="submit" titleKey="field.generarSolicitud" class="boton"><spring:message code="field.formulario790.enviarSolicitud"/></button>
			                         	</div>
			                     	</td>                   
			                  	</tr>
			               	</table>
			           	</td>
			     	</tr>
			     	<tr>
			     		<td style="text-align:justify;font-style: italic;" colspan="8">
			     			<span style="width:3em;"> </span>
			     			<br>
							  	<spring:message code="formulario790.presencial.enCumplimiento"/>
							<br>
						</td>
					</tr>
				</table>
  			</td>
  		</tr>
  	</table>   
  		<script type="text/javascript">
  		
  		//Comprobamos si hay convoctaroria para que no se m¡pueda modificar el importe 
  		if(document.getElementById("esConvocatoria")){
  			document.getElementById("importeSolicitud").readOnly = true;
  			document.getElementById("importeSolicitudDecimal").readOnly = true;
  		}
  		 
  		 var obligatorio = "<spring:message code="field.formulario790.errorObligatorios"/>";
  		 var telefono = "<spring:message code="field.formulario790.javascript.telefonoError"/>";
  		 var codPostal = "<spring:message code="field.formulario790.javascript.codigoPostalError"/>";
		 var anioNacimiento= "<spring:message code="field.formulario790.javascript.anioNacimientoError"/>";
  		 var anioBOE = "<spring:message code="field.formulario790.javascript.anioBOEError"/>";
  		 var fechaNacimiento = "<spring:message code="field.formulario790.javascript.fechaNacimientoError"/>";
  		 var fechaBOE="<spring:message code="field.formulario790.javascript.fechaBoeError"/>";
  		 var email = "<spring:message code="field.formulario790.javascript.correoElectronicoError"/>";
  		 var CPFormat = "<spring:message code="field.formulario790.javascript.codPostalFormatError"/>";
  		 var ejercicio = "<spring:message code="field.formulario790.javascript.ejercicioError"/>";
  		 var importeLiq = "<spring:message code="field.formulario790.javascript.importeLiquidacionError"/>";
  		 var importe = "<spring:message code="field.formulario790.javascript.importeError"/>";
  		 var numeroTituloFNErr= "<spring:message code="field.formulario790.javascript.numeroTituloFNError"/>";
  		 var IBANErr ="<spring:message code="field.pagoSolicitud.incorrectoIBAN"/>";
  		 var ibanErr ="<spring:message code="field.formulario790.javascript.ibanError"/>";
  		 var entidadErr ="<spring:message code="field.formulario790.javascript.entidadError"/>";
  		 var oficinaErr ="<spring:message code="field.formulario790.javascript.oficinaError"/>";
  		 var dcErr ="<spring:message code="field.formulario790.javascript.dcError"/>";
  		 var cuentaErr ="<spring:message code="field.formulario790.javascript.cuentaError"/>";
  		 var numCuentaErr ="<spring:message code="field.formulario790.javascript.numCuentaError"/>";
  		 var edadErr ="<spring:message code="field.formulario790.javascript.edadError"/>";
  		 var diaErr ="<spring:message code="field.formulario790.javascript.dia"/>";
  		 var mesErr ="<spring:message code="field.formulario790.javascript.mes"/>";
  		 var anioErr ="<spring:message code="field.formulario790.javascript.anio"/>";
  		 var diaBOE ="<spring:message code="field.formulario790.javascript.diaBoe"/>";
  		 var mesBOE ="<spring:message code="field.formulario790.javascript.mesBoe"/>";
  		 var anioBOE ="<spring:message code="field.formulario790.javascript.anioBoe"/>";
  		 var dni1 ="<spring:message code="field.solicitud.alta.errors.dni1"/>";
  		 var dni2 ="<spring:message code="field.solicitud.alta.errors.dni2"/>";
  		 var dni3 ="<spring:message code="field.solicitud.alta.errors.dni3"/>";
  		 var gradoDiscapacidad ="<spring:message code="field.formulario790.javascript.gradoDiscapacidadError"/>";
  		 var gradoDiscapacidad1 ="<spring:message code="field.formulario790.javascript.gradoDiscapacidadError1"/>";
 		 var porcentajeDiscapacidad = "<spring:message code="field.formulario790.javascript.porcentajeDiscapacidadError"/>";
  		 var caractEspeciales ='<spring:message code="field.formulario790.javascript.caracteresEspecialesError"/>';
  		 var gradoDiscapacidad2 ="<spring:message code="field.formulario790.javascript.gradoDiscapacidadError2%"/>";
  		 var dolar = "<spring:message code="field.formulario790.errorDolar"/>";  	
  		 
  		 // Evalua edicion de codigos si se trata del formulario en blanco o de convocatoria
  		 // Se toma como referencia la existencia de la descripcion del centro gestor.
  		 if(document.getElementsByName("desCentroGestor")[0] == null){
  			document.getElementById("codigoNacionalidad").readOnly = false; 
  			document.getElementById("codigoMunicipio").readOnly = false;
  			document.getElementById("codigoProvinciaDomicilio").readOnly = false;
  			document.getElementById("codigoPaisDomicilio").readOnly = false;
  			document.getElementById("codigoCuerpoEscala").readOnly = false;
  			document.getElementById("codigoEspecialidad").readOnly = false;
  			document.getElementById("codigoMinisterio").readOnly = false;
  			document.getElementById("codigoProvinciaExamen").readOnly = false;
  			document.getElementById("codigoTituloExigido").readOnly = false;
  			document.getElementById("codigoTituloOtros").readOnly = false;
  		 }
  		 
   </script> 
   	<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/javascript/validaciones.js"></script>   		  
</form:form>
</body>
</html>