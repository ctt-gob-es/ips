package es.map.ipsc.bean;

public class ConfiguracionErroresBean {
	private Integer id;
	private String descripcion;
	private Boolean visibilidad;
	private Integer numTotal;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getVisibilidad() {
		return visibilidad;
	}
	public void setVisibilidad(Boolean visibilidad) {
		this.visibilidad = visibilidad;
	}
	public Integer getNumTotal() {
		return numTotal;
	}
	public void setNumTotal(Integer numTotal) {
		this.numTotal = numTotal;
	}
	
}
