package es.map.ipsc.manager.modelo;

import java.util.ArrayList;
import java.util.List;

import es.map.ips.model.Modelo;
import es.map.ips.model.ModeloQuery;
import es.map.ipsc.bean.ModeloBean;


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
		 * @return el list
		 */
		public List<ModeloBean> buscarModelosAll(ModeloQuery modeloQuery);
		
		/**
		 * Buscar modelo by num modelo.
		 *
		 * @param modeloQuery el modelo query
		 * @return el modelo
		 */
		public Modelo buscarModeloByNumModelo(ModeloQuery modeloQuery);
}
