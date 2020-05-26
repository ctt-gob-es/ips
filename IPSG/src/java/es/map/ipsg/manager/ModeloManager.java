package es.map.ipsg.manager;

import java.util.ArrayList;
import java.util.List;

import es.map.ips.model.Modelo;
import es.map.ips.model.ModeloQuery;
import es.map.ipsg.bean.ModeloBean;


/**
 * El Interface ModeloManager.
 */
public interface ModeloManager {

		/**
		 * Buscar modelo combo todos.
		 *
		 * @return el array list
		 */
		public ArrayList<ModeloBean> buscarModeloComboTodos();

		/**
		 * Buscar modelo combo.
		 *
		 * @param modeloQuery el modelo query
		 * @return el list
		 */
		public List<ModeloBean> buscarModeloCombo(ModeloQuery modeloQuery);

		/**
		 * Buscar modelos all.
		 *
		 * @param modeloQuery el modelo query
		 * @return el array list
		 */
		public ArrayList<ModeloBean> buscarModelosAll(ModeloQuery modeloQuery);

		/**
		 * Guardar modelo.
		 *
		 * @param modeloBean el modelo bean
		 * @return el integer
		 */
		public Integer guardarModelo(ModeloBean modeloBean);

		/**
		 * Obtener modelo 790 by id.
		 *
		 * @param valueOf el value of
		 * @return el modelo bean
		 */
		public ModeloBean obtenerModelo790ById(Integer valueOf);
		
		/**
		 * Conseguir modelo 790 by id.
		 *
		 * @param valueOf el value of
		 * @return el modelo
		 */
		public Modelo conseguirModelo790ById(Integer valueOf);

		/**
		 * Modificar modelo 790.
		 *
		 * @param modeloBean el modelo bean
		 */
		public void modificarModelo790(ModeloBean modeloBean);

}
