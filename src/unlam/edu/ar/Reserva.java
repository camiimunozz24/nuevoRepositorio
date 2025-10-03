package unlam.edu.ar;

import java.time.LocalDate;

public class Reserva {

	private Integer id;
	private static Integer proximoId = 0;
	private Cliente titular;
	private LocalDate fechaIngreso;
	private LocalDate fechaEgreso;
	private Habitacion habitacion;

	public Reserva(Cliente titular, LocalDate fechaIngreso, LocalDate fechaEgreso, Habitacion habitacion) {
		this.id = ++proximoId;
		this.titular = titular;
		this.fechaIngreso = fechaIngreso;
		this.fechaEgreso = fechaEgreso;
		this.habitacion = habitacion;
	}

	public Cliente getTitular() {
		return titular;
	}

	public void setTitular(Cliente titular) {
		this.titular = titular;
	}

	public LocalDate getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(LocalDate fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public LocalDate getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(LocalDate fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public Habitacion getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(Habitacion habitacion) {
		this.habitacion = habitacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	

}
