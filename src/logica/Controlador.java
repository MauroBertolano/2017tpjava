package logica;

import java.util.ArrayList;

import data.DataPersona;
import entidades.Persona;

public class Controlador {
	private ArrayList<Persona> pers;
	DataPersona dataPer= new DataPersona();

	public Controlador() {
		
		pers = new ArrayList<Persona>();
		pers.add(new Persona("Juan", "Perez", "12121212", true));
		pers.add(new Persona("Fulano", "De Tal", "13131313", true));
	}

	public void add(Persona p) {
		this.pers.add(p);
	}

	public Persona getByDni(Persona p) {
		/*String dni = p.getDni();
		for (int i = 0; i < this.pers.size(); i++) {
			if (pers.get(i).getDni().equals(dni)) {
				return pers.get(i);
			}
		}
		return 	null;*/
		return dataPer.getByDni(p);
	}

	public void borrar(Persona p) {
		pers.remove(this.getByDni(p));
	}

	public void actualiza(Persona p) {
		this.borrar(p);
		this.add(p);
	}
	public ArrayList<Persona> getPersonas(){
		return pers;
	}

	public Persona getByNombreApellido(Persona p) {
		for(int i=0;i<pers.size();i++){
			if(pers.get(i).getNombre().equalsIgnoreCase(p.getNombre()) && pers.get(i).getApellido().equalsIgnoreCase(p.getApellido())){
				return pers.get(i);
			}
		}
		return null;
	}
	public ArrayList<Persona> getAll(){
		return dataPer.getAll();
}
	
}