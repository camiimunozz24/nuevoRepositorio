package unlam.edu.ar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class ReservaTest {
	
	
	private SistemaDeReserva sistema;
	
	@Before
	public void init() {
		this.sistema = new SistemaDeReserva();
	}
	/*
	 * Construir un Sistema para administrar las reservas de habitaciones. Tenemos las Habitaciones estandars y las de lujos  
	 * Cada habitacion debe calcular suPrecio en funcion  la cantidad de Hspedes Por el tipo habitacion. las habitaciones de lujos 
	 * cuestan e doble que las estandars 
	 * Ejemplo-> cantidad Precio * cantidadDePersonasMaximas * coheficiente del tipo Habitacion (1 Para las St y 2 Par de lujo)
	 * Para cada reserva se debe registrar id(aoutoincremental), TitularDeLaReserva , fechaIngreso, fechaEgreso, Habitacion
	 * Para realizar la Reserva se debe pasar el (cliente, fecha ingreso, numeroHabitacion)
	 * Para cerrar la reserva debe pasar idReserva y la fecha de Checkuot
	 * Obtener precio de la reserva (id) y este va a calcular el precio de la habitacion por la cantidad de dias que duro la reserva 
	 * Si la fecha de checkout es nulo devuele null
	 * No se puede cerrar una reserva si la fecha de salida es anterior a la de entrada
	 * No se puede realizar una reserva para un cliente si ya tiene un reserva activa
	 * Obtener un listado de las reservas vigentes
	 * Obtener un listado de clientes que hicieron reservas
	 */
	
	@Test
	public void dadoQueExisteUnaHabitacionEstandarQueCuesta500LaNocheCalcularElPrecioPara3HuespedesPara5Noches() {
		Integer cantNoches = 5;
		Integer cantHuespedes = 3;
		Habitacion estandar = new Estandar(cantNoches, cantHuespedes);
		
		Double precioEsperado = 7500.0;
		Double precioObtenido = estandar.calcularPrecioFinal();
		
		assertEquals(precioEsperado, precioObtenido);
		
	}
	
	@Test
	public void dadoQueExisteUnaHabitacionSuiteQueCuesta1000LaNocheCalcularElPrecioPara2HuespedesPara3Noches() {
		Integer cantNoches = 3;
		Integer cantHuespedes = 2;
		Habitacion suite = new Suite(cantNoches, cantHuespedes);
		
		Double precioEsperado = 6000.0;
		Double precioObtenido = suite.calcularPrecioFinal();
		
		assertEquals(precioEsperado, precioObtenido);	
	}
	
	@Test
	public void realizarReserva() {
		String nombre = "Paulina";
		Integer dni = 589;
		Cliente titular = new Cliente(nombre, dni);
		LocalDate fechaIngreso = LocalDate.of(2025, 9, 14);
		LocalDate fechaEgreso = LocalDate.of(2025, 9, 24);
		Integer cantNoches = 3;
		Integer cantHuespedes = 2;
		Habitacion suite = new Suite(cantNoches, cantHuespedes);
		
		Reserva nuevaReserva = new Reserva(titular, fechaIngreso, fechaEgreso, suite);
		
		Boolean registrada = sistema.registrarReserva(nuevaReserva);
		
		assertTrue(registrada);
	}
	
	@Test
	public void dadoQueSeRegistraUnaReservaEnElSistemaLaMismaPuedeSerCerradaExitosamente() {
		String nombre = "Paulina";
		Integer dni = 589;
		Cliente titular = new Cliente(nombre, dni);
		LocalDate fechaIngreso = LocalDate.of(2025, 9, 14);
		LocalDate fechaEgreso = LocalDate.of(2025, 9, 24);
		Integer cantNoches = 3;
		Integer cantHuespedes = 2;
		Habitacion suite = new Suite(cantNoches, cantHuespedes);
		
		Reserva nuevaReserva = new Reserva(titular, fechaIngreso, fechaEgreso, suite);
		
		sistema.registrarReserva(nuevaReserva);
		
		Boolean cerrada = sistema.cerrarReserva(nuevaReserva.getId(), nuevaReserva.getFechaEgreso());
		
		assertTrue(cerrada);
	}
	
	@Test
	public void dadoQueExisteUnaReservaSePuedeCalcularElPrecioDeLaHabitacionPorCantidadDeDias() {
		String nombre = "Paulina";
		Integer dni = 589;
		Cliente titular = new Cliente(nombre, dni);
		LocalDate fechaIngreso = LocalDate.of(2025, 9, 14);
		LocalDate fechaEgreso = LocalDate.of(2025, 9, 24);
		Integer cantNoches = 5;
		Integer cantHuespedes = 2;
		Habitacion suite = new Suite(cantNoches, cantHuespedes);
		
		Reserva nuevaReserva = new Reserva(titular, fechaIngreso, fechaEgreso, suite);
		
		sistema.registrarReserva(nuevaReserva);
		
		Double montoEsperado = 10000.0;
		Double montoFinal = sistema.calcularMontoReserva(nuevaReserva.getId());
		
		assertEquals(montoEsperado, montoFinal);
	}
	
	@Test
	public void noSePuedeCerrarUnaReservaSiLaFechaDeSalidaEsAnteriorALaFechaDeEntrada() {
		String nombre = "Paulina";
		Integer dni = 589;
		Cliente titular = new Cliente(nombre, dni);
		LocalDate fechaIngreso = LocalDate.of(2025, 9, 24);
		LocalDate fechaEgreso = LocalDate.of(2025, 9, 14);
		Integer cantNoches = 3;
		Integer cantHuespedes = 2;
		Habitacion suite = new Suite(cantNoches, cantHuespedes);
		
		Reserva nuevaReserva = new Reserva(titular, fechaIngreso, fechaEgreso, suite);
		
		sistema.registrarReserva(nuevaReserva);
		
		Boolean cerrada = sistema.cerrarReserva(nuevaReserva.getId(), nuevaReserva.getFechaEgreso());
		
		assertFalse(cerrada);
	}
	
	@Test
	public void noSePuedeRealizarUnaReservaAUnClienteQueAunTieneUnaReservaActiva() {
		String nombre = "Paulina";
		Integer dni = 589;
		Cliente titular = new Cliente(nombre, dni);
		LocalDate fechaIngreso = LocalDate.of(2025, 9, 14);
		LocalDate fechaEgreso = LocalDate.of(2025, 9, 24);
		Integer cantNoches = 3;
		Integer cantHuespedes = 2;
		Habitacion suite = new Suite(cantNoches, cantHuespedes);
		
		Reserva nuevaReserva = new Reserva(titular, fechaIngreso, fechaEgreso, suite);
		
		Habitacion estandar = new Estandar(cantNoches, cantHuespedes);
		LocalDate fechaIngreso2 = LocalDate.of(2025, 9, 15);
		LocalDate fechaEgreso2 = LocalDate.of(2025, 9, 30);
		
		Reserva nuevaReserva2 = new Reserva(titular, fechaIngreso2, fechaEgreso2, estandar);
		
		Boolean registrada = sistema.registrarReserva(nuevaReserva);
		Boolean registrada2 = sistema.registrarReserva(nuevaReserva2);
		
		assertTrue(registrada);
		assertFalse(registrada2);
	}
	
	@Test
	public void obtenerUnListadoDeLasReservasVigentes() {
		String nombre = "Paulina";
		Integer dni = 589;
		Cliente titular = new Cliente(nombre, dni);
		LocalDate fechaIngreso = LocalDate.of(2025, 9, 14);
		LocalDate fechaEgreso = LocalDate.of(2025, 9, 24);
		Integer cantNoches = 3;
		Integer cantHuespedes = 2;
		Habitacion suite = new Suite(cantNoches, cantHuespedes);
		
		Reserva nuevaReserva = new Reserva(titular, fechaIngreso, fechaEgreso, suite);
		
		String nombre2 = "Enzo";
		Integer dni2 = 656;
		Cliente titular2 = new Cliente(nombre2, dni2);
		LocalDate fechaIngreso2 = LocalDate.of(2025, 9, 16);
		LocalDate fechaEgreso2 = LocalDate.of(2025, 9, 30);
		Integer cantNoches2 = 4;
		Integer cantHuespedes2 = 3;
		Habitacion estandar = new Estandar(cantNoches2, cantHuespedes2);
		
		Reserva nuevaReserva2 = new Reserva(titular2, fechaIngreso2, fechaEgreso2, estandar);
		
		sistema.registrarReserva(nuevaReserva);
		sistema.registrarReserva(nuevaReserva2);
		
		HashSet<Reserva> reservasEsperadas = new HashSet<>();
		reservasEsperadas.add(nuevaReserva2);
		
		HashSet<Reserva> reservasObtenidas = sistema.obtenerReservasVigentes();
		
		assertEquals(reservasEsperadas, reservasObtenidas);
	}
	
	@Test
	public void obtenerUnListadoDeLosClientesQueRealizaronReservas() {
		String nombre = "Paulina";
		Integer dni = 589;
		Cliente titular = new Cliente(nombre, dni);
		LocalDate fechaIngreso = LocalDate.of(2025, 9, 14);
		LocalDate fechaEgreso = LocalDate.of(2025, 9, 24);
		Integer cantNoches = 3;
		Integer cantHuespedes = 2;
		Habitacion suite = new Suite(cantNoches, cantHuespedes);
		
		Reserva nuevaReserva = new Reserva(titular, fechaIngreso, fechaEgreso, suite);
		
		String nombre2 = "Enzo";
		Integer dni2 = 656;
		Cliente titular2 = new Cliente(nombre2, dni2);
		LocalDate fechaIngreso2 = LocalDate.of(2025, 9, 16);
		LocalDate fechaEgreso2 = LocalDate.of(2025, 9, 30);
		Integer cantNoches2 = 4;
		Integer cantHuespedes2 = 3;
		Habitacion estandar = new Estandar(cantNoches2, cantHuespedes2);
		
		Reserva nuevaReserva2 = new Reserva(titular2, fechaIngreso2, fechaEgreso2, estandar);
		
		sistema.registrarReserva(nuevaReserva);
		sistema.registrarReserva(nuevaReserva2);
		
		HashSet<Cliente> clientesEsperados = new HashSet<>();
		clientesEsperados.add(titular);
		clientesEsperados.add(titular2);
		
		HashSet<Cliente> clientesObtenidos = sistema.obtenerClientesConReservas();
		
		assertEquals(clientesEsperados, clientesObtenidos);
	}

}
