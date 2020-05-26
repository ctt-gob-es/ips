package es.map.ipsc.manager.formulario790;

import es.map.ipsc.bean.CentroGestorBean;
import es.map.ipsc.bean.ComunidadesBean;
import es.map.ipsc.bean.ConvocatoriaBean;
import es.map.ipsc.bean.CuerpoEscalaBean;
import es.map.ipsc.bean.EspecialidadBean;
import es.map.ipsc.bean.FormaAccesoBean;
import es.map.ipsc.bean.MinisterioBean;
import es.map.ipsc.bean.PaisBean;
import es.map.ipsc.bean.ProvinciaBean;
import es.map.ipsc.bean.ProvinciaExamenBean;
import es.map.ipsc.bean.SolicitudCcaaBean;
import es.map.ipsc.bean.TipoDiscapacidadBean;
import es.map.ipsc.bean.TituloOficialBean;


/**
 * El Interface Formulario790Manager.
 */
public interface Formulario790Manager {

	/**
	 * Obtiene el pais por id.
	 *
	 * @param id el id
	 * @return el pais por id
	 */
	public PaisBean getPaisPorId(Long id);
	
	/**
	 * Obtiene el provincia por id.
	 *
	 * @param id el id
	 * @return el provincia por id
	 */
	public ProvinciaBean getProvinciaPorId(Long id);
	
	/**
	 * Obtiene el provincia examen por id.
	 *
	 * @param id el id
	 * @return el provincia examen por id
	 */
	public ProvinciaExamenBean getProvinciaExamenPorId(Long id);
	
	/**
	 * Obtiene el cuerpo escala por id.
	 *
	 * @param id el id
	 * @return el cuerpo escala por id
	 */
	public CuerpoEscalaBean getCuerpoEscalaPorId(Long id);
	
	/**
	 * Obtiene el centro gestor por id.
	 *
	 * @param id el id
	 * @return el centro gestor por id
	 */
	public CentroGestorBean getCentroGestorPorId(Long id);
	
	/**
	 * Obtiene el especialidad por id.
	 *
	 * @param id el id
	 * @return el especialidad por id
	 */
	public EspecialidadBean getEspecialidadPorId(Long id);
	
	/**
	 * Obtiene el forma acceso por id.
	 *
	 * @param id el id
	 * @return el forma acceso por id
	 */
	public FormaAccesoBean getFormaAccesoPorId(Long id);
	
	/**
	 * Obtiene el ministerio por id.
	 *
	 * @param id el id
	 * @return el ministerio por id
	 */
	public MinisterioBean getMinisterioPorId(Long id);
	
	/**
	 * Obtiene el titulo oficial por id.
	 *
	 * @param id el id
	 * @return el titulo oficial por id
	 */
	public TituloOficialBean getTituloOficialPorId(Long id);
	
	/**
	 * Obtiene el tipo discapacidad por id.
	 *
	 * @param id el id
	 * @return el tipo discapacidad por id
	 */
	public TipoDiscapacidadBean getTipoDiscapacidadPorId(Long id);
	
	/**
	 * Obtiene el convocatoria por id.
	 *
	 * @param id el id
	 * @return el convocatoria por id
	 */
	public ConvocatoriaBean getConvocatoriaPorId(Long id);
	
	/**
	 * Obtiene el comunidad por id.
	 *
	 * @param id el id
	 * @return el comunidad por id
	 */
	public ComunidadesBean getComunidadPorId(Long id);
	
	/**
	 * Obtiene el codigos precargables plantilla general.
	 *
	 * @param codMinisterio el cod ministerio
	 * @param codCuerpoEscala el cod cuerpo escala
	 * @param codEspecialidad el cod especialidad
	 * @param codTituloOficial el cod titulo oficial
	 * @param codPaisDomicilio el cod pais domicilio
	 * @param codProvinciaDomicilio el cod provincia domicilio
	 * @param codProvinciaExamen el cod provincia examen
	 * @return el codigos precargables plantilla general
	 */
	public void getCodigosPrecargablesPlantillaGeneral(Boolean codMinisterio ,Boolean codCuerpoEscala ,Boolean codEspecialidad ,
			Boolean codTituloOficial ,Boolean codPaisDomicilio ,Boolean codProvinciaDomicilio ,Boolean codProvinciaExamen);
	
	/**
	 * Obtiene el codigos precargables plantilla convocatoria.
	 *
	 * @param idPlantilla el id plantilla
	 * @param codMinisterio el cod ministerio
	 * @param codCuerpoEscala el cod cuerpo escala
	 * @param codEspecialidad el cod especialidad
	 * @param codTituloOficial el cod titulo oficial
	 * @param codPaisDomicilio el cod pais domicilio
	 * @param codProvinciaDomicilio el cod provincia domicilio
	 * @param codProvinciaExamen el cod provincia examen
	 * @return el codigos precargables plantilla convocatoria
	 */
	public void getCodigosPrecargablesPlantillaConvocatoria(Long idPlantilla,Boolean codMinisterio ,Boolean codCuerpoEscala ,Boolean codEspecialidad ,
			Boolean codTituloOficial ,Boolean codPaisDomicilio ,Boolean codProvinciaDomicilio ,Boolean codProvinciaExamen);
		
}
