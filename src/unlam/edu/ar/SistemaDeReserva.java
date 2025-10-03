package unlam.edu.ar;

import java.time.LocalDate;
import java.util.HashSet;

public class SistemaDeReserva {

	private HashSet<Reserva> reservas;
	
	public SistemaDeReserva () {
		this.reservas = new HashSet<>();
	}

	public Boolean registrarReserva(Reserva nuevaReserva) {
		for (Reserva reserva : reservas) {
			if(reserva.getTitular().equals(nuevaReserva.getTitular())) {
				return false;
			}
		}
		return reservas.add(nuevaReserva);
	}

	public Boolean cerrarReserva(Integer id, LocalDate fechaEgreso) {
		for (Reserva reserva : reservas) {
			if(reserva.getId().equals(id) && reserva.getFechaEgreso().equals(fechaEgreso) && reserva.getFechaEgreso().isAfter(reserva.getFechaIngreso()) && fechaEgreso.isBefore(LocalDate.now())) {
				return true;
			}
		}
		return false;
	}

	public Double calcularMontoReserva(Integer id) {
		for (Reserva reserva : reservas) {
			if(reserva.getId().equals(id) && reserva.getFechaEgreso() != null) {
				return reserva.getHabitacion().calcularPrecioFinal();
			}
		}
		return null;
	}

	public HashSet<Cliente> obtenerClientesConReservas() {
		HashSet<Cliente> clientesConReservas = new HashSet<>();
		for (Reserva reserva : reservas) {
			Cliente cliente = reserva.getTitular();
			clientesConReservas.add(cliente);
		}
		return clientesConReservas;
	}
	
	public HashSet<Reserva> obtenerReservasVigentes() {
		HashSet<Reserva> reservasVigentes = new HashSet<>();
		for (Reserva reserva : reservas) {
			if(reserva.getFechaEgreso().isAfter(LocalDate.now())) {
				reservasVigentes.add(reserva);
			}
		}
		return reservasVigentes;
	}

}
