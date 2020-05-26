package es.map.ipsg.manager;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.ConvocatoriaMotivoReduccionCustomDAO;
import es.map.ips.dao.MotivoReduccionTasaDAO;
import es.map.ips.model.Convocatoria;
import es.map.ips.model.ConvocatoriaQuery;
import es.map.ips.model.MotivoReduccionTasa;
import es.map.ips.model.MotivoReduccionTasaQuery;
import es.map.ipsg.bean.ConvocatoriasBean;
import es.map.ipsg.bean.MotivoReduccionTasaBean;
import es.map.ipsg.util.Constantes;


/**
 * El Class MotivoReduccionTasaManagerImpl.
 */
public class MotivoReduccionTasaManagerImpl implements MotivoReduccionTasaManager {

	/** el motivo reduccion tasa DAO. */
	//Variables
	private MotivoReduccionTasaDAO motivoReduccionTasaDAO;
	
	/** el convocatoria motivo reduccion DAO. */
	private  ConvocatoriaMotivoReduccionCustomDAO convocatoriaMotivoReduccionDAO;
	
	/** La constante logger. */
	private static final Logger logger = Logger.getLogger(MotivoReduccionTasaManagerImpl.class);
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.MotivoReduccionTasaManager#buscarMotivoReduccionTasaCombo(es.map.ips.model.MotivoReduccionTasaQuery)
	 */
	public ArrayList<MotivoReduccionTasaBean> buscarMotivoReduccionTasaCombo(MotivoReduccionTasaQuery motivoReduccionTasaQuery){
		motivoReduccionTasaQuery.setEstado(Constantes.MOTIVOREDUCCIONTASA_ESTADO_ACTIVO);
		SearchResult<MotivoReduccionTasa> motivoReduccionTasa = buscarMotivoReduccionTasa(motivoReduccionTasaQuery);
		ArrayList<MotivoReduccionTasaBean> arrMotivoReduccionTasa= new ArrayList<MotivoReduccionTasaBean>();
		for(int i=0;i<motivoReduccionTasa.getResults().size();i++){
			MotivoReduccionTasaBean aux;
			aux = toMotivoReduccionTasaBean(motivoReduccionTasa.getResults().get(i));
			if(aux != null){
				arrMotivoReduccionTasa.add(aux);
			}
		}	
		return arrMotivoReduccionTasa;		
	}
	
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.MotivoReduccionTasaManager#buscarMotivoReduccionTasaComboVisibilidad(es.map.ips.model.MotivoReduccionTasaQuery)
	 */
	public ArrayList<MotivoReduccionTasaBean> buscarMotivoReduccionTasaComboVisibilidad(MotivoReduccionTasaQuery motivoReduccionTasaQuery){
		motivoReduccionTasaQuery.addOrder(MotivoReduccionTasaQuery.DESCRIPCION, OrderType.ASC);
		motivoReduccionTasaQuery.setEstado(Constantes.MOTIVOREDUCCIONTASA_ESTADO_ACTIVO);
		SearchResult<MotivoReduccionTasa> motivoReduccionTasa = buscarMotivoReduccionTasa(motivoReduccionTasaQuery);
		ArrayList<MotivoReduccionTasaBean> arrMotivoReduccionTasa= new ArrayList<MotivoReduccionTasaBean>();
		for(int i=0;i<motivoReduccionTasa.getResults().size();i++){
			//Sólo se mostrarán registros visibles	
			if(motivoReduccionTasa.getResults().get(i).getVisible() == 'S' || motivoReduccionTasa.getResults().get(i).getVisible() == 's')
			{	
				MotivoReduccionTasaBean aux;
				aux = toMotivoReduccionTasaBean(motivoReduccionTasa.getResults().get(i));
				if(aux != null){
					arrMotivoReduccionTasa.add(aux);
				}
			}	
		}	
		return arrMotivoReduccionTasa;		
	}
	
	/**
	 * To motivo reduccion tasa bean.
	 *
	 * @param motivoReduccionTasa el motivo reduccion tasa
	 * @return el motivo reduccion tasa bean
	 */
	private MotivoReduccionTasaBean toMotivoReduccionTasaBean(MotivoReduccionTasa motivoReduccionTasa) {
		
		MotivoReduccionTasaBean auxMotivoReduccionTasa = new MotivoReduccionTasaBean();
		auxMotivoReduccionTasa.setId(motivoReduccionTasa.getId());
		auxMotivoReduccionTasa.setDescripcion( motivoReduccionTasa.getDescripcion());
		auxMotivoReduccionTasa.setTextoExplicativo(motivoReduccionTasa.getTextoExplicativo());
		auxMotivoReduccionTasa.setPorcentajeDescuento(motivoReduccionTasa.getPorcentajeDescuento());
		auxMotivoReduccionTasa.setCodigo(motivoReduccionTasa.getCodigo());
		auxMotivoReduccionTasa.setEstado(motivoReduccionTasa.getEstado());
		if(motivoReduccionTasa.getVisible() != null)
		{	
			if(motivoReduccionTasa.getVisible().equals('S'))
			{
				auxMotivoReduccionTasa.setVisibilidad(true);
			}	
			else if(motivoReduccionTasa.getVisible().equals('N'))
			{
				auxMotivoReduccionTasa.setVisibilidad(false);
			}	
		}
		else
		{
			auxMotivoReduccionTasa.setVisibilidad(false);
		}	
		
		return auxMotivoReduccionTasa;
	}
	
/* (non-Javadoc)
 * @see es.map.ipsg.manager.MotivoReduccionTasaManager#buscarMotivoReduccionTasaAll(es.map.ips.model.MotivoReduccionTasaQuery)
 */
public ArrayList<MotivoReduccionTasaBean> buscarMotivoReduccionTasaAll(MotivoReduccionTasaQuery motivoReduccionTasaQuery){
		
		SearchResult<MotivoReduccionTasa> motivoReduccionTasa = buscarMotivoReduccionTasa(motivoReduccionTasaQuery);
		int numTotal = 0;
		if (motivoReduccionTasa.getNumResults() !=null){
			numTotal = motivoReduccionTasa.getNumResults();
		}
		ArrayList<MotivoReduccionTasaBean> arrMotivoReduccionTasa= new ArrayList<MotivoReduccionTasaBean>();
		for(int i=0;i<motivoReduccionTasa.getResults().size();i++){
			MotivoReduccionTasaBean aux;
			aux = toMotivoReduccionTasaBeanAll(motivoReduccionTasa.getResults().get(i),numTotal);
			if(aux != null){
				arrMotivoReduccionTasa.add(aux);
			}
		}	
		return arrMotivoReduccionTasa;		
	}
	
/**
 * To motivo reduccion tasa bean all.
 *
 * @param motivoReduccionTasa el motivo reduccion tasa
 * @param numTotal el num total
 * @return el motivo reduccion tasa bean
 */
private MotivoReduccionTasaBean toMotivoReduccionTasaBeanAll(MotivoReduccionTasa motivoReduccionTasa, int numTotal) {
	
	String descripcion = motivoReduccionTasa.getDescripcion();
	
	MotivoReduccionTasaBean auxMotivoReduccionTasa = new MotivoReduccionTasaBean();
	auxMotivoReduccionTasa.setId(motivoReduccionTasa.getId());
	auxMotivoReduccionTasa.setDescripcion(descripcion);
	auxMotivoReduccionTasa.setPorcentajeDescuento(motivoReduccionTasa.getPorcentajeDescuento());
	auxMotivoReduccionTasa.setTextoExplicativo(motivoReduccionTasa.getTextoExplicativo());
	auxMotivoReduccionTasa.setEstado(motivoReduccionTasa.getEstado());
	auxMotivoReduccionTasa.setNumTotal(numTotal); 
	auxMotivoReduccionTasa.setCodigo(motivoReduccionTasa.getCodigo());
	if(motivoReduccionTasa.getVisible() != null)
	{	
		if(motivoReduccionTasa.getVisible().equals('S'))
		{
			auxMotivoReduccionTasa.setVisibilidad(true);
		}	
		else if(motivoReduccionTasa.getVisible().equals('N'))
		{
			auxMotivoReduccionTasa.setVisibilidad(false);
		}	
	}
	else
	{
		auxMotivoReduccionTasa.setVisibilidad(false);
	}	
	
	return auxMotivoReduccionTasa;
}
	
	/**
	 * Buscar motivo reduccion tasa.
	 *
	 * @param motivoReduccionTasaQuery el motivo reduccion tasa query
	 * @return el search result
	 */
	private SearchResult<MotivoReduccionTasa> buscarMotivoReduccionTasa(MotivoReduccionTasaQuery motivoReduccionTasaQuery) {
			ApplicationException.assertNotNull(motivoReduccionTasaQuery, "motivoReduccionTasaQuery no puede ser null");
		motivoReduccionTasaQuery.addOrder("descripcion", OrderType.ASC);
		return motivoReduccionTasaDAO.search(motivoReduccionTasaQuery);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.MotivoReduccionTasaManager#guardarMotivoReduccionTasa(es.map.ipsg.bean.MotivoReduccionTasaBean)
	 */
	public Integer guardarMotivoReduccionTasa(MotivoReduccionTasaBean motivoBean){
		MotivoReduccionTasa motivo= toMotivoReduccionTasa(motivoBean);
		Integer idMotivo = motivoReduccionTasaDAO.insert(motivo);
		
		return idMotivo;
	}

	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.MotivoReduccionTasaManager#toMotivoReduccionTasa(es.map.ipsg.bean.MotivoReduccionTasaBean)
	 */
	public MotivoReduccionTasa toMotivoReduccionTasa(MotivoReduccionTasaBean motivoBean){
		
		MotivoReduccionTasa motivo= new MotivoReduccionTasa();
		
		if(motivoBean.getId() != null && motivoBean.getId()>0){
			motivo = motivoReduccionTasaDAO.get(motivoBean.getId());
		}
		
		motivo.setId(motivoBean.getId());
		motivo.setDescripcion(motivoBean.getDescripcion());
		motivo.setTextoExplicativo(motivoBean.getTextoExplicativo());
		motivo.setPorcentajeDescuento(motivoBean.getPorcentajeDescuento());
		motivo.setEstado(motivoBean.getEstado());
		motivo.setCodigo(motivoBean.getCodigo());
		if(motivoBean.getVisibilidad() != null)
		{
			if(motivoBean.getVisibilidad() == true)
			{	
				motivo.setVisible('S');
			}
			else if(motivoBean.getVisibilidad() == false)
			{
				motivo.setVisible('N');
			}	
		}
		else
		{
			motivo.setVisible('N');
		}
				
		return motivo;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.MotivoReduccionTasaManager#obtenerMotivoReduccionTasa(java.lang.Integer)
	 */
	public MotivoReduccionTasaBean obtenerMotivoReduccionTasa(Integer idMotivo){
		MotivoReduccionTasa motivo = motivoReduccionTasaDAO.get(idMotivo);
		return toMotivoReduccionTasaBean(motivo);
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.MotivoReduccionTasaManager#compruebaConvocatoria(es.map.ips.model.ConvocatoriaQuery, es.map.ips.model.MotivoReduccionTasaQuery)
	 */
	public ArrayList<ConvocatoriasBean> compruebaConvocatoria (
			ConvocatoriaQuery convocatoriaQuery, MotivoReduccionTasaQuery motivoReduccionQuery){
		SearchResult<Convocatoria> convocatoria = convocatoriaMotivoReduccionDAO.searchConvocatoriaPorMotivoReduccion(motivoReduccionQuery, convocatoriaQuery);
		ArrayList<ConvocatoriasBean> arrConvocatoria= new ArrayList<ConvocatoriasBean>();
		for(int i=0;i<convocatoria.getResults().size();i++){
			ConvocatoriasBean aux;
			aux = toBuscarConvocatoriaBeanSinNumTotal(convocatoria.getResults().get(i));
			if(aux != null){
				arrConvocatoria.add(aux);
				}
			}	
		return arrConvocatoria;	
		}
	
	/**
	 * To buscar convocatoria bean sin num total.
	 *
	 * @param convocatoria el convocatoria
	 * @return el convocatorias bean
	 */
	private ConvocatoriasBean toBuscarConvocatoriaBeanSinNumTotal(Convocatoria convocatoria) {

		String id = convocatoria.getId().toString();
		logger.info("ID: " + id);

		String ejercicio = convocatoria.getEjercicio();
		logger.info("Ejercicio: " + ejercicio);

		String estado = convocatoria.getEstadoConvocatoria().getDescripcion();
		logger.info("Estado: " + estado);

		ConvocatoriasBean convocatoriaBean = new ConvocatoriasBean();
		convocatoriaBean.setIdConvocatoria(id);
		convocatoriaBean.setEstadoConvocatoria(estado);

		convocatoriaBean.setEjercicio(ejercicio);

		return convocatoriaBean;
	}
	
	/* (non-Javadoc)
	 * @see es.map.ipsg.manager.MotivoReduccionTasaManager#modificarMotivoReduccionTasa(es.map.ipsg.bean.MotivoReduccionTasaBean)
	 */
	public void modificarMotivoReduccionTasa(MotivoReduccionTasaBean motivoBean){
		MotivoReduccionTasa motivo = toMotivoReduccionTasa(motivoBean);
		motivoReduccionTasaDAO.update(motivo);
	}
	
	/**
	 * Obtiene el motivo reduccion tasa DAO.
	 *
	 * @return el motivo reduccion tasa DAO
	 */
	public MotivoReduccionTasaDAO getMotivoReduccionTasaDAO() {
		return motivoReduccionTasaDAO;
	}

	/**
	 * Establece el motivo reduccion tasa DAO.
	 *
	 * @param motivoReduccionTasaDAO el nuevo motivo reduccion tasa DAO
	 */
	public void setMotivoReduccionTasaDAO(MotivoReduccionTasaDAO motivoReduccionTasaDAO) {
		this.motivoReduccionTasaDAO = motivoReduccionTasaDAO;
	}

	/**
	 * Obtiene el logger.
	 *
	 * @return el logger
	 */
	public static Logger getLogger() {
		return logger;
	}

	/**
	 * Obtiene el convocatoria motivo reduccion DAO.
	 *
	 * @return the convocatoriaMotivoReduccionDAO
	 */
	public ConvocatoriaMotivoReduccionCustomDAO getConvocatoriaMotivoReduccionDAO() {
		return convocatoriaMotivoReduccionDAO;
	}

	/**
	 * Establece el convocatoria motivo reduccion DAO.
	 *
	 * @param convocatoriaMotivoReduccionDAO the convocatoriaMotivoReduccionDAO to set
	 */
	public void setConvocatoriaMotivoReduccionDAO(
			ConvocatoriaMotivoReduccionCustomDAO convocatoriaMotivoReduccionDAO) {
		this.convocatoriaMotivoReduccionDAO = convocatoriaMotivoReduccionDAO;
	}

	

}