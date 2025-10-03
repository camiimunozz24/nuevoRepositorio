package unlam.edu.ar;

public abstract class Habitacion {
	
	private Integer cantNoches;
	private Integer cantPersonas;
	private Double precio;
	
	
	public Habitacion(Integer cantNoches, Integer cantPersonas) {
		this.cantNoches = cantNoches;
		this.cantPersonas = cantPersonas;
		this.precio = 500.0;
	}


	public Integer getCantNoches() {
		return cantNoches;
	}


	public void setCantNoches(Integer cantNoches) {
		this.cantNoches = cantNoches;
	}


	public Integer getCantPersonas() {
		return cantPersonas;
	}


	public void setCantPersonas(Integer cantPersonas) {
		this.cantPersonas = cantPersonas;
	}


	public Double getPrecio() {
		return precio;
	}


	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	
	public abstract Double calcularPrecioFinal();
	
	

}
