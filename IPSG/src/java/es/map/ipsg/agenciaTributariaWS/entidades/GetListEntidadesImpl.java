package es.map.ipsg.agenciaTributariaWS.entidades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.log4j.Logger;

import es.agenciatributaria.www.ListaEntidades.peticion.wsdl.ListaEntidadesProxy;
import es.agenciatributaria.www.ListaEntidades.peticion.wsdl.ListaEntidades_PortType;
import es.agenciatributaria.www.ListaEntidades.peticion.wsdl.ListaEntidades_Service;
import es.agenciatributaria.www.ListaEntidades.peticion.wsdl.ListaEntidades_ServiceLocator;
import es.agenciatributaria.www.peticion.ClavePaginacion;
import es.agenciatributaria.www.peticion.PeticionListaEntidades;
import es.agenciatributaria.www.peticion.Tipo;
import es.agenciatributaria.www.respuesta.Entidad;
import es.agenciatributaria.www.respuesta.Respuesta;
import es.agenciatributaria.www.respuesta.RespuestaListaEntidades;
import es.map.ips.common.spring.ApplicationContextProvider;
import es.map.ipsg.util.IpsUtils;

public class GetListEntidadesImpl implements GetListEntidades {
	
	private static final Logger logger = Logger.getLogger(GetListEntidadesImpl.class);
	private Properties properties;

	public ArrayList<es.agenciatributaria.www.respuesta.Entidad> peticion() {

		List<Entidad> listaEnt = null;
		ArrayList <Entidad>listaFinal = new ArrayList<Entidad>();

		try {
			
			properties = (Properties) ApplicationContextProvider.getInstance().getBean("propertiesBean");
//			System.getProperties().put("https.proxyHost", "ws.ia.aeat.es");
//			System.getProperties().put("https.proxyPort", properties.getProperty("aeat.proxy.port"));
//			System.getProperties().put("http.proxyUser", properties.getProperty("proxy.usr"));
//			System.getProperties().put("http.proxyPassword", properties.getProperty("proxy.pwd"));
			

			ListaEntidades_Service llamada = new ListaEntidades_ServiceLocator();
			ListaEntidades_PortType lista;
			
			//Configuracion FUN 1.3 INI
//            ProxyAuthenticator proxyAuthenticator = new ProxyAuthenticator(properties.getProperty("proxy.usr"), properties.getProperty("proxy.pwd"));
//            Authenticator.setDefault(proxyAuthenticator);
			//Configuracion FUN 1.3 FIN
			
			lista = llamada.getListaEntidades();
			PeticionListaEntidades peticion = new PeticionListaEntidades();
			peticion.setNIFPeticionario(properties.getProperty("aeat.cif.MINHAP"));
			
			// Paginaciones
			ClavePaginacion clavePag = new ClavePaginacion();
			clavePag.setCodigo("");
			clavePag.setTipo(Tipo.value1);
			
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(new Date());
			XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

			peticion.setTimestamp(date2);
			
			logger.info("Se procede a realizar la peticion de entidades");
			
			ListaEntidadesProxy listaEEFF = new ListaEntidadesProxy("https://www2.agenciatributaria.gob.es/L/NCD1NCDLP0DL");
			
			RespuestaListaEntidades respuesta = listaEEFF.listaEntidades(peticion);
			Respuesta r = respuesta.getRespuesta();
			Entidad []ent = r.getEntidades();
			listaEnt = Arrays.asList(ent);
			listaFinal.addAll(listaEnt);
			
			// Resultados paginados
			if(null!=respuesta && null!=respuesta.getRespuesta() && null!=respuesta.getRespuesta().getEstado()){
				if(respuesta.getRespuesta().getEstado().getIndicadorPaginacion().toString().equals("Si")){
					
					logger.info("Resultados paginados. Se procede a realizar nueva busqueda");
					
					ClavePaginacion clavePaginacion = new ClavePaginacion();
					// Enviamos el código de la ultima entidad recuperada en la primera peticion
					String codigo = listaFinal.get(listaFinal.size()-1).getCodigo();
					clavePaginacion.setCodigo(codigo);
					clavePaginacion.setTipo(Tipo.value2);
					peticion.setClavePaginacion(clavePaginacion);
					respuesta = lista.listaEntidades(peticion);
					
					r = respuesta.getRespuesta();
					Entidad []ent2 = r.getEntidades();
					listaEnt = Arrays.asList(ent2);
					listaFinal.addAll(listaEnt);
				}
			}
			
		} catch (Exception e) {
			logger.error("Error en el servicio de actualización de EE.FF: ",e);
		}
		
		return listaFinal;

	}
}
