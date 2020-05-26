<link rel="shortcut icon" type="image/ico" href="../../images/ipsFavicon.ico" type="image/x-icon"/>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.mpr.es/ipsg/bean-tags" prefix="bean"%>
<%@ taglib uri="http://www.mpr.es/ipsg/html-tags" prefix="html"%>
<%@ taglib uri="http://www.mpr.es/ipsg/logic-tags" prefix="logic"%>
<%@ page import="java.util.ResourceBundle"%>
<%@ page import="java.util.*"%>
<%@ page import="es.map.ipsc.bean.*"%>
<%@ page import="es.map.ipsc.bean.BuscarConvocatoriasBean"%>
<%@ page import="es.map.ipsc.Constantes" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<meta http-equiv="cache-control" content="max-age=0" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<!-- Compatibilidad con Windows 8 -->
		<meta http-equiv="X-UA-Compatible" content="requiresActiveX=true"/>
		<title></title>
	</head>
	<body>

		<div class="pae-body2">
			<div class="pae-wrapper2">
		    	<div class="pae-layout2">
		        	<div class="pae-layout__item2">
		        		<br>       		        	
		      			<div class="pae-tabs__content2">
		      				<div class="pae-layout2">
		      					<div class="pae-layout__item2">
		      						<table class="pae-table2" >
		      							<tbody class="pae-table__body2">
		      								<tr class="pae-table__row2">
												<th>
													<spring:message code="field.comunidades"/>				
												</th>
									
												<th>
													<spring:message code="field.comunidades.discapacidad"/>
												</th>
										
												<th>
													<spring:message code="field.comunidades.familiaNumerosa"/>
												</th>			
											</tr> 
											<!-- Resultados de la consulta -->
											<tr class="pae-table__row2">	
												<th class="pae-table__cell-header2">
													<span class="pae-table__txt-title2">Comunidades Autonomas</span>
												</th>
												<th class="pae-table__cell-header2">
													<span class="pae-table__txt-title2">Discapacidad</span>
												</th>
												<th class="pae-table__cell-header2">
													<span class="pae-table__txt-title2">Familia Numerosa</span>
												</th>
											</tr>	
											<logic:iterate id="registro" name="listcomunidades" >					
												<tr class="pae-table__row2">	
													<td class="pae-table__cell-body2"><bean:write name="registro" property="descripcion" /></td>
													<logic:equal name="registro" property="servicioDD" value="true">
														<td class="pae-table__cell-body2">SI</td>
													</logic:equal>
													<logic:notEqual name="registro" property="servicioDD" value="true">
														<td class="pae-table__cell-body2">NO</td>
													</logic:notEqual>
													<logic:equal name="registro" property="servicioFN" value="true">
														<td class="pae-table__cell-body2">SI</td>
													</logic:equal>
													<logic:notEqual name="registro" property="servicioFN" value="true">
														<td class="pae-table__cell-body2">NO</td>
													</logic:notEqual>										
									    		</tr>
											</logic:iterate>
		      							</tbody>
		      						</table>	
		      					</div>
		      				</div>	
						</div>
		      		</div>
		    	</div>
			</div>
		</div>  
	    
	</body>
</html>
