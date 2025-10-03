package unlam.edu.ar;

public class Estandar extends Habitacion {

	public Estandar(Integer cantNoches, Integer cantPersonas) {
		super(cantNoches, cantPersonas);
		super.getPrecio();
	}

	@Override
	public Double calcularPrecioFinal() {
		return getPrecio() * getCantPersonas() * getCantNoches();
	}

}
