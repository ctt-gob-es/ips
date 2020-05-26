package es.map.ipsg.manager;

import java.util.ArrayList;

import es.map.ips.common.exception.ApplicationException;
import es.map.ips.common.model.OrderType;
import es.map.ips.common.model.SearchResult;
import es.map.ips.dao.CategoriaDAO;
import es.map.ips.model.Categoria;
import es.map.ips.model.CategoriaQuery;
import es.map.ipsg.bean.CategoriaBean;
import es.map.ipsg.util.Constantes;

/**
 * El Class CategoriaManagerImpl.
 *
 * @author ftoromar
 * Implementación de la clase CategoriaManagerImp
 */
public class CategoriaManagerImpl implements CategoriaManager {

	/** el categoria DAO. */
	//Variables
	private CategoriaDAO categoriaDAO;
	
	/**
	 * Buscar categoria combo.
	 *
	 * @param categoriaQuery CategoriaQuery
	 * @return arrCategoria ArrayList<CategoriaBean>
	 */
	public ArrayList<CategoriaBean> buscarCategoriaCombo(CategoriaQuery categoriaQuery){
		categoriaQuery.addOrder(CategoriaQuery.DESCRIPCION, OrderType.ASC);
		categoriaQuery.setEstado(Constantes.CATEGORIA_ESTADO_ACTIVO);
		SearchResult<Categoria> categoria = buscarCategoria(categoriaQuery);
		
		ArrayList<CategoriaBean> arrCategoria= new ArrayList<CategoriaBean>();
		for(int i=0;i<categoria.getResults().size();i++){
			CategoriaBean aux;
			aux = toCategoriaComboBean(categoria.getResults().get(i));
			if(aux != null){
				arrCategoria.add(aux);
			}
		}	
		return arrCategoria;		
	}
	
	/**
	 * Buscar categoria all.
	 *
	 * @param categoriaQuery  CategoriaQuery
	 * @return arrCategoria ArrayList<CategoriaBean>
	 */
	public ArrayList<CategoriaBean> buscarCategoriaAll(CategoriaQuery categoriaQuery){
		SearchResult<Categoria> categoria = buscarCategoria(categoriaQuery);
		Integer numTotal = categoria.getNumResults();
		int iNumTotal = 0;
		if (numTotal != null){
			iNumTotal = numTotal.intValue();
		}
		ArrayList<CategoriaBean> arrCategoria= new ArrayList<CategoriaBean>();
		for(int i=0;i<categoria.getResults().size();i++){
			CategoriaBean aux;
			aux = toCategoriaBean(categoria.getResults().get(i), iNumTotal);
			if(aux != null){
				arrCategoria.add(aux);
			}
		}	
		return arrCategoria;
	}

	/**
	 * Guardar categoria.
	 *
	 * @author ftoromar
	 * Metodo para guardar una categoria en la BBDD
	 * @param categoriaBean cargado con los datos necesarios
	 * @return Un entero con el id una vez se ha insertado en la BBDD
	 */
	public Integer guardarCategoria(CategoriaBean categoriaBean){
		Categoria categoria = toCategoria(categoriaBean);
		Integer idCentroGestor = categoriaDAO.insert(categoria);		
		return idCentroGestor;
	}
	
	/**
	 * Obtener categoria.
	 *
	 * @author ftoromar
	 * Metodo para obtener una categoria según su ID
	 * @param idCategoria  con el id de la categoria
	 * @return un objeto categoriaBean cargado con los datos de la BBDD
	 */
	public CategoriaBean obtenerCategoria(Integer idCategoria){
		Categoria categoria = categoriaDAO.get(idCategoria);
		return toCategoriaBean(categoria);
	}
	
	/**
	 * Modificar categoria.
	 *
	 * @author ftoromar
	 * Modifica los datos de una categoria
	 * @param categoriaBean cargado con todos los datos necesarios
	 */
	public void modificarCategoria(CategoriaBean  categoriaBean)
	{
		Categoria categoria=  toCategoria(categoriaBean);
		categoriaDAO.update(categoria);
	}
	
	/**
	 * To categoria bean.
	 *
	 * @author ftoromar
	 * Metodo para pasar un objeto categoria a categoriaBean y saber el numero de registros
	 * totales que tiene.
	 * @param categoria cargado con los datos necesarios
	 * @param numTotal indicando el numero total de registros que existen
	 * @return CategoriaBean parseado
	 */
	private CategoriaBean toCategoriaBean(Categoria categoria, int numTotal) {
		
		CategoriaBean auxCategoria = new CategoriaBean();
		auxCategoria.setId(categoria.getId());	
		auxCategoria.setCodigo(categoria.getCodigo());
		auxCategoria.setDescripcion(categoria.getDescripcion());
		auxCategoria.setEstado(categoria.getEstado());
		auxCategoria.setNumTotal(numTotal);
		
		if(categoria.getVisible() != null)
		{	
			if(categoria.getVisible().equals('S'))
			{
				auxCategoria.setVisibilidad(true);
			}	
			else if(categoria.getVisible().equals('N'))
			{
				auxCategoria.setVisibilidad(false);
			}	
		}
		else
		{
			auxCategoria.setVisibilidad(false);
		}
		return auxCategoria;
	}
	
	/**
	 * Metodo para pasar un objeto categoria a categoriaBean.
	 *
	 * @param categoria cargado con los datos necesarios
	 * @return CategoriaBean parseado
	 */
	private CategoriaBean toCategoriaBean(Categoria categoria) {		
		
		CategoriaBean auxCategoriaBean = new CategoriaBean();
		auxCategoriaBean.setId(categoria.getId());		
		auxCategoriaBean.setCodigo(categoria.getCodigo());
		auxCategoriaBean.setDescripcion(categoria.getDescripcion());	
		
		if(categoria.getVisible() != null)
		{	
			if(categoria.getVisible().equals('S'))
			{
				auxCategoriaBean.setVisibilidad(true);
			}	
			else if(categoria.getVisible().equals('N'))
			{
				auxCategoriaBean.setVisibilidad(false);
			}	
		}
		else
		{
			auxCategoriaBean.setVisibilidad(false);
		}
		
		return auxCategoriaBean;
	}
	
	/**
	 * Metodo para pasar de categoriaBean a categoria.
	 *
	 * @param categoriaBean cargado con los datos necesarios
	 * @return categoria parseado
	 */
	public Categoria toCategoria(CategoriaBean categoriaBean){
		Categoria categoria = new Categoria();
		
		categoria.setId(categoriaBean.getId());	
		categoria.setCodigo(categoriaBean.getCodigo());
		categoria.setDescripcion(categoriaBean.getDescripcion());
		categoria.setEstado(categoriaBean.getEstado());
		if(categoriaBean.getVisibilidad() != null)
		{
			if(categoriaBean.getVisibilidad() == true)
			{	
				categoria.setVisible('S');
			}
			else if(categoriaBean.getVisibilidad() == false)
			{
				categoria.setVisible('N');
			}	
		}
		else
		{
			categoria.setVisible('N');
		}
		return categoria;
	}
	
	/**
	 * Buscar categoria.
	 *
	 * @param categoriaQuery el categoria query
	 * @return el search result
	 */
	private SearchResult<Categoria> buscarCategoria(CategoriaQuery categoriaQuery) {
		
			ApplicationException.assertNotNull(categoriaQuery, "categoriaQuery no puede ser null");
		
		return categoriaDAO.search(categoriaQuery);
	}

	/**
	 * To categoria combo bean.
	 *
	 * @param categoria Categoria
	 * @return categoriaBean CategoriaBean
	 */
	public CategoriaBean toCategoriaComboBean(Categoria categoria) {		
	
		CategoriaBean categoriaBean= new CategoriaBean();
		
		categoriaBean.setDescripcion(categoria.getDescripcion());
		categoriaBean.setId(categoria.getId());
		categoriaBean.setCodigo(categoria.getCodigo());	
		categoriaBean.setEstado(categoria.getEstado());
	
	    return categoriaBean;
	}

	/**
	 * Obtiene el cuerpo escala DAO.
	 *
	 * @return categoriaDAO CategoriaDAO
	 */
	public CategoriaDAO getCuerpoEscalaDAO() {
		return categoriaDAO;
	}

	/**
	 * Establece el categoria DAO.
	 *
	 * @param categoriaDAO CategoriaDAO
	 */
	public void setCategoriaDAO(CategoriaDAO categoriaDAO) {
		this.categoriaDAO = categoriaDAO;
	}

}	
	
