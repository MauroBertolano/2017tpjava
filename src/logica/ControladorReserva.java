package logica;

import java.util.ArrayList;

import data.DataReserva;
import entidades.Elemento;
import entidades.Reserva;

public class ControladorReserva {
	DataReserva dataRes = new DataReserva();
	
	public  ArrayList<Elemento> getDisponibles(Reserva res)throws Exception {
		return (this.dataRes.getDisponibles(res));	
	}

	public void addReserva(Reserva res) throws Exception {
		this.dataRes.add(res);		
	}

	public ArrayList<Reserva> getAll() throws Exception {
		return this.dataRes.getAll();
	}

	public void remove(Reserva reserva) throws Exception {
		dataRes.remove(reserva);
	}
	

}
