package es.map.ipsc.manager.plantilla;

import es.map.ips.dao.PlantillaDAO;
import es.map.ips.model.Plantilla;
import es.map.ips.model.PlantillaQuery;
import es.map.ipsc.bean.PlantillaBean;

/**
 * El Class PlantillaManagerImpl.
 */
public class PlantillaManagerImpl implements PlantillaManager {

	/** el plantilla DAO. */
	private PlantillaDAO plantillaDAO;
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.plantilla.PlantillaManager#buscarPlantillaById(es.map.ips.model.PlantillaQuery)
	 */
	public PlantillaBean buscarPlantillaById(PlantillaQuery plantillaQuery) {
		PlantillaBean plantillaBean;
		Plantilla plantilla =  plantillaDAO.searchUnique(plantillaQuery);
		if(plantilla != null){
			plantillaBean = toPlantillaBean(plantilla);
			return plantillaBean;
		}
		return null;
	}
	
	
	/**
	 * To plantilla bean.
	 *
	 * @param plantilla el plantilla
	 * @return el plantilla bean
	 */
	private PlantillaBean toPlantillaBean(Plantilla plantilla) {
		PlantillaBean aux = new PlantillaBean();
		aux.setId(plantilla.getId());
		aux.setNif(plantilla.getNif());
		aux.setPrimerApellido(plantilla.getPrimerApellido());
		aux.setSegundoApellido(plantilla.getSegundoApellido());
		aux.setNombre(plantilla.getNombre());
		aux.setFechaNacimiento(plantilla.getFechaNacimiento());
		aux.setProvinciaNacimiento(plantilla.getProvinciaNacimiento());
		aux.setSexo(plantilla.getSexo());
		aux.setLocalidadNacimiento(plantilla.getLocalidadNacimiento());
		aux.setNacionalidad(plantilla.getNacionalidad());
		aux.setCorreoElectronico(plantilla.getCorreoElectronico());
		aux.setTelefono(plantilla.getTelefono());
		aux.setVia(plantilla.getVia());
		aux.setCodigoPostal(plantilla.getCodigoPostal());
		aux.setProvincia(plantilla.getProvincia());
		aux.setMunicipio(plantilla.getMunicipio());
		aux.setPais(plantilla.getPais());
		aux.setCuerpo(plantilla.getCuerpo());
		aux.setEspecialidad(plantilla.getEspecialidad());
		aux.setFormaacceso(plantilla.getFormaacceso());
		aux.setEntidadConvocante(plantilla.getEntidadConvocante());
		aux.setFechaBoe(plantilla.getFechaBoe());
		aux.setProvinciaExamen(plantilla.getProvinciaExamen());
		aux.setTipoDiscapacidad(plantilla.getTipoDiscapacidad());
		aux.setPorcentajeDiscapacidad(plantilla.getPorcentajeDiscapacidad());
		aux.setReservaDiscapacidad(plantilla.getReservaDiscapacidad());
		aux.setDetalleDiscapacidad(plantilla.getDetalleDiscapacidad());
		aux.setTitulosExigidos(plantilla.getTitulosExigidos());
		aux.setOtrosTitulos(plantilla.getOtrosTitulos());
		aux.setDatosA(plantilla.getDatosA());
		aux.setDatosB(plantilla.getDatosB());
		aux.setDatosC(plantilla.getDatosC());
		aux.setCodigoCuerpoEscala(plantilla.getCodigoCuerpoEscala());
		aux.setCodigoEspecialidad(plantilla.getCodigoEspecialidad());
		aux.setCodigoMinisterio(plantilla.getCodigoMinisterio());
		aux.setCodigoPaisDomicilio(plantilla.getCodigoPaisDomicilio());
		aux.setCodigoProvinciaDomicilio(plantilla.getCodigoProvinciaDomicilio());
		aux.setCodigoProvinciaExamen(plantilla.getCodigoProvinciaExamen());
		aux.setCodigoTituloOficial(plantilla.getCodigoTituloOficial());
		
		
		return aux;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsc.manager.plantilla.PlantillaManager#buscarPlantilla(es.map.ips.model.PlantillaQuery)
	 */
	public PlantillaBean buscarPlantilla(PlantillaQuery plantillaQuery){
		Plantilla plantilla =  plantillaDAO.searchUnique(plantillaQuery);		
		return toPlantillaBean(plantilla);
	}

	/**
	 * Obtiene el plantilla DAO.
	 *
	 * @return el plantilla DAO
	 */
	public PlantillaDAO getPlantillaDAO() {
		return plantillaDAO;
	}
	
	/**
	 * Establece el plantilla DAO.
	 *
	 * @param plantillaDAO el nuevo plantilla DAO
	 */
	public void setPlantillaDAO(PlantillaDAO plantillaDAO) {
		this.plantillaDAO = plantillaDAO;
	}

	
	
	
}
