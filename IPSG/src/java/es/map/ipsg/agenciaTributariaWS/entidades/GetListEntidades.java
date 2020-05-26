package es.map.ipsg.agenciaTributariaWS.entidades;

import java.util.ArrayList;

public interface GetListEntidades extends java.rmi.Remote {
	
	public ArrayList<es.agenciatributaria.www.respuesta.Entidad> peticion();

}
