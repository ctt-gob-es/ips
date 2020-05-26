package es.map.ipsg.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * El Class IntermediacionDesempleoBean.
 */
public class IntermediacionDesempleoBean {
	
	/** el dias demandante empleo. */
	private Integer diasDemandanteEmpleo;
	
	/** el salario demandante empleo. */
	private Float salarioDemandanteEmpleo;
	
	/** el resultado verificacion dias. */
	private boolean resultadoVerificacionDias;
	
	/** el resultado verificacion salario. */
	private boolean resultadoVerificacionSalario;
	
	/** el descripcion error dias. */
	private String descripcionErrorDias;
	
	/** el descripcion error importe. */
	private String descripcionErrorImporte;

	/** el descripcion error ejercicio. */
	private String ejercicio;
	
	/** el tipo IM. */
	private boolean tipoIM;
	
	private List<ImputacionesBean> listaImputaciones= new ArrayList<ImputacionesBean>();


    /**
     * Obtiene si la verificación de los dias de desempleo es positiva o no.
     * 
     * @return resultadoVerificacionDias
     */
	public boolean isResultadoVerificacionDias() {
		return resultadoVerificacionDias;
	}

	/**
	 * Establece el resultado verificacion dias.
	 *
	 * @param resultadoVerificacionDias el nuevo resultado verificacion dias
	 */
	public void setResultadoVerificacionDias(boolean resultadoVerificacionDias) {
		this.resultadoVerificacionDias = resultadoVerificacionDias;
	}
	
    /**
     * Obtiene si la verificación del Salario cobrado durante el desempleo es positiva o no.
     * 
     * @return resultadoVerificacionSalario
     */
	public boolean isResultadoVerificacionSalario() {
		return resultadoVerificacionSalario;
	}

	/**
	 * Establece el resultado verificacion salario.
	 *
	 * @param resultadoVerificacionSalario el nuevo resultado verificacion salario
	 */
	public void setResultadoVerificacionSalario(boolean resultadoVerificacionSalario) {
		this.resultadoVerificacionSalario = resultadoVerificacionSalario;
	}


	/**
	 * Obtiene el dias demandante empleo.
	 *
	 * @return el dias demandante empleo
	 */
	public Integer getDiasDemandanteEmpleo() {
		return diasDemandanteEmpleo;
	}

	/**
	 * Establece el dias demandante empleo.
	 *
	 * @param diasDemandanteEmpleo el nuevo dias demandante empleo
	 */
	public void setDiasDemandanteEmpleo(Integer diasDemandanteEmpleo) {
		this.diasDemandanteEmpleo = diasDemandanteEmpleo;
	}

	/**
	 * Obtiene el salario demandante empleo.
	 *
	 * @return el salario demandante empleo
	 */
	public Float getSalarioDemandanteEmpleo() {
		return salarioDemandanteEmpleo;
	}

	/**
	 * Establece el salario demandante empleo.
	 *
	 * @param salarioDemandanteEmpleo el nuevo salario demandante empleo
	 */
	public void setSalarioDemandanteEmpleo(Float salarioDemandanteEmpleo) {
		this.salarioDemandanteEmpleo = salarioDemandanteEmpleo;
	}

	/**
	 * Obtiene el descripcion error dias.
	 *
	 * @return el descripcion error dias
	 */
	public String getDescripcionErrorDias() {
		return descripcionErrorDias;
	}

	/**
	 * Establece el descripcion error dias.
	 *
	 * @param descripcionErrorDias el nuevo descripcion error dias
	 */
	public void setDescripcionErrorDias(String descripcionErrorDias) {
		this.descripcionErrorDias = descripcionErrorDias;
	}

	/**
	 * Obtiene el descripcion error importe.
	 *
	 * @return el descripcion error importe
	 */
	public String getDescripcionErrorImporte() {
		return descripcionErrorImporte;
	}

	/**
	 * Establece el descripcion error importe.
	 *
	 * @param descripcionErrorImporte el nuevo descripcion error importe
	 */
	public void setDescripcionErrorImporte(String descripcionErrorImporte) {
		this.descripcionErrorImporte = descripcionErrorImporte;
	}

	public String getEjercicio() {
		return ejercicio;
	}

	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
	}

	/**
	 * @return the listaImputaciones
	 */
	public List<ImputacionesBean> getListaImputaciones() {
		return listaImputaciones;
	}

	/**
	 * @param listaImputaciones the listaImputaciones to set
	 */
	public void setListaImputaciones(List<ImputacionesBean> listaImputaciones) {
		this.listaImputaciones = listaImputaciones;
	}

	/**
	 * @return the tipoIM
	 */
	public boolean isTipoIM() {
		return tipoIM;
	}

	/**
	 * @param tipoIM the tipoIM to set
	 */
	public void setTipoIM(boolean tipoIM) {
		this.tipoIM = tipoIM;
	}
	
}
