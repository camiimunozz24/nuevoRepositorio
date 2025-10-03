package unlam.edu.ar;

public class Suite extends Habitacion {

	public Suite(Integer cantNoches, Integer cantPersonas) {
		super(cantNoches, cantPersonas);
		super.setPrecio(getPrecio() * 2);
	}

	@Override
	public Double calcularPrecioFinal() {
		return getPrecio() * getCantNoches() * getCantPersonas();
	}

}
