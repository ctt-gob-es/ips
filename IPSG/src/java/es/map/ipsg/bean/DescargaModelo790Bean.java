package es.map.ipsg.bean;

import java.util.Date;

import es.map.ips.model.Convocatoria;


/**
 * ConvocatoriasViewBean.
 *
 * @author amartinl
 */
public class DescargaModelo790Bean {
	
	/** el id. */
	private Integer id;
	
	/** el convocatoria. */
	private Convocatoria convocatoria;
	
	/** el fecha. */
	private Date fecha;
	
	
	/**
	 * Obtiene el id.
	 *
	 * @return  id Integer
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Establece el id.
	 *
	 * @param id Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el convocatoria.
	 *
	 * @return convocatoria Convocatoria
	 */
	public Convocatoria getConvocatoria() {
		return convocatoria;
	}
	
	/**
	 * Establece el convocatoria.
	 *
	 * @param convocatoria Convocatoria
	 */
	public void setConvocatoria(Convocatoria convocatoria) {
		this.convocatoria = convocatoria;
	}
	
	/**
	 * Obtiene el fecha.
	 *
	 * @return  fecha Date
	 */
	public Date getFecha() {
		return fecha;
	}
	
	/**
	 * Establece el fecha.
	 *
	 * @param fecha Date
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
    
  
}
