package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.PlantillaDAO;
import es.map.ips.model.Plantilla;
import es.map.ips.model.PlantillaQuery;
import es.map.ipsg.bean.PlantillaBean;


/**
 * El Class PlantillaManagerImpl.
 */
public class PlantillaManagerImpl implements PlantillaManager {

	/** el plantilla DAO. */
	//Variables
	private PlantillaDAO plantillaDAO;
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.PlantillaManager#buscarPlantillaAll(es.map.ips.model.PlantillaQuery)
	 */
	public ArrayList<PlantillaBean> buscarPlantillaAll(PlantillaQuery plantillaQuery){		
		SearchResult<Plantilla> plantilla = plantillaDAO.search(plantillaQuery);
		
		ArrayList<PlantillaBean> arrPlantilla= new ArrayList<PlantillaBean>();
		if(plantilla != null){
			for(int i=0;i<plantilla.getResults().size();i++){
				PlantillaBean aux;
				aux = toPlantillaBean(plantilla.getResults().get(i));
				if(aux != null){
					arrPlantilla.add(aux);
				}
			}	
		}
		return arrPlantilla;		
	}
		
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.PlantillaManager#buscarPlantilla(es.map.ips.model.PlantillaQuery)
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
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.PlantillaManager#toPlantilla(es.map.ipsg.bean.PlantillaBean)
	 */
	public Plantilla toPlantilla(PlantillaBean plantillaBean){
		Plantilla plantilla = new Plantilla();
		
		plantilla.setId(plantillaBean.getId());
		plantilla.setNif(plantillaBean.getNif());
		plantilla.setPrimerApellido(plantillaBean.getPrimerApellido());
		plantilla.setSegundoApellido(plantillaBean.getSegundoApellido());
		plantilla.setNombre(plantillaBean.getNombre());
		plantilla.setFechaNacimiento(plantillaBean.getFechaNacimiento());
		plantilla.setSexo(plantillaBean.getSexo());
		plantilla.setProvinciaNacimiento(plantillaBean.getProvinciaNacimiento());
		plantilla.setLocalidadNacimiento(plantillaBean.getLocalidadNacimiento());
		plantilla.setNacionalidad(plantillaBean.getNacionalidad());
		plantilla.setTelefono(plantillaBean.getTelefono());
		plantilla.setCorreoElectronico(plantillaBean.getCorreoElectronico());
		plantilla.setVia(plantillaBean.getVia());
		plantilla.setCodigoPostal(plantillaBean.getCodigoPostal());
		plantilla.setMunicipio(plantillaBean.getMunicipio());
		plantilla.setProvincia(plantillaBean.getProvincia());
		plantilla.setPais(plantillaBean.getPais());
		plantilla.setCuerpo(plantillaBean.getCuerpo());
		plantilla.setEspecialidad(plantillaBean.getEspecialidad());
		plantilla.setFormaacceso(plantillaBean.getFormaacceso());
		plantilla.setEntidadConvocante(plantillaBean.getEntidadConvocante());
		plantilla.setFechaBoe(plantillaBean.getEntidadConvocante());
		plantilla.setProvinciaExamen(plantillaBean.getProvinciaExamen());
		plantilla.setTipoDiscapacidad(plantillaBean.getTipoDiscapacidad());
		plantilla.setPorcentajeDiscapacidad(plantillaBean.getPorcentajeDiscapacidad());
		plantilla.setReservaDiscapacidad(plantillaBean.getReservaDiscapacidad());
		plantilla.setDetalleDiscapacidad(plantillaBean.getDetalleDiscapacidad());
		plantilla.setTitulosExigidos(plantillaBean.getTitulosExigidos());
		plantilla.setOtrosTitulos(plantillaBean.getOtrosTitulos());
		plantilla.setDatosA(plantillaBean.getDatosA());
		plantilla.setDatosB(plantillaBean.getDatosB());
		plantilla.setDatosC(plantillaBean.getDatosC());
		plantilla.setTipoPlantilla(plantillaBean.getTipoPlantilla());
		plantilla.setCodigoCuerpoEscala(plantillaBean.getCodigoCuerpoEscala());
		plantilla.setCodigoEspecialidad(plantillaBean.getCodigoEspecialidad());
		plantilla.setCodigoMinisterio(plantillaBean.getCodigoMinisterio());
		plantilla.setCodigoPaisDomicilio(plantillaBean.getCodigoPaisDomicilio());
		plantilla.setCodigoProvinciaDomicilio(plantillaBean.getCodigoProvinciaDomicilio());
		plantilla.setCodigoProvinciaExamen(plantillaBean.getCodigoProvinciaExamen());
		plantilla.setCodigoTituloOficial(plantillaBean.getCodigoTituloOficial());
		
		return plantilla;
	}
	
	/**
	 * To plantilla bean.
	 *
	 * @param plantilla el plantilla
	 * @return el plantilla bean
	 */
	public PlantillaBean toPlantillaBean(Plantilla plantilla){
		PlantillaBean plantillaBean = new PlantillaBean();
		
		plantillaBean.setId(plantilla.getId());
		plantillaBean.setNif(plantilla.getNif());
		plantillaBean.setPrimerApellido(plantilla.getPrimerApellido());
		plantillaBean.setSegundoApellido(plantilla.getSegundoApellido());
		plantillaBean.setNombre(plantilla.getNombre());
		plantillaBean.setFechaNacimiento(plantilla.getFechaNacimiento());
		plantillaBean.setSexo(plantilla.getSexo());		
		plantillaBean.setProvinciaNacimiento(plantilla.getProvinciaNacimiento());
		plantillaBean.setLocalidadNacimiento(plantilla.getLocalidadNacimiento());
		plantillaBean.setNacionalidad(plantilla.getNacionalidad());
		plantillaBean.setTelefono(plantilla.getTelefono());
		plantillaBean.setCorreoElectronico(plantilla.getCorreoElectronico());
		plantillaBean.setVia(plantilla.getVia());
		plantillaBean.setCodigoPostal(plantilla.getCodigoPostal());
		plantillaBean.setMunicipio(plantilla.getMunicipio());
		plantillaBean.setProvincia(plantilla.getProvincia());
		plantillaBean.setPais(plantilla.getPais());
		plantillaBean.setCuerpo(plantilla.getCuerpo());
		plantillaBean.setEspecialidad(plantilla.getEspecialidad());
		plantillaBean.setFormaacceso(plantilla.getFormaacceso());
		plantillaBean.setEntidadConvocante(plantilla.getEntidadConvocante());
		plantillaBean.setFechaBoe(plantilla.getEntidadConvocante());
		plantillaBean.setProvinciaExamen(plantilla.getProvinciaExamen());
		plantillaBean.setTipoDiscapacidad(plantilla.getTipoDiscapacidad());
		plantillaBean.setPorcentajeDiscapacidad(plantilla.getPorcentajeDiscapacidad());
		plantillaBean.setReservaDiscapacidad(plantilla.getReservaDiscapacidad());
		plantillaBean.setDetalleDiscapacidad(plantilla.getDetalleDiscapacidad());
		plantillaBean.setTitulosExigidos(plantilla.getTitulosExigidos());
		plantillaBean.setOtrosTitulos(plantilla.getOtrosTitulos());
		plantillaBean.setDatosA(plantilla.getDatosA());
		plantillaBean.setDatosB(plantilla.getDatosB());
		plantillaBean.setDatosC(plantilla.getDatosC());
		plantillaBean.setTipoPlantilla(plantilla.getTipoPlantilla());
		plantillaBean.setCodigoCuerpoEscala(plantilla.getCodigoCuerpoEscala());
		plantillaBean.setCodigoEspecialidad(plantilla.getCodigoEspecialidad());
		plantillaBean.setCodigoMinisterio(plantilla.getCodigoMinisterio());
		plantillaBean.setCodigoPaisDomicilio(plantilla.getCodigoPaisDomicilio());
		plantillaBean.setCodigoProvinciaDomicilio(plantilla.getCodigoProvinciaDomicilio());
		plantillaBean.setCodigoProvinciaExamen(plantilla.getCodigoProvinciaExamen());
		plantillaBean.setCodigoTituloOficial(plantilla.getCodigoTituloOficial());
		
		return plantillaBean;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.PlantillaManager#guardarPlantilla(es.map.ipsg.bean.PlantillaBean)
	 */
	public Long guardarPlantilla(PlantillaBean plantillaBean){
		Plantilla plantilla = toPlantilla(plantillaBean);
		Long idPlantilla = plantillaDAO.insert(plantilla);
		return idPlantilla;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.PlantillaManager#obtenerPlantilla(java.lang.Long)
	 */
	public PlantillaBean obtenerPlantilla(Long idPlantilla){
		Plantilla plantilla = plantillaDAO.get(idPlantilla);
		if(plantilla == null){
			return null;
		}
		return toPlantillaBean(plantilla);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.PlantillaManager#actualizarPlantilla(es.map.ipsg.bean.PlantillaBean)
	 */
	public void actualizarPlantilla(PlantillaBean plantillaBean) {
		Plantilla plantilla = toPlantilla(plantillaBean);
		plantillaDAO.update(plantilla);
	}

}
