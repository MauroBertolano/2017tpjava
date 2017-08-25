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
	

}
